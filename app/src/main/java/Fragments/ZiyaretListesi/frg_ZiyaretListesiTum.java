package Fragments.ZiyaretListesi;

import Patient.VisitInformations;
import android.content.Context;

import java.util.ArrayList;

public class frg_ZiyaretListesiTum extends Base_frg_ZiyaretListesi{
    public frg_ZiyaretListesiTum(Context context) {
        super(context, null);
    }

    @Override
    public void genel_guncelle() {

        ArrayList<VisitInformations> visitInformations=dbsqLiteOfVisit.getAllVisitsOfPatient();

        setAdapter(visitInformations,visitInformations.size());

    }

}
