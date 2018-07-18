package com.example.jinliyu.comcast.data.network;

import com.example.jinliyu.comcast.data.model.SimpsonsCharacter;
import com.example.jinliyu.comcast.data.model.WireCharacter;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface ApiService {

    @GET
    Call<SimpsonsCharacter> getSimpsonData(@Url String empty);

    @GET
    Call<WireCharacter> getWireData(@Url String empty);
}
