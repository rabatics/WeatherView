package weather.rajesh.com.weather;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rajesh on 8/16/2016.
 */
public class FragmentCur extends Fragment {

    ImageView weatherfc;
    TextView temp;
    TextView city;
    TextView state;
    View chatview;
    public FragmentCur(){

    }

    @Override

    public void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);

    }

    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View dataview=inflater.inflate(R.layout.fragment_current, container, false);

        weatherfc=(ImageView)dataview.findViewById(R.id.icon);
        temp=(TextView)dataview.findViewById(R.id.temp);
        city=(TextView)dataview.findViewById(R.id.city);
        state=(TextView)dataview.findViewById(R.id.state);

        int mode=((NavDrawer)getActivity()).getMode_cf();
        //   data.execute();

        JSONObject ob=((NavDrawer)getActivity()).getCurrentWeather();

        SharedPreferences p= PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());
city.setText(p.getString("curCt",""));
        state.setText(p.getString("curSt",""));
        if(ob!=null) {
            Log.v("weather check:", ob.toString());


                try {

                    String condn = ob.getString("icon");
                    String stc = ob.getString("temp_c") + " C";
                    String stf = ob.getString("temp_f") + " F";
                    temp.setText(stc);
                switch(condn){
                   case "partlycloudy":weatherfc.setImageResource(R.mipmap.partlycloudy);
                                        break;
                    case "thunderstorm":weatherfc.setImageResource(R.mipmap.thunderstorm);
                                        break;
                    default:weatherfc.setImageResource(R.mipmap.sunny);
                }
                    Log.v("list", condn+":"+stc+":"+stf);

                } catch (JSONException e) {
                    Log.v("list error", e.toString());
                }
            }
      /*      for ( i = 0; i < 30; i++) {

                listNum.add("" + i);

            }*/

            //  ListView myListView = (ListView) findViewById(R.id.l);



        return dataview;
    }

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





