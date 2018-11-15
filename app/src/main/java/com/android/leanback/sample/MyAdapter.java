package com.android.leanback.sample;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by cdslily 20181114
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private Context context;
    ArrayList<PackageInfo> mPackageInfoList;
    private OnItemCallBack onItemCallBack;
    private PackageManager packageManager;

    public MyAdapter(Context context,  ArrayList<PackageInfo> packageInfoList) {
        this.context = context;
        this.mPackageInfoList = packageInfoList;
        this.packageManager = context.getPackageManager();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(
                R.layout.item, null);
        MyViewHolder holder = new MyViewHolder(inflate);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyAdapter.MyViewHolder holder, int position) {
        holder.tv_text.setText(mPackageInfoList.get(position).applicationInfo.loadLabel(packageManager));
        holder.iv_pic.setImageDrawable(mPackageInfoList.get(position).applicationInfo.loadIcon(packageManager));
        holder.itemView.setOnClickListener(new MyClick(position));
        holder.itemView.setOnFocusChangeListener(new MyFocusChange(position,
                holder));
    }

    @Override
    public int getItemCount() {
        return mPackageInfoList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView iv_pic;
        public TextView tv_text;
        public LinearLayout rl_scale;

        public MyViewHolder(View itemView) {
            super(itemView);
            iv_pic = (ImageView) itemView.findViewById(R.id.icon);
            tv_text = (TextView) itemView.findViewById(R.id.title);
            rl_scale = (LinearLayout) itemView.findViewById(R.id.scale_layout);
        }
    }


    private class MyFocusChange implements View.OnFocusChangeListener {

        int position;
        MyViewHolder holder;

        public MyFocusChange(int position, MyViewHolder holder) {
            this.position = position;
            this.holder = holder;
        }

        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            /*if (hasFocus) {
                holder.rl_scale.animate().scaleX(1.15f).scaleY(1.15f).start();
            } else {
                holder.rl_scale.animate().scaleX(1f).scaleY(1f).start();
            }*/
            holder.tv_text.setSelected(hasFocus);//不设置跑马灯无效
            if (onItemCallBack != null) {
                onItemCallBack.onFocusChange(v, hasFocus, position);
            }
        }

    }

    private class MyClick implements View.OnClickListener {

        int position;

        public MyClick(int position) {
            this.position = position;
        }

        @Override
        public void onClick(View v) {
            if (onItemCallBack != null) {
                onItemCallBack.onItemClick(v, position);
            }
        }

    }

    public void setOnItemCallBack(OnItemCallBack onItemCallBack) {
        this.onItemCallBack = onItemCallBack;
    }
}
