package com.example.xj8x;

import java.util.List;

/*
 *Creat by Ao on 2019/8/24--10:13
 *描述: TODO
 */
public class Main2Contract {

    public interface View extends BaseView<Presenter> {
        void initViews();
        void showData(List<Item2> list);
        void showRefreshData(List<Item2> list, int refreshType);
    }

    public interface Presenter extends BasePresenter {
        void getData();
        void getRefreshData(int refreshType);
    }

}
