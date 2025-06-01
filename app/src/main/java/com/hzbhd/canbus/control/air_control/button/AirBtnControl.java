package com.hzbhd.canbus.control.air_control.button;

import com.android.internal.util.ArrayUtils;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.control.air_control.AbstractAirControl;
import com.hzbhd.canbus.ui_datas.GeneralAirData;

public class AirBtnControl extends AbstractAirControl {
   private String[][] mAirBtnActions;
   private OnAirBtnClickListener[] mOnAirBtnClickListeners;

   public AirBtnControl(String[][] var1, OnAirBtnClickListener[] var2) {
      this.mAirBtnActions = var1;
      this.mOnAirBtnClickListeners = var2;
   }

   public void action(String var1) {
      if (!ArrayUtils.isEmpty(this.mOnAirBtnClickListeners)) {
         var1.hashCode();
         int var2 = -1;
         switch (var1) {
            case "air.ac.on":
               var2 = 0;
               break;
            case "ac.open":
               var2 = 1;
               break;
            case "air.in.out.cycle.off":
               var2 = 2;
               break;
            case "air.ac.off":
               var2 = 3;
               break;
            case "ac.close":
               var2 = 4;
               break;
            case "air.in.out.cycle.on":
               var2 = 5;
         }

         label95: {
            label94: {
               label93: {
                  switch (var2) {
                     case 0:
                        if (GeneralAirData.ac) {
                           return;
                        }
                        break label93;
                     case 1:
                        if (GeneralAirData.power) {
                           return;
                        }
                        break label94;
                     case 2:
                        if (GeneralAirData.in_out_cycle) {
                           return;
                        }
                        break;
                     case 3:
                        if (!GeneralAirData.ac) {
                           return;
                        }
                        break label93;
                     case 4:
                        if (!GeneralAirData.power) {
                           return;
                        }
                        break label94;
                     case 5:
                        if (!GeneralAirData.in_out_cycle) {
                           return;
                        }
                        break;
                     default:
                        break label95;
                  }

                  var1 = "in_out_cycle";
                  break label95;
               }

               var1 = "ac";
               break label95;
            }

            var1 = "power";
         }

         if (this.mAirBtnActions != null) {
            for(var2 = 0; var2 < this.mAirBtnActions.length; ++var2) {
               int var3 = 0;

               while(true) {
                  String[] var4 = this.mAirBtnActions[var2];
                  if (var3 >= var4.length) {
                     break;
                  }

                  if (var4[var3].equals(var1)) {
                     OnAirBtnClickListener var5 = this.mOnAirBtnClickListeners[var2];
                     if (var5 != null) {
                        var5.onClickItem(var3);
                     }

                     return;
                  }

                  ++var3;
               }
            }
         }

      }
   }
}
