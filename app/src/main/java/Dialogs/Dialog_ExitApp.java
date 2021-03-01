package Dialogs;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import com.example.esh_ajanda.R;

public class Dialog_ExitApp extends AlertDialog {

    View view;
    Button btn_ok,btn_cancel;
    ImageView img_view;
    Activity activity;

    public Dialog_ExitApp(@NonNull Context context) {
        super(context, R.style.AlertCustomDialog);

        activity= (Activity) context;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Animation animation_fade= AnimationUtils.loadAnimation(getContext(),R.anim.fade_in);
        Animation animation_rotate= AnimationUtils.loadAnimation(getContext(),R.anim.rotate);

        view=getLayoutInflater().inflate(R.layout.lyt_programdan_cikma,null);

        btn_ok=view.findViewById(R.id.btn_lyt_programdan_cikma_ok);
        btn_cancel=view.findViewById(R.id.btn_lyt_programdan_cikma_cancel);

        img_view=view.findViewById(R.id.img_lyt_programdan_cikma_image);


        img_view.setAnimation(animation_fade);

        img_view.animate();


        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                img_view.startAnimation(animation_rotate);



                animation_rotate.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        activity.finish();
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });


            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        setContentView(view);


    }
}
