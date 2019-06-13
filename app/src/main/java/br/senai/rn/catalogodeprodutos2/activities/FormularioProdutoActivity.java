package br.senai.rn.catalogodeprodutos2.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.senai.rn.catalogodeprodutos2.R;
import br.senai.rn.catalogodeprodutos2.dao.ProdutoDAO;
import br.senai.rn.catalogodeprodutos2.model.Produto;

public class FormularioProdutoActivity extends AppCompatActivity {

    private EditText campoNome;
    private EditText campoPreco;
    private EditText campoFab;
    private Button botao;
    private ProdutoDAO dao;
    private Produto produto;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_produto);
        setTitle("Novo Produto");
        iniciarComponentes();
        iniciarProduto();
        definirEventos();
    }

    private void definirEventos() {
        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                criarAluno();
                salvarAluno();
            }
        });
    }

    public void iniciarProduto() {
        dao = new ProdutoDAO();
        if (getIntent().hasExtra("produto")){
            produto = (Produto) getIntent().getSerializableExtra("produto");
            setTitle("Editar Produto");
            campoNome.setText(produto.getNome());
            campoPreco.setText(produto.getPreco());
            campoFab.setText(produto.getFabricante());
        } else {
            produto = new Produto();
        }
    }


    private void iniciarComponentes() {
        campoNome = findViewById(R.id.activity_formulario_produto_nome);
        campoPreco = findViewById(R.id.activity_formulario_produtoPreco);
        campoFab = findViewById(R.id.activity_formulario_produtoFab);
        botao = findViewById(R.id.botaoSalvar);
    }

    private void criarAluno() {
        String nome = campoNome.getText().toString();
        String preco = campoPreco.getText().toString();
        String fabricante = campoFab.getText().toString();

        produto.setNome(nome);
        produto.setPreco(preco);
        produto.setFabricante(fabricante);
    }

    private void salvarAluno() {
        dao.salvar(produto);
        finish();
    }
}
