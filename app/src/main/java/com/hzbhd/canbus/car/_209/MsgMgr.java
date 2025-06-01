package com.hzbhd.canbus.car._209;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.OriginalCarDeviceUpdateEntity;
import com.hzbhd.canbus.entity.PanoramicBtnUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.entity.SongListEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralOriginalCarDeviceData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
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
import java.util.List;
import java.util.TimerTask;

public class MsgMgr extends AbstractMsgMgr {
   private int[] m0x16Data;
   private int[] m0x22Data;
   private int[] m0x23Data;
   private int[] m0x25Data;
   private int[] m0x29Data;
   private int[] m0x30Data;
   private int[] m0x32Data;
   private int[] m0x33Data;
   private int[] m0x33DataIndexOne;
   private int[] m0x33DataIndexTwo;
   private int[] m0x70Data;
   private int[] m0x71Data;
   private int m0xC5Data1;
   private int[] m0xD1Data;
   private Thread m0xD1ResolveThread;
   private int[] m0xD2Data;
   private int[] m0xD3Data;
   private List mAmPresetFrequencyList;
   private List mAmValidFrequencyList;
   private boolean mBackStatus;
   private boolean mCameraStatus;
   private byte[] mCanBusInfoByte;
   private int[] mCanBusInfoInt;
   private Context mContext;
   private DecimalFormat mDecimalFormat0P0;
   private DecimalFormat mDecimalFormat0P00;
   private List mFmPresetFrequencyList;
   private List mFmValidFrequencyList;
   private boolean mFrontStatus;
   private boolean mFrontViewBtnStatus;
   private ID3[] mId3s;
   private boolean mIsDoorFirst = true;
   private boolean mLeftFrontRec;
   private boolean mLeftFrontStatus;
   private boolean mLeftRearRec;
   private boolean mLeftRearStatus;
   private int mOriginalRadioBand = -1;
   private List mPresetList;
   private boolean mRightFrontRec;
   private boolean mRightFrontStatus;
   private boolean mRightRearRec;
   private boolean mRightRearStatus;
   private TimerUtil mTimerUtil;
   private boolean mTurnSignalStatus;
   private List mValidList;

   private String getBand(int var1) {
      String var2;
      if (var1 == 0) {
         var2 = "FM";
      } else {
         var2 = "AM";
      }

      return var2;
   }

   private int getBandData(String var1) {
      if ("FM1".equals(var1)) {
         return 1;
      } else if ("FM2".equals(var1)) {
         return 2;
      } else if ("FM3".equals(var1)) {
         return 3;
      } else if ("AM1".equals(var1)) {
         return 17;
      } else {
         return "AM2".equals(var1) ? 18 : 0;
      }
   }

   private String getData(int[] var1, float var2, String var3) {
      int var6 = 0;
      int var5 = 0;

      int var4;
      for(var4 = 0; var5 < var1.length; ++var5) {
         var4 = (int)((double)var4 + (double)var1[var5] * Math.pow(2.0, (double)(var5 * 8)));
      }

      for(var5 = 1; var6 < var1.length * 8 - 1; ++var6) {
         var5 = (var5 << 1) + 1;
      }

      return var4 == var5 ? "- - -" : this.mDecimalFormat0P0.format((double)((float)var4 * var2)) + " " + var3;
   }

   private String getDistanceUnit(int var1) {
      if (var1 != 0) {
         return var1 != 1 ? "" : "mile";
      } else {
         return "km";
      }
   }

   private int getDriveData(int[] var1) {
      int var3 = var1[0];

      for(int var2 = 1; var2 < var1.length; ++var2) {
         var3 = (int)((double)var3 + (double)var1[var2] * Math.pow(256.0, (double)var2));
      }

      return var3;
   }

   private int[] getFreqByteHiLo(String var1, String var2) {
      int[] var4 = new int[2];
      if (!var1.equals("AM2") && !var1.equals("MW") && !var1.equals("AM1") && !var1.equals("LW")) {
         if (var1.equals("FM1") || var1.equals("FM2") || var1.equals("FM3") || var1.equals("OIRT")) {
            int var3 = (int)(Double.parseDouble(var2) * 100.0);
            var4[0] = (byte)(var3 >> 8);
            var4[1] = (byte)(var3 & 255);
         }
      } else {
         var4[0] = (byte)(Integer.parseInt(var2) >> 8);
         var4[1] = (byte)(Integer.parseInt(var2) & 255);
      }

      return var4;
   }

   private String getFrequency(int var1, int var2, int var3) {
      float var4 = (float)(var2 | var3 << 8);
      if (var1 == 0) {
         var4 /= 10.0F;
         return this.mDecimalFormat0P00.format((double)var4) + " MHz";
      } else {
         return this.mDecimalFormat0P00.format((double)var4) + " KHz";
      }
   }

   private String getFuelUnit(int var1) {
      if (var1 != 0) {
         if (var1 != 1) {
            return var1 != 2 ? "" : "l/100km";
         } else {
            return "km/l";
         }
      } else {
         return "mpg";
      }
   }

   private int getRange(int var1) {
      if (var1 != 0) {
         if (var1 != 1) {
            return var1 != 2 ? (var1 - 1) * 10 : 12;
         } else {
            return 10;
         }
      } else {
         return 60;
      }
   }

   private int[] getTime(int var1) {
      int var2 = var1 / 60;
      return new int[]{var2 / 60, var2 % 60, var1 % 60};
   }

