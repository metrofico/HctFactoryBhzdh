package com.hzbhd.canbus.car._283;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.util.FutureUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.commontools.SourceConstantsDef;
import java.util.regex.Pattern;

public class AMapBroadcast extends BroadcastReceiver {
   private static final String INIT_TIME = "0:0";
   private static final int WHAT_ONLINE_IN = 0;
   private static final int WHAT_ONLINE_OUT = 1;
   public static boolean isOnlinePlay;
   private int TempAllDistance = 0;
   private int TempCarDirection = 0;
   private int TempDestinationDistance = 0;
   private int TempIcon;
   private String TempName = "";
   private int TempNextDistance = 0;
   private String TempPlanTime = "0:0";
   private int TempSurplusAllTime = 0;
   private String TempSurplusAllTimeStr = "0:0";
   private boolean isStartNaving = false;
   private Context mContext;
   private Handler mHandler = new Handler(this) {
      final AMapBroadcast this$0;

      {
         this.this$0 = var1;
      }

      public void handleMessage(Message var1) {
         int var2 = var1.what;
         if (var2 != 0) {
            if (var2 == 1) {
               MeterManager.sendMediaMeterData(" ", " ", " ", " ");
               AMapBroadcast.isOnlinePlay = false;
            }
         } else if (this.this$0.mContext != null) {
            MeterManager.send0xE6Null(0);
            MeterManager.sendMediaMeterData(this.this$0.mContext.getString(2131760710), " ", " ", " ");
            AMapBroadcast.isOnlinePlay = true;
         }

      }
   };
   private String playPackageNameNow = "";
   private String tempTime = "0:0";

   private int getCarDirection(int var1) {
      double var2 = (double)var1;
      if (var2 > 337.5 && var1 <= 360 || var1 > 0 && var2 < 22.5) {
         return 7;
      } else if (var2 > 22.5 && var2 < 67.5) {
         return 8;
      } else if (var2 > 67.5 && var2 < 112.5) {
         return 1;
      } else if (var2 > 112.5 && var2 < 157.5) {
         return 2;
      } else if (var2 > 157.5 && var2 < 202.5) {
         return 3;
      } else if (var2 > 202.5 && var2 < 247.5) {
         return 4;
      } else if (var2 > 247.5 && var2 < 292.5) {
         return 5;
      } else {
         return var2 > 292.5 && var2 < 337.5 ? 6 : 0;
      }
   }

   private int getIcon(int var1) {
      if (var1 == 9) {
         return 1;
      } else if (var1 == 5) {
         return 2;
      } else if (var1 == 3) {
         return 3;
      } else if (var1 == 7) {
         return 4;
      } else if (var1 != 19 && var1 != 8) {
         if (var1 == 6) {
            return 6;
         } else if (var1 == 2) {
            return 7;
         } else if (var1 == 4) {
            return 8;
         } else if (var1 == 1) {
            return 9;
         } else if (var1 == 11) {
            return 10;
         } else if (var1 == 17) {
            return 11;
         } else if (var1 == 12) {
            return 12;
         } else if (var1 == 18) {
            return 13;
         } else {
            return var1 == 15 ? 14 : 0;
         }
      } else {
         return 5;
      }
   }

   private boolean isStartNai(int var1) {
      boolean var2;
      if (var1 != 8 && var1 != 10) {
         var2 = false;
      } else {
         var2 = true;
      }

      return var2;
   }

   private boolean isStopNai(int var1) {
      boolean var2;
      if (var1 != 12 && var1 != 9) {
         var2 = false;
      } else {
         var2 = true;
      }

      return var2;
   }

   private String removeChinese(String var1) {
      return TextUtils.isEmpty(var1) ? "" : Pattern.compile("[一-龥]").matcher(var1.trim()).replaceAll("");
   }

   private String removeChinese2(String var1) {
      if (TextUtils.isEmpty(var1)) {
         return "";
      } else {
         String[] var2 = Pattern.compile("[一-龥]").matcher(var1.trim()).replaceAll(":").replace("::", ":").split(":");
         if (var1.contains("时") && !var1.contains("分") && var2.length == 1) {
            var1 = var2[0] + ":0";
            this.tempTime = var1;
            return var1;
         } else if (var2.length == 2) {
            var1 = var2[0] + ":" + var2[1];
            this.tempTime = var1;
            return var1;
         } else if (var2.length == 1) {
            var1 = "0:" + var2[0];
            this.tempTime = var1;
            return var1;
         } else {
            return this.tempTime;
         }
      }
   }

