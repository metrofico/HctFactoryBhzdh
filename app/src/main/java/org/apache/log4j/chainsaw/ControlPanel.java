package org.apache.log4j.chainsaw;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

class ControlPanel extends JPanel {
   private static final Logger LOG;
   static Class class$org$apache$log4j$chainsaw$ControlPanel;

   static {
      Class var1 = class$org$apache$log4j$chainsaw$ControlPanel;
      Class var0 = var1;
      if (var1 == null) {
         var0 = class$("org.apache.log4j.chainsaw.ControlPanel");
         class$org$apache$log4j$chainsaw$ControlPanel = var0;
      }

      LOG = Logger.getLogger(var0);
   }

   ControlPanel(MyTableModel var1) {
      this.setBorder(BorderFactory.createTitledBorder("Controls: "));
      GridBagLayout var3 = new GridBagLayout();
      GridBagConstraints var2 = new GridBagConstraints();
      this.setLayout(var3);
      var2.ipadx = 5;
      var2.ipady = 5;
      var2.gridx = 0;
      var2.anchor = 13;
      var2.gridy = 0;
      JLabel var4 = new JLabel("Filter Level:");
      var3.setConstraints(var4, var2);
      this.add(var4);
      ++var2.gridy;
      var4 = new JLabel("Filter Thread:");
      var3.setConstraints(var4, var2);
      this.add(var4);
      ++var2.gridy;
      var4 = new JLabel("Filter Logger:");
      var3.setConstraints(var4, var2);
      this.add(var4);
      ++var2.gridy;
      var4 = new JLabel("Filter NDC:");
      var3.setConstraints(var4, var2);
      this.add(var4);
      ++var2.gridy;
      var4 = new JLabel("Filter Message:");
      var3.setConstraints(var4, var2);
      this.add(var4);
      var2.weightx = 1.0;
      var2.gridx = 1;
      var2.anchor = 17;
      var2.gridy = 0;
      Level[] var5 = new Level[]{Level.FATAL, Level.ERROR, Level.WARN, Level.INFO, Level.DEBUG, Level.TRACE};
      JComboBox var7 = new JComboBox(var5);
      Level var6 = var5[5];
      var7.setSelectedItem(var6);
      var1.setPriorityFilter(var6);
      var3.setConstraints(var7, var2);
      this.add(var7);
      var7.setEditable(false);
      var7.addActionListener(new ControlPanel$1(this, var1, var7));
      var2.fill = 2;
      ++var2.gridy;
      JTextField var8 = new JTextField("");
      var8.getDocument().addDocumentListener(new ControlPanel$2(this, var1, var8));
      var3.setConstraints(var8, var2);
      this.add(var8);
      ++var2.gridy;
      var8 = new JTextField("");
      var8.getDocument().addDocumentListener(new ControlPanel$3(this, var1, var8));
      var3.setConstraints(var8, var2);
      this.add(var8);
      ++var2.gridy;
      var8 = new JTextField("");
      var8.getDocument().addDocumentListener(new ControlPanel$4(this, var1, var8));
      var3.setConstraints(var8, var2);
      this.add(var8);
      ++var2.gridy;
      var8 = new JTextField("");
      var8.getDocument().addDocumentListener(new ControlPanel$5(this, var1, var8));
      var3.setConstraints(var8, var2);
      this.add(var8);
      var2.weightx = 0.0;
      var2.fill = 2;
      var2.anchor = 13;
      var2.gridx = 2;
      var2.gridy = 0;
      JButton var9 = new JButton("Exit");
      var9.setMnemonic('x');
      var9.addActionListener(ExitAction.INSTANCE);
      var3.setConstraints(var9, var2);
      this.add(var9);
      ++var2.gridy;
      var9 = new JButton("Clear");
      var9.setMnemonic('c');
      var9.addActionListener(new ControlPanel$6(this, var1));
      var3.setConstraints(var9, var2);
      this.add(var9);
      ++var2.gridy;
      var9 = new JButton("Pause");
      var9.setMnemonic('p');
      var9.addActionListener(new ControlPanel$7(this, var1, var9));
      var3.setConstraints(var9, var2);
      this.add(var9);
   }

   // $FF: synthetic method
   static Class class$(String var0) {
      try {
         Class var2 = Class.forName(var0);
         return var2;
      } catch (ClassNotFoundException var1) {
         throw new NoClassDefFoundError(var1.getMessage());
      }
   }
}
