package com.hzbhd.canbus.car._324;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.util.DataHandleUtils;

public class Id324AuxVideoActivity extends AppCompatActivity {
   private VideoInfoView videoInfoView;

   private void sendXy(int var1, int var2, int var3) {
      CanbusMsgSender.sendMsg(new byte[]{22, -104, (byte)var3, (byte)DataHandleUtils.getMsb(var1), (byte)DataHandleUtils.getLsb(var1), (byte)DataHandleUtils.getMsb(var2), (byte)DataHandleUtils.getLsb(var2), 0});
   }

   protected void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.setContentView(2131558663);
      VideoInfoView var2 = (VideoInfoView)this.findViewById(2131363754);
      this.videoInfoView = var2;
      var2.startVideoView();
      this.findViewById(2131363802).setOnTouchListener(new View.OnTouchListener(this) {
         final Id324AuxVideoActivity this$0;

         {
            this.this$0 = var1;
         }

         public boolean onTouch(View var1, MotionEvent var2) {
            if (var2.getAction() == 0) {
               this.this$0.sendXy((int)var2.getX(), (int)var2.getY(), 1);
               return true;
            } else if (var2.getAction() == 1) {
               this.this$0.sendXy((int)var2.getX(), (int)var2.getY(), 0);
               return true;
            } else {
               return false;
            }
         }
      });
   }

   protected void onDestroy() {
      super.onDestroy();
      this.videoInfoView.stopVideoView();
   }

   protected void onResume() {
      super.onResume();
      this.videoInfoView.startVideoView();
   }
}
