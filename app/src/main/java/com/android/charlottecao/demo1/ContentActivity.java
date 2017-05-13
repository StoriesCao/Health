package com.android.charlottecao.demo1;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

public class ContentActivity extends AppCompatActivity {

    private ViewPager mViewPager;

    private Toolbar mToolbar;

    private BottomNavigationView mBottomNavigationView;

    private NestedScrollView mScrollView;

    MenuItem prevMenuItem;


    /*启动此活动*/
    public static void actionStart(Context context) {
        Intent intent = new Intent(context, ContentActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);

        if (Build.VERSION.SDK_INT >= 21) {
            View decorateView = getWindow().getDecorView();
            decorateView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

        mToolbar = (Toolbar) findViewById(R.id.content_toolbar);
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_back);
        }

        mScrollView = (NestedScrollView) findViewById(R.id.nested_scroll_view);
        mScrollView.setFillViewport(true);

        FragmentManager fragmentManager = getSupportFragmentManager();

        /*mViewPager = (ViewPager) findViewById(R.id.content_view_paper);
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                return new ContentFragment();
            }

            @Override
            public int getCount() {
                return 3;
            }
        });*/
        mViewPager = (ViewPager) findViewById(R.id.content_view_paper);
        ViewPaperAdapter viewPaperAdapter = new ViewPaperAdapter(fragmentManager);
        viewPaperAdapter.addFragment(ContentFragment.newInstance("1.注意事项", R.string.temp_content_warn));
        viewPaperAdapter.addFragment(ContentFragment.newInstance("2.营养价值", R.string.temp_content_value));
        viewPaperAdapter.addFragment(ContentFragment.newInstance("3.食用方法", R.string.temp_content_way));
        mViewPager.setAdapter(viewPaperAdapter);

        mBottomNavigationView = (BottomNavigationView) findViewById(R.id.content_bottom_navigation);
        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.item_call:
                        mViewPager.setCurrentItem(0);
                        break;
                    case R.id.item_mail:
                        mViewPager.setCurrentItem(1);
                        break;
                    case R.id.item_person:
                        mViewPager.setCurrentItem(2);
                        break;
                    default:
                        return false;
                }
                return false;
            }
        });

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (prevMenuItem != null) {
                    prevMenuItem.setChecked(false);
                } else {
                    mBottomNavigationView.getMenu().getItem(0).setChecked(false);
                }
                mBottomNavigationView.getMenu().getItem(position).setChecked(true);
                prevMenuItem =mBottomNavigationView.getMenu().getItem(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                super.onOptionsItemSelected(item);
        }
        return true;
    }
}
