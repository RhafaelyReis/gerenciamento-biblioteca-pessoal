// src/main/java/br/edu/ifms/model/Ebook.java
package br.edu.ifms.model;

/**
 * Representa um ebook (livro digital) na biblioteca.
 * Herda de Item e adiciona o dispositivo compatível.
 */
public class Ebook extends Item {
    
    private String dispositivo; // Dispositivo ou plataforma compatível (ex: "Kindle", "Tablet").

    /**
     * Construtor do Ebook.
     *
     * @param titulo          Título do ebook.
     * @param autor           Autor do ebook.
     * @param anoPublicacao   Ano de publicação.
     * @param genero          Gênero literário.
     * @param descricao       Descrição do ebook.
     * @param dispositivo     Dispositivo compatível.
     */
    public Ebook(String titulo, String autor, int anoPublicacao,
                 Genero genero, String descricao,
                 String dispositivo) {
        super(titulo, autor, anoPublicacao, genero, descricao);
        this.dispositivo = dispositivo;
    }

    /** @return O dispositivo compatível. */
    public String getDispositivo() {
        return dispositivo;
    }

    /**
     * Define o dispositivo compatível.
     * @param dispositivo Novo dispositivo.
     */
    public void setDispositivo(String dispositivo) {
        this.dispositivo = dispositivo;
    }

    /** @return O tipo "Ebook". */
    @Override
    public String getTipo() {
        return "Ebook";
    }

    /**
     * Retorna os dados completos do ebook em formato de texto.
     */
    @Override
    public String toString() {
        return super.toString() +
               ", Tipo: " + getTipo() +
               ", Dispositivo: '" + dispositivo + '\'';
    }

    /**
     * Verifica se o ebook é compatível com dispositivos móveis.
     * @return true se for compatível, false caso contrário.
     */
    public boolean isCompativelComMobile() {
        return dispositivo.equalsIgnoreCase("Tablet") ||
               dispositivo.equalsIgnoreCase("Smartphone") ||
               dispositivo.equalsIgnoreCase("Universal");
    }

    /**
     * Verifica se o ebook é exclusivo para um dispositivo que não seja "Universal".
     * @return true se for exclusivo, false caso contrário.
     */
    public boolean isExclusivo() {
        return !dispositivo.equalsIgnoreCase("Universal");
    }

    /**
     * Fornece uma descrição textual da compatibilidade do dispositivo.
     * @return A descrição da compatibilidade.
     */
    public String getDescricaoCompatibilidade() {
        switch (dispositivo.toLowerCase()) {
            case "kindle":
                return "Compatível apenas com dispositivos Amazon Kindle";
            case "tablet":
                return "Otimizado para leitura em tablets";
            case "smartphone":
                return "Otimizado para leitura em smartphones";
            case "computador":
                return "Disponível para leitura em PCs e Macs";
            case "universal":
                return "Compatível com múltiplos dispositivos e plataformas";
            default:
                return "Compatível com " + dispositivo;
        }
    }
}