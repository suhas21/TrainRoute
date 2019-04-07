package com.example.suhas.trainroute

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.suhas.trainroute.TrainRouteJsonClasses.TrainRoutePOJO
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    fun findResults(v: View)
    {
    val r=Retrofit.Builder().baseUrl("https://api.railwayapi.com/").addConverterFactory(GsonConverterFactory.create()).build()
        val ref=r.create(TrainRouteINff::class.java)
        val trainNumber=et1.text.toString().toInt()
        val c=ref.findTrainInfo(trainNumber)
        c.enqueue(object : retrofit2.Callback<TrainRoutePOJO> {
            override fun onResponse(call: Call<TrainRoutePOJO>, response: Response<TrainRoutePOJO>) {
                Toast.makeText(this@MainActivity, "Successful", Toast.LENGTH_LONG).show()
                val tname = response.body()
                val list = mutableListOf<String>()
                list.add(" ")
                list.add("Number:" + tname!!.train.number)
                list.add("Name:" + tname.train.name)
                val paths = tname.route
                list.add(" ")
                for (x in paths!!) {
                    list.add("Station Num:" + x.no)
                    list.add("Station Name:" + x.station.name)
                    list.add("Arrival Time:" + x.scharr)
                    list.add("Halt Time:" + x.halt)
                    list.add("Depature Time:" + x.schdep)
                    list.add(" ")
                }
                val myadapter = ArrayAdapter<String>(this@MainActivity,R.layout.myownstyle, list)
                lview.adapter = myadapter

            }
            override fun onFailure(call: Call<TrainRoutePOJO>, t: Throwable) {

                   Toast.makeText(this@MainActivity,"Failed",Toast.LENGTH_SHORT).show()

            }
        })
    }
}
