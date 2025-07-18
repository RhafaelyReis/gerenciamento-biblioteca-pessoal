package br.edu.ifms.exception;

public class DispositivoInvalidoException extends Exception {
    @Override
    public String getMessage() {
        return "Dispositivo inválido. Os dispositivos válidos são: Kindle, Tablet, Smartphone, Computador e E-reader";
    }
}
