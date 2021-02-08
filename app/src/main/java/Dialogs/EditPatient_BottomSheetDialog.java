package Dialogs;

import Adapters.RecyclerViewAdapters.Hastalar.RecyclerViewAdapterOfHastaListesi;
import DataBaseSQLite.DataBaseSQLiteOfPatient.DBSQLiteOfAllPatients;
import Patient.Patient;
import Utils.SelectDate;
import Utils.Validatorler;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import com.example.esh_ajanda.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.Locale;

public class EditPatient_BottomSheetDialog extends BottomSheetDialog {

    private Context context;
    private Activity  activity;
    private EditText edtxt_tc_no,edtxt_ad,edtxt_soyad,edtxt_dogum_tarihi,edtxt_cinsiyet,edtxt_bagimlilik_durumu,edtxt_son_durum;
    Button btn_kaydet,btn_vazgec,btn_sayfayi_yenile;
    TextView txt_title;
    DBSQLiteOfAllPatients dbsqLiteOfAllPatients;
    private FragmentManager fragmentManager;
    private LayoutInflater inflater;
    private RecyclerViewAdapterOfHastaListesi adapter;

    private Patient patient;

    String ad,soyad,tc_no,dogum_tarihi,cinsiyet,bagimlilik_durmu,son_durumu;





    private Patient getPatient()
    {
        Patient patient= new Patient();

        ad=getString(edtxt_ad);
        soyad=getString(edtxt_soyad);
        tc_no=getString(edtxt_tc_no);
        cinsiyet=getString(edtxt_cinsiyet);
        dogum_tarihi=getString(edtxt_dogum_tarihi);
        bagimlilik_durmu= getString(edtxt_bagimlilik_durumu);
         son_durumu=getString(edtxt_son_durum);


        if(ad!=null)
        {
            patient.name=ad.toUpperCase();
        }

        if (soyad!=null)
        {
            patient.surname=soyad.toUpperCase();
        }

        if(cinsiyet!=null)
        {
            patient.sex=cinsiyet;
        }

        if(tc_no!=null)
        {
            patient.tc_no=tc_no.toUpperCase();
        }

        if(son_durumu!=null)
        {
            patient.final_situation=son_durumu;
        }


        if(bagimlilik_durmu!=null)
        {
            patient.dependency=bagimlilik_durmu;
        }

        if(dogum_tarihi!=null)
        {
            patient.birthday=dogum_tarihi;
        }





        return patient;


    }

    private  String getString(EditText  editText)
    {
        String gelen=editText.getText().toString();

        if(gelen.matches(""))
        {
            editText.setError("bu alanýn dodurulmasý zorunlu!!");

            return  null;

        }

        else
        {
            return gelen;
        }
    }


    private  boolean validatePatient(Patient patient)
    {
        boolean sonuc= true;


        if (!isEmpty(patient.tc_no) )
        {
            if(Validatorler.isValidTc_no(patient.tc_no))
            {

            }
            else
            {
                Toast.makeText(context,"GEÇERLÝ BÝR TC NO GÝRÝNÝZ!!",Toast.LENGTH_SHORT).show();

                sonuc=false;
            }

        }

        else
        {
            sonuc=false;
        }



        if (!isEmpty(patient.name))
        {}else{sonuc=false;}



        if (!isEmpty(patient.surname))
        {}else{sonuc=false;}


        if (!isEmpty(patient.birthday))
        {}else{sonuc=false;}


        if (!isEmpty(patient.sex))
        {}else{sonuc=false;}

        if (!isEmpty(patient.dependency))
        {}else{sonuc=false;}

        /*if (!isEmpty(patient.final_situation))
        {}else{sonuc=false;}
*/
        return  sonuc;

    }

    private  boolean isEmpty(String s)
    {
        if(s!=null)
        {
            if (s.matches(""))
            {
                return true;
            }

            else
            {
                return false;
            }
        }

        else {return true;}

    }


