package com.empresa.domain.state;

import com.empresa.domain.chain.Encargado;
import com.empresa.domain.Excusa;

public class ModoProductivo implements IModo {
    @Override
    public void manejar(Encargado encargado, Excusa excusa) {
        System.out.println(encargado.getNombre() + " (Productivo)");
        if (encargado.puedeManejar(excusa)) {
            encargado.revisarExcusa(excusa);
        } else {
            System.out.println("Email al CTO: " + encargado.getNombre() + " no pudo procesar.");
            encargado.derivar(excusa);
        }
    }
}
