package com.hzbhd.canbus.car._30;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import com.android.internal.util.ArrayUtils;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.AirActivity;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.entity.TireUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralHybirdData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_datas.GeneralTireData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.SystemUtil;
import com.hzbhd.canbus.util.TimerUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.commontools.SourceConstantsDef;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.TimerTask;

public class MsgMgr extends AbstractMsgMgr {
   static final boolean $assertionsDisabled = false;
   static final String SHARE_30_LANGUAGE = "share_30_language";
   private static final String TAG = "_1030_MsgMgr";
   static final int[] THEME_COLOR_INDEXES = new int[]{3, 6, 9, 12, 15, 18};
   private boolean BatteryStatus0001 = false;
   private boolean BatteryStatus0010 = false;
   private boolean BatteryStatus0100 = false;
   private boolean BatteryStatus1000 = false;
   private boolean Leak0001 = false;
   private boolean Leak0010 = false;
   private boolean Leak0100 = false;
   private boolean Leak1000 = false;
   List MTire0 = new ArrayList();
   List MTire1 = new ArrayList();
   List MTire2 = new ArrayList();
   List MTire3 = new ArrayList();
   private int SensePressure0001 = 0;
   private int SensePressure0010 = 0;
   private int SensePressure0100 = 0;
   private int SensePressure1000 = 0;
   private int SenseTempture0001 = 0;
   private int SenseTempture0010 = 0;
   private int SenseTempture0100 = 0;
   private int SenseTempture1000 = 0;
   private String TirePressure0001 = "NO INFO";
   private String TirePressure0010 = "NO INFO";
   private String TirePressure0100 = "NO INFO";
   private String TirePressure1000 = "NO INFO";
   private boolean TireSense0001 = false;
   private boolean TireSense0010 = false;
   private boolean TireSense0100 = false;
   private boolean TireSense1000 = false;
   private String TireTempture0001 = "NO INFO";
   private String TireTempture0010 = "NO INFO";
   private String TireTempture0100 = "NO INFO";
   private String TireTempture1000 = "NO INFO";
   DecimalFormat decimalFormat;
   private boolean is15Carnival;
   private boolean is1819KiaSportage;
   private boolean is18SonataHybrid;
   private boolean is1920Tucson;
   private boolean is19K3NewEnergy;
   private boolean is19K5NewEnergy;
   private boolean is19KiaK3;
   private boolean is19Lafesta;
   private boolean is19Santafe;
   private boolean is20EncinoElectrical;
   private boolean is20K3;
   private boolean isK4;
   private boolean isKx5Top;
   int mCanId;
   private SparseArray mCanbusDataArray;
   private byte[] mCanbusInfoByte;
   private int[] mCanbusInfoInt;
   private Context mContext;
   int mDifferent;
   private HashMap mDriveItemIndeHashMap;
   int mEachId;
   Handler mHandler = new Handler(Looper.getMainLooper());
   private boolean mIsBackOpen;
   private boolean mIsFrontOpen;
   private boolean mIsLeftFrontOpen;
   private boolean mIsLeftRearOpen;
   private boolean mIsRightFrontOpen;
   private boolean mIsRightRearOpen;
   private SparseArray mParserArray;
   private SeatStatusView mSeatStatusView;
   private HashMap mSettingItemIndeHashMap;
   private Page mSettingPage;
   private UiMgr mUiMgr;
   Runnable mVersionRequestRunnable = new MsgMgr$$ExternalSyntheticLambda0();
   private int mVolume = 28;

   private DriverUpdateEntity getDriveUpdateEntity(String var1) {
      if (this.mDriveItemIndeHashMap.containsKey(var1)) {
         return (DriverUpdateEntity)this.mDriveItemIndeHashMap.get(var1);
      } else {
         this.mDriveItemIndeHashMap.put(var1, new DriverUpdateEntity(-1, -1, "set_default"));
         return this.getDriveUpdateEntity(var1);
      }
   }

   private SettingUpdateEntity getSettingUpdateEntity(String var1) {
      if (this.mSettingItemIndeHashMap.containsKey(var1)) {
         return (SettingUpdateEntity)this.mSettingItemIndeHashMap.get(var1);
      } else {
         this.mSettingItemIndeHashMap.put(var1, new SettingUpdateEntity(-1, -1, (Object)null));
         return this.getSettingUpdateEntity(var1);
      }
   }

   private int getThemeColorIndex(int var1) {
      int var2 = 0;

      while(true) {
         int[] var3 = THEME_COLOR_INDEXES;
         if (var2 >= var3.length) {
            return 0;
         }

         if (var3[var2] == var1) {
            return var2;
         }

         ++var2;
      }
   }

   private int[] getTime(int var1) {
      int var2 = var1 / 60;
      return new int[]{var2 / 60, var2 % 60, var1 % 60};
   }

   private TireUpdateEntity getTireEntity(int var1, String var2, String var3, boolean var4, boolean var5, int var6, int var7, boolean var8) {
      byte var9;
      String var12;
      if (var4) {
         var12 = this.mContext.getResources().getString(2131762215);
         var9 = 1;
      } else {
         var12 = this.mContext.getResources().getString(2131762207);
         var9 = 0;
      }

      String var13;
      if (var5) {
         var13 = this.mContext.getResources().getString(2131762200);
      } else {
         var13 = this.mContext.getResources().getString(2131762201);
      }

      Log.d("Lai", "报警" + var9 + "电池" + var13);
      Log.d("Lai", "SenseTempture" + var6);
      String var10 = "";
      String var11;
      if (var6 == 0) {
         var11 = this.mContext.getResources().getString(2131762206);
      } else if (var6 == 3) {
         var11 = this.mContext.getResources().getString(2131762208);
      } else if (var6 == 4) {
         var11 = this.mContext.getResources().getString(2131762209);
         var9 = 1;
      } else {
         var11 = "";
      }

      Log.d("Lai", "SensePressure" + var7);
      if (var7 == 0) {
         var10 = this.mContext.getResources().getString(2131762206);
      } else if (var7 == 3) {
         var10 = this.mContext.getResources().getString(2131762208);
      } else {
         label47: {
            if (var7 == 4) {
               var10 = this.mContext.getResources().getString(2131762209);
            } else {
               if (var7 != 1) {
                  if (var7 == 2) {
                     var10 = this.mContext.getResources().getString(2131762211);
                  }
                  break label47;
               }

               var10 = this.mContext.getResources().getString(2131762210);
            }

            var9 = 1;
         }
      }

      String var14;
      if (var8) {
         var14 = this.mContext.getResources().getString(2131762203);
         var9 = 1;
      } else {
         var14 = this.mContext.getResources().getString(2131762204);
      }

      return new TireUpdateEntity(var1, var9, new String[]{var2, var3, var12, var13, var11, var10, var14});
   }

   private UiMgr getUiMgr(Context var1) {
      if (this.mUiMgr == null) {
         this.mUiMgr = (UiMgr)UiMgrFactory.getCanUiMgr(var1);
      }

      return this.mUiMgr;
   }

   private void initAmplifier(Context var1) {
      if (var1 != null) {
         this.getAmplifierData(var1, this.getCanId(), UiMgrFactory.getCanUiMgr(var1).getAmplifierPageUiSet(var1));
      }

      TimerUtil var2 = new TimerUtil();
      var2.startTimer(new TimerTask(this, var2) {
         final Iterator iterator;
         final MsgMgr this$0;
         final TimerUtil val$util;

         {
            this.this$0 = var1;
            this.val$util = var2;
            byte var3 = (byte)var1.mVolume;
            byte[] var4 = new byte[]{22, 7, (byte)(GeneralAmplifierData.frontRear + 7), (byte)(GeneralAmplifierData.leftRight + 7)};
            byte[] var5 = new byte[]{22, 8, (byte)GeneralAmplifierData.bandBass, (byte)GeneralAmplifierData.bandMiddle, (byte)GeneralAmplifierData.bandTreble};
            this.iterator = Arrays.stream(new byte[][]{{22, -127, 1, 1}, {22, 5, var3}, var4, var5}).iterator();
         }

         public void run() {
            if (this.iterator.hasNext()) {
               CanbusMsgSender.sendMsg((byte[])this.iterator.next());
            } else {
               this.val$util.stopTimer();
            }

         }
      }, 0L, 100L);
   }

   private void initDriveItem(Context var1) {
      this.mDriveItemIndeHashMap = new HashMap();
      List var5 = this.getUiMgr(var1).getDriverDataPageUiSet(var1).getList();

      for(int var2 = 0; var2 < var5.size(); ++var2) {
         List var4 = ((DriverDataPageUiSet.Page)var5.get(var2)).getItemList();

         for(int var3 = 0; var3 < var4.size(); ++var3) {
            this.mDriveItemIndeHashMap.put(((DriverDataPageUiSet.Page.Item)var4.get(var3)).getTitleSrn(), new DriverUpdateEntity(var2, var3, "set_default"));
         }
      }

   }

