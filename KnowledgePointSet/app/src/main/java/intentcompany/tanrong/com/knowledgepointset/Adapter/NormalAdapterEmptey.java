package intentcompany.tanrong.com.knowledgepointset.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
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

public class NormalAdapterEmptey extends RecyclerView.Adapter<NormalAdapterEmptey.VH>{

    private List<ObjectModel> mDatas;


    public NormalAdapterEmptey(List<ObjectModel> data) {
        this.mDatas = data;
    }
    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    @Override
    public NormalAdapterEmptey.VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_touchhelper, parent, false);
        return new VH(v);
    }



    @Override
    public void onBindViewHolder(NormalAdapterEmptey.VH holder, int position) {
        ObjectModel model = mDatas.get(position);
        holder.title.setText(model.title);
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