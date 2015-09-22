package pe.edu.ulima.vetguidev02.view;


import pe.edu.ulima.vetguidev02.ApplicationController;

/**
 * Created by hquintana on 12/09/15.
 */
public interface LoginView {

    public void onLoginCorrecto();
    public void onLoginIncorrecto();
    public void onError(String msg);
    public ApplicationController getApplicationController();

}
