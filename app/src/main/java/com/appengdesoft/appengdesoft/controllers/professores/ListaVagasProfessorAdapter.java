package com.appengdesoft.appengdesoft.controllers.professores;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.appengdesoft.appengdesoft.R;
import com.appengdesoft.appengdesoft.model.Vaga;

import java.util.List;

/**
 * Created by vzaffalon on 08/11/16.
 */

public class ListaVagasProfessorAdapter extends RecyclerView.Adapter<ListaVagasProfessorAdapter.ListaVagasViewHolder> {
    private final Context context;
    private ListaVagasProfessorAdapter.ListOnClickListener listOnClickListener;
    private List<Vaga> vagas;

    public ListaVagasProfessorAdapter(Context context, List<Vaga> vagas, ListaVagasProfessorAdapter.ListOnClickListener listOnClickListener){
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
    public ListaVagasProfessorAdapter.ListaVagasViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.vagas_rh_list_item,viewGroup,false);
        ListaVagasProfessorAdapter.ListaVagasViewHolder holder = new ListaVagasProfessorAdapter.ListaVagasViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ListaVagasProfessorAdapter.ListaVagasViewHolder holder, final int position) {

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

    public static class ListaVagasViewHolder extends RecyclerView.ViewHolder{
        public TextView textView_titulo;

        public ListaVagasViewHolder(View view){
            super(view);
            textView_titulo =(TextView) view.findViewById(R.id.tv_titulo);
        }
    }

    public interface ListOnClickListener{
        public void onClickList(View view, int idx);
    }

}

