package org.toktakprogramming.foodlover;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Fragment_review extends Fragment {
    String got_tableid;
    ListView reviewList;
   ArrayList<Review> reviews=new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootview= inflater.inflate(R.layout.fragment_reviews,container,false);
        got_tableid=getArguments().getString("resturent_id");
         reviewList=rootview.findViewById(R.id.listview_lvReview);
         LoadData();




        return rootview;
    }


    private  void LoadData(){
        String Url="http://sharmin.ourcuet.com/query.php";
        String query="select * from review_table where resturent_id='"+got_tableid+"';";
        Map<String ,String> map=new HashMap<>();
        map.put("query",query);

        CustomJsonArrayRequest customJsonArrayRequest=new CustomJsonArrayRequest(Request.Method.POST, Url, map, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for(int i=0;i<response.length();i++){
                    try {
                        JSONObject jsonObject=response.getJSONObject(i);
                        Review review=new Review(jsonObject.getString("reviewer_name"),jsonObject.getString("review_text"));
                        reviews.add(review);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
                ReviewAdapter reviewAdapter=new ReviewAdapter(getActivity(),R.layout.list_view_review,reviews);
                reviewList.setAdapter(reviewAdapter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "Some thing Went Wrong", Toast.LENGTH_SHORT).show();


            }
        });
        MySingleton.getmInstance(getActivity()).AddToRequestQueue(customJsonArrayRequest);
    }
}