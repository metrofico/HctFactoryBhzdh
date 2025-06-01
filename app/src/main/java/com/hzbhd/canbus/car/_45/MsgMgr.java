package com.hzbhd.canbus.car._45;

import android.content.Context;
import android.os.Bundle;
import android.util.SparseArray;
import com.hzbhd.canbus.adapter.util.HzbhdComponentName;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.OriginalCarDeviceUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralOriginalCarDeviceData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.OriginalCarDevicePageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MsgMgr extends AbstractMsgMgr {
   private int DoorIndoData0;
   int differentId;
   int eachId;
   byte[] mCanBusInfoByte;
   int[] mCanBusInfoInt;
   int[] mCarEpuInfo;
   Context mContext;
   private OriginalCarDevicePageUiSet mOriginalCarDevicePageUiSet;
   private SparseArray mOriginalDeviceDataArray;
   private UiMgr mUiMgr;

   private String getBtConnState(boolean var1) {
      return var1 ? this.mContext.getString(2131766466) : this.mContext.getString(2131766467);
   }

   private String getBtEpuState(boolean var1) {
      return var1 ? this.mContext.getString(2131766463) : this.mContext.getString(2131766464);
   }

   private String getBtPhoneState(boolean var1) {
      return var1 ? this.mContext.getString(2131766469) : this.mContext.getString(2131766470);
   }

   private String getFileNumber() {
      int var1 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[12], 4, 4);
      String var5 = " ";
      String var2;
      if (var1 == 15) {
         var2 = " ";
      } else {
         var2 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[12], 4, 4) + "";
      }

      String var3;
      if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[12], 0, 4) == 15) {
         var3 = " ";
      } else {
         var3 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[12], 0, 4) + "";
      }

      String var4;
      if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[13], 4, 4) == 15) {
         var4 = " ";
      } else {
         var4 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[13], 4, 4) + "";
      }

      if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[13], 0, 4) != 15) {
         var5 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[13], 0, 4) + "";
      }

      return var2 + var3 + var4 + var5;
   }

   private int getLsb(int var1) {
      return (var1 & '\uffff') >> 0 & 255;
   }

   private String getMediaTime() {
      int var1 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 4, 4);
      String var7 = "0";
      String var2;
      if (var1 == 15) {
         var2 = "0";
      } else {
         var2 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 4, 4) + "";
      }

      String var3;
      if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 0, 4) == 15) {
         var3 = "0";
      } else {
         var3 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 0, 4) + "";
      }

      String var4;
      if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 4, 4) == 15) {
         var4 = "0";
      } else {
         var4 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 4, 4) + "";
      }

      String var5;
      if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 0, 4) == 15) {
         var5 = "0";
      } else {
         var5 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 0, 4) + "";
      }

      String var6;
      if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[10], 4, 4) == 15) {
         var6 = "0";
      } else {
         var6 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[10], 4, 4) + "";
      }

      if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[10], 0, 4) != 15) {
         var7 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[10], 0, 4) + "";
      }

      return var2 + var3 + ":" + var4 + var5 + ":" + var6 + var7;
   }

   private String getMediaType(int var1) {
      if (var1 == 0) {
         return this.mContext.getString(2131766472);
      } else if (var1 == 1) {
         return this.mContext.getString(2131766473);
      } else {
         return var1 == 128 ? this.mContext.getString(2131766474) : "NO MEDIA";
      }
   }

   private int getMsb(int var1) {
      return (var1 & '\uffff') >> 8 & 255;
   }

   private int getMsbLsbResult(int var1, int var2) {
      return (var1 & 255) << 8 | var2 & 255;
   }

   private String getNowSongNumber() {
      int var1 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 4, 4);
      String var5 = " ";
      String var2;
      if (var1 == 15) {
         var2 = " ";
      } else {
         var2 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 4, 4) + "";
      }

      String var3;
      if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 4) == 15) {
         var3 = " ";
      } else {
         var3 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 4) + "";
      }

      String var4;
      if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 4, 4) == 15) {
         var4 = " ";
      } else {
         var4 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 4, 4) + "";
      }

      if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 0, 4) != 15) {
         var5 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 0, 4) + "";
      }

      return var2 + var3 + var4 + var5;
   }

   private OriginalCarDevicePageUiSet getOriginalCarDevicePageUiSet(Context var1) {
      if (this.mOriginalCarDevicePageUiSet == null) {
         this.mOriginalCarDevicePageUiSet = this.getUiMgr(var1).getOriginalCarDevicePageUiSet(var1);
      }

      return this.mOriginalCarDevicePageUiSet;
   }

   private List getOriginalDeviceCdDvdUsbUpdateEntityList(int[] var1) {
      String var2 = this.getPalyModel(var1[4]);
      String var4 = this.getNowSongNumber();
      String var3 = this.getMediaTime();
      String var7;
      if (var1[11] == 255) {
         var7 = "100%";
      } else {
         var7 = Integer.toHexString(var1[11]) + "%";
      }

      String var5 = this.getFileNumber();
      ArrayList var6 = new ArrayList();
      if (var2 != null) {
         var6.add(new OriginalCarDeviceUpdateEntity(0, var2));
      }

      if (var4 != null) {
         var6.add(new OriginalCarDeviceUpdateEntity(1, var4));
      }

      if (var3 != null) {
         var6.add(new OriginalCarDeviceUpdateEntity(2, var3));
      }

      if (var7 != null) {
         var6.add(new OriginalCarDeviceUpdateEntity(3, var7));
      }

      if (var5 != null) {
         var6.add(new OriginalCarDeviceUpdateEntity(4, var5));
      }

      return var6;
   }

   private String getPalyModel(int var1) {
      if (var1 == 0) {
         return this.mContext.getString(2131766483);
      } else if (var1 == 1) {
         return this.mContext.getString(2131766484);
      } else if (var1 == 2) {
         return this.mContext.getString(2131766485);
      } else if (var1 == 3) {
         return this.mContext.getString(2131766486);
      } else {
         return var1 == 4 ? this.mContext.getString(2131766487) : "NULL INFO";
      }
   }

   private UiMgr getUiMgr(Context var1) {
      if (this.mUiMgr == null) {
         this.mUiMgr = (UiMgr)UiMgrFactory.getCanUiMgr(var1);
      }

      return this.mUiMgr;
   }

   private String getUsbState(boolean var1) {
      return var1 ? this.mContext.getString(2131766460) : this.mContext.getString(2131766461);
   }

   private String getWordState(int var1) {
      if (var1 == 0) {
         return this.mContext.getString(2131766476);
      } else if (var1 == 1) {
         return this.mContext.getString(2131766477);
      } else if (var1 == 2) {
         return this.mContext.getString(2131766481);
      } else if (var1 == 5) {
         return this.mContext.getString(2131766478);
      } else if (var1 == 6) {
         return this.mContext.getString(2131766479);
      } else if (var1 == 7) {
         return this.mContext.getString(2131766480);
      } else {
         return var1 == 128 ? "NO DATA" : "NO INFO";
      }
   }

   private void initOriginalCarDevice() {
      (new Thread(this) {
         final MsgMgr this$0;

         {
            this.this$0 = var1;
         }

         public void run() {
            super.run();
            this.this$0.initOriginalDeviceDataArray();
         }
      }).start();
   }

   private void initOriginalDeviceDataArray() {
      ArrayList var2 = new ArrayList();
      var2.add(new OriginalCarDevicePageUiSet.Item("music_list", "_45_car_equipment7", (String)null));
      var2.add(new OriginalCarDevicePageUiSet.Item("music_list", "_45_car_equipment8", (String)null));
      var2.add(new OriginalCarDevicePageUiSet.Item("music_list", "_45_car_equipment9", (String)null));
      var2.add(new OriginalCarDevicePageUiSet.Item("music_list", "_45_car_equipment10", (String)null));
      var2.add(new OriginalCarDevicePageUiSet.Item("music_list", "_45_car_equipment11", (String)null));
      SparseArray var1 = new SparseArray();
      this.mOriginalDeviceDataArray = var1;
      var1.put(2, new OriginalDeviceData(var2, new String[]{"prev_disc", "left", "play_pause", "right", "next_disc"}));
   }

   private boolean is0x03CarInfoChange() {
      if (Arrays.equals(this.mCarEpuInfo, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.mCarEpuInfo = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private void set0x01WheelKeyInfo() {
      switch (this.mCanBusInfoInt[2]) {
         case 1:
            this.buttonKey(7);
            break;
         case 2:
            this.buttonKey(8);
            break;
         case 3:
            this.buttonKey(46);
            break;
         case 4:
            this.buttonKey(45);
            break;
         case 5:
            this.buttonKey(2);
         case 6:
         case 7:
         default:
            break;
         case 8:
            this.buttonKey(3);
            break;
         case 9:
            this.buttonKey(14);
            break;
         case 10:
            this.buttonKey(15);
      }

   }

   private void set0x02DoorInfo() {
      int var1 = this.DoorIndoData0;
      int var2 = this.mCanBusInfoInt[2];
      if (var1 != var2) {
         this.DoorIndoData0 = var2;
         GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(var2);
         GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
         GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
         GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
         GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
         GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
         this.updateDoorView(this.mContext);
      }
   }

   private void set0x03CarEquipmentInfo() {
      if (!this.is0x03CarInfoChange()) {
         ArrayList var1 = new ArrayList();
         var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_45_car_equipment"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_45_car_equipment1"), this.getUsbState(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]))));
         var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_45_car_equipment"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_45_car_equipment2"), this.getBtEpuState(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]))));
         var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_45_car_equipment"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_45_car_equipment3"), this.getBtConnState(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]))));
         var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_45_car_equipment"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_45_car_equipment4"), this.getBtPhoneState(DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[3]))));
         this.updateGeneralDriveData(var1);
         this.updateDriveDataActivity((Bundle)null);
      }
   }

   private void set0x04CarMediaInfo() {
      OriginalDeviceData var1 = (OriginalDeviceData)this.mOriginalDeviceDataArray.get(2);
      GeneralOriginalCarDeviceData.cdStatus = this.getMediaType(this.mCanBusInfoInt[2]);
      GeneralOriginalCarDeviceData.runningState = this.getWordState(this.mCanBusInfoInt[3]);
      GeneralOriginalCarDeviceData.mList = null;
      GeneralOriginalCarDeviceData.mList = this.getOriginalDeviceCdDvdUsbUpdateEntityList(this.mCanBusInfoInt);
      OriginalCarDevicePageUiSet var2 = this.getOriginalCarDevicePageUiSet(this.mContext);
      var2.setItems(var1.getItemList());
      var2.setRowBottomBtnAction(var1.getBottomBtns());
      Bundle var3 = new Bundle();
      var3.putBoolean("bundle_key_orinal_init_view", true);
      this.updateOriginalCarDeviceActivity(var3);
   }

   public void afterServiceNormalSetting(Context var1) {
      super.afterServiceNormalSetting(var1);
      this.differentId = this.getCurrentCanDifferentId();
      this.eachId = this.getCurrentEachCanId();
      this.mContext = var1;
   }

   public void auxInInfoChange() {
      super.auxInInfoChange();
      this.getUiMgr(this.mContext).send0x82MediaType(3);
   }

   public void btMusicInfoChange() {
      super.btMusicInfoChange();
      this.getUiMgr(this.mContext).send0x82MediaType(5);
   }

   public void buttonKey(int var1) {
      this.realKeyLongClick1(this.mContext, var1, this.mCanBusInfoInt[3]);
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoByte = var2;
      int[] var4 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfoInt = var4;
      int var3 = var4[1];
      if (var3 != 1) {
         if (var3 != 2) {
            if (var3 != 3) {
               if (var3 == 4) {
                  this.set0x04CarMediaInfo();
               }
            } else {
               this.set0x03CarEquipmentInfo();
            }
         } else {
            this.set0x02DoorInfo();
         }
      } else {
         this.set0x01WheelKeyInfo();
      }

   }

   public void currentVolumeInfoChange(int var1, boolean var2) {
      super.currentVolumeInfoChange(var1, var2);
      this.getUiMgr(this.mContext).send0x86VolControl(var1);
   }

   public void dateTimeRepCanbus(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, boolean var10, boolean var11, boolean var12, int var13) {
      super.dateTimeRepCanbus(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13);
      short var14;
      if (var10) {
         var14 = 128;
      } else {
         var14 = 0;
      }

      this.getUiMgr(this.mContext).send0x92TimeInfo(var14, var5, var6);
   }

   public void discInfoChange(byte var1, int var2, int var3, int var4, int var5, int var6, int var7, boolean var8, boolean var9, int var10, String var11, String var12, String var13) {
      super.discInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13);
      this.getUiMgr(this.mContext).send0x82MediaType(2);
      if (var9) {
         UiMgr var14 = this.getUiMgr(this.mContext);
         var3 = this.getMsb(var4);
         var5 = this.getLsb(var4);
         var4 = var2 / 60;
         var14.send0x84DiscInfo(0, 1, 0, 0, var3, var5, var4 / 60, var4 % 60, var2 % 60, 255, 0, 0);
      }

   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      this.getUiMgr(this.mContext).makeConnection();
      SelectCanTypeUtil.enableApp(var1, HzbhdComponentName.NewCanBusOriginalCarDeviceActivity);
      this.initOriginalCarDevice();
   }

   public void musicInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, byte var8, byte var9, byte var10, String var11, String var12, String var13, long var14, byte var16, int var17, boolean var18, long var19, String var21, String var22, String var23, boolean var24) {
      super.musicInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var16, var17, var18, var19, var21, var22, var23, var24);
      if (var1 == 9) {
         this.getUiMgr(this.mContext).send0x82MediaType(2);
      } else {
         this.getUiMgr(this.mContext).send0x82MediaType(4);
      }

   }

   public void radioInfoChange(int var1, String var2, String var3, String var4, int var5) {
      super.radioInfoChange(var1, var2, var3, var4, var5);
      this.getUiMgr(this.mContext).send0x82MediaType(1);
      byte var7;
      if (var2.equals("FM1")) {
         var7 = 1;
      } else if (!var2.equals("FM2") && (var2.equals("FM3") || !var2.equals("AM1") && !var2.equals("AM2"))) {
         var7 = 0;
      } else {
         var7 = 2;
      }

      int var6;
      if (!var2.equals("FM1") && !var2.equals("FM2") && !var2.equals("FM3")) {
         var6 = Integer.parseInt(var3);
      } else {
         var6 = Integer.parseInt(var3) * 100;
      }

      this.getUiMgr(this.mContext).send0x83RadioInfo(var7, this.getMsb(var6), this.getLsb(var6), var1, 0, 0);
   }

   public void sourceSwitchNoMediaInfoChange(boolean var1) {
      super.sourceSwitchNoMediaInfoChange(var1);
      this.getUiMgr(this.mContext).send0x82MediaType(0);
   }

   public void videoInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, String var8, byte var9, byte var10, String var11, String var12, String var13, int var14, byte var15, boolean var16, int var17) {
      super.videoInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var15, var16, var17);
      if (var1 == 9) {
         this.getUiMgr(this.mContext).send0x82MediaType(2);
      } else {
         this.getUiMgr(this.mContext).send0x82MediaType(4);
      }

   }

   private static class OriginalDeviceData {
      private final String[] bottomBtns;
      private final List list;

      public OriginalDeviceData(List var1) {
         this(var1, (String[])null);
      }

      public OriginalDeviceData(List var1, String[] var2) {
         this.list = var1;
         this.bottomBtns = var2;
      }

      public String[] getBottomBtns() {
         return this.bottomBtns;
      }

      public List getItemList() {
         return this.list;
      }
   }
}
