package com.teamturtle.gnu.exercisescheduler.settingTap;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by chris on 2017-05-31.
 */

public class RPasswordRequest extends StringRequest {
    private static final String REGISTER_REQUEST_URL = "http://exercisemanagementapp.iptime.org/Rpassword.php";
    private Map<String, String> params;

    public RPasswordRequest(String password, Response.Listener<String> listener) {
        super(Method.POST, REGISTER_REQUEST_URL, listener, null);

        params = new HashMap<>();
        params.put("password", password);

    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }

}
