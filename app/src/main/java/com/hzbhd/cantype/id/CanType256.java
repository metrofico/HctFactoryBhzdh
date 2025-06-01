package com.hzbhd.cantype.id;

import com.hzbhd.canbus.adapter.bean.CanTypeAllEntity;
import com.hzbhd.cantype.CanTypeBase;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;

@Metadata(
   d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R$\u0010\u0003\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u0004j\b\u0012\u0004\u0012\u00020\u0005`\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t"},
   d2 = {"Lcom/hzbhd/cantype/id/CanType256;", "Lcom/hzbhd/cantype/CanTypeBase;", "()V", "list", "Ljava/util/ArrayList;", "Lcom/hzbhd/canbus/adapter/bean/CanTypeAllEntity;", "Lkotlin/collections/ArrayList;", "getList", "()Ljava/util/ArrayList;", "CanBusInfo_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class CanType256 implements CanTypeBase {
   private final ArrayList list = CollectionsKt.arrayListOf(new CanTypeAllEntity[]{new CanTypeAllEntity("睿志诚", "吉利", "全球鹰GC7", "2012-2013", "Raise", "Geely", "global hawk GC7", "2012-2013", 256, 0, 0, 2, 1, 11, 1, 0, 0, "null"), new CanTypeAllEntity("睿志诚", "中华", "H530", "2014", "Raise", "Brilliance", "H530", "2014", 256, 0, 0, 2, 1, 11, 1, 0, 0, "null"), new CanTypeAllEntity("睿志诚", "中华", "V5", "2014-2016", "Raise", "Brilliance", "V5", "2014-2016", 256, 0, 0, 2, 1, 11, 1, 0, 0, "null"), new CanTypeAllEntity("睿志诚", "奔腾", "B50", "2009-2013", "Raise", "Besturn", "B50", "2009-2013", 256, 0, 0, 2, 1, 11, 1, 0, 0, "null"), new CanTypeAllEntity("睿志诚", "奔腾", "B70", "2006-2013", "Raise", "Besturn", "B70", "2006-2013", 256, 0, 0, 2, 1, 11, 1, 0, 0, "null"), new CanTypeAllEntity("睿志诚", "Chery", "A3", "2010-2012", "Raise", "Chery", "A3", "2010-2012", 256, 0, 0, 2, 1, 11, 1, 0, 0, "null"), new CanTypeAllEntity("睿志诚", "马自达", "默认", "默认", "Raise", "Mazda", "Default", "Default", 256, 0, 0, 2, 1, 11, 1, 0, 0, "null"), new CanTypeAllEntity("睿志诚", "马自达", "CX-7", "2014-Present", "Raise", "Mazda", "CX-7", "2014-Present", 256, 1, 0, 2, 1, 11, 1, 0, 0, "null")});

   public ArrayList getList() {
      return this.list;
   }
}
