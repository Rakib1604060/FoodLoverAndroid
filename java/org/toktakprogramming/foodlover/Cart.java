package org.toktakprogramming.foodlover;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Cart extends AppCompatActivity {
 ListView listView;
    int price ;
 Boolean isEmpty=false;
 TextView textView,no_item;
 Button button;
 View view;
 AlertDialog.Builder builder;
 ArrayList<Menu>myArray=new ArrayList<Menu>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        listView=findViewById(R.id.listView_cart);
        textView=findViewById(R.id.price_tv);
        no_item=findViewById(R.id.no_item_found);
        button=findViewById(R.id.button);
        UseMemory.Save save=new UseMemory.Save(this);
        myArray=save.getArray();
        ListViewAdapter listViewAdapter=new ListViewAdapter(Cart.this,R.layout.list_tamplate,myArray);
        listView.setAdapter(listViewAdapter);
        calculatePrice();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Cart.this,Payment.class);
                startActivity(intent);
            }
        });
        builder=new AlertDialog.Builder(this);


        if(myArray.size()<1){
            textView.setText("Price : 0");
            no_item.setVisibility(View.VISIBLE);
            isEmpty=true;

        }





    }


    private  void calculatePrice(){
      if(!isEmpty){
          for(int i=0;i<myArray.size();i++){

              price+= Integer.parseInt(myArray.get(i).getMenu_price());

          }
          textView.setText("Total Price:"+price+"tk");
      }

    }

    @Override
    public void onBackPressed() {

        if(isEmpty){
            super.onBackPressed();
        }
        else{
            builder.setTitle("Attention!!!");

            builder.setMessage("Do You Want TO pay NOw? or clear ");

            builder.setPositiveButton("cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent=new Intent(Cart.this,Resturent.class);
                    startActivity(intent);
                }
            });

            builder.setNegativeButton("Delete Now", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    UseMemory.Save save=new UseMemory.Save(Cart.this);
                    save.deleteData();
                    Toast.makeText(Cart.this, "Cart Cleared!!", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(Cart.this,Resturent.class);
                    startActivity(intent);
                }
            });
            AlertDialog alertDialog=builder.create();
            alertDialog.show();

        }

    }
}
