package com.hzbhd.proxy.bluetooth.bean;

import android.text.TextUtils;
import android.util.Log;
import com.hzbhd.constant.bluetooth.BtConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

@Metadata(
   d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b \u0018\u0000 )2\u00020\u0001:\u0001)B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010(\u001a\u00020\u0004H\u0016R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\u000b\"\u0004\b\f\u0010\rR\u001a\u0010\u000e\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000b\"\u0004\b\u000f\u0010\rR\u001a\u0010\u0010\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u000b\"\u0004\b\u0011\u0010\rR\u001a\u0010\u0012\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u000b\"\u0004\b\u0013\u0010\rR\u001a\u0010\u0014\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u000b\"\u0004\b\u0015\u0010\rR\u001a\u0010\u0016\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u000b\"\u0004\b\u0017\u0010\rR\u001a\u0010\u0018\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u000b\"\u0004\b\u0019\u0010\rR\u001a\u0010\u001a\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u000b\"\u0004\b\u001b\u0010\rR\u001a\u0010\u001c\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u000b\"\u0004\b\u001d\u0010\rR\u001a\u0010\u001e\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u000b\"\u0004\b\u001f\u0010\rR\u001a\u0010 \u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010\u000b\"\u0004\b!\u0010\rR\u001a\u0010\"\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u000b\"\u0004\b#\u0010\rR\u001a\u0010$\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010\u000b\"\u0004\b%\u0010\rR\u001a\u0010&\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010\u000b\"\u0004\b'\u0010\r¨\u0006*"},
   d2 = {"Lcom/hzbhd/proxy/bluetooth/bean/BtStatus;", "", "()V", "address", "", "getAddress", "()Ljava/lang/String;", "setAddress", "(Ljava/lang/String;)V", "isA2dpConn", "", "()Z", "setA2dpConn", "(Z)V", "isA2dpPlaying", "setA2dpPlaying", "isCalling", "setCalling", "isConning", "setConning", "isEnable", "setEnable", "isHfpConn", "setHfpConn", "isInA2dpSource", "setInA2dpSource", "isInComing", "setInComing", "isInHfpSource", "setInHfpSource", "isOutGoing", "setOutGoing", "isPairing", "setPairing", "isPbSyncFinish", "setPbSyncFinish", "isPbSyncing", "setPbSyncing", "isScanning", "setScanning", "toString", "Companion", "bt-proxy_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class BtStatus {
   private static final String ADDRESS = "address";
   public static final Companion Companion = new Companion((DefaultConstructorMarker)null);
   private String address = "";
   private boolean isA2dpConn;
   private boolean isA2dpPlaying;
   private boolean isCalling;
   private boolean isConning;
   private boolean isEnable;
   private boolean isHfpConn;
   private boolean isInA2dpSource;
   private boolean isInComing;
   private boolean isInHfpSource;
   private boolean isOutGoing;
   private boolean isPairing;
   private boolean isPbSyncFinish;
   private boolean isPbSyncing;
   private boolean isScanning;

   public final String getAddress() {
      return this.address;
   }

   public final boolean isA2dpConn() {
      return this.isA2dpConn;
   }

   public final boolean isA2dpPlaying() {
      return this.isA2dpPlaying;
   }

   public final boolean isCalling() {
      return this.isCalling;
   }

   public final boolean isConning() {
      return this.isConning;
   }

   public final boolean isEnable() {
      return this.isEnable;
   }

   public final boolean isHfpConn() {
      return this.isHfpConn;
   }

   public final boolean isInA2dpSource() {
      return this.isInA2dpSource;
   }

   public final boolean isInComing() {
      return this.isInComing;
   }

   public final boolean isInHfpSource() {
      return this.isInHfpSource;
   }

   public final boolean isOutGoing() {
      return this.isOutGoing;
   }

   public final boolean isPairing() {
      return this.isPairing;
   }

   public final boolean isPbSyncFinish() {
      return this.isPbSyncFinish;
   }

   public final boolean isPbSyncing() {
      return this.isPbSyncing;
   }

   public final boolean isScanning() {
      return this.isScanning;
   }

   public final void setA2dpConn(boolean var1) {
      this.isA2dpConn = var1;
   }

   public final void setA2dpPlaying(boolean var1) {
      this.isA2dpPlaying = var1;
   }

   public final void setAddress(String var1) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.address = var1;
   }

   public final void setCalling(boolean var1) {
      this.isCalling = var1;
   }

   public final void setConning(boolean var1) {
      this.isConning = var1;
   }

   public final void setEnable(boolean var1) {
      this.isEnable = var1;
   }

   public final void setHfpConn(boolean var1) {
      this.isHfpConn = var1;
   }

   public final void setInA2dpSource(boolean var1) {
      this.isInA2dpSource = var1;
   }

   public final void setInComing(boolean var1) {
      this.isInComing = var1;
   }

   public final void setInHfpSource(boolean var1) {
      this.isInHfpSource = var1;
   }

   public final void setOutGoing(boolean var1) {
      this.isOutGoing = var1;
   }

   public final void setPairing(boolean var1) {
      this.isPairing = var1;
   }

   public final void setPbSyncFinish(boolean var1) {
      this.isPbSyncFinish = var1;
   }

   public final void setPbSyncing(boolean var1) {
      this.isPbSyncing = var1;
   }

   public final void setScanning(boolean var1) {
      this.isScanning = var1;
   }

   public String toString() {
      StringBuilder var1 = new StringBuilder();
      var1.append("BtStatus{address=").append(this.address).append("isHfpConn=").append(this.isHfpConn).append(", isA2dpConn=").append(this.isA2dpConn).append(", isScanning=").append(this.isScanning).append(", isPairing=").append(this.isPairing).append(", isConning=").append(this.isConning).append(", isInHfpSource=").append(this.isInHfpSource).append(", isInA2dpSource=").append(this.isInA2dpSource).append(", isA2dpPlaying=").append(this.isA2dpPlaying).append(", isCalling=").append(this.isCalling).append(", isInComing=").append(this.isInComing).append(", isOutGoing=");
      var1.append(this.isOutGoing).append(", isPbSyncing=").append(this.isPbSyncing).append(", isPbSyncFinish=").append(this.isPbSyncFinish).append('}');
      return var1.toString();
   }

   @Metadata(
      d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\u0004J\u000e\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u0006R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\n"},
      d2 = {"Lcom/hzbhd/proxy/bluetooth/bean/BtStatus$Companion;", "", "()V", "ADDRESS", "", "fromJson", "Lcom/hzbhd/proxy/bluetooth/bean/BtStatus;", "statusString", "toJsonString", "btStatus", "bt-proxy_release"},
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

      public final BtStatus fromJson(String var1) {
         try {
            if (!TextUtils.isEmpty((CharSequence)var1)) {
               JSONObject var4 = new JSONObject(var1);
               BtStatus var2 = new BtStatus();
               String var3 = var4.optString("address");
               Intrinsics.checkNotNullExpressionValue(var3, "jsonObject.optString(ADDRESS)");
               var2.setAddress(var3);
               var2.setEnable(var4.optBoolean(BtConstants.BT_STATUS.ENABLE.name()));
               var2.setHfpConn(var4.optBoolean(BtConstants.BT_STATUS.HFP_CONN.name()));
               var2.setA2dpConn(var4.optBoolean(BtConstants.BT_STATUS.A2DP_CONN.name()));
               var2.setScanning(var4.optBoolean(BtConstants.BT_STATUS.SCANNING.name()));
               var2.setPairing(var4.optBoolean(BtConstants.BT_STATUS.PAIRING.name()));
               var2.setConning(var4.optBoolean(BtConstants.BT_STATUS.CONNING.name()));
               var2.setInHfpSource(var4.optBoolean(BtConstants.BT_STATUS.IN_HFP.name()));
               var2.setInA2dpSource(var4.optBoolean(BtConstants.BT_STATUS.IN_A2DP.name()));
               var2.setA2dpPlaying(var4.optBoolean(BtConstants.BT_STATUS.A2DP_PLAYING.name()));
               var2.setCalling(var4.optBoolean(BtConstants.BT_STATUS.CALLING.name()));
               var2.setInComing(var4.optBoolean(BtConstants.BT_STATUS.INCOMING.name()));
               var2.setOutGoing(var4.optBoolean(BtConstants.BT_STATUS.OUTGOING.name()));
               var2.setPbSyncing(var4.optBoolean(BtConstants.BT_STATUS.SYNC_PB_ING.name()));
               var2.setPbSyncFinish(var4.optBoolean(BtConstants.BT_STATUS.SYNC_PB_FINISH.name()));
               return var2;
            }
         } catch (Exception var5) {
            Log.d("Device", "fromJson: error:" + var1);
         }

         return null;
      }

      public final String toJsonString(BtStatus var1) {
         Intrinsics.checkNotNullParameter(var1, "btStatus");

         try {
            JSONObject var2 = new JSONObject();
            var2.put("address", var1.getAddress());
            var2.put(BtConstants.BT_STATUS.ENABLE.name(), var1.isEnable());
            var2.put(BtConstants.BT_STATUS.HFP_CONN.name(), var1.isHfpConn());
            var2.put(BtConstants.BT_STATUS.A2DP_CONN.name(), var1.isA2dpConn());
            var2.put(BtConstants.BT_STATUS.SCANNING.name(), var1.isScanning());
            var2.put(BtConstants.BT_STATUS.PAIRING.name(), var1.isPairing());
            var2.put(BtConstants.BT_STATUS.CONNING.name(), var1.isConning());
            var2.put(BtConstants.BT_STATUS.IN_HFP.name(), var1.isInHfpSource());
            var2.put(BtConstants.BT_STATUS.IN_A2DP.name(), var1.isInA2dpSource());
            var2.put(BtConstants.BT_STATUS.A2DP_PLAYING.name(), var1.isA2dpPlaying());
            var2.put(BtConstants.BT_STATUS.CALLING.name(), var1.isCalling());
            var2.put(BtConstants.BT_STATUS.INCOMING.name(), var1.isInComing());
            var2.put(BtConstants.BT_STATUS.OUTGOING.name(), var1.isOutGoing());
            var2.put(BtConstants.BT_STATUS.SYNC_PB_ING.name(), var1.isPbSyncing());
            var2.put(BtConstants.BT_STATUS.SYNC_PB_FINISH.name(), var1.isPbSyncFinish());
            String var4 = var2.toString();
            Intrinsics.checkNotNullExpressionValue(var4, "jsonObject.toString()");
            return var4;
         } catch (Exception var3) {
            Log.d("Device", "toJsonString: error");
            return "";
         }
      }
   }
}
