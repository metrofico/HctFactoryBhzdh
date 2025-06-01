package com.hzbhd.cantype;

import com.hzbhd.canbus.adapter.bean.CanTypeAllEntity;
import com.hzbhd.cantype.id.CanType0;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J.\u0010\u0007\u001a\u0012\u0012\u0004\u0012\u00020\t0\bj\b\u0012\u0004\u0012\u00020\t`\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u0006¨\u0006\u000f"},
   d2 = {"Lcom/hzbhd/cantype/CanTypeUtil;", "", "()V", "getCanType", "Lcom/hzbhd/cantype/CanTypeBase;", "type", "", "getCanTypeAllEntityByCompanyAndCategory", "Ljava/util/ArrayList;", "Lcom/hzbhd/canbus/adapter/bean/CanTypeAllEntity;", "Lkotlin/collections/ArrayList;", "company", "", "category", "canType", "CanBusInfo_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class CanTypeUtil {
   public static final CanTypeUtil INSTANCE = new CanTypeUtil();

   private CanTypeUtil() {
   }

   public final CanTypeBase getCanType(int var1) {
      try {
         StringBuilder var2 = new StringBuilder();
         Object var4 = Class.forName(var2.append("com.hzbhd.cantype.id.CanType").append(var1).toString()).newInstance();
         Intrinsics.checkNotNull(var4, "null cannot be cast to non-null type com.hzbhd.cantype.CanTypeBase");
         CanTypeBase var5 = (CanTypeBase)var4;
         return var5;
      } catch (Exception var3) {
         var3.printStackTrace();
         return (CanTypeBase)(new CanType0());
      }
   }

   public final ArrayList getCanTypeAllEntityByCompanyAndCategory(String var1, String var2, int var3) {
      Intrinsics.checkNotNullParameter(var1, "company");
      Intrinsics.checkNotNullParameter(var2, "category");
      ArrayList var5 = new ArrayList();
      Iterator var4 = this.getCanType(var3).getList().iterator();

      while(var4.hasNext()) {
         CanTypeAllEntity var6 = (CanTypeAllEntity)var4.next();
         if (var1.equals(var6.getProtocol_company()) & var2.equals(var6.getCar_category())) {
            var5.add(var6);
         } else if (var1.equals(var6.getEnglish_protocol_company()) & var2.equals(var6.getEnglish_car_category())) {
            var5.add(var6);
         }
      }

      return var5;
   }
}
