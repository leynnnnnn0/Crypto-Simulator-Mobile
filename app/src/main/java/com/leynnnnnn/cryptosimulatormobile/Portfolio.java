package com.leynnnnnn.cryptosimulatormobile;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
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
    String query;
    public interface GetApiResponse {
        @GET("coins/markets?vs_currency=usd&order=market_cap_desc&per_page=100&page=1&sparkline=false&locale=en")
        Call<List<AllCoins>> getAllCoins();

        @GET("search")
        Call<SearchCoinResponse> getCoinDetails(@Query("query") String query);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_portfolio, container, false);
        exploreSearchBar = rootView.findViewById(R.id.exloreSearchBar);
        recyclerView = rootView.findViewById(R.id.coinsRecyclerView);
        TextView errorMessage = rootView.findViewById(R.id.fetchingDataErrorText);
        Button searchButton = rootView.findViewById(R.id.searchButton);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.coingecko.com/api/v3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GetApiResponse apiResponse = retrofit.create(GetApiResponse.class);

        searchButton.setOnClickListener(v -> {
            query = exploreSearchBar.getText().toString();
            Log.d("message", query);
            apiResponse.getCoinDetails(query).enqueue(new Callback<SearchCoinResponse>() {
                @Override
                public void onResponse(Call<SearchCoinResponse> call, Response<SearchCoinResponse> response) {
                    if(response.isSuccessful()) {
                        List<CoinsSearched> coins = response.body().getCoins();
                        SearchedCoinsAdapter adapter1 = new SearchedCoinsAdapter(requireContext(), coins);
                        recyclerView.setAdapter(adapter1);
                        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
                        errorMessage.setVisibility(View.INVISIBLE);
                    }


                }

                @Override
                public void onFailure(Call<SearchCoinResponse> call, Throwable throwable) {

                }
            });
        });
        apiResponse.getAllCoins().enqueue(new Callback<List<AllCoins>>() {
            @Override
            public void onResponse(Call<List<AllCoins>> call, Response<List<AllCoins>> response) {
                if(response.isSuccessful()) {
                    coins = response.body();
                    Log.d("message", exploreSearchBar.getText().toString());
                    if(!coins.isEmpty() && exploreSearchBar.getText().toString().isEmpty()) {
                        adapter = new AllCoinsAdapter(requireContext(), coins);
                        recyclerView.setAdapter(adapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
                        recyclerView.setHasFixedSize(true);
                        errorMessage.setVisibility(View.INVISIBLE);
                    }
                }

            }

            @Override
            public void onFailure(Call<List<AllCoins>> call, Throwable throwable) {

            }
        });



        return rootView;
    }
}