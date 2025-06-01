package com.hzbhd.canbus.car._417;

import android.content.Context;
import android.os.Bundle;
import com.hzbhd.canbus.entity.PanoramicBtnUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.SystemButton;
import java.util.ArrayList;

public class MsgMgr extends AbstractMsgMgr {
   int differentId;
   int eachId;
   byte[] mCanBusInfoByte;
   int[] mCanBusInfoInt;
   Context mContext;
   private UiMgr mUiMgr;
   private SystemButton systemButton;

   private UiMgr getUiMgr(Context var1) {
      if (this.mUiMgr == null) {
         this.mUiMgr = (UiMgr)UiMgrFactory.getCanUiMgr(var1);
      }

      return this.mUiMgr;
   }

   private void setPanoramic0xE8() {
      Context var4 = this.mContext;
      int var1 = this.mCanBusInfoInt[5];
      boolean var3 = false;
      boolean var2;
      if (var1 == 1) {
         var2 = true;
      } else {
         var2 = false;
      }

      this.forceReverse(var4, var2);
      ArrayList var5 = new ArrayList();
      if (this.mCanBusInfoInt[3] == 4) {
         var2 = true;
      } else {
         var2 = false;
      }

      var5.add(new PanoramicBtnUpdateEntity(0, var2));
      if (this.mCanBusInfoInt[3] == 5) {
         var2 = true;
      } else {
         var2 = false;
      }

      var5.add(new PanoramicBtnUpdateEntity(1, var2));
      if (this.mCanBusInfoInt[3] == 6) {
         var2 = true;
      } else {
         var2 = false;
      }

      var5.add(new PanoramicBtnUpdateEntity(2, var2));
      var2 = var3;
      if (this.mCanBusInfoInt[3] == 7) {
         var2 = true;
      }

      var5.add(new PanoramicBtnUpdateEntity(3, var2));
      GeneralParkData.dataList = var5;
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void setSwc0x11() {
      int var1 = this.mCanBusInfoInt[4];
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 8) {
                  if (var1 != 9) {
                     if (var1 == 11) {
                        this.buttonKey(2);
                     }
                  } else {
                     this.buttonKey(46);
                  }
               } else {
                  this.buttonKey(45);
               }
            } else {
               this.buttonKey(8);
            }
         } else {
            this.buttonKey(7);
         }
      } else {
         this.buttonKey(0);
      }

   }

   private void setVersion0xF0() {
      this.updateVersionInfo(this.mContext, this.getVersionStr(this.mCanBusInfoByte));
   }

   public void afterServiceNormalSetting(Context var1) {
      super.afterServiceNormalSetting(var1);
      this.differentId = this.getCurrentCanDifferentId();
      this.eachId = this.getCurrentEachCanId();
      this.mContext = var1;
   }

   public void buttonKey(int var1) {
      this.realKeyLongClick1(this.mContext, var1, this.mCanBusInfoInt[5]);
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoByte = var2;
      int[] var4 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfoInt = var4;
      int var3 = var4[1];
      if (var3 != 17) {
         if (var3 != 232) {
            if (var3 == 240) {
               this.setVersion0xF0();
            }
         } else {
            this.setPanoramic0xE8();
         }
      } else {
         this.setSwc0x11();
      }

   }

   public void hideButton() {
      this.runOnUi(new AbstractMsgMgr.CallBackInterface(this) {
         final MsgMgr this$0;

         {
            this.this$0 = var1;
         }

         public void callback() {
            if (this.this$0.systemButton == null) {
               this.this$0.systemButton = new SystemButton(this.this$0.getActivity(), "P", new SystemButton.PanoramaListener(this) {
                  final <undefinedtype> this$1;

                  {
                     this.this$1 = var1;
                  }

                  public void clickEvent() {
                     this.this$1.this$0.getUiMgr(this.this$1.this$0.mContext).sendPanoramicInfo0xFD(1);
                  }
               });
            }

            this.this$0.systemButton.hide();
         }
      });
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
   }

   public void showButton() {
      this.runOnUi(new AbstractMsgMgr.CallBackInterface(this) {
         final MsgMgr this$0;

         {
            this.this$0 = var1;
         }

         public void callback() {
            if (this.this$0.systemButton == null) {
               this.this$0.systemButton = new SystemButton(this.this$0.getActivity(), "P", new SystemButton.PanoramaListener(this) {
                  final <undefinedtype> this$1;

                  {
                     this.this$1 = var1;
                  }

                  public void clickEvent() {
                     this.this$1.this$0.getUiMgr(this.this$1.this$0.mContext).sendPanoramicInfo0xFD(1);
                  }
               });
            }

            this.this$0.systemButton.show();
         }
      });
   }

   public void updateSettings(int var1, int var2, int var3) {
      ArrayList var4 = new ArrayList();
      var4.add(new SettingUpdateEntity(var1, var2, var3));
      this.updateGeneralSettingData(var4);
      this.updateSettingActivity((Bundle)null);
   }
}
