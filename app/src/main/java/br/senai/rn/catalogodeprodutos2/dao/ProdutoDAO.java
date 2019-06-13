package br.senai.rn.catalogodeprodutos2.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.senai.rn.catalogodeprodutos2.model.Produto;

public class ProdutoDAO {

    private static Long contadorDeIds = 1L;
    private static List<Produto> produtos = new ArrayList<>();

    public void salvar(Produto produto) {
        if (produto.getId() == null) {
            produto.setId(contadorDeIds);
            produtos.add(produto);
            contadorDeIds++;
        } else {
            int posicao = produtos.indexOf(produto);
            produtos.set(posicao, produto);
        }
    }

    public List<Produto> obterTodos() {
        List<Produto> copiaProdutos = new ArrayList<>(produtos);
        Collections.sort(copiaProdutos);
        return copiaProdutos;
    }

    public void remover(Produto aluno) {
        produtos.remove(aluno);
    }

}
