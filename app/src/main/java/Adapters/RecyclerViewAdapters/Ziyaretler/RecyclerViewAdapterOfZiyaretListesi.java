package Adapters.RecyclerViewAdapters.Ziyaretler;

import Adapters.RecyclerViewAdapters.Hastalar.HastalarOrtakAdapterIslemleri;
import Adapters.RecyclerViewAdapters.Hastalar.RecyclerViewAdapterOfHastaListesi;
import DataBaseSQLite.DBSQLiteOfPersonelInformations;
import DataBaseSQLite.DBSQLiteOfVisit;
import DataBaseSQLite.DataBaseSQLiteOfPatient.DBSQLiteOfAllPatients;
import Dialogs.Dialog_Show_ResultOfVisit;
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

                    if(visitInformations.get(getBindingAdapterPosition()).visitResult.matches(VisitInformations.TAMAMLANDI))
                    {
                        Patient patient=dbsqLiteOfAllPatients.getPatient(visitInformations.get(getBindingAdapterPosition()).tc_no);
                        Dialog_Show_ResultOfVisit dialog_show_resultOfVisit= new Dialog_Show_ResultOfVisit(context,
                                patient,patientInnerManager,visitInformations.get(getBindingAdapterPosition())
                        );


                        dialog_show_resultOfVisit.show();
                    }

                    else
                    {
                        Toast.makeText(context,"Ziyaretle Ýlgili Herhangi Bir Veri Bulunamadý!!",Toast.LENGTH_SHORT).show();
                    }

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



    public void setVisit(ArrayList<VisitInformations> visit) {

        this.visitInformations = visit;
        notifyDataSetChanged();

    }


}