package com.example.raghu.usdtoinr;

/**
 * Created by raghu on 12/20/2016.
 */
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class MainFragment extends Fragment {

    private AlertDialog mDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        // Handle buttons here...
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);
        TextView value =   (TextView) rootView.findViewById(R.id.textview1);
        StringBuilder s = new StringBuilder();

        try {
            URL url = new URL("http://api.fixer.io/latest?base=USD");

            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
            String strTemp = "";
            while (null != (strTemp = br.readLine())) {
                s.append(strTemp);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println("Raghu"+s.toString());
        JSONParser parser = new JSONParser();
        JSONObject j;
        JSONObject rate = null;
        try {
            j = (JSONObject)parser.parse(s.toString());
            rate = (JSONObject)j.get("rates");
            String inrValue= String.valueOf(rate.get("INR"));
            value.setText("Current INR rate for USD \n               "+inrValue);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return rootView;
    }
}
