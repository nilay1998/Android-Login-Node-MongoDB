package com.example.androidlogin;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.androidlogin.Retrofit.NetworkClient;
import com.example.androidlogin.Retrofit.RequestService;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;

import javax.net.ssl.HttpsURLConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



public class MainActivity extends AppCompatActivity {

    private View login_view;
    private View register_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        login_view=getLayoutInflater().inflate(R.layout.activity_main,null);
        register_view=getLayoutInflater().inflate(R.layout.register,null);
        setContentView(login_view);

        //Obtain an instance of Retrofit by calling the static method.
        Retrofit retrofit = NetworkClient.getRetrofitClient();

        final RequestService requestService=retrofit.create(RequestService.class);

        final EditText edittext_email = (EditText) findViewById(R.id.email);
        final EditText edittext_password = (EditText) findViewById(R.id.password);

        Button button_login = (Button) findViewById(R.id.login);
        button_login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Do something in response to button click
                Call call=requestService.loginUser(edittext_email.getText().toString(),edittext_password.getText().toString());
                //Call call=requestService.loginUser("nilaygupta1998@gmail.com","nilay");
                call.enqueue(new Callback() {
                    @Override
                    public void onResponse(Call call, Response response) {
                        Toast.makeText(MainActivity.this,""+response.body().toString(),Toast.LENGTH_SHORT).show();
                        Log.d("Success",response.body().toString());
                    }

                    @Override
                    public void onFailure(Call call, Throwable t) {
                        Toast.makeText(MainActivity.this,t.getMessage(),Toast.LENGTH_SHORT).show();
                        Log.e("Error",t.getMessage());
                    }
                });
            }
        });

        Button button_signup = (Button) findViewById(R.id.signup);
        button_signup.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Do something in response to button click
                //setContentView(R.layout.register);

                setContentView(register_view);

                final EditText edittext_name = (EditText) findViewById(R.id.name);
                final EditText edittext_email = (EditText) findViewById(R.id.remail);
                final EditText edittext_password = (EditText) findViewById(R.id.rpassword);
                final EditText edittext_phone = (EditText) findViewById(R.id.phone);

                Button button_register=(Button) findViewById(R.id.register);
                button_register.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        setContentView(login_view);
                        Call call=requestService.createUser(edittext_name.getText().toString(),edittext_email.getText().toString(),edittext_password.getText().toString(),edittext_phone.getText().toString());
                        call.enqueue(new Callback() {
                            @Override
                            public void onResponse(Call call, Response response) {
                                Toast.makeText(MainActivity.this,""+response.body().toString(),Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onFailure(Call call, Throwable t) {
                                Toast.makeText(MainActivity.this,t.getMessage(),Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
            }
        });

        Button test=(Button) findViewById(R.id.test);
        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call call=requestService.requestGet();
                call.enqueue(new Callback() {
                    @Override
                    public void onResponse(Call call, Response response) {
                        Toast.makeText(MainActivity.this,"Success",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call call, Throwable t) {
                        Toast.makeText(MainActivity.this,t.getMessage(),Toast.LENGTH_SHORT).show();
                        Log.d("Error",t.getMessage());
                    }
                });
            }
        });
    }

}