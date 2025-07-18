package br.edu.ifms.controller;

import br.edu.ifms.exception.CampoInvalidoException;
import br.edu.ifms.exception.CampoObrigatorioException;
import br.edu.ifms.exception.FormatoInvalidoException;
import br.edu.ifms.exception.ItemNaoSelecionadoException;
import br.edu.ifms.exception.Validador;
import br.edu.ifms.model.Genero;
import br.edu.ifms.model.Livro;
import br.edu.ifms.view.panels.PainelLivro;

import java.util.List;

public class LivroController {

    private PainelLivro view;
    private List<Livro> livros;

    public LivroController(PainelLivro view, List<Livro> livros) {
        this.view = view;
        this.livros = livros;
    }

    public void initController() {
        view.getBtnNovo().addActionListener(e -> view.limparCampos());
        view.getBtnCancelar().addActionListener(e -> view.limparCampos());
        view.getBtnRemover().addActionListener(e -> removerLivro());
        view.getBtnAtualizar().addActionListener(e -> salvarLivro());
        view.getTabelaLivros().getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                preencherFormularioComLivroSelecionado();
            }
        });
    }

    private void removerLivro() {
        try {
            Validador.validarItemSelecionado(view.getTabelaLivros());

            int linhaSelecionada = view.getTabelaLivros().getSelectedRow();
            int id = (Integer) view.getModeloLivros().getValueAt(linhaSelecionada, 0);
            livros.removeIf(livro -> livro.getId() == id);
            view.getAtualizarTabelaCallback().accept("livro");
            view.limparCampos();

        } catch (ItemNaoSelecionadoException e) {
            view.displayWarning(e.getMessage());
        }
    }

    private void salvarLivro() {
        try {
            String titulo = view.getTxtTitulo().getText();
            String autor = view.getTxtAutor().getText();
            String anoStr = view.getTxtAno().getText();
            String paginasStr = view.getTxtPaginas().getText();
            String isbn = view.getTxtIsbn().getText();

            Validador.validarCamposObrigatorios(titulo, autor, anoStr, paginasStr);
            
            int ano = Validador.validarFormatoInteiro(anoStr);
            int paginas = Validador.validarFormatoInteiro(paginasStr);

            Validador.validarAno(ano);
            Validador.validarNumeroPositivo(paginas);
            
            Genero genero = (Genero) view.getComboGenero().getSelectedItem();
            String descricao = view.getTxtDescricao().getText();
            int linhaSelecionada = view.getTabelaLivros().getSelectedRow();

            if (linhaSelecionada >= 0) {
                int id = (Integer) view.getModeloLivros().getValueAt(linhaSelecionada, 0);
                for (Livro livro : livros) {
                    if (livro.getId() == id) {
                        livro.setTitulo(titulo);
                        livro.setAutor(autor);
                        livro.setAnoPublicacao(ano);
                        livro.setGenero(genero);
                        livro.setDescricao(descricao);
                        livro.setNumPaginas(paginas);
                        livro.setIsbn(isbn);
                        break;
                    }
                }
            } else {
                Livro novo = new Livro(titulo, autor, ano, genero, descricao, paginas, isbn);
                livros.add(novo);
            }
            view.getAtualizarTabelaCallback().accept("livro");
            view.limparCampos();

        } catch (CampoObrigatorioException | FormatoInvalidoException | CampoInvalidoException e) {
            view.displayError(e.getMessage());
        } catch (Exception e) {
            view.displayError("Ocorreu um erro inesperado ao salvar: " + e.getMessage());
        }
    }

    private void preencherFormularioComLivroSelecionado() {
        int selectedRow = view.getTabelaLivros().getSelectedRow();
        if (selectedRow != -1) {
            int idSelecionado = (Integer) view.getModeloLivros().getValueAt(selectedRow, 0);
            Livro livroSelecionado = livros.stream()
                                          .filter(l -> l.getId() == idSelecionado)
                                          .findFirst()
                                          .orElse(null);

            if (livroSelecionado != null) {
                view.getTxtTitulo().setText(livroSelecionado.getTitulo());
                view.getTxtAutor().setText(livroSelecionado.getAutor());
                view.getTxtAno().setText(String.valueOf(livroSelecionado.getAnoPublicacao()));
                view.getComboGenero().setSelectedItem(livroSelecionado.getGenero());
                view.getTxtDescricao().setText(livroSelecionado.getDescricao());
                view.getTxtPaginas().setText(String.valueOf(livroSelecionado.getNumPaginas()));
                view.getTxtIsbn().setText(livroSelecionado.getIsbn());
            }
        }
    }
}