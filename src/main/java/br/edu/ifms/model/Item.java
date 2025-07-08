// src/main/java/br/edu/ifms/model/Item.java
package br.edu.ifms.model;

import java.util.Objects;

public abstract class Item {
    private static int proxId = 1;

    protected int id;
    protected String titulo;
    protected String autor;
    protected int anoPublicacao;
    protected Genero genero;
    protected String descricao;
    protected boolean lido;
    protected Nota nota;

    /**
     * Construtor para inicializar um Item.
     * O ID é gerado automaticamente.
     *
     * @param titulo O título do item.
     * @param autor O autor do item.
     * @param anoPublicacao O ano de publicação do item.
     * @param genero O gênero do item (usando o enum Genero).
     * @param descricao A descrição breve do item.
     */
    public Item(String titulo, String autor, int anoPublicacao, Genero genero, String descricao) {
        this.id = proxId++; // Atribui e incrementa o id
        this.titulo = titulo;
        this.autor = autor;
        this.anoPublicacao = anoPublicacao;
        this.genero = genero;
        this.descricao = descricao;
        this.lido = false; // Por padrão, não lido
        this.nota = Nota.NAO_AVALIADO; // Avaliação inicial como "Não Avaliado"
    }

    // --- Getters e Setters ---

    public long getId() {
        return id;
    }

    // O ID não possui setter público para garantir sua unicidade após a criação.
    // public void setId(long id) { this.id = id; }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getAnoPublicacao() {
        return anoPublicacao;
    }

    public void setAnoPublicacao(int anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isLido() {
        return lido;
    }

    public void setLido(boolean lido) {
        this.lido = lido;
        // Se o item for marcado como "não lido", sua avaliação deve ser resetada.
        if (!lido) {
            this.nota = Nota.NAO_AVALIADO;
        }
    }

    public Nota getNota() {
        return nota;
    }

    /**
     * Define a avaliação do item.
     * Observação: A validação de se o item está lido e a faixa da avaliação
     * será tratada na camada de serviço (Service).
     *
     * @param nota A avaliação do item (usando o enum Nota).
     */
    public void setNota(Nota nota) {
        this.nota = nota;
    }

    /**
     * Método abstrato para obter o tipo específico do item.
     * Será implementado por cada subclasse (Livro, Ebook, Audiobook).
     *
     * @return Uma string representando o tipo do item.
     */
    public abstract String getTipo();

    /**
     * Compara dois objetos Item com base no ID.
     * Essencial para o correto funcionamento de métodos como contains() e remove().
     *
     * @param o Objeto a ser comparado.
     * @return true se os IDs forem iguais.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return id == item.id;
    }

    /**
     * Gera o código hash do item com base no ID.
     * Deve ser consistente com o equals().
     *
     * @return Código hash baseado no ID.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    /**
     * Retorna uma representação textual do item com seus principais atributos.
     * Útil para logs, debug ou exibição na interface.
     *
     * @return String representando o item.
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
