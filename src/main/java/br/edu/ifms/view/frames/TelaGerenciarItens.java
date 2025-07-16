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

public class TelaGerenciarItens extends JPanel {

    private List<Livro> livros;
    private List<Ebook> ebooks;
    private List<Audiobook> audiobooks;

    private JTabbedPane tabbedPane;
    private JTable tabelaLivros, tabelaEbooks, tabelaAudiobooks;
    private DefaultTableModel modeloLivros, modeloEbooks, modeloAudiobooks;

    private JButton btnMarcarLidoLivro, btnMarcarLidoEbook, btnMarcarLidoAudiobook, btnVoltar;

    private PainelLivro painelLivro;
    private PainelEbook painelEbook;
    private PainelAudiobook painelAudiobook;

    private TelaInicial telaPrincipal;

    public TelaGerenciarItens(TelaInicial telaPrincipal) {
        this.telaPrincipal = telaPrincipal;
        inicializarDados();
        initComponents();
    }

    private void inicializarDados() {
        livros = new ArrayList<>();
        ebooks = new ArrayList<>();
        audiobooks = new ArrayList<>();

        livros.add(new Livro("O Senhor dos Anéis", "J.R.R. Tolkien", 1954, Genero.FANTASIA, "Uma épica aventura na Terra Média", 1200, "978-0544003415"));
        livros.add(new Livro("1984", "George Orwell", 1949, Genero.FICCAO, "Distopia sobre totalitarismo", 328, "978-0452284234"));
        ebooks.add(new Ebook("Steve Jobs", "Walter Isaacson", 2011, Genero.BIOGRAFIA, "Biografia do fundador da Apple", "Kindle"));
        ebooks.add(new Ebook("Sapiens", "Yuval Noah Harari", 2011, Genero.HISTORIA, "Uma breve história da humanidade", "Tablet"));
        audiobooks.add(new Audiobook("Mindset", "Carol Dweck", 2006, Genero.AUTOAJUDA, "Sobre mentalidade de crescimento", 480, "Amy Eldon"));
        audiobooks.add(new Audiobook("Atomic Habits", "James Clear", 2018, Genero.AUTOAJUDA, "Como formar bons hábitos", 350, "James Clear"));
    }

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

    private void carregarDadosTabelas() {
        atualizarTabela("livro");
        atualizarTabela("ebook");
        atualizarTabela("audiobook");
    }

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

    public Container getContentPane() { return this; }
    public List<Livro> getLivros() { return livros; }
    public List<Ebook> getEbooks() { return ebooks; }
    public List<Audiobook> getAudiobooks() { return audiobooks; }
    public TelaInicial getTelaPrincipal() { return telaPrincipal; }
    public JButton getBtnVoltar() { return btnVoltar; }
    public JTable getTabelaLivros() { return tabelaLivros; }
    public JTable getTabelaEbooks() { return tabelaEbooks; }
    public JTable getTabelaAudiobooks() { return tabelaAudiobooks; }
    public JButton getBtnMarcarLidoLivro() { return btnMarcarLidoLivro; }
    public JButton getBtnMarcarLidoEbook() { return btnMarcarLidoEbook; }
    public JButton getBtnMarcarLidoAudiobook() { return btnMarcarLidoAudiobook; }
    public PainelLivro getPainelLivro() { return painelLivro; }
    public PainelEbook getPainelEbook() { return painelEbook; }
    public PainelAudiobook getPainelAudiobook() { return painelAudiobook; }
}