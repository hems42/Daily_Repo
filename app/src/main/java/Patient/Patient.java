package Patient;

public class Patient {


    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", tc_no='" + tc_no + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", birthday='" + birthday + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                ", dependency='" + dependency + '\'' +
                ", final_situation='" + final_situation + '\'' +
                ", created_date='" + created_date + '\'' +
                '}';
    }

    public Patient() {
    }





    public Patient(String tc_no, String name, String surname, String birthday, String sex, String dependency, String final_situation) {
        this.tc_no = tc_no;
        this.name = name;
        this.surname = surname;
        this.birthday = birthday;
        this.sex = sex;
        this.dependency = dependency;
        this.final_situation = final_situation;


    }



    public static  String TUM_HASTALAR="TÜM HASTALAR";

    public static  String TAM_BAGIMLI="TAM BAÐIMLI";
    public static  String YARI_BAGIMLI="YARI BAÐIMLI";
    public static  String BAGIMSIZ="BAÐIMSIZ";

    public static  String AKTIF="AKTÝF";
    public static  String PASIF="PASÝF";
    public static  String EX="EX";

    public static  String ERKEK="E";
    public static  String KADIN="K";






    public int id;
    public String tc_no;
    public String name;
    public String surname;
    public String birthday;
    public  int age;
    public String sex;
    public String dependency;
    public String final_situation;
    public String created_date;







