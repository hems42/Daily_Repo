package Activities;

import Utils.CustomTime;
import android.content.Intent;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.airbnb.lottie.LottieAnimationView;
import com.example.esh_ajanda.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


//
//        ExcelManager excelManager= new ExcelManager();
//
//        String path="/sdcard/hastalar.xls";
//
//        DBSQLiteOfAllPatients dbsqLiteOfAllPatients= new DBSQLiteOfAllPatients(SplashActivity.this);
//        dbsqLiteOfAllPatients.onCreate(dbsqLiteOfAllPatients.getWritableDatabase());
//
//        for(Patient patient:excelManager.getPatientFromExcel(path))
//        {
//           dbsqLiteOfAllPatients.addPatient(patient);
//        }


        Animation animation= AnimationUtils.loadAnimation(SplashActivity.this,R.anim.rotate);
        TextView textView=findViewById(R.id.txt_splahs_screeen_title);

        LottieAnimationView lottieAnimationView=findViewById(R.id.lottie_splash_scren);

        textView.setAnimation(animation);

        CustomTime.startApp(this); // uygulama baþlangýç dakikasýný aldýk.


        lottieAnimationView.setSpeed(0.5f);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent  intent=new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }


        },3000);

    }}
