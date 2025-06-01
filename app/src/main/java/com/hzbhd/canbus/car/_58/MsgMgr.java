package com.hzbhd.canbus.car._58;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.provider.Settings.System;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.util.FutureUtil;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.OriginalCarDeviceUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralOriginalCarDeviceData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.canbus.util.amap.AMapEntity;
import com.hzbhd.canbus.util.amap.AMapUtils;
import com.hzbhd.commontools.SourceConstantsDef;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

public class MsgMgr extends AbstractMsgMgr {
   TextView content;
   CountDownTimer countDownTimer;
   boolean currentPresetStore;
   AlertDialog dialog;
   int[] m0xB1Info;
   private boolean mBackStatus;
   private byte[] mCanBusInfoByte;
   private int[] mCanBusInfoInt;
   private Context mContext;
   private boolean mFrontStatus;
   private boolean mIsDoorFirst = true;
   private boolean mIsUsbFirst_0xa4 = true;
   private boolean mLeftFrontStatus;
   private boolean mLeftRearStatus;
   private byte[] mOriginalUsb_0xa4;
   private boolean mRightFrontStatus;
   private boolean mRightRearStatus;
   private byte[] mTrackDataNow;
   private String mUsbSonTimeNow;
   private String mUsbSongAlbumNow;
   private String mUsbSongArtistNow;
   private String mUsbSongTitleNow;
   private byte[] mVersionInfoNow;
   private int mWheelKeyNow;
   private byte[] mediaDataNow;
   private byte naviStatus;
   View view;

   private byte getAllBandTypeData(String var1, byte var2, byte var3, byte var4, byte var5, byte var6) {
      var1.hashCode();
      int var8 = var1.hashCode();
      byte var7 = -1;
      switch (var8) {
         case 2443:
            if (var1.equals("LW")) {
               var7 = 0;
            }
            break;
         case 2474:
            if (var1.equals("MW")) {
               var7 = 1;
            }
            break;
         case 64901:
            if (var1.equals("AM1")) {
               var7 = 2;
            }
            break;
         case 64902:
            if (var1.equals("AM2")) {
               var7 = 3;
            }
            break;
         case 69706:
            if (var1.equals("FM1")) {
               var7 = 4;
            }
            break;
         case 69707:
            if (var1.equals("FM2")) {
               var7 = 5;
            }
            break;
         case 69708:
            if (var1.equals("FM3")) {
               var7 = 6;
            }
            break;
         case 2426268:
            if (var1.equals("OIRT")) {
               var7 = 7;
            }
      }

      switch (var7) {
         case 0:
         case 2:
            return var5;
         case 1:
         case 3:
            return var6;
         case 4:
            return var2;
         case 5:
            return var3;
         case 6:
         case 7:
            return var4;
         default:
            return 0;
      }
   }

