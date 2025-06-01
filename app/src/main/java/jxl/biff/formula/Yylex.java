package jxl.biff.formula;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import jxl.biff.WorkbookMethods;

class Yylex {
   public static final int YYEOF = -1;
   public static final int YYINITIAL = 0;
   public static final int YYSTRING = 1;
   private static final int[] ZZ_ACTION = zzUnpackAction();
   private static final String ZZ_ACTION_PACKED_0 = "\u0001\u0000\u0001\u0001\u0001\u0002\u0001\u0003\u0001\u0004\u0001\u0005\u0001\u0006\u0001\u0007\u0001\u0000\u0002\u0002\u0001\b\u0001\u0000\u0001\t\u0001\u0000\u0001\n\u0001\u000b\u0001\f\u0001\r\u0001\u000e\u0001\u000f\u0001\u0010\u0001\u0001\u0001\u0011\u0001\u0002\u0001\u0012\u0001\u0000\u0001\u0013\u0001\u0000\u0001\u0002\u0003\u0000\u0002\u0002\u0005\u0000\u0001\u0014\u0001\u0015\u0001\u0016\u0001\u0002\u0001\u0000\u0001\u0017\u0001\u0000\u0001\u0012\u0002\u0000\u0001\u0018\u0001\u0000\u0002\u0002\b\u0000\u0001\u0017\u0001\u0000\u0001\u0019\u0001\u0000\u0001\u001a\b\u0000\u0001\u001b\u0002\u0000\u0001\u0019\u0002\u0000\u0001\u001c\u0004\u0000\u0001\u001d\u0003\u0000\u0001\u001d\u0001\u0000\u0001\u001e\u0001\u0000";
   private static final int[] ZZ_ATTRIBUTE = zzUnpackAttribute();
   private static final String ZZ_ATTRIBUTE_PACKED_0 = "\u0001\u0000\u0003\u0001\u0004\t\u0001\u0000\u0002\u0001\u0001\t\u0001\u0000\u0001\t\u0001\u0000\u0004\t\u0001\u0001\u0001\t\u0002\u0001\u0001\t\u0002\u0001\u0001\u0000\u0001\t\u0001\u0000\u0001\u0001\u0003\u0000\u0002\u0001\u0005\u0000\u0003\t\u0001\u0001\u0001\u0000\u0001\u0001\u0001\u0000\u0001\u0001\u0002\u0000\u0001\u0001\u0001\u0000\u0002\u0001\b\u0000\u0001\t\u0001\u0000\u0001\u0001\u0001\u0000\u0001\u0001\b\u0000\u0001\u0001\u0002\u0000\u0001\u0001\u0002\u0000\u0001\t\u0004\u0000\u0001\u0001\u0003\u0000\u0001\t\u0001\u0000\u0001\u0001\u0001\u0000";
   private static final int ZZ_BUFFERSIZE = 16384;
   private static final char[] ZZ_CMAP = zzUnpackCMap("\b\u0000\u0003\u0015\u0015\u0000\u0001\u0015\u0001\u0014\u0001\u0011\u0001\u0016\u0001\b\u0002\u0000\u0001\u0012\u0001\u0005\u0001\u0006\u0001!\u0001\u001f\u0001\u0004\u0001 \u0001\u0007\u0001\u001b\u0001\u001c\t\u0002\u0001\u0003\u0001\u0000\u0001$\u0001#\u0001\"\u0001\u001e\u0001\u0000\u0001\u000e\u0002\u0001\u0001\u0018\u0001\f\u0001\r\u0002\u0001\u0001\u0019\u0002\u0001\u0001\u000f\u0001\u001d\u0001\u0017\u0003\u0001\u0001\n\u0001\u0010\u0001\t\u0001\u000b\u0001\u001a\u0004\u0001\u0004\u0000\u0001\u0013\u0001\u0000\u001a\u0001ﾅ\u0000");
   private static final String ZZ_CMAP_PACKED = "\b\u0000\u0003\u0015\u0015\u0000\u0001\u0015\u0001\u0014\u0001\u0011\u0001\u0016\u0001\b\u0002\u0000\u0001\u0012\u0001\u0005\u0001\u0006\u0001!\u0001\u001f\u0001\u0004\u0001 \u0001\u0007\u0001\u001b\u0001\u001c\t\u0002\u0001\u0003\u0001\u0000\u0001$\u0001#\u0001\"\u0001\u001e\u0001\u0000\u0001\u000e\u0002\u0001\u0001\u0018\u0001\f\u0001\r\u0002\u0001\u0001\u0019\u0002\u0001\u0001\u000f\u0001\u001d\u0001\u0017\u0003\u0001\u0001\n\u0001\u0010\u0001\t\u0001\u000b\u0001\u001a\u0004\u0001\u0004\u0000\u0001\u0013\u0001\u0000\u001a\u0001ﾅ\u0000";
   private static final String[] ZZ_ERROR_MSG = new String[]{"Unkown internal scanner error", "Error: could not match input", "Error: pushback value was too large"};
   private static final int ZZ_NO_MATCH = 1;
   private static final int ZZ_PUSHBACK_2BIG = 2;
   private static final int[] ZZ_ROWMAP = zzUnpackRowMap();
   private static final String ZZ_ROWMAP_PACKED_0 = "\u0000\u0000\u0000%\u0000J\u0000o\u0000\u0094\u0000\u0094\u0000\u0094\u0000\u0094\u0000¹\u0000Þ\u0000ă\u0000\u0094\u0000Ĩ\u0000\u0094\u0000ō\u0000\u0094\u0000\u0094\u0000\u0094\u0000\u0094\u0000Ų\u0000\u0094\u0000Ɨ\u0000Ƽ\u0000\u0094\u0000ǡ\u0000Ȇ\u0000ȫ\u0000\u0094\u0000ɐ\u0000ɵ\u0000ʚ\u0000ʿ\u0000ˤ\u0000̉\u0000̮\u0000͓\u0000\u0378\u0000Ν\u0000ς\u0000ϧ\u0000\u0094\u0000\u0094\u0000\u0094\u0000Ќ\u0000б\u0000і\u0000ѻ\u0000Ҡ\u0000Ӆ\u0000Ӫ\u0000ʿ\u0000ԏ\u0000Դ\u0000ՙ\u0000վ\u0000֣\u0000\u05c8\u0000\u05ed\u0000ؒ\u0000ط\u0000ٜ\u0000ځ\u0000\u0094\u0000ڦ\u0000ۋ\u0000ۋ\u0000Ќ\u0000۰\u0000ܕ\u0000ܺ\u0000ݟ\u0000ބ\u0000ީ\u0000ߎ\u0000߳\u0000࠘\u0000࠘\u0000࠽\u0000ࡢ\u0000ࢇ\u0000ࢬ\u0000\u0094\u0000࣑\u0000ࣶ\u0000छ\u0000ी\u0000॥\u0000ঊ\u0000য\u0000\u09d4\u0000\u0094\u0000৹\u0000ਞ\u0000ਞ";
   private static final int[] ZZ_TRANS = zzUnpackTrans();
   private static final String ZZ_TRANS_PACKED_0 = "\u0001\u0000\u0001\u0003\u0001\u0004\u0001\u0005\u0001\u0006\u0001\u0007\u0001\b\u0001\u0000\u0001\t\u0001\n\u0003\u0003\u0001\u000b\u0003\u0003\u0001\f\u0001\r\u0002\u0000\u0001\u000e\u0001\u000f\u0004\u0003\u0001\u0010\u0001\u0004\u0001\u0003\u0001\u0000\u0001\u0011\u0001\u0012\u0001\u0013\u0001\u0014\u0001\u0015\u0001\u0016\u0011\u0017\u0001\u0018\u0013\u0017\u0001\u0000\u0001\u0019\u0001\u001a\u0001\u001b\u0001\u0000\u0001\u001c\u0002\u0000\u0001\u001d\b\u0019\u0002\u0000\u0001\u001e\u0001\u001f\u0002\u0000\u0004\u0019\u0001\u0000\u0001\u001a\u0001\u0019\t\u0000\u0001\u0004\u0004\u0000\u0001 \u0014\u0000\u0001\u0004.\u0000\u0001!\u0007\u0000\b!\u0006\u0000\u0004!\u0002\u0000\u0001!\b\u0000\u0001\u0019\u0001\u001a\u0001\u001b\u0001\u0000\u0001\u001c\u0002\u0000\u0001\u001d\u0001\u0019\u0001\"\u0006\u0019\u0002\u0000\u0001\u001e\u0001\u001f\u0002\u0000\u0004\u0019\u0001\u0000\u0001\u001a\u0001\u0019\b\u0000\u0001\u0019\u0001\u001a\u0001\u001b\u0001\u0000\u0001\u001c\u0002\u0000\u0001\u001d\u0005\u0019\u0001#\u0002\u0019\u0002\u0000\u0001\u001e\u0001\u001f\u0002\u0000\u0004\u0019\u0001\u0000\u0001\u001a\u0001\u0019\u0007\u0000\u0012\r\u0001$\u0012\r\n\u0000\u0001%\f\u0000\u0001&\u0001'\u0001\u0000\u0001(-\u0000\u0001)#\u0000\u0001*\u0001+\u0001\u0000\u0011\u0017\u0001\u0000\u0013\u0017\u0001\u0000\u0001,\u0001\u001a\u0001\u001b\u0001\u0000\u0001\u001c\u0002\u0000\u0001\u001d\b,\u0002\u0000\u0001\u001e\u0001\u001f\u0002\u0000\u0004,\u0001\u0000\u0001\u001a\u0001,\b\u0000\u0001\u001e\u0001\u001a\u0001-\u0005\u0000\b\u001e\u0002\u0000\u0001\u001e\u0003\u0000\u0004\u001e\u0001\u0000\u0001\u001a\u0001\u001e\b\u0000\u0001.\u0006\u0000\u0001/\b.\u0006\u0000\u0004.\u0002\u0000\u0001.\t\u0000\u00010\u0019\u0000\u00010\t\u0000\u0002\u001e\u0006\u0000\b\u001e\u0002\u0000\u0001\u001e\u0003\u0000\u0004\u001e\u0001\u0000\u0002\u001e\b\u0000\u00011\u0006\u0000\u00012\b1\u0006\u0000\u00041\u0002\u0000\u00011\t\u0000\u00013\u0019\u0000\u00013\t\u0000\u00014\u00010\u0001\u001b\u0004\u0000\u0001\u001d\b4\u0006\u0000\u00044\u0001\u0000\u00010\u00014\b\u0000\u0001,\u0001\u001a\u0001\u001b\u0001\u0000\u0001\u001c\u0002\u0000\u0001\u001d\u0002,\u00015\u0005,\u0002\u0000\u0001\u001e\u0001\u001f\u0002\u0000\u0004,\u0001\u0000\u0001\u001a\u0001,\b\u0000\u0001,\u0001\u001a\u0001\u001b\u0001\u0000\u0001\u001c\u0002\u0000\u0001\u001d\u0006,\u00016\u0001,\u0002\u0000\u0001\u001e\u0001\u001f\u0002\u0000\u0004,\u0001\u0000\u0001\u001a\u0001,\u001b\u0000\u00017\u001c\u0000\u00018#\u0000\u00019\u0002\u0000\u0001:/\u0000\u0001;\u0019\u0000\u0001<\u0017\u0000\u0001,\u0001\u001e\u0002\u0000\u0001\u001c\u0003\u0000\b,\u0002\u0000\u0001\u001e\u0001\u001f\u0002\u0000\u0004,\u0001\u0000\u0001\u001e\u0001,\b\u0000\u0001=\u0006\u0000\u0001>\b=\u0006\u0000\u0004=\u0002\u0000\u0001=\b\u0000\u0001?\u0007\u0000\b?\u0006\u0000\u0004?\u0002\u0000\u0001?\b\u0000\u0001.\u0007\u0000\b.\u0006\u0000\u0004.\u0002\u0000\u0001.\t\u0000\u00010\u0001-\u0018\u0000\u00010\t\u0000\u0001@\u0001A\u0005\u0000\u0001B\b@\u0006\u0000\u0004@\u0001\u0000\u0001A\u0001@\b\u0000\u00011\u0007\u0000\b1\u0006\u0000\u00041\u0002\u0000\u00011\t\u0000\u00010\u0001\u001b\u0004\u0000\u0001\u001d\u0013\u0000\u00010\t\u0000\u0001,\u0001\u001e\u0002\u0000\u0001\u001c\u0003\u0000\u0003,\u0001C\u0004,\u0002\u0000\u0001\u001e\u0001\u001f\u0002\u0000\u0004,\u0001\u0000\u0001\u001e\u0001,\b\u0000\u0001,\u0001\u001e\u0002\u0000\u0001\u001c\u0003\u0000\u0007,\u00015\u0002\u0000\u0001\u001e\u0001\u001f\u0002\u0000\u0004,\u0001\u0000\u0001\u001e\u0001,\b\u0000\u0001D\u0006\u0000\u0001E\bD\u0006\u0000\u0004D\u0002\u0000\u0001D\u0014\u0000\u0001F&\u0000\u0001G\r\u0000\u0001F$\u0000\u0001H!\u0000\u0001I\u0019\u0000\u0001J\u0016\u0000\u0001K\u0001L\u0005\u0000\u0001M\bK\u0006\u0000\u0004K\u0001\u0000\u0001L\u0001K\b\u0000\u0001=\u0007\u0000\b=\u0006\u0000\u0004=\u0002\u0000\u0001=\t\u0000\u0001A\u0005\u0000\u0001B\u0013\u0000\u0001A\n\u0000\u0001A\u0019\u0000\u0001A\t\u0000\u0001N\u0001O\u0001P\u0004\u0000\u0001Q\bN\u0006\u0000\u0004N\u0001\u0000\u0001O\u0001N\b\u0000\u0001D\u0007\u0000\bD\u0006\u0000\u0004D\u0002\u0000\u0001D\u001b\u0000\u0001R\u001f\u0000\u0001F!\u0000\u0001S3\u0000\u0001T\u0014\u0000\u0001U\u001b\u0000\u0001L\u0005\u0000\u0001M\u0013\u0000\u0001L\n\u0000\u0001L\u0019\u0000\u0001L\n\u0000\u0001O\u0001P\u0004\u0000\u0001Q\u0013\u0000\u0001O\n\u0000\u0001O\u0001V\u0018\u0000\u0001O\t\u0000\u0001W\u0006\u0000\u0001X\bW\u0006\u0000\u0004W\u0002\u0000\u0001W\t\u0000\u0001O\u0019\u0000\u0001O&\u0000\u0001R\"\u0000\u0001F\u0014\u0000\u0001F\u0019\u0000\u0001Y\u0006\u0000\u0001Z\bY\u0006\u0000\u0004Y\u0002\u0000\u0001Y\b\u0000\u0001[\u0007\u0000\b[\u0006\u0000\u0004[\u0002\u0000\u0001[\b\u0000\u0001W\u0007\u0000\bW\u0006\u0000\u0004W\u0002\u0000\u0001W\b\u0000\u0001\\\u0001]\u0005\u0000\u0001^\b\\\u0006\u0000\u0004\\\u0001\u0000\u0001]\u0001\\\b\u0000\u0001Y\u0007\u0000\bY\u0006\u0000\u0004Y\u0002\u0000\u0001Y\t\u0000\u0001]\u0005\u0000\u0001^\u0013\u0000\u0001]\n\u0000\u0001]\u0019\u0000\u0001]\b\u0000";
   private static final int ZZ_UNKNOWN_ERROR = 0;
   private boolean emptyString;
   private ExternalSheet externalSheet;
   private WorkbookMethods nameTable;
   private int yychar;
   private int yycolumn;
   private int yyline;
   private boolean zzAtBOL;
   private boolean zzAtEOF;
   private char[] zzBuffer;
   private int zzCurrentPos;
   private int zzEndRead;
   private int zzLexicalState;
   private int zzMarkedPos;
   private int zzPushbackPos;
   private Reader zzReader;
   private int zzStartRead;
   private int zzState;

