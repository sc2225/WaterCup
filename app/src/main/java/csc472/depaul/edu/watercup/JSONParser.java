package csc472.depaul.edu.watercup;

import org.json.JSONException;
import org.json.JSONObject;

public class JSONParser {

    public static void Parser(JSONObject jo) {

       if (jo == null) {
            throw new NullPointerException("Null JSONObject");
        }

        try {
            JSONObject jobj = new JSONObject(jo.toString());

            String cod = jobj.getString("cod");
            

            System.out.println("LOOK MA NO HANDS" + cod);

        } catch (JSONException e) {
            e.printStackTrace();
        }



    }
}
