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

public class recyclecentres extends RecyclerView.Adapter<recyclecentres.MyViewHolder> {

    private final Context mContext;
    private final List<Anime> mData;
    RequestOptions option;

    public recyclecentres(Context mContext, List<Anime> mData) {

        this.mContext = mContext;
        this.mData = mData;

        option = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.anime_row_centres,parent,false);


        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.centre.setText(mData.get(position).getCentre());
        holder.description.setText(mData.get(position).getDescription());
        holder.phone.setText(mData.get(position).getPhone());
        holder.location.setText(mData.get(position).getLocation());
        holder.type.setText(mData.get(position).getType());

        //load image from the internet and set it to Image view using glide
        Glide.with(mContext).load(mData.get(position).getUrl()).apply(option).into(holder.img_thumbnail);

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView centre,location,type,description,phone;
        ImageView img_thumbnail;
        public MyViewHolder(View itemView){
            super(itemView);
            centre = itemView.findViewById(R.id.centre);
            description = itemView.findViewById(R.id.description);
            phone = itemView.findViewById(R.id.phone);
            location = itemView.findViewById(R.id.location);
            type = itemView.findViewById(R.id.type);
            img_thumbnail = itemView.findViewById(R.id.thumbnail);
        }

    }
}
