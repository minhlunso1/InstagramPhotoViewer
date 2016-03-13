package minhna.submission.instagramphotoviewer.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedImageView;

import butterknife.Bind;
import butterknife.ButterKnife;
import minhna.submission.instagramphotoviewer.R;

/**
 * Created by Administrator on 13-Mar-16.
 */
public class CommentViewHolder extends RecyclerView.ViewHolder {

    @Bind(R.id.userAva)
    RoundedImageView userAva;
    @Bind(R.id.userName)
    TextView userName;
    @Bind(R.id.cmt1)
    TextView cmt1;


    public CommentViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
