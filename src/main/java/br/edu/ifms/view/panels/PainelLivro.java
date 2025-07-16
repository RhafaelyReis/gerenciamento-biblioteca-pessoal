package br.edu.ifms.view.panels;

import br.edu.ifms.model.Genero;
import br.edu.ifms.view.styles.ButtonStyles;
import br.edu.ifms.view.styles.StyleConstants;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.function.Consumer;

public class PainelLivro {

    private DefaultTableModel modeloLivros;
    private JTable tabelaLivros;
    private Consumer<String> atualizarTabelaCallback;

    private JTextField txtTitulo, txtAutor, txtAno, txtPaginas, txtIsbn;
    private JComboBox<Genero> comboGenero;
    private JTextArea txtDescricao;

    private JButton btnAdicionar, btnAtualizar, btnRemover, btnMarcarLidoLivro;

    public PainelLivro(Consumer<String> atualizarTabelaCallback, JButton btnMarcarLidoLivro) {
        this.atualizarTabelaCallback = atualizarTabelaCallback;
        this.btnMarcarLidoLivro = btnMarcarLidoLivro;
    }

    public JPanel criarPainelLivros() {
        JPanel painel = new JPanel(new BorderLayout());
        painel.setBackground(StyleConstants.SECONDARY_COLOR);
        painel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel painelFormulario = new JPanel(new GridBagLayout());
        painelFormulario.setBackground(StyleConstants.SECONDARY_COLOR);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(2, 2, 2, 2);
        gbc.anchor = GridBagConstraints.WEST;

        txtTitulo = new JTextField(20);
        txtAutor = new JTextField(20);
        txtAno = new JTextField(20);
        comboGenero = new JComboBox<>(Genero.values());
        txtDescricao = new JTextArea(3, 20);
        txtDescricao.setLineWrap(true);
        txtDescricao.setWrapStyleWord(true);
        JScrollPane scrollDescricao = new JScrollPane(txtDescricao);
        txtPaginas = new JTextField(20);
        txtIsbn = new JTextField(20);

        gbc.gridx = 0; gbc.gridy = 0; painelFormulario.add(new JLabel("Título:"), gbc);
        gbc.gridx = 1; gbc.weightx = 1.0; gbc.fill = GridBagConstraints.HORIZONTAL; painelFormulario.add(txtTitulo, gbc);
        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0; gbc.fill = GridBagConstraints.NONE; painelFormulario.add(new JLabel("Autor:"), gbc);
        gbc.gridx = 1; gbc.weightx = 1.0; gbc.fill = GridBagConstraints.HORIZONTAL; painelFormulario.add(txtAutor, gbc);
        gbc.gridx = 0; gbc.gridy = 2; gbc.weightx = 0; gbc.fill = GridBagConstraints.NONE; painelFormulario.add(new JLabel("Ano:"), gbc);
        gbc.gridx = 1; gbc.weightx = 1.0; gbc.fill = GridBagConstraints.HORIZONTAL; painelFormulario.add(txtAno, gbc);
        gbc.gridx = 0; gbc.gridy = 3; gbc.weightx = 0; gbc.fill = GridBagConstraints.NONE; painelFormulario.add(new JLabel("Gênero:"), gbc);
        gbc.gridx = 1; gbc.weightx = 1.0; gbc.fill = GridBagConstraints.HORIZONTAL; painelFormulario.add(comboGenero, gbc);
        gbc.gridx = 0; gbc.gridy = 4; gbc.weightx = 0; gbc.fill = GridBagConstraints.NONE; painelFormulario.add(new JLabel("Descrição:"), gbc);
        gbc.gridx = 1; gbc.weightx = 1.0; gbc.weighty = 1.0; gbc.fill = GridBagConstraints.BOTH; painelFormulario.add(scrollDescricao, gbc);
        gbc.gridx = 0; gbc.gridy = 5; gbc.weightx = 0; gbc.weighty = 0; gbc.fill = GridBagConstraints.NONE; painelFormulario.add(new JLabel("Páginas:"), gbc);
        gbc.gridx = 1; gbc.weightx = 1.0; gbc.fill = GridBagConstraints.HORIZONTAL; painelFormulario.add(txtPaginas, gbc);
        gbc.gridx = 0; gbc.gridy = 6; gbc.weightx = 0; gbc.fill = GridBagConstraints.NONE; painelFormulario.add(new JLabel("ISBN:"), gbc);
        gbc.gridx = 1; gbc.weightx = 1.0; gbc.fill = GridBagConstraints.HORIZONTAL; painelFormulario.add(txtIsbn, gbc);

        painel.add(painelFormulario, BorderLayout.NORTH);
        painel.add(Box.createVerticalStrut(20), BorderLayout.CENTER);

        JPanel painelTabelaContainer = new JPanel(new BorderLayout());
        painelTabelaContainer.setBackground(StyleConstants.SECONDARY_COLOR);
        painelTabelaContainer.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        JLabel tituloTabela = new JLabel("Lista de Livros");
        tituloTabela.setFont(StyleConstants.FONT_BOLD);
        tituloTabela.setForeground(StyleConstants.PRIMARY_COLOR);
        tituloTabela.setHorizontalAlignment(SwingConstants.CENTER);
        painelTabelaContainer.add(tituloTabela, BorderLayout.NORTH);
        String[] colunas = {"ID", "Título", "Autor", "Lido", "Avaliação"};
        modeloLivros = new DefaultTableModel(colunas, 0) {
            @Override public boolean isCellEditable(int row, int column) { return false; }
        };
        tabelaLivros = new JTable(modeloLivros);
        tabelaLivros.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabelaLivros.setFont(StyleConstants.FONT);
        tabelaLivros.setRowHeight(25);
        tabelaLivros.getTableHeader().setFont(StyleConstants.FONT_BOLD);
        tabelaLivros.getTableHeader().setBackground(StyleConstants.PRIMARY_COLOR);
        tabelaLivros.getTableHeader().setForeground(StyleConstants.SECONDARY_COLOR);
        JScrollPane scrollPane = new JScrollPane(tabelaLivros);
        scrollPane.setPreferredSize(new Dimension(750, 200));
        painelTabelaContainer.add(scrollPane, BorderLayout.CENTER);
        painel.add(painelTabelaContainer, BorderLayout.CENTER);

        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER));
        painelBotoes.setBackground(StyleConstants.SECONDARY_COLOR);
        btnAdicionar = new JButton("Adicionar");
        btnAtualizar = new JButton("Salvar");
        btnRemover = new JButton("Remover");
        ButtonStyles.applyDefaultStyle(btnAdicionar);
        btnAdicionar.setToolTipText("Adicionar um novo livro");
        ButtonStyles.applyDefaultStyle(btnAtualizar);
        btnAtualizar.setToolTipText("Salvar alterações");
        ButtonStyles.applyDangerStyle(btnRemover);
        btnRemover.setToolTipText("Remover o livro selecionado");
        ButtonStyles.applyDefaultStyle(btnMarcarLidoLivro);
        btnMarcarLidoLivro.setToolTipText("Marcar o livro selecionado como lido");
        painelBotoes.add(btnAdicionar);
        painelBotoes.add(btnAtualizar);
        painelBotoes.add(btnRemover);
        painelBotoes.add(btnMarcarLidoLivro);
        painel.add(painelBotoes, BorderLayout.SOUTH);

        return painel;
    }

    public void limparCampos() {
        txtTitulo.setText("");
        txtAutor.setText("");
        txtAno.setText("");
        txtDescricao.setText("");
        txtPaginas.setText("");
        txtIsbn.setText("");
        comboGenero.setSelectedIndex(0);
        tabelaLivros.clearSelection();
    }

    public void displayError(String message) {
        JOptionPane.showMessageDialog(tabelaLivros.getParent(), message, "Erro", JOptionPane.ERROR_MESSAGE);
    }

    public void displayWarning(String message) {
        JOptionPane.showMessageDialog(tabelaLivros.getParent(), message, "Aviso", JOptionPane.WARNING_MESSAGE);
    }

    public DefaultTableModel getModeloLivros() { return modeloLivros; }
    public JTable getTabelaLivros() { return tabelaLivros; }
    public Consumer<String> getAtualizarTabelaCallback() { return atualizarTabelaCallback; }
    public JTextField getTxtTitulo() { return txtTitulo; }
    public JTextField getTxtAutor() { return txtAutor; }
    public JTextField getTxtAno() { return txtAno; }
    public JComboBox<Genero> getComboGenero() { return comboGenero; }
    public JTextArea getTxtDescricao() { return txtDescricao; }
    public JTextField getTxtPaginas() { return txtPaginas; }
    public JTextField getTxtIsbn() { return txtIsbn; }
    public JButton getBtnAdicionar() { return btnAdicionar; }
    public JButton getBtnAtualizar() { return btnAtualizar; }
    public JButton getBtnRemover() { return btnRemover; }
}