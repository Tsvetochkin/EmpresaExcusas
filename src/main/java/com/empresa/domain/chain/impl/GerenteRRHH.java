package com.empresa.domain.chain.impl;

import com.empresa.domain.Excusa;
import com.empresa.domain.chain.Encargado;

public class GerenteRRHH extends Encargado {
    public GerenteRRHH(String nombre, String email, int nroLegajo) {
        super(nombre, email, nroLegajo);
    }

    @Override
    public boolean puedeManejar(Excusa excusa) {
        return excusa.esCompleja();
    }

    @Override
    protected void procesar(Excusa excusa) {
        System.out.println("Gerente RRHH процессирует.");
        excusa.setAceptada(true);
        excusa.ejecutarAccion((dest, orig, asunto, cuerpo) -> System.out.println("Email enviado."));
    }
}
