package Observation;

public class RecyclerRandev� extends ObservableRecList {

   private boolean degisiklik;

    public boolean isDegisiklik() {
        return degisiklik;



    }

    public void setDegisiklik(boolean degisiklik) {
        this.degisiklik = degisiklik;
        degisiklikKontrolEt();
    }

    public void degisiklikKontrolEt()
    {
        if(degisiklik)
        {
            haberVer();
        }
    }
}
