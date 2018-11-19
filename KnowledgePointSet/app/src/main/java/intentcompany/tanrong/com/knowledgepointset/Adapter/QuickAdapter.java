package intentcompany.tanrong.com.knowledgepointset.Adapter;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2018/7/30.
 * 万能的布局适配器
 */

public abstract  class QuickAdapter<T>extends RecyclerView.Adapter<QuickAdapter.VH> {

    public static class VH extends RecyclerView.ViewHolder{
        private SparseArray<View> mViews;
        private View mConvertView;

        private VH(View v){
            super(v);
            mConvertView = v;
            mViews = new SparseArray<>();
        }

        public static VH get(ViewGroup parent, int layoutId){
            View convertView = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
            return new VH(convertView);
        }

        public <T extends View> T getView(int id){
            View v = mViews.get(id);
            if(v == null){
                v = mConvertView.findViewById(id);
                mViews.put(id, v);
            }
            return (T)v;
        }

        public TextView setText(int id, String value){
            TextView view = getView(id);
            view.setText(value);
            return view;
        }
    }


    private List<T> mDatas;
    public QuickAdapter(List<T> datas){
        this.mDatas = datas;
    }

    public abstract int getLayoutId(int viewType);

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        return VH.get(parent,getLayoutId(viewType));
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        convert(holder, mDatas.get(position), position);
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public abstract void convert(VH holder, T data, int position);

    //在指定位置插入，原位置的向后移动一格
    //
    public boolean addItem(int position, T data) {
        if (position < mDatas.size() && position >= 0) {
            mDatas.add(position, data);
            notifyItemInserted(position);
            return true;
        }
        return false;
    }
    //末尾添加元素
    public void addItemLast(T data) {
        if(mDatas == null) {
            mDatas = new ArrayList<>();
        }
        mDatas.add(mDatas.size(), data);
        ////更新数据集不是用adapter.notifyDataSetChanged()而是notifyItemInserted(position)与notifyItemRemoved(position) 否则没有动画效果。
        notifyItemInserted(mDatas.size()-1);
    }

    //去除指定位置的子项
    public boolean removeItem(int position) {
        if (position < mDatas.size() && position >= 0) {
            mDatas.remove(position);
            notifyItemRemoved(position);
            return true;
        }
        return false;
    }
    //删除末尾的元素
    public boolean removeItemLast() {
        if (mDatas!=null&&mDatas.size()>0) {
            mDatas.remove(mDatas.size()-1);
            notifyItemRemoved(mDatas.size());
            return true;
        }
        return false;
    }

    //清空显示数据
    public void clearAll() {
        mDatas.clear();
        notifyDataSetChanged();
    }



    //使用说明 简单实用
//    mAdapter = new QuickAdapter<String>(data) {
//        @Override
//        public int getLayoutId(int viewType) {
//            return R.layout.item;
//        }
//
//        @Override
//        public void convert(VH holder, String data, int position) {
//            holder.setText(R.id.text, data);
//            //holder.itemView.setOnClickListener(); 此处还可以添加点击事件
//        }
//    };
//多样式使用
   /* mAdapter = new QuickAdapter<Model>(data) {
        @Override
        public int getLayoutId(int viewType) {
            switch(viewType){
                case TYPE_1:
                    return R.layout.item_1;
                case TYPE_2:
                    return R.layout.item_2;
            }
        }

        @Override
        public int getItemViewType(int position) {
            if(position % 2 == 0){
                return TYPE_1;
            } else{
                return TYPE_2;
            }
        }

        @Override
        public void convert(VH holder, Model data, int position) {
            int type = getItemViewType(position);
            switch(type){
                case TYPE_1:
                    holder.setText(R.id.text, data.text);
                    break;
                case TYPE_2:
                    holder.setImage(R.id.image, data.image);
                    break;
            }
        }
    };
*/


}
