package Adapters.RecyclerViewAdapters.Hastalar;

import DataBaseSQLite.DBSQLiteOfAppointment;
import DataBaseSQLite.DBSQLiteOfVisit;
import DataBaseSQLite.DataBaseSQLiteOfPatient.DBSQLiteOfAllPatients;
import Dialogs.*;
import Manager.PatientInnerManager;
import Manager.PatientManager;
import Patient.*;
import Utils.CustomTime;
import Utils.DigitalSign;
import Utils.SelectDate;
import Utils.Validatorler;
import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import androidx.annotation.Nullable;

import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.esh_ajanda.R;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class HastalarOrtakAdapterIslemleri {

    private final Context context;
    private final Activity activity;
    private final LayoutInflater inflater;
   final PatientInnerManager  patientInnerManager;
    DBSQLiteOfAllPatients dbsqLiteOfAllPatients;
    DBSQLiteOfAppointment dbsqLiteOfAppointment;
    DBSQLiteOfVisit dbsqLiteOfVisit;
    private PatientManager patientManager;

    private RecyclerViewAdapterOfHastaListesi recHastaAdapter;
    private RecyclerViewAdapterOfRandevuListesi recRandevuAdapter;


    public HastalarOrtakAdapterIslemleri(

            Context context, PatientInnerManager patientInnerManager,
            DBSQLiteOfAllPatients dbsqLiteOfAllPatients, RecyclerViewAdapterOfHastaListesi recHastaAdapter,
            RecyclerViewAdapterOfRandevuListesi recRandevuAdapter


    ) {
        this.context = context;
        activity = (Activity) context;
        inflater = LayoutInflater.from(context);
        this.patientInnerManager = patientInnerManager;
        this.dbsqLiteOfAllPatients = dbsqLiteOfAllPatients;
        this.recHastaAdapter = recHastaAdapter;
        this.recRandevuAdapter = recRandevuAdapter;

        dbsqLiteOfAppointment = new DBSQLiteOfAppointment(context);
        dbsqLiteOfAppointment.onCreate(dbsqLiteOfAppointment.getWritableDatabase());

        dbsqLiteOfVisit = new DBSQLiteOfVisit(context);
        dbsqLiteOfVisit.onCreate(dbsqLiteOfVisit.getWritableDatabase());

        patientManager = new PatientManager(context);

    }


    public void callPatient(final Patient patient) {


        Button btn_call_alert_ok, btn_call_alert_cancel;

        ArrayList<Telefon> telefonlar = new ArrayList<>();

        String tc = patient.tc_no;

        for (Telefon telefon : patientInnerManager.tum_telefonlari_getir(patient)) {
            telefonlar.add(telefon);
        }

        ListView listView = new ListView(activity);


        if (tc != null && telefonlar.size() == 0) {
            AlertDialog.Builder builder = new AlertDialog.Builder(activity);

            View view_numara_yok = inflater.inflate(R.layout.layout_call_alertview, null);

            btn_call_alert_ok = view_numara_yok.findViewById(R.id.btn_lyt_call_alertview_ok);

            btn_call_alert_cancel = view_numara_yok.findViewById(R.id.btn_lyt_call_alertview_cancel);

            builder.setView(view_numara_yok);

            final AlertDialog dialog = builder.create();

            dialog.show();


            btn_call_alert_ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    dialog.dismiss();

                    add_tell_number(patient);

                }
            });


            btn_call_alert_cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    dialog.dismiss();
                }
            });


        } else if (tc != null && telefonlar.size() > 0) {
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                list_tell_numbers(patient);

            } else {


                activity.requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, 545);


            }


        }


    }


    // yardýmcý sýnýf içi metodlar
    public void add_tell_number(final Patient patient) {
        {


            Button btn_add_tel_number_ok, btn_add_tel_number_cancel;

            final EditText edtxt_add_tel_number_tel_description, edtxt_add_tel_number_tel1, edtxt_add_tel_number_tel2;

            AlertDialog.Builder builder = new AlertDialog.Builder(activity);

            View view_numara_ekle = inflater.inflate(R.layout.layout_adding_tel_number, null);

            btn_add_tel_number_ok = view_numara_ekle.findViewById(R.id.btn_lyt_add_tel_number_ok);

            btn_add_tel_number_cancel = view_numara_ekle.findViewById(R.id.btn_lyt_add_tel_number_cancel);

            edtxt_add_tel_number_tel_description = view_numara_ekle.findViewById(R.id.edtxt_lyt_add_tel_number_tel_description);
            edtxt_add_tel_number_tel1 = view_numara_ekle.findViewById(R.id.edtxt_lyt_add_tel_number_tel1);
            edtxt_add_tel_number_tel2 = view_numara_ekle.findViewById(R.id.edtxt_lyt_add_tel_number_tel2);


            builder.setView(view_numara_ekle);

            final AlertDialog dialog_adding_number = builder.create();

            dialog_adding_number.show();


            btn_add_tel_number_ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String tel_description, tel_1, tel_2;

                    tel_description = edtxt_add_tel_number_tel_description.getText().toString();
                    tel_1 = edtxt_add_tel_number_tel1.getText().toString();
                    tel_2 = edtxt_add_tel_number_tel2.getText().toString();

                    if (!tel_description.matches("")) {

                        Telefon telefon = new Telefon();
                        telefon.tel_no_description = tel_description.toLowerCase();

                        if (!tel_1.matches("") || !tel_2.matches("")) {
                            if (!tel_1.matches("")) {


                                if (!patientInnerManager.telefon_onceden_olusturulmusmu(patient, tel_1)) {
                                    if (Validatorler.isValidTelNumber(tel_1)) {
                                        telefon.tel_no1 =convertPhoneNumberType(tel_1);
                                    } else {

                                        edtxt_add_tel_number_tel1.setError("geçerli bir tel no girin!!");

                                    }


                                } else {
                                    edtxt_add_tel_number_tel1.setError("bu numara daha önce oluþturulmuþ!!");
                                }


                            }
                            if (!tel_2.matches("")) {


                                if (!patientInnerManager.telefon_onceden_olusturulmusmu(patient, tel_2)) {

                                    if (Validatorler.isValidTelNumber(tel_2)) {
                                        telefon.tel_no2 = convertPhoneNumberType(tel_2);
                                    } else {

                                        edtxt_add_tel_number_tel2.setError("geçerli bir tel no girin!!");

                                    }


                                } else {
                                    edtxt_add_tel_number_tel2.setError("bu numara daha önce oluþturulmuþ!!");
                                }
                            }


                            if ((telefon.tel_no1 != "" && telefon.tel_no1 != null) || (telefon.tel_no2 != "" && telefon.tel_no2 != null)) {
                                if (!tel_1.matches(tel_2)) {
                                    if (patientInnerManager.telefon_ekle(patient, telefon)) {
                                        dialog_adding_number.dismiss();
                                        Toast.makeText(context, "Telefon Kaydý Baþarýyla Oluþturulmuþtur...", Toast.LENGTH_SHORT).show();

                                        callPatient(patient);

                                    }
                                } else {
                                    edtxt_add_tel_number_tel1.setError("ayný numaralarý kaydedemessiniz!!");
                                    edtxt_add_tel_number_tel2.setError("ayný numaralarý kaydedemessiniz!!");
                                }


                            }

                        } else {
                            edtxt_add_tel_number_tel1.setError("en az bir tel no girin!!");
                        }


                    } else {
                        edtxt_add_tel_number_tel_description.setError("bu alaný doldurmak zorunlu!!!");
                    }

                }
            });


            btn_add_tel_number_cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog_adding_number.dismiss();
                }
            });


        }
    }

    public void list_tell_numbers(final Patient patient) {

        ArrayList<String> nolar = new ArrayList<>();
        String gelen_no = null;

        for (Telefon telefon : patientInnerManager.tum_telefonlari_getir(patient)) {
            if ((telefon.tel_no1 != null) && (!telefon.tel_no1.matches(""))) {
                gelen_no = telefon.tel_no1 + " - " + telefon.tel_no_description;
                nolar.add(gelen_no);
            }

            if ((telefon.tel_no2 != null) && (!telefon.tel_no2.matches(""))) {
                gelen_no = telefon.tel_no2 + " - " + telefon.tel_no_description;
                nolar.add(gelen_no);
            }

        }

        final ArrayAdapter<String> numbers = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, nolar);


        ListView listView_newOne = new ListView(activity);


        listView_newOne.setAdapter(numbers);


        AlertDialog.Builder builder_list_numbers = new AlertDialog.Builder(activity);

        builder_list_numbers.setView(listView_newOne);
        builder_list_numbers.setIcon(R.drawable.phone);
        builder_list_numbers.setTitle("Kayýtlý Olan Numaralar!!");
        builder_list_numbers.setPositiveButton("Tel Numarasý Ekle", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
                add_tell_number(patient);
            }
        });
        builder_list_numbers.setNegativeButton("Vazgeç", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });


        final AlertDialog dialog_list_numbers = builder_list_numbers.create();

        dialog_list_numbers.show();

        listView_newOne.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
                menu.add("sil").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        AdapterView.AdapterContextMenuInfo listAdapterInfo = (AdapterView.AdapterContextMenuInfo) menuInfo;

                        Telefon telefon= new Telefon();


                       String gelen_tel=numbers.getItem(listAdapterInfo.position);

                       int baslangýc=0;

                       for(int i=0;i<gelen_tel.length();i++)

                       {

                           if(Character.isAlphabetic(gelen_tel.charAt(i)))
                           {
                               baslangýc=i;
                               break;
                           }



                       }


                       telefon.tel_no_description=gelen_tel.substring(baslangýc);


                        if(patientInnerManager.telefon_sil(patient,telefon))
                        {
                            dialog_list_numbers.dismiss();

                            callPatient(patient);

                            return true;

                        }
                        else
                        {

                            return false;
                        }





                    }
                });
            }
        });

        listView_newOne.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                dialog_list_numbers.dismiss();


                String tel = numbers.getItem(position).substring(0, 16);

                if(tel.charAt(0)!='0')
                {
                    tel="0 "+numbers.getItem(position).substring(0, 16);
                }

                call_number(tel);
            }
        });


    }

    public void call_number(String tel_number) {
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + tel_number));
        activity.startActivity(intent);
    }


    public void tedaviPlani(Patient patient, RecyclerViewAdapterOfHastaListesi adapter, int position, ArrayList<Patient> refreshablePatients) {
        if (patientInnerManager.tum_randevulari_getir(patient).size() > 0) {
            listAllAppointments(patient, adapter, position);


        } else {
            AlertDialog.Builder builder_randevu_yok = new AlertDialog.Builder(activity);

            View view = inflater.inflate(R.layout.layout_oluturulmus_randevu_yok_uyarisi, null);

            builder_randevu_yok.setView(view);

            AlertDialog dialog_randevu_yok = builder_randevu_yok.create();

            Button btn_randevu_yok_olusturulsun, btn_randevu_yok_olusturulmasin;

            btn_randevu_yok_olusturulsun = view.findViewById(R.id.btn_lyt_appointment_alert_ok);
            btn_randevu_yok_olusturulmasin = view.findViewById(R.id.btn_lyt_appointment_alert_cancel);

            btn_randevu_yok_olusturulmasin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog_randevu_yok.dismiss();
                }
            });

            btn_randevu_yok_olusturulsun.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (patient.final_situation.matches(Patient.PASIF))

                    {
                        dialog_randevu_yok.dismiss();

                        AlertDialog.Builder builder_hasta_pasifte = new AlertDialog.Builder(activity);

                        View view_hasta_pasifte = inflater.inflate(R.layout.layout_genel_uyarilar, null);

                        TextView txt_hasta_pasifte_mesaji = view_hasta_pasifte.findViewById(R.id.txt_lyt_general_alerts_note);
                        txt_hasta_pasifte_mesaji.setText("Hasta dosyasý pasif iken randevü oluþturamassýnýz aktife alýnsýn mý?");

                        Button btn_hastayi_aktife_alalýmmi_ok = view_hasta_pasifte.findViewById(R.id.btn_lyt_general_alerts_ok);
                        Button btn_hastayi_aktife_alalýmmi_cancel = view_hasta_pasifte.findViewById(R.id.btn_lyt_general_alerts_cancel);


                        builder_hasta_pasifte.setView(view_hasta_pasifte);
                        AlertDialog dialog_hasta_pasifte = builder_hasta_pasifte.create();

                        btn_hastayi_aktife_alalýmmi_ok.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                if (patientInnerManager.sondurum_degistir(patient, Patient.AKTIF)) {

                                    adapter.notifyItemChanged(position);


                                    dialog_hasta_pasifte.dismiss();


                                    createAppointment(patient, adapter, position);


                                } else {

                                    Toast.makeText(activity, "Beklenmeyen Bir Hata Oldu!!!", Toast.LENGTH_SHORT).show();
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


                    }

                    else

                        {

                        dialog_randevu_yok.dismiss();

                        createAppointment(patient, adapter, position);

                    }
                }

            });


            dialog_randevu_yok.show();


        }
    }

    public void createAppointment(Patient patient, RecyclerView.Adapter adapter, int position) {
        AlertDialog.Builder builder_randevu_olustur = new AlertDialog.Builder(activity);

        View view_randevu_olusturma = inflater.inflate(R.layout.layout_randevu_olusturma, null);

        builder_randevu_olustur.setView(view_randevu_olusturma);

        AlertDialog dialog_randevu_olustur = builder_randevu_olustur.create();

        EditText edtxt_hasta_adi_soyadi, edtxt_tc_no, edtxt_randevu_gunu;
        Button btn_randevu_kaydet, btn_randevuden_vazgec;

        edtxt_hasta_adi_soyadi = view_randevu_olusturma.findViewById(R.id.edtxt_lyt_add_appointment_patient_name_surname);
        edtxt_tc_no = view_randevu_olusturma.findViewById(R.id.edtxt_lyt_add_appointment_patient_tc_no);
        edtxt_randevu_gunu = view_randevu_olusturma.findViewById(R.id.edtxt_lyt_add_appointment_select_appointment_day);

        edtxt_hasta_adi_soyadi.setText(patient.name + " " + patient.surname);

        edtxt_tc_no.setText(patient.tc_no);

        edtxt_randevu_gunu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new SelectDate(context).tarih_sec_liste_takvimli(edtxt_randevu_gunu);
            }
        });

        edtxt_randevu_gunu.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                new SelectDate(context).tarih_sec_liste_takvimli(edtxt_randevu_gunu);
            }
        });

        btn_randevu_kaydet = view_randevu_olusturma.findViewById(R.id.btn_lyt_add_appointment_ok);
        btn_randevuden_vazgec = view_randevu_olusturma.findViewById(R.id.btn_lyt_add_appointment_cancel);


        btn_randevu_kaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String ad_soyad, tc_no, randevu_tarihi;

                ad_soyad = edtxt_hasta_adi_soyadi.getText().toString();
                tc_no = edtxt_tc_no.getText().toString();
                randevu_tarihi = edtxt_randevu_gunu.getText().toString();

                String gelen_randevu_tarihi;



                if ((!ad_soyad.matches("")) &&
                        (!tc_no.matches("")) &&
                        (!randevu_tarihi.matches("")))

                {


                    gelen_randevu_tarihi=patientInnerManager.randevuyu_getir(patient, randevu_tarihi).appointmentDate;

                    if (gelen_randevu_tarihi == null)

                    {
                        VisitInformations visitInformations = new VisitInformations();
                        visitInformations.appointmentDate = randevu_tarihi;

                        if (patientInnerManager.randevu_ekle(patient, visitInformations))

                        {
                            Toast.makeText(activity, "Randevü Baþarýyla Oluþturuldu!!!", Toast.LENGTH_SHORT).show();
                            dialog_randevu_olustur.dismiss();


                            adapter.notifyItemChanged(position);

                        }


                    }

                    else

                        {
                            if(gelen_randevu_tarihi.matches(randevu_tarihi))
                            {
                                Toast.makeText(activity, "Ayný Tarihe Oluþtutulmuþ Randevü Bulundu!!", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                VisitInformations visitInformations = new VisitInformations();
                                visitInformations.appointmentDate = randevu_tarihi;

                                if (patientInnerManager.randevu_ekle(patient, visitInformations)) {
                                    Toast.makeText(activity, "Randevü Baþarýyla Oluþturuldu!!!", Toast.LENGTH_SHORT).show();
                                    dialog_randevu_olustur.dismiss();

                                    adapter.notifyItemChanged(position);


                                }
                            }


                    }

                }

                else
                    {
                    Toast.makeText(activity, "Lütfen Gerekli Alanlarý Doldurun!!!", Toast.LENGTH_SHORT).show();
                    }

            }
        });


        btn_randevuden_vazgec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog_randevu_olustur.dismiss();

            }
        });


        dialog_randevu_olustur.show();


    }



    public void listAllAppointments(Patient patient, RecyclerViewAdapterOfHastaListesi adapter, int position) {
        View view_randevuler_listesi = inflater.inflate(R.layout.layout_bottomsheet_hastanin_randevulari_listesi, null);
        RecyclerView recyclerView = view_randevuler_listesi.findViewById(R.id.recyclerview_hasta_randevulari_oluturulmus_randevuler_listesi);
        Button btn_yeni_randevu_ekle = view_randevuler_listesi.findViewById(R.id.btn_lyt_hastanin_olusuturlmus_randevuleri_listesi_randevu_ekle);
        Button btn_cik = view_randevuler_listesi.findViewById(R.id.btn_lyt_hastanin_olusuturlmus_randevuleri_listesi_randevu_cik);

        RecyclerViewAdapterOfRandevuListesi adapter_hastanin_randevuleri = new RecyclerViewAdapterOfRandevuListesi(context, patientInnerManager.tum_randevulari_getir(patient));
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        adapter_hastanin_randevuleri.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeChanged(int positionStart, int itemCount) {
                super.onItemRangeChanged(positionStart, itemCount);

                patientInnerManager.tum_randevulari_getir(patient);
                adapter_hastanin_randevuleri.notifyDataSetChanged();

            }

            @Override
            public void onItemRangeChanged(int positionStart, int itemCount, @Nullable Object payload) {
                super.onItemRangeChanged(positionStart, itemCount, payload);

                patientInnerManager.tum_randevulari_getir(patient);

            }

            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);patientInnerManager.tum_randevulari_getir(patient);

                patientInnerManager.tum_randevulari_getir(patient);

            }

            @Override
            public void onItemRangeRemoved(int positionStart, int itemCount) {
                super.onItemRangeRemoved(positionStart, itemCount);

                patientInnerManager.tum_randevulari_getir(patient);
            }

            @Override
            public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
                super.onItemRangeMoved(fromPosition, toPosition, itemCount);

                patientInnerManager.tum_randevulari_getir(patient);
            }
        });

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter_hastanin_randevuleri);

        AlertDialog.Builder builder_hastanin_randevuleri_listesi = new AlertDialog.Builder(activity);

        builder_hastanin_randevuleri_listesi.setView(view_randevuler_listesi);

        AlertDialog dialog_hastanin_randevuleri_listesi = builder_hastanin_randevuleri_listesi.create();


        btn_yeni_randevu_ekle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_hastanin_randevuleri_listesi.dismiss();
                createAppointment(patient, adapter, position);
            }
        });

        btn_cik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_hastanin_randevuleri_listesi.dismiss();
            }
        });

        dialog_hastanin_randevuleri_listesi.show();


    }


    public boolean selectFinalSituation(Patient patient, int positonPatient) {

        final boolean[] sonuc = {false};

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        ListView listView = new ListView(activity);

        ArrayList<String> sex = new ArrayList<>();
        sex.add(Patient.AKTIF);
        sex.add(Patient.PASIF);
        sex.add(Patient.EX);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(activity, android.R.layout.simple_expandable_list_item_1, sex);

        listView.setAdapter(adapter);

        builder.setTitle("son Durumunu Seçin!");
        builder.setIcon(R.drawable.j);
        builder.setView(listView);

        builder.setNegativeButton("VAZGEÇ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });


        AlertDialog dialog = builder.create();


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String sonu_durum = listView.getItemAtPosition(position).toString();


                if (patientInnerManager.hastanin_tum_randevulerini_getir(patient).size() > 0) {
                    if (sonu_durum.matches(Patient.EX)
                            || sonu_durum.matches(Patient.PASIF)) {
                        dialog.dismiss();

                        AlertDialog.Builder builder_p_e = new AlertDialog.Builder(activity);

                        builder_p_e.setIcon(R.drawable.error);
                        builder_p_e.setTitle("DÝKKATT!!");
                        builder_p_e.setMessage("Hasta Pasif ya da Ex te iken randevü oluþturulumaz, tüm randevüler iptal edilecek!! \n Onaylýyor musunuz?");
                        builder_p_e.setNegativeButton("VAZGEÇ", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {


                            }
                        });

                        builder_p_e.setPositiveButton("ONAYLIYORUM", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                if (patientInnerManager.hastanin_tum_randevulerini_sil(patient) && dbsqLiteOfAllPatients.updatePatientFinalSituations(patient, sonu_durum)) {


                                    recHastaAdapter.notifyItemChanged(position);
                                    Toast.makeText(context, "Hastanýn Son Durumu Baþarýyla Deðiþtirilmiþtir..", Toast.LENGTH_SHORT).show();


                                } else {
                                    Toast.makeText(context, "Hastanýn Durumu Deðiþtirilememiþtir!!!", Toast.LENGTH_SHORT).show();
                                }

                            }
                        });

                        AlertDialog dialog_p_e = builder_p_e.create();

                        dialog_p_e.show();

                    } else {
                        if (dbsqLiteOfAllPatients.updatePatientFinalSituations(patient, sonu_durum)) {


                            dialog.dismiss();
                            Toast.makeText(context, "Hastanýn Son Durumu Baþarýyla Deðiþtirilmiþtir..", Toast.LENGTH_SHORT).show();
                            recHastaAdapter.notifyItemChanged(position);

                        } else {
                            dialog.dismiss();
                            Toast.makeText(context, "Hastanýn Durumu Deðiþtirilememiþtir!!!", Toast.LENGTH_SHORT).show();
                        }

                    }


                } else {
                    if (dbsqLiteOfAllPatients.updatePatientFinalSituations(patient, sonu_durum)) {


                        dialog.dismiss();
                        Toast.makeText(context, "Hastanýn Son Durumu Baþarýyla Deðiþtirilmiþtir..", Toast.LENGTH_SHORT).show();
                        recHastaAdapter.notifyItemChanged(position);

                    } else {
                        dialog.dismiss();
                        Toast.makeText(context, "Hastanýn Durumu Deðiþtirilememiþtir!!!", Toast.LENGTH_SHORT).show();
                    }

                }


            }
        });


        dialog.show();


        return true;

    }


    public void selectDependency(Patient patient, int positonPatient) {

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        ListView listView = new ListView(activity);

        ArrayList<String> dependencylist = new ArrayList<>();
        dependencylist.add(Patient.TAM_BAGIMLI);
        dependencylist.add(Patient.YARI_BAGIMLI);
        dependencylist.add(Patient.BAGIMSIZ);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(activity, android.R.layout.simple_expandable_list_item_1, dependencylist);

        listView.setAdapter(adapter);

        builder.setTitle("Baðýmlýlýk Durumunu Seçin!");
        builder.setIcon(R.drawable.i);
        builder.setView(listView);

        builder.setNegativeButton("VAZGEÇ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });


        AlertDialog dialog = builder.create();


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                if (dbsqLiteOfAllPatients.updatePatientDependency(patient, listView.getItemAtPosition(position).toString())) {
                    Toast.makeText(context, "Hastanýn Baðýmlýlýk Durumu Baþarýyla Deðiþtirilmiþtir..", Toast.LENGTH_SHORT).show();


                    dialog.dismiss();

                    recHastaAdapter.notifyItemChanged(position);
                } else {
                    Toast.makeText(context, "Hastanýn Baðýmlýlýk Durumu Deðiþtirilememiþtir..", Toast.LENGTH_SHORT).show();

                }


            }
        });
        ;


        dialog.show();


    }


    public void seeIstatisticsOfPatient(Patient patient, RecyclerViewAdapterOfHastaListesi adapter) {

        adapter.notifyDataSetChanged();

        new Dialog_HastaIstatistikleri(context, patient).showIstatistic();
    }


    public void showAge(Patient patient) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        AlertDialog dialog;

        View view = inflater.inflate(R.layout.layout_genel_uyarilar, null);
        Button btn_ok, btn_cancel;
        TextView txt_note_title, txt_note;

        btn_ok = view.findViewById(R.id.btn_lyt_general_alerts_ok);
        btn_cancel = view.findViewById(R.id.btn_lyt_general_alerts_cancel);


        txt_note_title = view.findViewById(R.id.txt_lyt_general_alerts_note_title);
        txt_note = view.findViewById(R.id.txt_lyt_general_alerts_note);

        builder.setView(view);

        dialog = builder.create();


        btn_cancel.setVisibility(View.GONE);
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


        txt_note_title.setVisibility(View.VISIBLE);
        txt_note_title.setText(patient.name + " " + patient.surname + " isimli hastanýn yaþý:");
        txt_note.setText("" + CustomTime.getAge(patient.birthday));

        dialog.show();


    }


    public void showBirthDay(Patient patient) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        AlertDialog dialog;

        View view = inflater.inflate(R.layout.layout_genel_uyarilar, null);
        Button btn_ok, btn_cancel;
        TextView txt_note_title, txt_note;

        btn_ok = view.findViewById(R.id.btn_lyt_general_alerts_ok);
        btn_cancel = view.findViewById(R.id.btn_lyt_general_alerts_cancel);


        txt_note_title = view.findViewById(R.id.txt_lyt_general_alerts_note_title);
        txt_note = view.findViewById(R.id.txt_lyt_general_alerts_note);

        builder.setView(view);

        dialog = builder.create();


        btn_cancel.setVisibility(View.GONE);
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


        txt_note_title.setVisibility(View.VISIBLE);
        txt_note_title.setText(patient.name + " " + patient.surname + " isimli hastanýn doðum tarihi:");
        txt_note.setText("" + patient.birthday);

        dialog.show();


    }

    public void addAdress(Patient patient) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        View view = inflater.inflate(R.layout.layout_adding_address, null);


        Button btn_add, btn_cancel;

        EditText edtxt_adress_description, edtxt_sehir, edtxt_ilce, edtxt_mahalle, edtxt_sokak_cadde, edtxt_kapi_no, edtxt_apartman_adi, edtxt_ic_kapi_no;

        btn_add = view.findViewById(R.id.btn_lyt_add_adress_ok);
        btn_cancel = view.findViewById(R.id.btn_lyt_add_adress_cancel);

        edtxt_adress_description = view.findViewById(R.id.edtxt_lyt_add_adress_description);
        edtxt_sehir = view.findViewById(R.id.edtxt_lyt_add_adress_city);
        edtxt_ilce = view.findViewById(R.id.edtxt_lyt_add_adress_district);
        edtxt_mahalle = view.findViewById(R.id.edtxt_lyt_add_adress_neighborhood);
        edtxt_sokak_cadde = view.findViewById(R.id.edtxt_lyt_add_adress_street);
        edtxt_apartman_adi = view.findViewById(R.id.edtxt_lyt_add_adress_apartmant_name);
        edtxt_kapi_no = view.findViewById(R.id.edtxt_lyt_add_adress_door_number);
        edtxt_ic_kapi_no = view.findViewById(R.id.edtxt_lyt_add_adress_internal_door_number);


        edtxt_sehir.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (edtxt_sehir.isFocused()) {
                    edtxt_sehir.setText("");
                }
            }
        });

        edtxt_ilce.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (edtxt_ilce.isFocused()) {
                    edtxt_ilce.setText("");
                }
            }
        });


        builder.setView(view);


        AlertDialog dialog = builder.create();


        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String adres_aciklamasi, il, ilce, mahalle, sokak_yada_cadde, apartman_adi, kapi_no, ic_kapi_no;

                adres_aciklamasi = edtxt_adress_description.getText().toString();
                il = edtxt_sehir.getText().toString();
                ilce = edtxt_ilce.getText().toString();
                mahalle = edtxt_mahalle.getText().toString();
                sokak_yada_cadde = edtxt_sokak_cadde.getText().toString();
                apartman_adi = edtxt_apartman_adi.getText().toString();
                kapi_no = edtxt_kapi_no.getText().toString();
                ic_kapi_no = edtxt_ic_kapi_no.getText().toString();


                Adress adress_eklenecek = new Adress();

                if (adres_aciklamasi != null && !adres_aciklamasi.matches("")) {
                    adress_eklenecek.adress_description = adres_aciklamasi.toUpperCase();


                    if (il != null && !il.matches("")) {
                        adress_eklenecek.city = il.toUpperCase();
                    }


                    if (ilce != null && !ilce.matches("")) {
                        adress_eklenecek.district = ilce.toUpperCase();
                    }


                    if (mahalle != null && !mahalle.matches("")) {
                        adress_eklenecek.neighborhood = mahalle.toUpperCase();
                    }


                    if (sokak_yada_cadde != null && !sokak_yada_cadde.matches("")) {
                        adress_eklenecek.street = sokak_yada_cadde.toUpperCase();
                    }

                    if (kapi_no != null && !kapi_no.matches("")) {
                        adress_eklenecek.door_number = kapi_no.toUpperCase();
                    }


                    if (apartman_adi != null && !apartman_adi.matches("")) {
                        adress_eklenecek.apartmant_name = apartman_adi.toUpperCase();
                    }

                    if (ic_kapi_no != null && !ic_kapi_no.matches("")) {

                    }


                    if (patientInnerManager.adres_ekle(patient, adress_eklenecek)) {
                        dialog.dismiss();

                        Toast.makeText(context, "Adres baþarýyla eklenmiþtir!!", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    edtxt_adress_description.setError("bu alaný doldurun!!!");
                }

            }
        });


        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


        dialog.show();


    }


    public void addLocation(Patient patient) {


    }

    public void selectNavigationMetod(Patient patient) {

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);


        View view = inflater.inflate(R.layout.layout_select_navigation_metod, null);

        Button btn_location, btn_adresses, btn_cancel;

        btn_location = view.findViewById(R.id.btn_lyt_select_navigation_metod_map);

        btn_adresses = view.findViewById(R.id.btn_lyt_select_navigation_metod_show_adress);
        btn_cancel = view.findViewById(R.id.btn_lyt_select_navigation_metod_cancel);

        builder.setView(view);


        AlertDialog dialog = builder.create();


        btn_adresses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
                getAdresess(patient);
            }
        });


        btn_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
                getLocations(patient);
            }
        });


        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();


    }

    public void getLocations(Patient patient) {

        Toast.makeText(context, "henüz kullanýmda deðildir!!", Toast.LENGTH_SHORT).show();

    }


    public void getAdresess(Patient patient) {

        ListView listView = new ListView(activity);


        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        AlertDialog dialog;


        ArrayList<Adress> gelenadresListesi = new ArrayList<Adress>();

        if (!gelenadresListesi.isEmpty()) {
            gelenadresListesi.clear();
        }

        gelenadresListesi = patientInnerManager.tum_adresleri_getir(patient);


        if (patientInnerManager.tum_adresleri_getir(patient).size() > 0) {

            builder.setTitle("Hasta adýna kayýtlý adresler");
            builder.setIcon(R.drawable.add_location);
            ArrayList<String> adreslistesi = new ArrayList<>();

            final ArrayAdapter<String> adapter_adresler = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, adreslistesi);

            for (Adress adress : gelenadresListesi) {
                if (adress.adress_description != null) {
                    adreslistesi.add(adress.adress_description);
                }
            }

            listView.setAdapter(adapter_adresler);

            builder.setView(listView);
            builder.setMessage("\ndetaylarýný görmek için seçiniz!! ");


            builder.setPositiveButton("Adres Ekle", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {


                    dialog.dismiss();
                    addAdress(patient);
                }
            });

            builder.setNegativeButton("Vazgeç", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    dialog.dismiss();

                }
            });


            dialog = builder.create();

            listView.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
                @Override
                public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
                    menu.add("sil").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {

                            AdapterView.AdapterContextMenuInfo acmi = (AdapterView.AdapterContextMenuInfo) menuInfo;


                            Adress adress = new Adress();
                            adress.adress_description = adapter_adresler.getItem(acmi.position);

                            if (patientInnerManager.adres_sil(patient, adress)) {
                                dialog.dismiss();
                                getAdresess(patient);
                            }

                            return true;
                        }
                    });
                }
            });

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    dialog.dismiss();
                    showAdressDetails(patientInnerManager.adres_getir(patient, adapter_adresler.getItem(position)));


                }
            });

            dialog.show();


        } else {
            builder.setTitle("Gösterilecek herhangi bir adres bilgisi  bulunamadý!!");
            builder.setIcon(R.drawable.add_location);
            builder.setMessage("Eklemek ister misiniz??");


            builder.setPositiveButton("Adres Ekle", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    addAdress(patient);
                }
            });

            builder.setNegativeButton("Vazgeç", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    dialog.dismiss();

                }
            });


            dialog = builder.create();


            dialog.show();
        }

    }


    public void showAdressDetails(Adress adress) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        View view = inflater.inflate(R.layout.layout_show_adress_details, null);

        Button btn_show_adress_ok;

        TextView txt_city, txt_district, txt_neighobour, txt_street, txt_apartmant_name, txt_door_number, txt_adress_descriptions;


        btn_show_adress_ok = view.findViewById(R.id.btn_lyt_show_adress_details_ok);

        txt_city = view.findViewById(R.id.txt_lyt_show_adress_city_name);
        txt_district = view.findViewById(R.id.txt_lyt_lyt_show_adress_district_name);
        txt_neighobour = view.findViewById(R.id.txt_lyt_lyt_show_adress_neighoobur_name);
        txt_apartmant_name = view.findViewById(R.id.txt_lyt_lyt_show_adress_apartmant_name);
        txt_street = view.findViewById(R.id.txt_lyt_lyt_show_adress_street);
        txt_door_number = view.findViewById(R.id.txt_lyt_lyt_show_adress_door_number);
        txt_adress_descriptions = view.findViewById(R.id.txt_lyt_lyt_show_adress_description);


        txt_city.setText(adress.city);
        txt_district.setText(adress.district);
        txt_neighobour.setText(adress.neighborhood);
        txt_apartmant_name.setText(adress.apartmant_name);
        txt_street.setText(adress.street);
        txt_door_number.setText("" + adress.door_number);
        txt_adress_descriptions.setText(adress.adress_description);


        builder.setView(view);


        AlertDialog dialog = builder.create();


        btn_show_adress_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
            }
        });


        dialog.show();


    }


    public void editPatient(Patient patient) {
        EditPatient_BottomSheetDialog editPatient_bottomSheetDialog = new EditPatient_BottomSheetDialog(context, recHastaAdapter, patient);

        editPatient_bottomSheetDialog.show();
    }


    // bu metod da edit patient n alertdialog hali var
    public void editPatient_2(Patient patient) {
        EditText edtxt_tc_no, edtxt_ad, edtxt_soyad, edtxt_dogum_tarihi, edtxt_cinsiyet, edtxt_bagimlilik_durumu, edtxt_son_durum;
        Button btn_kaydet, btn_vazgec, btn_sayfayi_yenile;
        TextView txt_title;

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

                if (edtxt_son_durum.isFocused()) {
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


        Patient patient_new = new Patient();


        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        builder.setView(view);


        AlertDialog dialog = builder.create();


        btn_kaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                patient_new.name = getString(edtxt_ad);
                patient_new.surname = getString(edtxt_soyad);
                patient_new.tc_no = getString(edtxt_tc_no);
                patient_new.sex = getString(edtxt_cinsiyet);
                patient_new.final_situation = getString(edtxt_son_durum);
                patient_new.birthday = getString(edtxt_dogum_tarihi);
                patient_new.dependency = getString(edtxt_bagimlilik_durumu);

                if (!patient.equals(patient_new)) {
                    if (validatePatient(patient_new)) {
                        if (dbsqLiteOfAllPatients.updatePatient(patient, patient_new)) {
                            dialog.dismiss();

                            recHastaAdapter.notifyItemChanged(0);

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


                dialog.dismiss();


            }
        });


        dialog.show();


    }


    public void deletePatient(Patient patient, int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        builder.setTitle("DÝKKAT!!");
        builder.setMessage("Hastayla Ýlgili Herþeyi Silmek Üzeresiniz!!");
        builder.setIcon(R.drawable.error);

        builder.setPositiveButton("onaylýyorum", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                boolean sonuc = patientManager.deletePatient(patient);
                System.out.println("hasta silinme sonucu :" + sonuc);
                if (sonuc) {
                    dialog.dismiss();
                    recHastaAdapter.notifyItemChanged(position);
                    Toast.makeText(context, "Hasta Baþarýyla Silindi!!!", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(context, "Hasta Silinemedi!!!", Toast.LENGTH_SHORT).show();
                }
            }
        });


        builder.setNegativeButton("vazgeç", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();

        dialog.show();

    }


    public void addVisit(Patient patient, VisitInformations appointment) {
        Button btn_ok, btn_cancel;

        DigitalSign digitalSign;

        EditText editText_not;

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        View view = inflater.inflate(R.layout.layout_add_visit, null);

        btn_ok = view.findViewById(R.id.btn_lyt_add_visit_ok);
        btn_cancel = view.findViewById(R.id.btn_lyt_add_visit_cancel);

        editText_not = view.findViewById(R.id.edtxt_lyt_add_visit);

        digitalSign = view.findViewById(R.id.dgtl_sign_lyt_add_visit);


        builder.setView(view);


        AlertDialog dialog = builder.create();

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String gelen_messaj = editText_not.getText().toString();


                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

                digitalSign.getBitmap().compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);

                appointment.visitType = "PERÝYODÝK ZÝYARET";



                Date visitDate=CustomTime.getParsedDate(appointment.appointmentDate);


                if (!visitDate.after(CustomTime.getNowDate()))

                {
                    if (gelen_messaj != null && !gelen_messaj.matches("")) {
                        appointment.notes = gelen_messaj;

                        if (byteArrayOutputStream.size() > 4900) {
                            appointment.sign = byteArrayOutputStream.toByteArray();
                            appointment.visitResult = VisitInformations.TAMAMLANDI;

                            if (patientInnerManager.ziyaret_ekle(patient, appointment)) {

                                if (patientInnerManager.randevu_sil(patient, appointment)) {
                                    recRandevuAdapter.notifyItemChanged(0);
                                    dialog.dismiss();
                                    Toast.makeText(context, "Ziyaret Baþarýyla Eklenmiþtir!!", Toast.LENGTH_SHORT).show();

                                }


                            }
                        } else {
                            Toast.makeText(context, "Lütfen imza bilgisini girin!!", Toast.LENGTH_SHORT).show();
                        }


                    } else {
                        editText_not.setError("bu alaný doldurun!!");
                    }
                }

                else
                    {
                    Toast.makeText(context, "Randevü gününden önce ziyaret ekleyemessiniz!!", Toast.LENGTH_SHORT).show();
                    }


            }
        });

        dialog.show();

    }


    private String getString(EditText editText) {
        String gelen = editText.getText().toString();

        if (gelen.matches("")) {
            editText.setError("bu alanýn dodurulmasý zorunlu!!");

            return null;

        } else {
            return gelen;
        }
    }


    private boolean validatePatient(Patient patient) {
        boolean sonuc = true;


        if (!isEmpty(patient.tc_no)) {
            if (Validatorler.isValidTc_no(patient.tc_no)) {

            } else {
                Toast.makeText(context, "GEÇERLÝ BÝR TC NO GÝRÝNÝZ!!", Toast.LENGTH_SHORT).show();

                sonuc = false;
            }

        } else {
            sonuc = false;
        }


        if (!isEmpty(patient.name)) {
        } else {
            sonuc = false;
        }


        if (!isEmpty(patient.surname)) {
        } else {
            sonuc = false;
        }


        if (!isEmpty(patient.birthday)) {
        } else {
            sonuc = false;
        }


        if (!isEmpty(patient.sex)) {
        } else {
            sonuc = false;
        }

        if (!isEmpty(patient.dependency)) {
        } else {
            sonuc = false;
        }

        /*if (!isEmpty(patient.final_situation))
        {}else{sonuc=false;}
*/
        return sonuc;

    }

    private boolean isEmpty(String s) {
        if (s != null) {
            if (s.matches("")) {
                return true;
            } else {
                return false;
            }
        } else {
            return true;
        }

    }

    public String convertPhoneNumberType(String phoneNumber)
    {
        String tel_no="";

        tel_no=phoneNumber.charAt(0)+ " ("+phoneNumber.substring(1,4)+") "+phoneNumber.substring(4,7)+" "+phoneNumber.substring(7,11);

       return tel_no;


    }

}




