package Activities;

import Adapters.FragmentAdapters.FragmentPagerAdapterZiyaretler;
import Dialogs.Dialog_ExitApp;
import Fragments.RandevuListesi.frg_BugunkuRandevuler;
import Fragments.RandevuListesi.frg_TumRandevuler;
import Fragments.RandevuListesi.frg_UzakRandevuler;
import Fragments.RandevuListesi.frg_YakinRandevuler;
import Fragments.ZiyaretListesi.frg_ZiyaretListesiBasarili;
import Fragments.ZiyaretListesi.frg_ZiyaretListesiBasarisiz;
import Fragments.ZiyaretListesi.frg_ZiyaretListesiTum;
import android.content.Context;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;

import android.view.View;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import androidx.appcompat.widget.SearchView;

import androidx.appcompat.widget.Toolbar;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import Adapters.RecyclerViewAdapters.Hastalar.RecyclerViewAdapterOfHastaListesi;
import DataBaseSQLite.DataBaseSQLiteOfPatient.DBSQLiteOfAllPatients;

import Patient.Patient;
import Patient.Telefon;
import androidx.viewpager.widget.ViewPager;
import com.example.esh_ajanda.R;
import com.google.android.material.tabs.TabLayout;


import java.util.ArrayList;

public class RandevulerActivity extends AppCompatActivity {

    ViewPager viewPager;
    TabLayout tabLayout;
    Toolbar toolbar;
    DBSQLiteOfAllPatients liteOfAllPatients;
    FragmentPagerAdapterZiyaretler adapter;


    ArrayList<Fragment> fragments = new ArrayList<>();
    ArrayList<String> tab_isimleri = new ArrayList<>();

    Context context = RandevulerActivity.this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_randevuler);


        liteOfAllPatients = new DBSQLiteOfAllPatients(context);
        liteOfAllPatients.onCreate(liteOfAllPatients.getWritableDatabase());


        toolbar = findViewById(R.id.toolbar_randevuler_activity);

        setSupportActionBar(toolbar);

        sayfalariEkle();

        adapter = new FragmentPagerAdapterZiyaretler(
                getSupportFragmentManager(), this,
                fragments, tab_isimleri);


        tabLayout = findViewById(R.id.tablayout_randevuler_activity);

        viewPager = findViewById(R.id.viewpager_randevuler_activity);

        viewPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewPager);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(RandevulerActivity.this,HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }


    public void sayfalariEkle() {


        fragments.add(new frg_BugunkuRandevuler(context));
        fragments.add(new frg_TumRandevuler(context));
        fragments.add(new frg_UzakRandevuler(context));
        fragments.add(new frg_YakinRandevuler(context));


        tab_isimleri.add("BUG�NK� RANDEV�LER");
        tab_isimleri.add("T�M RANDEV�LER");
        tab_isimleri.add("UZAK RANDEV�LER");
        tab_isimleri.add("YAKIN RANDEV�LER");


    }

    @Override
    public void onBackPressed() {

        new Dialog_ExitApp(context).show();

    }

}