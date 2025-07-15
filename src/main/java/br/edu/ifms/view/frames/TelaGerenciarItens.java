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
import java.util.function.Consumer;

public class TelaGerenciarItens extends JPanel {

    // Listas para armazenar os itens
    private List<Livro> livros;
    private List<Ebook> ebooks;
    private List<Audiobook> audiobooks;

    // Componentes da interface
    private JTabbedPane tabbedPane;
    private JTable tabelaLivros;
    private JTable tabelaEbooks;
    private JTable tabelaAudiobooks;

    private DefaultTableModel modeloLivros;
    private DefaultTableModel modeloEbooks;
    private DefaultTableModel modeloAudiobooks;

    // Botões (serão passados para os painéis)
    private JButton btnMarcarLidoLivro;
    private JButton btnEditarLivro;
    private JButton btnMarcarLidoEbook;
    private JButton btnEditarEbook;
    private JButton btnMarcarLidoAudiobook;
    private JButton btnEditarAudiobook;
    private JButton btnVoltar;

    // Referência para a tela principal
    private TelaInicial telaPrincipal;

    public TelaGerenciarItens(TelaInicial telaPrincipal) {
        this.telaPrincipal = telaPrincipal;
        inicializarDados();
        initComponents();
        configurarEventos();
    }

    private void inicializarDados() {
        livros = new ArrayList<>();
        ebooks = new ArrayList<>();
        audiobooks = new ArrayList<>();

        // Dados de exemplo
        livros.add(new Livro("O Senhor dos Anéis", "J.R.R. Tolkien", 1954,
                            Genero.FANTASIA, "Uma épica aventura na Terra Média", 1200, "978-0544003415"));
        livros.add(new Livro("1984", "George Orwell", 1949,
                            Genero.FICCAO, "Distopia sobre totalitarismo", 328, "978-0452284234"));

        ebooks.add(new Ebook("Steve Jobs", "Walter Isaacson", 2011,
                            Genero.BIOGRAFIA, "Biografia do fundador da Apple", "Kindle"));
        ebooks.add(new Ebook("Sapiens", "Yuval Noah Harari", 2011,
                            Genero.HISTORIA, "Uma breve história da humanidade", "Tablet"));

        audiobooks.add(new Audiobook("Mindset", "Carol Dweck", 2006,
                                    Genero.AUTOAJUDA, "Sobre mentalidade de crescimento", 480, "Amy Eldon"));
        audiobooks.add(new Audiobook("Atomic Habits", "James Clear", 2018,
                                    Genero.AUTOAJUDA, "Como formar bons hábitos", 350, "James Clear"));
    }

    private void initComponents() {
        // Configuração do painel
        setBackground(StyleConstants.SECONDARY_COLOR);
        setLayout(new BorderLayout());

        // Título
        JLabel titulo = new JLabel("Gerenciar Itens da Biblioteca");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titulo.setForeground(StyleConstants.PRIMARY_COLOR);
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        titulo.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(titulo, BorderLayout.NORTH);

        // Inicializar botões que serão passados para os painéis
        btnMarcarLidoLivro = new JButton("Marcar como Lido");
        btnEditarLivro = new JButton("Ver Detalhes");
        btnMarcarLidoEbook = new JButton("Marcar como Lido");
        btnEditarEbook = new JButton("Ver Detalhes");
        btnMarcarLidoAudiobook = new JButton("Marcar como Lido");
        btnEditarAudiobook = new JButton("Ver Detalhes");

        // Criar TabbedPane
        tabbedPane = new JTabbedPane();
        tabbedPane.setFont(StyleConstants.FONT_BOLD);
        tabbedPane.setBackground(StyleConstants.SECONDARY_COLOR);
        tabbedPane.setForeground(StyleConstants.PRIMARY_COLOR);

        // Criar e adicionar abas usando as novas classes de painel
        // O Consumer<String> é o callback para o método atualizarTabela
        PainelLivro painelLivro = new PainelLivro(livros, this::atualizarTabela, btnMarcarLidoLivro, btnEditarLivro);
        tabbedPane.addTab("Livros", painelLivro.criarPainelLivros());
        this.modeloLivros = painelLivro.getModeloLivros();
        this.tabelaLivros = painelLivro.getTabelaLivros();

        PainelEbook painelEbook = new PainelEbook(ebooks, this::atualizarTabela, btnMarcarLidoEbook, btnEditarEbook);
        tabbedPane.addTab("Ebooks", painelEbook.criarPainelEbooks());
        this.modeloEbooks = painelEbook.getModeloEbooks();
        this.tabelaEbooks = painelEbook.getTabelaEbooks();

        PainelAudiobook painelAudiobook = new PainelAudiobook(audiobooks, this::atualizarTabela, btnMarcarLidoAudiobook, btnEditarAudiobook);
        tabbedPane.addTab("Audiobooks", painelAudiobook.criarPainelAudiobooks());
        this.modeloAudiobooks = painelAudiobook.getModeloAudiobooks();
        this.tabelaAudiobooks = painelAudiobook.getTabelaAudiobooks();

        add(tabbedPane, BorderLayout.CENTER);

        // Painel inferior com botão voltar
        JPanel painelInferior = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        painelInferior.setBackground(StyleConstants.SECONDARY_COLOR);
        painelInferior.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        btnVoltar = new JButton("Voltar ao Menu");
        ButtonStyles.applyDangerStyle(btnVoltar);
        painelInferior.add(btnVoltar);

        add(painelInferior, BorderLayout.SOUTH);

        // Carregar dados nas tabelas
        carregarDadosTabelas();
    }

