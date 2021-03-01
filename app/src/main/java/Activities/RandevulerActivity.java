package Activities;

import Adapters.FragmentAdapters.FragmentPagerAdapterZiyaretler;
import Fragments.RandevuListesi.frg_BugunkuRandevuler;
import Fragments.RandevuListesi.frg_TumRandevuler;
import Fragments.RandevuListesi.frg_UzakRandevuler;
import Fragments.RandevuListesi.frg_YakinRandevuler;
import Fragments.ZiyaretListesi.frg_ZiyaretListesiBasarili;
import Fragments.ZiyaretListesi.frg_ZiyaretListesiBasarisiz;
import Fragments.ZiyaretListesi.frg_ZiyaretListesiTum;
import android.content.Context;

import android.view.Menu;
import android.view.MenuItem;

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


    }


    public void sayfalariEkle() {


        fragments.add(new frg_BugunkuRandevuler(context));
        fragments.add(new frg_TumRandevuler(context));
        fragments.add(new frg_UzakRandevuler(context));
        fragments.add(new frg_YakinRandevuler(context));


        tab_isimleri.add("BUGÜN KÜ RANDEVÜLER");
        tab_isimleri.add("TÜM RANDEVÜLER");
        tab_isimleri.add("UZAK RANDEVÜLER");
        tab_isimleri.add("YAKIN RANDEVÜLER");


    }

}