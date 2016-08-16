package weather.rajesh.com.weather;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rajesh on 3/24/2016.
 */
public class FragmentWeather extends Fragment {

    ListView weatherfc;
    View chatview;
    public FragmentWeather(){

    }

    @Override

    public void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);

    }

    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View dataview=inflater.inflate(R.layout.fragment_weather, container, false);

        weatherfc=(ListView)dataview.findViewById(R.id.listweather);


        int mode=((NavDrawer)getActivity()).getMode_cf();
        //   data.execute();

        JSONObject ob=((NavDrawer)getActivity()).getWeather();
        Log.v("weather check:",ob.toString());

        JSONArray f = ob.optJSONArray("forecastday");
        if(ob!=null && f!=null) {

            int length = f.length();
            Log.v("data.length", ":" + length);
            List<String> listContents = new ArrayList<String>(length);
            //     List<String> listNum=new ArrayList<>(30);
            for (int i = 0; i < length; i++) {
                try {
                    JSONObject c = f.getJSONObject(i).optJSONObject("date");
                    JSONObject th = f.getJSONObject(i).optJSONObject("high");
                    JSONObject tl = f.getJSONObject(i).optJSONObject("low");
                    String condn = f.getJSONObject(i).getString("icon");
                    String sth = th.getString("celsius") + " C";
                    String stl = tl.getString("celsius") + " C";
                    String inp = c.getString("day") + "/" + c.getString("month") + ";High:" + sth + "  Low:" + stl + " ; " + condn;
                    Log.v("list", inp);
                    listContents.add(inp);
                } catch (JSONException e) {
                    Log.v("list error", e.toString());
                }
            }
      /*      for ( i = 0; i < 30; i++) {

                listNum.add("" + i);

            }*/

            //  ListView myListView = (ListView) findViewById(R.id.l);


            String[] s = new String[listContents.size()];
            s = listContents.toArray(s);

            weatherfc.setAdapter(new CustomAdapter(getActivity(), R.layout.drawer_list_item, s));
        }
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





