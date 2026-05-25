package com.empresa.domain.chain.impl;

import com.empresa.domain.Excusa;
import com.empresa.domain.Prontuario;
import com.empresa.domain.chain.Encargado;
import com.empresa.domain.observer.AdministradorProntuarios;
import com.empresa.domain.observer.IObservador;

public class CEO extends Encargado implements IObservador {

    public CEO(String nombre, String email, int nroLegajo) {
        super(nombre, email, nroLegajo);
    }

    @Override
    public Boolean puedeManejar(Excusa excusa) {
        return excusa.esInverosimil();
    }

    @Override
    protected void procesar(Excusa excusa) {
        System.out.println("CEO procesando excusa inverosímil.");
        excusa.setAceptada(true);
        
        // Текст из ТЗ
        excusa.ejecutarAccion((dest, orig, asunto, cuerpo) -> 
            System.out.println("Email enviado a " + dest + " | Respuesta: Aprobado por creatividad"));
        
        Prontuario prontuario = new Prontuario(excusa.getEmpleado(), excusa);
        AdministradorProntuarios.getInstancia().registrarProntuario(prontuario);
    }

    @Override
    public void actualizar(Prontuario prontuario) {
        System.out.println("CEO notificado: nuevo registro en prontuario.");
    }
}
