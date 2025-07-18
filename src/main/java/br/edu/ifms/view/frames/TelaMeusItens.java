package br.edu.ifms.view.frames;

import br.edu.ifms.model.*;
import br.edu.ifms.view.styles.ButtonStyles;
import br.edu.ifms.view.styles.StyleConstants;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Painel para visualizar e filtrar todos os itens da biblioteca em uma única tabela.
 */
public class TelaMeusItens extends JPanel {
    
    // --- Dados ---
    private TelaInicial telaPrincipal;
    private List<Livro> livros;
    private List<Ebook> ebooks;
    private List<Audiobook> audiobooks;
    private List<Item> todosItens;
    
    // --- Componentes UI ---
    private JTextField campoBusca;
    private JComboBox<String> comboTipo, comboGenero, comboAvaliacao, comboStatus;
    private JTable tabelaItens;
    private DefaultTableModel modeloTabela;
    private TableRowSorter<DefaultTableModel> sorter;
    private JButton btnLimparFiltros, btnVoltar;
    private JLabel lblTotalItens;
    
    /**
     * Construtor da tela Meus Itens.
     * @param telaPrincipal Referência à tela principal.
     * @param livros Lista de livros.
     * @param ebooks Lista de ebooks.
     * @param audiobooks Lista de audiobooks.
     */
    public TelaMeusItens(TelaInicial telaPrincipal, List<Livro> livros, 
                         List<Ebook> ebooks, List<Audiobook> audiobooks) {
        this.telaPrincipal = telaPrincipal;
        this.livros = livros;
        this.ebooks = ebooks;
        this.audiobooks = audiobooks;
        this.todosItens = new ArrayList<>();
        
        initComponents();
        configurarEventos();
        atualizarListaItens();
        carregarDadosTabela();
    }
    
    /** Cria e configura os componentes da interface. */
    private void initComponents() {
        setBackground(StyleConstants.SECONDARY_COLOR);
        setLayout(new BorderLayout());
        
        JPanel painelSuperior = new JPanel(new BorderLayout());
        painelSuperior.setBackground(StyleConstants.SECONDARY_COLOR);
        
        JLabel titulo = new JLabel("Meus Itens");
        titulo.setFont(StyleConstants.TITLE);
        titulo.setForeground(StyleConstants.PRIMARY_COLOR);
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        titulo.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        painelSuperior.add(titulo, BorderLayout.NORTH);
        
        painelSuperior.add(criarPainelFiltros(), BorderLayout.CENTER);
        add(painelSuperior, BorderLayout.NORTH);
        
        JPanel painelCentral = new JPanel(new BorderLayout());
        painelCentral.setBackground(StyleConstants.SECONDARY_COLOR);
        painelCentral.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        
        String[] colunas = {"Tipo", "Título", "Autor", "Gênero", "Ano", "Status", "Avaliação"};
        modeloTabela = new DefaultTableModel(colunas, 0) {
            @Override public boolean isCellEditable(int row, int column) { return false; }
        };
        
        tabelaItens = new JTable(modeloTabela);
        tabelaItens.setFont(StyleConstants.FONT);
        tabelaItens.setRowHeight(25);
        tabelaItens.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabelaItens.setForeground(StyleConstants.PRIMARY_COLOR);
        tabelaItens.getTableHeader().setBackground(StyleConstants.PRIMARY_COLOR);
        tabelaItens.getTableHeader().setForeground(Color.WHITE);
        tabelaItens.getTableHeader().setFont(StyleConstants.FONT_BOLD);
        
        sorter = new TableRowSorter<>(modeloTabela);
        tabelaItens.setRowSorter(sorter);
        
        JScrollPane scrollPane = new JScrollPane(tabelaItens);
        scrollPane.setBorder(BorderFactory.createLineBorder(StyleConstants.PRIMARY_COLOR));
        painelCentral.add(scrollPane, BorderLayout.CENTER);
        
        JPanel painelInfo = new JPanel(new FlowLayout(FlowLayout.LEFT));
        painelInfo.setBackground(StyleConstants.SECONDARY_COLOR);
        painelInfo.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        
        lblTotalItens = new JLabel("Total de itens: 0");
        lblTotalItens.setFont(StyleConstants.FONT_BOLD);
        lblTotalItens.setForeground(StyleConstants.PRIMARY_COLOR);
        painelInfo.add(lblTotalItens);
        
        painelCentral.add(painelInfo, BorderLayout.SOUTH);
        add(painelCentral, BorderLayout.CENTER);
        
        JPanel painelInferior = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        painelInferior.setBackground(StyleConstants.SECONDARY_COLOR);
        painelInferior.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        btnVoltar = new JButton("Voltar ao Menu");
        ButtonStyles.applyDangerStyle(btnVoltar);
        painelInferior.add(btnVoltar);
        add(painelInferior, BorderLayout.SOUTH);
    }
    
