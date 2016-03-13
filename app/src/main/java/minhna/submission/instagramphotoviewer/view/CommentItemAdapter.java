package minhna.submission.instagramphotoviewer.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.List;

import minhna.submission.instagramphotoviewer.R;
import minhna.submission.instagramphotoviewer.pojo.ChildItem;

/**
 * Created by Administrator on 13-Mar-16.
 */
public class CommentItemAdapter extends RecyclerView.Adapter<CommentViewHolder> {
    private List<ChildItem> list;
    private Context context;

    public CommentItemAdapter(Context context, List<ChildItem> list) {
        this.list = list;
        this.context = context;
    }

    @Override
    public CommentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comment, parent, false);
        CommentViewHolder viewHolder = new CommentViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CommentViewHolder holder, int position) {
        final ChildItem item = list.get(position);
        try {
            Picasso.with(context)
                    .load(item.getAva())
                    .placeholder(R.drawable.placeholder)
                    .error(R.drawable.placeholder)
                    .into(holder.userAva);
            holder.userName.setText(item.getName());
            holder.cmt1.setText(item.getComment());
        } catch (NullPointerException e){
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }
}

