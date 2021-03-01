package Activities;

import Dialogs.AddPatient_BottomSheetDialog;
import Dialogs.Dialog_ExitApp;
import Fragments.HastaListesi.frg_HastaListesiTum;
import android.content.Context;
import android.content.Intent;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.example.esh_ajanda.R;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import Fragments.RandevuListesi.*;
import com.google.android.material.navigation.NavigationView;

public class HomeActivity extends AppCompatActivity {
    Context context= HomeActivity.this;
    FloatingActionButton fb_button;
    BottomAppBar  bottomAppBar;
    CardView crdview_hastalar,crdview_randevuler,crdview_ziyaretler;
    Fragment fragment,fragment_2;
    FragmentTransaction  transaction;
    NavigationView view;
    DrawerLayout drawerLayout;

    Animation animation_bounce,animation_fade,animation_rotate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        fragment=new frg_TumRandevuler(context);
        fragment_2=new frg_HastaListesiTum(context);
        animation_bounce =AnimationUtils.loadAnimation(context,R.anim.bounce);
        animation_fade =AnimationUtils.loadAnimation(context,R.anim.fade_in);
        animation_rotate =AnimationUtils.loadAnimation(context,R.anim.rotate);

        view=findViewById(R.id.navi_view);




        fb_button=findViewById(R.id.floating_button_home_activity);
        bottomAppBar=findViewById(R.id.bottomAppBar_home);
        drawerLayout=findViewById(R.id.layout_drawer_home_activty);
        crdview_hastalar=findViewById(R.id.crdview_home_activty_hastalar);
        crdview_ziyaretler=findViewById(R.id.crdview_home_activty_ziyaretler);
        crdview_randevuler=findViewById(R.id.crdview_home_activty_randevuler);



        crdview_hastalar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                animation_bounce.setDuration(200);

                crdview_hastalar.startAnimation(animation_bounce);

                animation_bounce.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {

                        Intent intent= new Intent(HomeActivity.this,HastalarActivity.class);

                        startActivity(intent);

                        finish();
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });

            }
        });

        crdview_ziyaretler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                crdview_ziyaretler.startAnimation(animation_bounce);

                animation_bounce.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {

                        Intent intent= new Intent(HomeActivity.this,ZiyaretlerActivity.class);

                        startActivity(intent);

                        finish();
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });

            }
        });

        crdview_randevuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                crdview_randevuler.startAnimation(animation_bounce);

                animation_bounce.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {

                        Intent intent= new Intent(HomeActivity.this,RandevulerActivity.class);

                        startActivity(intent);

                        finish();
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
            }
        });

        bottomAppBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START,    true);
            }
        });

        bottomAppBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                boolean sonuc=false;


                SearchView searchView = (SearchView) item.getActionView();

                searchView.setQueryHint("hastalarda ara..");




                switch (item.getItemId())
                {
                    case R.id.menu_app_bar_search_hastalar:


                        sonuc=true;

                        break;
                }

                return sonuc;
            }
        });

        fb_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                animation_fade.setDuration(200);

                fb_button.startAnimation(animation_fade);

                animation_fade.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        new AddPatient_BottomSheetDialog(context,null).show();
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });

            }
        });


    }



    @Override
    public void onBackPressed() {

        new Dialog_ExitApp(context).show();

    }
}