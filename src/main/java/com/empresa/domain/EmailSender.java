package com.empresa.domain;

public interface EmailSender {
    void enviarEmail(String dest, String orig, String asunto, String cuerpo);
}
