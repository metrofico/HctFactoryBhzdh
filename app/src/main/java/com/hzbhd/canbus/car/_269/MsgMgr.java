package com.hzbhd.canbus.car._269;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.location.ILocAidlInterface;
import com.hzbhd.canbus.adapter.location.LocationChange;
import com.hzbhd.canbus.adapter.util.Util;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.commontools.SourceConstantsDef;
import java.io.UnsupportedEncodingException;
import java.util.Timer;
import java.util.TimerTask;
import org.json.JSONException;
import org.json.JSONObject;

public class MsgMgr extends AbstractMsgMgr {
   protected static final byte AMP_BAL_OFFSET = 10;
   protected static final byte AMP_DEFAULT_BAL_BAND = 9;
   protected static final byte AMP_DEFAULT_EQ = 0;
   protected static final byte AMP_DEFAULT_VOL = 8;
   protected static final byte AMP_EQ_OFFSET = 1;
   protected static final byte AMP_VOL_OFFSET = 22;
   private static final String AUTONAVI_CUR_POINT_NUM = "CUR_POINT_NUM";
   private static final String AUTONAVI_EXTRA_STATE = "EXTRA_STATE";
   private static final int AUTONAVI_EXTRA_STATE_END_NAVI = 9;
   private static final int AUTONAVI_EXTRA_STATE_START_NAVI = 8;
   private static final String AUTONAVI_ICON = "ICON";
   private static final int AUTONAVI_KEY_EXTRA_ROAD_INFO = 10056;
   private static final int AUTONAVI_KEY_ID_GUIDE = 10001;
   private static final int AUTONAVI_KEY_MAP_RUNNING_ST = 10019;
   private static final String AUTONAVI_KEY_TYPE = "KEY_TYPE";
   private static final String AUTONAVI_NEXT_ROAD_NAME = "NEXT_ROAD_NAME";
   private static final String AUTONAVI_ROUTE_ALL_DIS = "ROUTE_ALL_DIS";
   private static final String AUTONAVI_ROUTE_REMAIN_DIS = "ROUTE_REMAIN_DIS";
   private static final String AUTONAVI_SEG_REMAIN_DIS = "SEG_REMAIN_DIS";
   private static final String AUTONAVI_STANDARD_BROADCAST_SEND = "AUTONAVI_STANDARD_BROADCAST_SEND";
   protected static final int CAN_269_AMP_DEFAULT_EQ_VALUE = 9;
   protected static final String CAN_269_SAVE_AMP_BASS = "__269_SAVE_AMP_BASS";
   protected static final String CAN_269_SAVE_AMP_FR = "__269_SAVE_AMP_FR";
   protected static final String CAN_269_SAVE_AMP_LR = "__269_SAVE_AMP_LR";
   protected static final String CAN_269_SAVE_AMP_MID = "__269_SAVE_AMP_MID";
   protected static final String CAN_269_SAVE_AMP_TRE = "__269_SAVE_AMP_TRE";
   protected static final String CAN_269_SAVE_AMP_VOL = "__269_SAVE_AMP_VOL";
   protected static final String CAN_269_SAVE_IS_AMP_FIRST = "CAN_269_SAVE_IS_AMP_FIRST";
   private static boolean mIsBackOpened;
   private static boolean mIsBackOpenedRec;
   private static boolean mIsFLOpened;
   private static boolean mIsFLOpenedRec;
   private static boolean mIsFROpened;
   private static boolean mIsFROpenedRec;
   private static boolean mIsFourDoorVersion;
   private static boolean mIsFrontOpened;
   private static boolean mIsFrontOpenedRec;
   private static boolean mIsRLOpened;
   private static boolean mIsRLOpenedRec;
   private static boolean mIsRROpened;
   private static boolean mIsRROpenedRec;
   private final String ACTION_CAN_DOOR_CHANGED = "ACTION_CAN_DOOR_CHANGED";
   private final String KEY_DOOR_BACK = "KEY_DOOR_BACK";
   private final String KEY_DOOR_FL = "KEY_DOOR_FL";
   private final String KEY_DOOR_FR = "KEY_DOOR_FR";
   private final String KEY_DOOR_RL = "KEY_DOOR_RL";
   private final String KEY_DOOR_RR = "KEY_DOOR_RR";
   private final String KEY_FOUR_DOOR_VERSION = "KEY_FOUR_DOOR_VERSION";
   LocationListener locationListener = new LocationListener(this) {
      final MsgMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onLocationChanged(Location var1) {
         if (var1 != null) {
            this.this$0.sendDrirectAndAltitude(var1);
         }

      }

      public void onProviderDisabled(String var1) {
      }

      public void onProviderEnabled(String var1) {
         Location var2 = this.this$0.mLocationManager.getLastKnownLocation(var1);
         if (var2 != null) {
            this.this$0.sendDrirectAndAltitude(var2);
         }

      }

      public void onStatusChanged(String var1, int var2, Bundle var3) {
      }
   };
   private String mAlbumRec = "";
   private String mArtistRec = "";
   private byte[] mCanBusInfoByte;
   private int[] mCanBusInfoInt;
   private Context mContext;
   private ILocAidlInterface mILocAidlInterface;
   private boolean mIsPayingRec = false;
   LocChange mLocChange = null;
   private LocationManager mLocationManager;
   private int mNaviAllDist = 0;
   private String mNaviDestName = "";
   private int mNaviNextRoadDist = 0;
   private float mNaviNextRoadDistKm = 0.0F;
   private String mNaviNextRoadDistStr = "";
   private String mNaviNextRoadName = "";
   private int mNaviPointNum = 0;
   private int mNaviRemainDist = 0;
   private int mNaviRunningSt = 0;
   private String mNaviStartName = "";
   private int mNaviTurningIconId = 0;
   private String mSongRec = "";
   private Timer mTimer;
   BroadcastReceiver receiver = new BroadcastReceiver(this) {
      final MsgMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onReceive(Context var1, Intent var2) {
         if (var2.getAction().equals("AUTONAVI_STANDARD_BROADCAST_SEND")) {
            int var3 = var2.getIntExtra("KEY_TYPE", 0);
            StringBuilder var9;
            MsgMgr var11;
            MsgMgr var12;
            if (var3 != 10001) {
               if (var3 != 10019) {
                  if (var3 == 10056) {
                     Log.i("[PENGLING]", "SND: <AUTONAVI_KEY_EXTRA_ROAD_INFO>");
                     String var10 = var2.getStringExtra("EXTRA_ROAD_INFO");

                     try {
                        JSONObject var8 = new JSONObject(var10);
                        this.this$0.mNaviDestName = (String)var8.get("ToPoiName");
                        this.this$0.mNaviStartName = (String)var8.get("FromPoiName");

                        try {
                           var9 = new StringBuilder();
                           Log.i("[PENGLING]", var9.append("SND: <AUTONAVI_KEY_EXTRA_ROAD_INFO> mNaviDestName=").append(this.this$0.mNaviDestName).toString());
                           var9 = new StringBuilder();
                           Log.i("[PENGLING]", var9.append("SND: <AUTONAVI_KEY_EXTRA_ROAD_INFO> mNaviStartName=").append(this.this$0.mNaviStartName).toString());
                        } catch (Exception var6) {
                        }

                        var11 = this.this$0;
                        var9 = new StringBuilder();
                        var11.sendNaviCmds(var9.append(this.this$0.mNaviStartName).append("").toString(), (byte)4);
                        var12 = this.this$0;
                        StringBuilder var13 = new StringBuilder();
                        var12.sendNaviCmds(var13.append(this.this$0.mNaviDestName).append("").toString(), (byte)5);
                     } catch (JSONException var7) {
                        var7.printStackTrace();
                     }
                  }
               } else {
                  Log.i("[PENGLING]", "SND: <AUTONAVI_KEY_MAP_RUNNING_ST>");
                  this.this$0.mNaviRunningSt = var2.getIntExtra("EXTRA_STATE", 0);

                  try {
                     var9 = new StringBuilder();
                     Log.i("[PENGLING]", var9.append("SND: <AUTONAVI_KEY_MAP_RUNNING_ST> mNaviRunningSt=").append(this.this$0.mNaviRunningSt).toString());
                  } catch (Exception var5) {
                  }

                  if (this.this$0.mNaviRunningSt == 8) {
                     CanbusMsgSender.sendMsg(new byte[]{22, -96, 6, 10, 7});
                  } else if (this.this$0.mNaviRunningSt == 9) {
                     CanbusMsgSender.sendMsg(new byte[]{22, -96, 6, 10, 8});
                  }
               }
            } else {
               Log.i("[PENGLING]", "SND: <AUTONAVI_KEY_ID_GUIDE>");
               this.this$0.mNaviAllDist = var2.getIntExtra("ROUTE_ALL_DIS", 0);
               this.this$0.mNaviRemainDist = var2.getIntExtra("ROUTE_REMAIN_DIS", 0);
               this.this$0.mNaviTurningIconId = var2.getIntExtra("ICON", 0);
               this.this$0.mNaviPointNum = var2.getIntExtra("CUR_POINT_NUM", 0);
               this.this$0.mNaviNextRoadDist = var2.getIntExtra("SEG_REMAIN_DIS", 0);
               this.this$0.mNaviNextRoadName = var2.getStringExtra("NEXT_ROAD_NAME");

               try {
                  var9 = new StringBuilder();
                  Log.i("[PENGLING]", var9.append("SND: <AUTONAVI_KEY_ID_GUIDE> mNaviAllDist=").append(this.this$0.mNaviAllDist).toString());
                  var9 = new StringBuilder();
                  Log.i("[PENGLING]", var9.append("SND: <AUTONAVI_KEY_ID_GUIDE> mNaviRemainDist=").append(this.this$0.mNaviRemainDist).toString());
                  var9 = new StringBuilder();
                  Log.i("[PENGLING]", var9.append("SND: <AUTONAVI_KEY_ID_GUIDE> mNaviTurningIconId=").append(this.this$0.mNaviTurningIconId).toString());
                  var9 = new StringBuilder();
                  Log.i("[PENGLING]", var9.append("SND: <AUTONAVI_KEY_ID_GUIDE> mNaviPointNum=").append(this.this$0.mNaviPointNum).toString());
                  var9 = new StringBuilder();
                  Log.i("[PENGLING]", var9.append("SND: <AUTONAVI_KEY_ID_GUIDE> mNaviNextRoadName=").append(this.this$0.mNaviNextRoadName).toString());
               } catch (Exception var4) {
               }

               CanbusMsgSender.sendMsg(new byte[]{22, -96, 6, 1, (byte)this.this$0.mNaviTurningIconId, (byte)this.this$0.mNaviPointNum});
               this.this$0.sendNaviCmds(this.this$0.mNaviAllDist + "", (byte)2);
               this.this$0.sendNaviCmds(this.this$0.mNaviRemainDist + "", (byte)3);
               this.this$0.sendNaviCmds(this.this$0.mNaviNextRoadName + "", (byte)6);
               this.this$0.sendNaviCmds(this.this$0.mNaviNextRoadDist + "", (byte)7);
               if (this.this$0.mNaviNextRoadDist / 1000 >= 10) {
                  this.this$0.mNaviNextRoadDistStr = this.this$0.mNaviNextRoadDist / 1000 + "";
               } else if (this.this$0.mNaviNextRoadDist >= 1000 && this.this$0.mNaviNextRoadDist < 10000) {
                  var12 = this.this$0;
                  var12.mNaviNextRoadDistKm = (float)var12.mNaviNextRoadDist / 1000.0F;
                  var12 = this.this$0;
                  var12.mNaviNextRoadDistKm = DataHandleUtils.getRound(var12.mNaviNextRoadDistKm, 2);
                  this.this$0.mNaviNextRoadDistStr = (this.this$0.mNaviNextRoadDistKm + "").substring(0, 3);
               } else {
                  this.this$0.mNaviNextRoadDistStr = this.this$0.mNaviNextRoadDist + "";
                  this.this$0.sendNaviCmds(this.this$0.mNaviNextRoadDist + "", (byte)7);
               }

               Log.i("[PENGLING]", "SND: <AUTONAVI_KEY_ID_GUIDE> mNaviNextRoadDistStr=" + this.this$0.mNaviNextRoadDistStr);
               var12 = this.this$0;
               var12.sendNaviCmds(var12.mNaviNextRoadDistStr, (byte)7);
               var11 = this.this$0;
               String var14;
               if (var11.mNaviNextRoadDist >= 1000) {
                  var14 = "KM";
               } else {
                  var14 = "M";
               }

               var11.sendNaviCmds(var14, (byte)8);
            }
         }

      }
   };

