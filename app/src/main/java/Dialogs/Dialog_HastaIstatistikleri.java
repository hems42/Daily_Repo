package Dialogs;

import DataBaseSQLite.DataBaseSQLiteOfPatient.DBSQLiteOfAllPatients;
import Manager.PatientInnerManager;
import Patient.Patient;
import Patient.VisitInformations;
import Utils.CustomTime;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.example.esh_ajanda.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Dialog_HastaIstatistikleri extends Dialog {

    private Context context;
    private DBSQLiteOfAllPatients dbsqLiteOfAllPatients;
    private View view;
    private Patient patient;
    private PatientInnerManager patientInnerManager;
    private Activity activity;
    AlertDialog dialog;
    AlertDialog.Builder builder;

    Button btn_ok;
    TextView txt_name_and_surname,txt_tc_no,txt_regsiter_date,txt_age,txt_sex,txt_patient_finalsituation,txt_dependency_situation,txt_visit_sayisi,txt_unrealized_visit,txt_last_visit_date;



    public Dialog_HastaIstatistikleri(Context context, Patient patient) {
        super(context);
        this.context = context;
        this.patient=patient;
        activity= (Activity) context;
        patientInnerManager= new PatientInnerManager(context);
        dbsqLiteOfAllPatients= new DBSQLiteOfAllPatients(context);
        dbsqLiteOfAllPatients.onCreate(dbsqLiteOfAllPatients.getWritableDatabase());
    }



    public void  showIstatistic()
    {

        builder= new AlertDialog.Builder(activity);

        LayoutInflater layoutInflater=LayoutInflater.from(context);

        view=layoutInflater.inflate(R.layout.layout_istatistic_of_patient,null);

        registerElemnts();


        txt_name_and_surname.setText(patient.name+" "+patient.surname);
        txt_tc_no.setText(patient.tc_no);
        txt_age.setText(""+CustomTime.getAge(patient.birthday));

        if(patient.sex.matches(Patient.ERKEK))
        {
            txt_sex.setText("ERKEK");
        }
        else
        {
            txt_sex.setText("KADIN");
        }

        txt_regsiter_date.setText(patient.created_date);
        txt_patient_finalsituation.setText(patient.final_situation);
        txt_dependency_situation.setText(patient.dependency);
        txt_last_visit_date.setText(getLastVisitDate(patient));
        txt_visit_sayisi.setText(""+patientInnerManager.tum_ziyaretleri_getir(patient).size());
        txt_unrealized_visit.setText(""+patientInnerManager.tum_tamamlanmamis_ziyaretleri_getir(patient).size());



        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();


            }
        });


        builder.setView(view);

        dialog=builder.create();




        dialog.show();
    }

    private  void registerElemnts()
    {
        btn_ok=view.findViewById(R.id.btn_lyt_istatistik_ok);

        txt_name_and_surname=view.findViewById(R.id.txt_lyt_patient_istatistic_name_and_surname);
        txt_tc_no=view.findViewById(R.id.txt_lyt_patient_istatistic_tc_no);
        txt_age=view.findViewById(R.id.txt_lyt_patient_istatistic_age);
        txt_sex=view.findViewById(R.id.txt_lyt_patient_istatistic_sex);
        txt_regsiter_date=view.findViewById(R.id.txt_lyt_patient_istatistic_register_date);
        txt_patient_finalsituation=view.findViewById(R.id.txt_lyt_patient_istatistic_final_situation);
        txt_dependency_situation=view.findViewById(R.id.txt_lyt_patient_istatistic_dependency);
        txt_visit_sayisi=view.findViewById(R.id.txt_lyt_patient_istatistic_visit_amount);
        txt_unrealized_visit=view.findViewById(R.id.txt_lyt_patient_istatistic_unrealized_visit_amount);
        txt_last_visit_date=view.findViewById(R.id.txt_lyt_patient_istatistic_last_visit_date);


    }

    private String getLastVisitDate(Patient patient)
    {



        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd.MM.yyyy");

        String son_ziyaret_tarihi="00.00.0000";

        ArrayList<Date> dateArrayList =  new ArrayList<>();



        for(VisitInformations visitInformations:patientInnerManager.tum_tamamlanmis_ziyaretleri_getir(patient))

        {


            try {




                if(visitInformations.visitDate!=null)
                {
                    dateArrayList.add(simpleDateFormat.parse(visitInformations.visitDate));
                }

            } catch (ParseException e) {
                e.printStackTrace();
            }


        }




        son_ziyaret_tarihi= CustomTime.selectLastDate(dateArrayList);

        return son_ziyaret_tarihi;

    }



}
