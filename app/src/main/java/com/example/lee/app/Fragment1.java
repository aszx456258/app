package com.example.lee.app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.lee.hi.R;

/**
 * Created by Carson_Ho on 16/5/23.
 */
public class Fragment1 extends Fragment

{
    private Button img;
//    private RecyclerView recyclerView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item1, null);
        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.clock);
        img = (Button) view.findViewById(R.id.add);
        recyclerView.setItemAnimator(new DefaultItemAnimator());


        return view;
    }
}
