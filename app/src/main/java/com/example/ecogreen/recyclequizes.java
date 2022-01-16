package com.example.ecogreen;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.ecogreen.R ;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.ecogreen.Anime;

import org.w3c.dom.Text;

import java.util.List;

public class recyclequizes extends RecyclerView.Adapter<recyclequizes.MyViewHolder> {

    private final Context mContext;
    private final List<Anime> mData;
    RequestOptions option;

    public recyclequizes(Context mContext, List<Anime> mData) {

        this.mContext = mContext;
        this.mData = mData;

        option = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.anime_row_quizes,parent,false);


        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.question.setText(mData.get(position).getQuestion());
        holder.answer.setText(mData.get(position).getAnswer());
        holder.id.setText(mData.get(position).getId());



    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView answer,question,id;
        Button reply;
        public MyViewHolder(View itemView){
            super(itemView);

            question = itemView.findViewById(R.id.question);
            answer = itemView.findViewById(R.id.answer);
            reply = itemView.findViewById(R.id.reply);
            id = itemView.findViewById(R.id.id);

            reply.setOnClickListener(v->{
                 String idd = id.getText().toString();
                Intent intent = new Intent(v.getContext(),replyquiz.class);
                intent.putExtra("id",idd);
                v.getContext().startActivity(intent);
            });

        }

    }
}
