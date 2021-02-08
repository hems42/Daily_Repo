package Manager;

import DataBaseSQLite.DBSQLiteOfAppointment;
import DataBaseSQLite.DBSQLiteOfPersonelInformations;
import DataBaseSQLite.DBSQLiteOfVisit;
import DataBaseSQLite.DataBaseSQLiteOfPatient.DBSQLiteOfAllPatients;
import Patient.Patient;
import android.app.Activity;
import android.content.Context;

public class PatientManager implements  IThePatient{

    private Context context;
    private Activity activity;
    private DBSQLiteOfAllPatients dblitePatient;
    private DBSQLiteOfAppointment dbliteAppoinment;
    private DBSQLiteOfPersonelInformations dblitePersonelInfo;
    private DBSQLiteOfVisit dbliteVisit;

    public PatientManager(Context context) {
        this.context = context;
        activity= (Activity) context;

        dblitePatient= new DBSQLiteOfAllPatients(context);
        dblitePatient.onCreate(dblitePatient.getWritableDatabase());

        dbliteAppoinment= new DBSQLiteOfAppointment(context);
        dbliteAppoinment.onCreate(dbliteAppoinment.getWritableDatabase());

        dbliteVisit= new DBSQLiteOfVisit(context);
        dbliteVisit.onCreate(dbliteVisit.getWritableDatabase());

        dblitePersonelInfo= new DBSQLiteOfPersonelInformations(context);
        dblitePersonelInfo.onCreate(dblitePersonelInfo.getWritableDatabase());

    }

    @Override
    public boolean addPatient(Patient patient) {
        return false;
    }

    @Override
    public boolean deletePatient(Patient patient) {

        boolean sonuc=true;



        if(dblitePatient.isContainPatient(patient))
        {
            if(!dblitePatient.deletePatient(patient))
            {

                sonuc=false;

            }
        }
        else
        {
            sonuc=false;
        }


        if(dbliteAppoinment.getAllAppointmentsOfPatient(patient.tc_no).size()>0)
        {
            if(!dbliteAppoinment.deleteAllAppointmentOfPatient(patient.tc_no))
            {
                sonuc=false;

            }
        }



        if(dbliteVisit.getAllVisitsOfPatient(patient.tc_no).size()>0)
        {
            if(!dbliteVisit.deletePatientAllVisit(patient))
            {
                sonuc=false;

            }

        }


        if(dblitePersonelInfo.getAllPatientInformations(patient.tc_no).size()>0)
        {
            if(!dblitePersonelInfo.deleteAllPatientInformation(patient.tc_no))
            {
                sonuc=false;

            }
        }



        return sonuc;
    }

    @Override
    public boolean updatePatient(Patient patient_old, Patient patient_new) {
        return false;
    }

    @Override
    public boolean getPatient(String tc_no) {
        return false;
    }


    /*
    * validate
    * addPatient
    * deletePatient
    * selectPatient
    * updatePatient
    *
    * addVisit
    * addAppointment
    * addPersonelInformation
    * addLocation
    * add
    * */
}
