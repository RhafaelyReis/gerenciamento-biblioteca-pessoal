// src/main/java/br/edu/ifms/model/Ebook.java
package br.edu.ifms.model;

//Representa um ebook (livro digital) na biblioteca. Estende a classe abstrata Item e adiciona um atributo específico para o tipo de dispositivo para o qual o ebook é compatível.
public class Ebook extends Item {
    private String dispositivo; // O atributo 'dispositivo' que você especificou

    /**
     * Construtor para inicializar um objeto Ebook.
     * Chama o construtor da superclasse Item para os atributos comuns e inicializa o atributo específico de Ebook.
     *
     * @param titulo O título do ebook.
     * @param autor O autor do ebook.
     * @param anoPublicacao O ano de publicação do ebook.
     * @param genero O gênero do ebook (usando o enum Genero).
     * @param descricao A descrição breve do ebook.
     * @param dispositivo O tipo de dispositivo compatível com o ebook (e.g., "Kindle", "Tablet", "Computador").
     */
    public Ebook(String titulo, String autor, int anoPublicacao,
                 Genero genero, String descricao,
                 String dispositivo) { // Inclui 'dispositivo' no construtor
        super(titulo, autor, anoPublicacao, genero, descricao); // Chama o construtor da superclasse Item
        this.dispositivo = dispositivo; // Inicializa o atributo específico
    }

    // --- Getter e Setter Específico para Ebook ---

    /**
     * Retorna o tipo de dispositivo para o qual o ebook é compatível.
     *
     * @return O tipo de dispositivo.
     */
    public String getDispositivo() {
        return dispositivo;
    }

    /**
     * Define o tipo de dispositivo para o qual o ebook é compatível.
     *
     * @param dispositivo O novo tipo de dispositivo.
     */
    public void setDispositivo(String dispositivo) {
        this.dispositivo = dispositivo;
    }

    /**
     * Implementa o método abstrato getTipo() da superclasse Item.
     *
     * @return Uma String representando o tipo do item, neste caso "Ebook".
     */
    @Override
    public String getTipo() {
        return "Ebook";
    }

    /**
     * Retorna uma representação em formato de String deste objeto Ebook.
     * Inclui as informações da superclasse Item e adiciona o atributo específico de Ebook (dispositivo).
     *
     * @return Uma String que representa o objeto Ebook com todos os seus atributos.
     */
    @Override
    public String toString() {
        return super.toString() +
               ", Tipo: " + getTipo() + // Adiciona o tipo de item
               ", Dispositivo: '" + dispositivo + '\''; // Inclui o dispositivo
    }
}