package br.senai.rn.catalogodeprodutos2.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import br.senai.rn.catalogodeprodutos2.R;
import br.senai.rn.catalogodeprodutos2.adapter.ListaProdutoAdapter;
import br.senai.rn.catalogodeprodutos2.dao.ProdutoDAO;
import br.senai.rn.catalogodeprodutos2.model.Produto;

public class ListaProdutoActivity extends AppCompatActivity {
    private ListView listaProdutos;
    private FloatingActionButton botaoAdicionar;
    private ListaProdutoAdapter adapter;
    private ProdutoDAO dao;
    private Menu Edit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_produtos);
        setTitle("Lista de Produtos");
        inicializarComponentes();
        definirEventos();
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.update(dao.obterTodos());
    }

    private void definirEventos() {
        botaoAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListaProdutoActivity.this, FormularioProdutoActivity.class);
                startActivity(intent);
            }
        });

        listaProdutos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Produto produtoSelecionado = dao.obterTodos().get(position);
                Intent intent = new Intent(ListaProdutoActivity.this, FormularioProdutoActivity.class);
                intent.putExtra("produto", produtoSelecionado);
                startActivity(intent);
            }
        });
    }

    private void inicializarComponentes() {
        dao = new ProdutoDAO();

        listaProdutos = findViewById(R.id.activity_lista_produtos_listview);
        botaoAdicionar = findViewById(R.id.floatingActionButton_btLista);
        Edit = findViewById(R.id.activity_lista_produto_menu_editar);
        configurarAdapter(listaProdutos);

        registerForContextMenu(listaProdutos);
    }

    private void configurarAdapter(ListView listView) {
        adapter = new ListaProdutoAdapter(this, dao.obterTodos());

        listView.setAdapter(adapter);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.activity_lista_produto_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(final MenuItem item) {
        int menuSelecionado = item.getItemId();

        if (menuSelecionado == R.id.activity_lista_produto_menu_remover) {

            AdapterView.AdapterContextMenuInfo menuInfo
                    = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            final Produto produtoSelecionado = (Produto) adapter.getItem(menuInfo.position);

            new AlertDialog.Builder(this)
                    .setTitle("Remover Produto?")
                    .setMessage("Deseja remover esse produto "+ produtoSelecionado.getNome()+"?")
                    .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dao.remover(produtoSelecionado);
                            adapter.remove(produtoSelecionado);
                        }
                    })
                    .setNegativeButton("Não", null)
                    .show();
        } if (menuSelecionado == R.id.activity_lista_produto_menu_editar){
            AdapterView.AdapterContextMenuInfo menuInfo
                    = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            final Produto produtoSelecionado = (Produto) adapter.getItem(menuInfo.position);

            new AlertDialog.Builder(this)
                    .setTitle("Informações do Produto")
                    .setMessage("Nome: " + produtoSelecionado.getNome()
                                +"\nPreço: " + produtoSelecionado.getPreco()
                                +"\nFabricante: " +produtoSelecionado.getFabricante())
                    .show();
        }

        return super.onContextItemSelected(item);

    }
}
