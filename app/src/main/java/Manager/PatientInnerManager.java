package Manager;

import android.content.Context;
import DataBaseSQLite.DBSQLiteOfAppointment;
import DataBaseSQLite.DBSQLiteOfPersonelInformations;
import DataBaseSQLite.DBSQLiteOfVisit;
import DataBaseSQLite.DataBaseSQLiteOfPatient.DBSQLiteOfAllPatients;
import Patient.*;

import java.text.ParseException;
import java.util.ArrayList;

public class PatientInnerManager {

    private Context context;
    private DBSQLiteOfPersonelInformations litePersonalInformation;
    private DBSQLiteOfVisit liteVisit;
    private DBSQLiteOfAppointment liteAppointment;
    private DBSQLiteOfAllPatients liteOfAllPatients;


    public PatientInnerManager(Context context) {
        this.context = context;

        litePersonalInformation = new DBSQLiteOfPersonelInformations(context);
        liteVisit= new DBSQLiteOfVisit(context);
        liteAppointment= new DBSQLiteOfAppointment(context);
        liteOfAllPatients= new DBSQLiteOfAllPatients(context);

        liteVisit.onCreate(liteVisit.getWritableDatabase());
        litePersonalInformation.onCreate(litePersonalInformation.getWritableDatabase());
        liteAppointment.onCreate(liteAppointment.getWritableDatabase());
        liteOfAllPatients.onCreate(liteOfAllPatients.getWritableDatabase());
    }




    public static  ArrayList<Patient> getAllPatients(Context context) throws ParseException {
        DBSQLiteOfAllPatients liteOfAllPatients = new DBSQLiteOfAllPatients(context);
        liteOfAllPatients.onCreate(liteOfAllPatients.getWritableDatabase());

        return liteOfAllPatients.getAllPatient();
    }

    public static  ArrayList<VisitInformations> getAllAppointments(Context context)
    {
        DBSQLiteOfAppointment dbsqLiteOfAppointment= new DBSQLiteOfAppointment(context);
        dbsqLiteOfAppointment.onCreate(dbsqLiteOfAppointment.getWritableDatabase());
        return dbsqLiteOfAppointment.getAllAppointmentsOfPatient();
    }


    public static  ArrayList<VisitInformations> getAllVisits(Context context)
    {
     DBSQLiteOfVisit dbsqLiteOfVisit= new DBSQLiteOfVisit(context);
     dbsqLiteOfVisit.onCreate(dbsqLiteOfVisit.getWritableDatabase());
        return dbsqLiteOfVisit.getAllVisitsOfPatient();
    }


    public static  ArrayList<Adress> getAllAdress(Context context)
    {
        DBSQLiteOfPersonelInformations lite= new DBSQLiteOfPersonelInformations(context);
        lite.onCreate(lite.getWritableDatabase());
        return lite.getAllPatientAdress();
    }

    public static  ArrayList<Telefon> getAllTelefons(Context context)
    {
        DBSQLiteOfPersonelInformations lite= new DBSQLiteOfPersonelInformations(context);
        lite.onCreate(lite.getWritableDatabase());
        return lite.getAllPatientTelefons();
    }

    public static  ArrayList<PersonelInformations> getAllPersonelInformations(Context context)
    {
        DBSQLiteOfPersonelInformations lite= new DBSQLiteOfPersonelInformations(context);
        lite.onCreate(lite.getWritableDatabase());
        return lite.getAllPatientInformations();
    }






    public boolean randevu_ekle(Patient patient, VisitInformations visitInformations)
    {
        visitInformations.tc_no=patient.tc_no;
        visitInformations.name=patient.name;
        visitInformations.surname=patient.surname;
        visitInformations.sex=patient.sex;

        return liteAppointment.addPatientAppointment(visitInformations);


    }

    public boolean ziyaret_ekle(Patient patient,VisitInformations visitInformations)
    {
        visitInformations.tc_no=patient.tc_no;
        visitInformations.name=patient.name;
        visitInformations.surname=patient.surname;

        return liteVisit.addPatientVisit(visitInformations);


    }


    public boolean kisisel_bilgi_ekle(Patient patient,PersonelInformations informations)
    {
        informations.tc_no=patient.tc_no;
        informations.name=patient.name;
        informations.surname=patient.surname;

        return litePersonalInformation.addPatientInformation(informations);

    }


