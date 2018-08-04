package com.itdeveapps.omar.builditbigger;

import android.os.AsyncTask;
import android.util.Log;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.omar.builditbigger.backend.jokerApi.JokerApi;

import java.io.IOException;

/**
 * Created by Omar on 08-Jun-18 7:28 PM.
 */
public class JokerApiAsyncTask extends AsyncTask<Void, Void, String> {
    private static final String TAG = JokerApiAsyncTask.class.getSimpleName();

    private JokerApi api = null;
    private OnJokerAsyncTaskCompleted listiner;
    private static final String END_POINT = "http://10.0.2.2:8080/_ah/api/";

    public JokerApiAsyncTask(OnJokerAsyncTaskCompleted listener) {
        this.listiner = listener;
    }

    @Override
    protected String doInBackground(Void... args) {
        if (api == null) {
            JokerApi.Builder builder = new JokerApi.Builder(
                    AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(),
                    null)
                    .setRootUrl(END_POINT)
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            api = builder.build();
        }
        try {
            return api.tellAJoke().execute().getData();
        } catch (IOException e) {
            Log.e(TAG, "Error getting joke: ", e);
            return "";
        }
    }

    @Override
    protected void onPostExecute(String result) {
        listiner.onTaskCompleted(result);
    }

    public interface OnJokerAsyncTaskCompleted {
        void onTaskCompleted(String data);

    }
}
