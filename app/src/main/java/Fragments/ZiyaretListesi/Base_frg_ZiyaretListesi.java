package Fragments.ZiyaretListesi;

import Adapters.RecyclerViewAdapters.Hastalar.RecyclerViewAdapterOfHastaListesi;
import Adapters.RecyclerViewAdapters.Ziyaretler.RecyclerViewAdapterOfZiyaretListesi;
import DataBaseSQLite.DBSQLiteOfVisit;
import DataBaseSQLite.DataBaseSQLiteOfPatient.DBSQLiteOfAllPatients;
import Dialogs.AddPatient_BottomSheetDialog;
import Manager.PatientInnerManager;
import Patient.*;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.*;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.esh_ajanda.R;

import java.util.ArrayList;

public abstract class Base_frg_ZiyaretListesi extends Fragment {

    public TextView txt_ziyaret_sayisi,txt_baslik;
    public Context context;
    public DBSQLiteOfAllPatients dbsqlPatient;
    public DBSQLiteOfVisit dbsqLiteOfVisit;
    public RecyclerView recyclerView;
    public Activity activity;
    public RecyclerViewAdapterOfZiyaretListesi adapter;
    public LinearLayoutManager layoutManager;
    public PatientInnerManager patientInnerManager;
    public ArrayList<VisitInformations> innerVisits;







    public Base_frg_ZiyaretListesi(Context context, String visitResult) {
        this.context = context;

        dbsqlPatient= new DBSQLiteOfAllPatients(context);
        dbsqlPatient.onCreate(dbsqlPatient.getWritableDatabase());
        dbsqLiteOfVisit=new DBSQLiteOfVisit(context);
        dbsqLiteOfVisit.onCreate(dbsqLiteOfVisit.getWritableDatabase());
        patientInnerManager= new PatientInnerManager(context);
        activity= (Activity) context;


        if(visitResult!=null)
        {

            if(visitResult.matches(VisitInformations.TAMAMLANDI))
            {
                innerVisits=dbsqLiteOfVisit.getAllCompletedVisitsOfPatient();

                adapter= new RecyclerViewAdapterOfZiyaretListesi(context,innerVisits,null);

                applyObserver();
            }


          else  if(visitResult.matches(VisitInformations.TAMAMLANMADI))
            {
                innerVisits=dbsqLiteOfVisit.getAllUnCompletedVisitsOfPatient();

                adapter= new RecyclerViewAdapterOfZiyaretListesi(context,innerVisits,null);

                applyObserver();
            }


            else  if(visitResult.matches(VisitInformations.TUM_ZIYARETLER))
            {
                innerVisits=dbsqLiteOfVisit.getAllVisitsOfPatient();

                adapter= new RecyclerViewAdapterOfZiyaretListesi(context,innerVisits,null);

                applyObserver();
            }



        }

        else
        {
            innerVisits=dbsqLiteOfVisit.getAllVisitsOfPatient();

            adapter= new RecyclerViewAdapterOfZiyaretListesi(context,innerVisits,null);

            applyObserver();
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
        View view=inflater.inflate(R.layout.layout_visit_list, container, false);

        txt_baslik=view.findViewById(R.id.txt_layout_visit_list_baslik_yazisi);
        txt_ziyaret_sayisi=view.findViewById(R.id.txt_layout_visit_list_listelenen_ziayret_sayisi);
        recyclerView=view.findViewById(R.id.recyclerview_frg_ziyaretlistesi_tum);

        txt_baslik.setVisibility(View.GONE);


        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        sayi_guncelle(innerVisits.size());





        return view;
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
        txt_ziyaret_sayisi.setText(""+i);
    }


    public void setAdapter(ArrayList<VisitInformations> visits, int i) {

        adapter.setVisit(visits);
        sayi_guncelle(i);
    }


    public abstract void  genel_guncelle();


    public void  applyObserver()
    {
        adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeChanged(int positionStart, int itemCount) {
                genel_guncelle();
            }

            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
               genel_guncelle();
            }

            @Override
            public void onItemRangeRemoved(int positionStart, int itemCount) {
               genel_guncelle();
            }
        });
    }









    }




