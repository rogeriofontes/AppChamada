package unipac.com.br.appchamada;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText nomeEdt, emailEdt, telefoneEdt;
    Button salvarBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nomeEdt = (EditText) findViewById(R.id.nomeEdt);
        emailEdt = (EditText) findViewById(R.id.emailEdt);
        telefoneEdt = (EditText) findViewById(R.id.telefoneEdt);
        salvarBtn = (Button) findViewById(R.id.salvarBtn);

        salvarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nome = nomeEdt.getText().toString();
                String email = emailEdt.getText().toString();
                String telefone = emailEdt.getText().toString();

                insereInformacao(nome, email, telefone);
            }
        });
    }

    public void insereInformacao(String nome, String email, String telefone){

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        SharedPreferences.Editor editor = preferences.edit();

        editor.putString("NOME", nome);
        editor.putString("EMAIL", email);
        editor.putString("TELEFONE", telefone);

        editor.commit();

    }

    public void lerDados() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);

        String nome = preferences.getString("NOME", "Retornou Nada");
        String email = preferences.getString("EMAIL", "Retornou Nada");
        String telefone = preferences.getString("TELEFONE", "Retornou Nada");

        imprime(nome, email, telefone);
    }

    public void imprime(String nome, String email, String telefone) {
        StringBuilder sb = new StringBuilder(1200);
        sb.append("Os dados cadastrados Foram:");
        sb.append("Nome: " + nome);
        sb.append("e-mail" + email);
        sb.append("telefone" + telefone);

        Toast.makeText(MainActivity.this, sb.toString(), Toast.LENGTH_LONG).show();
    }
}
