package intentcompany.tanrong.com.knowledgepointset.Adapter.MultItemType;

import android.content.Context;

import java.util.List;

import intentcompany.tanrong.com.knowledgepointset.Model.ChatMessage;

public class ChatAdapterForRv extends MultiItemTypeAdapter<ChatMessage>
{
    public ChatAdapterForRv(Context context, List<ChatMessage> datas)
    {
        super(context, datas);

        addItemViewDelegate(new MsgSendItemDelagate());
        addItemViewDelegate(new MsgComingItemDelagate());
    }
}

