package com.leynnnnnn.cryptosimulatormobile;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.function.LongFunction;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class Home extends Fragment {
    // Interface for the api request method
    public interface ApiRequest {
        @GET("search/trending")
        Call<CoinApiResponse> getTrendingCoins();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        // Api request using retrofit
        // Documentation  https://square.github.io/retrofit/
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.coingecko.com/api/v3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Log.d("message", "test only 1");
        ApiRequest request = retrofit.create(ApiRequest.class);
        request.getTrendingCoins().enqueue(new Callback<CoinApiResponse>() {
            @Override
            public void onResponse(Call<CoinApiResponse> call, Response<CoinApiResponse> response) {
                // Telling the ide that I'm sure that the response is not empty
                assert response.body() != null;
                // Getting the response and storing in a list
                List<Coin> coins = response.body().getCoins();
                // List for the formatted coins
                List<CoinInfo> coinsList = new ArrayList<>();
                // Getting the recycler view from the layout
                for(Coin coin : coins) {
                    Item item = coin.getItem();
                    Data data = item.getData();
                    // Creating an object and putting it in the list
                    coinsList.add(new CoinInfo(item.getName(), item.getSymbol(), item.getSmall(), data.getPrice()));
                }
                // Creating an instance of the recycler view and adapter
                RecyclerView recyclerView = rootView.findViewById(R.id.trendingCoins);
                TredingCoinsAdapter adapter = new TredingCoinsAdapter(coinsList, requireContext());
                // Setting the recycler view
                recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
                recyclerView.setAdapter(adapter);
            }
            @Override
            public void onFailure(Call<CoinApiResponse> call, Throwable throwable) {

            }
        });
        return rootView;
    }
}