   Yylex(InputStream var1) {
      this((Reader)(new InputStreamReader(var1)));
   }

   Yylex(Reader var1) {
      this.zzLexicalState = 0;
      this.zzBuffer = new char[16384];
      this.zzAtBOL = true;
      this.zzReader = var1;
   }

   private boolean zzRefill() throws IOException {
      int var1 = this.zzStartRead;
      char[] var3;
      if (var1 > 0) {
         var3 = this.zzBuffer;
         System.arraycopy(var3, var1, var3, 0, this.zzEndRead - var1);
         var1 = this.zzEndRead;
         int var2 = this.zzStartRead;
         this.zzEndRead = var1 - var2;
         this.zzCurrentPos -= var2;
         this.zzMarkedPos -= var2;
         this.zzPushbackPos -= var2;
         this.zzStartRead = 0;
      }

      var1 = this.zzCurrentPos;
      var3 = this.zzBuffer;
      char[] var4;
      if (var1 >= var3.length) {
         var4 = new char[var1 * 2];
         System.arraycopy(var3, 0, var4, 0, var3.length);
         this.zzBuffer = var4;
      }

      Reader var5 = this.zzReader;
      var4 = this.zzBuffer;
      var1 = this.zzEndRead;
      var1 = var5.read(var4, var1, var4.length - var1);
      if (var1 < 0) {
         return true;
      } else {
         this.zzEndRead += var1;
         return false;
      }
   }

