package unipac.com.br.appchamada.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import unipac.com.br.appchamada.domain.Aluno;
import unipac.com.br.appchamada.infra.DbHelper;

public class AlunoDAO {

    private SQLiteDatabase database;
    private DbHelper dbHelper;
    private String[] allColumns = { DbHelper.ID, DbHelper.NOME, DbHelper.EMAIL,
            DbHelper.TELEFONE};

    public AlunoDAO(Context context) {
        dbHelper = new DbHelper(context);
    }

    public Aluno salvar(Aluno aluno) {

        ContentValues values = new ContentValues();
        values.put(DbHelper.NOME, aluno.getNome());
        values.put(DbHelper.EMAIL, aluno.getEmail());
        values.put(DbHelper.TELEFONE, aluno.getTelefone());

        long insertId = database.insert(DbHelper.TABLE_NAME, null, values);

        // To show how to query
        Cursor cursor = database.query(DbHelper.TABLE_NAME, allColumns, DbHelper.ID + " = " +
                insertId, null,null, null, null);
        cursor.moveToFirst();

        return buscarPorId(insertId);
    }

    public Aluno buscarPorId(long id){
        Cursor cursor = database.query(DbHelper.TABLE_NAME, allColumns, DbHelper.ID + " = " +
                id, null,null, null, null);
        cursor.moveToFirst();
        return cursorToObject(cursor);
    }

    public List<Aluno> buscarTodos(){
            Cursor cursor = database.query(DbHelper.TABLE_NAME, allColumns, null, null,null, null, null);

            List<Aluno> alunos = new ArrayList<>();

            if (cursor.moveToFirst()) {

                int idxId = cursor.getColumnIndex(DbHelper.ID);
                int idxNome = cursor.getColumnIndex(DbHelper.NOME);
                int idxEmail = cursor.getColumnIndex(DbHelper.EMAIL);
                int idxTelefone = cursor.getColumnIndex(DbHelper.TELEFONE);

                do {
                    Aluno aluno = new Aluno();
                    alunos.add(aluno);

                    // recupera os atributos de Expense
                    if (idxId != 0)
                        aluno.setId(cursor.getLong(idxId));
                    if (idxNome != 0)
                        aluno.setNome(cursor.getString(idxNome));
                    if (idxEmail != 0)
                        aluno.setEmail(cursor.getString(idxEmail));
                    if (idxTelefone != 0)
                        aluno.setTelefone(cursor.getString(idxTelefone));
                } while (cursor.moveToNext());
            }

            cursor.close();
            return alunos;

    }

    private Aluno cursorToObject(Cursor cursor) {
        Aluno aluno = new Aluno();

        aluno.setId(cursor.getLong(0));
        aluno.setNome(cursor.getString(1));
        aluno.setEmail(cursor.getString(2));
        aluno.setTelefone(cursor.getString(3));

        return aluno;
    }

}
