package com.empresa.domain.state;

import com.empresa.domain.chain.Encargado;
import com.empresa.domain.Excusa;

public class ModoNormal implements IModo {
    @Override
    public void manejar(Encargado encargado, Excusa excusa) {
        System.out.println(encargado.getNombre() + " (Normal)");
        encargado.revisarExcusa(excusa);
    }
}
