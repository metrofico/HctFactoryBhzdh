package com.hzbhd.canbus.car._436;

import com.hzbhd.proxy.callback.IDvrDataCallBack;
import com.hzbhd.proxy.dvr.DvrManager;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\u0007B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\b"},
   d2 = {"Lcom/hzbhd/canbus/car/_436/DvrDataKAdapter;", "", "()V", "registerOnDataReadCallback", "", "listener", "Lcom/hzbhd/canbus/car/_436/DvrDataKAdapter$OnDvrDataReadListener;", "OnDvrDataReadListener", "CanBusInfo_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class DvrDataKAdapter {
   public static final DvrDataKAdapter INSTANCE = new DvrDataKAdapter();

   private DvrDataKAdapter() {
   }

   public final void registerOnDataReadCallback(OnDvrDataReadListener var1) {
      Intrinsics.checkNotNullParameter(var1, "listener");
      DvrManager.INSTANCE.registerOnDataReadCallback((IDvrDataCallBack)(new IDvrDataCallBack.Stub(var1) {
         final OnDvrDataReadListener $listener;

         {
            this.$listener = var1;
         }

         public void onDvrDataRead(byte[] var1) {
            if (var1 != null) {
               this.$listener.onDataRead(var1);
            }

         }
      }));
   }

   @Metadata(
      d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"},
      d2 = {"Lcom/hzbhd/canbus/car/_436/DvrDataKAdapter$OnDvrDataReadListener;", "", "onDataRead", "", "bytes", "", "CanBusInfo_release"},
      k = 1,
      mv = {1, 7, 1},
      xi = 48
   )
   public interface OnDvrDataReadListener {
      void onDataRead(byte[] var1);
   }
}
