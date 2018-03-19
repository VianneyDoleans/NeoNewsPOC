package neonews.neonews;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
                .url("https://api.apify.com/v1/hBRgTbxWsQsDGhbN2/crawlers/z7b9bosPdBp48RFbZ/lastExec/results?token=jsutssvK2SP4s7YP4ns5BoWSQ")
                .build();
        Response response = client.newCall(request).execute();
        String jsonData = response.body().string();
        JSONObject Jobject = new JSONArray(jsonData).getJSONObject(0);
        JSONArray Jarray = Jobject.getJSONArray("pageFunctionResult");
        for (int i = 0; i < Jarray.length(); i++) {
            JSONObject object = Jarray.getJSONObject(i);

            List<SubjectMedia> mediaFirst = new ArrayList<>();
            mediaFirst.add(new SubjectMedia(
                    "https://upload.wikimedia.org/wikipedia/fr/b/b1/CNews_Matin_logo.png",
                    "Cnews matin",
                    SubjectMedia.MediaType.NEWSPAPER,
                    object.getString("link")));

            Subject subject = new Subject(object.getString("image"), object.getString("category"), object.getString("title"), null, 50.633333, 3.066667, getDateTime(object.getString("hours")));
            subject.setListMedia(mediaFirst);
            listSubject.add(subject);
        }
        return listSubject;
    }

    public static ArrayList<Subject> getLeMonde() throws IOException, JSONException {
        ArrayList<Subject> listSubject = new ArrayList<>();
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.apify.com/v1/hBRgTbxWsQsDGhbN2/crawlers/EnL9LkmfjZefXA4mC/lastExec/results?token=LSGgGq9pkvgzjtobsz9JgzJFL")
                .build();
        Response response = client.newCall(request).execute();
        String jsonData = response.body().string();
        JSONObject Jobject = new JSONArray(jsonData).getJSONObject(0);
        JSONArray Jarray = Jobject.getJSONArray("pageFunctionResult");
        for (int i = 0; i < Jarray.length(); i++) {
            JSONObject object = Jarray.getJSONObject(i);

            List<SubjectMedia> mediaFirst = new ArrayList<>();
            mediaFirst.add(new SubjectMedia(
                    "http://img.over-blog-kiwi.com/1/26/05/85/20150505/ob_c6df29_url.png",
                    "Le monde",
                    SubjectMedia.MediaType.NEWSPAPER,
                    object.getString("link")));

            Subject subject = new Subject(object.getString("image"), object.getString("title"), "", null, 50.633333, 3.066667, getDateTime(object.getString("hours")));
            subject.setListMedia(mediaFirst);
            listSubject.add(subject);
        }
        return listSubject;
    }

    private static Date getDateTime(String time)
    {
        String times[] = time.split(":");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(times[0].replaceAll("\n", "")));
        calendar.set(Calendar.MINUTE, Integer.parseInt(times[1].replaceAll("\n", "")));
        return calendar.getTime();
    }
}
