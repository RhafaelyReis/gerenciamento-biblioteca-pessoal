// src/main/java/br/edu/ifms/model/Livro.java
package br.edu.ifms.model;

//Representa um livro físico na biblioteca. Estende a classe abstrata Item e adiciona atributos específicos como número de páginas e ISBN.
public class Livro extends Item{
    private int numPaginas;
    private String isbn;

    /**
     * Construtor para inicializar um objeto Livro.
     * Chama o construtor da superclasse Item para os atributos comuns
     * e inicializa os atributos específicos de Livro.
     *
     * @param titulo O título do livro.
     * @param autor O autor do livro.
     * @param anoPublicacao O ano de publicação do livro.
     * @param genero O gênero do livro (usando o enum Genero).
     * @param descricao A descrição breve do livro.
     * @param numPaginas O número total de páginas do livro.
     * @param isbn O International Standard Book Number do livro.
     */
    public Livro(String titulo, String autor, int anoPublicacao,
                 Genero genero, String descricao,
                 int numPaginas, String isbn) {
        super(titulo, autor, anoPublicacao, genero, descricao); // Chama o construtor da superclasse Item
        this.numPaginas = numPaginas;
        this.isbn = isbn;
    }

    // --- Getters e Setters de Livro ---

    /**
     * Retorna o número de páginas do livro.
     *
     * @return O número de páginas.
     */
    public int getNumPaginas() {
        return numPaginas;
    }

    /**
     * Define o número de páginas do livro.
     *
     * @param numPaginas O novo número de páginas.
     */
    public void setNumPaginas(int numPaginas) {
        this.numPaginas = numPaginas;
    }

    /**
     * Retorna o ISBN do livro.
     *
     * @return O ISBN do livro.
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * Define o ISBN do livro.
     *
     * @param isbn O novo ISBN do livro.
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    /**
     * Implementa o método abstrato getTipo() da superclasse Item.
     *
     * @return Uma String representando o tipo do item, neste caso "Livro".
     */
    @Override
    public String getTipo() {
        return "Livro";
    }

    /**
     * Retorna uma representação em formato de String deste objeto Livro.
     * Inclui as informações da superclasse Item e adiciona os atributos
     * específicos de Livro (número de páginas e ISBN).
     *
     * @return Uma String que representa o objeto Livro com todos os seus atributos.
     */
    @Override
    public String toString() {
        return super.toString() +
               ", Tipo: " + getTipo() + // Adiciona o tipo de item
               ", Páginas: " + numPaginas +
               ", ISBN: '" + isbn + '\'';
    }
}
