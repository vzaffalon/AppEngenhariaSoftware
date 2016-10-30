package com.appengdesoft.appengdesoft.controllers;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.appengdesoft.appengdesoft.R;
import com.appengdesoft.appengdesoft.model.Bolsas;

import java.util.List;


/**
 * Created by zaffalon on 3/8/16.
 */
public class ListaAlunosAdapter extends RecyclerView.Adapter<ListaAlunosAdapter.ListaAlunosViewHolder> {

    private final Context context;
    private ListOnClickListener listOnClickListener;
    private List<Bolsas> bolsas;

    public ListaAlunosAdapter(Context context, List<Bolsas> bolsas, ListOnClickListener listOnClickListener){
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

        holder.tv_titulo.setText(bolsas.get(position).getName());

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

        public ListaAlunosViewHolder(View view){
            super(view);
            tv_titulo =(TextView) view.findViewById(R.id.tv_titulo);
        }
    }

    public interface ListOnClickListener{
        public void onClickList(View view, int idx);
    }
}
