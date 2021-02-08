package Observation;

public class RandevuListesiEkrani implements ObserverRecList {

    private String adapterAdi;

    public RandevuListesiEkrani(String adapterAdi) {
        this.adapterAdi = adapterAdi;
    }

    @Override
    public void update(ObservableRecList observableRecList) {

        RecyclerRandevü adapter=(RecyclerRandevü) observableRecList;


        System.out.println(adapterAdi+"  :listesi güncellendi");
    }
}
