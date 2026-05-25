package com.empresa;

import com.empresa.controller.ExcusaController;
import com.empresa.builder.CadenaEncargadosBuilder;
import com.empresa.domain.*;
import com.empresa.domain.chain.impl.*;
import com.empresa.domain.excusas.*;
import com.empresa.domain.observer.*;
import com.empresa.domain.state.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EmpresaIntegrationTest {

    private ExcusaController controller;
    private CEO ceo;
    private EquipoDireccion equipoDireccion;

    @BeforeEach
    void setUp() {
        // Настраиваем цепочку через Builder
        ceo = new CEO("Elon", "elon@empresa.com", 1);
        
        controller = new CadenaEncargadosBuilder()
            .agregar(new Recepcionista("Ana", "ana@empresa.com", 101))
            .agregar(new Supervisora("Berta", "berta@empresa.com", 102))
            .agregar(new GerenteRRHH("Carlos", "carlos@empresa.com", 103))
            .agregar(ceo)
            .agregar(new EncargadoDefecto("Bot", "bot@empresa.com", 999))
            .build();

        // Настраиваем Observer
        equipoDireccion = new EquipoDireccion();
        AdministradorProntuarios.getInstancia().agregarObservador(equipoDireccion);
        AdministradorProntuarios.getInstancia().agregarObservador(ceo);
    }

    @Test
    void testCEO_GeneratesProntuario_AndNotifiesObservers() {
        Empleado emp = new Empleado("Juan", "juan@mail.com", 500);
        Excusa excusa = new ExcusaInverosimil(emp);

        // Перед обработкой пронтуариев нет (или запоминаем текущее кол-во)
        int initialSize = AdministradorProntuarios.getInstancia().getProntuarios().size();

        controller.manejar(excusa);

        // Проверяем, что пронтуарий создался
        assertEquals(initialSize + 1, AdministradorProntuarios.getInstancia().getProntuarios().size());
        assertTrue(excusa.isAceptada());
    }

    @Test
    void testStateTransitions() {
        Empleado emp = new Empleado("Juan", "juan@mail.com", 500);
        Recepcionista recep = new Recepcionista("Ana", "ana@empresa.com", 101);
        
        // Рецепционист начинает в ModoProductivo (0-5 excusas)
        assertTrue(recep.getDeliveryModos().getModoActual() instanceof ModoProductivo);

        // Пропускаем 5 отговорок (тривиальных, чтобы он их процессил)
        for (int i = 0; i < 5; i++) {
            recep.manejar(new ExcusaTrivial(emp));
        }
        
        // После 5-й он должен стать Normal (так как 5-я инкрементирует до 5, а условие в DeliveryModos: <=5 Productivo)
        // На самом деле, после 5-й (т.е. когда станет 6) он должен стать Normal.
        // Проверим текущую логику: if (excusasProcesadas <= 5) Productivo else if (<= 10) Normal
        
        assertTrue(recep.getDeliveryModos().getModoActual() instanceof ModoProductivo);
        
        recep.manejar(new ExcusaTrivial(emp)); // 6-я отговорка
        assertTrue(recep.getDeliveryModos().getModoActual() instanceof ModoNormal);

        // Пропускаем еще 5 (всего 11)
        for (int i = 0; i < 5; i++) {
            recep.manejar(new ExcusaTrivial(emp));
        }
        
        // Теперь он должен быть Vago (>10)
        assertTrue(recep.getDeliveryModos().getModoActual() instanceof ModoVago);
    }

    @Test
    void testVagoMode_DerivesAutomatically() {
        Empleado emp = new Empleado("Juan", "juan@mail.com", 500);
        Recepcionista recep = new Recepcionista("Ana", "ana@empresa.com", 101);
        Supervisora superv = new Supervisora("Berta", "berta@empresa.com", 102);
        recep.setSiguiente(superv);

        // Переводим в Vago (больше 10 отговорок)
        for (int i = 0; i < 11; i++) {
            recep.manejar(new ExcusaTrivial(emp));
        }
        
        assertTrue(recep.getDeliveryModos().getModoActual() instanceof ModoVago);

        // Подаем тривиальную отговорку. Vago должен её передать, хотя он МОЖЕТ её решить.
        ExcusaTrivial trivial = new ExcusaTrivial(emp);
        recep.manejar(trivial);
        
        // Если бы он её решил, она была бы принята. Но Vago просто передает дальше.
        // Supervisora её не примет (так как она не тривиальная для неё).
        // В итоге она должна дойти до конца или остаться не принятой.
        assertFalse(trivial.isAceptada());
    }
}
