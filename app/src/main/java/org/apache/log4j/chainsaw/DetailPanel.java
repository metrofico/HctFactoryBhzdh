package org.apache.log4j.chainsaw;

import java.awt.BorderLayout;
import java.text.MessageFormat;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;

class DetailPanel extends JPanel implements ListSelectionListener {
   private static final MessageFormat FORMATTER;
   private static final Logger LOG;
   static Class class$org$apache$log4j$chainsaw$DetailPanel;
   private final JEditorPane mDetails;
   private final MyTableModel mModel;

   static {
      Class var1 = class$org$apache$log4j$chainsaw$DetailPanel;
      Class var0 = var1;
      if (var1 == null) {
         var0 = class$("org.apache.log4j.chainsaw.DetailPanel");
         class$org$apache$log4j$chainsaw$DetailPanel = var0;
      }

      LOG = Logger.getLogger(var0);
      FORMATTER = new MessageFormat("<b>Time:</b> <code>{0,time,medium}</code>&nbsp;&nbsp;<b>Priority:</b> <code>{1}</code>&nbsp;&nbsp;<b>Thread:</b> <code>{2}</code>&nbsp;&nbsp;<b>NDC:</b> <code>{3}</code><br><b>Logger:</b> <code>{4}</code><br><b>Location:</b> <code>{5}</code><br><b>Message:</b><pre>{6}</pre><b>Throwable:</b><pre>{7}</pre>");
   }

   DetailPanel(JTable var1, MyTableModel var2) {
      this.mModel = var2;
      this.setLayout(new BorderLayout());
      this.setBorder(BorderFactory.createTitledBorder("Details: "));
      JEditorPane var3 = new JEditorPane();
      this.mDetails = var3;
      var3.setEditable(false);
      var3.setContentType("text/html");
      this.add(new JScrollPane(var3), "Center");
      var1.getSelectionModel().addListSelectionListener(this);
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

   private String escape(String var1) {
      if (var1 == null) {
         return null;
      } else {
         StringBuffer var4 = new StringBuffer();

         for(int var3 = 0; var3 < var1.length(); ++var3) {
            char var2 = var1.charAt(var3);
            if (var2 != '"') {
               if (var2 != '&') {
                  if (var2 != '<') {
                     if (var2 != '>') {
                        var4.append(var2);
                     } else {
                        var4.append("&gt;");
                     }
                  } else {
                     var4.append("&lt;");
                  }
               } else {
                  var4.append("&amp;");
               }
            } else {
               var4.append("&quot;");
            }
         }

         return var4.toString();
      }
   }

   private static String getThrowableStrRep(EventDetails var0) {
      String[] var3 = var0.getThrowableStrRep();
      if (var3 == null) {
         return null;
      } else {
         StringBuffer var2 = new StringBuffer();

         for(int var1 = 0; var1 < var3.length; ++var1) {
            var2.append(var3[var1]).append("\n");
         }

         return var2.toString();
      }
   }

   public void valueChanged(ListSelectionEvent var1) {
      if (!var1.getValueIsAdjusting()) {
         ListSelectionModel var10 = (ListSelectionModel)var1.getSource();
         if (var10.isSelectionEmpty()) {
            this.mDetails.setText("Nothing selected");
         } else {
            int var2 = var10.getMinSelectionIndex();
            EventDetails var9 = this.mModel.getEventDetails(var2);
            Date var5 = new Date(var9.getTimeStamp());
            Priority var11 = var9.getPriority();
            String var3 = this.escape(var9.getThreadName());
            String var4 = this.escape(var9.getNDC());
            String var6 = this.escape(var9.getCategoryName());
            String var8 = this.escape(var9.getLocationDetails());
            String var7 = this.escape(var9.getMessage());
            String var12 = this.escape(getThrowableStrRep(var9));
            this.mDetails.setText(FORMATTER.format(new Object[]{var5, var11, var3, var4, var6, var8, var7, var12}));
            this.mDetails.setCaretPosition(0);
         }

      }
   }
}
