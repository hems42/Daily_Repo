package Manager;

import Patient.Patient;

public interface IThePatient {

    boolean addPatient(Patient patient);
    boolean deletePatient(Patient patient);
    boolean updatePatient(Patient patient_old,Patient patient_new);
    boolean getPatient(String tc_no);
}