    public boolean adres_ekle(Patient patient,Adress adress)
    {
        adress.tc_no=patient.tc_no;
        adress.name=patient.name;
        adress.surname=patient.surname;

        return litePersonalInformation.addPatientAdress(adress);

    }

    public boolean telefon_ekle(Patient patient,Telefon telefon)
    {
        telefon.tc_no=patient.tc_no;
        telefon.name=patient.name;
        telefon.surname=patient.surname;

        return litePersonalInformation.addPatientTelefon(telefon);

    }


    public void lokasyon_ekle()
    {
        // bunu sonra ekleyecem
    }


    public ArrayList<VisitInformations> tum_randevulari_getir(Patient patient)
    {

        return  liteAppointment.getAllAppointmentsOfPatient(patient.tc_no);

    }

    public VisitInformations randevuyu_getir(Patient patient,String appointment_day)
    {
        return  liteAppointment.getAppointment(patient.tc_no,appointment_day);
    }

    public ArrayList<VisitInformations> hastanin_tum_randevulerini_getir(Patient patient)
    {
        return  liteAppointment.getAllAppointmentsOfPatient(patient.tc_no);
    }

    public ArrayList<VisitInformations> tum_ziyaretleri_getir(Patient patient)
    {

        return  liteVisit.getAllVisitsOfPatient(patient.tc_no);

    }


    public ArrayList<VisitInformations> tum_tamamlanmis_ziyaretleri_getir(Patient patient)
    {

        return  liteVisit.getAllCompletedVisitsOfPatient(patient.tc_no);

    }

    public ArrayList<VisitInformations> tum_tamamlanmamis_ziyaretleri_getir(Patient patient)
    {

        return  liteVisit.getAllUnCompletedVisitsOfPatient(patient.tc_no);

    }


    public VisitInformations ziyareti_getir(Patient patient,String visit_day)
    {
        return  liteVisit.getVisit(patient.tc_no, visit_day);
    }


    public ArrayList<PersonelInformations> tum_bigileri_getir(Patient patient)
    {

        return  litePersonalInformation.getAllPatientInformations(patient.tc_no);

    }


    public ArrayList<Adress> tum_adresleri_getir(Patient patient)
    {

        return  litePersonalInformation.getAllPatientAdress(patient.tc_no);

    }

    public ArrayList<Telefon> tum_telefonlari_getir(Patient patient)
    {

        return  litePersonalInformation.getAllPatientTelefons(patient.tc_no);

    }


    public Adress adres_getir(Patient patient,String adres_aciklamasi)
    {

        return  litePersonalInformation.getPatientAdress(patient.tc_no,adres_aciklamasi);

    }


    public Telefon telefon_getir(Patient patient,String telefon_aciklamasi)
    {

        return  litePersonalInformation.getPatientTelefon(patient.tc_no,telefon_aciklamasi);

    }



    public boolean tum_randevulari_sil()
    {


        return liteAppointment.deleteAllAppointment();


    }

    public boolean randevu_sil(Patient patient,VisitInformations visitInformations)
    {
        visitInformations.tc_no=patient.tc_no;
        visitInformations.name=patient.name;
        visitInformations.surname=patient.surname;

        return liteAppointment.deletePatientAppointment(visitInformations);


    }

    public boolean hastanin_tum_randevulerini_sil(Patient patient)
    {

        return liteAppointment.deleteAllAppointmentOfPatient(patient.tc_no);


    }


    public boolean ziyaret_sil(Patient patient,VisitInformations visitInformations)
    {
        visitInformations.tc_no=patient.tc_no;
        visitInformations.name=patient.name;
        visitInformations.surname=patient.surname;

        return liteVisit.deletePatientVisit(visitInformations);


    }


    public boolean kisisel_bilgi_sil(Patient patient,PersonelInformations informations)
    {
        informations.tc_no=patient.tc_no;
        informations.name=patient.name;
        informations.surname=patient.surname;

        return   litePersonalInformation.deletePatientInformation(informations);

    }


    public boolean tum_kisisel_bilgileri_sil(Patient patient)
    {
        PersonelInformations informations= new PersonelInformations();

        informations.tc_no=patient.tc_no;
        informations.name=patient.name;
        informations.surname=patient.surname;

        return litePersonalInformation.deleteAllPatientInformation(patient.tc_no);

    }