    public EditPatient_BottomSheetDialog(@NonNull Context context, RecyclerViewAdapterOfHastaListesi adapter,Patient patient_old) {
        super(context, R.style.CustomBottomSheetDialogTheme);

        this.context = context;
        activity= (Activity) context;

        this.patient=patient_old;

        this.fragmentManager=fragmentManager;
        inflater=LayoutInflater.from(context);

        dbsqLiteOfAllPatients= new DBSQLiteOfAllPatients(context);
        dbsqLiteOfAllPatients.onCreate(dbsqLiteOfAllPatients.getWritableDatabase());


        this.adapter=adapter;

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View view = inflater.inflate(R.layout.layout_adding_patient, activity.findViewById(R.id.bottom_sheet_lyt_add_patient));

        edtxt_tc_no = view.findViewById(R.id.edtxt_lyt_add_patient_tc_no);
        edtxt_ad = view.findViewById(R.id.edtxt_lyt_add_patient_name);
        edtxt_soyad = view.findViewById(R.id.edtxt_lyt_add_patient_surname);
        edtxt_dogum_tarihi = view.findViewById(R.id.edtxt_lyt_add_patient_birthday);
        edtxt_cinsiyet = view.findViewById(R.id.edtxt_lyt_add_patient_sex);
        edtxt_son_durum = view.findViewById(R.id.edtxt_lyt_add_patient_finasitution);
        edtxt_bagimlilik_durumu = view.findViewById(R.id.edtxt_lyt_add_patient_dependency);
        // edtxt_son_durumu=view.findViewById(R.id.edtxt_lyt_add_patient_finasitution);
        txt_title = view.findViewById(R.id.txt_lyt_add_patient_title);


        txt_title.setText("HASTA GÜNCELLEME");
        edtxt_son_durum.setVisibility(View.VISIBLE);

        btn_kaydet = view.findViewById(R.id.btn_lyt_add_patient_ok);
        btn_kaydet.setText("GÜNCELLE");
        btn_vazgec = view.findViewById(R.id.btn_lyt_add_patient_cancel);
        btn_sayfayi_yenile = view.findViewById(R.id.btn_lyt_add_refresh_form);


        edtxt_dogum_tarihi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new SelectDate(context).tarih_sec(edtxt_dogum_tarihi);
            }
        });

        edtxt_dogum_tarihi.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if (edtxt_dogum_tarihi.hasFocus()) {
                    new SelectDate(context).tarih_sec(edtxt_dogum_tarihi);
                }

            }
        });


        edtxt_bagimlilik_durumu.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if (edtxt_bagimlilik_durumu.hasFocus()) {
                    new SelectDependency(context).selectDependency(edtxt_bagimlilik_durumu);
                }

            }
        });

        edtxt_bagimlilik_durumu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new SelectDependency(context).selectDependency(edtxt_bagimlilik_durumu);
            }
        });


        edtxt_cinsiyet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new SelectSex(context).selectSex(edtxt_cinsiyet);
            }
        });

        edtxt_cinsiyet.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (edtxt_cinsiyet.hasFocus()) {
                    new SelectSex(context).selectSex(edtxt_cinsiyet);
                }

            }
        });



        edtxt_son_durum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new SelectFinalSituation(context).selectFinalSituation(edtxt_son_durum);
            }
        });

        edtxt_son_durum.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if(edtxt_son_durum.isFocused())
                {
                    new SelectFinalSituation(context).selectFinalSituation(edtxt_son_durum);
                }

            }
        });



        btn_sayfayi_yenile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                edtxt_ad.setError(null);
                edtxt_ad.setText("");
                edtxt_soyad.setError(null);
                edtxt_soyad.setText("");
                edtxt_tc_no.setError(null);
                edtxt_tc_no.setText("");
                edtxt_cinsiyet.setError(null);
                edtxt_cinsiyet.setText("");
                edtxt_dogum_tarihi.setError(null);
                edtxt_dogum_tarihi.setText("");
                edtxt_bagimlilik_durumu.setError(null);
                edtxt_bagimlilik_durumu.setText("");
                edtxt_son_durum.setError(null);
                edtxt_son_durum.setText("");

            }
        });


        edtxt_tc_no.setText(patient.tc_no);
        edtxt_ad.setText(patient.name);
        edtxt_soyad.setText(patient.surname);
        edtxt_dogum_tarihi.setText(patient.birthday);
        edtxt_cinsiyet.setText(patient.sex);
        edtxt_bagimlilik_durumu.setText(patient.dependency);
        edtxt_son_durum.setText(patient.final_situation);











        btn_kaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Patient patient_new =getPatient();
             /*   patient_new.name = getString(edtxt_ad);
                patient_new.surname = getString(edtxt_soyad);
                patient_new.tc_no = getString(edtxt_tc_no);
                patient_new.sex = getString(edtxt_cinsiyet);
                patient_new.final_situation = getString(edtxt_son_durum);
                patient_new.birthday = getString(edtxt_dogum_tarihi);
                patient_new.dependency = getString(edtxt_bagimlilik_durumu);
*/
                if (!patient.equals(patient_new)) {
                    if (validatePatient(patient_new)) {
                        if (dbsqLiteOfAllPatients.updatePatient(patient, patient_new)) {

                            dismiss();

                            adapter.notifyItemChanged(0);

                            Toast.makeText(context, "HASTA GÜNCELLEMESÝ BAÞARILI!!", Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(context, "HASTA GÜNCELLEMESÝ BAÞARISIZ!!", Toast.LENGTH_SHORT).show();
                        }
                    }
                } else {
                    Toast.makeText(context, "Güncellemek istediðiniz alaný deðiþtirin!!", Toast.LENGTH_SHORT).show();
                }


            }
        });

        btn_vazgec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


               dismiss();


            }
        });



        setContentView(view);






    }
}
