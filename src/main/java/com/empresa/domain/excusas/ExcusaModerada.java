package com.empresa.domain.excusas;

import com.empresa.domain.Empleado;
import com.empresa.domain.Excusa;

/**
 * Базовый класс для умеренных оправданий.
 */
public abstract class ExcusaModerada extends Excusa {

    public ExcusaModerada(Empleado empleado) {
        super(empleado);
    }

    @Override
    public boolean esModerada() {
        return true;
    }
}
