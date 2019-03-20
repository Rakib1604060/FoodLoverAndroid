package org.toktakprogramming.foodlover;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ReviewAdapter extends ArrayAdapter<Review> {
    Context mcontext;
     int mresource;

    public ReviewAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Review> objects) {
        super(context, resource, objects);
        this.mcontext = context;
        this.mresource=resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String nameMenu=getItem(position).getName();
        String menuprice=getItem(position).getReviews();
        Menu menu=new Menu(nameMenu,menuprice);
        LayoutInflater inflater=LayoutInflater.from(mcontext);
        convertView = inflater.inflate(mresource,parent,false);
        TextView name_tv=convertView.findViewById(R.id.custormername_tv);
        TextView review_tv=convertView.findViewById(R.id.reviewsText_tv);
        name_tv.setText(nameMenu);
        review_tv.setText(menuprice);
        return convertView;



    }
}
