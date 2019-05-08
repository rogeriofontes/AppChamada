package unipac.com.br.appchamada.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import unipac.com.br.appchamada.domain.Aluno;

public class AlunoDAO extends SQLiteOpenHelper {

    public static final String CHAMADA_DB = "chamada.db";

    public AlunoDAO(Context context) {
        super(context, CHAMADA_DB, null, 1);
    }

    private void salvar(Aluno aluno) {

    }

    private void alterar(Aluno aluno) {

    }

    private Aluno Buscar() {
        return null;
    }

    private void deletar(Long id) {

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        StringBuilder sb = new StringBuilder(1200);
        sb.append(" CREATE TABLE");
        sb.append(" (id int autoincrement not null,");
        sb.append(" nome varchar(120) not null, ");
        sb.append(" email varchar(120) not null, ");
        sb.append(" telefone varchar(120) not null) ");
        db.execSQL(sb.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }
}
