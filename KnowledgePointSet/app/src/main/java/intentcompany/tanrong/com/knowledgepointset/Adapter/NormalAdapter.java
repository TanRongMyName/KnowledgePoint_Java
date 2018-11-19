package intentcompany.tanrong.com.knowledgepointset.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import intentcompany.tanrong.com.knowledgepointset.Model.ObjectModel;
import intentcompany.tanrong.com.knowledgepointset.R;

/**
 * Created by admin on 2018/8/7.
 */

public class NormalAdapter extends RecyclerView.Adapter<NormalAdapter.VH>{

    private List<ObjectModel> mDatas;
    private OnStartDragListener mListener;

    public interface OnStartDragListener{
        void startDrag(RecyclerView.ViewHolder holder);
    }
    public NormalAdapter(List<ObjectModel> data, OnStartDragListener listener) {
        this.mDatas = data;
        mListener = listener;
    }

    @Override
    public void onBindViewHolder(final VH holder, int position) {
        ObjectModel model = mDatas.get(position);
        holder.title.setText(model.title);
        holder.number.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    mListener.startDrag(holder);
                }
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_touchhelper, parent, false);
        return new VH(v);
    }

    public static class VH extends RecyclerView.ViewHolder{
        public final TextView title;
        public final ImageView number;
        public VH(View v) {
            super(v);
            title = (TextView) v.findViewById(R.id.title);
            number = (ImageView) v.findViewById(R.id.icon);
        }
    }
}