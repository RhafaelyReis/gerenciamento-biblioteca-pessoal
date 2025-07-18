package br.edu.ifms.view.panels;

import br.edu.ifms.model.Genero;
import br.edu.ifms.view.styles.ButtonStyles;
import br.edu.ifms.view.styles.StyleConstants;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.function.Consumer;

/**
 * Classe auxiliar que cria e gerencia o painel de formulário e tabela para Livros.
 */
public class PainelLivro {

    // --- Modelos e Controles ---
    private DefaultTableModel modeloLivros; // Modelo de dados para a tabela de livros.
    private JTable tabelaLivros; // Tabela para exibir os livros.
    private Consumer<String> atualizarTabelaCallback; // Callback para atualizar a tabela na tela principal.

    // --- Componentes do Formulário ---
    private JTextField txtTitulo, txtAutor, txtAno, txtPaginas, txtIsbn;
    private JComboBox<Genero> comboGenero;
    private JTextArea txtDescricao;
    
    // --- Botões de Ação ---
    private JButton btnNovo, btnAtualizar, btnRemover, btnCancelar, btnMarcarLidoLivro;

    /**
     * Construtor do painel de livros.
     * @param atualizarTabelaCallback Função para notificar a tela principal que a tabela precisa ser atualizada.
     * @param btnMarcarLidoLivro Botão da tela principal para a ação de marcar como lido.
     */
    public PainelLivro(Consumer<String> atualizarTabelaCallback, JButton btnMarcarLidoLivro) {
        this.atualizarTabelaCallback = atualizarTabelaCallback;
        this.btnMarcarLidoLivro = btnMarcarLidoLivro;
    }

    /**
     * Cria e retorna o painel completo com formulário, tabela e botões para Livros.
     * @return O JPanel configurado.
     */
    public JPanel criarPainelLivros() {
        JPanel painel = new JPanel(new BorderLayout());
        painel.setBackground(StyleConstants.SECONDARY_COLOR);
        painel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // --- Painel do Formulário ---
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

        // --- Painel da Tabela ---
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
        JScrollPane scrollPane = new JScrollPane(tabelaLivros);
        scrollPane.setPreferredSize(new Dimension(750, 200));
        painelTabelaContainer.add(scrollPane, BorderLayout.CENTER);
        painel.add(painelTabelaContainer, BorderLayout.CENTER);

        // --- Painel de Botões ---
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER));
        painelBotoes.setBackground(StyleConstants.SECONDARY_COLOR);
        btnNovo = new JButton("Novo");
        btnAtualizar = new JButton("Salvar");
        btnRemover = new JButton("Remover");
        btnCancelar = new JButton("Cancelar");

        ButtonStyles.applyDefaultStyle(btnNovo);
        ButtonStyles.applyDefaultStyle(btnAtualizar);
        ButtonStyles.applyDangerStyle(btnRemover);
        ButtonStyles.applyDefaultStyle(btnCancelar);
        ButtonStyles.applyDefaultStyle(btnMarcarLidoLivro);

        painelBotoes.add(btnNovo);
        painelBotoes.add(btnAtualizar);
        painelBotoes.add(btnRemover);
        painelBotoes.add(btnCancelar);
        painelBotoes.add(btnMarcarLidoLivro);
        painel.add(painelBotoes, BorderLayout.SOUTH);

        return painel;
    }

    /** Limpa todos os campos do formulário e a seleção da tabela. */
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

    /**
     * Exibe uma caixa de diálogo de erro.
     * @param message A mensagem a ser exibida.
     */
    public void displayError(String message) {
        JOptionPane.showMessageDialog(tabelaLivros.getParent(), message, "Erro", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Exibe uma caixa de diálogo de aviso.
     * @param message A mensagem a ser exibida.
     */
    public void displayWarning(String message) {
        JOptionPane.showMessageDialog(tabelaLivros.getParent(), message, "Aviso", JOptionPane.WARNING_MESSAGE);
    }

    // --- Getters ---
    /** @return O modelo de dados da tabela de livros. */
    public DefaultTableModel getModeloLivros() { return modeloLivros; }
    /** @return A tabela de livros. */
    public JTable getTabelaLivros() { return tabelaLivros; }
    /** @return O callback de atualização da tabela. */
    public Consumer<String> getAtualizarTabelaCallback() { return atualizarTabelaCallback; }
    /** @return O campo de texto do título. */
    public JTextField getTxtTitulo() { return txtTitulo; }
    /** @return O campo de texto do autor. */
    public JTextField getTxtAutor() { return txtAutor; }
    /** @return O campo de texto do ano. */
    public JTextField getTxtAno() { return txtAno; }
    /** @return O seletor de gênero. */
    public JComboBox<Genero> getComboGenero() { return comboGenero; }
    /** @return A área de texto da descrição. */
    public JTextArea getTxtDescricao() { return txtDescricao; }
    /** @return O campo de texto do número de páginas. */
    public JTextField getTxtPaginas() { return txtPaginas; }
    /** @return O campo de texto do ISBN. */
    public JTextField getTxtIsbn() { return txtIsbn; }
    /** @return O botão "Novo". */
    public JButton getBtnNovo() { return btnNovo; }
    /** @return O botão "Salvar". */
    public JButton getBtnAtualizar() { return btnAtualizar; }
    /** @return O botão "Remover". */
    public JButton getBtnRemover() { return btnRemover; }
    /** @return O botão "Cancelar". */
    public JButton getBtnCancelar() { return btnCancelar; }
}