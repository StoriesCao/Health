package com.android.charlottecao.demo1;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ListActivity extends AppCompatActivity {

    private Toolbar mToolbar;

    private ListAdapter mAdapter;

    /*暂时数据源*/
    private Fruit[] fruits = {new Fruit("Apple"), new Fruit("banana"),
            new Fruit("Orange"), new Fruit("Watermelon"),
            new Fruit("Pear"), new Fruit("Grape"),
            new Fruit("Pineapple"), new Fruit("Mango"),
            new Fruit("Strawberry"), new Fruit("Cherry")};

    private List<Fruit> mFruitList = new ArrayList<>();

    private void initList() {
        mFruitList.clear();
        for (int i = 0; i < 50; i++) {
            Random random = new Random();
            int index = random.nextInt(fruits.length);
            mFruitList.add(fruits[index]);
        }
    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, ListActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        mToolbar = (Toolbar) findViewById(R.id.list_toolbar);
        setSupportActionBar(mToolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_back);
        }

        initList();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.list_recycler_view);
        //GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new ListAdapter(mFruitList);
        recyclerView.setAdapter(mAdapter);
    }
}