   private String getDateTime2Digit(int var1) {
      return var1 <= 9 ? "0" + var1 : "" + var1;
   }

   private void initAmpCmds() {
      CanbusMsgSender.sendMsg(new byte[]{22, -93, (byte)((byte)GeneralAmplifierData.volume + 22), (byte)((byte)GeneralAmplifierData.frontRear + 10), (byte)((byte)GeneralAmplifierData.leftRight + 10), (byte)((byte)GeneralAmplifierData.bandBass + 1), (byte)((byte)GeneralAmplifierData.bandMiddle + 1), (byte)((byte)GeneralAmplifierData.bandTreble + 1)});
   }

   private void initAmpPrams(Context var1) {
      if (var1 != null) {
         GeneralAmplifierData.volume = SharePreUtil.getIntValue(var1, "__269_SAVE_AMP_VOL", 8);
         GeneralAmplifierData.frontRear = SharePreUtil.getIntValue(var1, "__269_SAVE_AMP_FR", 9);
         GeneralAmplifierData.leftRight = SharePreUtil.getIntValue(var1, "__269_SAVE_AMP_LR", 9);
         GeneralAmplifierData.bandTreble = SharePreUtil.getIntValue(var1, "__269_SAVE_AMP_TRE", 9);
         GeneralAmplifierData.bandMiddle = SharePreUtil.getIntValue(var1, "__269_SAVE_AMP_MID", 9);
         GeneralAmplifierData.bandBass = SharePreUtil.getIntValue(var1, "__269_SAVE_AMP_BASS", 9);
      }

   }

