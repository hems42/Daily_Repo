package Activities;

import Dialogs.AddPatient_BottomSheetDialog;
import Dialogs.Dialog_ExitApp;
import Fragments.HastaListesi.frg_HastaListesiTum;
import Utils.ProgressBar;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;
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
    CardView crdview_hastalar,crdview_randevuler,crdview_ziyaretler,crdview_istatistikler,crdview_veri_yonetimi,crdview_gun_ozeti;
    Fragment fragment,fragment_2;
    FragmentTransaction  transaction;
    NavigationView view;
    DrawerLayout drawerLayout;

    Animation animation_slide,animation_fade,animation_rotate;

    ProgressBar  progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);

        fragment=new frg_TumRandevuler(context);
        fragment_2=new frg_HastaListesiTum(context);
        animation_slide =AnimationUtils.loadAnimation(context,R.anim.sequential);
        animation_fade =AnimationUtils.loadAnimation(context,R.anim.fade_in);
        animation_rotate =AnimationUtils.loadAnimation(context,R.anim.rotate);
        progressBar=new ProgressBar(context);

        view=findViewById(R.id.navi_view);




        fb_button=findViewById(R.id.floating_button_home_activity);
        bottomAppBar=findViewById(R.id.bottomAppBar_home);
        drawerLayout=findViewById(R.id.layout_drawer_home_activty);
        crdview_hastalar=findViewById(R.id.crdview_home_activty_hastalar);
        crdview_ziyaretler=findViewById(R.id.crdview_home_activty_ziyaretler);
        crdview_randevuler=findViewById(R.id.crdview_home_activty_randevuler);
        crdview_istatistikler=findViewById(R.id.crdview_home_activty_istatistikler);
        crdview_veri_yonetimi=findViewById(R.id.crdview_home_activty_veri_yonetimi);
        crdview_gun_ozeti=findViewById(R.id.crdview_home_activty_gun_ozeti);



        crdview_hastalar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                startProgress();


                Intent intent= new Intent(HomeActivity.this,HastalarActivity.class);

                startActivity(intent);



                finish();


            }
        });

        crdview_ziyaretler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                       startProgress();

                        Intent intent= new Intent(HomeActivity.this,ZiyaretlerActivity.class);

                        startActivity(intent);

                        finish();


            }
        });

        crdview_randevuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                       startProgress();

                        Intent intent= new Intent(HomeActivity.this,RandevulerActivity.class);

                        startActivity(intent);

                        finish();

            }
        });

        crdview_istatistikler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                startProgress();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        progressBar.stopProgress();

                        Toast.makeText(context,"Modül Henüz Kullanýmda Deðil!!",Toast.LENGTH_SHORT).show();
                    }
                },1000);

            }
        });

        crdview_veri_yonetimi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startProgress();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        progressBar.stopProgress();

                        Toast.makeText(context,"Modül Henüz Kullanýmda Deðil!!",Toast.LENGTH_SHORT).show();
                    }
                },1000);



            }
        });

        crdview_gun_ozeti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startProgress();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        progressBar.stopProgress();

                        Toast.makeText(context,"Modül Henüz Kullanýmda Deðil!!",Toast.LENGTH_SHORT).show();
                    }
                },1000);

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




                new AddPatient_BottomSheetDialog(context,null).show();




            }
        });


    }



    @Override
    public void onBackPressed() {

        new Dialog_ExitApp(context).show();

    }


    private void startProgress()
    {
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                progressBar.show();
            }
        });
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup contnr, Bundle savedInstSt) {
        try {
           View mContentView = inflater.inflate(R.layout.activity_home, null);
            // ... rest of body of onCreateView() ...
        } catch (Exception e) {
            System.out.println("muhtemel hata : "+e.toString());
            throw e;
        }

        return new View(context);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        progressBar.dismiss();
    }
}