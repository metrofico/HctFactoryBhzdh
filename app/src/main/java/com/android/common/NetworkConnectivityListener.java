package com.android.common;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import java.util.HashMap;
import java.util.Iterator;

@Deprecated
public class NetworkConnectivityListener {
   private static final boolean DBG = false;
   private static final String TAG = "NetworkConnectivityListener";
   private Context mContext;
   private HashMap mHandlers = new HashMap();
   private boolean mIsFailover;
   private boolean mListening;
   private NetworkInfo mNetworkInfo;
   private NetworkInfo mOtherNetworkInfo;
   private String mReason;
   private ConnectivityBroadcastReceiver mReceiver;
   private State mState;

   public NetworkConnectivityListener() {
      this.mState = State.UNKNOWN;
      this.mReceiver = new ConnectivityBroadcastReceiver(this);
   }

   public NetworkInfo getNetworkInfo() {
      return this.mNetworkInfo;
   }

   public NetworkInfo getOtherNetworkInfo() {
      return this.mOtherNetworkInfo;
   }

   public String getReason() {
      return this.mReason;
   }

   public State getState() {
      return this.mState;
   }

   public boolean isFailover() {
      return this.mIsFailover;
   }

   public void registerHandler(Handler var1, int var2) {
      this.mHandlers.put(var1, var2);
   }

   public void startListening(Context var1) {
      synchronized(this){}

      try {
         if (!this.mListening) {
            this.mContext = var1;
            IntentFilter var2 = new IntentFilter();
            var2.addAction("android.net.conn.CONNECTIVITY_CHANGE");
            var1.registerReceiver(this.mReceiver, var2);
            this.mListening = true;
         }
      } finally {
         ;
      }

   }

   public void stopListening() {
      synchronized(this){}

      try {
         if (this.mListening) {
            this.mContext.unregisterReceiver(this.mReceiver);
            this.mContext = null;
            this.mNetworkInfo = null;
            this.mOtherNetworkInfo = null;
            this.mIsFailover = false;
            this.mReason = null;
            this.mListening = false;
         }
      } finally {
         ;
      }

   }

   public void unregisterHandler(Handler var1) {
      this.mHandlers.remove(var1);
   }

   private class ConnectivityBroadcastReceiver extends BroadcastReceiver {
      final NetworkConnectivityListener this$0;

      private ConnectivityBroadcastReceiver(NetworkConnectivityListener var1) {
         this.this$0 = var1;
      }

      // $FF: synthetic method
      ConnectivityBroadcastReceiver(NetworkConnectivityListener var1, Object var2) {
         this(var1);
      }

      public void onReceive(Context var1, Intent var2) {
         if (var2.getAction().equals("android.net.conn.CONNECTIVITY_CHANGE") && this.this$0.mListening) {
            if (var2.getBooleanExtra("noConnectivity", false)) {
               this.this$0.mState = State.NOT_CONNECTED;
            } else {
               this.this$0.mState = State.CONNECTED;
            }

            this.this$0.mNetworkInfo = (NetworkInfo)var2.getParcelableExtra("networkInfo");
            this.this$0.mOtherNetworkInfo = (NetworkInfo)var2.getParcelableExtra("otherNetwork");
            this.this$0.mReason = var2.getStringExtra("reason");
            this.this$0.mIsFailover = var2.getBooleanExtra("isFailover", false);
            Iterator var4 = this.this$0.mHandlers.keySet().iterator();

            while(var4.hasNext()) {
               Handler var5 = (Handler)var4.next();
               var5.sendMessage(Message.obtain(var5, (Integer)this.this$0.mHandlers.get(var5)));
            }

         } else {
            StringBuilder var3 = new StringBuilder();
            var3.append("onReceived() called with ");
            var3.append(this.this$0.mState.toString());
            var3.append(" and ");
            var3.append(var2);
            Log.w("NetworkConnectivityListener", var3.toString());
         }
      }
   }

   public static enum State {
      private static final State[] $VALUES = new State[]{UNKNOWN, CONNECTED, NOT_CONNECTED};
      CONNECTED,
      NOT_CONNECTED,
      UNKNOWN;
   }
}
