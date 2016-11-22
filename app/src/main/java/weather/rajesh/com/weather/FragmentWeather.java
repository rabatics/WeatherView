package weather.rajesh.com.weather;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
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
    AlertDialog dialog;
    ArrayList<Weather> weaList;
    public FragmentWeather(){

    }

    @Override

    public void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);

    }

    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View dataview=inflater.inflate(R.layout.fragment_weather, container, false);
        weaList=new ArrayList<>();
        weatherfc=(ListView)dataview.findViewById(R.id.listweather);


        int mode=((NavDrawer)getActivity()).getMode_cf();
        //   data.execute();

        JSONObject ob=((NavDrawer)getActivity()).getWeather();
        Log.v("weather check:",ob.toString());
        String statetxt=((NavDrawer)getActivity()).getSelState();
        String citytxt=((NavDrawer)getActivity()).getSelCity();
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
                    JSONObject mw=f.getJSONObject(i).optJSONObject("maxwind");
                    String mwm=mw.getString("mph")+" mph";
                    String mwk=mw.getString("kph")+" kph";
                    String condn = f.getJSONObject(i).getString("icon");
                    String stch="",stcl="",inp="",stfh="",stfl="";
                    Weather w=new Weather();
                    w.wind="Wind Speed: "+mwm;
                    int cf=((NavDrawer)getActivity()).getMode_cf();
                    if(cf==0) {
                        stch = th.getString("celsius")+" "+(char) 0x00B0+"C";
                        stcl = tl.getString("celsius")+" "+(char) 0x00B0+"C";
                        inp = c.getString("day") + "/" + c.getString("month") + ";High:" + stch + "  Low:" + stcl + " ; " + condn;
                        w.date= c.getString("day") + "/" + c.getString("month");
                        w.highlow="High:" + stch + "  Low:" + stcl;
                        w.condn=condn;

                    }else{
                        stfh = th.getString("fahrenheit")+" "+(char) 0x00B0+"F";
                        stfl = tl.getString("fahrenheit")+" "+(char) 0x00B0+"F";
                        inp = c.getString("day") + "/" + c.getString("month") + ";High:" + stfh + "  Low:" + stfl + " ; " + condn;
                        w.date= c.getString("day") + "/" + c.getString("month");
                        w.highlow="High:" + stfh + "  Low:" + stfl;
                        w.condn=condn;


                    }
                    if(w.condn.contains("sunny")||w.condn.contains("clear")){
                        w.image=R.mipmap.sunny;
                    }
                    else if(w.condn.contains("rain")||w.condn.contains("storm")||w.condn.contains("thunder")){
                        w.image=R.mipmap.thunderstorm;
                    }
                    else if(w.condn.contains("mostlycloudy")){
                        w.image=R.mipmap.mostlycloudy;
                    }
                    else if(w.condn.contains("partlycloudy")){
                        w.image=R.mipmap.partlycloudy;
                    }
                    else if(w.condn.contains("snow")){
                        w.image=R.mipmap.snow;
                    }
                    Log.v("condition:",w.condn);

                    w.loc=citytxt+" , "+statetxt;
                    weaList.add(w);
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

        weatherfc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), "Weather " , Toast.LENGTH_LONG).show();

                AlertDialog.Builder builder2 = new AlertDialog.Builder(getActivity());

                dialog = builder2.create();
                dialog.setCancelable(true);
                final View customDialog = getActivity().getLayoutInflater().inflate(R.layout.weathinfo, null);
                Weather c=weaList.get(position);

                TextView temp=(TextView)customDialog.findViewById(R.id.temp);
                TextView date=(TextView)customDialog.findViewById(R.id.date);
                TextView locn=(TextView)customDialog.findViewById(R.id.loc);
                ImageView imgFlag=(ImageView)customDialog.findViewById(R.id.imgWeath);
                TextView wind=(TextView)customDialog.findViewById(R.id.wind);


                locn.setText(c.loc);
                wind.setText(c.wind);
                temp.setText(c.highlow);
                date.setText(c.date);
               // time.setText(c.getTime());
                imgFlag.setImageResource(c.image);
                Log.v("winfo:",c.condn);
                dialog.setView(customDialog);
                dialog.getWindow().getDecorView().setBackgroundResource(android.R.color.transparent);
                dialog.show();
            }
        });
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



private class Weather{
    int image;
    String loc;
    String highlow;
    String date;
    String condn;
    String wind;

}


        }





