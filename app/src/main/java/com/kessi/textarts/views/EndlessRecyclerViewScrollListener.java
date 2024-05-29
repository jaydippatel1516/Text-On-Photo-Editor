package com.kessi.textarts.views;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

public abstract class EndlessRecyclerViewScrollListener extends RecyclerView.OnScrollListener {
    private int currentPage = 0;
    RecyclerView.LayoutManager layoutManager;
    private boolean loading = true;
    private int previousTotalItemCount = 0;
    private int visibleThreshold = 5;

    public abstract void onLoadMore(int i, int i2, RecyclerView recyclerView);

    public EndlessRecyclerViewScrollListener(GridLayoutManager gridLayoutManager) {
        this.layoutManager = gridLayoutManager;
        this.visibleThreshold = 5 * gridLayoutManager.getSpanCount();
    }

    public int getLastVisibleItem(int[] iArr) {
        int i = 0;
        for (int i2 = 0; i2 < iArr.length; i2++) {
            if (i2 == 0) {
                i = iArr[i2];
            } else {
                int i3 = iArr[i2];
                if (i3 > i) {
                    i = i3;
                }
            }
        }
        return i;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
    public void onScrolled(RecyclerView recyclerView, int i, int i2) {
        int i3;
        int itemCount = this.layoutManager.getItemCount();
        RecyclerView.LayoutManager layoutManager2 = this.layoutManager;
        if (layoutManager2 instanceof StaggeredGridLayoutManager) {
            i3 = getLastVisibleItem(((StaggeredGridLayoutManager) layoutManager2).findLastVisibleItemPositions(null));
        } else if (layoutManager2 instanceof GridLayoutManager) {
            i3 = ((GridLayoutManager) layoutManager2).findLastVisibleItemPosition();
        } else {
            i3 = layoutManager2 instanceof LinearLayoutManager ? ((LinearLayoutManager) layoutManager2).findLastVisibleItemPosition() : 0;
        }
        if (itemCount < this.previousTotalItemCount) {
            this.currentPage = 0;
            this.previousTotalItemCount = itemCount;
            if (itemCount == 0) {
                this.loading = true;
            }
        }
        if (this.loading && itemCount > this.previousTotalItemCount) {
            this.loading = false;
            this.previousTotalItemCount = itemCount;
        }
        if (!this.loading && i3 + this.visibleThreshold > itemCount) {
            int i4 = this.currentPage + 1;
            this.currentPage = i4;
            onLoadMore(i4, itemCount, recyclerView);
            this.loading = true;
        }
    }
}
