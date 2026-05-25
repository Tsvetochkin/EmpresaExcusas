package com.empresa;

import com.empresa.controller.ExcusaController;
import com.empresa.builder.CadenaEncargadosBuilder;
import com.empresa.domain.*;
import com.empresa.domain.chain.impl.*;
import com.empresa.domain.excusas.*;
import com.empresa.domain.observer.*;

public class Demo {
    public static void main(String[] args) {
        System.out.println("=== Empresa Excusas S.A. System Demo ===\n");

        // 1. Создаем сотрудников
        Empleado juan = new Empleado("Juan Tarde", "juan@empresa.com", 501);

        // 2. Настраиваем наблюдателей
        AdministradorProntuarios admin = AdministradorProntuarios.getInstancia();
        admin.agregarObservador(new EquipoDireccion());
        
        CEO ceo = new CEO("Elon Musk", "elon@empresa.com", 1);
        admin.agregarObservador(ceo);

        // 3. Собираем цепочку
        ExcusaController controller = new CadenaEncargadosBuilder()
            .agregar(new Recepcionista("Ana", "ana@empresa.com", 101))
            .agregar(new Supervisora("Berta", "berta@empresa.com", 102))
            .agregar(ceo)
            .agregar(new EncargadoDefecto("Bot de Rechazo", "bot@empresa.com", 999))
            .build();

        // 4. Тестируем разные отговорки
        System.out.println("--- Caso 1: Excusa Trivial ---");
        controller.manejar(new ExcusaTrivial(juan));

        System.out.println("\n--- Caso 2: Excusa de Luz (Moderada) ---");
        controller.manejar(new ExcusaLuz(juan));

        System.out.println("\n--- Caso 3: Excusa Inverosímil (CEO + Observer + Singleton) ---");
        controller.manejar(new ExcusaInverosimil(juan));

        System.out.println("\n--- Caso 4: Excusa Rechazada ---");
        controller.manejar(new ExcusaCompleja(juan)); // GerenteRRHH не в цепочке

        System.out.println("\n=== Fin del Demo ===");
    }
}
