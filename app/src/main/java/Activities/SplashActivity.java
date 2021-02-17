package Activities;

import android.content.Intent;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.airbnb.lottie.LottieAnimationView;
import com.example.esh_ajanda.R;
import org.w3c.dom.Text;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Animation animation= AnimationUtils.loadAnimation(SplashActivity.this,R.anim.rotate);
        TextView textView=findViewById(R.id.txt_splahs_screeen_title);

        LottieAnimationView lottieAnimationView=findViewById(R.id.lottie_splash_scren);

        textView.setAnimation(animation);



        lottieAnimationView.setSpeed(0.5f);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent  intent=new Intent(SplashActivity.this,HastalarActivity.class);

                startActivity(intent);
            }


        },3000);

    }}
