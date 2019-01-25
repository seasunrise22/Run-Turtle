package com.teamturtle.gnu.exercisescheduler.scheduler;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class ListRetrieve extends StringRequest {
    private static String RETRIEVE_REQUEST_URL = "http://exercisemanagementapp.iptime.org/SYRetrieve.php";
    private Map<String,String>params;

    public ListRetrieve(int n_id, Response.Listener<String> Listener) {
        super(Method.POST, RETRIEVE_REQUEST_URL, Listener, null);
        params = new HashMap<>();
        params.put("n_id", n_id + "");

    }
@Override
    public Map<String, String> getParams() {
        return params;

    }
}