   private void zzScanError(int var1) {
      String var2;
      try {
         var2 = ZZ_ERROR_MSG[var1];
      } catch (ArrayIndexOutOfBoundsException var3) {
         var2 = ZZ_ERROR_MSG[0];
      }

      throw new Error(var2);
   }

   private static int zzUnpackAction(String var0, int var1, int[] var2) {
      int var5 = var0.length();

      int var6;
      for(int var3 = 0; var3 < var5; var3 = var6 + 1) {
         var6 = var3 + 1;
         int var4 = var0.charAt(var3);
         char var7 = var0.charAt(var6);
         var3 = var1;

         while(true) {
            var1 = var3 + 1;
            var2[var3] = var7;
            --var4;
            if (var4 <= 0) {
               break;
            }

            var3 = var1;
         }
      }

      return var1;
   }

   private static int[] zzUnpackAction() {
      int[] var0 = new int[94];
      zzUnpackAction("\u0001\u0000\u0001\u0001\u0001\u0002\u0001\u0003\u0001\u0004\u0001\u0005\u0001\u0006\u0001\u0007\u0001\u0000\u0002\u0002\u0001\b\u0001\u0000\u0001\t\u0001\u0000\u0001\n\u0001\u000b\u0001\f\u0001\r\u0001\u000e\u0001\u000f\u0001\u0010\u0001\u0001\u0001\u0011\u0001\u0002\u0001\u0012\u0001\u0000\u0001\u0013\u0001\u0000\u0001\u0002\u0003\u0000\u0002\u0002\u0005\u0000\u0001\u0014\u0001\u0015\u0001\u0016\u0001\u0002\u0001\u0000\u0001\u0017\u0001\u0000\u0001\u0012\u0002\u0000\u0001\u0018\u0001\u0000\u0002\u0002\b\u0000\u0001\u0017\u0001\u0000\u0001\u0019\u0001\u0000\u0001\u001a\b\u0000\u0001\u001b\u0002\u0000\u0001\u0019\u0002\u0000\u0001\u001c\u0004\u0000\u0001\u001d\u0003\u0000\u0001\u001d\u0001\u0000\u0001\u001e\u0001\u0000", 0, var0);
      return var0;
   }

