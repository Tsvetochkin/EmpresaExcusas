package com.empresa.domain.chain.impl;

import com.empresa.domain.Excusa;
import com.empresa.domain.chain.Encargado;

public class Recepcionista extends Encargado {
    public Recepcionista(String nombre, String email, int nroLegajo) {
        super(nombre, email, nroLegajo);
    }

    @Override
    public Boolean puedeManejar(Excusa excusa) {
        return excusa.esTrivial();
    }

    @Override
    protected void procesar(Excusa excusa) {
        System.out.println("Recepcionista procesando.");
        excusa.setAceptada(true);
        excusa.ejecutarAccion((dest, orig, asunto, cuerpo) -> System.out.println("Email enviado."));
    }
}