   private int getStr() {
      int var1 = this.mCanBusInfoInt[2];
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  return var1 != 4 ? 2131766880 : 2131766879;
               } else {
                  return 2131766878;
               }
            } else {
               return 2131766877;
            }
         } else {
            return 2131766876;
         }
      } else {
         return 2131766875;
      }
   }

   private boolean is0xB1InfoNoChange() {
      if (Arrays.equals(this.m0xB1Info, this.mCanBusInfoInt)) {
         return true;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.m0xB1Info = Arrays.copyOf(var1, var1.length);
         return false;
      }
   }

   private boolean is0xa4Repeat(byte[] var1) {
      if (Arrays.equals(var1, this.mOriginalUsb_0xa4)) {
         return true;
      } else {
         this.mOriginalUsb_0xa4 = Arrays.copyOf(var1, var1.length);
         if (this.mIsUsbFirst_0xa4) {
            this.mIsUsbFirst_0xa4 = false;
            return true;
         } else {
            return false;
         }
      }
   }

   private boolean isDoorDataChange() {
      if (GeneralDoorData.isLeftFrontDoorOpen == this.mLeftFrontStatus && GeneralDoorData.isRightFrontDoorOpen == this.mRightFrontStatus && GeneralDoorData.isLeftRearDoorOpen == this.mLeftRearStatus && GeneralDoorData.isRightRearDoorOpen == this.mRightRearStatus && GeneralDoorData.isBackOpen == this.mBackStatus && GeneralDoorData.isFrontOpen == this.mFrontStatus) {
         return false;
      } else {
         this.mLeftFrontStatus = GeneralDoorData.isLeftFrontDoorOpen;
         this.mRightFrontStatus = GeneralDoorData.isRightFrontDoorOpen;
         this.mLeftRearStatus = GeneralDoorData.isLeftRearDoorOpen;
         this.mRightRearStatus = GeneralDoorData.isRightRearDoorOpen;
         this.mBackStatus = GeneralDoorData.isBackOpen;
         this.mFrontStatus = GeneralDoorData.isFrontOpen;
         return true;
      }
   }

   private boolean isDoorFirst() {
      if (this.mIsDoorFirst) {
         this.mIsDoorFirst = false;
         if (!GeneralDoorData.isLeftFrontDoorOpen && !GeneralDoorData.isRightFrontDoorOpen && !GeneralDoorData.isLeftRearDoorOpen && !GeneralDoorData.isRightRearDoorOpen && !GeneralDoorData.isBackOpen && !GeneralDoorData.isFrontOpen) {
            return true;
         }
      }

      return false;
   }

   private boolean isHaveMedia() {
      return true;
   }

   private boolean isTrackDataChange() {
      byte[] var2 = new byte[2];
      byte[] var1 = this.mCanBusInfoByte;
      var2[0] = var1[8];
      var2[1] = var1[9];
      if (Arrays.equals(this.mTrackDataNow, var2)) {
         return false;
      } else {
         this.mTrackDataNow = Arrays.copyOf(var2, 2);
         return true;
      }
   }

   private boolean isVersionInfoChange() {
      if (Arrays.equals(this.mVersionInfoNow, this.mCanBusInfoByte)) {
         return false;
      } else {
         byte[] var1 = this.mCanBusInfoByte;
         this.mVersionInfoNow = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isWheelKeyDataChange() {
      int var1 = this.mWheelKeyNow;
      int var2 = this.mCanBusInfoInt[4];
      if (var1 == var2) {
         return var2 == 1 || var2 == 2 || var2 == 8 || var2 == 9 || var2 == 13 || var2 == 14;
      } else {
         this.mWheelKeyNow = var2;
         return true;
      }
   }

   private void reportID3Info(String var1, String var2, String var3, boolean var4, String var5) {
      (new Thread(this, var4, var1, var2, var3, var5) {
         final MsgMgr this$0;
         final String val$album;
         final String val$artist;
         final String val$charsetName;
         final boolean val$isClean;
         final String val$title;

         {
            this.this$0 = var1;
            this.val$isClean = var2;
            this.val$title = var3;
            this.val$album = var4;
            this.val$artist = var5;
            this.val$charsetName = var6;
         }

         public void run() {
            super.run();

            try {
               if (this.val$isClean) {
                  sleep(1000L);
               }

               if (!this.val$title.equals(this.this$0.mUsbSongTitleNow) || !this.val$album.equals(this.this$0.mUsbSongAlbumNow) || !this.val$artist.equals(this.this$0.mUsbSongArtistNow)) {
                  this.this$0.mUsbSongTitleNow = this.val$title;
                  this.this$0.mUsbSongAlbumNow = this.val$album;
                  this.this$0.mUsbSongArtistNow = this.val$artist;
                  sleep(100L);
                  this.this$0.reportID3InfoFinal((byte)-30, this.val$title, this.val$charsetName);
                  sleep(100L);
                  this.this$0.reportID3InfoFinal((byte)-29, this.val$album, this.val$charsetName);
                  sleep(100L);
                  this.this$0.reportID3InfoFinal((byte)-28, this.val$artist, this.val$charsetName);
               }
            } catch (Exception var2) {
               var2.printStackTrace();
            }

         }
      }).start();
   }

   private void reportID3InfoFinal(byte var1, String var2, String var3) {
      try {
         byte[] var5 = DataHandleUtils.exceptBOMHead(var2.getBytes(var3));
         CanbusMsgSender.sendMsg(DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(new byte[]{22, var1}, var5), 34));
      } catch (Exception var4) {
         var4.printStackTrace();
      }

   }

   private String runningStateUsb(int var1) {
      String var2;
      if (var1 != 1) {
         if (var1 != 2) {
            if (var1 != 3) {
               if (var1 != 6) {
                  if (var1 != 9) {
                     if (var1 != 255) {
                        if (var1 != 12) {
                           if (var1 != 13) {
                              var2 = "";
                           } else {
                              var2 = this.mContext.getResources().getString(2131757176);
                           }
                        } else {
                           var2 = this.mContext.getResources().getString(2131757428);
                        }
                     } else {
                        var2 = this.mContext.getResources().getString(2131768909);
                     }
                  } else {
                     var2 = this.mContext.getResources().getString(2131766857);
                  }
               } else {
                  var2 = this.mContext.getResources().getString(2131757180);
               }
            } else {
               var2 = this.mContext.getResources().getString(2131766856);
            }
         } else {
            var2 = this.mContext.getResources().getString(2131757425);
         }
      } else {
         var2 = this.mContext.getResources().getString(2131757429);
      }

      return var2;
   }

   private void sendMediaMsg1(Context var1, String var2, byte[] var3) {
      Log.i("AbstractMsgMgr", "sendMediaMsg: context: " + var1 + ", media: " + var2);
      if (var1 != null) {
         String var4 = FutureUtil.instance.getCurrentValidSource().toString();
         Log.i("AbstractMsgMgr", "sendMediaMsg: frontSeat:" + var4);
         if (System.getInt(var1.getContentResolver(), "btState", 1) == 1 && (var2.equals(var4) || SourceConstantsDef.SOURCE_ID.NORMAL_SOURCE.name().equals(var2)) && !Arrays.equals(var3, this.mediaDataNow)) {
            CanbusMsgSender.sendMsg(var3);
            this.mediaDataNow = Arrays.copyOf(var3, var3.length);
            if (!SourceConstantsDef.SOURCE_ID.BTPHONE.name().equals(var2)) {
               System.putString(var1.getContentResolver(), "reportAfterHangUp", Base64.encodeToString(var3, 0));
               if (!SourceConstantsDef.SOURCE_ID.MPEG.toString().equals(var4)) {
                  System.putString(var1.getContentResolver(), "reportForDiscEject", Base64.encodeToString(var3, 0));
               }
            }
         }

      }
   }

   private void set0xB1Info() {
      if (!this.is0xB1InfoNoChange()) {
         if (this.view == null) {
            this.view = LayoutInflater.from(this.mContext).inflate(2131558728, (ViewGroup)null, true);
         }

         if (this.dialog == null) {
            this.dialog = (new AlertDialog.Builder(this.mContext)).setView(this.view).create();
         }

         if (this.content == null) {
            this.content = (TextView)this.view.findViewById(2131361915);
         }

         this.content.setText(this.getStr());
         this.dialog.setCancelable(false);
         this.dialog.getWindow().setBackgroundDrawableResource(17170445);
         this.dialog.getWindow().setType(2003);
         this.dialog.show();
         CountDownTimer var1 = this.countDownTimer;
         if (var1 != null) {
            var1.cancel();
         }

         var1 = new CountDownTimer(this, 3000L, 1000L) {
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
         this.countDownTimer = var1;
         var1.start();
      }
   }

   private void setDoorData0x12() {
      GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[4]);
      GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[4]);
      GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
      GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
      GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4]);
      GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4]);
      if (this.isDoorDataChange() && !this.isDoorFirst()) {
         this.updateDoorView(this.mContext);
      }

   }

   private void setDriveData() {
      ArrayList var1 = new ArrayList();
      var1.add(new DriverUpdateEntity(0, 0, this.mCanBusInfoInt[3] + "km/h"));
      this.updateGeneralDriveData(var1);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setKey0x72() {
      if (this.isWheelKeyDataChange()) {
         int var1 = this.mCanBusInfoInt[4];
         if (var1 != 0) {
            if (var1 != 1) {
               if (var1 != 2) {
                  if (var1 != 5) {
                     if (var1 != 6) {
                        switch (var1) {
                           case 8:
                              this.wheelKeyLongClick(46);
                              break;
                           case 9:
                              this.wheelKeyLongClick(45);
                              break;
                           case 10:
                              this.wheelKeyLongClick(2);
                              break;
                           case 11:
                              this.wheelKeyLongClick(187);
                        }
                     } else {
                        this.wheelKeyLongClick(189);
                     }
                  } else {
                     this.wheelKeyLongClick(14);
                  }
               } else {
                  this.wheelKeyLongClick(8);
               }
            } else {
               this.wheelKeyLongClick(7);
            }
         } else {
            this.wheelKeyLongClick(0);
         }
      }

   }

   private void setMultimediaInfo() {
      ArrayList var3 = new ArrayList();
      String var2;
      if (DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3])) {
         var2 = this.mContext.getResources().getString(2131766854);
      } else {
         var2 = this.mContext.getResources().getString(2131766855);
      }

      var3.add(new OriginalCarDeviceUpdateEntity(0, var2));
      StringBuilder var4 = new StringBuilder();
      int[] var5 = this.mCanBusInfoInt;
      var3.add(new OriginalCarDeviceUpdateEntity(1, var4.append(var5[8] * 256 + var5[7]).append("").toString()));
      var4 = new StringBuilder();
      var5 = this.mCanBusInfoInt;
      var3.add(new OriginalCarDeviceUpdateEntity(2, var4.append(var5[6] * 256 + var5[5]).append("").toString()));
      StringBuilder var6 = new StringBuilder();
      int[] var7 = this.mCanBusInfoInt;
      var3.add(new OriginalCarDeviceUpdateEntity(3, var6.append(this.startEndTimeMethod(var7[9] * 60 + var7[10])).append("").toString()));
      var3.add(new OriginalCarDeviceUpdateEntity(4, this.mCanBusInfoInt[11] + "%"));
      GeneralOriginalCarDeviceData.runningState = this.runningStateUsb(this.mCanBusInfoInt[12]);
      if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 4) == 13) {
         GeneralOriginalCarDeviceData.cdStatus = "Usb";
         this.naviStatus = 21;
      } else {
         GeneralOriginalCarDeviceData.cdStatus = "Ipod";
         this.naviStatus = 22;
      }

      int var1 = this.mCanBusInfoInt[12];
      if (var1 == 6) {
         GeneralOriginalCarDeviceData.power = false;
      } else if (var1 == 2) {
         GeneralOriginalCarDeviceData.power = true;
      }

      GeneralOriginalCarDeviceData.mList = var3;
      this.updateOriginalCarDeviceActivity((Bundle)null);
      this.enterAuxIn2(this.mContext, Constant.OriginalDeviceActivity);
      CanbusMsgSender.sendMsg(DataHandleUtils.compensateZero(new byte[]{22, -31, this.naviStatus}, 15));
   }

   private void setTrack0x11() {
      if (this.isTrackDataChange()) {
         byte[] var1 = this.mCanBusInfoByte;
         GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle1(var1[9], var1[8], 0, 5200, 16);
         this.updateParkUi((Bundle)null, this.mContext);
      }

   }

   private void setVersionInfo0xF0() {
      if (this.isVersionInfoChange()) {
         this.updateVersionInfo(this.mContext, this.getVersionStr(this.mCanBusInfoByte));
      }

   }

   private void wheelKeyLongClick(int var1) {
      this.realKeyLongClick2(this.mContext, var1);
   }

   private void wheelKeyShortClick(int var1) {
      this.realKeyClick4(this.mContext, var1);
   }

   public void afterServiceNormalSetting(Context var1) {
      super.afterServiceNormalSetting(var1);
      SelectCanTypeUtil.enableApp(var1, Constant.OriginalDeviceActivity);
      AMapUtils.getInstance().startAMap(var1);
   }

   public void atvInfoChange() {
      super.atvInfoChange();
      Log.d("_55_atv", "  .");
      byte[] var1 = new byte[12];
      Arrays.fill(var1, (byte)32);
      java.lang.System.arraycopy("TV".getBytes(), 0, var1, 0, "TV".getBytes().length);
      CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -31, 8}, var1));
   }

   public void auxInInfoChange() {
      super.auxInInfoChange();
      Log.d("_55_aux", "  .");
      byte[] var1 = new byte[12];
      Arrays.fill(var1, (byte)32);
      java.lang.System.arraycopy("AUX".getBytes(), 0, var1, 0, "AUX".getBytes().length);
      CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -31, 12}, var1));
   }

   public void btMusicInfoChange() {
      super.btMusicInfoChange();
      byte[] var1 = new byte[12];
      Arrays.fill(var1, (byte)32);
      java.lang.System.arraycopy("A2DP".getBytes(), 0, var1, 0, "A2DP".getBytes().length);
      CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -31, -123}, var1));
   }

   public void btPhoneHangUpInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneHangUpInfoChange(var1, var2, var3);
      CanbusMsgSender.sendMsg(new byte[]{22, -79, 4, 0});
   }

   public void btPhoneIncomingInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneIncomingInfoChange(var1, var2, var3);
      CanbusMsgSender.sendMsg(new byte[]{22, -79, 1, 0});
   }

   public void btPhoneOutGoingInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneOutGoingInfoChange(var1, var2, var3);
      CanbusMsgSender.sendMsg(new byte[]{22, -79, 2, 0});
   }

   public void btPhoneStatusInfoChange(int var1, byte[] var2, boolean var3, boolean var4, boolean var5, boolean var6, int var7, int var8, Bundle var9) {
      super.btPhoneStatusInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9);
      if (!var3) {
         CanbusMsgSender.sendMsg(new byte[]{22, -79, 0, 0});
      } else {
         CanbusMsgSender.sendMsg(new byte[]{22, -79, 3, 0});
      }

   }

   public void btPhoneTalkingWithTimeInfoChange(byte[] var1, boolean var2, boolean var3, int var4) {
      super.btPhoneTalkingWithTimeInfoChange(var1, var2, var3, var4);
      var1 = ("    " + (new DecimalFormat("00")).format((long)(var4 / 60 % 60)) + (new DecimalFormat("00")).format((long)(var4 % 60)) + "    ").getBytes();
      CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -31, 10}, var1));
   }

   public void cameraInfoChange() {
      super.cameraInfoChange();
      CanbusMsgSender.sendMsg(new byte[]{22, -31, 16, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoByte = var2;
      int[] var4 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfoInt = var4;
      this.mContext = var1;
      int var3 = var4[1];
      if (var3 != 17) {
         if (var3 != 18) {
            if (var3 != 114) {
               if (var3 != 164) {
                  if (var3 != 177) {
                     if (var3 == 240) {
                        this.setVersionInfo0xF0();
                     }
                  } else {
                     this.set0xB1Info();
                  }
               } else {
                  if (this.is0xa4Repeat(var2)) {
                     return;
                  }

                  this.setMultimediaInfo();
               }
            } else {
               this.setKey0x72();
               this.setDriveData();
               this.updateSpeedInfo(this.mCanBusInfoInt[3]);
            }
         } else {
            this.setDoorData0x12();
         }
      } else {
         this.setTrack0x11();
      }

   }

   public void dateTimeRepCanbus(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, boolean var10, boolean var11, boolean var12, int var13) {
      super.dateTimeRepCanbus(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13);
      CanbusMsgSender.sendMsg(new byte[]{22, -75, (byte)var5, (byte)var6, (byte)var7});
   }

   public void discInfoChange(byte var1, int var2, int var3, int var4, int var5, int var6, int var7, boolean var8, boolean var9, int var10, String var11, String var12, String var13) {
      super.discInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13);
      if (var1 == 16) {
         var11 = "LOADING ";
      } else if (var1 == 17) {
         var11 = "EJECTING";
      } else {
         var11 = "";
      }

      if (var10 != 48) {
         if (var10 != 65) {
            if (var10 == 241) {
               var11 = "ERROR   ";
            }
         } else {
            var11 = "STOP    ";
         }
      } else {
         var11 = "PAUSE   ";
      }

      byte[] var14 = DataHandleUtils.byteMerger(var11.getBytes(), new byte[]{0, 0, 0, 0});
      this.sendMediaMsg1(this.mContext, SourceConstantsDef.SOURCE_ID.MPEG.toString(), DataHandleUtils.byteMerger(new byte[]{22, -31, 6}, var14));
      DecimalFormat var15 = new DecimalFormat("00");
      var14 = DataHandleUtils.byteMerger(((new DecimalFormat("000")).format((long)var5) + " " + var15.format((long)(var2 / 60)) + var15.format((long)(var2 & 60))).getBytes(), new byte[]{0, 0, 0, 0});
      this.sendMediaMsg1(this.mContext, SourceConstantsDef.SOURCE_ID.MPEG.toString(), DataHandleUtils.byteMerger(new byte[]{22, -31, 6}, var14));
   }

   public void dtvInfoChange() {
      super.dtvInfoChange();
      byte[] var1 = new byte[12];
      Arrays.fill(var1, (byte)32);
      java.lang.System.arraycopy("TV".getBytes(), 0, var1, 0, "TV".getBytes().length);
      CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -31, 8}, var1));
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      this.mContext = var1;
   }

   public void musicDestroy() {
      super.musicDestroy();
      this.reportID3Info("", "", "", true, "ascii");
   }

   public void musicInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, byte var8, byte var9, byte var10, String var11, String var12, String var13, long var14, byte var16, int var17, boolean var18, long var19, String var21, String var22, String var23, boolean var24) {
      super.musicInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var16, var17, var18, var19, var21, var22, var23, var24);
      var12 = (new DecimalFormat("00")).format((long)var6);
      var11 = (new DecimalFormat("00")).format((long)var7);
      byte[] var25 = (String.format((new DecimalFormat("000")).format((long)((var9 & 255) * 256 + (var3 & 255)))) + " " + var12 + var11 + "    ").getBytes();
      CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -31, 13}, var25));
      if (!var11.equals(this.mUsbSonTimeNow)) {
         this.mUsbSonTimeNow = var11;
         this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MUSIC.toString(), new byte[]{22, -104, 0, 0, 0, 0, var6, var7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
      }

      this.reportID3Info(var21, var22, var23, false, "unicode");
   }

   public void onAMapCallBack(AMapEntity var1) {
      super.onAMapCallBack(var1);
      switch (var1.getCarDirection()) {
         case 1:
            CanbusMsgSender.sendMsg(new byte[]{22, -74, 3});
            break;
         case 2:
            CanbusMsgSender.sendMsg(new byte[]{22, -74, 4});
            break;
         case 3:
            CanbusMsgSender.sendMsg(new byte[]{22, -74, 5});
            break;
         case 4:
            CanbusMsgSender.sendMsg(new byte[]{22, -74, 6});
            break;
         case 5:
            CanbusMsgSender.sendMsg(new byte[]{22, -74, 7});
            break;
         case 6:
            CanbusMsgSender.sendMsg(new byte[]{22, -74, 8});
            break;
         case 7:
            CanbusMsgSender.sendMsg(new byte[]{22, -74, 1});
            break;
         case 8:
            CanbusMsgSender.sendMsg(new byte[]{22, -74, 2});
      }

   }

   public void radioInfoChange(int var1, String var2, String var3, String var4, int var5) {
      super.radioInfoChange(var1, var2, var3, var4, var5);
      var5 = var1;
      if (var1 == 256) {
         var5 = 0;
      }

      byte var6 = this.getAllBandTypeData(var2, (byte)1, (byte)2, (byte)3, (byte)4, (byte)5);
      if (this.isBandAm(var2)) {
         if (var3.length() == 4) {
            var2 = (new DecimalFormat("00")).format((long)var5) + " " + var3 + "     ";
         } else {
            var2 = (new DecimalFormat("00")).format((long)var5) + " 0" + var3 + "     ";
         }
      } else if (var3.length() == 5) {
         var2 = (new DecimalFormat("00")).format((long)var5) + "  " + var3.substring(0, var3.length() - 1) + "    ";
      } else {
         var2 = (new DecimalFormat("00")).format((long)var5) + " " + var3.substring(0, var3.length() - 1) + "    ";
      }

      byte[] var7 = var2.getBytes();
      CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -31, var6}, var7));
   }

   public void sourceSwitchNoMediaInfoChange(boolean var1) {
      super.sourceSwitchNoMediaInfoChange(var1);
      if (this.isHaveMedia()) {
         this.sendMediaMsg1(this.mContext, SourceConstantsDef.SOURCE_ID.NORMAL_SOURCE.toString(), new byte[]{22, -31, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
         CanbusMsgSender.sendMsg(new byte[]{22, -104, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
         this.reportID3Info("", "", "", true, "ascii");
      }

   }

   public void videoInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, String var8, byte var9, byte var10, String var11, String var12, String var13, int var14, byte var15, boolean var16, int var17) {
      super.videoInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var15, var16, var17);
      var11 = (new DecimalFormat("00")).format((long)var6);
      var8 = (new DecimalFormat("00")).format((long)var7);
      byte[] var18 = (String.format((new DecimalFormat("000")).format((long)((var9 & 255) * 256 + (var3 & 255)))) + " " + var11 + var8 + "    ").getBytes();
      CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -31, 13}, var18));
      if (!var8.equals(this.mUsbSonTimeNow)) {
         CanbusMsgSender.sendMsg(new byte[]{22, -104, 0, 0, 0, 0, var6, var7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
         this.mUsbSonTimeNow = var8;
      }

   }
}
