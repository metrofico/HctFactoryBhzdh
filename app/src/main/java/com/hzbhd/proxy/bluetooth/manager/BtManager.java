package com.hzbhd.proxy.bluetooth.manager;

import com.hzbhd.commontools.SourceConstantsDef;
import com.hzbhd.constant.bluetooth.BtConstants;
import com.hzbhd.proxy.bluetooth.BtUtil;
import com.hzbhd.proxy.bluetooth.aidl.IBtCallback;
import com.hzbhd.proxy.bluetooth.aidl.IBtManager;
import com.hzbhd.proxy.bluetooth.bean.BtCall;
import com.hzbhd.proxy.bluetooth.bean.BtStatus;
import com.hzbhd.proxy.bluetooth.listener.BtListener;
import com.hzbhd.systemstatus.proxy.ServiceStateManager;
import com.hzbhd.util.LogUtil;
import java.util.ArrayList;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u0000 W2\u00020\u0001:\u0001WB\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010E\u001a\u00020F2\u0006\u0010G\u001a\u00020\tJ\u000e\u0010G\u001a\u00020F2\u0006\u0010H\u001a\u00020\u0011J\u0006\u0010I\u001a\u00020%J\b\u0010J\u001a\u0004\u0018\u00010\tJ\u000e\u0010K\u001a\u00020F2\u0006\u0010G\u001a\u00020\tJ\b\u0010L\u001a\u00020FH\u0002J\u000e\u0010M\u001a\u00020F2\u0006\u0010N\u001a\u00020OJ\u0018\u0010P\u001a\u00020F2\u0006\u0010N\u001a\u00020O2\b\u0010Q\u001a\u0004\u0018\u00010\u0011J\u0016\u0010R\u001a\u00020F2\u0006\u0010S\u001a\u00020\t2\u0006\u0010T\u001a\u00020\u0011J\u000e\u0010R\u001a\u00020F2\u0006\u0010T\u001a\u00020\u0011J\u0010\u0010U\u001a\u00020F2\b\u0010V\u001a\u0004\u0018\u000102R\u0011\u0010\u0003\u001a\u00020\u00048F¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R!\u0010\u0007\u001a\u0012\u0012\u0004\u0012\u00020\t0\bj\b\u0012\u0004\u0012\u00020\t`\n8F¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR!\u0010\r\u001a\u0012\u0012\u0004\u0012\u00020\u000e0\bj\b\u0012\u0004\u0012\u00020\u000e`\n8F¢\u0006\u0006\u001a\u0004\b\u000f\u0010\fR\u0011\u0010\u0010\u001a\u00020\u00118F¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0014\u001a\u00020\u00118F¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0013R\u0011\u0010\u0016\u001a\u00020\u00178F¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019R\u001a\u0010\u001a\u001a\u00020\u001bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u0016\u0010 \u001a\u0004\u0018\u00010!8DX\u0084\u0004¢\u0006\u0006\u001a\u0004\b\"\u0010#R\u0011\u0010$\u001a\u00020%8F¢\u0006\u0006\u001a\u0004\b$\u0010&R\u0011\u0010'\u001a\u00020%8F¢\u0006\u0006\u001a\u0004\b'\u0010&R\u0011\u0010(\u001a\u00020%8F¢\u0006\u0006\u001a\u0004\b(\u0010&R\u0011\u0010)\u001a\u00020%8F¢\u0006\u0006\u001a\u0004\b)\u0010&R\u0011\u0010*\u001a\u00020%8F¢\u0006\u0006\u001a\u0004\b*\u0010&R$\u0010,\u001a\u00020%2\u0006\u0010+\u001a\u00020%8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b,\u0010&\"\u0004\b-\u0010.R\u0011\u0010/\u001a\u00020\u00118F¢\u0006\u0006\u001a\u0004\b0\u0010\u0013R\u0010\u00101\u001a\u0004\u0018\u000102X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u00103\u001a\u0004\u0018\u00010!X\u0082\u000e¢\u0006\u0002\n\u0000R\u0013\u00104\u001a\u0004\u0018\u00010\u00118F¢\u0006\u0006\u001a\u0004\b5\u0010\u0013R\u0013\u00106\u001a\u0004\u0018\u00010\u00118F¢\u0006\u0006\u001a\u0004\b7\u0010\u0013R\u0013\u00108\u001a\u0004\u0018\u00010\u00118F¢\u0006\u0006\u001a\u0004\b9\u0010\u0013R$\u0010:\u001a\u00020\u00112\u0006\u0010:\u001a\u00020\u00118F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b;\u0010\u0013\"\u0004\b<\u0010=R\u0011\u0010>\u001a\u00020%8F¢\u0006\u0006\u001a\u0004\b?\u0010&R!\u0010@\u001a\u0012\u0012\u0004\u0012\u00020\u000e0\bj\b\u0012\u0004\u0012\u00020\u000e`\n8F¢\u0006\u0006\u001a\u0004\bA\u0010\fR$\u0010B\u001a\u00020\u00112\u0006\u0010B\u001a\u00020\u00118F@FX\u0086\u000e¢\u0006\f\u001a\u0004\bC\u0010\u0013\"\u0004\bD\u0010=¨\u0006X"},
   d2 = {"Lcom/hzbhd/proxy/bluetooth/manager/BtManager;", "", "()V", "callDevice", "Lcom/hzbhd/constant/bluetooth/BtConstants$CallDevice;", "getCallDevice", "()Lcom/hzbhd/constant/bluetooth/BtConstants$CallDevice;", "callList", "Ljava/util/ArrayList;", "Lcom/hzbhd/proxy/bluetooth/bean/BtCall;", "Lkotlin/collections/ArrayList;", "getCallList", "()Ljava/util/ArrayList;", "conHfp", "Lcom/hzbhd/proxy/bluetooth/bean/Device;", "getConHfp", "currA2dpAddress", "", "getCurrA2dpAddress", "()Ljava/lang/String;", "currHfpAddress", "getCurrHfpAddress", "currStatus", "Lcom/hzbhd/proxy/bluetooth/bean/BtStatus;", "getCurrStatus", "()Lcom/hzbhd/proxy/bluetooth/bean/BtStatus;", "iBtCallback", "Lcom/hzbhd/proxy/bluetooth/aidl/IBtCallback;", "getIBtCallback", "()Lcom/hzbhd/proxy/bluetooth/aidl/IBtCallback;", "setIBtCallback", "(Lcom/hzbhd/proxy/bluetooth/aidl/IBtCallback;)V", "iBtManager", "Lcom/hzbhd/proxy/bluetooth/aidl/IBtManager;", "getIBtManager", "()Lcom/hzbhd/proxy/bluetooth/aidl/IBtManager;", "isAutoAnswer", "", "()Z", "isAutoConn", "isConn", "isMicMute", "isMicOut", "filter", "isWechatFilter", "setWechatFilter", "(Z)V", "localAddress", "getLocalAddress", "mBtListener", "Lcom/hzbhd/proxy/bluetooth/listener/BtListener;", "mIBtManager", "musicAlbum", "getMusicAlbum", "musicArtist", "getMusicArtist", "musicTitle", "getMusicTitle", "name", "getName", "setName", "(Ljava/lang/String;)V", "pairMode", "getPairMode", "pairedHfp", "getPairedHfp", "pinCode", "getPinCode", "setPinCode", "answer", "", "call", "num", "enableWechatFilter", "getWechatCall", "handup", "onServiceConnected", "sendAction", "action", "Lcom/hzbhd/constant/bluetooth/BtConstants$BT_ACTION;", "sendDeviceAction", "address", "sendKey", "btCall", "key", "setBtCallBack", "callback", "Companion", "bt-proxy_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class BtManager {
   public static final Companion Companion = new Companion((DefaultConstructorMarker)null);
   public static final String SERVICE_NAME_BT = "hzbhd_bluetooth";
   private static final Lazy instance$delegate;
   private IBtCallback iBtCallback = (IBtCallback)(new IBtCallback.Stub(this) {
      final BtManager this$0;

      {
         this.this$0 = var1;
      }

      public void callingMessage(String var1, String var2) {
      }

      public void onCallNumChange(String var1) {
      }

      public void onCloseA2dp() {
         if (this.this$0.mBtListener == null) {
            if (LogUtil.log3()) {
               LogUtil.d("iBtCallback: listener is null");
            }

         } else {
            BtListener var1 = this.this$0.mBtListener;
            Intrinsics.checkNotNull(var1);
            var1.onCloseA2dp();
         }
      }

      public void onCloseHfp() {
         if (this.this$0.mBtListener == null) {
            if (LogUtil.log3()) {
               LogUtil.d("iBtCallback: listener is null");
            }

         } else {
            BtListener var1 = this.this$0.mBtListener;
            Intrinsics.checkNotNull(var1);
            var1.onCloseHfp();
         }
      }

      public void onCurrA2dpAddressChange(String var1) {
         Intrinsics.checkNotNullParameter(var1, "address");
         if (this.this$0.mBtListener == null) {
            if (LogUtil.log3()) {
               LogUtil.d("iBtCallback: listener is null");
            }

         } else {
            BtListener var2 = this.this$0.mBtListener;
            Intrinsics.checkNotNull(var2);
            var2.onCurrA2dpAddressChange(var1);
         }
      }

      public void onCurrHfpAddressChange(String var1) {
         Intrinsics.checkNotNullParameter(var1, "address");
         if (this.this$0.mBtListener == null) {
            if (LogUtil.log3()) {
               LogUtil.d("iBtCallback: listener is null");
            }

         } else {
            BtListener var2 = this.this$0.mBtListener;
            Intrinsics.checkNotNull(var2);
            var2.onCurrHfpAddressChange(var1);
         }
      }

      public void onFoundDeviceChange(List var1) {
         Intrinsics.checkNotNullParameter(var1, "stringList");
         if (this.this$0.mBtListener == null) {
            if (LogUtil.log3()) {
               LogUtil.d("iBtCallback: listener is null");
            }

         } else {
            BtListener var2 = this.this$0.mBtListener;
            Intrinsics.checkNotNull(var2);
            var2.onVisibleDeviceChange(BtUtil.INSTANCE.stringToDevices(var1));
         }
      }

      public void onHfpConnChange(List var1) {
         Intrinsics.checkNotNullParameter(var1, "hfpList");
         if (this.this$0.mBtListener == null) {
            if (LogUtil.log3()) {
               LogUtil.d("iBtCallback: listener is null");
            }

         } else {
            BtListener var2 = this.this$0.mBtListener;
            Intrinsics.checkNotNull(var2);
            var2.onHfpConnChange(BtUtil.INSTANCE.stringToDevices(var1));
         }
      }

      public void onMicOutChange(boolean var1) {
         if (this.this$0.mBtListener == null) {
            if (LogUtil.log3()) {
               LogUtil.d("iBtCallback: listener is null");
            }

         } else {
            BtListener var2 = this.this$0.mBtListener;
            Intrinsics.checkNotNull(var2);
            var2.onMicOutChange(var1);
         }
      }

      public void onPairedChange(List var1) {
         Intrinsics.checkNotNullParameter(var1, "pairList");
         if (this.this$0.mBtListener == null) {
            if (LogUtil.log3()) {
               LogUtil.d("iBtCallback: listener is null");
            }

         } else {
            BtListener var2 = this.this$0.mBtListener;
            Intrinsics.checkNotNull(var2);
            var2.onPairedChange(BtUtil.INSTANCE.stringToDevices(var1));
         }
      }

      public void updateBtStatus(int var1, String var2) {
         Intrinsics.checkNotNullParameter(var2, "status");
         if (this.this$0.mBtListener == null) {
            if (LogUtil.log3()) {
               LogUtil.d("iBtCallback: listener is null");
            }

         } else {
            BtListener var4 = this.this$0.mBtListener;
            Intrinsics.checkNotNull(var4);
            BtConstants.BT_STATUS var3 = BtConstants.BT_STATUS.values()[var1];
            BtStatus var5 = BtStatus.Companion.fromJson(var2);
            Intrinsics.checkNotNull(var5);
            var4.updateBtStatus(var3, var5);
         }
      }

      public void updateCall(List var1, String var2) {
         Intrinsics.checkNotNullParameter(var1, "call");
         if (this.this$0.mBtListener == null) {
            if (LogUtil.log3()) {
               LogUtil.d("iBtCallback: listener is null");
            }

         } else {
            BtListener var3 = this.this$0.mBtListener;
            Intrinsics.checkNotNull(var3);
            ArrayList var4 = BtUtil.INSTANCE.stringToCall(var1);
            BtCall var5;
            if (var2 != null) {
               var5 = BtCall.Companion.fromJson(var2);
            } else {
               var5 = null;
            }

            var3.updateCall(var4, var5);
         }
      }

      public void updateCallDevice(int var1) {
         if (this.this$0.mBtListener == null) {
            if (LogUtil.log3()) {
               LogUtil.d("iBtCallback: listener is null");
            }

         } else {
            BtListener var2 = this.this$0.mBtListener;
            Intrinsics.checkNotNull(var2);
            var2.updateCallDevice(BtConstants.CallDevice.values()[var1]);
         }
      }

      public void updateCallName(String var1) {
      }

      public void updateCallTime(long var1) {
      }

      public void updateId3(String var1, String var2, String var3) {
         if (this.this$0.mBtListener == null) {
            if (LogUtil.log3()) {
               LogUtil.d("iBtCallback: listener is null");
            }

         } else {
            BtListener var4 = this.this$0.mBtListener;
            Intrinsics.checkNotNull(var4);
            var4.updateId3(var1, var2, var3);
         }
      }

      public void updateIsAutoAnswer(boolean var1) {
         if (this.this$0.mBtListener == null) {
            if (LogUtil.log3()) {
               LogUtil.d("iBtCallback: listener is null");
            }

         } else {
            BtListener var2 = this.this$0.mBtListener;
            Intrinsics.checkNotNull(var2);
            var2.updateIsAutoAnswer(var1);
         }
      }

      public void updateIsAutoConn(boolean var1) {
         if (this.this$0.mBtListener == null) {
            if (LogUtil.log3()) {
               LogUtil.d("iBtCallback: listener is null");
            }

         } else {
            BtListener var2 = this.this$0.mBtListener;
            Intrinsics.checkNotNull(var2);
            var2.updateIsAutoConn(var1);
         }
      }

      public void updateMicMute(boolean var1) {
         if (this.this$0.mBtListener == null) {
            if (LogUtil.log3()) {
               LogUtil.d("iBtCallback: listener is null");
            }

         } else {
            BtListener var2 = this.this$0.mBtListener;
            Intrinsics.checkNotNull(var2);
            var2.updateMicMute(var1);
         }
      }

      public void updateName(String var1) {
         Intrinsics.checkNotNullParameter(var1, "name");
         if (this.this$0.mBtListener == null) {
            if (LogUtil.log3()) {
               LogUtil.d("iBtCallback: listener is null");
            }

         } else {
            BtListener var2 = this.this$0.mBtListener;
            Intrinsics.checkNotNull(var2);
            var2.updateName(var1);
         }
      }

      public void updateWechatCall(String var1) {
         if (this.this$0.mBtListener == null) {
            if (LogUtil.log3()) {
               LogUtil.d("iBtCallback: listener is null");
            }

         } else {
            BtListener var2 = this.this$0.mBtListener;
            Intrinsics.checkNotNull(var2);
            BtCall var3;
            if (var1 != null) {
               var3 = BtCall.Companion.fromJson(var1);
            } else {
               var3 = null;
            }

            var2.updateWechatCall(var3);
         }
      }
   });
   private BtListener mBtListener;
   private IBtManager mIBtManager;

   // $FF: synthetic method
   public static void $r8$lambda$IYs78szneaBBaOiu19qw6_mmpQI(BtManager var0) {
      onServiceConnected$lambda$2(var0);
   }

   // $FF: synthetic method
   public static void $r8$lambda$IbhU_nyO1y_6jakeAHgYnwpKEBw(BtManager var0) {
      _get_iBtManager_$lambda$1$lambda$0(var0);
   }

   static {
      instance$delegate = LazyKt.lazy((Function0)null.INSTANCE);
   }

   private static final void _get_iBtManager_$lambda$1$lambda$0(BtManager var0) {
      Intrinsics.checkNotNullParameter(var0, "this$0");
      var0.mIBtManager = null;
   }

   private final void onServiceConnected() {
      ServiceStateManager.getInstance().registerConnectListener(SourceConstantsDef.MODULE_ID.BLUETOOTH.ordinal(), new BtManager$$ExternalSyntheticLambda1(this));
   }

   private static final void onServiceConnected$lambda$2(BtManager var0) {
      Intrinsics.checkNotNullParameter(var0, "this$0");
      if (var0.mBtListener != null) {
         Exception var10000;
         label37: {
            boolean var10001;
            try {
               if (LogUtil.log5()) {
                  LogUtil.d("onServiceConnected: bt");
               }
            } catch (Exception var4) {
               var10000 = var4;
               var10001 = false;
               break label37;
            }

            IBtManager var1;
            try {
               var1 = var0.getIBtManager();
            } catch (Exception var3) {
               var10000 = var3;
               var10001 = false;
               break label37;
            }

            if (var1 == null) {
               return;
            }

            try {
               var1.addBtCallBack(var0.iBtCallback);
               return;
            } catch (Exception var2) {
               var10000 = var2;
               var10001 = false;
            }
         }

         Exception var5 = var10000;
         var5.printStackTrace();
      }

   }

   public final void answer(BtCall var1) {
      Intrinsics.checkNotNullParameter(var1, "call");

      Exception var10000;
      label28: {
         boolean var10001;
         IBtManager var2;
         try {
            var2 = this.getIBtManager();
         } catch (Exception var4) {
            var10000 = var4;
            var10001 = false;
            break label28;
         }

         if (var2 == null) {
            return;
         }

         try {
            var2.answer(BtCall.Companion.toJsonString(var1));
            return;
         } catch (Exception var3) {
            var10000 = var3;
            var10001 = false;
         }
      }

      Exception var5 = var10000;
      var5.printStackTrace();
   }

   public final void call(String var1) {
      Intrinsics.checkNotNullParameter(var1, "num");

      Exception var10000;
      label28: {
         boolean var10001;
         IBtManager var2;
         try {
            var2 = this.getIBtManager();
         } catch (Exception var4) {
            var10000 = var4;
            var10001 = false;
            break label28;
         }

         if (var2 == null) {
            return;
         }

         try {
            var2.call(var1);
            return;
         } catch (Exception var3) {
            var10000 = var3;
            var10001 = false;
         }
      }

      Exception var5 = var10000;
      var5.printStackTrace();
   }

   public final boolean enableWechatFilter() {
      try {
         IBtManager var2 = this.getIBtManager();
         Intrinsics.checkNotNull(var2);
         boolean var1 = var2.enableWechatFilter();
         return var1;
      } catch (Exception var3) {
         var3.printStackTrace();
         return false;
      }
   }

   public final BtConstants.CallDevice getCallDevice() {
      try {
         BtConstants.CallDevice[] var2 = BtConstants.CallDevice.values();
         IBtManager var1 = this.getIBtManager();
         Intrinsics.checkNotNull(var1);
         BtConstants.CallDevice var4 = var2[var1.getCallDevice()];
         return var4;
      } catch (Exception var3) {
         var3.printStackTrace();
         return BtConstants.CallDevice.Car;
      }
   }

   public final ArrayList getCallList() {
      try {
         BtUtil var1 = BtUtil.INSTANCE;
         IBtManager var2 = this.getIBtManager();
         Intrinsics.checkNotNull(var2);
         List var5 = var2.getCall();
         Intrinsics.checkNotNullExpressionValue(var5, "iBtManager!!.call");
         ArrayList var4 = var1.stringToCall(var5);
         return var4;
      } catch (Exception var3) {
         var3.printStackTrace();
         return new ArrayList();
      }
   }

   public final ArrayList getConHfp() {
      try {
         BtUtil var1 = BtUtil.INSTANCE;
         IBtManager var2 = this.getIBtManager();
         Intrinsics.checkNotNull(var2);
         List var5 = var2.getConHfp();
         Intrinsics.checkNotNullExpressionValue(var5, "iBtManager!!.conHfp");
         ArrayList var4 = var1.stringToDevices(var5);
         return var4;
      } catch (Exception var3) {
         var3.printStackTrace();
         return new ArrayList();
      }
   }

   public final String getCurrA2dpAddress() {
      try {
         IBtManager var1 = this.getIBtManager();
         Intrinsics.checkNotNull(var1);
         String var3 = var1.getCurrA2dpAddress();
         Intrinsics.checkNotNullExpressionValue(var3, "iBtManager!!.currA2dpAddress");
         return var3;
      } catch (Exception var2) {
         var2.printStackTrace();
         return "";
      }
   }

   public final String getCurrHfpAddress() {
      try {
         IBtManager var1 = this.getIBtManager();
         Intrinsics.checkNotNull(var1);
         String var3 = var1.getCurrHfpAddress();
         Intrinsics.checkNotNullExpressionValue(var3, "iBtManager!!.currHfpAddress");
         return var3;
      } catch (Exception var2) {
         var2.printStackTrace();
         return "";
      }
   }

   public final BtStatus getCurrStatus() {
      try {
         BtStatus.Companion var1 = BtStatus.Companion;
         IBtManager var2 = this.getIBtManager();
         Intrinsics.checkNotNull(var2);
         BtStatus var4 = var1.fromJson(var2.getState());
         Intrinsics.checkNotNull(var4);
         return var4;
      } catch (Exception var3) {
         var3.printStackTrace();
         return new BtStatus();
      }
   }

   public final IBtCallback getIBtCallback() {
      return this.iBtCallback;
   }

   protected final IBtManager getIBtManager() {
      // $FF: Couldn't be decompiled
   }

   public final String getLocalAddress() {
      try {
         IBtManager var1 = this.getIBtManager();
         Intrinsics.checkNotNull(var1);
         String var3 = var1.getLocalAddress();
         Intrinsics.checkNotNullExpressionValue(var3, "iBtManager!!.localAddress");
         return var3;
      } catch (Exception var2) {
         var2.printStackTrace();
         return "";
      }
   }

   public final String getMusicAlbum() {
      try {
         IBtManager var1 = this.getIBtManager();
         Intrinsics.checkNotNull(var1);
         String var3 = var1.getMusicAlbum();
         return var3;
      } catch (Exception var2) {
         var2.printStackTrace();
         return "";
      }
   }

   public final String getMusicArtist() {
      try {
         IBtManager var1 = this.getIBtManager();
         Intrinsics.checkNotNull(var1);
         String var3 = var1.getMusicArtist();
         return var3;
      } catch (Exception var2) {
         var2.printStackTrace();
         return "";
      }
   }

   public final String getMusicTitle() {
      try {
         IBtManager var1 = this.getIBtManager();
         Intrinsics.checkNotNull(var1);
         String var3 = var1.getMusicTitle();
         return var3;
      } catch (Exception var2) {
         var2.printStackTrace();
         return "";
      }
   }

   public final String getName() {
      try {
         IBtManager var1 = this.getIBtManager();
         Intrinsics.checkNotNull(var1);
         String var3 = var1.getName();
         Intrinsics.checkNotNullExpressionValue(var3, "iBtManager!!.name");
         return var3;
      } catch (Exception var2) {
         var2.printStackTrace();
         return "AWC001";
      }
   }

   public final boolean getPairMode() {
      try {
         IBtManager var2 = this.getIBtManager();
         Intrinsics.checkNotNull(var2);
         boolean var1 = var2.getPairMode();
         return var1;
      } catch (Exception var3) {
         var3.printStackTrace();
         return false;
      }
   }

   public final ArrayList getPairedHfp() {
      try {
         BtUtil var1 = BtUtil.INSTANCE;
         IBtManager var2 = this.getIBtManager();
         Intrinsics.checkNotNull(var2);
         List var5 = var2.getPairedHfp();
         Intrinsics.checkNotNullExpressionValue(var5, "iBtManager!!.pairedHfp");
         ArrayList var4 = var1.stringToDevices(var5);
         return var4;
      } catch (Exception var3) {
         var3.printStackTrace();
         return new ArrayList();
      }
   }

   public final String getPinCode() {
      try {
         IBtManager var1 = this.getIBtManager();
         Intrinsics.checkNotNull(var1);
         String var3 = var1.getPinCode();
         Intrinsics.checkNotNullExpressionValue(var3, "iBtManager!!.pinCode");
         return var3;
      } catch (Exception var2) {
         var2.printStackTrace();
         return "";
      }
   }

   public final BtCall getWechatCall() {
      Exception var10000;
      label30: {
         IBtManager var1;
         boolean var10001;
         BtCall.Companion var2;
         try {
            var2 = BtCall.Companion;
            var1 = this.getIBtManager();
         } catch (Exception var5) {
            var10000 = var5;
            var10001 = false;
            break label30;
         }

         String var6;
         if (var1 != null) {
            try {
               var6 = var1.getWechatCall();
            } catch (Exception var4) {
               var10000 = var4;
               var10001 = false;
               break label30;
            }
         } else {
            var6 = null;
         }

         try {
            BtCall var8 = var2.fromJson(var6);
            return var8;
         } catch (Exception var3) {
            var10000 = var3;
            var10001 = false;
         }
      }

      Exception var7 = var10000;
      var7.printStackTrace();
      return null;
   }

   public final void handup(BtCall var1) {
      Intrinsics.checkNotNullParameter(var1, "call");

      Exception var10000;
      label28: {
         boolean var10001;
         IBtManager var2;
         try {
            var2 = this.getIBtManager();
         } catch (Exception var4) {
            var10000 = var4;
            var10001 = false;
            break label28;
         }

         if (var2 == null) {
            return;
         }

         try {
            var2.handup(BtCall.Companion.toJsonString(var1));
            return;
         } catch (Exception var3) {
            var10000 = var3;
            var10001 = false;
         }
      }

      Exception var5 = var10000;
      var5.printStackTrace();
   }

   public final boolean isAutoAnswer() {
      try {
         IBtManager var2 = this.getIBtManager();
         Intrinsics.checkNotNull(var2);
         boolean var1 = var2.isAutoAnswer();
         return var1;
      } catch (Exception var3) {
         var3.printStackTrace();
         return false;
      }
   }

   public final boolean isAutoConn() {
      try {
         IBtManager var2 = this.getIBtManager();
         Intrinsics.checkNotNull(var2);
         boolean var1 = var2.isAutoConn();
         return var1;
      } catch (Exception var3) {
         var3.printStackTrace();
         return false;
      }
   }

   public final boolean isConn() {
      boolean var1;
      if (this.getIBtManager() != null) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public final boolean isMicMute() {
      try {
         IBtManager var2 = this.getIBtManager();
         Intrinsics.checkNotNull(var2);
         boolean var1 = var2.isMicMute();
         return var1;
      } catch (Exception var3) {
         var3.printStackTrace();
         return false;
      }
   }

   public final boolean isMicOut() {
      try {
         IBtManager var2 = this.getIBtManager();
         Intrinsics.checkNotNull(var2);
         boolean var1 = var2.isMicOut();
         return var1;
      } catch (Exception var3) {
         var3.printStackTrace();
         return false;
      }
   }

   public final boolean isWechatFilter() {
      try {
         IBtManager var2 = this.getIBtManager();
         Intrinsics.checkNotNull(var2);
         boolean var1 = var2.isWechatFilter();
         return var1;
      } catch (Exception var3) {
         var3.printStackTrace();
         return false;
      }
   }

   public final void sendAction(BtConstants.BT_ACTION var1) {
      Intrinsics.checkNotNullParameter(var1, "action");

      Exception var10000;
      label28: {
         boolean var10001;
         IBtManager var2;
         try {
            var2 = this.getIBtManager();
         } catch (Exception var4) {
            var10000 = var4;
            var10001 = false;
            break label28;
         }

         if (var2 == null) {
            return;
         }

         try {
            var2.sendAction(var1.name());
            return;
         } catch (Exception var3) {
            var10000 = var3;
            var10001 = false;
         }
      }

      Exception var5 = var10000;
      var5.printStackTrace();
   }

   public final void sendDeviceAction(BtConstants.BT_ACTION var1, String var2) {
      Intrinsics.checkNotNullParameter(var1, "action");

      Exception var10000;
      label28: {
         boolean var10001;
         IBtManager var3;
         try {
            var3 = this.getIBtManager();
         } catch (Exception var5) {
            var10000 = var5;
            var10001 = false;
            break label28;
         }

         if (var3 == null) {
            return;
         }

         try {
            var3.sendDeviceAction(var1.name(), var2);
            return;
         } catch (Exception var4) {
            var10000 = var4;
            var10001 = false;
         }
      }

      Exception var6 = var10000;
      var6.printStackTrace();
   }

   public final void sendKey(BtCall var1, String var2) {
      Intrinsics.checkNotNullParameter(var1, "btCall");
      Intrinsics.checkNotNullParameter(var2, "key");

      Exception var10000;
      label28: {
         boolean var10001;
         IBtManager var3;
         try {
            var3 = this.getIBtManager();
         } catch (Exception var5) {
            var10000 = var5;
            var10001 = false;
            break label28;
         }

         if (var3 == null) {
            return;
         }

         try {
            var3.sendCallKey(BtCall.Companion.toJsonString(var1), var2);
            return;
         } catch (Exception var4) {
            var10000 = var4;
            var10001 = false;
         }
      }

      Exception var6 = var10000;
      var6.printStackTrace();
   }

   public final void sendKey(String var1) {
      Intrinsics.checkNotNullParameter(var1, "key");

      Exception var10000;
      label28: {
         boolean var10001;
         IBtManager var2;
         try {
            var2 = this.getIBtManager();
         } catch (Exception var4) {
            var10000 = var4;
            var10001 = false;
            break label28;
         }

         if (var2 == null) {
            return;
         }

         try {
            var2.sendKey(var1);
            return;
         } catch (Exception var3) {
            var10000 = var3;
            var10001 = false;
         }
      }

      Exception var5 = var10000;
      var5.printStackTrace();
   }

   public final void setBtCallBack(BtListener var1) {
      this.mBtListener = var1;
      Exception var10000;
      boolean var10001;
      IBtManager var6;
      Exception var7;
      if (var1 != null) {
         if (this.getIBtManager() != null) {
            label49: {
               try {
                  var6 = this.getIBtManager();
               } catch (Exception var5) {
                  var10000 = var5;
                  var10001 = false;
                  break label49;
               }

               if (var6 == null) {
                  return;
               }

               try {
                  var6.addBtCallBack(this.iBtCallback);
                  return;
               } catch (Exception var4) {
                  var10000 = var4;
                  var10001 = false;
               }
            }

            var7 = var10000;
            var7.printStackTrace();
         }
      } else if (this.getIBtManager() != null) {
         label50: {
            try {
               var6 = this.getIBtManager();
            } catch (Exception var3) {
               var10000 = var3;
               var10001 = false;
               break label50;
            }

            if (var6 == null) {
               return;
            }

            try {
               var6.removeBtCallBack(this.iBtCallback);
               return;
            } catch (Exception var2) {
               var10000 = var2;
               var10001 = false;
            }
         }

         var7 = var10000;
         var7.printStackTrace();
      }

   }

   public final void setIBtCallback(IBtCallback var1) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.iBtCallback = var1;
   }

   public final void setName(String var1) {
      Intrinsics.checkNotNullParameter(var1, "name");

      Exception var10000;
      label29: {
         boolean var10001;
         IBtManager var2;
         try {
            var2 = this.getIBtManager();
         } catch (Exception var4) {
            var10000 = var4;
            var10001 = false;
            break label29;
         }

         if (var2 == null) {
            return;
         }

         try {
            var2.setName(var1);
            return;
         } catch (Exception var3) {
            var10000 = var3;
            var10001 = false;
         }
      }

      Exception var5 = var10000;
      var5.printStackTrace();
   }

   public final void setPinCode(String var1) {
      Intrinsics.checkNotNullParameter(var1, "pinCode");

      Exception var10000;
      label29: {
         boolean var10001;
         IBtManager var2;
         try {
            var2 = this.getIBtManager();
         } catch (Exception var4) {
            var10000 = var4;
            var10001 = false;
            break label29;
         }

         if (var2 == null) {
            return;
         }

         try {
            var2.setPinCode(var1);
            return;
         } catch (Exception var3) {
            var10000 = var3;
            var10001 = false;
         }
      }

      Exception var5 = var10000;
      var5.printStackTrace();
   }

   public final void setWechatFilter(boolean param1) {
      // $FF: Couldn't be decompiled
   }

   @Metadata(
      d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u001b\u0010\u0005\u001a\u00020\u00068FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\b¨\u0006\u000b"},
      d2 = {"Lcom/hzbhd/proxy/bluetooth/manager/BtManager$Companion;", "", "()V", "SERVICE_NAME_BT", "", "instance", "Lcom/hzbhd/proxy/bluetooth/manager/BtManager;", "getInstance", "()Lcom/hzbhd/proxy/bluetooth/manager/BtManager;", "instance$delegate", "Lkotlin/Lazy;", "bt-proxy_release"},
      k = 1,
      mv = {1, 7, 1},
      xi = 48
   )
   public static final class Companion {
      private Companion() {
      }

      // $FF: synthetic method
      public Companion(DefaultConstructorMarker var1) {
         this();
      }

      public final BtManager getInstance() {
         return (BtManager)BtManager.instance$delegate.getValue();
      }
   }
}
