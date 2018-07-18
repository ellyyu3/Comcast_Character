package com.example.jinliyu.comcast.ui.itemsListFragment;

import android.support.annotation.NonNull;

import com.example.jinliyu.comcast.BuildConfig;
import com.example.jinliyu.comcast.data.model.SimpsonsCharacter;
import com.example.jinliyu.comcast.data.model.WireCharacter;
import com.example.jinliyu.comcast.data.network.ApiService;
import com.example.jinliyu.comcast.data.network.RetrofitInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * presenter of ItemsListFragment, for communication between Model and View
 */
public class ItemsListPresenter implements ItemsListContract.IPresenter {
private final ItemsListContract.IView itemListFragment;

            public ItemsListPresenter(ItemsListContract.IView itemListFragment){
                this.itemListFragment = itemListFragment;

            }


           @Override
           public void getDataFromServer(){
               ApiService apiService = RetrofitInstance.getRetrofitInstance().create(ApiService.class);

               if (BuildConfig.IS_SIMPSON) {
                   apiService.getSimpsonData("").enqueue(new Callback<SimpsonsCharacter>() {
                       @Override
                       public void onResponse(@NonNull Call<SimpsonsCharacter> call, @NonNull Response<SimpsonsCharacter> response) {
                           if(response.body() != null) {
                               itemListFragment.initSimpsonsRecyclerView(response.body());
                           }

                       }
                       @Override
                       public void onFailure(@NonNull Call<SimpsonsCharacter> call, @NonNull Throwable t) {
                           itemListFragment.showErrorToast();
                       }
                   });
               } else {
                   apiService.getWireData("").enqueue(new Callback<WireCharacter>() {
                       @Override
                       public void onResponse(@NonNull Call<WireCharacter> call, @NonNull Response<WireCharacter> response) {
                           if(response.body() != null) {
                               itemListFragment.initWireRecyclerView(response.body());
                           }
                       }

                       @Override
                       public void onFailure(@NonNull Call<WireCharacter> call, @NonNull Throwable t) {
                           itemListFragment.showErrorToast();
                       }
                   });

               }

           }


}
