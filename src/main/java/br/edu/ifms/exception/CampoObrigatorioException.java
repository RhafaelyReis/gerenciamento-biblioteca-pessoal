package br.edu.ifms.exception;

public class CampoObrigatorioException extends Exception {
    @Override
    public String getMessage() {
        return "Os campo obrigatórios não podem estar vazios.";
    }
}