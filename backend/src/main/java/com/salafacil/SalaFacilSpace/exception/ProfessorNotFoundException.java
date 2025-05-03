package com.salafacil.SalaFacilSpace.exception;

public class ProfessorNotFoundException extends RuntimeException {
    public ProfessorNotFoundException(Long id) {
        super("Professor com ID " + id + " não encontrado.");
    }
}
