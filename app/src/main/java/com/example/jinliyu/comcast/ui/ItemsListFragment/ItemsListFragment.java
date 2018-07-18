package com.example.jinliyu.comcast.ui.ItemsListFragment;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jinliyu.comcast.BuildConfig;
import com.example.jinliyu.comcast.R;
import com.example.jinliyu.comcast.data.model.SimpsonsCharacter;
import com.example.jinliyu.comcast.data.model.WireCharacter;
import com.example.jinliyu.comcast.ui.adapter.CharacterAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

public class ItemsListFragment extends Fragment implements  CharacterAdapter.RecyclerViewClickListener, ItemsListContract.IView {

    private RecyclerView recyclerView;
    private List<?> topicsBeanList;
    private String layoutType = "linear";
    private ItemsListContract.IPresenter itemListPresenter;

    private ItemClickListener itemClickListener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);
        recyclerView = view.findViewById(R.id.items_recyclerview);
        EventBus.getDefault().register(this);
        itemListPresenter = new ItemsListPresenter(this);
        itemListPresenter.getDataFromServer();

        return view;

    }

    @Subscribe
    public void onEvent(String type) {
        Log.i("eventbus",   "received");
        layoutType = type;
        itemListPresenter.getDataFromServer();

    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ItemClickListener) {
            itemClickListener = (ItemClickListener) context;
        } else {
            throw new ClassCastException(context.toString()
                    + " must implement ItemsListFragment.ItemClickListener");
        }
    }

    @Override
    public void recyclerViewListClicked(View v, int position) {
        if (BuildConfig.IS_SIMPSON) {
            List<SimpsonsCharacter.RelatedTopicsBean> simpsonList = (List<SimpsonsCharacter.RelatedTopicsBean>) topicsBeanList;
            itemClickListener.onItemClicked(simpsonList.get(position));
        } else {
            List<WireCharacter.RelatedTopicsBean> wireList = (List<WireCharacter.RelatedTopicsBean>) topicsBeanList;
            itemClickListener.onItemClicked(wireList.get(position));
        }
    }

    @Override
    public void initSimpsonsRecyclerView(SimpsonsCharacter simpsonsCharacter) {
        topicsBeanList = simpsonsCharacter.getRelatedTopics();
        CharacterAdapter characterAdapter = new CharacterAdapter(getContext(), (List<Object>) topicsBeanList, layoutType, ItemsListFragment.this);
        if (layoutType.equals("linear")) {
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        }
        recyclerView.setAdapter(characterAdapter);
    }

    @Override
    public void initWireRecyclerView(WireCharacter wireCharacter) {
        topicsBeanList = wireCharacter.getRelatedTopics();
        List<WireCharacter.RelatedTopicsBean> checklist = (List<WireCharacter.RelatedTopicsBean>) topicsBeanList;
        CharacterAdapter characterAdapter = new CharacterAdapter(getContext(), (List<Object>) topicsBeanList, layoutType, ItemsListFragment.this);

        if (layoutType.equals("linear")) {
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false)); }
        else {
            recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        }
        recyclerView.setAdapter(characterAdapter);
    }


    public interface ItemClickListener {
        void onItemClicked(Object item);
    }




    @Override
    public void onDestroyView() {

        EventBus.getDefault().unregister(this);
        super.onDestroyView();
    }
}

