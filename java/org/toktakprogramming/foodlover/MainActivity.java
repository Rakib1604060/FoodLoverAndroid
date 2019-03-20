package org.toktakprogramming.foodlover;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    EditText email_t,pass_t;
    Button loginButton;
    String email,password;
    ProgressBar progressBar;
    TextView createNewAccoutn;
    String url="http://sharmin.ourcuet.com/query.php";
    Map<String , String > loginmap=new HashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Check_LogedIn();


        createNewAccoutn=findViewById(R.id.create_account_tv);

        email_t=findViewById(R.id.email);
        pass_t=findViewById(R.id.password);
        progressBar=findViewById(R.id.progressbarr);
        progressBar.setVisibility(View.GONE);

        loginButton=findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(email_t.getText().toString().equals("")||pass_t.getText().equals("")){

                    Toast.makeText(getApplicationContext(), "Insert all the information", Toast.LENGTH_SHORT).show();
                }
                else {
                    email = email_t.getText().toString();
                    password=pass_t.getText().toString();
                    String sqlQuery="select * From registration_table where email like'"+email+"'and password like '"+password+"';";
                    loginmap.put("query",sqlQuery);


                    PushOrParse.getDataFromServer(url,loginmap,getApplicationContext());
                    progressBar.setVisibility(View.VISIBLE);
                    sendData();




                }


            }
        });
     createNewAccoutn.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             SharedPreferences sharedPreferences=getSharedPreferences("foodlover",MODE_PRIVATE);
             SharedPreferences.Editor editor=sharedPreferences.edit();
             editor.putBoolean("login",true);
             editor.apply();

             Intent intent=new Intent(MainActivity.this,Registration.class);
             startActivity(intent);
             finish();
         }
     });



    }



    private void sendData(){

        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                JSONArray jsonArray=PushOrParse.deliverData();




                   if(jsonArray.length()<1) {
                       Toast.makeText(MainActivity.this,"User Does Not Exist", Toast.LENGTH_LONG).show();

                    }else{
                        SharedPreferences sharedPreferences=getSharedPreferences("foodlover",MODE_PRIVATE);
                        SharedPreferences.Editor editor=sharedPreferences.edit();
                        editor.putBoolean("login",true);
                        editor.apply();

                        Intent intent =new Intent(MainActivity.this,Resturent.class);
                        startActivity(intent);

                    }

                progressBar.setVisibility(View.GONE);

            }
        },4000);


    }
  private void Check_LogedIn(){
      SharedPreferences sharedPreferences=getSharedPreferences("foodlover",MODE_PRIVATE);
      boolean isLogin=sharedPreferences.getBoolean("login",false);

      if(isLogin){
          Intent intent=new Intent(MainActivity.this,Resturent.class);
          startActivity(intent);
          finish();
      }



  }

}