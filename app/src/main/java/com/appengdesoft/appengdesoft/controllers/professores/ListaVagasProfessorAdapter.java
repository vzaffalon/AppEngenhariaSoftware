package com.appengdesoft.appengdesoft.controllers.professores;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.appengdesoft.appengdesoft.R;
import com.appengdesoft.appengdesoft.controllers.gerente.ListaVagasRHAdapter;
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

        holder.tv_departamento.setText(vagas.get(position).getCursos());
        holder.tv_data.setText(vagas.get(position).getData());
        holder.tv_titulo.setText(vagas.get(position).getArea());
        if(vagas.get(position).getTipo().equals("tcc")) {
            holder.tv_descricao.setText(vagas.get(position).getTcc().getAssunto());
        }
        if(vagas.get(position).getTipo().equals("pibic")){
            holder.tv_descricao.setText(vagas.get(position).getPibic().getAssunto());
        }

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
        public TextView tv_departamento;
        public TextView tv_data;
        public TextView tv_titulo;
        public TextView tv_descricao;

        public ListaVagasViewHolder(View view) {
            super(view);
            tv_departamento = (TextView) view.findViewById(R.id.tv_departamento);
            tv_data = (TextView) view.findViewById(R.id.tv_data);
            tv_titulo = (TextView) view.findViewById(R.id.tv_titulo);
            tv_descricao =(TextView) view.findViewById(R.id.tv_descricao);
        }
    }

    public interface ListOnClickListener{
        public void onClickList(View view, int idx);
    }

}

