package org.apache.log4j.lf5.viewer;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LogTableColumn implements Serializable {
   public static final LogTableColumn CATEGORY;
   public static final LogTableColumn DATE;
   public static final LogTableColumn LEVEL;
   public static final LogTableColumn LOCATION;
   public static final LogTableColumn MESSAGE;
   public static final LogTableColumn MESSAGE_NUM;
   public static final LogTableColumn NDC;
   public static final LogTableColumn THREAD;
   public static final LogTableColumn THROWN;
   private static LogTableColumn[] _log4JColumns;
   private static Map _logTableColumnMap;
   protected String _label;

   static {
      LogTableColumn var3 = new LogTableColumn("Date");
      DATE = var3;
      LogTableColumn var8 = new LogTableColumn("Thread");
      THREAD = var8;
      LogTableColumn var5 = new LogTableColumn("Message #");
      MESSAGE_NUM = var5;
      LogTableColumn var4 = new LogTableColumn("Level");
      LEVEL = var4;
      LogTableColumn var9 = new LogTableColumn("NDC");
      NDC = var9;
      LogTableColumn var2 = new LogTableColumn("Category");
      CATEGORY = var2;
      LogTableColumn var6 = new LogTableColumn("Message");
      MESSAGE = var6;
      LogTableColumn var1 = new LogTableColumn("Location");
      LOCATION = var1;
      LogTableColumn var7 = new LogTableColumn("Thrown");
      THROWN = var7;
      int var0 = 0;
      _log4JColumns = new LogTableColumn[]{var3, var8, var5, var4, var9, var2, var6, var1, var7};
      _logTableColumnMap = new HashMap();

      while(true) {
         LogTableColumn[] var10 = _log4JColumns;
         if (var0 >= var10.length) {
            return;
         }

         _logTableColumnMap.put(var10[var0].getLabel(), _log4JColumns[var0]);
         ++var0;
      }
   }

   public LogTableColumn(String var1) {
      this._label = var1;
   }

   public static LogTableColumn[] getLogTableColumnArray() {
      return _log4JColumns;
   }

   public static List getLogTableColumns() {
      return Arrays.asList(_log4JColumns);
   }

   public static LogTableColumn valueOf(String var0) throws LogTableColumnFormatException {
      LogTableColumn var1;
      if (var0 != null) {
         var0 = var0.trim();
         var1 = (LogTableColumn)_logTableColumnMap.get(var0);
      } else {
         var1 = null;
      }

      if (var1 != null) {
         return var1;
      } else {
         StringBuffer var2 = new StringBuffer();
         var2.append("Error while trying to parse (" + var0 + ") into");
         var2.append(" a LogTableColumn.");
         throw new LogTableColumnFormatException(var2.toString());
      }
   }

   public boolean equals(Object var1) {
      boolean var2;
      if (var1 instanceof LogTableColumn && this.getLabel() == ((LogTableColumn)var1).getLabel()) {
         var2 = true;
      } else {
         var2 = false;
      }

      return var2;
   }

   public String getLabel() {
      return this._label;
   }

   public int hashCode() {
      return this._label.hashCode();
   }

   public String toString() {
      return this._label;
   }
}
