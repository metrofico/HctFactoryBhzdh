package org.apache.log4j.lf5.viewer;

import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LogFactor5InputDialog extends LogFactor5Dialog {
   public static final int SIZE = 30;
   private JTextField _textField;

   public LogFactor5InputDialog(JFrame var1, String var2, String var3) {
      this(var1, var2, var3, 30);
   }

   public LogFactor5InputDialog(JFrame var1, String var2, String var3, int var4) {
      super(var1, var2, true);
      JPanel var7 = new JPanel();
      var7.setLayout(new FlowLayout());
      JPanel var6 = new JPanel();
      var6.setLayout(new FlowLayout());
      var6.add(new JLabel(var3));
      JTextField var8 = new JTextField(var4);
      this._textField = var8;
      var6.add(var8);
      this.addKeyListener(new LogFactor5InputDialog$1(this));
      JButton var9 = new JButton("Ok");
      var9.addActionListener(new LogFactor5InputDialog$2(this));
      JButton var5 = new JButton("Cancel");
      var5.addActionListener(new LogFactor5InputDialog$3(this));
      var7.add(var9);
      var7.add(var5);
      this.getContentPane().add(var6, "Center");
      this.getContentPane().add(var7, "South");
      this.pack();
      this.centerWindow(this);
      this.show();
   }

   // $FF: synthetic method
   static JTextField access$000(LogFactor5InputDialog var0) {
      return var0._textField;
   }

   public String getText() {
      String var2 = this._textField.getText();
      String var1 = var2;
      if (var2 != null) {
         var1 = var2;
         if (var2.trim().length() == 0) {
            var1 = null;
         }
      }

      return var1;
   }
}
