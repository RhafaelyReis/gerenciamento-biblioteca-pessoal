// src/main/java/br/edu/ifms/model/Item.java
package br.edu.ifms.model;

import java.util.Objects;

/**
 * Classe abstrata que representa um item da biblioteca.
 * Define atributos e comportamentos comuns a todos os itens.
 */
public abstract class Item {
    
    private static int proxId = 1; // Contador estático para geração de IDs únicos.

    protected int id; // Identificador único do item.
    protected String titulo; // Título do item.
    protected String autor; // Autor do item.
    protected int anoPublicacao; // Ano de publicação do item.
    protected Genero genero; // Gênero literário do item.
    protected String descricao; // Descrição ou sinopse do item.
    protected boolean lido; // Status de leitura (true se lido, false caso contrário).
    protected Nota nota; // Avaliação atribuída ao item.

    /**
     * Construtor do Item.
     *
     * @param titulo        Título do item.
     * @param autor         Autor do item.
     * @param anoPublicacao Ano de publicação.
     * @param genero        Gênero do item.
     * @param descricao     Descrição do item.
     */
    public Item(String titulo, String autor, int anoPublicacao, Genero genero, String descricao) {
        this.id = proxId++;
        this.titulo = titulo;
        this.autor = autor;
        this.anoPublicacao = anoPublicacao;
        this.genero = genero;
        this.descricao = descricao;
        this.lido = false;
        this.nota = Nota.NAO_AVALIADO;
    }

    /** @return O ID único do item. */
    public int getId() {
        return id;
    }

    /** @return O título do item. */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Define o título do item.
     * @param titulo Novo título.
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /** @return O autor do item. */
    public String getAutor() {
        return autor;
    }

    /**
     * Define o autor do item.
     * @param autor Novo autor.
     */
    public void setAutor(String autor) {
        this.autor = autor;
    }

    /** @return O ano de publicação do item. */
    public int getAnoPublicacao() {
        return anoPublicacao;
    }

    /**
     * Define o ano de publicação.
     * @param anoPublicacao Novo ano.
     */
    public void setAnoPublicacao(int anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }

    /** @return O gênero do item. */
    public Genero getGenero() {
        return genero;
    }

    /**
     * Define o gênero do item.
     * @param genero Novo gênero.
     */
    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    /** @return A descrição do item. */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Define a descrição do item.
     * @param descricao Nova descrição.
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /** @return true se o item foi lido, false caso contrário. */
    public boolean isLido() {
        return lido;
    }

    /**
     * Define o status de leitura. Se "não lido", a nota é resetada.
     * @param lido Novo status de leitura.
     */
    public void setLido(boolean lido) {
        this.lido = lido;
        if (!lido) {
            this.nota = Nota.NAO_AVALIADO;
        }
    }

    /** @return A avaliação do item. */
    public Nota getNota() {
        return nota;
    }

    /**
     * Define a avaliação do item.
     * @param nota Nova avaliação.
     */
    public void setNota(Nota nota) {
        this.nota = nota;
    }

    /**
     * Método abstrato para obter o tipo específico do item (Livro, Ebook, etc.).
     * @return O tipo do item como String.
     */
    public abstract String getTipo();

    /**
     * Compara este item com outro objeto. A igualdade é baseada apenas no ID.
     * @param o Objeto a ser comparado.
     * @return true se os IDs forem iguais, false caso contrário.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return id == item.id;
    }

    /**
     * Gera o código hash para o item, baseado no ID.
     * @return O código hash.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    /**
     * Retorna os dados completos do item em formato de texto.
     */
    @Override
    public String toString() {
        return "ID: " + id +
               ", Título: '" + titulo + '\'' +
               ", Autor: '" + autor + '\'' +
               ", Ano: " + anoPublicacao +
               ", Gênero: " + genero.toString() +
               ", Lido: " + (lido ? "Sim" : "Não") +
               ", Avaliação: " + nota.toString() +
               ", Descrição: '" + descricao + '\'';
    }
}