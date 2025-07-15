package br.edu.ifms.view.panels;

import br.edu.ifms.model.Audiobook;
import br.edu.ifms.model.Genero;
import br.edu.ifms.model.Nota;
import br.edu.ifms.view.styles.ButtonStyles;
import br.edu.ifms.view.styles.StyleConstants;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.function.Consumer; // Para o callback

public class PainelAudiobook {

    private List<Audiobook> audiobooks;
    private DefaultTableModel modeloAudiobooks;
    private JTable tabelaAudiobooks;
    private Consumer<String> atualizarTabelaCallback; // Callback para o método atualizarTabela da tela principal

    // Campos do formulário
    private JTextField txtTitulo;
    private JTextField txtAutor;
    private JTextField txtAno;
    private JComboBox<Genero> comboGenero;
    private JTextField txtDescricao;
    private JTextField txtDuracaoMinutos;
    private JTextField txtNarrador;

    // Botões específicos para a tela principal (marcar lido e ver detalhes)
    private JButton btnMarcarLidoAudiobook;
    private JButton btnEditarAudiobook;

    public PainelAudiobook(List<Audiobook> audiobooks, Consumer<String> atualizarTabelaCallback, JButton btnMarcarLidoAudiobook, JButton btnEditarAudiobook) {
        this.audiobooks = audiobooks;
        this.atualizarTabelaCallback = atualizarTabelaCallback;
        this.btnMarcarLidoAudiobook = btnMarcarLidoAudiobook;
        this.btnEditarAudiobook = btnEditarAudiobook;
    }

    public JPanel criarPainelAudiobooks() {
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
        txtDuracaoMinutos = new JTextField();
        txtNarrador = new JTextField();

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
        painelFormulario.add(new JLabel("Duração (min):"));
        painelFormulario.add(txtDuracaoMinutos);
        painelFormulario.add(new JLabel("Narrador:"));
        painelFormulario.add(txtNarrador);

        painel.add(painelFormulario, BorderLayout.NORTH);

        // Criar tabela
        String[] colunas = {"ID", "Título", "Autor", "Lido", "Avaliação"};
        modeloAudiobooks = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tabelaAudiobooks = new JTable(modeloAudiobooks);
        tabelaAudiobooks.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabelaAudiobooks.setFont(StyleConstants.FONT);
        tabelaAudiobooks.setRowHeight(25);
        tabelaAudiobooks.getTableHeader().setFont(StyleConstants.FONT_BOLD);
        tabelaAudiobooks.getTableHeader().setBackground(StyleConstants.PRIMARY_COLOR);
        tabelaAudiobooks.getTableHeader().setForeground(StyleConstants.SECONDARY_COLOR);

        JScrollPane scrollPane = new JScrollPane(tabelaAudiobooks);
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
        ButtonStyles.applyDefaultStyle(btnMarcarLidoAudiobook);
        ButtonStyles.applyDefaultStyle(btnEditarAudiobook);

        painelBotoes.add(btnAdicionar);
        painelBotoes.add(btnAtualizar);
        painelBotoes.add(btnRemover);
        painelBotoes.add(btnMarcarLidoAudiobook);
        painelBotoes.add(btnEditarAudiobook);

        painel.add(painelBotoes, BorderLayout.SOUTH);

        // Eventos CRUD
        btnAdicionar.addActionListener(e -> {
            try {
                String titulo = txtTitulo.getText();
                String autor = txtAutor.getText();
                int ano = Integer.parseInt(txtAno.getText());
                Genero genero = (Genero) comboGenero.getSelectedItem();
                String descricao = txtDescricao.getText();
                int duracao = Integer.parseInt(txtDuracaoMinutos.getText());
                String narrador = txtNarrador.getText();

                Audiobook novo = new Audiobook(titulo, autor, ano, genero, descricao, duracao, narrador);
                audiobooks.add(novo);
                atualizarTabelaCallback.accept("audiobook");

                // Limpar campos
                txtTitulo.setText("");
                txtAutor.setText("");
                txtAno.setText("");
                txtDescricao.setText("");
                txtDuracaoMinutos.setText("");
                txtNarrador.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(painel, "Erro de formato numérico. Verifique Ano e Duração.", "Erro de Entrada", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(painel, "Erro ao adicionar audiobook. Verifique os dados." + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnRemover.addActionListener(e -> {
            int linha = tabelaAudiobooks.getSelectedRow();
            if (linha >= 0) {
                int id = (Integer) modeloAudiobooks.getValueAt(linha, 0);
                audiobooks.removeIf(audiobook -> audiobook.getId() == id);
                atualizarTabelaCallback.accept("audiobook");
            } else {
                JOptionPane.showMessageDialog(painel, "Selecione um audiobook para remover.", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        });

        btnAtualizar.addActionListener(e -> {
            int linha = tabelaAudiobooks.getSelectedRow();
            if (linha >= 0) {
                try {
                    int id = (Integer) modeloAudiobooks.getValueAt(linha, 0);
                    for (Audiobook audiobook : audiobooks) {
                        if (audiobook.getId() == id) {
                            audiobook.setTitulo(txtTitulo.getText());
                            audiobook.setAutor(txtAutor.getText());
                            audiobook.setAnoPublicacao(Integer.parseInt(txtAno.getText()));
                            audiobook.setGenero((Genero) comboGenero.getSelectedItem());
                            audiobook.setDescricao(txtDescricao.getText());
                            audiobook.setDuracaoMinutos(Integer.parseInt(txtDuracaoMinutos.getText()));
                            audiobook.setNarrador(txtNarrador.getText());
                            break;
                        }
                    }
                    atualizarTabelaCallback.accept("audiobook");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(painel, "Erro de formato numérico. Verifique Ano e Duração.", "Erro de Entrada", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(painel, "Erro ao atualizar audiobook. Verifique os dados." + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(painel, "Selecione um audiobook para atualizar.", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        });

        // Preencher formulário ao selecionar uma linha na tabela
        tabelaAudiobooks.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = tabelaAudiobooks.getSelectedRow();
                if (selectedRow != -1) {
                    int idSelecionado = (Integer) modeloAudiobooks.getValueAt(selectedRow, 0);
                    Audiobook audiobookSelecionado = audiobooks.stream()
                                                            .filter(ab -> ab.getId() == idSelecionado)
                                                            .findFirst()
                                                            .orElse(null);

                    if (audiobookSelecionado != null) {
                        txtTitulo.setText(audiobookSelecionado.getTitulo());
                        txtAutor.setText(audiobookSelecionado.getAutor());
                        txtAno.setText(String.valueOf(audiobookSelecionado.getAnoPublicacao()));
                        comboGenero.setSelectedItem(audiobookSelecionado.getGenero());
                        txtDescricao.setText(audiobookSelecionado.getDescricao());
                        txtDuracaoMinutos.setText(String.valueOf(audiobookSelecionado.getDuracaoMinutos()));
                        txtNarrador.setText(audiobookSelecionado.getNarrador());
                    }
                }
            }
        });

        return painel;
    }

    public DefaultTableModel getModeloAudiobooks() {
        return modeloAudiobooks;
    }

    public JTable getTabelaAudiobooks() {
        return tabelaAudiobooks;
    }
}