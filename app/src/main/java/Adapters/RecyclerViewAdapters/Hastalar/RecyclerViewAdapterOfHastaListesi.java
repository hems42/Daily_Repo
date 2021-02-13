package Adapters.RecyclerViewAdapters.Hastalar;


import DataBaseSQLite.DataBaseSQLiteOfPatient.DBSQLiteOfAllPatients;
import Fragments.frg_HastaDosyasi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.*;
import android.widget.*;
import androidx.cardview.widget.CardView;
import DataBaseSQLite.DBSQLiteOfPersonelInformations;
import DataBaseSQLite.DBSQLiteOfVisit;
import Manager.PatientInnerManager;
import Patient.*;
import androidx.recyclerview.widget.RecyclerView;
import com.example.esh_ajanda.R;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class RecyclerViewAdapterOfHastaListesi extends RecyclerView.Adapter<RecyclerViewAdapterOfHastaListesi.PatientHolder> {

    public void setPatient(ArrayList<Patient> patient) {

        this.patient = patient;
        notifyDataSetChanged();

    }

    ArrayList<Patient> patient;
    DBSQLiteOfPersonelInformations litePersonelInformation;
    DBSQLiteOfVisit  liteVisit;
    DBSQLiteOfAllPatients dbsqLiteOfAllPatients;
    int selectedItem=0;
    Activity activity;
    Context context;
    LayoutInflater inflater;

    PatientInnerManager patientInnerManager;

    HastalarOrtakAdapterIslemleri commonOp;

    private String adapterTAG=null;





    public String getAdapterTAG() {
        return adapterTAG;
    }



    public class PatientHolder extends  RecyclerView.ViewHolder {
        CardView  cardView;
        LinearLayout linearLayout_1,linearLayout_2,linearLayout_3,linearLayout_4,linearLayout_randevu_tarihi;
        ImageView img_cinsiyet,img_hastayý_ara,img_hastaya_git,img_tedavi_plani_ekle,img_hasta_secenekleri, img_ziyaret_listesi,img_hasta_durumu;
        TextView txt_isim_soyisim,txt_tc_no,txt_randevu_tarihi,txt_tc_yazisi;


        public PatientHolder(View itemView) {
            super(itemView);

            cardView=itemView.findViewById(R.id.layout_patient_list_cardview);
            linearLayout_1=itemView.findViewById(R.id.layout_patient_list_firs_linear_layout);
            linearLayout_2=itemView.findViewById(R.id.layout_patient_list_second_linearlayout);
            linearLayout_3=itemView.findViewById(R.id.layout_patient_list_third_linearlayout);
            linearLayout_4=itemView.findViewById(R.id.layout_patient_list_fourth_linearlayout);
            linearLayout_randevu_tarihi=itemView.findViewById(R.id.layout_patient_list_appointment_day_linearlayout);

            img_cinsiyet=itemView.findViewById(R.id.layout_patient_list_sex_img);
            img_hastayý_ara=itemView.findViewById(R.id.layout_patient_list_imgbtn_dial);
            img_hastaya_git=itemView.findViewById(R.id.layout_patient_list_imgbtn_location);
            img_tedavi_plani_ekle=itemView.findViewById(R.id.layout_patient_list_imgbtn_appointments);
            img_hasta_secenekleri=itemView.findViewById(R.id.layout_patient_list_imgbtn_options);
            img_ziyaret_listesi =itemView.findViewById(R.id.layout_patient_list_imgbtn_visit_list);
            img_hasta_durumu=itemView.findViewById(R.id.layout_patient_list_img_patient_situation);

            txt_isim_soyisim=itemView.findViewById(R.id.layout_patient_list_txtview_name_an_surname);
            txt_tc_no=itemView.findViewById(R.id.layout_patient_list_txtview_tc_no);
            txt_randevu_tarihi=itemView.findViewById(R.id.layout_patient_list_txtview_appointment_day);
            txt_tc_yazisi=itemView.findViewById(R.id.layout_patient_list_txtview_tc__yazisi);



            txt_tc_yazisi.setOnClickListener(null);
            txt_isim_soyisim.setOnClickListener(null);
            txt_randevu_tarihi.setOnClickListener(null);
            txt_tc_no.setOnClickListener(null);


            img_tedavi_plani_ekle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    commonOp.tedaviPlani(patient.get(getBindingAdapterPosition()),
                            RecyclerViewAdapterOfHastaListesi.this,
                            getBindingAdapterPosition(),patient);





                }
            });


            img_hastayý_ara.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {



                    commonOp.callPatient(patient.get(getBindingAdapterPosition()));





                }
            });


            img_hastaya_git.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    commonOp.selectNavigationMetod(patient.get(getBindingAdapterPosition()));


                }
            });


            img_hasta_secenekleri.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    PopupMenu popupMenu= new PopupMenu(context,  img_hasta_secenekleri, Gravity.CENTER_HORIZONTAL);


                    popupMenu.inflate(R.menu.menu_popup_hastalar);

                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {

                            boolean sonuc=false;


                            switch (item.getItemId())

                            {
                                case R.id.menu_popup_hastalar_son_durumu_degistir:

                                    commonOp.selectFinalSituation(patient.get(getBindingAdapterPosition()),getAbsoluteAdapterPosition());

                                    sonuc=true;
                                    break;


                                case R.id.menu_popup_hastalar_bagimlilik_durumunu_degistir:

                                    commonOp.selectDependency(patient.get(getBindingAdapterPosition()),getBindingAdapterPosition());

                                    sonuc=true;
                                    break;

                                case R.id.menu_popup_hastalar_tel_no_ekle:

                                    commonOp.add_tell_number(patient.get(getBindingAdapterPosition()));

                                    sonuc=true;
                                    break;


                                case R.id.menu_popup_hastalar_istatistikler:



                                    commonOp.seeIstatisticsOfPatient(patient.get(getAbsoluteAdapterPosition()),RecyclerViewAdapterOfHastaListesi.this);

                                    sonuc=true;
                                    break;


                                case R.id.menu_popup_hastalar_yasini_gor:



                                    commonOp.showAge(patient.get(getAbsoluteAdapterPosition()));

                                    sonuc=true;
                                    break;



                                case R.id.menu_popup_hastalar_dogum_tarihi_gor:



                                    commonOp.showBirthDay(patient.get(getAbsoluteAdapterPosition()));

                                    sonuc=true;
                                    break;



                                case R.id.menu_popup_hastalar_adress_ekle:



                                    commonOp.addAdress(patient.get(getAbsoluteAdapterPosition()));

                                    sonuc=true;
                                    break;



                                case R.id.menu_popup_hastalar_duzenle:



                                    commonOp.editPatient(patient.get(getAbsoluteAdapterPosition()));

                                    sonuc=true;
                                    break;




                                case R.id.menu_popup_hastalar_hastanin_tum_randevulerini_iptal_et:



                                    commonOp.cancelAllAppointmentOfPatient(patient.get(getAbsoluteAdapterPosition()),RecyclerViewAdapterOfHastaListesi.this);

                                    sonuc=true;
                                    break;




                                case R.id.menu_popup_hastalar_hastayi_sil:



                                    commonOp.deletePatient(patient.get(getAbsoluteAdapterPosition()),getBindingAdapterPosition());

                                    sonuc=true;
                                    break;


                            }

                            return sonuc;
                        }
                    });

                    popupMenu.show();

                }
            });

            img_ziyaret_listesi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });


            img_cinsiyet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Fragments.frg_HastaDosyasi frg_hastaDosyasi= new frg_HastaDosyasi();


                }
            });


            img_hasta_durumu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

        }

        public void setData(Patient selectedPatient) {


            selectedItem= getAbsoluteAdapterPosition();

            linearLayout_randevu_tarihi.setVisibility(View.GONE);

            txt_tc_no.setText(selectedPatient.tc_no);

            String cinsiyet=selectedPatient.sex;





            if(selectedPatient.final_situation!=null&&!selectedPatient.final_situation.matches(Patient.EX))
            {
                img_tedavi_plani_ekle.clearColorFilter();
                img_tedavi_plani_ekle.setEnabled(true);



                if(patientInnerManager.tum_randevulari_getir(selectedPatient).size()>0)
                {
                    img_tedavi_plani_ekle.setColorFilter(Color.GREEN);
                }
            }

            else if(selectedPatient.final_situation!=null&&selectedPatient.final_situation.matches(Patient.EX))
            {


                img_tedavi_plani_ekle.setEnabled(false);
                img_tedavi_plani_ekle.setColorFilter(Color.RED);



            }




            if(cinsiyet!=null&&selectedPatient.final_situation!=null)
            {

                if(Pattern.matches("K",cinsiyet))
                {


                    this.img_cinsiyet.setImageResource(R.drawable.user_female);
                }

                else
                {


                    this.img_cinsiyet.setImageDrawable(context.getResources().getDrawable(R.drawable.user_male));
                }



                if(Pattern.matches(Patient.AKTIF,selectedPatient.final_situation))
                {


                    this.img_hasta_durumu.setImageResource(R.drawable.patient_unlocked);
                }

                else if(Pattern.matches(Patient.PASIF,selectedPatient.final_situation))
                {

                    this.img_hasta_durumu.setImageResource(R.drawable.patient_locked);
                }

                else if(Pattern.matches(Patient.EX,selectedPatient.final_situation))
                {

                    this.img_hasta_durumu.setImageResource(R.drawable.ex_patient);
                }
            }



            this.txt_isim_soyisim.setText(selectedPatient.name +  " " + selectedPatient.surname);

        }
    }


    public RecyclerViewAdapterOfHastaListesi(Context context, ArrayList<Patient> patient, String adapterTAG) {
        this.context = context;
        activity= (Activity) context;

        inflater=LayoutInflater.from(context);
        this.patient=patient;

        patientInnerManager= new PatientInnerManager(context);


        litePersonelInformation= new DBSQLiteOfPersonelInformations(context);
        litePersonelInformation.onCreate(litePersonelInformation.getWritableDatabase());

        liteVisit= new DBSQLiteOfVisit(context);
        liteVisit.onCreate(liteVisit.getWritableDatabase());

        dbsqLiteOfAllPatients= new DBSQLiteOfAllPatients(context);
        dbsqLiteOfAllPatients.onCreate(dbsqLiteOfAllPatients.getWritableDatabase());


        commonOp= new HastalarOrtakAdapterIslemleri(context,patientInnerManager,dbsqLiteOfAllPatients,this,null);
        this.adapterTAG=adapterTAG;



    }

    @Override
    public PatientHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view=inflater.inflate(R.layout.layout_patient_list,null);

        PatientHolder patientHolder=new PatientHolder(view);

        return patientHolder;
    }





    @Override
    public void onBindViewHolder(RecyclerViewAdapterOfHastaListesi.PatientHolder holder, int position) {

        Patient selected_patient=patient.get(position);
        holder.setData(selected_patient);
    }




    @Override
    public int getItemCount() {

        return patient.size();
    }






    public  void  listeyiGuncelle()
    {

        String gelenTAG=getAdapterTAG();

        if(gelenTAG!=null)
        {
            if(gelenTAG.matches(Patient.TUM_HASTALAR))
            {
               setPatient(dbsqLiteOfAllPatients.getAllPatient());
            }

            if(gelenTAG.matches(Patient.TAM_BAGIMLI))
            {
               setPatient(dbsqLiteOfAllPatients.getAllPatientDependency(gelenTAG));
            }

            if(gelenTAG.matches(Patient.YARI_BAGIMLI))
            {
                setPatient(dbsqLiteOfAllPatients.getAllPatientDependency(gelenTAG));
            }

            if(gelenTAG.matches(Patient.BAGIMSIZ))
            {
                setPatient(dbsqLiteOfAllPatients.getAllPatientDependency(gelenTAG));
            }

            if(gelenTAG.matches(Patient.AKTIF))
            {
                setPatient(dbsqLiteOfAllPatients.getAllPatientFinalSituation(gelenTAG));
            }

            if(gelenTAG.matches(Patient.PASIF))
            {

                setPatient(dbsqLiteOfAllPatients.getAllPatientFinalSituation(gelenTAG));

            }

            if(gelenTAG.matches(Patient.EX))
            {

               setPatient(dbsqLiteOfAllPatients.getAllPatientFinalSituation(gelenTAG));
            }



        }







/* public void isPatientAtPasif(final Patient patient, ArrayList<Patient> refreshPatients)
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




        }*/
    }



    public  void  listeyiGuncelle(int position) {

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


    }


    }




































































