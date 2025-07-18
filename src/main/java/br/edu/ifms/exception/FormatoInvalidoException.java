package br.edu.ifms.exception;

public class FormatoInvalidoException extends Exception {
    @Override
    public String getMessage() {
        return "Formato inválido. Verifique se os dados estão no formato correto.";
    }
}