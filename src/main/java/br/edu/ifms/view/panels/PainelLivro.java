package br.edu.ifms.view.panels;

import br.edu.ifms.model.Genero;
import br.edu.ifms.model.Livro;
import br.edu.ifms.view.styles.ButtonStyles;
import br.edu.ifms.view.styles.StyleConstants;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.function.Consumer; // Para o callback

public class PainelLivro {

    private List<Livro> livros;
    private DefaultTableModel modeloLivros;
    private JTable tabelaLivros;
    private Consumer<String> atualizarTabelaCallback; // Callback para o método atualizarTabela da tela principal

    // Campos do formulário
    private JTextField txtTitulo;
    private JTextField txtAutor;
    private JTextField txtAno;
    private JComboBox<Genero> comboGenero;
    private JTextArea txtDescricao;
    private JTextField txtPaginas;
    private JTextField txtIsbn;

    // Botões específicos para a tela principal (marcar lido)
    private JButton btnMarcarLidoLivro;


    public PainelLivro(List<Livro> livros, Consumer<String> atualizarTabelaCallback, JButton btnMarcarLidoLivro) {
        this.livros = livros;
        this.atualizarTabelaCallback = atualizarTabelaCallback;
        this.btnMarcarLidoLivro = btnMarcarLidoLivro;
    }

