package weather.rajesh.com.weather;

import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationManager;
import android.media.Image;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.telephony.SmsManager;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

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
        ImageView weath;
        String curSt;
        String curCt;
        JSONObject current;
        private SharedPreferences preferences;
        private SharedPreferences.Editor editor;

        public void setCurrentWeather(JSONObject o){
                current=o;

        }

        public JSONObject getCurrentWeather(){
                return current;
        }


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





    public int getMode_cf() {
        return mode_cf;
    }

    public void setModeC() {
        this.mode_cf = 0;
    }

    public void setModeF() {
        this.mode_cf = 1;
    }

        public void setSelState(String st){
                NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
                View header=navigationView.getHeaderView(0);
                state=st;
                navstate = (TextView)header.findViewById(R.id.statetext);
                navstate.setText(state);
        }

        public void setSelCity(String ct){
                NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
                View header=navigationView.getHeaderView(0);
                city=ct;
                navcity = (TextView)header.findViewById(R.id.citytext);
                navcity.setText(city);
        }

        public String getSelState(){
                return state;
        }


        public String getSelCity(){
                return city;
        }


        private int mode_cf=1;





     /*  public void saveMapToPref(){
                Gson gson = new Gson();

           HashMap<String,HashMap<String,String>> today=new HashMap<>();
           Calendar c=Calendar.getInstance();
            String date=(c.get(Calendar.MONTH)+1)+"/"+(c.get(Calendar.DAY_OF_MONTH)+1);
           Log.v("date:", date);
           HashMap<String,String> locations=new HashMap<>();
           for(Map.Entry e:getLocations().entrySet()){
               locations.put((String)e.getKey(),e.getValue().toString());
           }
         today.put(date,locations);
           String wrapper = gson.toJson(today);


                preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                editor=preferences.edit();
                Log.d("Error check", "saving map  : ");

                editor.putString("locweath", wrapper);
                editor.commit();

        }

        public HashMap<String,JSONObject> getMapFromPref(){
               HashMap<String,String> map=new HashMap<>();
                Gson gson=new Gson();
                preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                String wrapperStr = preferences.getString("locweath", "failed");
                Log.d("Error check", "getting map  : ");
                java.lang.reflect.Type type = new TypeToken<HashMap<String,String>>(){}.getType();
                 if(!wrapperStr.contentEquals("failed")) {
                  map = gson.fromJson(wrapperStr, type);
                 }
              //  map = wrapper.getMyMapMap();
                Calendar c=Calendar.getInstance();
                String date=(c.get(Calendar.MONTH)+1)+"/"+(c.get(Calendar.DAY_OF_MONTH)+1);
                HashMap<String, JSONObject> today=new HashMap<>();
                if(map.containsKey(date)){

                    try{
                        today.put(date,new JSONObject(map.get(date)));
                    }
                    catch(JSONException e){
                    }
                }

                return today;
        }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_drawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Bundle extras= getIntent().getExtras();



        state=extras.getString("state");
        city=extras.getString("city");


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
            preferences=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            View header=navigationView.getHeaderView(0);
            weath=(ImageView)header.findViewById(R.id.imageView);
            navcity = (TextView)header.findViewById(R.id.citytext);
            navstate = (TextView)header.findViewById(R.id.statetext);
            weath.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                            FragmentManager fm = getSupportFragmentManager();
                            //  Fragment fr = new FragmentCur();
                            Fragment frs=new FragmentMain();
                            fm.beginTransaction().replace(R.id.content, frs).commit();
                    }
            });
           // preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

          //  setLocations(getMapFromPref());
            navstate.setText(state);
            navcity.setText(city);
            curSt=preferences.getString("curSt","");
            curCt=preferences.getString("curCt","");
            String key=  city+","+state;

           GetCurWeath c=new GetCurWeath(this,curSt,curCt);
            c.execute();
        //    locations=getMapFromPref();
           // if(!locations.containsKey(city+","+state)) {
                GetWeather g = new GetWeather(this, state, city);
                g.execute();
          //  }
                   Log.v("data.length", ":" + new String(getResources().getString(R.string.data)));



            FragmentManager fm = getSupportFragmentManager();
         //  Fragment fr = new FragmentCur();
            Fragment frs=new FragmentMain();
            fm.beginTransaction().replace(R.id.content, frs).commit();


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
                FragmentManager fm = getSupportFragmentManager();
                Fragment fr = new FragmentChangeLoc();
                fm.beginTransaction().replace(R.id.content, fr).commit(); // Handle the camera action
        }
        else if(id==R.id.refresh){
                FragmentManager fm = getSupportFragmentManager();
                Fragment fr = new FragmentMaps();
                fm.beginTransaction().replace(R.id.content, fr).commit();
        }
        else if(id==R.id.curlocweath){

                FragmentManager fm = getSupportFragmentManager();
                Fragment fr = new FragmentCur();
                fm.beginTransaction().replace(R.id.content, fr).commit();
        }
        else if (id == R.id.forecast) {
                FragmentManager fm = getSupportFragmentManager();
                Fragment fr = new FragmentWeather();
                fm.beginTransaction().replace(R.id.content, fr).commit();
        }
        else if(id== R.id.emgCon){
            emergencyContact();
        }





        else if (id == R.id.exit) {
          //  saveMapToPref();
           SharedPreferences.Editor e=preferences.edit();
            e.remove("locweath");
            e.commit();

                finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public void emergencyContact(){
        final AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
        final AlertDialog dialog = builder2.create();

        dialog.setCancelable(true);

        LinearLayout layout = new LinearLayout(this);
        ScrollView view = new ScrollView(this);
        TableLayout table = new TableLayout(this);

        ContentResolver resolver = getContentResolver();
        Cursor cursor = resolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                new String[]{"display_name", "data1"}, null, null, null);

        while (cursor.moveToNext()) {
            TableRow tableRow = new TableRow(this);

            TextView contactName = new TextView(this);
            contactName.setPadding(10, 10, 10, 10);
            contactName.setTextSize(20);
            contactName.setTextColor(Color.BLACK);

            TextView contactPhone = new TextView(this);
            contactPhone.setPadding(10, 10, 10, 10);
            contactPhone.setTextSize(20);
            contactPhone.setTextColor(Color.BLUE);

            String name = cursor.getString(0);
            final String phoneNumber = cursor.getString(1);

            contactName.setText(name);
            contactPhone.setText(phoneNumber);

            tableRow.addView(contactName);
            tableRow.addView(contactPhone);

            tableRow.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    AlertDialog.Builder confirm = new AlertDialog.Builder(NavDrawer.this);
                    confirm.setTitle("Confirm Send Alert SMS");
                    confirm.setMessage("Are you sure you want to send an Alert SMS to this contact ?");

                    confirm.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                            // Write your code here to invoke YES event
                            SmsManager sms = SmsManager.getDefault();
                            SharedPreferences pref= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                            double latit=Double.parseDouble(pref.getString("curLat", "37.776289"));
                            double longit=Double.parseDouble(pref.getString("curLong","-122.395234"));
                            sms.sendTextMessage(phoneNumber, null,"Hi,I need help. I am located at :\n https://www.google.com/maps/place/"+latit+"+"+longit+"/@"+latit+","+longit+",15z ", null, null);
                            Toast.makeText(getApplicationContext(), "You clicked on YES", Toast.LENGTH_SHORT).show();
                        }
                    });

                    // Setting Negative "NO" Button
                    confirm.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog1, int which) {
                            // Write your code here to invoke NO event
                            Toast.makeText(getApplicationContext(), "You clicked on NO", Toast.LENGTH_SHORT).show();
                            dialog1.cancel();
                            dialog.cancel();
                        }
                    });

                    // Showing Alert Message
                    confirm.show();








                }
            });
            table.addView(tableRow);

        }
        cursor.close();
        view.addView(table);
        layout.addView(view);
        dialog.setView(layout);
        dialog.show();


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

