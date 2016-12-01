package com.example.victor.sdi3_10cli_rest_android;

import android.os.AsyncTask;
import android.util.Log;

import com.example.victor.sdi3_10cli_rest_android.model.AddressPoint;
import com.example.victor.sdi3_10cli_rest_android.model.Trip;
import com.example.victor.sdi3_10cli_rest_android.model.Waypoint;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created sdi3-10.AndroidCli
 */
public class RESTTask  extends AsyncTask<String, String, Boolean> {

    private static String TAG = "ReceiveFeedTask";
    private  String jaxrsmessage = null;

    private static String DIRECCION_GET = "http://81.9.173.32:8280/sdi3-10.WEB/rest/TripsServiceRs/user1/user1";



    @Override
    protected Boolean doInBackground(String... params) {
        HttpClient httpclient = null;


        try {
            httpclient = new DefaultHttpClient();

            HttpGet request = new HttpGet(DIRECCION_GET);

            request.addHeader("Content-Type", "application/json");
            request.addHeader("Accept", "application/json");
            HttpResponse response = httpclient.execute(request);
            HttpEntity entity = response.getEntity();
            InputStream instream = entity.getContent();
            jaxrsmessage = read(instream);



            GsonBuilder builder = new GsonBuilder();
            builder.registerTypeAdapter(AddressPoint.class, new AddressPointDeserializer());

            JSONArray arr = new JSONArray(jaxrsmessage);
            for (int i = 0; i < arr.length(); i++) {
                JSONObject obj = (JSONObject) arr.get(i);
                Log.i(i+"", obj.toString());

                Gson gson = builder.create();
                Trip t = gson.fromJson(obj.toString(),Trip.class);
                Log.i("DES", t.toString());

            }




        } catch (ClientProtocolException e) {
            Log.e(TAG, "Se ha producido un error: " + e.toString() + "\nMensaje: " + e.getMessage());
        } catch (IOException e) {
            Log.e(TAG, "Se ha producido un error: " + e.toString() + "\nMensaje: " + e.getMessage());
        } catch (Exception e){
            Log.e(TAG, "Se ha producido un error: " + e.toString() + "\nMensaje: " + e.getMessage());
        }finally {
            httpclient.getConnectionManager().shutdown();
        }
        return true;
    }

    @Override
    protected void onPostExecute(Boolean result) {

    }

    private static String read(InputStream instream) {
        StringBuilder sb = null;
        try {
            sb = new StringBuilder();
            BufferedReader r = new BufferedReader(new InputStreamReader(
                    instream));
            for (String line = r.readLine(); line != null; line = r.readLine()) {
                sb.append(line);
            }

            instream.close();
            r.close();

        } catch (IOException e) {
            Log.e(TAG, "Se ha producido un error: " + e.toString() + "\nMensaje: " + e.getMessage());
        }
        return sb.toString();

    }
}
