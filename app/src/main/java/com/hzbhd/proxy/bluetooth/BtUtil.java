package com.hzbhd.proxy.bluetooth;

import com.hzbhd.proxy.bluetooth.bean.BtCall;
import com.hzbhd.proxy.bluetooth.bean.Device;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J$\u0010\u0003\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u0004j\b\u0012\u0004\u0012\u00020\u0005`\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bJ$\u0010\n\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u0004j\b\u0012\u0004\u0012\u00020\u0005`\u00062\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\bJ$\u0010\r\u001a\u0012\u0012\u0004\u0012\u00020\t0\u0004j\b\u0012\u0004\u0012\u00020\t`\u00062\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00050\bJ\u001a\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\f0\u00042\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00050\b¨\u0006\u0011"},
   d2 = {"Lcom/hzbhd/proxy/bluetooth/BtUtil;", "", "()V", "callsToString", "Ljava/util/ArrayList;", "", "Lkotlin/collections/ArrayList;", "calls", "", "Lcom/hzbhd/proxy/bluetooth/bean/BtCall;", "devicesToString", "devices", "Lcom/hzbhd/proxy/bluetooth/bean/Device;", "stringToCall", "callStrings", "stringToDevices", "stringDevices", "bt-proxy_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class BtUtil {
   public static final BtUtil INSTANCE = new BtUtil();

   private BtUtil() {
   }

   public final ArrayList callsToString(List var1) {
      Intrinsics.checkNotNullParameter(var1, "calls");
      ArrayList var4 = new ArrayList();
      int var3 = var1.size();

      for(int var2 = 0; var2 < var3; ++var2) {
         var4.add(BtCall.Companion.toJsonString((BtCall)var1.get(var2)));
      }

      return var4;
   }

   public final ArrayList devicesToString(List var1) {
      Intrinsics.checkNotNullParameter(var1, "devices");
      ArrayList var4 = new ArrayList();
      int var3 = var1.size();

      for(int var2 = 0; var2 < var3; ++var2) {
         var4.add(Device.Companion.toJsonString((Device)var1.get(var2)));
      }

      return var4;
   }

   public final ArrayList stringToCall(List var1) {
      Intrinsics.checkNotNullParameter(var1, "callStrings");
      ArrayList var4 = new ArrayList();
      int var3 = var1.size();

      for(int var2 = 0; var2 < var3; ++var2) {
         BtCall var5 = BtCall.Companion.fromJson((String)var1.get(var2));
         Intrinsics.checkNotNull(var5);
         var4.add(var5);
      }

      return var4;
   }

   public final ArrayList stringToDevices(List var1) {
      Intrinsics.checkNotNullParameter(var1, "stringDevices");
      ArrayList var5 = new ArrayList();
      int var3 = var1.size();

      for(int var2 = 0; var2 < var3; ++var2) {
         Device var4 = Device.Companion.fromJson((String)var1.get(var2));
         Intrinsics.checkNotNull(var4);
         var5.add(var4);
      }

      return var5;
   }
}
