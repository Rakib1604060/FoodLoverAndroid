package org.toktakprogramming.foodlover;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Resturent extends AppCompatActivity implements ResturentAdapter.OnNoteListener {
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<ResturentItem> resturentItemArrayList=new ArrayList<>();
    String url="http://sharmin.ourcuet.com/query.php";
    String query="Select * from resturent_table;";
    Map<String ,String> pack=new HashMap<>();
    ProgressBar progressBar;
    ResturentAdapter.OnNoteListener onNoteListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resturent);
        recyclerView=findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        progressBar=findViewById(R.id.progressbar);
        progressBar.setVisibility(View.GONE);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        pack.put("query",query);
        onNoteListener=this;
        getData();


    }
    private  void getData(){

        CustomJsonArrayRequest customJsonArrayRequest=new CustomJsonArrayRequest(Request.Method.POST, url, pack, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {


                for(int i=0;i<response.length();i++){
                    try {
                        JSONObject jsonObject=response.getJSONObject(i);

                        ResturentItem item= new ResturentItem(jsonObject.getString("name"),jsonObject.getString("location"),
                                jsonObject.getString("menuitem"),jsonObject.getString("tablenumber"),jsonObject.getString("id"),jsonObject.getString("information"));

                        resturentItemArrayList.add(item);


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }

                adapter=new ResturentAdapter(resturentItemArrayList,onNoteListener);
                recyclerView.setAdapter(adapter);


                progressBar.setVisibility(View.GONE);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(Resturent.this, error.toString(), Toast.LENGTH_LONG).show();
                Log.e("Current Error",error.toString());
            }
        });
        MySingleton.getmInstance(this).AddToRequestQueue(customJsonArrayRequest);
    }




    @Override
    public void onNoteClick(int position) {
        String name=resturentItemArrayList.get(position).getResturentName();
        String id=resturentItemArrayList.get(position).getId();
        String information=resturentItemArrayList.get(position).getInfo();
        Intent intent =new Intent(Resturent.this,Details.class);
        intent.putExtra("ResturentName",name);
        intent.putExtra("resturent_id",id);
        intent.putExtra("information",information);
        startActivity(intent);
    }
}

