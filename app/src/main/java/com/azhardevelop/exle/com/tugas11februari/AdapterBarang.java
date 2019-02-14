package com.azhardevelop.exle.com.tugas11februari;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class AdapterBarang extends RecyclerView.Adapter<AdapterBarang.AdapterViewHolder> {

    private ArrayList<HashMap<String, String>> listBarang;
    private Context context;

    public AdapterBarang(FragmentActivity activity,
                         ArrayList<HashMap<String, String>> dataBarang){
        this.context = activity;
        this.listBarang = dataBarang;
    }

    @NonNull
    @Override
    public AdapterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_barang, viewGroup,
                false);
        AdapterViewHolder adapterViewHolder = new AdapterViewHolder(view);
        return new AdapterViewHolder(view);

    }



    @Override
    public void onBindViewHolder(@NonNull AdapterBarang.AdapterViewHolder adapterViewHolder, int i) {
        adapterViewHolder.txtNamaBarang.setText(listBarang.get(i).get("nama"));
        adapterViewHolder.txtJumlahStok.setText(listBarang.get(i).get("stock"));
    }

    @Override
    public int getItemCount() {
        return listBarang.size();
    }

    public class AdapterViewHolder extends RecyclerView.ViewHolder {

        TextView txtNamaBarang, txtJumlahStok;
        ImageView imgBarang;
        public AdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNamaBarang = itemView.findViewById(R.id.item_namaBarang);
            txtJumlahStok = itemView.findViewById(R.id.item_jumlahStok);
            imgBarang = itemView.findViewById(R.id.item_gambarBarang);
        }
    }


}
