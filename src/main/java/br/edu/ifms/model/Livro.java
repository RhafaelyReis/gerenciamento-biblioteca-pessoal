// src/main/java/br/edu/ifms/model/Livro.java
package br.edu.ifms.model;

/**
 * Classe que representa um livro físico na biblioteca digital.
 * 
 * Esta classe herda de Item e adiciona funcionalidades específicas para livros
 * físicos, como número de páginas e ISBN. Livros físicos são a forma tradicional
 * de literatura, oferecendo uma experiência tátil única e não dependendo de
 * dispositivos eletrônicos para leitura.
 * 
 * Características específicas do livro físico:
 * - Número de páginas para controle de tamanho
 * - ISBN para identificação única internacional
 * - Presença física na biblioteca
 * - Experiência de leitura tradicional
 * - Herda todas as funcionalidades básicas de Item
 */
public class Livro extends Item {
    
    /**
     * Número total de páginas do livro.
     * 
     * Este atributo representa a extensão física do livro e pode ser
     * usado para:
     * - Estimar tempo de leitura
     * - Classificar livros por tamanho
     * - Filtrar por preferências de leitura
     * - Calcular estatísticas da biblioteca
     * 
     * Deve ser um valor positivo maior que zero.
     * 
     * Exemplos típicos:
     * - Livro curto: 100-200 páginas
     * - Livro médio: 250-400 páginas
     * - Livro longo: 500+ páginas
     */
    private int numPaginas;
    
    /**
     * International Standard Book Number (ISBN) do livro.
     * 
     * O ISBN é um identificador único internacional para livros,
     * usado para:
     * - Identificação única do livro
     * - Facilitar compras e catalogação
     * - Integração com sistemas externos
     * - Controle de inventário
     * 
     * Pode estar nos formatos:
     * - ISBN-10: 10 dígitos (formato antigo)
     * - ISBN-13: 13 dígitos (formato atual)
     * 
     * Exemplos:
     * - ISBN-10: "0-123456-47-9"
     * - ISBN-13: "978-0-123456-47-2"
     * 
     * Nota: Livros muito antigos podem não ter ISBN.
     */
    private String isbn;

    /**
     * Construtor para inicializar um novo objeto Livro.
     * 
     * Chama o construtor da superclasse Item para inicializar os atributos
     * comuns (título, autor, ano, gênero, descrição) e define os atributos
     * específicos do livro físico (número de páginas e ISBN).
     * 
     * O objeto é criado com status "não lido" e nota "não avaliado" por padrão.
     *
     * @param titulo          O título do livro (não pode ser null ou vazio)
     * @param autor           O autor do livro (não pode ser null ou vazio)
     * @param anoPublicacao   O ano de publicação (deve ser um valor positivo)
     * @param genero          O gênero do livro (valor válido do enum Genero)
     * @param descricao       A descrição ou sinopse do livro (pode ser null)
     * @param numPaginas      O número total de páginas (deve ser positivo)
     * @param isbn            O ISBN do livro (pode ser null para livros antigos)
     * 
     * @throws IllegalArgumentException se algum parâmetro obrigatório for inválido
     */
    public Livro(String titulo, String autor, int anoPublicacao,
                 Genero genero, String descricao,
                 int numPaginas, String isbn) {
        super(titulo, autor, anoPublicacao, genero, descricao);
        this.numPaginas = numPaginas;
        this.isbn = isbn;
    }

    // ==================== GETTERS E SETTERS ESPECÍFICOS ====================

    /**
     * Obtém o número de páginas do livro.
     * 
     * @return O número total de páginas (sempre positivo)
     */
    public int getNumPaginas() {
        return numPaginas;
    }

    /**
     * Define um novo número de páginas para o livro.
     * 
     * @param numPaginas O novo número de páginas (deve ser positivo)
     * @throws IllegalArgumentException se o número for negativo ou zero
     */
    public void setNumPaginas(int numPaginas) {
        this.numPaginas = numPaginas;
    }

