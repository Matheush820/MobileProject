package com.salafacil.SalaFacilSpace.exception;

public class ProfessorNotFoundException extends RuntimeException {
    
    // Construtor para id (Long)
    public ProfessorNotFoundException(Long id) {
        super("Professor com id " + id + " não encontrado.");
    }

    // Construtor para mensagem customizada (String)
    public ProfessorNotFoundException(String message) {
        super(message);
    }
}
