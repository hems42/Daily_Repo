package Observation;

import java.util.ArrayList;
import java.util.List;

public abstract class ObservableRecList {

    private List<ObserverRecList> observerRecListList;

    public ObservableRecList() {
        observerRecListList = new ArrayList<>();
    }



    public void ekle(ObserverRecList observerRecList)
    {
        observerRecListList.add(observerRecList);
    }



    public void cikar(ObserverRecList observerRecList)
    {
        observerRecListList.remove(observerRecList);
    }


    public void haberVer()
    {
        for(ObserverRecList observerRecList : observerRecListList)
        {
            observerRecList.update(this);
        }
    }

}
