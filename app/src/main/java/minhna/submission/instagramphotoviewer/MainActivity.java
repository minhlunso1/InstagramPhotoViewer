package minhna.submission.instagramphotoviewer;

import android.support.v4.app.FragmentManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import minhna.submission.instagramphotoviewer.pojo.Item;
import minhna.submission.instagramphotoviewer.view.ItemAdapter;
import minhna.submission.instagramphotoviewer.view.ViewAllCommentsDialogFragment;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements ItemAdapter.ItemClickListener {

    @Bind(R.id.rvItems)
    RecyclerView recyclerView;
    @Bind(R.id.swipeRefeshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    private ServerClient serverClient;
    private ItemAdapter adapter;
    private MainActivity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        activity = (MainActivity) this;
        serverClient = new ServerClient();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onStart() {
        super.onStart();
        try {
            getData();
        } catch (Exception e) {
            e.printStackTrace();
        }

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Make sure you call swipeContainer.setRefreshing(false)
                // once the network request has completed successfully.
                try {
                    getData();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        // Configure the refreshing colors
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
    }

    public void getData() throws Exception {
        serverClient.getItems()
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
                        swipeRefreshLayout.setRefreshing(false);
                    }
                })
                .subscribe(new Action1<List<Item>>() {
                    @Override
                    public void call(List<Item> list) {
                        adapter = new ItemAdapter(activity, list, activity);
                        recyclerView.setAdapter(adapter);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {

                        throwable.printStackTrace();
                        Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void onItemClick(String itemId) {
        FragmentManager fm = getSupportFragmentManager();
        ViewAllCommentsDialogFragment fragment = ViewAllCommentsDialogFragment.newInstance(itemId);
        fragment.show(fm, "fragment_all_cmt");
    }
}