   private void initId3() {
      this.mId3s = new ID3[]{new ID3(this, 2), new ID3(this, 3), new ID3(this, 4)};
   }

   private void initOriginalDeviceList() {
      ArrayList var1 = new ArrayList();
      this.mFmPresetFrequencyList = var1;
      var1.add(new SongListEntity(CommUtil.getStrByResId(this.mContext, "_209_preset_station")));
      this.mFmPresetFrequencyList.add(new SongListEntity(CommUtil.getStrByResId(this.mContext, "null_value")));
      var1 = new ArrayList();
      this.mFmValidFrequencyList = var1;
      var1.add(new SongListEntity(CommUtil.getStrByResId(this.mContext, "_209_valid_station")));
      this.mFmValidFrequencyList.add(new SongListEntity(CommUtil.getStrByResId(this.mContext, "null_value")));
      var1 = new ArrayList();
      this.mAmPresetFrequencyList = var1;
      var1.add(new SongListEntity(CommUtil.getStrByResId(this.mContext, "_209_preset_station")));
      this.mAmPresetFrequencyList.add(new SongListEntity(CommUtil.getStrByResId(this.mContext, "null_value")));
      var1 = new ArrayList();
      this.mAmValidFrequencyList = var1;
      var1.add(new SongListEntity(CommUtil.getStrByResId(this.mContext, "_209_valid_station")));
      this.mAmValidFrequencyList.add(new SongListEntity(CommUtil.getStrByResId(this.mContext, "null_value")));
      this.mPresetList = this.mFmPresetFrequencyList;
      this.mValidList = this.mFmValidFrequencyList;
   }

