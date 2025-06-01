package jxl.read.biff;

import java.util.ArrayList;
import jxl.WorkbookSettings;
import jxl.biff.BuiltInName;
import jxl.biff.IntegerHelper;
import jxl.biff.RecordData;
import jxl.biff.StringHelper;
import jxl.common.Assert;
import jxl.common.Logger;

public class NameRecord extends RecordData {
   private static final int areaReference = 59;
   public static Biff7 biff7 = new Biff7();
   private static final int builtIn = 32;
   private static final int cellReference = 58;
   private static final int commandMacro = 12;
   private static Logger logger = Logger.getLogger(NameRecord.class);
   private static final int subExpression = 41;
   private static final int union = 16;
   private BuiltInName builtInName;
   private int index;
   private boolean isbiff8;
   private String name;
   private ArrayList ranges;
   private int sheetRef;

   NameRecord(Record var1, WorkbookSettings var2, int var3) {
      super(var1);
      boolean var9 = false;
      this.sheetRef = 0;
      this.index = var3;
      this.isbiff8 = true;

      label3165: {
         boolean var10001;
         byte[] var318;
         try {
            ArrayList var317 = new ArrayList();
            this.ranges = var317;
            var318 = this.getRecord().getData();
            var3 = IntegerHelper.getInt(var318[0], var318[1]);
         } catch (Throwable var316) {
            var10001 = false;
            break label3165;
         }

         int var4 = var318[3];

         try {
            this.sheetRef = IntegerHelper.getInt(var318[8], var318[9]);
         } catch (Throwable var315) {
            var10001 = false;
            break label3165;
         }

         if ((var3 & 32) != 0) {
            try {
               this.builtInName = BuiltInName.getBuiltInName(var318[15]);
            } catch (Throwable var314) {
               var10001 = false;
               break label3165;
            }
         } else {
            try {
               this.name = StringHelper.getString(var318, var4, 15, var2);
            } catch (Throwable var313) {
               var10001 = false;
               break label3165;
            }
         }

         if ((var3 & 12) != 0) {
            return;
         }

         var4 += 15;
         int var5 = var318[var4];
         int var6;
         NameRange var319;
         if (var5 == 58) {
            try {
               var3 = IntegerHelper.getInt(var318[var4 + 1], var318[var4 + 2]);
               var5 = IntegerHelper.getInt(var318[var4 + 3], var318[var4 + 4]);
               var4 = IntegerHelper.getInt(var318[var4 + 5], var318[var4 + 6]);
            } catch (Throwable var301) {
               var10001 = false;
               break label3165;
            }

            var6 = var4 & 255;
            if ((var4 & 786432) == 0) {
               var9 = true;
            }

            try {
               Assert.verify(var9);
               var319 = new NameRange(this, var3, var6, var5, var6, var5);
               this.ranges.add(var319);
            } catch (Throwable var300) {
               var10001 = false;
               break label3165;
            }
         } else {
            NameRange var321;
            int var7;
            int var8;
            if (var5 == 59) {
               var3 = var4;

               while(true) {
                  try {
                     if (var3 >= var318.length) {
                        return;
                     }

                     var5 = IntegerHelper.getInt(var318[var3 + 1], var318[var3 + 2]);
                     var4 = IntegerHelper.getInt(var318[var3 + 3], var318[var3 + 4]);
                     var7 = IntegerHelper.getInt(var318[var3 + 5], var318[var3 + 6]);
                     var8 = IntegerHelper.getInt(var318[var3 + 7], var318[var3 + 8]);
                  } catch (Throwable var304) {
                     var10001 = false;
                     break label3165;
                  }

                  if ((var8 & 786432) == 0) {
                     var9 = true;
                  } else {
                     var9 = false;
                  }

                  try {
                     Assert.verify(var9);
                     var6 = IntegerHelper.getInt(var318[var3 + 9], var318[var3 + 10]);
                  } catch (Throwable var303) {
                     var10001 = false;
                     break label3165;
                  }

                  if ((var6 & 786432) == 0) {
                     var9 = true;
                  } else {
                     var9 = false;
                  }

                  try {
                     Assert.verify(var9);
                     var321 = new NameRange(this, var5, var8 & 255, var4, var6 & 255, var7);
                     this.ranges.add(var321);
                  } catch (Throwable var302) {
                     var10001 = false;
                     break label3165;
                  }

                  var3 += 11;
               }
            } else if (var5 == 41) {
               var3 = var4;

               label3118: {
                  try {
                     if (var4 >= var318.length) {
                        break label3118;
                     }
                  } catch (Throwable var309) {
                     var10001 = false;
                     break label3165;
                  }

                  var3 = var4;
                  if (var5 != 58) {
                     var3 = var4;
                     if (var5 != 59) {
                        if (var5 == 41) {
                           var3 = var4 + 3;
                        } else {
                           var3 = var4;
                           if (var5 == 16) {
                              var3 = var4 + 1;
                           }
                        }
                     }
                  }
               }

               while(true) {
                  try {
                     if (var3 >= var318.length) {
                        return;
                     }

                     var8 = IntegerHelper.getInt(var318[var3 + 1], var318[var3 + 2]);
                     var4 = IntegerHelper.getInt(var318[var3 + 3], var318[var3 + 4]);
                     var6 = IntegerHelper.getInt(var318[var3 + 5], var318[var3 + 6]);
                     var5 = IntegerHelper.getInt(var318[var3 + 7], var318[var3 + 8]);
                  } catch (Throwable var308) {
                     var10001 = false;
                     break label3165;
                  }

                  if ((var5 & 786432) == 0) {
                     var9 = true;
                  } else {
                     var9 = false;
                  }

                  try {
                     Assert.verify(var9);
                     var7 = IntegerHelper.getInt(var318[var3 + 9], var318[var3 + 10]);
                  } catch (Throwable var307) {
                     var10001 = false;
                     break label3165;
                  }

                  if ((var7 & 786432) == 0) {
                     var9 = true;
                  } else {
                     var9 = false;
                  }

                  try {
                     Assert.verify(var9);
                     var321 = new NameRange(this, var8, var5 & 255, var4, var7 & 255, var6);
                     this.ranges.add(var321);
                  } catch (Throwable var306) {
                     var10001 = false;
                     break label3165;
                  }

                  var4 = var3 + 11;
                  var3 = var4;

                  try {
                     if (var4 >= var318.length) {
                        continue;
                     }
                  } catch (Throwable var305) {
                     var10001 = false;
                     break label3165;
                  }

                  byte var323 = var318[var4];
                  var3 = var4;
                  if (var323 != 58) {
                     var3 = var4;
                     if (var323 != 59) {
                        if (var323 == 41) {
                           var3 = var4 + 3;
                        } else {
                           var3 = var4;
                           if (var323 == 16) {
                              var3 = var4 + 1;
                           }
                        }
                     }
                  }
               }
            } else {
               String var320;
               try {
                  var320 = this.name;
               } catch (Throwable var312) {
                  var10001 = false;
                  break label3165;
               }

               if (var320 == null) {
                  try {
                     var320 = this.builtInName.getName();
                  } catch (Throwable var311) {
                     var10001 = false;
                     break label3165;
                  }
               }

               try {
                  Logger var10 = logger;
                  StringBuilder var322 = new StringBuilder();
                  var10.warn(var322.append("Cannot read name ranges for ").append(var320).append(" - setting to empty").toString());
                  var319 = new NameRange(this, 0, 0, 0, 0, 0);
                  this.ranges.add(var319);
               } catch (Throwable var310) {
                  var10001 = false;
                  break label3165;
               }
            }
         }

         return;
      }

      logger.warn("Cannot read name");
      this.name = "ERROR";
   }

