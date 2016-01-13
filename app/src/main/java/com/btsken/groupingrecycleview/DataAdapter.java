package com.btsken.groupingrecycleview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.btsken.library.RecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ken on 2016/1/13.
 */
public class DataAdapter extends RecyclerViewAdapter<Data> {

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mName;
        private TextView mid;

        public ViewHolder(View v) {
            super(v);
            mName = (TextView) v.findViewById(R.id.name_textview);
            mid = (TextView) v.findViewById(R.id.id_textview);
        }
    }

    public DataAdapter(List<Data> datas, View.OnClickListener listener) {
        super(datas, listener);
        mList = new ArrayList<>(datas);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        ViewHolder vh = new ViewHolder(v);
        v.setOnClickListener(mOnItemClickListener);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        ViewHolder holder = (ViewHolder) viewHolder;

        Data data = mList.get(position);
        holder.mName.setText(data.getName());
        holder.mid.setText(String.format("%s", data.getId()));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