   private static int zzUnpackAttribute(String var0, int var1, int[] var2) {
      int var5 = var0.length();

      int var6;
      for(int var3 = 0; var3 < var5; var3 = var6 + 1) {
         var6 = var3 + 1;
         var3 = var0.charAt(var3);
         char var7 = var0.charAt(var6);
         int var4 = var1;

         while(true) {
            var1 = var4 + 1;
            var2[var4] = var7;
            --var3;
            if (var3 <= 0) {
               break;
            }

            var4 = var1;
         }
      }

      return var1;
   }

   private static int[] zzUnpackAttribute() {
      int[] var0 = new int[94];
      zzUnpackAttribute("\u0001\u0000\u0003\u0001\u0004\t\u0001\u0000\u0002\u0001\u0001\t\u0001\u0000\u0001\t\u0001\u0000\u0004\t\u0001\u0001\u0001\t\u0002\u0001\u0001\t\u0002\u0001\u0001\u0000\u0001\t\u0001\u0000\u0001\u0001\u0003\u0000\u0002\u0001\u0005\u0000\u0003\t\u0001\u0001\u0001\u0000\u0001\u0001\u0001\u0000\u0001\u0001\u0002\u0000\u0001\u0001\u0001\u0000\u0002\u0001\b\u0000\u0001\t\u0001\u0000\u0001\u0001\u0001\u0000\u0001\u0001\b\u0000\u0001\u0001\u0002\u0000\u0001\u0001\u0002\u0000\u0001\t\u0004\u0000\u0001\u0001\u0003\u0000\u0001\t\u0001\u0000\u0001\u0001\u0001\u0000", 0, var0);
      return var0;
   }

