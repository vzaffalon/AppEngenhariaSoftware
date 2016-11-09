package com.appengdesoft.appengdesoft.controllers.gerente;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.appengdesoft.appengdesoft.R;
import com.appengdesoft.appengdesoft.model.Vaga;

import android.content.Context;

import java.util.List;

/**
 * Created by vvieira on 03/11/2016.
 */

public class ListaVagasRHAdapter extends RecyclerView.Adapter<ListaVagasRHAdapter.ViewHolder> {
    private final Context context;
    private ListOnClickListener listOnClickListener;
    private List<Vaga> vagas;

    public ListaVagasRHAdapter(Context context, List<Vaga> vagas, ListOnClickListener listOnClickListener){
        this.context=context;
        this.vagas = vagas;
        this.listOnClickListener = listOnClickListener;
    }

    @Override
    public int getItemCount() {
        return this.vagas != null ? this.vagas.size() : 0;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.vagas_rh_list_item,viewGroup,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.tv_empresa.setText(vagas.get(position).getCursos());
        holder.tv_data.setText(vagas.get(position).getData());
        holder.tv_titulo.setText(vagas.get(position).getArea());
        holder.tv_descricao.setText(vagas.get(position).getEstagio().getDescricao());

        if(listOnClickListener != null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listOnClickListener.onClickList(holder.itemView, position);
                }
            });
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView tv_empresa;
        public TextView tv_data;
        public TextView tv_titulo;
        public TextView tv_descricao;

        public ViewHolder(View view) {
            super(view);
            tv_empresa = (TextView) view.findViewById(R.id.tv_empresa);
            tv_data = (TextView) view.findViewById(R.id.tv_data);
            tv_titulo = (TextView) view.findViewById(R.id.tv_titulo);
            tv_descricao =(TextView) view.findViewById(R.id.tv_descricao);
        }
    }

    public interface ListOnClickListener{
        public void onClickList(View view, int idx);
    }

}
