package com.salafacil.SalaFacilSpace.exception;

public class LaboratorioNotFoundException extends RuntimeException {
    public LaboratorioNotFoundException(Long id) {
        super("Laboratório com ID " + id + " não encontrado.");
    }
}
