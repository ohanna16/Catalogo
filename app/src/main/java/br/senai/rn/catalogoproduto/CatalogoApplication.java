package br.senai.rn.catalogoproduto;

import android.app.Application;

import br.senai.rn.catalogoproduto.dao.ProdutoDAO;
import br.senai.rn.catalogoproduto.model.Produto;

public class CatalogoApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
    }

    private void CriarProdutosDeTeste() {
        ProdutoDAO dao = new ProdutoDAO();

        dao.salvar(new Produto("chocolate", "88552233", "Branco"));
    }
}
