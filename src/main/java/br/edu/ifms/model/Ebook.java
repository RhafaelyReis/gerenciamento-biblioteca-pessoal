// src/main/java/br/edu/ifms/model/Ebook.java
package br.edu.ifms.model;

/**
 * Classe que representa um ebook (livro digital) na biblioteca digital.
 * 
 * Esta classe herda de Item e adiciona funcionalidades específicas para ebooks,
 * como informações sobre compatibilidade com dispositivos. Ebooks são uma forma
 * moderna e conveniente de consumir conteúdo literário, oferecendo vantagens
 * como portabilidade, ajuste de fonte e pesquisa de texto.
 * 
 * Características específicas do ebook:
 * - Compatibilidade com diferentes dispositivos
 * - Formato digital que permite busca e marcação
 * - Não ocupa espaço físico
 * - Herda todas as funcionalidades básicas de Item
 */
public class Ebook extends Item {
    
    /**
     * Tipo de dispositivo ou plataforma compatível com o ebook.
     * 
     * Este atributo especifica em qual(is) dispositivo(s) ou plataforma(s)
     * o ebook pode ser lido. Diferentes formatos de ebook têm diferentes
     * níveis de compatibilidade.
     * 
     * Exemplos comuns:
     * - "Kindle" - para dispositivos Amazon Kindle
     * - "Tablet" - para tablets em geral (iOS, Android)
     * - "Computador" - para leitura em PCs e Macs
     * - "Universal" - compatível com múltiplos dispositivos
     * - "Smartphone" - otimizado para leitura em celulares
     * 
     * A informação é importante para que os usuários saibam se podem
     * acessar o conteúdo em seus dispositivos preferidos.
     */
    private String dispositivo;

    /**
     * Construtor para inicializar um novo objeto Ebook.
     * 
     * Chama o construtor da superclasse Item para inicializar os atributos
     * comuns (título, autor, ano, gênero, descrição) e define o atributo
     * específico do ebook (dispositivo compatível).
     * 
     * O objeto é criado com status "não lido" e nota "não avaliado" por padrão.
     *
     * @param titulo          O título do ebook (não pode ser null ou vazio)
     * @param autor           O autor do ebook (não pode ser null ou vazio)
     * @param anoPublicacao   O ano de publicação (deve ser um valor positivo)
     * @param genero          O gênero do ebook (valor válido do enum Genero)
     * @param descricao       A descrição ou sinopse do ebook (pode ser null)
     * @param dispositivo     O tipo de dispositivo compatível (não pode ser null)
     * 
     * @throws IllegalArgumentException se algum parâmetro obrigatório for inválido
     */
    public Ebook(String titulo, String autor, int anoPublicacao,
                 Genero genero, String descricao,
                 String dispositivo) {
        super(titulo, autor, anoPublicacao, genero, descricao);
        this.dispositivo = dispositivo;
    }

    // ==================== GETTERS E SETTERS ESPECÍFICOS ====================

    /**
     * Obtém o tipo de dispositivo compatível com o ebook.
     * 
     * @return O tipo de dispositivo (nunca null)
     */
    public String getDispositivo() {
        return dispositivo;
    }

    /**
     * Define um novo tipo de dispositivo compatível com o ebook.
     * 
     * @param dispositivo O novo tipo de dispositivo (não pode ser null ou vazio)
     * @throws IllegalArgumentException se o dispositivo for null ou vazio
     */
    public void setDispositivo(String dispositivo) {
        this.dispositivo = dispositivo;
    }

    // ==================== MÉTODOS SOBRESCRITOS ====================

    /**
     * Implementa o método abstrato getTipo() da superclasse Item.
     * 
     * Este método identifica o tipo específico do item como "Ebook",
     * permitindo que o sistema diferencie entre diferentes tipos de itens
     * na biblioteca (Livro, Ebook, Audiobook, etc.).
     * 
     * @return A string "Ebook" identificando o tipo do item
     */
    @Override
    public String getTipo() {
        return "Ebook";
    }

    /**
     * Retorna uma representação textual completa do ebook.
     * 
     * Esta representação inclui todas as informações da superclasse Item
     * (ID, título, autor, ano, gênero, status de leitura, avaliação, descrição)
     * e adiciona as informações específicas do ebook (tipo e dispositivo compatível).
     * 
     * O formato é projetado para ser legível e informativo, útil para:
     * - Exibição em interfaces de usuário
     * - Logs e debug
     * - Relatórios do sistema
     * - Exportação de dados
     * 
     * Exemplo de saída:
     * "ID: 1, Título: 'Clean Code', Autor: 'Robert C. Martin', 
     *  Ano: 2008, Gênero: Técnico, Lido: Sim, Avaliação: Cinco estrelas, 
     *  Descrição: 'Um guia sobre boas práticas...', Tipo: Ebook, 
     *  Dispositivo: 'Tablet'"
     * 
     * @return String formatada contendo todos os atributos do ebook
     */
    @Override
    public String toString() {
        return super.toString() +
               ", Tipo: " + getTipo() +
               ", Dispositivo: '" + dispositivo + '\'';
    }

    // ==================== MÉTODOS UTILITÁRIOS ====================

    /**
     * Verifica se o ebook é compatível com dispositivos móveis.
     * 
     * Método utilitário que verifica se o ebook pode ser lido em
     * dispositivos móveis como smartphones e tablets.
     * 
     * @return true se o dispositivo for "Tablet", "Smartphone" ou "Universal"
     */
    public boolean isCompativelComMobile() {
        return dispositivo.equalsIgnoreCase("Tablet") ||
               dispositivo.equalsIgnoreCase("Smartphone") ||
               dispositivo.equalsIgnoreCase("Universal");
    }

    /**
     * Verifica se o ebook é exclusivo para um dispositivo específico.
     * 
     * Método utilitário que identifica se o ebook tem restrições
     * de compatibilidade ou se é universal.
     * 
     * @return true se o dispositivo não for "Universal"
     */
    public boolean isExclusivo() {
        return !dispositivo.equalsIgnoreCase("Universal");
    }

    /**
     * Obtém uma descrição mais detalhada sobre a compatibilidade.
     * 
     * Método utilitário que fornece informações mais específicas
     * sobre onde o ebook pode ser lido.
     * 
     * @return String descritiva sobre a compatibilidade
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