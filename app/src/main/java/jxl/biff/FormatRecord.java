package jxl.biff;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import jxl.WorkbookSettings;
import jxl.common.Logger;
import jxl.format.Format;
import jxl.read.biff.Record;

public class FormatRecord extends WritableRecordData implements DisplayFormat, Format {
   public static final BiffType biff7 = new BiffType();
   public static final BiffType biff8 = new BiffType();
   private static String[] dateStrings = new String[]{"dd", "mm", "yy", "hh", "ss", "m/", "/d"};
   public static Logger logger = Logger.getLogger(FormatRecord.class);
   private byte[] data;
   private boolean date;
   private java.text.Format format;
   private String formatString;
   private int indexCode;
   private boolean initialized;
   private boolean number;

   protected FormatRecord() {
      super(Type.FORMAT);
      this.initialized = false;
   }

   FormatRecord(String var1, int var2) {
      super(Type.FORMAT);
      this.formatString = var1;
      this.indexCode = var2;
      this.initialized = true;
   }

   protected FormatRecord(FormatRecord var1) {
      super(Type.FORMAT);
      this.initialized = false;
      this.formatString = var1.formatString;
      this.date = var1.date;
      this.number = var1.number;
   }

   public FormatRecord(Record var1, WorkbookSettings var2, BiffType var3) {
      super(var1);
      byte[] var6 = this.getRecord().getData();
      int var4 = 0;
      this.indexCode = IntegerHelper.getInt(var6[0], var6[1]);
      this.initialized = true;
      if (var3 == biff8) {
         int var5 = IntegerHelper.getInt(var6[2], var6[3]);
         if (var6[4] == 0) {
            this.formatString = StringHelper.getString(var6, var5, 5, var2);
         } else {
            this.formatString = StringHelper.getUnicodeString(var6, var5, 5);
         }
      } else {
         byte var10 = var6[2];
         byte[] var9 = new byte[var10];
         System.arraycopy(var6, 3, var9, 0, var10);
         this.formatString = new String(var9);
      }

      this.date = false;
      this.number = false;

      while(true) {
         String[] var7 = dateStrings;
         if (var4 >= var7.length) {
            break;
         }

         String var8 = var7[var4];
         if (this.formatString.indexOf(var8) != -1 || this.formatString.indexOf(var8.toUpperCase()) != -1) {
            this.date = true;
            break;
         }

         ++var4;
      }

      if (!this.date && (this.formatString.indexOf(35) != -1 || this.formatString.indexOf(48) != -1)) {
         this.number = true;
      }

   }

   public boolean equals(Object var1) {
      if (var1 == this) {
         return true;
      } else if (!(var1 instanceof FormatRecord)) {
         return false;
      } else {
         FormatRecord var2 = (FormatRecord)var1;
         if (this.initialized && var2.initialized) {
            return this.date == var2.date && this.number == var2.number ? this.formatString.equals(var2.formatString) : false;
         } else {
            return this.formatString.equals(var2.formatString);
         }
      }
   }

   public byte[] getData() {
      byte[] var1 = new byte[this.formatString.length() * 2 + 3 + 2];
      this.data = var1;
      IntegerHelper.getTwoBytes(this.indexCode, var1, 0);
      IntegerHelper.getTwoBytes(this.formatString.length(), this.data, 2);
      var1 = this.data;
      var1[4] = 1;
      StringHelper.getUnicodeBytes(this.formatString, var1, 5);
      return this.data;
   }

