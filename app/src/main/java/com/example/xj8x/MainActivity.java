package com.example.xj8x;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    //未选中的Tab图片
    private int[] unSelectTabRes = new int[]{R.drawable.home_normal,R.drawable.choose_normal,R.drawable.mine_normal};
    //选中的Tab图片
    private int[] selectTabRes = new int[]{R.drawable.home_pass,R.drawable.choose_pass,R.drawable.mine_pass};
    //Tab标题
    private String[] title = new String[]{"首页", "娱乐", "我的"};

    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    private List<String> mTitle;
    private List<Fragment> mFragment;

    private TabLayout tabLayout;
    private TabLayout.Tab tabAtOne;
    private TabLayout.Tab tabAttwo;
    private TabLayout.Tab tabAtthree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();
    }

    private void initData(){
        mTitle=new ArrayList<>();
        mTitle.add("首页");
        mTitle.add("娱乐");
        mTitle.add("我的");

        mFragment=new ArrayList<>();
        mFragment.add(new First_fragment());
        mFragment.add(new Main2Fragment());
        mFragment.add(new Thirtly_fragment());
    }

    private void initView(){


        mTabLayout=findViewById(R.id.tab_layout_view);
        mViewPager=findViewById(R.id.viewpager_content_view);
        //预加载
        mViewPager.setOffscreenPageLimit(mFragment.size());
        //mViewPager滑动监听
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        //设置适配器
        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            //选中的item
            @Override
            public Fragment getItem(int i) {
                return mFragment.get(i);
            }
            //返回item的个数
            @Override
            public int getCount() {
                return mFragment.size();
            }
            //设置标题
            @Override
            public CharSequence getPageTitle(int position) {
                return mTitle.get(position);
            }
        });


        //绑定
        mTabLayout.setupWithViewPager(mViewPager);
    }

}
