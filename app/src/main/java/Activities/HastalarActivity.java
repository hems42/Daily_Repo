package Activities;

import Adapters.FragmentAdapters.FragmentPagerAdapterHastalar;
import DataBaseSQLite.DataBaseSQLiteOfPatient.DBSQLiteOfAllPatients;
import Fragments.HastaListesi.*;
import Fragments.RandevuListesi.frg_TumRandevuler;
import Observation.RandevuListesiEkrani;
import Observation.RecyclerRandevü;
import Patient.*;
import android.content.Context;
import android.content.Intent;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import com.example.esh_ajanda.R;
import com.google.android.material.tabs.TabLayout;
import java.text.ParseException;
import java.util.ArrayList;

public class HastalarActivity extends AppCompatActivity {

    ViewPager viewPager;
    TabLayout tabLayout;
    Toolbar toolbar;
    DBSQLiteOfAllPatients liteOfAllPatients;
    FragmentPagerAdapterHastalar adapter;


    ArrayList<Fragment> fragments= new ArrayList<>();
    ArrayList<String> tab_isimleri=new ArrayList<>();

    Context context=HastalarActivity.this;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hastalar);



        liteOfAllPatients = new DBSQLiteOfAllPatients(HastalarActivity.this);
        liteOfAllPatients.onCreate(liteOfAllPatients.getWritableDatabase());




        toolbar=findViewById(R.id.toolbar_hastalar_activity);

        setSupportActionBar(toolbar);

            sayfalariEkle();

        adapter= new FragmentPagerAdapterHastalar(
                getSupportFragmentManager(),this,
                fragments,tab_isimleri);


        tabLayout=findViewById(R.id.tablayout);

        viewPager=findViewById(R.id.viewpager);

        viewPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewPager);


        RecyclerRandevü recyclerRandevü =new RecyclerRandevü();
        RandevuListesiEkrani adapter=new RandevuListesiEkrani("randevü listesi");

        RandevuListesiEkrani adapter_2= new RandevuListesiEkrani("hasta listesi tüm hastalar");
        recyclerRandevü.ekle(adapter);
        recyclerRandevü.ekle(adapter_2);








    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


    }

    public  void sayfalariEkle()
    {


        try {

            fragments.add(new frg_TumRandevuler(context));
            fragments.add(new frg_HastaListesiTum(context));
            fragments.add(new frg_HastaListesiBagimli(context));
            fragments.add(new frg_HastaListesiYariBagimli(context));
            fragments.add(new frg_HastaListesiBagimsiz(context));
            fragments.add(new frg_HastaListesiAktif(context));
            fragments.add(new frg_HastaListesiPasif(context));
            fragments.add(new frg_HastaListesiEx(context));

        } catch (ParseException e) {
            e.printStackTrace();

        }




        tab_isimleri.add("TÜM RANDEVULER");
        tab_isimleri.add("TÜM HASTALAR");
        tab_isimleri.add("BAÐIMLI HASTALAR");
        tab_isimleri.add("YARI BAÐIMLI HASTALAR");
        tab_isimleri.add("BAÐIMSIZ HASTALAR");
        tab_isimleri.add("AKTÝF HASTALAR");
        tab_isimleri.add("PASÝF HASTALAR");
        tab_isimleri.add("EX LÝSTESÝ");


    }







}