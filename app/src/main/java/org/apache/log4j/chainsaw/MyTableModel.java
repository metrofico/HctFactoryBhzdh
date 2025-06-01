package org.apache.log4j.chainsaw;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import javax.swing.table.AbstractTableModel;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;

class MyTableModel extends AbstractTableModel {
   private static final String[] COL_NAMES;
   private static final DateFormat DATE_FORMATTER;
   private static final EventDetails[] EMPTY_LIST;
   private static final Logger LOG;
   private static final Comparator MY_COMP;
   static Class class$java$lang$Boolean;
   static Class class$java$lang$Object;
   static Class class$org$apache$log4j$chainsaw$MyTableModel;
   private final SortedSet mAllEvents;
   private String mCategoryFilter;
   private EventDetails[] mFilteredEvents;
   private final Object mLock = new Object();
   private String mMessageFilter;
   private String mNDCFilter;
   private boolean mPaused;
   private final List mPendingEvents;
   private Priority mPriorityFilter;
   private String mThreadFilter;

   static {
      Class var1 = class$org$apache$log4j$chainsaw$MyTableModel;
      Class var0 = var1;
      if (var1 == null) {
         var0 = class$("org.apache.log4j.chainsaw.MyTableModel");
         class$org$apache$log4j$chainsaw$MyTableModel = var0;
      }

      LOG = Logger.getLogger(var0);
      MY_COMP = new MyTableModel$1();
      COL_NAMES = new String[]{"Time", "Priority", "Trace", "Category", "NDC", "Message"};
      EMPTY_LIST = new EventDetails[0];
      DATE_FORMATTER = DateFormat.getDateTimeInstance(3, 2);
   }

