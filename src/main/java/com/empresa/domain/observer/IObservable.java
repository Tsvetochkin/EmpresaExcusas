package com.empresa.domain.observer;

import com.empresa.domain.Prontuario;

public interface IObservable {
    void agregarObservador(IObservador observador);
    void notificarObservadores(Prontuario prontuario);
}
