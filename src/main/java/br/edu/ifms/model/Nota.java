// src/main/java/br/edu/ifms/model/Nota.java
package br.edu.ifms.model;

/**
 * Enumeração para as avaliações dos itens da biblioteca, com descrição e símbolo.
 */
public enum Nota {
    NAO_AVALIADO("Não avaliado", ""),
    UMA_ESTRELA("Muito Ruim", "★"),
    DUAS_ESTRELAS("Ruim", "★★"),
    TRES_ESTRELAS("Regular", "★★★"),
    QUATRO_ESTRELAS("Bom", "★★★★"),
    CINCO_ESTRELAS("Excelente", "★★★★★");

    private final String descricao; // Descrição textual da nota (ex: "Muito Ruim").
    private final String simbolo; // Representação em estrelas da nota (ex: "★").

    /**
     * Construtor da Nota.
     * @param descricao Descrição textual.
     * @param simbolo Símbolo em estrelas.
     */
    Nota(String descricao, String simbolo) {
        this.descricao = descricao;
        this.simbolo = simbolo;
    }

    /** @return A descrição da nota. */
    public String getDescricao() {
        return descricao;
    }
    
    /** @return O símbolo em estrelas. */
    public String getSimbolo() {
        return simbolo;
    }

    /**
     * Converte um símbolo de estrela de volta para o enum Nota correspondente.
     * @param simbolo Símbolo a ser convertido.
     * @return O enum Nota correspondente.
     */
    public static Nota fromSimbolo(String simbolo) {
        for (Nota nota : Nota.values()) {
            if (nota.getSimbolo().equals(simbolo)) {
                return nota;
            }
        }
        return NAO_AVALIADO;
    }
}