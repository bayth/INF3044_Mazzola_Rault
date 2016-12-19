package com.example.joseph.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.w3c.dom.Text;

import static android.content.ContentValues.TAG;

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
            holder.name.setText(biers.getJSONObject(position).getString("nameFR"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        holder.name.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                TextView tp = (TextView) v.findViewById(R.id.rv_bier_element_name);
                Log.d(TAG, tp.toString());
                Uri gmmIntentUri = Uri.parse("geo:0,0?q="+tp.getText().toString());
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                v.getContext().startActivity(mapIntent);
            }
        });
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