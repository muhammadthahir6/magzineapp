package com.sctce.armagazine;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

public class ExtrasActivity extends AppCompatActivity {
    LinearLayout ToStore,MagSite,eMag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extras);
        ToStore=findViewById(R.id.playstoreAr);
        ToStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Also Ask Your Friends for the Module(apk Installation)",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.sctce.arunitymagazine"));
                startActivity(intent);
            }
        });
        MagSite=findViewById(R.id.magsite);
        MagSite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("http://sctcollegemagazine.in/");
                Intent intent= new Intent(Intent.ACTION_VIEW,uri);
                startActivity(intent);
            }
        });
        eMag=findViewById(R.id.emag);
        eMag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("http://sctcemagazine.000webhost.com/pdf/magazine.pdf");
                Intent intent= new Intent(Intent.ACTION_VIEW,uri);
                startActivity(intent);
            }
        });

    }
}
