package com.empresa.domain.state;

import com.empresa.domain.chain.Encargado;
import com.empresa.domain.Excusa;

public class ModoVago implements IModo {
    @Override
    public void manejar(Encargado encargado, Excusa excusa) {
        System.out.println(encargado.getNombre() + " (Vago) - Derivando...");
        encargado.derivar(excusa);
    }
}