    private void carregarDadosTabelas() {
        // Carregar dados dos livros
        for (Livro livro : livros) {
            Object[] linha = {
                livro.getId(),
                livro.getTitulo(),
                livro.getAutor(),
                livro.isLido() ? "Sim" : "Não",
                livro.getNota().getDescricao()
            };
            modeloLivros.addRow(linha);
        }

        // Carregar dados dos ebooks
        for (Ebook ebook : ebooks) {
            Object[] linha = {
                ebook.getId(),
                ebook.getTitulo(),
                ebook.getAutor(),
                ebook.isLido() ? "Sim" : "Não",
                ebook.getNota().getDescricao()
            };
            modeloEbooks.addRow(linha);
        }

        // Carregar dados dos audiobooks
        for (Audiobook audiobook : audiobooks) {
            Object[] linha = {
                audiobook.getId(),
                audiobook.getTitulo(),
                audiobook.getAutor(),
                audiobook.isLido() ? "Sim" : "Não",
                audiobook.getNota().getDescricao()
            };
            modeloAudiobooks.addRow(linha);
        }
    }

    private void configurarEventos() {
        // Eventos dos botões de livros
        btnMarcarLidoLivro.addActionListener(e -> marcarComoLido("livro"));
        btnEditarLivro.addActionListener(e -> editarItem("livro"));

        // Eventos dos botões de ebooks
        btnMarcarLidoEbook.addActionListener(e -> marcarComoLido("ebook"));
        btnEditarEbook.addActionListener(e -> editarItem("ebook"));

        // Eventos dos botões de audiobooks
        btnMarcarLidoAudiobook.addActionListener(e -> marcarComoLido("audiobook"));
        btnEditarAudiobook.addActionListener(e -> editarItem("audiobook"));

        // Evento do botão voltar
        btnVoltar.addActionListener(e -> {
            telaPrincipal.voltarParaMenu();
        });
    }

