package neonews.neonews;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
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

public class NewsParser {
    public static ArrayList<Subject> getAllNewsFromFile(Context ctx) throws IOException, JSONException {
        ArrayList<Subject> listSubject = new ArrayList<>();
        JSONObject Jobject = new JSONArray(loadJSONFromAsset(ctx)).getJSONObject(0);
        JSONArray Jarray = Jobject.getJSONArray("Articles");
        for (int i = 0; i < Jarray.length(); i++) {
            JSONObject subjectObject = Jarray.getJSONObject(i);
            List<SubjectMedia> subjectMediaList = new ArrayList<>();
            Subject subject = new Subject(
                    subjectObject.getString("image"),
                    subjectObject.getString("description"),
                    subjectObject.getString("title"),
                    Double.parseDouble(subjectObject.getString("lat")),
                    Double.parseDouble(subjectObject.getString("lng")),
                    getDateTime(subjectObject.getString("hours")));

            JSONArray JarraySubject = subjectObject.getJSONArray("Medias");
            //Load media
            for (int j = 0; j < JarraySubject.length(); j++) {
                JSONObject mediaObject = JarraySubject.getJSONObject(j);
                SubjectMedia subjectMedia = new SubjectMedia(
                        mediaObject.getString("logo"),
                        mediaObject.getString("name"),
                        SubjectMedia.MediaType.valueOf(mediaObject.getString("type")),
                        mediaObject.getString("link")
                );
                subjectMediaList.add(subjectMedia);
            }
            subject.setListMedia(subjectMediaList);
            listSubject.add(subject);
        }
        return listSubject;
    }

    private static String loadJSONFromAsset(Context ctx) {
        String json = null;
        try {
            InputStream is = ctx.getAssets().open("news.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
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