   private static char[] zzUnpackCMap(String var0) {
      char[] var6 = new char[65536];
      int var3 = 0;

      int var5;
      for(int var2 = 0; var3 < 100; var3 = var5 + 1) {
         var5 = var3 + 1;
         int var4 = var0.charAt(var3);
         char var1 = var0.charAt(var5);
         var3 = var2;

         while(true) {
            var2 = var3 + 1;
            var6[var3] = var1;
            --var4;
            if (var4 <= 0) {
               break;
            }

            var3 = var2;
         }
      }

      return var6;
   }

   private static int zzUnpackRowMap(String var0, int var1, int[] var2) {
      int var4 = var0.length();

      int var5;
      for(int var3 = 0; var3 < var4; var3 = var5 + 1) {
         var5 = var3 + 1;
         var2[var1] = var0.charAt(var3) << 16 | var0.charAt(var5);
         ++var1;
      }

      return var1;
   }

   private static int[] zzUnpackRowMap() {
      int[] var0 = new int[94];
      zzUnpackRowMap("\u0000\u0000\u0000%\u0000J\u0000o\u0000\u0094\u0000\u0094\u0000\u0094\u0000\u0094\u0000¹\u0000Þ\u0000ă\u0000\u0094\u0000Ĩ\u0000\u0094\u0000ō\u0000\u0094\u0000\u0094\u0000\u0094\u0000\u0094\u0000Ų\u0000\u0094\u0000Ɨ\u0000Ƽ\u0000\u0094\u0000ǡ\u0000Ȇ\u0000ȫ\u0000\u0094\u0000ɐ\u0000ɵ\u0000ʚ\u0000ʿ\u0000ˤ\u0000̉\u0000̮\u0000͓\u0000\u0378\u0000Ν\u0000ς\u0000ϧ\u0000\u0094\u0000\u0094\u0000\u0094\u0000Ќ\u0000б\u0000і\u0000ѻ\u0000Ҡ\u0000Ӆ\u0000Ӫ\u0000ʿ\u0000ԏ\u0000Դ\u0000ՙ\u0000վ\u0000֣\u0000\u05c8\u0000\u05ed\u0000ؒ\u0000ط\u0000ٜ\u0000ځ\u0000\u0094\u0000ڦ\u0000ۋ\u0000ۋ\u0000Ќ\u0000۰\u0000ܕ\u0000ܺ\u0000ݟ\u0000ބ\u0000ީ\u0000ߎ\u0000߳\u0000࠘\u0000࠘\u0000࠽\u0000ࡢ\u0000ࢇ\u0000ࢬ\u0000\u0094\u0000࣑\u0000ࣶ\u0000छ\u0000ी\u0000॥\u0000ঊ\u0000য\u0000\u09d4\u0000\u0094\u0000৹\u0000ਞ\u0000ਞ", 0, var0);
      return var0;
   }

   private static int zzUnpackTrans(String var0, int var1, int[] var2) {
      int var5 = var0.length();

      int var6;
      for(int var3 = 0; var3 < var5; var3 = var6 + 1) {
         var6 = var3 + 1;
         int var4 = var0.charAt(var3);
         char var7 = var0.charAt(var6);
         var3 = var1;

         while(true) {
            var1 = var3 + 1;
            var2[var3] = var7 - 1;
            --var4;
            if (var4 <= 0) {
               break;
            }

            var3 = var1;
         }
      }

      return var1;
   }

