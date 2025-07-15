// src/main/java/br/edu/ifms/model/Nota.java
package br.edu.ifms.model;

//Enumeração para representar a avaliação de um item da biblioteca em estrelas (1 a 5)
public enum Nota {
    NAO_AVALIADO(0, "Não avaliado"),
    UMA_ESTRELA(1, "Muito ruim"),
    DUAS_ESTRELAS(2, "Ruim"),
    TRES_ESTRELAS(3, "Regular"),
    QUATRO_ESTRELAS(4, "Bom"),
    CINCO_ESTRELAS(5, "Excelente");

    private final int valor;
    private final String descricao;

    /**
     * Construtor para inicializar uma Nota.
     *
     * @param valor O valor numérico da nota (0-5).
     * @param descricao A descrição textual da nota.
     */
    Nota(int valor, String descricao) {
        this.valor = valor;
        this.descricao = descricao;
    }

    /**
     * Retorna o valor numérico da nota.
     *
     * @return O valor inteiro da nota.
     */
    public int getValor() {
        return valor;
    }

    /**
     * Retorna a descrição textual da nota.
     *
     * @return A string de descrição da nota.
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Retorna a enumeração Nota correspondente a um valor numérico.
     * Útil para converter um inteiro (ex: vindo de um campo de texto ou banco de dados)
     * para a enumeração correta.
     *
     * @param valor O valor numérico da nota (0-5).
     * @return A enumeração Nota correspondente, ou NAO_AVALIADO se o valor for inválido.
     */
    public static Nota fromValor(int valor) {
        for (Nota nota : Nota.values()) {
            if (nota.getValor() == valor) {
                return nota;
            }
        }
        // Retorna NAO_AVALIADO se o valor não corresponder a nenhuma nota válida
        return NAO_AVALIADO;
    }
}
