package Utils;

import Activities.HastalarActivity;
import DataBaseSQLite.DataBaseSQLiteOfPatient.DBSQLiteOfAllPatients;
import Manager.PatientInnerManager;
import Patient.*;
import android.content.Context;

import java.util.Random;

public class PatientUtillty {

    public static void generatePatient(Patient patient) {
        Random random = new Random();

        int a = random.nextInt();
        patient.tc_no = Integer.toString(a);
        String tc=patient.tc_no;


        boolean sex = random.nextBoolean();
        boolean situation = random.nextBoolean();

        int dependency=random.nextInt();

        patient.birthday="02/11/1989";

        if (sex) {
            patient.sex = "E";
        } else {
            patient.sex = "K";
        }

        if (Integer.parseInt(tc.substring(tc.length()))<2) {
            patient.final_situation = Patient.PASIF;
        } else if (Integer.parseInt(tc.substring(tc.length()))>5)
        {
            patient.final_situation = Patient.EX;
        }

        else
        {
            patient.final_situation = Patient.AKTIF;
        }


        if (dependency<2)
        {
            patient.dependency=Patient.TAM_BAGIMLI;
        }


        else if (dependency>5)
        {
            patient.dependency=Patient.YARI_BAGIMLI;
        }
        else {
            patient.dependency=Patient.BAGIMSIZ;
        }

        if(patient.sex==Patient.KADIN)
        {
            patient.name = "ELÝF";
        }
        else
        {
            patient.name = "AHMET";
        }

    }


    public static Patient getGeneratedPatient() {

        Patient patient= new Patient();

        Random random = new Random();

        int a = random.nextInt();
        patient.tc_no = Integer.toString(a);
        String tc=patient.tc_no;


        boolean sex = random.nextBoolean();
        boolean situation = random.nextBoolean();

        int dependency=random.nextInt();

        patient.birthday="02/11/1989";

        if (sex) {
            patient.sex = "E";
        } else {
            patient.sex = "K";
        }

        if (a<3) {
            patient.final_situation = Patient.PASIF;
        } else if (a>6)
        {
            patient.final_situation = Patient.AKTIF;
        }

        else
        {
            patient.final_situation = Patient.EX;
        }


        if (dependency<3)
        {
            patient.dependency=Patient.TAM_BAGIMLI;
        }


        else if (dependency>6)
        {
            patient.dependency=Patient.YARI_BAGIMLI;
        }
        else {
            patient.dependency=Patient.BAGIMSIZ;
        }

        if(patient.sex==Patient.KADIN)
        {
            patient.name = "ELÝF";
            patient.surname="TEVETOÐLU";
        }
        else
        {
            patient.name = "ASLAN";
            patient.surname="ATAÇ";
        }




        System.out.println(patient.toString());

        return patient;

    }

    public static void hastaYarat(Context context,int kac_hasta)
    {
        DBSQLiteOfAllPatients liteOfAllPatients = new DBSQLiteOfAllPatients(context);
        liteOfAllPatients.onCreate(liteOfAllPatients.getWritableDatabase());

        for(int i=0;i<kac_hasta;i++)
        {

            liteOfAllPatients.addPatient(PatientUtillty.getGeneratedPatient());
        }

    }

    public static void randevuEkle(Context context,Patient hangi_hasta,int kac_randevu,String hangi_gune)
    {

        PatientInnerManager patientInnerManager= new PatientInnerManager(context);

        for(int i=0;i<kac_randevu;i++)
        {

            patientInnerManager.randevu_ekle(hangi_hasta,getAppointment(hangi_gune));

        }

    }


    public static void kisiselBilgiEkle(Context context,Patient hangi_hasta,int kac_tane)
    {

        PatientInnerManager patientInnerManager= new PatientInnerManager(context);

        for(int i=0;i<kac_tane;i++)
        {

            patientInnerManager.kisisel_bilgi_ekle(hangi_hasta,getPersonalInformation());

        }

    }


    public static void kisiselBilgiEkleAdress(Context context,Patient hangi_hasta,int kac_tane)
    {

        PatientInnerManager patientInnerManager= new PatientInnerManager(context);

        for(int i=0;i<kac_tane;i++)
        {

            patientInnerManager.kisisel_bilgi_ekle(hangi_hasta,getPersonalInformationAdress());

        }

    }


    public static void kisiselBilgiEkleTelefone(Context context,Patient hangi_hasta,int kac_tane)
    {

        PatientInnerManager patientInnerManager= new PatientInnerManager(context);

        for(int i=0;i<kac_tane;i++)
        {

            patientInnerManager.kisisel_bilgi_ekle(hangi_hasta,getPersonalInformationTelefone());

        }

    }


    public  static VisitInformations getAppointment(String hangi_gun)
    {
        VisitInformations visitInformations= new VisitInformations();

        visitInformations.appointmentDate=hangi_gun;
        visitInformations.visitType="rutin randevü";
        visitInformations.notes="bu hasta da saðlam parça kalmamýþ";

        return visitInformations;
    }


    public static PersonelInformations getPersonalInformation()
    {
        PersonelInformations informations= new PersonelInformations();

        informations.adress_description="kendi evi";
        informations.city="konya";
        informations.district="karapýnar";
        informations.neighborhood="ipekçi mah.";
        informations.street="132145 sok.";
        informations.apartmant_name="lalegül apartmaný";
        informations.door_number=55;

        informations.tel_no_description="kendi telefonu";
        informations.tel_no1="05421457878";
        informations.tel_no2="05324562312";

        return informations;
    }

    public static PersonelInformations getPersonalInformationAdress()
    {
        PersonelInformations informations= new PersonelInformations();

        informations.adress_description="kendi evi";
        informations.city="konya";
        informations.district="karapýnar";
        informations.neighborhood="ipekçi mah.";
        informations.street="132145 sok.";
        informations.apartmant_name="lalegül apartmaný";
        informations.door_number=55;


        return informations;
    }

    public static PersonelInformations getPersonalInformationTelefone()
    {
        PersonelInformations informations= new PersonelInformations();



        informations.tel_no_description="kendi telefonu";
        informations.tel_no1="05421457878";
        informations.tel_no2="05324562312";

        return informations;
    }
}


