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

public class TelaMeusItens extends JPanel {
    
    private TelaInicial telaPrincipal;
    private List<Livro> livros;
    private List<Ebook> ebooks;
    private List<Audiobook> audiobooks;
    private List<Item> todosItens;
    
    private JTextField campoBusca;
    private JComboBox<String> comboTipo;
    private JComboBox<String> comboGenero;
    private JComboBox<String> comboAvaliacao;
    private JComboBox<String> comboStatus;
    private JTable tabelaItens;
    private DefaultTableModel modeloTabela;
    private TableRowSorter<DefaultTableModel> sorter;
    private JButton btnLimparFiltros;
    private JButton btnVoltar;
    private JLabel lblTotalItens;
    
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
    
    private void initComponents() {
        setBackground(StyleConstants.SECONDARY_COLOR);
        setLayout(new BorderLayout());
        
        // Criar painel superior que conterá tanto o título quanto os filtros
        JPanel painelSuperior = new JPanel(new BorderLayout());
        painelSuperior.setBackground(StyleConstants.SECONDARY_COLOR);
        
        // Título
        JLabel titulo = new JLabel("Meus Itens");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titulo.setForeground(StyleConstants.PRIMARY_COLOR);
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        titulo.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        painelSuperior.add(titulo, BorderLayout.NORTH);
        
        // Painel de filtros
        JPanel painelFiltros = criarPainelFiltros();
        painelSuperior.add(painelFiltros, BorderLayout.CENTER);
        
        // Adicionar o painel superior completo
        add(painelSuperior, BorderLayout.NORTH);
        
        // Painel central com tabela
        JPanel painelCentral = new JPanel(new BorderLayout());
        painelCentral.setBackground(StyleConstants.SECONDARY_COLOR);
        painelCentral.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        
        // Criar tabela
        String[] colunas = {"Tipo", "Título", "Autor", "Gênero", "Ano", "Status", "Avaliação"};
        modeloTabela = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        tabelaItens = new JTable(modeloTabela);
        tabelaItens.setFont(StyleConstants.FONT);
        tabelaItens.setRowHeight(25);
        tabelaItens.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabelaItens.setForeground(StyleConstants.PRIMARY_COLOR);
        tabelaItens.getTableHeader().setBackground(StyleConstants.PRIMARY_COLOR);
        tabelaItens.getTableHeader().setForeground(Color.WHITE);
        tabelaItens.getTableHeader().setFont(StyleConstants.FONT_BOLD);
        
        // Configurar sorter para filtragem
        sorter = new TableRowSorter<>(modeloTabela);
        tabelaItens.setRowSorter(sorter);
        
        JScrollPane scrollPane = new JScrollPane(tabelaItens);
        scrollPane.setBorder(BorderFactory.createLineBorder(StyleConstants.PRIMARY_COLOR));
        painelCentral.add(scrollPane, BorderLayout.CENTER);
        
        // Painel com informações
        JPanel painelInfo = new JPanel(new FlowLayout(FlowLayout.LEFT));
        painelInfo.setBackground(StyleConstants.SECONDARY_COLOR);
        painelInfo.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        
        lblTotalItens = new JLabel("Total de itens: 0");
        lblTotalItens.setFont(StyleConstants.FONT_BOLD);
        lblTotalItens.setForeground(StyleConstants.PRIMARY_COLOR);
        painelInfo.add(lblTotalItens);
        
        painelCentral.add(painelInfo, BorderLayout.SOUTH);
        
        add(painelCentral, BorderLayout.CENTER);
        
        // Painel inferior com botões
        JPanel painelInferior = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        painelInferior.setBackground(StyleConstants.SECONDARY_COLOR);
        painelInferior.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        btnVoltar = new JButton("Voltar ao Menu");
        ButtonStyles.applyDangerStyle(btnVoltar);
        painelInferior.add(btnVoltar);
        
        add(painelInferior, BorderLayout.SOUTH);
    }
    
