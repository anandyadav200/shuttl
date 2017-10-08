package com.example.anand.shuttl;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvdate, tvHeader, tvPeopleText, tvQuoteText, tvName, tvDescription;
    private ImageView peopleImage, placeImage;
    private TextView btnLike;
    private Bundle bundle;

    private Feed feed = null;
    private String isLike;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        initViews();
        btnLike.setOnClickListener(this);

        bundle = getIntent().getExtras();
        feed = (Feed)bundle.get("DETAIL");
        isLike = bundle.getString("LIKE");

        setData();
    }

    private void setData() {
        DateFormat ft = new SimpleDateFormat("d MMMM yyyy");
        Date d = new Date(feed.getTime());
        tvdate.setText(ft.format(d));
        tvHeader.setText(feed.getTitle());
        tvName.setText("From "+feed.getName());
        tvDescription.setText(feed.getDescription());
        if(feed.getImageUrl()==null || feed.getImageUrl().length()==0) {
            peopleImage.setVisibility(View.GONE);
            tvPeopleText.setVisibility(View.GONE);
            placeImage.setVisibility(View.GONE);
            tvQuoteText.setVisibility(View.VISIBLE);
            tvQuoteText.setText(feed.getText());
        } else if(feed.getText()==null || feed.getText().length()==0) {
            Glide.with(this).load(feed.getImageUrl()).error(R.mipmap.ic_launcher).into(placeImage);
            peopleImage.setVisibility(View.GONE);
            tvPeopleText.setVisibility(View.GONE);
            tvQuoteText.setVisibility(View.GONE);
            placeImage.setVisibility(View.VISIBLE);
        } else {
            Glide.with(this).load(feed.getImageUrl()).error(R.mipmap.ic_launcher).into(peopleImage);
            placeImage.setVisibility(View.GONE);
            tvQuoteText.setVisibility(View.GONE);
            tvPeopleText.setVisibility(View.VISIBLE);
            peopleImage.setVisibility(View.VISIBLE);
            tvPeopleText.setText(feed.getText());
        }
        if(isLike.equals("LIKE")) {
            btnLike.setText("LIKE");
            btnLike.setBackgroundColor(ContextCompat.getColor(this, R.color.date_bg));
            btnLike.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary));
        } else {
            btnLike.setText("UNLIKE");
            btnLike.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary));
            btnLike.setTextColor(ContextCompat.getColor(this, R.color.white));
        }
    }

    private void initViews() {
        tvdate = (TextView)findViewById(R.id.tv_date);
        tvHeader = (TextView)findViewById(R.id.tv_header);
        tvPeopleText = (TextView)findViewById(R.id.tv_people_text);
        tvQuoteText = (TextView)findViewById(R.id.tv_quote_text);
        tvName = (TextView)findViewById(R.id.tv_name);
        tvDescription = (TextView)findViewById(R.id.tv_description);
        peopleImage = (ImageView) findViewById(R.id.people_image);
        placeImage = (ImageView) findViewById(R.id.place_image);
        btnLike = (TextView) findViewById(R.id.btn_like);
    }

    @Override
    public void onClick(View v) {
        if (btnLike.getText().equals("LIKE")) {
            btnLike.setText("UNLIKE");
            btnLike.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary));
            btnLike.setTextColor(ContextCompat.getColor(this, R.color.white));
        } else {
            btnLike.setText("LIKE");
            btnLike.setBackgroundColor(ContextCompat.getColor(this, R.color.date_bg));
            btnLike.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary));
        }
    }
}
