package com.appengdesoft.appengdesoft.controllers;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.appengdesoft.appengdesoft.R;
import com.appengdesoft.appengdesoft.model.Estagios;

import java.util.List;

/**
 * Created by vzaffalon on 08/11/16.
 */

public class ListaVagasProfessorAdapter extends RecyclerView.Adapter<ListaVagasRHAdapter.ListaEstagiosViewHolder> {
    private final Context context;
    private ListaVagasRHAdapter.ListOnClickListener listOnClickListener;
    private List<Estagios> estagios;

    public ListaVagasProfessorAdapter(Context context, List<Estagios> estagios, ListaVagasRHAdapter.ListOnClickListener listOnClickListener){
        this.context=context;
        this.estagios = estagios;
        this.listOnClickListener = listOnClickListener;
    }

    @Override
    public int getItemCount() {
        if(this.estagios != null){
            return estagios.size();
        }
        return 0;
    }

    @Override
    public ListaVagasRHAdapter.ListaEstagiosViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.vagas_rh_list_item,viewGroup,false);
        ListaVagasRHAdapter.ListaEstagiosViewHolder holder = new ListaVagasRHAdapter.ListaEstagiosViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ListaVagasRHAdapter.ListaEstagiosViewHolder holder, final int position) {

        holder.textView_titulo.setText(estagios.get(position).getName());

        if(listOnClickListener != null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listOnClickListener.onClickList(holder.itemView, position);
                }
            });
        }
    }

    public static class ListaEstagiosViewHolder extends RecyclerView.ViewHolder{
        public TextView textView_titulo;

        public ListaEstagiosViewHolder(View view){
            super(view);
            textView_titulo =(TextView) view.findViewById(R.id.tv_titulo);
        }
    }

    public interface ListOnClickListener{
        public void onClickList(View view, int idx);
    }

}

