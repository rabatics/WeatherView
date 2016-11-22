package weather.rajesh.com.weather;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rajesh on 3/24/2016.
 */
public class FragmentMain extends Fragment {

    TextView city,state;
    ToggleButton toggle;
    View chatview;
    Switch s;
    public FragmentMain(){

    }

    @Override

    public void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);

    }

    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View dataview=inflater.inflate(R.layout.fragment_main, container, false);

        city=(TextView)dataview.findViewById(R.id.city);
        state=(TextView)dataview.findViewById(R.id.state);
        Bundle extras= getActivity().getIntent().getExtras();
       String statetxt=((NavDrawer)getActivity()).getSelState();
        String citytxt=((NavDrawer)getActivity()).getSelCity();

city.setText(citytxt);
        state.setText(statetxt);

        int mode=((NavDrawer)getActivity()).getMode_cf();
        //   data.execute();

         toggle = (ToggleButton) dataview.findViewById(R.id.toggleButton);
        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    ((NavDrawer)getActivity()).setModeC();
                    Toast.makeText(getActivity(), "Celsius", Toast.LENGTH_LONG);
                } else {
                    ((NavDrawer)getActivity()).setModeF();
                    Toast.makeText(getActivity(),"Fahrenheit",Toast.LENGTH_LONG);
                }
            }
        });

        if(((NavDrawer)getActivity()).getMode_cf()==0){
            toggle.setChecked(true);
        }
        else{
            toggle.setChecked(false);
        }




        return dataview;
    }


 /*   public void onRadioButtonClicked(View view) {
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
    }*/



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





