package kotlin.collections;

import kotlin.Metadata;

@Metadata(
   d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0010\u0010\u0003\u001a\u00020\u00048\u0000X\u0081\u0004¢\u0006\u0002\n\u0000¨\u0006\u0005"},
   d2 = {"Lkotlin/collections/CollectionSystemProperties;", "", "()V", "brittleContainsOptimizationEnabled", "", "kotlin-stdlib"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class CollectionSystemProperties {
   public static final CollectionSystemProperties INSTANCE = new CollectionSystemProperties();
   public static final boolean brittleContainsOptimizationEnabled;

   static {
      String var1 = System.getProperty("kotlin.collections.convert_arg_to_set_in_removeAll");
      boolean var0;
      if (var1 != null) {
         var0 = Boolean.parseBoolean(var1);
      } else {
         var0 = false;
      }

      brittleContainsOptimizationEnabled = var0;
   }

   private CollectionSystemProperties() {
   }
}
