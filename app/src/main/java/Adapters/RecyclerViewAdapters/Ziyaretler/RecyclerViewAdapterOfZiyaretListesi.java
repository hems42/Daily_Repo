package Adapters.RecyclerViewAdapters.Ziyaretler;

import Adapters.RecyclerViewAdapters.Hastalar.HastalarOrtakAdapterIslemleri;
import Adapters.RecyclerViewAdapters.Hastalar.RecyclerViewAdapterOfHastaListesi;
import DataBaseSQLite.DBSQLiteOfPersonelInformations;
import DataBaseSQLite.DBSQLiteOfVisit;
import DataBaseSQLite.DataBaseSQLiteOfPatient.DBSQLiteOfAllPatients;
import Fragments.frg_HastaDosyasi;
import Manager.PatientInnerManager;
import Patient.*;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.*;
import android.widget.*;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.esh_ajanda.R;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class RecyclerViewAdapterOfZiyaretListesi extends RecyclerView.Adapter<RecyclerViewAdapterOfZiyaretListesi.VisitHolder> {



    ArrayList<VisitInformations> visitInformations;
    DBSQLiteOfPersonelInformations litePersonelInformation;
    DBSQLiteOfVisit liteVisit;
    DBSQLiteOfAllPatients dbsqLiteOfAllPatients;
    int selectedItem = 0;
    Activity activity;
    Context context;
    LayoutInflater inflater;

    PatientInnerManager patientInnerManager;



    public RecyclerViewAdapterOfZiyaretListesi(Context context,
                                               ArrayList<VisitInformations> visitInformations,
                                               String adapterTAG)

    {
        this.context = context;
        activity = (Activity) context;

        inflater = LayoutInflater.from(context);
        this.visitInformations = visitInformations;

        patientInnerManager = new PatientInnerManager(context);


        litePersonelInformation = new DBSQLiteOfPersonelInformations(context);
        litePersonelInformation.onCreate(litePersonelInformation.getWritableDatabase());

        liteVisit = new DBSQLiteOfVisit(context);
        liteVisit.onCreate(liteVisit.getWritableDatabase());

        dbsqLiteOfAllPatients = new DBSQLiteOfAllPatients(context);
        dbsqLiteOfAllPatients.onCreate(dbsqLiteOfAllPatients.getWritableDatabase());


        this.adapterTAG = adapterTAG;


    }



    private String adapterTAG = null;


    public String getAdapterTAG() {
        return adapterTAG;
    }


    public class VisitHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView img_sonuc;
        TextView txt_isim_soyisim, txt_randevu_tarihi, txt_ziyaret_tarihi;


        public VisitHolder(View itemView) {
            super(itemView);

            cardView = itemView.findViewById(R.id.layout_visit_list_cardview);
            img_sonuc=itemView.findViewById(R.id.img_layout_visit_list_situation_img);
            txt_isim_soyisim=itemView.findViewById(R.id.txt_layout_visit_list_txtview_name_and_surname);
            txt_randevu_tarihi=itemView.findViewById(R.id.txt_layout_visit_list_txtview_appointment_date);
            txt_ziyaret_tarihi=itemView.findViewById(R.id.txt_layout_visit_list_txtview_visit_date);

            img_sonuc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context,"Sonuç gösterme denemesi baþarýlý olmuþtur",Toast.LENGTH_SHORT).show();
                }
            });


        }

        public void setData(VisitInformations selectedVisit) {

            txt_isim_soyisim.setText(selectedVisit.name + " "+ selectedVisit.surname);

            txt_randevu_tarihi.setText(selectedVisit.appointmentDate);



            if(selectedVisit.visitResult!=null&&selectedVisit.visitResult.matches(VisitInformations.TAMAMLANDI))
            {
                txt_ziyaret_tarihi.setText(selectedVisit.visitDate.substring(0,10));
                cardView.setCardBackgroundColor(Color.rgb(61,90,254));
                img_sonuc.setImageResource(R.drawable.green_tick_1);
            }
            else
            {
                txt_ziyaret_tarihi.setText("---");
                cardView.setCardBackgroundColor(Color.rgb(170,170,170));
                img_sonuc.setImageResource(R.drawable.red_cross);
            }

        }
    }



    @Override
    public RecyclerViewAdapterOfZiyaretListesi.VisitHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.layout_visit_row, null);

        RecyclerViewAdapterOfZiyaretListesi.VisitHolder visitHolder = new RecyclerViewAdapterOfZiyaretListesi.VisitHolder(view);

        return visitHolder;
    }


    @Override
    public void onBindViewHolder(RecyclerViewAdapterOfZiyaretListesi.VisitHolder holder, int position) {

        VisitInformations selected_visit = visitInformations.get(position);
        holder.setData(selected_visit);
    }


    @Override
    public int getItemCount() {

        return visitInformations.size();
    }

