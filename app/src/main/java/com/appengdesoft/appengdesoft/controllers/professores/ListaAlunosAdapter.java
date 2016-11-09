package com.appengdesoft.appengdesoft.controllers.professores;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.appengdesoft.appengdesoft.R;
import com.appengdesoft.appengdesoft.model.Aluno;
import com.appengdesoft.appengdesoft.model.Vaga;

import java.util.List;


/**
 * Created by vvieira on 08/11/2016.
 */

public class ListaAlunosAdapter extends RecyclerView.Adapter<ListaAlunosAdapter.ListaAlunosViewHolder>{
    private final Context context;
    private ListOnClickListener listOnClickListener;
    private List<Aluno> alunos;

    public ListaAlunosAdapter(Context context, List<Aluno> alunos, ListOnClickListener listOnClickListener){
        this.context=context;
        this.alunos = alunos;
        this.listOnClickListener = listOnClickListener;
    }

    @Override
    public int getItemCount() {
        if(this.alunos != null){
            return alunos.size();
        }
        return 0;
    }

    @Override
    public ListaAlunosViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.vagas_aluno_professor_list_item,viewGroup,false);
        ListaAlunosViewHolder holder = new ListaAlunosViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ListaAlunosViewHolder holder, final int position) {

        holder.textView_titulo.setText(alunos.get(position).getMatricula());

        if(listOnClickListener != null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listOnClickListener.onClickList(holder.itemView, position);
                }
            });
        }
    }

    public static class ListaAlunosViewHolder extends RecyclerView.ViewHolder{
        public TextView textView_titulo;

        public ListaAlunosViewHolder(View view){
            super(view);
            textView_titulo =(TextView) view.findViewById(R.id.tv_titulo);
        }
    }

    public interface ListOnClickListener{
        public void onClickList(View view, int idx);
    }

}
