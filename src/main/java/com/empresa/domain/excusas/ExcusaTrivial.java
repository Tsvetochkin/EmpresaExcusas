package com.empresa.domain.excusas;

import com.empresa.domain.EmailSender;
import com.empresa.domain.Empleado;
import com.empresa.domain.Excusa;

/**
 * Тривиальное оправдание. 
 * Принимается автоматически без лишних проверок.
 */
public class ExcusaTrivial extends Excusa {

    public ExcusaTrivial(Empleado empleado) {
        super(empleado); // Передаем сотрудника наверх родителю
    }

    @Override
    public boolean esTrivial() {
        return true;
    }

    @Override
    public void ejecutarAccion(EmailSender emailSender) {
        // Логика: Тривиальное оправдание просто уведомляет по почте
        emailSender.enviarEmail(
            getEmpleado().getEmail(), 
            "sistema@empresa.com", 
            "Excusa Trivial Aceptada", 
            "Tu excusa ha sido procesada automáticamente."
        );
    }
}
