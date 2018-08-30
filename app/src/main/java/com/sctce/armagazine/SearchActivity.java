package com.sctce.armagazine;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    RecyclerView SearchRecyler;
    AutoCompleteTextView SrchTxt;
    ImageView SearchBtn;
    List<MagPage> MagPageList=new ArrayList<>();
    List<String> editetxtdata=new ArrayList<>();
    MagPage magPage;
    String[] dataarray;
    String word;
    Intent intent;
    Adapter2 adapter2;
    int i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        SrchTxt=findViewById(R.id.SearchText);
        SearchBtn=findViewById(R.id.SearchButton);
        SearchRecyler=findViewById(R.id.SearchRecycler);

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
        MagPageList= databaseAccess.getMagData();
        databaseAccess.close();

        adapter2= new Adapter2(getApplicationContext(), MagPageList);
        RecyclerView.LayoutManager manager=new GridLayoutManager(this,3);
        SearchRecyler.setLayoutManager(manager);
        SearchRecyler.setAdapter(adapter2);

        i=0;
        while(i<MagPageList.size()){
            magPage=MagPageList.get(i);
            editetxtdata.add(magPage.getArtEng());
            editetxtdata.add(magPage.getAuthName());
            i++;
        }
       dataarray=editetxtdata.toArray(new String[0]);
       ArrayAdapter adapter = new
                ArrayAdapter(this,android.R.layout.simple_list_item_1,dataarray);
        SrchTxt.setAdapter(adapter);
        SrchTxt.setThreshold(1);

        SrchTxt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {

                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    srchfun();
                    return true;
                }
                return false;
            }
        });


        SearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               srchfun();
            }
        });
            intent=new Intent(SearchActivity.this,ArticleReadView.class);
        SearchRecyler.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext(), SearchRecyler, new RecyclerItemClickListener.OnItemClickListener() {
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



    }

    public void srchfun(){
        if(SrchTxt.getText().toString().equals("")){
            Toast.makeText(SearchActivity.this,"Enter Search Word",Toast.LENGTH_SHORT).show();
        }
        else{
            word=SrchTxt.getText().toString();
            DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
            databaseAccess.open();
            MagPageList= databaseAccess.searchMagData(word);
            databaseAccess.close();

            if (MagPageList.size()>0){
                adapter2= new Adapter2(getApplicationContext(), MagPageList);
                RecyclerView.LayoutManager manager=new GridLayoutManager(getApplicationContext(),3);
                SearchRecyler.setLayoutManager(manager);
                SearchRecyler.setAdapter(adapter2);
            }
            else {
                adapter2 = new Adapter2(getApplicationContext(), MagPageList);
                RecyclerView.LayoutManager manager = new GridLayoutManager(getApplicationContext(), 3);
                SearchRecyler.setLayoutManager(manager);
                SearchRecyler.setAdapter(adapter2);
                Toast.makeText(SearchActivity.this, "Found Nothing", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
