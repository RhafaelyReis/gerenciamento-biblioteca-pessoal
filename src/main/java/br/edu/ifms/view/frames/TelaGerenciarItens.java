package br.edu.ifms.view.frames;

import br.edu.ifms.model.*;
import br.edu.ifms.view.panels.PainelAudiobook;
import br.edu.ifms.view.panels.PainelEbook;
import br.edu.ifms.view.panels.PainelLivro;
import br.edu.ifms.view.styles.ButtonStyles;
import br.edu.ifms.view.styles.StyleConstants;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Painel para gerenciar (adicionar, editar, remover) livros, ebooks e audiobooks.
 */
public class TelaGerenciarItens extends JPanel {

    // --- Listas de Dados ---
    private List<Livro> livros;
    private List<Ebook> ebooks;
    private List<Audiobook> audiobooks;

    // --- Componentes UI ---
    private JTabbedPane tabbedPane;
    private JTable tabelaLivros, tabelaEbooks, tabelaAudiobooks;
    private DefaultTableModel modeloLivros, modeloEbooks, modeloAudiobooks;
    private JButton btnMarcarLidoLivro, btnMarcarLidoEbook, btnMarcarLidoAudiobook, btnVoltar;
    private PainelLivro painelLivro;
    private PainelEbook painelEbook;
    private PainelAudiobook painelAudiobook;
    private TelaInicial telaPrincipal; // Referência à tela principal para navegação

    /**
     * Construtor da tela de gerenciamento.
     * @param telaPrincipal Referência à tela principal.
     */
    public TelaGerenciarItens(TelaInicial telaPrincipal) {
        this.telaPrincipal = telaPrincipal;
        inicializarDados();
        initComponents();
    }

    /** Popula as listas com dados iniciais para exemplo. */
    private void inicializarDados() {
        livros = new ArrayList<>();
        ebooks = new ArrayList<>();
        audiobooks = new ArrayList<>();

        livros.add(new Livro("O Senhor dos Anéis", "J.R.R. Tolkien", 1954, Genero.FANTASIA, "Uma épica aventura na Terra Média", 1200, "978-0544003415"));
        livros.add(new Livro("1984", "George Orwell", 1949, Genero.FICCAO, "Distopia sobre totalitarismo", 328, "978-0452284234"));
        livros.add(new Livro("Dom Casmurro", "Machado de Assis", 1899, Genero.ROMANCE, "Clássico da literatura brasileira", 256, "978-8525415162"));
        livros.add(new Livro("O Pequeno Príncipe", "Antoine de Saint-Exupéry", 1943, Genero.INFANTIL, "Fábula poética para todas as idades", 96, "978-0156012195"));
        livros.add(new Livro("A Arte da Guerra", "Sun Tzu", -500, Genero.TECNICO, "Estratégias militares e filosóficas", 273, "978-8528607385"));

        ebooks.add(new Ebook("Steve Jobs", "Walter Isaacson", 2011, Genero.BIOGRAFIA, "Biografia do fundador da Apple", "Kindle"));
        ebooks.add(new Ebook("Sapiens", "Yuval Noah Harari", 2011, Genero.HISTORIA, "Uma breve história da humanidade", "Tablet"));
        ebooks.add(new Ebook("Clean Code", "Robert C. Martin", 2008, Genero.TECNICO, "Guia para escrever código limpo", "Kindle"));
        ebooks.add(new Ebook("A Menina que Roubava Livros", "Markus Zusak", 2005, Genero.ROMANCE, "Romance histórico na Segunda Guerra", "Tablet"));
        ebooks.add(new Ebook("O Guia do Mochileiro das Galáxias", "Douglas Adams", 1979, Genero.FICCAO_CIENTIFICA, "Comédia e ficção científica", "Kindle"));

        audiobooks.add(new Audiobook("Mindset", "Carol Dweck", 2006, Genero.AUTOAJUDA, "Sobre mentalidade de crescimento", 480, "Amy Eldon"));
        audiobooks.add(new Audiobook("Atomic Habits", "James Clear", 2018, Genero.AUTOAJUDA, "Como formar bons hábitos", 350, "James Clear"));
        audiobooks.add(new Audiobook("A Revolução dos Bichos", "George Orwell", 1945, Genero.SUSPENSE, "Fábula política alegórica", 200, "Simon Prebble"));
        audiobooks.add(new Audiobook("O Poder do Hábito", "Charles Duhigg", 2012, Genero.AUTOAJUDA, "Ciência dos hábitos", 400, "Mike Chamberlain"));
        audiobooks.add(new Audiobook("Harry Potter e a Pedra Filosofal", "J.K. Rowling", 1997, Genero.FANTASIA, "Primeiro livro da saga Harry Potter", 480, "Stephen Fry"));
    }


