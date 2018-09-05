package com.sctce.armagazine;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedImageView;

import java.io.ByteArrayInputStream;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;



public class MagPageAdapter extends RecyclerView.Adapter<MagPageAdapter.MagPageHolder> {

    //this context we will use to inflate the layout
    private Context mCtx;

    //we are storing all the page in a list
    private List<MagPage> MagPageList;

    

    //getting the context and pages list with constructor
    public MagPageAdapter(Context mCtx, List<MagPage> MagPageList) {
        this.mCtx = mCtx;
        this.MagPageList = MagPageList;

    }


    @Override
    public MagPageHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.magpage_layout, parent,false);
        return new MagPageHolder(view);
    }

    @Override
    public void onBindViewHolder(MagPageHolder holder, int position) {
        //getting the product of the specified position
        MagPage magPage = MagPageList.get(position);
        //binding the data with the viewholder views

        holder.articleName.setText(magPage.getArtName());
        holder.authorName.setText(magPage.getAuthName());
        byte[] artimg= magPage.getArtImg();
        ByteArrayInputStream artStream = new ByteArrayInputStream(artimg);
        Bitmap artbitmap= BitmapFactory.decodeStream(artStream);

        byte[] authimg=magPage.getAuthImg();
        ByteArrayInputStream authStream = new ByteArrayInputStream(authimg);
        Bitmap authbitmap= BitmapFactory.decodeStream(authStream);


        holder.cardbg.setImageBitmap(artbitmap);
        holder.authimg.setImageBitmap(authbitmap);



        setFadeAnimation(holder.itemView);
    }
    @Override
    public int getItemCount() {
        return MagPageList.size();
    }
    private void setFadeAnimation(View view){
        AlphaAnimation animation=new AlphaAnimation(0.0f,0.5f);
        animation.setDuration(500);
        view.startAnimation(animation);
    }

    class MagPageHolder extends RecyclerView.ViewHolder  {

        TextView articleName, authorName;
        RoundedImageView cardbg;
        CircleImageView authimg;
        public MagPageHolder(View itemView) {
            super(itemView);

            articleName = itemView.findViewById(R.id.articleName);
            authorName = itemView.findViewById(R.id.authorName);
            cardbg = itemView.findViewById(R.id.cardbg);
            authimg=itemView.findViewById(R.id.authimg);

        }
    }
}
