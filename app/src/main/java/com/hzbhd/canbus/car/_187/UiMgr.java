package com.hzbhd.canbus.car._187;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.AirActivity;
import com.hzbhd.canbus.activity.AmplifierActivity;
import com.hzbhd.canbus.interfaces.OnAmplifierPositionListener;
import com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.AmplifierPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;

public class UiMgr extends AbstractUiMgr {
   private AirActivity mActivity;
   private Context mContext;
   private MsgMgr mMsgMgr;

   public UiMgr(Context var1) {
      this.mContext = var1;
      if (this.mMsgMgr == null) {
         this.mMsgMgr = (MsgMgr)MsgMgrFactory.getCanMsgMgr(var1);
      }

      SettingPageUiSet var2 = this.getSettingUiSet(var1);
      var2.setOnSettingItemSelectListener(new OnSettingItemSelectListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickItem(int var1, int var2, int var3) {
            if (var1 == 0) {
               if (var2 == 1) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 39, (byte)var3});
               } else if (var2 == 3) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 41, (byte)var3});
               }
            }

         }
      });
      var2.setOnSettingItemSeekbarSelectListener(new OnSettingItemSeekbarSelectListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickItem(int var1, int var2, int var3) {
            if (var1 == 0) {
               int var4 = 49;
               var1 = 33;
               if (var2 == 0) {
                  var2 = var3 - this.this$0.mMsgMgr.mAmpAslValueNow;
                  if (var2 <= 0) {
                     var1 = var2;
                  }

                  if (var1 < 0) {
                     var1 = var4;
                  }

                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 38, (byte)var1});
                  this.this$0.mMsgMgr.mAmpAslValueNow = var3;
               } else if (var2 == 2) {
                  var2 = var3 - this.this$0.mMsgMgr.mAmpSurroundValueNow;
                  if (var2 <= 0) {
                     var1 = var2;
                  }

                  if (var1 >= 0) {
                     var4 = var1;
                  }

                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 40, (byte)var4});
                  this.this$0.mMsgMgr.mAmpSurroundValueNow = var3;
               }
            }

         }
      });
      AmplifierPageUiSet var3 = this.getAmplifierPageUiSet(var1);
      var3.setOnAmplifierPositionListener(new OnAmplifierPositionListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void position(AmplifierActivity.AmplifierPosition var1, int var2) {
            if (var1 == AmplifierActivity.AmplifierPosition.LEFT_RIGHT) {
               this.this$0.sendAmplifierCommand(36, var2, GeneralAmplifierData.leftRight);
            } else if (var1 == AmplifierActivity.AmplifierPosition.FRONT_REAR) {
               this.this$0.sendAmplifierCommand(37, var2, GeneralAmplifierData.frontRear);
            }

         }
      });
      var3.setOnAmplifierSeekBarListener(new OnAmplifierSeekBarListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void progress(AmplifierActivity.AmplifierBand var1, int var2) {
            if (var1 == AmplifierActivity.AmplifierBand.VOLUME) {
               this.this$0.sendAmplifierCommand(33, var2, GeneralAmplifierData.volume);
            } else if (var1 == AmplifierActivity.AmplifierBand.BASS) {
               this.this$0.sendAmplifierCommand(34, var2, GeneralAmplifierData.bandBass);
            } else if (var1 == AmplifierActivity.AmplifierBand.TREBLE) {
               this.this$0.sendAmplifierCommand(35, var2, GeneralAmplifierData.bandTreble);
            }

         }
      });
   }

   private void sendAmplifierCommand(int var1, int var2, int var3) {
      var2 -= var3;
      byte var4;
      if (var2 > 0) {
         var4 = 33;
      } else {
         if (var2 >= 0) {
            return;
         }

         var4 = 49;
      }

      CanbusMsgSender.sendMsg(new byte[]{22, -125, (byte)var1, (byte)var4});
   }

   public void updateUiByDifferentCar(Context var1) {
      super.updateUiByDifferentCar(var1);
   }
}