    /** Cria e configura os componentes da interface gráfica. */
    private void initComponents() {
        setBackground(StyleConstants.SECONDARY_COLOR);
        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Gerenciar Itens da Biblioteca");
        titulo.setFont(StyleConstants.TITLE);
        titulo.setForeground(StyleConstants.PRIMARY_COLOR);
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        titulo.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(titulo, BorderLayout.NORTH);

        btnMarcarLidoLivro = new JButton("Marcar como Lido");
        btnMarcarLidoEbook = new JButton("Marcar como Lido");
        btnMarcarLidoAudiobook = new JButton("Marcar como Lido");

        tabbedPane = new JTabbedPane();
        tabbedPane.setFont(StyleConstants.FONT_BOLD);
        tabbedPane.setBackground(StyleConstants.SECONDARY_HOVER);
        tabbedPane.setForeground(StyleConstants.PRIMARY_COLOR);

        painelLivro = new PainelLivro(this::atualizarTabela, btnMarcarLidoLivro);
        tabbedPane.addTab("Livros", painelLivro.criarPainelLivros());
        this.modeloLivros = painelLivro.getModeloLivros();
        this.tabelaLivros = painelLivro.getTabelaLivros();

        painelEbook = new PainelEbook(this::atualizarTabela, btnMarcarLidoEbook);
        tabbedPane.addTab("Ebooks", painelEbook.criarPainelEbooks());
        this.modeloEbooks = painelEbook.getModeloEbooks();
        this.tabelaEbooks = painelEbook.getTabelaEbooks();

        painelAudiobook = new PainelAudiobook(this::atualizarTabela, btnMarcarLidoAudiobook);
        tabbedPane.addTab("Audiobooks", painelAudiobook.criarPainelAudiobooks());
        this.modeloAudiobooks = painelAudiobook.getModeloAudiobooks();
        this.tabelaAudiobooks = painelAudiobook.getTabelaAudiobooks();

        add(tabbedPane, BorderLayout.CENTER);

        JPanel painelInferior = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        painelInferior.setBackground(StyleConstants.SECONDARY_COLOR);
        painelInferior.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        btnVoltar = new JButton("Voltar ao Menu");
        ButtonStyles.applyDangerStyle(btnVoltar);
        painelInferior.add(btnVoltar);
        add(painelInferior, BorderLayout.SOUTH);

        carregarDadosTabelas();
    }

    /** Carrega os dados iniciais em todas as tabelas. */
    private void carregarDadosTabelas() {
        atualizarTabela("livro");
        atualizarTabela("ebook");
        atualizarTabela("audiobook");
    }

    /**
     * Atualiza os dados da tabela para um tipo de item específico.
     * @param tipo O tipo de item a ser atualizado ("livro", "ebook", "audiobook").
     */
    public void atualizarTabela(String tipo) {
        switch (tipo) {
            case "livro":
                modeloLivros.setRowCount(0);
                for (Livro livro : livros) {
                    Object[] linha = {livro.getId(), livro.getTitulo(), livro.getAutor(), livro.isLido() ? "Sim" : "Não", livro.getNota().getDescricao()};
                    modeloLivros.addRow(linha);
                }
                break;
            case "ebook":
                modeloEbooks.setRowCount(0);
                for (Ebook ebook : ebooks) {
                    Object[] linha = {ebook.getId(), ebook.getTitulo(), ebook.getAutor(), ebook.isLido() ? "Sim" : "Não", ebook.getNota().getDescricao()};
                    modeloEbooks.addRow(linha);
                }
                break;
            case "audiobook":
                modeloAudiobooks.setRowCount(0);
                for (Audiobook audiobook : audiobooks) {
                    Object[] linha = {audiobook.getId(), audiobook.getTitulo(), audiobook.getAutor(), audiobook.isLido() ? "Sim" : "Não", audiobook.getNota().getDescricao()};
                    modeloAudiobooks.addRow(linha);
                }
                break;
        }
    }

    // --- Getters ---
    public Container getContentPane() { return this; }
    /** @return A lista de livros. */
    public List<Livro> getLivros() { return livros; }
    /** @return A lista de ebooks. */
    public List<Ebook> getEbooks() { return ebooks; }
    /** @return A lista de audiobooks. */
    public List<Audiobook> getAudiobooks() { return audiobooks; }
    /** @return A referência para a tela principal. */
    public TelaInicial getTelaPrincipal() { return telaPrincipal; }
    /** @return O botão "Voltar". */
    public JButton getBtnVoltar() { return btnVoltar; }
    /** @return A tabela de livros. */
    public JTable getTabelaLivros() { return tabelaLivros; }
    /** @return A tabela de ebooks. */
    public JTable getTabelaEbooks() { return tabelaEbooks; }
    /** @return A tabela de audiobooks. */
    public JTable getTabelaAudiobooks() { return tabelaAudiobooks; }
    /** @return O botão "Marcar como Lido" para livros. */
    public JButton getBtnMarcarLidoLivro() { return btnMarcarLidoLivro; }
    /** @return O botão "Marcar como Lido" para ebooks. */
    public JButton getBtnMarcarLidoEbook() { return btnMarcarLidoEbook; }
    /** @return O botão "Marcar como Lido" para audiobooks. */
    public JButton getBtnMarcarLidoAudiobook() { return btnMarcarLidoAudiobook; }
    /** @return O painel de formulário de livro. */
    public PainelLivro getPainelLivro() { return painelLivro; }
    /** @return O painel de formulário de ebook. */
    public PainelEbook getPainelEbook() { return painelEbook; }
    /** @return O painel de formulário de audiobook. */
    public PainelAudiobook getPainelAudiobook() { return painelAudiobook; }
}