package org.toktakprogramming.foodlover;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
import java.util.Map;


public class Fragment_item extends Fragment {
    ArrayList<Menu>menus=new ArrayList<Menu>();
    String resturent_id;
    ListView listView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         View rootView=inflater.inflate(R.layout.fragment_items,container,false);
         listView=rootView.findViewById(R.id.listview);
         resturent_id = getArguments().getString("resturent_id");
         if(menus.size()==0){
             FirstRequest();
         }
         else
             {
                 ListViewAdapter listViewAdapter=new ListViewAdapter(getActivity(),R.layout.list_tamplate,menus);
                 listView.setAdapter(listViewAdapter);
        }



         listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
             @Override
             public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                 Menu temp =(Menu)parent.getItemAtPosition(position);
                   String data=temp.getMenu_name();
                   String price=temp.getMenu_price();
                   UseMemory.Save save=new UseMemory.Save(getActivity());
                   save.saveArray(data,price);
                  Toast.makeText(getActivity(), "New Item Added to Cart!!", Toast.LENGTH_SHORT).show();


             }
         });


        return rootView;

    }
    private void FirstRequest(){
        String url="http://sharmin.ourcuet.com/query.php";
        String query="select menu_name,menu_price from menu_table where resturent_id='"+resturent_id+"';";
        Map<String ,String> map=new HashMap<>();
        map.put("query",query);

            CustomJsonArrayRequest customJsonArrayRequest=new CustomJsonArrayRequest(Request.Method.POST, url, map, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                  for(int i=0;i<response.length();i++){
                        try {
                            JSONObject jsonObject=response.getJSONObject(i);
                            Menu menu=new Menu(jsonObject.getString("menu_name"),jsonObject.getString("menu_price"));
                            menus.add(menu);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                    ListViewAdapter listViewAdapter=new ListViewAdapter(getActivity(),R.layout.list_tamplate,menus);
                    listView.setAdapter(listViewAdapter);

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {


                }
            });
            MySingleton.getmInstance(getActivity()).AddToRequestQueue(customJsonArrayRequest);
        }



}
