package br.edu.ifms.view.panels;

import br.edu.ifms.model.Genero;
import br.edu.ifms.model.Livro;
import br.edu.ifms.model.Nota;
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
    private JTextField txtDescricao;
    private JTextField txtPaginas;
    private JTextField txtIsbn;

    // Botões específicos para a tela principal (marcar lido e ver detalhes)
    private JButton btnMarcarLidoLivro;
    private JButton btnEditarLivro;


    public PainelLivro(List<Livro> livros, Consumer<String> atualizarTabelaCallback, JButton btnMarcarLidoLivro, JButton btnEditarLivro) {
        this.livros = livros;
        this.atualizarTabelaCallback = atualizarTabelaCallback;
        this.btnMarcarLidoLivro = btnMarcarLidoLivro;
        this.btnEditarLivro = btnEditarLivro;
    }

    public JPanel criarPainelLivros() {
        JPanel painel = new JPanel(new BorderLayout());
        painel.setBackground(StyleConstants.SECONDARY_COLOR);
        painel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Painel de formulário
        JPanel painelFormulario = new JPanel(new GridLayout(3, 4, 10, 10));
        painelFormulario.setBackground(StyleConstants.SECONDARY_COLOR);

        txtTitulo = new JTextField();
        txtAutor = new JTextField();
        txtAno = new JTextField();
        comboGenero = new JComboBox<>(Genero.values());
        txtDescricao = new JTextField();
        txtPaginas = new JTextField();
        txtIsbn = new JTextField();

        painelFormulario.add(new JLabel("Título:"));
        painelFormulario.add(txtTitulo);
        painelFormulario.add(new JLabel("Autor:"));
        painelFormulario.add(txtAutor);
        painelFormulario.add(new JLabel("Ano:"));
        painelFormulario.add(txtAno);
        painelFormulario.add(new JLabel("Gênero:"));
        painelFormulario.add(comboGenero);
        painelFormulario.add(new JLabel("Descrição:"));
        painelFormulario.add(txtDescricao);
        painelFormulario.add(new JLabel("Páginas:"));
        painelFormulario.add(txtPaginas);
        painelFormulario.add(new JLabel("ISBN:"));
        painelFormulario.add(txtIsbn);

        painel.add(painelFormulario, BorderLayout.NORTH);

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
        painel.add(scrollPane, BorderLayout.CENTER);

        // Painel de botões
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.LEFT));
        painelBotoes.setBackground(StyleConstants.SECONDARY_COLOR);

        JButton btnAdicionar = new JButton("Adicionar");
        JButton btnAtualizar = new JButton("Atualizar");
        JButton btnRemover = new JButton("Remover");

        ButtonStyles.applyDefaultStyle(btnAdicionar);
        ButtonStyles.applyDefaultStyle(btnAtualizar);
        ButtonStyles.applyDangerStyle(btnRemover);
        ButtonStyles.applyDefaultStyle(btnMarcarLidoLivro); // Este botão vem da TelaGerenciarItens
        ButtonStyles.applyDefaultStyle(btnEditarLivro); // Este botão vem da TelaGerenciarItens

        painelBotoes.add(btnAdicionar);
        painelBotoes.add(btnAtualizar);
        painelBotoes.add(btnRemover);
        painelBotoes.add(btnMarcarLidoLivro);
        painelBotoes.add(btnEditarLivro);

        painel.add(painelBotoes, BorderLayout.SOUTH);

        // Eventos CRUD
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
                atualizarTabelaCallback.accept("livro"); // Chama o callback para atualizar a tabela principal

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
                int id = (Integer) modeloLivros.getValueAt(linha, 0); // Use modeloLivros aqui
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
                    int id = (Integer) modeloLivros.getValueAt(linha, 0); // Use modeloLivros aqui
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
                    // O ID está na coluna 0, mas precisamos encontrar o objeto Livro real
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