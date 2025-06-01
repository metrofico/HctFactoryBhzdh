package org.apache.log4j.lf5.viewer;

import java.awt.Font;
import java.awt.Graphics;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import org.apache.log4j.lf5.util.DateFormatManager;

public class LogTable extends JTable {
   protected int _colCategory;
   protected int _colDate;
   protected int _colLevel;
   protected int _colLocation;
   protected int _colMessage;
   protected int _colMessageNum;
   protected int _colNDC;
   protected LogTableColumn[] _colNames = LogTableColumn.getLogTableColumnArray();
   protected int _colThread;
   protected int _colThrown;
   protected int[] _colWidths = new int[]{40, 40, 40, 70, 70, 360, 440, 200, 60};
   protected DateFormatManager _dateFormatManager;
   protected JTextArea _detailTextArea;
   protected int _numCols = 9;
   protected int _rowHeight = 30;
   protected TableColumn[] _tableColumns = new TableColumn[9];

   public LogTable(JTextArea var1) {
      int var2 = 0;
      this._colDate = 0;
      this._colThread = 1;
      this._colMessageNum = 2;
      this._colLevel = 3;
      this._colNDC = 4;
      this._colCategory = 5;
      this._colMessage = 6;
      this._colLocation = 7;
      this._colThrown = 8;
      this._dateFormatManager = null;
      this.init();
      this._detailTextArea = var1;
      this.setModel(new FilteredLogTableModel());

      for(Enumeration var3 = this.getColumnModel().getColumns(); var3.hasMoreElements(); ++var2) {
         TableColumn var4 = (TableColumn)var3.nextElement();
         var4.setCellRenderer(new LogTableRowRenderer());
         var4.setPreferredWidth(this._colWidths[var2]);
         this._tableColumns[var2] = var4;
      }

      this.getSelectionModel().addListSelectionListener(new LogTableListSelectionListener(this));
   }

   public void clearLogRecords() {
      synchronized(this){}

      try {
         this.getFilteredLogTableModel().clear();
      } finally {
         ;
      }

   }

   protected Vector getColumnNameAndNumber() {
      Vector var3 = new Vector();
      int var1 = 0;

      while(true) {
         LogTableColumn[] var2 = this._colNames;
         if (var1 >= var2.length) {
            return var3;
         }

         var3.add(var1, var2[var1]);
         ++var1;
      }
   }

   public DateFormatManager getDateFormatManager() {
      return this._dateFormatManager;
   }

   public FilteredLogTableModel getFilteredLogTableModel() {
      return (FilteredLogTableModel)this.getModel();
   }

   protected void init() {
      this.setRowHeight(this._rowHeight);
      this.setSelectionMode(0);
   }

   public void setDateFormatManager(DateFormatManager var1) {
      this._dateFormatManager = var1;
   }

   public void setDetailedView() {
      TableColumnModel var3 = this.getColumnModel();
      byte var2 = 0;

      int var1;
      for(var1 = 0; var1 < this._numCols; ++var1) {
         var3.removeColumn(this._tableColumns[var1]);
      }

      for(var1 = var2; var1 < this._numCols; ++var1) {
         var3.addColumn(this._tableColumns[var1]);
      }

      this.sizeColumnsToFit(-1);
   }

   public void setFont(Font var1) {
      super.setFont(var1);
      Graphics var3 = this.getGraphics();
      if (var3 != null) {
         int var2 = var3.getFontMetrics(var1).getHeight();
         var2 += var2 / 3;
         this._rowHeight = var2;
         this.setRowHeight(var2);
      }

   }

   public void setView(List var1) {
      TableColumnModel var3 = this.getColumnModel();

      for(int var2 = 0; var2 < this._numCols; ++var2) {
         var3.removeColumn(this._tableColumns[var2]);
      }

      Iterator var4 = var1.iterator();
      Vector var5 = this.getColumnNameAndNumber();

      while(var4.hasNext()) {
         var3.addColumn(this._tableColumns[var5.indexOf(var4.next())]);
      }

      this.sizeColumnsToFit(-1);
   }

   class LogTableListSelectionListener implements ListSelectionListener {
      protected JTable _table;

      public LogTableListSelectionListener(JTable var2) {
         this._table = var2;
      }

      public void valueChanged(ListSelectionEvent var1) {
         if (!var1.getValueIsAdjusting()) {
            ListSelectionModel var5 = (ListSelectionModel)var1.getSource();
            if (!var5.isSelectionEmpty()) {
               StringBuffer var4 = new StringBuffer();
               int var3 = var5.getMinSelectionIndex();

               Object var6;
               for(int var2 = 0; var2 < LogTable.this._numCols - 1; ++var2) {
                  var6 = this._table.getModel().getValueAt(var3, var2);
                  String var7;
                  if (var6 != null) {
                     var7 = var6.toString();
                  } else {
                     var7 = "";
                  }

                  var4.append(LogTable.this._colNames[var2] + ":");
                  var4.append("\t");
                  if (var2 == LogTable.this._colThread || var2 == LogTable.this._colMessage || var2 == LogTable.this._colLevel) {
                     var4.append("\t");
                  }

                  if (var2 == LogTable.this._colDate || var2 == LogTable.this._colNDC) {
                     var4.append("\t\t");
                  }

                  var4.append(var7);
                  var4.append("\n");
               }

               var4.append(LogTable.this._colNames[LogTable.this._numCols - 1] + ":\n");
               var6 = this._table.getModel().getValueAt(var3, LogTable.this._numCols - 1);
               if (var6 != null) {
                  var4.append(var6.toString());
               }

               LogTable.this._detailTextArea.setText(var4.toString());
            }

         }
      }
   }
}
