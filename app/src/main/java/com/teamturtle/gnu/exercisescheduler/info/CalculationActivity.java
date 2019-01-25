package com.teamturtle.gnu.exercisescheduler.info;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.teamturtle.gnu.exercisescheduler.R;

import org.json.JSONException;
import org.json.JSONObject;

public class CalculationActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculation);

        final TextView result = (TextView)findViewById(R.id.result_text);
        final String index = "1";
        Response.Listener<String> responseListener = new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");

                    if (success) {
                       // Toast.makeText(CalculationActivity.this,"총합.", Toast.LENGTH_SHORT).show();

                        String c_sum = jsonResponse.getString("c_sum");

                        final TextView result_text = (TextView) findViewById(R.id.result_text);
                        //Intent intent = getIntent();
                        //String c_sum = intent.getStringExtra("c_sum");
                        result_text.setText(c_sum);


                    } else {
                        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(CalculationActivity.this);
                        builder.setMessage("에러에러")
                                .setNegativeButton("다시입력",null)
                                .create()
                                .show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        };

        CalculationRequest  calculationRequest = new  CalculationRequest(index,responseListener);
        RequestQueue queue = Volley.newRequestQueue(CalculationActivity.this);
        queue.add( calculationRequest);

    }
}


