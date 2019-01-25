package com.teamturtle.gnu.exercisescheduler.scheduler;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class InputRequest extends StringRequest {
    private static String LIST_REQUEST_URL = "http://exercisemanagementapp.iptime.org/SYInput.php";
    private Map<String,String> params;

    public InputRequest(String name, Response.Listener<String> Listener) {
        super(Method.POST,LIST_REQUEST_URL,Listener,null);
        params = new HashMap<>();
        params.put("name", name);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