   private static int[] zzUnpackTrans() {
      int[] var0 = new int[2627];
      zzUnpackTrans("\u0001\u0000\u0001\u0003\u0001\u0004\u0001\u0005\u0001\u0006\u0001\u0007\u0001\b\u0001\u0000\u0001\t\u0001\n\u0003\u0003\u0001\u000b\u0003\u0003\u0001\f\u0001\r\u0002\u0000\u0001\u000e\u0001\u000f\u0004\u0003\u0001\u0010\u0001\u0004\u0001\u0003\u0001\u0000\u0001\u0011\u0001\u0012\u0001\u0013\u0001\u0014\u0001\u0015\u0001\u0016\u0011\u0017\u0001\u0018\u0013\u0017\u0001\u0000\u0001\u0019\u0001\u001a\u0001\u001b\u0001\u0000\u0001\u001c\u0002\u0000\u0001\u001d\b\u0019\u0002\u0000\u0001\u001e\u0001\u001f\u0002\u0000\u0004\u0019\u0001\u0000\u0001\u001a\u0001\u0019\t\u0000\u0001\u0004\u0004\u0000\u0001 \u0014\u0000\u0001\u0004.\u0000\u0001!\u0007\u0000\b!\u0006\u0000\u0004!\u0002\u0000\u0001!\b\u0000\u0001\u0019\u0001\u001a\u0001\u001b\u0001\u0000\u0001\u001c\u0002\u0000\u0001\u001d\u0001\u0019\u0001\"\u0006\u0019\u0002\u0000\u0001\u001e\u0001\u001f\u0002\u0000\u0004\u0019\u0001\u0000\u0001\u001a\u0001\u0019\b\u0000\u0001\u0019\u0001\u001a\u0001\u001b\u0001\u0000\u0001\u001c\u0002\u0000\u0001\u001d\u0005\u0019\u0001#\u0002\u0019\u0002\u0000\u0001\u001e\u0001\u001f\u0002\u0000\u0004\u0019\u0001\u0000\u0001\u001a\u0001\u0019\u0007\u0000\u0012\r\u0001$\u0012\r\n\u0000\u0001%\f\u0000\u0001&\u0001'\u0001\u0000\u0001(-\u0000\u0001)#\u0000\u0001*\u0001+\u0001\u0000\u0011\u0017\u0001\u0000\u0013\u0017\u0001\u0000\u0001,\u0001\u001a\u0001\u001b\u0001\u0000\u0001\u001c\u0002\u0000\u0001\u001d\b,\u0002\u0000\u0001\u001e\u0001\u001f\u0002\u0000\u0004,\u0001\u0000\u0001\u001a\u0001,\b\u0000\u0001\u001e\u0001\u001a\u0001-\u0005\u0000\b\u001e\u0002\u0000\u0001\u001e\u0003\u0000\u0004\u001e\u0001\u0000\u0001\u001a\u0001\u001e\b\u0000\u0001.\u0006\u0000\u0001/\b.\u0006\u0000\u0004.\u0002\u0000\u0001.\t\u0000\u00010\u0019\u0000\u00010\t\u0000\u0002\u001e\u0006\u0000\b\u001e\u0002\u0000\u0001\u001e\u0003\u0000\u0004\u001e\u0001\u0000\u0002\u001e\b\u0000\u00011\u0006\u0000\u00012\b1\u0006\u0000\u00041\u0002\u0000\u00011\t\u0000\u00013\u0019\u0000\u00013\t\u0000\u00014\u00010\u0001\u001b\u0004\u0000\u0001\u001d\b4\u0006\u0000\u00044\u0001\u0000\u00010\u00014\b\u0000\u0001,\u0001\u001a\u0001\u001b\u0001\u0000\u0001\u001c\u0002\u0000\u0001\u001d\u0002,\u00015\u0005,\u0002\u0000\u0001\u001e\u0001\u001f\u0002\u0000\u0004,\u0001\u0000\u0001\u001a\u0001,\b\u0000\u0001,\u0001\u001a\u0001\u001b\u0001\u0000\u0001\u001c\u0002\u0000\u0001\u001d\u0006,\u00016\u0001,\u0002\u0000\u0001\u001e\u0001\u001f\u0002\u0000\u0004,\u0001\u0000\u0001\u001a\u0001,\u001b\u0000\u00017\u001c\u0000\u00018#\u0000\u00019\u0002\u0000\u0001:/\u0000\u0001;\u0019\u0000\u0001<\u0017\u0000\u0001,\u0001\u001e\u0002\u0000\u0001\u001c\u0003\u0000\b,\u0002\u0000\u0001\u001e\u0001\u001f\u0002\u0000\u0004,\u0001\u0000\u0001\u001e\u0001,\b\u0000\u0001=\u0006\u0000\u0001>\b=\u0006\u0000\u0004=\u0002\u0000\u0001=\b\u0000\u0001?\u0007\u0000\b?\u0006\u0000\u0004?\u0002\u0000\u0001?\b\u0000\u0001.\u0007\u0000\b.\u0006\u0000\u0004.\u0002\u0000\u0001.\t\u0000\u00010\u0001-\u0018\u0000\u00010\t\u0000\u0001@\u0001A\u0005\u0000\u0001B\b@\u0006\u0000\u0004@\u0001\u0000\u0001A\u0001@\b\u0000\u00011\u0007\u0000\b1\u0006\u0000\u00041\u0002\u0000\u00011\t\u0000\u00010\u0001\u001b\u0004\u0000\u0001\u001d\u0013\u0000\u00010\t\u0000\u0001,\u0001\u001e\u0002\u0000\u0001\u001c\u0003\u0000\u0003,\u0001C\u0004,\u0002\u0000\u0001\u001e\u0001\u001f\u0002\u0000\u0004,\u0001\u0000\u0001\u001e\u0001,\b\u0000\u0001,\u0001\u001e\u0002\u0000\u0001\u001c\u0003\u0000\u0007,\u00015\u0002\u0000\u0001\u001e\u0001\u001f\u0002\u0000\u0004,\u0001\u0000\u0001\u001e\u0001,\b\u0000\u0001D\u0006\u0000\u0001E\bD\u0006\u0000\u0004D\u0002\u0000\u0001D\u0014\u0000\u0001F&\u0000\u0001G\r\u0000\u0001F$\u0000\u0001H!\u0000\u0001I\u0019\u0000\u0001J\u0016\u0000\u0001K\u0001L\u0005\u0000\u0001M\bK\u0006\u0000\u0004K\u0001\u0000\u0001L\u0001K\b\u0000\u0001=\u0007\u0000\b=\u0006\u0000\u0004=\u0002\u0000\u0001=\t\u0000\u0001A\u0005\u0000\u0001B\u0013\u0000\u0001A\n\u0000\u0001A\u0019\u0000\u0001A\t\u0000\u0001N\u0001O\u0001P\u0004\u0000\u0001Q\bN\u0006\u0000\u0004N\u0001\u0000\u0001O\u0001N\b\u0000\u0001D\u0007\u0000\bD\u0006\u0000\u0004D\u0002\u0000\u0001D\u001b\u0000\u0001R\u001f\u0000\u0001F!\u0000\u0001S3\u0000\u0001T\u0014\u0000\u0001U\u001b\u0000\u0001L\u0005\u0000\u0001M\u0013\u0000\u0001L\n\u0000\u0001L\u0019\u0000\u0001L\n\u0000\u0001O\u0001P\u0004\u0000\u0001Q\u0013\u0000\u0001O\n\u0000\u0001O\u0001V\u0018\u0000\u0001O\t\u0000\u0001W\u0006\u0000\u0001X\bW\u0006\u0000\u0004W\u0002\u0000\u0001W\t\u0000\u0001O\u0019\u0000\u0001O&\u0000\u0001R\"\u0000\u0001F\u0014\u0000\u0001F\u0019\u0000\u0001Y\u0006\u0000\u0001Z\bY\u0006\u0000\u0004Y\u0002\u0000\u0001Y\b\u0000\u0001[\u0007\u0000\b[\u0006\u0000\u0004[\u0002\u0000\u0001[\b\u0000\u0001W\u0007\u0000\bW\u0006\u0000\u0004W\u0002\u0000\u0001W\b\u0000\u0001\\\u0001]\u0005\u0000\u0001^\b\\\u0006\u0000\u0004\\\u0001\u0000\u0001]\u0001\\\b\u0000\u0001Y\u0007\u0000\bY\u0006\u0000\u0004Y\u0002\u0000\u0001Y\t\u0000\u0001]\u0005\u0000\u0001^\u0013\u0000\u0001]\n\u0000\u0001]\u0019\u0000\u0001]\b\u0000", 0, var0);
      return var0;
   }

