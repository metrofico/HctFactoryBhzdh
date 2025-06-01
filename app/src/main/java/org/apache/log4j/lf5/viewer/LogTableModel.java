package org.apache.log4j.lf5.viewer;

import javax.swing.table.DefaultTableModel;

public class LogTableModel extends DefaultTableModel {
   public LogTableModel(Object[] var1, int var2) {
      super(var1, var2);
   }

   public boolean isCellEditable(int var1, int var2) {
      return false;
   }
}
