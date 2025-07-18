package br.edu.ifms.model;

/**
 * Representa um audiobook na biblioteca.
 * Herda os atributos de Item e adiciona duração em minutos e narrador.
 */
public class Audiobook extends Item {

    private int duracaoMinutos; // Duração total do audiobook em minutos.
    private String narrador; // Nome do narrador do audiobook.

    /**
     * Construtor do Audiobook.
     *
     * @param titulo Título do audiobook.
     * @param autor Autor do audiobook.
     * @param anoPublicacao Ano de publicação.
     * @param genero Gênero literário.
     * @param descricao Descrição do audiobook.
     * @param duracaoMinutos Duração em minutos.
     * @param narrador Nome do narrador.
     */
    public Audiobook(String titulo, String autor, int anoPublicacao,
                     Genero genero, String descricao,
                     int duracaoMinutos, String narrador) {
        super(titulo, autor, anoPublicacao, genero, descricao);
        this.duracaoMinutos = duracaoMinutos;
        this.narrador = narrador;
    }

    /** @return Duração do audiobook em minutos. */
    public int getDuracaoMinutos() {
        return duracaoMinutos;
    }

    /**
     * Define a duração do audiobook.
     * @param duracaoMinutos Nova duração.
     */
    public void setDuracaoMinutos(int duracaoMinutos) {
        this.duracaoMinutos = duracaoMinutos;
    }

    /** @return Nome do narrador. */
    public String getNarrador() {
        return narrador;
    }

    /**
     * Define o nome do narrador.
     * @param narrador Novo nome.
     */
    public void setNarrador(String narrador) {
        this.narrador = narrador;
    }

    /** @return Tipo "Audiobook". */
    @Override
    public String getTipo() {
        return "Audiobook";
    }

    /**
     * Retorna os dados completos do audiobook em formato de texto.
     */
    @Override
    public String toString() {
        return super.toString() +
               ", Tipo: " + getTipo() +
               ", Duração: " + duracaoMinutos + " min" +
               ", Narrador: '" + narrador + '\'';
    }

    /**
     * Converte a duração para o formato "Xh Ym".
     * @return Duração formatada.
     */
    public String getDuracaoFormatada() {
        int horas = duracaoMinutos / 60;
        int minutos = duracaoMinutos % 60;
        return (horas > 0) ? horas + "h " + minutos + "m" : minutos + "m";
    }
}