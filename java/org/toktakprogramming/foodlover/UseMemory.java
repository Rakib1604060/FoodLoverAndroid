package org.toktakprogramming.foodlover;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.ArrayList;

public  class UseMemory {

    public static  class  Save{
        Context context;

        public Save(Context context) {
            this.context = context;

        }

        public void saveArray(String data,String price){
            SharedPreferences preferences=context.getSharedPreferences("cart",Context.MODE_PRIVATE);
            SharedPreferences.Editor editor=preferences.edit();
            int sizeofArray=preferences.getInt("size",0);

            editor.putString("Name"+sizeofArray,data);
            editor.putString("Price"+sizeofArray,price);
            sizeofArray=sizeofArray+1;
            editor.putInt("size",sizeofArray);
            editor.apply();

        }
        public ArrayList<Menu> getArray(){
            ArrayList<Menu> Array=new ArrayList<Menu>();
            SharedPreferences preferences=context.getSharedPreferences("cart",Context.MODE_PRIVATE);

            int size=preferences.getInt("size",0);
            for(int i=0;i<size;i++){
                String data=preferences.getString("Name"+i,null);
                String price=preferences.getString("Price"+i,null);

                Menu temp=new Menu(data,price);
                Array.add(temp);


            }
            return  Array;
        }

        public void deleteData(){
            SharedPreferences preferences=context.getSharedPreferences("cart",Context.MODE_PRIVATE);
            SharedPreferences.Editor editor=preferences.edit();
            editor.clear();
            editor.apply();
        }

    }









}
