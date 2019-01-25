package com.teamturtle.gnu.exercisescheduler.settingTap;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Rpassword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_rpassword2);

        AlertDialog.Builder alert = new AlertDialog.Builder(this);

        alert.setTitle("Setting up your password for register");

        final EditText etPassword = new EditText(this);
        alert.setView(etPassword);

        alert.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                final String password = etPassword.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        try {
                            Log.e("json", response);
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if (success) {
                                finish();
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(Rpassword.this);
                                builder.setMessage("Writing Failed")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                };

                RPasswordRequest rPasswordRequest = new RPasswordRequest(password, responseListener);
                RequestQueue queue = Volley.newRequestQueue(Rpassword.this);
                queue.add(rPasswordRequest);
            }
        });
        alert.setNegativeButton("no",new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                finish();
            }
        });

        alert.show();
    }
}
