package com.teamturtle.gnu.exercisescheduler.gps;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * DB에 저장된 주소 받아오는 class
 */
public class DeleteAddrRequest extends StringRequest {
    private static final String DELETE_REQUEST_URL = "http://exercisemanagementapp.iptime.org/addressDelete.php";
    private Map<String, String> params;

    public DeleteAddrRequest(String addrName, Response.Listener<String> listener) {
        super(Method.POST, DELETE_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("addrName", addrName);
    }

    @Override
    protected Map<String, String> getParams() {
        return params;
    }
}

