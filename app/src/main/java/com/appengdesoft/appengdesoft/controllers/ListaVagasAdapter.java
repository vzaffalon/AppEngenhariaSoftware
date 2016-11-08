package com.appengdesoft.appengdesoft.controllers;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.appengdesoft.appengdesoft.R;
import com.appengdesoft.appengdesoft.model.Vaga;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;


/**
 * Created by zaffalon on 3/8/16.
 */
public class ListaVagasAdapter extends RecyclerView.Adapter<ListaVagasAdapter.ListaAlunosViewHolder> {

    private final Context context;
    private ListOnClickListener listOnClickListener;
    private RealmList<Vaga> bolsas;

    public ListaVagasAdapter(Context context, RealmList<Vaga> bolsas, ListOnClickListener listOnClickListener){
        this.context=context;
        this.bolsas = bolsas;
        this.listOnClickListener = listOnClickListener;
    }


    @Override
    public ListaAlunosViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.vagas_list_item,viewGroup,false);
        ListaAlunosViewHolder holder = new ListaAlunosViewHolder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(final ListaAlunosViewHolder holder,final int position) {
        holder.tv_titulo.setText(bolsas.get(position).getArea());
        holder.tv_departamento.setText(bolsas.get(position).getCursos());
        holder.tv_data.setText(bolsas.get(position).getData());
        String type = bolsas.get(position).getTipo();
        if(type.equals("pibic")){
            holder.tv_descricao.setText(bolsas.get(position).getPibic().getAssunto());
        }
        if(type.equals("tcc")){
            holder.tv_descricao.setText(bolsas.get(position).getTcc().getAssunto());
        }
        if(type.equals("estagio")){
            holder.tv_descricao.setText(bolsas.get(position).getRequisitos());
        }

        if(listOnClickListener != null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //a variavel position é final
                    listOnClickListener.onClickList(holder.itemView, position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return this.bolsas != null ? this.bolsas.size() : 0;
    }

    //viewholder com as views

    public static class ListaAlunosViewHolder extends RecyclerView.ViewHolder{
        public TextView tv_titulo;
        public TextView tv_departamento;
        public TextView tv_data;
        public TextView tv_descricao;

        public ListaAlunosViewHolder(View view){
            super(view);
            tv_titulo =(TextView) view.findViewById(R.id.tv_titulo);
            tv_departamento =(TextView) view.findViewById(R.id.tv_departamento);
            tv_data =(TextView) view.findViewById(R.id.tv_data);
            tv_descricao =(TextView) view.findViewById(R.id.tv_descricao);
        }
    }

    public interface ListOnClickListener{
        public void onClickList(View view, int idx);
    }
}