    private JPanel criarPainelFiltros() {
        JPanel painelFiltros = new JPanel(new GridBagLayout());
        painelFiltros.setBackground(StyleConstants.SECONDARY_HOVER);
        painelFiltros.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(StyleConstants.PRIMARY_COLOR, 1),
            BorderFactory.createEmptyBorder(15, 20, 15, 20)
        ));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        
        // Título dos filtros
        JLabel tituloFiltros = new JLabel("Filtros e Busca");
        tituloFiltros.setFont(new Font("Segoe UI", Font.BOLD, 16));
        tituloFiltros.setForeground(StyleConstants.PRIMARY_COLOR);
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 4;
        painelFiltros.add(tituloFiltros, gbc);
        
        // Campo de busca
        gbc.gridwidth = 1;
        gbc.gridy = 1;
        
        JLabel lblBusca = new JLabel("Buscar:");
        lblBusca.setFont(StyleConstants.FONT_BOLD);
        lblBusca.setForeground(StyleConstants.PRIMARY_COLOR);
        gbc.gridx = 0;
        painelFiltros.add(lblBusca, gbc);
        
        campoBusca = new JTextField(20);
        campoBusca.setFont(StyleConstants.FONT);
        campoBusca.setToolTipText("Digite o título, autor ou gênero para buscar");
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        painelFiltros.add(campoBusca, gbc);
        
        // Filtro por tipo
        JLabel lblTipo = new JLabel("Tipo:");
        lblTipo.setFont(StyleConstants.FONT_BOLD);
        lblTipo.setForeground(StyleConstants.PRIMARY_COLOR);
        gbc.gridx = 2; gbc.fill = GridBagConstraints.NONE;
        painelFiltros.add(lblTipo, gbc);
        
        comboTipo = new JComboBox<>(new String[]{"Todos", "Livro", "Ebook", "Audiobook"});
        comboTipo.setFont(StyleConstants.FONT);
        gbc.gridx = 3;
        painelFiltros.add(comboTipo, gbc);
        
        // Segunda linha de filtros
        gbc.gridy = 2;
        
        // Filtro por gênero
        JLabel lblGenero = new JLabel("Gênero:");
        lblGenero.setFont(StyleConstants.FONT_BOLD);
        lblGenero.setForeground(StyleConstants.PRIMARY_COLOR);
        gbc.gridx = 0;
        painelFiltros.add(lblGenero, gbc);
        
        String[] generos = {"Todos", "FICCAO", "FANTASIA", "ROMANCE", "MISTERIO", "TERROR", 
                           "AVENTURA", "BIOGRAFIA", "HISTORIA", "CIENCIA", "AUTOAJUDA", "OUTROS"};
        comboGenero = new JComboBox<>(generos);
        comboGenero.setFont(StyleConstants.FONT);
        gbc.gridx = 1;
        painelFiltros.add(comboGenero, gbc);
        
        // Filtro por avaliação
        JLabel lblAvaliacao = new JLabel("Avaliação:");
        lblAvaliacao.setFont(StyleConstants.FONT_BOLD);
        lblAvaliacao.setForeground(StyleConstants.PRIMARY_COLOR);
        gbc.gridx = 2;
        painelFiltros.add(lblAvaliacao, gbc);
        
        comboAvaliacao = new JComboBox<>(new String[]{"Todas", "1 estrela", "2 estrelas", "3 estrelas", "4 estrelas", "5 estrelas", "Não avaliado"});
        comboAvaliacao.setFont(StyleConstants.FONT);
        gbc.gridx = 3;
        painelFiltros.add(comboAvaliacao, gbc);
        
        // Terceira linha
        gbc.gridy = 3;
        
        // Filtro por status
        JLabel lblStatus = new JLabel("Status:");
        lblStatus.setFont(StyleConstants.FONT_BOLD);
        lblStatus.setForeground(StyleConstants.PRIMARY_COLOR);
        gbc.gridx = 0;
        painelFiltros.add(lblStatus, gbc);
        
        comboStatus = new JComboBox<>(new String[]{"Todos", "Lido", "Não lido"});
        comboStatus.setFont(StyleConstants.FONT);
        gbc.gridx = 1;
        painelFiltros.add(comboStatus, gbc);
        
        // Botão limpar filtros
        btnLimparFiltros = new JButton("Limpar Filtros");
        ButtonStyles.applyDefaultStyle(btnLimparFiltros);
        gbc.gridx = 2; gbc.gridwidth = 2;
        painelFiltros.add(btnLimparFiltros, gbc);
        
        return painelFiltros;
    }
    
    private void configurarEventos() {
        // Evento do botão voltar
        btnVoltar.addActionListener(e -> telaPrincipal.voltarParaMenu());
        
        // Evento do botão limpar filtros
        btnLimparFiltros.addActionListener(e -> limparFiltros());
        
        // Eventos para aplicar filtros em tempo real
        campoBusca.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                aplicarFiltros();
            }
        });
        
        comboTipo.addActionListener(e -> aplicarFiltros());
        comboGenero.addActionListener(e -> aplicarFiltros());
        comboAvaliacao.addActionListener(e -> aplicarFiltros());
        comboStatus.addActionListener(e -> aplicarFiltros());
    }
    
    private void atualizarListaItens() {
        todosItens.clear();
        todosItens.addAll(livros);
        todosItens.addAll(ebooks);
        todosItens.addAll(audiobooks);
    }
    
    private void carregarDadosTabela() {
        modeloTabela.setRowCount(0);
        
        for (Item item : todosItens) {
            String tipo = "";
            if (item instanceof Livro) tipo = "Livro";
            else if (item instanceof Ebook) tipo = "Ebook";
            else if (item instanceof Audiobook) tipo = "Audiobook";
            
            Object[] linha = {
                tipo,
                item.getTitulo(),
                item.getAutor(),
                item.getGenero().name(),
                item.getAnoPublicacao(),
                item.isLido() ? "Lido" : "Não lido",
                item.getNota().getDescricao()
            };
            modeloTabela.addRow(linha);
        }
        
        atualizarContadorItens();
    }
    
    private void aplicarFiltros() {
        List<RowFilter<Object, Object>> filtros = new ArrayList<>();
        
        // Filtro de busca por texto
        String textoBusca = campoBusca.getText().trim();
        if (!textoBusca.isEmpty()) {
            RowFilter<Object, Object> filtroTexto = RowFilter.regexFilter("(?i)" + textoBusca, 1, 2, 3);
            filtros.add(filtroTexto);
        }
        
        // Filtro por tipo
        String tipoSelecionado = (String) comboTipo.getSelectedItem();
        if (!tipoSelecionado.equals("Todos")) {
            RowFilter<Object, Object> filtroTipo = RowFilter.regexFilter(tipoSelecionado, 0);
            filtros.add(filtroTipo);
        }
        
        // Filtro por gênero
        String generoSelecionado = (String) comboGenero.getSelectedItem();
        if (!generoSelecionado.equals("Todos")) {
            RowFilter<Object, Object> filtroGenero = RowFilter.regexFilter(generoSelecionado, 3);
            filtros.add(filtroGenero);
        }
        
        // Filtro por avaliação
        String avaliacaoSelecionada = (String) comboAvaliacao.getSelectedItem();
        if (!avaliacaoSelecionada.equals("Todas")) {
            RowFilter<Object, Object> filtroAvaliacao = RowFilter.regexFilter(avaliacaoSelecionada, 6);
            filtros.add(filtroAvaliacao);
        }
        
        // Filtro por status
        String statusSelecionado = (String) comboStatus.getSelectedItem();
        if (!statusSelecionado.equals("Todos")) {
            RowFilter<Object, Object> filtroStatus = RowFilter.regexFilter(statusSelecionado, 5);
            filtros.add(filtroStatus);
        }
        
        // Aplicar filtros
        if (filtros.isEmpty()) {
            sorter.setRowFilter(null);
        } else {
            RowFilter<Object, Object> filtroComposto = RowFilter.andFilter(filtros);
            sorter.setRowFilter(filtroComposto);
        }
        
        atualizarContadorItens();
    }
    
    private void limparFiltros() {
        campoBusca.setText("");
        comboTipo.setSelectedIndex(0);
        comboGenero.setSelectedIndex(0);
        comboAvaliacao.setSelectedIndex(0);
        comboStatus.setSelectedIndex(0);
        sorter.setRowFilter(null);
        atualizarContadorItens();
    }
    
    private void atualizarContadorItens() {
        int totalVisivel = tabelaItens.getRowCount();
        int totalGeral = todosItens.size();
        
        if (totalVisivel == totalGeral) {
            lblTotalItens.setText("Total de itens: " + totalGeral);
        } else {
            lblTotalItens.setText("Mostrando " + totalVisivel + " de " + totalGeral + " itens");
        }
    }
    
    // Métodos públicos para atualizar dados externamente
    public void atualizarDados(List<Livro> livros, List<Ebook> ebooks, List<Audiobook> audiobooks) {
        this.livros = livros;
        this.ebooks = ebooks;
        this.audiobooks = audiobooks;
        atualizarListaItens();
        carregarDadosTabela();
    }
    
    public void refresh() {
        atualizarListaItens();
        carregarDadosTabela();
    }
    
    // Método para obter o item selecionado na tabela
    public Item getItemSelecionado() {
        int linhaSelecionada = tabelaItens.getSelectedRow();
        if (linhaSelecionada == -1) {
            return null;
        }
        
        int linhaReal = tabelaItens.convertRowIndexToModel(linhaSelecionada);
        return todosItens.get(linhaReal);
    }
}