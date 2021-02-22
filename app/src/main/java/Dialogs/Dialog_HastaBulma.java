package Dialogs;

import Adapters.RecyclerViewAdapters.Hastalar.RecyclerViewAdapterOfHastaListesi;
import DataBaseSQLite.DataBaseSQLiteOfPatient.DBSQLiteOfAllPatients;
import Observation.ObserverRandevuLer;
import Patient.Patient;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.esh_ajanda.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;

public class Dialog_HastaBulma extends BottomSheetDialog {

    private Context context;
    private Activity activity;
    private View view;
    private RecyclerViewAdapterOfHastaListesi recAdapter;
    private RecyclerView.Adapter adapter_2;
    RecyclerView recyclerView;
    Button btn_ok;
    DBSQLiteOfAllPatients dbsqLiteOfAllPatients;
    LinearLayoutManager layoutManager;
    TextView txt_baslik,txt_hasta_sayisi;
    EditText edtxt_hasta_bul;
    ArrayList<Patient> innerPatients;
    Fragment fragment;

    public Dialog_HastaBulma(Context context,RecyclerView.Adapter  adapter) {

        super(context,R.style.CustomBottomSheetDialogTheme);

        this.context=context;
        activity= (Activity) context;

        dbsqLiteOfAllPatients=new DBSQLiteOfAllPatients(context);
        dbsqLiteOfAllPatients.onCreate(dbsqLiteOfAllPatients.getWritableDatabase());

        layoutManager= new LinearLayoutManager(activity);

        innerPatients=dbsqLiteOfAllPatients.getAllPatient();

       this.adapter_2=adapter;


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        view=getLayoutInflater().inflate(R.layout.layout_hasta_bul,activity.findViewById(R.id.lyt_hasta_bulma));

        txt_baslik=view.findViewById(R.id.txt_frg_hasta_listesi_tum_listelenen_hasta_sayisi_baslik);
        txt_hasta_sayisi=view.findViewById(R.id.txt_frg_hasta_listesi_tum_listelenen_hasta_sayisi);
        edtxt_hasta_bul=view.findViewById(R.id.edtxt_lyt_hasta_bulma);
        recyclerView=view.findViewById(R.id.recyclerview_frg_hasta_listesi_tum);
        btn_ok=view.findViewById(R.id.btn_lyt_hasta_bulma_ok);

        txt_hasta_sayisi.setTextSize(16);

        txt_baslik.setTextSize(16);

        txt_hasta_sayisi.setTextColor(Color.WHITE);
        txt_baslik.setTextColor(Color.WHITE);


        sayi_guncelle(innerPatients.size());

        recAdapter = new RecyclerViewAdapterOfHastaListesi(context,innerPatients,"kljlkj");

        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);




        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(recAdapter);

        recAdapter.registerAdapterDataObserver(new ObserverRandevuLer());

        edtxt_hasta_bul.addTextChangedListener(textWatcher);

        edtxt_hasta_bul.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                edtxt_hasta_bul.setText("");

                return true;

            }
        });

        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                adapter_2.notifyItemChanged(0);
                dismiss();
            }
        });



        setContentView(view);


    }

    TextWatcher textWatcher= new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

            String aranan=s.toString();

            ArrayList<Patient> filtretedPatients = new ArrayList<>();

            for (Patient patient : innerPatients)
            {
                if (patient.name.toLowerCase().contains(aranan.toLowerCase())
                        || patient.surname.toLowerCase().contains(aranan.toLowerCase())
                        || patient.tc_no.toLowerCase().contains(aranan.toLowerCase())

                ) {
                    filtretedPatients.add(patient);
                }
            }

            liste_guncelle(filtretedPatients);

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    private void liste_guncelle(ArrayList<Patient> patients)
    {
        recAdapter.setPatient(patients);
        sayi_guncelle(patients.size());
    }

    private   void sayi_guncelle(int sayi)
    {
        txt_hasta_sayisi.setText(""+sayi);
    }

}
