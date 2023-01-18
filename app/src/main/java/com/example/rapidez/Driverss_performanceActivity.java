package com.example.rapidez;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Driverss_performanceActivity extends AppCompatActivity {
    FirebaseDatabase firebaseDatabase;
    DatabaseReference speedref;
    private TextView speed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driverss_performance);
        DatabaseReference reference= FirebaseDatabase.getInstance().getReference().child("newRequest");
        firebaseDatabase = FirebaseDatabase.getInstance();
        speed = findViewById(R.id.speed);
        speedref=firebaseDatabase.getReference("A");
        getdata();
    }
    private void getdata() {
        // calling add value event listener method
        // for getting the values from database.
        speedref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // this method is call to get the realtime
                // updates in the data.
                // this method is called when the data is
                // changed in our Firebase console.
                // below line is for getting the data from
                // snapshot of our database.
                float value = snapshot.getValue(float.class);
                String value1=Float.toString(value);

                // after getting the value we are setting
                // our value to our text view in below line.
                speed.setText(value1);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // calling on cancelled method when we receive
                // any error or we are not able to get the data.
                Toast.makeText(Driverss_performanceActivity.this, "Fail to get data.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}