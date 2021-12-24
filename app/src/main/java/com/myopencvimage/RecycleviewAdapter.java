package com.myopencvimage;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class RecycleviewAdapter extends RecyclerView.Adapter<RecycleviewAdapter.ViewHolder> {

    List<String> mData ;
    public RecycleviewAdapter(List<String> data) {
        this.mData = data;
    }

    @Override
    public RecycleviewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_main_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecycleviewAdapter.ViewHolder holder, final int position) {
        if (mData == null) {
            return;
        }
        Log.e("-------",mData.get(position)+"");
        holder.titleView.setText(mData.get(position));
        holder.setPosition(position);
        holder.setItemClickListener(mOnItemClickListener);
    }

    @Override
    public int getItemCount() {
        return  mData.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setDataList(List<String> listBean) {
        if (listBean != null) {
            mData = listBean;
            this.notifyDataSetChanged();
        }
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView titleView;
        int position;
        OnItemClickListener onItemClickListener;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            this.titleView = itemView.findViewById(R.id.jump_title);
        }

        public void setPosition(int position) {
            this.position = position;
        }

        public void setItemClickListener(OnItemClickListener onItemClickListener) {
            this.onItemClickListener = onItemClickListener;
        }

        @Override
        public void onClick(View v) {
            if (v == this.itemView) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(v, position);
                }
            }
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

}
