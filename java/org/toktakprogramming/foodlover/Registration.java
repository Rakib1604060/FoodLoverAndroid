package org.toktakprogramming.foodlover;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Registration extends AppCompatActivity {
   EditText et_email,et_password,et_passwrod2;
   Button submitButton;
   ProgressBar progressBar;
   AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        et_email=findViewById(R.id.email_et);
        et_password=findViewById(R.id.password_et);
        et_passwrod2=findViewById(R.id.password_et2);
        submitButton=findViewById(R.id.bt_submit);
        progressBar=findViewById(R.id.pb_progressbar);
        builder=new AlertDialog.Builder(this);
        builder.setTitle("Registration Information");
        //submit button clicked
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(et_email.getText().equals("")||et_password.getText().equals("")||et_passwrod2.getText().equals(""))
                {
                    Toast.makeText(Registration.this, "Please Fill up every Input", Toast.LENGTH_SHORT).show();
                }
                else if(CheckPassword(et_password.getText().toString(),et_passwrod2.getText().toString())){
                    Toast.makeText(Registration.this, "Password Mismatch", Toast.LENGTH_SHORT).show();
                }
                else{
                    progressBar.setVisibility(View.VISIBLE);
                    MakePackage();
                    LoadData();


                }
            }
        });


    }
    private boolean CheckPassword(String a, String b){
        if(a.equals(b)){
            return false;
        }
        else{
            return true;
        }
    }

    private void MakePackage(){
        String email, password;
        email=et_email.getText().toString();
        password=et_password.getText().toString();
        sendData(email,password);
    }
    private void sendData(String email,String password){
      String url="http://sharmin.ourcuet.com/query.php";
      String query="Insert into registration_table (email,password)values('"+email+"','"+password+"');";
      Map<String,String>map=new HashMap<>();
      map.put("query",query);
      PushOrParse.getDataFromServer(url,map,Registration.this);


    }
    private  void LoadData(){

        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                JSONArray jsonArray=PushOrParse.deliverData();
                builder.setCancelable(false);
                Toast.makeText(Registration.this, jsonArray.toString(), Toast.LENGTH_SHORT).show();
                if(jsonArray.toString().equals("[]")){

                    Save_login();
                    progressBar.setVisibility(View.GONE);
                    builder.setMessage(" Your Registration Successfull");
                    builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                         Intent intent =new Intent(Registration.this,Resturent.class);
                         startActivity(intent);
                        }
                    });
                }
                else{
                    builder.setMessage("Sorry !! Something went Wrong!!");
                    builder.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                }
                AlertDialog alertDialog=builder.create();
                alertDialog.show();

            }
        },3000);

    }

  private void Save_login(){
      SharedPreferences sharedPreferences=getSharedPreferences("foodlover",MODE_PRIVATE);
      SharedPreferences.Editor editor = sharedPreferences.edit();
      editor.putBoolean("login",true);
      editor.apply();

  }
}
