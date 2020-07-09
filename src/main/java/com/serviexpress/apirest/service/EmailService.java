package com.serviexpress.apirest.service;

public interface EmailService {
    public void emailSend(String email,String name, String usuario, String pass);
    public void emailSendReserva(String email,String name, String mensaje, String estado);
    public void emailSendEncuesta(String email,String name, String mensaje, String estado);
}