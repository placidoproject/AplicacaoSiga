package com.example.gavin.aplicacaosiga.View;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.gavin.aplicacaosiga.BO.UsuarioBO;
import com.example.gavin.aplicacaosiga.R;
import com.example.gavin.aplicacaosiga.Validacao.Validation;
import com.exemplo.gavin.Model.ModelUsuario;
import com.exemplo.gavin.msg.MensagemUtil;


public class cadusuario extends Activity {


    private EditText edtCadlogin;
    private EditText edtCadsenha;
    private Button btCadlogin, btCadcancelar;

    private UsuarioBO usuario;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadusuario);
        usuario = new UsuarioBO(this);

        edtCadlogin = (EditText) findViewById(R.id.edt_cadlogin);
        edtCadsenha = (EditText) findViewById(R.id.edt_cadpassword);
        btCadlogin = (Button) findViewById(R.id.btn_novoentrar);
        btCadcancelar = (Button) findViewById(R.id.btn_cadcancelar);

    }


    public void CadastrarUsuario(View v){

        ModelUsuario mo = new ModelUsuario();
        mo.setLOGIN(edtCadlogin.getText().toString());
        mo.setSENHA(edtCadsenha.getText().toString());

        Validation resultado = usuario.cadastrarUsuario(mo);
        MensagemUtil.addMsg(this, resultado.getMensagem());
        Intent i = new Intent(this,aplicacaoSiga.class);
        startActivity(i);
        finish();

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cadusuario, menu);
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
