package Fragments.RandevuListesi;

import Adapters.RecyclerViewAdapters.Hastalar.RecyclerViewAdapterOfRandevuListesi;
import DataBaseSQLite.DBSQLiteOfAppointment;
import Patient.VisitInformations;
import Utils.CustomTime;
import android.content.Context;
import android.os.Bundle;
import android.view.*;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.esh_ajanda.R;

import java.security.acl.Permission;
import java.util.ArrayList;

public class frg_RandevuListesi extends Fragment {


    private TextView txt_randevu_sayisi;
    private Context context;
    private RecyclerView recyclerView;
    private RecyclerViewAdapterOfRandevuListesi adapter;
    private LinearLayoutManager layoutManager;
    private DBSQLiteOfAppointment dbliteAppointment;
    ArrayList<VisitInformations> filtederVisit;
    public frg_RandevuListesi(Context context) {
        this.context = context;
        dbliteAppointment= new DBSQLiteOfAppointment(context);
        dbliteAppointment.onCreate(dbliteAppointment.getWritableDatabase());

       filtederVisit = new ArrayList<>();

        for(VisitInformations visit:dbliteAppointment.getAllAppointments())
        {
            if(CustomTime.getDate().matches(visit.appointmentDate))
            {
                filtederVisit.add(visit);
            }
            else if(CustomTime.getTomorrow().matches(visit.appointmentDate))
            {
                filtederVisit.add(visit);
            }
            else {
                filtederVisit.add(visit);
            }
        }




        adapter= new RecyclerViewAdapterOfRandevuListesi(context,filtederVisit);

        appyObserver();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        setHasOptionsMenu(true);
        View view=inflater.inflate(R.layout.layout_frg_randevu_listesi, container, false);

        txt_randevu_sayisi=view.findViewById(R.id.txt_frg_randevu_listesi_tum_listelenen_randevu_sayisi);

        txt_randevu_sayisi.setText(""+dbliteAppointment.getAllAppointments().size());


        recyclerView=view.findViewById(R.id.recyclerview_frg_randevu_listesi_tum);


        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);

        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.menu_frg_randevu,menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        ArrayList<VisitInformations>  visitInformations= new ArrayList<>();
        boolean sonuc=false;

        switch (item.getItemId())
        {

            case R.id.menu_randevu_randevu_ekle:


                sonuc=true;

                break;

            case R.id.menu_randevu_bugunku_randevuler:
                visitInformations=dbliteAppointment.getAllAppointments(VisitInformations.BUGUN);

                adapter.listeyi_guncelle(visitInformations);
                sayi_guncelle(visitInformations.size());

                sonuc=true;

                break;

            case R.id.menu_randevu_tum_randevuler:
                visitInformations=dbliteAppointment.getAllAppointments();

                adapter.listeyi_guncelle(visitInformations);
                sayi_guncelle(visitInformations.size());

                sonuc=true;

                break;

            case R.id.menu_randevu_sirala_yarinki:

                visitInformations=dbliteAppointment.getAllAppointments(VisitInformations.YARIN);

                adapter.listeyi_guncelle(visitInformations);

                sayi_guncelle(visitInformations.size());

                sonuc=true;

                break;


            case R.id.menu_randevu_sirala_dunku:

                visitInformations=dbliteAppointment.getAllAppointments(VisitInformations.DUN);

                adapter.listeyi_guncelle(visitInformations);

                sayi_guncelle(visitInformations.size());

                sonuc=true;

                break;

            case R.id.menu_randevu_sirala_diger_sonraki:

                visitInformations=dbliteAppointment.getAllAppointments(VisitInformations.DIGER_SONRA);

                adapter.listeyi_guncelle(visitInformations);

                sayi_guncelle(visitInformations.size());

                sonuc=true;

                break;


            case R.id.menu_randevu_sirala_diger_onceki:

                visitInformations=dbliteAppointment.getAllAppointments(VisitInformations.DIGER_ONCE);

                adapter.listeyi_guncelle(visitInformations);

                sayi_guncelle(visitInformations.size());

                sonuc=true;

                break;

            case R.id.menu_randevu_temizle:

                dbliteAppointment.deleteAllAppointment();

                visitInformations=dbliteAppointment.getAllAppointments();

                adapter.listeyi_guncelle(visitInformations);

                sayi_guncelle(visitInformations.size());

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

    public void listeGuncelle()
    {
       ArrayList<VisitInformations> visitInformations=dbliteAppointment.getAllAppointments();

        adapter.listeyi_guncelle(visitInformations);
        sayi_guncelle(visitInformations.size());
    }


    public void sayi_guncelle(int sayi)
    {
        txt_randevu_sayisi.setText(""+sayi);
    }





    @Override
    public void onResume() {
        super.onResume();

        listeGuncelle();

    }


    public void appyObserver()
    {
        adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {

            @Override
            public void onItemRangeChanged(int positionStart, int itemCount) {
                listeGuncelle();
            }

            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                listeGuncelle();
            }

            @Override
            public void onItemRangeRemoved(int positionStart, int itemCount) {
                listeGuncelle();
            }
        });
    }

}
