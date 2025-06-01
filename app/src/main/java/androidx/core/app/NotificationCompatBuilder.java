package androidx.core.app;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Bundle;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.util.SparseArray;
import android.widget.RemoteViews;
import androidx.collection.ArraySet;
import androidx.core.graphics.drawable.IconCompat;
import androidx.core.os.BuildCompat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

class NotificationCompatBuilder implements NotificationBuilderWithBuilderAccessor {
   private final List mActionExtrasList = new ArrayList();
   private RemoteViews mBigContentView;
   private final Notification.Builder mBuilder;
   private final NotificationCompat.Builder mBuilderCompat;
   private RemoteViews mContentView;
   private final Context mContext;
   private final Bundle mExtras = new Bundle();
   private int mGroupAlertBehavior;
   private RemoteViews mHeadsUpContentView;

   NotificationCompatBuilder(NotificationCompat.Builder var1) {
      this.mBuilderCompat = var1;
      this.mContext = var1.mContext;
      if (VERSION.SDK_INT >= 26) {
         this.mBuilder = new Notification.Builder(var1.mContext, var1.mChannelId);
      } else {
         this.mBuilder = new Notification.Builder(var1.mContext);
      }

      Notification var6 = var1.mNotification;
      Notification.Builder var4 = this.mBuilder.setWhen(var6.when).setSmallIcon(var6.icon, var6.iconLevel).setContent(var6.contentView).setTicker(var6.tickerText, var1.mTickerView).setVibrate(var6.vibrate).setLights(var6.ledARGB, var6.ledOnMS, var6.ledOffMS);
      boolean var3;
      if ((var6.flags & 2) != 0) {
         var3 = true;
      } else {
         var3 = false;
      }

      var4 = var4.setOngoing(var3);
      if ((var6.flags & 8) != 0) {
         var3 = true;
      } else {
         var3 = false;
      }

      var4 = var4.setOnlyAlertOnce(var3);
      if ((var6.flags & 16) != 0) {
         var3 = true;
      } else {
         var3 = false;
      }

      Notification.Builder var5 = var4.setAutoCancel(var3).setDefaults(var6.defaults).setContentTitle(var1.mContentTitle).setContentText(var1.mContentText).setContentInfo(var1.mContentInfo).setContentIntent(var1.mContentIntent).setDeleteIntent(var6.deleteIntent);
      PendingIntent var8 = var1.mFullScreenIntent;
      if ((var6.flags & 128) != 0) {
         var3 = true;
      } else {
         var3 = false;
      }

      var5.setFullScreenIntent(var8, var3).setLargeIcon(var1.mLargeIcon).setNumber(var1.mNumber).setProgress(var1.mProgressMax, var1.mProgress, var1.mProgressIndeterminate);
      if (VERSION.SDK_INT < 21) {
         this.mBuilder.setSound(var6.sound, var6.audioStreamType);
      }

      Iterator var9;
      if (VERSION.SDK_INT >= 16) {
         this.mBuilder.setSubText(var1.mSubText).setUsesChronometer(var1.mUseChronometer).setPriority(var1.mPriority);
         var9 = var1.mActions.iterator();

         while(var9.hasNext()) {
            this.addAction((NotificationCompat.Action)var9.next());
         }

         if (var1.mExtras != null) {
            this.mExtras.putAll(var1.mExtras);
         }

         if (VERSION.SDK_INT < 20) {
            if (var1.mLocalOnly) {
               this.mExtras.putBoolean("android.support.localOnly", true);
            }

            if (var1.mGroupKey != null) {
               this.mExtras.putString("android.support.groupKey", var1.mGroupKey);
               if (var1.mGroupSummary) {
                  this.mExtras.putBoolean("android.support.isGroupSummary", true);
               } else {
                  this.mExtras.putBoolean("android.support.useSideChannel", true);
               }
            }

            if (var1.mSortKey != null) {
               this.mExtras.putString("android.support.sortKey", var1.mSortKey);
            }
         }

         this.mContentView = var1.mContentView;
         this.mBigContentView = var1.mBigContentView;
      }

      if (VERSION.SDK_INT >= 17) {
         this.mBuilder.setShowWhen(var1.mShowWhen);
      }

      if (VERSION.SDK_INT >= 19 && VERSION.SDK_INT < 21) {
         List var11 = combineLists(getPeople(var1.mPersonList), var1.mPeople);
         if (var11 != null && !var11.isEmpty()) {
            this.mExtras.putStringArray("android.people", (String[])var11.toArray(new String[var11.size()]));
         }
      }

      if (VERSION.SDK_INT >= 20) {
         this.mBuilder.setLocalOnly(var1.mLocalOnly).setGroup(var1.mGroupKey).setGroupSummary(var1.mGroupSummary).setSortKey(var1.mSortKey);
         this.mGroupAlertBehavior = var1.mGroupAlertBehavior;
      }

      if (VERSION.SDK_INT >= 21) {
         this.mBuilder.setCategory(var1.mCategory).setColor(var1.mColor).setVisibility(var1.mVisibility).setPublicVersion(var1.mPublicVersion).setSound(var6.sound, var6.audioAttributes);
         Object var13;
         if (VERSION.SDK_INT < 28) {
            var13 = combineLists(getPeople(var1.mPersonList), var1.mPeople);
         } else {
            var13 = var1.mPeople;
         }

         if (var13 != null && !((List)var13).isEmpty()) {
            Iterator var10 = ((List)var13).iterator();

            while(var10.hasNext()) {
               String var15 = (String)var10.next();
               this.mBuilder.addPerson(var15);
            }
         }

         this.mHeadsUpContentView = var1.mHeadsUpContentView;
         if (var1.mInvisibleActions.size() > 0) {
            Bundle var12 = var1.getExtras().getBundle("android.car.EXTENSIONS");
            Bundle var16 = var12;
            if (var12 == null) {
               var16 = new Bundle();
            }

            Bundle var7 = new Bundle(var16);
            var12 = new Bundle();

            for(int var2 = 0; var2 < var1.mInvisibleActions.size(); ++var2) {
               var12.putBundle(Integer.toString(var2), NotificationCompatJellybean.getBundleForAction((NotificationCompat.Action)var1.mInvisibleActions.get(var2)));
            }

            var16.putBundle("invisible_actions", var12);
            var7.putBundle("invisible_actions", var12);
            var1.getExtras().putBundle("android.car.EXTENSIONS", var16);
            this.mExtras.putBundle("android.car.EXTENSIONS", var7);
         }
      }

      if (VERSION.SDK_INT >= 23 && var1.mSmallIcon != null) {
         this.mBuilder.setSmallIcon(var1.mSmallIcon);
      }

      if (VERSION.SDK_INT >= 24) {
         this.mBuilder.setExtras(var1.mExtras).setRemoteInputHistory(var1.mRemoteInputHistory);
         if (var1.mContentView != null) {
            this.mBuilder.setCustomContentView(var1.mContentView);
         }

         if (var1.mBigContentView != null) {
            this.mBuilder.setCustomBigContentView(var1.mBigContentView);
         }

         if (var1.mHeadsUpContentView != null) {
            this.mBuilder.setCustomHeadsUpContentView(var1.mHeadsUpContentView);
         }
      }

      if (VERSION.SDK_INT >= 26) {
         this.mBuilder.setBadgeIconType(var1.mBadgeIcon).setSettingsText(var1.mSettingsText).setShortcutId(var1.mShortcutId).setTimeoutAfter(var1.mTimeout).setGroupAlertBehavior(var1.mGroupAlertBehavior);
         if (var1.mColorizedSet) {
            this.mBuilder.setColorized(var1.mColorized);
         }

         if (!TextUtils.isEmpty(var1.mChannelId)) {
            this.mBuilder.setSound((Uri)null).setDefaults(0).setLights(0, 0, 0).setVibrate((long[])null);
         }
      }

      if (VERSION.SDK_INT >= 28) {
         var9 = var1.mPersonList.iterator();

         while(var9.hasNext()) {
            Person var14 = (Person)var9.next();
            this.mBuilder.addPerson(var14.toAndroidPerson());
         }
      }

      if (VERSION.SDK_INT >= 29) {
         this.mBuilder.setAllowSystemGeneratedContextualActions(var1.mAllowSystemGeneratedContextualActions);
         this.mBuilder.setBubbleMetadata(NotificationCompat.BubbleMetadata.toPlatform(var1.mBubbleMetadata));
         if (var1.mLocusId != null) {
            this.mBuilder.setLocusId(var1.mLocusId.toLocusId());
         }
      }

      if (BuildCompat.isAtLeastS() && var1.mFgsDeferBehavior != 0) {
         this.mBuilder.setForegroundServiceBehavior(var1.mFgsDeferBehavior);
      }

      if (var1.mSilent) {
         if (this.mBuilderCompat.mGroupSummary) {
            this.mGroupAlertBehavior = 2;
         } else {
            this.mGroupAlertBehavior = 1;
         }

         this.mBuilder.setVibrate((long[])null);
         this.mBuilder.setSound((Uri)null);
         var6.defaults &= -2;
         var6.defaults &= -3;
         this.mBuilder.setDefaults(var6.defaults);
         if (VERSION.SDK_INT >= 26) {
            if (TextUtils.isEmpty(this.mBuilderCompat.mGroupKey)) {
               this.mBuilder.setGroup("silent");
            }

            this.mBuilder.setGroupAlertBehavior(this.mGroupAlertBehavior);
         }
      }

   }

