package com.sctce.armagazine;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
//import android.view.MotionEvent;
import android.view.View;
//import android.widget.AdapterView;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
//import android.widget.RadioButton;
//import android.widget.RadioGroup;
import android.widget.Toast;

//import com.HashTech.SctArMagazine.UnityPlayerActivity;

import java.security.acl.AclNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {


    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);

    //a list to store all the pages
    List<MagPage> MagPageList=new ArrayList<>();
    MagPage magPage;
    //the recyclerview
    RecyclerView recyclerView;
    ImageView Search,Team,Gifts,ARbtn;

     MagPageAdapter magPageAdapter;
   //  RadioGroup CatGrp;
    // RadioButton Cat1,Cat2,Cat3,Cat4;
     LinearLayout MainLayout;
     Bundle bundle;
     Intent intent,srchIntent,teamIntent,arIntent,GiftIntent;
    String jsonList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        MainLayout=findViewById(R.id.mainLayout);
       /* CatGrp=findViewById(R.id.catgrp);
        Cat1=findViewById(R.id.cat1);
        Cat2=findViewById(R.id.cat2);
        Cat3=findViewById(R.id.cat3);
        Cat4=findViewById(R.id.cat4);
       */
        //getting the recyclerview from xml
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        //initializing the pagelist

       ComponentName lComponentName= new ComponentName("com.sctce.arunitymagazine", "com.sctce.arunitymagazine.UnityPlayerActivity");

        arIntent=new Intent(Intent.ACTION_MAIN);
        arIntent.setComponent(lComponentName);
        arIntent.addCategory(Intent.CATEGORY_LAUNCHER);

      //  arIntent=new Intent(Main2Activity.this,UnityPlayerActivity.class);
        ARbtn=findViewById(R.id.arbtn);
        ARbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    view.startAnimation(buttonClick);
                    open();



            }
        });
        srchIntent=new Intent(Main2Activity.this,SearchActivity.class);
        teamIntent=new Intent(Main2Activity.this,AboutActivity.class);
        Gifts=findViewById(R.id.extras);
        GiftIntent=new Intent(Main2Activity.this,ExtrasActivity.class);
        Gifts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(buttonClick);
                startActivity(GiftIntent);
            }

        });
        Search=findViewById(R.id.Search);
        Team=findViewById(R.id.team);
        Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(buttonClick);
                startActivity(srchIntent);
            }
        });
        Team.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(buttonClick);
                startActivity(teamIntent);
            }
        });
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
        MagPageList= databaseAccess.getMagData();
        databaseAccess.close();

        magPageAdapter= new MagPageAdapter(getApplicationContext(), MagPageList);
        RecyclerView.LayoutManager manager=new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(magPageAdapter);

        intent = new Intent(Main2Activity.this,ArticleReadView.class);


        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                magPage=MagPageList.get(position);
               intent.putExtra("i",magPage.getArtId());

                        startActivity(intent);

            }

            @Override
            public void onItemLongClick(View view, int position) {
                // ...
            }
        }));
       /*MagPageList.add(
                new MagPage("Article 1","Author 1",MagPageList.get(0).getArtImg(),MagPageList.get(0).getAuthImg())
        );

        MagPageList.add(
                new MagPage("Article 2","Author 2",R.drawable.imgmag1,R.drawable.prosq)
        );

        MagPageList.add(
                new MagPage("Article 3","Author 3",R.drawable.imgmag1,R.drawable.prosq)
        );

        MagPageList.add(
                new MagPage("Article 4","Author 4",R.drawable.imgmag1,R.drawable.prosq)
        );
*/
       /* CatGrp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    selectionNone();
                    if(i==R.id.cat1) {
                        //Cat1.setBackgroundResource(R.drawable.rbtnback1);
                        MainLayout.setBackgroundResource(R.drawable.mygradent1);
                        recyclerView.setAdapter(magPageAdapter);
                    }
                     else if(i==R.id.cat2) {
                        //Cat2.setBackgroundResource(R.drawable.rbtnback1);
                        MainLayout.setBackgroundResource(R.drawable.mygradent2);
                        recyclerView.setAdapter(magPageAdapter);
                    }
                    else if(i==R.id.cat3) {
                       // Cat3.setBackgroundResource(R.drawable.rbtnback1);
                        MainLayout.setBackgroundResource(R.drawable.mygradent3);
                        recyclerView.setAdapter(magPageAdapter);
                    }
                    else if(i==R.id.cat4) {
                       // Cat4.setBackgroundResource(R.drawable.rbtnback1);
                        MainLayout.setBackgroundResource(R.drawable.mygradent4);
                        recyclerView.setAdapter(magPageAdapter);
                    }
            }
        });*/
    }

    /*public void selectionNone(){
        Cat1.setBackgroundResource(R.drawable.rbtnback2);
        Cat2.setBackgroundResource(R.drawable.rbtnback2);
        Cat3.setBackgroundResource(R.drawable.rbtnback2);
        Cat4.setBackgroundResource(R.drawable.rbtnback2);
    }*/

    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
    public void open(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setIcon(getResources().getDrawable(R.drawable.fltbtn));
        alertDialogBuilder.setTitle("SCT Magazine 2018");
        alertDialogBuilder.setMessage("I Have a Hard Copy of Magazine or e-Magazine for Scanning with AR Camera");
                alertDialogBuilder.setPositiveButton("YES",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                try {
                                    startActivity(arIntent);
                                    Toast.makeText(getApplicationContext(), "Loading may Take a while", Toast.LENGTH_LONG).show();
                                }
                                catch (ActivityNotFoundException e){
                                    Toast.makeText(getApplicationContext(), "AR Module Not Installed", Toast.LENGTH_LONG).show();
                                    startActivity(GiftIntent);
                                }

                            }
                        });

        alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(GiftIntent);
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}