   NameRecord(Record param1, WorkbookSettings param2, int param3, Biff7 param4) {
      // $FF: Couldn't be decompiled
   }

   public BuiltInName getBuiltInName() {
      return this.builtInName;
   }

   public byte[] getData() {
      return this.getRecord().getData();
   }

   int getIndex() {
      return this.index;
   }

   public String getName() {
      return this.name;
   }

   public NameRange[] getRanges() {
      NameRange[] var1 = new NameRange[this.ranges.size()];
      return (NameRange[])this.ranges.toArray(var1);
   }

   public int getSheetRef() {
      return this.sheetRef;
   }

   public boolean isBiff8() {
      return this.isbiff8;
   }

   public boolean isGlobal() {
      boolean var1;
      if (this.sheetRef == 0) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public void setSheetRef(int var1) {
      this.sheetRef = var1;
   }

   private static class Biff7 {
      private Biff7() {
      }

      // $FF: synthetic method
      Biff7(Object var1) {
         this();
      }
   }

   public class NameRange {
      private int columnFirst;
      private int columnLast;
      private int externalSheet;
      private int rowFirst;
      private int rowLast;
      final NameRecord this$0;

      NameRange(NameRecord var1, int var2, int var3, int var4, int var5, int var6) {
         this.this$0 = var1;
         this.columnFirst = var3;
         this.rowFirst = var4;
         this.columnLast = var5;
         this.rowLast = var6;
         this.externalSheet = var2;
      }

      public int getExternalSheet() {
         return this.externalSheet;
      }

      public int getFirstColumn() {
         return this.columnFirst;
      }

      public int getFirstRow() {
         return this.rowFirst;
      }

      public int getLastColumn() {
         return this.columnLast;
      }

      public int getLastRow() {
         return this.rowLast;
      }
   }
}
