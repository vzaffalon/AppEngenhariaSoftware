package com.appengdesoft.appengdesoft.controllers.gerente;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.appengdesoft.appengdesoft.R;

import com.appengdesoft.appengdesoft.model.Estagios;
import com.appengdesoft.appengdesoft.model.Vaga;

import android.content.Context;

import java.util.List;

/**
 * Created by vvieira on 03/11/2016.
 */

public class ListaVagasRHAdapter extends RecyclerView.Adapter<ListaVagasRHAdapter.ListaEstagiosViewHolder> {
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
        if(this.vagas != null){
            return vagas.size();
        }
        return 0;
    }

    @Override
    public ListaEstagiosViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.vagas_rh_list_item,viewGroup,false);
        ListaEstagiosViewHolder holder = new ListaEstagiosViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ListaEstagiosViewHolder holder, final int position) {

        holder.textView_titulo.setText(vagas.get(position).getArea());

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
