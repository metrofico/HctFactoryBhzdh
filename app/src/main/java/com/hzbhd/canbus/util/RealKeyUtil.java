package com.hzbhd.canbus.util;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.hzbhd.canbus.activity.SwcActivity;
import com.hzbhd.canbus.adapter.util.Util;
import com.hzbhd.canbus.config.CanIdSpecialConfig;
import com.hzbhd.canbus.config.CanbusConfig;
import com.hzbhd.canbus.config.CustomKeyConfig;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.msg_mgr.MsgMgrInterfaceUtil;
import com.hzbhd.commontools.SourceConstantsDef;
import com.hzbhd.commontools.utils.ConfigUtil;
import com.hzbhd.midware.constant.HotKeyConstant;
import com.hzbhd.proxy.keydispatcher.SendKeyManager;
import com.hzbhd.ui.util.BaseUtil;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;
import java.util.TimerTask;
import kotlin.Unit;

public class RealKeyUtil {
   private static final long KEY_PARAM_KNOB_INTERVAL = 150L;
   public static final int K_HANGUP = 98;
   public static final int K_PICKUP = 97;
   static final String TAG = "RealKeyUtil";
   private static int downSum;
   private static boolean lastIsLongKey;
   private static boolean lastSendKeyIsNext;
   private static boolean lastSendKeyIsPre;
   private static HashMap mCompoundKeyMap;
   private static Intent mIntent;
   private static TimerUtil mKnobTimer;
   private static int mPreCompoundKey;
   private static int mPreKeyStatus;
   private static int mPreKeyValue;
   private static int mPreWhatKey;
   private static SwcActivity mSwcActivity;
   private static int upSum;

   static {
      HashMap var0 = new HashMap();
      mCompoundKeyMap = var0;
      var0.put(184, new int[]{3, 15});
      mCompoundKeyMap.put(188, new int[]{97, 98});
      mCompoundKeyMap.put(189, new int[]{50, 15});
      mCompoundKeyMap.put(199, new int[]{62, 14});
      mCompoundKeyMap.put(200, new int[]{2, 15});
      mCompoundKeyMap.put(201, new int[]{187, 14});
      mCompoundKeyMap.put(202, new int[]{187, 15});
      mCompoundKeyMap.put(203, new int[]{62, 15});
      mCompoundKeyMap.put(206, new int[]{21, 14});
      mCompoundKeyMap.put(207, new int[]{20, 15});
      mCompoundKeyMap.put(464, new int[]{3, 14, 15});
      mCompoundKeyMap.put(465, new int[]{45, 14});
      mCompoundKeyMap.put(466, new int[]{46, 15});
      mCompoundKeyMap.put(467, new int[]{33, 14});
      mCompoundKeyMap.put(468, new int[]{34, 15});
      mCompoundKeyMap.put(469, new int[]{35, 29});
      mCompoundKeyMap.put(470, new int[]{36, 27});
      mCompoundKeyMap.put(471, new int[]{37, 27});
      mCompoundKeyMap.put(472, new int[]{38, 29});
      upSum = 0;
      downSum = 0;
      lastSendKeyIsPre = false;
      lastSendKeyIsNext = false;
      lastIsLongKey = false;
   }

   public static void compoundKey(Context var0, int[] var1, int var2) {
      if (com.hzbhd.util.LogUtil.log5()) {
         com.hzbhd.util.LogUtil.d("compoundKey: " + var2 + "," + Arrays.toString(var1));
      }

      for(int var3 = var1.length - 1; var3 >= 0; --var3) {
         if (isSatisfied(var1[var3])) {
            realKeyLongClick(var0, var1[var3], var2);
            break;
         }
      }

   }

   public static boolean isCompoundKey(Context var0, int var1, int var2) {
      if (var2 == 1 && mCompoundKeyMap.containsKey(var1)) {
         compoundKey(var0, (int[])mCompoundKeyMap.get(var1), var2);
      } else {
         if (var2 != 0 || !mCompoundKeyMap.containsKey(mPreCompoundKey)) {
            return false;
         }

         realKeyLongClick(var0, var1, var2);
      }

      mPreCompoundKey = var1;
      return true;
   }

