package Dialogs;

import Adapters.RecyclerViewAdapters.Ziyaretler.RecyclerViewAdapterOfZiyaretListesi;
import Manager.PatientInnerManager;
import Patient.Patient;
import Patient.VisitInformations;
import Utils.CustomTime;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.esh_ajanda.R;

import java.util.ArrayList;
import java.util.Date;


public class Dialog_Hasta_Dosyasý extends AlertDialog {


    private Activity activity;
    private Context context;
    TextView txt_ad_soyad,txt_son_ziyaret_tarihi,txt_yas,txt_cinsiyet,txt_son_durum;
    ImageView img_hasta_imaji,img_geri_don;

    PatientInnerManager patientInnerManager;
    Patient patient;
    ArrayList<VisitInformations> visitInformations;
    RecyclerView recyclerView;
    RecyclerViewAdapterOfZiyaretListesi recyclerViewAdapterOfZiyaretListesi;




    public Dialog_Hasta_Dosyasý(Context context,Patient patient) {
        super(context);

        this.context=context;
        activity= (Activity) context;
        this.patient=patient;
        patientInnerManager= new PatientInnerManager(context);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout linearLayout;

        View view=getLayoutInflater().inflate(R.layout.layout_fragment_hasta_dosyasi,activity.findViewById(R.id.bottom_sheet_lyt_file_of_patient));

        linearLayout=view.findViewById(R.id.linear_lyt_visit_list_dialog_geri_don);

        linearLayout.setVisibility(View.GONE);

        recyclerView=view.findViewById(R.id.recyclerview_dialog_ziyaretlistesi_tum);

        img_geri_don=view.findViewById(R.id.img_layout_file_of_patient_geri_don);
        img_hasta_imaji=view.findViewById(R.id.img_hasta_dosyasi_hasta_imaji);
        txt_ad_soyad=view.findViewById(R.id.txt_hasta_dosyasi_ad_soyad);
        txt_son_ziyaret_tarihi=view.findViewById(R.id.txt_hasta_dosyasi_son_ziyaret_tarihi);
        txt_yas=view.findViewById(R.id.txt_hasta_dosyasi_yas);
        txt_cinsiyet=view.findViewById(R.id.txt_hasta_dosyasi_cinsiyet);
        txt_son_durum=view.findViewById(R.id.txt_hasta_dosyasi_son_durum);

        if(patient.sex.matches(Patient.ERKEK))
        {
            img_hasta_imaji.setImageResource(R.drawable.user_male);
        }
        else
        {
            img_hasta_imaji.setImageResource(R.drawable.user_female);
        }

        txt_yas.setText(""+ CustomTime.getAge(patient.birthday));
        txt_cinsiyet.setText(patient.sex);
        txt_son_durum.setText(patient.final_situation);

        txt_ad_soyad.setText(patient.name+" "+patient.surname);

        ArrayList<Date> dates= new ArrayList<Date>();



        visitInformations=patientInnerManager.tum_ziyaretleri_getir(patient);

        recyclerViewAdapterOfZiyaretListesi=new RecyclerViewAdapterOfZiyaretListesi(context,visitInformations,"ffff");

        recyclerView.setAdapter(recyclerViewAdapterOfZiyaretListesi);

        for(VisitInformations visitInformations:visitInformations)
        {
            if(visitInformations.visitDate!=null)
            {
                dates.add(CustomTime.getParsedDate(visitInformations.visitDate));
            }

        }

        txt_son_ziyaret_tarihi.setText(CustomTime.selectLastDate(dates));

        LinearLayoutManager layoutManager= new LinearLayoutManager(context);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        img_geri_don.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        setContentView(view);

    }


}
