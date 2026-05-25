package com.empresa.domain.chain;

import com.empresa.domain.Excusa;

/**
 * Контракт для любого лица, принимающего решения по оправданиям.
 */
public interface IEncargado {
    void revisarExcusa(Excusa excusa);
}
