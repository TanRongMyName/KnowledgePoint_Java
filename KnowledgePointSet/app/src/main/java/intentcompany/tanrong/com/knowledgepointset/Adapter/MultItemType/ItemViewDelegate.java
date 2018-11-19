package intentcompany.tanrong.com.knowledgepointset.Adapter.MultItemType;

import intentcompany.tanrong.com.knowledgepointset.SelfView.ViewHolder;

public interface ItemViewDelegate<T>
{

    int getItemViewLayoutId();

    boolean isForViewType(T item, int position);

    void convert(ViewHolder holder, T t, int position);

}