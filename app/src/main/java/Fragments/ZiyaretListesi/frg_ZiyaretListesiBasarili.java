package Fragments.ZiyaretListesi;

import Patient.VisitInformations;
import android.content.Context;

import java.util.ArrayList;

public class frg_ZiyaretListesiBasarili extends Base_frg_ZiyaretListesi {


    public frg_ZiyaretListesiBasarili(Context context) {
        super(context,VisitInformations.TAMAMLANDI);
    }

    @Override
    public void genel_guncelle() {

        ArrayList<VisitInformations> visitInformations=dbsqLiteOfVisit.getAllCompletedVisitsOfPatient();

        setAdapter(visitInformations,visitInformations.size());

    }

}
