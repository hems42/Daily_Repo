package Observation;

import androidx.recyclerview.widget.RecyclerView;

public class ObserverRandevuLer extends RecyclerView.AdapterDataObserver {

    @Override
    public void onItemRangeChanged(int positionStart, int itemCount) {
        super.onItemRangeChanged(positionStart, itemCount);

        System.out.println("güncellendi observer randevüler tarafýndan");
    }
}
