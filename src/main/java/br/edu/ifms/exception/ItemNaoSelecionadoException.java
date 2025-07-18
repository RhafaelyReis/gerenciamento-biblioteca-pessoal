package br.edu.ifms.exception;

public class ItemNaoSelecionadoException extends Exception {
    @Override
    public String getMessage() {
        return "Nenhum item foi selecionado. Por favor, selecione um item da lista.";
    }
}
