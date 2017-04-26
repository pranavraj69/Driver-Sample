package com.example.appu.driversample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class Main3Activity extends AppCompatActivity {

    private Button b;
    private EditText e1;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private EditText e2;
    String vehicle_no;
    String pass_word;
    String new_pass_word;
    Intent intent;
    String new_vehicle_no;
    private String TAG;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference= firebaseDatabase.getReference("loc/Userdetails");
        setContentView(R.layout.activity_main3);
        b=(Button) findViewById(R.id.button);
        e1=(EditText) findViewById(R.id.editText2);
        e2 =(EditText) findViewById(R.id.editText4);
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Details det = dataSnapshot.getValue(Details.class);
                    new_pass_word= det.getPassword();
                    new_vehicle_no=det.getVehiclenum();


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check();
                //startActivity(intent);
            }
        });

    }

    private void check()
    {
        vehicle_no = e1.getText().toString().trim();
        pass_word = e2.getText().toString().trim();
        Log.e(TAG, "check: "+vehicle_no +"ksdjv   "+pass_word);
        if(Objects.equals(vehicle_no, new_vehicle_no) && Objects.equals(pass_word,new_pass_word))
        {
            // Successful Login;
             final Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);

        }
        else
        {
            Toast.makeText(this,"Unsuccessful Login",Toast.LENGTH_SHORT).show();
        }
    }



}
