package Activities;

import Dialogs.ChangePassWord;
import Utils.CustomTime;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.TaskInfo;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.*;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.biometric.BiometricPrompt;
import com.example.esh_ajanda.R;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class LoginActivity extends AppCompatActivity {

    Context context = LoginActivity.this;
    ImageView img_finger;
    Button btn_change_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        img_finger = findViewById(R.id.img_login_finger_print);
        btn_change_password = findViewById(R.id.btn_login_change_password);

        Executor newExecutor = Executors.newSingleThreadExecutor();
        Executor newExecutor_2 = Executors.newSingleThreadExecutor();


        //Parma�� sens�re dokundurdu�unuz anda, telefonda kay�tl� iz ile
        //kars�last�rma i�in BiometricPrompt s�n�f�n�n i�indeki i�lem durumu metodlar� olu�turulur
        final BiometricPrompt myBiometricPrompt = new BiometricPrompt(this, newExecutor, new BiometricPrompt.AuthenticationCallback() {

            //Bu metodda sens�re dokundurdu�unuzda, bir hatayla kars�la��p kar��la�mad���n�z�n kontrolunu yapt�k
            //�rnek hatalar; cihaz�n dokunmatik sens�r� kir kapl�d�r, kullan�c� bu cihaza herhangi bir parmak izi kaydetmemi�tir veya tam biyometrik tarama yapmak i�in yeterli bellek yoktur
            @Override
            public void onAuthenticationError(int errorCode, CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
                if (errorCode == BiometricPrompt.ERROR_USER_CANCELED) {
                } else {

                }
            }

            //Bu metod, parmak izi cihazda kay�tl� parmak izlerinden biriyle ba�ar�l� bir �ekilde e�le�ti�inde �a�r�l�r.
            @Override
            public void onAuthenticationSucceeded(BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);


                Intent intent = new Intent(LoginActivity.this, HastalarActivity.class);
                startActivity(intent);
                finish();
            }

            //Bu metodda parmak izi tan�ma i�lemi ba�ar�l� olmad���nda, yap�lacak i�lemler yaz�lmal�
            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();

            }

        });
        //kimlik do�rulama ileti�im kutusunda g�r�nmesi gereken metni tan�mlaman�z ve kimlik do�rulamas�n� iptal etmesini sa�layan metoda isim atama
        final BiometricPrompt.PromptInfo promptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle("Parmak �zi ile Giri�!!")
                .setSubtitle("Giri� yapmak i�in parmak izinizi kullan�n�z!!")

                .setNegativeButtonText("VAZGE�")
                .build();


        EditText editText = findViewById(R.id.edtxt_login_pasword);

        final String[] gelen_parola = {null};


        Button button = findViewById(R.id.btn_login_ok);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gelen_parola[0] = editText.getText().toString();
                if (gelen_parola[0] != null && !gelen_parola[0].matches("")) {
                    if (gelen_parola[0].matches("1234")) {
                        Toast.makeText(context, "Giri� Ba�ar�l�!!!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this, HastalarActivity.class);
                        editText.setText("");
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(context, "Hatal� Parola...!!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    editText.setError("bu alan� doldurun!!");
                }
            }
        });


        Animation animation = AnimationUtils.loadAnimation(context, R.anim.rotate);

        img_finger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                myBiometricPrompt.authenticate(promptInfo);

                img_finger.startAnimation(animation);

            }
        });

        editText.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {


                return true;
            }
        });


        btn_change_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ChangePassWord(context).show();
            }
        });


    }

    @Override
    protected void onDestroy() {

        Toast.makeText(LoginActivity.this, "Giri� Ba�ar�l�!!!", Toast.LENGTH_SHORT).show();

        super.onDestroy();

    }


}






