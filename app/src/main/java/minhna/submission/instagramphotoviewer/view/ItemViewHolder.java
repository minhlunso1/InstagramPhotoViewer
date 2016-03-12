package minhna.submission.instagramphotoviewer.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedImageView;

import minhna.submission.instagramphotoviewer.R;

/**
 * Created by Administrator on 20-Jan-16.
 */
public class ItemViewHolder extends RecyclerView.ViewHolder {

    protected RoundedImageView userAva;
    protected TextView tvUserName;
    protected TextView tvTimeStamp;
    protected ImageView imgage;
    protected TextView tvLikeCount;
    protected TextView tvUsernameCmt1;
    protected TextView tvCmt1;
    protected TextView tvUsernameCmt2;
    protected TextView tvCmt2;

    public ItemViewHolder(View itemView) {
        super(itemView);
        this.userAva = (RoundedImageView) itemView.findViewById(R.id.userAva);
        this.tvUserName = (TextView) itemView.findViewById(R.id.userName);
        this.tvTimeStamp = (TextView) itemView.findViewById(R.id.timestamp);
        this.imgage = (ImageView) itemView.findViewById(R.id.graphic);
        this.tvLikeCount = (TextView) itemView.findViewById(R.id.tv_like_count);
        this.tvUsernameCmt1 = (TextView) itemView.findViewById(R.id.username_cmt1);
        this.tvUsernameCmt2 = (TextView) itemView.findViewById(R.id.username_cmt2);
        this.tvCmt1 = (TextView) itemView.findViewById(R.id.cmt1);
        this.tvCmt2 = (TextView) itemView.findViewById(R.id.cmt2);
    }

}
