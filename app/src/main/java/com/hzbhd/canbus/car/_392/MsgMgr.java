package com.hzbhd.canbus.car._392;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.SharePreUtil;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

public class MsgMgr extends AbstractMsgMgr {
   private TextView content;
   private CountDownTimer countDownTimer;
   private int data5 = -1;
   private AlertDialog dialog;
   int differentId;
   int eachId;
   private Button i_know;
   int[] mAirData;
   byte[] mCanBusInfoByte;
   int[] mCanBusInfoInt;
   int[] mCarDoorData;
   Context mContext;
   int[] mFrontRadarData;
   int[] mPanoramicInfo;
   int[] mRearRadarData;
   int[] mTireInfo;
   byte[] mTrackData;
   private UiMgr mUiMgr;
   private int nowLightLever = 5;
   DecimalFormat timeFormat = new DecimalFormat("00");
   private View view;

   private byte[] SplicingByte(byte[] var1, byte[] var2) {
      byte[] var3 = new byte[var1.length + var2.length];
      System.arraycopy(var1, 0, var3, 0, var1.length);
      System.arraycopy(var2, 0, var3, var1.length, var2.length);
      return var3;
   }

   private int blockBit(int var1, int var2) {
      if (var2 == 0) {
         return (DataHandleUtils.getIntFromByteWithBit(var1, 1, 7) & 255) << 1;
      } else if (var2 == 7) {
         return DataHandleUtils.getIntFromByteWithBit(var1, 0, 7);
      } else {
         int var4 = var2 + 1;
         int var3 = DataHandleUtils.getIntFromByteWithBit(var1, var4, 7 - var2);
         return DataHandleUtils.getIntFromByteWithBit(var1, 0, var2) & 255 & 255 ^ (var3 & 255) << var4;
      }
   }

   private int getLsb(int var1) {
      return (var1 & '\uffff') >> 0 & 255;
   }

   private String getMediaType() {
      int var1 = this.mCanBusInfoInt[3];
      if (var1 != 1) {
         if (var1 != 2) {
            return var1 != 3 ? "NO MEDIA" : "AUX";
         } else {
            return "CD";
         }
      } else {
         return "Radio";
      }
   }

   private int getMsb(int var1) {
      return (var1 & '\uffff') >> 8 & 255;
   }

   private int getMsbLsbResult(int var1, int var2) {
      return (var1 & 255) << 8 | var2 & 255;
   }

   private String getTemperature(int var1, double var2, double var4, String var6, int var7, int var8) {
      if (var1 == var7) {
         return "LO";
      } else if (var1 == var8) {
         return "HI";
      } else {
         return var6.trim().equals("C") ? (double)var1 * var2 + var4 + this.getTempUnitC(this.mContext) : (double)var1 * var2 + var4 + this.getTempUnitF(this.mContext);
      }
   }

   private String getUTF8Result(String var1) {
      try {
         var1 = URLDecoder.decode(var1, "utf-8");
      } catch (UnsupportedEncodingException var2) {
         var2.printStackTrace();
         var1 = "";
      }

      return var1;
   }

   private UiMgr getUiMgr(Context var1) {
      if (this.mUiMgr == null) {
         this.mUiMgr = (UiMgr)UiMgrFactory.getCanUiMgr(var1);
      }

      return this.mUiMgr;
   }

