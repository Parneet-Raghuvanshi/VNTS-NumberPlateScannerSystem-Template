package com.example.vnts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ViewReport extends AppCompatActivity {

    TextView product_id,report_unread,report_read;
    String pid;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_report);

        report_unread = findViewById(R.id.report_unread);
        report_unread.setVisibility(View.GONE);
        report_read = findViewById(R.id.report_read);
        report_read.setVisibility(View.GONE);
        product_id = findViewById(R.id.product_id);
        progressBar = findViewById(R.id.progress_bar);

        pid = getIntent().getStringExtra("pid");

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Products").child("Uread");
        databaseReference.keepSynced(true);
        databaseReference.orderByChild("pid").equalTo(pid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue()!=null){
                    report_unread.setVisibility(View.VISIBLE);
                    report_read.setVisibility(View.GONE);
                    progressBar.setVisibility(View.GONE);

                    updateData(pid);
                }
                else {
                    report_read.setVisibility(View.VISIBLE);
                    report_unread.setVisibility(View.GONE);
                    progressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void updateData(String pid) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Products");
        databaseReference.keepSynced(true);
        databaseReference.child("Read").child(pid).child("pid").setValue(pid);
        databaseReference.child("Uread").child(pid).child("pid").setValue(null);
    }
}