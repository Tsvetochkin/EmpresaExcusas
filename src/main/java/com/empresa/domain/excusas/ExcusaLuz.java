package com.empresa.domain.excusas;

import com.empresa.domain.EmailSender;
import com.empresa.domain.Empleado;

/**
 * Оправдание: Отключили свет.
 */
public class ExcusaLuz extends ExcusaModerada {

    public ExcusaLuz(Empleado empleado) {
        super(empleado);
    }

    @Override
    public void ejecutarAccion(EmailSender emailSender) {
        emailSender.enviarEmail(
            getEmpleado().getEmail(),
            "mantenimiento@empresa.com",
            "Reporte de falta de luz",
            "Se ha registrado tu falta por problemas eléctricos. Por favor, adjunta el comprobante de la compañía eléctrica."
        );
    }
}
