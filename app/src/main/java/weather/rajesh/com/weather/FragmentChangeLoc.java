package weather.rajesh.com.weather;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by rajesh on 3/24/2016.
 */
public class FragmentChangeLoc extends Fragment {

    String city,state;
    View chatview;
    Spinner spinnerst,spinnercity;
    Button next;
    public FragmentChangeLoc(){

    }

    @Override

    public void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);

    }

    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View dataview=inflater.inflate(R.layout.fragment_changeloc, container, false);

        spinnerst = (Spinner) dataview.findViewById(R.id.spinner);
        spinnercity = (Spinner) dataview.findViewById(R.id.spinner2);

        next = (Button) dataview.findViewById(R.id.button);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.states, android.R.layout.simple_spinner_item);

// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinnerst.setAdapter(adapter);

        spinnerst.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //spinnercity.setVisibility(View.VISIBLE);
                state = (String) parent.getItemAtPosition(position);
                ArrayAdapter<CharSequence> adapter = null;
                //  statetxt.setText(state);
                switch (position) {
                    case 0:
                        adapter = ArrayAdapter.createFromResource(getActivity(),
                                R.array.MA, android.R.layout.simple_spinner_item);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        break;

                    case 1:
                        adapter = ArrayAdapter.createFromResource(getActivity(),
                                R.array.CA, android.R.layout.simple_spinner_item);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        break;
                    case 2:
                        adapter = ArrayAdapter.createFromResource(getActivity(),
                                R.array.NY, android.R.layout.simple_spinner_item);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        break;
                    case 3:
                        adapter = ArrayAdapter.createFromResource(getActivity(),
                                R.array.FL, android.R.layout.simple_spinner_item);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        break;

                }


                spinnercity.setAdapter(adapter);

                spinnercity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        city = (String) parent.getItemAtPosition(position);
                        // citytxt.setText(city);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        // citytxt.setText("");
                    }
                });

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // statetxt.setText("");
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!state.contentEquals("") && !city.contentEquals("")) {
                    HashMap<String, JSONObject> map = ((NavDrawer) getActivity()).getLocations();
                    if (!map.containsKey(city + "," + state)) {
                        GetWeather g = new GetWeather(getActivity(), state, city);
                        g.execute();
                    } else {
                        ((NavDrawer)getActivity()).setWeather(map.get(city + "," + state));
                    }

                    ((NavDrawer) getActivity()).setSelCity(city);
                    ((NavDrawer) getActivity()).setSelState(state);
                }
            }

        });

        return dataview;
    }


  /*  public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.ctof:
                if (checked) {
                    ((NavDrawer)getActivity()).setModeF();
                    Toast.makeText(getActivity(),"Fahrenheit",Toast.LENGTH_LONG);
                }
                break;
            case R.id.ftoc:
                if (checked) {
                    ((NavDrawer)getActivity()).setModeC();
                    Toast.makeText(getActivity(), "Celsius", Toast.LENGTH_LONG);
                }
                break;
        }
    }

*/

/*weatherfc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // When clicked, show a toast with the TextView text
        String name = (String) weatherfc.getItemAtPosition(position);
        ((NavDrawer) getActivity()).setTo(name, getActivity().getResources().getString(R.string.f));
        GetFriendPosts g = new GetFriendPosts(getActivity(), username, name);
        g.execute();

        ((NavDrawer) getActivity()).addRecent(name, getActivity().getResources().getString(R.string.f));
        Toast.makeText(getActivity(),
                ((TextView) view).getText(), Toast.LENGTH_SHORT).show();
        ((NavDrawer) getActivity()).invalidateOptionsMenu();
    }
});*/






}





