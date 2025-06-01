package org.apache.log4j.lf5.viewer.categoryexplorer;

import java.awt.event.MouseEvent;
import javax.swing.JTree;
import javax.swing.tree.TreePath;

public class CategoryExplorerTree extends JTree {
   protected CategoryExplorerModel _model;
   protected boolean _rootAlreadyExpanded = false;

   public CategoryExplorerTree() {
      CategoryExplorerModel var1 = new CategoryExplorerModel(new CategoryNode("Categories"));
      this._model = var1;
      this.setModel(var1);
      this.init();
   }

   public CategoryExplorerTree(CategoryExplorerModel var1) {
      super(var1);
      this._model = var1;
      this.init();
   }

   protected void ensureRootExpansion() {
      this._model.addTreeModelListener(new CategoryExplorerTree$1(this));
   }

   protected void expandRootNode() {
      if (!this._rootAlreadyExpanded) {
         this._rootAlreadyExpanded = true;
         this.expandPath(new TreePath(this._model.getRootCategoryNode().getPath()));
      }
   }

   public CategoryExplorerModel getExplorerModel() {
      return this._model;
   }

   public String getToolTipText(MouseEvent var1) {
      try {
         String var3 = super.getToolTipText(var1);
         return var3;
      } catch (Exception var2) {
         return "";
      }
   }

   protected void init() {
      this.putClientProperty("JTree.lineStyle", "Angled");
      CategoryNodeRenderer var1 = new CategoryNodeRenderer();
      this.setEditable(true);
      this.setCellRenderer(var1);
      CategoryNodeEditor var2 = new CategoryNodeEditor(this._model);
      this.setCellEditor(new CategoryImmediateEditor(this, new CategoryNodeRenderer(), var2));
      this.setShowsRootHandles(true);
      this.setToolTipText("");
      this.ensureRootExpansion();
   }
}
