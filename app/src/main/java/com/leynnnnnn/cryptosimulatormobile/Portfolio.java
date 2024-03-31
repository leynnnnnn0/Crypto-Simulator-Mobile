package com.leynnnnnn.cryptosimulatormobile;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class Portfolio extends Fragment {
    EditText exploreSearchBar;
    RecyclerView recyclerView;
    AllCoinsAdapter adapter;
    List<AllCoins> coins;
    public interface GetApiResponse {
        @GET("coins/markets?vs_currency=usd&order=market_cap_desc&per_page=100&page=1&sparkline=false&locale=en")
        Call<List<AllCoins>> getAllCoins();

        @GET("search")
        Call<AllCoins> getCoinDetails(@Query("query") String query);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_portfolio, container, false);
        exploreSearchBar = rootView.findViewById(R.id.exloreSearchBar);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.coingecko.com/api/v3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GetApiResponse apiResponse = retrofit.create(GetApiResponse.class);
        apiResponse.getAllCoins().enqueue(new Callback<List<AllCoins>>() {
            @Override
            public void onResponse(Call<List<AllCoins>> call, Response<List<AllCoins>> response) {
                coins = response.body();
                recyclerView = rootView.findViewById(R.id.coinsRecyclerView);
                adapter = new AllCoinsAdapter(requireContext(), coins);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
                recyclerView.setHasFixedSize(true);



            }

            @Override
            public void onFailure(Call<List<AllCoins>> call, Throwable throwable) {

            }
        });



        return rootView;
    }
}