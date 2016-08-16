package weather.rajesh.com.weather;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationManager;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class AddLocActivity extends AppCompatActivity {

    String state,city;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    Spinner spinnerst,spinnercity;
    TextView statetxt,citytxt;
    Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_loc);
        spinnerst = (Spinner) findViewById(R.id.spinner);
        spinnercity = (Spinner) findViewById(R.id.spinner2);
        statetxt = (TextView) findViewById(R.id.statetxt);
        citytxt = (TextView) findViewById(R.id.citytxt);
        next = (Button) findViewById(R.id.button);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.states, android.R.layout.simple_spinner_item);

// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinnerst.setAdapter(adapter);

        spinnerst.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinnercity.setVisibility(View.VISIBLE);
                state = (String) parent.getItemAtPosition(position);
                ArrayAdapter<CharSequence> adapter = null;
                statetxt.setText(state);
                switch (position) {
                    case 0:adapter = ArrayAdapter.createFromResource(getApplicationContext(),
                            R.array.MA, android.R.layout.simple_spinner_item);
                        break;

                    case 1:adapter = ArrayAdapter.createFromResource(getApplicationContext(),
                                R.array.CA, android.R.layout.simple_spinner_item);
                                break;
                    case 2: adapter = ArrayAdapter.createFromResource(getApplicationContext(),
                                R.array.NY, android.R.layout.simple_spinner_item);
                        break;
                    case 3: adapter = ArrayAdapter.createFromResource(getApplicationContext(),
                                R.array.FL, android.R.layout.simple_spinner_item);
                            break;

                }

                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                spinnercity.setAdapter(adapter);

                spinnercity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        city = (String) parent.getItemAtPosition(position);
                        citytxt.setText(city);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        citytxt.setText("");
                    }
                });

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                statetxt.setText("");
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!state.contentEquals("") && !city.contentEquals("")){
                    Intent i=new Intent(getApplicationContext(),NavDrawer.class);
                    i.putExtra("state",state);
                    i.putExtra("city",city);
                    startActivity(i);
                    finish();

                }
            }

        });
    }


    @Override
    public void onResume(){
        super.onResume();
        preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        LocationManager lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        double longitude =0;
        double latitude = 0;
        try {
            Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            longitude = location.getLongitude();
            latitude = location.getLatitude();
            GetLocation g=new GetLocation(getApplicationContext(),""+latitude,""+longitude);
            g.execute();
        }catch(SecurityException e){

        }

    }


}
