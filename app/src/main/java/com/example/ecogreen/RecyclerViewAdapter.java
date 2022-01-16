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
import com.google.android.material.bottomnavigation.BottomNavigationItemView;

import org.w3c.dom.Text;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private final Context mContext;
    private final List<Anime> mData;

    RequestOptions option;

    public RecyclerViewAdapter(Context mContext, List<Anime> mData) {

        this.mContext = mContext;
        this.mData = mData;

        option = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        LayoutInflater inflater = LayoutInflater.from(mContext);
      view = inflater.inflate(R.layout.anime_row_item,parent,false);


        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.item_name.setText(mData.get(position).getItemname());
        holder.description.setText(mData.get(position).getDescription());
        holder.price.setText(mData.get(position).getPrice());
        holder.username.setText(mData.get(position).getUsername());

        //load image from the internet and set it to Image view using glide
        Glide.with(mContext).load(mData.get(position).getUrl()).apply(option).into(holder.img_thumbnail);

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView item_name,description,price,username;
        Button buy;
        ImageView img_thumbnail;
        public MyViewHolder(View itemView){
            super(itemView);


            item_name = itemView.findViewById(R.id.item_name);
            description = itemView.findViewById(R.id.description);
            price = itemView.findViewById(R.id.price);
            username= itemView.findViewById(R.id.username);
            buy = itemView.findViewById(R.id.buy);

            img_thumbnail = itemView.findViewById(R.id.thumbnail);
            buy.setOnClickListener(v->{
                String amount = price.getText().toString();
                String user = username.getText().toString();
                String itemm = item_name.getText().toString();
                Intent intent = new Intent(v.getContext(),buyitem.class);
                intent.putExtra("item",itemm);
                intent.putExtra("username",user);
                intent.putExtra("amount",amount);
                v.getContext().startActivity(intent);

            });

        }

    }
}