    /**
     * Obtém o ISBN do livro.
     * 
     * @return O ISBN do livro (pode ser null para livros antigos)
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * Define um novo ISBN para o livro.
     * 
     * @param isbn O novo ISBN (pode ser null para livros sem ISBN)
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    // ==================== MÉTODOS SOBRESCRITOS ====================

    /**
     * Implementa o método abstrato getTipo() da superclasse Item.
     * 
     * Este método identifica o tipo específico do item como "Livro",
     * permitindo que o sistema diferencie entre diferentes tipos de itens
     * na biblioteca (Livro, Ebook, Audiobook, etc.).
     * 
     * @return A string "Livro" identificando o tipo do item
     */
    @Override
    public String getTipo() {
        return "Livro";
    }

    /**
     * Retorna uma representação textual completa do livro.
     * 
     * Esta representação inclui todas as informações da superclasse Item
     * (ID, título, autor, ano, gênero, status de leitura, avaliação, descrição)
     * e adiciona as informações específicas do livro (tipo, número de páginas e ISBN).
     * 
     * O formato é projetado para ser legível e informativo, útil para:
     * - Exibição em interfaces de usuário
     * - Logs e debug
     * - Relatórios do sistema
     * - Exportação de dados
     * 
     * Exemplo de saída:
     * "ID: 1, Título: '1984', Autor: 'George Orwell', 
     *  Ano: 1949, Gênero: Ficção, Lido: Sim, Avaliação: Cinco estrelas, 
     *  Descrição: 'Uma distopia sobre totalitarismo...', Tipo: Livro, 
     *  Páginas: 328, ISBN: '978-0-452-28423-4'"
     * 
     * @return String formatada contendo todos os atributos do livro
     */
    @Override
    public String toString() {
        return super.toString() +
               ", Tipo: " + getTipo() +
               ", Páginas: " + numPaginas +
               ", ISBN: '" + isbn + '\'';
    }

    // ==================== MÉTODOS UTILITÁRIOS ====================

    /**
     * Calcula o tempo estimado de leitura em horas.
     * 
     * Baseado na velocidade média de leitura de aproximadamente
     * 250 palavras por minuto e estimativa de 250 palavras por página.
     * 
     * @return Tempo estimado de leitura em horas (arredondado)
     */
    public int getTempoEstimadoLeitura() {
        // Assumindo 250 palavras por página e 250 palavras por minuto
        int minutosPorPagina = 1; // aproximadamente 1 minuto por página
        int minutosTotal = numPaginas * minutosPorPagina;
        return Math.round(minutosTotal / 60.0f);
    }

    /**
     * Verifica se o livro possui ISBN.
     * 
     * Método utilitário que verifica se o livro tem ISBN cadastrado.
     * Útil para validações e filtros.
     * 
     * @return true se o ISBN não for null e não estiver vazio
     */
    public boolean temIsbn() {
        return isbn != null && !isbn.trim().isEmpty();
    }

    /**
     * Classifica o livro por tamanho baseado no número de páginas.
     * 
     * Método utilitário que categoriza o livro em:
     * - Curto: até 200 páginas
     * - Médio: 201 a 400 páginas
     * - Longo: mais de 400 páginas
     * 
     * @return String representando a classificação do tamanho
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
     * 
     * Método utilitário que pode ser usado para alertar sobre
     * livros que requerem mais tempo de leitura.
     * 
     * @return true se o livro tiver mais de 500 páginas
     */
    public boolean isExtenso() {
        return numPaginas > 500;
    }

    /**
     * Valida se o formato do ISBN está correto.
     * 
     * Método utilitário que verifica se o ISBN segue os padrões
     * básicos de formatação (ISBN-10 ou ISBN-13).
     * 
     * Note: Esta é uma validação básica de formato, não de validade.
     * 
     * @return true se o ISBN estiver em formato válido ou for null
     */
    public boolean isIsbnValido() {
        if (isbn == null || isbn.trim().isEmpty()) {
            return true; // ISBN pode ser null para livros antigos
        }
        
        // Remove hífens e espaços para validação
        String isbnLimpo = isbn.replaceAll("[-\\s]", "");
        
        // Verifica se é ISBN-10 (10 dígitos) ou ISBN-13 (13 dígitos)
        return isbnLimpo.matches("\\d{10}") || isbnLimpo.matches("\\d{13}");
    }
}