    public boolean adres_sil(Patient patient,Adress adress)
    {
        adress.tc_no=patient.tc_no;
        adress.name=patient.name;
        adress.surname=patient.surname;

        return litePersonalInformation.deletePatientAdress(adress);

    }

    public boolean telefon_sil(Patient patient,Telefon telefon)
    {
        telefon.tc_no=patient.tc_no;
        telefon.name=patient.name;
        telefon.surname=patient.surname;

        return   litePersonalInformation.deletePatientTelefon(telefon);

    }


    public boolean tum_adresleri_sil(Patient patient)
    {


        return litePersonalInformation.deleteAllPatientAdress(patient.tc_no);

    }

    public boolean tum_telefonlari_sil(Patient patient)
    {


        return litePersonalInformation.deleteAllPatientTelefon(patient.tc_no);

    }

    public boolean randevu_guncelle(Patient patient,VisitInformations visitInformations_eski, VisitInformations visitInformations_yeni)
    {
        visitInformations_eski.tc_no=patient.tc_no;
        visitInformations_eski.name=patient.name;
        visitInformations_eski.surname=patient.surname;

        return liteAppointment.updatePatientAppointment(visitInformations_eski,visitInformations_yeni);


    }

    public boolean ziyaret_guncelle(Patient patient,VisitInformations visitInformations_eski,VisitInformations visitInformations_yeni)
    {
        visitInformations_eski.tc_no=patient.tc_no;
        visitInformations_eski.name=patient.name;
        visitInformations_eski.surname=patient.surname;

        return liteVisit.updatePatientVisit(visitInformations_eski,visitInformations_yeni);


    }

    public boolean kisisel_bilgi_guncelle(Patient patient,PersonelInformations informations_eski,PersonelInformations personelInformations_yeni)
    {
        informations_eski.tc_no=patient.tc_no;
        informations_eski.name=patient.name;
        informations_eski.surname=patient.surname;

        return litePersonalInformation.updatePatientInformationBoth(informations_eski,personelInformations_yeni);

    }

    public boolean adress_guncelle(Patient patient,String adress_eski_aciklamasi,Adress adress_yeni)
    {
        Adress adress_eski= new Adress();
        adress_eski.tc_no=patient.tc_no;
        adress_eski.name=patient.name;
        adress_eski.surname=patient.surname;
        adress_eski.adress_description=adress_eski_aciklamasi;

        return   litePersonalInformation.updatePatientAdress(adress_eski,adress_yeni);

    }

    public boolean adress_guncelle(Patient patient,Adress adress_eski,Adress adress_yeni)
    {

        adress_eski.tc_no=patient.tc_no;
        adress_eski.name=patient.name;
        adress_eski.surname=patient.surname;


        return litePersonalInformation.updatePatientAdress(adress_eski,adress_yeni);

    }

    public boolean telefon_guncelle(Patient patient,String telefon_eski_aciklamasi,Telefon telefon_yeni)
    {
        Telefon telefon_eski= new Telefon();
        telefon_eski.tc_no=patient.tc_no;
        telefon_eski.name=patient.name;
        telefon_eski.surname=patient.surname;
        telefon_eski.tel_no_description=telefon_eski_aciklamasi;


        return litePersonalInformation.updatePatientTelNumber(telefon_eski,telefon_yeni);

    }

    public boolean telefon_guncelle(Patient patient,Telefon informations_eski,Telefon personelInformations_yeni)
    {
        informations_eski.tc_no=patient.tc_no;
        informations_eski.name=patient.name;
        informations_eski.surname=patient.surname;

        return litePersonalInformation.updatePatientTelNumber(informations_eski,personelInformations_yeni);

    }

    public boolean sondurum_degistir(Patient patient,String son_durum)
    {

        return liteOfAllPatients.updatePatientFinalSituations(patient,son_durum);

    }

    public boolean bagimlilik_degistir(Patient patient,String bagimlilik)
    {

        return liteOfAllPatients.updatePatientDependency(patient,bagimlilik);

    }


    public boolean adress_onceden_olusturulmusmu(Patient patient,String adres_aciklamsi)
    {
        return   litePersonalInformation.isContainPatientAdress(patient.tc_no,adres_aciklamsi);
    }

    public boolean telefon_onceden_olusturulmusmu(Patient patient,String tel_no)
    {

        return  litePersonalInformation.isContainPatientTelefon(patient.tc_no,tel_no);

    }

    public boolean onceden_olusturulmusmu()
    {

        return  false;
    }




}
