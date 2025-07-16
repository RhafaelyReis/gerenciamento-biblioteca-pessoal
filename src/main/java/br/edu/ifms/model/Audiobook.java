// src/main/java/br/edu/ifms/model/Audiobook.java
package br.edu.ifms.model;

/**
 * Classe que representa um audiobook (livro em áudio) na biblioteca digital.
 * 
 * Esta classe herda de Item e adiciona funcionalidades específicas para audiobooks,
 * como duração em minutos e informações sobre o narrador. Audiobooks são uma forma
 * popular de consumir conteúdo literário, especialmente para pessoas com deficiência
 * visual ou aquelas que preferem o formato de áudio.
 * 
 * Características específicas do audiobook:
 * - Duração medida em minutos
 * - Informações sobre o narrador
 * - Herda todas as funcionalidades básicas de Item
 */
public class Audiobook extends Item {

    /**
     * Duração total do audiobook em minutos.
     * 
     * Este valor representa o tempo total necessário para ouvir
     * todo o conteúdo do audiobook. Deve ser um valor positivo.
     * 
     * Exemplos típicos:
     * - Audiobook curto: 180 minutos (3 horas)
     * - Audiobook médio: 480 minutos (8 horas)
     * - Audiobook longo: 720 minutos (12 horas)
     */
    private int duracaoMinutos;
    
    /**
     * Nome do narrador responsável pela narração do audiobook.
     * 
     * O narrador é uma informação importante pois pode influenciar
     * significativamente a experiência de audição. Alguns usuários
     * podem ter preferência por determinados narradores.
     * 
     * Pode conter:
     * - Nome de um único narrador
     * - Múltiplos narradores separados por vírgula
     * - Informações adicionais como "Narrado pelo próprio autor"
     */
    private String narrador;

    /**
     * Construtor para inicializar um novo objeto Audiobook.
     * 
     * Chama o construtor da superclasse Item para inicializar os atributos
     * comuns (título, autor, ano, gênero, descrição) e define os atributos
     * específicos do audiobook (duração e narrador).
     * 
     * O objeto é criado com status "não lido" e nota "não avaliado" por padrão.
     *
     * @param titulo          O título do audiobook (não pode ser null ou vazio)
     * @param autor           O autor do audiobook (não pode ser null ou vazio)
     * @param anoPublicacao   O ano de publicação (deve ser um valor positivo)
     * @param genero          O gênero do audiobook (valor válido do enum Genero)
     * @param descricao       A descrição ou sinopse do audiobook (pode ser null)
     * @param duracaoMinutos  A duração total em minutos (deve ser positivo)
     * @param narrador        O nome do narrador (não pode ser null ou vazio)
     * 
     * @throws IllegalArgumentException se algum parâmetro obrigatório for inválido
     */
    public Audiobook(String titulo, String autor, int anoPublicacao,
                     Genero genero, String descricao,
                     int duracaoMinutos, String narrador) {
        super(titulo, autor, anoPublicacao, genero, descricao);
        this.duracaoMinutos = duracaoMinutos;
        this.narrador = narrador;
    }

    // ==================== GETTERS E SETTERS ESPECÍFICOS ====================

    /**
     * Obtém a duração do audiobook em minutos.
     * 
     * @return A duração total em minutos (sempre positivo)
     */
    public int getDuracaoMinutos() {
        return duracaoMinutos;
    }

    /**
     * Define uma nova duração para o audiobook.
     * 
     * @param duracaoMinutos A nova duração em minutos (deve ser positivo)
     * @throws IllegalArgumentException se a duração for negativa ou zero
     */
    public void setDuracaoMinutos(int duracaoMinutos) {
        this.duracaoMinutos = duracaoMinutos;
    }

    /**
     * Obtém o nome do narrador do audiobook.
     * 
     * @return O nome do narrador (nunca null)
     */
    public String getNarrador() {
        return narrador;
    }

    /**
     * Define um novo narrador para o audiobook.
     * 
     * @param narrador O novo nome do narrador (não pode ser null ou vazio)
     * @throws IllegalArgumentException se o narrador for null ou vazio
     */
    public void setNarrador(String narrador) {
        this.narrador = narrador;
    }

    // ==================== MÉTODOS SOBRESCRITOS ====================

    /**
     * Implementa o método abstrato getTipo() da superclasse Item.
     * 
     * Este método identifica o tipo específico do item como "Audiobook",
     * permitindo que o sistema diferencie entre diferentes tipos de itens
     * na biblioteca (Livro, Ebook, Audiobook, etc.).
     * 
     * @return A string "Audiobook" identificando o tipo do item
     */
    @Override
    public String getTipo() {
        return "Audiobook";
    }

    /**
     * Retorna uma representação textual completa do audiobook.
     * 
     * Esta representação inclui todas as informações da superclasse Item
     * (ID, título, autor, ano, gênero, status de leitura, avaliação, descrição)
     * e adiciona as informações específicas do audiobook (tipo, duração e narrador).
     * 
     * O formato é projetado para ser legível e informativo, útil para:
     * - Exibição em interfaces de usuário
     * - Logs e debug
     * - Relatórios do sistema
     * - Exportação de dados
     * 
     * Exemplo de saída:
     * "ID: 1, Título: 'O Senhor dos Anéis', Autor: 'J.R.R. Tolkien', 
     *  Ano: 1954, Gênero: Fantasia, Lido: Não, Avaliação: Não avaliado, 
     *  Descrição: 'Uma épica aventura...', Tipo: Audiobook, Duração: 720 min, 
     *  Narrador: 'Rob Inglis'"
     * 
     * @return String formatada contendo todos os atributos do audiobook
     */
    @Override
    public String toString() {
        return super.toString() +
               ", Tipo: " + getTipo() +
               ", Duração: " + duracaoMinutos + " min" +
               ", Narrador: '" + narrador + '\'';
    }

    // ==================== MÉTODOS UTILITÁRIOS ====================

    /**
     * Converte a duração do audiobook para formato de horas e minutos.
     * 
     * Método utilitário que pode ser útil para exibição em interfaces
     * de usuário mais amigáveis.
     * 
     * @return String no formato "Xh Ym" (ex: "2h 30m")
     */
    public String getDuracaoFormatada() {
        int horas = duracaoMinutos / 60;
        int minutos = duracaoMinutos % 60;
        
        if (horas > 0) {
            return horas + "h " + minutos + "m";
        } else {
            return minutos + "m";
        }
    }
}