package org.apache.log4j.lf5.viewer.categoryexplorer;

import java.util.Enumeration;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;

public class CategoryNode extends DefaultMutableTreeNode {
   protected boolean _hasFatalChildren = false;
   protected boolean _hasFatalRecords = false;
   protected int _numberOfContainedRecords = 0;
   protected int _numberOfRecordsFromChildren = 0;
   protected boolean _selected = true;

   public CategoryNode(String var1) {
      this.setUserObject(var1);
   }

   public void addRecord() {
      ++this._numberOfContainedRecords;
      this.addRecordToParent();
   }

   protected void addRecordFromChild() {
      ++this._numberOfRecordsFromChildren;
      this.addRecordToParent();
   }

   protected void addRecordToParent() {
      TreeNode var1 = this.getParent();
      if (var1 != null) {
         ((CategoryNode)var1).addRecordFromChild();
      }
   }

   public boolean equals(Object var1) {
      if (var1 instanceof CategoryNode) {
         CategoryNode var2 = (CategoryNode)var1;
         if (this.getTitle().toLowerCase().equals(var2.getTitle().toLowerCase())) {
            return true;
         }
      }

      return false;
   }

   public int getNumberOfContainedRecords() {
      return this._numberOfContainedRecords;
   }

   protected int getNumberOfRecordsFromChildren() {
      return this._numberOfRecordsFromChildren;
   }

   public String getTitle() {
      return (String)this.getUserObject();
   }

   protected int getTotalNumberOfRecords() {
      return this.getNumberOfRecordsFromChildren() + this.getNumberOfContainedRecords();
   }

   public boolean hasFatalChildren() {
      return this._hasFatalChildren;
   }

   public boolean hasFatalRecords() {
      return this._hasFatalRecords;
   }

   public int hashCode() {
      return this.getTitle().hashCode();
   }

   public boolean isSelected() {
      return this._selected;
   }

   public void resetNumberOfContainedRecords() {
      this._numberOfContainedRecords = 0;
      this._numberOfRecordsFromChildren = 0;
      this._hasFatalRecords = false;
      this._hasFatalChildren = false;
   }

   public void setAllDescendantsDeSelected() {
      Enumeration var1 = this.children();

      while(var1.hasMoreElements()) {
         CategoryNode var2 = (CategoryNode)var1.nextElement();
         var2.setSelected(false);
         var2.setAllDescendantsDeSelected();
      }

   }

   public void setAllDescendantsSelected() {
      Enumeration var2 = this.children();

      while(var2.hasMoreElements()) {
         CategoryNode var1 = (CategoryNode)var2.nextElement();
         var1.setSelected(true);
         var1.setAllDescendantsSelected();
      }

   }

   public void setHasFatalChildren(boolean var1) {
      this._hasFatalChildren = var1;
   }

   public void setHasFatalRecords(boolean var1) {
      this._hasFatalRecords = var1;
   }

   public void setSelected(boolean var1) {
      if (var1 != this._selected) {
         this._selected = var1;
      }

   }

   public String toString() {
      return this.getTitle();
   }
}