   private void initParser(Context var1) {
      SparseArray var2 = new SparseArray();
      this.mParserArray = var2;
      var2.put(1, new Parser(this, "0x01 室外温度信息", var1) {
         String temperature;
         final MsgMgr this$0;
         final Context val$context;

         {
            this.this$0 = var1;
            this.val$context = var3;
         }

         // $FF: synthetic method
         void lambda$setOnParseListeners$0$com_hzbhd_canbus_car__30_MsgMgr$2(Context var1) {
            if (this.this$0.mCanbusInfoInt[2] != 255) {
               if (this.this$0.mCanbusInfoInt[2] == 128) {
                  this.temperature = "-";
               } else {
                  this.temperature = (this.this$0.mCanbusInfoInt[2] & 127) + this.this$0.getTempUnitC(var1);
                  if (DataHandleUtils.getBoolBit7(this.this$0.mCanbusInfoInt[2])) {
                     this.temperature = "-" + this.temperature;
                  }
               }
            }

         }

         // $FF: synthetic method
         void lambda$setOnParseListeners$1$com_hzbhd_canbus_car__30_MsgMgr$2(Context var1) {
            if (this.this$0.mCanbusInfoInt[3] != 255) {
               this.temperature = this.this$0.mCanbusInfoInt[3] + this.this$0.getTempUnitF(var1);
            }

         }

         public void parse(int var1) {
            MsgMgr var2 = this.this$0;
            if (var2.isDataChange(var2.mCanbusInfoInt)) {
               this.temperature = " ";
               super.parse(var1);
               if (this.temperature.equals("-")) {
                  return;
               }

               this.this$0.updateOutDoorTemp(this.val$context, this.temperature);
            }

         }

         public OnParseListener[] setOnParseListeners() {
            return new OnParseListener[]{new MsgMgr$2$$ExternalSyntheticLambda0(this, this.val$context), new MsgMgr$2$$ExternalSyntheticLambda1(this, this.val$context)};
         }
      });
      this.mParserArray.append(2, new Parser(this, "0x02 方控面板按键信息", var1) {
         final MsgMgr this$0;
         final Context val$context;

         {
            this.this$0 = var1;
            this.val$context = var3;
         }

         // $FF: synthetic method
         void lambda$setOnParseListeners$0$com_hzbhd_canbus_car__30_MsgMgr$3(Context var1) {
            int var2 = this.this$0.mCanbusInfoInt[2];
            if (var2 != 0) {
               if (var2 != 132) {
                  if (var2 != 133) {
                     switch (var2) {
                        case 16:
                           this.this$0.realKeyLongClick1(var1, 3);
                           break;
                        case 17:
                           this.this$0.realKeyLongClick1(var1, 2);
                           break;
                        case 18:
                           this.this$0.realKeyLongClick1(var1, 46);
                           break;
                        case 19:
                           this.this$0.realKeyLongClick1(var1, 45);
                           break;
                        case 20:
                           this.this$0.realKeyLongClick1(var1, 7);
                           break;
                        case 21:
                           this.this$0.realKeyLongClick1(var1, 8);
                           break;
                        case 22:
                           this.this$0.realKeyLongClick1(var1, 14);
                           break;
                        case 23:
                           this.this$0.realKeyLongClick1(var1, 15);
                           break;
                        case 24:
                           this.this$0.realKeyLongClick1(var1, 134);
                           break;
                        case 25:
                           this.this$0.realKeyClick3_1(var1, 7);
                           break;
                        case 26:
                           this.this$0.realKeyClick3_1(var1, 8);
                           break;
                        case 27:
                           if (this.this$0.isKx5Top()) {
                              this.this$0.realKeyLongClick1(var1, 59);
                           } else if (this.this$0.isK4()) {
                              this.this$0.realKeyLongClick1(var1, 129);
                           } else {
                              this.this$0.realKeyLongClick1(var1, 129);
                           }
                           break;
                        case 28:
                           if (this.this$0.isKx5Top()) {
                              this.this$0.realKeyLongClick1(var1, 128);
                           } else {
                              this.this$0.realKeyLongClick1(var1, 59);
                           }
                           break;
                        case 29:
                           if (this.this$0.isK4()) {
                              this.this$0.realKeyLongClick1(var1, 21);
                           } else {
                              this.this$0.realKeyLongClick1(var1, 14);
                           }
                           break;
                        case 30:
                           if (this.this$0.isKx5Top()) {
                              this.this$0.realKeyLongClick1(var1, 185);
                           } else if (this.this$0.isK4()) {
                              this.this$0.realKeyLongClick1(var1, 14);
                           } else {
                              this.this$0.realKeyLongClick1(var1, 152);
                           }
                           break;
                        case 31:
                           if (this.this$0.isK4()) {
                              this.this$0.realKeyLongClick1(var1, 20);
                           } else {
                              this.this$0.realKeyLongClick1(var1, 21);
                           }
                           break;
                        case 32:
                           this.this$0.realKeyLongClick1(var1, 20);
                           break;
                        case 33:
                           this.this$0.realKeyLongClick1(var1, 128);
                           break;
                        case 34:
                           this.this$0.realKeyLongClick1(var1, 128);
                           break;
                        case 35:
                           this.this$0.realKeyLongClick1(var1, 128);
                           break;
                        case 36:
                           if (this.this$0.isK4()) {
                              this.this$0.realKeyLongClick1(var1, 152);
                           } else {
                              this.this$0.realKeyLongClick1(var1, 58);
                           }
                           break;
                        case 37:
                           this.this$0.realKeyLongClick1(var1, 49);
                           break;
                        case 38:
                           this.this$0.realKeyClick3_2(var1, 48);
                           break;
                        case 39:
                           this.this$0.realKeyClick3_2(var1, 47);
                           break;
                        case 40:
                           this.this$0.realKeyLongClick1(var1, 185);
                           break;
                        case 41:
                           if (this.this$0.isKx5Top()) {
                              this.this$0.realKeyLongClick1(var1, 129);
                           } else {
                              this.this$0.realKeyLongClick1(var1, 52);
                           }
                           break;
                        case 42:
                           this.this$0.realKeyLongClick1(var1, 76);
                           break;
                        case 43:
                           this.this$0.realKeyLongClick1(var1, 77);
                           break;
                        case 44:
                           this.this$0.realKeyLongClick1(var1, 58);
                           break;
                        case 45:
                           this.this$0.realKeyLongClick1(var1, 52);
                           break;
                        case 46:
                           this.this$0.realKeyLongClick1(var1, 50);
                           break;
                        case 47:
                           this.this$0.realKeyLongClick1(var1, 45);
                           break;
                        case 48:
                           this.this$0.realKeyLongClick1(var1, 187);
                           break;
                        case 49:
                           this.this$0.realKeyLongClick1(var1, 128);
                           break;
                        case 50:
                           this.this$0.realKeyLongClick1(var1, 185);
                           break;
                        case 51:
                           this.this$0.realKeyLongClick1(var1, 14);
                           break;
                        case 52:
                           this.this$0.realKeyLongClick1(var1, 46);
                           break;
                        case 53:
                           this.this$0.realKeyLongClick1(var1, 39);
                     }
                  } else {
                     this.this$0.realKeyClick3_1(var1, 8);
                  }
               } else {
                  this.this$0.realKeyClick3_1(var1, 7);
               }
            } else {
               this.this$0.realKeyLongClick1(var1, 0);
            }

         }

         public void parse(int var1) {
            if (this.this$0.mCanbusInfoInt.length > 3) {
               MsgMgr var2 = this.this$0;
               if (var2.isDataChange(var2.mCanbusInfoInt)) {
                  super.parse(var1);
               }
            }

         }

         public OnParseListener[] setOnParseListeners() {
            return new OnParseListener[]{new MsgMgr$3$$ExternalSyntheticLambda0(this, this.val$context)};
         }
      });
      this.mParserArray.append(3, new Parser(this, "0x03 空调信息", var1) {
         private final int[] canbusInfo;
         private int[] frontDatas;
         private int[] rearDatas;
         final MsgMgr this$0;
         final Context val$context;

         {
            this.this$0 = var1;
            this.val$context = var3;
            this.canbusInfo = new int[8];
         }

         private String getAirTemperature(Context var1, int var2) {
            if (var2 == 0) {
               return "Low";
            } else if (var2 == 30) {
               return "High";
            } else if (var2 == 255) {
               return "--";
            } else if (var2 > 0 && var2 < 30) {
               return (float)var2 * 0.5F + 17.0F + this.this$0.getTempUnitC(var1);
            } else {
               return var2 >= 62 && var2 <= 90 ? var2 + this.this$0.getTempUnitF(var1) : "";
            }
         }

         private int getAirWhat() {
            boolean var3 = this.this$0.is15Carnival();
            short var2 = 1001;
            short var1 = var2;
            if (var3) {
               int[] var4 = this.canbusInfo;
               if (this.isFrontDataChange(var4[2], var4[3], var4[4])) {
                  var1 = var2;
               } else {
                  var4 = this.canbusInfo;
                  if (this.isRearDataChange(var4[6], var4[7])) {
                     var1 = 1002;
                  } else {
                     var1 = 0;
                  }
               }
            }

            return var1;
         }

         private boolean isFrontDataChange(int... var1) {
            if (Arrays.equals(this.frontDatas, var1)) {
               return false;
            } else {
               this.frontDatas = Arrays.copyOf(var1, var1.length);
               return true;
            }
         }

         private boolean isRearDataChange(int... var1) {
            if (Arrays.equals(this.rearDatas, var1)) {
               return false;
            } else {
               this.rearDatas = Arrays.copyOf(var1, var1.length);
               return true;
            }
         }

         // $FF: synthetic method
         void lambda$setOnParseListeners$0$com_hzbhd_canbus_car__30_MsgMgr$4() {
            this.canbusInfo[5] = 0;
         }

         // $FF: synthetic method
         void lambda$setOnParseListeners$1$com_hzbhd_canbus_car__30_MsgMgr$4(Context var1) {
            GeneralAirData.rear_temperature = this.getAirTemperature(var1, this.canbusInfo[6]);
         }

         // $FF: synthetic method
         void lambda$setOnParseListeners$2$com_hzbhd_canbus_car__30_MsgMgr$4() {
            GeneralAirData.rear_wind_level = this.canbusInfo[7];
         }

         // $FF: synthetic method
         void lambda$setOnParseListeners$3$com_hzbhd_canbus_car__30_MsgMgr$4() {
            GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit7(this.canbusInfo[5]) ^ true;
            GeneralAirData.front_defog = DataHandleUtils.getBoolBit6(this.canbusInfo[5]);
            GeneralAirData.rear_defog = DataHandleUtils.getBoolBit5(this.canbusInfo[5]);
            GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit4(this.canbusInfo[5]);
            GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit3(this.canbusInfo[5]);
            GeneralAirData.auto = DataHandleUtils.getBoolBit2(this.canbusInfo[5]);
            GeneralAirData.sync = DataHandleUtils.getBoolBit1(this.canbusInfo[5]);
            GeneralAirData.ac = DataHandleUtils.getBoolBit0(this.canbusInfo[5]);
         }

         // $FF: synthetic method
         void lambda$setOnParseListeners$4$com_hzbhd_canbus_car__30_MsgMgr$4() {
            this.canbusInfo[6] = 0;
         }

         // $FF: synthetic method
         void lambda$setOnParseListeners$5$com_hzbhd_canbus_car__30_MsgMgr$4() {
            this.canbusInfo[7] = 0;
         }

         // $FF: synthetic method
         void lambda$setOnParseListeners$6$com_hzbhd_canbus_car__30_MsgMgr$4(Context var1) {
            GeneralAirData.front_left_temperature = this.getAirTemperature(var1, this.canbusInfo[2]);
         }

         // $FF: synthetic method
         void lambda$setOnParseListeners$7$com_hzbhd_canbus_car__30_MsgMgr$4(Context var1) {
            GeneralAirData.front_right_temperature = this.getAirTemperature(var1, this.canbusInfo[3]);
         }

         // $FF: synthetic method
         void lambda$setOnParseListeners$8$com_hzbhd_canbus_car__30_MsgMgr$4() {
            GeneralAirData.front_wind_level = this.canbusInfo[4];
         }

         public void parse(int var1) {
            System.arraycopy(this.this$0.mCanbusInfoInt, 0, this.canbusInfo, 0, Math.min(this.this$0.mCanbusInfoInt.length, this.canbusInfo.length));
            super.parse(var1);
            if (this.this$0.isDataChange(this.canbusInfo)) {
               GeneralAirData.front_right_blow_foot = GeneralAirData.front_left_blow_foot;
               GeneralAirData.front_right_blow_head = GeneralAirData.front_left_blow_head;
               this.this$0.updateAirActivity(this.val$context, this.getAirWhat());
            }

         }

         public OnParseListener[] setOnParseListeners() {
            Object var1;
            Object var2;
            Object var3;
            if (this.this$0.is15Carnival()) {
               var1 = new MsgMgr$4$$ExternalSyntheticLambda0(this);
               var2 = new MsgMgr$4$$ExternalSyntheticLambda1(this, this.val$context);
               var3 = new MsgMgr$4$$ExternalSyntheticLambda2(this);
            } else {
               var1 = new MsgMgr$4$$ExternalSyntheticLambda3(this);
               var2 = new MsgMgr$4$$ExternalSyntheticLambda4(this);
               var3 = new MsgMgr$4$$ExternalSyntheticLambda5(this);
            }

            return new OnParseListener[]{new MsgMgr$4$$ExternalSyntheticLambda6(this, this.val$context), new MsgMgr$4$$ExternalSyntheticLambda7(this, this.val$context), new MsgMgr$4$$ExternalSyntheticLambda8(this), (OnParseListener)var1, (OnParseListener)var2, (OnParseListener)var3};
         }
      });
      this.mParserArray.append(4, new Parser(this, "0x04 雷达信息", var1) {
         final MsgMgr this$0;
         final Context val$context;

         {
            this.this$0 = var1;
            this.val$context = var3;
         }

         // $FF: synthetic method
         void lambda$setOnParseListeners$0$com_hzbhd_canbus_car__30_MsgMgr$5() {
            RadarInfoUtil.setFrontRadarLocationData(3, DataHandleUtils.getIntFromByteWithBit(this.this$0.mCanbusInfoInt[2], 6, 2), DataHandleUtils.getIntFromByteWithBit(this.this$0.mCanbusInfoInt[2], 4, 2), DataHandleUtils.getIntFromByteWithBit(this.this$0.mCanbusInfoInt[2], 2, 2), DataHandleUtils.getIntFromByteWithBit(this.this$0.mCanbusInfoInt[2], 0, 2));
         }

         // $FF: synthetic method
         void lambda$setOnParseListeners$1$com_hzbhd_canbus_car__30_MsgMgr$5() {
            RadarInfoUtil.setRearRadarLocationData(3, DataHandleUtils.getIntFromByteWithBit(this.this$0.mCanbusInfoInt[3], 6, 2), DataHandleUtils.getIntFromByteWithBit(this.this$0.mCanbusInfoInt[3], 4, 2), DataHandleUtils.getIntFromByteWithBit(this.this$0.mCanbusInfoInt[3], 2, 2), DataHandleUtils.getIntFromByteWithBit(this.this$0.mCanbusInfoInt[3], 0, 2));
         }

         public void parse(int var1) {
            MsgMgr var2 = this.this$0;
            if (var2.isDataChange(var2.mCanbusInfoInt)) {
               super.parse(var1);
               GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
               this.this$0.updateParkUi((Bundle)null, this.val$context);
            }

         }

         public OnParseListener[] setOnParseListeners() {
            return new OnParseListener[]{new MsgMgr$5$$ExternalSyntheticLambda0(this), new MsgMgr$5$$ExternalSyntheticLambda1(this)};
         }
      });
      this.mParserArray.append(5, new Parser(this, "0x05 车门信息", var1) {
         final MsgMgr this$0;
         final Context val$context;

         {
            this.this$0 = var1;
            this.val$context = var3;
         }

         // $FF: synthetic method
         void lambda$setOnParseListeners$0$com_hzbhd_canbus_car__30_MsgMgr$6() {
            GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit5(this.this$0.mCanbusInfoInt[2]);
            GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit4(this.this$0.mCanbusInfoInt[2]);
            GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit3(this.this$0.mCanbusInfoInt[2]);
            GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit2(this.this$0.mCanbusInfoInt[2]);
            GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit1(this.this$0.mCanbusInfoInt[2]);
            GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit0(this.this$0.mCanbusInfoInt[2]);
         }

         public void parse(int var1) {
            super.parse(var1);
            if (this.this$0.isDoorStatusChange()) {
               this.this$0.updateDoorView(this.val$context);
            }

         }

         public OnParseListener[] setOnParseListeners() {
            return new OnParseListener[]{new MsgMgr$6$$ExternalSyntheticLambda0(this)};
         }
      });
      this.mParserArray.append(6, new Parser(this, "0x06 空调Climate按键信息", var1) {
         private int climateBtnStatus;
         final MsgMgr this$0;
         final Context val$context;

         {
            this.this$0 = var1;
            this.val$context = var3;
         }

         // $FF: synthetic method
         void lambda$setOnParseListeners$0$com_hzbhd_canbus_car__30_MsgMgr$7(Context var1) {
            if (this.climateBtnStatus == 1 && this.this$0.mCanbusInfoInt[2] == 0) {
               if (SystemUtil.isForeground(var1, AirActivity.class.getName())) {
                  this.this$0.finishActivity();
               } else {
                  Intent var2 = new Intent(var1, AirActivity.class);
                  var2.setFlags(268435456);
                  var1.startActivity(var2);
               }
            }

            this.climateBtnStatus = this.this$0.mCanbusInfoInt[2];
         }

         public void parse(int var1) {
            super.parse(var1);
         }

         public OnParseListener[] setOnParseListeners() {
            return new OnParseListener[]{new MsgMgr$7$$ExternalSyntheticLambda0(this, this.val$context)};
         }
      });
      this.mParserArray.append(7, new Parser(this, "0x07 背光调节信息") {
         final MsgMgr this$0;

         {
            this.this$0 = var1;
         }

         // $FF: synthetic method
         void lambda$setOnParseListeners$0$com_hzbhd_canbus_car__30_MsgMgr$8() {
            if (this.this$0.mCanbusInfoInt[2] >= 1 && this.this$0.mCanbusInfoInt[2] < 5) {
               this.this$0.setBacklightLevel(1);
            } else if (this.this$0.mCanbusInfoInt[2] >= 5 && this.this$0.mCanbusInfoInt[2] < 9) {
               this.this$0.setBacklightLevel(2);
            } else if (this.this$0.mCanbusInfoInt[2] >= 9 && this.this$0.mCanbusInfoInt[2] < 13) {
               this.this$0.setBacklightLevel(3);
            } else if (this.this$0.mCanbusInfoInt[2] >= 13 && this.this$0.mCanbusInfoInt[2] < 17) {
               this.this$0.setBacklightLevel(4);
            } else if (this.this$0.mCanbusInfoInt[2] >= 13 && this.this$0.mCanbusInfoInt[2] < 17) {
               this.this$0.setBacklightLevel(5);
            }

         }

         public OnParseListener[] setOnParseListeners() {
            return new OnParseListener[]{new MsgMgr$8$$ExternalSyntheticLambda0(this)};
         }
      });
      this.mParserArray.append(127, new Parser(this, "0x7F 版本信息", var1) {
         final MsgMgr this$0;
         final Context val$context;

         {
            this.this$0 = var1;
            this.val$context = var3;
         }

         public void parse(int var1) {
            MsgMgr var2 = this.this$0;
            var2.updateVersionInfo(this.val$context, var2.getVersionStr(var2.mCanbusInfoByte));
         }

         public OnParseListener[] setOnParseListeners() {
            return null;
         }
      });
      this.mParserArray.append(64, new Parser(this, "0x40 全景影像状态", var1) {
         private int combi;
         private boolean isEnterPanoramic;
         final MsgMgr this$0;
         final Context val$context;
         private int view;

         {
            this.this$0 = var1;
            this.val$context = var3;
         }

         // $FF: synthetic method
         void lambda$parse$2$com_hzbhd_canbus_car__30_MsgMgr$10(Context var1) {
            int var2;
            label21: {
               ((MyPanoramicView)this.this$0.getUiMgr(var1).getCusPanoramicView(var1)).updateSide(this.view - 1);
               var2 = this.view;
               if (var2 == 1) {
                  int var3 = this.combi;
                  if (var3 < 6) {
                     var2 = var3 - 1;
                     break label21;
                  }
               }

               if (var2 == 2) {
                  var2 = this.combi;
                  if (var2 > 5) {
                     var2 -= 6;
                     break label21;
                  }
               }

               var2 = -1;
            }

            ((MyPanoramicView)this.this$0.getUiMgr(var1).getCusPanoramicView(var1)).updateBtns(var2);
         }

         // $FF: synthetic method
         void lambda$setOnParseListeners$0$com_hzbhd_canbus_car__30_MsgMgr$10() {
            this.view = this.this$0.mCanbusInfoInt[2];
         }

         // $FF: synthetic method
         void lambda$setOnParseListeners$1$com_hzbhd_canbus_car__30_MsgMgr$10() {
            this.combi = this.this$0.mCanbusInfoInt[3];
         }

         public void parse(int var1) {
            MsgMgr var4 = this.this$0;
            if (var4.isDataChange(var4.mCanbusInfoInt)) {
               super.parse(var1);
               boolean var2;
               if (this.view != 0 && this.combi != 0) {
                  var2 = true;
               } else {
                  var2 = false;
               }

               if (this.isEnterPanoramic ^ var2) {
                  this.isEnterPanoramic = var2;
                  var4 = this.this$0;
                  var4.forceReverse(var4.mContext, var2);
               }

               boolean var3 = CommUtil.isMiscReverse();
               if (var2 || var3) {
                  this.this$0.runOnUi(new MsgMgr$10$$ExternalSyntheticLambda2(this, this.val$context));
               }
            }

         }

         public OnParseListener[] setOnParseListeners() {
            return new OnParseListener[]{new MsgMgr$10$$ExternalSyntheticLambda0(this), new MsgMgr$10$$ExternalSyntheticLambda1(this)};
         }
      });
      this.mParserArray.append(65, new Parser(this, "0x41 泊车导航设置状态") {
         private List list;
         final MsgMgr this$0;

         {
            this.this$0 = var1;
         }

         // $FF: synthetic method
         void lambda$setOnParseListeners$0$com_hzbhd_canbus_car__30_MsgMgr$11() {
            this.list.add(this.this$0.getSettingUpdateEntity("_29_rear_view_parking_guide_line").setValue(DataHandleUtils.getIntFromByteWithBit(this.this$0.mCanbusInfoInt[2], 7, 1)));
            this.list.add(this.this$0.getSettingUpdateEntity("_29_park_distance_warning").setValue(DataHandleUtils.getIntFromByteWithBit(this.this$0.mCanbusInfoInt[2], 6, 1)));
         }

         // $FF: synthetic method
         void lambda$setOnParseListeners$1$com_hzbhd_canbus_car__30_MsgMgr$11() {
            this.list.add(this.this$0.getSettingUpdateEntity("vm_golf7_front_mode").setValue(DataHandleUtils.getIntFromByteWithBit(this.this$0.mCanbusInfoInt[3], 0, 4) - 1));
         }

         // $FF: synthetic method
         void lambda$setOnParseListeners$2$com_hzbhd_canbus_car__30_MsgMgr$11() {
            this.list.add(this.this$0.getSettingUpdateEntity("vm_golf7_rear_mode").setValue(DataHandleUtils.getIntFromByteWithBit(this.this$0.mCanbusInfoInt[4], 0, 4) - 1));
         }

         public void parse(int var1) {
            MsgMgr var2 = this.this$0;
            if (var2.isDataChange(var2.mCanbusInfoInt)) {
               this.list = new ArrayList();
               super.parse(var1);
               this.this$0.updateGeneralSettingData(this.list);
               this.this$0.updateSettingActivity((Bundle)null);
            }

         }

         public OnParseListener[] setOnParseListeners() {
            return new OnParseListener[]{new MsgMgr$11$$ExternalSyntheticLambda0(this), new MsgMgr$11$$ExternalSyntheticLambda1(this), new MsgMgr$11$$ExternalSyntheticLambda2(this)};
         }
      });
      this.mParserArray.append(66, new Parser(this, "0x42 后置摄像头影像状态") {
         private int isEnterBackCamera;
         final MsgMgr this$0;

         {
            this.this$0 = var1;
         }

         // $FF: synthetic method
         static void lambda$setOnParseListeners$1() {
         }

         // $FF: synthetic method
         void lambda$setOnParseListeners$0$com_hzbhd_canbus_car__30_MsgMgr$12() {
            if (this.isEnterBackCamera != this.this$0.mCanbusInfoInt[2]) {
               this.isEnterBackCamera = this.this$0.mCanbusInfoInt[2];
               MsgMgr var4 = this.this$0;
               Context var3 = var4.mContext;
               int var1 = this.isEnterBackCamera;
               boolean var2 = true;
               if (var1 != 1) {
                  var2 = false;
               }

               var4.forceReverse(var3, var2);
            }

         }

         public void parse(int var1) {
            super.parse(var1);
         }

         public OnParseListener[] setOnParseListeners() {
            return new OnParseListener[]{new MsgMgr$12$$ExternalSyntheticLambda0(this), new MsgMgr$12$$ExternalSyntheticLambda1()};
         }
      });
      this.mParserArray.append(81, new Parser(this, "0x51 座椅位置变化信息") {
         final MsgMgr this$0;

         {
            this.this$0 = var1;
         }

         // $FF: synthetic method
         void lambda$parse$2$com_hzbhd_canbus_car__30_MsgMgr$13(int var1) {
            super.parse(var1);
            this.this$0.mSeatStatusView.addViewToWindow();
         }

         // $FF: synthetic method
         void lambda$setOnParseListeners$0$com_hzbhd_canbus_car__30_MsgMgr$13() {
            SeatStatusView var4 = this.this$0.mSeatStatusView;
            int var1 = this.this$0.mCanbusInfoInt[2];
            boolean var3 = true;
            boolean var2;
            if (var1 != 0) {
               var2 = true;
            } else {
               var2 = false;
            }

            var4.setSeatSelect(var2);
            this.this$0.mSeatStatusView.setSeatForward(DataHandleUtils.getBoolBit0(this.this$0.mCanbusInfoInt[2]));
            this.this$0.mSeatStatusView.setSeatBack(DataHandleUtils.getBoolBit1(this.this$0.mCanbusInfoInt[2]));
            var4 = this.this$0.mSeatStatusView;
            if (!DataHandleUtils.getBoolBit2(this.this$0.mCanbusInfoInt[2]) && !DataHandleUtils.getBoolBit4(this.this$0.mCanbusInfoInt[2])) {
               var2 = false;
            } else {
               var2 = true;
            }

            var4.setSeatUp(var2);
            var4 = this.this$0.mSeatStatusView;
            var2 = var3;
            if (!DataHandleUtils.getBoolBit3(this.this$0.mCanbusInfoInt[2])) {
               if (DataHandleUtils.getBoolBit5(this.this$0.mCanbusInfoInt[2])) {
                  var2 = var3;
               } else {
                  var2 = false;
               }
            }

            var4.setSeatDown(var2);
         }

         // $FF: synthetic method
         void lambda$setOnParseListeners$1$com_hzbhd_canbus_car__30_MsgMgr$13() {
            SeatStatusView var2 = this.this$0.mSeatStatusView;
            boolean var1;
            if (this.this$0.mCanbusInfoInt[3] != 0) {
               var1 = true;
            } else {
               var1 = false;
            }

            var2.setBackrestSelelct(var1);
            this.this$0.mSeatStatusView.setBackrestForward(DataHandleUtils.getBoolBit0(this.this$0.mCanbusInfoInt[3]));
            this.this$0.mSeatStatusView.setBackrestBackward(DataHandleUtils.getBoolBit1(this.this$0.mCanbusInfoInt[3]));
            this.this$0.mSeatStatusView.setBackrestUp(DataHandleUtils.getBoolBit2(this.this$0.mCanbusInfoInt[3]));
            this.this$0.mSeatStatusView.setBackrestDown(DataHandleUtils.getBoolBit3(this.this$0.mCanbusInfoInt[3]));
         }

         public void parse(int var1) {
            MsgMgr var2 = this.this$0;
            if (var2.isDataChange(var2.mCanbusInfoInt)) {
               this.this$0.runOnUi(new MsgMgr$13$$ExternalSyntheticLambda0(this, var1));
            }

         }

         public OnParseListener[] setOnParseListeners() {
            return new OnParseListener[]{new MsgMgr$13$$ExternalSyntheticLambda1(this), new MsgMgr$13$$ExternalSyntheticLambda2(this)};
         }
      });
      this.mParserArray.append(82, new Parser(this, "0x52 车辆设置信息") {
         final MsgMgr this$0;

         {
            this.this$0 = var1;
         }

         public void parse(int var1) {
            ArrayList var4 = new ArrayList();
            var1 = this.this$0.mCanbusInfoInt[2];
            boolean var3 = true;
            switch (var1) {
               case 1:
                  var4.add(this.this$0.getSettingUpdateEntity("_213_car_set15_76").setValue(2 - this.this$0.mCanbusInfoInt[3]));
                  break;
               case 2:
                  var4.add(this.this$0.getSettingUpdateEntity("_30_auto_temp_circulation").setValue(2 - this.this$0.mCanbusInfoInt[3]));
                  break;
               case 3:
                  var4.add(this.this$0.getSettingUpdateEntity("_30_third_left_backrest_fold").setValue(this.this$0.mCanbusInfoInt[3] - 1));
                  break;
               case 4:
                  var4.add(this.this$0.getSettingUpdateEntity("_30_third_right_backrest_fold").setValue(this.this$0.mCanbusInfoInt[3] - 1));
                  break;
               case 5:
                  var4.add(this.this$0.getSettingUpdateEntity("_29_steering_wheel_heater").setValue(2 - this.this$0.mCanbusInfoInt[3]));
                  break;
               case 6:
                  var4.add(this.this$0.getSettingUpdateEntity("_29_seat_heat_ventil").setValue(2 - this.this$0.mCanbusInfoInt[3]));
                  break;
               case 7:
                  var4.add(this.this$0.getSettingUpdateEntity("_30_seat_status_change_tip").setValue(2 - this.this$0.mCanbusInfoInt[3]));
                  break;
               case 8:
                  var4.add(this.this$0.getSettingUpdateEntity("_30_back_camera_open_hold").setValue(2 - this.this$0.mCanbusInfoInt[3]));
               case 9:
               default:
                  break;
               case 10:
                  var4.add(this.this$0.getSettingUpdateEntity("_30_air_internal_circulation").setValue(2 - this.this$0.mCanbusInfoInt[3]));
                  break;
               case 11:
                  var4.add(this.this$0.getSettingUpdateEntity("_284_setting_item_47").setValue(this.this$0.mCanbusInfoInt[3] - 1));
                  SettingUpdateEntity var7 = this.this$0.getSettingUpdateEntity("_30_theme_color");
                  boolean var2;
                  if (this.this$0.mCanbusInfoInt[3] == 1) {
                     var2 = true;
                  } else {
                     var2 = false;
                  }

                  var4.add(var7.setEnable(var2));
                  var7 = this.this$0.getSettingUpdateEntity("_30_single_color");
                  if (this.this$0.mCanbusInfoInt[3] == 2) {
                     var2 = var3;
                  } else {
                     var2 = false;
                  }

                  var4.add(var7.setEnable(var2));
                  break;
               case 12:
                  SettingUpdateEntity var6 = this.this$0.getSettingUpdateEntity("_30_theme_color");
                  MsgMgr var5 = this.this$0;
                  var4.add(var6.setValue(var5.getThemeColorIndex(var5.mCanbusInfoInt[3])));
                  break;
               case 13:
                  var4.add(this.this$0.getSettingUpdateEntity("_30_single_color").setValue(this.this$0.mCanbusInfoInt[3] - 1));
                  break;
               case 14:
                  var4.add(this.this$0.getSettingUpdateEntity("_283_media_titls_2").setValue(2 - this.this$0.mCanbusInfoInt[3]));
                  break;
               case 15:
                  var4.add(this.this$0.getSettingUpdateEntity("brightness").setValue(this.this$0.mCanbusInfoInt[3] - 1).setProgress(this.this$0.mCanbusInfoInt[3] - 2));
            }

            this.this$0.updateGeneralSettingData(var4);
            this.this$0.updateSettingActivity((Bundle)null);
         }

         public OnParseListener[] setOnParseListeners() {
            return null;
         }
      });
      this.mParserArray.append(83, new Parser(this, "0x53 新能源设置信息1") {
         private final int CHARGE_TYPE_1_START_100;
         private final int CHARGE_TYPE_2_START_END;
         private final int CHARGE_TYPE_CLOSE;
         private List list;
         final MsgMgr this$0;

         {
            this.this$0 = var1;
            this.CHARGE_TYPE_CLOSE = 1;
            this.CHARGE_TYPE_1_START_100 = 2;
            this.CHARGE_TYPE_2_START_END = 3;
         }

         private int getTime(int var1) {
            int var2 = DataHandleUtils.getIntFromByteWithBit(var1, 5, 3);
            return DataHandleUtils.getIntFromByteWithBit(var1, 0, 5) * 6 + var2;
         }

         void init() {
            ArrayList var1 = new ArrayList();
            this.list = var1;
            var1.add(this.this$0.getSettingUpdateEntity("_30_new_energy_repeat_1").setEnable(false));
            this.list.add(this.this$0.getSettingUpdateEntity("_30_new_energy_sunday_1").setEnable(false));
            this.list.add(this.this$0.getSettingUpdateEntity("_30_new_energy_monday_1").setEnable(false));
            this.list.add(this.this$0.getSettingUpdateEntity("_30_new_energy_tuesday_1").setEnable(false));
            this.list.add(this.this$0.getSettingUpdateEntity("_30_new_energy_wednesday_1").setEnable(false));
            this.list.add(this.this$0.getSettingUpdateEntity("_30_new_energy_thursday_1").setEnable(false));
            this.list.add(this.this$0.getSettingUpdateEntity("_30_new_energy_friday_1").setEnable(false));
            this.list.add(this.this$0.getSettingUpdateEntity("_30_new_energy_saturday_1").setEnable(false));
            this.list.add(this.this$0.getSettingUpdateEntity("_30_new_energy_repeat_2").setEnable(false));
            this.list.add(this.this$0.getSettingUpdateEntity("_30_new_energy_sunday_2").setEnable(false));
            this.list.add(this.this$0.getSettingUpdateEntity("_30_new_energy_monday_2").setEnable(false));
            this.list.add(this.this$0.getSettingUpdateEntity("_30_new_energy_tuesday_2").setEnable(false));
            this.list.add(this.this$0.getSettingUpdateEntity("_30_new_energy_wednesday_2").setEnable(false));
            this.list.add(this.this$0.getSettingUpdateEntity("_30_new_energy_thursday_2").setEnable(false));
            this.list.add(this.this$0.getSettingUpdateEntity("_30_new_energy_friday_2").setEnable(false));
            this.list.add(this.this$0.getSettingUpdateEntity("_30_new_energy_saturday_2").setEnable(false));
            this.list.add(this.this$0.getSettingUpdateEntity("_30_new_energy_repeat_3").setEnable(false));
            this.list.add(this.this$0.getSettingUpdateEntity("_30_new_energy_sunday_3").setEnable(false));
            this.list.add(this.this$0.getSettingUpdateEntity("_30_new_energy_monday_3").setEnable(false));
            this.list.add(this.this$0.getSettingUpdateEntity("_30_new_energy_tuesday_3").setEnable(false));
            this.list.add(this.this$0.getSettingUpdateEntity("_30_new_energy_wednesday_3").setEnable(false));
            this.list.add(this.this$0.getSettingUpdateEntity("_30_new_energy_thursday_3").setEnable(false));
            this.list.add(this.this$0.getSettingUpdateEntity("_30_new_energy_friday_3").setEnable(false));
            this.list.add(this.this$0.getSettingUpdateEntity("_30_new_energy_saturday_3").setEnable(false));
            this.this$0.updateGeneralSettingData(this.list);
         }

         // $FF: synthetic method
         void lambda$setOnParseListeners$0$com_hzbhd_canbus_car__30_MsgMgr$15() {
            this.list.add(this.this$0.getSettingUpdateEntity("_30_new_energy_53_0").setValue(this.this$0.mCanbusInfoInt[2] - 1));
         }

         // $FF: synthetic method
         void lambda$setOnParseListeners$1$com_hzbhd_canbus_car__30_MsgMgr$15() {
            this.list.add(this.this$0.getSettingUpdateEntity("_30_start_time_1").setValue(this.getTime(this.this$0.mCanbusInfoInt[3])).setProgress(this.getTime(this.this$0.mCanbusInfoInt[3])));
         }

         // $FF: synthetic method
         void lambda$setOnParseListeners$10$com_hzbhd_canbus_car__30_MsgMgr$15() {
            this.list.add(this.this$0.getSettingUpdateEntity("_30_new_energy_53_C_1").setValue(DataHandleUtils.getIntFromByteWithBit(this.this$0.mCanbusInfoInt[12], 7, 1)));
            this.list.add(this.this$0.getSettingUpdateEntity("_30_new_energy_53_C_2").setValue(DataHandleUtils.getIntFromByteWithBit(this.this$0.mCanbusInfoInt[12], 6, 1)));
            this.list.add(this.this$0.getSettingUpdateEntity("_30_new_energy_53_B_3").setValue(DataHandleUtils.getIntFromByteWithBit(this.this$0.mCanbusInfoInt[12], 5, 1)));
            this.list.add(this.this$0.getSettingUpdateEntity("_30_new_energy_53_B_4").setValue(DataHandleUtils.getIntFromByteWithBit(this.this$0.mCanbusInfoInt[12], 4, 1)));
            this.list.add(this.this$0.getSettingUpdateEntity("_30_new_energy_53_B_5").setValue(DataHandleUtils.getIntFromByteWithBit(this.this$0.mCanbusInfoInt[12], 2, 2)));
            this.list.add(this.this$0.getSettingUpdateEntity("_30_new_energy_53_B_6").setValue(DataHandleUtils.getIntFromByteWithBit(this.this$0.mCanbusInfoInt[12], 0, 2)));
         }

         // $FF: synthetic method
         void lambda$setOnParseListeners$11$com_hzbhd_canbus_car__30_MsgMgr$15() {
            this.list.add(this.this$0.getSettingUpdateEntity("_30_new_energy_53_C_10").setValue(this.this$0.mCanbusInfoInt[13]).setProgress(this.this$0.mCanbusInfoInt[13] - 6));
         }

         // $FF: synthetic method
         void lambda$setOnParseListeners$2$com_hzbhd_canbus_car__30_MsgMgr$15() {
            List var3 = this.list;
            SettingUpdateEntity var2 = this.this$0.getSettingUpdateEntity("_30_end_time_1").setValue(this.getTime(this.this$0.mCanbusInfoInt[4])).setProgress(this.getTime(this.this$0.mCanbusInfoInt[4]));
            boolean var1;
            if (this.this$0.mCanbusInfoInt[2] == 3) {
               var1 = true;
            } else {
               var1 = false;
            }

            var3.add(var2.setEnable(var1));
         }

         // $FF: synthetic method
         void lambda$setOnParseListeners$3$com_hzbhd_canbus_car__30_MsgMgr$15() {
            this.list.add(this.this$0.getSettingUpdateEntity("_30_new_energy_sunday_1").setValue(DataHandleUtils.getIntFromByteWithBit(this.this$0.mCanbusInfoInt[5], 6, 1)));
            this.list.add(this.this$0.getSettingUpdateEntity("_30_new_energy_monday_1").setValue(DataHandleUtils.getIntFromByteWithBit(this.this$0.mCanbusInfoInt[5], 5, 1)));
            this.list.add(this.this$0.getSettingUpdateEntity("_30_new_energy_tuesday_1").setValue(DataHandleUtils.getIntFromByteWithBit(this.this$0.mCanbusInfoInt[5], 4, 1)));
            this.list.add(this.this$0.getSettingUpdateEntity("_30_new_energy_wednesday_1").setValue(DataHandleUtils.getIntFromByteWithBit(this.this$0.mCanbusInfoInt[5], 3, 1)));
            this.list.add(this.this$0.getSettingUpdateEntity("_30_new_energy_thursday_1").setValue(DataHandleUtils.getIntFromByteWithBit(this.this$0.mCanbusInfoInt[5], 2, 1)));
            this.list.add(this.this$0.getSettingUpdateEntity("_30_new_energy_friday_1").setValue(DataHandleUtils.getIntFromByteWithBit(this.this$0.mCanbusInfoInt[5], 1, 1)));
            this.list.add(this.this$0.getSettingUpdateEntity("_30_new_energy_saturday_1").setValue(DataHandleUtils.getIntFromByteWithBit(this.this$0.mCanbusInfoInt[5], 0, 1)));
         }

         // $FF: synthetic method
         void lambda$setOnParseListeners$4$com_hzbhd_canbus_car__30_MsgMgr$15() {
            this.list.add(this.this$0.getSettingUpdateEntity("_30_new_energy_53_4").setValue(this.getTime(this.this$0.mCanbusInfoInt[6])).setProgress(this.getTime(this.this$0.mCanbusInfoInt[6])));
         }

         // $FF: synthetic method
         void lambda$setOnParseListeners$5$com_hzbhd_canbus_car__30_MsgMgr$15() {
            this.list.add(this.this$0.getSettingUpdateEntity("_30_new_energy_monday_2").setValue(DataHandleUtils.getIntFromByteWithBit(this.this$0.mCanbusInfoInt[7], 0, 1)));
            this.list.add(this.this$0.getSettingUpdateEntity("_30_new_energy_tuesday_2").setValue(DataHandleUtils.getIntFromByteWithBit(this.this$0.mCanbusInfoInt[7], 1, 1)));
            this.list.add(this.this$0.getSettingUpdateEntity("_30_new_energy_wednesday_2").setValue(DataHandleUtils.getIntFromByteWithBit(this.this$0.mCanbusInfoInt[7], 2, 1)));
            this.list.add(this.this$0.getSettingUpdateEntity("_30_new_energy_thursday_2").setValue(DataHandleUtils.getIntFromByteWithBit(this.this$0.mCanbusInfoInt[7], 3, 1)));
            this.list.add(this.this$0.getSettingUpdateEntity("_30_new_energy_friday_2").setValue(DataHandleUtils.getIntFromByteWithBit(this.this$0.mCanbusInfoInt[7], 4, 1)));
            this.list.add(this.this$0.getSettingUpdateEntity("_30_new_energy_saturday_2").setValue(DataHandleUtils.getIntFromByteWithBit(this.this$0.mCanbusInfoInt[7], 5, 1)));
            this.list.add(this.this$0.getSettingUpdateEntity("_30_new_energy_sunday_2").setValue(DataHandleUtils.getIntFromByteWithBit(this.this$0.mCanbusInfoInt[7], 6, 1)));
            this.list.add(this.this$0.getSettingUpdateEntity("_30_new_energy_53_B_1").setValue(DataHandleUtils.getIntFromByteWithBit(this.this$0.mCanbusInfoInt[7], 7, 1)));
         }

         // $FF: synthetic method
         void lambda$setOnParseListeners$6$com_hzbhd_canbus_car__30_MsgMgr$15() {
            this.list.add(this.this$0.getSettingUpdateEntity("_30_new_energy_53_6").setValue(this.getTime(this.this$0.mCanbusInfoInt[8])).setProgress(this.getTime(this.this$0.mCanbusInfoInt[8])));
         }

         // $FF: synthetic method
         void lambda$setOnParseListeners$7$com_hzbhd_canbus_car__30_MsgMgr$15() {
            this.list.add(this.this$0.getSettingUpdateEntity("_30_new_energy_monday_3").setValue(DataHandleUtils.getIntFromByteWithBit(this.this$0.mCanbusInfoInt[9], 0, 1)));
            this.list.add(this.this$0.getSettingUpdateEntity("_30_new_energy_tuesday_3").setValue(DataHandleUtils.getIntFromByteWithBit(this.this$0.mCanbusInfoInt[9], 1, 1)));
            this.list.add(this.this$0.getSettingUpdateEntity("_30_new_energy_wednesday_3").setValue(DataHandleUtils.getIntFromByteWithBit(this.this$0.mCanbusInfoInt[9], 2, 1)));
            this.list.add(this.this$0.getSettingUpdateEntity("_30_new_energy_thursday_3").setValue(DataHandleUtils.getIntFromByteWithBit(this.this$0.mCanbusInfoInt[9], 3, 1)));
            this.list.add(this.this$0.getSettingUpdateEntity("_30_new_energy_friday_3").setValue(DataHandleUtils.getIntFromByteWithBit(this.this$0.mCanbusInfoInt[9], 4, 1)));
            this.list.add(this.this$0.getSettingUpdateEntity("_30_new_energy_saturday_3").setValue(DataHandleUtils.getIntFromByteWithBit(this.this$0.mCanbusInfoInt[9], 5, 1)));
            this.list.add(this.this$0.getSettingUpdateEntity("_30_new_energy_sunday_3").setValue(DataHandleUtils.getIntFromByteWithBit(this.this$0.mCanbusInfoInt[9], 6, 1)));
            this.list.add(this.this$0.getSettingUpdateEntity("_30_new_energy_53_B_2").setValue(DataHandleUtils.getIntFromByteWithBit(this.this$0.mCanbusInfoInt[9], 7, 1)));
         }

         // $FF: synthetic method
         void lambda$setOnParseListeners$8$com_hzbhd_canbus_car__30_MsgMgr$15() {
            this.list.add(this.this$0.getSettingUpdateEntity("_30_start_time_2").setValue(this.getTime(this.this$0.mCanbusInfoInt[10])).setProgress(this.getTime(this.this$0.mCanbusInfoInt[10])));
         }

         // $FF: synthetic method
         void lambda$setOnParseListeners$9$com_hzbhd_canbus_car__30_MsgMgr$15() {
            this.list.add(this.this$0.getSettingUpdateEntity("_30_end_time_2").setValue(this.getTime(this.this$0.mCanbusInfoInt[11])).setProgress(this.getTime(this.this$0.mCanbusInfoInt[11])));
         }

         public void parse(int var1) {
            MsgMgr var5 = this.this$0;
            if (var5.isDataChange(var5.mCanbusInfoInt)) {
               ArrayList var6 = new ArrayList();
               this.list = var6;
               Page var7 = this.this$0.mSettingPage.getChildByTitle("_30_new_energy_53_0");
               int var2 = this.this$0.mCanbusInfoInt[2];
               boolean var4 = false;
               boolean var3;
               if (var2 != 1) {
                  var3 = true;
               } else {
                  var3 = false;
               }

               var6.addAll(var7.setChildsEnable(var3));
               List var9 = this.list;
               var7 = this.this$0.mSettingPage.getChildByTitle("_30_new_energy_53_B_1");
               if ((this.this$0.mCanbusInfoInt[7] >> 7 & 1) == 1) {
                  var3 = true;
               } else {
                  var3 = false;
               }

               var9.addAll(var7.setChildsEnable(var3));
               List var8 = this.list;
               Page var10 = this.this$0.mSettingPage.getChildByTitle("_30_new_energy_53_B_2");
               if ((this.this$0.mCanbusInfoInt[9] >> 7 & 1) == 1) {
                  var3 = true;
               } else {
                  var3 = false;
               }

               var8.addAll(var10.setChildsEnable(var3));
               var8 = this.list;
               var10 = this.this$0.mSettingPage.getChildByTitle("_30_new_energy_53_B_3");
               if ((this.this$0.mCanbusInfoInt[12] >> 5 & 1) == 1) {
                  var3 = true;
               } else {
                  var3 = false;
               }

               var8.addAll(var10.setChildsEnable(var3));
               var9 = this.list;
               var7 = this.this$0.mSettingPage.getChildByTitle("_30_new_energy_53_C_1");
               var3 = var4;
               if ((this.this$0.mCanbusInfoInt[12] >> 7 & 1) == 1) {
                  var3 = true;
               }

               var9.addAll(var7.setChildsEnable(var3));
               super.parse(var1);
               this.this$0.updateGeneralSettingData(this.list);
               this.this$0.updateSettingActivity((Bundle)null);
            }

         }

         public OnParseListener[] setOnParseListeners() {
            return new OnParseListener[]{new MsgMgr$15$$ExternalSyntheticLambda0(this), new MsgMgr$15$$ExternalSyntheticLambda5(this), new MsgMgr$15$$ExternalSyntheticLambda6(this), new MsgMgr$15$$ExternalSyntheticLambda7(this), new MsgMgr$15$$ExternalSyntheticLambda8(this), new MsgMgr$15$$ExternalSyntheticLambda9(this), new MsgMgr$15$$ExternalSyntheticLambda10(this), new MsgMgr$15$$ExternalSyntheticLambda11(this), new MsgMgr$15$$ExternalSyntheticLambda1(this), new MsgMgr$15$$ExternalSyntheticLambda2(this), new MsgMgr$15$$ExternalSyntheticLambda3(this), new MsgMgr$15$$ExternalSyntheticLambda4(this)};
         }
      });
      this.mParserArray.append(84, new Parser(this, "0x54 新能源设置信息2") {
         private List list;
         final MsgMgr this$0;

         {
            this.this$0 = var1;
         }

         // $FF: synthetic method
         void lambda$setOnParseListeners$0$com_hzbhd_canbus_car__30_MsgMgr$16() {
            this.list.add(this.this$0.getDriveUpdateEntity("_30_eco_level").setValue(Integer.toString(this.this$0.mCanbusInfoInt[2])));
         }

         // $FF: synthetic method
         void lambda$setOnParseListeners$1$com_hzbhd_canbus_car__30_MsgMgr$16() {
            int var1 = this.this$0.mCanbusInfoInt[3] << 8 | this.this$0.mCanbusInfoInt[4];
            if (var1 < 999) {
               this.list.add(this.this$0.getDriveUpdateEntity("vm_golf7_vehicle_setup_mfd_avg_consumption").setValue((float)var1 / 10.0F + " L/100KM"));
            }
         }

         // $FF: synthetic method
         void lambda$setOnParseListeners$2$com_hzbhd_canbus_car__30_MsgMgr$16() {
            int var1 = this.this$0.mCanbusInfoInt[5] << 8 | this.this$0.mCanbusInfoInt[6];
            if (var1 < 999) {
               this.list.add(this.this$0.getDriveUpdateEntity("_30_hybrid_fuel_consumption").setValue((float)var1 / 10.0F + " L/100KM"));
            }
         }

         // $FF: synthetic method
         void lambda$setOnParseListeners$3$com_hzbhd_canbus_car__30_MsgMgr$16() {
            this.list.add(this.this$0.getDriveUpdateEntity("_30_motor_energy_consumption").setValue(this.this$0.mCanbusInfoInt[7] + " KW"));
         }

         // $FF: synthetic method
         void lambda$setOnParseListeners$4$com_hzbhd_canbus_car__30_MsgMgr$16() {
            this.list.add(this.this$0.getDriveUpdateEntity("_279_dashboard_data2").setValue(this.this$0.mCanbusInfoInt[8] + " KM/H"));
            MsgMgr var1 = this.this$0;
            var1.updateSpeedInfo(var1.mCanbusInfoInt[8]);
         }

         // $FF: synthetic method
         void lambda$setOnParseListeners$5$com_hzbhd_canbus_car__30_MsgMgr$16() {
            int var1 = this.this$0.mCanbusInfoInt[9];
            int var2 = this.this$0.mCanbusInfoInt[10];
            int var3 = this.this$0.mCanbusInfoInt[11];
            this.list.add(this.this$0.getDriveUpdateEntity("_197_odo").setValue((float)(var1 << 16 | var2 << 8 | var3) / 10.0F + " KM"));
         }

         // $FF: synthetic method
         void lambda$setOnParseListeners$6$com_hzbhd_canbus_car__30_MsgMgr$16() {
            int var1 = this.this$0.mCanbusInfoInt[12] << 8 | this.this$0.mCanbusInfoInt[13];
            if (var1 < 999) {
               this.list.add(this.this$0.getDriveUpdateEntity("_30_energy_consumption_record").setValue((float)var1 / 10.0F + " KWH/100KM"));
            }
         }

         public void parse(int var1) {
            Log.i("_1030_MsgMgr", "parse: 0x54");
            MsgMgr var2 = this.this$0;
            if (var2.isDataChange(var2.mCanbusInfoInt)) {
               this.list = new ArrayList();
               super.parse(var1);
               this.this$0.updateGeneralDriveData(this.list);
               this.this$0.updateDriveDataActivity((Bundle)null);
            }

         }

         public OnParseListener[] setOnParseListeners() {
            return new OnParseListener[]{new MsgMgr$16$$ExternalSyntheticLambda0(this), null, new MsgMgr$16$$ExternalSyntheticLambda1(this), null, new MsgMgr$16$$ExternalSyntheticLambda2(this), new MsgMgr$16$$ExternalSyntheticLambda3(this), new MsgMgr$16$$ExternalSyntheticLambda4(this), null, null, new MsgMgr$16$$ExternalSyntheticLambda5(this), null, new MsgMgr$16$$ExternalSyntheticLambda6(this)};
         }
      });
      this.mParserArray.append(85, new Parser(this, "0x55 新能源设置信息3", var1) {
         private int[] driveDataRecord;
         List driverUpdateList;
         private int[] hybridDataRecord;
         List hybridUpdateList;
         final MsgMgr this$0;
         final Context val$context;

         {
            this.this$0 = var1;
            this.val$context = var3;
         }

         // $FF: synthetic method
         void lambda$setOnParseListeners$0$com_hzbhd_canbus_car__30_MsgMgr$17(Context var1) {
            int var3 = this.this$0.mCanbusInfoInt[2];
            int var2 = this.this$0.mCanbusInfoInt[3];
            String var4 = String.format("%s: %d KM", CommUtil.getStrByResId(var1, "_30_power"), var3 << 8 | var2);
            this.hybridUpdateList.add(var4);
         }

         // $FF: synthetic method
         void lambda$setOnParseListeners$1$com_hzbhd_canbus_car__30_MsgMgr$17(Context var1) {
            int var3 = this.this$0.mCanbusInfoInt[4];
            int var2 = this.this$0.mCanbusInfoInt[5];
            String var4 = String.format("%s: %d KM", CommUtil.getStrByResId(var1, "_30_gasoline"), var3 << 8 | var2);
            this.hybridUpdateList.add(var4);
         }

         // $FF: synthetic method
         void lambda$setOnParseListeners$2$com_hzbhd_canbus_car__30_MsgMgr$17() {
            int var2 = this.this$0.mCanbusInfoInt[6];
            int var1 = this.this$0.mCanbusInfoInt[7];
            this.driverUpdateList.add(this.this$0.getDriveUpdateEntity("_30_total_mileage_available").setValue((var2 << 8 | var1) + " KM"));
         }

         // $FF: synthetic method
         void lambda$setOnParseListeners$3$com_hzbhd_canbus_car__30_MsgMgr$17() {
            int var1 = this.this$0.mCanbusInfoInt[8];
            this.driverUpdateList.add(this.this$0.getDriveUpdateEntity("_30_battery_percentage").setValue(var1 + "%"));
         }

         // $FF: synthetic method
         void lambda$setOnParseListeners$4$com_hzbhd_canbus_car__30_MsgMgr$17(Context var1) {
            int var3 = this.this$0.mCanbusInfoInt[9] << 8 | this.this$0.mCanbusInfoInt[10];
            int var2 = var3 / 60;
            this.driverUpdateList.add(this.this$0.getDriveUpdateEntity("_30_estimated_charging_time_charger").setValue(var2 + CommUtil.getStrByResId(var1, "unit_hour") + var3 % 60 + CommUtil.getStrByResId(var1, "unit_minute")));
         }

         // $FF: synthetic method
         void lambda$setOnParseListeners$5$com_hzbhd_canbus_car__30_MsgMgr$17(Context var1) {
            int var2 = this.this$0.mCanbusInfoInt[11] << 8 | this.this$0.mCanbusInfoInt[12];
            int var3 = var2 / 60;
            this.driverUpdateList.add(this.this$0.getDriveUpdateEntity("_30_estimated_charging_time_portables").setValue(var3 + CommUtil.getStrByResId(var1, "unit_hour") + var2 % 60 + CommUtil.getStrByResId(var1, "unit_minute")));
         }

         // $FF: synthetic method
         void lambda$setOnParseListeners$6$com_hzbhd_canbus_car__30_MsgMgr$17() {
            int var2 = DataHandleUtils.getIntFromByteWithBit(this.this$0.mCanbusInfoInt[13], 4, 4);
            int var1 = DataHandleUtils.getIntFromByteWithBit(this.this$0.mCanbusInfoInt[13], 0, 4);
            GeneralHybirdData.powerBatteryLevel = var2;
            GeneralHybirdData.isWheelDriveMotor = false;
            GeneralHybirdData.isBatteryDriveMotor = false;
            GeneralHybirdData.isEngineDriveWheel = false;
            GeneralHybirdData.isEngineDriveMotor = false;
            GeneralHybirdData.isMotorDriveWheel = false;
            GeneralHybirdData.isMotorDriveBattery = false;
            switch (var1) {
               case 0:
               case 3:
                  GeneralHybirdData.isBatteryDriveMotor = true;
                  GeneralHybirdData.isMotorDriveWheel = true;
                  GeneralHybirdData.isEngineDriveWheel = true;
                  GeneralHybirdData.isEngineDriveMotor = true;
                  break;
               case 1:
                  GeneralHybirdData.isEngineDriveWheel = true;
                  GeneralHybirdData.isEngineDriveMotor = true;
                  GeneralHybirdData.isMotorDriveWheel = true;
                  break;
               case 2:
                  GeneralHybirdData.isBatteryDriveMotor = true;
                  GeneralHybirdData.isMotorDriveWheel = true;
                  break;
               case 4:
                  GeneralHybirdData.isEngineDriveWheel = true;
                  break;
               case 5:
               case 6:
               case 8:
               case 9:
               case 10:
               case 11:
                  GeneralHybirdData.isWheelDriveMotor = true;
                  GeneralHybirdData.isMotorDriveBattery = true;
                  break;
               case 7:
                  GeneralHybirdData.isEngineDriveWheel = true;
                  GeneralHybirdData.isEngineDriveMotor = true;
            }

         }

         public void parse(int var1) {
            MsgMgr var2 = this.this$0;
            if (var2.isDataChange(var2.mCanbusInfoInt)) {
               this.hybridUpdateList = new ArrayList();
               this.driverUpdateList = new ArrayList();
               super.parse(var1);
               GeneralHybirdData.title = CommUtil.getStrByResId(this.val$context, "_30_energy_info");
               GeneralHybirdData.valueList = this.hybridUpdateList;
               this.this$0.updateHybirdActivity((Bundle)null);
               this.this$0.updateGeneralDriveData(this.driverUpdateList);
               this.this$0.updateDriveDataActivity((Bundle)null);
            }

         }

         public OnParseListener[] setOnParseListeners() {
            return new OnParseListener[]{null, new MsgMgr$17$$ExternalSyntheticLambda0(this, this.val$context), null, new MsgMgr$17$$ExternalSyntheticLambda1(this, this.val$context), null, new MsgMgr$17$$ExternalSyntheticLambda2(this), new MsgMgr$17$$ExternalSyntheticLambda3(this), null, new MsgMgr$17$$ExternalSyntheticLambda4(this, this.val$context), null, new MsgMgr$17$$ExternalSyntheticLambda5(this, this.val$context), new MsgMgr$17$$ExternalSyntheticLambda6(this)};
         }
      });
      this.mParserArray.append(86, new Parser(this, "0x56 新能源设置信息4", var1) {
         List driverUpdateList;
         List settingUpdateList;
         final MsgMgr this$0;
         final Context val$context;

         {
            this.this$0 = var1;
            this.val$context = var3;
         }

         private int[] getRangeAndPercent(int var1) {
            return new int[]{var1 >> 6, var1 & 63};
         }

         void init() {
            ArrayList var1 = new ArrayList();
            this.settingUpdateList = var1;
            SettingUpdateEntity var2 = this.this$0.getSettingUpdateEntity("_30_set_charge_amount_1");
            Integer var3 = 5;
            var1.add(var2.setValue(var3).setEnable(false));
            this.settingUpdateList.add(this.this$0.getSettingUpdateEntity("_30_set_charge_amount_2").setValue(var3).setEnable(false));
            this.this$0.updateGeneralSettingData(this.settingUpdateList);
         }

         // $FF: synthetic method
         void lambda$setOnParseListeners$0$com_hzbhd_canbus_car__30_MsgMgr$18() {
            int[] var2 = this.getRangeAndPercent(this.this$0.mCanbusInfoInt[2] << 8 | this.this$0.mCanbusInfoInt[3]);
            this.settingUpdateList.add(this.this$0.getSettingUpdateEntity("_30_approximate_range_fast_charge").setValue(var2[0]));
            int var1 = var2[1] / 5;
            this.settingUpdateList.add(this.this$0.getSettingUpdateEntity("_30_set_charge_amount_1").setValue(var1).setProgress(var1 - 5));
         }

         // $FF: synthetic method
         void lambda$setOnParseListeners$1$com_hzbhd_canbus_car__30_MsgMgr$18() {
            int[] var2 = this.getRangeAndPercent(this.this$0.mCanbusInfoInt[4] << 8 | this.this$0.mCanbusInfoInt[5]);
            this.settingUpdateList.add(this.this$0.getSettingUpdateEntity("_30_approximate_range_normal").setValue(var2[0]));
            int var1 = var2[1] / 5;
            this.settingUpdateList.add(this.this$0.getSettingUpdateEntity("_30_set_charge_amount_2").setValue(var1).setProgress(var1 - 5));
         }

         // $FF: synthetic method
         void lambda$setOnParseListeners$2$com_hzbhd_canbus_car__30_MsgMgr$18(Context var1) {
            int var2 = this.this$0.mCanbusInfoInt[6] << 8 | this.this$0.mCanbusInfoInt[7];
            int var3 = var2 / 60;
            this.driverUpdateList.add(this.this$0.getDriveUpdateEntity("_30_estimated_charging_time_fast_charging").setValue(var3 + CommUtil.getStrByResId(var1, "unit_hour") + var2 % 60 + CommUtil.getStrByResId(var1, "unit_minute")));
         }

         // $FF: synthetic method
         void lambda$setOnParseListeners$3$com_hzbhd_canbus_car__30_MsgMgr$18() {
            this.driverUpdateList.add(this.this$0.getDriveUpdateEntity("_30_traditional_system").setValue((this.this$0.mCanbusInfoInt[8] << 8 | this.this$0.mCanbusInfoInt[9]) + " KW"));
         }

         // $FF: synthetic method
         void lambda$setOnParseListeners$4$com_hzbhd_canbus_car__30_MsgMgr$18() {
            this.driverUpdateList.add(this.this$0.getDriveUpdateEntity("_30_air_conditioning_system").setValue((float)(this.this$0.mCanbusInfoInt[10] << 8 | this.this$0.mCanbusInfoInt[11]) / 100.0F + " KW"));
         }

         // $FF: synthetic method
         void lambda$setOnParseListeners$5$com_hzbhd_canbus_car__30_MsgMgr$18() {
            this.driverUpdateList.add(this.this$0.getDriveUpdateEntity("_30_electronic_equipment").setValue((float)this.this$0.mCanbusInfoInt[12] / 100.0F + " KW"));
         }

         // $FF: synthetic method
         void lambda$setOnParseListeners$6$com_hzbhd_canbus_car__30_MsgMgr$18() {
            this.driverUpdateList.add(this.this$0.getDriveUpdateEntity("_30_battery_maintenance").setValue((float)this.this$0.mCanbusInfoInt[13] / 100.0F + " KW"));
         }

         public void parse(int var1) {
            MsgMgr var2 = this.this$0;
            if (var2.isDataChange(var2.mCanbusInfoInt)) {
               this.driverUpdateList = new ArrayList();
               this.settingUpdateList = new ArrayList();
               super.parse(var1);
               this.this$0.updateGeneralSettingData(this.settingUpdateList);
               this.this$0.updateSettingActivity((Bundle)null);
               this.this$0.updateGeneralDriveData(this.driverUpdateList);
               this.this$0.updateDriveDataActivity((Bundle)null);
            }

         }

         public OnParseListener[] setOnParseListeners() {
            return new OnParseListener[]{null, new MsgMgr$18$$ExternalSyntheticLambda0(this), null, new MsgMgr$18$$ExternalSyntheticLambda1(this), null, new MsgMgr$18$$ExternalSyntheticLambda2(this, this.val$context), null, new MsgMgr$18$$ExternalSyntheticLambda3(this), null, new MsgMgr$18$$ExternalSyntheticLambda4(this), new MsgMgr$18$$ExternalSyntheticLambda5(this), new MsgMgr$18$$ExternalSyntheticLambda6(this)};
         }
      });
      this.mParserArray.append(87, new Parser(this, "0x57 新能源设置信息5") {
         List driveDataUpdateList;
         List settingUpdateList;
         final MsgMgr this$0;

         {
            this.this$0 = var1;
         }

         // $FF: synthetic method
         static void lambda$setOnParseListeners$9() {
         }

         void init() {
            ArrayList var1 = new ArrayList();
            this.settingUpdateList = var1;
            var1.add(this.this$0.getSettingUpdateEntity("_30_maximum_speed_limit").setValue(8).setEnable(false));
            this.settingUpdateList.add(this.this$0.getSettingUpdateEntity("_30_taxi_energy_regeneration_1").setEnable(false));
            this.settingUpdateList.add(this.this$0.getSettingUpdateEntity("_30_air_conditioning_control_1").setEnable(false));
            this.settingUpdateList.add(this.this$0.getSettingUpdateEntity("_30_taxi_energy_regeneration_2").setEnable(false));
            this.settingUpdateList.add(this.this$0.getSettingUpdateEntity("_30_air_conditioning_control_2").setEnable(false));
            this.settingUpdateList.add(this.this$0.getSettingUpdateEntity("_30_taxi_energy_regeneration_3").setEnable(false));
            this.settingUpdateList.add(this.this$0.getSettingUpdateEntity("_30_air_conditioning_control_3").setEnable(false));
            this.this$0.updateGeneralSettingData(this.settingUpdateList);
         }

         // $FF: synthetic method
         void lambda$setOnParseListeners$0$com_hzbhd_canbus_car__30_MsgMgr$19() {
            this.driveDataUpdateList.add(this.this$0.getDriveUpdateEntity("_30_sport_mode_ceo").setValue(this.this$0.mCanbusInfoInt[2] + "%"));
         }

         // $FF: synthetic method
         void lambda$setOnParseListeners$1$com_hzbhd_canbus_car__30_MsgMgr$19() {
            this.driveDataUpdateList.add(this.this$0.getDriveUpdateEntity("_30_com_mode_ceo").setValue(this.this$0.mCanbusInfoInt[3] + "%"));
         }

         // $FF: synthetic method
         void lambda$setOnParseListeners$2$com_hzbhd_canbus_car__30_MsgMgr$19() {
            this.driveDataUpdateList.add(this.this$0.getDriveUpdateEntity("_30_eco_mode_ceo").setValue(this.this$0.mCanbusInfoInt[4] + "%"));
         }

         // $FF: synthetic method
         void lambda$setOnParseListeners$3$com_hzbhd_canbus_car__30_MsgMgr$19() {
            int var1 = this.this$0.mCanbusInfoInt[5] / 10;
            if (this.this$0.mCanbusInfoInt[5] == 254) {
               var1 = 8;
            }

            this.settingUpdateList.add(this.this$0.getSettingUpdateEntity("_30_maximum_speed_limit").setValue(var1).setProgress(var1 - 8));
         }

         // $FF: synthetic method
         void lambda$setOnParseListeners$4$com_hzbhd_canbus_car__30_MsgMgr$19() {
            this.settingUpdateList.add(this.this$0.getSettingUpdateEntity("_30_taxi_energy_regeneration_1").setValue(DataHandleUtils.getIntFromByteWithBit(this.this$0.mCanbusInfoInt[6], 6, 2)));
            this.settingUpdateList.add(this.this$0.getSettingUpdateEntity("_30_taxi_energy_regeneration_2").setValue(DataHandleUtils.getIntFromByteWithBit(this.this$0.mCanbusInfoInt[6], 4, 2)));
            this.settingUpdateList.add(this.this$0.getSettingUpdateEntity("_30_taxi_energy_regeneration_3").setValue(DataHandleUtils.getIntFromByteWithBit(this.this$0.mCanbusInfoInt[6], 2, 2)));
         }

         // $FF: synthetic method
         void lambda$setOnParseListeners$5$com_hzbhd_canbus_car__30_MsgMgr$19() {
            this.settingUpdateList.add(this.this$0.getSettingUpdateEntity("_30_air_conditioning_control_1").setValue(DataHandleUtils.getIntFromByteWithBit(this.this$0.mCanbusInfoInt[7], 5, 2)));
            this.settingUpdateList.add(this.this$0.getSettingUpdateEntity("_30_air_conditioning_control_2").setValue(DataHandleUtils.getIntFromByteWithBit(this.this$0.mCanbusInfoInt[7], 3, 2)));
            this.settingUpdateList.add(this.this$0.getSettingUpdateEntity("_30_air_conditioning_control_3").setValue(DataHandleUtils.getIntFromByteWithBit(this.this$0.mCanbusInfoInt[7], 1, 2)));
         }

         // $FF: synthetic method
         void lambda$setOnParseListeners$6$com_hzbhd_canbus_car__30_MsgMgr$19() {
            int var1 = this.this$0.mCanbusInfoInt[8];
            String var2;
            if (var1 != 0) {
               if (var1 != 1) {
                  if (var1 != 2) {
                     var2 = "";
                  } else {
                     var2 = "ECO";
                  }
               } else {
                  var2 = "SPORT";
               }
            } else {
               var2 = "COMFORT";
            }

            this.driveDataUpdateList.add(this.this$0.getDriveUpdateEntity("_143_setting_11").setValue(var2));
         }

         // $FF: synthetic method
         void lambda$setOnParseListeners$7$com_hzbhd_canbus_car__30_MsgMgr$19() {
            int var1 = this.this$0.mCanbusInfoInt[9];
            int var2 = this.this$0.mCanbusInfoInt[10];
            this.driveDataUpdateList.add(this.this$0.getDriveUpdateEntity("_30_eco_driving_distance").setValue((var1 << 8 | var2) + " KM"));
         }

         // $FF: synthetic method
         void lambda$setOnParseListeners$8$com_hzbhd_canbus_car__30_MsgMgr$19() {
            int var1 = this.this$0.mCanbusInfoInt[11];
            int var2 = this.this$0.mCanbusInfoInt[12];
            this.driveDataUpdateList.add(this.this$0.getDriveUpdateEntity("_30_carbon_dioxide_emission_reduction").setValue((float)(var1 << 8 | var2) / 10.0F + " Kg"));
         }

         public void parse(int var1) {
            MsgMgr var2 = this.this$0;
            if (var2.isDataChange(var2.mCanbusInfoInt)) {
               this.driveDataUpdateList = new ArrayList();
               this.settingUpdateList = new ArrayList();
               super.parse(var1);
               this.this$0.updateGeneralSettingData(this.settingUpdateList);
               this.this$0.updateSettingActivity((Bundle)null);
               this.this$0.updateGeneralDriveData(this.driveDataUpdateList);
               this.this$0.updateDriveDataActivity((Bundle)null);
            }

         }

         public OnParseListener[] setOnParseListeners() {
            return new OnParseListener[]{new MsgMgr$19$$ExternalSyntheticLambda0(this), new MsgMgr$19$$ExternalSyntheticLambda1(this), new MsgMgr$19$$ExternalSyntheticLambda2(this), new MsgMgr$19$$ExternalSyntheticLambda3(this), new MsgMgr$19$$ExternalSyntheticLambda4(this), new MsgMgr$19$$ExternalSyntheticLambda5(this), new MsgMgr$19$$ExternalSyntheticLambda6(this), null, new MsgMgr$19$$ExternalSyntheticLambda7(this), null, new MsgMgr$19$$ExternalSyntheticLambda8(this), new MsgMgr$19$$ExternalSyntheticLambda9()};
         }
      });
      this.mParserArray.append(255, new Parser(this, "0xFF") {
         final MsgMgr this$0;

         {
            this.this$0 = var1;
         }

         // $FF: synthetic method
         static void lambda$setOnParseListeners$0() {
         }

         // $FF: synthetic method
         static void lambda$setOnParseListeners$1() {
         }

         public void parse(int var1) {
            super.parse(var1);
         }

         public OnParseListener[] setOnParseListeners() {
            return new OnParseListener[]{new MsgMgr$20$$ExternalSyntheticLambda0(), new MsgMgr$20$$ExternalSyntheticLambda1()};
         }
      });
   }

