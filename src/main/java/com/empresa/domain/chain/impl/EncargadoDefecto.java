package com.empresa.domain.chain.impl;

import com.empresa.domain.Excusa;
import com.empresa.domain.chain.Encargado;

public class EncargadoDefecto extends Encargado {
    public EncargadoDefecto(String nombre, String email, int nroLegajo) {
        super(nombre, email, nroLegajo);
    }

    @Override
    public Boolean puedeManejar(Excusa excusa) {
        return true;
    }

    @Override
    protected void procesar(Excusa excusa) {
        System.out.println("Encargado Defecto: Rechazada.");
        excusa.setAceptada(false);
    }
}
