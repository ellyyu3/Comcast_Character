package com.example.jinliyu.comcast.ui.main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.Switch;

import com.example.jinliyu.comcast.BuildConfig;
import com.example.jinliyu.comcast.ui.ItemDetailFragment.ItemDetailFragment;
import com.example.jinliyu.comcast.ui.ItemsListFragment.ItemsListFragment;
import com.example.jinliyu.comcast.data.model.SimpsonsCharacter;
import com.example.jinliyu.comcast.data.model.WireCharacter;
import com.example.jinliyu.comcast.ui.detailActivity.DetailActivity;
import com.example.jinliyu.comcast.R;

import org.greenrobot.eventbus.EventBus;

public class MainActivity extends AppCompatActivity implements ItemsListFragment.ItemClickListener{
    private boolean isLargeScreen = false;
    private String layoutType = "linear";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        FrameLayout fragmentItemDetails = findViewById(R.id.detail_container);
        if(fragmentItemDetails!=null) {
            isLargeScreen = true;
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        MenuItem item = menu.findItem(R.id.switch_id);
        item.setActionView(R.layout.switch_layout);
        final Switch toggle = item
                .getActionView().findViewById(R.id.toggle_switch);

        if (isLargeScreen) {
            toggle.setVisibility(View.INVISIBLE);
        } else {
            toggle.setVisibility(View.VISIBLE);
        }
        toggle.setChecked(false);


//        toggle.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (toggle.isChecked()) {
//                    layoutType = "grid";
//                    toggle.setChecked(false);
//                    EventBus.getDefault().post(layoutType);
//                } else {
//                    layoutType = "linear";
//                    toggle.setChecked(true);
//
//                    EventBus.getDefault().post(layoutType);
//                }
//            }
//
//        });
//        return true;
//    }
        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                Log.i("eventbus",   "triggered");

                if (isChecked) {
                    Log.i("eventbus",   "post");

                    layoutType = "grid";
                    EventBus.getDefault().post(layoutType);

                } else {
                    Log.i("eventbus",   "post");
                    layoutType = "linear";
                    EventBus.getDefault().post(layoutType);

                }
            }
        });

        return true;

    }


    @Override
    public void onItemClicked(Object item) {
        if(isLargeScreen){

            if(BuildConfig.IS_SIMPSON) {
                SimpsonsCharacter.RelatedTopicsBean topicsBean = (SimpsonsCharacter.RelatedTopicsBean) item;
                ItemDetailFragment itemDetailFragment = ItemDetailFragment.newInstance(topicsBean);
                getSupportFragmentManager().beginTransaction().replace(R.id.detail_container, itemDetailFragment).commit();
            }
            else
            {
                WireCharacter.RelatedTopicsBean topicsBean = (WireCharacter.RelatedTopicsBean)item;
                ItemDetailFragment itemDetailFragment = ItemDetailFragment.newInstance(topicsBean);
                getSupportFragmentManager().beginTransaction().replace(R.id.detail_container, itemDetailFragment).commit();
            }
        }
        else
        {
            Intent intent = new Intent(MainActivity.this, DetailActivity.class);
            if(BuildConfig.IS_SIMPSON) {
                SimpsonsCharacter.RelatedTopicsBean topicsBean = (SimpsonsCharacter.RelatedTopicsBean) item;
                intent.putExtra("text", topicsBean.getText());
                intent.putExtra("url", topicsBean.getIcon().getURL());
                startActivity(intent);
            }
            else{
                WireCharacter.RelatedTopicsBean topicsBean = (WireCharacter.RelatedTopicsBean) item;
                intent.putExtra("text",topicsBean.getText());
                intent.putExtra("url",topicsBean.getIcon().getURL());
                startActivity(intent);
            }

        }

    }


}
