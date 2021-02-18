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


        //Parmaðý sensöre dokundurduðunuz anda, telefonda kayýtlý iz ile
        //karsýlastýrma için BiometricPrompt sýnýfýnýn içindeki iþlem durumu metodlarý oluþturulur
        final BiometricPrompt myBiometricPrompt = new BiometricPrompt(this, newExecutor, new BiometricPrompt.AuthenticationCallback() {

            //Bu metodda sensöre dokundurduðunuzda, bir hatayla karsýlaþýp karþýlaþmadýðýnýzýn kontrolunu yaptýk
            //Örnek hatalar; cihazýn dokunmatik sensörü kir kaplýdýr, kullanýcý bu cihaza herhangi bir parmak izi kaydetmemiþtir veya tam biyometrik tarama yapmak için yeterli bellek yoktur
            @Override
            public void onAuthenticationError(int errorCode, CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
                if (errorCode == BiometricPrompt.ERROR_USER_CANCELED) {
                } else {

                }
            }

            //Bu metod, parmak izi cihazda kayýtlý parmak izlerinden biriyle baþarýlý bir þekilde eþleþtiðinde çaðrýlýr.
            @Override
            public void onAuthenticationSucceeded(BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);


                Intent intent = new Intent(LoginActivity.this, HastalarActivity.class);
                startActivity(intent);
                finish();
            }

            //Bu metodda parmak izi tanýma iþlemi baþarýlý olmadýðýnda, yapýlacak iþlemler yazýlmalý
            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();

            }

        });
        //kimlik doðrulama iletiþim kutusunda görünmesi gereken metni tanýmlamanýz ve kimlik doðrulamasýný iptal etmesini saðlayan metoda isim atama
        final BiometricPrompt.PromptInfo promptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle("Parmak Ýzi ile Giriþ!!")
                .setSubtitle("Giriþ yapmak için parmak izinizi kullanýnýz!!")

                .setNegativeButtonText("VAZGEÇ")
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
                        Toast.makeText(context, "Giriþ Baþarýlý!!!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this, HastalarActivity.class);
                        editText.setText("");
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(context, "Hatalý Parola...!!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    editText.setError("bu alaný doldurun!!");
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

        Toast.makeText(LoginActivity.this, "Giriþ Baþarýlý!!!", Toast.LENGTH_SHORT).show();

        super.onDestroy();

    }


}






