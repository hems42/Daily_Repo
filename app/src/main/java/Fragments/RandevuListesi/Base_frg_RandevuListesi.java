package Fragments.RandevuListesi;

import Adapters.RecyclerViewAdapters.Hastalar.HastalarOrtakAdapterIslemleri;
import Adapters.RecyclerViewAdapters.Hastalar.RecyclerViewAdapterOfRandevuListesi;
import DataBaseSQLite.DBSQLiteOfAppointment;
import Dialogs.Dialog_HastaBulma;
import Fragments.frg_HastaBulma;
import Observation.ObserverRandevuLer;
import Patient.VisitInformations;
import Utils.CustomTime;
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
import java.util.Calendar;
import java.util.Date;

public class Base_frg_RandevuListesi extends Fragment {


    private TextView txt_randevu_sayisi, txt_randevu_tarihi;
    private Context context;
    private RecyclerView recyclerView;
    private RecyclerViewAdapterOfRandevuListesi adapter;
    private LinearLayoutManager layoutManager;
    private DBSQLiteOfAppointment dbliteAppointment;
    ArrayList<VisitInformations> filteredVisit;



    public Base_frg_RandevuListesi(Context context, String RandevuTipi) {
        this.context = context;
        dbliteAppointment= new DBSQLiteOfAppointment(context);
        dbliteAppointment.onCreate(dbliteAppointment.getWritableDatabase());

        filteredVisit = new ArrayList<>();


        if(RandevuTipi.matches(VisitInformations.RANDEVU_TUM))
        {
            filteredVisit=dbliteAppointment.getAllAppointments();
        }
        else if(RandevuTipi.matches(VisitInformations.RANDEVU_BUGUN))
        {
            filteredVisit=dbliteAppointment.getAllAppointments(CustomTime.getTimeJustDate());
        }







        adapter= new RecyclerViewAdapterOfRandevuListesi(context, filteredVisit);

        appyObserver();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        setHasOptionsMenu(true);
        View view=inflater.inflate(R.layout.layout_frg_randevu_listesi, container, false);

        txt_randevu_sayisi=view.findViewById(R.id.txt_frg_randevu_listesi_tum_listelenen_randevu_sayisi);
        txt_randevu_tarihi=view.findViewById(R.id.txt_frg_randevu_listesi_secilen_randevu_tarihi);

        txt_randevu_sayisi.setText(""+dbliteAppointment.getAllAppointments().size());

        txt_randevu_tarihi.setText("TÜM GÜNLER");


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

        String currentDate;

        boolean sonuc=false;

        switch (item.getItemId())
        {

            case R.id.menu_randevu_randevu_ekle:



                new Dialog_HastaBulma(context,adapter).show();

                sonuc=true;

                break;

            case R.id.menu_randevu_bugunku_randevuler:

                currentDate=VisitInformations.BUGUN;

                visitInformations=dbliteAppointment.getAllAppointments(currentDate);


                adapter.listeyi_guncelle(visitInformations);

                sayi_guncelle(visitInformations.size());
                tarih_guncelle(CustomTime.getDateWithDay(currentDate));

                sonuc=true;

                break;

            case R.id.menu_randevu_tum_randevuler:

                visitInformations=dbliteAppointment.getAllAppointments();

                adapter.listeyi_guncelle(visitInformations);

                sayi_guncelle(visitInformations.size());
                tarih_guncelle("TÜM GÜNLER");

                sonuc=true;

                break;

            case R.id.menu_randevu_sirala_yarinki:

                currentDate=VisitInformations.YARIN;

                visitInformations=dbliteAppointment.getAllAppointments(currentDate);

                adapter.listeyi_guncelle(visitInformations);

                sayi_guncelle(visitInformations.size());
                tarih_guncelle(CustomTime.getDateWithDay(currentDate));

                sonuc=true;

                break;


            case R.id.menu_randevu_sirala_dunku:

                currentDate=VisitInformations.DUN;

                visitInformations=dbliteAppointment.getAllAppointments(currentDate);

                adapter.listeyi_guncelle(visitInformations);

                sayi_guncelle(visitInformations.size());

                tarih_guncelle(CustomTime.getDateWithDay(currentDate));

                sonuc=true;

                break;

            case R.id.menu_randevu_sirala_diger_sonraki:

                currentDate=VisitInformations.DIGER_SONRA;

                visitInformations=dbliteAppointment.getAllAppointments(currentDate);

                adapter.listeyi_guncelle(visitInformations);

                sayi_guncelle(visitInformations.size());

                tarih_guncelle(CustomTime.getDateWithDay(currentDate));

                sonuc=true;

                break;


            case R.id.menu_randevu_sirala_diger_onceki:

                currentDate=VisitInformations.DIGER_ONCE;

                visitInformations=dbliteAppointment.getAllAppointments(currentDate);

                adapter.listeyi_guncelle(visitInformations);

                sayi_guncelle(visitInformations.size());

                tarih_guncelle(CustomTime.getDateWithDay(currentDate));

                sonuc=true;

                break;

            case R.id.menu_randevu_temizle:





                adapter.tum_Randevuleri_Sil();

                visitInformations=dbliteAppointment.getAllAppointments();

                adapter.listeyi_guncelle(visitInformations);

                sayi_guncelle(visitInformations.size());

                tarih_guncelle("TÜM GÜNLER");

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

    // bu güncelleme iþini anlýk tarih seçimini koruyacak þekilde düzelteceðim
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

    public void tarih_guncelle(String date)
    {
        txt_randevu_tarihi.setText(date);
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
