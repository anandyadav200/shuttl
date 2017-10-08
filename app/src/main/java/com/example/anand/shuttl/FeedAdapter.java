package com.example.anand.shuttl;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Anand on 07-Oct-17.
 */

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.MyViewHolder> {

    private ArrayList<Feed> feedList;
    private Context context;
    private long previourDate=0;
    private long currentDate=0;

    public FeedAdapter(ArrayList<Feed> feedList, Context context) {
        this.feedList = feedList;
        this.context = context;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvdate, tvTitle, tvPeopleText, tvQuoteText, tvName;
        public ImageView peopleImage, placeImage;
        public TextView btnLike;

        public MyViewHolder(View view) {
            super(view);
            tvdate = (TextView)view.findViewById(R.id.tv_date);
            tvTitle = (TextView)view.findViewById(R.id.tv_title);
            tvPeopleText = (TextView)view.findViewById(R.id.tv_people_text);
            tvQuoteText = (TextView)view.findViewById(R.id.tv_quote_text);
            tvName = (TextView)view.findViewById(R.id.tv_name);
            peopleImage = (ImageView) view.findViewById(R.id.people_image);
            placeImage = (ImageView) view.findViewById(R.id.place_image);
            btnLike = (TextView) view.findViewById(R.id.btn_like);
            btnLike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (btnLike.getText().equals("LIKE")) {
                        btnLike.setText("UNLIKE");
                        btnLike.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary));
                        btnLike.setTextColor(ContextCompat.getColor(context, R.color.white));
                    } else {
                        btnLike.setText("LIKE");
                        btnLike.setBackgroundColor(ContextCompat.getColor(context, R.color.date_bg));
                        btnLike.setTextColor(ContextCompat.getColor(context, R.color.colorPrimary));
                    }
                }
            });
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.feed_item, parent, false);
        return new MyViewHolder(item);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        Feed feed = feedList.get(position);
        DateFormat ft = new SimpleDateFormat("d MMMM yyyy");
        Date d = new Date(feed.getTime());
        currentDate = feed.getTime();
        if (position==0) {
            holder.tvdate.setVisibility(View.VISIBLE);
        }
        else if (currentDate<previourDate) {
            holder.tvdate.setVisibility(View.VISIBLE);
        } else {
            holder.tvdate.setVisibility(View.GONE);
        }
        previourDate = currentDate;
        holder.tvdate.setText(ft.format(d));
        holder.tvTitle.setText(feed.getTitle());
        holder.tvName.setText("From "+feed.getName());
        if(feed.getImageUrl()==null || feed.getImageUrl().length()==0) {
            holder.peopleImage.setVisibility(View.GONE);
            holder.tvPeopleText.setVisibility(View.GONE);
            holder.placeImage.setVisibility(View.GONE);
            holder.tvQuoteText.setVisibility(View.VISIBLE);
            holder.tvQuoteText.setText(feed.getText());
        } else if(feed.getText()==null || feed.getText().length()==0) {
            Glide.with(context).load(feed.getImageUrl()).error(R.mipmap.ic_launcher).into(holder.placeImage);
            holder.peopleImage.setVisibility(View.GONE);
            holder.tvPeopleText.setVisibility(View.GONE);
            holder.tvQuoteText.setVisibility(View.GONE);
            holder.placeImage.setVisibility(View.VISIBLE);
        } else {
            Glide.with(context).load(feed.getImageUrl()).error(R.mipmap.ic_launcher).into(holder.peopleImage);
            holder.placeImage.setVisibility(View.GONE);
            holder.tvQuoteText.setVisibility(View.GONE);
            holder.tvPeopleText.setVisibility(View.VISIBLE);
            holder.peopleImage.setVisibility(View.VISIBLE);
            holder.tvPeopleText.setText(feed.getText());
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("LIKE", holder.btnLike.getText().toString());
                bundle.putSerializable("DETAIL", feedList.get(position));
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return feedList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }
}
