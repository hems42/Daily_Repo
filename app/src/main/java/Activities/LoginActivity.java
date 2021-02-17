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
                .setDescription("Giri� Yapmak ��in Parmak �zinizi Kullan�n!!")
                .setNegativeButton("VAZGE�", newExecutor, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();
                    }
                })
                .setTitle("PARMAK �Z� �LE G�R��")
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
                Toast.makeText(context,"PARMAK �Z� HATA VERD�",Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onAuthenticationSucceeded(BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);

                Toast.makeText(context,"PARMAK �Z� TANIMA BA�ARILI",Toast.LENGTH_SHORT).show();
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
        //Parma�� sens�re dokundurdu�unuz anda, telefonda kay�tl� iz ile
        //kars�last�rma i�in BiometricPrompt s�n�f�n�n i�indeki i�lem durumu metodlar� olu�turulur
        final BiometricPrompt myBiometricPrompt =new BiometricPrompt(this, newExecutor, new BiometricPrompt.AuthenticationCallback() {

            //Bu metodda sens�re dokundurdu�unuzda, bir hatayla kars�la��p kar��la�mad���n�z�n kontrolunu yapt�k
            //�rnek hatalar; cihaz�n dokunmatik sens�r� kir kapl�d�r, kullan�c� bu cihaza herhangi bir parmak izi kaydetmemi�tir veya tam biyometrik tarama yapmak i�in yeterli bellek yoktur
            @Override
            public void onAuthenticationError(int errorCode,CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
                if (errorCode == BiometricPrompt.BIOMETRIC_ERROR_CANCELED) {
                } else {
                    Log.d("MainActivity", "bir hata olu�tu");
                }
            }
            //Bu metod, parmak izi cihazda kay�tl� parmak izlerinden biriyle ba�ar�l� bir �ekilde e�le�ti�inde �a�r�l�r.
            @Override
            public void onAuthenticationSucceeded(BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                Log.d("MainActivity", "Parmak izi ba�ar�yla tan�nd�");
            }
            //Bu metodda parmak izi tan�ma i�lemi ba�ar�l� olmad���nda, yap�lacak i�lemler yaz�lmal�
            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                Log.d("MainActivity", "Parmak izi tan�nmad�");
            }

        });
        //kimlik do�rulama ileti�im kutusunda g�r�nmesi gereken metni tan�mlaman�z ve kimlik do�rulamas�n� iptal etmesini sa�layan metoda isim atama
        final BiometricPrompt.PromptInfo promptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle("Ba�l�k yaz�lacak alan")
                .setSubtitle("Altba�l�k yaz�lacak alan")
                .setDescription("A��klama yaz�lacak alan")
                .setNegativeButtonText("�ptal")
                .build();

        findViewById(R.id.btn_login_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myBiometricPrompt.authenticate(promptInfo);
            }
        });*/

    }


}