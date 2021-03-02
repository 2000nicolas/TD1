package com.example.td1.presentation.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.td1.R
import com.example.td1.presentation.api.PlanetApi
import com.example.td1.presentation.api.PlanetResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class PlaneteListFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private val adapter = PlaneteAdapter(listOf())
    private val layoutManager = LinearLayoutManager(context)

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_planete_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.planete_recyclerview)
        recyclerView.apply {
            layoutManager = this@PlaneteListFragment.layoutManager
            adapter = this@PlaneteListFragment.adapter
        }
        //recyclerView.layoutManager = layoutManager
        //recyclerView.adapter = adapter

        val retrofit = Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val planetApi = retrofit.create(PlanetApi ::class.java)

        planetApi.getPlaneteList().enqueue(object : Callback<PlanetResponse> {
            override fun onFailure(call: Call<PlanetResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }

            override fun onResponse(
                call: Call<PlanetResponse>,
                response: Response<PlanetResponse>
            ) {
                if(response.isSuccessful && response.body() != null){
                    val planetResponse = response.body()
                    if (planetResponse != null) {
                        adapter.updateList(planetResponse.results)
                    }
                }
            }

        })

        val planetList = arrayListOf<Planete>().apply{
            add(Planete("Terre"))
            add(Planete("Mars"))
            add(Planete("Jupiter"))
            add(Planete("Saturne"))
        }

        adapter.updateList(planetList)
    }


}