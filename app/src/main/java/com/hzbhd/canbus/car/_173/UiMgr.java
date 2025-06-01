package com.hzbhd.canbus.car._173;

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
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.AmplifierPageUiSet;
import com.hzbhd.canbus.ui_set.ParkPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import java.util.ArrayList;

public class UiMgr extends AbstractUiMgr {
   static final String _173_AMPLIFIER_BALANCE = "_173_amplifier_balance";
   static final String _173_AMPLIFIER_BASS = "_173_amplifier_bass";
   static final String _173_AMPLIFIER_FADE = "_173_amplifier_fade";
   static final String _173_AMPLIFIER_MIDDLE = "_173_amplifier_middle";
   static final String _173_AMPLIFIER_TREBLE = "_173_amplifier_treble";
   static final String _173_SAVE_LANGUAGE = "_173_SAVE_LANGUAGE";
   private int data1;
   private int data2;
   private int data3;
   private int data4;
   private int data5;
   private MsgMgr msgMgr;

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
               SharePreUtil.setIntValue(this.val$context, "_173_SAVE_LANGUAGE", var3);
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
                  this.this$0.data2 = var2 + 10;
                  SharePreUtil.setIntValue(this.val$context, "_173_amplifier_balance", this.this$0.data2);
                  this.this$0.msgMgr.initAmplifierData(this.val$context);
               }
            } else {
               this.this$0.data1 = 10 - var2;
               SharePreUtil.setIntValue(this.val$context, "_173_amplifier_fade", this.this$0.data1);
               this.this$0.msgMgr.initAmplifierData(this.val$context);
            }

            CanbusMsgSender.sendMsg(new byte[]{22, -57, 0, (byte)this.this$0.data1, (byte)this.this$0.data2, (byte)this.this$0.data3, (byte)this.this$0.data4, (byte)this.this$0.data5});
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
            int var7 = null.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[var1.ordinal()];
            byte var3;
            byte var4;
            if (var7 != 1) {
               byte var5;
               if (var7 != 2) {
                  if (var7 == 3) {
                     var5 = (byte)this.this$0.data1;
                     byte var6 = (byte)this.this$0.data2;
                     var4 = (byte)this.this$0.data3;
                     var3 = (byte)this.this$0.data4;
                     var2 += 10;
                     CanbusMsgSender.sendMsg(new byte[]{22, -57, 0, var5, var6, var4, var3, (byte)var2});
                     this.this$0.data5 = var2;
                     SharePreUtil.setIntValue(this.val$context, "_173_amplifier_bass", this.this$0.data5);
                     this.this$0.msgMgr.initAmplifierData(this.val$context);
                  }
               } else {
                  var5 = (byte)this.this$0.data1;
                  var4 = (byte)this.this$0.data2;
                  var3 = (byte)this.this$0.data3;
                  var2 += 10;
                  CanbusMsgSender.sendMsg(new byte[]{22, -57, 0, var5, var4, var3, (byte)var2, (byte)this.this$0.data5});
                  this.this$0.data4 = var2;
                  SharePreUtil.setIntValue(this.val$context, "_173_amplifier_middle", this.this$0.data4);
                  this.this$0.msgMgr.initAmplifierData(this.val$context);
               }
            } else {
               var4 = (byte)this.this$0.data1;
               var3 = (byte)this.this$0.data2;
               var2 += 10;
               CanbusMsgSender.sendMsg(new byte[]{22, -57, 0, var4, var3, (byte)var2, (byte)this.this$0.data4, (byte)this.this$0.data5});
               this.this$0.data3 = var2;
               SharePreUtil.setIntValue(this.val$context, "_173_amplifier_treble", this.this$0.data3);
               this.this$0.msgMgr.initAmplifierData(this.val$context);
            }

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
            boolean var4 = SharePreUtil.getBoolValue(this.val$context, "_173_is_back_camera", false);
            boolean var3 = CommUtil.isBackCamera(this.val$context);
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
            boolean var1 = SharePreUtil.getBoolValue(this.val$context, "_173_is_back_camera", false);
            boolean var4 = SharePreUtil.getBoolValue(this.val$context, "_173_is_panoramic", false);
            boolean var3 = CommUtil.isBackCamera(this.val$context);
            boolean var2 = CommUtil.isPanoramic(this.val$context);
            if (this.val$parkPageUiSet.getPanoramicBtnList() != null) {
               this.val$parkPageUiSet.getPanoramicBtnList().clear();
               Log.d("cwh", " list = " + this.val$parkPageUiSet.getPanoramicBtnList());
               ArrayList var5 = new ArrayList();
               if (!var3 && !var1) {
                  if (var2 && var4) {
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
}
