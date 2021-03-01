package Fragments.RandevuListesi;

import Patient.VisitInformations;
import android.content.Context;

public class frg_YakinRandevuler extends  Base_frg_RandevuListesi{
    public frg_YakinRandevuler(Context context) {
        super(context, VisitInformations.DIGER_ONCE);
        tag_Randevu=VisitInformations.TAG_RANDEVU_YAKIN;


    }
}
