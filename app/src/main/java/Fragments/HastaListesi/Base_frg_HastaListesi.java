package Fragments.HastaListesi;

import Adapters.RecyclerViewAdapters.Hastalar.RecyclerViewAdapterOfHastaListesi;
import DataBaseSQLite.DataBaseSQLiteOfPatient.DBSQLiteOfAllPatients;
import Dialogs.AddPatient_BottomSheetDialog;
import Observation.adapterObserverPatientList;
import Patient.Patient;
import Utils.PatientUtillty;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.*;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.esh_ajanda.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.text.ParseException;
import java.util.ArrayList;

public abstract class Base_frg_HastaListesi extends Fragment  {

    public TextView txt_hasta_sayisi;
    public Context context;
    public DBSQLiteOfAllPatients dbsqlPatient;
    public RecyclerView recyclerView;
    public ArrayList<Patient> innerlistPatient;

    public Activity  activity;

   public RecyclerViewAdapterOfHastaListesi adapter;

   public LinearLayoutManager layoutManager;






    public Base_frg_HastaListesi(Context context)  {
        this.context = context;

        dbsqlPatient= new DBSQLiteOfAllPatients(context);
        dbsqlPatient.onCreate(dbsqlPatient.getWritableDatabase());
        innerlistPatient =dbsqlPatient.getAllPatient();
        adapter= new RecyclerViewAdapterOfHastaListesi(context, innerlistPatient,Patient.TUM_HASTALAR);

        activity= (Activity) context;


        applyObserver(); // recadaptorunun deðiþikliklerini listeye yansýtsýnlar dimi


    }



