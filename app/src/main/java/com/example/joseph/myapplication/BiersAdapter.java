package com.example.joseph.myapplication;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;

/**
 * Created by Joseph on 15/11/2016.
 */

public class BiersAdapter extends RecyclerView.Adapter<BiersAdapter.BierHolder> {

    private JSONArray biers;

    public BiersAdapter(JSONArray jsonArray){
        this.biers = jsonArray;
    }

    @Override
    public BierHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_bier_element, null, false);
        return new BierHolder(v);
    }

    @Override
    public void onBindViewHolder(BierHolder holder, int position) {
        try {
            holder.name.setText(biers.getString(position));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        if(biers==null) {
            return 0;
        }else{
            return biers.length();
        }
    }

    class BierHolder extends RecyclerView.ViewHolder{

        public TextView name;

        public BierHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.rv_bier_element_name);
        }
    }

    public void setNewBiere(JSONArray jsonArray){
        biers = jsonArray;
        notifyDataSetChanged();
    }
}