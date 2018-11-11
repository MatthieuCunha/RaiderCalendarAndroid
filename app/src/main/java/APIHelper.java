import android.util.ArrayMap;
import android.util.Log;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.net.ssl.HttpsURLConnection;

public class APIHelper {

    public static StringBuffer  contactAPI(String urlSite,ArrayMap param){
        try {
            String urlParameters ="";

            URL url = new URL(urlSite);
            HttpsURLConnection uc = (HttpsURLConnection) url.openConnection();
            InputStreamReader inputStreamReader = new InputStreamReader(uc.getInputStream());
            uc.setDoInput(true);
            uc.setDoOutput(true);

            Set paramSet = param.keySet();
            Iterator iterParam = paramSet.iterator();
            while (iterParam.hasNext()) {
                String key=iterParam.next().toString();
                String value=param.get(key).toString();

                urlParameters +=key+"="+"value"+"&";

                //System.out.println(iterParam.next());
            }


            if(param.get("type")=="POST"){

                uc.setRequestMethod("POST");

                DataOutputStream wr = new DataOutputStream(uc.getOutputStream());
                wr.writeBytes(urlParameters);
                wr.flush();
                wr.close();

                int responseCode = uc.getResponseCode();
                Log.d("debug","\nSending 'POST' request to URL : " + url);
                Log.d("debug","Post parameters : " + urlParameters);
                Log.d("debug","Response Code : " + responseCode);

                BufferedReader in = new BufferedReader(
                        new InputStreamReader(uc.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                return response;

            }else{
                // get par defaut
                uc.setRequestMethod("GET");
                DataOutputStream wr = new DataOutputStream(uc.getOutputStream());
                wr.writeBytes(urlParameters);
                wr.flush();
                wr.close();

                int responseCode = uc.getResponseCode();
                Log.d("debug","\nSending 'GET' request to URL : " + url);
                Log.d("debug","Response Code : " + responseCode);

                BufferedReader in = new BufferedReader(
                        new InputStreamReader(uc.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                return response;
            }


        } catch (java.io.IOException e) {
            e.printStackTrace();
        }

        return null;
    }


}