   private void initPgae() {
      this.mSettingPage = new Page(this, "settings", new Page[]{new Page(this, "_30_new_energy_53_0", new Page[]{new Page(this, "_30_start_time_1"), new Page(this, "_30_end_time_1"), new Page(this, "_30_new_energy_repeat_1", new Page[]{new Page(this, "_30_new_energy_sunday_1"), new Page(this, "_30_new_energy_monday_1"), new Page(this, "_30_new_energy_tuesday_1"), new Page(this, "_30_new_energy_wednesday_1"), new Page(this, "_30_new_energy_thursday_1"), new Page(this, "_30_new_energy_friday_1"), new Page(this, "_30_new_energy_saturday_1")})}), new Page(this, "_30_new_energy_53_B_1", new Page[]{new Page(this, "_30_new_energy_53_4"), new Page(this, "_30_new_energy_repeat_2", new Page[]{new Page(this, "_30_new_energy_sunday_2"), new Page(this, "_30_new_energy_monday_2"), new Page(this, "_30_new_energy_tuesday_2"), new Page(this, "_30_new_energy_wednesday_2"), new Page(this, "_30_new_energy_thursday_2"), new Page(this, "_30_new_energy_friday_2"), new Page(this, "_30_new_energy_saturday_2")})}), new Page(this, "_30_new_energy_53_B_2", new Page[]{new Page(this, "_30_new_energy_53_6"), new Page(this, "_30_new_energy_repeat_3", new Page[]{new Page(this, "_30_new_energy_sunday_3"), new Page(this, "_30_new_energy_monday_3"), new Page(this, "_30_new_energy_tuesday_3"), new Page(this, "_30_new_energy_wednesday_3"), new Page(this, "_30_new_energy_thursday_3"), new Page(this, "_30_new_energy_friday_3"), new Page(this, "_30_new_energy_saturday_3")})}), new Page(this, "_30_new_energy_53_B_3", new Page[]{new Page(this, "_30_start_time_2"), new Page(this, "_30_end_time_2")}), new Page(this, "_30_new_energy_53_C_1", new Page[]{new Page(this, "_30_new_energy_53_C_10")}), new Page(this, "_30_approximate_range_fast_charge", new Page[]{new Page(this, "_30_set_charge_amount_1")}), new Page(this, "_30_approximate_range_normal", new Page[]{new Page(this, "_30_set_charge_amount_2")}), new Page(this, "_30_eco_mode", new Page[]{new Page(this, "_30_maximum_speed_limit"), new Page(this, "_30_taxi_energy_regeneration_1"), new Page(this, "_30_air_conditioning_control_1")}), new Page(this, "_30_com_mode", new Page[]{new Page(this, "_30_taxi_energy_regeneration_2"), new Page(this, "_30_air_conditioning_control_2")}), new Page(this, "_30_sport_mode", new Page[]{new Page(this, "_30_taxi_energy_regeneration_3"), new Page(this, "_30_air_conditioning_control_3")})});
   }

