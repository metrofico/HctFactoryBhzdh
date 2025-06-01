package jxl.biff;

public class BuiltInName {
   public static final BuiltInName AUTO_ACTIVATE = new BuiltInName("Auto_Activate", 10);
   public static final BuiltInName AUTO_CLOSE = new BuiltInName("Auto_Open", 2);
   public static final BuiltInName AUTO_DEACTIVATE = new BuiltInName("Auto_Deactivate", 11);
   public static final BuiltInName AUTO_OPEN = new BuiltInName("Auto_Open", 1);
   public static final BuiltInName CONSOLIDATE_AREA = new BuiltInName("Consolidate_Area", 0);
   public static final BuiltInName CRITERIA = new BuiltInName("Criteria", 5);
   public static final BuiltInName DATABASE = new BuiltInName("Database", 4);
   public static final BuiltInName DATA_FORM = new BuiltInName("Data_Form", 9);
   public static final BuiltInName EXTRACT = new BuiltInName("Extract", 3);
   public static final BuiltInName FILTER_DATABASE = new BuiltInName("_FilterDatabase", 13);
   public static final BuiltInName PRINT_AREA = new BuiltInName("Print_Area", 6);
   public static final BuiltInName PRINT_TITLES = new BuiltInName("Print_Titles", 7);
   public static final BuiltInName RECORDER = new BuiltInName("Recorder", 8);
   public static final BuiltInName SHEET_TITLE = new BuiltInName("Sheet_Title", 11);
   private static BuiltInName[] builtInNames = new BuiltInName[0];
   private String name;
   private int value;

   private BuiltInName(String var1, int var2) {
      this.name = var1;
      this.value = var2;
      BuiltInName[] var3 = builtInNames;
      BuiltInName[] var4 = new BuiltInName[var3.length + 1];
      builtInNames = var4;
      System.arraycopy(var3, 0, var4, 0, var3.length);
      builtInNames[var3.length] = this;
   }

   public static BuiltInName getBuiltInName(int var0) {
      BuiltInName var2 = FILTER_DATABASE;
      int var1 = 0;

      while(true) {
         BuiltInName[] var3 = builtInNames;
         if (var1 >= var3.length) {
            return var2;
         }

         if (var3[var1].getValue() == var0) {
            var2 = builtInNames[var1];
         }

         ++var1;
      }
   }

   public String getName() {
      return this.name;
   }

   public int getValue() {
      return this.value;
   }
}