/*
    public void listeyiGuncelle() {

        String gelenTAG = getAdapterTAG();

        if (gelenTAG != null) {
            if (gelenTAG.matches(Patient.TUM_HASTALAR)) {
                setPatient(dbsqLiteOfAllPatients.getAllPatient());
            }

            if (gelenTAG.matches(Patient.TAM_BAGIMLI)) {
                setPatient(dbsqLiteOfAllPatients.getAllPatientDependency(gelenTAG));
            }

            if (gelenTAG.matches(Patient.YARI_BAGIMLI)) {
                setPatient(dbsqLiteOfAllPatients.getAllPatientDependency(gelenTAG));
            }

            if (gelenTAG.matches(Patient.BAGIMSIZ)) {
                setPatient(dbsqLiteOfAllPatients.getAllPatientDependency(gelenTAG));
            }

            if (gelenTAG.matches(Patient.AKTIF)) {
                setPatient(dbsqLiteOfAllPatients.getAllPatientFinalSituation(gelenTAG));
            }

            if (gelenTAG.matches(Patient.PASIF)) {

                setPatient(dbsqLiteOfAllPatients.getAllPatientFinalSituation(gelenTAG));

            }

            if (gelenTAG.matches(Patient.EX)) {

                setPatient(dbsqLiteOfAllPatients.getAllPatientFinalSituation(gelenTAG));
            }


        }







*//* public void isPatientAtPasif(final Patient patient, ArrayList<Patient> refreshPatients)
    {






            AlertDialog.Builder  builder_hasta_pasifte= new AlertDialog.Builder(activity);

            View view_hasta_pasifte=inflater.inflate(R.layout.layout_genel_uyarilar,null);

            TextView txt_hasta_pasifte_mesaji=view_hasta_pasifte.findViewById(R.id.txt_lyt_general_alerts_note);
            txt_hasta_pasifte_mesaji.setText("Hasta dosyasý pasif iken randevü oluþturamassýnýz aktife alýnsýn mý?");

            Button btn_hastayi_aktife_alalýmmi_ok=view_hasta_pasifte.findViewById(R.id.btn_lyt_general_alerts_ok);
            Button btn_hastayi_aktife_alalýmmi_cancel=view_hasta_pasifte.findViewById(R.id.btn_lyt_general_alerts_cancel);


            builder_hasta_pasifte.setView(view_hasta_pasifte);
            AlertDialog  dialog_hasta_pasifte=builder_hasta_pasifte.create();

            btn_hastayi_aktife_alalýmmi_ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(patientInnerManager.sondurum_degistir(patient,Patient.AKTIF))
                    {

                     //   setPatient(refreshPatients,refreshPatients.size());


                        dialog_hasta_pasifte.dismiss();

                        createAppointment(patient);


                    }
                    else {

                        Toast.makeText(activity,"Beklenmeyen Bir Hata Oldu!!!",Toast.LENGTH_SHORT).show();
                    }



                }
            });

            btn_hastayi_aktife_alalýmmi_cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog_hasta_pasifte.dismiss();
                }
            });



            dialog_hasta_pasifte.show();




        }*//*
    }


    public void listeyiGuncelle(int position) {

        String gelenTAG = getAdapterTAG();

        if (gelenTAG != null) {
            if (gelenTAG.matches(Patient.TUM_HASTALAR)) {
                setPatient(dbsqLiteOfAllPatients.getAllPatient());
            }

            if (gelenTAG.matches(Patient.TAM_BAGIMLI)) {
                setPatient(dbsqLiteOfAllPatients.getAllPatientDependency(gelenTAG));
            }

            if (gelenTAG.matches(Patient.YARI_BAGIMLI)) {
                setPatient(dbsqLiteOfAllPatients.getAllPatientDependency(gelenTAG));
            }

            if (gelenTAG.matches(Patient.BAGIMSIZ)) {
                setPatient(dbsqLiteOfAllPatients.getAllPatientDependency(gelenTAG));
            }

            if (gelenTAG.matches(Patient.AKTIF)) {
                setPatient(dbsqLiteOfAllPatients.getAllPatientFinalSituation(gelenTAG));
            }

            if (gelenTAG.matches(Patient.PASIF)) {

                setPatient(dbsqLiteOfAllPatients.getAllPatientFinalSituation(gelenTAG));

            }

            if (gelenTAG.matches(Patient.EX)) {

                setPatient(dbsqLiteOfAllPatients.getAllPatientFinalSituation(gelenTAG));
            }


        }


        notifyItemChanged(position);


    }*/



    public void setVisit(ArrayList<VisitInformations> visit) {

        this.visitInformations = visit;
        notifyDataSetChanged();

    }


}