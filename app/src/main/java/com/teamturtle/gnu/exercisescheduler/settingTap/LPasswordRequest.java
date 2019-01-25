package com.teamturtle.gnu.exercisescheduler.settingTap;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by chris on 2017-05-31.
 */

public class LPasswordRequest extends StringRequest {
    private static final String LOGIN_REQUEST_URL = "http://exercisemanagementapp.iptime.org/Lpassword.php";
    private Map<String, String> params;

    public LPasswordRequest(String password, Response.Listener<String> listener) {
        super(Method.POST, LOGIN_REQUEST_URL, listener, null);

        params = new HashMap<>();
        params.put("password", password);

    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
