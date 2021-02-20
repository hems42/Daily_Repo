package BroadCasts;

import BackUp.BackUpManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;
import org.apache.poi.ss.formula.functions.T;

public class BackUpBroadCast extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

       if(new BackUpManager().getBackUpDataBase())
       {
           Toast.makeText(context,"VeriTaban� Otomatik Olarak G�ncellendi!!",Toast.LENGTH_SHORT).show();
       }
    }
}
