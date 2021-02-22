package Dialogs;

import Adapters.RecyclerViewAdapters.Ziyaretler.RecyclerViewAdapterOfZiyaretListesi;
import Manager.PatientInnerManager;
import Patient.*;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.esh_ajanda.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;

public class Dialog_ShowVisits extends BottomSheetDialog

{
    private Patient patient;
    private Context context;
    private Activity activity;
    private PatientInnerManager  patientInnerManager;



    public Dialog_ShowVisits(@NonNull Context context, Patient  patient) {
        super(context, R.style.CustomBottomSheetDialogTheme);

        this.context=context;
        activity= (Activity) context;
        this.patient=patient;

        patientInnerManager=new PatientInnerManager(context);

    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {

        View view = getLayoutInflater().inflate(R.layout.layout_visit_list_dialog,activity.findViewById(R.id.lyt_visit_list_dialog));
            TextView txt_ziyaret_sayisi, txt_baslik;
            RecyclerView recyclerView;
            RecyclerViewAdapterOfZiyaretListesi adapter;
            ArrayList<VisitInformations> innerVisits;
            LinearLayoutManager layoutManager;
            ImageView img_geri_don;

            innerVisits = patientInnerManager.tum_ziyaretleri_getir(patient);



                layoutManager = new LinearLayoutManager(activity);
                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

                adapter = new RecyclerViewAdapterOfZiyaretListesi(context, innerVisits, null);



                txt_baslik = view.findViewById(R.id.txt_layout_visit_list_baslik_yazisi);
                txt_ziyaret_sayisi = view.findViewById(R.id.txt_layout_visit_list_listelenen_ziayret_sayisi);
                img_geri_don = view.findViewById(R.id.img_layout_visit_list_dialog_geri_don);
                recyclerView = view.findViewById(R.id.recyclerview_dialog_ziyaretlistesi_tum);

                txt_baslik.setText("KAYDEDÝLMÝÞ ZÝYARETLER");


                txt_ziyaret_sayisi.setText("" + innerVisits.size());

                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(adapter);







                img_geri_don.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       dismiss();
                    }
                });

                setContentView(view);




        }


    }

