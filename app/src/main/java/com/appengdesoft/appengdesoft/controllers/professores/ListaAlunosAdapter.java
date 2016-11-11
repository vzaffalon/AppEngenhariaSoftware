package com.appengdesoft.appengdesoft.controllers.professores;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.appengdesoft.appengdesoft.R;
import com.appengdesoft.appengdesoft.model.Aluno;
import com.appengdesoft.appengdesoft.model.Aplicacao;
import com.appengdesoft.appengdesoft.model.User;
import com.appengdesoft.appengdesoft.model.Vaga;

import java.util.List;


/**
 * Created by vvieira on 08/11/2016.
 */

public class ListaAlunosAdapter extends RecyclerView.Adapter<ListaAlunosAdapter.ListaAlunosViewHolder>{
    private final Context context;
    private ListOnClickListener listOnClickListener;
    private List<User> users;
    private List<Aplicacao> aplicacaos;

    public ListaAlunosAdapter(Context context, List<User> users,List<Aplicacao> aplicacaos, ListOnClickListener listOnClickListener){
        this.context=context;
        this.users = users;
        this.aplicacaos = aplicacaos;
        this.listOnClickListener = listOnClickListener;
    }

    @Override
    public int getItemCount() {
        if(this.users != null){
            return users.size();
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

        holder.textView_titulo.setText(users.get(position).getNome());
        holder.tv_type.setText(aplicacaos.get(position).getType());
        holder.tv_data.setText("22/10/2016");
        holder.tv_descricao.setText(aplicacaos.get(position).getHabilidade());

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
        public TextView tv_type;
        public TextView tv_data;
        public TextView tv_descricao;

        public ListaAlunosViewHolder(View view){
            super(view);
            textView_titulo =(TextView) view.findViewById(R.id.tv_student_name);
            tv_type = (TextView) view.findViewById(R.id.tv_type);
            tv_data = (TextView) view.findViewById(R.id.tv_data);
            tv_descricao = (TextView) view.findViewById(R.id.tv_descricao);
        }
    }

    public interface ListOnClickListener{
        public void onClickList(View view, int idx);
    }

}
