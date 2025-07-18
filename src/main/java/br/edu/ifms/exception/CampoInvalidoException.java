package br.edu.ifms.exception;

public class CampoInvalidoException extends Exception{
    @Override
    public String getMessage() {
        return "Erro: o valor informado em um dos campos é inválido. Verifique se o ano é válido!";
    }
}
