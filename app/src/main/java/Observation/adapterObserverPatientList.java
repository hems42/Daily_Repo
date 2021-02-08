package Observation;

import Fragments.HastaListesi.Base_frg_HastaListesi;
import androidx.recyclerview.widget.RecyclerView;

public class adapterObserverPatientList extends RecyclerView.AdapterDataObserver {


    private Base_frg_HastaListesi base_frg_hastaListesi;

    public adapterObserverPatientList(Base_frg_HastaListesi base_frg_hastaListesi) {
        this.base_frg_hastaListesi = base_frg_hastaListesi;
    }


    @Override
    public void onChanged() {


        System.out.println("de�i�me protokol�...");
        base_frg_hastaListesi.genel_guncelle();

    }
}
