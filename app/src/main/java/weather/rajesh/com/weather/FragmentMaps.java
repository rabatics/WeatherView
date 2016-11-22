package weather.rajesh.com.weather;

import android.app.Dialog;
import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

/**
 * Created by rajesh on 10/14/2016.
 */
public class FragmentMaps extends Fragment implements OnMapReadyCallback{
    private GoogleMap gm;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private double latit;
    private double longit;
    @Override

    public void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);

    }

    public FragmentMaps(){

    }

    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View dataview = inflater.inflate(R.layout.fragment_maps, container, false);
        if(gServAvailable()){
            pref= PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());
            latit=Double.parseDouble(pref.getString("curLat", "37.776289"));
            longit=Double.parseDouble(pref.getString("curLong","-122.395234"));
            Toast.makeText(getActivity(),"Maps is up and running!",Toast.LENGTH_LONG);
            initMap();
        }
        else{

        }
return dataview;
    }

    private void initMap(){
        SupportMapFragment mf=(SupportMapFragment)getChildFragmentManager().findFragmentById(R.id.map);
        mf.getMapAsync(this);
    }

    public boolean gServAvailable(){
        GoogleApiAvailability api=GoogleApiAvailability.getInstance();
        int avail=api.isGooglePlayServicesAvailable(getActivity());
        if(avail== ConnectionResult.SUCCESS){
            return true;
        }
        else if(api.isUserResolvableError(avail)){
            Dialog dial= api.getErrorDialog(getActivity(),avail,0);
            dial.show();

        }else{
            Toast.makeText(getActivity(),"Cant Connect to Play Services",Toast.LENGTH_LONG);
        }
        return false;
    }







    @Override
    public void onMapReady(GoogleMap map){
        gm=map;
        goToLocationZoom(latit,longit,15);
    }

    public void goToLocationZoom(double lat,double lon,float zoom){
        LatLng ll=new LatLng(lat,lon);
        CameraUpdate update= CameraUpdateFactory.newLatLngZoom(ll,zoom);
        Geocoder gc=new Geocoder(getActivity());

        JSONObject ob=((NavDrawer)getActivity()).getCurrentWeather();
        String wind = "";
        String fl = "";
        String st = "";

        if(ob!=null) {
            Log.v("weather check:", ob.toString());


            try {

                String condn = ob.getString("icon");


                String windmph = ob.getString("wind_mph") + " mph";
                String windkph = ob.getString("wind_kph") + " kph";
                wind = windmph;
                int cf = ((NavDrawer) getActivity()).getMode_cf();
                if (cf == 0) {
                    st = ob.getString("temp_c") + " " + (char) 0x00B0 + "C";
                    fl = ob.getString("feelslike_c") + " " + (char) 0x00B0 + "C";

                } else {
                    st =  ob.getString("temp_f") + " " + (char) 0x00B0 + "F";
                    fl = ob.getString("feelslike_f") + " " + (char) 0x00B0 + "F";
                }
            } catch (JSONException e) {

            }

        }






        String location=((NavDrawer) getActivity()).curCt;
        MarkerOptions options=new MarkerOptions().title(location).snippet("Feels Like:"+fl+"\nTemp:"+st+"\nWind Speed:"+wind).position(new LatLng(latit,longit));
        gm.addMarker(options);
        gm.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {

            @Override
            public View getInfoWindow(Marker arg0) {
                return null;
            }

            @Override
            public View getInfoContents(Marker marker) {

                Context context = getActivity(); //or getActivity(), YourActivity.this, etc.

                LinearLayout info = new LinearLayout(context);
                info.setOrientation(LinearLayout.VERTICAL);
                // info.setBackgroundColor(Color.BLACK);
                info.setPadding(0, 0, 0, 0);
                LinearLayout.LayoutParams infoparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                infoparams.setMargins(0, 0, 0, 0);
                info.setLayoutParams(infoparams);
                TextView title = new TextView(context);
                title.setTextColor(Color.BLACK);
                title.setGravity(Gravity.CENTER);
                title.setTypeface(null, Typeface.BOLD);
                title.setText(marker.getTitle());




                        /*  PendingIntent pi = PendingIntent.getActivity(this, 0,
            new Intent(this, SMS.class), 0);
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phoneNumber, null, message, pi, null);     */







                TextView snippet = new TextView(context);
                snippet.setTextColor(Color.BLUE);
                snippet.setText(marker.getSnippet());

                info.addView(title);
                info.addView(snippet);
             //   info.addView(alert);
                return info;
            }
        });
        gm.moveCamera(update);
    }
}
