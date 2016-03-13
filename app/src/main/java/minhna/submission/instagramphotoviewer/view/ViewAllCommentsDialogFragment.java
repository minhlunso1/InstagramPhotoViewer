package minhna.submission.instagramphotoviewer.view;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import minhna.submission.instagramphotoviewer.R;
import minhna.submission.instagramphotoviewer.ServerClient;
import minhna.submission.instagramphotoviewer.pojo.ChildItem;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 15-Feb-16.
 */
public class ViewAllCommentsDialogFragment extends DialogFragment {

    @Bind(R.id.rvCmt)
    RecyclerView recyclerView;
    @Bind(R.id.swipeRefeshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @Bind(R.id.btn_close)
    Button closeBtn;
    private CommentItemAdapter adapter;
    private Activity activity;

    public static ViewAllCommentsDialogFragment newInstance(String id) {
        ViewAllCommentsDialogFragment frag = new ViewAllCommentsDialogFragment();
        Bundle args = new Bundle();
        args.putString("id", id);
        frag.setArguments(args);

        return frag;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        return dialog;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.view_cmt_diag_frag, container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        getDialog().setTitle("All Comments");
    }

    @Override
    public void onStart() {
        super.onStart();
        activity = getActivity();
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                try {
                    getData();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        getData();
    }

    public void getData(){
        ServerClient serverClient = new ServerClient();
        serverClient.getAllComments(getArguments().getString("id", ""))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        if (!swipeRefreshLayout.isRefreshing()) {
                            swipeRefreshLayout.post(new Runnable() {
                                @Override
                                public void run() {
                                    swipeRefreshLayout.setRefreshing(true);
                                }
                            });
                        }
                    }
                })
                .doOnTerminate(new Action0() {
                    @Override
                    public void call() {
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                swipeRefreshLayout.setRefreshing(false);
                            }
                        }, 2000);
                    }
                })
                .subscribe(new Action1<List<ChildItem>>() {
                    @Override
                    public void call(List<ChildItem> list) {
                        adapter = new CommentItemAdapter(activity, list);
                        recyclerView.setAdapter(adapter);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        throwable.printStackTrace();
                        Toast.makeText(activity, "Error", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @OnClick({R.id.btn_close})
    public void close(){
        getDialog().dismiss();
    }

}
