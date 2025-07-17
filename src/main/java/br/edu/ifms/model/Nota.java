// src/main/java/br/edu/ifms/model/Nota.java
package br.edu.ifms.model;

public enum Nota {
    NAO_AVALIADO("Não avaliado", ""),
    UMA_ESTRELA("Muito Ruim", "★"),
    DUAS_ESTRELAS("Ruim", "★★"),
    TRES_ESTRELAS("Regular", "★★★"),
    QUATRO_ESTRELAS("Bom", "★★★★"),
    CINCO_ESTRELAS("Excelente", "★★★★★");

    private final String descricao;
    private final String simbolo;

    Nota(String descricao, String simbolo) {
        this.descricao = descricao;
        this.simbolo = simbolo;
    }

    public String getDescricao() {
        return descricao;
    }
    
    public String getSimbolo() {
        return simbolo;
    }

    public static Nota fromSimbolo(String simbolo) {
        for (Nota nota : Nota.values()) {
            if (nota.getSimbolo().equals(simbolo)) {
                return nota;
            }
        }
        return NAO_AVALIADO;
    }
}