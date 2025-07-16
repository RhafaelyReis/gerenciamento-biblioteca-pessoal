package br.edu.ifms.view.panels;

import br.edu.ifms.model.Audiobook;
import br.edu.ifms.model.Genero;
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
    private JTextArea txtDescricao;
    private JTextField txtDuracaoMinutos;
    private JTextField txtNarrador;

    // Botões específicos para a tela principal (marcar lido)
    private JButton btnMarcarLidoAudiobook;

    public PainelAudiobook(List<Audiobook> audiobooks, Consumer<String> atualizarTabelaCallback, JButton btnMarcarLidoAudiobook) {
        this.audiobooks = audiobooks;
        this.atualizarTabelaCallback = atualizarTabelaCallback;
        this.btnMarcarLidoAudiobook = btnMarcarLidoAudiobook;
    }

    public JPanel criarPainelAudiobooks() {
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
        txtDuracaoMinutos = new JTextField(20);
        txtNarrador = new JTextField(20);

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

        // Duração (min)
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.NONE;
        painelFormulario.add(new JLabel("Duração (min):"), gbc);
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        painelFormulario.add(txtDuracaoMinutos, gbc);

        // Narrador
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        painelFormulario.add(new JLabel("Narrador:"), gbc);
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        painelFormulario.add(txtNarrador, gbc);

        painel.add(painelFormulario, BorderLayout.NORTH);

        // Adicionar um espaçador vertical
        painel.add(Box.createVerticalStrut(20), BorderLayout.CENTER);

        // Painel para o título da tabela e a tabela em si
        JPanel painelTabelaContainer = new JPanel(new BorderLayout());
        painelTabelaContainer.setBackground(StyleConstants.SECONDARY_COLOR);
        painelTabelaContainer.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

        JLabel tituloTabela = new JLabel("Lista de Audiobooks");
        tituloTabela.setFont(StyleConstants.FONT_BOLD);
        tituloTabela.setForeground(StyleConstants.PRIMARY_COLOR);
        tituloTabela.setHorizontalAlignment(SwingConstants.CENTER);
        painelTabelaContainer.add(tituloTabela, BorderLayout.NORTH);

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
        painelTabelaContainer.add(scrollPane, BorderLayout.CENTER);

        painel.add(painelTabelaContainer, BorderLayout.CENTER);

        // Painel de botões
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER)); // Centraliza os botões
        painelBotoes.setBackground(StyleConstants.SECONDARY_COLOR);

        JButton btnAdicionar = new JButton("Adicionar");
        JButton btnAtualizar = new JButton("Salvar");
        JButton btnRemover = new JButton("Remover");

        ButtonStyles.applyDefaultStyle(btnAdicionar);
        btnAdicionar.setToolTipText("Adicionar novo audiobook");
        ButtonStyles.applyDefaultStyle(btnAtualizar);
        btnAtualizar.setToolTipText("Salvar alterações");
        ButtonStyles.applyDangerStyle(btnRemover);
        btnRemover.setToolTipText("Remover audiobook selecionado");
        ButtonStyles.applyDefaultStyle(btnMarcarLidoAudiobook);
        btnMarcarLidoAudiobook.setToolTipText("Marcar audiobook selecionado como lido");

        painelBotoes.add(btnAdicionar);
        painelBotoes.add(btnAtualizar);
        painelBotoes.add(btnRemover);
        painelBotoes.add(btnMarcarLidoAudiobook);

        painel.add(painelBotoes, BorderLayout.SOUTH);

        // Eventos CRUD (mantidos como antes)
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