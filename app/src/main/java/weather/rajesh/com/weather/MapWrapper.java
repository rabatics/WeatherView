package weather.rajesh.com.weather;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by rajesh on 7/17/2016.
 */
public class MapWrapper {


    private HashMap<String,HashMap<String,JSONObject>> myMapMap=new HashMap<>();



    public HashMap<String, HashMap<String,JSONObject>> getMyMapMap() {
        if(myMapMap.equals("")) {
            myMapMap = new HashMap<>();
        }
        return myMapMap;
    }

    public void setMyMapMap(HashMap<String, HashMap<String,JSONObject>> myMapMap) {
        this.myMapMap = myMapMap;
    }




}