   private void initSettingsItem(Context var1) {
      Log.i("_1030_MsgMgr", "initSettingsItem: ");
      this.mSettingItemIndeHashMap = new HashMap();
      SparseArray var6 = new SparseArray();
      List var5 = this.getUiMgr(var1).getSettingUiSet(var1).getList();

      for(int var2 = 0; var2 < var5.size(); ++var2) {
         List var7 = ((SettingPageUiSet.ListBean)var5.get(var2)).getItemList();

         for(int var3 = 0; var3 < var7.size(); ++var3) {
            String var4 = ((SettingPageUiSet.ListBean.ItemListBean)var7.get(var3)).getTitleSrn();
            var6.append(var2 << 8 | var3, var4);
            this.mSettingItemIndeHashMap.put(var4, new SettingUpdateEntity(var2, var3, (Object)null));
         }
      }

      this.getUiMgr(var1).setSettingsItemArray(var6);
   }

   private void initVehicleType() {
      int var1 = this.mDifferent;
      if (var1 != 20) {
         if (var1 != 21) {
            switch (var1) {
               case 1:
                  this.is1819KiaSportage = true;
                  break;
               case 2:
                  this.is19Lafesta = true;
                  break;
               case 3:
                  this.is19Santafe = true;
                  break;
               case 4:
                  this.is1920Tucson = true;
                  break;
               case 5:
                  this.isKx5Top = true;
                  break;
               case 6:
                  this.is19KiaK3 = true;
                  break;
               case 7:
                  this.is18SonataHybrid = true;
                  break;
               case 8:
                  this.is20K3 = true;
                  break;
               case 9:
                  this.is19K3NewEnergy = true;
                  break;
               case 10:
                  this.is19K5NewEnergy = true;
                  break;
               case 11:
                  this.is20EncinoElectrical = true;
            }
         } else {
            this.is15Carnival = true;
         }
      } else {
         this.isK4 = true;
      }

   }

