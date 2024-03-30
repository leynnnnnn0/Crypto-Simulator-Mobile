package com.leynnnnnn.cryptosimulatormobile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class TredingCoinsAdapter extends RecyclerView.Adapter<TredingCoinsAdapter.ViewHolder> {
    List<CoinInfo> coins;
    Context context;

    public TredingCoinsAdapter(List<CoinInfo> coins, Context context) {
        this.coins = coins;
        this.context = context;
    }

    @NonNull
    @Override
    public TredingCoinsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.trending_coin_box, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TredingCoinsAdapter.ViewHolder holder, int position) {
        holder.name.setText(coins.get(position).getName());
        holder.symbol.setText(coins.get(position).getSymbol());
        Glide.with(context).load(coins.get(position).getImage()).into(holder.image);
        holder.price.setText(String.valueOf(coins.get(position).getPrice()));

    }

    @Override
    public int getItemCount() {
        return coins.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, symbol, price;
        ImageView image;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.coinName);
            symbol = itemView.findViewById(R.id.coinSymbol);
            image = itemView.findViewById(R.id.coinLogo);
            price = itemView.findViewById(R.id.coinPrice);
        }
    }
}
