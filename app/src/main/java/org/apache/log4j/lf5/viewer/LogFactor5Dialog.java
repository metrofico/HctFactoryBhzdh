package org.apache.log4j.lf5.viewer;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.Toolkit;
import java.awt.Window;
import javax.swing.JDialog;
import javax.swing.JFrame;

public abstract class LogFactor5Dialog extends JDialog {
   protected static final Font DISPLAY_FONT = new Font("Arial", 1, 12);

   protected LogFactor5Dialog(JFrame var1, String var2, boolean var3) {
      super(var1, var2, var3);
   }

   protected void centerWindow(Window var1) {
      Dimension var2 = Toolkit.getDefaultToolkit().getScreenSize();
      if (var2.width < var1.getSize().width) {
         var1.setSize(var2.width, var1.getSize().height);
      }

      if (var2.height < var1.getSize().height) {
         var1.setSize(var1.getSize().width, var2.height);
      }

      var1.setLocation((var2.width - var1.getSize().width) / 2, (var2.height - var1.getSize().height) / 2);
   }

   protected GridBagConstraints getDefaultConstraints() {
      GridBagConstraints var1 = new GridBagConstraints();
      var1.weightx = 1.0;
      var1.weighty = 1.0;
      var1.gridheight = 1;
      var1.insets = new Insets(4, 4, 4, 4);
      var1.fill = 0;
      var1.anchor = 17;
      return var1;
   }

   protected void minimumSizeDialog(Component var1, int var2, int var3) {
      if (var1.getSize().width < var2) {
         var1.setSize(var2, var1.getSize().height);
      }

      if (var1.getSize().height < var3) {
         var1.setSize(var1.getSize().width, var3);
      }

   }

   public void show() {
      this.pack();
      this.minimumSizeDialog(this, 200, 100);
      this.centerWindow(this);
      super.show();
   }

   protected void wrapStringOnPanel(String var1, Container var2) {
      GridBagConstraints var6 = this.getDefaultConstraints();
      var6.gridwidth = 0;
      var6.insets = new Insets(0, 0, 0, 0);
      GridBagLayout var7 = (GridBagLayout)var2.getLayout();

      while(var1.length() > 0) {
         int var3 = var1.indexOf(10);
         String var4;
         if (var3 >= 0) {
            var4 = var1.substring(0, var3);
            var1 = var1.substring(var3 + 1);
         } else {
            String var5 = "";
            var4 = var1;
            var1 = var5;
         }

         Label var8 = new Label(var4);
         var8.setFont(DISPLAY_FONT);
         var7.setConstraints(var8, var6);
         var2.add(var8);
      }

   }
}
