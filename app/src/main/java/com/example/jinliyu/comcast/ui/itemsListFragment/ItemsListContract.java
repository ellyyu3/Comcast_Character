package com.example.jinliyu.comcast.ui.itemsListFragment;

import com.example.jinliyu.comcast.data.model.SimpsonsCharacter;
import com.example.jinliyu.comcast.data.model.WireCharacter;

/**
 * Contract interface for View and Presenter
 */
interface ItemsListContract {

    interface IView{
         void initSimpsonsRecyclerView(SimpsonsCharacter simpsonsCharacter);
         void initWireRecyclerView(WireCharacter wireCharacter);
         void showErrorToast();
    }

    interface IPresenter{
         void getDataFromServer();
    }
}
