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
    private int currentColor;
    private ItemClickListener listener;

    public ItemAdapter(Context context, List<Item> list, ItemClickListener listener) {
        this.list = list;
        this.context = context;
        //currentItemId = AS.getMyDetail(context).currentItemId;
        this.listener = listener;
    }

    public interface ItemClickListener {
        void onItemClick(String ItemId);
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
        int color=0;
        try {
            if (currentColor==0) {
                color=android.R.color.holo_blue_bright;
                currentColor++;
            } else if (currentColor==1) {
                color=android.R.color.holo_orange_light;
                currentColor++;
            } else if (currentColor==2) {
                color=android.R.color.holo_green_light;
                currentColor++;
            } else {
                color=android.R.color.holo_red_light;
                currentColor=0;
            }
            holder.divider.setBackgroundColor(context.getResources().getColor(color));

            Picasso.with(context)
                    .load(item.getProfileAva())
                    .placeholder(R.drawable.placeholder)
                    .error(R.drawable.placeholder)
                    .into(holder.userAva);
            holder.tvUserName.setText(item.getProfileName());
            holder.tvUserName.setTextColor(context.getResources().getColor(color));
            holder.tvTimeStamp.setText(item.getTimeStamp());
            Picasso.with(context)
                    .load(item.getImage())
                    .placeholder(R.drawable.placeholder2)
                    .error(R.drawable.placeholder2)
                    .into(holder.imgage);
            holder.tvLikeCount.setText(item.getLikeCount());
            holder.tvLikeCount.setTextColor(context.getResources().getColor(color));
            holder.tvUsernameCmt1.setText(item.getLatestChild1().getName());
            holder.tvUsernameCmt1.setTextColor(context.getResources().getColor(color));
            holder.tvCmt1.setText(item.getLatestChild1().getComment());
            holder.tvUsernameCmt2.setText(item.getLatestChild2().getName());
            holder.tvUsernameCmt2.setTextColor(context.getResources().getColor(color));
            holder.tvCmt2.setText(item.getLatestChild2().getComment());

            holder.viewAllComments.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(item.getId());
                }
            });
        } catch (NullPointerException e){
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }
}

