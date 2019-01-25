package com.teamturtle.gnu.exercisescheduler.settingTap;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.teamturtle.gnu.exercisescheduler.IntroActivity;
import com.teamturtle.gnu.exercisescheduler.MainActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class LPasswordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_lpassword);

        AlertDialog.Builder alert = new AlertDialog.Builder(this);

        alert.setTitle("LOGIN");

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
                                Toast.makeText(LPasswordActivity.this, "Have a good day!", Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(LPasswordActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(LPasswordActivity.this);
                                builder.setMessage("Writing Failed")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();

                                finish();
                                Intent intent = new Intent(LPasswordActivity.this, IntroActivity.class);
                                startActivity(intent);

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                };

                LPasswordRequest lPasswordRequest = new LPasswordRequest(password, responseListener);
                RequestQueue queue = Volley.newRequestQueue(LPasswordActivity.this);
                queue.add(lPasswordRequest);
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
