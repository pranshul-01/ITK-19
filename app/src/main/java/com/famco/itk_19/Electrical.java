package com.famco.itk_19;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Electrical extends AppCompatActivity implements View.OnClickListener{
    Button syllabus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_electrical);
        syllabus=findViewById(R.id.syl);
        syllabus.setOnClickListener(this);
    }
    @Override
    public void onClick(View view)
    {
        if(view==syllabus)
        {
            Intent intent=new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("https://csvtu.ac.in/ew/download/b-tech-5th-semester-5/?wpdmdl=14364&refresh=616e41f21403d1634615794"));
            startActivity(intent);
        }
    }
}






