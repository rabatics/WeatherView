package weather.rajesh.com.weather;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.HashMap;

public class NavDrawer extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public JSONObject getWeather() {
        return weather;
    }

    public void setWeather(JSONObject weather) {
        this.weather = weather;
    }

    private JSONObject weather=new JSONObject();
    private String state;
    private String city;
        TextView navcity;
        TextView navstate;
        private SharedPreferences preferences;
        private SharedPreferences.Editor editor;

        public HashMap<String, JSONObject> getLocations() {
                return locations;
        }

        public void setLocations(HashMap<String, JSONObject> locations) {
                this.locations = locations;
        }

        public void addLocation(String loc,JSONObject weatherinfo){
                locations.put(loc,weatherinfo);
        }

        private HashMap<String,JSONObject> locations=new HashMap<>();
    String datas="{\n" +
            "  \"forecastday\": [{\n" +
            "  \"date\": {\n" +
            "  \"epoch\": \"1340776800\",\n" +
            "  \"pretty\": \"11:00 PM PDT on June 26, 2012\",\n" +
            "  \"day\": 26,\n" +
            "  \"month\": 6,\n" +
            "  \"year\": 2012,\n" +
            "  \"yday\": 177,\n" +
            "  \"hour\": 23,\n" +
            "  \"min\": \"00\",\n" +
            "  \"sec\": 0,\n" +
            "  \"isdst\": \"1\",\n" +
            "  \"monthname\": \"June\",\n" +
            "  \"weekday_short\": \"Tue\",\n" +
            "  \"weekday\": \"Tuesday\",\n" +
            "  \"ampm\": \"PM\",\n" +
            "  \"tz_short\": \"PDT\",\n" +
            "  \"tz_long\": \"America/Los_Angeles\"\n" +
            "  },\n" +
            "  \"period\": 1,\n" +
            "  \"high\": {\n" +
            "  \"fahrenheit\": \"68\",\n" +
            "  \"celsius\": \"20\"\n" +
            "  },\n" +
            "  \"low\": {\n" +
            "  \"fahrenheit\": \"50\",\n" +
            "  \"celsius\": \"10\"\n" +
            "  },\n" +
            "  \"conditions\": \"Partly Cloudy\",\n" +
            "  \"icon\": \"partlycloudy\",\n" +
            "  \"icon_url\": \"http://icons-ak.wxug.com/i/c/k/partlycloudy.gif\",\n" +
            "  \"skyicon\": \"mostlysunny\",\n" +
            "  \"pop\": 0,\n" +
            "  \"qpf_allday\": {\n" +
            "  \"in\": 0.00,\n" +
            "  \"mm\": 0.0\n" +
            "  },\n" +
            "  \"qpf_day\": {\n" +
            "  \"in\": 0.00,\n" +
            "  \"mm\": 0.0\n" +
            "  },\n" +
            "  \"qpf_night\": {\n" +
            "  \"in\": 0.00,\n" +
            "  \"mm\": 0.0\n" +
            "  },\n" +
            "  \"snow_allday\": {\n" +
            "  \"in\": 0,\n" +
            "  \"cm\": 0\n" +
            "  },\n" +
            "  \"snow_day\": {\n" +
            "  \"in\": 0,\n" +
            "  \"cm\": 0\n" +
            "  },\n" +
            "  \"snow_night\": {\n" +
            "  \"in\": 0,\n" +
            "  \"cm\": 0\n" +
            "  },\n" +
            "  \"maxwind\": {\n" +
            "  \"mph\": 21,\n" +
            "  \"kph\": 34,\n" +
            "  \"dir\": \"West\",\n" +
            "  \"degrees\": 272\n" +
            "  },\n" +
            "  \"avewind\": {\n" +
            "  \"mph\": 17,\n" +
            "  \"kph\": 27,\n" +
            "  \"dir\": \"West\",\n" +
            "  \"degrees\": 272\n" +
            "  },\n" +
            "  \"avehumidity\": 72,\n" +
            "  \"maxhumidity\": 94,\n" +
            "  \"minhumidity\": 58\n" +
            "  }, {\n" +
            "  \"date\": {\n" +
            "  \"epoch\": \"1340863200\",\n" +
            "  \"pretty\": \"11:00 PM PDT on June 27, 2012\",\n" +
            "  \"day\": 27,\n" +
            "  \"month\": 6,\n" +
            "  \"year\": 2012,\n" +
            "  \"yday\": 178,\n" +
            "  \"hour\": 23,\n" +
            "  \"min\": \"00\",\n" +
            "  \"sec\": 0,\n" +
            "  \"isdst\": \"1\",\n" +
            "  \"monthname\": \"June\",\n" +
            "  \"weekday_short\": \"Wed\",\n" +
            "  \"weekday\": \"Wednesday\",\n" +
            "  \"ampm\": \"PM\",\n" +
            "  \"tz_short\": \"PDT\",\n" +
            "  \"tz_long\": \"America/Los_Angeles\"\n" +
            "  },\n" +
            "  \"period\": 2,\n" +
            "  \"high\": {\n" +
            "  \"fahrenheit\": \"72\",\n" +
            "  \"celsius\": \"22\"\n" +
            "  },\n" +
            "  \"low\": {\n" +
            "  \"fahrenheit\": \"54\",\n" +
            "  \"celsius\": \"12\"\n" +
            "  },\n" +
            "  \"conditions\": \"Partly Cloudy\",\n" +
            "  \"icon\": \"partlycloudy\",\n" +
            "  \"icon_url\": \"http://icons-ak.wxug.com/i/c/k/partlycloudy.gif\",\n" +
            "  \"skyicon\": \"mostlysunny\",\n" +
            "  \"pop\": 0,\n" +
            "  \"qpf_allday\": {\n" +
            "  \"in\": 0.00,\n" +
            "  \"mm\": 0.0\n" +
            "  },\n" +
            "  \"qpf_day\": {\n" +
            "  \"in\": 0.00,\n" +
            "  \"mm\": 0.0\n" +
            "  },\n" +
            "  \"qpf_night\": {\n" +
            "  \"in\": 0.00,\n" +
            "  \"mm\": 0.0\n" +
            "  },\n" +
            "  \"snow_allday\": {\n" +
            "  \"in\": 0,\n" +
            "  \"cm\": 0\n" +
            "  },\n" +
            "  \"snow_day\": {\n" +
            "  \"in\": 0,\n" +
            "  \"cm\": 0\n" +
            "  },\n" +
            "  \"snow_night\": {\n" +
            "  \"in\": 0,\n" +
            "  \"cm\": 0\n" +
            "  },\n" +
            "  \"maxwind\": {\n" +
            "  \"mph\": 11,\n" +
            "  \"kph\": 18,\n" +
            "  \"dir\": \"WSW\",\n" +
            "  \"degrees\": 255\n" +
            "  },\n" +
            "  \"avewind\": {\n" +
            "  \"mph\": 9,\n" +
            "  \"kph\": 14,\n" +
            "  \"dir\": \"WSW\",\n" +
            "  \"degrees\": 252\n" +
            "  },\n" +
            "  \"avehumidity\": 70,\n" +
            "  \"maxhumidity\": 84,\n" +
            "  \"minhumidity\": 54\n" +
            "  }, {\n" +
            "  \"date\": {\n" +
            "  \"epoch\": \"1340949600\",\n" +
            "  \"pretty\": \"11:00 PM PDT on June 28, 2012\",\n" +
            "  \"day\": 28,\n" +
            "  \"month\": 6,\n" +
            "  \"year\": 2012,\n" +
            "  \"yday\": 179,\n" +
            "  \"hour\": 23,\n" +
            "  \"min\": \"00\",\n" +
            "  \"sec\": 0,\n" +
            "  \"isdst\": \"1\",\n" +
            "  \"monthname\": \"June\",\n" +
            "  \"weekday_short\": \"Thu\",\n" +
            "  \"weekday\": \"Thursday\",\n" +
            "  \"ampm\": \"PM\",\n" +
            "  \"tz_short\": \"PDT\",\n" +
            "  \"tz_long\": \"America/Los_Angeles\"\n" +
            "  },\n" +
            "  \"period\": 3,\n" +
            "  \"high\": {\n" +
            "  \"fahrenheit\": \"72\",\n" +
            "  \"celsius\": \"22\"\n" +
            "  },\n" +
            "  \"low\": {\n" +
            "  \"fahrenheit\": \"54\",\n" +
            "  \"celsius\": \"12\"\n" +
            "  },\n" +
            "  \"conditions\": \"Partly Cloudy\",\n" +
            "  \"icon\": \"mostlycloudy\",\n" +
            "  \"icon_url\": \"http://icons-ak.wxug.com/i/c/k/partlycloudy.gif\",\n" +
            "  \"skyicon\": \"partlycloudy\",\n" +
            "  \"pop\": 0,\n" +
            "  \"qpf_allday\": {\n" +
            "  \"in\": 0.00,\n" +
            "  \"mm\": 0.0\n" +
            "  },\n" +
            "  \"qpf_day\": {\n" +
            "  \"in\": 0.00,\n" +
            "  \"mm\": 0.0\n" +
            "  },\n" +
            "  \"qpf_night\": {\n" +
            "  \"in\": 0.00,\n" +
            "  \"mm\": 0.0\n" +
            "  },\n" +
            "  \"snow_allday\": {\n" +
            "  \"in\": 0,\n" +
            "  \"cm\": 0\n" +
            "  },\n" +
            "  \"snow_day\": {\n" +
            "  \"in\": 0,\n" +
            "  \"cm\": 0\n" +
            "  },\n" +
            "  \"snow_night\": {\n" +
            "  \"in\": 0,\n" +
            "  \"cm\": 0\n" +
            "  },\n" +
            "  \"maxwind\": {\n" +
            "  \"mph\": 14,\n" +
            "  \"kph\": 22,\n" +
            "  \"dir\": \"West\",\n" +
            "  \"degrees\": 265\n" +
            "  },\n" +
            "  \"avewind\": {\n" +
            "  \"mph\": 12,\n" +
            "  \"kph\": 19,\n" +
            "  \"dir\": \"WSW\",\n" +
            "  \"degrees\": 256\n" +
            "  },\n" +
            "  \"avehumidity\": 80,\n" +
            "  \"maxhumidity\": 91,\n" +
            "  \"minhumidity\": 56\n" +
            "  }, {\n" +
            "  \"date\": {\n" +
            "  \"epoch\": \"1341036000\",\n" +
            "  \"pretty\": \"11:00 PM PDT on June 29, 2012\",\n" +
            "  \"day\": 29,\n" +
            "  \"month\": 6,\n" +
            "  \"year\": 2012,\n" +
            "  \"yday\": 180,\n" +
            "  \"hour\": 23,\n" +
            "  \"min\": \"00\",\n" +
            "  \"sec\": 0,\n" +
            "  \"isdst\": \"1\",\n" +
            "  \"monthname\": \"June\",\n" +
            "  \"weekday_short\": \"Fri\",\n" +
            "  \"weekday\": \"Friday\",\n" +
            "  \"ampm\": \"PM\",\n" +
            "  \"tz_short\": \"PDT\",\n" +
            "  \"tz_long\": \"America/Los_Angeles\"\n" +
            "  },\n" +
            "  \"period\": 4,\n" +
            "  \"high\": {\n" +
            "  \"fahrenheit\": \"68\",\n" +
            "  \"celsius\": \"20\"\n" +
            "  },\n" +
            "  \"low\": {\n" +
            "  \"fahrenheit\": \"52\",\n" +
            "  \"celsius\": \"11\"\n" +
            "  },\n" +
            "  \"conditions\": \"Fog\",\n" +
            "  \"icon\": \"thunderstorm\",\n" +
            "  \"icon_url\": \"http://icons-ak.wxug.com/i/c/k/partlycloudy.gif\",\n" +
            "  \"skyicon\": \"mostlysunny\",\n" +
            "  \"pop\": 0,\n" +
            "  \"qpf_allday\": {\n" +
            "  \"in\": 0.00,\n" +
            "  \"mm\": 0.0\n" +
            "  },\n" +
            "  \"qpf_day\": {\n" +
            "  \"in\": 0.00,\n" +
            "  \"mm\": 0.0\n" +
            "  },\n" +
            "  \"qpf_night\": {\n" +
            "  \"in\": 0.00,\n" +
            "  \"mm\": 0.0\n" +
            "  },\n" +
            "  \"snow_allday\": {\n" +
            "  \"in\": 0,\n" +
            "  \"cm\": 0\n" +
            "  },\n" +
            "  \"snow_day\": {\n" +
            "  \"in\": 0,\n" +
            "  \"cm\": 0\n" +
            "  },\n" +
            "  \"snow_night\": {\n" +
            "  \"in\": 0,\n" +
            "  \"cm\": 0\n" +
            "  },\n" +
            "  \"maxwind\": {\n" +
            "  \"mph\": 11,\n" +
            "  \"kph\": 18,\n" +
            "  \"dir\": \"West\",\n" +
            "  \"degrees\": 267\n" +
            "  },\n" +
            "  \"avewind\": {\n" +
            "  \"mph\": 10,\n" +
            "  \"kph\": 16,\n" +
            "  \"dir\": \"West\",\n" +
            "  \"degrees\": 272\n" +
            "  },\n" +
            "  \"avehumidity\": 79,\n" +
            "  \"maxhumidity\": 93,\n" +
            "  \"minhumidity\": 63\n" +
            "  }, {\n" +
            "  \"date\": {\n" +
            "  \"epoch\": \"1341036000\",\n" +
            "  \"pretty\": \"11:00 PM PDT on June 29, 2012\",\n" +
            "  \"day\": 29,\n" +
            "  \"month\": 6,\n" +
            "  \"year\": 2012,\n" +
            "  \"yday\": 180,\n" +
            "  \"hour\": 23,\n" +
            "  \"min\": \"00\",\n" +
            "  \"sec\": 0,\n" +
            "  \"isdst\": \"1\",\n" +
            "  \"monthname\": \"June\",\n" +
            "  \"weekday_short\": \"Fri\",\n" +
            "  \"weekday\": \"Friday\",\n" +
            "  \"ampm\": \"PM\",\n" +
            "  \"tz_short\": \"PDT\",\n" +
            "  \"tz_long\": \"America/Los_Angeles\"\n" +
            "  },\n" +
            "  \"period\": 4,\n" +
            "  \"high\": {\n" +
            "  \"fahrenheit\": \"68\",\n" +
            "  \"celsius\": \"20\"\n" +
            "  },\n" +
            "  \"low\": {\n" +
            "  \"fahrenheit\": \"52\",\n" +
            "  \"celsius\": \"11\"\n" +
            "  },\n" +
            "  \"conditions\": \"Fog\",\n" +
            "  \"icon\": \"thunderstorm\",\n" +
            "  \"icon_url\": \"http://icons-ak.wxug.com/i/c/k/partlycloudy.gif\",\n" +
            "  \"skyicon\": \"mostlysunny\",\n" +
            "  \"pop\": 0,\n" +
            "  \"qpf_allday\": {\n" +
            "  \"in\": 0.00,\n" +
            "  \"mm\": 0.0\n" +
            "  },\n" +
            "  \"qpf_day\": {\n" +
            "  \"in\": 0.00,\n" +
            "  \"mm\": 0.0\n" +
            "  },\n" +
            "  \"qpf_night\": {\n" +
            "  \"in\": 0.00,\n" +
            "  \"mm\": 0.0\n" +
            "  },\n" +
            "  \"snow_allday\": {\n" +
            "  \"in\": 0,\n" +
            "  \"cm\": 0\n" +
            "  },\n" +
            "  \"snow_day\": {\n" +
            "  \"in\": 0,\n" +
            "  \"cm\": 0\n" +
            "  },\n" +
            "  \"snow_night\": {\n" +
            "  \"in\": 0,\n" +
            "  \"cm\": 0\n" +
            "  },\n" +
            "  \"maxwind\": {\n" +
            "  \"mph\": 11,\n" +
            "  \"kph\": 18,\n" +
            "  \"dir\": \"West\",\n" +
            "  \"degrees\": 267\n" +
            "  },\n" +
            "  \"avewind\": {\n" +
            "  \"mph\": 10,\n" +
            "  \"kph\": 16,\n" +
            "  \"dir\": \"West\",\n" +
            "  \"degrees\": 272\n" +
            "  },\n" +
            "  \"avehumidity\": 79,\n" +
            "  \"maxhumidity\": 93,\n" +
            "  \"minhumidity\": 63\n" +
            "  }, {\n" +
            "  \"date\": {\n" +
            "  \"epoch\": \"1341036000\",\n" +
            "  \"pretty\": \"11:00 PM PDT on June 29, 2012\",\n" +
            "  \"day\": 29,\n" +
            "  \"month\": 6,\n" +
            "  \"year\": 2012,\n" +
            "  \"yday\": 180,\n" +
            "  \"hour\": 23,\n" +
            "  \"min\": \"00\",\n" +
            "  \"sec\": 0,\n" +
            "  \"isdst\": \"1\",\n" +
            "  \"monthname\": \"June\",\n" +
            "  \"weekday_short\": \"Fri\",\n" +
            "  \"weekday\": \"Friday\",\n" +
            "  \"ampm\": \"PM\",\n" +
            "  \"tz_short\": \"PDT\",\n" +
            "  \"tz_long\": \"America/Los_Angeles\"\n" +
            "  },\n" +
            "  \"period\": 4,\n" +
            "  \"high\": {\n" +
            "  \"fahrenheit\": \"36\",\n" +
            "  \"celsius\": \"3\"\n" +
            "  },\n" +
            "  \"low\": {\n" +
            "  \"fahrenheit\": \"30\",\n" +
            "  \"celsius\": \"-1\"\n" +
            "  },\n" +
            "  \"conditions\": \"Fog\",\n" +
            "  \"icon\": \"snow\",\n" +
            "  \"icon_url\": \"http://icons-ak.wxug.com/i/c/k/partlycloudy.gif\",\n" +
            "  \"skyicon\": \"mostlysunny\",\n" +
            "  \"pop\": 0,\n" +
            "  \"qpf_allday\": {\n" +
            "  \"in\": 0.00,\n" +
            "  \"mm\": 0.0\n" +
            "  },\n" +
            "  \"qpf_day\": {\n" +
            "  \"in\": 0.00,\n" +
            "  \"mm\": 0.0\n" +
            "  },\n" +
            "  \"qpf_night\": {\n" +
            "  \"in\": 0.00,\n" +
            "  \"mm\": 0.0\n" +
            "  },\n" +
            "  \"snow_allday\": {\n" +
            "  \"in\": 0,\n" +
            "  \"cm\": 0\n" +
            "  },\n" +
            "  \"snow_day\": {\n" +
            "  \"in\": 0,\n" +
            "  \"cm\": 0\n" +
            "  },\n" +
            "  \"snow_night\": {\n" +
            "  \"in\": 0,\n" +
            "  \"cm\": 0\n" +
            "  },\n" +
            "  \"maxwind\": {\n" +
            "  \"mph\": 11,\n" +
            "  \"kph\": 18,\n" +
            "  \"dir\": \"West\",\n" +
            "  \"degrees\": 267\n" +
            "  },\n" +
            "  \"avewind\": {\n" +
            "  \"mph\": 10,\n" +
            "  \"kph\": 16,\n" +
            "  \"dir\": \"West\",\n" +
            "  \"degrees\": 272\n" +
            "  },\n" +
            "  \"avehumidity\": 79,\n" +
            "  \"maxhumidity\": 93,\n" +
            "  \"minhumidity\": 63\n" +
            "  }]\n" +
            "  }";





    public int getMode_cf() {
        return mode_cf;
    }

    public void setModeC() {
        this.mode_cf = 0;
    }

    public void setModeF() {
        this.mode_cf = 1;
    }


    private int mode_cf=0;


        public void saveMapToPref(){
                Gson gson = new Gson();
                MapWrapper wrapper = new MapWrapper();
                 wrapper.setMyMap(getLocations());
                String serializedMap = gson.toJson(wrapper);
                preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                editor=preferences.edit();
                Log.d("Error check", "saving map  : ");
                editor.putString("locweath", serializedMap);
                editor.commit();

        }

        public HashMap getMapFromPref(){
               HashMap<String,JSONObject> map=new HashMap<>();
                Gson gson=new Gson();
                preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                String wrapperStr = preferences.getString("locweath", "");
                Log.d("Error check", "getting map  : ");
                MapWrapper wrapper = gson.fromJson(wrapperStr, MapWrapper.class);
                map = wrapper.getMyMap();
                return map;
        }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_drawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Bundle extras= getIntent().getExtras();
            navcity=(TextView)findViewById(R.id.citytext);
            navstate=(TextView)findViewById(R.id.statetext);


        state=extras.getString("state");
        city=extras.getString("city");
        navstate.setText(state);
            navcity.setText(city);

       /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();



        Log.v("data.length", ":" + city + "," + state);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
            setLocations(getMapFromPref());
            String key=  city+","+state;
           if( locations.containsKey(key)){
                   setWeather(locations.get(key));
           }
            else {
                   GetWeather g = new GetWeather(this, state, city);
                   g.execute();
                   Log.v("data.length", ":" + new String(getResources().getString(R.string.data)));
           }
        try {
           JSONObject data = new JSONObject(datas);
         /*   setWeather(data);
            Log.v("data:",data.toString());*/

              /*  Gson gson = new Gson();
                MapWrapper wrapper = new MapWrapper();
               // wrapper.setMyMap(HtKpi);
                String serializedMap = gson.toJson(wrapper);*/
            FragmentManager fm = getSupportFragmentManager();
            Fragment fr = new FragmentWeather();
            fm.beginTransaction().replace(R.id.content, fr).commit();
        }catch(JSONException e){
Log.v("error",e.toString());
        }

      /*  ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
           *//* *//*

        }else{
            Toast.makeText(this, "Bad Internet Connection........ Please try again later. ", Toast.LENGTH_LONG).show();
        }*/



           /* f = new FragmentFriends();*/


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.nav_drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.change_city) {
            // Handle the camera action
        }
        else if (id == R.id.forecast) {
                FragmentManager fm = getSupportFragmentManager();
                Fragment fr = new FragmentWeather();
                fm.beginTransaction().replace(R.id.content, fr).commit();
        }
        else if (id == R.id.exit) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}


/*

LocationManager lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
double longitude = location.getLongitude();
double latitude = location.getLatitude();



  Geocoder geocoder = new Geocoder(this, Locale.ENGLISH);
    List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
    Address address=null;
    String addr="";
    String zipcode="";
    String city="";
    String state="";
    if (addresses != null && addresses.size() > 0){

            addr=addresses.get(0).getAddressLine(0)+"," +addresses.get(0).getSubAdminArea();
                 city=addresses.get(0).getLocality();
                 state=addresses.get(0).getAdminArea();

                 for(int i=0 ;i<addresses.size();i++){
                     address = addresses.get(i);
                     if(address.getPostalCode()!=null){
                         zipcode=address.getPostalCode();
                         break;
                     }

                }









 */

