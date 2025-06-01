package org.apache.log4j.lf5.viewer;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import org.apache.log4j.lf5.LogLevel;

public class LogTableRowRenderer extends DefaultTableCellRenderer {
   protected Color _color = new Color(230, 230, 230);
   protected boolean _highlightFatal = true;

   protected Color getLogLevelColor(LogLevel var1) {
      return (Color)LogLevel.getLogLevelColorMap().get(var1);
   }

   public Component getTableCellRendererComponent(JTable var1, Object var2, boolean var3, boolean var4, int var5, int var6) {
      if (var5 % 2 == 0) {
         this.setBackground(this._color);
      } else {
         this.setBackground(Color.white);
      }

      this.setForeground(this.getLogLevelColor(((FilteredLogTableModel)var1.getModel()).getFilteredRecord(var5).getLevel()));
      return super.getTableCellRendererComponent(var1, var2, var3, var4, var5, var6);
   }
}