   private void initPenglingCmds() {
      TimerTask var1 = new TimerTask(this) {
         final MsgMgr this$0;

         {
            this.this$0 = var1;
         }

         public void run() {
            this.this$0.initAmpCmds();
            this.this$0.mTimer.cancel();
         }
      };
      Timer var2 = new Timer();
      this.mTimer = var2;
      var2.schedule(var1, 6000L, 500L);
   }

   private boolean isNeedSendDoorInfo() {
      if (mIsFourDoorVersion) {
         return mIsFLOpened != mIsFLOpenedRec || mIsFROpenedRec != mIsFROpened || mIsRLOpenedRec != mIsRLOpened || mIsRROpenedRec != mIsRROpened || mIsBackOpenedRec != mIsBackOpened;
      } else {
         return mIsFLOpened != mIsFLOpenedRec || mIsFROpenedRec != mIsFROpened || mIsBackOpenedRec != mIsBackOpened;
      }
   }

   private void realKeyClick(int var1) {
      Log.i("[PENGLING]", "REC: {canbusInfoChange} --0x01--sndSwcKey--realKeyClick--KEY_STATE=" + this.mCanBusInfoInt[3]);
      Context var2 = this.mContext;
      int[] var3 = this.mCanBusInfoInt;
      this.realKeyClick2(var2, var1, var3[2], var3[3]);
   }

