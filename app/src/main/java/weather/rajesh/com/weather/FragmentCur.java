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
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

/**
 * Created by rajesh on 8/16/2016.
 */
public class FragmentCur extends Fragment {

    ImageView weatherfc;
    TextView temp;
    TextView city;
    TextView state;
    TextView fl;
    TextView wind;
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
        temp=(TextView)dataview.findViewById(R.id.textView);
        city=(TextView)dataview.findViewById(R.id.citytxt);
        state=(TextView)dataview.findViewById(R.id.state);
        fl=(TextView)dataview.findViewById(R.id.fl);
        wind=(TextView)dataview.findViewById(R.id.wind);

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
                    String flc=ob.getString("feelslike_c")+" "+(char) 0x00B0+"C";
                    String flf=ob.getString("feelslike_f")+" "+(char) 0x00B0+"F";
                    String stc = ob.getString("temp_c") +" "+(char) 0x00B0+ "C";
                    String stf = ob.getString("temp_f") +" "+(char) 0x00B0+ "F";
                    String windmph=ob.getString("wind_mph")+" mph";
                    String windkph=ob.getString("wind_kph")+" kph";
                    wind.setText(windmph);
                   int cf=((NavDrawer)getActivity()).getMode_cf();
                   if(cf==0) {
                       temp.setText(stc);
                       fl.setText(flc);

                   }
                    else{
                       temp.setText(stf);
                       fl.setText(flf);
                   }

                   if(condn.contains("partlycloudy")) {
                       weatherfc.setImageResource(R.mipmap.partlycloudy);
                   }
                   else if(condn.contains("storm")||condn.contains("rain")) {

                       weatherfc.setImageResource(R.mipmap.thunderstorm);
                   }
                   else if(condn.contains("mostlycloudy")||condn.contains("cloudy")){
                        weatherfc.setImageResource(R.mipmap.mostlycloudy);
                   }
                   else if(condn.contains("clear")||condn.contains("sunny")){
                       weatherfc.setImageResource(R.mipmap.sunny);
                   }
                   else if(condn.contains("snow")){
                       weatherfc.setImageResource(R.mipmap.snow);
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





