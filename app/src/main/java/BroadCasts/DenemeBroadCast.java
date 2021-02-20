package BroadCasts;

import BackUp.BackUpManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class DenemeBroadCast extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {


        Toast.makeText(context,"BroadCast Denem Çalýþtý!!   "+intent.getAction()+ "   "+intent.getDataString(),Toast.LENGTH_SHORT).show();

        System.out.println("action adý: "+intent.getAction());
    }
}
