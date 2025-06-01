package com.microntek.controlsettings;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings.System;
import android.view.View;

public class canbus85air extends BaseActivity {
   private int cftype = 0;

   private void SetCmdkey(byte var1) {
      byte[] var2 = new byte[]{var1, 1};
      this.SendCanBusCmdData5AA5((byte)61, var2, 2);
      var2[1] = 0;
      this.SendCanBusCmdData5AA5((byte)61, var2, 2);
   }

   private void SetCmdkey(byte var1, byte var2) {
      this.SendCanBusCmdData5AA5((byte)61, new byte[]{var1, var2}, 2);
   }

   private void updateView(int var1, int var2) {
      this.findViewById(var1).setBackgroundResource(var2);
   }

   protected void ProcessData(byte[] var1) {
      if (var1[0] == 49) {
         this.updateView(2131165187, 2131099653);
         this.updateView(2131165188, 2131099653);
         this.updateView(2131165189, 2131099654);
         this.updateView(2131165190, 2131099654);
         this.updateView(2131165191, 2131099654);
         this.updateView(2131165192, 2131099654);
         this.updateView(2131165198, 2131099655);
         this.updateView(2131165199, 2131099656);
         this.updateView(2131165200, 2131099657);
         this.updateView(2131165201, 2131099658);
         this.updateView(2131165202, 2131099659);
         this.updateView(2131165203, 2131099660);
         this.updateView(2131165204, 2131099661);
         if ((var1[2] & 1) != 0) {
            this.updateView(2131165188, 2131099686);
         } else {
            this.updateView(2131165187, 2131099686);
         }

         if (var1[6] == 5) {
            this.updateView(2131165190, 2131099692);
         } else if (var1[6] == 4) {
            this.updateView(2131165192, 2131099692);
         } else if (var1[6] == 6) {
            this.updateView(2131165189, 2131099692);
         } else if (var1[6] == 3) {
            this.updateView(2131165191, 2131099692);
         }

         switch (var1[7] & 15) {
            case 1:
               this.updateView(2131165198, 2131099700);
               break;
            case 2:
               this.updateView(2131165199, 2131099702);
               break;
            case 3:
               this.updateView(2131165200, 2131099704);
               break;
            case 4:
               this.updateView(2131165201, 2131099706);
               break;
            case 5:
               this.updateView(2131165202, 2131099708);
               break;
            case 6:
               this.updateView(2131165203, 2131099710);
               break;
            case 7:
               this.updateView(2131165204, 2131099712);
         }

         if ((var1[5] & 1) != 0 && this.cftype == 1) {
            this.finish();
         }
      }

   }

   public void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.setContentView(2131230722);
   }

   protected void onNewIntent(Intent var1) {
      super.onNewIntent(var1);
      this.cftype = var1.getIntExtra("cftype", 0);
   }

   protected void onResume() {
      super.onResume();
      System.putInt(this.getContentResolver(), "com.microntek.hiworld.ari", 1);
   }

   protected void onStop() {
      super.onStop();
      System.putInt(this.getContentResolver(), "com.microntek.hiworld.ari", 0);
   }

   public void syncbutton(View var1) {
      int var2 = var1.getId();
      switch (var2) {
         case 2131165187:
            this.SetCmdkey((byte)2, (byte)0);
            break;
         case 2131165188:
            this.SetCmdkey((byte)2, (byte)1);
            break;
         case 2131165189:
            this.SetCmdkey((byte)9);
            break;
         case 2131165190:
            this.SetCmdkey((byte)24);
            break;
         case 2131165191:
            this.SetCmdkey((byte)10);
            break;
         case 2131165192:
            this.SetCmdkey((byte)23);
            break;
         default:
            switch (var2) {
               case 2131165198:
                  this.SetCmdkey((byte)25, (byte)1);
                  break;
               case 2131165199:
                  this.SetCmdkey((byte)25, (byte)2);
                  break;
               case 2131165200:
                  this.SetCmdkey((byte)25, (byte)3);
                  break;
               case 2131165201:
                  this.SetCmdkey((byte)25, (byte)4);
                  break;
               case 2131165202:
                  this.SetCmdkey((byte)25, (byte)5);
                  break;
               case 2131165203:
                  this.SetCmdkey((byte)25, (byte)6);
                  break;
               case 2131165204:
                  this.SetCmdkey((byte)25, (byte)7);
            }
      }

   }
}
