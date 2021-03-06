package intentcompany.tanrong.com.knowledgepointset.Adapter;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import java.util.Collections;
import java.util.List;

import intentcompany.tanrong.com.knowledgepointset.Model.ObjectModel;

/**
 * Created by admin on 2018/8/7.
 */

public class SimpleItemTouchCallback extends ItemTouchHelper.Callback {

    private NormalAdapter mAdapter;
    private List<ObjectModel> mData;
    public SimpleItemTouchCallback(NormalAdapter adapter, List<ObjectModel> data){
        mAdapter = adapter;
        mData = data;
    }

    //设置支持的拖拽、滑动的方向
    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        int dragFlag = ItemTouchHelper.UP | ItemTouchHelper.DOWN; //s上下拖拽
        int swipeFlag = ItemTouchHelper.START | ItemTouchHelper.END; //左->右和右->左滑动
        return makeMovementFlags(dragFlag,swipeFlag);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        int from = viewHolder.getAdapterPosition();
        int to = target.getAdapterPosition();
        Collections.swap(mData, from, to);
        mAdapter.notifyItemMoved(from, to);
        return true;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        int pos = viewHolder.getAdapterPosition();
        mData.remove(pos);
        mAdapter.notifyItemRemoved(pos);
    }

    //状态改变时回调
    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        super.onSelectedChanged(viewHolder, actionState);
        if(actionState != ItemTouchHelper.ACTION_STATE_IDLE){
            NormalAdapter.VH holder = (NormalAdapter.VH)viewHolder;
            holder.itemView.setBackgroundColor(0xffbcbcbc); //设置拖拽和侧滑时的背景色
        }
    }

    //拖拽或滑动完成之后调用，用来清除一些状态
    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);
        NormalAdapter.VH holder = (NormalAdapter.VH)viewHolder;
        holder.itemView.setBackgroundColor(0xffeeeeee); //背景色还原
    }
}


