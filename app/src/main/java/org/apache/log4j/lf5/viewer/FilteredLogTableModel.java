package org.apache.log4j.lf5.viewer;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import org.apache.log4j.lf5.LogRecord;
import org.apache.log4j.lf5.LogRecordFilter;
import org.apache.log4j.lf5.PassingLogRecordFilter;

public class FilteredLogTableModel extends AbstractTableModel {
   protected List _allRecords = new ArrayList();
   protected String[] _colNames = new String[]{"Date", "Thread", "Message #", "Level", "NDC", "Category", "Message", "Location", "Thrown"};
   protected LogRecordFilter _filter = new PassingLogRecordFilter();
   protected List _filteredRecords;
   protected int _maxNumberOfLogRecords = 5000;

   private int numberOfRecordsToTrim() {
      return this._allRecords.size() - this._maxNumberOfLogRecords;
   }

   public boolean addLogRecord(LogRecord var1) {
      synchronized(this){}

      Throwable var10000;
      label78: {
         boolean var10001;
         boolean var2;
         try {
            this._allRecords.add(var1);
            var2 = this._filter.passes(var1);
         } catch (Throwable var8) {
            var10000 = var8;
            var10001 = false;
            break label78;
         }

         if (!var2) {
            return false;
         }

         try {
            this.getFilteredRecords().add(var1);
            this.fireTableRowsInserted(this.getRowCount(), this.getRowCount());
            this.trimRecords();
         } catch (Throwable var7) {
            var10000 = var7;
            var10001 = false;
            break label78;
         }

         return true;
      }

      Throwable var9 = var10000;
      throw var9;
   }

   public void clear() {
      synchronized(this){}

      try {
         this._allRecords.clear();
         this._filteredRecords.clear();
         this.fireTableDataChanged();
      } finally {
         ;
      }

   }

   protected List createFilteredRecordsList() {
      ArrayList var3 = new ArrayList();
      Iterator var1 = this._allRecords.iterator();

      while(var1.hasNext()) {
         LogRecord var2 = (LogRecord)var1.next();
         if (this._filter.passes(var2)) {
            var3.add(var2);
         }
      }

      return var3;
   }

   public void fastRefresh() {
      synchronized(this){}

      try {
         this._filteredRecords.remove(0);
         this.fireTableRowsDeleted(0, 0);
      } finally {
         ;
      }

   }

   protected Object getColumn(int var1, LogRecord var2) {
      if (var2 == null) {
         return "NULL Column";
      } else {
         String var3 = (new Date(var2.getMillis())).toString();
         switch (var1) {
            case 0:
               return var3 + " (" + var2.getMillis() + ")";
            case 1:
               return var2.getThreadDescription();
            case 2:
               return new Long(var2.getSequenceNumber());
            case 3:
               return var2.getLevel();
            case 4:
               return var2.getNDC();
            case 5:
               return var2.getCategory();
            case 6:
               return var2.getMessage();
            case 7:
               return var2.getLocation();
            case 8:
               return var2.getThrownStackTrace();
            default:
               throw new IllegalArgumentException("The column number " + var1 + "must be between 0 and 8");
         }
      }
   }

   public int getColumnCount() {
      return this._colNames.length;
   }

   public String getColumnName(int var1) {
      return this._colNames[var1];
   }

   protected LogRecord getFilteredRecord(int var1) {
      List var3 = this.getFilteredRecords();
      int var2 = var3.size();
      return var1 < var2 ? (LogRecord)var3.get(var1) : (LogRecord)var3.get(var2 - 1);
   }

   protected List getFilteredRecords() {
      if (this._filteredRecords == null) {
         this.refresh();
      }

      return this._filteredRecords;
   }

   public LogRecordFilter getLogRecordFilter() {
      return this._filter;
   }

   public int getRowCount() {
      return this.getFilteredRecords().size();
   }

   public int getTotalRowCount() {
      return this._allRecords.size();
   }

   public Object getValueAt(int var1, int var2) {
      return this.getColumn(var2, this.getFilteredRecord(var1));
   }

   protected boolean needsTrimming() {
      boolean var1;
      if (this._allRecords.size() > this._maxNumberOfLogRecords) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public void refresh() {
      synchronized(this){}

      try {
         this._filteredRecords = this.createFilteredRecordsList();
         this.fireTableDataChanged();
      } finally {
         ;
      }

   }

   public void setLogRecordFilter(LogRecordFilter var1) {
      this._filter = var1;
   }

   public void setMaxNumberOfLogRecords(int var1) {
      if (var1 > 0) {
         this._maxNumberOfLogRecords = var1;
      }

   }

   protected void trimOldestRecords() {
      List var2 = this._allRecords;
      synchronized(var2){}

      Throwable var10000;
      label161: {
         int var1;
         boolean var10001;
         try {
            var1 = this.numberOfRecordsToTrim();
         } catch (Throwable var23) {
            var10000 = var23;
            var10001 = false;
            break label161;
         }

         if (var1 > 1) {
            try {
               this._allRecords.subList(0, var1).clear();
               this.refresh();
            } catch (Throwable var22) {
               var10000 = var22;
               var10001 = false;
               break label161;
            }
         } else {
            try {
               this._allRecords.remove(0);
               this.fastRefresh();
            } catch (Throwable var21) {
               var10000 = var21;
               var10001 = false;
               break label161;
            }
         }

         label148:
         try {
            return;
         } catch (Throwable var20) {
            var10000 = var20;
            var10001 = false;
            break label148;
         }
      }

      Throwable var3 = var10000;
      throw var3;
   }

   protected void trimRecords() {
      if (this.needsTrimming()) {
         this.trimOldestRecords();
      }

   }
}