    /**
     * Cria o painel com todos os campos de filtro e busca.
     * @return O painel de filtros.
     */
    private JPanel criarPainelFiltros() {
        //... Implementação do método (já concisa)
        JPanel painelFiltros = new JPanel(new GridBagLayout());
        painelFiltros.setBackground(StyleConstants.SECONDARY_HOVER);
        painelFiltros.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(StyleConstants.PRIMARY_COLOR, 1),
            BorderFactory.createEmptyBorder(15, 20, 15, 20)
        ));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        
        JLabel tituloFiltros = new JLabel("Filtros e Busca");
        tituloFiltros.setFont(StyleConstants.LARGE_FONT);
        tituloFiltros.setForeground(StyleConstants.PRIMARY_COLOR);
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 4;
        painelFiltros.add(tituloFiltros, gbc);
        
        gbc.gridwidth = 1; gbc.gridy = 1;
        
        JLabel lblBusca = new JLabel("Buscar:");
        lblBusca.setFont(StyleConstants.FONT_BOLD);
        lblBusca.setForeground(StyleConstants.PRIMARY_COLOR);
        gbc.gridx = 0;
        painelFiltros.add(lblBusca, gbc);
        
        campoBusca = new JTextField(20);
        campoBusca.setFont(StyleConstants.FONT);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        painelFiltros.add(campoBusca, gbc);
        
        JLabel lblTipo = new JLabel("Tipo:");
        lblTipo.setFont(StyleConstants.FONT_BOLD);
        lblTipo.setForeground(StyleConstants.PRIMARY_COLOR);
        gbc.gridx = 2; gbc.fill = GridBagConstraints.NONE;
        painelFiltros.add(lblTipo, gbc);
        
        comboTipo = new JComboBox<>(new String[]{"Todos", "Livro", "Ebook", "Audiobook"});
        comboTipo.setFont(StyleConstants.FONT);
        gbc.gridx = 3;
        painelFiltros.add(comboTipo, gbc);
        
        gbc.gridy = 2;
        
        JLabel lblGenero = new JLabel("Gênero:");
        lblGenero.setFont(StyleConstants.FONT_BOLD);
        lblGenero.setForeground(StyleConstants.PRIMARY_COLOR);
        gbc.gridx = 0;
        painelFiltros.add(lblGenero, gbc);
        
        Genero[] generosEnum = Genero.values();
        String[] generosParaCombo = new String[generosEnum.length + 1];
        generosParaCombo[0] = "Todos";
        for (int i = 0; i < generosEnum.length; i++) { generosParaCombo[i + 1] = generosEnum[i].toString(); }
        comboGenero = new JComboBox<>(generosParaCombo);
        comboGenero.setFont(StyleConstants.FONT);
        gbc.gridx = 1;
        painelFiltros.add(comboGenero, gbc);
        
        JLabel lblAvaliacao = new JLabel("Avaliação:");
        lblAvaliacao.setFont(StyleConstants.FONT_BOLD);
        lblAvaliacao.setForeground(StyleConstants.PRIMARY_COLOR);
        gbc.gridx = 2;
        painelFiltros.add(lblAvaliacao, gbc);
        
        comboAvaliacao = new JComboBox<>(new String[]{"Todas", "Muito Ruim", "Ruim", "Regular", "Bom", "Excelente", "Não avaliado"});
        comboAvaliacao.setFont(StyleConstants.FONT);
        gbc.gridx = 3;
        painelFiltros.add(comboAvaliacao, gbc);
        
        gbc.gridy = 3;
        
        JLabel lblStatus = new JLabel("Status:");
        lblStatus.setFont(StyleConstants.FONT_BOLD);
        lblStatus.setForeground(StyleConstants.PRIMARY_COLOR);
        gbc.gridx = 0;
        painelFiltros.add(lblStatus, gbc);
        
        comboStatus = new JComboBox<>(new String[]{"Todos", "Lido", "Não lido"});
        comboStatus.setFont(StyleConstants.FONT);
        gbc.gridx = 1;
        painelFiltros.add(comboStatus, gbc);
        
        btnLimparFiltros = new JButton("Limpar Filtros");
        ButtonStyles.applyDefaultStyle(btnLimparFiltros);
        gbc.gridx = 2; gbc.gridwidth = 2;
        painelFiltros.add(btnLimparFiltros, gbc);
        
        return painelFiltros;
    }
    
    /** Configura os listeners para os botões e campos de filtro. */
    private void configurarEventos() {
        btnVoltar.addActionListener(e -> telaPrincipal.voltarParaMenu());
        btnLimparFiltros.addActionListener(e -> limparFiltros());
        
        campoBusca.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override public void keyReleased(java.awt.event.KeyEvent evt) { aplicarFiltros(); }
        });
        
        comboTipo.addActionListener(e -> aplicarFiltros());
        comboGenero.addActionListener(e -> aplicarFiltros());
        comboAvaliacao.addActionListener(e -> aplicarFiltros());
        comboStatus.addActionListener(e -> aplicarFiltros());
    }
    
    /** Combina as listas de livros, ebooks e audiobooks em uma única lista. */
    private void atualizarListaItens() {
        todosItens.clear();
        todosItens.addAll(livros);
        todosItens.addAll(ebooks);
        todosItens.addAll(audiobooks);
    }
    
    /** Popula a tabela com os dados da lista de todos os itens. */
    private void carregarDadosTabela() {
        modeloTabela.setRowCount(0);
        
        for (Item item : todosItens) {
            String tipo = item.getTipo(); // Usando o método polimórfico
            Object[] linha = {
                tipo, item.getTitulo(), item.getAutor(), item.getGenero().toString(),
                item.getAnoPublicacao(), item.isLido() ? "Lido" : "Não lido", item.getNota().getDescricao()
            };
            modeloTabela.addRow(linha);
        }
        
        atualizarContadorItens();
    }
    
    /** Aplica os filtros selecionados à tabela de itens. */
    private void aplicarFiltros() {
        List<RowFilter<Object, Object>> filtros = new ArrayList<>();
        
        String textoBusca = campoBusca.getText().trim();
        if (!textoBusca.isEmpty()) {
            filtros.add(RowFilter.regexFilter("(?i)" + textoBusca, 1, 2, 3));
        }
        
        String tipoSelecionado = (String) comboTipo.getSelectedItem();
        if (!"Todos".equals(tipoSelecionado)) {
            filtros.add(RowFilter.regexFilter(tipoSelecionado, 0));
        }
        
        String generoSelecionado = (String) comboGenero.getSelectedItem();
        if (!"Todos".equals(generoSelecionado)) {
            filtros.add(RowFilter.regexFilter(generoSelecionado, 3));
        }
        
        String avaliacaoSelecionada = (String) comboAvaliacao.getSelectedItem();
        if (!"Todas".equals(avaliacaoSelecionada)) {
            filtros.add(RowFilter.regexFilter(avaliacaoSelecionada, 6));
        }
        
        String statusSelecionado = (String) comboStatus.getSelectedItem();
        if (!"Todos".equals(statusSelecionado)) {
            filtros.add(RowFilter.regexFilter(statusSelecionado, 5));
        }
        
        sorter.setRowFilter(filtros.isEmpty() ? null : RowFilter.andFilter(filtros));
        
        atualizarContadorItens();
    }
    
    /** Reseta todos os campos de filtro e exibe todos os itens. */
    private void limparFiltros() {
        campoBusca.setText("");
        comboTipo.setSelectedIndex(0);
        comboGenero.setSelectedIndex(0);
        comboAvaliacao.setSelectedIndex(0);
        comboStatus.setSelectedIndex(0);
        sorter.setRowFilter(null);
        atualizarContadorItens();
    }
    
    /** Atualiza o rótulo que exibe o número de itens visíveis. */
    private void atualizarContadorItens() {
        int totalVisivel = tabelaItens.getRowCount();
        int totalGeral = todosItens.size();
        
        if (totalVisivel == totalGeral) {
            lblTotalItens.setText("Total de itens: " + totalGeral);
        } else {
            lblTotalItens.setText("Mostrando " + totalVisivel + " de " + totalGeral + " itens");
        }
    }
    
    /** @return O item selecionado na tabela. */
    public Item getItemSelecionado() {
        int linhaSelecionada = tabelaItens.getSelectedRow();
        if (linhaSelecionada == -1) return null;
        
        int linhaReal = tabelaItens.convertRowIndexToModel(linhaSelecionada);
        // Evita IndexOutOfBoundsException se a lista for modificada
        if (linhaReal >= 0 && linhaReal < todosItens.size()) {
            return todosItens.get(linhaReal);
        }
        return null;
    }
}