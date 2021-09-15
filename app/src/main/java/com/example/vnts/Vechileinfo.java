package com.example.vnts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.util.Calendar;

public class Vechileinfo extends AppCompatActivity {

    String vno,finvno;
    TextView police_record_txt,vehicle_model,vehicle_age,reg_auth,reg_date,v_class,fuel_type;
    TextView vno_txt,owner_name;
    LinearLayout police_records1,police_records2,extra_detail;
    ImageView back_btn;
    ProgressBar progressBar;
    TextView loadingtxt;
    LinearLayout no_data_found;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vechileinfo);

        vno = getIntent().getStringExtra("vno");
        finvno = vno.replaceAll("\\s","");
        Log.d("------------","-----"+finvno);

        police_record_txt = findViewById(R.id.police_record_txt);
        vehicle_model = findViewById(R.id.vehicle_model);
        vehicle_age = findViewById(R.id.vehicle_age);
        reg_auth = findViewById(R.id.reg_auth);
        reg_date = findViewById(R.id.reg_date);
        v_class = findViewById(R.id.v_class);
        fuel_type = findViewById(R.id.fuel_type);
        vno_txt = findViewById(R.id.vno_txt);
        owner_name = findViewById(R.id.owner_name);
        back_btn = findViewById(R.id.back_btn);

        progressBar = findViewById(R.id.progress_bar);
        loadingtxt = findViewById(R.id.loading_txt);
        no_data_found = findViewById(R.id.no_data_found);

        police_records1 = findViewById(R.id.police_records1);
        police_records2 = findViewById(R.id.police_records2);
        extra_detail = findViewById(R.id.extra_detail);

        police_records1.setVisibility(View.GONE);
        police_records2.setVisibility(View.GONE);
        extra_detail.setVisibility(View.GONE);
        no_data_found.setVisibility(View.GONE);
        owner_name.setVisibility(View.INVISIBLE);
        vno_txt.setVisibility(View.INVISIBLE);
        loadingtxt.setVisibility(View.GONE);

        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Vehicles");
        databaseReference.keepSynced(true);
        databaseReference.orderByChild("vno").equalTo(finvno).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue()!=null){
                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                        Vehicle_md vehicle_md = dataSnapshot1.getValue(Vehicle_md.class);

                        owner_name.setText(vehicle_md.getOwnername());
                        vno_txt.setText(vehicle_md.getVno());
                        fuel_type.setText(vehicle_md.getFueltype());
                        v_class.setText(vehicle_md.getVclass());
                        reg_date.setText(vehicle_md.getRegistrationdate());
                        reg_auth.setText(vehicle_md.getRegisteringauth());
                        vehicle_age.setText(vehicle_md.getVechileage());
                        vehicle_model.setText(vehicle_md.getVechilemodel());

                        extra_detail.setVisibility(View.VISIBLE);
                        progressBar.setVisibility(View.GONE);
                        owner_name.setVisibility(View.VISIBLE);
                        vno_txt.setVisibility(View.VISIBLE);
                        loadingtxt.setVisibility(View.GONE);

                        if (vehicle_md.getVhistory().equals("NONE")){
                            police_records1.setVisibility(View.VISIBLE);
                            police_records2.setVisibility(View.GONE);
                            police_record_txt.setText(vehicle_md.getVhistory());
                        }
                        else {
                            police_records1.setVisibility(View.GONE);
                            police_records2.setVisibility(View.VISIBLE);
                            police_record_txt.setText(vehicle_md.getVhistory());
                        }

                        Calendar calendar = Calendar.getInstance();
                        String currentdate = DateFormat.getDateInstance().format(calendar.getTime());

                        DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference().child("History").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(vehicle_md.getVno());
                        databaseReference1.keepSynced(true);
                        databaseReference1.child("uid").setValue(FirebaseAuth.getInstance().getCurrentUser().getUid());
                        databaseReference1.child("vno").setValue(vehicle_md.getVno());
                        databaseReference1.child("lstime").setValue(currentdate);
                    }
                }
                else {
                    loadingtxt.setVisibility(View.VISIBLE);
                    loadingtxt.setText("No Data Found");
                    progressBar.setVisibility(View.GONE);
                    no_data_found.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
