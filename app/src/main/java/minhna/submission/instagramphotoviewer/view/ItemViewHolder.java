package minhna.submission.instagramphotoviewer.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedImageView;

import butterknife.Bind;
import butterknife.ButterKnife;
import minhna.submission.instagramphotoviewer.R;

/**
 * Created by Administrator on 20-Jan-16.
 */
public class ItemViewHolder extends RecyclerView.ViewHolder {

    @Bind(R.id.tv_view_all_cmt)
    protected TextView viewAllComments;
    @Bind(R.id.divider)
    protected View divider;
    @Bind(R.id.userAva)
    protected RoundedImageView userAva;
    @Bind(R.id.userName)
    protected TextView tvUserName;
    @Bind(R.id.timestamp)
    protected TextView tvTimeStamp;
    @Bind(R.id.graphic)
    protected ImageView imgage;
    @Bind(R.id.tv_like_count)
    protected TextView tvLikeCount;
    @Bind(R.id.username_cmt1)
    protected TextView tvUsernameCmt1;
    @Bind(R.id.cmt1)
    protected TextView tvCmt1;
    @Bind(R.id.username_cmt2)
    protected TextView tvUsernameCmt2;
    @Bind(R.id.cmt2)
    protected TextView tvCmt2;

    public ItemViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

}
