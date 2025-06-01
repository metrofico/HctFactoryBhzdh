package com.hzbhd.canbus.car_cus._439.activity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.car_cus._439.drive.util.NotifyDriveDate;
import com.hzbhd.canbus.car_cus._439.drive.viiew.DriveDateView;

public class ID439DriveDataActivity extends AppCompatActivity {
   DriveDateView repair;

   public void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.setContentView(2131558664);
      this.repair = (DriveDateView)this.findViewById(2131363091);
      NotifyDriveDate.getInstance().register(this.repair);
      CanbusMsgSender.sendMsg(new byte[]{22, -112, 33, 0});
   }

   protected void onDestroy() {
      super.onDestroy();
      NotifyDriveDate.getInstance().unregister(this.repair);
   }
}
