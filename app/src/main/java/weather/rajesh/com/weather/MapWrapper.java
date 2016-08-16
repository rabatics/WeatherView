package weather.rajesh.com.weather;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by rajesh on 7/17/2016.
 */
public class MapWrapper {

    public HashMap<String, JSONObject> getMyMap() {
        return myMap;
    }

    public void setMyMap(HashMap<String, JSONObject> myMap) {
        this.myMap = myMap;
    }

    private HashMap<String, JSONObject> myMap;
}
