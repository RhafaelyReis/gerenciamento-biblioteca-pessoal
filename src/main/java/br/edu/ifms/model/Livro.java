// src/main/java/br/edu/ifms/model/Livro.java
package br.edu.ifms.model;

/**
 * Representa um livro físico na biblioteca.
 * Herda de Item e adiciona número de páginas e ISBN.
 */
public class Livro extends Item {

    private int numPaginas; // Número total de páginas do livro.
    private String isbn; // Código ISBN (International Standard Book Number) do livro.

    /**
     * Construtor do Livro.
     *
     * @param titulo          Título do livro.
     * @param autor           Autor do livro.
     * @param anoPublicacao   Ano de publicação.
     * @param genero          Gênero literário.
     * @param descricao       Descrição do livro.
     * @param numPaginas      Número de páginas.
     * @param isbn            Código ISBN.
     */
    public Livro(String titulo, String autor, int anoPublicacao,
                 Genero genero, String descricao,
                 int numPaginas, String isbn) {
        super(titulo, autor, anoPublicacao, genero, descricao);
        this.numPaginas = numPaginas;
        this.isbn = isbn;
    }

    /** @return O número de páginas do livro. */
    public int getNumPaginas() {
        return numPaginas;
    }

    /**
     * Define o número de páginas do livro.
     * @param numPaginas Novo número de páginas.
     */
    public void setNumPaginas(int numPaginas) {
        this.numPaginas = numPaginas;
    }

    /** @return O código ISBN do livro. */
    public String getIsbn() {
        return isbn;
    }

    /**
     * Define o código ISBN do livro.
     * @param isbn Novo código ISBN.
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    /** @return O tipo "Livro". */
    @Override
    public String getTipo() {
        return "Livro";
    }

    /**
     * Retorna os dados completos do livro em formato de texto.
     */
    @Override
    public String toString() {
        return super.toString() +
               ", Tipo: " + getTipo() +
               ", Páginas: " + numPaginas +
               ", ISBN: '" + isbn + '\'';
    }

    /**
     * Calcula o tempo estimado de leitura em horas.
     * @return Tempo estimado em horas.
     */
    public int getTempoEstimadoLeitura() {
        int minutosPorPagina = 1; // Assumindo 1 minuto por página
        int minutosTotal = numPaginas * minutosPorPagina;
        return Math.round(minutosTotal / 60.0f);
    }

    /**
     * Verifica se o livro possui um ISBN.
     * @return true se o ISBN existir, false caso contrário.
     */
    public boolean temIsbn() {
        return isbn != null && !isbn.trim().isEmpty();
    }

    /**
     * Classifica o livro como "Curto", "Médio" ou "Longo".
     * @return A classificação de tamanho.
     */
    public String getClassificacaoTamanho() {
        if (numPaginas <= 200) {
            return "Curto";
        } else if (numPaginas <= 400) {
            return "Médio";
        } else {
            return "Longo";
        }
    }

    /**
     * Verifica se o livro é considerado extenso (mais de 500 páginas).
     * @return true se for extenso, false caso contrário.
     */
    public boolean isExtenso() {
        return numPaginas > 500;
    }

    /**
     * Valida o formato do ISBN (10 ou 13 dígitos).
     * @return true se o formato for válido ou nulo.
     */
    public boolean isIsbnValido() {
        if (isbn == null || isbn.trim().isEmpty()) {
            return true;
        }
        String isbnLimpo = isbn.replaceAll("[-\\s]", "");
        return isbnLimpo.matches("\\d{10}") || isbnLimpo.matches("\\d{13}");
    }
}