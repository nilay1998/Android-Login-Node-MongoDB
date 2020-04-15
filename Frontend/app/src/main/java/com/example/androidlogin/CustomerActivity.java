package com.example.androidlogin;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CustomerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);
        final Sessions session=new Sessions(getApplicationContext());
        final Intent intent = new Intent(this, MainActivity.class);
        Bundle extras = getIntent().getExtras();
        final String userName;
        final String useremail;
        final long userphone;
        TextView name=(TextView)findViewById(R.id.name);
        TextView email=(TextView)findViewById(R.id.email);
        TextView phone=(TextView)findViewById(R.id.phone);

            userName = extras.getString("name");
            useremail=extras.getString("email");
            userphone=extras.getLong("phone");
            name.setText(userName);
            email.setText(useremail);
            phone.setText(String.valueOf(userphone));

        //Log.e("NAME",String.valueOf(userphone));

        Button button=(Button)findViewById(R.id.logout);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                session.killSession();
                session.editor.putString("name",userName);
                session.editor.putString("email",useremail);
                session.editor.putLong("phone",userphone);
                session.editor.commit();

                Log.e("name",""+session.pref.getString("name",""));
                Log.e("name",""+session.pref.getString("email",""));
                Log.e("name",""+session.pref.getLong("phone",0));

                //Log.e("check CUSTOMER",""+sharedPreferences.getBoolean(MainActivity.isLoggedIn,false));
//                // Closing all the Activities
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//
//                // Add new Flag to start new Activity
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
    }
}
