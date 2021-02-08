package DataBaseSQLite;


import Patient.Patient;
import Utils.CustomTime;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
import Patient.VisitInformations;


import java.util.ArrayList;

public  class DBSQLiteOfVisit extends SQLiteOpenHelper  {


    private static final int dataBaseVersion = 1;
    private static final String dataBaseName =  DataBaseExpressions.databaseNameOfPatient;

    private static final String dataBasePatient_TC_NO = "TC_NO";
    private static final String dataBasePatient_name = "AD";
    private static final String dataBasePatient_surname = "SOYAD";
    private static final String dataBasePatient_visit_types = "ZIYARET_TURU";
    private static final String dataBasePatient_notes = "NOTLAR";
    private static final String dataBasePatient_sign = "IMZA";
    private static final String dataBasePatient_visit_result = "ZIYARET_SONUCU";
    private static final String dataBasePatient_scheduled_appointment_date = "RANDEVU_TARIHI";
    private static final String dataBasePatient_visit_date = "ZIYARET_TARIHI";
    private static final String dataBasePatient_created_date = "ISLEM_TARIHI";


    // bu ikisini constructor la birlikte hangi tablo kullanacaksa o kullans?n diye omnlara b?rakt?m...
    private static String dataBasePatient_table_name = "VisitInformations";
    public static String dataBasePatient_Create_Table = null;


      String current_time= CustomTime.getTime();