   private void sendSourceMsg1(String var1) {
      byte[] var2 = var1.getBytes();
      CanbusMsgSender.sendMsg(DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(new byte[]{22, -111, 0, 0}, var2), 28));
   }

   private void sendSourceMsg2(String var1, int var2) {
      byte[] var3 = var1.getBytes();
      CanbusMsgSender.sendMsg(DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(new byte[]{22, (byte)var2, 0}, var3), 27));
   }

   public void onReceive(Context var1, Intent var2) {
      String var23 = var2.getAction();
      if (!TextUtils.isEmpty(var23)) {
         if (var23.equals("AUTONAVI_STANDARD_BROADCAST_SEND")) {
            Bundle var27 = var2.getExtras();
            int var11 = var27.getInt("EXTRA_STATE");
            if (this.isStopNai(var11)) {
               this.TempAllDistance = 0;
               this.TempDestinationDistance = 0;
               this.TempNextDistance = 0;
               this.TempSurplusAllTime = 0;
               this.TempPlanTime = "0:0";
               this.TempName = "";
               this.TempSurplusAllTimeStr = "0:0";
               this.TempCarDirection = 0;
               this.TempIcon = 0;
               this.isStartNaving = false;
            }

            if (this.isStartNai(var11)) {
               this.isStartNaving = true;
            }

            var11 = var27.getInt("ROUTE_ALL_DIS");
            int var12 = var27.getInt("SEG_REMAIN_DIS");
            int var13 = var27.getInt("ROUTE_REMAIN_DIS");
            int var15 = var27.getInt("ICON");
            int var16 = var27.getInt("ROUTE_REMAIN_TIME");
            String var25 = this.removeChinese2(var27.getString("ROUTE_REMAIN_TIME_AUTO"));
            String var24 = this.removeChinese(var27.getString("ETA_TEXT"));
            int var14 = var27.getInt("CAR_DIRECTION");
            if (var11 == 0) {
               var11 = this.TempAllDistance;
            } else {
               this.TempAllDistance = var11;
            }

            if (var12 == 0) {
               var12 = this.TempNextDistance;
            } else {
               this.TempNextDistance = var12;
            }

            if (var13 == 0) {
               var13 = this.TempDestinationDistance;
            } else {
               this.TempDestinationDistance = var13;
            }

            if (var16 != 0) {
               this.TempSurplusAllTime = var16;
            }

            if (TextUtils.isEmpty(var24)) {
               var24 = this.TempPlanTime;
            } else {
               this.TempPlanTime = var24;
            }

            if (TextUtils.isEmpty(var25)) {
               var25 = this.TempSurplusAllTimeStr;
            } else {
               this.TempSurplusAllTimeStr = var25;
            }

            if (var14 == 0) {
               var14 = this.TempCarDirection;
            } else {
               this.TempCarDirection = var14;
            }

            if (var15 == 0) {
               var15 = this.TempIcon;
            } else {
               this.TempIcon = var15;
            }

            int var17 = Integer.parseInt(var25.split(":")[0]);
            int var21 = Integer.parseInt(var25.split(":")[1].replaceAll("<", ""));
            int var19 = Integer.parseInt(var24.split(":")[0]);
            int var20 = Integer.parseInt(var24.split(":")[1]);
            if (var15 != 0 && !this.isStartNaving) {
               this.isStartNaving = true;
            }

            int var18 = DataHandleUtils.setIntByteWithBit(0, 7, true);
            if (this.isStartNaving) {
               var16 = DataHandleUtils.setIntByteWithBit(0, 7, true);
            } else {
               var16 = 0;
            }

            var12 *= 10;
            byte var10 = (byte)(var12 >> 24);
            byte var8 = (byte)(var12 >> 16);
            byte var7 = (byte)(var12 >> 8);
            byte var3 = (byte)var12;
            var11 = (int)((double)(var11 - var13) / (double)var11 * 100.0);
            var12 = var13 * 10;
            byte var9 = (byte)(var12 >> 24);
            byte var5 = (byte)(var12 >> 16);
            byte var6 = (byte)(var12 >> 8);
            byte var4 = (byte)var12;
            var12 = this.getCarDirection(var14);
            CanbusMsgSender.sendMsg(new byte[]{22, -28, (byte)var18, (byte)var16, var10, var8, var7, var3, (byte)var11, var9, var5, var6, var4, (byte)var12, (byte)var19, (byte)var20, (byte)var17, (byte)var21, (byte)this.getIcon(var15), 0, 0});
            var24 = var27.getString("NEXT_ROAD_NAME");
            if (TextUtils.isEmpty(var24)) {
               var24 = this.TempName;
            } else {
               this.TempName = var24;
            }

            byte[] var26 = DataHandleUtils.makeBytesFixedLength(var24.getBytes(), 30);
            CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -27, 0}, var26));
         } else if (var23.equals("AUDIO_PLAY_NAME_AND_STAUS")) {
            this.mContext = var1;
            boolean var22 = var2.getBooleanExtra("isPlay", false);
            var2.getStringExtra("processName");
            Log.d("scyscyscy", "网络播放onReceive: " + var22 + "------当前源-->" + FutureUtil.instance.getCurrentValidSource().name());
            if (var22) {
               this.mHandler.removeMessages(0);
               this.mHandler.sendEmptyMessageDelayed(0, 1000L);
            } else if (FutureUtil.instance.getCurrentValidSource().name().equals(SourceConstantsDef.SOURCE_ID.NORMAL_SOURCE.name())) {
               this.mHandler.sendEmptyMessage(1);
            }
         } else {
            Log.d("scyscyscy", "---------->action 是空置");
         }
      }

   }
}
