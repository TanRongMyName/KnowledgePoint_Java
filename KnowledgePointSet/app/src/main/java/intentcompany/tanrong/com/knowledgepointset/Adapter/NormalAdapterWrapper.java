package intentcompany.tanrong.com.knowledgepointset.Adapter;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by admin on 2018/8/6.
 */

public class NormalAdapterWrapper extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    enum ITEM_TYPE {
        HEADER,
        FOOTER,
        NORMAL
    }

    private QuickAdapter mAdapter;
    private View mHeaderView;
    private View mFooterView;
    //用来加载更多数据
    private OnLoadMoreListener onloadMoreListener;
    //实现加载更多接口
    public void setOnloadMoreListener(final OnLoadMoreListener onloadMoreListener, RecyclerView recyclerView) {
        this.onloadMoreListener = onloadMoreListener;
        if(recyclerView != null && onloadMoreListener != null)
        {
            recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                    super.onScrollStateChanged(recyclerView, newState);
                }

                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    if(isSlideToBottom(recyclerView))
                    {
                        if(onloadMoreListener != null)
                        {
                            onloadMoreListener.onLoadMore();
                        }
                    }
                }
            });
        }
    }
    /**
     * 处理当时Gridview类型的效果时，也把头部和尾部设置成一整行（这就是RecyclerView的其中一个优秀之处，列表的每行可以不同数量的列）
     * @param recyclerView
     */
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        final RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if(layoutManager instanceof GridLayoutManager)
        {
            /**
             * getSpanSize的返回值的意思是：position位置的item的宽度占几列
             * 比如总的是4列，然后头部全部显示的话就应该占4列，此时就返回4
             * 其他的只占一列，所以就返回1，剩下的三列就由后面的item来依次填充。
             */
            ((GridLayoutManager) layoutManager).setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    if(mHeaderView != null && mFooterView != null)
                    {
                        if(position == 0)
                        {
                            return ((GridLayoutManager) layoutManager).getSpanCount();
                        }
                        else if(position == getItemCount() - 1) {
                            return ((GridLayoutManager) layoutManager).getSpanCount();
                        }
                        else
                        {
                            return 1;
                        }
                    }
                    else if(mHeaderView != null) {
                        if (position == 0) {
                            return ((GridLayoutManager) layoutManager).getSpanCount();
                        }
                        return 1;
                    }
                    else if(mFooterView != null)
                    {
                        if(position == getItemCount() - 1)
                        {
                            return ((GridLayoutManager) layoutManager).getSpanCount();
                        }
                        return 1;
                    }
                    return 1;
                }
            });
        }
    }

    /**
     * 判断是否到底部了
     * @param recyclerView
     * @return
     */
    protected boolean isSlideToBottom(RecyclerView recyclerView) {
        if (recyclerView == null) return false;
        if (recyclerView.computeVerticalScrollExtent() + recyclerView.computeVerticalScrollOffset() >= recyclerView.computeVerticalScrollRange())
            return true;
        return false;
    }

    /**
     * 加载更多回调接口
     */
    public interface OnLoadMoreListener
    {
        void onLoadMore();
    }


    public NormalAdapterWrapper(QuickAdapter adapter) {
        mAdapter = adapter;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return ITEM_TYPE.HEADER.ordinal();
        } else if (position == mAdapter.getItemCount() + 1) {
            return ITEM_TYPE.FOOTER.ordinal();
        } else {
            return ITEM_TYPE.NORMAL.ordinal();
        }
    }

    @Override
    public int getItemCount() {
        return mAdapter.getItemCount() + 2;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position == 0) {
            return;
        } else if (position == mAdapter.getItemCount() + 1) {
            return;
        } else {
            mAdapter.onBindViewHolder(((QuickAdapter.VH) holder), position - 1);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_TYPE.HEADER.ordinal()) {
            return new RecyclerView.ViewHolder(mHeaderView) {
            };
        } else if (viewType == ITEM_TYPE.FOOTER.ordinal()) {
            return new RecyclerView.ViewHolder(mFooterView) {
            };
        } else {
            return mAdapter.onCreateViewHolder(parent, viewType);
        }
    }

    public void addHeaderView(View view) {
        this.mHeaderView = view;
    }

    public void addFooterView(View view) {
        this.mFooterView = view;
    }

}
