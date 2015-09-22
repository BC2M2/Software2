package pe.edu.ulima.vetguidev02;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import pe.edu.ulima.vetguidev02.presenter.IRegistroPresenter;
import pe.edu.ulima.vetguidev02.presenter.RegistroPresenter;
import pe.edu.ulima.vetguidev02.view.RegistroView;


public class Registro extends ActionBarActivity implements View.OnClickListener, RegistroView{

    Button btnRegistro;
    Button btnCancelar;
    Button btnVeterinaria;

    EditText etCorreo;
    EditText etPassword;
    EditText etNombre;
    EditText etApellido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        btnRegistro= (Button)this.findViewById(R.id.btnRegistro);
        btnCancelar= (Button)this.findViewById(R.id.btnCancelar);
        btnVeterinaria=(Button)this.findViewById(R.id.btn_Veterinaria);

        etCorreo= (EditText)this.findViewById(R.id.correo);
        etPassword= (EditText)this.findViewById(R.id.pasword);
        etNombre= (EditText)this.findViewById(R.id.nombre);
        etApellido= (EditText)this.findViewById(R.id.apellido);

        btnRegistro.setOnClickListener(this);
        btnVeterinaria.setOnClickListener(this);
        btnCancelar.setOnClickListener(this);

    }

    //Metodo a ejecutarse al clickear
    public void onClick(View view){

        String correo= etCorreo.getText().toString();
        String password= etPassword.getText().toString();
        String nombre= etNombre.getText().toString();
        String apellido= etApellido.getText().toString();

        if(view.getId()==R.id.btn_Veterinaria){

            //Redireccionar
            Intent intent= new Intent();
            intent.setClass(this,RegistroVeterinaria.class);
            this.startActivity(intent);

        }else if(view.getId()==R.id.btnCancelar){

            Intent intent= new Intent();
            intent.setClass(this,Login.class);
            this.startActivity(intent);

        }else if(view.getId()==R.id.btnRegistro){

            IRegistroPresenter presenter= new RegistroPresenter(this);
            presenter.registrar(correo,password,nombre,apellido);
        }
    }

    public void onRegistroCorrecto(){
        //Redireccionar

    }
    public void onError(String msg){
        Toast.makeText(
                this,
                "EXC: " + msg,
                Toast.LENGTH_LONG
        ).show();
    }
    public ApplicationController getApplicationController(){
        return (ApplicationController)getApplication();
    }
    public void onRegistroIncorrecto(){
        Toast toast = Toast.makeText(this,"Ya existe un usuario asociado a ese correo",Toast.LENGTH_LONG);
        toast.show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_registro, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
