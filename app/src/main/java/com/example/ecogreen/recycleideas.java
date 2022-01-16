package com.example.ecogreen;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class recycleideas extends RecyclerView.Adapter<recycleideas.MyViewHolder> {

    private final Context mContext;
    private final List<Anime> mData;
    RequestOptions option;

    public recycleideas(Context mContext, List<Anime> mData) {

        this.mContext = mContext;
        this.mData = mData;

        option = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.anime_row_ideas,parent,false);


        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.heading.setText(mData.get(position).getHeading());
        holder.description.setText(mData.get(position).getDescription());
        holder.phone.setText(mData.get(position).getPhone());
        holder.link.setText(mData.get(position).getLink());

        //load image from the internet and set it to Image view using glide
        Glide.with(mContext).load(mData.get(position).getUrl()).apply(option).into(holder.img_thumbnail);

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView heading,description,phone,link;
        ImageView img_thumbnail;
        public MyViewHolder(View itemView){
            super(itemView);
            heading = itemView.findViewById(R.id.heading);
            description = itemView.findViewById(R.id.description);
            phone = itemView.findViewById(R.id.phone);
            link = itemView.findViewById(R.id.link);
            //view= itemView.findViewById(R.id.view);
            img_thumbnail = itemView.findViewById(R.id.thumbnail);
        }

    }
}
