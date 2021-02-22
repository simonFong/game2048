package com.simonfong.game2048;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by fengzimin  on  2021/2/22.
 * interface by
 */
public class WidgetAdapter extends RecyclerView.Adapter<WidgetAdapter.ViewHolder> {

    private List<Integer> mData = new ArrayList<>();

    public void setNewData(List<Integer> data) {
        mData = data;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_widget, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Integer type = mData.get(position);
        int num = 0;
        int color = App.getInstance().getColor(R.color.colorAccent);
        switch (type) {
            case 0:
                color = App.getInstance().getColor(R.color.colorAccent);
                break;
            case 1:
                num = 2;
                color = App.getInstance().getColor(R.color.colorPrimary);
                break;
            case 2:
                num = 4;
                color = App.getInstance().getColor(R.color.colorPrimaryDark);
                break;
            case 3:
                num = 8;
                color = App.getInstance().getColor(R.color.colorPrimaryDark);
                break;
            case 4:
                num = 16;
                color = App.getInstance().getColor(R.color.colorPrimaryDark);
                break;
            case 5:
                num = 32;
                color = App.getInstance().getColor(R.color.colorPrimaryDark);
                break;
            case 6:
                num = 64;
                color = App.getInstance().getColor(R.color.colorPrimaryDark);
                break;
            case 7:
                num = 128;
                color = App.getInstance().getColor(R.color.colorPrimaryDark);
                break;
            case 8:
                num = 256;
                color = App.getInstance().getColor(R.color.colorPrimaryDark);
                break;
            case 9:
                num = 512;
                color = App.getInstance().getColor(R.color.colorPrimaryDark);
                break;
            case 10:
                num = 1024;
                color = App.getInstance().getColor(R.color.colorPrimaryDark);
                break;
            case 11:
                num = 2048;
                color = App.getInstance().getColor(R.color.colorPrimaryDark);
                break;
        }
        holder.viewBg.setBackgroundColor(color);
        holder.tvNum.setText("" + num);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        View crushView;
        View viewBg;
        TextView tvNum;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            crushView = itemView;
            viewBg = (View) itemView.findViewById(R.id.view_bg);
            tvNum = itemView.findViewById(R.id.tv_num);
        }
    }

}
