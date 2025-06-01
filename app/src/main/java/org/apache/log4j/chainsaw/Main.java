package org.apache.log4j.chainsaw;

import java.awt.Dimension;
import java.io.IOException;
import java.util.Properties;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Main extends JFrame {
   private static final int DEFAULT_PORT = 4445;
   private static final Logger LOG;
   public static final String PORT_PROP_NAME = "chainsaw.port";
   static Class class$org$apache$log4j$chainsaw$Main;

   static {
      Class var1 = class$org$apache$log4j$chainsaw$Main;
      Class var0 = var1;
      if (var1 == null) {
         var0 = class$("org.apache.log4j.chainsaw.Main");
         class$org$apache$log4j$chainsaw$Main = var0;
      }

      LOG = Logger.getLogger(var0);
   }

   private Main() {
      super("CHAINSAW - Log4J Log Viewer");
      MyTableModel var1 = new MyTableModel();
      JMenuBar var3 = new JMenuBar();
      this.setJMenuBar(var3);
      JMenu var2 = new JMenu("File");
      var3.add(var2);

      try {
         LoadXMLAction var10 = new LoadXMLAction(this, var1);
         JMenuItem var4 = new JMenuItem("Load file...");
         var2.add(var4);
         var4.addActionListener(var10);
      } catch (NoClassDefFoundError var5) {
         LOG.info("Missing classes for XML parser", var5);
         JOptionPane.showMessageDialog(this, "XML parser not in classpath - unable to load XML events.", "CHAINSAW", 0);
      } catch (Exception var6) {
         LOG.info("Unable to create the action to load XML files", var6);
         JOptionPane.showMessageDialog(this, "Unable to create a XML parser - unable to load XML events.", "CHAINSAW", 0);
      }

      JMenuItem var11 = new JMenuItem("Exit");
      var2.add(var11);
      var11.addActionListener(ExitAction.INSTANCE);
      ControlPanel var7 = new ControlPanel(var1);
      this.getContentPane().add(var7, "North");
      JTable var12 = new JTable(var1);
      var12.setSelectionMode(0);
      JScrollPane var8 = new JScrollPane(var12);
      var8.setBorder(BorderFactory.createTitledBorder("Events: "));
      var8.setPreferredSize(new Dimension(900, 300));
      DetailPanel var13 = new DetailPanel(var12, var1);
      var13.setPreferredSize(new Dimension(900, 300));
      JSplitPane var9 = new JSplitPane(0, var8, var13);
      this.getContentPane().add(var9, "Center");
      this.addWindowListener(new Main$1(this));
      this.pack();
      this.setVisible(true);
      this.setupReceiver(var1);
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

   private static void initLog4J() {
      Properties var0 = new Properties();
      var0.setProperty("log4j.rootLogger", "DEBUG, A1");
      var0.setProperty("log4j.appender.A1", "org.apache.log4j.ConsoleAppender");
      var0.setProperty("log4j.appender.A1.layout", "org.apache.log4j.TTCCLayout");
      PropertyConfigurator.configure(var0);
   }

   public static void main(String[] var0) {
      initLog4J();
      new Main();
   }

   private void setupReceiver(MyTableModel var1) {
      int var2;
      label22: {
         String var3 = System.getProperty("chainsaw.port");
         if (var3 != null) {
            try {
               var2 = Integer.parseInt(var3);
               break label22;
            } catch (NumberFormatException var6) {
               LOG.fatal("Unable to parse chainsaw.port property with value " + var3 + ".");
               JOptionPane.showMessageDialog(this, "Unable to parse port number from '" + var3 + "', quitting.", "CHAINSAW", 0);
               System.exit(1);
            }
         }

         var2 = 4445;
      }

      try {
         LoggingReceiver var7 = new LoggingReceiver(var1, var2);
         var7.start();
      } catch (IOException var5) {
         LOG.fatal("Unable to connect to socket server, quiting", var5);
         JOptionPane.showMessageDialog(this, "Unable to create socket on port " + var2 + ", quitting.", "CHAINSAW", 0);
         System.exit(1);
      }

   }
}
