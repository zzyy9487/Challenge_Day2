package com.example.bath

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_list.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ListActivity : AppCompatActivity() {

    var list = mutableListOf<People>()
    lateinit var adapter:ListAdapter
    lateinit var body:Kick

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://32cc8640.ngrok.io")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val apiInterface = retrofit.create(APIInterface::class.java)
        val call = apiInterface.getList()

        call.enqueue(object :retrofit2.Callback<Data>{
            override fun onFailure(call: Call<Data>, t: Throwable) {
                Toast.makeText(this@ListActivity, t.toString(), Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<Data>, response: Response<Data>) {
                if (response.isSuccessful){
                    val data = response.body()

                    if (!data!!.data.isNullOrEmpty()){
                        for (i in 0 until data.data.size){
                            list.add(i, People(data.data[i].id, data.data[i].name, data.data[i].drink , data.data[i].isIn ))
                        }
                        if (list.size ==0){
                            recyclerView.visibility = View.INVISIBLE
                        }
                        else{
                            recyclerView.visibility = View.VISIBLE
                        }
                        adapter = ListAdapter(list, this@ListActivity)
                        recyclerView.layoutManager = LinearLayoutManager(this@ListActivity)
                        recyclerView.adapter = adapter

                        adapter.setdeleteListener(object :ListAdapter.deleteListener{
                            override fun delete(id: Int, name: String) {
                                var str :String = "要將 "+"$name"+" 踢出嗎?!"

                                AlertDialog.Builder(this@ListActivity)
                                    .setTitle(str)
                                    .setNeutralButton("放你一馬") { dialog, which -> }
                                    .setPositiveButton("滾吧！XD") { dialog, which ->

                                        body= Kick(id)
                                        val retrofit2 = Retrofit.Builder()
                                            .baseUrl("https://32cc8640.ngrok.io")
                                            .addConverterFactory(GsonConverterFactory.create())
                                            .build()
                                        var apiInterface = retrofit2.create(APIInterface::class.java)
                                        val call2 = apiInterface.byebye(body)

                                        call2.enqueue(object :retrofit2.Callback<Data2>{
                                            override fun onFailure(call: Call<Data2>, t: Throwable) {
                                                Toast.makeText(this@ListActivity, t.toString(), Toast.LENGTH_LONG).show()
                                            }

                                            override fun onResponse(call: Call<Data2>, response: Response<Data2>) {
                                                if (response.isSuccessful){
                                                    var word:String = "已將 " +"$name" + " 踢出澡堂"
                                                    Toast.makeText(this@ListActivity, word, Toast.LENGTH_LONG).show()

                                                    val retrofit = Retrofit.Builder()
                                                        .baseUrl("https://32cc8640.ngrok.io")
                                                        .addConverterFactory(GsonConverterFactory.create())
                                                        .build()
                                                    val apiInterface = retrofit.create(APIInterface::class.java)
                                                    val call = apiInterface.getList()

                                                    call.enqueue(object :retrofit2.Callback<Data>{
                                                        override fun onFailure(call: Call<Data>, t: Throwable) {
                                                            Toast.makeText(this@ListActivity, t.toString(), Toast.LENGTH_LONG).show()
                                                        }

                                                        override fun onResponse(call: Call<Data>, response: Response<Data>) {
                                                            if (response.isSuccessful){
                                                                val data = response.body()
                                                                if (!data!!.data.isNullOrEmpty()){
                                                                    list.clear()
                                                                    for (i in 0 until data.data.size){
                                                                        list.add(i, People(data.data[i].id, data.data[i].name, data.data[i].drink , data.data[i].isIn ))
                                                                    }
                                                                    if (list.size ==0){
                                                                        recyclerView.visibility = View.INVISIBLE
                                                                    }
                                                                    else{
                                                                        recyclerView.visibility = View.VISIBLE
                                                                    }
                                                                    adapter.notifyDataSetChanged()
                                                                }
                                                            }
                                                            else{
                                                                Toast.makeText(this@ListActivity, response.toString(), Toast.LENGTH_LONG).show()
                                                            }
                                                        }
                                                    })
                                                }


                                            }
                                        })

                                    }.show()
                            }

                            override fun remove(id: Int, name: String) {
                                var str :String = "要將 "+"$name"+" 踢出嗎?!"
                                body= Kick(id)
                                val retrofit2 = Retrofit.Builder()
                                    .baseUrl("https://32cc8640.ngrok.io")
                                    .addConverterFactory(GsonConverterFactory.create())
                                    .build()
                                var apiInterface = retrofit2.create(APIInterface::class.java)
                                val call2 = apiInterface.byebye(body)

                                call2.enqueue(object :retrofit2.Callback<Data2>{
                                    override fun onFailure(call: Call<Data2>, t: Throwable) {
                                        Toast.makeText(this@ListActivity, t.toString(), Toast.LENGTH_LONG).show()
                                    }

                                    override fun onResponse(call: Call<Data2>, response: Response<Data2>) {
                                        if (response.isSuccessful){
                                            var word:String = "已將 " +"$name" + " 踢出澡堂"
                                            Toast.makeText(this@ListActivity, word, Toast.LENGTH_LONG).show()

                                            val retrofit = Retrofit.Builder()
                                                .baseUrl("https://32cc8640.ngrok.io")
                                                .addConverterFactory(GsonConverterFactory.create())
                                                .build()
                                            val apiInterface = retrofit.create(APIInterface::class.java)
                                            val call = apiInterface.getList()

                                            call.enqueue(object :retrofit2.Callback<Data>{
                                                override fun onFailure(call: Call<Data>, t: Throwable) {
                                                    Toast.makeText(this@ListActivity, t.toString(), Toast.LENGTH_LONG).show()
                                                }

                                                override fun onResponse(call: Call<Data>, response: Response<Data>) {
                                                    if (response.isSuccessful){
                                                        val data = response.body()
                                                        if (!data!!.data.isNullOrEmpty()){
                                                            list.clear()
                                                            for (i in 0 until data.data.size){
                                                                list.add(i, People(data.data[i].id, data.data[i].name, data.data[i].drink , data.data[i].isIn ))
                                                            }
                                                            if (list.size ==0){
                                                                recyclerView.visibility = View.INVISIBLE
                                                            }
                                                            else{
                                                                recyclerView.visibility = View.VISIBLE
                                                            }
                                                            adapter.notifyDataSetChanged()
                                                        }
                                                    }
                                                    else{
                                                        Toast.makeText(this@ListActivity, response.toString(), Toast.LENGTH_LONG).show()
                                                    }
                                                }
                                            })
                                        }


                                    }
                                })


                            }










                        })






                    }
                    else{

                    }

                }
                else{
                    Toast.makeText(this@ListActivity, response.toString(), Toast.LENGTH_LONG).show()
                }
            }
        })






    }

    override fun onBackPressed() {
        super.onBackPressed()
        this.finish()
    }
}
