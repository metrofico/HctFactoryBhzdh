package com.hzbhd.canbus.car._31;

import android.content.Context;
import android.util.Log;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.AmplifierActivity;
import com.hzbhd.canbus.interfaces.OnAmplifierPositionListener;
import com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener;
import com.hzbhd.canbus.interfaces.OnBackCameraStatusListener;
import com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.AmplifierPageUiSet;
import com.hzbhd.canbus.ui_set.ParkPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import java.util.ArrayList;

public class UiMgr extends AbstractUiMgr {
   static final String _31_SAVE_LANGUAGE = "_31_SAVE_LANGUAGE";
   private byte[] mAmplifierCmd = new byte[]{22, -57, 0, 0, 0, 0, 0, 0};
   private MsgMgr mMsgMgr;
   private MsgMgr msgMgr;
   private int nowAmplFrontRear = 0;
   private int nowAmplLeftRight = 0;

   public UiMgr(Context var1) {
      this.msgMgr = (MsgMgr)MsgMgrFactory.getCanMsgMgr(var1);
      SettingPageUiSet var3 = this.getSettingUiSet(var1);
      ParkPageUiSet var2 = this.getParkPageUiSet(var1);
      var3.setOnSettingItemSelectListener(new OnSettingItemSelectListener(this, var1) {
         final UiMgr this$0;
         final Context val$context;

         {
            this.this$0 = var1;
            this.val$context = var2;
         }

         public void onClickItem(int var1, int var2, int var3) {
            if (var1 == 0) {
               CanbusMsgSender.sendMsg(new byte[]{22, -124, (byte)(var2 + 2), (byte)var3});
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 2, (byte)(var3 + 1)});
               SharePreUtil.setIntValue(this.val$context, "_31_SAVE_LANGUAGE", var3);
               this.this$0.msgMgr.setLanguage_recNull(this.val$context);
            }

         }
      });
      AmplifierPageUiSet var4 = this.getAmplifierPageUiSet(var1);
      var4.setOnAmplifierPositionListener(new OnAmplifierPositionListener(this, var1) {
         final UiMgr this$0;
         final Context val$context;

         {
            this.this$0 = var1;
            this.val$context = var2;
         }

         public void position(AmplifierActivity.AmplifierPosition var1, int var2) {
            int var3 = null.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierPosition[var1.ordinal()];
            if (var3 != 1) {
               if (var3 == 2) {
                  this.this$0.nowAmplLeftRight = var2;
                  this.this$0.getMsgMgr(this.val$context).setAmplifier(this.this$0.nowAmplFrontRear, this.this$0.nowAmplLeftRight);
               }
            } else {
               this.this$0.nowAmplFrontRear = var2;
               this.this$0.getMsgMgr(this.val$context).setAmplifier(this.this$0.nowAmplFrontRear, this.this$0.nowAmplLeftRight);
            }

            this.this$0.sendAmplifierCommand(true);
            this.this$0.msgMgr.saveAmplifierData(this.val$context);
         }
      });
      var4.setOnAmplifierSeekBarListener(new OnAmplifierSeekBarListener(this, var1) {
         final UiMgr this$0;
         final Context val$context;

         {
            this.this$0 = var1;
            this.val$context = var2;
         }

         public void progress(AmplifierActivity.AmplifierBand var1, int var2) {
            int var3 = null.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[var1.ordinal()];
            if (var3 != 1) {
               if (var3 != 2) {
                  if (var3 == 3) {
                     GeneralAmplifierData.bandBass = var2;
                  }
               } else {
                  GeneralAmplifierData.bandMiddle = var2;
               }
            } else {
               GeneralAmplifierData.bandTreble = var2;
            }

            this.this$0.sendAmplifierCommand(true);
            this.this$0.msgMgr.saveAmplifierData(this.val$context);
         }
      });
      var2.setOnPanoramicItemClickListener(new OnPanoramicItemClickListener(this, var1) {
         final UiMgr this$0;
         final Context val$context;

         {
            this.this$0 = var1;
            this.val$context = var2;
         }

         public void onClickItem(int var1) {
            boolean var4 = SharePreUtil.getBoolValue(this.val$context, "_31_is_back_camera", false);
            boolean var3 = CommUtil.isMiscReverse();
            byte var2;
            if (!var4 && !var3) {
               var2 = 5;
            } else {
               var2 = 1;
            }

            Log.d("cwh", "rev = " + var2);
            CanbusMsgSender.sendMsg(new byte[]{22, -124, 1, (byte)(var1 + var2)});
         }
      });
      var2.setOnBackCameraStatusListener(new OnBackCameraStatusListener(this, var1, var2) {
         final UiMgr this$0;
         final Context val$context;
         final ParkPageUiSet val$parkPageUiSet;

         {
            this.this$0 = var1;
            this.val$context = var2;
            this.val$parkPageUiSet = var3;
         }

         public void addViewToWindows() {
            boolean var3 = SharePreUtil.getBoolValue(this.val$context, "_31_is_back_camera", false);
            boolean var1 = SharePreUtil.getBoolValue(this.val$context, "_31_is_panoramic", false);
            boolean var4 = CommUtil.isMiscReverse();
            boolean var2 = CommUtil.isPanoramic(this.val$context);
            if (this.val$parkPageUiSet.getPanoramicBtnList() != null) {
               this.val$parkPageUiSet.getPanoramicBtnList().clear();
               Log.d("cwh", " list = " + this.val$parkPageUiSet.getPanoramicBtnList());
               ArrayList var5 = new ArrayList();
               if (!var4 && !var3) {
                  if (var2 && var1) {
                     var5.add(new ParkPageUiSet.Bean(1, "", "hyundai_front_all"));
                     var5.add(new ParkPageUiSet.Bean(1, "", "hyundai_only_front"));
                     var5.add(new ParkPageUiSet.Bean(1, "", "hyundai_front_left_front"));
                     var5.add(new ParkPageUiSet.Bean(1, "", "hyundai_front_right_front"));
                  }
               } else {
                  var5.add(new ParkPageUiSet.Bean(1, "", "hyundai_rear_all"));
                  var5.add(new ParkPageUiSet.Bean(1, "", "hyundai_only_rear"));
                  var5.add(new ParkPageUiSet.Bean(1, "", "hyundai_rear_left_rear"));
                  var5.add(new ParkPageUiSet.Bean(1, "", "hyundai_rear_right_rear"));
               }

               this.val$parkPageUiSet.setPanoramicBtnList(var5);
            }

         }
      });
   }

   private MsgMgr getMsgMgr(Context var1) {
      if (this.mMsgMgr == null) {
         this.mMsgMgr = (MsgMgr)MsgMgrFactory.getCanMsgMgr(var1);
      }

      return this.mMsgMgr;
   }

   public void sendAmplifierCommand(boolean var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, -57, (byte)(var1 ^ 1), (byte)(10 - GeneralAmplifierData.frontRear), (byte)(GeneralAmplifierData.leftRight + 10), (byte)GeneralAmplifierData.bandTreble, (byte)GeneralAmplifierData.bandMiddle, (byte)GeneralAmplifierData.bandBass});
   }
}
