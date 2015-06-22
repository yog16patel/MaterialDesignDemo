package com.citymart.yg.materialdesigndemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by yg on 6/21/2015.
 */
public class VivzAdapter extends RecyclerView.Adapter<VivzAdapter.MyRecycleViewHolder>
{
    List<Information> mData = Collections.emptyList();
    LayoutInflater inflater;
    private Context mContext ;
    public VivzAdapter(Context context,List<Information> data)
    {
        inflater = LayoutInflater.from(context);
        mContext = context;
        mData = data;
    }
    @Override
    public MyRecycleViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        Log.d(MainActivity.TAG,"onCreateViewHolder");
        View  view = inflater.inflate(R.layout.custom_row,parent,false);
        MyRecycleViewHolder viewHolder = new MyRecycleViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyRecycleViewHolder holder, int position)
    {
        Log.d(MainActivity.TAG, "onBindViewHolder" + position);
        Information currentObject = mData.get(position);
        holder.titleTV.setText(currentObject.getTittle());
        holder.iconMV.setImageResource(currentObject.getIconId());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    private void delete(int position)
    {
        mData.remove(position);
        notifyItemRemoved(position);
    }

    class MyRecycleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        TextView titleTV;
        ImageView iconMV;
        public MyRecycleViewHolder(View itemView)
        {
            super(itemView);
            Log.d(MainActivity.TAG, "MyRecycleViewHolder");
            titleTV = (TextView)itemView.findViewById(R.id.list_msg);
            iconMV = (ImageView)itemView.findViewById(R.id.list_icon);
            iconMV.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
           delete(getPosition());
        }
    }
}
