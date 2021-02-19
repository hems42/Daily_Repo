package Utils;

import android.content.Context;
import android.content.SharedPreferences;

public class PassWordManager {

    private Context context;
   private SharedPreferences sharedPassword;
   private SharedPreferences.Editor editorPassword;
    public PassWordManager(Context context) {
        this.context = context;
        sharedPassword=context.getSharedPreferences(PAROLA_KULLANICI,Context.MODE_PRIVATE);
        editorPassword=sharedPassword.edit();

//        editorPassword.putInt(PAROLA_GECERLI_KULLANICI,1234);
//        editorPassword.commit();


    }

    public static String PAROLA_KULLANICI="kullanici_parolasi";
    public static String PAROLA_GECERLI_KULLANICI="geçerli parola";
    public static int GECERLI_PAROLA_YOK=0;

    public int getCurrentUserPassWord()
    {


        int currentPassword=sharedPassword.getInt(PAROLA_GECERLI_KULLANICI,0);

        return currentPassword;

    }

    public boolean changePassord(int new_Password)
    {
        boolean sonuc=false;

        if(getCurrentUserPassWord()!=GECERLI_PAROLA_YOK)
        {
            editorPassword.putInt(PAROLA_GECERLI_KULLANICI,new_Password);

            if(editorPassword.commit())
            {
                sonuc=true;
            }
        }


        return  sonuc;
    }
}
