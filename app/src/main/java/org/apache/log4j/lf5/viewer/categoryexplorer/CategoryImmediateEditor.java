package org.apache.log4j.lf5.viewer.categoryexplorer;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.util.EventObject;
import javax.swing.Icon;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellEditor;
import javax.swing.tree.TreePath;

public class CategoryImmediateEditor extends DefaultTreeCellEditor {
   protected Icon editingIcon = null;
   private CategoryNodeRenderer renderer;

   public CategoryImmediateEditor(JTree var1, CategoryNodeRenderer var2, CategoryNodeEditor var3) {
      super(var1, var2, var3);
      this.renderer = var2;
      var2.setIcon((Icon)null);
      var2.setLeafIcon((Icon)null);
      var2.setOpenIcon((Icon)null);
      var2.setClosedIcon((Icon)null);
      super.editingIcon = null;
   }

   protected boolean canEditImmediately(EventObject var1) {
      boolean var2;
      if (var1 instanceof MouseEvent) {
         var2 = this.inCheckBoxHitRegion((MouseEvent)var1);
      } else {
         var2 = false;
      }

      return var2;
   }

   protected void determineOffset(JTree var1, Object var2, boolean var3, boolean var4, boolean var5, int var6) {
      super.offset = 0;
   }

   public boolean inCheckBoxHitRegion(MouseEvent var1) {
      TreePath var2 = super.tree.getPathForLocation(var1.getX(), var1.getY());
      if (var2 == null) {
         return false;
      } else {
         CategoryNode var4 = (CategoryNode)var2.getLastPathComponent();
         Rectangle var3 = super.tree.getRowBounds(super.lastRow);
         Dimension var5 = this.renderer.getCheckBoxOffset();
         var3.translate(super.offset + var5.width, var5.height);
         var3.contains(var1.getPoint());
         return true;
      }
   }

   public boolean shouldSelectCell(EventObject var1) {
      boolean var2;
      if (var1 instanceof MouseEvent) {
         MouseEvent var3 = (MouseEvent)var1;
         var2 = ((CategoryNode)super.tree.getPathForLocation(var3.getX(), var3.getY()).getLastPathComponent()).isLeaf();
      } else {
         var2 = false;
      }

      return var2;
   }
}