   private void addAction(NotificationCompat.Action var1) {
      if (VERSION.SDK_INT >= 20) {
         IconCompat var5 = var1.getIconCompat();
         int var2 = VERSION.SDK_INT;
         byte var3 = 0;
         Notification.Action.Builder var8;
         if (var2 >= 23) {
            Icon var7;
            if (var5 != null) {
               var7 = var5.toIcon();
            } else {
               var7 = null;
            }

            var8 = new Notification.Action.Builder(var7, var1.getTitle(), var1.getActionIntent());
         } else {
            if (var5 != null) {
               var2 = var5.getResId();
            } else {
               var2 = 0;
            }

            var8 = new Notification.Action.Builder(var2, var1.getTitle(), var1.getActionIntent());
         }

         if (var1.getRemoteInputs() != null) {
            android.app.RemoteInput[] var6 = RemoteInput.fromCompat(var1.getRemoteInputs());
            int var4 = var6.length;

            for(var2 = var3; var2 < var4; ++var2) {
               var8.addRemoteInput(var6[var2]);
            }
         }

         Bundle var9;
         if (var1.getExtras() != null) {
            var9 = new Bundle(var1.getExtras());
         } else {
            var9 = new Bundle();
         }

         var9.putBoolean("android.support.allowGeneratedReplies", var1.getAllowGeneratedReplies());
         if (VERSION.SDK_INT >= 24) {
            var8.setAllowGeneratedReplies(var1.getAllowGeneratedReplies());
         }

         var9.putInt("android.support.action.semanticAction", var1.getSemanticAction());
         if (VERSION.SDK_INT >= 28) {
            var8.setSemanticAction(var1.getSemanticAction());
         }

         if (VERSION.SDK_INT >= 29) {
            var8.setContextual(var1.isContextual());
         }

         var9.putBoolean("android.support.action.showsUserInterface", var1.getShowsUserInterface());
         var8.addExtras(var9);
         this.mBuilder.addAction(var8.build());
      } else if (VERSION.SDK_INT >= 16) {
         this.mActionExtrasList.add(NotificationCompatJellybean.writeActionAndGetExtras(this.mBuilder, var1));
      }

   }

