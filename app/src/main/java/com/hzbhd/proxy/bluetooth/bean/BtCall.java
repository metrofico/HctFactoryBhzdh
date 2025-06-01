package com.hzbhd.proxy.bluetooth.bean;

import android.text.TextUtils;
import android.util.Log;
import com.hzbhd.constant.bluetooth.BtConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

@Metadata(
   d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\n\u0018\u0000 (2\u00020\u0001:\u0002'(B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010&\u001a\u00020\u0004H\u0016R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\bR\u001a\u0010\f\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u0006\"\u0004\b\u000e\u0010\bR\u001a\u0010\u000f\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0006\"\u0004\b\u0011\u0010\bR\u001a\u0010\u0012\u001a\u00020\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001a\u0010\u0018\u001a\u00020\u0019X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u001a\u0010\u001e\u001a\u00020\u001fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010 \"\u0004\b!\u0010\"R\u001a\u0010#\u001a\u00020\u0019X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010\u001b\"\u0004\b%\u0010\u001d¨\u0006)"},
   d2 = {"Lcom/hzbhd/proxy/bluetooth/bean/BtCall;", "", "()V", "callDeviceAddress", "", "getCallDeviceAddress", "()Ljava/lang/String;", "setCallDeviceAddress", "(Ljava/lang/String;)V", "callDeviceName", "getCallDeviceName", "setCallDeviceName", "callName", "getCallName", "setCallName", "callNum", "getCallNum", "setCallNum", "callState", "Lcom/hzbhd/constant/bluetooth/BtConstants$CallState;", "getCallState", "()Lcom/hzbhd/constant/bluetooth/BtConstants$CallState;", "setCallState", "(Lcom/hzbhd/constant/bluetooth/BtConstants$CallState;)V", "callingTime", "", "getCallingTime", "()J", "setCallingTime", "(J)V", "isCurrCallDevice", "", "()Z", "setCurrCallDevice", "(Z)V", "ringTime", "getRingTime", "setRingTime", "toString", "CALL_NAME", "Companion", "bt-proxy_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class BtCall {
   public static final Companion Companion = new Companion((DefaultConstructorMarker)null);
   private String callDeviceAddress;
   private String callDeviceName;
   private String callName;
   private String callNum;
   private BtConstants.CallState callState;
   private long callingTime;
   private boolean isCurrCallDevice;
   private long ringTime;

   public BtCall() {
      this.callState = BtConstants.CallState.NORMAL;
      this.callNum = "";
      this.callName = "";
      this.callDeviceAddress = "";
      this.callDeviceName = "";
   }

   public final String getCallDeviceAddress() {
      return this.callDeviceAddress;
   }

   public final String getCallDeviceName() {
      return this.callDeviceName;
   }

   public final String getCallName() {
      return this.callName;
   }

   public final String getCallNum() {
      return this.callNum;
   }

   public final BtConstants.CallState getCallState() {
      return this.callState;
   }

   public final long getCallingTime() {
      return this.callingTime;
   }

   public final long getRingTime() {
      return this.ringTime;
   }

   public final boolean isCurrCallDevice() {
      return this.isCurrCallDevice;
   }

   public final void setCallDeviceAddress(String var1) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.callDeviceAddress = var1;
   }

   public final void setCallDeviceName(String var1) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.callDeviceName = var1;
   }

   public final void setCallName(String var1) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.callName = var1;
   }

   public final void setCallNum(String var1) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.callNum = var1;
   }

   public final void setCallState(BtConstants.CallState var1) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.callState = var1;
   }

   public final void setCallingTime(long var1) {
      this.callingTime = var1;
   }

   public final void setCurrCallDevice(boolean var1) {
      this.isCurrCallDevice = var1;
   }

   public final void setRingTime(long var1) {
      this.ringTime = var1;
   }

   public String toString() {
      return "BtCall{mCallState=" + this.callState + ", callNum='" + this.callNum + "', callName='" + this.callName + "', ringTime=" + this.ringTime + ", callingTime=" + this.callingTime + ", callDeviceAddress='" + this.callDeviceAddress + "', callDeviceName='" + this.callDeviceName + "', isCurrCallDevice=" + this.isCurrCallDevice + '}';
   }

   @Metadata(
      d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\n\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\n¨\u0006\u000b"},
      d2 = {"Lcom/hzbhd/proxy/bluetooth/bean/BtCall$CALL_NAME;", "", "(Ljava/lang/String;I)V", "CALL_STATE", "CALL_NUM", "CALL_NAME", "RING_TIME", "CALLING_TIME", "CALL_DEVICE_ADDRRESS", "CALL_DEVICE_NAME", "IS_CURR_CALL_DEVICE", "bt-proxy_release"},
      k = 1,
      mv = {1, 7, 1},
      xi = 48
   )
   public static enum CALL_NAME {
      private static final CALL_NAME[] $VALUES = $values();
      CALLING_TIME,
      CALL_DEVICE_ADDRRESS,
      CALL_DEVICE_NAME,
      CALL_NAME,
      CALL_NUM,
      CALL_STATE,
      IS_CURR_CALL_DEVICE,
      RING_TIME;

      // $FF: synthetic method
      private static final CALL_NAME[] $values() {
         return new CALL_NAME[]{CALL_STATE, CALL_NUM, CALL_NAME, RING_TIME, CALLING_TIME, CALL_DEVICE_ADDRRESS, CALL_DEVICE_NAME, IS_CURR_CALL_DEVICE};
      }
   }

   @Metadata(
      d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u0004\u0018\u00010\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006J\u000e\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0004¨\u0006\t"},
      d2 = {"Lcom/hzbhd/proxy/bluetooth/bean/BtCall$Companion;", "", "()V", "fromJson", "Lcom/hzbhd/proxy/bluetooth/bean/BtCall;", "callString", "", "toJsonString", "call", "bt-proxy_release"},
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

      public final BtCall fromJson(String var1) {
         try {
            if (!TextUtils.isEmpty((CharSequence)var1)) {
               JSONObject var2 = new JSONObject(var1);
               BtCall var3 = new BtCall();
               String var4 = var2.optString("CALL_STATE");
               Intrinsics.checkNotNullExpressionValue(var4, "jsonObject.optString(CALL_NAME.CALL_STATE.name)");
               var3.setCallState(BtConstants.CallState.valueOf(var4));
               var4 = var2.optString("CALL_NUM");
               Intrinsics.checkNotNullExpressionValue(var4, "jsonObject.optString(CALL_NAME.CALL_NUM.name)");
               var3.setCallNum(var4);
               var4 = var2.optString("CALL_NAME");
               Intrinsics.checkNotNullExpressionValue(var4, "jsonObject.optString(CALL_NAME.CALL_NAME.name)");
               var3.setCallName(var4);
               var3.setRingTime(var2.optLong("RING_TIME"));
               var3.setCallingTime(var2.optLong("CALLING_TIME"));
               var4 = var2.optString("CALL_DEVICE_ADDRRESS");
               Intrinsics.checkNotNullExpressionValue(var4, "jsonObject.optString(CAL…ALL_DEVICE_ADDRRESS.name)");
               var3.setCallDeviceAddress(var4);
               var4 = var2.optString("CALL_DEVICE_NAME");
               Intrinsics.checkNotNullExpressionValue(var4, "jsonObject.optString(CAL…ME.CALL_DEVICE_NAME.name)");
               var3.setCallDeviceName(var4);
               var3.setCurrCallDevice(var2.optBoolean("IS_CURR_CALL_DEVICE"));
               return var3;
            }
         } catch (Exception var5) {
            Log.d("Call", "fromJson: error:" + var1);
         }

         return null;
      }

      public final String toJsonString(BtCall var1) {
         Intrinsics.checkNotNullParameter(var1, "call");

         try {
            JSONObject var2 = new JSONObject();
            BtConstants.CallState var3 = var1.getCallState();
            Intrinsics.checkNotNull(var3);
            var2.put("CALL_STATE", var3.name());
            var2.put("CALL_NUM", var1.getCallNum());
            var2.put("CALL_NAME", var1.getCallName());
            var2.put("RING_TIME", var1.getRingTime());
            var2.put("CALLING_TIME", var1.getCallingTime());
            var2.put("CALL_DEVICE_ADDRRESS", var1.getCallDeviceAddress());
            var2.put("CALL_DEVICE_NAME", var1.getCallDeviceName());
            var2.put("IS_CURR_CALL_DEVICE", var1.isCurrCallDevice());
            String var5 = var2.toString();
            Intrinsics.checkNotNullExpressionValue(var5, "jsonObject.toString()");
            return var5;
         } catch (Exception var4) {
            Log.d("Call", "toJsonString: error");
            return "";
         }
      }
   }
}
