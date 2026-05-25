package com.empresa.domain;

public interface IExcusa {
    boolean esTrivial();
    boolean esModerada();
    boolean esCompleja();
    boolean esInverosimil();
    void ejecutarAccion(EmailSender emailSender);
}