   private static boolean isSatisfied(int var0) {
      if (var0 == 14) {
         return "IN_CALLING".equals(MediaShareData.Bluetooth.INSTANCE.getCallState());
      } else {
         boolean var1 = false;
         String var2;
         if (var0 != 15) {
            if (var0 != 27 && var0 != 29) {
               if (var0 != 98) {
                  return true;
               } else {
                  var2 = MediaShareData.Bluetooth.INSTANCE.getCallState();
                  var2.hashCode();
                  return var2.equals("OUT_CALLING") || var2.equals("CALLING");
               }
            } else {
               SourceConstantsDef.SOURCE_ID var4 = SourceConstantsDef.SOURCE_ID.getType(MediaShareData.Source.INSTANCE.getId());
               if (var4 == SourceConstantsDef.SOURCE_ID.MUSIC || var4 == SourceConstantsDef.SOURCE_ID.VIDEO) {
                  var1 = true;
               }

               return var1;
            }
         } else {
            var2 = MediaShareData.Bluetooth.INSTANCE.getCallState();
            var2.hashCode();
            switch (var2) {
               case "OUT_CALLING":
               case "IN_CALLING":
               case "CALLING":
                  return true;
               default:
                  return false;
            }
         }
      }
   }

   public static void keyTime(Context var0) {
      Intent var1 = new Intent("android.intent.action.QUICK_CLOCK");
      mIntent = var1;
      var1.setFlags(268435456);
      var0.startActivity(mIntent);
   }

   public static void realKeyClick(Context var0, int var1) {
      if (var1 != 0) {
         LogUtil.showLog("getKey:" + var1);
         if (var1 != 196 && var1 != 183) {
            int var2 = var1;
            if (var1 == 187) {
               if (CanIdSpecialConfig.haveSpeechModule(CanbusConfig.INSTANCE.getCanType())) {
                  KeyBroadcast.sendSpeechBroadcast(var0);
                  var2 = 187;
               } else {
                  var2 = 3;
               }
            }

            var1 = var2;
            if (var2 == 97) {
               var1 = 14;
            }

            var2 = var1;
            if (var1 == 98) {
               var2 = 15;
            }

            LogUtil.showLog("setKey:" + var2);
            Log.d("SWC-SEND-WHAT-KEY？", "0x" + Integer.toHexString(var2));
            SendKeyManager.getInstance().setKeyEvent(var2, HotKeyConstant.KeyState.CLICK, true);
         } else {
            keyTime(var0);
         }
      }
   }

   public static void realKeyClick1(Context var0, int var1, int var2, int var3) {
      RealKeyTimerManager.realKeyClick1(var0, var1, var2, var3);
      LogUtil.showLog("whatKey:" + var1 + " keyValue:" + var2 + " keyState:" + var3);
      if ((var1 == 8 || var1 == 7) && var3 == 2) {
         realKeyClick(var0, var1);
      } else {
         if (var1 == 45 && var3 == 2 && mPreKeyStatus == 1) {
            realKeyClick(var0, 21);
         }

         if (var1 == 46 && var3 == 2 && mPreKeyStatus == 1) {
            realKeyClick(var0, 20);
         }

         if (var2 == 0 && var3 == 0 && mPreKeyStatus == 1) {
            realKeyClick(var0, mPreWhatKey);
         }

         mPreKeyStatus = var3;
         mPreWhatKey = var1;
         mPreKeyValue = var2;
      }
   }

   public static void realKeyClick2(Context var0, int var1, int var2, int var3) {
      RealKeyTimerManager.realKeyClick2(var0, var1, var2, var3);
      LogUtil.showLog("whatKey:" + var1 + " keyValue:" + var2 + " keyState:" + var3);
      if ((var1 == 8 || var1 == 7) && var3 == 2) {
         realKeyClick(var0, var1);
      } else {
         if (var1 == 45 && var3 == 2 && mPreKeyStatus == 1) {
            realKeyClick(var0, 21);
         }

         if (var1 == 46 && var3 == 2 && mPreKeyStatus == 1) {
            realKeyClick(var0, 20);
         }

         if (var2 == mPreKeyValue && var3 == 0 && mPreKeyStatus == 1) {
            realKeyClick(var0, mPreWhatKey);
         }

         mPreKeyStatus = var3;
         mPreWhatKey = var1;
         mPreKeyValue = var2;
      }
   }

   public static void realKeyClick3(Context var0, int var1, int var2, int var3) {
      RealKeyTimerManager.realKeyClick3(var0, var1, var2, var3);
      if (var3 != 0) {
         LogUtil.showLog("whatKey:" + var1 + " keyValue:" + var2 + " keyState:" + var3);
         if (var3 == 1) {
            realKeyLongClick(var0, var1, 1);
            realKeyLongClick(var0, var1, 0);
         } else {
            runKnob(var0, var1, var3);
         }

      }
   }