   int getPos() {
      return this.yychar;
   }

   void setExternalSheet(ExternalSheet var1) {
      this.externalSheet = var1;
   }

   void setNameTable(WorkbookMethods var1) {
      this.nameTable = var1;
   }

   public final void yybegin(int var1) {
      this.zzLexicalState = var1;
   }

   public final char yycharat(int var1) {
      return this.zzBuffer[this.zzStartRead + var1];
   }

   public final void yyclose() throws IOException {
      this.zzAtEOF = true;
      this.zzEndRead = this.zzStartRead;
      Reader var1 = this.zzReader;
      if (var1 != null) {
         var1.close();
      }

   }

   public final int yylength() {
      return this.zzMarkedPos - this.zzStartRead;
   }

   public ParseItem yylex() throws IOException, FormulaException {
      int var1 = this.zzEndRead;
      char[] var9 = this.zzBuffer;
      char[] var13 = ZZ_CMAP;
      int[] var11 = ZZ_TRANS;
      int[] var12 = ZZ_ROWMAP;
      int[] var14 = ZZ_ATTRIBUTE;

      while(true) {
         int var4 = this.zzMarkedPos;
         int var2 = this.yychar;
         int var3 = this.zzStartRead;
         this.yychar = var2 + (var4 - var3);

         boolean var5;
         boolean var16;
         for(var5 = false; var3 < var4; var5 = var16) {
            label118: {
               label117: {
                  char var15 = var9[var3];
                  if (var15 != 133 && var15 != 8232 && var15 != 8233) {
                     switch (var15) {
                        case '\n':
                           if (!var5) {
                              ++this.yyline;
                              var16 = var5;
                              break label118;
                           }
                           break label117;
                        case '\u000b':
                        case '\f':
                           break;
                        case '\r':
                           ++this.yyline;
                           var16 = true;
                           break label118;
                        default:
                           break label117;
                     }
                  }

                  ++this.yyline;
               }

               var16 = false;
            }

            ++var3;
         }

         var3 = var1;
         char[] var10 = var9;
         var2 = var4;
         boolean var8;
         if (var5) {
            label131: {
               if (var4 < var1) {
                  if (var9[var4] == '\n') {
                     var5 = true;
                     break label131;
                  }
               } else if (!this.zzAtEOF) {
                  var8 = this.zzRefill();
                  var1 = this.zzEndRead;
                  var4 = this.zzMarkedPos;
                  var9 = this.zzBuffer;
                  if (var8) {
                     var16 = false;
                  } else if (var9[var4] == '\n') {
                     var16 = true;
                  } else {
                     var16 = false;
                  }

                  var5 = var16;
                  break label131;
               }

               var5 = false;
            }

            var3 = var1;
            var10 = var9;
            var2 = var4;
            if (var5) {
               --this.yyline;
               var2 = var4;
               var10 = var9;
               var3 = var1;
            }
         }

         this.zzStartRead = var2;
         this.zzCurrentPos = var2;
         this.zzState = this.zzLexicalState;
         var1 = var2;
         var4 = -1;
         int var17 = var2;
         var2 = var3;

         while(true) {
            int var6;
            if (var17 < var2) {
               var6 = var17 + 1;
               var17 = var10[var17];
               var3 = var1;
               var1 = var6;
            } else {
               if (this.zzAtEOF) {
                  var17 = -1;
                  break;
               }

               this.zzCurrentPos = var17;
               this.zzMarkedPos = var1;
               var8 = this.zzRefill();
               var1 = this.zzCurrentPos;
               var3 = this.zzMarkedPos;
               var10 = this.zzBuffer;
               var2 = this.zzEndRead;
               if (var8) {
                  var17 = -1;
                  var1 = var3;
                  break;
               }

               var17 = var10[var1];
               ++var1;
            }

            var6 = var11[var12[this.zzState] + var13[var17]];
            if (var6 == -1) {
               var1 = var3;
               break;
            }

            this.zzState = var6;
            int var7 = var14[var6];
            if ((var7 & 1) == 1) {
               if ((var7 & 8) == 8) {
                  var4 = var6;
                  break;
               }

               var3 = var1;
               var4 = var6;
            }

            var17 = var1;
            var1 = var3;
         }

         this.zzMarkedPos = var1;
         if (var4 >= 0) {
            var4 = ZZ_ACTION[var4];
         }

         var1 = var2;
         var9 = var10;
         switch (var4) {
            case 1:
               this.emptyString = false;
               return new StringValue(this.yytext());
            case 2:
               return new NameRange(this.yytext(), this.nameTable);
            case 3:
               return new IntegerValue(this.yytext());
            case 4:
               return new RangeSeparator();
            case 5:
               return new ArgumentSeparator();
            case 6:
               return new OpenParentheses();
            case 7:
               return new CloseParentheses();
            case 8:
               this.emptyString = true;
               this.yybegin(1);
               var1 = var2;
               var9 = var10;
            case 9:
            case 31:
            case 32:
            case 33:
            case 34:
            case 35:
            case 36:
            case 37:
            case 38:
            case 39:
            case 40:
            case 41:
            case 42:
            case 43:
            case 44:
            case 45:
            case 46:
            case 47:
            case 48:
            case 49:
            case 50:
            case 51:
            case 52:
            case 53:
            case 54:
            case 55:
            case 56:
            case 57:
            case 58:
            case 59:
            case 60:
               break;
            case 10:
               return new Divide();
            case 11:
               return new Plus();
            case 12:
               return new Minus();
            case 13:
               return new Multiply();
            case 14:
               return new GreaterThan();
            case 15:
               return new Equal();
            case 16:
               return new LessThan();
            case 17:
               this.yybegin(0);
               var1 = var2;
               var9 = var10;
               if (this.emptyString) {
                  return new StringValue("");
               }
               break;
            case 18:
               return new CellReference(this.yytext());
            case 19:
               return new StringFunction(this.yytext());
            case 20:
               return new GreaterEqual();
            case 21:
               return new NotEqual();
            case 22:
               return new LessEqual();
            case 23:
               return new ColumnRange(this.yytext());
            case 24:
               return new DoubleValue(this.yytext());
            case 25:
               return new CellReference3d(this.yytext(), this.externalSheet);
            case 26:
               return new BooleanValue(this.yytext());
            case 27:
               return new Area(this.yytext());
            case 28:
               return new ErrorConstant(this.yytext());
            case 29:
               return new ColumnRange3d(this.yytext(), this.externalSheet);
            case 30:
               return new Area3d(this.yytext(), this.externalSheet);
            default:
               if (var17 == -1 && this.zzStartRead == this.zzCurrentPos) {
                  this.zzAtEOF = true;
                  return null;
               }

               this.zzScanError(1);
               var1 = var2;
               var9 = var10;
         }
      }
   }

   public void yypushback(int var1) {
      if (var1 > this.yylength()) {
         this.zzScanError(2);
      }

      this.zzMarkedPos -= var1;
   }

   public final void yyreset(Reader var1) {
      this.zzReader = var1;
      this.zzAtBOL = true;
      this.zzAtEOF = false;
      this.zzStartRead = 0;
      this.zzEndRead = 0;
      this.zzPushbackPos = 0;
      this.zzMarkedPos = 0;
      this.zzCurrentPos = 0;
      this.yycolumn = 0;
      this.yychar = 0;
      this.yyline = 0;
      this.zzLexicalState = 0;
   }

   public final int yystate() {
      return this.zzLexicalState;
   }

   public final String yytext() {
      char[] var2 = this.zzBuffer;
      int var1 = this.zzStartRead;
      return new String(var2, var1, this.zzMarkedPos - var1);
   }
}
