package com.empresa.controller;

import com.empresa.domain.Excusa;
import com.empresa.domain.chain.Encargado;

/**
 * Реализация контроллера, которая запускает цепочку обязанностей.
 */
public class CadenaDeEncargados implements ExcusaController {
    private Encargado primero;

    public CadenaDeEncargados(Encargado primero) {
        this.primero = primero;
    }

    @Override
    public void manejar(Excusa excusa) {
        if (primero != null) {
            primero.manejar(excusa);
        } else {
            System.out.println("Error: No hay una cadena configurada.");
        }
    }
}
