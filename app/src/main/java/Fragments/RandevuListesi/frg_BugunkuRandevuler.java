package Fragments.RandevuListesi;

import Patient.VisitInformations;
import Utils.CustomTime;
import android.content.Context;

public class frg_BugunkuRandevuler extends Base_frg_RandevuListesi{
    public frg_BugunkuRandevuler(Context context) {
        super(context, VisitInformations.RANDEVU_BUGUN);
        tag_Randevu=VisitInformations.TAG_RANDEVU_BUGUN;

}
}
