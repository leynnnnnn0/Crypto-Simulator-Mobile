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

import org.w3c.dom.Text;

import java.util.List;

public class SearchedCoinsAdapter extends RecyclerView.Adapter<SearchedCoinsAdapter.ViewHolder> {
    Context context;
    List<CoinsSearched> coins;

    public SearchedCoinsAdapter(Context context, List<CoinsSearched> coins) {
        this.context = context;
        this.coins = coins;
    }

    @NonNull
    @Override
    public SearchedCoinsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.trending_coin_box, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchedCoinsAdapter.ViewHolder holder, int position) {
        holder.symbol.setText(coins.get(position).getSymbol());
        holder.name.setText(coins.get(position).getName());
        Glide.with(context).load(coins.get(position).getThumb()).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return coins.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView name, symbol;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.coinLogo);
            name = itemView.findViewById(R.id.coinName);
            symbol = itemView.findViewById(R.id.coinSymbol);

        }
    }
}
