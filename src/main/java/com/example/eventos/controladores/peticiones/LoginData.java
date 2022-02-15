package com.example.eventos.controladores.peticiones;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class LoginData {
    @NotBlank(message = "El correo no puede estar vacío")
    @Email(message = "El correo no tiene un formato válido")
    private String correo;
    @NotBlank(message = "La contraseña es obligatoria")
    private String password;
}
