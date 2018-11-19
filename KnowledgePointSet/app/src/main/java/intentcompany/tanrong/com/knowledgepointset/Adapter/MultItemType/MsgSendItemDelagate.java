package intentcompany.tanrong.com.knowledgepointset.Adapter.MultItemType;

import intentcompany.tanrong.com.knowledgepointset.Model.ChatMessage;
import intentcompany.tanrong.com.knowledgepointset.R;
import intentcompany.tanrong.com.knowledgepointset.SelfView.ViewHolder;

public class MsgSendItemDelagate implements ItemViewDelegate<ChatMessage>
{

    @Override
    public int getItemViewLayoutId()
    {
        return R.layout.main_chat_send_msg;
    }

    @Override
    public boolean isForViewType(ChatMessage item, int position)
    {
        return !item.isComMeg();
    }

    @Override
    public void convert(ViewHolder holder, ChatMessage chatMessage, int position)
    {
        holder.setText(R.id.chat_send_content, chatMessage.getContent());
        holder.setText(R.id.chat_send_name, chatMessage.getName());
        holder.setImageResource(R.id.chat_send_icon, chatMessage.getIcon());
    }
}
