package intentcompany.tanrong.com.knowledgepointset.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import intentcompany.tanrong.com.knowledgepointset.R;

/**
 * Created by admin on 2018/7/30.
 * RecycleView 的导入
 * compile 'com.android.support:recyclerview-v7:26.0.0-alpha1'
 */

public class Main_Recycle_ItemAdapter extends RecyclerView.Adapter<Main_Recycle_ItemAdapter.MyViewHolder> {

    List<String> list;//存放数据
    Context context;
    //点击的接口  提出来让外部实现
    public interface EventSend{
        public void onClickEvent(String str);
    }
    public EventSend myeventSend;

    public Main_Recycle_ItemAdapter(List<String> list, Context context,EventSend eventsend) {
        this.list = list;
        this.context = context;
        this.myeventSend=eventsend;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.main_recycle_item_layout, parent, false));
        return holder;
    }

    //在这里可以获得每个子项里面的控件的实例，比如这里的TextView,子项本身的实例是itemView，
// 在这里对获取对象进行操作
    //holder.itemView是子项视图的实例，holder.textView是子项内控件的实例
    //position是点击位置
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        //设置textView显示内容为list里的对应项
        final String content=list.get(position);
        holder.textView.setText(content);
        //子项的点击事件监听
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Main_Recycle_ItemAdapter.this.myeventSend.onClickEvent(content);
            }
        });
    }

    //要显示的子项数量
    @Override
    public int getItemCount() {
        return list.size();
    }

    //这里定义的是子项的类，不要在这里直接对获取对象进行操作
    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textView;

        public MyViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
        }
    }

    /*之下的方法都是为了方便操作，并不是必须的*/

    //在指定位置插入，原位置的向后移动一格
    public boolean addItem(int position, String msg) {
        if (position < list.size() && position >= 0) {
            list.add(position, msg);
            notifyItemInserted(position);
            return true;
        }
        return false;
    }

    //去除指定位置的子项
    public boolean removeItem(int position) {
        if (position < list.size() && position >= 0) {
            list.remove(position);
            notifyItemRemoved(position);
            return true;
        }
        return false;
    }

    //清空显示数据
    public void clearAll() {
        list.clear();
        notifyDataSetChanged();
    }
}
