package com.example.gavin.aplicacaosiga.View;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gavin.aplicacaosiga.BO.UsuarioBO;
import com.example.gavin.aplicacaosiga.R;


public class aplicacaoSiga extends Activity {

    EditText login, senha;
    Button entrar, cancelar,cadastrar;

    UsuarioBO usuarioBo;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aplicacao_siga);

        usuarioBo = new UsuarioBO(this);

        login = (EditText) findViewById(R.id.edt_login);
        senha = (EditText) findViewById(R.id.edt_password);
        entrar = (Button) findViewById(R.id.btn_entrar);
        cancelar = (Button) findViewById(R.id.btn_cancelar);
        cadastrar = (Button) findViewById(R.id.btn_cadastroLogin);

        final SharedPreferences preference = getSharedPreferences("config",0);



        entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /*
                String nomeLogin = "admin";
                String nomeSenha="1234";
                String strlogin = login.getText().toString();
                String strsenha = senha.getText().toString();

              */

                String strlogin = preference.getString("login","admin");
                String strsenha = preference.getString("senha","1234");

                boolean autentica = usuarioBo.AutenticaUsuario(login.getText().toString(), senha.getText().toString());

                //if(strlogin.equals(login.getText().toString()) && strsenha.equals(senha.getText().toString())){
                if (autentica){
                    Toast.makeText(aplicacaoSiga.this, "Logado com sucesso", Toast.LENGTH_SHORT).show();
                    login.setText("");
                    senha.setText("");
                    ChamaPrincipal();
                }else{
                    Toast.makeText(aplicacaoSiga.this,"erro no Login ou Senha!!!",Toast.LENGTH_SHORT ).show();

                }
            }
        });

        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login.setText("");
                senha.setText("");

            }
        });

        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChamarCadastroLogin();
            }
        });




    }





    public void ChamaPrincipal(){
        //Intent chamar = new Intent(this,principal.class);
        //startActivity(chamar);
        setContentView(R.layout.activity_principal);
    }

    public void ChamarCadastroLogin(){
        Intent chamar = new Intent(this,cadusuario.class);
        startActivity(chamar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_aplicacao_siga, menu);
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