   private boolean isDoorStatusChange() {
      if (!(this.mIsLeftFrontOpen ^ GeneralDoorData.isLeftFrontDoorOpen) && !(this.mIsRightFrontOpen ^ GeneralDoorData.isRightFrontDoorOpen) && !(this.mIsLeftRearOpen ^ GeneralDoorData.isLeftRearDoorOpen) && !(this.mIsRightRearOpen ^ GeneralDoorData.isRightRearDoorOpen) && !(this.mIsBackOpen ^ GeneralDoorData.isBackOpen) && !(this.mIsFrontOpen ^ GeneralDoorData.isFrontOpen)) {
         return false;
      } else {
         this.mIsLeftFrontOpen = GeneralDoorData.isLeftFrontDoorOpen;
         this.mIsRightFrontOpen = GeneralDoorData.isRightFrontDoorOpen;
         this.mIsLeftRearOpen = GeneralDoorData.isLeftRearDoorOpen;
         this.mIsRightRearOpen = GeneralDoorData.isRightRearDoorOpen;
         this.mIsBackOpen = GeneralDoorData.isBackOpen;
         this.mIsFrontOpen = GeneralDoorData.isFrontOpen;
         return true;
      }
   }

   // $FF: synthetic method
   static void lambda$new$0() {
      CanbusMsgSender.sendMsg(new byte[]{22, -112, 127, 0});
   }