   public final DateFormat getDateFormat() {
      java.text.Format var10 = this.format;
      if (var10 != null && var10 instanceof DateFormat) {
         return (DateFormat)var10;
      } else {
         String var15 = this.formatString;
         int var2 = var15.indexOf("AM/PM");

         while(true) {
            int var6 = 0;
            StringBuffer var11;
            if (var2 == -1) {
               for(var2 = var15.indexOf("ss.0"); var2 != -1; var2 = var15.indexOf("ss.0")) {
                  var11 = new StringBuffer(var15.substring(0, var2));
                  var11.append("ss.SSS");

                  for(var2 += 4; var2 < var15.length() && var15.charAt(var2) == '0'; ++var2) {
                  }

                  var11.append(var15.substring(var2));
                  var15 = var11.toString();
               }

               var11 = new StringBuffer();

               for(var2 = 0; var2 < var15.length(); ++var2) {
                  if (var15.charAt(var2) != '\\') {
                     var11.append(var15.charAt(var2));
                  }
               }

               String var16 = var11.toString();
               var15 = var16;
               if (var16.charAt(0) == '[') {
                  var2 = var16.indexOf(93);
                  var15 = var16;
                  if (var2 != -1) {
                     var15 = var16.substring(var2 + 1);
                  }
               }

               char[] var12;
               for(var12 = this.replace(var15, ";@", "").toCharArray(); var6 < var12.length; ++var6) {
                  if (var12[var6] == 'm') {
                     if (var6 > 0) {
                        char var1 = var12[var6 - 1];
                        if (var1 == 'm' || var1 == 'M') {
                           var12[var6] = var1;
                           continue;
                        }
                     }

                     var2 = var6 - 1;
                     int var3 = var2;

                     int var4;
                     while(true) {
                        if (var3 <= 0) {
                           var4 = Integer.MAX_VALUE;
                           break;
                        }

                        if (var12[var3] == 'h') {
                           var4 = var6 - var3;
                           break;
                        }

                        --var3;
                     }

                     var3 = var6 + 1;
                     int var7 = var3;

                     int var5;
                     while(true) {
                        var5 = var4;
                        if (var7 >= var12.length) {
                           break;
                        }

                        if (var12[var7] == 'h') {
                           var5 = Math.min(var4, var7 - var6);
                           break;
                        }

                        ++var7;
                     }

                     var7 = var2;

                     while(true) {
                        var4 = var5;
                        if (var7 <= 0) {
                           break;
                        }

                        if (var12[var7] == 'H') {
                           var4 = var6 - var7;
                           break;
                        }

                        --var7;
                     }

                     var7 = var3;

                     while(true) {
                        var5 = var4;
                        if (var7 >= var12.length) {
                           break;
                        }

                        if (var12[var7] == 'H') {
                           var5 = Math.min(var4, var7 - var6);
                           break;
                        }

                        ++var7;
                     }

                     var7 = var2;

                     while(true) {
                        var4 = var5;
                        if (var7 <= 0) {
                           break;
                        }

                        if (var12[var7] == 's') {
                           var4 = Math.min(var5, var6 - var7);
                           break;
                        }

                        --var7;
                     }

                     var5 = var3;

                     while(true) {
                        var7 = var4;
                        if (var5 >= var12.length) {
                           break;
                        }

                        if (var12[var5] == 's') {
                           var7 = Math.min(var4, var5 - var6);
                           break;
                        }

                        ++var5;
                     }

                     var4 = var2;

                     while(true) {
                        if (var4 <= 0) {
                           var5 = Integer.MAX_VALUE;
                           break;
                        }

                        if (var12[var4] == 'd') {
                           var5 = var6 - var4;
                           break;
                        }

                        --var4;
                     }

                     int var9 = var3;

                     int var8;
                     while(true) {
                        var8 = var2;
                        var4 = var5;
                        if (var9 >= var12.length) {
                           break;
                        }

                        if (var12[var9] == 'd') {
                           var4 = Math.min(var5, var9 - var6);
                           var8 = var2;
                           break;
                        }

                        ++var9;
                     }

                     while(true) {
                        var2 = var4;
                        var5 = var3;
                        if (var8 <= 0) {
                           break;
                        }

                        if (var12[var8] == 'y') {
                           var2 = Math.min(var4, var6 - var8);
                           var5 = var3;
                           break;
                        }

                        --var8;
                     }

                     while(true) {
                        var3 = var2;
                        if (var5 >= var12.length) {
                           break;
                        }

                        if (var12[var5] == 'y') {
                           var3 = Math.min(var2, var5 - var6);
                           break;
                        }

                        ++var5;
                     }

                     if (var3 < var7) {
                        var12[var6] = Character.toUpperCase(var12[var6]);
                     } else if (var3 == var7 && var3 != Integer.MAX_VALUE) {
                        char var14 = var12[var6 - var3];
                        if (var14 == 'y' || var14 == 'd') {
                           var12[var6] = Character.toUpperCase(var12[var6]);
                        }
                     }
                  }
               }

               try {
                  var15 = new String(var12);
                  SimpleDateFormat var17 = new SimpleDateFormat(var15);
                  this.format = var17;
               } catch (IllegalArgumentException var13) {
                  this.format = new SimpleDateFormat("dd MM yyyy hh:mm:ss");
               }

               return (DateFormat)this.format;
            }

            var11 = new StringBuffer(var15.substring(0, var2));
            var11.append('a');
            var11.append(var15.substring(var2 + 5));
            var15 = var11.toString();
            var2 = var15.indexOf("AM/PM");
         }
      }
   }

   public int getFormatIndex() {
      return this.indexCode;
   }

   public String getFormatString() {
      return this.formatString;
   }

   public int getIndexCode() {
      return this.indexCode;
   }

   public final NumberFormat getNumberFormat() {
      java.text.Format var1 = this.format;
      if (var1 != null && var1 instanceof NumberFormat) {
         return (NumberFormat)var1;
      } else {
         try {
            String var2 = this.replace(this.replace(this.replace(this.replace(this.replace(this.formatString, "E+", "E"), "_)", ""), "_", ""), "[Red]", ""), "\\", "");
            DecimalFormat var4 = new DecimalFormat(var2);
            this.format = var4;
         } catch (IllegalArgumentException var3) {
            this.format = new DecimalFormat("#.###");
         }

         return (NumberFormat)this.format;
      }
   }

   public int hashCode() {
      return this.formatString.hashCode();
   }

   public void initialize(int var1) {
      this.indexCode = var1;
      this.initialized = true;
   }

   public boolean isBuiltIn() {
      return false;
   }

   public final boolean isDate() {
      return this.date;
   }

   public boolean isInitialized() {
      return this.initialized;
   }

   public final boolean isNumber() {
      return this.number;
   }

   protected final String replace(String var1, String var2, String var3) {
      for(int var4 = var1.indexOf(var2); var4 != -1; var4 = var1.indexOf(var2)) {
         StringBuffer var5 = new StringBuffer(var1.substring(0, var4));
         var5.append(var3);
         var5.append(var1.substring(var4 + var2.length()));
         var1 = var5.toString();
      }

      return var1;
   }

   protected final void setFormatString(String var1) {
      this.formatString = var1;
   }

   private static class BiffType {
      private BiffType() {
      }

      // $FF: synthetic method
      BiffType(Object var1) {
         this();
      }
   }
}
