package com.example.ecogreen;

import android.content.Context;
import android.content.Intent;
import android.icu.text.Transliterator;
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

public class recyclefeebacks extends RecyclerView.Adapter<recyclefeebacks.MyViewHolder> {

    private final Context mContext;
    private final List<Anime> mData;
    RequestOptions option;

    public recyclefeebacks(Context mContext, List<Anime> mData) {

        this.mContext = mContext;
        this.mData = mData;

        option = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.anime_row_feedbacks,parent,false);


        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.feedback.setText(mData.get(position).getFeedback());
        holder.time.setText(mData.get(position).getTime());


    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView feedback,time;

        public MyViewHolder(View itemView){
            super(itemView);
            time = itemView.findViewById(R.id.time);
            feedback = itemView.findViewById(R.id.feedback);



        }

    }
}
