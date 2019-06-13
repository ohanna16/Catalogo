package br.senai.rn.catalogodeprodutos2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import br.senai.rn.catalogodeprodutos2.R;

import java.util.ArrayList;
import java.util.List;

import br.senai.rn.catalogodeprodutos2.model.Produto;

public class ListaProdutoAdapter extends BaseAdapter {

    private final List<Produto> produtos;
    private final Context context;

    public ListaProdutoAdapter(Context context, List<Produto> produtos) {
        this.context = context;
        this.produtos = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return produtos.size();
    }

    @Override
    public Object getItem(int position) {
        return produtos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return produtos.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View viewCriada = criarView(parent);
        Produto produtoEncontrado = (Produto) getItem(position);
        vincularElementos(viewCriada, produtoEncontrado);
        return viewCriada;
    }

    private void vincularElementos(View view, Produto produto) {
        TextView nome = view.findViewById(R.id.item_produto_nome);
        TextView preco = view.findViewById(R.id.item_produto_preco);

        nome.setText(produto.getNome());
        preco.setText("R$: "+ produto.getPreco());
    }

    private View criarView(ViewGroup parent) {
        return LayoutInflater
                .from(context)
                .inflate(R.layout.item_lista, parent, false);
    }

    public void remove (Produto produto){
        produtos.remove(produto);
        notifyDataSetChanged();
    }

    public void update(List<Produto> produtos){
        this.produtos.clear();
        this.produtos.addAll(produtos);
        notifyDataSetChanged();
    }
}
