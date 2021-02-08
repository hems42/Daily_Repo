package DataBaseSQLite;


import Utils.CustomTime;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
import Patient.Adress;
import Patient.PersonelInformations;
import Patient.Telefon;


import java.util.ArrayList;

public  class DBSQLiteOfPersonelInformations extends SQLiteOpenHelper  {




    private static final int dataBaseVersion = 1;
    private static final String dataBaseName =DataBaseExpressions.databaseNameOfPatient;
    private static final String dataBasePatient_TC_NO = "TC_NO";
    private static final String dataBasePatient_name = "AD";
    private static final String dataBasePatient_surname = "SOYAD";
    private static final String dataBasePatient_adress_description = "ADRES_ACIKLAMASI";
    private static final String dataBasePatient_adress_city = "SEHIR";
    private static final String dataBasePatient_adress_district = "ILCE";
    private static final String dataBasePatient_adress_neighborhood = "MAHALLE";
    private static final String dataBasePatient_adress_street = "SOKAK_CADDE";
    private static final String dataBasePatient_adress_apartmant_name = "APARTMAN";
    private static final String dataBasePatient_adress_door_number = "KAPI_NO";
    private static final String dataBasePatient_tel_no_description = "TELEFON_NO_ACIKLAMASI";
    private static final String dataBasePatient_tel_no1 = "TELEFON_NO_1";
    private static final String dataBasePatient_tel_no2 = "TELEFON_NO_2";
    private static final String dataBasePatient_created_date = "ISLEM_TARIHI";

    // bu ikisini constructor la birlikte hangi tablo kullanacaksa o kullansýn diye omnlara býraktým...
    private static String dataBasePatient_table_name = "PatientInformations";
    public static String dataBasePatient_Create_Table = null;


       String current_time=CustomTime.getTime();

    public DBSQLiteOfPersonelInformations(@Nullable Context context) {

        super(context, dataBaseName, null, dataBaseVersion);

        // açýklama :
        // burda ayný veri tabanýnýn farklý tablolarýný kullanayým diye base veri tabaný tasarladým ammma velakin bir türlü tablo ismini altý
        //sýnýftan aktaramadým ne yaptýysam null dönderdi table nama e sen öle yaparsa bende senin sorgu tablonu deðiþtiririm dsedim ve constructoruýn içinde gelen
        // table name i aldým sorgucümlesine çaktým ve table name ide bu þekilde güncellemiþ oldum sýkýntý yok yani ::))))))))))



        dataBasePatient_Create_Table = "CREATE TABLE IF NOT EXISTS "
                + dataBasePatient_table_name
                + " ( "
                + dataBasePatient_TC_NO
                + " TEXT  NOT NULL, "
                + dataBasePatient_name
                + " TEXT, "
                + dataBasePatient_surname
                + " TEXT, "
                + dataBasePatient_adress_description
                + " TEXT, "
                +dataBasePatient_adress_city
                + " TEXT, "
                +dataBasePatient_adress_district
                + " TEXT, "
                +dataBasePatient_adress_neighborhood
                + " TEXT, "
                +dataBasePatient_adress_street
                + " TEXT, "
                +dataBasePatient_adress_apartmant_name
                + " TEXT, "
                +dataBasePatient_adress_door_number
                + " TEXT, "
                + dataBasePatient_tel_no_description
                + " TEXT, "
                + dataBasePatient_tel_no1
                + " TEXT, "
                + dataBasePatient_tel_no2
                + " TEXT, "
                + dataBasePatient_created_date
                + " TEXT );";

    }


