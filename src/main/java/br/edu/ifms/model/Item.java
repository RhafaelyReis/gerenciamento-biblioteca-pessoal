// src/main/java/br/edu/ifms/model/Item.java
package br.edu.ifms.model;

import java.util.Objects;

/**
 * Classe abstrata que representa um item de biblioteca digital.
 * 
 * Esta classe define os atributos e comportamentos comuns a todos os tipos
 * de itens que podem ser gerenciados no sistema (livros, e-books, audiobooks, etc.).
 * 
 * Características principais:
 * - Geração automática de IDs únicos
 * - Controle de status de leitura
 * - Sistema de avaliação por notas
 * - Informações bibliográficas básicas
 */
public abstract class Item {
    
    /**
     * Contador estático para geração automática de IDs únicos.
     * Incrementado a cada nova instância criada.
     */
    private static int proxId = 1;

    /**
     * Identificador único do item.
     * Gerado automaticamente no momento da criação.
     */
    protected int id;
    
    /**
     * Título do item.
     * Não pode ser nulo ou vazio após a criação.
     */
    protected String titulo;
    
    /**
     * Nome do autor ou criador do item.
     * Pode incluir múltiplos autores separados por vírgula.
     */
    protected String autor;
    
    /**
     * Ano de publicação do item.
     * Deve ser um valor positivo e realista.
     */
    protected int anoPublicacao;
    
    /**
     * Gênero literário ou categoria do item.
     * Utiliza o enum Genero para garantir valores válidos.
     */
    protected Genero genero;
    
    /**
     * Descrição ou sinopse do item.
     * Fornece informações adicionais sobre o conteúdo.
     */
    protected String descricao;
    
    /**
     * Status de leitura do item.
     * true = já foi lido, false = ainda não foi lido.
     * Inicializado como false por padrão.
     */
    protected boolean lido;
    
    /**
     * Avaliação ou nota atribuída ao item.
     * Utiliza o enum Nota para padronizar as avaliações.
     * Inicializado como NAO_AVALIADO por padrão.
     */
    protected Nota nota;

    /**
     * Construtor para inicializar um novo Item.
     * 
     * O ID é gerado automaticamente de forma incremental.
     * O status de leitura é inicializado como false.
     * A nota é inicializada como NAO_AVALIADO.
     *
     * @param titulo        O título do item (não pode ser null ou vazio)
     * @param autor         O autor do item (não pode ser null ou vazio)
     * @param anoPublicacao O ano de publicação (deve ser um valor positivo)
     * @param genero        O gênero do item (deve ser um valor válido do enum Genero)
     * @param descricao     A descrição ou sinopse do item (pode ser null ou vazio)
     * 
     * @throws IllegalArgumentException se algum parâmetro obrigatório for inválido
     */
    public Item(String titulo, String autor, int anoPublicacao, Genero genero, String descricao) {
        this.id = proxId++;
        this.titulo = titulo;
        this.autor = autor;
        this.anoPublicacao = anoPublicacao;
        this.genero = genero;
        this.descricao = descricao;
        this.lido = false;
        this.nota = Nota.NAO_AVALIADO;
    }

    // ==================== GETTERS E SETTERS ====================

    /**
     * Obtém o identificador único do item.
     * 
     * @return O ID do item (sempre maior que 0)
     */
    public int getId() {
        return id;
    }

    /**
     * Obtém o título do item.
     * 
     * @return O título do item
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Define um novo título para o item.
     * 
     * @param titulo O novo título (não deve ser null ou vazio)
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * Obtém o autor do item.
     * 
     * @return O nome do autor
     */
    public String getAutor() {
        return autor;
    }

    /**
     * Define um novo autor para o item.
     * 
     * @param autor O novo autor (não deve ser null ou vazio)
     */
    public void setAutor(String autor) {
        this.autor = autor;
    }

    /**
     * Obtém o ano de publicação do item.
     * 
     * @return O ano de publicação
     */
    public int getAnoPublicacao() {
        return anoPublicacao;
    }

    /**
     * Define um novo ano de publicação para o item.
     * 
     * @param anoPublicacao O novo ano de publicação (deve ser positivo)
     */
    public void setAnoPublicacao(int anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }

    /**
     * Obtém o gênero do item.
     * 
     * @return O gênero do item (valor do enum Genero)
     */
    public Genero getGenero() {
        return genero;
    }

