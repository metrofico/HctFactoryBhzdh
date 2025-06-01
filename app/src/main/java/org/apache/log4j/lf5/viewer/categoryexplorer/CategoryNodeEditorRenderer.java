package org.apache.log4j.lf5.viewer.categoryexplorer;

import java.awt.Component;
import javax.swing.JCheckBox;
import javax.swing.JTree;

public class CategoryNodeEditorRenderer extends CategoryNodeRenderer {
   public JCheckBox getCheckBox() {
      return super._checkBox;
   }

   public Component getTreeCellRendererComponent(JTree var1, Object var2, boolean var3, boolean var4, boolean var5, int var6, boolean var7) {
      return super.getTreeCellRendererComponent(var1, var2, var3, var4, var5, var6, var7);
   }
}