   private boolean is404(String var1, String var2) {
      return this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, var1) == -1 || this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, var1, var2) == -1;
   }

   private boolean isAirDataChange() {
      if (Arrays.equals(this.mAirData, this.mCanBusInfoInt)) {
         return true;
      } else {
         this.mAirData = this.mCanBusInfoInt;
         return false;
      }
   }

   private boolean isBasicInfoChange() {
      if (Arrays.equals(this.mCarDoorData, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.mCarDoorData = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isFrontRadarDataChange() {
      if (Arrays.equals(this.mFrontRadarData, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.mFrontRadarData = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isPanoramicInfoChange() {
      if (Arrays.equals(this.mPanoramicInfo, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.mPanoramicInfo = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isRearRadarDataChange() {
      if (Arrays.equals(this.mRearRadarData, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.mRearRadarData = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isTireInfoChange() {
      if (Arrays.equals(this.mTireInfo, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.mTireInfo = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isTrackInfoChange() {
      if (Arrays.equals(this.mTrackData, this.mCanBusInfoByte)) {
         return false;
      } else {
         byte[] var1 = this.mCanBusInfoByte;
         this.mTrackData = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private void setAlert0x11() {
      int var2 = this.data5;
      if (var2 == -1) {
         this.data5 = this.mCanBusInfoInt[7];
      } else {
         int var1 = this.mCanBusInfoInt[7];
         if (var2 != var1) {
            this.data5 = var1;
            if (var1 != 0) {
               String var4 = "";
               if (DataHandleUtils.getBoolBit7(var1)) {
                  var4 = "" + "【" + this.mContext.getString(2131765121) + ":" + this.mContext.getString(2131765150) + "】";
               }

               String var3 = var4;
               if (DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[7])) {
                  var3 = var4 + "【" + this.mContext.getString(2131765122) + ":" + this.mContext.getString(2131765150) + "】";
               }

               var4 = var3;
               if (DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[7])) {
                  var4 = var3 + "【" + this.mContext.getString(2131765123) + ":" + this.mContext.getString(2131765151) + "】";
               }

               if (this.view == null) {
                  this.view = LayoutInflater.from(this.mContext).inflate(2131558513, (ViewGroup)null, true);
               }

               if (this.dialog == null) {
                  this.dialog = (new AlertDialog.Builder(this.mContext)).setView(this.view).create();
               }

               if (this.content == null) {
                  this.content = (TextView)this.view.findViewById(2131361915);
               }

               if (this.i_know == null) {
                  this.i_know = (Button)this.view.findViewById(2131362380);
               }

               this.content.setText(var4);
               AlertDialog var5 = this.dialog;
               if (var5 != null) {
                  var5.dismiss();
               }

               this.i_know.setOnClickListener(new View.OnClickListener(this) {
                  final MsgMgr this$0;

                  {
                     this.this$0 = var1;
                  }

                  public void onClick(View var1) {
                     this.this$0.dialog.dismiss();
                  }
               });
               CountDownTimer var6 = this.countDownTimer;
               if (var6 != null) {
                  var6.cancel();
               }

               var6 = new CountDownTimer(this, 5000L, 1000L) {
                  final MsgMgr this$0;

                  {
                     this.this$0 = var1;
                  }

                  public void onFinish() {
                     this.this$0.dialog.dismiss();
                  }

                  public void onTick(long var1) {
                  }
               };
               this.countDownTimer = var6;
               var6.start();
               this.dialog.setCancelable(false);
               this.dialog.getWindow().setBackgroundDrawableResource(17170445);
               this.dialog.getWindow().setType(2003);
               this.dialog.show();
            }
         }
      }
   }

   private void setLight0x11() {
      int var2 = this.nowLightLever;
      int var1 = this.mCanBusInfoInt[6];
      if (var2 != var1) {
         this.nowLightLever = var1;
         if (var1 >= 17) {
            this.setBacklightLevel((var1 - 17) / 36 + 1);
         }
      }
   }

   private void setMedia0x11() {
      ArrayList var1 = new ArrayList();
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_392_drive"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_392_drive1"), this.getMediaType()));
      this.updateGeneralDriveData(var1);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setOutDoorTem0x31() {
      if (this.mCanBusInfoInt[13] == 255) {
         this.updateOutDoorTemp(this.mContext, "");
      } else {
         this.updateOutDoorTemp(this.mContext, (double)this.mCanBusInfoInt[13] * 0.5 - 40.0 + this.getTempUnitC(this.mContext));
      }

   }

   private void setSWC0x11() {
      // $FF: Couldn't be decompiled
   }

   private void setTime0x11() {
      String var1;
      if (!DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[10])) {
         var1 = "        (Invalid)";
      } else {
         var1 = "        (Support)";
      }

      ArrayList var2 = new ArrayList();
      var2.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_392_drive"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_392_drive2"), this.timeFormat.format((long)this.mCanBusInfoInt[11]) + ":" + this.timeFormat.format((long)DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[10], 0, 7)) + var1));
      this.updateGeneralDriveData(var2);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setTrack0x11() {
      boolean var1 = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[8]);
      int[] var2 = this.mCanBusInfoInt;
      var2[8] = this.blockBit(var2[8], 7);
      if (var1) {
         var2 = this.mCanBusInfoInt;
         GeneralParkData.trackAngle = this.getMsbLsbResult(var2[8], var2[9]) / 10 / 21;
      } else {
         var2 = this.mCanBusInfoInt;
         GeneralParkData.trackAngle = -this.getMsbLsbResult(var2[8], var2[9]) / 10 / 21;
      }

      this.updateParkUi((Bundle)null, this.mContext);
   }

   private int sixteenToTen(String var1) {
      return Integer.parseInt(("0x" + var1).replaceAll("^0[x|X]", ""), 16);
   }

   private String tenToSixTeen(int var1) {
      return String.format("%02x", var1);
   }

   public void afterServiceNormalSetting(Context var1) {
      super.afterServiceNormalSetting(var1);
      this.differentId = this.getCurrentCanDifferentId();
      this.eachId = this.getCurrentEachCanId();
      this.mContext = var1;
   }

   public void btPhoneHangUpInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneHangUpInfoChange(var1, var2, var3);
      this.getUiMgr(this.mContext).send0xC8BtInfo(0);
   }

   public void btPhoneTalkingInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneTalkingInfoChange(var1, var2, var3);
      this.getUiMgr(this.mContext).send0xC8BtInfo(1);
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
      if (var3 != 17) {
         if (var3 != 49) {
            if (var3 == 240) {
               this.updateVersionInfo(this.mContext, this.getVersionStr(this.mCanBusInfoByte));
            }
         } else {
            this.setOutDoorTem0x31();
         }
      } else {
         this.setMedia0x11();
         this.setSWC0x11();
         this.setLight0x11();
         this.setAlert0x11();
         this.setTrack0x11();
         this.setTime0x11();
      }

   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
   }

   public void knobKey(int var1) {
      this.realKeyClick4(this.mContext, var1);
   }

   public void toast(String var1) {
      this.runOnUi(new AbstractMsgMgr.CallBackInterface(this, var1) {
         final MsgMgr this$0;
         final String val$content;

         {
            this.this$0 = var1;
            this.val$content = var2;
         }

         public void callback() {
            Toast.makeText(this.this$0.getActivity(), this.val$content, 0).show();
         }
      });
   }

   public void updateSettings(int var1, int var2, int var3) {
      ArrayList var4 = new ArrayList();
      var4.add(new SettingUpdateEntity(var1, var2, var3));
      this.updateGeneralSettingData(var4);
      this.updateSettingActivity((Bundle)null);
   }

   public void updateSettings(Context var1, int var2, int var3, String var4, int var5, String var6) {
      SharePreUtil.setIntValue(var1, var4, var5);
      ArrayList var7 = new ArrayList();
      var7.add(new SettingUpdateEntity(var2, var3, var5 + var6));
      this.updateGeneralSettingData(var7);
      this.updateSettingActivity((Bundle)null);
   }
}
