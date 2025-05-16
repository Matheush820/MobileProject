package com.salafacil.SalaFacilSpace.testes;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class TesteSenha {
    public static void main(String[] args) {
        String senha = "Legiondbd123#";
        String hash = "$2a$10$eHJEQh7lMNJlCTOTWbd0s.sVB7BVOzTAgZ/nbuUvAMgxV8nfcjL52";

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println("Senha confere? " + encoder.matches(senha, hash));
    }
}
