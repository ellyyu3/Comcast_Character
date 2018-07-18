package com.example.jinliyu.comcast.ui.detailActivity;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.jinliyu.comcast.R;

/**
 *  Activity to display character detail
 */
public class DetailActivity extends AppCompatActivity implements DetailActivityContract.IView{
private ImageView imageView;
private TextView textView;
private String iconUrl;
private String characterText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        imageView = findViewById(R.id.character_image);
        textView = findViewById(R.id.detail_textview);
        iconUrl = getIntent().getStringExtra("url");
        characterText = getIntent().getStringExtra("text");

        initDetailContent();
    }

    @Override
    public void initDetailContent(){

        String[] splitText = characterText.split("-");
        String characterName = splitText[0];
        String characterDescription = splitText[1];
        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.placeholder)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH);
        Glide.with(this)
                .load(iconUrl)
                .apply(options)
                .into(imageView);
        textView.setText(characterDescription);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(characterName);

        }

}
