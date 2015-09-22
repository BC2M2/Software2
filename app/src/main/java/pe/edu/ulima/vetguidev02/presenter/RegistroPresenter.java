package pe.edu.ulima.vetguidev02.presenter;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;

import pe.edu.ulima.vetguidev02.DTO.GenericResponse;
import pe.edu.ulima.vetguidev02.DTO.RegistroRequest;
import pe.edu.ulima.vetguidev02.view.RegistroView;

/**
 * Created by Kevin on 18/09/2015.
 */
public class RegistroPresenter implements IRegistroPresenter{

    //cambiar ip deacuerdo al lugar donde se corra
    private static final String url = "http://192.168.1.64:8080/VetGuideBackEndv0.1/RegistrarUsuarioServlet";
    private RegistroView view;

    public RegistroPresenter(RegistroView view){
        this.view=view;
    }

    //Este metodo manda la data al Servlet
    public void registrar(String correo, String password, String nombre, String apellido){

        RegistroRequest usuario= new RegistroRequest();
        usuario.setCorreo(correo);
        usuario.setPassword(password);
        usuario.setNombre(nombre);
        usuario.setApellido(apellido);

        final String json= new Gson().toJson(usuario);

        RequestQueue queue = view.getApplicationController().getRequestQueue();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        GenericResponse responseDTO = new Gson().fromJson(response, GenericResponse.class);

                        if (responseDTO.getMsgStatus().equals("OK")){
                            view.onRegistroCorrecto();

                        }else if (responseDTO.getMsgStatus().equals("ERROR")){
                            view.onRegistroIncorrecto();

                        }
                            else{
                            view.onError(responseDTO.getMsgError());
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                view.onError("RegistroPresenter (5XX): " + error.getMessage());
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

        stringRequest.setTag("Registro");
        queue.add(stringRequest);

    }

}
