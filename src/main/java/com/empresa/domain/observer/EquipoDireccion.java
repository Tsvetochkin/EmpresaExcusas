package com.empresa.domain.observer;

import com.empresa.domain.Prontuario;

public class EquipoDireccion implements IObservador {
    @Override
    public void actualizar(Prontuario prontuario) {
        System.out.println("Equipo de Dirección notificado: El empleado " + 
            prontuario.getEmpleado().getNombre() + 
            " (Legajo: " + prontuario.getNroLegajo() + ") presentó una excusa.");
    }
}
