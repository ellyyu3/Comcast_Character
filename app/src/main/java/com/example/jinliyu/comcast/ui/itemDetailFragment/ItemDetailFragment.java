package com.example.jinliyu.comcast.ui.itemDetailFragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.jinliyu.comcast.BuildConfig;
import com.example.jinliyu.comcast.R;
import com.example.jinliyu.comcast.data.model.SimpsonsCharacter;
import com.example.jinliyu.comcast.data.model.WireCharacter;

/**
 *  fragment for display character item details
 */
public class ItemDetailFragment extends Fragment implements ItemDetailFragmentContract.IView{
private String characterText;
private String iconUrl;
private ImageView imageView;
private TextView detailTextView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();

        if(bundle != null) {
            characterText = bundle.getString("text");
            iconUrl = bundle.getString("url");
        }

    }

    public static ItemDetailFragment newInstance(Object object) {
      if(BuildConfig.IS_SIMPSON) {
          SimpsonsCharacter.RelatedTopicsBean simpsonTopicsBean = (SimpsonsCharacter.RelatedTopicsBean) object;
          Bundle args = new Bundle();
          args.putString("text", simpsonTopicsBean.getText());
          args.putString("url", simpsonTopicsBean.getIcon().getURL());
          ItemDetailFragment fragment = new ItemDetailFragment();
          fragment.setArguments(args);
          return fragment;
      }
      else {
          WireCharacter.RelatedTopicsBean wireTopicsBean = (WireCharacter.RelatedTopicsBean) object;
          Bundle args = new Bundle();
          args.putString("text", wireTopicsBean.getText());
          args.putString("url", wireTopicsBean.getIcon().getURL());
          ItemDetailFragment fragment = new ItemDetailFragment();
          fragment.setArguments(args);
          return fragment;

      }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail,
                container, false);
        imageView = view.findViewById(R.id.character_image);
        detailTextView = view.findViewById(R.id.detail_textview);

        Bundle bundle = getArguments();
        if (bundle != null) {
            characterText = bundle.getString("text");
            iconUrl = bundle.getString("url");
        }

        initItemDetail();

        return view;
    }


    @Override
    public void initItemDetail() {
        String[] splitText = characterText.split("-");
        detailTextView.setText(splitText[1]);
        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.placeholder)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH);
        Glide.with(this)
                .load(iconUrl)
                .apply(options)
                .into(imageView);
    }
}