   private static List combineLists(List var0, List var1) {
      if (var0 == null) {
         return var1;
      } else if (var1 == null) {
         return var0;
      } else {
         ArraySet var2 = new ArraySet(var0.size() + var1.size());
         var2.addAll((Collection)var0);
         var2.addAll((Collection)var1);
         return new ArrayList(var2);
      }
   }

   private static List getPeople(List var0) {
      if (var0 == null) {
         return null;
      } else {
         ArrayList var1 = new ArrayList(var0.size());
         Iterator var2 = var0.iterator();

         while(var2.hasNext()) {
            var1.add(((Person)var2.next()).resolveToLegacyUri());
         }

         return var1;
      }
   }

   private void removeSoundAndVibration(Notification var1) {
      var1.sound = null;
      var1.vibrate = null;
      var1.defaults &= -2;
      var1.defaults &= -3;
   }

   public Notification build() {
      NotificationCompat.Style var2 = this.mBuilderCompat.mStyle;
      if (var2 != null) {
         var2.apply(this);
      }

      RemoteViews var1;
      if (var2 != null) {
         var1 = var2.makeContentView(this);
      } else {
         var1 = null;
      }

      Notification var3 = this.buildInternal();
      if (var1 != null) {
         var3.contentView = var1;
      } else if (this.mBuilderCompat.mContentView != null) {
         var3.contentView = this.mBuilderCompat.mContentView;
      }

      if (VERSION.SDK_INT >= 16 && var2 != null) {
         var1 = var2.makeBigContentView(this);
         if (var1 != null) {
            var3.bigContentView = var1;
         }
      }

      if (VERSION.SDK_INT >= 21 && var2 != null) {
         var1 = this.mBuilderCompat.mStyle.makeHeadsUpContentView(this);
         if (var1 != null) {
            var3.headsUpContentView = var1;
         }
      }

      if (VERSION.SDK_INT >= 16 && var2 != null) {
         Bundle var4 = NotificationCompat.getExtras(var3);
         if (var4 != null) {
            var2.addCompatExtras(var4);
         }
      }

      return var3;
   }

