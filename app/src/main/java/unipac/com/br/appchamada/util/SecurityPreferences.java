package unipac.com.br.appchamada.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import unipac.com.br.appchamada.MainActivity;

public class SecurityPreferences {

    public static final String NULO = "Nulo";

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    public SecurityPreferences(Context context) {
         this.preferences = PreferenceManager.getDefaultSharedPreferences(context);
         this.editor = preferences.edit();
    }

    public void guardaString(String chave, String valor) {
        editor.putString(chave, valor).commit();
    }

    public String recuperaString(String chave) {
        return preferences.getString(chave, NULO);
    }

    public void guardaBoleano(String chave, boolean valor) {
        editor.putBoolean(chave, valor).commit();
    }

    public boolean recuperaBoleano(String chave) {
        return preferences.getBoolean(chave, Boolean.FALSE);
    }
}
