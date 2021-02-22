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

    public void up() {
        List<Integer> integers1 = new ArrayList<>();
        List<Integer> integers2 = new ArrayList<>();
        List<Integer> integers3 = new ArrayList<>();
        List<Integer> integers4 = new ArrayList<>();
        for (int i = 0; i < mData.size(); i++) {
            int value = mData.get(i).intValue();
            int i1 = value % 4;
            switch (i1) {
                case 0:
                    integers1.add(value);
                    break;
                case 1:
                    integers2.add(value);
                    break;
                case 2:
                    integers3.add(value);
                    break;
                case 3:
                    integers4.add(value);
                    break;
            }
        }
        sort(calc(integers1));
        sort(calc(integers2));
        sort(calc(integers3));
        sort(calc(integers4));


    }

    private List<Integer> sort(List<Integer> list) {
        int i = 0;
        while (i < list.size() - 1) {
            Integer view1 = list.get(i);
            if (view1 == 0) {
                int j = i + 1;
                while (j < list.size()) {
                    Integer view2 = list.get(j);
                    if (view2 == 0) {
                        j++;
                    } else {
                        Collections.swap(list, i, j);
                        break;
                    }
                }
            }
            i++;
        }
        return list;
    }

    private List<Integer> calc(List<Integer> list) {
        int i = 0;
        while (i < list.size() - 1) {
            Integer view1 = list.get(i);
            if (view1 == 0) {
                i++;
            } else {
                int j = i + 1;
                while (j < list.size()) {
                    Integer view2 = list.get(j);
                    if (view2 == 0) {
                        j++;
                    } else if (view1 == view2) {
                        view1++;
                        view2 = 0;
                        i++;
                        break;
                    } else {
                        i++;
                        break;
                    }
                }

            }
        }
        return list;
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
                num = 2;
                color = App.getInstance().getColor(R.color.colorAccent);
                break;
            case 1:
                num = 4;
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