   MyTableModel() {
      this.mAllEvents = new TreeSet(MY_COMP);
      this.mFilteredEvents = EMPTY_LIST;
      this.mPendingEvents = new ArrayList();
      this.mPaused = false;
      this.mThreadFilter = "";
      this.mMessageFilter = "";
      this.mNDCFilter = "";
      this.mCategoryFilter = "";
      this.mPriorityFilter = Priority.DEBUG;
      Thread var1 = new Thread(new Processor((MyTableModel$1)null));
      var1.setDaemon(true);
      var1.start();
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

   private boolean matchFilter(EventDetails var1) {
      boolean var5 = var1.getPriority().isGreaterOrEqual(this.mPriorityFilter);
      boolean var4 = false;
      boolean var3 = false;
      boolean var2 = var4;
      if (var5) {
         var2 = var4;
         if (var1.getThreadName().indexOf(this.mThreadFilter) >= 0) {
            var2 = var4;
            if (var1.getCategoryName().indexOf(this.mCategoryFilter) >= 0) {
               if (this.mNDCFilter.length() != 0) {
                  var2 = var4;
                  if (var1.getNDC() == null) {
                     return var2;
                  }

                  var2 = var4;
                  if (var1.getNDC().indexOf(this.mNDCFilter) < 0) {
                     return var2;
                  }
               }

               String var6 = var1.getMessage();
               if (var6 == null) {
                  var2 = var3;
                  if (this.mMessageFilter.length() == 0) {
                     var2 = true;
                  }

                  return var2;
               }

               var2 = var4;
               if (var6.indexOf(this.mMessageFilter) >= 0) {
                  var2 = true;
               }
            }
         }
      }

      return var2;
   }

   private void updateFilteredEvents(boolean var1) {
      long var4 = System.currentTimeMillis();
      ArrayList var9 = new ArrayList();
      int var3 = this.mAllEvents.size();
      Iterator var8 = this.mAllEvents.iterator();

      while(var8.hasNext()) {
         EventDetails var10 = (EventDetails)var8.next();
         if (this.matchFilter(var10)) {
            var9.add(var10);
         }
      }

      EventDetails[] var11 = this.mFilteredEvents;
      EventDetails var12;
      if (var11.length == 0) {
         var12 = null;
      } else {
         var12 = var11[0];
      }

      this.mFilteredEvents = (EventDetails[])var9.toArray(EMPTY_LIST);
      if (var1 && var12 != null) {
         int var2 = var9.indexOf(var12);
         if (var2 < 1) {
            LOG.warn("In strange state");
            this.fireTableDataChanged();
         } else {
            this.fireTableRowsInserted(0, var2 - 1);
         }
      } else {
         this.fireTableDataChanged();
      }

      long var6 = System.currentTimeMillis();
      LOG.debug("Total time [ms]: " + (var6 - var4) + " in update, size: " + var3);
   }

   public void addEvent(EventDetails var1) {
      Object var2 = this.mLock;
      synchronized(var2) {
         this.mPendingEvents.add(var1);
      }
   }

   public void clear() {
      Object var1 = this.mLock;
      synchronized(var1) {
         this.mAllEvents.clear();
         this.mFilteredEvents = new EventDetails[0];
         this.mPendingEvents.clear();
         this.fireTableDataChanged();
      }
   }

   public Class getColumnClass(int var1) {
      Class var2;
      Class var3;
      if (var1 == 2) {
         var3 = class$java$lang$Boolean;
         var2 = var3;
         if (var3 == null) {
            var2 = class$("java.lang.Boolean");
            class$java$lang$Boolean = var2;
         }
      } else {
         var3 = class$java$lang$Object;
         var2 = var3;
         if (var3 == null) {
            var2 = class$("java.lang.Object");
            class$java$lang$Object = var2;
         }
      }

      return var2;
   }

   public int getColumnCount() {
      return COL_NAMES.length;
   }

   public String getColumnName(int var1) {
      return COL_NAMES[var1];
   }

   public EventDetails getEventDetails(int var1) {
      Object var2 = this.mLock;
      synchronized(var2) {
         EventDetails var3 = this.mFilteredEvents[var1];
         return var3;
      }
   }

   public int getRowCount() {
      Object var2 = this.mLock;
      synchronized(var2) {
         int var1 = this.mFilteredEvents.length;
         return var1;
      }
   }

   public Object getValueAt(int var1, int var2) {
      Object var4 = this.mLock;
      synchronized(var4){}

      Throwable var10000;
      label653: {
         EventDetails var3;
         boolean var10001;
         try {
            var3 = this.mFilteredEvents[var1];
         } catch (Throwable var96) {
            var10000 = var96;
            var10001 = false;
            break label653;
         }

         String var97;
         if (var2 == 0) {
            label625:
            try {
               DateFormat var6 = DATE_FORMATTER;
               Date var5 = new Date(var3.getTimeStamp());
               var97 = var6.format(var5);
               return var97;
            } catch (Throwable var88) {
               var10000 = var88;
               var10001 = false;
               break label625;
            }
         } else if (var2 == 1) {
            label627:
            try {
               Priority var98 = var3.getPriority();
               return var98;
            } catch (Throwable var89) {
               var10000 = var89;
               var10001 = false;
               break label627;
            }
         } else if (var2 == 2) {
            label639: {
               Boolean var99;
               label657: {
                  try {
                     if (var3.getThrowableStrRep() == null) {
                        var99 = Boolean.FALSE;
                        break label657;
                     }
                  } catch (Throwable var92) {
                     var10000 = var92;
                     var10001 = false;
                     break label639;
                  }

                  try {
                     var99 = Boolean.TRUE;
                  } catch (Throwable var91) {
                     var10000 = var91;
                     var10001 = false;
                     break label639;
                  }
               }

               label629:
               try {
                  return var99;
               } catch (Throwable var90) {
                  var10000 = var90;
                  var10001 = false;
                  break label629;
               }
            }
         } else if (var2 == 3) {
            label641:
            try {
               var97 = var3.getCategoryName();
               return var97;
            } catch (Throwable var93) {
               var10000 = var93;
               var10001 = false;
               break label641;
            }
         } else if (var2 == 4) {
            label643:
            try {
               var97 = var3.getNDC();
               return var97;
            } catch (Throwable var94) {
               var10000 = var94;
               var10001 = false;
               break label643;
            }
         } else {
            label645:
            try {
               var97 = var3.getMessage();
               return var97;
            } catch (Throwable var95) {
               var10000 = var95;
               var10001 = false;
               break label645;
            }
         }
      }

      Throwable var100 = var10000;
      throw var100;
   }

   public boolean isPaused() {
      Object var2 = this.mLock;
      synchronized(var2) {
         boolean var1 = this.mPaused;
         return var1;
      }
   }

   public void setCategoryFilter(String var1) {
      Object var2 = this.mLock;
      synchronized(var2) {
         this.mCategoryFilter = var1.trim();
         this.updateFilteredEvents(false);
      }
   }

   public void setMessageFilter(String var1) {
      Object var2 = this.mLock;
      synchronized(var2) {
         this.mMessageFilter = var1.trim();
         this.updateFilteredEvents(false);
      }
   }

   public void setNDCFilter(String var1) {
      Object var2 = this.mLock;
      synchronized(var2) {
         this.mNDCFilter = var1.trim();
         this.updateFilteredEvents(false);
      }
   }

   public void setPriorityFilter(Priority var1) {
      Object var2 = this.mLock;
      synchronized(var2) {
         this.mPriorityFilter = var1;
         this.updateFilteredEvents(false);
      }
   }

   public void setThreadFilter(String var1) {
      Object var2 = this.mLock;
      synchronized(var2) {
         this.mThreadFilter = var1.trim();
         this.updateFilteredEvents(false);
      }
   }

   public void toggle() {
      Object var2 = this.mLock;
      synchronized(var2){}

      Throwable var10000;
      label76: {
         boolean var10001;
         boolean var1;
         label75: {
            label74: {
               try {
                  if (!this.mPaused) {
                     break label74;
                  }
               } catch (Throwable var9) {
                  var10000 = var9;
                  var10001 = false;
                  break label76;
               }

               var1 = false;
               break label75;
            }

            var1 = true;
         }

         label68:
         try {
            this.mPaused = var1;
            return;
         } catch (Throwable var8) {
            var10000 = var8;
            var10001 = false;
            break label68;
         }
      }

      Throwable var3 = var10000;
      throw var3;
   }

   private class Processor implements Runnable {
      private Processor() {
      }

      // $FF: synthetic method
      Processor(MyTableModel$1 var2) {
         this();
      }

      public void run() {
         while(true) {
            try {
               Thread.sleep(1000L);
            } catch (InterruptedException var79) {
            }

            Object var4 = MyTableModel.this.mLock;
            synchronized(var4){}

            Throwable var10000;
            label759: {
               boolean var10001;
               try {
                  if (MyTableModel.this.mPaused) {
                     continue;
                  }
               } catch (Throwable var87) {
                  var10000 = var87;
                  var10001 = false;
                  break label759;
               }

               Iterator var6;
               try {
                  var6 = MyTableModel.this.mPendingEvents.iterator();
               } catch (Throwable var86) {
                  var10000 = var86;
                  var10001 = false;
                  break label759;
               }

               boolean var1 = false;
               boolean var2 = true;

               while(true) {
                  try {
                     if (!var6.hasNext()) {
                        MyTableModel.this.mPendingEvents.clear();
                        break;
                     }
                  } catch (Throwable var85) {
                     var10000 = var85;
                     var10001 = false;
                     break label759;
                  }

                  EventDetails var5;
                  try {
                     var5 = (EventDetails)var6.next();
                     MyTableModel.this.mAllEvents.add(var5);
                  } catch (Throwable var83) {
                     var10000 = var83;
                     var10001 = false;
                     break label759;
                  }

                  label734: {
                     label733: {
                        if (var2) {
                           try {
                              if (var5 == MyTableModel.this.mAllEvents.first()) {
                                 break label733;
                              }
                           } catch (Throwable var84) {
                              var10000 = var84;
                              var10001 = false;
                              break label759;
                           }
                        }

                        var2 = false;
                        break label734;
                     }

                     var2 = true;
                  }

                  if (!var1) {
                     boolean var3;
                     try {
                        var3 = MyTableModel.this.matchFilter(var5);
                     } catch (Throwable var82) {
                        var10000 = var82;
                        var10001 = false;
                        break label759;
                     }

                     if (!var3) {
                        var1 = false;
                        continue;
                     }
                  }

                  var1 = true;
               }

               if (var1) {
                  try {
                     MyTableModel.this.updateFilteredEvents(var2);
                  } catch (Throwable var81) {
                     var10000 = var81;
                     var10001 = false;
                     break label759;
                  }
               }

               label715:
               try {
                  continue;
               } catch (Throwable var80) {
                  var10000 = var80;
                  var10001 = false;
                  break label715;
               }
            }

            Throwable var88 = var10000;
            throw var88;
         }
      }
   }
}
