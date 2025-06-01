package com.hzbhd.adapter;

import com.hzbhd.canbus.util.MediaShareData;
import com.hzbhd.constant.bluetooth.BtConstants;
import com.hzbhd.proxy.bluetooth.bean.BtCall;
import com.hzbhd.proxy.bluetooth.bean.BtStatus;
import com.hzbhd.proxy.bluetooth.bean.Device;
import com.hzbhd.proxy.bluetooth.listener.BtListener;
import com.hzbhd.proxy.bluetooth.manager.BtManager;
import com.hzbhd.util.LogUtil;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;

@Metadata(
   d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u001e\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u001d\u001a\u00020\u001eJ\u0006\u0010\u001f\u001a\u00020\u001eJ\u0006\u0010 \u001a\u00020\u001eJ\u0006\u0010!\u001a\u00020\u001eJ\u0006\u0010\"\u001a\u00020\u001eJ\u000e\u0010#\u001a\u00020\u001e2\u0006\u0010$\u001a\u00020\u0014J\u0016\u0010%\u001a\u00020\u001e2\u0006\u0010&\u001a\u00020\u00142\u0006\u0010'\u001a\u00020\u000fJ\u0016\u0010(\u001a\u00020\u001e2\u0006\u0010&\u001a\u00020\u00142\u0006\u0010'\u001a\u00020\u000fJ\u0010\u0010)\u001a\u00020\u00062\u0006\u0010*\u001a\u00020\tH\u0002J\u0006\u0010+\u001a\u00020\u0014J\u0006\u0010,\u001a\u00020\u001eJ\u000e\u0010-\u001a\u00020\u001e2\u0006\u0010.\u001a\u00020\u000fJ\u0016\u0010/\u001a\u00020\u001e2\u0006\u0010&\u001a\u00020\u00142\u0006\u0010/\u001a\u00020\u000fJ\u0006\u00100\u001a\u00020\u001eJ\u000e\u00101\u001a\u00020\u001e2\u0006\u00101\u001a\u00020\u000fJ\u000e\u00102\u001a\u00020\u001e2\u0006\u00103\u001a\u00020\u0014J\u000e\u00104\u001a\u00020\u001e2\u0006\u00105\u001a\u00020\u000fJ\u000e\u00106\u001a\u00020\u001e2\u0006\u00107\u001a\u00020\u0014J\u000e\u00108\u001a\u00020\u001e2\u0006\u00109\u001a\u00020\u000fJ\u0006\u0010:\u001a\u00020\u001eJ\u0010\u0010;\u001a\u00020\u001e2\u0006\u0010*\u001a\u00020\tH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\b\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0016\u001a\u00020\u0017¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u000e\u0010\u001a\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u001cX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006<"},
   d2 = {"Lcom/hzbhd/adapter/BtAdapter;", "", "()V", "btManager", "Lcom/hzbhd/proxy/bluetooth/manager/BtManager;", "callStatus", "", "callTime", "curCallState", "Lcom/hzbhd/constant/bluetooth/BtConstants$CallState;", "getCurCallState", "()Lcom/hzbhd/constant/bluetooth/BtConstants$CallState;", "setCurCallState", "(Lcom/hzbhd/constant/bluetooth/BtConstants$CallState;)V", "isAudioTransferTowardsAG", "", "isCallingFlag", "isHfpConnected", "isMicMute", "mAlbum", "", "mArtist", "mCanInf", "Lcom/hzbhd/canbus/util/MediaShareData$Bluetooth;", "getMCanInf", "()Lcom/hzbhd/canbus/util/MediaShareData$Bluetooth;", "mTitle", "phoneNumber", "", "a2dpNext", "", "a2dpPause", "a2dpPlay", "a2dpPrev", "answer", "call", "number", "connectA2dp", "address", "conn", "connectHfp", "getCallStatus", "callState", "getHfpDeviceName", "hangup", "muteMic", "mute", "pair", "registerListener", "scan", "sendKey", "key", "setAutoConn", "autoConn", "setBtName", "name", "setMicOut", "out", "syncPhoneBook", "updateCallState", "CanBusInfo_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class BtAdapter {
   public static final BtAdapter INSTANCE = new BtAdapter();
   private static final BtManager btManager;
   private static int callStatus;
   private static int callTime;
   private static BtConstants.CallState curCallState;
   private static boolean isAudioTransferTowardsAG;
   private static boolean isCallingFlag;
   private static boolean isHfpConnected;
   private static boolean isMicMute;
   private static String mAlbum;
   private static String mArtist;
   private static final MediaShareData.Bluetooth mCanInf;
   private static String mTitle;
   private static byte[] phoneNumber;

   static {
      btManager = BtManager.Companion.getInstance();
      phoneNumber = new byte[0];
      mTitle = "";
      mArtist = "";
      mAlbum = "";
      mCanInf = MediaShareData.Bluetooth.INSTANCE;
      curCallState = BtConstants.CallState.NORMAL;
   }

   private BtAdapter() {
   }

   private final int getCallStatus(BtConstants.CallState var1) {
      int var3 = BtAdapter.WhenMappings.$EnumSwitchMapping$0[var1.ordinal()];
      byte var2 = 1;
      if (var3 != 1) {
         if (var3 != 2) {
            var2 = 4;
            if (var3 != 3) {
               if (var3 != 4) {
                  if (var3 != 5) {
                     return callStatus;
                  }

                  return 5;
               }

               return 0;
            }
         }

         return var2;
      } else {
         return 2;
      }
   }

   private final void updateCallState(BtConstants.CallState var1) {
      callStatus = this.getCallStatus(var1);
      boolean var3;
      if (var1 != BtConstants.CallState.NORMAL) {
         var3 = true;
      } else {
         var3 = false;
      }

      isCallingFlag = var3;
      int var2 = BtAdapter.WhenMappings.$EnumSwitchMapping$0[var1.ordinal()];
      if (var2 != 1) {
         if (var2 != 2) {
            if (var2 != 3) {
               if (var2 == 4) {
                  BtConstants.CallState var4 = curCallState;
                  var2 = BtAdapter.WhenMappings.$EnumSwitchMapping$0[var4.ordinal()];
                  if (var2 == 1 || var2 == 2 || var2 == 3) {
                     mCanInf.btPhoneHangUpInfoChange(phoneNumber, isMicMute, isAudioTransferTowardsAG);
                  }
               }
            } else {
               mCanInf.btPhoneTalkingWithTimeInfoChange(phoneNumber, isMicMute, isAudioTransferTowardsAG, callTime);
            }
         } else {
            mCanInf.btPhoneIncomingInfoChange(phoneNumber, isMicMute, isAudioTransferTowardsAG);
         }
      } else {
         mCanInf.btPhoneOutGoingInfoChange(phoneNumber, isMicMute, isAudioTransferTowardsAG);
      }

      curCallState = var1;
      MediaShareData.Bluetooth var5 = mCanInf;
      var5.btPhoneStatusInfoChange(callStatus, phoneNumber, isHfpConnected, isCallingFlag, isMicMute, isAudioTransferTowardsAG);
      var5.notifyBtStatusChange();
   }

   public final void a2dpNext() {
      btManager.sendAction(BtConstants.BT_ACTION.A2DP_NEXT);
   }

   public final void a2dpPause() {
      btManager.sendAction(BtConstants.BT_ACTION.A2DP_PAUSE);
   }

   public final void a2dpPlay() {
      btManager.sendAction(BtConstants.BT_ACTION.A2DP_PLAY);
   }

   public final void a2dpPrev() {
      btManager.sendAction(BtConstants.BT_ACTION.A2DP_PREV);
   }

   public final void answer() {
      btManager.sendAction(BtConstants.BT_ACTION.ANSWER);
   }

   public final void call(String var1) {
      Intrinsics.checkNotNullParameter(var1, "number");
      btManager.call(var1);
   }

   public final void connectA2dp(String var1, boolean var2) {
      Intrinsics.checkNotNullParameter(var1, "address");
      if (var2) {
         btManager.sendDeviceAction(BtConstants.BT_ACTION.CONN_A2DP, var1);
      } else {
         btManager.sendDeviceAction(BtConstants.BT_ACTION.DISCONN_A2DP, var1);
      }

   }

   public final void connectHfp(String var1, boolean var2) {
      Intrinsics.checkNotNullParameter(var1, "address");
      if (var2) {
         btManager.sendDeviceAction(BtConstants.BT_ACTION.CONN_HFP, var1);
      } else {
         btManager.sendDeviceAction(BtConstants.BT_ACTION.DISCONN_HFP, var1);
      }

   }

   public final BtConstants.CallState getCurCallState() {
      return curCallState;
   }

   public final String getHfpDeviceName() {
      ArrayList var1 = btManager.getConHfp();
      return var1.isEmpty() ? "" : ((Device)var1.get(0)).getName();
   }

   public final MediaShareData.Bluetooth getMCanInf() {
      return mCanInf;
   }

   public final void hangup() {
      btManager.sendAction(BtConstants.BT_ACTION.HANDUP);
   }

   public final void muteMic(boolean var1) {
      if (var1) {
         btManager.sendAction(BtConstants.BT_ACTION.MUTE_MIC);
      } else {
         btManager.sendAction(BtConstants.BT_ACTION.UNMUTE_MIC);
      }

   }

   public final void pair(String var1, boolean var2) {
      Intrinsics.checkNotNullParameter(var1, "address");
      if (var2) {
         btManager.sendDeviceAction(BtConstants.BT_ACTION.PAIR, var1);
      } else {
         btManager.sendDeviceAction(BtConstants.BT_ACTION.UNPAIR, var1);
      }

   }

   public final void registerListener() {
      btManager.setBtCallBack((BtListener)(new BtListener() {
         public void onCloseA2dp() {
         }

         public void onCloseHfp() {
         }

         public void onCurrA2dpAddressChange(String var1) {
            Intrinsics.checkNotNullParameter(var1, "address");
         }

         public void onCurrHfpAddressChange(String var1) {
            Intrinsics.checkNotNullParameter(var1, "address");
         }

         public void onHfpConnChange(ArrayList var1) {
            Intrinsics.checkNotNullParameter(var1, "hfpList");
            BtAdapter var2 = BtAdapter.INSTANCE;
            BtAdapter.isHfpConnected = var1.isEmpty() ^ true;
            BtAdapter.INSTANCE.getMCanInf().btPhoneStatusInfoChange(BtAdapter.callStatus, BtAdapter.phoneNumber, BtAdapter.isHfpConnected, BtAdapter.isCallingFlag, BtAdapter.isMicMute, BtAdapter.isAudioTransferTowardsAG);
            BtAdapter.INSTANCE.getMCanInf().notifyBtStatusChange();
         }

         public void onMicOutChange(boolean var1) {
         }

         public void onPairedChange(ArrayList var1) {
            Intrinsics.checkNotNullParameter(var1, "pairList");
         }

         public void onVisibleDeviceChange(ArrayList var1) {
            Intrinsics.checkNotNullParameter(var1, "deviceList");
         }

         public void updateBtStatus(BtConstants.BT_STATUS var1, BtStatus var2) {
            Intrinsics.checkNotNullParameter(var1, "statusName");
            Intrinsics.checkNotNullParameter(var2, "status");
            if (LogUtil.log5()) {
               LogUtil.d("updateBtStatus: " + var1 + ':' + BtStatus.Companion.toJsonString(var2));
            }

            int var3 = null.WhenMappings.$EnumSwitchMapping$0[var1.ordinal()];
            if (var3 != 1) {
               if (var3 != 2) {
                  if (var3 == 3) {
                     if (var2.isOutGoing()) {
                        BtAdapter.INSTANCE.updateCallState(BtConstants.CallState.OUT_CALLING);
                     } else if (!var2.isInComing() && !var2.isCalling()) {
                        BtAdapter.INSTANCE.updateCallState(BtConstants.CallState.NORMAL);
                     }
                  }
               } else if (var2.isInComing()) {
                  BtAdapter.INSTANCE.updateCallState(BtConstants.CallState.IN_CALLING);
               } else if (!var2.isCalling() && !var2.isOutGoing()) {
                  BtAdapter.INSTANCE.updateCallState(BtConstants.CallState.NORMAL);
               }
            } else if (var2.isCalling()) {
               BtAdapter.INSTANCE.updateCallState(BtConstants.CallState.CALLING);
            } else if (!var2.isInComing() && !var2.isOutGoing()) {
               BtAdapter.INSTANCE.updateCallState(BtConstants.CallState.NORMAL);
            }

         }

         public void updateCall(ArrayList var1, BtCall var2) {
            Intrinsics.checkNotNullParameter(var1, "calls");
            if (!var1.isEmpty()) {
               BtAdapter var4 = BtAdapter.INSTANCE;
               byte[] var5 = ((BtCall)var1.get(0)).getCallNum().getBytes(Charsets.UTF_8);
               Intrinsics.checkNotNullExpressionValue(var5, "this as java.lang.String).getBytes(charset)");
               BtAdapter.phoneNumber = var5;
               BtConstants.CallState var3 = ((BtCall)var1.get(0)).getCallState();
               var4 = BtAdapter.INSTANCE;
               BtAdapter.callTime = (int)((BtCall)var1.get(0)).getCallingTime();
               BtAdapter.INSTANCE.updateCallState(var3);
            }
         }

         public void updateCallDevice(BtConstants.CallDevice var1) {
            Intrinsics.checkNotNullParameter(var1, "callDevice");
            BtAdapter var3 = BtAdapter.INSTANCE;
            boolean var2;
            if (var1 == BtConstants.CallDevice.Phone) {
               var2 = true;
            } else {
               var2 = false;
            }

            BtAdapter.isAudioTransferTowardsAG = var2;
            BtAdapter.INSTANCE.getMCanInf().btPhoneStatusInfoChange(BtAdapter.callStatus, BtAdapter.phoneNumber, BtAdapter.isHfpConnected, BtAdapter.isCallingFlag, BtAdapter.isMicMute, BtAdapter.isAudioTransferTowardsAG);
            BtAdapter.INSTANCE.getMCanInf().notifyBtStatusChange();
         }

         public void updateId3(String var1, String var2, String var3) {
            MediaShareData.Bluetooth var5 = BtAdapter.INSTANCE.getMCanInf();
            String var4 = var1;
            if (var1 == null) {
               var4 = "";
            }

            var1 = var2;
            if (var2 == null) {
               var1 = "";
            }

            var2 = var3;
            if (var3 == null) {
               var2 = "";
            }

            var5.btMusicId3InfoChange(var4, var1, var2);
         }

         public void updateIsAutoAnswer(boolean var1) {
         }

         public void updateIsAutoConn(boolean var1) {
         }

         public void updateMicMute(boolean var1) {
            BtAdapter var2 = BtAdapter.INSTANCE;
            BtAdapter.isMicMute = var1;
            BtAdapter.INSTANCE.getMCanInf().btPhoneStatusInfoChange(BtAdapter.callStatus, BtAdapter.phoneNumber, BtAdapter.isHfpConnected, BtAdapter.isCallingFlag, BtAdapter.isMicMute, BtAdapter.isAudioTransferTowardsAG);
            BtAdapter.INSTANCE.getMCanInf().notifyBtStatusChange();
         }

         public void updateName(String var1) {
            Intrinsics.checkNotNullParameter(var1, "name");
         }

         public void updateWechatCall(BtCall var1) {
         }

         @Metadata(
            k = 3,
            mv = {1, 7, 1},
            xi = 48
         )
         public final class WhenMappings {
            public static final int[] $EnumSwitchMapping$0;

            static {
               int[] var0 = new int[BtConstants.BT_STATUS.values().length];
               var0[BtConstants.BT_STATUS.CALLING.ordinal()] = 1;
               var0[BtConstants.BT_STATUS.INCOMING.ordinal()] = 2;
               var0[BtConstants.BT_STATUS.OUTGOING.ordinal()] = 3;
               $EnumSwitchMapping$0 = var0;
            }
         }
      }));
   }

   public final void scan(boolean var1) {
      if (var1) {
         btManager.sendAction(BtConstants.BT_ACTION.SCAN);
      } else {
         btManager.sendAction(BtConstants.BT_ACTION.STOP_SCAN);
      }

   }

   public final void sendKey(String var1) {
      Intrinsics.checkNotNullParameter(var1, "key");
      btManager.sendKey(var1);
   }

   public final void setAutoConn(boolean var1) {
      if (var1) {
         btManager.sendAction(BtConstants.BT_ACTION.AUTOCONN);
      } else {
         btManager.sendAction(BtConstants.BT_ACTION.NOT_AUTOCONN);
      }

   }

   public final void setBtName(String var1) {
      Intrinsics.checkNotNullParameter(var1, "name");
      btManager.setName(var1);
   }

   public final void setCurCallState(BtConstants.CallState var1) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      curCallState = var1;
   }

   public final void setMicOut(boolean var1) {
      if (var1) {
         btManager.sendAction(BtConstants.BT_ACTION.MIC_OUT);
      } else {
         btManager.sendAction(BtConstants.BT_ACTION.MIC_IN);
      }

   }

   public final void syncPhoneBook() {
      btManager.sendAction(BtConstants.BT_ACTION.SYNC_PHONE_BOOK);
   }

   @Metadata(
      k = 3,
      mv = {1, 7, 1},
      xi = 48
   )
   public final class WhenMappings {
      public static final int[] $EnumSwitchMapping$0;

      static {
         int[] var0 = new int[BtConstants.CallState.values().length];
         var0[BtConstants.CallState.OUT_CALLING.ordinal()] = 1;
         var0[BtConstants.CallState.IN_CALLING.ordinal()] = 2;
         var0[BtConstants.CallState.CALLING.ordinal()] = 3;
         var0[BtConstants.CallState.NORMAL.ordinal()] = 4;
         var0[BtConstants.CallState.END_CALLING.ordinal()] = 5;
         $EnumSwitchMapping$0 = var0;
      }
   }
}