    public DBSQLiteOfVisit(@Nullable Context context) {

        super(context, dataBaseName, null, dataBaseVersion);

        // aç?klama :
        // burda ayn? veri taban?n?n farkl? tablolar?n? kullanay?m diye base veri taban? tasarlad?m ammma velakin bir türlü tablo ismini alt?
        //s?n?ftan aktaramad?m ne yapt?ysam null dönderdi table nama e sen öle yaparsa bende senin sorgu tablonu de?i?tiririm dsedim ve constructoru?n içinde gelen
        // table name i ald?m sorgucümlesine çakt?m ve table name ide bu ?ekilde güncellemi? oldum s?k?nt? yok yani ::))))))))))



        dataBasePatient_Create_Table = "CREATE TABLE IF NOT EXISTS "
                + dataBasePatient_table_name
                + " ( "
                + dataBasePatient_TC_NO
                + " TEXT NOT NULL, "
                + dataBasePatient_name
                + " TEXT, "
                + dataBasePatient_surname
                + " TEXT, "
                +dataBasePatient_visit_types
                + " TEXT, "
                +dataBasePatient_notes
                + " TEXT, "
                +dataBasePatient_visit_result
                + " TEXT, "
                +dataBasePatient_sign
                + " BLOB, "
                + dataBasePatient_scheduled_appointment_date
                + " TEXT, "
                + dataBasePatient_visit_date
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








    // alt s?n?flar için gerekli metodlar

    public boolean addPatientVisit(VisitInformations visitInformations) {
        SQLiteDatabase db = this.getWritableDatabase();
        if (db != null) {
            ContentValues values = new ContentValues();


            values.put(dataBasePatient_TC_NO, visitInformations.tc_no);
            values.put(dataBasePatient_name, visitInformations.name);
            values.put(dataBasePatient_surname, visitInformations.surname);
            values.put(dataBasePatient_visit_types,visitInformations.visitType);
            values.put(dataBasePatient_notes, visitInformations.notes);
            values.put(dataBasePatient_sign,visitInformations.sign);
            values.put(dataBasePatient_visit_result,visitInformations.visitResult);
            values.put(dataBasePatient_scheduled_appointment_date,visitInformations.appointmentDate);
            values.put(dataBasePatient_visit_date,current_time);
            values.put(dataBasePatient_created_date,current_time); // i?lem zaman?n?ve tarihini kendisi otoamtik at?cak



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

    public boolean deletePatientVisit(VisitInformations visitInformations) {
        SQLiteDatabase db = this.getWritableDatabase();

        int i = db.delete(dataBasePatient_table_name, dataBasePatient_TC_NO  + " = ? " + " AND "
                +dataBasePatient_scheduled_appointment_date + " =? ", new String[]{visitInformations.tc_no,visitInformations.appointmentDate});
/*
        db.execSQL("DELETE FROM "+dataBasePatient_table_name+ " WHERE "+ dataBasePatient_TC_NO +"="+"'"+visitInformations.tc_no+"'"+ " AND "

        +dataBasePatient_scheduled_appointment_date +"="+"'"+ visitInformations.appointmentDate+"';"    );*/


        db.close();



        if (i > 0) {
            return true;
        } else {
            return false;
        }


    }

    public boolean deletePatientAllVisit(Patient patient) {
        SQLiteDatabase db = this.getWritableDatabase();

        int i = db.delete(dataBasePatient_table_name, dataBasePatient_TC_NO  + " = ? ", new String[]{patient.tc_no});
/*
        db.execSQL("DELETE FROM "+dataBasePatient_table_name+ " WHERE "+ dataBasePatient_TC_NO +"="+"'"+visitInformations.tc_no+"'"+ " AND "

        +dataBasePatient_scheduled_appointment_date +"="+"'"+ visitInformations.appointmentDate+"';"    );*/


        db.close();



        if (i > 0) {
            return true;
        } else {
            return false;
        }


    }

    public boolean updatePatientVisit(VisitInformations visitoutOfDate,VisitInformations visitcurrent) {
        // dönen int de?ri güncellemeden etkilenen de?r say?s?d?r.
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(dataBasePatient_visit_types, visitcurrent.visitType);
        values.put(dataBasePatient_notes, visitcurrent.notes);
        values.put(dataBasePatient_sign, visitcurrent.sign);
        values.put(dataBasePatient_visit_result, visitcurrent.visitResult);
        values.put(dataBasePatient_scheduled_appointment_date, visitcurrent.appointmentDate);
        values.put(dataBasePatient_visit_date, visitcurrent.visitDate);
        values.put(dataBasePatient_created_date,current_time); // i?lem zaman?n?ve tarihini kendisi otoamtik at?cak

        int i = db.update(dataBasePatient_table_name, values,  dataBasePatient_TC_NO  + " = ? " + " AND "
                +dataBasePatient_scheduled_appointment_date + " =? ", new String[]{visitoutOfDate.tc_no,visitoutOfDate.appointmentDate});
        db.close();

        if (i > 0) {
            return true;
        } else {
            return false;
        }


    }

    public VisitInformations getVisit(String patient_tc, String visit_day) {

        SQLiteDatabase db = this.getReadableDatabase();
        VisitInformations visitInformations= new VisitInformations();
        String query = "SELECT * FROM " + dataBasePatient_table_name + " WHERE "+dataBasePatient_TC_NO+"=" + "'" +patient_tc +"'" + " AND  "
                +dataBasePatient_visit_date+"=" + "'" +visit_day +"'";
        Cursor cursor = db.rawQuery(query, null);




        if (cursor!= null && cursor.getCount() > 0)
        {
            cursor.moveToFirst();


            visitInformations.tc_no=cursor.getString(0);
            visitInformations.name=cursor.getString(1);
            visitInformations.surname=cursor.getString(2);
            visitInformations.visitType=cursor.getString(3);
            visitInformations.notes=cursor.getString(4);
            visitInformations.visitResult=cursor.getString(5);
            visitInformations.sign=cursor.getBlob(6);
            visitInformations.appointmentDate=cursor.getString(7);
            visitInformations.visitDate=cursor.getString(8);
            visitInformations.setMomentOfOpetaions(cursor.getString(9));



        }

        cursor.close();


        if(visitInformations!=null)
        {
            return visitInformations;
        }
        else
        {
            return null;
        }





    }

    public ArrayList<VisitInformations> getAllVisitsOfPatient(String tc_no)
    {

        SQLiteDatabase db = this.getReadableDatabase();
        VisitInformations  visitInformations;
        ArrayList<VisitInformations> AllvisitInformations= new ArrayList<>();
        String query = "SELECT * FROM " + dataBasePatient_table_name + " WHERE "+ dataBasePatient_TC_NO +" = "+ "'"+tc_no+"'";
        Cursor cursor = db.rawQuery(query, null);




        if (cursor!= null && cursor.getCount() > 0)
        {
            cursor.moveToFirst();
            do {
                {
                    visitInformations=new VisitInformations();


                    visitInformations.tc_no=cursor.getString(0);
                    visitInformations.name=cursor.getString(1);
                    visitInformations.surname=cursor.getString(2);
                    visitInformations.visitType=cursor.getString(3);
                    visitInformations.notes=cursor.getString(4);
                    visitInformations.visitResult=cursor.getString(5);
                    visitInformations.sign=cursor.getBlob(6);
                    visitInformations.appointmentDate=cursor.getString(7);
                    visitInformations.visitDate=cursor.getString(8);
                    visitInformations.setMomentOfOpetaions(cursor.getString(9));


                    AllvisitInformations.add(visitInformations);
                }
            }
            while (cursor.moveToNext());

        }

        cursor.close();


        if(AllvisitInformations!=null)
        {
            return AllvisitInformations;
        }
        else
        {
            return null;
        }
    }

    public ArrayList<VisitInformations> getAllCompletedVisitsOfPatient(String tc_no)
    {

        SQLiteDatabase db = this.getReadableDatabase();
        VisitInformations  visitInformations;
        ArrayList<VisitInformations> AllvisitInformations= new ArrayList<>();
        String query = "SELECT * FROM " + dataBasePatient_table_name + " WHERE "+ dataBasePatient_TC_NO +" = "+ "'"+tc_no+"'"+ " AND "+ dataBasePatient_visit_result +" = "+ "'"+VisitInformations.TAMAMLANDI+"'";
        Cursor cursor = db.rawQuery(query, null);





        if (cursor!= null && cursor.getCount() > 0)
        {
            cursor.moveToFirst();
            do {
                {
                    visitInformations=new VisitInformations();


                    visitInformations.tc_no=cursor.getString(0);
                    visitInformations.name=cursor.getString(1);
                    visitInformations.surname=cursor.getString(2);
                    visitInformations.visitType=cursor.getString(3);
                    visitInformations.notes=cursor.getString(4);
                    visitInformations.visitResult=cursor.getString(5);
                    visitInformations.sign=cursor.getBlob(6);
                    visitInformations.appointmentDate=cursor.getString(7);
                    visitInformations.visitDate=cursor.getString(8);
                    visitInformations.setMomentOfOpetaions(cursor.getString(9));


                    AllvisitInformations.add(visitInformations);
                }
            }
            while (cursor.moveToNext());

        }

        cursor.close();


        if(AllvisitInformations!=null)
        {
            return AllvisitInformations;
        }
        else
        {
            return null;
        }
    }

    public ArrayList<VisitInformations> getAllUnCompletedVisitsOfPatient(String tc_no)
    {

        SQLiteDatabase db = this.getReadableDatabase();
        VisitInformations  visitInformations;
        ArrayList<VisitInformations> AllvisitInformations= new ArrayList<>();
        String query = "SELECT * FROM " + dataBasePatient_table_name + " WHERE "+ dataBasePatient_TC_NO +" = "+ "'"+tc_no+"'"+ " AND "+ dataBasePatient_visit_result +" = "+ "'"+VisitInformations.TAMAMLANMADI+"'";
        Cursor cursor = db.rawQuery(query, null);




        if (cursor!= null && cursor.getCount() > 0)
        {
            cursor.moveToFirst();
            do {
                {
                    visitInformations=new VisitInformations();


                    visitInformations.tc_no=cursor.getString(0);
                    visitInformations.name=cursor.getString(1);
                    visitInformations.surname=cursor.getString(2);
                    visitInformations.visitType=cursor.getString(3);
                    visitInformations.notes=cursor.getString(4);
                    visitInformations.visitResult=cursor.getString(5);
                    visitInformations.sign=cursor.getBlob(6);
                    visitInformations.appointmentDate=cursor.getString(7);
                    visitInformations.visitDate=cursor.getString(8);
                    visitInformations.setMomentOfOpetaions(cursor.getString(9));


                    AllvisitInformations.add(visitInformations);
                }
            }
            while (cursor.moveToNext());

        }

        cursor.close();


        if(AllvisitInformations!=null)
        {
            return AllvisitInformations;
        }
        else
        {
            return null;
        }
    }



    public ArrayList<VisitInformations> getAllVisitsOfPatient()
    {

        SQLiteDatabase db = this.getReadableDatabase();
        VisitInformations  visitInformations;
        ArrayList<VisitInformations> AllvisitInformations= new ArrayList<>();
        String query = "SELECT * FROM " + dataBasePatient_table_name;
        Cursor cursor = db.rawQuery(query, null);




        if (cursor!= null && cursor.getCount() > 0)
        {
            cursor.moveToFirst();
            do {
                {
                    visitInformations=new VisitInformations();


                    visitInformations.tc_no=cursor.getString(0);
                    visitInformations.name=cursor.getString(1);
                    visitInformations.surname=cursor.getString(2);
                    visitInformations.visitType=cursor.getString(3);
                    visitInformations.notes=cursor.getString(4);
                    visitInformations.visitResult=cursor.getString(5);
                    visitInformations.sign=cursor.getBlob(6);
                    visitInformations.appointmentDate=cursor.getString(7);
                    visitInformations.visitDate=cursor.getString(8);
                    visitInformations.setMomentOfOpetaions(cursor.getString(9));


                    AllvisitInformations.add(visitInformations);
                }
            }
            while (cursor.moveToNext());

        }

        cursor.close();


        if(AllvisitInformations!=null)
        {
            return AllvisitInformations;
        }
        else
        {
            return null;
        }
    }

    public ArrayList<VisitInformations> getAllCompletedVisitsOfPatient()
    {

        SQLiteDatabase db = this.getReadableDatabase();
        VisitInformations  visitInformations;
        ArrayList<VisitInformations> AllvisitInformations= new ArrayList<>();
        String query = "SELECT * FROM " + dataBasePatient_table_name+ " WHERE "+ dataBasePatient_visit_result +" = "+ "'"+VisitInformations.TAMAMLANDI+"'";
        Cursor cursor = db.rawQuery(query, null);




        if (cursor!= null && cursor.getCount() > 0)
        {
            cursor.moveToFirst();
            do {
                {
                    visitInformations=new VisitInformations();


                    visitInformations.tc_no=cursor.getString(0);
                    visitInformations.name=cursor.getString(1);
                    visitInformations.surname=cursor.getString(2);
                    visitInformations.visitType=cursor.getString(3);
                    visitInformations.notes=cursor.getString(4);
                    visitInformations.visitResult=cursor.getString(5);
                    visitInformations.sign=cursor.getBlob(6);
                    visitInformations.appointmentDate=cursor.getString(7);
                    visitInformations.visitDate=cursor.getString(8);
                    visitInformations.setMomentOfOpetaions(cursor.getString(9));


                    AllvisitInformations.add(visitInformations);
                }
            }
            while (cursor.moveToNext());

        }

        cursor.close();


        if(AllvisitInformations!=null)
        {
            return AllvisitInformations;
        }
        else
        {
            return null;
        }
    }

    public ArrayList<VisitInformations> getAllUnCompletedVisitsOfPatient()
    {

        SQLiteDatabase db = this.getReadableDatabase();
        VisitInformations  visitInformations;
        ArrayList<VisitInformations> AllvisitInformations= new ArrayList<>();
        String query = "SELECT * FROM " + dataBasePatient_table_name+ " WHERE "+ dataBasePatient_visit_result +" = "+ "'"+VisitInformations.TAMAMLANMADI+"'";
        Cursor cursor = db.rawQuery(query, null);




        if (cursor!= null && cursor.getCount() > 0)
        {
            cursor.moveToFirst();
            do {
                {
                    visitInformations=new VisitInformations();


                    visitInformations.tc_no=cursor.getString(0);
                    visitInformations.name=cursor.getString(1);
                    visitInformations.surname=cursor.getString(2);
                    visitInformations.visitType=cursor.getString(3);
                    visitInformations.notes=cursor.getString(4);
                    visitInformations.visitResult=cursor.getString(5);
                    visitInformations.sign=cursor.getBlob(6);
                    visitInformations.appointmentDate=cursor.getString(7);
                    visitInformations.visitDate=cursor.getString(8);
                    visitInformations.setMomentOfOpetaions(cursor.getString(9));


                    AllvisitInformations.add(visitInformations);
                }
            }
            while (cursor.moveToNext());

        }

        cursor.close();


        if(AllvisitInformations!=null)
        {
            return AllvisitInformations;
        }
        else
        {
            return null;
        }
    }


    public boolean isContainVisit(VisitInformations visitInformations) {

        boolean sonuc=false;

        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + dataBasePatient_table_name + " WHERE "+dataBasePatient_TC_NO+"=" + "'" +visitInformations.tc_no +"'" + " AND  "
                +dataBasePatient_visit_date+"=" + "'" +visitInformations.visitDate +"'";
        Cursor cursor = db.rawQuery(query, null);


        if (cursor!= null && cursor.getCount() > 0)
        {
            cursor.moveToFirst();
            do {

                if((visitInformations.tc_no.matches(cursor.getString(0)))&&(visitInformations.visitDate.matches(cursor.getString(8))))

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


}
