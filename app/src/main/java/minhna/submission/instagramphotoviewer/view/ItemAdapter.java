package minhna.submission.instagramphotoviewer.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.List;

import minhna.submission.instagramphotoviewer.R;
import minhna.submission.instagramphotoviewer.pojo.Item;

/**
 * Created by Administrator on 13-Mar-16.
 */
public class ItemAdapter extends RecyclerView.Adapter<ItemViewHolder> {
    private List<Item> list;
    private Context context;
    private long currentItemId;
    private ItemClickListener listener;

    public ItemAdapter(Context context, List<Item> list, ItemClickListener listener) {
        this.list = list;
        this.context = context;
        //currentItemId = AS.getMyDetail(context).currentItemId;
        this.listener = listener;
    }

    public interface ItemClickListener {
        void onItemClick(long ItemId);
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);

        ItemViewHolder viewHolder = new ItemViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        final Item item = list.get(position);

        Picasso.with(context)
                .load(item.getProfileAva())
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.placeholder)
                .into(holder.userAva);
        holder.tvUserName.setText(item.getProfileName());
        holder.tvTimeStamp.setText(item.getTimeStamp());
        Picasso.with(context)
                .load(item.getProfileAva())
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.placeholder)
                .into(holder.imgage);
        holder.tvLikeCount.setText(item.getLikeCount());
        holder.tvUsernameCmt1.setText(item.getLatestChild1().getName());
        holder.tvCmt1.setText(item.getLatestChild1().getComment());
        holder.tvUsernameCmt2.setText(item.getLatestChild2().getName());
        holder.tvCmt2.setText(item.getLatestChild2().getComment());
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }
}

