package com.example.xj8x;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*
 *Creat by Ao on 2019/8/24--10:20
 *描述: 刷新次数。。。
 */
public class Main2Presenter implements Main2Contract.Presenter {

    Main2Contract.View view;

    public Main2Presenter(Main2Contract.View view) {
        this.view = view;
        view.setPresenter(this);
        view.initViews();
    }

    @Override
    public void getData() {
        int NUM = 20;
        List<Item2> itemList = new ArrayList<>();

        for (int i = 0; i < NUM; i++) {
            itemList.add(new Item2(String.valueOf(i)));
        }

        view.showData(itemList);
    }

    @Override
    public void getRefreshData(int refreshType) {
        if (refreshType != 1 && refreshType != 2) {
            return;
        }

        int NUM = 20;
        List<Item2> itemList = new ArrayList<>();

        Random random = new Random();

        for (int i = 0; i < NUM; i++) {
            if (refreshType == 1) {
                // 下拉刷新
                itemList.add(new Item2(String.valueOf(random.nextInt(20) + 50)));
            } else {
                itemList.add(new Item2(String.valueOf(random.nextInt(20) + 100)));
            }
        }

        view.showRefreshData(itemList, refreshType);
    }
}