   public static void realKeyClick3_1(Context var0, int var1, int var2, int var3) {
      RealKeyTimerManager.realKeyClick3_1(var0, var1, var2, var3);
      LogUtil.showLog("whatKey:" + var1 + " keyValue:" + var2 + " keyState:" + var3);
      if (var1 == 7 || var1 == 8) {
         if (mKnobTimer == null) {
            mKnobTimer = new TimerUtil();
         }

         mKnobTimer.stopTimer();
         runKnob(var0, var1, var3);
      }

   }

   public static void realKeyClick3_2(Context var0, int var1, int var2, int var3) {
      RealKeyTimerManager.realKeyClick3_2(var0, var1, var2, var3);
      LogUtil.showLog("whatKey:" + var1 + " keyValue:" + var2 + " keyState:" + var3);
      if (var1 == 47 || var1 == 48) {
         if (mKnobTimer == null) {
            mKnobTimer = new TimerUtil();
         }

         mKnobTimer.stopTimer();
         runKnob(var0, var1, var3);
      }

   }

   public static void realKeyClick5(Context var0, int var1, int var2, int var3) {
      RealKeyTimerManager.realKeyClick5(var0, var1, var2, var3);
      LogUtil.showLog("fang", "5 whatKey:" + var1 + " keyValue:" + var2 + " keyState:" + var3);
      if (mPreWhatKey == 45 && var1 == 45 && var3 == 1) {
         ++upSum;
         Log.d("fang", "lastSendKeyIsPre--------:" + lastSendKeyIsPre);
         if (upSum > 5 && !lastSendKeyIsPre) {
            lastSendKeyIsPre = true;
            realKeyClick(var0, 21);
            mPreWhatKey = 0;
            lastIsLongKey = true;
         }

      } else {
         Log.d("fang", "lastSendKeyIsPre=false;");
         if (var1 == 0) {
            lastSendKeyIsPre = false;
         }

         var2 = mPreWhatKey;
         if (var2 == 46 && var1 == 46 && var3 == 1) {
            var1 = downSum + 1;
            downSum = var1;
            if (var1 > 5 && !lastSendKeyIsNext) {
               lastSendKeyIsNext = true;
               realKeyClick(var0, 20);
               mPreWhatKey = 0;
               lastIsLongKey = true;
            }

         } else {
            if (var1 == 0) {
               lastSendKeyIsNext = false;
            }

            upSum = 0;
            downSum = 0;
            if (var2 == 45 && var1 == 0 && !lastIsLongKey) {
               realKeyClick(var0, 45);
               lastIsLongKey = false;
            } else if (var2 == 46 && var1 == 0 && !lastIsLongKey) {
               realKeyClick(var0, 46);
               lastIsLongKey = false;
            } else if (var3 == 1 && var1 != 45 && var1 != 46) {
               realKeyClick(var0, var1);
               lastIsLongKey = false;
            }

            if (var1 == 0 && lastIsLongKey) {
               lastIsLongKey = false;
            }

            mPreWhatKey = var1;
         }
      }
   }

   public static void realKeyLongClick(Context var0, int var1, int var2) {
      label30: {
         RealKeyTimerManager.realKeyLongClick(var0, var1, var2);
         Log.i("RealKeyUtil", "realKeyLongClick1 keyState:" + var2 + " mPreKeyStatus:" + mPreKeyStatus);
         if (var2 == 0) {
            int var3 = mPreKeyStatus;
            if (var3 == 1 || var3 == 2) {
               RealKeyUtil.LongClickHelper.getInstance().keyUp(var0, mPreWhatKey);
               break label30;
            }
         }

         if ((var2 == 1 || var2 == 2) && mPreKeyStatus == 0) {
            RealKeyUtil.LongClickHelper.getInstance().keyDown(var0, var1);
         }
      }

      mPreWhatKey = var1;
      mPreKeyStatus = var2;
   }

   private static void runKnob(Context var0, int var1, int var2) {
      Log.i("RealKeyUtil", "runKnob: in time: " + var2);
      if (mKnobTimer == null) {
         mKnobTimer = new TimerUtil();
      }

      mKnobTimer.stopTimer();
      mKnobTimer.startTimer(new TimerTask(var2, var1, var0) {
         int mTime;
         final Context val$context;
         final int val$key;
         final int val$time;

         {
            this.val$time = var1;
            this.val$key = var2;
            this.val$context = var3;
            this.mTime = 0;
         }

         public void run() {
            int var1 = this.mTime++;
            if (var1 < this.val$time) {
               Log.d("RealKeyUtil", "runKnob: " + this.val$key + ", " + this.mTime);
               RealKeyUtil.realKeyLongClick(this.val$context, this.val$key, 1);
               RealKeyUtil.realKeyLongClick(this.val$context, this.val$key, 0);
            } else {
               RealKeyUtil.mKnobTimer.stopTimer();
            }

         }
      }, 0L, 150L);
   }

