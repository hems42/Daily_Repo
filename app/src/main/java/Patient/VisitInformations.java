package Patient;

import Utils.CustomTime;

public class VisitInformations extends Patient {


    public String visitType;
    public String notes;
    public byte[] sign;
    public String visitDate;
    public String appointmentDate;
    public String visitResult;

    public static  String TAMAMLANDI="TAMAMLANDI";
    public static  String TAMAMLANMADI="TAMAMLANMADI";
    public static  String TUM_ZIYARETLER="T�M Z�YARETLER";

    public static  String BUGUN= CustomTime.getDate();
    public static  String YARIN= CustomTime.getTomorrow();
    public static  String DUN= CustomTime.getLastDay();
    public static  String DIGER_SONRA= CustomTime.getAnyDay(2);
    public static  String DIGER_ONCE= CustomTime.getAnyDay(-2);

    public static  String RANDEVU_TUM= "T�M RANDEV�LER";
    public static  String RANDEVU_BUGUN= "BUG�NK� RANDEV�LER";


    public static final String PAZARTESI = "PAZARTES�";
    public static final String SALI = "SALI";
    public static final String CARSAMBA = "�AR�AMBA";
    public static final String PERSEMBE = "PER�EMBE";
    public static final String CUMA = "CUMA";
    public static final String CUMARTESI = "CUMARTES�";
    public static final String PAZAR = "PAZAR";



    public VisitInformations() {
    }

    public VisitInformations(String visitType, String appointmentDate) {
        this.visitType = visitType;
        this.appointmentDate = appointmentDate;
    }

    public VisitInformations(String notes, byte[] sign, String visitDate, String visitResult) {
        this.notes = notes;
        this.sign = sign;
        this.visitDate = visitDate;
        this.visitResult = visitResult;
    }

    public String getMomentOfOpetaions() {
        return momentOfOpetaions;
    }

    public void setMomentOfOpetaions(String momentOfOpetaions) {
        this.momentOfOpetaions = momentOfOpetaions;
    }

    private String momentOfOpetaions;
}
