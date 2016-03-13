package minhna.submission.instagramphotoviewer;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import minhna.submission.instagramphotoviewer.pojo.ChildItem;
import minhna.submission.instagramphotoviewer.pojo.Item;
import minhna.submission.instagramphotoviewer.util.MyUtils;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by Administrator on 13-Mar-16.
 */
public class ServerClient {
    private final String REQUEST_API = "https://api.instagram.com/v1/media/popular?client_id=e05c462ebd86446ea48a5af73769b602";
    private OkHttpClient client;
    private static String response;

    public ServerClient(){
        client = new OkHttpClient();
    }

    public String doGetRequest(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public Observable<List<String>> getNames() {
        return Observable.create(new Observable.OnSubscribe<List<String>>() {
            @Override
            public void call(Subscriber<? super List<String>> subscriber) {
                try {
                    String response = doGetRequest(getREQUEST_API());
                    JSONObject object = new JSONObject(response);
                    JSONArray data = object.getJSONArray("data");
                    List<String> names = new ArrayList<>();
                    for (int i = 0; i < data.length(); i++) {
                        JSONObject dataChild = data.getJSONObject(i);
                        JSONObject user = dataChild.getJSONObject("user");
                        String name = user.getString("username");
                        names.add(name);
                    }
                    subscriber.onNext(names);
                    subscriber.onCompleted();
                } catch (Exception exception) {
                    subscriber.onError(exception);
                }
            }
        });
    }

    public Observable<List<Item>> getItems() {
        return Observable.create(new Observable.OnSubscribe<List<Item>>() {
            @Override
            public void call(Subscriber<? super List<Item>> subscriber) {
                try {
                    response = doGetRequest(getREQUEST_API());
                    JSONObject object = new JSONObject(response);
                    JSONArray data = object.getJSONArray("data");
                    List<Item> list = new ArrayList<>();
                    for (int i = 0; i < data.length(); i++) {
                        Item item = new Item();
                        JSONObject dataChild = data.getJSONObject(i);
                        if (dataChild.getString("type").equals("image")) {
                            JSONObject user = dataChild.getJSONObject("user");

                            item.setId(dataChild.getString("id"));
                            item.setProfileName(user.getString("username"));
                            item.setProfileAva(user.getString("profile_picture"));
                            item.setTimeStamp(MyUtils.calculatePassTime(dataChild.getLong("created_time")));
                            item.setImage(dataChild.getJSONObject("images").getJSONObject("low_resolution").getString("url"));
                            item.setLikeCount(MyUtils.getLikeCount(dataChild.getJSONObject("likes").getLong("count")));

                            JSONArray commentData = dataChild.getJSONObject("comments").getJSONArray("data");
                            int commentDataLength = commentData.length();
                            if (commentDataLength > 0) {
                                JSONObject child1 = commentData.getJSONObject(commentDataLength - 1);
                                item.setLatestChild1(new ChildItem(child1.getJSONObject("from").getString("username"), child1.getString("text")));
                                if (commentDataLength > 1) {
                                    JSONObject child2 = commentData.getJSONObject(commentDataLength - 2);
                                    item.setLatestChild2(new ChildItem(child2.getJSONObject("from").getString("username"), child2.getString("text")));
                                }
                            }
                            list.add(item);
                        }
                    }
                    subscriber.onNext(list);
                    subscriber.onCompleted();
                } catch (Exception exception) {
                    subscriber.onError(exception);
                }
            }
        });
    }

    public Observable<List<ChildItem>> getAllComments(final String id) {
        return Observable.create(new Observable.OnSubscribe<List<ChildItem>>() {
            @Override
            public void call(Subscriber<? super List<ChildItem>> subscriber) {
                try {
                    JSONObject object = new JSONObject(response);
                    JSONArray data = object.getJSONArray("data");
                    List<ChildItem> list = new ArrayList<>();
                    for (int i = 0; i < data.length(); i++) {
                        JSONObject dataChild = data.getJSONObject(i);
                        if (dataChild.getString("id").equals(id)){
                            JSONArray commentData = dataChild.getJSONObject("comments").getJSONArray("data");
                            int commentDataLength = commentData.length();
                            if (commentDataLength > 0) {
                                for (int j=0;j<commentDataLength;j++){
                                    JSONObject comment = commentData.getJSONObject(j);
                                    JSONObject from = comment.getJSONObject("from");
                                    list.add(new ChildItem(from.getString("profile_picture"), from.getString("username"), comment.getString("text")));
                                }
                            }
                        }
                    }
                    subscriber.onNext(list);
                    subscriber.onCompleted();
                } catch (Exception exception) {
                    subscriber.onError(exception);
                }
            }
        });
    }

    public String getREQUEST_API() {
        return REQUEST_API;
    }

    public OkHttpClient getClient() {
        return client;
    }

}
