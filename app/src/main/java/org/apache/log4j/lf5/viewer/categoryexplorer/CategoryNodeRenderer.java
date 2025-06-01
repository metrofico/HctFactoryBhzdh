package org.apache.log4j.lf5.viewer.categoryexplorer;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.tree.DefaultTreeCellRenderer;

public class CategoryNodeRenderer extends DefaultTreeCellRenderer {
   public static final Color FATAL_CHILDREN = new Color(189, 113, 0);
   protected static ImageIcon _sat = null;
   protected JCheckBox _checkBox = new JCheckBox();
   protected JPanel _panel;

   public CategoryNodeRenderer() {
      JPanel var1 = new JPanel();
      this._panel = var1;
      var1.setBackground(UIManager.getColor("Tree.textBackground"));
      if (_sat == null) {
         _sat = new ImageIcon(this.getClass().getResource("/org/apache/log4j/lf5/viewer/images/channelexplorer_satellite.gif"));
      }

      this.setOpaque(false);
      this._checkBox.setOpaque(false);
      this._panel.setOpaque(false);
      this._panel.setLayout(new FlowLayout(0, 0, 0));
      this._panel.add(this._checkBox);
      this._panel.add(this);
      this.setOpenIcon(_sat);
      this.setClosedIcon(_sat);
      this.setLeafIcon(_sat);
   }

   protected String buildToolTip(CategoryNode var1) {
      StringBuffer var2 = new StringBuffer();
      var2.append(var1.getTitle()).append(" contains a total of ");
      var2.append(var1.getTotalNumberOfRecords());
      var2.append(" LogRecords.");
      var2.append(" Right-click for more info.");
      return var2.toString();
   }

   public Dimension getCheckBoxOffset() {
      return new Dimension(0, 0);
   }

   public Component getTreeCellRendererComponent(JTree var1, Object var2, boolean var3, boolean var4, boolean var5, int var6, boolean var7) {
      CategoryNode var8 = (CategoryNode)var2;
      super.getTreeCellRendererComponent(var1, var2, var3, var4, var5, var6, var7);
      if (var6 == 0) {
         this._checkBox.setVisible(false);
      } else {
         this._checkBox.setVisible(true);
         this._checkBox.setSelected(var8.isSelected());
      }

      String var9 = this.buildToolTip(var8);
      this._panel.setToolTipText(var9);
      if (var8.hasFatalChildren()) {
         this.setForeground(FATAL_CHILDREN);
      }

      if (var8.hasFatalRecords()) {
         this.setForeground(Color.red);
      }

      return this._panel;
   }
}