/*



    public boolean randevu_ekle(VisitInformations visitInformations)
    {
        visitInformations.tc_no=this.tc_no;
        visitInformations.name=this.name;
        visitInformations.surname=this.surname;

       return liteAppointment.addPatientAppointment(visitInformations);


    }

    public boolean ziyaret_ekle(VisitInformations visitInformations)
    {
        visitInformations.tc_no=this.tc_no;
        visitInformations.name=this.name;
        visitInformations.surname=this.surname;

     return liteVisit.addPatientVisit(visitInformations);


    }


    public boolean kisisel_bilgi_ekle(PersonelInformations informations)
    {
        informations.tc_no=this.tc_no;
        informations.name=this.name;
        informations.surname=this.surname;

       return litePersonalInformation.addPatientInformation(informations);

    }


    public boolean adres_ekle(Adress adress)
    {
        adress.tc_no=this.tc_no;
        adress.name=this.name;
        adress.surname=this.surname;

       return litePersonalInformation.addPatientAdress(adress);

    }

    public boolean telefon_ekle(Telefon telefon)
    {
        telefon.tc_no=this.tc_no;
        telefon.name=this.name;
        telefon.surname=this.surname;

       return litePersonalInformation.addPatientTelefon(telefon);

    }


    public void lokasyon_ekle()
    {
        // bunu sonra ekleyecem
    }


    public ArrayList<VisitInformations> tum_randevulari_getir()
    {

      return  liteAppointment.getAllAppointmentsOfPatient(this.tc_no);

    }

    public VisitInformations randevuyu_getir(String appointment_day)
    {
        return  liteAppointment.getAppointment(this.tc_no,appointment_day);
    }

    public ArrayList<VisitInformations> tum_ziyaretleri_getir()
    {

        return  liteVisit.getAllVisitsOfPatient(this.tc_no);

    }

    public VisitInformations ziyareti_getir(String visit_day)
    {
        return  liteVisit.getVisit(this.tc_no, visit_day);
    }


    public ArrayList<PersonelInformations> tum_bigileri_getir()
    {

        return  litePersonalInformation.getAllPatientInformations(this.tc_no);

    }


    public ArrayList<Adress> tum_adresleri_getir()
    {

        return  litePersonalInformation.getAllPatientAdress(this.tc_no);

    }

    public ArrayList<Telefon> tum_telefonlari_getir()
    {

        return  litePersonalInformation.getAllPatientTelefons(this.tc_no);

    }


    public Adress adres_getir(String adres_aciklamasi)
    {

        return  litePersonalInformation.getPatientAdress(this.tc_no,adres_aciklamasi);

    }


    public Telefon telefon_getir(String telefon_aciklamasi)
    {

        return  litePersonalInformation.getPatientTelefon(this.tc_no,telefon_aciklamasi);

    }




    public boolean randevu_sil(VisitInformations visitInformations)
    {
        visitInformations.tc_no=this.tc_no;
        visitInformations.name=this.name;
        visitInformations.surname=this.surname;

       return liteAppointment.deletePatientAppointment(visitInformations);


    }

    public boolean ziyaret_sil(VisitInformations visitInformations)
    {
        visitInformations.tc_no=this.tc_no;
        visitInformations.name=this.name;
        visitInformations.surname=this.surname;

       return liteVisit.deletePatientVisit(visitInformations);


    }


    public boolean kisisel_bilgi_sil(PersonelInformations informations)
    {
        informations.tc_no=this.tc_no;
        informations.name=this.name;
        informations.surname=this.surname;

      return   litePersonalInformation.deletePatientInformation(informations);

    }


    public boolean tum_kisisel_bilgileri_sil()
    {
        PersonelInformations informations= new PersonelInformations();

        informations.tc_no=this.tc_no;
        informations.name=this.name;
        informations.surname=this.surname;

       return litePersonalInformation.deleteAllPatientInformation(this.tc_no);

    }

    public boolean adres_sil(Adress adress)
    {
        adress.tc_no=this.tc_no;
        adress.name=this.name;
        adress.surname=this.surname;

       return litePersonalInformation.deletePatientAdress(adress);

    }

    public boolean telefon_sil(Telefon telefon)
    {
        telefon.tc_no=this.tc_no;
        telefon.name=this.name;
        telefon.surname=this.surname;

      return   litePersonalInformation.deletePatientTelefon(telefon);

    }


    public boolean tum_adresleri_sil()
    {


       return litePersonalInformation.deleteAllPatientAdress(this.tc_no);

    }

    public boolean tum_telefonlari_sil()
    {


       return litePersonalInformation.deleteAllPatientTelefon(this.tc_no);

    }

    public boolean randevu_guncelle(VisitInformations visitInformations_eski, VisitInformations visitInformations_yeni)
    {
        visitInformations_eski.tc_no=this.tc_no;
        visitInformations_eski.name=this.name;
        visitInformations_eski.surname=this.surname;

       return liteAppointment.updatePatientAppointment(visitInformations_eski,visitInformations_yeni);


    }

    public boolean ziyaret_guncelle(VisitInformations visitInformations_eski,VisitInformations visitInformations_yeni)
    {
        visitInformations_eski.tc_no=this.tc_no;
        visitInformations_eski.name=this.name;
        visitInformations_eski.surname=this.surname;

       return liteVisit.updatePatientVisit(visitInformations_eski,visitInformations_yeni);


    }

    public boolean kisisel_bilgi_guncelle(PersonelInformations informations_eski,PersonelInformations personelInformations_yeni)
    {
        informations_eski.tc_no=this.tc_no;
        informations_eski.name=this.name;
        informations_eski.surname=this.surname;

       return litePersonalInformation.updatePatientInformationBoth(informations_eski,personelInformations_yeni);

    }

    public boolean adress_guncelle(String adress_eski_aciklamasi,Adress adress_yeni)
    {
        Adress adress_eski= new Adress();
        adress_eski.tc_no=this.tc_no;
        adress_eski.name=this.name;
        adress_eski.surname=this.surname;
        adress_eski.adress_description=adress_eski_aciklamasi;

      return   litePersonalInformation.updatePatientAdress(adress_eski,adress_yeni);

    }

    public boolean adress_guncelle(Adress adress_eski,Adress adress_yeni)
    {

        adress_eski.tc_no=this.tc_no;
        adress_eski.name=this.name;
        adress_eski.surname=this.surname;


       return litePersonalInformation.updatePatientAdress(adress_eski,adress_yeni);

    }

    public boolean telefon_guncelle(String telefon_eski_aciklamasi,Telefon telefon_yeni)
    {
        Telefon telefon_eski= new Telefon();
        telefon_eski.tc_no=this.tc_no;
        telefon_eski.name=this.name;
        telefon_eski.surname=this.surname;
        telefon_eski.tel_no_description=telefon_eski_aciklamasi;


       return litePersonalInformation.updatePatientTelNumber(telefon_eski,telefon_yeni);

    }

    public boolean telefon_guncelle(Telefon informations_eski,Telefon personelInformations_yeni)
    {
        informations_eski.tc_no=this.tc_no;
        informations_eski.name=this.name;
        informations_eski.surname=this.surname;

       return litePersonalInformation.updatePatientTelNumber(informations_eski,personelInformations_yeni);

    }


    public boolean adress_onceden_olusturulmusmu(String adres_aciklamsi)
    {
      return   litePersonalInformation.isContainPatientAdress(this.tc_no,adres_aciklamsi);
    }

    public boolean telefon_onceden_olusturulmusmu(String tel_no)
    {

        return  litePersonalInformation.isContainPatientTelefon(this.tc_no,tel_no);

    }

    public boolean onceden_olusturulmusmu()
    {

        return  false;
    }



*/

}
