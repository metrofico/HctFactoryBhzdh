package jxl.biff.drawing;

import jxl.biff.IntegerHelper;
import jxl.biff.Type;
import jxl.biff.WritableRecordData;
import jxl.common.Assert;
import jxl.common.Logger;
import jxl.read.biff.Record;

public class ObjRecord extends WritableRecordData {
   public static final ObjType ARC = new ObjType(4, "Arc");
   public static final ObjType BUTTON = new ObjType(7, "Button");
   public static final ObjType CHART = new ObjType(5, "Chart");
   public static final ObjType CHECKBOX = new ObjType(11, "Checkbox");
   private static final int CLIPBOARD_FORMAT_LENGTH = 6;
   public static final ObjType COMBOBOX = new ObjType(20, "Combo Box");
   private static final int COMBOBOX_STRUCTURE_LENGTH = 44;
   private static final int COMMON_DATA_LENGTH = 22;
   public static final ObjType DIALOGUEBOX = new ObjType(15, "Dialogue Box");
   public static final ObjType EDITBOX = new ObjType(13, "Edit Box");
   private static final int END_LENGTH = 4;
   public static final ObjType EXCELNOTE = new ObjType(25, "Excel Note");
   public static final ObjType FORMCONTROL = new ObjType(20, "Form Combo Box");
   public static final ObjType GROUP = new ObjType(0, "Group");
   public static final ObjType GROUPBOX = new ObjType(19, "Group Box");
   public static final ObjType LABEL = new ObjType(14, "Label");
   public static final ObjType LINE = new ObjType(1, "Line");
   public static final ObjType LISTBOX = new ObjType(18, "List Box");
   public static final ObjType MSOFFICEDRAWING = new ObjType(30, "MS Office Drawing");
   private static final int NOTE_STRUCTURE_LENGTH = 26;
   public static final ObjType OPTION = new ObjType(12, "Option");
   public static final ObjType OVAL = new ObjType(3, "Oval");
   public static final ObjType PICTURE = new ObjType(8, "Picture");
   private static final int PICTURE_OPTION_LENGTH = 6;
   public static final ObjType POLYGON = new ObjType(9, "Polygon");
   public static final ObjType RECTANGLE = new ObjType(2, "Rectangle");
   public static final ObjType SCROLLBAR = new ObjType(17, "Scrollbar");
   public static final ObjType SPINBOX = new ObjType(16, "Spin Box");
   public static final ObjType TEXT = new ObjType(6, "Text");
   public static final ObjType UNKNOWN = new ObjType(255, "Unknown");
   private static final Logger logger = Logger.getLogger(ObjRecord.class);
   private int objectId;
   private boolean read;
   private ObjType type;

   ObjRecord(int var1, ObjType var2) {
      super(Type.OBJ);
      this.objectId = var1;
      this.type = var2;
   }

   public ObjRecord(Record var1) {
      super(var1);
      byte[] var3 = var1.getData();
      int var2 = IntegerHelper.getInt(var3[4], var3[5]);
      this.read = true;
      ObjType var4 = ObjType.getType(var2);
      this.type = var4;
      if (var4 == UNKNOWN) {
         logger.warn("unknown object type code " + var2);
      }

      this.objectId = IntegerHelper.getInt(var3[6], var3[7]);
   }

   private byte[] getComboBoxData() {
      byte[] var1 = new byte[70];
      IntegerHelper.getTwoBytes(21, var1, 0);
      IntegerHelper.getTwoBytes(18, var1, 2);
      IntegerHelper.getTwoBytes(this.type.value, var1, 4);
      IntegerHelper.getTwoBytes(this.objectId, var1, 6);
      IntegerHelper.getTwoBytes(0, var1, 8);
      IntegerHelper.getTwoBytes(12, var1, 22);
      IntegerHelper.getTwoBytes(20, var1, 24);
      var1[36] = 1;
      var1[38] = 4;
      var1[42] = 16;
      var1[46] = 19;
      var1[48] = -18;
      var1[49] = 31;
      var1[52] = 4;
      var1[56] = 1;
      var1[57] = 6;
      var1[60] = 2;
      var1[62] = 8;
      var1[64] = 64;
      IntegerHelper.getTwoBytes(0, var1, 66);
      IntegerHelper.getTwoBytes(0, var1, 68);
      return var1;
   }

   private byte[] getNoteData() {
      byte[] var1 = new byte[52];
      IntegerHelper.getTwoBytes(21, var1, 0);
      IntegerHelper.getTwoBytes(18, var1, 2);
      IntegerHelper.getTwoBytes(this.type.value, var1, 4);
      IntegerHelper.getTwoBytes(this.objectId, var1, 6);
      IntegerHelper.getTwoBytes(16401, var1, 8);
      IntegerHelper.getTwoBytes(13, var1, 22);
      IntegerHelper.getTwoBytes(22, var1, 24);
      IntegerHelper.getTwoBytes(0, var1, 48);
      IntegerHelper.getTwoBytes(0, var1, 50);
      return var1;
   }

   private byte[] getPictureData() {
      byte[] var1 = new byte[38];
      IntegerHelper.getTwoBytes(21, var1, 0);
      IntegerHelper.getTwoBytes(18, var1, 2);
      IntegerHelper.getTwoBytes(this.type.value, var1, 4);
      IntegerHelper.getTwoBytes(this.objectId, var1, 6);
      IntegerHelper.getTwoBytes(24593, var1, 8);
      IntegerHelper.getTwoBytes(7, var1, 22);
      IntegerHelper.getTwoBytes(2, var1, 24);
      IntegerHelper.getTwoBytes(65535, var1, 26);
      IntegerHelper.getTwoBytes(8, var1, 28);
      IntegerHelper.getTwoBytes(2, var1, 30);
      IntegerHelper.getTwoBytes(1, var1, 32);
      IntegerHelper.getTwoBytes(0, var1, 34);
      IntegerHelper.getTwoBytes(0, var1, 36);
      return var1;
   }

   public byte[] getData() {
      if (this.read) {
         return this.getRecord().getData();
      } else {
         ObjType var1 = this.type;
         if (var1 != PICTURE && var1 != CHART) {
            if (var1 == EXCELNOTE) {
               return this.getNoteData();
            } else if (var1 == COMBOBOX) {
               return this.getComboBoxData();
            } else {
               Assert.verify(false);
               return null;
            }
         } else {
            return this.getPictureData();
         }
      }
   }

   public int getObjectId() {
      return this.objectId;
   }

   public Record getRecord() {
      return super.getRecord();
   }

   public ObjType getType() {
      return this.type;
   }

   private static final class ObjType {
      private static ObjType[] types = new ObjType[0];
      public String desc;
      public int value;

      ObjType(int var1, String var2) {
         this.value = var1;
         this.desc = var2;
         ObjType[] var4 = types;
         ObjType[] var3 = new ObjType[var4.length + 1];
         types = var3;
         System.arraycopy(var4, 0, var3, 0, var4.length);
         types[var4.length] = this;
      }

      public static ObjType getType(int var0) {
         ObjType var2 = ObjRecord.UNKNOWN;

         for(int var1 = 0; var1 < types.length && var2 == ObjRecord.UNKNOWN; ++var1) {
            ObjType var3 = types[var1];
            if (var3.value == var0) {
               var2 = var3;
            }
         }

         return var2;
      }

      public String toString() {
         return this.desc;
      }
   }
}