    public JPanel criarPainelLivros() {
        JPanel painel = new JPanel(new BorderLayout());
        painel.setBackground(StyleConstants.SECONDARY_COLOR);
        painel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Painel de formulário
        JPanel painelFormulario = new JPanel(new GridBagLayout());
        painelFormulario.setBackground(StyleConstants.SECONDARY_COLOR);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(2, 2, 2, 2); // Reduzido o espaço interno
        gbc.anchor = GridBagConstraints.WEST; // Alinha os componentes à esquerda

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

        // Título
        gbc.gridx = 0;
        gbc.gridy = 0;
        painelFormulario.add(new JLabel("Título:"), gbc);
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        painelFormulario.add(txtTitulo, gbc);

        // Autor
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        painelFormulario.add(new JLabel("Autor:"), gbc);
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        painelFormulario.add(txtAutor, gbc);

        // Ano
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        painelFormulario.add(new JLabel("Ano:"), gbc);
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        painelFormulario.add(txtAno, gbc);

        // Gênero
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        painelFormulario.add(new JLabel("Gênero:"), gbc);
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        painelFormulario.add(comboGenero, gbc);

        // Descrição
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        painelFormulario.add(new JLabel("Descrição:"), gbc);
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        painelFormulario.add(scrollDescricao, gbc);

        // Páginas
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.NONE;
        painelFormulario.add(new JLabel("Páginas:"), gbc);
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        painelFormulario.add(txtPaginas, gbc);

        // ISBN
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        painelFormulario.add(new JLabel("ISBN:"), gbc);
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        painelFormulario.add(txtIsbn, gbc);

        painel.add(painelFormulario, BorderLayout.NORTH);

        // Adicionar um espaçador vertical
        painel.add(Box.createVerticalStrut(20), BorderLayout.CENTER);

        // Painel para o título da tabela e a tabela em si
        JPanel painelTabelaContainer = new JPanel(new BorderLayout());
        painelTabelaContainer.setBackground(StyleConstants.SECONDARY_COLOR);
        painelTabelaContainer.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

        JLabel tituloTabela = new JLabel("Lista de Livros");
        tituloTabela.setFont(StyleConstants.FONT_BOLD);
        tituloTabela.setForeground(StyleConstants.PRIMARY_COLOR);
        tituloTabela.setHorizontalAlignment(SwingConstants.CENTER);
        painelTabelaContainer.add(tituloTabela, BorderLayout.NORTH);

        // Criar tabela
        String[] colunas = {"ID", "Título", "Autor", "Lido", "Avaliação"};
        modeloLivros = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
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

        // Painel de botões
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER)); // Centraliza os botões
        painelBotoes.setBackground(StyleConstants.SECONDARY_COLOR);

        JButton btnAdicionar = new JButton("Adicionar");
        JButton btnAtualizar = new JButton("Salvar");
        JButton btnRemover = new JButton("Remover");

        ButtonStyles.applyDefaultStyle(btnAdicionar);
        ButtonStyles.applyDefaultStyle(btnAtualizar);
        ButtonStyles.applyDangerStyle(btnRemover);
        ButtonStyles.applyDefaultStyle(btnMarcarLidoLivro);

        painelBotoes.add(btnAdicionar);
        painelBotoes.add(btnAtualizar);
        painelBotoes.add(btnRemover);
        painelBotoes.add(btnMarcarLidoLivro);

        painel.add(painelBotoes, BorderLayout.SOUTH);

        // Eventos CRUD (mantidos como antes)
        btnAdicionar.addActionListener(e -> {
            try {
                String titulo = txtTitulo.getText();
                String autor = txtAutor.getText();
                int ano = Integer.parseInt(txtAno.getText());
                Genero genero = (Genero) comboGenero.getSelectedItem();
                String descricao = txtDescricao.getText();
                int paginas = Integer.parseInt(txtPaginas.getText());
                String isbn = txtIsbn.getText();

                Livro novo = new Livro(titulo, autor, ano, genero, descricao, paginas, isbn);
                livros.add(novo);
                atualizarTabelaCallback.accept("livro");

                // Limpar campos
                txtTitulo.setText("");
                txtAutor.setText("");
                txtAno.setText("");
                txtDescricao.setText("");
                txtPaginas.setText("");
                txtIsbn.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(painel, "Erro de formato numérico. Verifique Ano e Páginas.", "Erro de Entrada", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(painel, "Erro ao adicionar livro. Verifique os dados." + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnRemover.addActionListener(e -> {
            int linha = tabelaLivros.getSelectedRow();
            if (linha >= 0) {
                int id = (Integer) modeloLivros.getValueAt(linha, 0);
                livros.removeIf(livro -> livro.getId() == id);
                atualizarTabelaCallback.accept("livro");
            } else {
                JOptionPane.showMessageDialog(painel, "Selecione um livro para remover.", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        });

        btnAtualizar.addActionListener(e -> {
            int linha = tabelaLivros.getSelectedRow();
            if (linha >= 0) {
                try {
                    int id = (Integer) modeloLivros.getValueAt(linha, 0);
                    for (Livro livro : livros) {
                        if (livro.getId() == id) {
                            livro.setTitulo(txtTitulo.getText());
                            livro.setAutor(txtAutor.getText());
                            livro.setAnoPublicacao(Integer.parseInt(txtAno.getText()));
                            livro.setGenero((Genero) comboGenero.getSelectedItem());
                            livro.setDescricao(txtDescricao.getText());
                            livro.setNumPaginas(Integer.parseInt(txtPaginas.getText()));
                            livro.setIsbn(txtIsbn.getText());
                            break;
                        }
                    }
                    atualizarTabelaCallback.accept("livro");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(painel, "Erro de formato numérico. Verifique Ano e Páginas.", "Erro de Entrada", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(painel, "Erro ao atualizar livro. Verifique os dados." + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(painel, "Selecione um livro para atualizar.", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        });

        // Preencher formulário ao selecionar uma linha na tabela
        tabelaLivros.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = tabelaLivros.getSelectedRow();
                if (selectedRow != -1) {
                    int idSelecionado = (Integer) modeloLivros.getValueAt(selectedRow, 0);
                    Livro livroSelecionado = livros.stream()
                                                  .filter(l -> l.getId() == idSelecionado)
                                                  .findFirst()
                                                  .orElse(null);

                    if (livroSelecionado != null) {
                        txtTitulo.setText(livroSelecionado.getTitulo());
                        txtAutor.setText(livroSelecionado.getAutor());
                        txtAno.setText(String.valueOf(livroSelecionado.getAnoPublicacao()));
                        comboGenero.setSelectedItem(livroSelecionado.getGenero());
                        txtDescricao.setText(livroSelecionado.getDescricao());
                        txtPaginas.setText(String.valueOf(livroSelecionado.getNumPaginas()));
                        txtIsbn.setText(livroSelecionado.getIsbn());
                    }
                }
            }
        });


        return painel;
    }

    public DefaultTableModel getModeloLivros() {
        return modeloLivros;
    }

    public JTable getTabelaLivros() {
        return tabelaLivros;
    }
}