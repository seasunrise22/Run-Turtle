package com.teamturtle.gnu.exercisescheduler.gps;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * DB에 저장된 주소 받아오는 class
 */
public class RetrieveAddrRequest extends StringRequest {
    private static final String RETRIEVE_REQUEST_URL = "http://exercisemanagementapp.iptime.org/addressRetrieve.php";
    private Map<String, String> params;

    public RetrieveAddrRequest(int address_id, Response.Listener<String> listener) {
        super(Method.POST, RETRIEVE_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("address_id", address_id + "");
    }

    @Override
    protected Map<String, String> getParams() {
        return params;
    }
}

