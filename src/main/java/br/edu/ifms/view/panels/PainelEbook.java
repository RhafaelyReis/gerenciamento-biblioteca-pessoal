package br.edu.ifms.view.panels;

import br.edu.ifms.model.Ebook;
import br.edu.ifms.model.Genero;
import br.edu.ifms.model.Nota;
import br.edu.ifms.view.styles.ButtonStyles;
import br.edu.ifms.view.styles.StyleConstants;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.function.Consumer; // Para o callback

public class PainelEbook {

    private List<Ebook> ebooks;
    private DefaultTableModel modeloEbooks;
    private JTable tabelaEbooks;
    private Consumer<String> atualizarTabelaCallback; // Callback para o método atualizarTabela da tela principal

    // Campos do formulário
    private JTextField txtTitulo;
    private JTextField txtAutor;
    private JTextField txtAno;
    private JComboBox<Genero> comboGenero;
    private JTextField txtDescricao;
    private JTextField txtDispositivo;

    // Botões específicos para a tela principal (marcar lido e ver detalhes)
    private JButton btnMarcarLidoEbook;
    private JButton btnEditarEbook;


    public PainelEbook(List<Ebook> ebooks, Consumer<String> atualizarTabelaCallback, JButton btnMarcarLidoEbook, JButton btnEditarEbook) {
        this.ebooks = ebooks;
        this.atualizarTabelaCallback = atualizarTabelaCallback;
        this.btnMarcarLidoEbook = btnMarcarLidoEbook;
        this.btnEditarEbook = btnEditarEbook;
    }

    public JPanel criarPainelEbooks() {
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
        txtDispositivo = new JTextField();

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
        painelFormulario.add(new JLabel("Dispositivo:"));
        painelFormulario.add(txtDispositivo);

        painel.add(painelFormulario, BorderLayout.NORTH);

        // Criar tabela
        String[] colunas = {"ID", "Título", "Autor", "Lido", "Avaliação"};
        modeloEbooks = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
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
        ButtonStyles.applyDefaultStyle(btnMarcarLidoEbook);
        ButtonStyles.applyDefaultStyle(btnEditarEbook);

        painelBotoes.add(btnAdicionar);
        painelBotoes.add(btnAtualizar);
        painelBotoes.add(btnRemover);
        painelBotoes.add(btnMarcarLidoEbook);
        painelBotoes.add(btnEditarEbook);

        painel.add(painelBotoes, BorderLayout.SOUTH);

        // Eventos CRUD
        btnAdicionar.addActionListener(e -> {
            try {
                String titulo = txtTitulo.getText();
                String autor = txtAutor.getText();
                int ano = Integer.parseInt(txtAno.getText());
                Genero genero = (Genero) comboGenero.getSelectedItem();
                String descricao = txtDescricao.getText();
                String dispositivo = txtDispositivo.getText();

                Ebook novo = new Ebook(titulo, autor, ano, genero, descricao, dispositivo);
                ebooks.add(novo);
                atualizarTabelaCallback.accept("ebook");

                // Limpar campos
                txtTitulo.setText("");
                txtAutor.setText("");
                txtAno.setText("");
                txtDescricao.setText("");
                txtDispositivo.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(painel, "Erro de formato numérico. Verifique o Ano.", "Erro de Entrada", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(painel, "Erro ao adicionar ebook. Verifique os dados." + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnRemover.addActionListener(e -> {
            int linha = tabelaEbooks.getSelectedRow();
            if (linha >= 0) {
                int id = (Integer) modeloEbooks.getValueAt(linha, 0);
                ebooks.removeIf(ebook -> ebook.getId() == id);
                atualizarTabelaCallback.accept("ebook");
            } else {
                JOptionPane.showMessageDialog(painel, "Selecione um ebook para remover.", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        });

        btnAtualizar.addActionListener(e -> {
            int linha = tabelaEbooks.getSelectedRow();
            if (linha >= 0) {
                try {
                    int id = (Integer) modeloEbooks.getValueAt(linha, 0);
                    for (Ebook ebook : ebooks) {
                        if (ebook.getId() == id) {
                            ebook.setTitulo(txtTitulo.getText());
                            ebook.setAutor(txtAutor.getText());
                            ebook.setAnoPublicacao(Integer.parseInt(txtAno.getText()));
                            ebook.setGenero((Genero) comboGenero.getSelectedItem());
                            ebook.setDescricao(txtDescricao.getText());
                            ebook.setDispositivo(txtDispositivo.getText());
                            break;
                        }
                    }
                    atualizarTabelaCallback.accept("ebook");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(painel, "Erro de formato numérico. Verifique o Ano.", "Erro de Entrada", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(painel, "Erro ao atualizar ebook. Verifique os dados." + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(painel, "Selecione um ebook para atualizar.", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        });

        // Preencher formulário ao selecionar uma linha na tabela
        tabelaEbooks.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = tabelaEbooks.getSelectedRow();
                if (selectedRow != -1) {
                    int idSelecionado = (Integer) modeloEbooks.getValueAt(selectedRow, 0);
                    Ebook ebookSelecionado = ebooks.stream()
                                                  .filter(eb -> eb.getId() == idSelecionado)
                                                  .findFirst()
                                                  .orElse(null);

                    if (ebookSelecionado != null) {
                        txtTitulo.setText(ebookSelecionado.getTitulo());
                        txtAutor.setText(ebookSelecionado.getAutor());
                        txtAno.setText(String.valueOf(ebookSelecionado.getAnoPublicacao()));
                        comboGenero.setSelectedItem(ebookSelecionado.getGenero());
                        txtDescricao.setText(ebookSelecionado.getDescricao());
                        txtDispositivo.setText(ebookSelecionado.getDispositivo());
                    }
                }
            }
        });

        return painel;
    }

    public DefaultTableModel getModeloEbooks() {
        return modeloEbooks;
    }

    public JTable getTabelaEbooks() {
        return tabelaEbooks;
    }
}