package com.jotta.librerasanfranciscodeass;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.Iterator;

public class Data {
    private static String[][] productos;
    public static int k = 1;
    public static boolean flag = false;
    //this method is actually fetching the json string
    public static void getJSON(final String urlWebService) {
        //System.out.println("Pasando por metodo getJSON " + k);
        k++;
        /*
         * As fetching the json string is a network operation
         * And we cannot perform a network operation in main thread
         * so we need an AsyncTask
         * The constrains defined here are
         * Void -> We are not passing anything
         * Void -> Nothing at progress update as well
         * String -> After completion it should return a string and it will be the json string
         * */
        class GetJSON extends AsyncTask<Void, Void, String> {
            //this method will be called before execution
            //you can display a progress bar or something
            //so that user can understand that he should wait
            //as network operation may take some time
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                //System.out.println("Pasando por onPreExecute " + k);
                k++;
            }

            //this method will be called after execution
            //so here we are displaying a toast with the json string
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                //System.out.println("Pasando por onPostExecute " + k);
                k++;
                try {
                    //System.out.println("Pasando por try catch post execute " + k);
                    k++;
                    convertToArray(s);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            //in this method we are fetching the json string
            @Override
            protected String doInBackground(Void... voids) {
                try {
                    //System.out.println("Pasando por doInBackground " + k);
                    k++;
                    //creating a URL
                    URL url = new URL(urlWebService);

                    //Opening the URL using HttpURLConnection
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();

                    //StringBuilder object to read the string from the service
                    StringBuilder sb = new StringBuilder();

                    //We will use a buffered reader to read the string from service
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                    //A simple string to read values from each line
                    String json;
                    while ((json = bufferedReader.readLine()) != null) {
                        //appending it to string builder
                        sb.append(json + "\n");
                    }
                    //finally returning the read string
                    return sb.toString().trim();
                } catch (Exception e) {
                    return e.toString();
                }
            }
        }

        //creating asynctask object and executing it
        GetJSON getJSON = new GetJSON();
        getJSON.execute();
        //System.out.println("luego de getJSON.execute() " + k);
        k++;
    }

    public static void convertToArray(String json) throws JSONException {
        //System.out.println("Inicio convertToArray " + k);
        k++;
        //creating a json array from the json string
        JSONArray jsonArray = new JSONArray(json);

        String[] columnas = new String[jsonArray.getJSONObject(0).length()];

        //creating a string array for listview
        productos = new String[jsonArray.length()][jsonArray.getJSONObject(0).length()];

        int j = 0;
        Iterator<?> keys = jsonArray.optJSONObject(j).keys();

        while( keys.hasNext() ) {
            String key = (String) keys.next();
            columnas[j] = key;
            j++;
        }
        //looping through all the elements in json array
        for (int i = 0; i < jsonArray.length(); i++) {

            //getting json object from the json array
            JSONObject obj = jsonArray.getJSONObject(i);
            for (int k = 0; k < obj.length(); k++){
                //getting the name from the json object and putting it inside string array
                productos[i][k] = obj.getString(columnas[k]);
            }
        }
        flag = true;
        //System.out.println(Arrays.deepToString(productos));
    }

    public static String[][] getData(){
        //System.out.println("Pasando por getData() " + k);
        k++;
        return productos;
    }


}
