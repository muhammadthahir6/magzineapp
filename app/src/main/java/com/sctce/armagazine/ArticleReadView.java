package com.sctce.armagazine;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ArticleReadView extends AppCompatActivity {

    ImageView ArtiImage;
    TextView  ArtiName,AutName,ArtCont;
    CircleImageView AutImage;
    String artname,authname;
    byte[] artimage,authimage;
    int i;
    MagPage magData;
    List<MagPage> MagPageList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_read_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
         i=intent.getExtras().getInt("i");

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
        MagPageList= databaseAccess.searchMagDatabyId(i);
        databaseAccess.close();

        magData=MagPageList.get(0);
        artname=magData.getArtName();
        authname=magData.getAuthName();
        ArtiName=findViewById(R.id.artiName);
        ArtiName.setText(artname);
        AutName=findViewById(R.id.athName);
        AutName.setText(authname);
        artimage=magData.getArtImg();
        authimage=magData.getAuthImg();
        ArtCont=findViewById(R.id.artiContent);
        ArtCont.setText(magData.getContent());

        ArtiImage=findViewById(R.id.artiImg);
        ByteArrayInputStream artStream = new ByteArrayInputStream(artimage);
        Bitmap artbitmap= BitmapFactory.decodeStream(artStream);
        ArtiImage.setImageBitmap(artbitmap);

        AutImage=findViewById(R.id.athImg);
        ByteArrayInputStream authStream = new ByteArrayInputStream(authimage);
        Bitmap authbitmap= BitmapFactory.decodeStream(authStream);
        AutImage.setImageBitmap(authbitmap);


    }
}
