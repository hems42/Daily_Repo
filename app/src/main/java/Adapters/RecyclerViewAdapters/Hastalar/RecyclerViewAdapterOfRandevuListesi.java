package Adapters.RecyclerViewAdapters.Hastalar;

import DataBaseSQLite.DBSQLiteOfAppointment;
import DataBaseSQLite.DBSQLiteOfPersonelInformations;
import DataBaseSQLite.DataBaseSQLiteOfPatient.DBSQLiteOfAllPatients;
import Manager.PatientInnerManager;
import Utils.CustomTime;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import Patient.*;
import android.view.*;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.esh_ajanda.R;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class RecyclerViewAdapterOfRandevuListesi extends RecyclerView.Adapter<RecyclerViewAdapterOfRandevuListesi.AppointmentHolder>
{
    @NonNull




    ArrayList<VisitInformations> appointments;
    DBSQLiteOfPersonelInformations dbLitePersonelInformation;
    DBSQLiteOfAppointment dbLiteSqlAppointment;
    DBSQLiteOfAllPatients dbsqLiteOfAllPatients;
    int selectedItem=0;
    Activity activity;
    Context context;
    LayoutInflater inflater;
    PatientInnerManager patientInnerManager;
    HastalarOrtakAdapterIslemleri commonOp;




    public class AppointmentHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        LinearLayout linearLayout_1,linearLayout_2,linearLayout_3,linearLayout_4,linearLayout_randevu_tarihi;
        ImageView img_cinsiyet,img_hastayý_ara,img_hastaya_git,img_tedavi_plani_ekle,img_hasta_secenekleri, img_ziyaret_listesi,img_hasta_durumu;
        TextView txt_isim_soyisim,txt_tc_no,txt_randevu_tarihi,txt_tc_yazisi;


        public AppointmentHolder(View itemView) {
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





            img_hastayý_ara.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    commonOp.callPatient(getCurrentPatient(getBindingAdapterPosition()));

                }
            });


            img_hasta_secenekleri.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    PopupMenu popupMenu= new PopupMenu(context,  img_hasta_secenekleri, Gravity.TOP);


                    popupMenu.inflate(R.menu.menu_popup_randevu);

                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {


                            switch (item.getItemId())
                            {
                                case R.id.menu_popup_randevu_ekle:

                                    commonOp.createAppointment(getCurrentPatient(getBindingAdapterPosition()),
                                            RecyclerViewAdapterOfRandevuListesi.this,getBindingAdapterPosition());

                                    return  true;


                                case R.id.menu_popup_randevu_iptal:

                                    commonOp.cancelAppointment(getCurrentPatient(getBindingAdapterPosition()),
                                            appointments.get(getBindingAdapterPosition()).appointmentDate,RecyclerViewAdapterOfRandevuListesi.this
                                            );

                                    return  true;

                                case R.id.menu_popup_tum_randevuleri_iptal:

                                    commonOp.cancelAllAppointmentOfPatient(getCurrentPatient(getBindingAdapterPosition()),RecyclerViewAdapterOfRandevuListesi.this
                                    );

                                    return  true;

                                case R.id.menu_popup_randevu_hasta_dosyasina_git:

                                    return  true;


                                default:

                                    return false;

                            }



                        }
                    });

                    popupMenu.show();

                }
            });


            img_tedavi_plani_ekle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    commonOp.addVisit(getCurrentPatient(getBindingAdapterPosition()),appointments.get(getBindingAdapterPosition()));
                }
            });

            img_ziyaret_listesi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                }
            });
            img_hastaya_git.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    commonOp.selectNavigationMetod(getCurrentPatient(getBindingAdapterPosition()));
                }
            });

            img_hasta_durumu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

        }

        public void setData(VisitInformations selectedAppointment) throws ParseException {


            txt_tc_no.setText(selectedAppointment.tc_no);

            String cinsiyet= dbLiteSqlAppointment.getPatientOfSex(selectedAppointment.tc_no);





            img_hasta_durumu.setImageResource(R.drawable.home);


            if(CustomTime.getDate().matches(selectedAppointment.appointmentDate))
            {


                cardView.setCardBackgroundColor(Color.rgb(0,139,100));



            }
            else if(CustomTime.getTomorrow().matches(selectedAppointment.appointmentDate))
            {

                cardView.setCardBackgroundColor(Color.rgb(17,104,129));
            }

            else if(CustomTime.getLastDay().matches(selectedAppointment.appointmentDate))
            {
                cardView.setCardBackgroundColor(Color.rgb(204,0,0));
            }

            else {

                cardView.setCardBackgroundColor(Color.rgb(144,16,82));
            }

            if(cinsiyet!=null)
            {

                if(Pattern.matches("K",cinsiyet))
                {


                    this.img_cinsiyet.setImageResource(R.drawable.user_female);
                }

                else
                {


                    this.img_cinsiyet.setImageResource(R.drawable.user_male);
                }


            }



            txt_randevu_tarihi.setText(selectedAppointment.appointmentDate);

            this.txt_isim_soyisim.setText(selectedAppointment.name +  " " + selectedAppointment.surname);

        }













    }





    public RecyclerViewAdapterOfRandevuListesi(Context context,ArrayList<VisitInformations> appointments) {
        this.context = context;
        activity= (Activity) context;

        this.appointments=appointments;
        inflater=LayoutInflater.from(context);



        patientInnerManager= new PatientInnerManager(context);
        commonOp= new HastalarOrtakAdapterIslemleri(context,patientInnerManager,dbsqLiteOfAllPatients,null,this);

        dbLitePersonelInformation = new DBSQLiteOfPersonelInformations(context);
        dbLitePersonelInformation.onCreate(dbLitePersonelInformation.getWritableDatabase());

        dbLiteSqlAppointment = new DBSQLiteOfAppointment(context);
        dbLiteSqlAppointment.onCreate(dbLiteSqlAppointment.getWritableDatabase());

        dbsqLiteOfAllPatients= new DBSQLiteOfAllPatients(context);
        dbsqLiteOfAllPatients.onCreate(dbsqLiteOfAllPatients.getWritableDatabase());

    }




    @Override
    public AppointmentHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view=inflater.inflate(R.layout.layout_patient_list,null);

        AppointmentHolder appointmentHolder =new AppointmentHolder(view);

        return appointmentHolder;
    }


    @Override
    public void onBindViewHolder(AppointmentHolder holder, int position) {



        VisitInformations selected_appointment= appointments.get(position);
        try {
            holder.setData(selected_appointment);
        } catch (ParseException e) {
            e.printStackTrace();
        }


    }

    @Override
    public int getItemCount() {
        return appointments.size();
    }














    public void  listeyi_guncelle(String date)
    {
        this.appointments= dbLiteSqlAppointment.getAllAppointments(date);
        notifyDataSetChanged();


    }

    public void  listeyi_guncelle(ArrayList<VisitInformations> randevular)
    {
        this.appointments=randevular;
        notifyDataSetChanged();

    }


    private  Patient getCurrentPatient(int position)
    {
        Patient patient=dbsqLiteOfAllPatients.getPatient(appointments.get(position).tc_no);

        return patient;
    }















































}
