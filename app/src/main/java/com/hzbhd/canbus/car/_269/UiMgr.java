package com.hzbhd.canbus.car._269;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.AmplifierActivity;
import com.hzbhd.canbus.interfaces.OnAmplifierPositionListener;
import com.hzbhd.canbus.interfaces.OnAmplifierResetPositionListener;
import com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.AmplifierPageUiSet;
import com.hzbhd.canbus.util.SharePreUtil;
import java.util.Timer;
import java.util.TimerTask;

public class UiMgr extends AbstractUiMgr {
   private Context mContext;
   private boolean mIsClickReset = false;
   private Timer mTimer;
   private MsgMgr msgMgr;

   public UiMgr(Context var1) {
      this.msgMgr = (MsgMgr)MsgMgrFactory.getCanMsgMgr(var1);
      this.mContext = var1;
      AmplifierPageUiSet var2 = this.getAmplifierPageUiSet(var1);
      var2.setOnAmplifierSeekBarListener(new OnAmplifierSeekBarListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void progress(AmplifierActivity.AmplifierBand var1, int var2) {
            int var3 = null.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[var1.ordinal()];
            if (var3 != 1) {
               if (var3 != 2) {
                  if (var3 != 3) {
                     if (var3 == 4) {
                        this.this$0.mIsClickReset = false;
                        SharePreUtil.setIntValue(this.this$0.mContext, "__269_SAVE_AMP_VOL", var2);
                        GeneralAmplifierData.volume = var2;
                        this.this$0.sendAmpCmds();
                     }
                  } else {
                     this.this$0.mIsClickReset = false;
                     SharePreUtil.setIntValue(this.this$0.mContext, "__269_SAVE_AMP_MID", var2);
                     GeneralAmplifierData.bandMiddle = var2;
                     this.this$0.sendAmpCmds();
                  }
               } else {
                  this.this$0.mIsClickReset = false;
                  SharePreUtil.setIntValue(this.this$0.mContext, "__269_SAVE_AMP_TRE", var2);
                  GeneralAmplifierData.bandTreble = var2;
                  this.this$0.sendAmpCmds();
               }
            } else {
               this.this$0.mIsClickReset = false;
               SharePreUtil.setIntValue(this.this$0.mContext, "__269_SAVE_AMP_BASS", var2);
               GeneralAmplifierData.bandBass = var2;
               this.this$0.sendAmpCmds();
            }

         }
      });
      var2.setOnAmplifierPositionListener(new OnAmplifierPositionListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void position(AmplifierActivity.AmplifierPosition var1, int var2) {
            int var3 = null.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierPosition[var1.ordinal()];
            if (var3 != 1) {
               if (var3 == 2) {
                  if (this.this$0.mIsClickReset) {
                     this.this$0.mIsClickReset = false;
                  } else {
                     SharePreUtil.setIntValue(this.this$0.mContext, "__269_SAVE_AMP_LR", var2);
                     GeneralAmplifierData.leftRight = var2;
                     this.this$0.sendAmpCmds();
                  }
               }
            } else if (!this.this$0.mIsClickReset) {
               SharePreUtil.setIntValue(this.this$0.mContext, "__269_SAVE_AMP_FR", var2);
               GeneralAmplifierData.frontRear = var2;
               this.this$0.sendAmpCmds();
            }

         }
      });
      var2.setOnAmplifierResetPositionListener(new OnAmplifierResetPositionListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void resetClick() {
            this.this$0.ampReset();
            this.this$0.sendAmpCmds();
            this.this$0.mIsClickReset = true;
            this.this$0.msgMgr.updateAmpUi((Context)null);
         }
      });
      this.intFirstUi();
   }

   private void ampReset() {
      GeneralAmplifierData.bandBass = 9;
      GeneralAmplifierData.bandMiddle = 9;
      GeneralAmplifierData.bandTreble = 9;
      GeneralAmplifierData.volume = 8;
      GeneralAmplifierData.frontRear = 0;
      GeneralAmplifierData.leftRight = 0;
      SharePreUtil.setIntValue(this.mContext, "__269_SAVE_AMP_VOL", GeneralAmplifierData.volume);
      SharePreUtil.setIntValue(this.mContext, "__269_SAVE_AMP_BASS", GeneralAmplifierData.bandBass);
      SharePreUtil.setIntValue(this.mContext, "__269_SAVE_AMP_MID", GeneralAmplifierData.bandMiddle);
      SharePreUtil.setIntValue(this.mContext, "__269_SAVE_AMP_TRE", GeneralAmplifierData.bandTreble);
      SharePreUtil.setIntValue(this.mContext, "__269_SAVE_AMP_FR", GeneralAmplifierData.frontRear);
      SharePreUtil.setIntValue(this.mContext, "__269_SAVE_AMP_LR", GeneralAmplifierData.leftRight);
   }

   private void intFirstUi() {
      TimerTask var2 = new TimerTask(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void run() {
            try {
               if (SharePreUtil.getIntValue(this.this$0.mContext, "CAN_269_SAVE_IS_AMP_FIRST", 1) == 1) {
                  this.this$0.ampReset();
                  this.this$0.msgMgr.updateAmpUi(this.this$0.mContext);
                  SharePreUtil.setIntValue(this.this$0.mContext, "CAN_269_SAVE_IS_AMP_FIRST", 0);
               }
            } catch (Exception var2) {
            }

            this.this$0.mTimer.cancel();
         }
      };
      Timer var1 = new Timer();
      this.mTimer = var1;
      var1.schedule(var2, 1500L, 500L);
   }

   private void sendAmpCmds() {
      CanbusMsgSender.sendMsg(new byte[]{22, -93, (byte)((byte)GeneralAmplifierData.volume + 22), (byte)((byte)GeneralAmplifierData.frontRear + 10), (byte)((byte)GeneralAmplifierData.leftRight + 10), (byte)((byte)GeneralAmplifierData.bandBass + 1), (byte)((byte)GeneralAmplifierData.bandMiddle + 1), (byte)((byte)GeneralAmplifierData.bandTreble + 1)});
   }
}
