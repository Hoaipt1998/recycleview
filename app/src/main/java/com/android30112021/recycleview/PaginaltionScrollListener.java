package com.android30112021.recycleview;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public abstract class PaginaltionScrollListener extends RecyclerView.OnScrollListener {
    private LinearLayoutManager linearLayoutManager;

    public PaginaltionScrollListener(LinearLayoutManager linearLayoutManager){
        this.linearLayoutManager = linearLayoutManager;
    }

    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        // Không load thêm dữ liệu mới
        if (isLoading() || isLastPage()){
            return;
        }

        // total = 10
        // firstvisible = 3
        // visible = 7

        int firstVisiblePosition = linearLayoutManager.findFirstVisibleItemPosition();
        int totalItem = linearLayoutManager.getItemCount();
        int visibleItems = linearLayoutManager.getChildCount();


        if (firstVisiblePosition >= 0 && (visibleItems + firstVisiblePosition) >= totalItem){
            loadMore();
        }

    }

    public abstract boolean isLastPage();
    public abstract boolean isLoading();
    public abstract void loadMore();
}
