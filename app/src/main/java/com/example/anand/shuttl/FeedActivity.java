package com.example.anand.shuttl;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;

public class FeedActivity extends AppCompatActivity {

    private ArrayList<Feed> feedList;
    private RecyclerView recyclerView;
    private FeedAdapter feedAdapter;
    private TextView headerText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        recyclerView = (RecyclerView)findViewById(R.id.rv_feed);
        headerText = (TextView)findViewById(R.id.tv_header);
        headerText.setText(R.string.shuttl);

        RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        String data = readJSONFromAsset();
        Gson gson = new Gson();
        Feed[] feedArray = gson.fromJson(data, Feed[].class);

        feedList = new ArrayList<Feed>(Arrays.asList(feedArray));

        feedAdapter = new FeedAdapter(feedList, this);
        recyclerView.setAdapter(feedAdapter);

    }

    private String readJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getAssets().open("shuttldata.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}
