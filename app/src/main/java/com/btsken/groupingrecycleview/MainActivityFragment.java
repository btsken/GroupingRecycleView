package com.btsken.groupingrecycleview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.btsken.library.SectionedRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private SectionedRecyclerViewAdapter mSectionedAdapter;
    private DataAdapter mAdapter;
    private List<Data> mDatas = new ArrayList<>();

    public MainActivityFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDatas.add(new Data(0, "a"));
        mDatas.add(new Data(2, "a"));
        mDatas.add(new Data(1, "ac"));
        mDatas.add(new Data(0, "a"));
        mDatas.add(new Data(1, "ac"));
        mDatas.add(new Data(0, "a"));
        mDatas.add(new Data(2, "ab"));
        mDatas.add(new Data(0, "ab"));
        mDatas.add(new Data(0, "ab"));
        mDatas.add(new Data(0, "ac"));
        mDatas.add(new Data(2, "a"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager =
                new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
        mAdapter = new DataAdapter(mDatas, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        orderById();
        setHasOptionsMenu(true);
        return rootView;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_id:
                orderById();
                break;
            case R.id.action_name:
                orderByName();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void orderById() {
        mAdapter.setComparator(new IdComparator());
        mSectionedAdapter = new SectionedRecyclerViewAdapter<Data>(getActivity(),
                R.layout.section, R.id.section_text, mAdapter, mDatas) {
            @Override
            public String getSectionName(Data obj) {
                return String.valueOf(obj.getId());
            }
        };
        mSectionedAdapter.setSections();
        mRecyclerView.setAdapter(mSectionedAdapter);
    }

    private void orderByName() {
        mAdapter.setComparator(new NameComparator());
        mSectionedAdapter = new SectionedRecyclerViewAdapter<Data>(getActivity(),
                R.layout.section, R.id.section_text, mAdapter, mDatas) {
            @Override
            public String getSectionName(Data obj) {
                return obj.getName();
            }
        };
        mSectionedAdapter.setSections();
        mRecyclerView.setAdapter(mSectionedAdapter);
    }
}
