package org.apache.log4j.lf5.viewer;

import java.awt.Adjustable;
import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.table.TableModel;

public class LF5SwingUtils {
   protected static boolean contains(int var0, TableModel var1) {
      if (var1 == null) {
         return false;
      } else if (var0 < 0) {
         return false;
      } else {
         return var0 < var1.getRowCount();
      }
   }

   public static void makeScrollBarTrack(Adjustable var0) {
      if (var0 != null) {
         var0.addAdjustmentListener(new TrackingAdjustmentListener());
      }
   }

   public static void makeVerticalScrollBarTrack(JScrollPane var0) {
      if (var0 != null) {
         makeScrollBarTrack(var0.getVerticalScrollBar());
      }
   }

   protected static void moveAdjustable(int var0, Adjustable var1) {
      if (var1 != null) {
         var1.setValue(var0);
      }
   }

   protected static void repaintLater(JComponent var0) {
      SwingUtilities.invokeLater(new LF5SwingUtils$1(var0));
   }

   public static void selectRow(int var0, JTable var1, JScrollPane var2) {
      if (var1 != null && var2 != null) {
         if (!contains(var0, var1.getModel())) {
            return;
         }

         moveAdjustable(var1.getRowHeight() * var0, var2.getVerticalScrollBar());
         selectRow(var0, var1.getSelectionModel());
         repaintLater(var1);
      }

   }

   protected static void selectRow(int var0, ListSelectionModel var1) {
      if (var1 != null) {
         var1.setSelectionInterval(var0, var0);
      }
   }
}
