// src/main/java/br/edu/ifms/model/Audiobook.java
package br.edu.ifms.model;

/**
 * Representa um audiobook (livro em áudio) na biblioteca.
 * Estende a classe abstrata Item e adiciona atributos específicos como a duração em minutos e o nome do narrador.
 */
public class Audiobook extends Item {

    private int duracaoMinutos; // Alterado de 'duracao' para 'duracaoMinutos' para clareza
    private String narrador;    // Adicionando um atributo comum para audiobooks

    /**
     * Construtor para inicializar um objeto Audiobook.
     * Chama o construtor da superclasse Item para os atributos comuns e inicializa os atributos específicos de Audiobook.
     *
     * @param titulo O título do audiobook.
     * @param autor O autor do audiobook.
     * @param anoPublicacao O ano de publicação do audiobook.
     * @param genero O gênero do audiobook (usando o enum Genero).
     * @param descricao A descrição breve do audiobook.
     * @param duracaoMinutos A duração total do audiobook em minutos.
     * @param narrador O nome do narrador do audiobook.
     */
    public Audiobook(String titulo, String autor, int anoPublicacao,
                     Genero genero, String descricao,
                     int duracaoMinutos, String narrador) {
        super(titulo, autor, anoPublicacao, genero, descricao); // Chama o construtor da superclasse Item
        this.duracaoMinutos = duracaoMinutos;
        this.narrador = narrador;
    }

    // --- Getters e Setters Específicos para Audiobook ---

    /**
     * Retorna a duração do audiobook em minutos.
     *
     * @return A duração em minutos.
     */
    public int getDuracaoMinutos() {
        return duracaoMinutos;
    }

    /**
     * Define a duração do audiobook em minutos.
     *
     * @param duracaoMinutos A nova duração em minutos.
     */
    public void setDuracaoMinutos(int duracaoMinutos) {
        this.duracaoMinutos = duracaoMinutos;
    }

    /**
     * Retorna o nome do narrador do audiobook.
     *
     * @return O nome do narrador.
     */
    public String getNarrador() {
        return narrador;
    }

    /**
     * Define o nome do narrador do audiobook.
     *
     * @param narrador O novo nome do narrador.
     */
    public void setNarrador(String narrador) {
        this.narrador = narrador;
    }

    /**
     * Implementa o método abstrato getTipo() da superclasse Item.
     *
     * @return Uma String representando o tipo do item, neste caso "Audiobook".
     */
    @Override
    public String getTipo() {
        return "Audiobook";
    }

    /**
     * Retorna uma representação em formato de String deste objeto Audiobook.
     * Inclui as informações da superclasse Item e adiciona os atributos específicos de Audiobook (duração e narrador).
     *
     * @return Uma String que representa o objeto Audiobook com todos os seus atributos.
     */
    @Override
    public String toString() {
        return super.toString() +
               ", Tipo: " + getTipo() + // Adiciona o tipo de item
               ", Duração: " + duracaoMinutos + " min" +
               ", Narrador: '" + narrador + '\'';
    }
}