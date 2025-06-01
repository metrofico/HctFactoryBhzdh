package jxl.biff;

import jxl.common.Assert;
import jxl.common.Logger;

public abstract class BaseCompoundFile {
   protected static final int BIG_BLOCK_DEPOT_BLOCKS_POS = 76;
   protected static final int BIG_BLOCK_SIZE = 512;
   private static final int CHILD_POS = 76;
   private static final int COLOUR_POS = 67;
   public static final String COMP_OBJ_NAME = "\u0001CompObj";
   public static final int DIRECTORY_PS_TYPE = 1;
   public static final String DOCUMENT_SUMMARY_INFORMATION_NAME = "\u0005DocumentSummaryInformation";
   protected static final int EXTENSION_BLOCK_POS = 68;
   public static final int FILE_PS_TYPE = 2;
   protected static final byte[] IDENTIFIER = new byte[]{-48, -49, 17, -32, -95, -79, 26, -31};
   private static final int NEXT_POS = 72;
   public static final int NONE_PS_TYPE = 0;
   protected static final int NUM_BIG_BLOCK_DEPOT_BLOCKS_POS = 44;
   protected static final int NUM_EXTENSION_BLOCK_POS = 72;
   protected static final int NUM_SMALL_BLOCK_DEPOT_BLOCKS_POS = 64;
   private static final int PREVIOUS_POS = 68;
   protected static final int PROPERTY_STORAGE_BLOCK_SIZE = 128;
   public static final String ROOT_ENTRY_NAME = "Root Entry";
   public static final int ROOT_ENTRY_PS_TYPE = 5;
   protected static final int ROOT_START_BLOCK_POS = 48;
   private static final int SIZE_OF_NAME_POS = 64;
   private static final int SIZE_POS = 120;
   protected static final int SMALL_BLOCK_DEPOT_BLOCK_POS = 60;
   protected static final int SMALL_BLOCK_SIZE = 64;
   protected static final int SMALL_BLOCK_THRESHOLD = 4096;
   public static final String[] STANDARD_PROPERTY_SETS = new String[]{"Root Entry", "Workbook", "\u0005SummaryInformation", "\u0005DocumentSummaryInformation"};
   private static final int START_BLOCK_POS = 116;
   public static final String SUMMARY_INFORMATION_NAME = "\u0005SummaryInformation";
   private static final int TYPE_POS = 66;
   public static final String WORKBOOK_NAME = "Workbook";
   private static Logger logger = Logger.getLogger(BaseCompoundFile.class);

   protected BaseCompoundFile() {
   }

   public class PropertyStorage {
      public int child;
      public int colour;
      public byte[] data;
      public String name;
      public int next;
      public int previous;
      public int size;
      public int startBlock;
      final BaseCompoundFile this$0;
      public int type;

      public PropertyStorage(BaseCompoundFile var1, String var2) {
         this.this$0 = var1;
         this.data = new byte[128];
         int var4 = var2.length();
         int var3 = 0;
         boolean var5;
         if (var4 < 32) {
            var5 = true;
         } else {
            var5 = false;
         }

         Assert.verify(var5);
         IntegerHelper.getTwoBytes((var2.length() + 1) * 2, this.data, 64);

         while(var3 < var2.length()) {
            this.data[var3 * 2] = (byte)var2.charAt(var3);
            ++var3;
         }

      }

      public PropertyStorage(BaseCompoundFile var1, byte[] var2) {
         this.this$0 = var1;
         this.data = var2;
         int var4 = 64;
         int var3 = IntegerHelper.getInt(var2[64], var2[65]);
         if (var3 > 64) {
            BaseCompoundFile.logger.warn("property set name exceeds max length - truncating");
            var3 = var4;
         }

         byte[] var5 = this.data;
         this.type = var5[66];
         this.colour = var5[67];
         this.startBlock = IntegerHelper.getInt(var5[116], var5[117], var5[118], var5[119]);
         var5 = this.data;
         this.size = IntegerHelper.getInt(var5[120], var5[121], var5[122], var5[123]);
         var5 = this.data;
         this.previous = IntegerHelper.getInt(var5[68], var5[69], var5[70], var5[71]);
         var5 = this.data;
         this.next = IntegerHelper.getInt(var5[72], var5[73], var5[74], var5[75]);
         var5 = this.data;
         this.child = IntegerHelper.getInt(var5[76], var5[77], var5[78], var5[79]);
         var4 = 0;
         if (var3 > 2) {
            var3 = (var3 - 1) / 2;
         } else {
            var3 = 0;
         }

         StringBuffer var6;
         for(var6 = new StringBuffer(""); var4 < var3; ++var4) {
            var6.append((char)this.data[var4 * 2]);
         }

         this.name = var6.toString();
      }

      public void setChild(int var1) {
         this.child = var1;
         IntegerHelper.getFourBytes(var1, this.data, 76);
      }

      public void setColour(int var1) {
         byte var2;
         if (var1 == 0) {
            var2 = 0;
         } else {
            var2 = 1;
         }

         this.colour = var2;
         this.data[67] = (byte)var2;
      }

      public void setNext(int var1) {
         this.next = var1;
         IntegerHelper.getFourBytes(var1, this.data, 72);
      }

      public void setPrevious(int var1) {
         this.previous = var1;
         IntegerHelper.getFourBytes(var1, this.data, 68);
      }

      public void setSize(int var1) {
         this.size = var1;
         IntegerHelper.getFourBytes(var1, this.data, 120);
      }

      public void setStartBlock(int var1) {
         this.startBlock = var1;
         IntegerHelper.getFourBytes(var1, this.data, 116);
      }

      public void setType(int var1) {
         this.type = var1;
         this.data[66] = (byte)var1;
      }
   }
}
