package weather.rajesh.com.weather;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;

/**
 * Created by rajesh on 4/13/2016.
 */
public class GetWeather extends AsyncTask<String,Void,String> {
    private static String TAG = "myApp";
    private  ProgressDialog progressDialog;
    private Context mContext;
    private String state;
    private String city;
    // private Semaphore s=new Semaphore(0);

    GetWeather(Context context, String state,String city){

        this.mContext = context;
        this.state=state;
        this.city=city;
    }

    @Override

    protected String doInBackground(String... params) {

        String result = "";

        try {
           // Calendar rightnow= Calendar.getInstance();

            URL url = new URL("http://api.wunderground.com/api/"+mContext.getResources().getString(R.string.key).toString()+"/forecast/q/"+this.state+"/"+this.city+".json");

            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            Log.v("Error check","f1 "+this.city+","+this.state);
            InputStream responseStream = urlConnection.getInputStream();
            if(responseStream!=null) {
                InputStream in = new BufferedInputStream(responseStream);

                StringBuffer sb = new StringBuffer();

                BufferedReader br = new BufferedReader(new InputStreamReader(in));
                Log.v("Error check","f2");
                String read;

                while ((read = br.readLine()) != null) {

                    sb.append(read);

                }

                br.close();

                result = sb.toString();
            }

        }catch (MalformedURLException e) {
            // Replace this with your exception handling
       /*     e.printStackTrace();
            progressDialog.setMessage("Could not connect to server");
            progressDialog.cancel();*/

        } catch (Exception e) {

            Log.d(TAG, "Error: " + e.toString());
         /*   progressDialog.setMessage("Could not connect to server");
            progressDialog.cancel();*/


        }

        return result;

    }



    @Override
    protected void onPreExecute() {
       /* try {
            s.acquire();
        }catch(InterruptedException e){

        }*/
        progressDialog = new ProgressDialog(
                mContext);
        progressDialog.setMessage("Getting Weather......");
        progressDialog.setCancelable(false);
        progressDialog.show();

    }

    protected void onPostExecute(String data){
        JSONObject o = new JSONObject();
        progressDialog.cancel();
        Log.v("Error check", "f3");

        try {
            if(!data.contentEquals("")) {
                o = new JSONObject(data);
              //  JSONObject err=o.getJSONObject("response").getJSONObject("error");

                    JSONObject f = o.optJSONObject("forecast");
                    JSONObject n = f.optJSONObject("simpleforecast");

                ((NavDrawer) this.mContext).addLocation(this.city+","+this.state,n);
                    ((NavDrawer) this.mContext).setWeather(n);
                    Log.v("Error check", "f4");


            }
            else{
                o = new JSONObject();
                o.put("simpleforecast", new JSONObject());
                Log.v("Error check", "f5");
             //   ((LoginActivity) this.mContext).setStatus(o.getInt("status"));
             //   ((LoginActivity)this.mContext).goAhead();
            }
        }
        catch(JSONException e){
            Log.v(TAG, "Error = "+e.toString());

            try {

                o.put("simpleforecast",new JSONObject());
                Log.v("Error check", "f6");
              //  ((LoginActivity) this.mContext).setStatus(o.getInt("status"));
             //   ((LoginActivity)this.mContext).goAhead();


            }catch(JSONException e1){
                Log.v(TAG, "Error = ");
            }
        }
        Log.v(TAG, "data = " + data);

//s.release();
    }


}
