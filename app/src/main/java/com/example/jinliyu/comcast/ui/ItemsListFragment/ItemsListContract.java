package com.example.jinliyu.comcast.ui.ItemsListFragment;

import com.example.jinliyu.comcast.data.model.SimpsonsCharacter;
import com.example.jinliyu.comcast.data.model.WireCharacter;

public interface ItemsListContract {

    interface IView{
         void initSimpsonsRecyclerView(SimpsonsCharacter simpsonsCharacter);
         void initWireRecyclerView(WireCharacter wireCharacter);
    }

    interface IPresenter{
         void getDataFromServer();
    }
}
