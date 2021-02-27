package Activities;

import Adapters.FragmentAdapters.FragmentPagerAdapterHastalar;
import BroadCasts.BackUpBroadCast;
import DataBaseSQLite.DBSQLiteOfPersonelInformations;
import DataBaseSQLite.DataBaseSQLiteOfPatient.DBSQLiteOfAllPatients;
import Excel.ExcelManager;
import Fragments.HastaListesi.*;
import Fragments.RandevuListesi.frg_TumRandevuler;
import Fragments.ZiyaretListesi.frg_ZiyaretListesiTum;
import Fragments.frg_HastaBulma;
import Manager.PatientInnerManager;
import Patient.Patient;
import Patient.Telefon;
import android.Manifest;
import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;
import com.example.esh_ajanda.R;
import com.google.android.material.tabs.TabLayout;
import org.apache.poi.ss.formula.functions.T;

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

        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE},0);





        liteOfAllPatients = new DBSQLiteOfAllPatients(HastalarActivity.this);
        liteOfAllPatients.onCreate(liteOfAllPatients.getWritableDatabase());




        toolbar=findViewById(R.id.toolbar_hastalar_activity);

        setSupportActionBar(toolbar);

            sayfalariEkle();

        adapter= new FragmentPagerAdapterHastalar(
                getSupportFragmentManager(),this,
                fragments,tab_isimleri);


        tabLayout=findViewById(R.id.tablayout_hastalar_activity);

        viewPager=findViewById(R.id.viewpager_hastalar_activity);

        viewPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewPager);





    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        Uri  uri;

        if(requestCode== Activity.RESULT_OK&&resultCode==125)
        {
            System.out.println("kodu doðru buldu");

            if(data!=null)
            {
                System.out.println("intente girdi");

                uri=data.getData();

                System.out.println("uri path : "+uri.getPath());

            }
        }


    }

    public  void sayfalariEkle()
    {


        try {

            fragments.add(new frg_ZiyaretListesiTum(context));
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




        tab_isimleri.add("ziyaretler");
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