   private boolean is0x16DataChange() {
      if (Arrays.equals(this.m0x16Data, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.m0x16Data = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean is0x22DataChange() {
      if (Arrays.equals(this.m0x22Data, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.m0x22Data = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean is0x23DataChange() {
      if (Arrays.equals(this.m0x23Data, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.m0x23Data = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean is0x25DataChange() {
      if (Arrays.equals(this.m0x25Data, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.m0x25Data = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean is0x29DataChange() {
      if (Arrays.equals(this.m0x29Data, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.m0x29Data = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean is0x30DataChange() {
      if (Arrays.equals(this.m0x30Data, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.m0x30Data = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean is0x32DataChange() {
      if (Arrays.equals(this.m0x32Data, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.m0x32Data = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean is0x33DataChange() {
      if (Arrays.equals(this.m0x33Data, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.m0x33Data = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean is0x33IndexOneDataChange() {
      if (Arrays.equals(this.m0x33DataIndexOne, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.m0x33DataIndexOne = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean is0x33IndexTwoDataChange() {
      if (Arrays.equals(this.m0x33DataIndexTwo, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.m0x33DataIndexTwo = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean is0x70DataChange() {
      if (Arrays.equals(this.m0x70Data, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.m0x70Data = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean is0x71DataChange() {
      if (Arrays.equals(this.m0x71Data, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.m0x71Data = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean is0xD1DataChange() {
      if (Arrays.equals(this.m0xD1Data, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.m0xD1Data = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean is0xD2DataChange() {
      if (Arrays.equals(this.m0xD2Data, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.m0xD2Data = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean is0xD3DataChange() {
      if (Arrays.equals(this.m0xD3Data, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.m0xD3Data = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isDoorDataChange() {
      if (this.mLeftFrontRec == this.mLeftFrontStatus && this.mRightFrontStatus == this.mRightFrontRec && this.mLeftRearStatus == this.mLeftRearRec && this.mRightRearStatus == this.mRightRearRec && this.mBackStatus == GeneralDoorData.isBackOpen && this.mFrontStatus == GeneralDoorData.isFrontOpen) {
         return false;
      } else {
         this.mLeftFrontStatus = this.mLeftFrontRec;
         this.mRightFrontStatus = this.mRightFrontRec;
         this.mLeftRearStatus = this.mLeftRearRec;
         this.mRightRearStatus = this.mRightRearRec;
         this.mBackStatus = GeneralDoorData.isBackOpen;
         this.mFrontStatus = GeneralDoorData.isFrontOpen;
         return true;
      }
   }

   private boolean isDoorFirst() {
      if (this.mIsDoorFirst) {
         this.mIsDoorFirst = false;
         if (!GeneralDoorData.isLeftFrontDoorOpen && !GeneralDoorData.isRightFrontDoorOpen && !GeneralDoorData.isLeftRearDoorOpen && !GeneralDoorData.isRightRearDoorOpen && !GeneralDoorData.isBackOpen && !GeneralDoorData.isFrontOpen && GeneralDoorData.skyWindowOpenLevel == 0) {
            return true;
         }
      }

      return false;
   }

   private void realKeyLongClick1(int var1) {
      this.realKeyLongClick1(this.mContext, var1, this.mCanBusInfoInt[3]);
   }

   private void refreshStationList(List var1) {
      int var2 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 4);
      if (var2 != 0) {
         (new StringBuilder()).append("P").append(var2).append(" ").toString();
      }

      if (this.mCanBusInfoInt[7] + 1 < var1.size()) {
         SongListEntity var4 = (SongListEntity)var1.get(this.mCanBusInfoInt[7] + 1);
         StringBuilder var3 = (new StringBuilder()).append("\t\t");
         int[] var5 = this.mCanBusInfoInt;
         var4.setTitle(var3.append(this.getFrequency(var5[3], var5[4], var5[5])).toString());
      } else {
         StringBuilder var7 = (new StringBuilder()).append("\t\t");
         int[] var6 = this.mCanBusInfoInt;
         var1.add(new SongListEntity(var7.append(this.getFrequency(var6[3], var6[4], var6[5])).toString()));
      }

   }

   private void reportID3Info(ID3[] var1, boolean var2) {
      (new Thread(this, var1, var2) {
         final MsgMgr this$0;
         final ID3[] val$id3s;
         final boolean val$isClean;

         {
            this.this$0 = var1;
            this.val$id3s = var2;
            this.val$isClean = var3;
         }

         public void run() {
            super.run();

            Exception var10000;
            label64: {
               int var3;
               ID3[] var4;
               boolean var10001;
               try {
                  var4 = this.val$id3s;
                  var3 = var4.length;
               } catch (Exception var9) {
                  var10000 = var9;
                  var10001 = false;
                  break label64;
               }

               byte var2 = 0;
               int var1 = 0;

               while(true) {
                  if (var1 >= var3) {
                     return;
                  }

                  try {
                     if (var4[var1].isId3Change()) {
                        if (this.val$isClean) {
                           sleep(900L);
                        }
                        break;
                     }
                  } catch (Exception var8) {
                     var10000 = var8;
                     var10001 = false;
                     break label64;
                  }

                  ++var1;
               }

               try {
                  var4 = this.val$id3s;
                  var3 = var4.length;
               } catch (Exception var7) {
                  var10000 = var7;
                  var10001 = false;
                  break label64;
               }

               var1 = var2;

               while(true) {
                  if (var1 >= var3) {
                     return;
                  }

                  ID3 var5 = var4[var1];

                  try {
                     sleep(100L);
                     this.this$0.reportID3InfoFinal((byte)var5.cmd, var5.id3);
                  } catch (Exception var6) {
                     var10000 = var6;
                     var10001 = false;
                     break;
                  }

                  ++var1;
               }
            }

            Exception var10 = var10000;
            var10.printStackTrace();
         }
      }).start();
   }

   private void reportID3InfoFinal(byte var1, String var2) throws Exception {
      byte[] var3 = DataHandleUtils.exceptBOMHead(var2.getBytes("unicodeLittle"));
      CanbusMsgSender.sendMsg(DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(new byte[]{22, -53, var1, 2}, var3), 36));
   }

   private String resolveAirTemp(int var1) {
      String var4;
      if (var1 == 0) {
         var4 = "LO";
      } else if (var1 == 255) {
         var4 = "HI";
      } else {
         StringBuilder var6 = new StringBuilder();
         float var3 = (float)var1;
         float var2;
         if (GeneralAirData.fahrenheit_celsius) {
            var2 = 1.0F;
         } else {
            var2 = 0.5F;
         }

         StringBuilder var5 = var6.append(var3 * var2);
         if (GeneralAirData.fahrenheit_celsius) {
            var4 = this.getTempUnitF(this.mContext);
         } else {
            var4 = this.getTempUnitC(this.mContext);
         }

         var4 = var5.append(var4).toString();
      }

      return var4;
   }

   private void sendMediaMsg(String var1, byte[][] var2) {
      (new Thread(this, var1, var2) {
         final MsgMgr this$0;
         final String val$mediaType;
         final byte[][] val$reports;

         {
            this.this$0 = var1;
            this.val$mediaType = var2;
            this.val$reports = var3;
         }

         public void run() {
            super.run();

            Exception var10000;
            label56: {
               String var4;
               boolean var10001;
               try {
                  var4 = this.val$mediaType;
               } catch (Exception var11) {
                  var10000 = var11;
                  var10001 = false;
                  break label56;
               }

               int var2 = 0;
               int var1 = 0;
               if (var4 == null) {
                  label43: {
                     byte[][] var12;
                     try {
                        var12 = this.val$reports;
                        var2 = var12.length;
                     } catch (Exception var8) {
                        var10000 = var8;
                        var10001 = false;
                        break label43;
                     }

                     while(true) {
                        if (var1 >= var2) {
                           return;
                        }

                        byte[] var5 = var12[var1];

                        try {
                           sleep(100L);
                           CanbusMsgSender.sendMsg(var5);
                        } catch (Exception var7) {
                           var10000 = var7;
                           var10001 = false;
                           break;
                        }

                        ++var1;
                     }
                  }
               } else {
                  label62: {
                     int var3;
                     byte[][] var6;
                     try {
                        var6 = this.val$reports;
                        var3 = var6.length;
                     } catch (Exception var10) {
                        var10000 = var10;
                        var10001 = false;
                        break label62;
                     }

                     var1 = var2;

                     while(true) {
                        if (var1 >= var3) {
                           return;
                        }

                        byte[] var13 = var6[var1];

                        try {
                           sleep(100L);
                           MsgMgr var15 = this.this$0;
                           var15.sendMediaMsg(var15.mContext, this.val$mediaType, var13);
                        } catch (Exception var9) {
                           var10000 = var9;
                           var10001 = false;
                           break;
                        }

                        ++var1;
                     }
                  }
               }
            }

            Exception var14 = var10000;
            var14.printStackTrace();
         }
      }).start();
   }

   private void setAirData0x21() {
      GeneralAirData.front_defog = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[6]);
      GeneralAirData.manual = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[6]);
      GeneralAirData.fahrenheit_celsius = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[6]);
      GeneralAirData.power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
      GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
      GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]) ^ true;
      GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
      GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
      GeneralAirData.rear_defog = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
      GeneralAirData.rear = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
      GeneralAirData.front_left_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
      GeneralAirData.front_right_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
      GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
      GeneralAirData.front_right_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
      GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
      GeneralAirData.front_right_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
      GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4);
      GeneralAirData.front_left_temperature = this.resolveAirTemp(this.mCanBusInfoInt[4]);
      GeneralAirData.front_right_temperature = this.resolveAirTemp(this.mCanBusInfoInt[5]);
      this.updateAirActivity(this.mContext, 1001);
   }

   private void setCameraStatus0x70() {
      if (this.is0x70DataChange()) {
         Context var6 = this.mContext;
         String var7 = Constant.FCameraActivity.getClassName();
         boolean var3 = false;
         boolean var4 = SystemUtil.isForeground(var6, new String[]{var7, Constant.AuxInActivity.getClassName()});
         boolean var5 = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
         boolean var2 = var4;
         if (this.mFrontViewBtnStatus) {
            var2 = var4;
            if (!var5) {
               this.switchFCamera(this.mContext, var4 ^ true);
               var2 = var4 ^ true;
            }
         }

         this.mFrontViewBtnStatus = var5;
         byte var1;
         if (SharePreUtil.getBoolValue(this.mContext, "share_209_front_camera_switch", false)) {
            var1 = 3;
         } else {
            var1 = 7;
         }

         Log.i("ljq", "setCameraStatus0x70: side-->" + var1);
         if ((1 << var1 & this.mCanBusInfoInt[3]) != 0) {
            var3 = true;
         }

         if (this.mTurnSignalStatus ^ var3) {
            this.mTurnSignalStatus = var3;
            if (var2 ^ var3) {
               this.switchFCamera(this.mContext, var3);
            }
         }
      }

   }

   private void setCameraStatusData0xD2() {
      if (this.is0xD2DataChange()) {
         boolean var2;
         if (this.mCameraStatus ^ DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2])) {
            var2 = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
            this.mCameraStatus = var2;
            this.forceReverse(this.mContext, var2);
         }

         int var1 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 5, 2);
         ArrayList var4 = new ArrayList();
         boolean var3 = false;
         if (var1 == 0) {
            var2 = true;
         } else {
            var2 = false;
         }

         var4.add(new PanoramicBtnUpdateEntity(0, var2));
         if (var1 == 1) {
            var2 = true;
         } else {
            var2 = false;
         }

         var4.add(new PanoramicBtnUpdateEntity(1, var2));
         var2 = var3;
         if (var1 == 2) {
            var2 = true;
         }

         var4.add(new PanoramicBtnUpdateEntity(2, var2));
         GeneralParkData.dataList = var4;
         this.updateParkUi((Bundle)null, this.mContext);
      }

   }

   private void setDoorData0x24() {
      boolean var1 = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
      GeneralDoorData.isRightFrontDoorOpen = var1;
      this.mRightFrontRec = var1;
      var1 = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
      GeneralDoorData.isLeftFrontDoorOpen = var1;
      this.mLeftFrontRec = var1;
      var1 = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
      GeneralDoorData.isRightRearDoorOpen = var1;
      this.mRightRearRec = var1;
      var1 = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
      GeneralDoorData.isLeftRearDoorOpen = var1;
      this.mLeftRearRec = var1;
      GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
      GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
      if (this.isDoorDataChange() && !this.isDoorFirst()) {
         this.updateDoorView(this.mContext);
      }

   }

   private void setEngineSpeed0x71() {
      if (this.is0x71DataChange()) {
         ArrayList var4 = new ArrayList();
         StringBuilder var2 = new StringBuilder();
         int[] var3 = this.mCanBusInfoInt;
         int var1 = var3[3];
         var4.add(new DriverUpdateEntity(2, 1, var2.append(var3[2] | var1 << 8).append(" rpm").toString()));
         this.updateGeneralDriveData(var4);
         this.updateDriveDataActivity((Bundle)null);
      }

   }

   private void setFrontRadarData0x23() {
      if (this.is0x23DataChange()) {
         int[] var1 = this.mCanBusInfoInt;
         RadarInfoUtil.setFrontRadarLocationData(4, var1[2], var1[3], var1[4], var1[5]);
         GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
         this.updateParkUi((Bundle)null, this.mContext);
      }

   }

   private void setFuelData0x33() {
      int var2 = this.mCanBusInfoInt[2];
      String var4;
      ArrayList var5;
      String var6;
      int[] var7;
      int[] var10;
      if (var2 != 1) {
         if (var2 == 2 && this.is0x33IndexTwoDataChange()) {
            var4 = this.getFuelUnit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[18], 4, 2));
            var6 = this.getDistanceUnit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[18], 6, 1));
            var5 = new ArrayList();
            var7 = this.mCanBusInfoInt;
            var5.add(new DriverUpdateEntity(1, 1, this.getData(new int[]{var7[5], var7[4], var7[3]}, 0.1F, var6)));
            var7 = this.mCanBusInfoInt;
            var5.add(new DriverUpdateEntity(1, 2, this.getData(new int[]{var7[7], var7[6]}, 0.1F, var4)));
            var7 = this.mCanBusInfoInt;
            var5.add(new DriverUpdateEntity(1, 4, this.getData(new int[]{var7[10], var7[9], var7[8]}, 0.1F, var6)));
            var7 = this.mCanBusInfoInt;
            var5.add(new DriverUpdateEntity(1, 5, this.getData(new int[]{var7[12], var7[11]}, 0.1F, var4)));
            var7 = this.mCanBusInfoInt;
            var5.add(new DriverUpdateEntity(1, 7, this.getData(new int[]{var7[15], var7[14], var7[13]}, 0.1F, var6)));
            var10 = this.mCanBusInfoInt;
            var5.add(new DriverUpdateEntity(1, 8, this.getData(new int[]{var10[17], var10[16]}, 0.1F, var4)));
            this.updateGeneralDriveData(var5);
            this.updateDriveDataActivity((Bundle)null);
         }
      } else if (this.is0x33IndexOneDataChange()) {
         String var9 = this.getFuelUnit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[15], 0, 2));
         String var8 = this.getFuelUnit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[15], 2, 2));
         String var12 = this.getFuelUnit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[15], 4, 2));
         var6 = this.getDistanceUnit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[15], 6, 1));
         var4 = this.getDistanceUnit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[15], 7, 1));
         var2 = this.getRange(this.mCanBusInfoInt[16]);
         var5 = new ArrayList();
         int var3 = this.mCanBusInfoInt[3];
         float var1 = (float)var2;
         var5.add(new DriverUpdateEntity(0, 0, this.getData(new int[]{var3}, var1 * 0.04761905F, var9)));
         int[] var13 = this.mCanBusInfoInt;
         var5.add(new DriverUpdateEntity(0, 1, this.getData(new int[]{var13[5], var13[4]}, 0.1F, var8)));
         var13 = this.mCanBusInfoInt;
         var5.add(new DriverUpdateEntity(0, 2, this.getData(new int[]{var13[7], var13[6]}, 0.1F, var8)));
         int[] var11 = this.mCanBusInfoInt;
         var5.add(new DriverUpdateEntity(0, 3, this.getData(new int[]{var11[9], var11[8]}, 0.1F, var12)));
         var7 = this.mCanBusInfoInt;
         var5.add(new DriverUpdateEntity(0, 4, this.getData(new int[]{var7[12], var7[11], var7[10]}, 0.1F, var6)));
         var10 = this.mCanBusInfoInt;
         var5.add(new DriverUpdateEntity(0, 5, this.getData(new int[]{var10[14], var10[13]}, 1.0F, var4)));
         this.updateGeneralDriveData(var5);
         this.updateDriveDataActivity((Bundle)null);
      }

   }

   private void setOriginalRadioData0xD1() {
      if (this.is0xD1DataChange()) {
         boolean var2;
         if (this.mOriginalRadioBand != this.mCanBusInfoInt[3]) {
            var2 = true;
         } else {
            var2 = false;
         }

         GeneralOriginalCarDeviceData.isSongListChange = var2;
         int[] var5 = this.mCanBusInfoInt;
         int var1 = var5[3];
         this.mOriginalRadioBand = var1;
         List var3;
         if (var1 == 0) {
            var3 = this.mFmPresetFrequencyList;
         } else {
            var3 = this.mAmPresetFrequencyList;
         }

         this.mPresetList = var3;
         List var4;
         if (var1 == 0) {
            var4 = this.mFmValidFrequencyList;
         } else {
            var4 = this.mAmValidFrequencyList;
         }

         this.mValidList = var4;
         var1 = var5[2];
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 == 3) {
                  this.refreshStationList(var3);
                  GeneralOriginalCarDeviceData.isSongListChange = true;
               }
            } else {
               this.refreshStationList(var4);
               GeneralOriginalCarDeviceData.isSongListChange = true;
            }
         } else {
            ArrayList var7 = new ArrayList();
            var7.add(new OriginalCarDeviceUpdateEntity(0, this.getBand(this.mCanBusInfoInt[3])));
            var7.add(new OriginalCarDeviceUpdateEntity(1, "P" + DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 4)));
            int[] var6 = this.mCanBusInfoInt;
            var7.add(new OriginalCarDeviceUpdateEntity(2, this.getFrequency(var6[3], var6[4], var6[5])));
            GeneralOriginalCarDeviceData.mList = var7;
         }

         GeneralOriginalCarDeviceData.st = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[6]);
         GeneralOriginalCarDeviceData.scan = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[6]);
         GeneralOriginalCarDeviceData.refresh = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[6]);
         GeneralOriginalCarDeviceData.songList.clear();
         GeneralOriginalCarDeviceData.songList.addAll(this.mPresetList);
         GeneralOriginalCarDeviceData.songList.addAll(this.mValidList);
         this.updateOriginalCarDeviceActivity((Bundle)null);
      }

   }

   private void setParkAssistData0x25() {
      if (this.is0x25DataChange()) {
         ArrayList var3 = new ArrayList();
         Context var2 = this.mContext;
         String var1;
         if (DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2])) {
            var1 = "open";
         } else {
            var1 = "close";
         }

         var3.add(new DriverUpdateEntity(2, 2, CommUtil.getStrByResId(var2, var1)));
         this.updateGeneralDriveData(var3);
         this.updateDriveDataActivity((Bundle)null);
      }

   }

   private void setRearRadarData0x22() {
      if (this.is0x22DataChange()) {
         int[] var1 = this.mCanBusInfoInt;
         RadarInfoUtil.setRearRadarLocationData(4, var1[2], var1[3], var1[4], var1[5]);
         GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
         this.updateParkUi((Bundle)null, this.mContext);
      }

   }

   private void setScreenData0xD3() {
      if (this.is0xD3DataChange()) {
         ArrayList var1 = new ArrayList();
         var1.add(new SettingUpdateEntity(5, 0, this.mCanBusInfoInt[2]));
         var1.add((new SettingUpdateEntity(5, 1, this.mCanBusInfoInt[3] - 5)).setProgress(this.mCanBusInfoInt[3]));
         var1.add((new SettingUpdateEntity(5, 2, this.mCanBusInfoInt[4] - 5)).setProgress(this.mCanBusInfoInt[4]));
         var1.add((new SettingUpdateEntity(5, 3, this.mCanBusInfoInt[5] - 5)).setProgress(this.mCanBusInfoInt[5]));
         this.updateGeneralSettingData(var1);
         this.updateSettingActivity((Bundle)null);
      }

   }

   private void setTrackData0x29() {
      if (this.is0x29DataChange()) {
         byte[] var1 = this.mCanBusInfoByte;
         GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(var1[2], var1[3], 0, 4608, 16);
         this.updateParkUi((Bundle)null, this.mContext);
      }

   }

   private void setVehicleSetupData0x32() {
      if (this.is0x32DataChange()) {
         ArrayList var2 = new ArrayList();
         var2.add(new SettingUpdateEntity(4, 6, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 1, 1)));
         var2.add(new SettingUpdateEntity(3, 5, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 0, 1)));
         var2.add(new SettingUpdateEntity(4, 0, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 7, 1)));
         var2.add(new SettingUpdateEntity(4, 1, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 6, 1)));
         var2.add(new SettingUpdateEntity(4, 2, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 5, 1)));
         var2.add(new SettingUpdateEntity(4, 3, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 4, 1)));
         var2.add(new SettingUpdateEntity(4, 4, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 2, 2)));
         var2.add(new SettingUpdateEntity(4, 5, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 0, 2)));
         var2.add(new SettingUpdateEntity(3, 0, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 7, 1)));
         var2.add(new SettingUpdateEntity(3, 1, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 6, 1)));
         var2.add(new SettingUpdateEntity(3, 2, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 5, 1)));
         var2.add(new SettingUpdateEntity(3, 3, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 4, 1)));
         var2.add(new SettingUpdateEntity(3, 4, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 3, 1)));
         var2.add(new SettingUpdateEntity(2, 0, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 7, 1)));
         var2.add(new SettingUpdateEntity(2, 1, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 6, 1)));
         var2.add(new SettingUpdateEntity(2, 2, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 2)));
         var2.add(new SettingUpdateEntity(2, 3, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 2, 2)));
         var2.add(new SettingUpdateEntity(2, 4, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 2)));
         var2.add(new SettingUpdateEntity(1, 0, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 7, 1)));
         var2.add(new SettingUpdateEntity(1, 1, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 3)));
         var2.add(new SettingUpdateEntity(1, 2, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 2, 2)));
         var2.add(new SettingUpdateEntity(1, 3, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 2)));
         int var1 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 4);
         var2.add(new SettingUpdateEntity(0, 0, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 6, 2)));
         var2.add(new SettingUpdateEntity(0, 1, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 2)));
         var2.add((new SettingUpdateEntity(0, 2, var1 - 5)).setProgress(var1));
         this.updateGeneralSettingData(var2);
         this.updateSettingActivity((Bundle)null);
      }

   }

   private void setVehicleSpeedData0x16() {
      if (this.is0x16DataChange()) {
         ArrayList var3 = new ArrayList();
         StringBuilder var2 = new StringBuilder();
         int[] var4 = this.mCanBusInfoInt;
         int var1 = var4[3];
         var3.add(new DriverUpdateEntity(2, 0, var2.append(var4[2] | var1 << 8).append(" km/h").toString()));
         this.updateGeneralDriveData(var3);
         this.updateDriveDataActivity((Bundle)null);
         int[] var5 = this.mCanBusInfoInt;
         var1 = var5[3];
         this.updateSpeedInfo(var5[2] | var1 << 8);
      }

   }

   private void setVersionInfo0x30() {
      if (this.is0x30DataChange()) {
         this.updateVersionInfo(this.mContext, this.getVersionStr(this.mCanBusInfoByte));
      }

   }

   private void setWheelKey0x20() {
      switch (this.mCanBusInfoInt[2]) {
         case 1:
            this.realKeyLongClick1(7);
            break;
         case 2:
            this.realKeyLongClick1(8);
            break;
         case 3:
            this.realKeyLongClick1(48);
            break;
         case 4:
            this.realKeyLongClick1(47);
         case 5:
         case 6:
         default:
            break;
         case 7:
            this.realKeyLongClick1(2);
            break;
         case 8:
            this.realKeyLongClick1(187);
            break;
         case 9:
            this.realKeyLongClick1(14);
            break;
         case 10:
            this.realKeyLongClick1(189);
      }

   }

   public void afterServiceNormalSetting(Context var1) {
      super.afterServiceNormalSetting(var1);
      SelectCanTypeUtil.enableApp(var1, Constant.OriginalDeviceActivity);
   }

   public void auxInInfoChange() {
      super.auxInInfoChange();
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.AUX1.name(), new byte[]{22, -64, 7, 48, 0, 0, 0, 0, 0, 0});
   }

   public void btMusicInfoChange() {
      super.btMusicInfoChange();
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.BTAUDIO.name(), new byte[]{22, -64, 11, 0, 0, 0, 0, 0, 0, 0});
   }

   public void btPhoneIncomingInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneIncomingInfoChange(var1, var2, var3);
      var1 = DataHandleUtils.byteMerger(new byte[]{32}, var1);
      var1 = DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(new byte[]{22, -53, 1}, var1), 36, 32);
      this.sendMediaMsg((String)null, new byte[][]{{22, -64, 5, 64, 0, 0, 0, 0, 0, 0}, var1});
   }

   public void btPhoneOutGoingInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneOutGoingInfoChange(var1, var2, var3);
      var1 = DataHandleUtils.byteMerger(new byte[]{32}, var1);
      var1 = DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(new byte[]{22, -53, 1}, var1), 36, 32);
      this.sendMediaMsg((String)null, new byte[][]{{22, -64, 5, 64, 0, 0, 0, 0, 0, 0}, var1});
   }

   public void btPhoneStatusInfoChange(int var1, byte[] var2, boolean var3, boolean var4, boolean var5, boolean var6, int var7, int var8, Bundle var9) {
      super.btPhoneStatusInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9);
      var7 = DataHandleUtils.setIntByteWithBit(this.m0xC5Data1, 4, var3);
      this.m0xC5Data1 = var7;
      var1 = DataHandleUtils.setIntFromByteWithBit(var7, var1, 0, 3);
      this.m0xC5Data1 = var1;
      CanbusMsgSender.sendMsg(new byte[]{22, -59, 0, (byte)var1});
   }

   public void btPhoneTalkingWithTimeInfoChange(byte[] var1, boolean var2, boolean var3, int var4) {
      super.btPhoneTalkingWithTimeInfoChange(var1, var2, var3, var4);
      int[] var8 = this.getTime(var4);
      byte var5 = (byte)var8[1];
      byte var7 = (byte)var8[2];
      var4 = DataHandleUtils.setIntByteWithBit(this.m0xC5Data1, 5, var2 ^ true);
      this.m0xC5Data1 = var4;
      byte var6 = (byte)var4;
      var1 = DataHandleUtils.byteMerger(new byte[]{32}, var1);
      byte[] var9 = DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(new byte[]{22, -53, 1}, var1), 36, 32);
      var1 = new byte[]{22, -59, 0, var6};
      this.sendMediaMsg((String)null, new byte[][]{{22, -64, 5, 64, 0, var5, var7, 0, 0, 0}, var1, var9});
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoByte = var2;
      int[] var4 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfoInt = var4;
      this.mContext = var1;
      int var3 = var4[1];
      if (var3 != 22) {
         if (var3 != 41) {
            if (var3 != 48) {
               if (var3 != 50) {
                  if (var3 != 51) {
                     if (var3 != 112) {
                        if (var3 != 113) {
                           switch (var3) {
                              case 32:
                                 this.setWheelKey0x20();
                                 break;
                              case 33:
                                 this.setAirData0x21();
                                 break;
                              case 34:
                                 this.setRearRadarData0x22();
                                 break;
                              case 35:
                                 this.setFrontRadarData0x23();
                                 break;
                              case 36:
                                 this.setDoorData0x24();
                                 break;
                              case 37:
                                 this.setParkAssistData0x25();
                                 break;
                              default:
                                 switch (var3) {
                                    case 209:
                                       this.setOriginalRadioData0xD1();
                                       break;
                                    case 210:
                                       this.setCameraStatusData0xD2();
                                       break;
                                    case 211:
                                       this.setScreenData0xD3();
                                 }
                           }
                        } else {
                           this.setEngineSpeed0x71();
                        }
                     } else {
                        this.setCameraStatus0x70();
                     }
                  } else {
                     this.setFuelData0x33();
                  }
               } else {
                  this.setVehicleSetupData0x32();
               }
            } else {
               this.setVersionInfo0x30();
            }
         } else {
            this.setTrackData0x29();
         }
      } else {
         this.setVehicleSpeedData0x16();
      }

   }

   public void currentVolumeInfoChange(int var1, boolean var2) {
      super.currentVolumeInfoChange(var1, var2);
      short var3;
      if (var2) {
         var3 = 128;
      } else {
         var3 = 0;
      }

      CanbusMsgSender.sendMsg(new byte[]{22, -60, (byte)(DataHandleUtils.rangeNumber(var1, 0, 30) | var3)});
   }

   public void dateTimeRepCanbus(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, boolean var10, boolean var11, boolean var12, int var13) {
      super.dateTimeRepCanbus(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13);
      CanbusMsgSender.sendMsg(new byte[]{22, -112, 48, 0});
      var1 = var8;
      if (!var10) {
         var1 = var8 | 128;
      }

      byte var14 = (byte)var1;
      byte var15 = (byte)var6;
      if (this.mTimerUtil == null) {
         this.mTimerUtil = new TimerUtil();
      }

      this.mTimerUtil.stopTimer();
      this.mTimerUtil.startTimer(new TimerTask(this, new byte[]{22, -119, var14, var15}) {
         int count;
         final MsgMgr this$0;
         final byte[] val$reprot;

         {
            this.this$0 = var1;
            this.val$reprot = var2;
            this.count = 0;
         }

         public void run() {
            if (this.count < 3) {
               CanbusMsgSender.sendMsg(this.val$reprot);
               ++this.count;
            } else {
               this.this$0.mTimerUtil.stopTimer();
            }

         }
      }, 0L, 5000L);
   }

   public void deviceStatusInfoChange(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, int var10, int var11, int var12) {
      super.deviceStatusInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12);
      var1 = DataHandleUtils.setIntFromByteWithBit(this.m0xC5Data1, var6, 6, 1);
      this.m0xC5Data1 = var1;
      var1 = DataHandleUtils.setIntFromByteWithBit(var1, var4, 3, 1);
      this.m0xC5Data1 = var1;
      CanbusMsgSender.sendMsg(new byte[]{22, -59, 0, (byte)var1});
   }

   public void discInfoChange(byte var1, int var2, int var3, int var4, int var5, int var6, int var7, boolean var8, boolean var9, int var10, String var11, String var12, String var13) {
      super.discInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13);
      if (var7 == 1 || var7 == 5) {
         var4 = var5;
      }

      int[] var18 = this.getTime(var2);
      byte var14 = (byte)var4;
      byte var15 = (byte)var6;
      var1 = (byte)var18[0];
      byte var16 = (byte)var18[1];
      byte var17 = (byte)var18[2];
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MPEG.name(), new byte[]{22, -64, 2, 16, 0, var14, var15, var1, var16, var17});
   }

   int getPresetListSize() {
      return this.mPresetList.size();
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      this.mContext = var1;
      CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
      this.initId3();
      GeneralOriginalCarDeviceData.songList = new ArrayList();
      this.initOriginalDeviceList();
      this.mDecimalFormat0P0 = new DecimalFormat("0.0");
      this.mDecimalFormat0P00 = new DecimalFormat("0.00");
      GeneralParkData.radar_location_data = null;
      RadarInfoUtil.mMinIsClose = true;
      this.m0xD1ResolveThread = new Thread(this) {
         final MsgMgr this$0;

         {
            this.this$0 = var1;
         }

         public void run() {
            super.run();
         }
      };
      GeneralOriginalCarDeviceData.isBottomBtnChange = false;
      this.mTimerUtil = new TimerUtil();
   }

   public void musicDestroy() {
      super.musicDestroy();
      this.mId3s[0].id3 = "";
      this.mId3s[1].id3 = "";
      this.mId3s[2].id3 = "";
      this.reportID3Info(this.mId3s, true);
   }

   public void musicInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, byte var8, byte var9, byte var10, String var11, String var12, String var13, long var14, byte var16, int var17, boolean var18, long var19, String var21, String var22, String var23, boolean var24) {
      super.musicInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var16, var17, var18, var19, var21, var22, var23, var24);
      var1 = (byte)(var4 & 255);
      var5 = (byte)(var4 >> 8);
      var2 = (byte)var3;
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MUSIC.name(), new byte[]{22, -64, 8, 17, var1, var5, var2, var9, var6, var7});
      this.mId3s[0].id3 = var21;
      this.mId3s[1].id3 = var22;
      this.mId3s[2].id3 = var23;
      this.reportID3Info(this.mId3s, false);
   }

   public void radioInfoChange(int var1, String var2, String var3, String var4, int var5) {
      super.radioInfoChange(var1, var2, var3, var4, var5);
      var5 = this.getBandData(var2);
      int[] var10 = this.getFreqByteHiLo(var2, var3);
      byte var6 = (byte)var5;
      byte var9 = (byte)var10[1];
      byte var7 = (byte)var10[0];
      byte var8 = (byte)var1;
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.FM.name(), new byte[]{22, -64, 1, 1, var6, var9, var7, var8, 0, 0});
   }

   public void sourceSwitchNoMediaInfoChange(boolean var1) {
      super.sourceSwitchNoMediaInfoChange(var1);
      CanbusMsgSender.sendMsg(new byte[]{22, -64, 0, 0, 0, 0, 0, 0, 0, 0});
   }

   void updateSettings(int var1, int var2, int var3) {
      ArrayList var4 = new ArrayList();
      var4.add(new SettingUpdateEntity(var1, var2, var3));
      this.updateGeneralSettingData(var4);
      this.updateSettingActivity((Bundle)null);
   }

   public void videoInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, String var8, byte var9, byte var10, String var11, String var12, String var13, int var14, byte var15, boolean var16, int var17) {
      super.videoInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var15, var16, var17);
      var5 = (byte)(var4 & 255);
      var2 = (byte)(var4 >> 8);
      var1 = (byte)var3;
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.VIDEO.name(), new byte[]{22, -64, 8, 17, var5, var2, var1, var9, var6, var7});
   }

   private class ID3 {
      private int cmd;
      private String id3;
      private String record;
      final MsgMgr this$0;

      public ID3(MsgMgr var1, int var2) {
         this.this$0 = var1;
         this.cmd = var2;
         this.id3 = new String();
         this.record = new String();
      }

      public boolean isId3Change() {
         if (this.record.equals(this.id3)) {
            return false;
         } else {
            this.record = this.id3;
            return true;
         }
      }
   }
}
