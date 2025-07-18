package br.edu.ifms.exception;

import javax.swing.JTable;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class Validador {

    private static final int MAX_DURACAO_AUDIOBOOK_MINUTOS = 50000; // ~833 horas

    /**
     * Garante que um campo de texto não está vazio ou nulo.
     * @param campo O valor a ser validado.
     * @throws CampoObrigatorioException se o campo for inválido.
     */
    public static void validarCampoObrigatorio(String campo) throws CampoObrigatorioException {
        if (campo == null || campo.trim().isEmpty()) {
            throw new CampoObrigatorioException();
        }
    }

    /**
     * Valida múltiplos campos de texto de uma só vez.
     * @param campos Os valores a serem validados.
     * @throws CampoObrigatorioException se algum campo for inválido.
     */
    public static void validarCamposObrigatorios(String... campos) throws CampoObrigatorioException {
        for (String campo : campos) {
            validarCampoObrigatorio(campo);
        }
    }

    /**
     * Garante que uma linha foi selecionada em uma JTable.
     * @param tabela A JTable a ser verificada.
     * @throws ItemNaoSelecionadoException se nenhuma linha estiver selecionada.
     */
    public static void validarItemSelecionado(JTable tabela) throws ItemNaoSelecionadoException {
        if (tabela.getSelectedRow() == -1) {
            throw new ItemNaoSelecionadoException();
        }
    }

    /**
     * Converte uma String para inteiro.
     * @param valor A string a ser convertida.
     * @return O valor inteiro.
     * @throws FormatoInvalidoException se a string não for um número inteiro válido.
     */
    public static int validarFormatoInteiro(String valor) throws FormatoInvalidoException {
        try {
            return Integer.parseInt(valor.trim());
        } catch (NumberFormatException e) {
            throw new FormatoInvalidoException();
        }
    }

    /**
     * Valida se o ano não é futuro ou muito antigo (menor que 1000).
     * @param ano O ano a ser validado.
     * @throws CampoInvalidoException se o ano for inválido.
     */
    public static void validarAno(int ano) throws CampoInvalidoException {
        int anoAtual = LocalDate.now().getYear();
        if (ano > anoAtual || ano < 1000) {
            throw new CampoInvalidoException();
        }
    }

    /**
     * Valida se um número inteiro é positivo (> 0).
     * @param numero O número a ser validado.
     * @throws CampoInvalidoException se o número for menor ou igual a zero.
     */
    public static void validarNumeroPositivo(int numero) throws CampoInvalidoException {
        if (numero <= 0) {
            throw new CampoInvalidoException();
        }
    }

    /**
     * Valida a duração de um audiobook em minutos.
     * @param duracao A duração a ser validada.
     * @throws CampoInvalidoException se a duração for inválida (menor ou igual a 0 ou maior que o máximo permitido).
     */
    public static void validarDuracaoAudiobook(int duracao) throws CampoInvalidoException {
        if (duracao <= 0 || duracao > MAX_DURACAO_AUDIOBOOK_MINUTOS) {
            throw new CampoInvalidoException();
        }
    }

    /**
     * Valida se o dispositivo de ebook é um dos tipos permitidos.
     * @param dispositivo O nome do dispositivo a ser validado.
     * @throws DispositivoInvalidoException se o dispositivo não for válido ou estiver em branco.
     */
    public static void validarDispositivoEbook(String dispositivo) throws DispositivoInvalidoException {
        try {
            validarCampoObrigatorio(dispositivo);
        } catch (CampoObrigatorioException e) {
            throw new DispositivoInvalidoException();
        }

        List<String> dispositivosValidos = Arrays.asList("Kindle", "Tablet", "Smartphone", "Computador", "E-reader");
        boolean valido = dispositivosValidos.stream()
                .anyMatch(d -> d.equalsIgnoreCase(dispositivo.trim()));

        if (!valido) {
            throw new DispositivoInvalidoException();
        }
    }
}