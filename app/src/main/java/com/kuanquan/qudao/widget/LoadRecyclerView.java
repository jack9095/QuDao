//package com.kuanquan.qudao.widget;
//
//import android.content.Context;
//import android.support.v7.widget.GridLayoutManager;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.support.v7.widget.StaggeredGridLayoutManager;
//import android.util.AttributeSet;
//import android.view.MotionEvent;
//import java.util.List;
//
//
///**
// */
//public class LoadRecyclerView extends RecyclerView {
//    /**
//     * 是否开启加载更多
//     */
//    private boolean loadingMoreEnabled = false;
//
//    /**
//     * 是否开启刷新
//     */
//    private boolean pullRefreshEnabled = false;
//
//    /**
//     * 加载更多
//     */
//    protected boolean isLoadMore = true;
//
//    /**
//     * 加载更多中
//     */
//    protected boolean isLoading = true;
//
//    /**
//     * 刷新中
//     */
//    private boolean mRefreshing = false;
//
//    /**
//     * true 没有更多
//     */
//    private boolean isNoMore = false;
//
//    /**
//     * 最后一个可见的item的位置
//     */
//    private int lastVisibleItemPosition;
//
//    private float mLastY = -1;
//
//    private boolean isBottom;
//
//
////    private OnRefreshListener mOnRefreshListener;
////
////    private OnTScrollListener mOnScrollListener;
////
////    private OnScrollStateListener mOnScrollStateListener;
//
//    private static final float DRAG_RATE = 2.0f;
//
////    private ArrowRefreshHeader mRefreshHeader = null;
//
//    public LoadRecyclerView(Context context) {
//        this(context, null);
//    }
//
//    public LoadRecyclerView(Context context, AttributeSet attrs) {
//        this(context, attrs, 0);
//    }
//
//    public LoadRecyclerView(Context context, AttributeSet attrs, int defStyle) {
//        super(context, attrs, defStyle);
//    }
//
//    /**
//     * refresh complete
//     *
//     * @param list
//     * @param noMore 是否有更多
//     */
//    public void refreshComplete(List<Object> list, boolean noMore) {
//        if (mRefreshHeader != null) {
//            mRefreshHeader.refreshComplete();
//        }
//        isLoadMore = false;
//        mRefreshing = false;
//        isNoMore = noMore;
//        if (pullRefreshEnabled) {
//            list.add(0, new HeaderVo());
//            if (loadingMoreEnabled){
//                if (noMore) {
//                    list.add(new FootVo(STATE_NOMORE));
//                } else {
//                    list.add(new FootVo(STATE_LOADING));
//                }
//            }
//        }
//        mMultiTypeAdapter.setItems(list);
//        mMultiTypeAdapter.notifyDataSetChanged();
//    }
//
//    /**
//     * 加载更多完成
//     *
//     * @param list
//     * @param noMore
//     */
//    public void loadMoreComplete(List<?> list, boolean noMore) {
//        if (mRefreshing) {
//            mRefreshing = false;
//        }
//        isNoMore = noMore;
//        if (null == list) {
//            mMultiTypeAdapter.getItems().remove(mMultiTypeAdapter.getItems().size() - 1);
//            ((List) mMultiTypeAdapter.getItems()).add(new FootVo(STATE_NOMORE));
//            mMultiTypeAdapter.notifyItemRangeChanged(mMultiTypeAdapter.getItems().size() - 1, mMultiTypeAdapter.getItems().size());
//
//        } else {
//            mMultiTypeAdapter.getItems().remove(mMultiTypeAdapter.getItems().size() - 1 - list.size());
//            if (isNoMore) {
//                ((List) mMultiTypeAdapter.getItems()).add(new FootVo(STATE_NOMORE));
//            } else {
//                ((List) mMultiTypeAdapter.getItems()).add(new FootVo(STATE_LOADING));
//            }
//            mMultiTypeAdapter.notifyItemRangeChanged(mMultiTypeAdapter.getItems().size() - list.size() - 1, mMultiTypeAdapter.getItems().size());
//
//        }
//        isLoading = true;
//        isLoadMore = false;
//    }
//
//
//    public void notifyItemRangeChanged(int positionStart, int itemCount) {
//        mMultiTypeAdapter.notifyItemRangeChanged(positionStart, itemCount);
//
//    }
//
//    public void notifyItemChanged(int position) {
//        mMultiTypeAdapter.notifyItemChanged(position);
//    }
//
//    /**
//     * set adapter
//     */
//    @Override
//    public void setAdapter(Adapter adapter) {
////        checkNotNull(adapter);
////        this.mMultiTypeAdapter = (MultiTypeAdapter) adapter;
////        super.setAdapter(adapter);
////        TypePool mTypePool = mMultiTypeAdapter.getTypePool();
////        for (int i = 0; i < mTypePool.size(); i++) {
////            if (mTypePool.getItemViewBinder(i) instanceof AbsFootView) {
////                setLoadingMoreEnabled(true);
////            } else if (mTypePool.getItemViewBinder(i) instanceof AbsHeaderView) {
////                AbsHeaderView mHeaderItemView = (AbsHeaderView) mTypePool.getItemViewBinder(i);
////                mRefreshHeader = mHeaderItemView.getRefreshHeaderView();
////                pullRefreshEnabled = true;
////            }
////        }
//    }
//
//    public void setLoadingMoreEnabled(boolean enabled) {
//        loadingMoreEnabled = enabled;
//    }
//
//    @Override
//    public boolean onTouchEvent(MotionEvent ev) {
//        if (mLastY == -1) {
//            mLastY = ev.getRawY();
//        }
//        switch (ev.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                mLastY = ev.getRawY();
//                break;
//            case MotionEvent.ACTION_MOVE:
//                final float deltaY = (ev.getRawY() - mLastY) / DRAG_RATE;
//                mLastY = ev.getRawY();
//                if (isOnTop() && pullRefreshEnabled && !mRefreshing) {
//                    mRefreshHeader.onMove(deltaY);
//                    if (mRefreshHeader.getVisibleHeight() > 0) {
//                        return false;
//                    }
//                }
//                break;
//            default:
//                mLastY = -1;
//                if (isOnTop() && pullRefreshEnabled && !mRefreshing) {
//                    if (mRefreshHeader.releaseAction()) {
//                        if (mOnRefreshListener != null) {
//                            mRefreshing = true;
//                            mOnRefreshListener.onRefresh();
//                        }
//                    }
//                }
//                break;
//        }
//        return super.onTouchEvent(ev);
//    }
//
//
//    @Override
//    public void onScrolled(int dx, int dy) {
//        super.onScrolled(dx, dy);
////        if (mOnScrollListener != null) {
////            mOnScrollListener.onScrolled(dx, dy);
////        }
////        int mAdapterCount = mMultiTypeAdapter.getItemCount();
//        LayoutManager layoutManager = getLayoutManager();
//        if (layoutManagerType == null) {
//            if (layoutManager instanceof LinearLayoutManager) {
//                layoutManagerType = LayoutManagerType.LinearLayout;
//            } else if (layoutManager instanceof GridLayoutManager) {
//                layoutManagerType = LayoutManagerType.GridLayout;
//            } else if (layoutManager instanceof StaggeredGridLayoutManager) {
//                layoutManagerType = LayoutManagerType.StaggeredGridLayout;
//            } else {
//                throw new RuntimeException(
//                        "Unsupported LayoutManager used. Valid ones are LinearLayoutManager, GridLayoutManager and StaggeredGridLayoutManager");
//            }
//        }
//        switch (layoutManagerType) {
//            case LinearLayout:
//                lastVisibleItemPosition = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition() + 1;
//                break;
//            case GridLayout:
//                lastVisibleItemPosition = ((GridLayoutManager) layoutManager).findLastVisibleItemPosition();
//                break;
//            case StaggeredGridLayout:
//                int[] into = new int[((StaggeredGridLayoutManager) layoutManager).getSpanCount()];
//                ((StaggeredGridLayoutManager) layoutManager).findLastVisibleItemPositions(into);
//                lastVisibleItemPosition = findMax(into) + 1;
//                break;
//            default:
//                break;
//        }
//
//        isBottom = mAdapterCount == lastVisibleItemPosition;
//        if (mOnRefreshListener != null && loadingMoreEnabled && !mRefreshing && isBottom && isLoading) {
//            mRefreshing = false;
//            isLoading = false;
//            if (!isNoMore) {
//                isLoadMore = true;
//            }
//        }
//
//    }
//
//    @Override
//    public void onScrollStateChanged(int state) {
//        super.onScrollStateChanged(state);
//        if (isLoadMore && state == RecyclerView.SCROLL_STATE_IDLE && isBottom) {
//            if (mOnRefreshListener != null) {
//                mOnRefreshListener.onLoadMore();
//            }
//        }
//        if (mOnScrollStateListener != null) {
//            mOnScrollStateListener.onScrollStateChanged(state);
//        }
//        if (mOnScrollListener != null) {
//            mOnScrollListener.onScrollStateChanged(state);
//        }
//    }
//
//    private int findMax(int[] lastPositions) {
//        int max = lastPositions[0];
//        for (int value : lastPositions) {
//            if (value > max) {
//                max = value;
//            }
//        }
//        return max;
//    }
//
//    private boolean isOnTop() {
//        return mRefreshHeader != null && mRefreshHeader.getParent() != null;
//    }
//
//
//    /**
//     * RecyclerView type
//     */
//    protected LayoutManagerType layoutManagerType;
//
//    public enum LayoutManagerType {
//        LinearLayout,
//        StaggeredGridLayout,
//        GridLayout
//    }
//
////    public void addOnScrollStateListener(OnScrollStateListener listener) {
////        mOnScrollStateListener = listener;
////    }
////
////    public void addOnRefreshListener(OnRefreshListener listener) {
////        mOnRefreshListener = listener;
////    }
////
////    public void addOnTScrollListener(OnTScrollListener onScrollListener) {
////        mOnScrollListener = onScrollListener;
////    }
//
//}
