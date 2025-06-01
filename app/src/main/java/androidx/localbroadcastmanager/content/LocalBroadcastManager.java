package androidx.localbroadcastmanager.content;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public final class LocalBroadcastManager {
   private static final boolean DEBUG = false;
   static final int MSG_EXEC_PENDING_BROADCASTS = 1;
   private static final String TAG = "LocalBroadcastManager";
   private static LocalBroadcastManager mInstance;
   private static final Object mLock = new Object();
   private final HashMap mActions = new HashMap();
   private final Context mAppContext;
   private final Handler mHandler;
   private final ArrayList mPendingBroadcasts = new ArrayList();
   private final HashMap mReceivers = new HashMap();

   private LocalBroadcastManager(Context var1) {
      this.mAppContext = var1;
      this.mHandler = new Handler(this, var1.getMainLooper()) {
         final LocalBroadcastManager this$0;

         {
            this.this$0 = var1;
         }

         public void handleMessage(Message var1) {
            if (var1.what != 1) {
               super.handleMessage(var1);
            } else {
               this.this$0.executePendingBroadcasts();
            }

         }
      };
   }

   public static LocalBroadcastManager getInstance(Context var0) {
      Object var1 = mLock;
      synchronized(var1){}

      Throwable var10000;
      boolean var10001;
      label122: {
         try {
            if (mInstance == null) {
               LocalBroadcastManager var2 = new LocalBroadcastManager(var0.getApplicationContext());
               mInstance = var2;
            }
         } catch (Throwable var14) {
            var10000 = var14;
            var10001 = false;
            break label122;
         }

         label119:
         try {
            LocalBroadcastManager var16 = mInstance;
            return var16;
         } catch (Throwable var13) {
            var10000 = var13;
            var10001 = false;
            break label119;
         }
      }

      while(true) {
         Throwable var15 = var10000;

         try {
            throw var15;
         } catch (Throwable var12) {
            var10000 = var12;
            var10001 = false;
            continue;
         }
      }
   }

   void executePendingBroadcasts() {
      label287:
      while(true) {
         HashMap var6 = this.mReceivers;
         synchronized(var6){}

         Throwable var10000;
         boolean var10001;
         label284: {
            int var3;
            try {
               var3 = this.mPendingBroadcasts.size();
            } catch (Throwable var27) {
               var10000 = var27;
               var10001 = false;
               break label284;
            }

            if (var3 <= 0) {
               label277:
               try {
                  return;
               } catch (Throwable var25) {
                  var10000 = var25;
                  var10001 = false;
                  break label277;
               }
            } else {
               label289: {
                  BroadcastRecord[] var28;
                  try {
                     var28 = new BroadcastRecord[var3];
                     this.mPendingBroadcasts.toArray(var28);
                     this.mPendingBroadcasts.clear();
                  } catch (Throwable var26) {
                     var10000 = var26;
                     var10001 = false;
                     break label289;
                  }

                  int var1 = 0;

                  while(true) {
                     if (var1 >= var3) {
                        continue label287;
                     }

                     BroadcastRecord var7 = var28[var1];
                     int var4 = var7.receivers.size();

                     for(int var2 = 0; var2 < var4; ++var2) {
                        ReceiverRecord var29 = (ReceiverRecord)var7.receivers.get(var2);
                        if (!var29.dead) {
                           var29.receiver.onReceive(this.mAppContext, var7.intent);
                        }
                     }

                     ++var1;
                  }
               }
            }
         }

         while(true) {
            Throwable var5 = var10000;

            try {
               throw var5;
            } catch (Throwable var24) {
               var10000 = var24;
               var10001 = false;
               continue;
            }
         }
      }
   }

   public void registerReceiver(BroadcastReceiver var1, IntentFilter var2) {
      HashMap var6 = this.mReceivers;
      synchronized(var6){}

      Throwable var10000;
      boolean var10001;
      label585: {
         ArrayList var5;
         ReceiverRecord var7;
         try {
            var7 = new ReceiverRecord(var2, var1);
            var5 = (ArrayList)this.mReceivers.get(var1);
         } catch (Throwable var79) {
            var10000 = var79;
            var10001 = false;
            break label585;
         }

         ArrayList var4 = var5;
         if (var5 == null) {
            try {
               var4 = new ArrayList(1);
               this.mReceivers.put(var1, var4);
            } catch (Throwable var78) {
               var10000 = var78;
               var10001 = false;
               break label585;
            }
         }

         try {
            var4.add(var7);
         } catch (Throwable var77) {
            var10000 = var77;
            var10001 = false;
            break label585;
         }

         int var3 = 0;

         while(true) {
            String var82;
            try {
               if (var3 >= var2.countActions()) {
                  break;
               }

               var82 = var2.getAction(var3);
               var4 = (ArrayList)this.mActions.get(var82);
            } catch (Throwable var76) {
               var10000 = var76;
               var10001 = false;
               break label585;
            }

            ArrayList var80 = var4;
            if (var4 == null) {
               try {
                  var80 = new ArrayList(1);
                  this.mActions.put(var82, var80);
               } catch (Throwable var75) {
                  var10000 = var75;
                  var10001 = false;
                  break label585;
               }
            }

            try {
               var80.add(var7);
            } catch (Throwable var74) {
               var10000 = var74;
               var10001 = false;
               break label585;
            }

            ++var3;
         }

         label557:
         try {
            return;
         } catch (Throwable var73) {
            var10000 = var73;
            var10001 = false;
            break label557;
         }
      }

      while(true) {
         Throwable var81 = var10000;

         try {
            throw var81;
         } catch (Throwable var72) {
            var10000 = var72;
            var10001 = false;
            continue;
         }
      }
   }

   public boolean sendBroadcast(Intent var1) {
      HashMap var10 = this.mReceivers;
      synchronized(var10){}

      Throwable var10000;
      boolean var10001;
      label3172: {
         boolean var2;
         String var7;
         String var9;
         Uri var11;
         String var12;
         Set var13;
         label3167: {
            label3166: {
               try {
                  var9 = var1.getAction();
                  var7 = var1.resolveTypeIfNeeded(this.mAppContext.getContentResolver());
                  var11 = var1.getData();
                  var12 = var1.getScheme();
                  var13 = var1.getCategories();
                  if ((var1.getFlags() & 8) != 0) {
                     break label3166;
                  }
               } catch (Throwable var356) {
                  var10000 = var356;
                  var10001 = false;
                  break label3172;
               }

               var2 = false;
               break label3167;
            }

            var2 = true;
         }

         StringBuilder var5;
         if (var2) {
            try {
               var5 = new StringBuilder();
               Log.v("LocalBroadcastManager", var5.append("Resolving type ").append(var7).append(" scheme ").append(var12).append(" of intent ").append(var1).toString());
            } catch (Throwable var355) {
               var10000 = var355;
               var10001 = false;
               break label3172;
            }
         }

         ArrayList var8;
         try {
            var8 = (ArrayList)this.mActions.get(var1.getAction());
         } catch (Throwable var354) {
            var10000 = var354;
            var10001 = false;
            break label3172;
         }

         if (var8 != null) {
            if (var2) {
               try {
                  var5 = new StringBuilder();
                  Log.v("LocalBroadcastManager", var5.append("Action list: ").append(var8).toString());
               } catch (Throwable var351) {
                  var10000 = var351;
                  var10001 = false;
                  break label3172;
               }
            }

            ArrayList var6 = null;
            int var3 = 0;

            while(true) {
               ReceiverRecord var359;
               try {
                  if (var3 >= var8.size()) {
                     break;
                  }

                  var359 = (ReceiverRecord)var8.get(var3);
               } catch (Throwable var352) {
                  var10000 = var352;
                  var10001 = false;
                  break label3172;
               }

               StringBuilder var14;
               if (var2) {
                  try {
                     var14 = new StringBuilder();
                     Log.v("LocalBroadcastManager", var14.append("Matching against filter ").append(var359.filter).toString());
                  } catch (Throwable var350) {
                     var10000 = var350;
                     var10001 = false;
                     break label3172;
                  }
               }

               label3175: {
                  label3145: {
                     try {
                        if (!var359.broadcasting) {
                           break label3145;
                        }
                     } catch (Throwable var353) {
                        var10000 = var353;
                        var10001 = false;
                        break label3172;
                     }

                     if (var2) {
                        try {
                           Log.v("LocalBroadcastManager", "  Filter's target already added");
                        } catch (Throwable var349) {
                           var10000 = var349;
                           var10001 = false;
                           break label3172;
                        }
                     }
                     break label3175;
                  }

                  int var4;
                  try {
                     IntentFilter var363 = var359.filter;
                     var4 = var363.match(var9, var7, var12, var11, var13, "LocalBroadcastManager");
                  } catch (Throwable var348) {
                     var10000 = var348;
                     var10001 = false;
                     break label3172;
                  }

                  if (var4 >= 0) {
                     if (var2) {
                        try {
                           var14 = new StringBuilder();
                           Log.v("LocalBroadcastManager", var14.append("  Filter matched!  match=0x").append(Integer.toHexString(var4)).toString());
                        } catch (Throwable var347) {
                           var10000 = var347;
                           var10001 = false;
                           break label3172;
                        }
                     }

                     if (var6 == null) {
                        try {
                           var6 = new ArrayList();
                        } catch (Throwable var346) {
                           var10000 = var346;
                           var10001 = false;
                           break label3172;
                        }
                     }

                     try {
                        var6.add(var359);
                        var359.broadcasting = true;
                     } catch (Throwable var345) {
                        var10000 = var345;
                        var10001 = false;
                        break label3172;
                     }
                  } else if (var2) {
                     String var360;
                     if (var4 != -4) {
                        if (var4 != -3) {
                           if (var4 != -2) {
                              if (var4 != -1) {
                                 var360 = "unknown reason";
                              } else {
                                 var360 = "type";
                              }
                           } else {
                              var360 = "data";
                           }
                        } else {
                           var360 = "action";
                        }
                     } else {
                        var360 = "category";
                     }

                     try {
                        var14 = new StringBuilder();
                        Log.v("LocalBroadcastManager", var14.append("  Filter did not match: ").append(var360).toString());
                     } catch (Throwable var344) {
                        var10000 = var344;
                        var10001 = false;
                        break label3172;
                     }
                  }
               }

               ++var3;
            }

            if (var6 != null) {
               int var358 = 0;

               while(true) {
                  try {
                     if (var358 >= var6.size()) {
                        break;
                     }

                     ((ReceiverRecord)var6.get(var358)).broadcasting = false;
                  } catch (Throwable var342) {
                     var10000 = var342;
                     var10001 = false;
                     break label3172;
                  }

                  ++var358;
               }

               try {
                  ArrayList var362 = this.mPendingBroadcasts;
                  BroadcastRecord var361 = new BroadcastRecord(var1, var6);
                  var362.add(var361);
                  if (!this.mHandler.hasMessages(1)) {
                     this.mHandler.sendEmptyMessage(1);
                  }
               } catch (Throwable var341) {
                  var10000 = var341;
                  var10001 = false;
                  break label3172;
               }

               try {
                  return true;
               } catch (Throwable var340) {
                  var10000 = var340;
                  var10001 = false;
                  break label3172;
               }
            }
         }

         label3111:
         try {
            return false;
         } catch (Throwable var343) {
            var10000 = var343;
            var10001 = false;
            break label3111;
         }
      }

      while(true) {
         Throwable var357 = var10000;

         try {
            throw var357;
         } catch (Throwable var339) {
            var10000 = var339;
            var10001 = false;
            continue;
         }
      }
   }

   public void sendBroadcastSync(Intent var1) {
      if (this.sendBroadcast(var1)) {
         this.executePendingBroadcasts();
      }

   }

   public void unregisterReceiver(BroadcastReceiver var1) {
      HashMap var5 = this.mReceivers;
      synchronized(var5){}

      Throwable var10000;
      boolean var10001;
      label986: {
         ArrayList var7;
         try {
            var7 = (ArrayList)this.mReceivers.remove(var1);
         } catch (Throwable var120) {
            var10000 = var120;
            var10001 = false;
            break label986;
         }

         if (var7 == null) {
            label949:
            try {
               return;
            } catch (Throwable var112) {
               var10000 = var112;
               var10001 = false;
               break label949;
            }
         } else {
            label982: {
               int var2;
               try {
                  var2 = var7.size() - 1;
               } catch (Throwable var117) {
                  var10000 = var117;
                  var10001 = false;
                  break label982;
               }

               label981:
               while(true) {
                  if (var2 < 0) {
                     try {
                        return;
                     } catch (Throwable var113) {
                        var10000 = var113;
                        var10001 = false;
                        break;
                     }
                  }

                  ReceiverRecord var10;
                  try {
                     var10 = (ReceiverRecord)var7.get(var2);
                     var10.dead = true;
                  } catch (Throwable var116) {
                     var10000 = var116;
                     var10001 = false;
                     break;
                  }

                  int var3 = 0;

                  while(true) {
                     ArrayList var6;
                     String var9;
                     try {
                        if (var3 >= var10.filter.countActions()) {
                           break;
                        }

                        var9 = var10.filter.getAction(var3);
                        var6 = (ArrayList)this.mActions.get(var9);
                     } catch (Throwable var118) {
                        var10000 = var118;
                        var10001 = false;
                        break label981;
                     }

                     if (var6 != null) {
                        int var4;
                        try {
                           var4 = var6.size() - 1;
                        } catch (Throwable var115) {
                           var10000 = var115;
                           var10001 = false;
                           break label981;
                        }

                        while(true) {
                           if (var4 < 0) {
                              try {
                                 if (var6.size() <= 0) {
                                    this.mActions.remove(var9);
                                 }
                                 break;
                              } catch (Throwable var114) {
                                 var10000 = var114;
                                 var10001 = false;
                                 break label981;
                              }
                           }

                           try {
                              ReceiverRecord var8 = (ReceiverRecord)var6.get(var4);
                              if (var8.receiver == var1) {
                                 var8.dead = true;
                                 var6.remove(var4);
                              }
                           } catch (Throwable var119) {
                              var10000 = var119;
                              var10001 = false;
                              break label981;
                           }

                           --var4;
                        }
                     }

                     ++var3;
                  }

                  --var2;
               }
            }
         }
      }

      while(true) {
         Throwable var121 = var10000;

         try {
            throw var121;
         } catch (Throwable var111) {
            var10000 = var111;
            var10001 = false;
            continue;
         }
      }
   }

   private static final class BroadcastRecord {
      final Intent intent;
      final ArrayList receivers;

      BroadcastRecord(Intent var1, ArrayList var2) {
         this.intent = var1;
         this.receivers = var2;
      }
   }

   private static final class ReceiverRecord {
      boolean broadcasting;
      boolean dead;
      final IntentFilter filter;
      final BroadcastReceiver receiver;

      ReceiverRecord(IntentFilter var1, BroadcastReceiver var2) {
         this.filter = var1;
         this.receiver = var2;
      }

      public String toString() {
         StringBuilder var1 = new StringBuilder(128);
         var1.append("Receiver{");
         var1.append(this.receiver);
         var1.append(" filter=");
         var1.append(this.filter);
         if (this.dead) {
            var1.append(" DEAD");
         }

         var1.append("}");
         return var1.toString();
      }
   }
}
