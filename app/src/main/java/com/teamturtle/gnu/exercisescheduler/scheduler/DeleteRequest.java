package com.teamturtle.gnu.exercisescheduler.scheduler;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class DeleteRequest extends StringRequest {
    private static String DELETE_REQUEST_URL = "http://exercisemanagementapp.iptime.org/SYDelete.php";
    private Map<String,String>params;

    public DeleteRequest(String name, Response.Listener<String> Listener) {
        super(Method.POST, DELETE_REQUEST_URL, Listener, null);
        params = new HashMap<>();
        params.put("name", name);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
