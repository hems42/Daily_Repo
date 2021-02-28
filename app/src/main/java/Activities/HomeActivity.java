package Activities;

import Dialogs.AddPatient_BottomSheetDialog;
import Fragments.HastaListesi.frg_HastaListesiTum;
import android.content.Context;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
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

    Fragment fragment,fragment_2;
    FragmentTransaction  transaction;
    NavigationView view;
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        fragment=new frg_TumRandevuler(context);
        fragment_2=new frg_HastaListesiTum(context);


        view=findViewById(R.id.navi_view);



          transaction=getSupportFragmentManager().beginTransaction();

          transaction.add(R.id.frg_container_activity_home,fragment,"gg");


        transaction.commit();

        fb_button=findViewById(R.id.floating_button_home_activity);
        bottomAppBar=findViewById(R.id.bottomAppBar_home);
        drawerLayout=findViewById(R.id.layout_drawer_home_activty);




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


}