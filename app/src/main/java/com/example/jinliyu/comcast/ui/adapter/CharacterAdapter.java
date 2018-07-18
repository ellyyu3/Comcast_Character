package com.example.jinliyu.comcast.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v7.widget.RecyclerView.ViewHolder;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.jinliyu.comcast.BuildConfig;
import com.example.jinliyu.comcast.R;
import com.example.jinliyu.comcast.data.model.SimpsonsCharacter;
import com.example.jinliyu.comcast.data.model.WireCharacter;

import java.util.List;

public class CharacterAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

   private final List<Object> topicsBeanList;
   private final Context context;
   private final RecyclerViewClickListener listener;
   private final String layoutType;

    public CharacterAdapter(Context context, List<Object> topicsBeanList, String layoutType, RecyclerViewClickListener listener) {
        this.context = context;
        this.topicsBeanList = topicsBeanList;
        this.listener = listener;
        this.layoutType = layoutType;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutType.equals("linear")) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.linear_item_layout, parent, false);
            return new MyViewHolder(view);
        } else if (layoutType.equals("grid")) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_item_layout, parent, false);
            return new GridViewHolder(view);

        }
        throw new RuntimeException("No match for " + layoutType + ".");
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        if (holder instanceof MyViewHolder) {
            if (BuildConfig.IS_SIMPSON) {
                SimpsonsCharacter.RelatedTopicsBean topicsBean = (SimpsonsCharacter.RelatedTopicsBean) topicsBeanList.get(position);
                String text = topicsBean.getText();
                String[] splitText = text.split("-");
                final String character_name = splitText[0];
                ((MyViewHolder) holder).textView.setText(character_name);
            } else {
                WireCharacter.RelatedTopicsBean topicsBean = (WireCharacter.RelatedTopicsBean) topicsBeanList.get(position);
                String text = topicsBean.getText();
                String[] splitText = text.split("-");
                final String character_name = splitText[0];
                ((MyViewHolder) holder).textView.setText(character_name);
            }
            ((MyViewHolder) holder).itemView.setTag(position);

        }
            if(holder instanceof GridViewHolder)
            {
                if (BuildConfig.IS_SIMPSON) {
                    SimpsonsCharacter.RelatedTopicsBean topicsBean = (SimpsonsCharacter.RelatedTopicsBean) topicsBeanList.get(position);
                    final String iconUrl = topicsBean.getIcon().getURL();
                    GridViewHolder gridViewHolder = (GridViewHolder) holder;
                    RequestOptions options = new RequestOptions()
                            .placeholder(R.drawable.placeholder)
                            .error(R.drawable.placeholder)
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .priority(Priority.HIGH);
                    Glide.with(context)
                            .load(iconUrl)
                            .apply(options)
                            .into(gridViewHolder.imageView);
                } else {
                    WireCharacter.RelatedTopicsBean topicsBean = (WireCharacter.RelatedTopicsBean) topicsBeanList.get(position);
                    final String iconUrl = topicsBean.getIcon().getURL();
                    GridViewHolder gridViewHolder = (GridViewHolder) holder;
                    RequestOptions options = new RequestOptions()
                            .placeholder(R.drawable.placeholder)
                            .error(R.drawable.placeholder)
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .priority(Priority.HIGH);
                    Glide.with(context)
                            .load(iconUrl)
                            .apply(options)
                            .into(gridViewHolder.imageView);

            }
                ((GridViewHolder) holder).itemView.setTag(position);

        }
    }

    @Override
    public int getItemCount() {
        return topicsBeanList.size();
    }


    public interface RecyclerViewClickListener {
            void recyclerViewListClicked(View view, int position);
        }



        class MyViewHolder extends RecyclerView.ViewHolder {

            final TextView textView;

            MyViewHolder(View itemView) {
                super(itemView);
                textView = itemView.findViewById(R.id.character_name);

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listener.recyclerViewListClicked(v, (int) v.getTag());
                    }
                });
            }
        }

        class GridViewHolder extends RecyclerView.ViewHolder {

            final ImageView imageView;

            GridViewHolder(View view) {
                super(view);
                imageView = view.findViewById(R.id.character_grid_image);

                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listener.recyclerViewListClicked(v, (int) v.getTag());
                    }
                });

            }

        }



}
