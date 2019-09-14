package com.example.xj8x;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/*
 *Creat by Ao on 2019/8/24--10:12
 *描述: TODO
 */
public class Main2Fragment extends Fragment
        implements Main2Contract.View, SwipeRefreshLayout.OnRefreshListener,
        Main2Adapter.OnClickListener {
    private static final String TAG = Main2Fragment.class.getSimpleName();

    private static final int PULL_DOWN_REFRESH = 1;
    private static final int PULL_UP_REFRESH = 2;

    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;

    private Main2Contract.Presenter presenter;

    private LinearLayoutManager linearLayoutManager;
    private Main2Adapter adapter;

    private List<Item2> items;

    // 用于判断是否正在刷新
    private boolean isRefreshing;

    private String status;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();

        status = (bundle == null) ? "null" : bundle.getString("status");
        Log.e(TAG, "onCreate: status " + status);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.second_fragment, container, false);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        new Main2Presenter(this);
    }

    @Override
    public void setPresenter(Main2Contract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onLoading(String text) {

    }

    @Override
    public void loadingComplete() {

    }

    @Override
    public void loadingError(String error) {

    }

    @Override
    public void initViews() {
        View view = getView();

        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe);
        swipeRefreshLayout.setOnRefreshListener(this);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

        recyclerView.setHasFixedSize(true);

        linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        items = new ArrayList<>();
        adapter = new Main2Adapter(items);
        adapter.setOnClickListener(this);
        recyclerView.setAdapter(adapter);

        recyclerView.addItemDecoration(new DividerItemDecoration(
                getActivity(), DividerItemDecoration.VERTICAL));

        isRefreshing = false;
        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                int lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                int totalItemCount = linearLayoutManager.getItemCount();
                if ((lastVisibleItem >= (totalItemCount - 2)) && dy > 0) {
                    if (!isRefreshing) {
                        isRefreshing = true;
                        swipeRefreshLayout.setRefreshing(isRefreshing);
                        presenter.getRefreshData(PULL_UP_REFRESH);
                    }
                }
            }
        });

        presenter.getData();
    }

    @Override
    public void showData(List<Item2> list) {
        if (list == null) {
            return;
        }

        items.addAll(list);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showRefreshData(List<Item2> list, int refreshType) {
        if (list == null) {
            return;
        }

        if (refreshType == PULL_DOWN_REFRESH) {
            items.clear();
            items.addAll(list);
            adapter.notifyDataSetChanged();

            swipeRefreshLayout.setRefreshing(false);
        } else {
            items.addAll(list);
            adapter.notifyDataSetChanged();

            isRefreshing = false;
            swipeRefreshLayout.setRefreshing(isRefreshing);
        }
    }

    @Override
    public void onRefresh() {
        presenter.getRefreshData(PULL_DOWN_REFRESH);
    }

    @Override
    public void onItemClick(Main2Adapter.ViewHolder viewHolder, Item2 item) {
        Log.e(TAG, "onItemClick: num = " + item.getNum());
        int num = Integer.valueOf(item.getNum());

        num = num + 1;
        item.setNum(String.valueOf(num));
        adapter.notifyDataSetChanged();
    }
}