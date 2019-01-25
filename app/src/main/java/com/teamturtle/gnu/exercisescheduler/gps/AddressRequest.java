package com.teamturtle.gnu.exercisescheduler.gps;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class AddressRequest extends StringRequest {
    private static final String ADDRESS_REQUEST_URL = "http://exercisemanagementapp.iptime.org/address.php";
    private Map<String, String> params;

    public AddressRequest(String addrName, Response.Listener<String> listener) {
        super(Method.POST, ADDRESS_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("addrName", addrName);
    }

    @Override
    protected Map<String, String> getParams() {
        return params;
    }
}

