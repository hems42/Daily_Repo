package Activities;

import android.content.Context;

import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import androidx.appcompat.widget.SearchView;

import androidx.appcompat.widget.Toolbar;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import Adapters.RecyclerViewAdapters.Hastalar.RecyclerViewAdapterOfHastaListesi;
import DataBaseSQLite.DataBaseSQLiteOfPatient.DBSQLiteOfAllPatients;

import Patient.Patient;
import Patient.Telefon;
import com.example.esh_ajanda.R;
import com.google.android.material.tabs.TabLayout;


import java.util.ArrayList;

public class VisitPatientActivity extends AppCompatActivity {
    Context context = VisitPatientActivity.this;
    DBSQLiteOfAllPatients liteOfAllPatients;
    RecyclerViewAdapterOfHastaListesi recyclerViewAdapterOfHastaListesi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visit_patient);



        TabLayout tabLayout=findViewById(R.id.tablayout_hastalar_activity);
/*
        tabLayout.addTab(new TabLayout.Tab().setText("hasta ekleme"));
        tabLayout.addTab(new TabLayout.Tab().setText("hasta dosyasý"));
        tabLayout.addTab(new TabLayout.Tab().setText("hasta ziyaret ekraný"));
        tabLayout.addTab(new TabLayout.Tab().setText("hasta ekelme"));
        */


        Toolbar toolbar = findViewById(R.id.toolbar_visit);

        setSupportActionBar(toolbar);


        liteOfAllPatients = new DBSQLiteOfAllPatients(context);
        liteOfAllPatients.onCreate(liteOfAllPatients.getWritableDatabase());


        /*
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.icon);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        bitmap.compress(Bitmap.CompressFormat.JPEG, 20, byteArrayOutputStream);

        VisitInformations visitInformations= new VisitInformations();

        visitInformations.visitType="kontrol muayenesi";
        visitInformations.appointmentDate="15.01.2021";



        Patient patientexample= new Patient(context);

        patientexample.tc_no="00000000001";
        patientexample.name="ALÝM";
        patientexample.surname="CANSU";
        patientexample.age=75;
        patientexample.dependency=Patient.YARI_BAGIMLI;
        patientexample.sex= Patient.ERKEK;
        patientexample.final_situation=Patient.PASIF;

        visitInformations.visitDate="15.01.2021";
        visitInformations.visitResult=VisitInformations.TAMAMLANDI;
        visitInformations.notes="bir sýkýntýýsý yok sadece caný sýkýlmýþ";
        visitInformations.sign=byteArrayOutputStream.toByteArray();

        patientexample.ziyaret_ekle(visitInformations);
        patientexample.randevu_sil(visitInformations);

      // liteOfAllPatients.addPatient(patientexample);


       // patientexample.randevu_ekle(visitInformations);


        PersonelInformations info= new PersonelInformations();


        info.adress_description="kendi evi";
        info.city="KONYA";
        info.district="karapýnar";
        info.neighborhood="zafer mahallesi";
        info.street="128345 sok";
        info.apartmant_name="pompacýlar apartmaný";
        info.door_number=12;
        info.tel_no_description="kendi telefonu";
        info.tel_no1="05347894556";
        info.tel_no2="05387821245";

        patientexample.kisisel_bilgi_ekle(info);

        PersonelInformations info2= new PersonelInformations();

        info2.tel_no_description="kýzýnýn cebi";
        info2.tel_no1="05372365896";
        info2.tel_no2="05312347889";

        patientexample.kisisel_bilgi_ekle(info2);


*/


        Patient patientexample= new Patient();

        patientexample.tc_no="00000000009";
        patientexample.name="SALÝM";
        patientexample.surname="KANSUK";
        patientexample.birthday ="12/07/1983";
        patientexample.dependency=Patient.YARI_BAGIMLI;
        patientexample.sex= Patient.ERKEK;
        patientexample.final_situation=Patient.PASIF;


        Telefon telefon= new Telefon();
        telefon.tel_no_description="kýzýnýn teli";
        telefon.tel_no1="05331237356";
        telefon.tel_no2="05312224237";

      /*  for(int i=0;i<255;i++)
        {
            generatePatient(patientexample);
            liteOfAllPatients.addPatient(patientexample);
        }

*/
        recyclerViewAdapterOfHastaListesi = new RecyclerViewAdapterOfHastaListesi(context,liteOfAllPatients.getAllPatient(),"tgfgh");

        RecyclerView recyclerView = findViewById(R.id.recyclerview);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView.setAdapter(recyclerViewAdapterOfHastaListesi);
       recyclerView.setLayoutManager(layoutManager);



    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.searhmenu, menu);

        MenuItem menuItem = menu.findItem(R.id.menu_search);


        SearchView searchView = (SearchView) menuItem.getActionView();


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {


                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ArrayList<Patient> filtretedPatients = new ArrayList<>();


                for (Patient patient : liteOfAllPatients.getAllPatient()) {
                    if (patient.name.toLowerCase().contains(newText.toLowerCase()) || patient.surname.toLowerCase().contains(newText.toLowerCase())) {
                        filtretedPatients.add(patient);
                    }
                }

                recyclerViewAdapterOfHastaListesi.setPatient(filtretedPatients);
                recyclerViewAdapterOfHastaListesi.notifyDataSetChanged();

                return true;
            }
        });


        return super.onCreateOptionsMenu(menu);
    }















/*


        FragmentManager fragmentManager= getSupportFragmentManager();

        FragmentTransaction transaction= fragmentManager.beginTransaction();


        transaction.add(R.id.activity_visit_patient,new fragment1(),"dtgtgdt");


        transaction.commit();
*/








}