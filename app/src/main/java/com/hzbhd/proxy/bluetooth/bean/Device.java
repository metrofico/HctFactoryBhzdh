package com.hzbhd.proxy.bluetooth.bean;

import android.text.TextUtils;
import android.util.Log;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

@Metadata(
   d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u001b\n\u0002\u0010\b\n\u0002\b\u0004\u0018\u0000 $2\u00020\u0001:\u0002$%BM\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\u0006\u0012\u0006\u0010\n\u001a\u00020\u0006\u0012\u0006\u0010\u000b\u001a\u00020\u0006\u0012\u0006\u0010\f\u001a\u00020\u0006¢\u0006\u0002\u0010\rJ\u0013\u0010\u001f\u001a\u00020\u00062\b\u0010 \u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010!\u001a\u00020\"H\u0016J\b\u0010#\u001a\u00020\u0003H\u0016R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\f\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0007\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\u0012\"\u0004\b\u0015\u0010\u0014R\u001a\u0010\u000b\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\u0012\"\u0004\b\u0016\u0010\u0014R\u001a\u0010\n\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0012\"\u0004\b\u0017\u0010\u0014R\u001a\u0010\b\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\u0012\"\u0004\b\u0018\u0010\u0014R\u001a\u0010\t\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\u0012\"\u0004\b\u0019\u0010\u0014R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0012\"\u0004\b\u001a\u0010\u0014R\u001a\u0010\u001b\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u0012\"\u0004\b\u001c\u0010\u0014R\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u000f\"\u0004\b\u001e\u0010\u0011¨\u0006&"},
   d2 = {"Lcom/hzbhd/proxy/bluetooth/bean/Device;", "", "address", "", "name", "isPaired", "", "isConn", "isHfpConn", "isPairIng", "isConnHfping", "isConnA2dping", "isA2dpConn", "(Ljava/lang/String;Ljava/lang/String;ZZZZZZZ)V", "getAddress", "()Ljava/lang/String;", "setAddress", "(Ljava/lang/String;)V", "()Z", "setA2dpConn", "(Z)V", "setConn", "setConnA2dping", "setConnHfping", "setHfpConn", "setPairIng", "setPaired", "isVisible", "setVisible", "getName", "setName", "equals", "other", "hashCode", "", "toString", "Companion", "DEVICE_NAME", "bt-proxy_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class Device {
   public static final Companion Companion = new Companion((DefaultConstructorMarker)null);
   private String address;
   private boolean isA2dpConn;
   private boolean isConn;
   private boolean isConnA2dping;
   private boolean isConnHfping;
   private boolean isHfpConn;
   private boolean isPairIng;
   private boolean isPaired;
   private boolean isVisible;
   private String name;

   public Device(String var1, String var2, boolean var3, boolean var4, boolean var5, boolean var6, boolean var7, boolean var8, boolean var9) {
      Intrinsics.checkNotNullParameter(var1, "address");
      Intrinsics.checkNotNullParameter(var2, "name");
      super();
      this.address = var1;
      this.name = var2;
      this.isPaired = var3;
      this.isConn = var4;
      this.isHfpConn = var5;
      this.isPairIng = var6;
      this.isConnHfping = var7;
      this.isConnA2dping = var8;
      this.isA2dpConn = var9;
   }

   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else {
         Class var3 = this.getClass();
         Class var2;
         if (var1 != null) {
            var2 = var1.getClass();
         } else {
            var2 = null;
         }

         if (!Intrinsics.areEqual((Object)var3, (Object)var2)) {
            return false;
         } else {
            Intrinsics.checkNotNull(var1, "null cannot be cast to non-null type com.hzbhd.proxy.bluetooth.bean.Device");
            Device var4 = (Device)var1;
            if (!Intrinsics.areEqual((Object)this.address, (Object)var4.address)) {
               return false;
            } else if (!Intrinsics.areEqual((Object)this.name, (Object)var4.name)) {
               return false;
            } else if (this.isPaired != var4.isPaired) {
               return false;
            } else if (this.isConn != var4.isConn) {
               return false;
            } else if (this.isHfpConn != var4.isHfpConn) {
               return false;
            } else if (this.isPairIng != var4.isPairIng) {
               return false;
            } else if (this.isConnHfping != var4.isConnHfping) {
               return false;
            } else if (this.isConnA2dping != var4.isConnA2dping) {
               return false;
            } else if (this.isA2dpConn != var4.isA2dpConn) {
               return false;
            } else {
               return this.isVisible == var4.isVisible;
            }
         }
      }
   }

   public final String getAddress() {
      return this.address;
   }

   public final String getName() {
      return this.name;
   }

   public int hashCode() {
      return ((((((((this.address.hashCode() * 31 + this.name.hashCode()) * 31 + Boolean.hashCode(this.isPaired)) * 31 + Boolean.hashCode(this.isConn)) * 31 + Boolean.hashCode(this.isHfpConn)) * 31 + Boolean.hashCode(this.isPairIng)) * 31 + Boolean.hashCode(this.isConnHfping)) * 31 + Boolean.hashCode(this.isConnA2dping)) * 31 + Boolean.hashCode(this.isA2dpConn)) * 31 + Boolean.hashCode(this.isVisible);
   }

   public final boolean isA2dpConn() {
      return this.isA2dpConn;
   }

   public final boolean isConn() {
      return this.isConn;
   }

   public final boolean isConnA2dping() {
      return this.isConnA2dping;
   }

   public final boolean isConnHfping() {
      return this.isConnHfping;
   }

   public final boolean isHfpConn() {
      return this.isHfpConn;
   }

   public final boolean isPairIng() {
      return this.isPairIng;
   }

   public final boolean isPaired() {
      return this.isPaired;
   }

   public final boolean isVisible() {
      return this.isVisible;
   }

   public final void setA2dpConn(boolean var1) {
      this.isA2dpConn = var1;
   }

   public final void setAddress(String var1) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.address = var1;
   }

   public final void setConn(boolean var1) {
      this.isConn = var1;
   }

   public final void setConnA2dping(boolean var1) {
      this.isConnA2dping = var1;
   }

   public final void setConnHfping(boolean var1) {
      this.isConnHfping = var1;
   }

   public final void setHfpConn(boolean var1) {
      this.isHfpConn = var1;
   }

   public final void setName(String var1) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.name = var1;
   }

   public final void setPairIng(boolean var1) {
      this.isPairIng = var1;
   }

   public final void setPaired(boolean var1) {
      this.isPaired = var1;
   }

   public final void setVisible(boolean var1) {
      this.isVisible = var1;
   }

   public String toString() {
      return "Device{address='" + this.address + "', name='" + this.name + "', isPaired=" + this.isPaired + ", isHfpConn=" + this.isHfpConn + ", isA2dpConn=" + this.isA2dpConn + '}';
   }

   @Metadata(
      d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u0004\u0018\u00010\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006J\u000e\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0004¨\u0006\t"},
      d2 = {"Lcom/hzbhd/proxy/bluetooth/bean/Device$Companion;", "", "()V", "fromJson", "Lcom/hzbhd/proxy/bluetooth/bean/Device;", "deviceString", "", "toJsonString", "device", "bt-proxy_release"},
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

      public final Device fromJson(String var1) {
         try {
            if (!TextUtils.isEmpty((CharSequence)var1)) {
               JSONObject var9 = new JSONObject(var1);
               String var10 = var9.optString("ADDRESS");
               String var11 = var9.optString("NAME");
               boolean var3 = var9.optBoolean("PAIRED");
               boolean var8 = var9.optBoolean("CONNED");
               boolean var6 = var9.optBoolean("PAIRING");
               boolean var5 = var9.optBoolean("CONN_HFP_ING");
               boolean var7 = var9.optBoolean("CONN_A2DP_ING");
               boolean var4 = var9.optBoolean("HFP_CONNED");
               boolean var2 = var9.optBoolean("A2DP_CONNED");
               Intrinsics.checkNotNullExpressionValue(var10, "optString(DEVICE_NAME.ADDRESS.name)");
               Intrinsics.checkNotNullExpressionValue(var11, "optString(DEVICE_NAME.NAME.name)");
               Device var13 = new Device(var10, var11, var3, var8, var4, var6, var5, var7, var2);
               return var13;
            }
         } catch (Exception var12) {
            Log.d("Device", "fromJson: error:" + var1);
         }

         return null;
      }

      public final String toJsonString(Device var1) {
         Intrinsics.checkNotNullParameter(var1, "device");

         try {
            JSONObject var2 = new JSONObject();
            var2.put("ADDRESS", var1.getAddress());
            var2.put("NAME", var1.getName());
            var2.put("PAIRED", var1.isPaired());
            var2.put("CONNED", var1.isConn());
            var2.put("HFP_CONNED", var1.isHfpConn());
            var2.put("A2DP_CONNED", var1.isA2dpConn());
            var2.put("PAIRING", var1.isPairIng());
            var2.put("CONN_A2DP_ING", var1.isConnA2dping());
            var2.put("CONN_HFP_ING", var1.isConnHfping());
            String var4 = var2.toString();
            Intrinsics.checkNotNullExpressionValue(var4, "jsonObject.toString()");
            return var4;
         } catch (Exception var3) {
            Log.d("Device", "toJsonString: error");
            return "";
         }
      }
   }

   @Metadata(
      d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\f\b\u0082\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\f¨\u0006\r"},
      d2 = {"Lcom/hzbhd/proxy/bluetooth/bean/Device$DEVICE_NAME;", "", "(Ljava/lang/String;I)V", "ADDRESS", "NAME", "PAIRED", "HFP_CONNED", "A2DP_CONNED", "CONNED", "CURR_HFP", "CONN_A2DP_ING", "CONN_HFP_ING", "PAIRING", "bt-proxy_release"},
      k = 1,
      mv = {1, 7, 1},
      xi = 48
   )
   private static enum DEVICE_NAME {
      private static final DEVICE_NAME[] $VALUES = $values();
      A2DP_CONNED,
      ADDRESS,
      CONNED,
      CONN_A2DP_ING,
      CONN_HFP_ING,
      CURR_HFP,
      HFP_CONNED,
      NAME,
      PAIRED,
      PAIRING;

      // $FF: synthetic method
      private static final DEVICE_NAME[] $values() {
         return new DEVICE_NAME[]{ADDRESS, NAME, PAIRED, HFP_CONNED, A2DP_CONNED, CONNED, CURR_HFP, CONN_A2DP_ING, CONN_HFP_ING, PAIRING};
      }
   }
}
