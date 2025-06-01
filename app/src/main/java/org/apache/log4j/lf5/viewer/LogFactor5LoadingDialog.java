package org.apache.log4j.lf5.viewer;

import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class LogFactor5LoadingDialog extends LogFactor5Dialog {
   public LogFactor5LoadingDialog(JFrame var1, String var2) {
      super(var1, "LogFactor5", false);
      JPanel var4 = new JPanel();
      var4.setLayout(new FlowLayout());
      JPanel var3 = new JPanel();
      var3.setLayout(new GridBagLayout());
      this.wrapStringOnPanel(var2, var3);
      this.getContentPane().add(var3, "Center");
      this.getContentPane().add(var4, "South");
      this.show();
   }
}