    public Base_frg_HastaListesi(Context context, String dependency_or_finalSituaton) {
        this.context = context;

        dbsqlPatient= new DBSQLiteOfAllPatients(context);
        dbsqlPatient.onCreate(dbsqlPatient.getWritableDatabase());

        activity= (Activity) context;

        if(dependency_or_finalSituaton.matches(Patient.BAGIMSIZ))
        {
            innerlistPatient =dbsqlPatient.getAllPatientDependency(dependency_or_finalSituaton);


            if(innerlistPatient !=null)
            {
                adapter=new RecyclerViewAdapterOfHastaListesi(context, innerlistPatient,Patient.BAGIMSIZ);

                applyObserver();
            }

        }

        else if(dependency_or_finalSituaton.matches(Patient.YARI_BAGIMLI))
        {
            innerlistPatient =dbsqlPatient.getAllPatientDependency(dependency_or_finalSituaton);


            if(innerlistPatient !=null)
            {
                adapter=new RecyclerViewAdapterOfHastaListesi(context, innerlistPatient,Patient.YARI_BAGIMLI);

                applyObserver();
            }
        }


        else if(dependency_or_finalSituaton.matches(Patient.TAM_BAGIMLI))
        {
            innerlistPatient =dbsqlPatient.getAllPatientDependency(dependency_or_finalSituaton);


            if(innerlistPatient !=null)
            {
                adapter=new RecyclerViewAdapterOfHastaListesi(context, innerlistPatient,Patient.TAM_BAGIMLI);

                applyObserver();
            }

        }

        else if(dependency_or_finalSituaton.matches(Patient.AKTIF))
        {
            innerlistPatient =dbsqlPatient.getAllPatientFinalSituation(dependency_or_finalSituaton);


            if(innerlistPatient !=null)
            {
                adapter=new RecyclerViewAdapterOfHastaListesi(context, innerlistPatient,Patient.AKTIF);

                applyObserver();
            }

        }


        else if(dependency_or_finalSituaton.matches(Patient.PASIF))
        {
            innerlistPatient =dbsqlPatient.getAllPatientFinalSituation(dependency_or_finalSituaton);


            if(innerlistPatient !=null)
            {
                adapter=new RecyclerViewAdapterOfHastaListesi(context, innerlistPatient,Patient.PASIF);

                applyObserver();
            }
        }


        else if(dependency_or_finalSituaton.matches(Patient.EX))
        {
            innerlistPatient =dbsqlPatient.getAllPatientFinalSituation(dependency_or_finalSituaton);

            if(innerlistPatient !=null)
            {
                adapter=new RecyclerViewAdapterOfHastaListesi(context, innerlistPatient,Patient.EX);

                applyObserver();
            }





        }





    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        View view=inflater.inflate(R.layout.layout_frg_hasta_listesi, container, false);

        txt_hasta_sayisi=view.findViewById(R.id.txt_frg_hasta_listesi_tum_listelenen_hasta_sayisi);
        recyclerView=view.findViewById(R.id.recyclerview_frg_hasta_listesi_tum);



        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        sayi_guncelle(innerlistPatient.size());





        return view;
    }





    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.menu_frg_hastalar_ve_arama,menu);


        MenuItem menuItem = menu.findItem(R.id.menu_search_hastalar);


        SearchView searchView = (SearchView) menuItem.getActionView();




        switch (adapter.getAdapterTAG())
        {
            case "TÜM HASTALAR":
                searchView.setQueryHint("tüm hastalarda ara...");
                break;

            case "TAM BAÐIMLI":
                searchView.setQueryHint("tam baðýmlý hastalarda ara...");
                break;


            case "YARI BAÐIMLI":
                searchView.setQueryHint("yarý baðýmlý hastalarda ara...");
                break;

            case "BAÐIMSIZ":
                searchView.setQueryHint("baðýmsýz hastalarda ara...");
                break;

            case "AKTÝF":
                searchView.setQueryHint("aktif hastalarda ara...");
                break;

            case "PASÝF":
                searchView.setQueryHint("pasif hastalarda ara...");
                break;

            case "EX":
                searchView.setQueryHint("ex olanlarda ara...");
                break;


        }


        searchView.setElevation(20);

        Animation animation = AnimationUtils.loadAnimation(context,
                R.anim.bounce);
        animation.setDuration(500);
        searchView.startAnimation(animation);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {


                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {



                ArrayList<Patient> filtretedPatients = new ArrayList<>();

                for (Patient patient : innerlistPatient) {
                    if (patient.name.toLowerCase().contains(newText.toLowerCase())
                            || patient.surname.toLowerCase().contains(newText.toLowerCase())
                            || patient.tc_no.toLowerCase().contains(newText.toLowerCase())

                    ) {
                        filtretedPatients.add(patient);
                    }
                }

                setAdapter(filtretedPatients,filtretedPatients.size());

                return true;
            }
        });



        searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                genel_guncelle();
            }
        });

    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        boolean sonuc=false;


        switch (item.getItemId())

        {
            case R.id.menu_hastalar_hasta_ekle:


                addPatient();

                sonuc=true;

                break;

        }

        return sonuc;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if(layoutManager==null)
        {
            layoutManager = new LinearLayoutManager(getActivity());
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        }
        else
        {
            layoutManager = new LinearLayoutManager(getActivity());
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        }





    }



    public void sayi_guncelle(int i)
    {
        txt_hasta_sayisi.setText(""+i);
    }


    public void setAdapter(ArrayList<Patient> patients, int i) {

        adapter.setPatient(patients);
        sayi_guncelle(i);
    }


  public abstract void  genel_guncelle();


    public void addPatient()
    {



        AddPatient_BottomSheetDialog kk= new AddPatient_BottomSheetDialog(context,adapter);


       kk.show();

    }

    public void addPatient2()
    {
        FragmentTransaction transaction=getActivity().getSupportFragmentManager().beginTransaction();

        transaction.add(LinearLayout.generateViewId(),new Fragments.HastaIslemleri.frg_HastaEkleme(context,getActivity().getSupportFragmentManager()));



        transaction.commit();
    }

    public void applyObserver()
    {
        adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeChanged(int positionStart, int itemCount) {
                super.onItemRangeChanged(positionStart, itemCount);
                genel_guncelle();
            }



            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);

                genel_guncelle();
            }

            @Override
            public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
                super.onItemRangeMoved(fromPosition, toPosition, itemCount);

                genel_guncelle();
            }
        });








    }




}