   protected static List mergeList(List... var0) {
      int var1 = 0;
      Class var3 = var0[0].getClass();

      List var5;
      try {
         var5 = (List)var3.newInstance();
      } catch (Exception var4) {
         var4.printStackTrace();
         var5 = null;
      }

      for(int var2 = var0.length; var1 < var2; ++var1) {
         var5.addAll(var0[var1]);
      }

      return var5;
   }

   private void realKeyClick3_1(Context var1, int var2) {
      this.realKeyClick3_1(var1, var2, 0, this.mCanbusInfoInt[3]);
   }

   private void realKeyClick3_2(Context var1, int var2) {
      this.realKeyClick3_2(var1, var2, 0, this.mCanbusInfoInt[3]);
   }

   private void realKeyLongClick1(Context var1, int var2) {
      this.realKeyLongClick1(var1, var2, this.mCanbusInfoInt[3]);
   }

   private void set0x7D0x08TrackData(Context var1) {
      if (this.isDataChange(this.mCanbusInfoInt)) {
         byte[] var2 = this.mCanbusInfoByte;
         GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(var2[3], var2[4], 0, 540, 16);
         this.updateParkUi((Bundle)null, var1);
      }

   }

   private void setTireInfo() {
      this.MTire0.add(this.getTireEntity(0, this.TirePressure1000, this.TireTempture1000, this.TireSense1000, this.BatteryStatus1000, this.SenseTempture1000, this.SensePressure1000, this.Leak1000));
      this.MTire1.add(this.getTireEntity(1, this.TirePressure0100, this.TireTempture0100, this.TireSense0100, this.BatteryStatus0100, this.SenseTempture0100, this.SensePressure0100, this.Leak0100));
      this.MTire2.add(this.getTireEntity(2, this.TirePressure0010, this.TireTempture0010, this.TireSense0010, this.BatteryStatus0010, this.SenseTempture0010, this.SensePressure0010, this.Leak0010));
      this.MTire3.add(this.getTireEntity(3, this.TirePressure0001, this.TireTempture0001, this.TireSense0001, this.BatteryStatus0001, this.SenseTempture0001, this.SensePressure0001, this.Leak0001));
      GeneralTireData.dataList = mergeList(this.MTire0, this.MTire1, this.MTire2, this.MTire3);
      this.updateTirePressureActivity((Bundle)null);
   }