   private void sendBtMusicPlayPause(boolean var1) {
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.BTAUDIO.toString(), new byte[]{22, -96, 10, 3, var1});
   }

   private void sendDoorMsg() {
      Log.i("[PENGLING]", "REC: {canbusInfoChange} --0x09--sendDoorMsg");

      try {
         mIsFLOpened = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4]);
         mIsFROpened = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4]);
         mIsRLOpened = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
         mIsRROpened = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
         mIsBackOpened = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[4]);
         if (this.isNeedSendDoorInfo()) {
            Log.i("[PENGLING]", "REC: {canbusInfoChange} --0x09--sendDoorMsg--isNeedSendDoorInfo");
            Intent var1 = new Intent("ACTION_CAN_DOOR_CHANGED");
            var1.putExtra("KEY_FOUR_DOOR_VERSION", mIsFourDoorVersion);
            var1.putExtra("KEY_DOOR_FL", mIsFLOpened);
            var1.putExtra("KEY_DOOR_FR", mIsFROpened);
            var1.putExtra("KEY_DOOR_RL", mIsRLOpened);
            var1.putExtra("KEY_DOOR_RR", mIsRROpened);
            var1.putExtra("KEY_DOOR_BACK", mIsBackOpened);
            this.mContext.sendBroadcast(var1);
         }

         mIsFLOpenedRec = mIsFLOpened;
         mIsFROpenedRec = mIsFROpened;
         mIsRLOpenedRec = mIsRLOpened;
         mIsRROpenedRec = mIsRROpened;
         mIsBackOpenedRec = mIsBackOpened;
      } catch (Exception var2) {
      }

   }

   private void sendDrirectAndAltitude(Location var1) {
      int var7 = (int)var1.getBearing();
      int var6 = (int)var1.getAltitude();
      byte var5 = (byte)(var7 >> 8);
      byte var3 = (byte)(var7 & 255);
      byte var2 = (byte)(var6 >> 8);
      byte var4 = (byte)(var6 & 255);

      try {
         StringBuilder var9 = new StringBuilder();
         Log.i("[PENGLING]", var9.append("SND: {sendDrirectAndAltitude} directValue=").append(var7).toString());
         var9 = new StringBuilder();
         Log.i("[PENGLING]", var9.append("SND: {sendDrirectAndAltitude} altitudeValue=").append(var6).toString());
         var9 = new StringBuilder();
         Log.i("[PENGLING]", var9.append("SND: {sendDrirectAndAltitude} directValueHi=").append(var5).toString());
         var9 = new StringBuilder();
         Log.i("[PENGLING]", var9.append("SND: {sendDrirectAndAltitude} directValueLo=").append(var3).toString());
         var9 = new StringBuilder();
         Log.i("[PENGLING]", var9.append("SND: {sendDrirectAndAltitude} altitudeValueHi=").append(var5).toString());
         var9 = new StringBuilder();
         Log.i("[PENGLING]", var9.append("SND: {sendDrirectAndAltitude} altitudeValueLo=").append(var4).toString());
      } catch (Exception var8) {
      }

      CanbusMsgSender.sendMsg(new byte[]{22, -96, 9, var5, var3, var2, var4});
   }

   private void sendId3Cmds(String var1, byte var2) {
      try {
         byte[] var4 = Util.exceptBOMHead(var1.getBytes("UTF-8"));
         CanbusMsgSender.sendMsg(Util.byteMerger(new byte[]{22, -96, var2}, var4));
      } catch (UnsupportedEncodingException var3) {
         var3.printStackTrace();
      }

   }

   private void sendMusicPlayPause(boolean var1) {
      byte var2 = 1;
      if (!var1) {
         var2 = 2;
      }

      CanbusMsgSender.sendMsg(new byte[]{22, -96, 10, 1, var2});
   }

   private void sendNaviCmds(String var1, byte var2) {
      if (var1 != null) {
         try {
            byte[] var4 = Util.exceptBOMHead(var1.getBytes("UTF-8"));
            CanbusMsgSender.sendMsg(Util.byteMerger(new byte[]{22, -96, 6, var2}, var4));
         } catch (UnsupportedEncodingException var3) {
            var3.printStackTrace();
         }

      }
   }

   private void setBrightness() {
      int var1 = this.mCanBusInfoInt[8];
      Log.i("[PENGLING]", "REC: {canbusInfoChange} --0x09--setBrightness level=" + var1);
      if (var1 <= 4) {
         this.setBacklightLevel(var1 + 1);
      }

   }

   private void setTrack() {
      int var2 = this.mCanBusInfoInt[2];
      byte var1 = (byte)var2;
      boolean var3 = false;
      GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(var1, (byte)0, 128, 173, 8);
      Log.i("[PENGLING]", "REC: {canbusInfoChange} --0x17--setTrack  track=" + var2);
      StringBuilder var4 = (new StringBuilder()).append("REC: {canbusInfoChange} --0x17--setTrack  IS_RIGHT=");
      if (var2 > 128) {
         var3 = true;
      }

      Log.i("[PENGLING]", var4.append(var3).toString());
      Log.i("[PENGLING]", "REC: {canbusInfoChange} --0x17--setTrack  GeneralParkData.trackAngle=" + GeneralParkData.trackAngle);
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void sndSwcKey() {
      int var1 = this.mCanBusInfoInt[2];
      if (var1 != 0) {
         if (var1 != 23) {
            if (var1 != 25) {
               switch (var1) {
                  case 17:
                     Log.i("[PENGLING]", "REC: {canbusInfoChange} --0x01--sndSwcKey--0x11");
                     this.realKeyClick(7);
                     break;
                  case 18:
                     Log.i("[PENGLING]", "REC: {canbusInfoChange} --0x01--sndSwcKey--0x12");
                     this.realKeyClick(8);
                     break;
                  case 19:
                     Log.i("[PENGLING]", "REC: {canbusInfoChange} --0x01--sndSwcKey--0x13");
                     this.realKeyClick(45);
                     break;
                  case 20:
                     Log.i("[PENGLING]", "REC: {canbusInfoChange} --0x01--sndSwcKey--0x14");
                     this.realKeyClick(46);
               }
            } else {
               Log.i("[PENGLING]", "REC: {canbusInfoChange} --0x01--sndSwcKey--0x19");
               this.realKeyClick(3);
            }
         } else {
            Log.i("[PENGLING]", "REC: {canbusInfoChange} --0x01--sndSwcKey--0x17");
            this.realKeyClick(14);
         }
      } else {
         this.realKeyClick(0);
      }

   }

   public void afterServiceNormalSetting(Context var1) {
      super.afterServiceNormalSetting(var1);
      this.mContext = var1;
   }

   public void btMusicId3InfoChange(String var1, String var2, String var3) {
      super.btMusicId3InfoChange(var1, var2, var3);
      Log.i("[PENGLING]", "SND: btMusicId3InfoChange title=" + var1);
      if (var2 != this.mArtistRec) {
         this.sendId3Cmds(var2, (byte)1);
      }

      if (var1 != this.mSongRec) {
         this.sendId3Cmds(var1, (byte)2);
      }

      if (var3 != this.mAlbumRec) {
         this.sendId3Cmds(var3, (byte)4);
      }

      this.sendId3Cmds(var1, (byte)3);
   }

   public void btMusicInfoChange() {
      super.btMusicInfoChange();
      this.sendBtMusicPlayPause(true);
   }

   public void btMusiceDestdroy() {
      super.btMusiceDestdroy();
      this.sendBtMusicPlayPause(false);
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoByte = var2;
      int[] var4 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfoInt = var4;
      int var3 = var4[1];
      if (var3 != 1) {
         if (var3 != 9) {
            if (var3 == 23) {
               Log.i("[PENGLING]", "REC: {canbusInfoChange} --0x17");
               this.setTrack();
            }
         } else {
            Log.i("[PENGLING]", "REC: {canbusInfoChange} --0x09");
            this.setBrightness();
            this.sendDoorMsg();
         }
      } else {
         Log.i("[PENGLING]", "REC: {canbusInfoChange} --0x01");
         this.sndSwcKey();
      }

   }

   public void currentVolumeInfoChange(int var1, boolean var2) {
      super.currentVolumeInfoChange(var1, var2);
   }

   public void dateTimeRepCanbus(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, boolean var10, boolean var11, boolean var12, int var13) {
      super.dateTimeRepCanbus(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13);
      String var14 = var1 + "-" + this.getDateTime2Digit(var3) + "-" + this.getDateTime2Digit(var4) + " " + this.getDateTime2Digit(var8) + ":" + this.getDateTime2Digit(var6) + ":00";

      try {
         byte[] var16 = Util.exceptBOMHead(var14.getBytes("UTF-8"));
         CanbusMsgSender.sendMsg(Util.byteMerger(new byte[]{22, -96, 6, 9}, var16));
      } catch (UnsupportedEncodingException var15) {
         var15.printStackTrace();
      }

   }

   LocChange getLocChange() {
      if (this.mLocChange == null) {
         this.mLocChange = new LocChange(this);
      }

      return this.mLocChange;
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      boolean var2;
      if (this.getCurrentCanDifferentId() == 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      mIsFourDoorVersion = var2;
      Log.i("[PENGLING]", "{initCommand}   _269");
      this.initAmpPrams(this.mContext);
      this.initPenglingCmds();
      this.updateAmplifierActivity((Bundle)null);
      IntentFilter var3 = new IntentFilter();
      var3.addAction("AUTONAVI_STANDARD_BROADCAST_SEND");
      var1.registerReceiver(this.receiver, var3);
      Intent var4 = new Intent();
      var4.setComponent(new ComponentName("com.hzbhd.midware.service", "com.hzbhd.midware.location.LocationService"));
      var1.bindService(var4, new ServiceConnection(this) {
         final MsgMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onServiceConnected(ComponentName var1, IBinder var2) {
            this.this$0.mILocAidlInterface = ILocAidlInterface.Stub.asInterface(var2);

            try {
               this.this$0.mILocAidlInterface.addCallBack(this.this$0.getLocChange());
            } catch (RemoteException var3) {
               var3.printStackTrace();
            }

         }

         public void onServiceDisconnected(ComponentName var1) {
         }
      }, 1);
   }

   public void musicDestroy() {
      super.musicDestroy();
      Log.i("[PENGLING]", "SND: {musicDestroy}");
      this.sendMusicPlayPause(false);
   }

   public void musicInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, byte var8, byte var9, byte var10, String var11, String var12, String var13, long var14, byte var16, int var17, boolean var18, long var19, String var21, String var22, String var23, boolean var24) {
      super.musicInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var16, var17, var18, var19, var21, var22, var23, var24);
      var3 = (int)(var19 / 1000L);
      CanbusMsgSender.sendMsg(new byte[]{22, -96, 8, (byte)(var3 % 3600 / 60), (byte)(var3 % 60), var6, var7});
      if (var23 != this.mArtistRec) {
         this.sendId3Cmds(var23, (byte)1);
      }

      if (var21 != this.mSongRec) {
         this.sendId3Cmds(var21, (byte)2);
      }

      if (var22 != this.mAlbumRec) {
         this.sendId3Cmds(var22, (byte)4);
      }

      this.sendMusicPlayPause(var18);
      this.mIsPayingRec = var18;
      this.mSongRec = var21;
      this.mArtistRec = var23;
      this.mAlbumRec = var22;
   }

   public void musicLrcInfoChange(String var1) {
      super.musicLrcInfoChange(var1);
      Log.i("[PENGLING]", "SND: musicLrcurrentAmpVolInfoChange lrc=" + var1);
      this.sendId3Cmds(var1, (byte)3);
   }

   public void radioInfoChange(int var1, String var2, String var3, String var4, int var5) {
      super.radioInfoChange(var1, var2, var3, var4, var5);
      byte var9 = CommUtil.getRadioCurrentBand(var2, (byte)1, (byte)2, (byte)3, (byte)17, (byte)18);
      byte var6 = CommUtil.getRadioFreqHiOrLow(var2, var3, true);
      byte var7 = CommUtil.getRadioFreqHiOrLow(var2, var3, false);
      byte var8 = (byte)var9;
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.FM.toString(), new byte[]{22, -96, 10, 2});
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.FM.toString(), new byte[]{22, -96, 11, var8, var6, var7});
   }

   public void sourceSwitchNoMediaInfoChange(boolean var1) {
      super.sourceSwitchNoMediaInfoChange(var1);
      if (!var1) {
         this.initAmpPrams(this.mContext);
         this.initPenglingCmds();
      }

   }

   void updateAmpUi(Context var1) {
      this.updateAmplifierActivity((Bundle)null);
   }

   private class LocChange extends LocationChange {
      final MsgMgr this$0;

      private LocChange(MsgMgr var1) {
         this.this$0 = var1;
      }

      // $FF: synthetic method
      LocChange(MsgMgr var1, Object var2) {
         this(var1);
      }

      public void getBearAndAltitude(float var1, double var2) throws RemoteException {
         super.getBearAndAltitude(var1, var2);
         int var9 = (int)var1;
         int var8 = (int)var2;
         byte var6 = (byte)(var9 >> 8);
         byte var5 = (byte)(var9 & 255);
         byte var4 = (byte)(var8 >> 8);
         byte var7 = (byte)(var8 & 255);

         try {
            StringBuilder var10 = new StringBuilder();
            Log.i("[PENGLING]", var10.append("SND: {sendDrirectAndAltitude} directValue=").append(var9).toString());
            var10 = new StringBuilder();
            Log.i("[PENGLING]", var10.append("SND: {sendDrirectAndAltitude} altitudeValue=").append(var8).toString());
            var10 = new StringBuilder();
            Log.i("[PENGLING]", var10.append("SND: {sendDrirectAndAltitude} directValueHi=").append(var6).toString());
            var10 = new StringBuilder();
            Log.i("[PENGLING]", var10.append("SND: {sendDrirectAndAltitude} directValueLo=").append(var5).toString());
            var10 = new StringBuilder();
            Log.i("[PENGLING]", var10.append("SND: {sendDrirectAndAltitude} altitudeValueHi=").append(var6).toString());
            var10 = new StringBuilder();
            Log.i("[PENGLING]", var10.append("SND: {sendDrirectAndAltitude} altitudeValueLo=").append(var7).toString());
         } catch (Exception var11) {
         }

         CanbusMsgSender.sendMsg(new byte[]{22, -96, 9, var6, var5, var4, var7});
      }
   }
}
