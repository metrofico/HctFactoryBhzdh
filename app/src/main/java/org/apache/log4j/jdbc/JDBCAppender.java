package org.apache.log4j.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.log4j.Appender;
import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.spi.LoggingEvent;

public class JDBCAppender extends AppenderSkeleton implements Appender {
   protected ArrayList buffer;
   protected int bufferSize = 1;
   protected Connection connection = null;
   protected String databasePassword = "mypassword";
   protected String databaseURL = "jdbc:odbc:myDB";
   protected String databaseUser = "me";
   protected ArrayList removes;
   protected String sqlStatement = "";

   public JDBCAppender() {
      this.buffer = new ArrayList(this.bufferSize);
      this.removes = new ArrayList(this.bufferSize);
   }

   public void append(LoggingEvent var1) {
      this.buffer.add(var1);
      if (this.buffer.size() >= this.bufferSize) {
         this.flushBuffer();
      }

   }

   public void close() {
      this.flushBuffer();

      label26: {
         SQLException var10000;
         label30: {
            Connection var1;
            boolean var10001;
            try {
               var1 = this.connection;
            } catch (SQLException var3) {
               var10000 = var3;
               var10001 = false;
               break label30;
            }

            if (var1 == null) {
               break label26;
            }

            try {
               if (!var1.isClosed()) {
                  this.connection.close();
               }
               break label26;
            } catch (SQLException var2) {
               var10000 = var2;
               var10001 = false;
            }
         }

         SQLException var4 = var10000;
         super.errorHandler.error("Error closing connection", var4, 0);
      }

      super.closed = true;
   }

   protected void closeConnection(Connection var1) {
   }

   protected void execute(String var1) throws SQLException {
      Statement var3 = null;
      Statement var2 = var3;

      Connection var4;
      label38: {
         SQLException var10000;
         label39: {
            boolean var10001;
            try {
               var4 = this.getConnection();
            } catch (SQLException var7) {
               var10000 = var7;
               var10001 = false;
               break label39;
            }

            var2 = var3;

            try {
               var3 = var4.createStatement();
            } catch (SQLException var6) {
               var10000 = var6;
               var10001 = false;
               break label39;
            }

            var2 = var3;

            try {
               var3.executeUpdate(var1);
               break label38;
            } catch (SQLException var5) {
               var10000 = var5;
               var10001 = false;
            }
         }

         SQLException var8 = var10000;
         if (var2 != null) {
            var2.close();
         }

         throw var8;
      }

      var3.close();
      this.closeConnection(var4);
   }

   public void finalize() {
      this.close();
   }

   public void flushBuffer() {
      this.removes.ensureCapacity(this.buffer.size());
      Iterator var1 = this.buffer.iterator();

      while(var1.hasNext()) {
         try {
            LoggingEvent var2 = (LoggingEvent)var1.next();
            this.execute(this.getLogStatement(var2));
            this.removes.add(var2);
         } catch (SQLException var3) {
            super.errorHandler.error("Failed to excute sql", var3, 2);
         }
      }

      this.buffer.removeAll(this.removes);
      this.removes.clear();
   }

   public int getBufferSize() {
      return this.bufferSize;
   }

   protected Connection getConnection() throws SQLException {
      if (!DriverManager.getDrivers().hasMoreElements()) {
         this.setDriver("sun.jdbc.odbc.JdbcOdbcDriver");
      }

      if (this.connection == null) {
         this.connection = DriverManager.getConnection(this.databaseURL, this.databaseUser, this.databasePassword);
      }

      return this.connection;
   }

   protected String getLogStatement(LoggingEvent var1) {
      return this.getLayout().format(var1);
   }

   public String getPassword() {
      return this.databasePassword;
   }

   public String getSql() {
      return this.sqlStatement;
   }

   public String getURL() {
      return this.databaseURL;
   }

   public String getUser() {
      return this.databaseUser;
   }

   public boolean requiresLayout() {
      return true;
   }

   public void setBufferSize(int var1) {
      this.bufferSize = var1;
      this.buffer.ensureCapacity(var1);
      this.removes.ensureCapacity(this.bufferSize);
   }

   public void setDriver(String var1) {
      try {
         Class.forName(var1);
      } catch (Exception var2) {
         super.errorHandler.error("Failed to load driver", var2, 0);
      }

   }

   public void setPassword(String var1) {
      this.databasePassword = var1;
   }

   public void setSql(String var1) {
      this.sqlStatement = var1;
      if (this.getLayout() == null) {
         this.setLayout(new PatternLayout(var1));
      } else {
         ((PatternLayout)this.getLayout()).setConversionPattern(var1);
      }

   }

   public void setURL(String var1) {
      this.databaseURL = var1;
   }

   public void setUser(String var1) {
      this.databaseUser = var1;
   }
}
