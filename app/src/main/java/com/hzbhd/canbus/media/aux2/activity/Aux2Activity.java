package com.hzbhd.canbus.media.aux2.activity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.canCustom.canBaseUtil.StatusUtil;
import com.hzbhd.canbus.media.aux2.action.ActionCallback;
import com.hzbhd.canbus.media.aux2.action.ActionTag;
import com.hzbhd.canbus.media.aux2.manager.Aux2Manager;
import com.hzbhd.canbus.media.aux2.view.Aux2CameraSurfaceView;
import com.hzbhd.commontools.SourceConstantsDef;
import com.hzbhd.proxy.sourcemanager.SourceManager;

public class Aux2Activity extends AppCompatActivity {
   public static final String SCALE_X_TAG = "0xFFFF";
   public static final String SCALE_Y_TAG = "0xDDDD";
   ActionCallback actionCallback = new ActionCallback(this) {
      final Aux2Activity this$0;

      {
         this.this$0 = var1;
      }

      public void actionDo(ActionTag.TAG var1, String var2) {
         if (var1.equals(ActionTag.TAG.EXIT)) {
            this.this$0.finish();
         }

      }
   };

   private void initView(int var1, int var2) {
      Aux2CameraSurfaceView var7 = (Aux2CameraSurfaceView)this.findViewById(2131363758);
      int var6 = Resources.getSystem().getDisplayMetrics().heightPixels;
      int var5 = Resources.getSystem().getDisplayMetrics().widthPixels;
      float var4 = (float)var1 * 0.1F;
      float var3 = (float)var2 * 0.1F;
      LinearLayout.LayoutParams var8;
      if (var6 > var5) {
         var4 = (float)var5 / var4;
         var8 = (LinearLayout.LayoutParams)var7.getLayoutParams();
         var8.height = (int)(var3 * var4);
         var8.width = var5;
         var7.setLayoutParams(var8);
      } else {
         var3 = (float)var6 / var3;
         var8 = (LinearLayout.LayoutParams)var7.getLayoutParams();
         var8.height = var6;
         var8.width = (int)(var4 * var3);
         var7.setLayoutParams(var8);
      }

   }

   public void close() {
      SourceManager.getInstance().releaseAudioChannel(SourceConstantsDef.SOURCE_ID.AUX2, SourceConstantsDef.DISP_ID.disp_main);
      Aux2Manager.getInstance().unRegisterExitListener();
   }

   protected void onCreate(Bundle var1) {
      super.onCreate(var1);
      StatusUtil.fullScreen(this, true);
      this.setContentView(2131558656);
      Intent var2 = this.getIntent();
      this.initView(var2.getIntExtra("0xFFFF", 1280), var2.getIntExtra("0xDDDD", 720));
      this.open();
   }

   protected void onDestroy() {
      super.onDestroy();
      this.close();
      CanbusMsgSender.sendMsg(new byte[]{22, -110, 0, 0});
   }

   protected void onResume() {
      super.onResume();
      CanbusMsgSender.sendMsg(new byte[]{22, -110, 1, 0});
   }

   public void open() {
      SourceManager.getInstance().requestAudioChannel(SourceConstantsDef.SOURCE_ID.AUX2, SourceConstantsDef.DISP_ID.disp_main, (Bundle)null);
      Aux2Manager.getInstance().registerExitListener(this.actionCallback);
   }
}