   private void setTirePresAndTemp0x08() {
      this.decimalFormat = new DecimalFormat("0.0");
      this.TirePressure1000 = this.decimalFormat.format((double)this.mCanbusInfoInt[2] * 1.378 + 100.0) + "Kpa";
      this.TirePressure0100 = this.decimalFormat.format((double)this.mCanbusInfoInt[4] * 1.378 + 100.0) + "Kpa";
      this.TirePressure0010 = this.decimalFormat.format((double)this.mCanbusInfoInt[6] * 1.378 + 100.0) + "Kpa";
      this.TirePressure0001 = this.decimalFormat.format((double)this.mCanbusInfoInt[8] * 1.378 + 100.0) + "Kpa";
      this.TireTempture1000 = this.decimalFormat.format((long)(this.mCanbusInfoInt[3] * 1 - 40)) + this.getTempUnitC(this.mContext);
      this.TireTempture0100 = this.decimalFormat.format((long)(this.mCanbusInfoInt[5] * 1 - 40)) + this.getTempUnitC(this.mContext);
      this.TireTempture0010 = this.decimalFormat.format((long)(this.mCanbusInfoInt[7] * 1 - 40)) + this.getTempUnitC(this.mContext);
      this.TireTempture0001 = this.decimalFormat.format((long)(this.mCanbusInfoInt[9] * 1 - 40)) + this.getTempUnitC(this.mContext);
      this.setTireInfo();
   }

   private void setTireSenseStatus0x09() {
      this.TireSense0001 = DataHandleUtils.getBoolBit3(this.mCanbusInfoInt[3]);
      this.TireSense0010 = DataHandleUtils.getBoolBit2(this.mCanbusInfoInt[3]);
      this.TireSense0100 = DataHandleUtils.getBoolBit1(this.mCanbusInfoInt[3]);
      this.TireSense1000 = DataHandleUtils.getBoolBit0(this.mCanbusInfoInt[3]);
      this.BatteryStatus1000 = DataHandleUtils.getBoolBit7(this.mCanbusInfoInt[5]);
      this.BatteryStatus0100 = DataHandleUtils.getBoolBit7(this.mCanbusInfoInt[6]);
      this.BatteryStatus0010 = DataHandleUtils.getBoolBit7(this.mCanbusInfoInt[7]);
      this.BatteryStatus0001 = DataHandleUtils.getBoolBit7(this.mCanbusInfoInt[8]);
      this.SenseTempture1000 = DataHandleUtils.getIntFromByteWithBit(this.mCanbusInfoInt[5], 4, 3);
      this.SenseTempture0100 = DataHandleUtils.getIntFromByteWithBit(this.mCanbusInfoInt[6], 4, 3);
      this.SenseTempture0010 = DataHandleUtils.getIntFromByteWithBit(this.mCanbusInfoInt[7], 4, 3);
      this.SenseTempture0001 = DataHandleUtils.getIntFromByteWithBit(this.mCanbusInfoInt[8], 4, 3);
      this.SensePressure1000 = DataHandleUtils.getIntFromByteWithBit(this.mCanbusInfoInt[5], 1, 3);
      this.SensePressure0100 = DataHandleUtils.getIntFromByteWithBit(this.mCanbusInfoInt[6], 1, 3);
      this.SensePressure0010 = DataHandleUtils.getIntFromByteWithBit(this.mCanbusInfoInt[7], 1, 3);
      this.SensePressure0001 = DataHandleUtils.getIntFromByteWithBit(this.mCanbusInfoInt[8], 1, 3);
      this.Leak1000 = DataHandleUtils.getBoolBit0(this.mCanbusInfoInt[5]);
      this.Leak0100 = DataHandleUtils.getBoolBit0(this.mCanbusInfoInt[6]);
      this.Leak0010 = DataHandleUtils.getBoolBit0(this.mCanbusInfoInt[7]);
      this.Leak0001 = DataHandleUtils.getBoolBit0(this.mCanbusInfoInt[8]);
      this.setTireInfo();
   }

   private void switchPanoramic(Context var1) {
      this.forceReverse(var1, CommUtil.isPanoramic(var1) ^ true);
   }

   public void afterServiceNormalSetting(Context var1) {
      super.afterServiceNormalSetting(var1);
      this.mContext = var1;
      this.mCanId = SelectCanTypeUtil.getCurrentCanTypeId(var1);
      this.mDifferent = this.getCurrentCanDifferentId();
      this.mEachId = this.getCurrentEachCanId();
      this.initVehicleType();
      this.initSettingsItem(var1);
      this.initDriveItem(var1);
      this.mCanbusDataArray = new SparseArray();
      this.initParser(var1);
      this.mSeatStatusView = new SeatStatusView(var1);
      this.initPgae();
   }

