import model.Categoria;
import model.Produto;
import repository.CategoriaCollectionRepository;
import repository.ProdutoColletionRepository;
import view.CategoriaView;
import view.Opcao;
import view.OpcaoView;
import view.ProdutoView;
import javax.swing.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Opcao opcao = null;
        do{
            opcao = OpcaoView.select();
            switch (opcao){
                case CADASTRAR_CATEGORIA -> cadastrarCategoria();
                case CADASTRAR_PRODUTOR -> cadastrarProduto();
                case CONSULTAR_PRODUTO_POR_ID -> consultarProdutoPorId();
                case CONSULTAR_PRODUTO_POR_CATEGORIA -> consultarProdutoPorCatergoria();
                case ALTERAR_PRODUTO -> alterarProduto();
                case ENCERRAR_SISTEMA -> encerrarOSistema();
            }
            
            
        }while (opcao != Opcao.ENCERRAR_SISTEMA);

    }

    private static void encerrarOSistema() {
        System.exit(0);
    }

    private static void alterarProduto() {
        Produto produto = ProdutoView.select(null);

        if (produto == null) return;

        ProdutoView.update(produto);
    }

    private static void consultarProdutoPorCatergoria() {
        Categoria categoria = CategoriaView.select(null);

        List<Produto> produtos = ProdutoColletionRepository.findByCategoria(categoria);

        if(produtos.isEmpty()){
            JOptionPane.showMessageDialog(null,"Não encontramos produtos com a categoria: " + categoria.getNome());

        }else {
            produtos.forEach(ProdutoView::show);
            produtos.forEach(System.out::println);
        }
    }

    private static void consultarProdutoPorId() {
        String idTexto = JOptionPane.showInputDialog(null, "Informe o ID do produto");

        if (idTexto == null) return;

        try {
            Long id = Long.parseLong(idTexto);
            Produto produto = ProdutoColletionRepository.findById(id);

            if (produto == null) {
                JOptionPane.showMessageDialog(null, "Não encontramos produto com o ID: " + id);
            } else {
                ProdutoView.show(produto);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "ID inválido.");
        }
    }

    private static void cadastrarProduto() {
        Produto produto = ProdutoView.form(new Produto());
        ProdutoColletionRepository.save(produto);
        ProdutoView.sucesso();
    }

    private static void cadastrarCategoria() {

        CategoriaView view = new CategoriaView();
        Categoria categoria = view.form(new Categoria());
        CategoriaCollectionRepository.save(categoria);
        view.sucesso(categoria);
    }

}
