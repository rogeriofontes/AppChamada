package unipac.com.br.appchamada;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import unipac.com.br.appchamada.dao.AlunoDAO;
import unipac.com.br.appchamada.domain.Aluno;
import unipac.com.br.appchamada.util.SecurityPreferences;

public class MainActivity extends AppCompatActivity {

    private ViewHolder viewHolder = new ViewHolder();
    private SecurityPreferences securityPreferences;

    private AlunoDAO alunoDAO = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        securityPreferences = new SecurityPreferences(this);

        this.viewHolder.nomeEdt = (EditText) findViewById(R.id.nomeEdt);
        this.viewHolder.emailEdt = (EditText) findViewById(R.id.emailEdt);
        this.viewHolder.telefoneEdt = (EditText) findViewById(R.id.telefoneEdt);
        this.viewHolder.salvarBtn = (Button) findViewById(R.id.salvarBtn);

        alunoDAO = new AlunoDAO(this);

        this.viewHolder.salvarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nome = MainActivity.this.viewHolder.nomeEdt.getText().toString();
                String email = MainActivity.this.viewHolder.emailEdt.getText().toString();
                String telefone = MainActivity.this.viewHolder.telefoneEdt.getText().toString();

                Aluno aluno = criaAluno(nome, email, telefone);
                insereInformacao(aluno);
            }
        });
    }

    public void insereInformacao(Aluno aluno){
        securityPreferences.guardaString("NOME", aluno.getNome());
        securityPreferences.guardaString("EMAIL", aluno.getEmail());
        securityPreferences.guardaString("TELEFONE", aluno.getTelefone());
    }

    public void lerDados() {
        String nome = securityPreferences.recuperaString("NOME");
        String email = securityPreferences.recuperaString("EMAIL");
        String telefone = securityPreferences.recuperaString("TELEFONE");

        Aluno aluno = criaAluno(nome, email, telefone);
        imprime(aluno);
    }

    public void imprime(Aluno aluno) {
        StringBuilder sb = new StringBuilder(1200);
        sb.append("Os dados cadastrados Foram:");
        sb.append("Nome: " + aluno.getNome());
        sb.append("e-mail" + aluno.getEmail());
        sb.append("telefone" + aluno.getTelefone());

        Toast.makeText(MainActivity.this, sb.toString(), Toast.LENGTH_LONG).show();
    }

    public static class ViewHolder {
        EditText nomeEdt;
        EditText emailEdt;
        EditText telefoneEdt;
        Button salvarBtn;
    }

    private Aluno criaAluno(String nome, String email, String telefone) {
        Aluno aluno = new Aluno();
        aluno.setNome(nome);
        aluno.setEmail(email);
        aluno.setTelefone(telefone);
        return aluno;
    }
}
