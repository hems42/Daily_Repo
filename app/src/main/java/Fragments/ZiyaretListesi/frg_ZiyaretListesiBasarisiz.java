package Fragments.ZiyaretListesi;

import Patient.VisitInformations;
import android.content.Context;

import java.util.ArrayList;

public class frg_ZiyaretListesiBasarisiz extends Base_frg_ZiyaretListesi {


    public frg_ZiyaretListesiBasarisiz(Context context) {
        super(context,VisitInformations.TAMAMLANMADI);
    }

    @Override
    public void genel_guncelle() {

        ArrayList<VisitInformations> visitInformations=dbsqLiteOfVisit.getAllUnCompletedVisitsOfPatient();

        setAdapter(visitInformations,visitInformations.size());

    }

}
