package jxl.biff.drawing;

final class EscherRecordType {
   public static final EscherRecordType BSE = new EscherRecordType(61447);
   public static final EscherRecordType BSTORE_CONTAINER = new EscherRecordType(61441);
   public static final EscherRecordType CLIENT_ANCHOR = new EscherRecordType(61456);
   public static final EscherRecordType CLIENT_DATA = new EscherRecordType(61457);
   public static final EscherRecordType CLIENT_TEXT_BOX = new EscherRecordType(61453);
   public static final EscherRecordType DG = new EscherRecordType(61448);
   public static final EscherRecordType DGG = new EscherRecordType(61446);
   public static final EscherRecordType DGG_CONTAINER = new EscherRecordType(61440);
   public static final EscherRecordType DG_CONTAINER = new EscherRecordType(61442);
   public static final EscherRecordType OPT = new EscherRecordType(61451);
   public static final EscherRecordType SP = new EscherRecordType(61450);
   public static final EscherRecordType SPGR = new EscherRecordType(61449);
   public static final EscherRecordType SPGR_CONTAINER = new EscherRecordType(61443);
   public static final EscherRecordType SPLIT_MENU_COLORS = new EscherRecordType(61726);
   public static final EscherRecordType SP_CONTAINER = new EscherRecordType(61444);
   public static final EscherRecordType UNKNOWN = new EscherRecordType(0);
   private static EscherRecordType[] types = new EscherRecordType[0];
   private int value;

   private EscherRecordType(int var1) {
      this.value = var1;
      EscherRecordType[] var2 = types;
      EscherRecordType[] var3 = new EscherRecordType[var2.length + 1];
      System.arraycopy(var2, 0, var3, 0, var2.length);
      var3[types.length] = this;
      types = var3;
   }

   public static EscherRecordType getType(int var0) {
      EscherRecordType var3 = UNKNOWN;
      int var1 = 0;

      EscherRecordType var2;
      while(true) {
         EscherRecordType[] var4 = types;
         var2 = var3;
         if (var1 >= var4.length) {
            break;
         }

         var2 = var4[var1];
         if (var0 == var2.value) {
            break;
         }

         ++var1;
      }

      return var2;
   }

   public int getValue() {
      return this.value;
   }
}
