package minhna.submission.instagramphotoviewer;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

    public String getREQUEST_API() {
        return REQUEST_API;
    }

    public OkHttpClient getClient() {
        return client;
    }

}
