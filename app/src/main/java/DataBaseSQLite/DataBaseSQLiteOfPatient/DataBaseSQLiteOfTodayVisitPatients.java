package DataBaseSQLite.DataBaseSQLiteOfPatient;

import android.content.Context;

public class DataBaseSQLiteOfTodayVisitPatients extends BaseDataBaseSQLiteOfPatient {



    public DataBaseSQLiteOfTodayVisitPatients(Context context) {
        super(context, DataBaseSQLiteCommonExpressions.today_visit_patient);
    }



}
