package com.example.newsapp.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.newsapp.R
import com.example.newsapp.adapter.NewsAdapter
import com.example.newsapp.api.ApiInterface
import com.example.newsapp.api.RetrofitHelper
import com.example.newsapp.databinding.FragmentHomeBinding
import com.example.newsapp.model.MainNewsModel
import com.example.newsapp.model.NewsModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory


class HomeFragment : Fragment() {
    private lateinit var binding:FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding=FragmentHomeBinding.inflate(layoutInflater)

        getData()

        return binding.root
    }

    fun getData(){

        val apiKey="42a9dd92bfc144f6ba69c1e5c06a1bcc"
        val list=ArrayList<NewsModel>()
        val countryName="bg"
         val BASE_URL="https://newsapi.org/v2/"

        val retrofit= Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)



        val apiInterface=RetrofitHelper.getInstance().create(ApiInterface::class.java)

/*
        GlobalScope.launch(Dispatchers.IO) {

            val result =apiInterface.getNews(countryName,10,apiKey)


            for (myData in result.body()!!.articles){

                list.add(myData)
            }
            binding.homeRv.adapter=NewsAdapter(requireContext(),list)

        }
*/

        GlobalScope.launch(Dispatchers.IO){

           // val result =apiInterface.getNews(countryName,10,apiKey)

            val result2=retrofit.getMainNews(countryName,10,apiKey)

            result2.enqueue(object : Callback<MainNewsModel>{
                override fun onResponse(
                    call: Call<MainNewsModel>,
                    response: Response<MainNewsModel>
                ) {
                    val responseBody=response.body()!!

                    for (myData in responseBody.articles){
                        list.add(myData)
                    }
                  //  Toast.makeText(requireContext(), "success", Toast.LENGTH_SHORT).show()
                    binding.homeRv.adapter=NewsAdapter(requireContext(),list)
                }

                override fun onFailure(call: Call<MainNewsModel>, t: Throwable) {
                    Toast.makeText(requireContext(), "Fail : "+t.message, Toast.LENGTH_SHORT).show()
                }
            })


        }


    }

}