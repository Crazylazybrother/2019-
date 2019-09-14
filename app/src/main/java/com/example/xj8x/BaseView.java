package com.example.xj8x;

/*
 *Creat by Ao on 2019/8/24--10:17
 *描述: TODO
 */
public interface BaseView<T> {
    void setPresenter(T presenter);

    void onLoading(String text);

    void loadingComplete();

    void loadingError(String error);
}