   public static void setSwcActivity(SwcActivity var0) {
      mSwcActivity = var0;
   }

   static class LongClickHelper {
      private static final long KEY_PARAM_LONG_CLICK_CONTINUE_INTERVAL = 166L;
      private static final long KEY_PARAM_LONG_CLICK_TIME = 500L;
      private static LongClickHelper instance;
      private final String mDeviceInfo = ConfigUtil.getDeviceId();
      private final Handler mHandler = new Handler(Looper.getMainLooper());
      private boolean mLongClickFlag;
      private Runnable mRunnable;

      public static LongClickHelper getInstance() {
         if (instance == null) {
            instance = new LongClickHelper();
         }

         return instance;
      }

      private boolean isUpDownLongClickDisable() {
         return Util.contains(this.mDeviceInfo, "XPH");
      }

      // $FF: synthetic method
      static Unit lambda$keyUp$1(int var0) {
         RealKeyUtil.mSwcActivity.learnKey(var0);
         return null;
      }

      private void runKey(Context var1, int var2) {
         Log.i("RealKeyUtil", "runKey: in");
         TimerUtil var3 = new TimerUtil();
         var3.startTimer(new TimerTask(this, var2, var1, var3) {
            final LongClickHelper this$0;
            final Context val$context;
            final int val$key;
            final TimerUtil val$timerUtil;

            {
               this.this$0 = var1;
               this.val$key = var2;
               this.val$context = var3;
               this.val$timerUtil = var4;
            }

            public void run() {
               if (this.this$0.mLongClickFlag) {
                  Log.d("RealKeyUtil", "runKey: " + this.val$key);
                  RealKeyUtil.realKeyClick(this.val$context, this.val$key);
               } else {
                  this.val$timerUtil.stopTimer();
               }

            }
         }, 0L, 166L);
      }

      public void keyDown(Context var1, int var2) {
         if (RealKeyUtil.mSwcActivity == null) {
            RealKeyUtil$LongClickHelper$$ExternalSyntheticLambda0 var3 = new RealKeyUtil$LongClickHelper$$ExternalSyntheticLambda0(this, var1, var2);
            this.mRunnable = var3;
            this.mHandler.postDelayed(var3, 500L);
         }

      }

      public void keyUp(Context var1, int var2) {
         if (RealKeyUtil.mSwcActivity == null) {
            Runnable var3 = this.mRunnable;
            if (var3 != null) {
               this.mHandler.removeCallbacks(var3);
            }

            if (!this.mLongClickFlag && !((MsgMgrInterfaceUtil)Objects.requireNonNull(MsgMgrFactory.getCanMsgMgrUtil(var1))).customShortClick(var1, var2)) {
               RealKeyUtil.realKeyClick(var1, CustomKeyConfig.INSTANCE.getShortKey(var2));
            }

            this.mLongClickFlag = false;
         } else {
            BaseUtil.INSTANCE.runUi(new RealKeyUtil$LongClickHelper$$ExternalSyntheticLambda1(var2));
         }

      }

      // $FF: synthetic method
      void lambda$keyDown$0$com_hzbhd_canbus_util_RealKeyUtil$LongClickHelper(Context var1, int var2) {
         this.startLongClick(var1, var2);
      }

      public void startLongClick(Context var1, int var2) {
         if (com.hzbhd.util.LogUtil.log5()) {
            com.hzbhd.util.LogUtil.d("startLongClick: " + var2);
         }

         boolean var4 = true;
         this.mLongClickFlag = true;
         int var3 = CustomKeyConfig.INSTANCE.getShortKey(var2);
         if (!((MsgMgrInterfaceUtil)Objects.requireNonNull(MsgMgrFactory.getCanMsgMgrUtil(var1))).customLongClick(var1, var3)) {
            if (var3 != 7 && var3 != 8 && var3 != 22 && var3 != 23 && var3 != 47 && var3 != 48) {
               var2 = CustomKeyConfig.INSTANCE.getLongKey(var2);
               RealKeyUtil.realKeyClick(var1, var2);
               if (var2 == 0) {
                  var4 = false;
               }

               this.mLongClickFlag = var4;
            } else {
               this.runKey(var1, var3);
            }
         }

      }
   }
}