    /**
     * Define um novo gênero para o item.
     * 
     * @param genero O novo gênero (deve ser um valor válido do enum Genero)
     */
    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    /**
     * Obtém a descrição do item.
     * 
     * @return A descrição ou sinopse do item
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Define uma nova descrição para o item.
     * 
     * @param descricao A nova descrição (pode ser null ou vazia)
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * Verifica se o item já foi lido.
     * 
     * @return true se o item foi lido, false caso contrário
     */
    public boolean isLido() {
        return lido;
    }

    /**
     * Define o status de leitura do item.
     * 
     * Comportamento especial: Se o item for marcado como "não lido" (false),
     * a avaliação é automaticamente resetada para NAO_AVALIADO, pois não
     * faz sentido manter uma avaliação de algo que não foi lido.
     * 
     * @param lido true se o item foi lido, false caso contrário
     */
    public void setLido(boolean lido) {
        this.lido = lido;

        // Reset da avaliação quando marcado como não lido
        if (!lido) {
            this.nota = Nota.NAO_AVALIADO;
        }
    }

    /**
     * Obtém a avaliação atual do item.
     * 
     * @return A nota/avaliação do item (valor do enum Nota)
     */
    public Nota getNota() {
        return nota;
    }

    /**
     * Define uma nova avaliação para o item.
     * 
     * IMPORTANTE: Este método apenas define a nota. A validação de regras
     * de negócio (como verificar se o item foi lido antes de avaliar)
     * deve ser implementada na camada de serviço (Service Layer).
     * 
     * Esta separação de responsabilidades mantém o modelo limpo e
     * centraliza a lógica de negócio nos serviços apropriados.
     *
     * @param nota A nova avaliação (deve ser um valor válido do enum Nota)
     */
    public void setNota(Nota nota) {
        this.nota = nota;
    }

    // ==================== MÉTODOS ABSTRATOS ====================

    /**
     * Obtém o tipo específico do item.
     * 
     * Este método deve ser implementado por cada subclasse concreta
     * (Livro, Ebook, Audiobook, etc.) para retornar uma string
     * que identifique o tipo específico do item.
     * 
     * Exemplos de retorno esperados:
     * - "Livro Físico"
     * - "E-book"
     * - "Audiobook"
     * - "Revista"
     *
     * @return Uma string representando o tipo específico do item
     */
    public abstract String getTipo();

    // ==================== MÉTODOS SOBRESCRITOS ====================

    /**
     * Compara dois objetos Item para verificar se são iguais.
     * 
     * A comparação é baseada exclusivamente no ID do item, pois
     * cada item possui um identificador único. Isso garante que
     * dois itens sejam considerados iguais apenas se tiverem o
     * mesmo ID, independentemente de outros atributos.
     * 
     * Este método é essencial para o correto funcionamento de
     * estruturas de dados como Collections (contains, remove, etc.).
     * 
     * @param o Objeto a ser comparado com este item
     * @return true se os objetos possuem o mesmo ID, false caso contrário
     */
    @Override
    public boolean equals(Object o) {
        // Verifica se é a mesma referência
        if (this == o) return true;
        
        // Verifica se o objeto é nulo ou de classe diferente
        if (o == null || getClass() != o.getClass()) return false;
        
        // Faz o cast e compara os IDs
        Item item = (Item) o;
        return id == item.id;
    }

    /**
     * Gera o código hash do item baseado no ID.
     * 
     * Este método deve ser consistente com equals(): se dois objetos
     * são iguais segundo equals(), eles devem ter o mesmo hashCode().
     * 
     * O uso apenas do ID para o hash garante essa consistência e
     * otimiza a performance em estruturas como HashMap e HashSet.
     * 
     * @return Código hash baseado no ID do item
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    /**
     * Retorna uma representação textual completa do item.
     * 
     * Esta representação inclui todos os atributos principais do item
     * em formato legível, sendo útil para:
     * - Logs e debug
     * - Exibição em interfaces de usuário
     * - Relatórios
     * - Testes unitários
     * 
     * O formato inclui labels descritivos para facilitar a leitura.
     * 
     * @return String formatada contendo todos os atributos do item
     */
    @Override
    public String toString() {
        return "ID: " + id +
               ", Título: '" + titulo + '\'' +
               ", Autor: '" + autor + '\'' +
               ", Ano: " + anoPublicacao +
               ", Gênero: " + genero.toString() +
               ", Lido: " + (lido ? "Sim" : "Não") +
               ", Avaliação: " + nota.toString() +
               ", Descrição: '" + descricao + '\'';
    }
}