package br.edu.ifms.controller;

import br.edu.ifms.exception.CampoInvalidoException;
import br.edu.ifms.exception.CampoObrigatorioException;
import br.edu.ifms.exception.FormatoInvalidoException;
import br.edu.ifms.exception.ItemNaoSelecionadoException;
import br.edu.ifms.exception.Validador;
import br.edu.ifms.model.Ebook;
import br.edu.ifms.model.Genero;
import br.edu.ifms.view.panels.PainelEbook;

import java.util.List;

/**
 * Controlador responsável por gerenciar a interação entre o painel de ebooks e os dados da aplicação.
 */
public class EbookController {

    private PainelEbook view;
    private List<Ebook> ebooks;

    public EbookController(PainelEbook view, List<Ebook> ebooks) {
        this.view = view;
        this.ebooks = ebooks;
    }

    /**
     * Inicializa os eventos dos botões e da tabela da interface gráfica.
     */
    public void initController() {
        view.getBtnNovo().addActionListener(e -> view.limparCampos());
        view.getBtnCancelar().addActionListener(e -> view.limparCampos());
        view.getBtnRemover().addActionListener(e -> removerEbook());
        view.getBtnAtualizar().addActionListener(e -> salvarEbook());
        view.getTabelaEbooks().getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                preencherFormularioComEbookSelecionado();
            }
        });
    }

    /**
     * Remove o ebook selecionado da lista e atualiza a tabela.
     * Exibe um aviso se nenhum item estiver selecionado.
     */
    private void removerEbook() {
        try {
            Validador.validarItemSelecionado(view.getTabelaEbooks());

            int linhaSelecionada = view.getTabelaEbooks().getSelectedRow();
            int id = (Integer) view.getModeloEbooks().getValueAt(linhaSelecionada, 0);

            // Remove o ebook com o ID correspondente
            ebooks.removeIf(ebook -> ebook.getId() == id);

            view.getAtualizarTabelaCallback().accept("ebook");
            view.limparCampos();

        } catch (ItemNaoSelecionadoException e) {
            view.displayWarning(e.getMessage());
        }
    }

    /**
     * Salva um novo ebook ou atualiza um existente, validando os campos antes.
     */
    private void salvarEbook() {
        try {
            // Captura dados da interface
            String titulo = view.getTxtTitulo().getText();
            String autor = view.getTxtAutor().getText();
            String anoStr = view.getTxtAno().getText();
            String dispositivo = view.getTxtDispositivo().getText();

            // Valida campos obrigatórios e formatos
            Validador.validarCamposObrigatorios(titulo, autor, anoStr, dispositivo);
            int ano = Validador.validarFormatoInteiro(anoStr);
            Validador.validarAno(ano);
            Validador.validarDispositivoEbook(dispositivo);

            // Campos opcionais
            Genero genero = (Genero) view.getComboGenero().getSelectedItem();
            String descricao = view.getTxtDescricao().getText();
            int linhaSelecionada = view.getTabelaEbooks().getSelectedRow();

            if (linhaSelecionada >= 0) {
                // Atualiza ebook existente
                int id = (Integer) view.getModeloEbooks().getValueAt(linhaSelecionada, 0);
                for (Ebook ebook : ebooks) {
                    if (ebook.getId() == id) {
                        ebook.setTitulo(titulo);
                        ebook.setAutor(autor);
                        ebook.setAnoPublicacao(ano);
                        ebook.setGenero(genero);
                        ebook.setDescricao(descricao);
                        ebook.setDispositivo(dispositivo);
                        break;
                    }
                }
            } else {
                // Cria novo ebook
                Ebook novo = new Ebook(titulo, autor, ano, genero, descricao, dispositivo);
                ebooks.add(novo);
            }

            view.getAtualizarTabelaCallback().accept("ebook");
            view.limparCampos();

        } catch (CampoObrigatorioException | FormatoInvalidoException | CampoInvalidoException e) {
            view.displayError(e.getMessage());
        } catch (Exception e) {
            view.displayError("Erro ao salvar ebook: " + e.getMessage());
        }
    }

    /**
     * Preenche os campos do formulário com os dados do ebook selecionado na tabela.
     */
    private void preencherFormularioComEbookSelecionado() {
        int selectedRow = view.getTabelaEbooks().getSelectedRow();
        if (selectedRow != -1) {
            int idSelecionado = (Integer) view.getModeloEbooks().getValueAt(selectedRow, 0);

            // Busca o ebook correspondente na lista
            Ebook ebookSelecionado = ebooks.stream()
                    .filter(eb -> eb.getId() == idSelecionado)
                    .findFirst()
                    .orElse(null);

            // Preenche o formulário se encontrado
            if (ebookSelecionado != null) {
                view.getTxtTitulo().setText(ebookSelecionado.getTitulo());
                view.getTxtAutor().setText(ebookSelecionado.getAutor());
                view.getTxtAno().setText(String.valueOf(ebookSelecionado.getAnoPublicacao()));
                view.getComboGenero().setSelectedItem(ebookSelecionado.getGenero());
                view.getTxtDescricao().setText(ebookSelecionado.getDescricao());
                view.getTxtDispositivo().setText(ebookSelecionado.getDispositivo());
            }
        }
    }
}