   protected Notification buildInternal() {
      if (VERSION.SDK_INT >= 26) {
         return this.mBuilder.build();
      } else {
         Notification var1;
         if (VERSION.SDK_INT >= 24) {
            var1 = this.mBuilder.build();
            if (this.mGroupAlertBehavior != 0) {
               if (var1.getGroup() != null && (var1.flags & 512) != 0 && this.mGroupAlertBehavior == 2) {
                  this.removeSoundAndVibration(var1);
               }

               if (var1.getGroup() != null && (var1.flags & 512) == 0 && this.mGroupAlertBehavior == 1) {
                  this.removeSoundAndVibration(var1);
               }
            }

            return var1;
         } else {
            RemoteViews var8;
            if (VERSION.SDK_INT >= 21) {
               this.mBuilder.setExtras(this.mExtras);
               var1 = this.mBuilder.build();
               var8 = this.mContentView;
               if (var8 != null) {
                  var1.contentView = var8;
               }

               var8 = this.mBigContentView;
               if (var8 != null) {
                  var1.bigContentView = var8;
               }

               var8 = this.mHeadsUpContentView;
               if (var8 != null) {
                  var1.headsUpContentView = var8;
               }

               if (this.mGroupAlertBehavior != 0) {
                  if (var1.getGroup() != null && (var1.flags & 512) != 0 && this.mGroupAlertBehavior == 2) {
                     this.removeSoundAndVibration(var1);
                  }

                  if (var1.getGroup() != null && (var1.flags & 512) == 0 && this.mGroupAlertBehavior == 1) {
                     this.removeSoundAndVibration(var1);
                  }
               }

               return var1;
            } else if (VERSION.SDK_INT >= 20) {
               this.mBuilder.setExtras(this.mExtras);
               var1 = this.mBuilder.build();
               var8 = this.mContentView;
               if (var8 != null) {
                  var1.contentView = var8;
               }

               var8 = this.mBigContentView;
               if (var8 != null) {
                  var1.bigContentView = var8;
               }

               if (this.mGroupAlertBehavior != 0) {
                  if (var1.getGroup() != null && (var1.flags & 512) != 0 && this.mGroupAlertBehavior == 2) {
                     this.removeSoundAndVibration(var1);
                  }

                  if (var1.getGroup() != null && (var1.flags & 512) == 0 && this.mGroupAlertBehavior == 1) {
                     this.removeSoundAndVibration(var1);
                  }
               }

               return var1;
            } else if (VERSION.SDK_INT >= 19) {
               SparseArray var6 = NotificationCompatJellybean.buildActionExtrasMap(this.mActionExtrasList);
               if (var6 != null) {
                  this.mExtras.putSparseParcelableArray("android.support.actionExtras", var6);
               }

               this.mBuilder.setExtras(this.mExtras);
               var1 = this.mBuilder.build();
               var8 = this.mContentView;
               if (var8 != null) {
                  var1.contentView = var8;
               }

               var8 = this.mBigContentView;
               if (var8 != null) {
                  var1.bigContentView = var8;
               }

               return var1;
            } else if (VERSION.SDK_INT >= 16) {
               var1 = this.mBuilder.build();
               Bundle var4 = NotificationCompat.getExtras(var1);
               Bundle var3 = new Bundle(this.mExtras);
               Iterator var5 = this.mExtras.keySet().iterator();

               while(var5.hasNext()) {
                  String var2 = (String)var5.next();
                  if (var4.containsKey(var2)) {
                     var3.remove(var2);
                  }
               }

               var4.putAll(var3);
               SparseArray var7 = NotificationCompatJellybean.buildActionExtrasMap(this.mActionExtrasList);
               if (var7 != null) {
                  NotificationCompat.getExtras(var1).putSparseParcelableArray("android.support.actionExtras", var7);
               }

               var8 = this.mContentView;
               if (var8 != null) {
                  var1.contentView = var8;
               }

               var8 = this.mBigContentView;
               if (var8 != null) {
                  var1.bigContentView = var8;
               }

               return var1;
            } else {
               return this.mBuilder.getNotification();
            }
         }
      }
   }

   public Notification.Builder getBuilder() {
      return this.mBuilder;
   }

   Context getContext() {
      return this.mContext;
   }
}
