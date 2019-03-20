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
import java.util.List;
import java.util.zip.Inflater;

public class ListViewAdapter extends ArrayAdapter<Menu> {
    Context mcontext;
     int mresource;

    public ListViewAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Menu> objects) {
        super(context, resource, objects);
        this.mcontext = context;
        this.mresource=resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String nameMenu=getItem(position).getMenu_name();
        String menuprice=getItem(position).getMenu_price();
        Menu menu=new Menu(nameMenu,menuprice);
        LayoutInflater inflater=LayoutInflater.from(mcontext);
        convertView = inflater.inflate(mresource,parent,false);
        TextView name_tv=convertView.findViewById(R.id.tv_menuName);
        TextView price_tv=convertView.findViewById(R.id.tv_menuPrice);
        name_tv.setText(nameMenu);
        price_tv.setText(menuprice+"tk");
        return convertView;



    }
}
