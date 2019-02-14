package com.azhardevelop.exle.com.tugas11februari;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShowBarangFragment extends Fragment {


    public ShowBarangFragment() {
        // Required empty public constructor
    }

    private ArrayList<HashMap<String, String>> dataBarang;
    private String url;

    TextView txtBnama, txtStock, txtBjson;
    RecyclerView lvBarang;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_show_barang, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        lvBarang = view.findViewById(R.id.lv_barang);
        lvBarang.setLayoutManager(new LinearLayoutManager(getActivity()));

        dataBarang = new ArrayList<HashMap<String, String>>();
        url = "http://192.168.71.199/SMPIDN/webdatabase/apitampilbarang.php";
        showDataBarang();
    }

    private void showDataBarang() {
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setTitle("Loading...");
        progressDialog.setMessage("Please Wait...");
        progressDialog.show();

        JsonObjectRequest myRequest = new JsonObjectRequest(Request.Method.GET, url, null
                , new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                progressDialog.dismiss();

                try {
                    JSONArray jsonArray = response.getJSONArray("barang");
                    for (int a = 0; a < jsonArray.length(); a++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(a);
                        HashMap<String, String> rowData = new HashMap<String, String>();
                        rowData.put("nama", jsonObject.getString("nama_barang"));
                        rowData.put("stock", jsonObject.getString("stock_barang"));
                        dataBarang.add(rowData);
                    }
                    AdapterBarang adapterBarang = new AdapterBarang(getActivity(), dataBarang);
                    lvBarang.setAdapter(adapterBarang);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Toast.makeText(getActivity(), response.toString(), Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue myRequestQueue = Volley.newRequestQueue(getActivity());
        myRequestQueue.add(myRequest);

    }
}
