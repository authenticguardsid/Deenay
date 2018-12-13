package com.denaay.pages;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.denaay.R;

import org.json.JSONException;
import org.json.JSONObject;

public class VerifiedActivity extends AppCompatActivity {

    public String PARTNER = "";
    public String GCODE = "";
    private static TextView result_code,result_name, result_address, result_phone,result_web;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verified);

        result_code = (TextView) findViewById(R.id.dtext2content);
        result_name = (TextView) findViewById(R.id.dtext3content);
        result_address = (TextView) findViewById(R.id.dtext4content);
        result_phone = (TextView) findViewById(R.id.dtext5content);
        result_web = (TextView) findViewById(R.id.dtext6content);
        set_profile_company(getIntent().getStringExtra("key"));

    }

    public void set_profile_company(String code){
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, "http://admin.authenticguards.net/api/check_/"+code+"?token=a&appid=001", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                if (response.length() > 0) {
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject jsonObject = response.getJSONObject("result");
                            GCODE = jsonObject.getString("code");
                            JSONObject data = jsonObject.getJSONObject("brand");
                            result_code.setText(GCODE);
                            result_name.setText(data.getString("Name"));
                            result_address.setText(data.getString("addressOfficeOrStore"));
                            result_phone.setText(data.getString("csPhone"));
                            result_web.setText(data.getString("web"));
                        } catch (JSONException e) {

                        }
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        Volley.newRequestQueue(this).add(jsonObjectRequest);

    }

}
