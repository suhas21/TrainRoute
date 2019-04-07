package com.example.suhas.trainroute

import com.example.suhas.trainroute.TrainRouteJsonClasses.TrainRoutePOJO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface TrainRouteINff {

    @GET("v2/route/train/{tnumber}/apikey/91umr8ozt9")
    fun findTrainInfo(@Path("tnumber") s:Int):Call<TrainRoutePOJO>
}