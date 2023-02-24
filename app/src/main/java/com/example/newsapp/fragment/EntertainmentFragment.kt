package com.example.newsapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.newsapp.R
import com.example.newsapp.adapter.NewsAdapter
import com.example.newsapp.api.ApiInterface
import com.example.newsapp.databinding.FragmentEntertainmentBinding
import com.example.newsapp.model.MainNewsModel
import com.example.newsapp.model.NewsModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class EntertainmentFragment : Fragment() {

    private lateinit var binding:FragmentEntertainmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentEntertainmentBinding.inflate(layoutInflater)


    return binding.root
    }
    fun getData(){

        val apiKey="42a9dd92bfc144f6ba69c1e5c06a1bcc"
        val list=ArrayList<NewsModel>()
        val countryName="bg"
        val category="entertainment"
        val BASE_URL="https://newsapi.org/v2/"

        val retrofit= Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)



        GlobalScope.launch(Dispatchers.IO){

            // val result =apiInterface.getNews(countryName,10,apiKey)

            val result=retrofit.getMainCategoryNews(countryName,category,10,apiKey)


            result.enqueue(object : Callback<MainNewsModel?> {
                override fun onResponse(
                    call: Call<MainNewsModel?>,
                    response: Response<MainNewsModel?>
                ) {

                    val  body=response.body()

                    for (myData in body!!.articles ){

                        list.add(myData)
                    }

                    binding.entertainmentRv.adapter= NewsAdapter(requireContext(),list)


                }

                override fun onFailure(call: Call<MainNewsModel?>, t: Throwable) {

                    Toast.makeText(requireContext(), "fail : "+t.message, Toast.LENGTH_SHORT).show()
                }
            })

        }


    }

}