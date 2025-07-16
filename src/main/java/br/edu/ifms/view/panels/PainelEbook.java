package br.edu.ifms.view.panels;

import br.edu.ifms.model.Genero;
import br.edu.ifms.view.styles.ButtonStyles;
import br.edu.ifms.view.styles.StyleConstants;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.function.Consumer;

public class PainelEbook {

    private DefaultTableModel modeloEbooks;
    private JTable tabelaEbooks;
    private Consumer<String> atualizarTabelaCallback;

    private JTextField txtTitulo, txtAutor, txtAno, txtDispositivo;
    private JComboBox<Genero> comboGenero;
    private JTextArea txtDescricao;

    private JButton btnAdicionar, btnAtualizar, btnRemover, btnMarcarLidoEbook;

    public PainelEbook(Consumer<String> atualizarTabelaCallback, JButton btnMarcarLidoEbook) {
        this.atualizarTabelaCallback = atualizarTabelaCallback;
        this.btnMarcarLidoEbook = btnMarcarLidoEbook;
    }

    public JPanel criarPainelEbooks() {
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
        txtDispositivo = new JTextField(20);

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
        gbc.gridx = 0; gbc.gridy = 5; gbc.weightx = 0; gbc.weighty = 0; gbc.fill = GridBagConstraints.NONE; painelFormulario.add(new JLabel("Dispositivo:"), gbc);
        gbc.gridx = 1; gbc.weightx = 1.0; gbc.fill = GridBagConstraints.HORIZONTAL; painelFormulario.add(txtDispositivo, gbc);

        painel.add(painelFormulario, BorderLayout.NORTH);
        painel.add(Box.createVerticalStrut(20), BorderLayout.CENTER);

        JPanel painelTabelaContainer = new JPanel(new BorderLayout());
        painelTabelaContainer.setBackground(StyleConstants.SECONDARY_COLOR);
        painelTabelaContainer.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        JLabel tituloTabela = new JLabel("Lista de Ebooks");
        tituloTabela.setFont(StyleConstants.FONT_BOLD);
        tituloTabela.setForeground(StyleConstants.PRIMARY_COLOR);
        tituloTabela.setHorizontalAlignment(SwingConstants.CENTER);
        painelTabelaContainer.add(tituloTabela, BorderLayout.NORTH);
        String[] colunas = {"ID", "Título", "Autor", "Lido", "Avaliação"};
        modeloEbooks = new DefaultTableModel(colunas, 0) {
            @Override public boolean isCellEditable(int row, int column) { return false; }
        };
        tabelaEbooks = new JTable(modeloEbooks);
        tabelaEbooks.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabelaEbooks.setFont(StyleConstants.FONT);
        tabelaEbooks.setRowHeight(25);
        tabelaEbooks.getTableHeader().setFont(StyleConstants.FONT_BOLD);
        tabelaEbooks.getTableHeader().setBackground(StyleConstants.PRIMARY_COLOR);
        tabelaEbooks.getTableHeader().setForeground(StyleConstants.SECONDARY_COLOR);
        JScrollPane scrollPane = new JScrollPane(tabelaEbooks);
        scrollPane.setPreferredSize(new Dimension(750, 200));
        painelTabelaContainer.add(scrollPane, BorderLayout.CENTER);
        painel.add(painelTabelaContainer, BorderLayout.CENTER);

        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER));
        painelBotoes.setBackground(StyleConstants.SECONDARY_COLOR);
        btnAdicionar = new JButton("Adicionar");
        btnAtualizar = new JButton("Salvar");
        btnRemover = new JButton("Remover");
        ButtonStyles.applyDefaultStyle(btnAdicionar);
        btnAdicionar.setToolTipText("Adicionar um novo ebook");
        ButtonStyles.applyDefaultStyle(btnAtualizar);
        btnAtualizar.setToolTipText("Salvar alterações");
        ButtonStyles.applyDangerStyle(btnRemover);
        btnRemover.setToolTipText("Remover o ebook selecionado");
        ButtonStyles.applyDefaultStyle(btnMarcarLidoEbook);
        btnMarcarLidoEbook.setToolTipText("Marcar o ebook selecionado como lido");
        painelBotoes.add(btnAdicionar);
        painelBotoes.add(btnAtualizar);
        painelBotoes.add(btnRemover);
        painelBotoes.add(btnMarcarLidoEbook);
        painel.add(painelBotoes, BorderLayout.SOUTH);

        return painel;
    }

    public void limparCampos() {
        txtTitulo.setText("");
        txtAutor.setText("");
        txtAno.setText("");
        txtDescricao.setText("");
        txtDispositivo.setText("");
        comboGenero.setSelectedIndex(0);
        tabelaEbooks.clearSelection();
    }

    public void displayError(String message) {
        JOptionPane.showMessageDialog(tabelaEbooks.getParent(), message, "Erro", JOptionPane.ERROR_MESSAGE);
    }

    public void displayWarning(String message) {
        JOptionPane.showMessageDialog(tabelaEbooks.getParent(), message, "Aviso", JOptionPane.WARNING_MESSAGE);
    }

    public DefaultTableModel getModeloEbooks() { return modeloEbooks; }
    public JTable getTabelaEbooks() { return tabelaEbooks; }
    public Consumer<String> getAtualizarTabelaCallback() { return atualizarTabelaCallback; }
    public JTextField getTxtTitulo() { return txtTitulo; }
    public JTextField getTxtAutor() { return txtAutor; }
    public JTextField getTxtAno() { return txtAno; }
    public JComboBox<Genero> getComboGenero() { return comboGenero; }
    public JTextArea getTxtDescricao() { return txtDescricao; }
    public JTextField getTxtDispositivo() { return txtDispositivo; }
    public JButton getBtnAdicionar() { return btnAdicionar; }
    public JButton getBtnAtualizar() { return btnAtualizar; }
    public JButton getBtnRemover() { return btnRemover; }
}