package br.edu.ifms.model;

public enum Nota {
    NAO_AVALIADO(0, "Não avaliado", "⚫"),
    UMA_ESTRELA(1, "Muito ruim", "⭐"),
    DUAS_ESTRELAS(2, "Ruim", "⭐⭐"),
    TRES_ESTRELAS(3, "Regular", "⭐⭐⭐"),
    QUATRO_ESTRELAS(4, "Bom", "⭐⭐⭐⭐"),
    CINCO_ESTRELAS(5, "Excelente", "⭐⭐⭐⭐⭐");
    
    public String getEstrelas() {
        return estrelas;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getValor() {
        return valor;
    }

    private final int valor;
    private final String descricao;
    private final String estrelas;
    
    // Construtor
    Nota(int valor, String descricao, String estrelas) {
        this.valor = valor;
        this.descricao = descricao;
        this.estrelas = estrelas;
    }

    // // Método para mostrar no JComboBox
    // @Override
    // public String toString() {
    //     return estrelas + " " + descricao;
    // }
    
    // // Método para converter valor numérico em enum
    // public static Nota fromValor(int valor) {
    //     for (Nota nota : Nota.values()) {
    //         if (nota.valor == valor) {
    //             return nota;
    //         }
    //     }
    //     return NAO_AVALIADO; // padrão se não encontrar
    // }
    
    // // Método para verificar se é uma avaliação válida
    // public boolean isAvaliado() {
    //     return this.valor > 0;
    // }

}
