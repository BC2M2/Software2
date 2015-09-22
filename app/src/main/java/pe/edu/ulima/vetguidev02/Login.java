package pe.edu.ulima.vetguidev02;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.net.UnknownHostException;

import pe.edu.ulima.vetguidev02.presenter.ILoginPresenter;
import pe.edu.ulima.vetguidev02.presenter.LoginPresenter;
import pe.edu.ulima.vetguidev02.view.LoginView;


public class Login extends Activity implements OnClickListener, LoginView{

    Button btnLogin;
    EditText etUsuario;
    EditText etPassword;
    TextView tvloginFB;
    TextView tvRegistro;
    TextView tvOlvido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsuario= (EditText)this.findViewById(R.id.usuario);
        etPassword=(EditText)this.findViewById(R.id.password);
        btnLogin=(Button)this.findViewById(R.id.btnLogin);
        tvloginFB=(TextView)this.findViewById(R.id.tvLoginFB);
        tvRegistro=(TextView)this.findViewById(R.id.tvRegistro);
        tvOlvido=(TextView)this.findViewById(R.id.tvOlvido);

        btnLogin.setOnClickListener(this);
        tvloginFB.setOnClickListener(this);
        tvRegistro.setOnClickListener(this);
        tvOlvido.setOnClickListener(this);

    }

    @Override
    public void onClick(View v){
        String usuario = etUsuario.getText().toString();
        String password = etPassword.getText().toString();
        boolean existe;

        if(v.getId()==R.id.btnLogin){

            String correo= etUsuario.getText().toString();
            String pass= etPassword.getText().toString();

            ILoginPresenter presenter= new LoginPresenter(this);
            presenter.login(correo,pass);


        }else if(v.getId()==R.id.tvLoginFB){

            //Hacer algo
            Toast toast = Toast.makeText(this,"Login con FB",Toast.LENGTH_SHORT);
            toast.show();

        }else if(v.getId()==R.id.tvRegistro){

            //Hacer algo
            Intent intent= new Intent();
            intent.setClass(this,Registro.class);
            this.startActivity(intent);

        }else if(v.getId()==R.id.tvOlvido){

            //Hacer algo
            Toast toast = Toast.makeText(this,"Olvido",Toast.LENGTH_SHORT);
            toast.show();

        }
    }

    @Override
    public void onLoginCorrecto(){
        Intent intent = new Intent();
        intent.setClass(this,PrincipalCliente.class);
        this.startActivity(intent);
    }

    @Override
    public void onError(String error){
        Toast.makeText(
                this,
                "EXC: " + error,
                Toast.LENGTH_LONG
        ).show();
    }

    @Override
    public ApplicationController getApplicationController() {
        return (ApplicationController)getApplication();
    }

    @Override
    public void onLoginIncorrecto(){
        Toast toast = Toast.makeText(this,"Usuario o password incorrectos",Toast.LENGTH_LONG);
        toast.show();
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
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
