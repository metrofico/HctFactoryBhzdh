package org.apache.log4j.lf5.viewer.categoryexplorer;

import java.awt.AWTEventMulticaster;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import javax.swing.SwingUtilities;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import org.apache.log4j.lf5.LogRecord;

public class CategoryExplorerModel extends DefaultTreeModel {
   protected ActionEvent _event = new ActionEvent(this, 1001, "Nodes Selection changed");
   protected ActionListener _listener = null;
   protected boolean _renderFatal = true;

   public CategoryExplorerModel(CategoryNode var1) {
      super(var1);
   }

   public void addActionListener(ActionListener var1) {
      synchronized(this){}

      try {
         this._listener = AWTEventMulticaster.add(this._listener, var1);
      } finally {
         ;
      }

   }

   public CategoryNode addCategory(CategoryPath var1) {
      CategoryNode var4 = (CategoryNode)this.getRoot();

      for(int var2 = 0; var2 < var1.size(); ++var2) {
         CategoryElement var6 = var1.categoryElementAt(var2);
         Enumeration var7 = var4.children();

         boolean var3;
         CategoryNode var5;
         label25: {
            while(var7.hasMoreElements()) {
               var5 = (CategoryNode)var7.nextElement();
               if (var5.getTitle().toLowerCase().equals(var6.getTitle().toLowerCase())) {
                  var3 = true;
                  var4 = var5;
                  break label25;
               }
            }

            var3 = false;
         }

         if (!var3) {
            var5 = new CategoryNode(var6.getTitle());
            this.insertNodeInto(var5, var4, var4.getChildCount());
            this.refresh(var5);
            var4 = var5;
         }
      }

      return var4;
   }

   public void addLogRecord(LogRecord var1) {
      CategoryPath var4 = new CategoryPath(var1.getCategory());
      this.addCategory(var4);
      CategoryNode var7 = this.getCategoryNode(var4);
      var7.addRecord();
      if (this._renderFatal && var1.isFatal()) {
         TreeNode[] var5 = this.getPathToRoot(var7);
         int var3 = var5.length;

         for(int var2 = 1; var2 < var3 - 1; ++var2) {
            CategoryNode var6 = (CategoryNode)var5[var2];
            var6.setHasFatalChildren(true);
            this.nodeChanged(var6);
         }

         var7.setHasFatalRecords(true);
         this.nodeChanged(var7);
      }

   }

   public CategoryNode getCategoryNode(String var1) {
      return this.getCategoryNode(new CategoryPath(var1));
   }

   public CategoryNode getCategoryNode(CategoryPath var1) {
      CategoryNode var4 = (CategoryNode)this.getRoot();

      for(int var2 = 0; var2 < var1.size(); ++var2) {
         CategoryElement var6 = var1.categoryElementAt(var2);
         Enumeration var7 = var4.children();

         boolean var3;
         label25: {
            while(var7.hasMoreElements()) {
               CategoryNode var5 = (CategoryNode)var7.nextElement();
               if (var5.getTitle().toLowerCase().equals(var6.getTitle().toLowerCase())) {
                  var3 = true;
                  var4 = var5;
                  break label25;
               }
            }

            var3 = false;
         }

         if (!var3) {
            return null;
         }
      }

      return var4;
   }

   public CategoryNode getRootCategoryNode() {
      return (CategoryNode)this.getRoot();
   }

   public TreePath getTreePathToRoot(CategoryNode var1) {
      return var1 == null ? null : new TreePath(this.getPathToRoot(var1));
   }

   public boolean isCategoryPathActive(CategoryPath var1) {
      CategoryNode var6 = (CategoryNode)this.getRoot();
      int var2 = 0;

      boolean var4;
      CategoryNode var5;
      for(var4 = false; var2 < var1.size(); var6 = var5) {
         CategoryElement var8 = var1.categoryElementAt(var2);
         Enumeration var7 = var6.children();

         boolean var3;
         while(true) {
            var4 = var7.hasMoreElements();
            var3 = true;
            if (!var4) {
               var4 = false;
               var3 = false;
               var5 = var6;
               break;
            }

            var5 = (CategoryNode)var7.nextElement();
            if (var5.getTitle().toLowerCase().equals(var8.getTitle().toLowerCase())) {
               if (var5.isSelected()) {
                  var4 = true;
               } else {
                  var4 = false;
               }
               break;
            }
         }

         if (!var4 || !var3) {
            return false;
         }

         ++var2;
      }

      return var4;
   }

   protected void notifyActionListeners() {
      ActionListener var1 = this._listener;
      if (var1 != null) {
         var1.actionPerformed(this._event);
      }

   }

   protected void refresh(CategoryNode var1) {
      SwingUtilities.invokeLater(new CategoryExplorerModel$1(this, var1));
   }

   public void removeActionListener(ActionListener var1) {
      synchronized(this){}

      try {
         this._listener = AWTEventMulticaster.remove(this._listener, var1);
      } finally {
         ;
      }

   }

   public void resetAllNodeCounts() {
      Enumeration var1 = this.getRootCategoryNode().depthFirstEnumeration();

      while(var1.hasMoreElements()) {
         CategoryNode var2 = (CategoryNode)var1.nextElement();
         var2.resetNumberOfContainedRecords();
         this.nodeChanged(var2);
      }

   }

   public void setDescendantSelection(CategoryNode var1, boolean var2) {
      Enumeration var4 = var1.depthFirstEnumeration();

      while(var4.hasMoreElements()) {
         CategoryNode var3 = (CategoryNode)var4.nextElement();
         if (var3.isSelected() != var2) {
            var3.setSelected(var2);
            this.nodeChanged(var3);
         }
      }

      this.notifyActionListeners();
   }

   public void setParentSelection(CategoryNode var1, boolean var2) {
      TreeNode[] var6 = this.getPathToRoot(var1);
      int var4 = var6.length;

      for(int var3 = 1; var3 < var4; ++var3) {
         CategoryNode var5 = (CategoryNode)var6[var3];
         if (var5.isSelected() != var2) {
            var5.setSelected(var2);
            this.nodeChanged(var5);
         }
      }

      this.notifyActionListeners();
   }

   public void update(CategoryNode var1, boolean var2) {
      if (var1.isSelected() != var2) {
         if (var2) {
            this.setParentSelection(var1, true);
         } else {
            this.setDescendantSelection(var1, false);
         }

      }
   }
}
