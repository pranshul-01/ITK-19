package com.famco.itk_19;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class Homepage extends AppCompatActivity {
    Spinner sp1,sp2;
    EditText name;
    Button submit;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        name=findViewById(R.id.name);
        sp1=findViewById(R.id.spinner1);
        sp2=findViewById(R.id.spinner2);
        submit=findViewById(R.id.btnSubmit);
        firebaseDatabase=FirebaseDatabase.getInstance();
        DatabaseReference databaseReference=firebaseDatabase.getReference().child("StudentDetails");
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String n1=name.getText().toString().trim();
                String item = sp1.getSelectedItem().toString();
                String item1 = sp2.getSelectedItem().toString();
                HashMap<String,Object> map=new HashMap<>();
                map.put("Name",n1);
                map.put("Branch",item);
                map.put("Semester",item1);
                databaseReference.child("Student").push().setValue(map).addOnCompleteListener(task ->
                {
                    if(task.isSuccessful())
                    {
                        Toast.makeText(Homepage.this,"Insert successfully",Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(Homepage.this,"Error",Toast.LENGTH_SHORT).show();
                    }

                });
                Toast.makeText(Homepage.this, item, Toast.LENGTH_SHORT).show();
                if (item.equals("CSE")) {
                    Intent intent = new Intent(Homepage.this, Cse.class);
                    startActivity(intent);
                }
                if (item.equals("Mechanical")) {
                    Intent intent = new Intent(Homepage.this, Mechanical.class);
                    startActivity(intent);
                }
                if (item.equals("Electrical")) {
                    Intent intent = new Intent(Homepage.this, Electrical.class);
                    startActivity(intent);
                }
                if (item.equals("Civil")) {
                    Intent intent = new Intent(Homepage.this, Civil.class);
                    startActivity(intent);
                }
            }
        });
    }
}