   public void atvInfoChange() {
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.ATV.name(), new byte[]{22, 9, 5, 0, 0});
   }

   public void auxInInfoChange() {
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.AUX1.name(), new byte[]{22, 9, 18, 0, 0});
   }

   public void btMusicInfoChange() {
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.BTAUDIO.name(), new byte[]{22, 9, 11, 0});
   }

   public void btPhoneHangUpInfoChange(byte[] var1, boolean var2, boolean var3) {
      CanbusMsgSender.sendMsg(new byte[]{22, 9, 14, 6});
   }

   public void btPhoneIncomingInfoChange(byte[] var1, boolean var2, boolean var3) {
      CanbusMsgSender.sendMsg(new byte[]{22, 9, 14, 1});
   }

   public void btPhoneOutGoingInfoChange(byte[] var1, boolean var2, boolean var3) {
      CanbusMsgSender.sendMsg(new byte[]{22, 9, 14, 3});
   }

   public void btPhoneStatusInfoChange(int var1, byte[] var2, boolean var3, boolean var4, boolean var5, boolean var6, int var7, int var8, Bundle var9) {
      if (var3) {
         if (var1 == 0) {
            CanbusMsgSender.sendMsg(new byte[]{22, 9, 14, 4});
         }
      } else {
         CanbusMsgSender.sendMsg(new byte[]{22, 9, 14, 5});
      }

   }

   public void btPhoneTalkingInfoChange(byte[] var1, boolean var2, boolean var3) {
      CanbusMsgSender.sendMsg(new byte[]{22, 9, 14, 2});
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      int[] var4 = this.getByteArrayToIntArray(var2);
      this.mCanbusInfoInt = var4;
      this.mCanbusInfoByte = var2;
      if (this.mParserArray.get(var4[1]) != null) {
         ((Parser)this.mParserArray.get(this.mCanbusInfoInt[1])).parse(this.mCanbusInfoInt.length - 2);
      }

      int[] var5 = this.mCanbusInfoInt;
      if (var5[1] == 125 && var5[2] == 8) {
         this.set0x7D0x08TrackData(var1);
      }

      int var3 = this.mCanbusInfoInt[1];
      if (var3 != 8) {
         if (var3 == 9) {
            this.setTireSenseStatus0x09();
         }
      } else {
         this.setTirePresAndTemp0x08();
      }

   }

   public void currentVolumeInfoChange(int var1, boolean var2) {
      var1 = (int)((float)var1 / 40.0F * 35.0F);
      this.mVolume = var1;
      CanbusMsgSender.sendMsg(new byte[]{22, 5, (byte)var1});
   }

   public void dateTimeRepCanbus(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, boolean var10, boolean var11, boolean var12, int var13) {
      CanbusMsgSender.sendMsg(new byte[]{22, 6, (byte)var6, (byte)var8, (byte)var10});
      this.mHandler.postDelayed(this.mVersionRequestRunnable, 1000L);
      this.sendCarInfo();
   }

   public void discInfoChange(byte var1, int var2, int var3, int var4, int var5, int var6, int var7, boolean var8, boolean var9, int var10, String var11, String var12, String var13) {
      if (var7 != 1 && var7 != 5) {
         var1 = 19;
      } else {
         var4 = var5;
         var1 = 1;
      }

      int[] var20 = this.getTime(var2);
      byte var19 = (byte)var1;
      byte var17 = (byte)(var4 >> 8);
      byte var16 = (byte)(var4 & 255);
      byte var18 = (byte)var20[0];
      byte var15 = (byte)var20[1];
      byte var14 = (byte)var20[2];
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MPEG.name(), new byte[]{22, 9, var19, var17, var16, var18, var15, var14});
   }

   public void dtvInfoChange() {
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.DTV.name(), new byte[]{22, 9, 5, 0, 0});
   }

   int[] getMsgDatas(int var1) {
      SparseArray var2 = this.mCanbusDataArray;
      return var2 == null ? null : (int[])var2.get(var1);
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      this.initAmplifier(var1);
      this.sendCarInfo();
      this.sendLanguageInit(var1);
      GeneralTireData.isHaveSpareTire = false;
   }

   boolean is15Carnival() {
      return this.is15Carnival;
   }

   boolean is1819KiaSportage() {
      return this.is1819KiaSportage;
   }

   boolean is18SonataHybrid() {
      return this.is18SonataHybrid;
   }

   boolean is1920Tucson() {
      return this.is1920Tucson;
   }

   boolean is19K3NewEnergy() {
      return this.is19K3NewEnergy;
   }

   boolean is19K5NewEnergy() {
      return this.is19K5NewEnergy;
   }

   boolean is19KiaK3() {
      return this.is19KiaK3;
   }

   boolean is19Lafesta() {
      return this.is19Lafesta;
   }

   boolean is19Santafe() {
      return this.is19Santafe;
   }

   boolean is20EncinoElectrical() {
      return this.is20EncinoElectrical;
   }

   boolean is20K3() {
      return this.is20K3;
   }

   boolean isK4() {
      return this.isK4;
   }

   boolean isKx5Top() {
      return this.isKx5Top;
   }

   public void musicInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, byte var8, byte var9, byte var10, String var11, String var12, String var13, long var14, byte var16, int var17, boolean var18, long var19, String var21, String var22, String var23, boolean var24) {
      if (var1 == 9) {
         var1 = 12;
      } else {
         var1 = 22;
      }

      byte var25 = (byte)var1;
      byte var26 = (byte)var3;
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MUSIC.name(), new byte[]{22, 9, var25, var9, var26, var5, var6, var7});
   }

   void pageSwitch(String... var1) {
      if (!ArrayUtils.isEmpty(var1)) {
         Page var3 = this.mSettingPage.getChildByTitle(var1[0]);

         for(int var2 = 1; var2 < var1.length; ++var2) {
            var3 = var3.getChildByTitle(var1[var2]);
         }

         if (var3 != null) {
            List var4 = var3.onSwitch();
            if (var4 != null) {
               this.updateGeneralSettingData(var4);
               this.updateSettingActivity((Bundle)null);
            }
         }

      }
   }

   public void radioInfoChange(int var1, String var2, String var3, String var4, int var5) {
      byte var12 = var2.equals("FM2");
      if (var2.equals("FM3")) {
         var12 = 2;
      }

      byte var14;
      if (var2.contains("AM")) {
         var12 = 3;
         var14 = 9;
      } else {
         var14 = 2;
      }

      if (var2.equals("AM1")) {
         var12 = 4;
      }

      boolean var13 = var2.equals("LW");
      byte var15 = 10;
      if (var13) {
         var12 = 5;
         var14 = 10;
      }

      if (var2.equals("WB")) {
         var12 = 6;
         var14 = var15;
      }

      float var11 = Float.parseFloat(var3);
      float var10 = var11;
      if (var2.contains("FM")) {
         var10 = var11 * 100.0F;
      }

      byte var9 = (byte)var14;
      byte var7 = (byte)var12;
      byte var6 = (byte)((int)(var10 / 100.0F));
      byte var8 = (byte)((int)(var10 % 100.0F));
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.FM.name(), new byte[]{22, 9, var9, var7, var6, var8});
   }

   void saveAmplifierData(Context var1) {
      this.saveAmplifierData(var1, this.mCanId);
   }

   public void sendCarInfo() {
      if (this.mEachId == 1) {
         CanbusMsgSender.sendMsg(new byte[]{22, -18, 32, 1});
      }

      if (this.mEachId == 2) {
         CanbusMsgSender.sendMsg(new byte[]{22, -18, 32, 2});
      }

      if (this.mEachId == 3) {
         CanbusMsgSender.sendMsg(new byte[]{22, -18, 32, 3});
      }

      if (this.mEachId == 4) {
         CanbusMsgSender.sendMsg(new byte[]{22, -18, 32, 4});
      }

      if (this.mEachId == 129) {
         CanbusMsgSender.sendMsg(new byte[]{22, -18, 32, -127});
      }

      if (this.mEachId == 5) {
         CanbusMsgSender.sendMsg(new byte[]{22, -18, 32, 5});
      }

      if (this.mEachId == 6) {
         CanbusMsgSender.sendMsg(new byte[]{22, -18, 32, 6});
      }

      if (this.mEachId == 7) {
         CanbusMsgSender.sendMsg(new byte[]{22, -18, 32, 7});
      }

      if (this.mEachId == 8) {
         CanbusMsgSender.sendMsg(new byte[]{22, -18, 32, 8});
      }

      if (this.mEachId == 9) {
         CanbusMsgSender.sendMsg(new byte[]{22, -18, 32, 9});
      }

      if (this.mEachId == 10) {
         CanbusMsgSender.sendMsg(new byte[]{22, -18, 32, 10});
      }

      if (this.mEachId == 11) {
         CanbusMsgSender.sendMsg(new byte[]{22, -18, 32, 11});
      }

      if (this.mEachId == 12) {
         CanbusMsgSender.sendMsg(new byte[]{22, -18, 32, 12});
      }

      if (this.mEachId == 13) {
         CanbusMsgSender.sendMsg(new byte[]{22, -18, 32, 13});
      }

      if (this.mEachId == 15) {
         CanbusMsgSender.sendMsg(new byte[]{22, -18, 32, -124});
      }

      if (this.mEachId == 18) {
         CanbusMsgSender.sendMsg(new byte[]{22, -18, 32, 14});
      }

      if (this.mEachId == 19) {
         CanbusMsgSender.sendMsg(new byte[]{22, -18, 32, 15});
      }

      if (this.mEachId == 20) {
         CanbusMsgSender.sendMsg(new byte[]{22, -18, 32, 16});
      }

      if (this.mEachId == 21) {
         CanbusMsgSender.sendMsg(new byte[]{22, -18, 32, 17});
      }

      if (this.mEachId == 23) {
         CanbusMsgSender.sendMsg(new byte[]{22, -18, 32, 18});
      }

      if (this.mEachId == 22) {
         CanbusMsgSender.sendMsg(new byte[]{22, -18, 32, 19});
      }

   }

   public void sendLanguageInit(Context var1) {
      SettingUpdateEntity var5 = this.getSettingUpdateEntity("_306_value_11");
      int var2 = var5.getLeftListIndex();
      int var4 = var5.getRightListIndex();
      int var3 = SharePreUtil.getIntValue(var1, "share_30_language", 0);
      this.getUiMgr(var1).getSettingUiSet(var1).getOnSettingItemSelectListener().onClickItem(var2, var4, var3);
   }

   public void sourceSwitchChange(String var1) {
      super.sourceSwitchChange(var1);
      if (SourceConstantsDef.SOURCE_ID.NULL.name().equals(var1)) {
         this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.NORMAL_SOURCE.name(), new byte[]{22, 9, -126, 0, 0});
      }

   }

   public void sourceSwitchNoMediaInfoChange(boolean var1) {
      super.sourceSwitchNoMediaInfoChange(var1);
      if (!var1) {
         this.initAmplifier(this.mContext);
      }

   }

   void updateSettingItem(String var1, Object var2) {
      ArrayList var3 = new ArrayList();
      var3.add(this.getSettingUpdateEntity(var1).setValue(var2));
      this.updateGeneralSettingData(var3);
      this.updateSettingActivity((Bundle)null);
   }

   public void videoInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, String var8, byte var9, byte var10, String var11, String var12, String var13, int var14, byte var15, boolean var16, int var17) {
      if (var1 == 9) {
         var1 = 12;
      } else {
         var1 = 21;
      }

      byte var18 = (byte)var1;
      byte var19 = (byte)var3;
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.VIDEO.name(), new byte[]{22, 9, var18, var9, var19, var5, var6, var7});
   }

   private interface OnParseListener {
      void onParse();
   }

   private class Page {
      private Page[] childs;
      final MsgMgr this$0;
      private final String title;

      public Page(MsgMgr var1, String var2) {
         this.this$0 = var1;
         this.title = var2;
      }

      public Page(MsgMgr var1, String var2, Page[] var3) {
         this.this$0 = var1;
         this.title = var2;
         this.childs = var3;
      }

      public Page getChildByTitle(String var1) {
         Page[] var4 = this.childs;
         if (var4 != null) {
            int var3 = var4.length;

            for(int var2 = 0; var2 < var3; ++var2) {
               Page var5 = var4[var2];
               if (var5.getTitle().equals(var1)) {
                  return var5;
               }
            }
         }

         return null;
      }

      public String getTitle() {
         return this.title;
      }

      public List onSwitch() {
         if (this.childs == null) {
            return null;
         } else {
            ArrayList var3 = new ArrayList();
            Page[] var4 = this.childs;
            int var2 = var4.length;

            for(int var1 = 0; var1 < var2; ++var1) {
               Page var5 = var4[var1];
               SettingUpdateEntity var6 = this.this$0.getSettingUpdateEntity(var5.getTitle());
               var3.add(var6.setEnable(var6.isEnable() ^ true));
            }

            return var3;
         }
      }

      public List setChildsEnable(boolean var1) {
         if (this.childs != null) {
            ArrayList var4 = new ArrayList();
            Page[] var5 = this.childs;
            int var3 = var5.length;

            for(int var2 = 0; var2 < var3; ++var2) {
               Page var6 = var5[var2];
               SettingUpdateEntity var7 = this.this$0.getSettingUpdateEntity(var6.getTitle());
               if (var7.isEnable() ^ var1) {
                  var4.add(var7.setEnable(var1));
                  if (!var1) {
                     List var8 = var6.setChildsEnable(var1);
                     if (var8 != null) {
                        var4.addAll(var8);
                     }
                  }
               }
            }

            return var4;
         } else {
            return null;
         }
      }
   }

   private abstract class Parser {
      final String MSG;
      private final int PARSE_LISTENERS_LENGTH;
      final OnParseListener[] onParseListeners;
      final MsgMgr this$0;

      public Parser(MsgMgr var1, String var2) {
         this.this$0 = var1;
         this.MSG = var2;
         this.init();
         OnParseListener[] var3 = this.setOnParseListeners();
         this.onParseListeners = var3;
         if (var3 == null) {
            this.PARSE_LISTENERS_LENGTH = 0;
         } else {
            this.PARSE_LISTENERS_LENGTH = var3.length;
         }

         Log.i("_1030_MsgMgr", "Parser: " + var2 + " length " + this.PARSE_LISTENERS_LENGTH);
      }

      void init() {
      }

      public void log(String var1) {
         Log.i("_1030_MsgMgr", this.MSG + "<-->" + var1);
      }

      public void parse(int var1) {
         for(var1 = Math.min(var1, this.PARSE_LISTENERS_LENGTH); var1 > 0; --var1) {
            OnParseListener var2 = this.onParseListeners[var1 - 1];
            if (var2 != null) {
               var2.onParse();
            }
         }

      }

      public abstract OnParseListener[] setOnParseListeners();
   }

   private static class SeatStatusView {
      private boolean isShowing;
      private final Runnable mDismissRunnable;
      private RelativeLayout mFloatView;
      private ViewHelper mIvBackrestBack;
      private ViewHelper mIvBackrestDown;
      private ViewHelper mIvBackrestForward;
      private ViewHelper mIvBackrestSelelct;
      private ViewHelper mIvBackrestUp;
      private ViewHelper mIvSeatBack;
      private ViewHelper mIvSeatDown;
      private ViewHelper mIvSeatForward;
      private ViewHelper mIvSeatSelect;
      private ViewHelper mIvSeatUp;
      private WindowManager.LayoutParams mLayoutParams;
      private final WindowManager mWindowManager;

      public SeatStatusView(Context var1) {
         this.mWindowManager = (WindowManager)var1.getSystemService("window");
         this.mDismissRunnable = new Runnable(this) {
            final SeatStatusView this$0;

            {
               this.this$0 = var1;
            }

            public void run() {
               this.this$0.dismissView();
            }
         };
         this.findView(var1);
         this.initView();
      }

      private void dismissView() {
         WindowManager var1 = this.mWindowManager;
         if (var1 != null) {
            RelativeLayout var2 = this.mFloatView;
            if (var2 != null) {
               var1.removeView(var2);
               this.isShowing = false;
            }
         }

      }

      private void findView(Context var1) {
         this.mFloatView = (RelativeLayout)LayoutInflater.from(var1).inflate(2131558744, (ViewGroup)null);
         this.mIvSeatSelect = new ViewHelper(this, this.mFloatView.findViewById(2131362638));
         this.mIvBackrestSelelct = new ViewHelper(this, this.mFloatView.findViewById(2131362539));
         this.mIvSeatForward = new ViewHelper(this, this.mFloatView.findViewById(2131362637));
         this.mIvSeatBack = new ViewHelper(this, this.mFloatView.findViewById(2131362635));
         this.mIvSeatUp = new ViewHelper(this, this.mFloatView.findViewById(2131362639));
         this.mIvSeatDown = new ViewHelper(this, this.mFloatView.findViewById(2131362636));
         this.mIvBackrestUp = new ViewHelper(this, this.mFloatView.findViewById(2131362540));
         this.mIvBackrestDown = new ViewHelper(this, this.mFloatView.findViewById(2131362537));
         this.mIvBackrestForward = new ViewHelper(this, this.mFloatView.findViewById(2131362538));
         this.mIvBackrestBack = new ViewHelper(this, this.mFloatView.findViewById(2131362536));
      }

      private void initView() {
         this.mFloatView.setOnClickListener(new View.OnClickListener(this) {
            final SeatStatusView this$0;

            {
               this.this$0 = var1;
            }

            public void onClick(View var1) {
               this.this$0.mFloatView.removeCallbacks(this.this$0.mDismissRunnable);
               this.this$0.mFloatView.post(this.this$0.mDismissRunnable);
            }
         });
      }

      public void addViewToWindow() {
         if (this.mLayoutParams == null) {
            WindowManager.LayoutParams var1 = new WindowManager.LayoutParams();
            this.mLayoutParams = var1;
            var1.type = 2002;
            this.mLayoutParams.gravity = 17;
            this.mLayoutParams.width = -2;
            this.mLayoutParams.height = -2;
         }

         if (!this.isShowing) {
            this.mWindowManager.addView(this.mFloatView, this.mLayoutParams);
            this.isShowing = true;
         }

         this.mFloatView.removeCallbacks(this.mDismissRunnable);
         this.mFloatView.postDelayed(this.mDismissRunnable, 5000L);
      }

      public void setBackrestBackward(boolean var1) {
         this.mIvBackrestBack.setShowView(var1);
      }

      public void setBackrestDown(boolean var1) {
         this.mIvBackrestDown.setShowView(var1);
      }

      public void setBackrestForward(boolean var1) {
         this.mIvBackrestForward.setShowView(var1);
      }

      public void setBackrestSelelct(boolean var1) {
         this.mIvBackrestSelelct.setShowView(var1);
      }

      public void setBackrestUp(boolean var1) {
         this.mIvBackrestUp.setShowView(var1);
      }

      public void setSeatBack(boolean var1) {
         this.mIvSeatBack.setShowView(var1);
      }

      public void setSeatDown(boolean var1) {
         this.mIvSeatDown.setShowView(var1);
      }

      public void setSeatForward(boolean var1) {
         this.mIvSeatForward.setShowView(var1);
      }

      public void setSeatSelect(boolean var1) {
         this.mIvSeatSelect.setShowView(var1);
      }

      public void setSeatUp(boolean var1) {
         this.mIvSeatUp.setShowView(var1);
      }

      private class ViewHelper {
         final SeatStatusView this$0;
         private final View view;

         public ViewHelper(SeatStatusView var1, View var2) {
            this.this$0 = var1;
            this.view = var2;
         }

         public void setShowView(boolean var1) {
            View var3 = this.view;
            byte var2;
            if (var1) {
               var2 = 0;
            } else {
               var2 = 4;
            }

            var3.setVisibility(var2);
         }
      }
   }

   private interface Sentence {
      void execute();
   }
}