    @Override
    public void onCreate(SQLiteDatabase db) {


        db.execSQL(dataBasePatient_Create_Table);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + dataBasePatient_table_name);
        this.onCreate(db);


    }








    // alt sýnýflar için gerekli metodlar

    public boolean addPatientInformation(PersonelInformations personelInformations) {
        SQLiteDatabase db = this.getWritableDatabase();
        if (db != null) {
            ContentValues values = new ContentValues();


            values.put(dataBasePatient_TC_NO, personelInformations.tc_no);
            values.put(dataBasePatient_name, personelInformations.name);
            values.put(dataBasePatient_surname, personelInformations.surname);
            values.put(dataBasePatient_adress_description,personelInformations.adress_description);
            values.put(dataBasePatient_adress_city, personelInformations.city);
            values.put(dataBasePatient_adress_district, personelInformations.district);
            values.put(dataBasePatient_adress_neighborhood, personelInformations.neighborhood);
            values.put(dataBasePatient_adress_street, personelInformations.street);
            values.put(dataBasePatient_adress_apartmant_name, personelInformations.apartmant_name);
            values.put(dataBasePatient_adress_door_number, personelInformations.door_number);
            values.put(dataBasePatient_tel_no_description, personelInformations.tel_no_description);
            values.put(dataBasePatient_tel_no1, personelInformations.tel_no1);
            values.put(dataBasePatient_tel_no2, personelInformations.tel_no2);
            values.put(dataBasePatient_created_date, current_time); // iþlem zamanýnýve tarihini kendisi otoamtik atýcak

            long i = db.insert(dataBasePatient_table_name, null, values);
            db.close();
            if (i > 0) {

                return true;
            } else {
                return false;
            }

        } else {
            return false;
        }


    }

    public boolean addPatientAdress(Adress adress) {
        SQLiteDatabase db = this.getWritableDatabase();
        if (db != null) {
            ContentValues values = new ContentValues();


            values.put(dataBasePatient_TC_NO, adress.tc_no);
            values.put(dataBasePatient_name, adress.name);
            values.put(dataBasePatient_surname, adress.surname);
            values.put(dataBasePatient_adress_description,adress.adress_description);
            values.put(dataBasePatient_adress_city, adress.city);
            values.put(dataBasePatient_adress_district, adress.district);
            values.put(dataBasePatient_adress_neighborhood, adress.neighborhood);
            values.put(dataBasePatient_adress_street, adress.street);
            values.put(dataBasePatient_adress_apartmant_name, adress.apartmant_name);
            values.put(dataBasePatient_adress_door_number, adress.door_number);
            values.put(dataBasePatient_created_date, current_time); // iþlem zamanýnýve tarihini kendisi otoamtik atýcak

            long i = db.insert(dataBasePatient_table_name, null, values);
            db.close();
            if (i > 0) {

                return true;
            } else {
                return false;
            }

        } else {
            return false;
        }


    }

    public boolean addPatientTelefon(Telefon telefone) {
        SQLiteDatabase db = this.getWritableDatabase();
        if (db != null) {
            ContentValues values = new ContentValues();


            values.put(dataBasePatient_TC_NO, telefone.tc_no);
            values.put(dataBasePatient_name, telefone.name);
            values.put(dataBasePatient_surname, telefone.surname);
            values.put(dataBasePatient_tel_no_description, telefone.tel_no_description);
            values.put(dataBasePatient_tel_no1, telefone.tel_no1);
            values.put(dataBasePatient_tel_no2, telefone.tel_no2);
            values.put(dataBasePatient_created_date, current_time); // iþlem zamanýnýve tarihini kendisi otoamtik atýcak

            long i = db.insert(dataBasePatient_table_name, null, values);
            db.close();
            if (i > 0) {

                return true;
            } else {
                return false;
            }

        } else {
            return false;
        }


    }



    public boolean deletePatientInformation(PersonelInformations personelInformations) {
        SQLiteDatabase db = this.getWritableDatabase();

        int i = db.delete(dataBasePatient_table_name, dataBasePatient_TC_NO + " = ?"
                +  " AND " + dataBasePatient_adress_description + " =?" , new String[]{personelInformations.tc_no,personelInformations.adress_description});

        db.close();

        if (i > 0) {
            return true;
        } else {
            return false;
        }


    }

    public boolean deletePatientAdress(Adress adress) {
        SQLiteDatabase db = this.getWritableDatabase();

        int i = db.delete(dataBasePatient_table_name, dataBasePatient_TC_NO + " = ?"
                +  " AND " + dataBasePatient_adress_description + " =?" , new String[]{adress.tc_no,adress.adress_description});

        db.close();

        if (i > 0) {
            return true;
        } else {
            return false;
        }


    }

    public boolean deletePatientTelefon(Telefon telefone) {
        SQLiteDatabase db = this.getWritableDatabase();

        int i = db.delete(dataBasePatient_table_name, dataBasePatient_TC_NO + " = ?"
                +  " AND " + dataBasePatient_adress_description + " =?" , new String[]{telefone.tc_no,telefone.tel_no_description});

        db.close();

        if (i > 0) {
            return true;
        } else {
            return false;
        }


    }


    public boolean deleteAllPatientInformation(String tc_no) {
        SQLiteDatabase db = this.getWritableDatabase();

        int i = db.delete(dataBasePatient_table_name, dataBasePatient_TC_NO + " = ?"
                , new String[]{tc_no});

        db.close();

        if (i > 0) {
            return true;
        } else {
            return false;
        }


    }

    public boolean deleteAllPatientAdress(String tc_no) {
        SQLiteDatabase db = this.getWritableDatabase();

        int i = db.delete(dataBasePatient_table_name, dataBasePatient_TC_NO + " = ?"
                , new String[]{tc_no});

        db.close();

        if (i > 0) {
            return true;
        } else {
            return false;
        }


    }

    public boolean deleteAllPatientTelefon(String tc_no) {
        SQLiteDatabase db = this.getWritableDatabase();

        int i = db.delete(dataBasePatient_table_name, dataBasePatient_TC_NO + " = ?"
                , new String[]{tc_no});

        db.close();

        if (i > 0) {
            return true;
        } else {
            return false;
        }


    }



    public boolean updatePatientInformationBoth(PersonelInformations informations_old ,PersonelInformations informations_new) {
        // dönen int deðri güncellemeden etkilenen deðr sayýsýdýr.
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(dataBasePatient_name, informations_new.name);
        values.put(dataBasePatient_surname, informations_new.surname);
        values.put(dataBasePatient_adress_description,informations_new.adress_description);
        values.put(dataBasePatient_adress_city, informations_new.city);
        values.put(dataBasePatient_adress_district, informations_new.district);
        values.put(dataBasePatient_adress_neighborhood, informations_new.neighborhood);
        values.put(dataBasePatient_adress_street, informations_new.street);
        values.put(dataBasePatient_adress_apartmant_name, informations_new.apartmant_name);
        values.put(dataBasePatient_adress_door_number, informations_new.door_number);
        values.put(dataBasePatient_tel_no_description, informations_new.tel_no_description);
        values.put(dataBasePatient_tel_no1, informations_new.tel_no1);
        values.put(dataBasePatient_tel_no2, informations_new.tel_no2);
        values.put(dataBasePatient_created_date, current_time); // iþlem zamanýnýve tarihini kendisi otoamtik atýcak

        int i = db.update(dataBasePatient_table_name, values, dataBasePatient_TC_NO + " = ?"

                        +  " AND " + dataBasePatient_adress_description + " =?", new String[]{informations_old.tc_no,informations_old.adress_description});
        db.close();

        if (i > 0) {
            return true;
        } else {
            return false;
        }


    }

    public boolean updatePatientAdress(Adress adress_old ,Adress adress_new) {
        // dönen int deðri güncellemeden etkilenen deðr sayýsýdýr.
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(dataBasePatient_name, adress_new.name);
        values.put(dataBasePatient_surname, adress_new.surname);
        values.put(dataBasePatient_adress_description,adress_new.adress_description);
        values.put(dataBasePatient_adress_city, adress_new.city);
        values.put(dataBasePatient_adress_district, adress_new.district);
        values.put(dataBasePatient_adress_neighborhood, adress_new.neighborhood);
        values.put(dataBasePatient_adress_street, adress_new.street);
        values.put(dataBasePatient_adress_apartmant_name, adress_new.apartmant_name);
        values.put(dataBasePatient_adress_door_number, adress_new.door_number);

        values.put(dataBasePatient_created_date,current_time); // iþlem zamanýnýve tarihini kendisi otoamtik atýcak

        int i = db.update(dataBasePatient_table_name, values, dataBasePatient_TC_NO + " = ?"

                +  " AND " + dataBasePatient_adress_description + " =?", new String[]{adress_old.tc_no,adress_old.adress_description});
        db.close();

        if (i > 0) {
            return true;
        } else {
            return false;
        }


    }

    public boolean updatePatientTelNumber(Telefon telefon_old ,Telefon telefon_new) {
        // dönen int deðri güncellemeden etkilenen deðr sayýsýdýr.
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(dataBasePatient_name, telefon_new.name);
        values.put(dataBasePatient_surname, telefon_new.surname);
        values.put(dataBasePatient_tel_no_description, telefon_new.tel_no_description);
        values.put(dataBasePatient_tel_no1, telefon_new.tel_no1);
        values.put(dataBasePatient_tel_no2, telefon_new.tel_no2);
        values.put(dataBasePatient_created_date,current_time); // iþlem zamanýnýve tarihini kendisi otoamtik atýcak

        int i = db.update(dataBasePatient_table_name, values, dataBasePatient_TC_NO + " = ?"

                +  " AND " + dataBasePatient_tel_no_description + " =?", new String[]{telefon_old.tc_no,telefon_old.tel_no_description});
        db.close();

        if (i > 0) {
            return true;
        } else {
            return false;
        }


    }




    public PersonelInformations getPersonelInformation(String patient_tc, String adress_description) {

        SQLiteDatabase db = this.getReadableDatabase();
        PersonelInformations  personelInformations= new PersonelInformations();
        String query = "SELECT * FROM " + dataBasePatient_table_name + " WHERE "+dataBasePatient_TC_NO+"=" + "'" +patient_tc +"' "
                + " AND " +dataBasePatient_adress_description+"=" + "'" +adress_description +"'";
                ;
        Cursor cursor = db.rawQuery(query, null);




        if (cursor!= null && cursor.getCount() > 0)
        {
            cursor.moveToFirst();



            personelInformations.tc_no=cursor.getString(0);
            personelInformations.name=cursor.getString(1);
            personelInformations.surname=cursor.getString(2);
            personelInformations.adress_description=cursor.getString(3);
            personelInformations.city=cursor.getString(4);
            personelInformations.district=cursor.getString(5);
            personelInformations.neighborhood=cursor.getString(6);
            personelInformations.street=cursor.getString(7);
            personelInformations.apartmant_name=cursor.getString(8);
            personelInformations.door_number=cursor.getInt(9);
            personelInformations.tel_no_description=cursor.getString(10);
            personelInformations.tel_no1=cursor.getString(11);
            personelInformations.tel_no2=cursor.getString(12);
            personelInformations.created_date =cursor.getString(13);


        }

        cursor.close();


        if(personelInformations!=null)
        {
            return personelInformations;
        }
        else
        {
            return null;
        }





    }

    public Adress getPatientAdress(String patient_tc, String adress_description) {

        SQLiteDatabase db = this.getReadableDatabase();
        Adress  adress= new Adress();
        String query = "SELECT * FROM " + dataBasePatient_table_name + " WHERE "+dataBasePatient_TC_NO+"=" + "'" +patient_tc +"' "
                + " AND " +dataBasePatient_adress_description+"=" + "'" +adress_description +"'";
        ;
        Cursor cursor = db.rawQuery(query, null);




        if (cursor!= null && cursor.getCount() > 0)
        {
            cursor.moveToFirst();



            adress.tc_no=cursor.getString(0);
            adress.name=cursor.getString(1);
            adress.surname=cursor.getString(2);
            adress.adress_description=cursor.getString(3);
            adress.city=cursor.getString(4);
            adress.district=cursor.getString(5);
            adress.neighborhood=cursor.getString(6);
            adress.street=cursor.getString(7);
            adress.apartmant_name=cursor.getString(8);
            adress.door_number=cursor.getString(9);
            adress.created_date =cursor.getString(13);


        }

        cursor.close();


        if(adress!=null)
        {
            return adress;
        }
        else
        {
            return null;
        }





    }

    public Telefon getPatientTelefon(String patient_tc, String telefon_description) {

        SQLiteDatabase db = this.getReadableDatabase();
        Telefon  telefon= new Telefon();
        String query = "SELECT * FROM " + dataBasePatient_table_name + " WHERE "+dataBasePatient_TC_NO+"=" + "'" +patient_tc +"' "
                + " AND " +dataBasePatient_adress_description+"=" + "'" +telefon_description +"'";
        ;
        Cursor cursor = db.rawQuery(query, null);




        if (cursor!= null && cursor.getCount() > 0)
        {
            cursor.moveToFirst();



            telefon.tc_no=cursor.getString(0);
            telefon.name=cursor.getString(1);
            telefon.surname=cursor.getString(2);
            telefon.tel_no_description=cursor.getString(10);
            telefon.tel_no1=cursor.getString(11);
            telefon.tel_no2=cursor.getString(12);
            telefon.created_date =cursor.getString(13);


        }

        cursor.close();


        if(telefon!=null)
        {
            return telefon;
        }
        else
        {
            return null;
        }





    }

    public ArrayList<PersonelInformations> getAllPatientInformations(String tc_no) {

        SQLiteDatabase db = this.getReadableDatabase();
        PersonelInformations  personelInformations;
        ArrayList<PersonelInformations> allPatient= new ArrayList<>();
        String query = "SELECT * FROM " + dataBasePatient_table_name + " WHERE " + dataBasePatient_TC_NO + " =" + "'" +tc_no+"'";
        Cursor cursor = db.rawQuery(query, null);




        if (cursor!= null && cursor.getCount() > 0)
        {
            cursor.moveToFirst();
             do {
                 {
                     personelInformations=new PersonelInformations();

                     personelInformations.tc_no=cursor.getString(0);
                     personelInformations.name=cursor.getString(1);
                     personelInformations.surname=cursor.getString(2);
                     personelInformations.adress_description=cursor.getString(3);
                     personelInformations.city=cursor.getString(4);
                     personelInformations.district=cursor.getString(5);
                     personelInformations.neighborhood=cursor.getString(6);
                     personelInformations.street=cursor.getString(7);
                     personelInformations.apartmant_name=cursor.getString(8);
                     personelInformations.door_number=cursor.getInt(9);
                     personelInformations.tel_no_description=cursor.getString(10);
                     personelInformations.tel_no1=cursor.getString(11);
                     personelInformations.tel_no2=cursor.getString(12);
                     personelInformations.created_date =cursor.getString(13);


                     allPatient.add(personelInformations);
                 }
             }
             while (cursor.moveToNext());

        }

        cursor.close();


        if(allPatient!=null)
        {
            return allPatient;
        }
        else
        {
            return null;
        }
    }

    public ArrayList<Adress> getAllPatientAdress(String tc_no) {

        SQLiteDatabase db = this.getReadableDatabase();
        Adress  adress;
        ArrayList<Adress> allAdresses= new ArrayList<>();
        String query = "SELECT * FROM " + dataBasePatient_table_name + " WHERE " + dataBasePatient_TC_NO + " =" + "'" +tc_no+"'";
        Cursor cursor = db.rawQuery(query, null);




        if (cursor!= null && cursor.getCount() > 0)
        {
            cursor.moveToFirst();
            do {
                {
                    if(cursor.getString(3)!=null)
                    {
                        adress=new Adress();

                        adress.tc_no=cursor.getString(0);
                        adress.name=cursor.getString(1);
                        adress.surname=cursor.getString(2);
                        adress.adress_description=cursor.getString(3);
                        adress.city=cursor.getString(4);
                        adress.district=cursor.getString(5);
                        adress.neighborhood=cursor.getString(6);
                        adress.street=cursor.getString(7);
                        adress.apartmant_name=cursor.getString(8);
                        adress.door_number=cursor.getString(9);
                        adress.created_date =cursor.getString(13);


                        allAdresses.add(adress);
                    }

                }
            }
            while (cursor.moveToNext());

        }

        cursor.close();



        if(allAdresses!=null)
        {
            return allAdresses;
        }
        else
        {
            return null;
        }
    }

    public ArrayList<Telefon> getAllPatientTelefons(String tc_no) {

        SQLiteDatabase db = this.getReadableDatabase();
        Telefon  telefon;
        ArrayList<Telefon> allTelefons= new ArrayList<>();
        String query = "SELECT * FROM " + dataBasePatient_table_name + " WHERE " + dataBasePatient_TC_NO + " =" + "'" +tc_no+"'";
        Cursor cursor = db.rawQuery(query, null);




        if (cursor!= null && cursor.getCount() > 0)
        {
            cursor.moveToFirst();
            do {
                {
                    if(cursor.getString(10)!=null)
                    {
                        telefon=new Telefon();

                        telefon.tc_no=cursor.getString(0);
                        telefon.name=cursor.getString(1);
                        telefon.surname=cursor.getString(2);
                        telefon.tel_no_description=cursor.getString(10);
                        telefon.tel_no1=cursor.getString(11);
                        telefon.tel_no2=cursor.getString(12);
                        telefon.created_date =cursor.getString(13);


                        allTelefons.add(telefon);
                    }

                }
            }
            while (cursor.moveToNext());

        }

        cursor.close();



        if(allTelefons!=null)
        {
            return allTelefons;
        }
        else
        {
            return null;
        }
    }





    public ArrayList<Adress> getAllPatientAdress() {

        SQLiteDatabase db = this.getReadableDatabase();
        Adress  adress;
        ArrayList<Adress> allAdresses= new ArrayList<>();
        String query = "SELECT * FROM " + dataBasePatient_table_name;
        Cursor cursor = db.rawQuery(query, null);




        if (cursor!= null && cursor.getCount() > 0)
        {
            cursor.moveToFirst();
            do {
                {
                    adress=new Adress();

                    adress.tc_no=cursor.getString(0);
                    adress.name=cursor.getString(1);
                    adress.surname=cursor.getString(2);
                    adress.adress_description=cursor.getString(3);
                    adress.city=cursor.getString(4);
                    adress.district=cursor.getString(5);
                    adress.neighborhood=cursor.getString(6);
                    adress.street=cursor.getString(7);
                    adress.apartmant_name=cursor.getString(8);
                    adress.door_number=cursor.getString(9);
                    adress.created_date =cursor.getString(13);


                    allAdresses.add(adress);
                }
            }
            while (cursor.moveToNext());

        }

        cursor.close();


        if(allAdresses!=null)
        {
            return allAdresses;
        }
        else
        {
            return null;
        }
    }

    public ArrayList<Telefon> getAllPatientTelefons() {

        SQLiteDatabase db = this.getReadableDatabase();
        Telefon  telefon;
        ArrayList<Telefon> allTelefons= new ArrayList<>();
        String query = "SELECT * FROM " + dataBasePatient_table_name;
        Cursor cursor = db.rawQuery(query, null);




        if (cursor!= null && cursor.getCount() > 0)
        {
            cursor.moveToFirst();
            do {
                {
                    telefon=new Telefon();

                    telefon.tc_no=cursor.getString(0);
                    telefon.name=cursor.getString(1);
                    telefon.surname=cursor.getString(2);
                    telefon.tel_no_description=cursor.getString(10);
                    telefon.tel_no1=cursor.getString(11);
                    telefon.tel_no2=cursor.getString(12);
                    telefon.created_date =cursor.getString(13);


                    allTelefons.add(telefon);
                }
            }
            while (cursor.moveToNext());

        }

        cursor.close();


        if(allTelefons!=null)
        {
            return allTelefons;
        }
        else
        {
            return null;
        }
    }

    public ArrayList<PersonelInformations> getAllPatientInformations() {

        SQLiteDatabase db = this.getReadableDatabase();
        PersonelInformations  personelInformations;
        ArrayList<PersonelInformations> allPatient= new ArrayList<>();
        String query = "SELECT * FROM " + dataBasePatient_table_name;
        Cursor cursor = db.rawQuery(query, null);




        if (cursor!= null && cursor.getCount() > 0)
        {
            cursor.moveToFirst();
            do {
                {
                    personelInformations=new PersonelInformations();

                    personelInformations.tc_no=cursor.getString(0);
                    personelInformations.name=cursor.getString(1);
                    personelInformations.surname=cursor.getString(2);
                    personelInformations.adress_description=cursor.getString(3);
                    personelInformations.city=cursor.getString(4);
                    personelInformations.district=cursor.getString(5);
                    personelInformations.neighborhood=cursor.getString(6);
                    personelInformations.street=cursor.getString(7);
                    personelInformations.apartmant_name=cursor.getString(8);
                    personelInformations.door_number=cursor.getInt(9);
                    personelInformations.tel_no_description=cursor.getString(10);
                    personelInformations.tel_no1=cursor.getString(11);
                    personelInformations.tel_no2=cursor.getString(12);
                    personelInformations.created_date =cursor.getString(13);


                    allPatient.add(personelInformations);
                }
            }
            while (cursor.moveToNext());

        }

        cursor.close();


        if(allPatient!=null)
        {
            return allPatient;
        }
        else
        {
            return null;
        }
    }



    public boolean isContainPatientInformation(PersonelInformations personelInformations) {

        boolean sonuc=false;

        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + dataBasePatient_table_name;
        Cursor cursor = db.rawQuery(query, null);


        if (cursor!= null && cursor.getCount() > 0)
        {
            cursor.moveToFirst();
            do {
                if(personelInformations.tc_no.equals(cursor.getString(1)))

                {
                    sonuc=true;
                    break;
                }




            }
            while (cursor.moveToNext());
        }

        cursor.close();

        return sonuc;

    }

    public boolean isContainPatientTelefon(Telefon telefon) {

        boolean sonuc=false;

        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + dataBasePatient_table_name;
        Cursor cursor = db.rawQuery(query, null);


        if (cursor!= null && cursor.getCount() > 0)
        {
            cursor.moveToFirst();
            do {
                if(telefon.tc_no.equals(cursor.getString(1)))

                {
                    sonuc=true;
                    break;
                }




            }
            while (cursor.moveToNext());
        }

        cursor.close();

        return sonuc;

    }

    public boolean isContainPatientTelefon(String tc_no,String telefon) {

        boolean sonuc=false;


        for(Telefon telefon_in:getAllPatientTelefons(tc_no))
        {

            if((telefon_in.tel_no1!=null&&telefon_in.tel_no1.matches(telefon))||(telefon_in.tel_no2!=null&&telefon_in.tel_no2.matches(telefon)))
            {
                sonuc=true;
            }
        }

       /* SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + dataBasePatient_table_name;
        Cursor cursor = db.rawQuery(query, null);


        if (cursor!= null && cursor.getCount() > 0)
        {
            cursor.moveToFirst();
            do {
                if(telefon.tc_no.equals(cursor.getString(1)))

                {
                    sonuc=true;
                    break;
                }




            }
            while (cursor.moveToNext());
        }

        cursor.close();*/

        return sonuc;

    }

    public boolean isContainPatientAdress(String tc_no,String adress_description) {

        boolean sonuc=false;


        for(Adress adress_in:getAllPatientAdress(tc_no))
        {
            if(adress_in.adress_description.matches(adress_description))
            {
                sonuc=true;
            }
        }

       /* SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + dataBasePatient_table_name;
        Cursor cursor = db.rawQuery(query, null);


        if (cursor!= null && cursor.getCount() > 0)
        {
            cursor.moveToFirst();
            do {
                if(telefon.tc_no.equals(cursor.getString(1)))

                {
                    sonuc=true;
                    break;
                }




            }
            while (cursor.moveToNext());
        }

        cursor.close();*/

        return sonuc;

    }



}
