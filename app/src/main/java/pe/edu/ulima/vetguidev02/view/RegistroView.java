package pe.edu.ulima.vetguidev02.view;

import pe.edu.ulima.vetguidev02.ApplicationController;

/**
 * Created by Kevin on 18/09/2015.
 */
public interface RegistroView {

    public void onRegistroCorrecto();
    public void onError(String msg);
    public ApplicationController getApplicationController();
    public void onRegistroIncorrecto();

}
