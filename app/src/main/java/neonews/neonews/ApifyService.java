package neonews.neonews;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Guillaume on 15/03/2018.
 */

public class ApifyService {
    public static ArrayList<Subject> getCnewsMatin() throws IOException, JSONException {
        ArrayList<Subject> listSubject = new ArrayList<>();
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.apify.com/v1/oCrXFMhDsX8QPXbS8/crawlers/zqqPkiFdSPZorgN7i/lastExec/results?token=FXsnZsSzRMNQucmAnRqvco5TA")
                .build();
        Response response = client.newCall(request).execute();
        String jsonData = response.body().string();
        JSONObject Jobject = new JSONArray(jsonData).getJSONObject(0);
        JSONArray Jarray = Jobject.getJSONArray("pageFunctionResult");
        for (int i = 0; i < Jarray.length(); i++) {
            JSONObject object = Jarray.getJSONObject(i);
            listSubject.add(new Subject(object.getString("image"), object.getString("title"), "", null, 50.633333, 3.066667));
        }
        return listSubject;
    }
}
