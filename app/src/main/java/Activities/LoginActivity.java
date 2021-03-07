package Activities;

import Dialogs.BottomSheet_Dialog_ChangePassWord;
import android.content.Context;
import android.content.Intent;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.biometric.BiometricPrompt;
import com.example.esh_ajanda.R;

import java.util.concurrent.Executors;

public class LoginActivity extends AppCompatActivity {

    Context context = LoginActivity.this;
    Button btn_login, btn_cange_password;
    ImageView btn_finger_print;
    EditText edtxt_password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btn_login = findViewById(R.id.btn_login_ok);
        edtxt_password = findViewById(R.id.edtxt_login_pasword);
        btn_finger_print = findViewById(R.id.img_login_finger_print);
        btn_cange_password = findViewById(R.id.btn_login_change_password);
        final String[] gelen_parola = {null};





        gotoHome();



        BiometricPrompt.AuthenticationCallback authenticationCallback= new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);


            }

            @Override
            public void onAuthenticationSucceeded(BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);


                //     Toast.makeText(context,"PARMAK ÝZÝ TANIMA BAÞARILI",Toast.LENGTH_SHORT).show();


                gotoHome();


            }
        };

        final BiometricPrompt myBiometricPrompt = new BiometricPrompt(this,Executors.newSingleThreadExecutor(), new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                gotoHome();

            }
        });



        final BiometricPrompt.PromptInfo promptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle("Parmak Ýziyle Giriþ")
                .setDescription("Giriþ Yapabilmek Ýçin Parmak Ýzinizi Kullanýnýz..")
                .setNegativeButtonText("Ýptal")
                .build();


        btn_cange_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new BottomSheet_Dialog_ChangePassWord(context).show();
            }
        });

        btn_finger_print.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myBiometricPrompt.authenticate(promptInfo);
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gelen_parola[0] = edtxt_password.getText().toString();
                if (gelen_parola[0] != null && !gelen_parola[0].matches("")) {
                    if (gelen_parola[0].matches("1234")) {
                        Toast.makeText(context, "Giriþ Baþarýlý!!!", Toast.LENGTH_SHORT).show();
                        gotoHome();
                    } else {
                        Toast.makeText(context, "Hatalý Parola...!!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    edtxt_password.setError("bu alaný doldurun!!");
                }
            }
        });


    }


    private void gotoHome() {
        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

}


