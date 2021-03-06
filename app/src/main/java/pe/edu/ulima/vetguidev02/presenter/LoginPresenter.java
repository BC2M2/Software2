package pe.edu.ulima.vetguidev02.presenter;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;

import pe.edu.ulima.vetguidev02.DTO.GenericResponse;
import pe.edu.ulima.vetguidev02.DTO.LoginRequest;
import pe.edu.ulima.vetguidev02.view.LoginView;

public class LoginPresenter implements ILoginPresenter{

    private static final String url = "http://vetguidev1.herokuapp.com/ValidarLoginServlet";

    private LoginView view;

    public LoginPresenter(LoginView view){
        this.view = view;
    }

    @Override
    public void login(String usuario, String password){

        LoginRequest loginRequest = new LoginRequest(usuario, password);
        final String json = new Gson().toJson(loginRequest);

        RequestQueue queue = view.getApplicationController().getRequestQueue();


        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                GenericResponse loginResponse =
                        new Gson().fromJson(response, GenericResponse.class);

                if (loginResponse.getMsgStatus().equals("OK")){
                    view.onLoginCorrecto();
                }else if (loginResponse.getMsgStatus().equals("ERROR")){
                    view.onLoginIncorrecto();
                }else{
                    view.onError(loginResponse.getMsgError());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                view.onError("LoginPresenter (5XX): " + error.getMessage());
            }
        }){
            @Override
            public String getBodyContentType() {
                return "application/json";
            }

            @Override
            public byte[] getBody(){
                return json.getBytes();
            }
        };


        stringRequest.setTag("LOGIN");
        queue.add(stringRequest);

        /*OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(JSON, json);

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();*/
    }
}
