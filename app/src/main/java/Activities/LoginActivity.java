package Activities;

import android.content.Context;
import android.content.DialogInterface;
import android.hardware.biometrics.BiometricManager;
import android.hardware.biometrics.BiometricPrompt;
import android.hardware.biometrics.*;
import android.os.CancellationSignal;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.example.esh_ajanda.R;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class LoginActivity extends AppCompatActivity {

    Context context=LoginActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

/*
        Executor newExecutor = Executors.newSingleThreadExecutor();
        BiometricPrompt.Builder builder= new BiometricPrompt.Builder(context);



     BiometricPrompt biometricPrompt= builder
                .setDescription("Giriþ Yapmak Ýçin Parmak Ýzinizi Kullanýn!!")
                .setNegativeButton("VAZGEÇ", newExecutor, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();
                    }
                })
                .setTitle("PARMAK ÝZÝ ÝLE GÝRÝÞ")
                .setConfirmationRequired(true)
                .build();

        CancellationSignal cancellationSignal= new CancellationSignal();

        cancellationSignal.setOnCancelListener(new CancellationSignal.OnCancelListener() {
            @Override
            public void onCancel() {
                Toast.makeText(context,"Parmak izi iptal edildi",Toast.LENGTH_SHORT).show();
            }
        });


        BiometricPrompt.AuthenticationCallback authenticationCallback= new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
                Toast.makeText(context,"PARMAK ÝZÝ HATA VERDÝ",Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onAuthenticationSucceeded(BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);

                Toast.makeText(context,"PARMAK ÝZÝ TANIMA BAÞARILI",Toast.LENGTH_SHORT).show();
            }
        };


        Button button=findViewById(R.id.btn_login_ok);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                biometricPrompt.authenticate(cancellationSignal,newExecutor,authenticationCallback);
            }
        });
*/

        /*
        //Parmaðý sensöre dokundurduðunuz anda, telefonda kayýtlý iz ile
        //karsýlastýrma için BiometricPrompt sýnýfýnýn içindeki iþlem durumu metodlarý oluþturulur
        final BiometricPrompt myBiometricPrompt =new BiometricPrompt(this, newExecutor, new BiometricPrompt.AuthenticationCallback() {

            //Bu metodda sensöre dokundurduðunuzda, bir hatayla karsýlaþýp karþýlaþmadýðýnýzýn kontrolunu yaptýk
            //Örnek hatalar; cihazýn dokunmatik sensörü kir kaplýdýr, kullanýcý bu cihaza herhangi bir parmak izi kaydetmemiþtir veya tam biyometrik tarama yapmak için yeterli bellek yoktur
            @Override
            public void onAuthenticationError(int errorCode,CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
                if (errorCode == BiometricPrompt.BIOMETRIC_ERROR_CANCELED) {
                } else {
                    Log.d("MainActivity", "bir hata oluþtu");
                }
            }
            //Bu metod, parmak izi cihazda kayýtlý parmak izlerinden biriyle baþarýlý bir þekilde eþleþtiðinde çaðrýlýr.
            @Override
            public void onAuthenticationSucceeded(BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                Log.d("MainActivity", "Parmak izi baþarýyla tanýndý");
            }
            //Bu metodda parmak izi tanýma iþlemi baþarýlý olmadýðýnda, yapýlacak iþlemler yazýlmalý
            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                Log.d("MainActivity", "Parmak izi tanýnmadý");
            }

        });
        //kimlik doðrulama iletiþim kutusunda görünmesi gereken metni tanýmlamanýz ve kimlik doðrulamasýný iptal etmesini saðlayan metoda isim atama
        final BiometricPrompt.PromptInfo promptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle("Baþlýk yazýlacak alan")
                .setSubtitle("Altbaþlýk yazýlacak alan")
                .setDescription("Açýklama yazýlacak alan")
                .setNegativeButtonText("Ýptal")
                .build();

        findViewById(R.id.btn_login_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myBiometricPrompt.authenticate(promptInfo);
            }
        });*/

    }


}