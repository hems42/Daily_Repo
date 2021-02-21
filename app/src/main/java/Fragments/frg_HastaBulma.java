package Fragments;

import Adapters.RecyclerViewAdapters.Hastalar.RecyclerViewAdapterOfHastaListesi;
import DataBaseSQLite.DataBaseSQLiteOfPatient.DBSQLiteOfAllPatients;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.*;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.esh_ajanda.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class frg_HastaBulma extends BottomSheetDialog {

    private Context context;
    private Activity activity;
    private View view;
    private RecyclerViewAdapterOfHastaListesi recHasta;
    RecyclerView recyclerView;
    Button btn_ok;
    DBSQLiteOfAllPatients dbsqLiteOfAllPatients;
    LinearLayoutManager layoutManager;
    TextView txt_baslik,txt_hasta_sayisi;
    EditText edtxt_hasta_bul;

    public frg_HastaBulma(Context context) {

        super(context,R.style.CustomBottomSheetDialogTheme);

        this.context=context;
        activity= (Activity) context;

        dbsqLiteOfAllPatients=new DBSQLiteOfAllPatients(context);
        dbsqLiteOfAllPatients.onCreate(dbsqLiteOfAllPatients.getWritableDatabase());

        layoutManager= new LinearLayoutManager(activity);


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        view=getLayoutInflater().inflate(R.layout.layout_hasta_bul,null);

        txt_baslik=view.findViewById(R.id.txt_frg_hasta_listesi_tum_listelenen_hasta_sayisi_baslik);
        txt_hasta_sayisi=view.findViewById(R.id.txt_frg_hasta_listesi_tum_listelenen_hasta_sayisi);
        edtxt_hasta_bul=view.findViewById(R.id.edtxt_lyt_hasta_bulma);
        recyclerView=view.findViewById(R.id.recyclerview_frg_hasta_listesi_tum);
        btn_ok=view.findViewById(R.id.btn_lyt_hasta_bulma_ok);

        txt_hasta_sayisi.setTextSize(16);
        txt_hasta_sayisi.setText(""+55);
        txt_baslik.setTextSize(16);

        txt_hasta_sayisi.setTextColor(Color.WHITE);
        txt_baslik.setTextColor(Color.WHITE);



        recHasta= new RecyclerViewAdapterOfHastaListesi(context,dbsqLiteOfAllPatients.getAllPatient(),"kljlkj");

        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);



        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(recHasta);






        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        setContentView(view);


    }


/*    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {

        inflater.inflate(R.menu.searhmenu,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }*/
}
