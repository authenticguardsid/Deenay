package com.denaay.pages;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.denaay.R;
import com.google.android.gms.vision.barcode.Barcode;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import info.androidhive.barcode.BarcodeReader;

public class QRcodeActivity extends AppCompatActivity implements BarcodeReader.BarcodeReaderListener {

    BarcodeReader barcodeReader;
    String GENIUNE_CODE = "GENIUNE";
    String FAKE_CODE = "FAKE";
    String rvalid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
        setContentView(R.layout.activity_qrcode);
        changeStatusBarColor();

        barcodeReader = (BarcodeReader) getSupportFragmentManager().findFragmentById(R.id.barcode_scanner);

    }

    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }

    @Override
    public void onScanned(Barcode barcode) {
        barcodeReader.playBeep();
        validation_code(barcode.displayValue);
    }

    @Override
    public void onScannedMultiple(List<Barcode> barcodes) {

    }

    @Override
    public void onBitmapScanned(SparseArray<Barcode> sparseArray) {

    }

    @Override
    public void onScanError(String errorMessage) {
        Toast.makeText(getApplicationContext(), "Error occurred while scanning " + errorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCameraPermissionDenied() {

    }

    public void validation_code(final String scancode){

        JsonArrayRequest arrayRequest = new JsonArrayRequest("http://control.zodapos.com/core/api/scanner.class.php?action=valid&code="+scancode, new com.android.volley.Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if (response.length() > 0) {
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject data = response.getJSONObject(i);
                            rvalid = data.getString("rvalid");
                            String gcode = data.getString("gcode");
                            String partner = data.getString("partner");
                        } catch (JSONException e) {

                        }
                    }
//                    if (rvalid.equals(GENIUNE_CODE)){
//                        Intent intent_geniune = new Intent(QRcodeActivity.this, ResultCodeActivity.class);
//                        intent_geniune.putExtra("key", scancode);
//                        startActivity(intent_geniune);
//                    }
//                    if (rvalid.equals(FAKE_CODE)){
//                        Intent intent_fake = new Intent(QRcodeActivity.this, FakeCodeActivity.class);
//                        startActivity(intent_fake);
//                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        Volley.newRequestQueue(this).add(arrayRequest);
    }

}