    private void marcarComoLido(String tipo) {
        JTable tabela = null;
        List<? extends Item> lista = null;

        switch (tipo) {
            case "livro":
                tabela = tabelaLivros;
                lista = livros;
                break;
            case "ebook":
                tabela = tabelaEbooks;
                lista = ebooks;
                break;
            case "audiobook":
                tabela = tabelaAudiobooks;
                lista = audiobooks;
                break;
        }

        if (tabela == null || tabela.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um item para marcar como lido.",
                                        "Nenhum item selecionado", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int linhaSelecionada = tabela.getSelectedRow();
        int id = (Integer) tabela.getValueAt(linhaSelecionada, 0);

        // Encontrar o item na lista
        Item itemSelecionado = null;
        for (Item item : lista) {
            if (item.getId() == id) {
                itemSelecionado = item;
                break;
            }
        }

        if (itemSelecionado == null) return;

        if (itemSelecionado.isLido()) {
            JOptionPane.showMessageDialog(this, "Este item já foi marcado como lido.",
                                        "Item já lido", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        // Confirmação
        int confirmacao = JOptionPane.showConfirmDialog(this,
            "Deseja marcar '" + itemSelecionado.getTitulo() + "' como lido?",
            "Confirmar", JOptionPane.YES_NO_OPTION);

        if (confirmacao == JOptionPane.YES_OPTION) {
            itemSelecionado.setLido(true);

            // Abrir tela de avaliação
            abrirTelaAvaliacao(itemSelecionado);

            // Atualizar tabela
            atualizarTabela(tipo);
        }
    }

    private void abrirTelaAvaliacao(Item item) {
        String[] opcoes = {"★", "★★", "★★★", "★★★★", "★★★★★"};
        String avaliacao = (String) JOptionPane.showInputDialog(this,
            "Como você avalia '" + item.getTitulo() + "'?",
            "Avaliar Item",
            JOptionPane.QUESTION_MESSAGE,
            null,
            opcoes,
            opcoes[2]);

        if (avaliacao != null) {
            switch (avaliacao) {
                case "★":
                    item.setNota(Nota.UMA_ESTRELA);
                    break;
                case "★★":
                    item.setNota(Nota.DUAS_ESTRELAS);
                    break;
                case "★★★":
                    item.setNota(Nota.TRES_ESTRELAS);
                    break;
                case "★★★★":
                    item.setNota(Nota.QUATRO_ESTRELAS);
                    break;
                case "★★★★★":
                    item.setNota(Nota.CINCO_ESTRELAS);
                    break;
            }
        }
    }

    private void editarItem(String tipo) {
        JTable tabela = null;
        List<? extends Item> lista = null;

        switch (tipo) {
            case "livro":
                tabela = tabelaLivros;
                lista = livros;
                break;
            case "ebook":
                tabela = tabelaEbooks;
                lista = ebooks;
                break;
            case "audiobook":
                tabela = tabelaAudiobooks;
                lista = audiobooks;
                break;
        }

        if (tabela == null || tabela.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um item para editar.",
                                        "Nenhum item selecionado", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int linhaSelecionada = tabela.getSelectedRow();
        int id = (Integer) tabela.getValueAt(linhaSelecionada, 0);

        // Encontrar o item na lista
        Item itemSelecionado = null;
        for (Item item : lista) {
            if (item.getId() == id) {
                itemSelecionado = item;
                break;
            }
        }

        if (itemSelecionado != null) {
            mostrarDetalhesItem(itemSelecionado);
        }
    }

    private void mostrarDetalhesItem(Item item) {
        StringBuilder detalhes = new StringBuilder();
        detalhes.append("ID: ").append(item.getId()).append("\n");
        detalhes.append("Título: ").append(item.getTitulo()).append("\n");
        detalhes.append("Autor: ").append(item.getAutor()).append("\n");
        detalhes.append("Ano: ").append(item.getAnoPublicacao()).append("\n");
        detalhes.append("Gênero: ").append(item.getGenero().toString()).append("\n");
        detalhes.append("Descrição: ").append(item.getDescricao()).append("\n");
        detalhes.append("Lido: ").append(item.isLido() ? "Sim" : "Não").append("\n");
        detalhes.append("Avaliação: ").append(item.getNota().toString()).append("\n");

        // Adicionar detalhes específicos do tipo
        if (item instanceof Livro) {
            Livro livro = (Livro) item;
            detalhes.append("Páginas: ").append(livro.getNumPaginas()).append("\n");
            detalhes.append("ISBN: ").append(livro.getIsbn()).append("\n");
        } else if (item instanceof Ebook) {
            Ebook ebook = (Ebook) item;
            detalhes.append("Dispositivo: ").append(ebook.getDispositivo()).append("\n");
        } else if (item instanceof Audiobook) {
            Audiobook audiobook = (Audiobook) item;
            detalhes.append("Duração: ").append(audiobook.getDuracaoMinutos()).append(" min\n");
            detalhes.append("Narrador: ").append(audiobook.getNarrador()).append("\n");
        }

        JTextArea textArea = new JTextArea(detalhes.toString());
        textArea.setFont(StyleConstants.FONT);
        textArea.setEditable(false);
        textArea.setRows(12);
        textArea.setColumns(30);

        JScrollPane scrollPane = new JScrollPane(textArea);

        JOptionPane.showMessageDialog(this, scrollPane, "Detalhes do Item", JOptionPane.INFORMATION_MESSAGE);
    }

    private void atualizarTabela(String tipo) {
        switch (tipo) {
            case "livro":
                modeloLivros.setRowCount(0);
                for (Livro livro : livros) {
                    Object[] linha = {
                        livro.getId(),
                        livro.getTitulo(),
                        livro.getAutor(),
                        livro.isLido() ? "Sim" : "Não",
                        livro.getNota().getDescricao()
                    };
                    modeloLivros.addRow(linha);
                }
                break;
            case "ebook":
                modeloEbooks.setRowCount(0);
                for (Ebook ebook : ebooks) {
                    Object[] linha = {
                        ebook.getId(),
                        ebook.getTitulo(),
                        ebook.getAutor(),
                        ebook.isLido() ? "Sim" : "Não",
                        ebook.getNota().getDescricao()
                    };
                    modeloEbooks.addRow(linha);
                }
                break;
            case "audiobook":
                modeloAudiobooks.setRowCount(0);
                for (Audiobook audiobook : audiobooks) {
                    Object[] linha = {
                        audiobook.getId(),
                        audiobook.getTitulo(),
                        audiobook.getAutor(),
                        audiobook.isLido() ? "Sim" : "Não",
                        audiobook.getNota().getDescricao()
                    };
                    modeloAudiobooks.addRow(linha);
                }
                break;
        }
    }

    public Container getContentPane() {
        return this;
    }
}