package com.btsken.library;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by ken on 2016/1/11.
 */
public class RecyclerViewAdapter<T> extends RecyclerView.Adapter {

    protected List<T> mList;
    protected View.OnClickListener mOnItemClickListener;

    public RecyclerViewAdapter(List<T> datas, View.OnClickListener listener) {
        mList = new ArrayList<>(datas);
        mOnItemClickListener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void update(List<T> devices) {
        mList.clear();
        mList.addAll(devices);
        notifyDataSetChanged();
    }

    public T getItem(int position) {
        T device = null;
        if (position < mList.size()) {
            device = mList.get(position);
        }
        return device;
    }

    public T removeItem(int position) {
        final T note = mList.remove(position);
        notifyItemRemoved(position);
        return note;
    }

    public void addItem(int position, T note) {
        mList.add(position, note);
        notifyItemInserted(position);
    }

    public void moveItem(int fromPosition, int toPosition) {
        final T note = mList.remove(fromPosition);
        mList.add(toPosition, note);
        notifyItemMoved(fromPosition, toPosition);
    }

    public void animateTo(List<T> models) {
        applyAndAnimateRemovals(models);
        applyAndAnimateAdditions(models);
        applyAndAnimateMovedItems(models);
    }

    private void applyAndAnimateRemovals(List<T> newModels) {
        for (int i = mList.size() - 1; i >= 0; i--) {
            final T model = mList.get(i);
            if (!newModels.contains(model)) {
                removeItem(i);
            }
        }
    }

    private void applyAndAnimateAdditions(List<T> newModels) {
        for (int i = 0, count = newModels.size(); i < count; i++) {
            final T model = newModels.get(i);
            if (!mList.contains(model)) {
                addItem(i, model);
            }
        }
    }

    private void applyAndAnimateMovedItems(List<T> newModels) {
        for (int toPosition = newModels.size() - 1; toPosition >= 0; toPosition--) {
            final T model = newModels.get(toPosition);
            final int fromPosition = mList.indexOf(model);
            if (fromPosition >= 0 && fromPosition != toPosition) {
                moveItem(fromPosition, toPosition);
            }
        }
    }

    public void setComparator(Comparator<T> comparator) {
        Collections.sort(mList, comparator);
        notifyDataSetChanged();
    }
}
