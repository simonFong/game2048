package com.simonfong.game2048;

import android.util.Log;
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
        if (type != 0) {
            num = (int) Math.pow(2, type);
            color = App.getInstance().getColor(R.color.colorPrimaryDark);
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

    public int getMaxPoint() {
        int max = 0;
        for (int i = 0; i < mData.size(); i++) {
            Log.d("getMaxPoint","dafdsf="+mData.get(i));
            int pow = (int) Math.pow(2, mData.get(i));
            if (pow>max){
                max = pow;
            }
        }
        Log.d("getMaxPoint","max="+max);
        return max;
    }

}
