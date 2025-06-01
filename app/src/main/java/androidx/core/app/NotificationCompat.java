package androidx.core.app;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.LocusId;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Bitmap.Config;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.media.AudioAttributes;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.SystemClock;
import android.os.Build.VERSION;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.TextAppearanceSpan;
import android.util.SparseArray;
import android.widget.RemoteViews;
import androidx.core.R;
import androidx.core.content.LocusIdCompat;
import androidx.core.content.pm.ShortcutInfoCompat;
import androidx.core.graphics.drawable.IconCompat;
import androidx.core.text.BidiFormatter;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class NotificationCompat {
   public static final int BADGE_ICON_LARGE = 2;
   public static final int BADGE_ICON_NONE = 0;
   public static final int BADGE_ICON_SMALL = 1;
   public static final String CATEGORY_ALARM = "alarm";
   public static final String CATEGORY_CALL = "call";
   public static final String CATEGORY_EMAIL = "email";
   public static final String CATEGORY_ERROR = "err";
   public static final String CATEGORY_EVENT = "event";
   public static final String CATEGORY_LOCATION_SHARING = "location_sharing";
   public static final String CATEGORY_MESSAGE = "msg";
   public static final String CATEGORY_MISSED_CALL = "missed_call";
   public static final String CATEGORY_NAVIGATION = "navigation";
   public static final String CATEGORY_PROGRESS = "progress";
   public static final String CATEGORY_PROMO = "promo";
   public static final String CATEGORY_RECOMMENDATION = "recommendation";
   public static final String CATEGORY_REMINDER = "reminder";
   public static final String CATEGORY_SERVICE = "service";
   public static final String CATEGORY_SOCIAL = "social";
   public static final String CATEGORY_STATUS = "status";
   public static final String CATEGORY_STOPWATCH = "stopwatch";
   public static final String CATEGORY_SYSTEM = "sys";
   public static final String CATEGORY_TRANSPORT = "transport";
   public static final String CATEGORY_WORKOUT = "workout";
   public static final int COLOR_DEFAULT = 0;
   public static final int DEFAULT_ALL = -1;
   public static final int DEFAULT_LIGHTS = 4;
   public static final int DEFAULT_SOUND = 1;
   public static final int DEFAULT_VIBRATE = 2;
   public static final String EXTRA_AUDIO_CONTENTS_URI = "android.audioContents";
   public static final String EXTRA_BACKGROUND_IMAGE_URI = "android.backgroundImageUri";
   public static final String EXTRA_BIG_TEXT = "android.bigText";
   public static final String EXTRA_CHANNEL_GROUP_ID = "android.intent.extra.CHANNEL_GROUP_ID";
   public static final String EXTRA_CHANNEL_ID = "android.intent.extra.CHANNEL_ID";
   public static final String EXTRA_CHRONOMETER_COUNT_DOWN = "android.chronometerCountDown";
   public static final String EXTRA_COLORIZED = "android.colorized";
   public static final String EXTRA_COMPACT_ACTIONS = "android.compactActions";
   public static final String EXTRA_COMPAT_TEMPLATE = "androidx.core.app.extra.COMPAT_TEMPLATE";
   public static final String EXTRA_CONVERSATION_TITLE = "android.conversationTitle";
   public static final String EXTRA_HIDDEN_CONVERSATION_TITLE = "android.hiddenConversationTitle";
   public static final String EXTRA_HISTORIC_MESSAGES = "android.messages.historic";
   public static final String EXTRA_INFO_TEXT = "android.infoText";
   public static final String EXTRA_IS_GROUP_CONVERSATION = "android.isGroupConversation";
   public static final String EXTRA_LARGE_ICON = "android.largeIcon";
   public static final String EXTRA_LARGE_ICON_BIG = "android.largeIcon.big";
   public static final String EXTRA_MEDIA_SESSION = "android.mediaSession";
   public static final String EXTRA_MESSAGES = "android.messages";
   public static final String EXTRA_MESSAGING_STYLE_USER = "android.messagingStyleUser";
   public static final String EXTRA_NOTIFICATION_ID = "android.intent.extra.NOTIFICATION_ID";
   public static final String EXTRA_NOTIFICATION_TAG = "android.intent.extra.NOTIFICATION_TAG";
   @Deprecated
   public static final String EXTRA_PEOPLE = "android.people";
   public static final String EXTRA_PEOPLE_LIST = "android.people.list";
   public static final String EXTRA_PICTURE = "android.picture";
   public static final String EXTRA_PROGRESS = "android.progress";
   public static final String EXTRA_PROGRESS_INDETERMINATE = "android.progressIndeterminate";
   public static final String EXTRA_PROGRESS_MAX = "android.progressMax";
   public static final String EXTRA_REMOTE_INPUT_HISTORY = "android.remoteInputHistory";
   public static final String EXTRA_SELF_DISPLAY_NAME = "android.selfDisplayName";
   public static final String EXTRA_SHOW_BIG_PICTURE_WHEN_COLLAPSED = "android.showBigPictureWhenCollapsed";
   public static final String EXTRA_SHOW_CHRONOMETER = "android.showChronometer";
   public static final String EXTRA_SHOW_WHEN = "android.showWhen";
   public static final String EXTRA_SMALL_ICON = "android.icon";
   public static final String EXTRA_SUB_TEXT = "android.subText";
   public static final String EXTRA_SUMMARY_TEXT = "android.summaryText";
   public static final String EXTRA_TEMPLATE = "android.template";
   public static final String EXTRA_TEXT = "android.text";
   public static final String EXTRA_TEXT_LINES = "android.textLines";
   public static final String EXTRA_TITLE = "android.title";
   public static final String EXTRA_TITLE_BIG = "android.title.big";
   public static final int FLAG_AUTO_CANCEL = 16;
   public static final int FLAG_BUBBLE = 4096;
   public static final int FLAG_FOREGROUND_SERVICE = 64;
   public static final int FLAG_GROUP_SUMMARY = 512;
   @Deprecated
   public static final int FLAG_HIGH_PRIORITY = 128;
   public static final int FLAG_INSISTENT = 4;
   public static final int FLAG_LOCAL_ONLY = 256;
   public static final int FLAG_NO_CLEAR = 32;
   public static final int FLAG_ONGOING_EVENT = 2;
   public static final int FLAG_ONLY_ALERT_ONCE = 8;
   public static final int FLAG_SHOW_LIGHTS = 1;
   public static final int FOREGROUND_SERVICE_DEFAULT = 0;
   public static final int FOREGROUND_SERVICE_DEFERRED = 2;
   public static final int FOREGROUND_SERVICE_IMMEDIATE = 1;
   public static final int GROUP_ALERT_ALL = 0;
   public static final int GROUP_ALERT_CHILDREN = 2;
   public static final int GROUP_ALERT_SUMMARY = 1;
   public static final String GROUP_KEY_SILENT = "silent";
   public static final String INTENT_CATEGORY_NOTIFICATION_PREFERENCES = "android.intent.category.NOTIFICATION_PREFERENCES";
   public static final int PRIORITY_DEFAULT = 0;
   public static final int PRIORITY_HIGH = 1;
   public static final int PRIORITY_LOW = -1;
   public static final int PRIORITY_MAX = 2;
   public static final int PRIORITY_MIN = -2;
   public static final int STREAM_DEFAULT = -1;
   public static final int VISIBILITY_PRIVATE = 0;
   public static final int VISIBILITY_PUBLIC = 1;
   public static final int VISIBILITY_SECRET = -1;

   public static Action getAction(Notification var0, int var1) {
      if (VERSION.SDK_INT >= 20) {
         return getActionCompatFromAction(var0.actions[var1]);
      } else {
         int var2 = VERSION.SDK_INT;
         Object var3 = null;
         if (var2 >= 19) {
            Notification.Action var4 = var0.actions[var1];
            SparseArray var5 = var0.extras.getSparseParcelableArray("android.support.actionExtras");
            Bundle var6 = (Bundle)var3;
            if (var5 != null) {
               var6 = (Bundle)var5.get(var1);
            }

            return NotificationCompatJellybean.readAction(var4.icon, var4.title, var4.actionIntent, var6);
         } else {
            return VERSION.SDK_INT >= 16 ? NotificationCompatJellybean.getAction(var0, var1) : null;
         }
      }
   }

   static Action getActionCompatFromAction(Notification.Action var0) {
      android.app.RemoteInput[] var9 = var0.getRemoteInputs();
      IconCompat var7 = null;
      boolean var4 = false;
      int var1;
      boolean var3;
      RemoteInput[] var6;
      if (var9 == null) {
         var6 = null;
      } else {
         var6 = new RemoteInput[var9.length];

         for(var1 = 0; var1 < var9.length; ++var1) {
            android.app.RemoteInput var11 = var9[var1];
            String var10 = var11.getResultKey();
            CharSequence var12 = var11.getLabel();
            CharSequence[] var8 = var11.getChoices();
            var3 = var11.getAllowFreeFormInput();
            int var2;
            if (VERSION.SDK_INT >= 29) {
               var2 = var11.getEditChoicesBeforeSending();
            } else {
               var2 = 0;
            }

            var6[var1] = new RemoteInput(var10, var12, var8, var3, var2, var11.getExtras(), (Set)null);
         }
      }

      if (VERSION.SDK_INT >= 24) {
         if (!var0.getExtras().getBoolean("android.support.allowGeneratedReplies") && !var0.getAllowGeneratedReplies()) {
            var3 = false;
         } else {
            var3 = true;
         }
      } else {
         var3 = var0.getExtras().getBoolean("android.support.allowGeneratedReplies");
      }

      boolean var5 = var0.getExtras().getBoolean("android.support.action.showsUserInterface", true);
      if (VERSION.SDK_INT >= 28) {
         var1 = var0.getSemanticAction();
      } else {
         var1 = var0.getExtras().getInt("android.support.action.semanticAction", 0);
      }

      if (VERSION.SDK_INT >= 29) {
         var4 = var0.isContextual();
      }

      if (VERSION.SDK_INT >= 23) {
         if (var0.getIcon() == null && var0.icon != 0) {
            return new Action(var0.icon, var0.title, var0.actionIntent, var0.getExtras(), var6, (RemoteInput[])null, var3, var1, var5, var4);
         } else {
            if (var0.getIcon() != null) {
               var7 = IconCompat.createFromIconOrNullIfZeroResId(var0.getIcon());
            }

            return new Action(var7, var0.title, var0.actionIntent, var0.getExtras(), var6, (RemoteInput[])null, var3, var1, var5, var4);
         }
      } else {
         return new Action(var0.icon, var0.title, var0.actionIntent, var0.getExtras(), var6, (RemoteInput[])null, var3, var1, var5, var4);
      }
   }

   public static int getActionCount(Notification var0) {
      int var2 = VERSION.SDK_INT;
      int var1 = 0;
      if (var2 >= 19) {
         if (var0.actions != null) {
            var1 = var0.actions.length;
         }

         return var1;
      } else {
         return VERSION.SDK_INT >= 16 ? NotificationCompatJellybean.getActionCount(var0) : 0;
      }
   }

   public static boolean getAllowSystemGeneratedContextualActions(Notification var0) {
      return VERSION.SDK_INT >= 29 ? var0.getAllowSystemGeneratedContextualActions() : false;
   }

   public static boolean getAutoCancel(Notification var0) {
      boolean var1;
      if ((var0.flags & 16) != 0) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public static int getBadgeIconType(Notification var0) {
      return VERSION.SDK_INT >= 26 ? var0.getBadgeIconType() : 0;
   }

   public static BubbleMetadata getBubbleMetadata(Notification var0) {
      return VERSION.SDK_INT >= 29 ? NotificationCompat.BubbleMetadata.fromPlatform(var0.getBubbleMetadata()) : null;
   }

   public static String getCategory(Notification var0) {
      return VERSION.SDK_INT >= 21 ? var0.category : null;
   }

   public static String getChannelId(Notification var0) {
      return VERSION.SDK_INT >= 26 ? var0.getChannelId() : null;
   }

   public static int getColor(Notification var0) {
      return VERSION.SDK_INT >= 21 ? var0.color : 0;
   }

   public static CharSequence getContentInfo(Notification var0) {
      return var0.extras.getCharSequence("android.infoText");
   }

   public static CharSequence getContentText(Notification var0) {
      return var0.extras.getCharSequence("android.text");
   }

   public static CharSequence getContentTitle(Notification var0) {
      return var0.extras.getCharSequence("android.title");
   }

   public static Bundle getExtras(Notification var0) {
      if (VERSION.SDK_INT >= 19) {
         return var0.extras;
      } else {
         return VERSION.SDK_INT >= 16 ? NotificationCompatJellybean.getExtras(var0) : null;
      }
   }

   public static String getGroup(Notification var0) {
      if (VERSION.SDK_INT >= 20) {
         return var0.getGroup();
      } else if (VERSION.SDK_INT >= 19) {
         return var0.extras.getString("android.support.groupKey");
      } else {
         return VERSION.SDK_INT >= 16 ? NotificationCompatJellybean.getExtras(var0).getString("android.support.groupKey") : null;
      }
   }

   public static int getGroupAlertBehavior(Notification var0) {
      return VERSION.SDK_INT >= 26 ? var0.getGroupAlertBehavior() : 0;
   }

   static boolean getHighPriority(Notification var0) {
      boolean var1;
      if ((var0.flags & 128) != 0) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public static List getInvisibleActions(Notification var0) {
      ArrayList var2 = new ArrayList();
      if (VERSION.SDK_INT >= 19) {
         Bundle var3 = var0.extras.getBundle("android.car.EXTENSIONS");
         if (var3 == null) {
            return var2;
         }

         var3 = var3.getBundle("invisible_actions");
         if (var3 != null) {
            for(int var1 = 0; var1 < var3.size(); ++var1) {
               var2.add(NotificationCompatJellybean.getActionFromBundle(var3.getBundle(Integer.toString(var1))));
            }
         }
      }

      return var2;
   }

   public static boolean getLocalOnly(Notification var0) {
      int var1 = VERSION.SDK_INT;
      boolean var2 = false;
      if (var1 >= 20) {
         if ((var0.flags & 256) != 0) {
            var2 = true;
         }

         return var2;
      } else if (VERSION.SDK_INT >= 19) {
         return var0.extras.getBoolean("android.support.localOnly");
      } else {
         return VERSION.SDK_INT >= 16 ? NotificationCompatJellybean.getExtras(var0).getBoolean("android.support.localOnly") : false;
      }
   }

   public static LocusIdCompat getLocusId(Notification var0) {
      int var1 = VERSION.SDK_INT;
      Object var3 = null;
      LocusIdCompat var2 = (LocusIdCompat)var3;
      if (var1 >= 29) {
         LocusId var4 = var0.getLocusId();
         if (var4 == null) {
            var2 = (LocusIdCompat)var3;
         } else {
            var2 = LocusIdCompat.toLocusIdCompat(var4);
         }
      }

      return var2;
   }

   static Notification[] getNotificationArrayFromBundle(Bundle var0, String var1) {
      Parcelable[] var4 = var0.getParcelableArray(var1);
      if (!(var4 instanceof Notification[]) && var4 != null) {
         Notification[] var3 = new Notification[var4.length];

         for(int var2 = 0; var2 < var4.length; ++var2) {
            var3[var2] = (Notification)var4[var2];
         }

         var0.putParcelableArray(var1, var3);
         return var3;
      } else {
         return (Notification[])var4;
      }
   }

   public static boolean getOngoing(Notification var0) {
      boolean var1;
      if ((var0.flags & 2) != 0) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public static boolean getOnlyAlertOnce(Notification var0) {
      boolean var1;
      if ((var0.flags & 8) != 0) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public static List getPeople(Notification var0) {
      ArrayList var3 = new ArrayList();
      if (VERSION.SDK_INT >= 28) {
         ArrayList var5 = var0.extras.getParcelableArrayList("android.people.list");
         if (var5 != null && !var5.isEmpty()) {
            Iterator var6 = var5.iterator();

            while(var6.hasNext()) {
               var3.add(Person.fromAndroidPerson((android.app.Person)var6.next()));
            }
         }
      } else if (VERSION.SDK_INT >= 19) {
         String[] var4 = var0.extras.getStringArray("android.people");
         if (var4 != null && var4.length != 0) {
            int var2 = var4.length;

            for(int var1 = 0; var1 < var2; ++var1) {
               String var7 = var4[var1];
               var3.add((new Person.Builder()).setUri(var7).build());
            }
         }
      }

      return var3;
   }

   public static Notification getPublicVersion(Notification var0) {
      return VERSION.SDK_INT >= 21 ? var0.publicVersion : null;
   }

   public static CharSequence getSettingsText(Notification var0) {
      return VERSION.SDK_INT >= 26 ? var0.getSettingsText() : null;
   }

   public static String getShortcutId(Notification var0) {
      return VERSION.SDK_INT >= 26 ? var0.getShortcutId() : null;
   }

   public static boolean getShowWhen(Notification var0) {
      return var0.extras.getBoolean("android.showWhen");
   }

   public static String getSortKey(Notification var0) {
      if (VERSION.SDK_INT >= 20) {
         return var0.getSortKey();
      } else if (VERSION.SDK_INT >= 19) {
         return var0.extras.getString("android.support.sortKey");
      } else {
         return VERSION.SDK_INT >= 16 ? NotificationCompatJellybean.getExtras(var0).getString("android.support.sortKey") : null;
      }
   }

   public static CharSequence getSubText(Notification var0) {
      return var0.extras.getCharSequence("android.subText");
   }

   public static long getTimeoutAfter(Notification var0) {
      return VERSION.SDK_INT >= 26 ? var0.getTimeoutAfter() : 0L;
   }

   public static boolean getUsesChronometer(Notification var0) {
      return var0.extras.getBoolean("android.showChronometer");
   }

   public static int getVisibility(Notification var0) {
      return VERSION.SDK_INT >= 21 ? var0.visibility : 0;
   }

   public static boolean isGroupSummary(Notification var0) {
      int var1 = VERSION.SDK_INT;
      boolean var2 = false;
      if (var1 >= 20) {
         if ((var0.flags & 512) != 0) {
            var2 = true;
         }

         return var2;
      } else if (VERSION.SDK_INT >= 19) {
         return var0.extras.getBoolean("android.support.isGroupSummary");
      } else {
         return VERSION.SDK_INT >= 16 ? NotificationCompatJellybean.getExtras(var0).getBoolean("android.support.isGroupSummary") : false;
      }
   }

   public static class Action {
      static final String EXTRA_SEMANTIC_ACTION = "android.support.action.semanticAction";
      static final String EXTRA_SHOWS_USER_INTERFACE = "android.support.action.showsUserInterface";
      public static final int SEMANTIC_ACTION_ARCHIVE = 5;
      public static final int SEMANTIC_ACTION_CALL = 10;
      public static final int SEMANTIC_ACTION_DELETE = 4;
      public static final int SEMANTIC_ACTION_MARK_AS_READ = 2;
      public static final int SEMANTIC_ACTION_MARK_AS_UNREAD = 3;
      public static final int SEMANTIC_ACTION_MUTE = 6;
      public static final int SEMANTIC_ACTION_NONE = 0;
      public static final int SEMANTIC_ACTION_REPLY = 1;
      public static final int SEMANTIC_ACTION_THUMBS_DOWN = 9;
      public static final int SEMANTIC_ACTION_THUMBS_UP = 8;
      public static final int SEMANTIC_ACTION_UNMUTE = 7;
      public PendingIntent actionIntent;
      @Deprecated
      public int icon;
      private boolean mAllowGeneratedReplies;
      private final RemoteInput[] mDataOnlyRemoteInputs;
      final Bundle mExtras;
      private IconCompat mIcon;
      private final boolean mIsContextual;
      private final RemoteInput[] mRemoteInputs;
      private final int mSemanticAction;
      boolean mShowsUserInterface;
      public CharSequence title;

      public Action(int var1, CharSequence var2, PendingIntent var3) {
         IconCompat var4 = null;
         if (var1 != 0) {
            var4 = IconCompat.createWithResource((Resources)null, "", var1);
         }

         this(var4, var2, var3);
      }

      Action(int var1, CharSequence var2, PendingIntent var3, Bundle var4, RemoteInput[] var5, RemoteInput[] var6, boolean var7, int var8, boolean var9, boolean var10) {
         IconCompat var11 = null;
         if (var1 != 0) {
            var11 = IconCompat.createWithResource((Resources)null, "", var1);
         }

         this(var11, var2, var3, var4, var5, var6, var7, var8, var9, var10);
      }

      public Action(IconCompat var1, CharSequence var2, PendingIntent var3) {
         this(var1, var2, var3, new Bundle(), (RemoteInput[])null, (RemoteInput[])null, true, 0, true, false);
      }

      Action(IconCompat var1, CharSequence var2, PendingIntent var3, Bundle var4, RemoteInput[] var5, RemoteInput[] var6, boolean var7, int var8, boolean var9, boolean var10) {
         this.mShowsUserInterface = true;
         this.mIcon = var1;
         if (var1 != null && var1.getType() == 2) {
            this.icon = var1.getResId();
         }

         this.title = NotificationCompat.Builder.limitCharSequenceLength(var2);
         this.actionIntent = var3;
         if (var4 == null) {
            var4 = new Bundle();
         }

         this.mExtras = var4;
         this.mRemoteInputs = var5;
         this.mDataOnlyRemoteInputs = var6;
         this.mAllowGeneratedReplies = var7;
         this.mSemanticAction = var8;
         this.mShowsUserInterface = var9;
         this.mIsContextual = var10;
      }

      public PendingIntent getActionIntent() {
         return this.actionIntent;
      }

      public boolean getAllowGeneratedReplies() {
         return this.mAllowGeneratedReplies;
      }

      public RemoteInput[] getDataOnlyRemoteInputs() {
         return this.mDataOnlyRemoteInputs;
      }

      public Bundle getExtras() {
         return this.mExtras;
      }

      @Deprecated
      public int getIcon() {
         return this.icon;
      }

      public IconCompat getIconCompat() {
         if (this.mIcon == null) {
            int var1 = this.icon;
            if (var1 != 0) {
               this.mIcon = IconCompat.createWithResource((Resources)null, "", var1);
            }
         }

         return this.mIcon;
      }

      public RemoteInput[] getRemoteInputs() {
         return this.mRemoteInputs;
      }

      public int getSemanticAction() {
         return this.mSemanticAction;
      }

      public boolean getShowsUserInterface() {
         return this.mShowsUserInterface;
      }

      public CharSequence getTitle() {
         return this.title;
      }

      public boolean isContextual() {
         return this.mIsContextual;
      }

      public static final class Builder {
         private boolean mAllowGeneratedReplies;
         private final Bundle mExtras;
         private final IconCompat mIcon;
         private final PendingIntent mIntent;
         private boolean mIsContextual;
         private ArrayList mRemoteInputs;
         private int mSemanticAction;
         private boolean mShowsUserInterface;
         private final CharSequence mTitle;

         public Builder(int var1, CharSequence var2, PendingIntent var3) {
            IconCompat var4 = null;
            if (var1 != 0) {
               var4 = IconCompat.createWithResource((Resources)null, "", var1);
            }

            this(var4, var2, var3, new Bundle(), (RemoteInput[])null, true, 0, true, false);
         }

         public Builder(Action var1) {
            this(var1.getIconCompat(), var1.title, var1.actionIntent, new Bundle(var1.mExtras), var1.getRemoteInputs(), var1.getAllowGeneratedReplies(), var1.getSemanticAction(), var1.mShowsUserInterface, var1.isContextual());
         }

         public Builder(IconCompat var1, CharSequence var2, PendingIntent var3) {
            this(var1, var2, var3, new Bundle(), (RemoteInput[])null, true, 0, true, false);
         }

         private Builder(IconCompat var1, CharSequence var2, PendingIntent var3, Bundle var4, RemoteInput[] var5, boolean var6, int var7, boolean var8, boolean var9) {
            this.mAllowGeneratedReplies = true;
            this.mShowsUserInterface = true;
            this.mIcon = var1;
            this.mTitle = NotificationCompat.Builder.limitCharSequenceLength(var2);
            this.mIntent = var3;
            this.mExtras = var4;
            ArrayList var10;
            if (var5 == null) {
               var10 = null;
            } else {
               var10 = new ArrayList(Arrays.asList(var5));
            }

            this.mRemoteInputs = var10;
            this.mAllowGeneratedReplies = var6;
            this.mSemanticAction = var7;
            this.mShowsUserInterface = var8;
            this.mIsContextual = var9;
         }

         private void checkContextualActionNullFields() {
            if (this.mIsContextual) {
               if (this.mIntent == null) {
                  throw new NullPointerException("Contextual Actions must contain a valid PendingIntent");
               }
            }
         }

         public static Builder fromAndroidAction(Notification.Action var0) {
            Builder var3;
            if (VERSION.SDK_INT >= 23 && var0.getIcon() != null) {
               var3 = new Builder(IconCompat.createFromIcon(var0.getIcon()), var0.title, var0.actionIntent);
            } else {
               var3 = new Builder(var0.icon, var0.title, var0.actionIntent);
            }

            if (VERSION.SDK_INT >= 20) {
               android.app.RemoteInput[] var4 = var0.getRemoteInputs();
               if (var4 != null && var4.length != 0) {
                  int var2 = var4.length;

                  for(int var1 = 0; var1 < var2; ++var1) {
                     var3.addRemoteInput(RemoteInput.fromPlatform(var4[var1]));
                  }
               }
            }

            if (VERSION.SDK_INT >= 24) {
               var3.mAllowGeneratedReplies = var0.getAllowGeneratedReplies();
            }

            if (VERSION.SDK_INT >= 28) {
               var3.setSemanticAction(var0.getSemanticAction());
            }

            if (VERSION.SDK_INT >= 29) {
               var3.setContextual(var0.isContextual());
            }

            return var3;
         }

         public Builder addExtras(Bundle var1) {
            if (var1 != null) {
               this.mExtras.putAll(var1);
            }

            return this;
         }

         public Builder addRemoteInput(RemoteInput var1) {
            if (this.mRemoteInputs == null) {
               this.mRemoteInputs = new ArrayList();
            }

            if (var1 != null) {
               this.mRemoteInputs.add(var1);
            }

            return this;
         }

         public Action build() {
            this.checkContextualActionNullFields();
            ArrayList var2 = new ArrayList();
            ArrayList var4 = new ArrayList();
            ArrayList var3 = this.mRemoteInputs;
            if (var3 != null) {
               Iterator var7 = var3.iterator();

               while(var7.hasNext()) {
                  RemoteInput var5 = (RemoteInput)var7.next();
                  if (var5.isDataOnly()) {
                     var2.add(var5);
                  } else {
                     var4.add(var5);
                  }
               }
            }

            boolean var1 = var2.isEmpty();
            RemoteInput[] var8 = null;
            RemoteInput[] var6;
            if (var1) {
               var6 = null;
            } else {
               var6 = (RemoteInput[])var2.toArray(new RemoteInput[var2.size()]);
            }

            if (!var4.isEmpty()) {
               var8 = (RemoteInput[])var4.toArray(new RemoteInput[var4.size()]);
            }

            return new Action(this.mIcon, this.mTitle, this.mIntent, this.mExtras, var8, var6, this.mAllowGeneratedReplies, this.mSemanticAction, this.mShowsUserInterface, this.mIsContextual);
         }

         public Builder extend(Extender var1) {
            var1.extend(this);
            return this;
         }

         public Bundle getExtras() {
            return this.mExtras;
         }

         public Builder setAllowGeneratedReplies(boolean var1) {
            this.mAllowGeneratedReplies = var1;
            return this;
         }

         public Builder setContextual(boolean var1) {
            this.mIsContextual = var1;
            return this;
         }

         public Builder setSemanticAction(int var1) {
            this.mSemanticAction = var1;
            return this;
         }

         public Builder setShowsUserInterface(boolean var1) {
            this.mShowsUserInterface = var1;
            return this;
         }
      }

      public interface Extender {
         Builder extend(Builder var1);
      }

      @Retention(RetentionPolicy.SOURCE)
      public @interface SemanticAction {
      }

      public static final class WearableExtender implements Extender {
         private static final int DEFAULT_FLAGS = 1;
         private static final String EXTRA_WEARABLE_EXTENSIONS = "android.wearable.EXTENSIONS";
         private static final int FLAG_AVAILABLE_OFFLINE = 1;
         private static final int FLAG_HINT_DISPLAY_INLINE = 4;
         private static final int FLAG_HINT_LAUNCHES_ACTIVITY = 2;
         private static final String KEY_CANCEL_LABEL = "cancelLabel";
         private static final String KEY_CONFIRM_LABEL = "confirmLabel";
         private static final String KEY_FLAGS = "flags";
         private static final String KEY_IN_PROGRESS_LABEL = "inProgressLabel";
         private CharSequence mCancelLabel;
         private CharSequence mConfirmLabel;
         private int mFlags = 1;
         private CharSequence mInProgressLabel;

         public WearableExtender() {
         }

         public WearableExtender(Action var1) {
            Bundle var2 = var1.getExtras().getBundle("android.wearable.EXTENSIONS");
            if (var2 != null) {
               this.mFlags = var2.getInt("flags", 1);
               this.mInProgressLabel = var2.getCharSequence("inProgressLabel");
               this.mConfirmLabel = var2.getCharSequence("confirmLabel");
               this.mCancelLabel = var2.getCharSequence("cancelLabel");
            }

         }

         private void setFlag(int var1, boolean var2) {
            if (var2) {
               this.mFlags |= var1;
            } else {
               this.mFlags &= ~var1;
            }

         }

         public WearableExtender clone() {
            WearableExtender var1 = new WearableExtender();
            var1.mFlags = this.mFlags;
            var1.mInProgressLabel = this.mInProgressLabel;
            var1.mConfirmLabel = this.mConfirmLabel;
            var1.mCancelLabel = this.mCancelLabel;
            return var1;
         }

         public Builder extend(Builder var1) {
            Bundle var3 = new Bundle();
            int var2 = this.mFlags;
            if (var2 != 1) {
               var3.putInt("flags", var2);
            }

            CharSequence var4 = this.mInProgressLabel;
            if (var4 != null) {
               var3.putCharSequence("inProgressLabel", var4);
            }

            var4 = this.mConfirmLabel;
            if (var4 != null) {
               var3.putCharSequence("confirmLabel", var4);
            }

            var4 = this.mCancelLabel;
            if (var4 != null) {
               var3.putCharSequence("cancelLabel", var4);
            }

            var1.getExtras().putBundle("android.wearable.EXTENSIONS", var3);
            return var1;
         }

         @Deprecated
         public CharSequence getCancelLabel() {
            return this.mCancelLabel;
         }

         @Deprecated
         public CharSequence getConfirmLabel() {
            return this.mConfirmLabel;
         }

         public boolean getHintDisplayActionInline() {
            boolean var1;
            if ((this.mFlags & 4) != 0) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         public boolean getHintLaunchesActivity() {
            boolean var1;
            if ((this.mFlags & 2) != 0) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         @Deprecated
         public CharSequence getInProgressLabel() {
            return this.mInProgressLabel;
         }

         public boolean isAvailableOffline() {
            int var1 = this.mFlags;
            boolean var2 = true;
            if ((var1 & 1) == 0) {
               var2 = false;
            }

            return var2;
         }

         public WearableExtender setAvailableOffline(boolean var1) {
            this.setFlag(1, var1);
            return this;
         }

         @Deprecated
         public WearableExtender setCancelLabel(CharSequence var1) {
            this.mCancelLabel = var1;
            return this;
         }

         @Deprecated
         public WearableExtender setConfirmLabel(CharSequence var1) {
            this.mConfirmLabel = var1;
            return this;
         }

         public WearableExtender setHintDisplayActionInline(boolean var1) {
            this.setFlag(4, var1);
            return this;
         }

         public WearableExtender setHintLaunchesActivity(boolean var1) {
            this.setFlag(2, var1);
            return this;
         }

         @Deprecated
         public WearableExtender setInProgressLabel(CharSequence var1) {
            this.mInProgressLabel = var1;
            return this;
         }
      }
   }

   @Retention(RetentionPolicy.SOURCE)
   public @interface BadgeIconType {
   }

   public static class BigPictureStyle extends Style {
      private static final String TEMPLATE_CLASS_NAME = "androidx.core.app.NotificationCompat$BigPictureStyle";
      private IconCompat mBigLargeIcon;
      private boolean mBigLargeIconSet;
      private Bitmap mPicture;
      private boolean mShowBigPictureWhenCollapsed;

      public BigPictureStyle() {
      }

      public BigPictureStyle(Builder var1) {
         this.setBuilder(var1);
      }

      private static IconCompat asIconCompat(Parcelable var0) {
         if (var0 != null) {
            if (VERSION.SDK_INT >= 23 && var0 instanceof Icon) {
               return IconCompat.createFromIcon((Icon)var0);
            }

            if (var0 instanceof Bitmap) {
               return IconCompat.createWithBitmap((Bitmap)var0);
            }
         }

         return null;
      }

      public void apply(NotificationBuilderWithBuilderAccessor var1) {
         if (VERSION.SDK_INT >= 16) {
            Notification.BigPictureStyle var3 = (new Notification.BigPictureStyle(var1.getBuilder())).setBigContentTitle(this.mBigContentTitle).bigPicture(this.mPicture);
            if (this.mBigLargeIconSet) {
               IconCompat var4 = this.mBigLargeIcon;
               Context var2 = null;
               if (var4 == null) {
                  var3.bigLargeIcon((Bitmap)null);
               } else if (VERSION.SDK_INT >= 23) {
                  if (var1 instanceof NotificationCompatBuilder) {
                     var2 = ((NotificationCompatBuilder)var1).getContext();
                  }

                  var3.bigLargeIcon(this.mBigLargeIcon.toIcon(var2));
               } else if (this.mBigLargeIcon.getType() == 1) {
                  var3.bigLargeIcon(this.mBigLargeIcon.getBitmap());
               } else {
                  var3.bigLargeIcon((Bitmap)null);
               }
            }

            if (this.mSummaryTextSet) {
               var3.setSummaryText(this.mSummaryText);
            }

            if (VERSION.SDK_INT >= 31) {
               var3.showBigPictureWhenCollapsed(this.mShowBigPictureWhenCollapsed);
            }
         }

      }

      public BigPictureStyle bigLargeIcon(Bitmap var1) {
         IconCompat var2;
         if (var1 == null) {
            var2 = null;
         } else {
            var2 = IconCompat.createWithBitmap(var1);
         }

         this.mBigLargeIcon = var2;
         this.mBigLargeIconSet = true;
         return this;
      }

      public BigPictureStyle bigPicture(Bitmap var1) {
         this.mPicture = var1;
         return this;
      }

      protected void clearCompatExtraKeys(Bundle var1) {
         super.clearCompatExtraKeys(var1);
         var1.remove("android.largeIcon.big");
         var1.remove("android.picture");
         var1.remove("android.showBigPictureWhenCollapsed");
      }

      protected String getClassName() {
         return "androidx.core.app.NotificationCompat$BigPictureStyle";
      }

      protected void restoreFromCompatExtras(Bundle var1) {
         super.restoreFromCompatExtras(var1);
         if (var1.containsKey("android.largeIcon.big")) {
            this.mBigLargeIcon = asIconCompat(var1.getParcelable("android.largeIcon.big"));
            this.mBigLargeIconSet = true;
         }

         this.mPicture = (Bitmap)var1.getParcelable("android.picture");
         this.mShowBigPictureWhenCollapsed = var1.getBoolean("android.showBigPictureWhenCollapsed");
      }

      public BigPictureStyle setBigContentTitle(CharSequence var1) {
         this.mBigContentTitle = NotificationCompat.Builder.limitCharSequenceLength(var1);
         return this;
      }

      public BigPictureStyle setSummaryText(CharSequence var1) {
         this.mSummaryText = NotificationCompat.Builder.limitCharSequenceLength(var1);
         this.mSummaryTextSet = true;
         return this;
      }

      public BigPictureStyle showBigPictureWhenCollapsed(boolean var1) {
         this.mShowBigPictureWhenCollapsed = var1;
         return this;
      }

      private static class Api16Impl {
         static void setBigLargeIcon(Notification.BigPictureStyle var0, Bitmap var1) {
            var0.bigLargeIcon(var1);
         }

         static void setSummaryText(Notification.BigPictureStyle var0, CharSequence var1) {
            var0.setSummaryText(var1);
         }
      }

      private static class Api23Impl {
         static void setBigLargeIcon(Notification.BigPictureStyle var0, Icon var1) {
            var0.bigLargeIcon(var1);
         }
      }

      private static class Api31Impl {
         static void showBigPictureWhenCollapsed(Notification.BigPictureStyle var0, boolean var1) {
            var0.showBigPictureWhenCollapsed(var1);
         }
      }
   }

   public static class BigTextStyle extends Style {
      private static final String TEMPLATE_CLASS_NAME = "androidx.core.app.NotificationCompat$BigTextStyle";
      private CharSequence mBigText;

      public BigTextStyle() {
      }

      public BigTextStyle(Builder var1) {
         this.setBuilder(var1);
      }

      public void addCompatExtras(Bundle var1) {
         super.addCompatExtras(var1);
         if (VERSION.SDK_INT < 21) {
            var1.putCharSequence("android.bigText", this.mBigText);
         }

      }

      public void apply(NotificationBuilderWithBuilderAccessor var1) {
         if (VERSION.SDK_INT >= 16) {
            Notification.BigTextStyle var2 = (new Notification.BigTextStyle(var1.getBuilder())).setBigContentTitle(this.mBigContentTitle).bigText(this.mBigText);
            if (this.mSummaryTextSet) {
               var2.setSummaryText(this.mSummaryText);
            }
         }

      }

      public BigTextStyle bigText(CharSequence var1) {
         this.mBigText = NotificationCompat.Builder.limitCharSequenceLength(var1);
         return this;
      }

      protected void clearCompatExtraKeys(Bundle var1) {
         super.clearCompatExtraKeys(var1);
         var1.remove("android.bigText");
      }

      protected String getClassName() {
         return "androidx.core.app.NotificationCompat$BigTextStyle";
      }

      protected void restoreFromCompatExtras(Bundle var1) {
         super.restoreFromCompatExtras(var1);
         this.mBigText = var1.getCharSequence("android.bigText");
      }

      public BigTextStyle setBigContentTitle(CharSequence var1) {
         this.mBigContentTitle = NotificationCompat.Builder.limitCharSequenceLength(var1);
         return this;
      }

      public BigTextStyle setSummaryText(CharSequence var1) {
         this.mSummaryText = NotificationCompat.Builder.limitCharSequenceLength(var1);
         this.mSummaryTextSet = true;
         return this;
      }
   }

   public static final class BubbleMetadata {
      private static final int FLAG_AUTO_EXPAND_BUBBLE = 1;
      private static final int FLAG_SUPPRESS_NOTIFICATION = 2;
      private PendingIntent mDeleteIntent;
      private int mDesiredHeight;
      private int mDesiredHeightResId;
      private int mFlags;
      private IconCompat mIcon;
      private PendingIntent mPendingIntent;
      private String mShortcutId;

      private BubbleMetadata(PendingIntent var1, PendingIntent var2, IconCompat var3, int var4, int var5, int var6, String var7) {
         this.mPendingIntent = var1;
         this.mIcon = var3;
         this.mDesiredHeight = var4;
         this.mDesiredHeightResId = var5;
         this.mDeleteIntent = var2;
         this.mFlags = var6;
         this.mShortcutId = var7;
      }

      // $FF: synthetic method
      BubbleMetadata(PendingIntent var1, PendingIntent var2, IconCompat var3, int var4, int var5, int var6, String var7, Object var8) {
         this(var1, var2, var3, var4, var5, var6, var7);
      }

      public static BubbleMetadata fromPlatform(Notification.BubbleMetadata var0) {
         if (var0 == null) {
            return null;
         } else if (VERSION.SDK_INT >= 30) {
            return NotificationCompat.BubbleMetadata.Api30Impl.fromPlatform(var0);
         } else {
            return VERSION.SDK_INT == 29 ? NotificationCompat.BubbleMetadata.Api29Impl.fromPlatform(var0) : null;
         }
      }

      public static Notification.BubbleMetadata toPlatform(BubbleMetadata var0) {
         if (var0 == null) {
            return null;
         } else if (VERSION.SDK_INT >= 30) {
            return NotificationCompat.BubbleMetadata.Api30Impl.toPlatform(var0);
         } else {
            return VERSION.SDK_INT == 29 ? NotificationCompat.BubbleMetadata.Api29Impl.toPlatform(var0) : null;
         }
      }

      public boolean getAutoExpandBubble() {
         int var1 = this.mFlags;
         boolean var2 = true;
         if ((var1 & 1) == 0) {
            var2 = false;
         }

         return var2;
      }

      public PendingIntent getDeleteIntent() {
         return this.mDeleteIntent;
      }

      public int getDesiredHeight() {
         return this.mDesiredHeight;
      }

      public int getDesiredHeightResId() {
         return this.mDesiredHeightResId;
      }

      public IconCompat getIcon() {
         return this.mIcon;
      }

      public PendingIntent getIntent() {
         return this.mPendingIntent;
      }

      public String getShortcutId() {
         return this.mShortcutId;
      }

      public boolean isNotificationSuppressed() {
         boolean var1;
         if ((this.mFlags & 2) != 0) {
            var1 = true;
         } else {
            var1 = false;
         }

         return var1;
      }

      public void setFlags(int var1) {
         this.mFlags = var1;
      }

      private static class Api29Impl {
         static BubbleMetadata fromPlatform(Notification.BubbleMetadata var0) {
            if (var0 == null) {
               return null;
            } else if (var0.getIntent() == null) {
               return null;
            } else {
               Builder var1 = (new Builder(var0.getIntent(), IconCompat.createFromIcon(var0.getIcon()))).setAutoExpandBubble(var0.getAutoExpandBubble()).setDeleteIntent(var0.getDeleteIntent()).setSuppressNotification(var0.isNotificationSuppressed());
               if (var0.getDesiredHeight() != 0) {
                  var1.setDesiredHeight(var0.getDesiredHeight());
               }

               if (var0.getDesiredHeightResId() != 0) {
                  var1.setDesiredHeightResId(var0.getDesiredHeightResId());
               }

               return var1.build();
            }
         }

         static Notification.BubbleMetadata toPlatform(BubbleMetadata var0) {
            if (var0 == null) {
               return null;
            } else if (var0.getIntent() == null) {
               return null;
            } else {
               Notification.Builder var1 = (new Notification.Builder()).setIcon(var0.getIcon().toIcon()).setIntent(var0.getIntent()).setDeleteIntent(var0.getDeleteIntent()).setAutoExpandBubble(var0.getAutoExpandBubble()).setSuppressNotification(var0.isNotificationSuppressed());
               if (var0.getDesiredHeight() != 0) {
                  var1.setDesiredHeight(var0.getDesiredHeight());
               }

               if (var0.getDesiredHeightResId() != 0) {
                  var1.setDesiredHeightResId(var0.getDesiredHeightResId());
               }

               return var1.build();
            }
         }
      }

      private static class Api30Impl {
         static BubbleMetadata fromPlatform(Notification.BubbleMetadata var0) {
            if (var0 == null) {
               return null;
            } else {
               Builder var1;
               if (var0.getShortcutId() != null) {
                  var1 = new Builder(var0.getShortcutId());
               } else {
                  var1 = new Builder(var0.getIntent(), IconCompat.createFromIcon(var0.getIcon()));
               }

               var1.setAutoExpandBubble(var0.getAutoExpandBubble()).setDeleteIntent(var0.getDeleteIntent()).setSuppressNotification(var0.isNotificationSuppressed());
               if (var0.getDesiredHeight() != 0) {
                  var1.setDesiredHeight(var0.getDesiredHeight());
               }

               if (var0.getDesiredHeightResId() != 0) {
                  var1.setDesiredHeightResId(var0.getDesiredHeightResId());
               }

               return var1.build();
            }
         }

         static Notification.BubbleMetadata toPlatform(BubbleMetadata var0) {
            if (var0 == null) {
               return null;
            } else {
               Notification.Builder var1;
               if (var0.getShortcutId() != null) {
                  var1 = new Notification.Builder(var0.getShortcutId());
               } else {
                  var1 = new Notification.Builder(var0.getIntent(), var0.getIcon().toIcon());
               }

               var1.setDeleteIntent(var0.getDeleteIntent()).setAutoExpandBubble(var0.getAutoExpandBubble()).setSuppressNotification(var0.isNotificationSuppressed());
               if (var0.getDesiredHeight() != 0) {
                  var1.setDesiredHeight(var0.getDesiredHeight());
               }

               if (var0.getDesiredHeightResId() != 0) {
                  var1.setDesiredHeightResId(var0.getDesiredHeightResId());
               }

               return var1.build();
            }
         }
      }

      public static final class Builder {
         private PendingIntent mDeleteIntent;
         private int mDesiredHeight;
         private int mDesiredHeightResId;
         private int mFlags;
         private IconCompat mIcon;
         private PendingIntent mPendingIntent;
         private String mShortcutId;

         @Deprecated
         public Builder() {
         }

         public Builder(PendingIntent var1, IconCompat var2) {
            if (var1 != null) {
               if (var2 != null) {
                  this.mPendingIntent = var1;
                  this.mIcon = var2;
               } else {
                  throw new NullPointerException("Bubbles require non-null icon");
               }
            } else {
               throw new NullPointerException("Bubble requires non-null pending intent");
            }
         }

         public Builder(String var1) {
            if (!TextUtils.isEmpty(var1)) {
               this.mShortcutId = var1;
            } else {
               throw new NullPointerException("Bubble requires a non-null shortcut id");
            }
         }

         private Builder setFlag(int var1, boolean var2) {
            if (var2) {
               this.mFlags |= var1;
            } else {
               this.mFlags &= ~var1;
            }

            return this;
         }

         public BubbleMetadata build() {
            String var1 = this.mShortcutId;
            if (var1 == null && this.mPendingIntent == null) {
               throw new NullPointerException("Must supply pending intent or shortcut to bubble");
            } else if (var1 == null && this.mIcon == null) {
               throw new NullPointerException("Must supply an icon or shortcut for the bubble");
            } else {
               BubbleMetadata var2 = new BubbleMetadata(this.mPendingIntent, this.mDeleteIntent, this.mIcon, this.mDesiredHeight, this.mDesiredHeightResId, this.mFlags, this.mShortcutId);
               var2.setFlags(this.mFlags);
               return var2;
            }
         }

         public Builder setAutoExpandBubble(boolean var1) {
            this.setFlag(1, var1);
            return this;
         }

         public Builder setDeleteIntent(PendingIntent var1) {
            this.mDeleteIntent = var1;
            return this;
         }

         public Builder setDesiredHeight(int var1) {
            this.mDesiredHeight = Math.max(var1, 0);
            this.mDesiredHeightResId = 0;
            return this;
         }

         public Builder setDesiredHeightResId(int var1) {
            this.mDesiredHeightResId = var1;
            this.mDesiredHeight = 0;
            return this;
         }

         public Builder setIcon(IconCompat var1) {
            if (this.mShortcutId == null) {
               if (var1 != null) {
                  this.mIcon = var1;
                  return this;
               } else {
                  throw new NullPointerException("Bubbles require non-null icon");
               }
            } else {
               throw new IllegalStateException("Created as a shortcut bubble, cannot set an Icon. Consider using BubbleMetadata.Builder(PendingIntent,Icon) instead.");
            }
         }

         public Builder setIntent(PendingIntent var1) {
            if (this.mShortcutId == null) {
               if (var1 != null) {
                  this.mPendingIntent = var1;
                  return this;
               } else {
                  throw new NullPointerException("Bubble requires non-null pending intent");
               }
            } else {
               throw new IllegalStateException("Created as a shortcut bubble, cannot set a PendingIntent. Consider using BubbleMetadata.Builder(PendingIntent,Icon) instead.");
            }
         }

         public Builder setSuppressNotification(boolean var1) {
            this.setFlag(2, var1);
            return this;
         }
      }
   }

   public static class Builder {
      private static final int MAX_CHARSEQUENCE_LENGTH = 5120;
      public ArrayList mActions;
      boolean mAllowSystemGeneratedContextualActions;
      int mBadgeIcon;
      RemoteViews mBigContentView;
      BubbleMetadata mBubbleMetadata;
      String mCategory;
      String mChannelId;
      boolean mChronometerCountDown;
      int mColor;
      boolean mColorized;
      boolean mColorizedSet;
      CharSequence mContentInfo;
      PendingIntent mContentIntent;
      CharSequence mContentText;
      CharSequence mContentTitle;
      RemoteViews mContentView;
      public Context mContext;
      Bundle mExtras;
      int mFgsDeferBehavior;
      PendingIntent mFullScreenIntent;
      int mGroupAlertBehavior;
      String mGroupKey;
      boolean mGroupSummary;
      RemoteViews mHeadsUpContentView;
      ArrayList mInvisibleActions;
      Bitmap mLargeIcon;
      boolean mLocalOnly;
      LocusIdCompat mLocusId;
      Notification mNotification;
      int mNumber;
      @Deprecated
      public ArrayList mPeople;
      public ArrayList mPersonList;
      int mPriority;
      int mProgress;
      boolean mProgressIndeterminate;
      int mProgressMax;
      Notification mPublicVersion;
      CharSequence[] mRemoteInputHistory;
      CharSequence mSettingsText;
      String mShortcutId;
      boolean mShowWhen;
      boolean mSilent;
      Icon mSmallIcon;
      String mSortKey;
      Style mStyle;
      CharSequence mSubText;
      RemoteViews mTickerView;
      long mTimeout;
      boolean mUseChronometer;
      int mVisibility;

      @Deprecated
      public Builder(Context var1) {
         String var2 = (String)null;
         this(var1, (String)null);
      }

      public Builder(Context var1, Notification var2) {
         this(var1, NotificationCompat.getChannelId(var2));
         Bundle var7 = var2.extras;
         Style var6 = NotificationCompat.Style.extractStyleFromNotification(var2);
         this.setContentTitle(NotificationCompat.getContentTitle(var2)).setContentText(NotificationCompat.getContentText(var2)).setContentInfo(NotificationCompat.getContentInfo(var2)).setSubText(NotificationCompat.getSubText(var2)).setSettingsText(NotificationCompat.getSettingsText(var2)).setStyle(var6).setContentIntent(var2.contentIntent).setGroup(NotificationCompat.getGroup(var2)).setGroupSummary(NotificationCompat.isGroupSummary(var2)).setLocusId(NotificationCompat.getLocusId(var2)).setWhen(var2.when).setShowWhen(NotificationCompat.getShowWhen(var2)).setUsesChronometer(NotificationCompat.getUsesChronometer(var2)).setAutoCancel(NotificationCompat.getAutoCancel(var2)).setOnlyAlertOnce(NotificationCompat.getOnlyAlertOnce(var2)).setOngoing(NotificationCompat.getOngoing(var2)).setLocalOnly(NotificationCompat.getLocalOnly(var2)).setLargeIcon(var2.largeIcon).setBadgeIconType(NotificationCompat.getBadgeIconType(var2)).setCategory(NotificationCompat.getCategory(var2)).setBubbleMetadata(NotificationCompat.getBubbleMetadata(var2)).setNumber(var2.number).setTicker(var2.tickerText).setContentIntent(var2.contentIntent).setDeleteIntent(var2.deleteIntent).setFullScreenIntent(var2.fullScreenIntent, NotificationCompat.getHighPriority(var2)).setSound(var2.sound, var2.audioStreamType).setVibrate(var2.vibrate).setLights(var2.ledARGB, var2.ledOnMS, var2.ledOffMS).setDefaults(var2.defaults).setPriority(var2.priority).setColor(NotificationCompat.getColor(var2)).setVisibility(NotificationCompat.getVisibility(var2)).setPublicVersion(NotificationCompat.getPublicVersion(var2)).setSortKey(NotificationCompat.getSortKey(var2)).setTimeoutAfter(NotificationCompat.getTimeoutAfter(var2)).setShortcutId(NotificationCompat.getShortcutId(var2)).setProgress(var7.getInt("android.progressMax"), var7.getInt("android.progress"), var7.getBoolean("android.progressIndeterminate")).setAllowSystemGeneratedContextualActions(NotificationCompat.getAllowSystemGeneratedContextualActions(var2)).setSmallIcon(var2.icon, var2.iconLevel).addExtras(getExtrasWithoutDuplicateData(var2, var6));
         if (VERSION.SDK_INT >= 23) {
            this.mSmallIcon = var2.getSmallIcon();
         }

         Notification.Action[] var10 = var2.actions;
         byte var4 = 0;
         int var3;
         int var5;
         if (var10 != null && var2.actions.length != 0) {
            var10 = var2.actions;
            var5 = var10.length;

            for(var3 = 0; var3 < var5; ++var3) {
               this.addAction(NotificationCompat.Action.Builder.fromAndroidAction(var10[var3]).build());
            }
         }

         if (VERSION.SDK_INT >= 21) {
            List var11 = NotificationCompat.getInvisibleActions(var2);
            if (!var11.isEmpty()) {
               Iterator var12 = var11.iterator();

               while(var12.hasNext()) {
                  this.addInvisibleAction((Action)var12.next());
               }
            }
         }

         String[] var13 = var2.extras.getStringArray("android.people");
         if (var13 != null && var13.length != 0) {
            var5 = var13.length;

            for(var3 = var4; var3 < var5; ++var3) {
               this.addPerson(var13[var3]);
            }
         }

         if (VERSION.SDK_INT >= 28) {
            ArrayList var8 = var2.extras.getParcelableArrayList("android.people.list");
            if (var8 != null && !var8.isEmpty()) {
               Iterator var9 = var8.iterator();

               while(var9.hasNext()) {
                  this.addPerson(Person.fromAndroidPerson((android.app.Person)var9.next()));
               }
            }
         }

         if (VERSION.SDK_INT >= 24 && var7.containsKey("android.chronometerCountDown")) {
            this.setChronometerCountDown(var7.getBoolean("android.chronometerCountDown"));
         }

         if (VERSION.SDK_INT >= 26 && var7.containsKey("android.colorized")) {
            this.setColorized(var7.getBoolean("android.colorized"));
         }

      }

      public Builder(Context var1, String var2) {
         this.mActions = new ArrayList();
         this.mPersonList = new ArrayList();
         this.mInvisibleActions = new ArrayList();
         this.mShowWhen = true;
         this.mLocalOnly = false;
         this.mColor = 0;
         this.mVisibility = 0;
         this.mBadgeIcon = 0;
         this.mGroupAlertBehavior = 0;
         this.mFgsDeferBehavior = 0;
         Notification var3 = new Notification();
         this.mNotification = var3;
         this.mContext = var1;
         this.mChannelId = var2;
         var3.when = System.currentTimeMillis();
         this.mNotification.audioStreamType = -1;
         this.mPriority = 0;
         this.mPeople = new ArrayList();
         this.mAllowSystemGeneratedContextualActions = true;
      }

      private static Bundle getExtrasWithoutDuplicateData(Notification var0, Style var1) {
         if (var0.extras == null) {
            return null;
         } else {
            Bundle var3 = new Bundle(var0.extras);
            var3.remove("android.title");
            var3.remove("android.text");
            var3.remove("android.infoText");
            var3.remove("android.subText");
            var3.remove("android.intent.extra.CHANNEL_ID");
            var3.remove("android.intent.extra.CHANNEL_GROUP_ID");
            var3.remove("android.showWhen");
            var3.remove("android.progress");
            var3.remove("android.progressMax");
            var3.remove("android.progressIndeterminate");
            var3.remove("android.chronometerCountDown");
            var3.remove("android.colorized");
            var3.remove("android.people.list");
            var3.remove("android.people");
            var3.remove("android.support.sortKey");
            var3.remove("android.support.groupKey");
            var3.remove("android.support.isGroupSummary");
            var3.remove("android.support.localOnly");
            var3.remove("android.support.actionExtras");
            Bundle var2 = var3.getBundle("android.car.EXTENSIONS");
            if (var2 != null) {
               var2 = new Bundle(var2);
               var2.remove("invisible_actions");
               var3.putBundle("android.car.EXTENSIONS", var2);
            }

            if (var1 != null) {
               var1.clearCompatExtraKeys(var3);
            }

            return var3;
         }
      }

      protected static CharSequence limitCharSequenceLength(CharSequence var0) {
         if (var0 == null) {
            return var0;
         } else {
            CharSequence var1 = var0;
            if (var0.length() > 5120) {
               var1 = var0.subSequence(0, 5120);
            }

            return var1;
         }
      }

      private Bitmap reduceLargeIconSize(Bitmap var1) {
         Bitmap var6 = var1;
         if (var1 != null) {
            if (VERSION.SDK_INT >= 27) {
               var6 = var1;
            } else {
               Resources var7 = this.mContext.getResources();
               int var4 = var7.getDimensionPixelSize(R.dimen.compat_notification_large_icon_max_width);
               int var5 = var7.getDimensionPixelSize(R.dimen.compat_notification_large_icon_max_height);
               if (var1.getWidth() <= var4 && var1.getHeight() <= var5) {
                  return var1;
               }

               double var2 = Math.min((double)var4 / (double)Math.max(1, var1.getWidth()), (double)var5 / (double)Math.max(1, var1.getHeight()));
               var6 = Bitmap.createScaledBitmap(var1, (int)Math.ceil((double)var1.getWidth() * var2), (int)Math.ceil((double)var1.getHeight() * var2), true);
            }
         }

         return var6;
      }

      private void setFlag(int var1, boolean var2) {
         Notification var3;
         if (var2) {
            var3 = this.mNotification;
            var3.flags |= var1;
         } else {
            var3 = this.mNotification;
            var3.flags &= ~var1;
         }

      }

      private boolean useExistingRemoteView() {
         Style var2 = this.mStyle;
         boolean var1;
         if (var2 != null && var2.displayCustomViewInline()) {
            var1 = false;
         } else {
            var1 = true;
         }

         return var1;
      }

      public Builder addAction(int var1, CharSequence var2, PendingIntent var3) {
         this.mActions.add(new Action(var1, var2, var3));
         return this;
      }

      public Builder addAction(Action var1) {
         if (var1 != null) {
            this.mActions.add(var1);
         }

         return this;
      }

      public Builder addExtras(Bundle var1) {
         if (var1 != null) {
            Bundle var2 = this.mExtras;
            if (var2 == null) {
               this.mExtras = new Bundle(var1);
            } else {
               var2.putAll(var1);
            }
         }

         return this;
      }

      public Builder addInvisibleAction(int var1, CharSequence var2, PendingIntent var3) {
         this.mInvisibleActions.add(new Action(var1, var2, var3));
         return this;
      }

      public Builder addInvisibleAction(Action var1) {
         if (var1 != null) {
            this.mInvisibleActions.add(var1);
         }

         return this;
      }

      public Builder addPerson(Person var1) {
         if (var1 != null) {
            this.mPersonList.add(var1);
         }

         return this;
      }

      @Deprecated
      public Builder addPerson(String var1) {
         if (var1 != null && !var1.isEmpty()) {
            this.mPeople.add(var1);
         }

         return this;
      }

      public Notification build() {
         return (new NotificationCompatBuilder(this)).build();
      }

      public Builder clearActions() {
         this.mActions.clear();
         return this;
      }

      public Builder clearInvisibleActions() {
         this.mInvisibleActions.clear();
         Bundle var1 = this.mExtras.getBundle("android.car.EXTENSIONS");
         if (var1 != null) {
            var1 = new Bundle(var1);
            var1.remove("invisible_actions");
            this.mExtras.putBundle("android.car.EXTENSIONS", var1);
         }

         return this;
      }

      public Builder clearPeople() {
         this.mPersonList.clear();
         this.mPeople.clear();
         return this;
      }

      public RemoteViews createBigContentView() {
         if (VERSION.SDK_INT < 16) {
            return null;
         } else if (this.mBigContentView != null && this.useExistingRemoteView()) {
            return this.mBigContentView;
         } else {
            NotificationCompatBuilder var1 = new NotificationCompatBuilder(this);
            Style var2 = this.mStyle;
            if (var2 != null) {
               RemoteViews var4 = var2.makeBigContentView(var1);
               if (var4 != null) {
                  return var4;
               }
            }

            Notification var3 = var1.build();
            return VERSION.SDK_INT >= 24 ? android.app.Notification.Builder.recoverBuilder(this.mContext, var3).createBigContentView() : var3.bigContentView;
         }
      }

      public RemoteViews createContentView() {
         if (this.mContentView != null && this.useExistingRemoteView()) {
            return this.mContentView;
         } else {
            NotificationCompatBuilder var1 = new NotificationCompatBuilder(this);
            Style var2 = this.mStyle;
            if (var2 != null) {
               RemoteViews var4 = var2.makeContentView(var1);
               if (var4 != null) {
                  return var4;
               }
            }

            Notification var3 = var1.build();
            return VERSION.SDK_INT >= 24 ? android.app.Notification.Builder.recoverBuilder(this.mContext, var3).createContentView() : var3.contentView;
         }
      }

      public RemoteViews createHeadsUpContentView() {
         if (VERSION.SDK_INT < 21) {
            return null;
         } else if (this.mHeadsUpContentView != null && this.useExistingRemoteView()) {
            return this.mHeadsUpContentView;
         } else {
            NotificationCompatBuilder var1 = new NotificationCompatBuilder(this);
            Style var2 = this.mStyle;
            if (var2 != null) {
               RemoteViews var4 = var2.makeHeadsUpContentView(var1);
               if (var4 != null) {
                  return var4;
               }
            }

            Notification var3 = var1.build();
            return VERSION.SDK_INT >= 24 ? android.app.Notification.Builder.recoverBuilder(this.mContext, var3).createHeadsUpContentView() : var3.headsUpContentView;
         }
      }

      public Builder extend(Extender var1) {
         var1.extend(this);
         return this;
      }

      public RemoteViews getBigContentView() {
         return this.mBigContentView;
      }

      public BubbleMetadata getBubbleMetadata() {
         return this.mBubbleMetadata;
      }

      public int getColor() {
         return this.mColor;
      }

      public RemoteViews getContentView() {
         return this.mContentView;
      }

      public Bundle getExtras() {
         if (this.mExtras == null) {
            this.mExtras = new Bundle();
         }

         return this.mExtras;
      }

      public int getForegroundServiceBehavior() {
         return this.mFgsDeferBehavior;
      }

      public RemoteViews getHeadsUpContentView() {
         return this.mHeadsUpContentView;
      }

      @Deprecated
      public Notification getNotification() {
         return this.build();
      }

      public int getPriority() {
         return this.mPriority;
      }

      public long getWhenIfShowing() {
         long var1;
         if (this.mShowWhen) {
            var1 = this.mNotification.when;
         } else {
            var1 = 0L;
         }

         return var1;
      }

      public Builder setAllowSystemGeneratedContextualActions(boolean var1) {
         this.mAllowSystemGeneratedContextualActions = var1;
         return this;
      }

      public Builder setAutoCancel(boolean var1) {
         this.setFlag(16, var1);
         return this;
      }

      public Builder setBadgeIconType(int var1) {
         this.mBadgeIcon = var1;
         return this;
      }

      public Builder setBubbleMetadata(BubbleMetadata var1) {
         this.mBubbleMetadata = var1;
         return this;
      }

      public Builder setCategory(String var1) {
         this.mCategory = var1;
         return this;
      }

      public Builder setChannelId(String var1) {
         this.mChannelId = var1;
         return this;
      }

      public Builder setChronometerCountDown(boolean var1) {
         this.mChronometerCountDown = var1;
         this.getExtras().putBoolean("android.chronometerCountDown", var1);
         return this;
      }

      public Builder setColor(int var1) {
         this.mColor = var1;
         return this;
      }

      public Builder setColorized(boolean var1) {
         this.mColorized = var1;
         this.mColorizedSet = true;
         return this;
      }

      public Builder setContent(RemoteViews var1) {
         this.mNotification.contentView = var1;
         return this;
      }

      public Builder setContentInfo(CharSequence var1) {
         this.mContentInfo = limitCharSequenceLength(var1);
         return this;
      }

      public Builder setContentIntent(PendingIntent var1) {
         this.mContentIntent = var1;
         return this;
      }

      public Builder setContentText(CharSequence var1) {
         this.mContentText = limitCharSequenceLength(var1);
         return this;
      }

      public Builder setContentTitle(CharSequence var1) {
         this.mContentTitle = limitCharSequenceLength(var1);
         return this;
      }

      public Builder setCustomBigContentView(RemoteViews var1) {
         this.mBigContentView = var1;
         return this;
      }

      public Builder setCustomContentView(RemoteViews var1) {
         this.mContentView = var1;
         return this;
      }

      public Builder setCustomHeadsUpContentView(RemoteViews var1) {
         this.mHeadsUpContentView = var1;
         return this;
      }

      public Builder setDefaults(int var1) {
         this.mNotification.defaults = var1;
         if ((var1 & 4) != 0) {
            Notification var2 = this.mNotification;
            var2.flags |= 1;
         }

         return this;
      }

      public Builder setDeleteIntent(PendingIntent var1) {
         this.mNotification.deleteIntent = var1;
         return this;
      }

      public Builder setExtras(Bundle var1) {
         this.mExtras = var1;
         return this;
      }

      public Builder setForegroundServiceBehavior(int var1) {
         this.mFgsDeferBehavior = var1;
         return this;
      }

      public Builder setFullScreenIntent(PendingIntent var1, boolean var2) {
         this.mFullScreenIntent = var1;
         this.setFlag(128, var2);
         return this;
      }

      public Builder setGroup(String var1) {
         this.mGroupKey = var1;
         return this;
      }

      public Builder setGroupAlertBehavior(int var1) {
         this.mGroupAlertBehavior = var1;
         return this;
      }

      public Builder setGroupSummary(boolean var1) {
         this.mGroupSummary = var1;
         return this;
      }

      public Builder setLargeIcon(Bitmap var1) {
         this.mLargeIcon = this.reduceLargeIconSize(var1);
         return this;
      }

      public Builder setLights(int var1, int var2, int var3) {
         this.mNotification.ledARGB = var1;
         this.mNotification.ledOnMS = var2;
         this.mNotification.ledOffMS = var3;
         byte var5;
         if (this.mNotification.ledOnMS != 0 && this.mNotification.ledOffMS != 0) {
            var5 = 1;
         } else {
            var5 = 0;
         }

         Notification var4 = this.mNotification;
         var4.flags = var5 | var4.flags & -2;
         return this;
      }

      public Builder setLocalOnly(boolean var1) {
         this.mLocalOnly = var1;
         return this;
      }

      public Builder setLocusId(LocusIdCompat var1) {
         this.mLocusId = var1;
         return this;
      }

      @Deprecated
      public Builder setNotificationSilent() {
         this.mSilent = true;
         return this;
      }

      public Builder setNumber(int var1) {
         this.mNumber = var1;
         return this;
      }

      public Builder setOngoing(boolean var1) {
         this.setFlag(2, var1);
         return this;
      }

      public Builder setOnlyAlertOnce(boolean var1) {
         this.setFlag(8, var1);
         return this;
      }

      public Builder setPriority(int var1) {
         this.mPriority = var1;
         return this;
      }

      public Builder setProgress(int var1, int var2, boolean var3) {
         this.mProgressMax = var1;
         this.mProgress = var2;
         this.mProgressIndeterminate = var3;
         return this;
      }

      public Builder setPublicVersion(Notification var1) {
         this.mPublicVersion = var1;
         return this;
      }

      public Builder setRemoteInputHistory(CharSequence[] var1) {
         this.mRemoteInputHistory = var1;
         return this;
      }

      public Builder setSettingsText(CharSequence var1) {
         this.mSettingsText = limitCharSequenceLength(var1);
         return this;
      }

      public Builder setShortcutId(String var1) {
         this.mShortcutId = var1;
         return this;
      }

      public Builder setShortcutInfo(ShortcutInfoCompat var1) {
         if (var1 == null) {
            return this;
         } else {
            this.mShortcutId = var1.getId();
            if (this.mLocusId == null) {
               if (var1.getLocusId() != null) {
                  this.mLocusId = var1.getLocusId();
               } else if (var1.getId() != null) {
                  this.mLocusId = new LocusIdCompat(var1.getId());
               }
            }

            if (this.mContentTitle == null) {
               this.setContentTitle(var1.getShortLabel());
            }

            return this;
         }
      }

      public Builder setShowWhen(boolean var1) {
         this.mShowWhen = var1;
         return this;
      }

      public Builder setSilent(boolean var1) {
         this.mSilent = var1;
         return this;
      }

      public Builder setSmallIcon(int var1) {
         this.mNotification.icon = var1;
         return this;
      }

      public Builder setSmallIcon(int var1, int var2) {
         this.mNotification.icon = var1;
         this.mNotification.iconLevel = var2;
         return this;
      }

      public Builder setSmallIcon(IconCompat var1) {
         this.mSmallIcon = var1.toIcon(this.mContext);
         return this;
      }

      public Builder setSortKey(String var1) {
         this.mSortKey = var1;
         return this;
      }

      public Builder setSound(Uri var1) {
         this.mNotification.sound = var1;
         this.mNotification.audioStreamType = -1;
         if (VERSION.SDK_INT >= 21) {
            this.mNotification.audioAttributes = (new AudioAttributes.Builder()).setContentType(4).setUsage(5).build();
         }

         return this;
      }

      public Builder setSound(Uri var1, int var2) {
         this.mNotification.sound = var1;
         this.mNotification.audioStreamType = var2;
         if (VERSION.SDK_INT >= 21) {
            this.mNotification.audioAttributes = (new AudioAttributes.Builder()).setContentType(4).setLegacyStreamType(var2).build();
         }

         return this;
      }

      public Builder setStyle(Style var1) {
         if (this.mStyle != var1) {
            this.mStyle = var1;
            if (var1 != null) {
               var1.setBuilder(this);
            }
         }

         return this;
      }

      public Builder setSubText(CharSequence var1) {
         this.mSubText = limitCharSequenceLength(var1);
         return this;
      }

      public Builder setTicker(CharSequence var1) {
         this.mNotification.tickerText = limitCharSequenceLength(var1);
         return this;
      }

      @Deprecated
      public Builder setTicker(CharSequence var1, RemoteViews var2) {
         this.mNotification.tickerText = limitCharSequenceLength(var1);
         this.mTickerView = var2;
         return this;
      }

      public Builder setTimeoutAfter(long var1) {
         this.mTimeout = var1;
         return this;
      }

      public Builder setUsesChronometer(boolean var1) {
         this.mUseChronometer = var1;
         return this;
      }

      public Builder setVibrate(long[] var1) {
         this.mNotification.vibrate = var1;
         return this;
      }

      public Builder setVisibility(int var1) {
         this.mVisibility = var1;
         return this;
      }

      public Builder setWhen(long var1) {
         this.mNotification.when = var1;
         return this;
      }
   }

   public static final class CarExtender implements Extender {
      static final String EXTRA_CAR_EXTENDER = "android.car.EXTENSIONS";
      private static final String EXTRA_COLOR = "app_color";
      private static final String EXTRA_CONVERSATION = "car_conversation";
      static final String EXTRA_INVISIBLE_ACTIONS = "invisible_actions";
      private static final String EXTRA_LARGE_ICON = "large_icon";
      private static final String KEY_AUTHOR = "author";
      private static final String KEY_MESSAGES = "messages";
      private static final String KEY_ON_READ = "on_read";
      private static final String KEY_ON_REPLY = "on_reply";
      private static final String KEY_PARTICIPANTS = "participants";
      private static final String KEY_REMOTE_INPUT = "remote_input";
      private static final String KEY_TEXT = "text";
      private static final String KEY_TIMESTAMP = "timestamp";
      private int mColor = 0;
      private Bitmap mLargeIcon;
      private UnreadConversation mUnreadConversation;

      public CarExtender() {
      }

      public CarExtender(Notification var1) {
         if (VERSION.SDK_INT >= 21) {
            Bundle var2;
            if (NotificationCompat.getExtras(var1) == null) {
               var2 = null;
            } else {
               var2 = NotificationCompat.getExtras(var1).getBundle("android.car.EXTENSIONS");
            }

            if (var2 != null) {
               this.mLargeIcon = (Bitmap)var2.getParcelable("large_icon");
               this.mColor = var2.getInt("app_color", 0);
               this.mUnreadConversation = getUnreadConversationFromBundle(var2.getBundle("car_conversation"));
            }

         }
      }

      private static Bundle getBundleForUnreadConversation(UnreadConversation var0) {
         Bundle var4 = new Bundle();
         String[] var3 = var0.getParticipants();
         int var1 = 0;
         String var7;
         if (var3 != null && var0.getParticipants().length > 1) {
            var7 = var0.getParticipants()[0];
         } else {
            var7 = null;
         }

         int var2 = var0.getMessages().length;

         Parcelable[] var5;
         for(var5 = new Parcelable[var2]; var1 < var2; ++var1) {
            Bundle var6 = new Bundle();
            var6.putString("text", var0.getMessages()[var1]);
            var6.putString("author", var7);
            var5[var1] = var6;
         }

         var4.putParcelableArray("messages", var5);
         RemoteInput var8 = var0.getRemoteInput();
         if (var8 != null) {
            var4.putParcelable("remote_input", (new android.app.RemoteInput.Builder(var8.getResultKey())).setLabel(var8.getLabel()).setChoices(var8.getChoices()).setAllowFreeFormInput(var8.getAllowFreeFormInput()).addExtras(var8.getExtras()).build());
         }

         var4.putParcelable("on_reply", var0.getReplyPendingIntent());
         var4.putParcelable("on_read", var0.getReadPendingIntent());
         var4.putStringArray("participants", var0.getParticipants());
         var4.putLong("timestamp", var0.getLatestTimestamp());
         return var4;
      }

      private static UnreadConversation getUnreadConversationFromBundle(Bundle var0) {
         CharSequence var8 = null;
         CharSequence[] var7 = null;
         if (var0 == null) {
            return null;
         } else {
            Parcelable[] var6 = var0.getParcelableArray("messages");
            byte var2 = 0;
            int var1;
            String[] var5;
            if (var6 != null) {
               int var3 = var6.length;
               var5 = new String[var3];
               var1 = 0;

               boolean var13;
               label46: {
                  while(true) {
                     if (var1 >= var3) {
                        var13 = true;
                        break label46;
                     }

                     Parcelable var9 = var6[var1];
                     if (!(var9 instanceof Bundle)) {
                        break;
                     }

                     String var17 = ((Bundle)var9).getString("text");
                     var5[var1] = var17;
                     if (var17 == null) {
                        break;
                     }

                     ++var1;
                  }

                  var13 = false;
               }

               if (!var13) {
                  return null;
               }
            } else {
               var5 = null;
            }

            PendingIntent var10 = (PendingIntent)var0.getParcelable("on_read");
            PendingIntent var18 = (PendingIntent)var0.getParcelable("on_reply");
            android.app.RemoteInput var12 = (android.app.RemoteInput)var0.getParcelable("remote_input");
            String[] var11 = var0.getStringArray("participants");
            UnreadConversation var14 = var8;
            if (var11 != null) {
               if (var11.length != 1) {
                  var14 = var8;
               } else {
                  RemoteInput var15 = var7;
                  if (var12 != null) {
                     String var16 = var12.getResultKey();
                     var8 = var12.getLabel();
                     var7 = var12.getChoices();
                     boolean var4 = var12.getAllowFreeFormInput();
                     var1 = var2;
                     if (VERSION.SDK_INT >= 29) {
                        var1 = var12.getEditChoicesBeforeSending();
                     }

                     var15 = new RemoteInput(var16, var8, var7, var4, var1, var12.getExtras(), (Set)null);
                  }

                  var14 = new UnreadConversation(var5, var15, var18, var10, var11, var0.getLong("timestamp"));
               }
            }

            return var14;
         }
      }

      public Builder extend(Builder var1) {
         if (VERSION.SDK_INT < 21) {
            return var1;
         } else {
            Bundle var3 = new Bundle();
            Bitmap var4 = this.mLargeIcon;
            if (var4 != null) {
               var3.putParcelable("large_icon", var4);
            }

            int var2 = this.mColor;
            if (var2 != 0) {
               var3.putInt("app_color", var2);
            }

            UnreadConversation var5 = this.mUnreadConversation;
            if (var5 != null) {
               var3.putBundle("car_conversation", getBundleForUnreadConversation(var5));
            }

            var1.getExtras().putBundle("android.car.EXTENSIONS", var3);
            return var1;
         }
      }

      public int getColor() {
         return this.mColor;
      }

      public Bitmap getLargeIcon() {
         return this.mLargeIcon;
      }

      @Deprecated
      public UnreadConversation getUnreadConversation() {
         return this.mUnreadConversation;
      }

      public CarExtender setColor(int var1) {
         this.mColor = var1;
         return this;
      }

      public CarExtender setLargeIcon(Bitmap var1) {
         this.mLargeIcon = var1;
         return this;
      }

      @Deprecated
      public CarExtender setUnreadConversation(UnreadConversation var1) {
         this.mUnreadConversation = var1;
         return this;
      }

      @Deprecated
      public static class UnreadConversation {
         private final long mLatestTimestamp;
         private final String[] mMessages;
         private final String[] mParticipants;
         private final PendingIntent mReadPendingIntent;
         private final RemoteInput mRemoteInput;
         private final PendingIntent mReplyPendingIntent;

         UnreadConversation(String[] var1, RemoteInput var2, PendingIntent var3, PendingIntent var4, String[] var5, long var6) {
            this.mMessages = var1;
            this.mRemoteInput = var2;
            this.mReadPendingIntent = var4;
            this.mReplyPendingIntent = var3;
            this.mParticipants = var5;
            this.mLatestTimestamp = var6;
         }

         public long getLatestTimestamp() {
            return this.mLatestTimestamp;
         }

         public String[] getMessages() {
            return this.mMessages;
         }

         public String getParticipant() {
            String[] var1 = this.mParticipants;
            String var2;
            if (var1.length > 0) {
               var2 = var1[0];
            } else {
               var2 = null;
            }

            return var2;
         }

         public String[] getParticipants() {
            return this.mParticipants;
         }

         public PendingIntent getReadPendingIntent() {
            return this.mReadPendingIntent;
         }

         public RemoteInput getRemoteInput() {
            return this.mRemoteInput;
         }

         public PendingIntent getReplyPendingIntent() {
            return this.mReplyPendingIntent;
         }

         public static class Builder {
            private long mLatestTimestamp;
            private final List mMessages = new ArrayList();
            private final String mParticipant;
            private PendingIntent mReadPendingIntent;
            private RemoteInput mRemoteInput;
            private PendingIntent mReplyPendingIntent;

            public Builder(String var1) {
               this.mParticipant = var1;
            }

            public Builder addMessage(String var1) {
               if (var1 != null) {
                  this.mMessages.add(var1);
               }

               return this;
            }

            public UnreadConversation build() {
               List var3 = this.mMessages;
               String[] var6 = (String[])var3.toArray(new String[var3.size()]);
               String var5 = this.mParticipant;
               RemoteInput var8 = this.mRemoteInput;
               PendingIntent var4 = this.mReplyPendingIntent;
               PendingIntent var7 = this.mReadPendingIntent;
               long var1 = this.mLatestTimestamp;
               return new UnreadConversation(var6, var8, var4, var7, new String[]{var5}, var1);
            }

            public Builder setLatestTimestamp(long var1) {
               this.mLatestTimestamp = var1;
               return this;
            }

            public Builder setReadPendingIntent(PendingIntent var1) {
               this.mReadPendingIntent = var1;
               return this;
            }

            public Builder setReplyAction(PendingIntent var1, RemoteInput var2) {
               this.mRemoteInput = var2;
               this.mReplyPendingIntent = var1;
               return this;
            }
         }
      }
   }

   public static class DecoratedCustomViewStyle extends Style {
      private static final int MAX_ACTION_BUTTONS = 3;
      private static final String TEMPLATE_CLASS_NAME = "androidx.core.app.NotificationCompat$DecoratedCustomViewStyle";

      private RemoteViews createRemoteViews(RemoteViews var1, boolean var2) {
         byte var5;
         RemoteViews var8;
         boolean var11;
         label26: {
            int var3 = R.layout.notification_template_custom_big;
            boolean var6 = true;
            var5 = 0;
            var8 = this.applyStandardTemplate(true, var3, false);
            var8.removeAllViews(R.id.actions);
            List var9 = getNonContextualActions(this.mBuilder.mActions);
            if (var2 && var9 != null) {
               int var7 = Math.min(var9.size(), 3);
               if (var7 > 0) {
                  int var4 = 0;

                  while(true) {
                     var11 = var6;
                     if (var4 >= var7) {
                        break label26;
                     }

                     RemoteViews var10 = this.generateActionButton((Action)var9.get(var4));
                     var8.addView(R.id.actions, var10);
                     ++var4;
                  }
               }
            }

            var11 = false;
         }

         byte var12;
         if (var11) {
            var12 = var5;
         } else {
            var12 = 8;
         }

         var8.setViewVisibility(R.id.actions, var12);
         var8.setViewVisibility(R.id.action_divider, var12);
         this.buildIntoRemoteViews(var8, var1);
         return var8;
      }

      private RemoteViews generateActionButton(Action var1) {
         boolean var2;
         if (var1.actionIntent == null) {
            var2 = true;
         } else {
            var2 = false;
         }

         String var4 = this.mBuilder.mContext.getPackageName();
         int var3;
         if (var2) {
            var3 = R.layout.notification_action_tombstone;
         } else {
            var3 = R.layout.notification_action;
         }

         RemoteViews var5 = new RemoteViews(var4, var3);
         IconCompat var6 = var1.getIconCompat();
         if (var6 != null) {
            var5.setImageViewBitmap(R.id.action_image, this.createColoredBitmap(var6, this.mBuilder.mContext.getResources().getColor(R.color.notification_action_color_filter)));
         }

         var5.setTextViewText(R.id.action_text, var1.title);
         if (!var2) {
            var5.setOnClickPendingIntent(R.id.action_container, var1.actionIntent);
         }

         if (VERSION.SDK_INT >= 15) {
            var5.setContentDescription(R.id.action_container, var1.title);
         }

         return var5;
      }

      private static List getNonContextualActions(List var0) {
         if (var0 == null) {
            return null;
         } else {
            ArrayList var1 = new ArrayList();
            Iterator var2 = var0.iterator();

            while(var2.hasNext()) {
               Action var3 = (Action)var2.next();
               if (!var3.isContextual()) {
                  var1.add(var3);
               }
            }

            return var1;
         }
      }

      public void apply(NotificationBuilderWithBuilderAccessor var1) {
         if (VERSION.SDK_INT >= 24) {
            var1.getBuilder().setStyle(new Notification.DecoratedCustomViewStyle());
         }

      }

      public boolean displayCustomViewInline() {
         return true;
      }

      protected String getClassName() {
         return "androidx.core.app.NotificationCompat$DecoratedCustomViewStyle";
      }

      public RemoteViews makeBigContentView(NotificationBuilderWithBuilderAccessor var1) {
         if (VERSION.SDK_INT >= 24) {
            return null;
         } else {
            RemoteViews var2 = this.mBuilder.getBigContentView();
            if (var2 == null) {
               var2 = this.mBuilder.getContentView();
            }

            return var2 == null ? null : this.createRemoteViews(var2, true);
         }
      }

      public RemoteViews makeContentView(NotificationBuilderWithBuilderAccessor var1) {
         if (VERSION.SDK_INT >= 24) {
            return null;
         } else {
            return this.mBuilder.getContentView() == null ? null : this.createRemoteViews(this.mBuilder.getContentView(), false);
         }
      }

      public RemoteViews makeHeadsUpContentView(NotificationBuilderWithBuilderAccessor var1) {
         if (VERSION.SDK_INT >= 24) {
            return null;
         } else {
            RemoteViews var2 = this.mBuilder.getHeadsUpContentView();
            RemoteViews var3;
            if (var2 != null) {
               var3 = var2;
            } else {
               var3 = this.mBuilder.getContentView();
            }

            return var2 == null ? null : this.createRemoteViews(var3, true);
         }
      }
   }

   public interface Extender {
      Builder extend(Builder var1);
   }

   @Retention(RetentionPolicy.SOURCE)
   public @interface GroupAlertBehavior {
   }

   public static class InboxStyle extends Style {
      private static final String TEMPLATE_CLASS_NAME = "androidx.core.app.NotificationCompat$InboxStyle";
      private ArrayList mTexts = new ArrayList();

      public InboxStyle() {
      }

      public InboxStyle(Builder var1) {
         this.setBuilder(var1);
      }

      public InboxStyle addLine(CharSequence var1) {
         if (var1 != null) {
            this.mTexts.add(NotificationCompat.Builder.limitCharSequenceLength(var1));
         }

         return this;
      }

      public void apply(NotificationBuilderWithBuilderAccessor var1) {
         if (VERSION.SDK_INT >= 16) {
            Notification.InboxStyle var2 = (new Notification.InboxStyle(var1.getBuilder())).setBigContentTitle(this.mBigContentTitle);
            if (this.mSummaryTextSet) {
               var2.setSummaryText(this.mSummaryText);
            }

            Iterator var3 = this.mTexts.iterator();

            while(var3.hasNext()) {
               var2.addLine((CharSequence)var3.next());
            }
         }

      }

      protected void clearCompatExtraKeys(Bundle var1) {
         super.clearCompatExtraKeys(var1);
         var1.remove("android.textLines");
      }

      protected String getClassName() {
         return "androidx.core.app.NotificationCompat$InboxStyle";
      }

      protected void restoreFromCompatExtras(Bundle var1) {
         super.restoreFromCompatExtras(var1);
         this.mTexts.clear();
         if (var1.containsKey("android.textLines")) {
            Collections.addAll(this.mTexts, var1.getCharSequenceArray("android.textLines"));
         }

      }

      public InboxStyle setBigContentTitle(CharSequence var1) {
         this.mBigContentTitle = NotificationCompat.Builder.limitCharSequenceLength(var1);
         return this;
      }

      public InboxStyle setSummaryText(CharSequence var1) {
         this.mSummaryText = NotificationCompat.Builder.limitCharSequenceLength(var1);
         this.mSummaryTextSet = true;
         return this;
      }
   }

   public static class MessagingStyle extends Style {
      public static final int MAXIMUM_RETAINED_MESSAGES = 25;
      private static final String TEMPLATE_CLASS_NAME = "androidx.core.app.NotificationCompat$MessagingStyle";
      private CharSequence mConversationTitle;
      private final List mHistoricMessages = new ArrayList();
      private Boolean mIsGroupConversation;
      private final List mMessages = new ArrayList();
      private Person mUser;

      MessagingStyle() {
      }

      public MessagingStyle(Person var1) {
         if (!TextUtils.isEmpty(var1.getName())) {
            this.mUser = var1;
         } else {
            throw new IllegalArgumentException("User's name must not be empty.");
         }
      }

      @Deprecated
      public MessagingStyle(CharSequence var1) {
         this.mUser = (new Person.Builder()).setName(var1).build();
      }

      public static MessagingStyle extractMessagingStyleFromNotification(Notification var0) {
         Style var1 = NotificationCompat.Style.extractStyleFromNotification(var0);
         return var1 instanceof MessagingStyle ? (MessagingStyle)var1 : null;
      }

      private Message findLatestIncomingMessage() {
         for(int var1 = this.mMessages.size() - 1; var1 >= 0; --var1) {
            Message var2 = (Message)this.mMessages.get(var1);
            if (var2.getPerson() != null && !TextUtils.isEmpty(var2.getPerson().getName())) {
               return var2;
            }
         }

         if (!this.mMessages.isEmpty()) {
            List var3 = this.mMessages;
            return (Message)var3.get(var3.size() - 1);
         } else {
            return null;
         }
      }

      private boolean hasMessagesWithoutSender() {
         for(int var1 = this.mMessages.size() - 1; var1 >= 0; --var1) {
            Message var2 = (Message)this.mMessages.get(var1);
            if (var2.getPerson() != null && var2.getPerson().getName() == null) {
               return true;
            }
         }

         return false;
      }

      private TextAppearanceSpan makeFontColorSpan(int var1) {
         return new TextAppearanceSpan((String)null, 0, 0, ColorStateList.valueOf(var1), (ColorStateList)null);
      }

      private CharSequence makeMessageLine(Message var1) {
         BidiFormatter var8 = BidiFormatter.getInstance();
         SpannableStringBuilder var9 = new SpannableStringBuilder();
         boolean var3;
         if (VERSION.SDK_INT >= 21) {
            var3 = true;
         } else {
            var3 = false;
         }

         int var2;
         if (var3) {
            var2 = -16777216;
         } else {
            var2 = -1;
         }

         Person var5 = var1.getPerson();
         String var7 = "";
         Object var11;
         if (var5 == null) {
            var11 = "";
         } else {
            var11 = var1.getPerson().getName();
         }

         int var4 = var2;
         Object var6 = var11;
         CharSequence var12;
         if (TextUtils.isEmpty((CharSequence)var11)) {
            var12 = this.mUser.getName();
            var4 = var2;
            var6 = var12;
            if (var3) {
               var4 = var2;
               var6 = var12;
               if (this.mBuilder.getColor() != 0) {
                  var4 = this.mBuilder.getColor();
                  var6 = var12;
               }
            }
         }

         var12 = var8.unicodeWrap((CharSequence)var6);
         var9.append(var12);
         var9.setSpan(this.makeFontColorSpan(var4), var9.length() - var12.length(), var9.length(), 33);
         Object var10;
         if (var1.getText() == null) {
            var10 = var7;
         } else {
            var10 = var1.getText();
         }

         var9.append("  ").append(var8.unicodeWrap((CharSequence)var10));
         return var9;
      }

      public void addCompatExtras(Bundle var1) {
         super.addCompatExtras(var1);
         var1.putCharSequence("android.selfDisplayName", this.mUser.getName());
         var1.putBundle("android.messagingStyleUser", this.mUser.toBundle());
         var1.putCharSequence("android.hiddenConversationTitle", this.mConversationTitle);
         if (this.mConversationTitle != null && this.mIsGroupConversation) {
            var1.putCharSequence("android.conversationTitle", this.mConversationTitle);
         }

         if (!this.mMessages.isEmpty()) {
            var1.putParcelableArray("android.messages", NotificationCompat.MessagingStyle.Message.getBundleArrayForMessages(this.mMessages));
         }

         if (!this.mHistoricMessages.isEmpty()) {
            var1.putParcelableArray("android.messages.historic", NotificationCompat.MessagingStyle.Message.getBundleArrayForMessages(this.mHistoricMessages));
         }

         Boolean var2 = this.mIsGroupConversation;
         if (var2 != null) {
            var1.putBoolean("android.isGroupConversation", var2);
         }

      }

      public MessagingStyle addHistoricMessage(Message var1) {
         if (var1 != null) {
            this.mHistoricMessages.add(var1);
            if (this.mHistoricMessages.size() > 25) {
               this.mHistoricMessages.remove(0);
            }
         }

         return this;
      }

      public MessagingStyle addMessage(Message var1) {
         if (var1 != null) {
            this.mMessages.add(var1);
            if (this.mMessages.size() > 25) {
               this.mMessages.remove(0);
            }
         }

         return this;
      }

      public MessagingStyle addMessage(CharSequence var1, long var2, Person var4) {
         this.addMessage(new Message(var1, var2, var4));
         return this;
      }

      @Deprecated
      public MessagingStyle addMessage(CharSequence var1, long var2, CharSequence var4) {
         this.mMessages.add(new Message(var1, var2, (new Person.Builder()).setName(var4).build()));
         if (this.mMessages.size() > 25) {
            this.mMessages.remove(0);
         }

         return this;
      }

      public void apply(NotificationBuilderWithBuilderAccessor var1) {
         this.setGroupConversation(this.isGroupConversation());
         if (VERSION.SDK_INT >= 24) {
            Notification.MessagingStyle var4;
            if (VERSION.SDK_INT >= 28) {
               var4 = new Notification.MessagingStyle(this.mUser.toAndroidPerson());
            } else {
               var4 = new Notification.MessagingStyle(this.mUser.getName());
            }

            Iterator var5 = this.mMessages.iterator();

            while(var5.hasNext()) {
               var4.addMessage(((Message)var5.next()).toAndroidMessage());
            }

            if (VERSION.SDK_INT >= 26) {
               var5 = this.mHistoricMessages.iterator();

               while(var5.hasNext()) {
                  var4.addHistoricMessage(((Message)var5.next()).toAndroidMessage());
               }
            }

            if (this.mIsGroupConversation || VERSION.SDK_INT >= 28) {
               var4.setConversationTitle(this.mConversationTitle);
            }

            if (VERSION.SDK_INT >= 28) {
               var4.setGroupConversation(this.mIsGroupConversation);
            }

            var4.setBuilder(var1.getBuilder());
         } else {
            Message var8 = this.findLatestIncomingMessage();
            if (this.mConversationTitle != null && this.mIsGroupConversation) {
               var1.getBuilder().setContentTitle(this.mConversationTitle);
            } else if (var8 != null) {
               var1.getBuilder().setContentTitle("");
               if (var8.getPerson() != null) {
                  var1.getBuilder().setContentTitle(var8.getPerson().getName());
               }
            }

            CharSequence var9;
            if (var8 != null) {
               Notification.Builder var6 = var1.getBuilder();
               if (this.mConversationTitle != null) {
                  var9 = this.makeMessageLine(var8);
               } else {
                  var9 = var8.getText();
               }

               var6.setContentText(var9);
            }

            if (VERSION.SDK_INT >= 16) {
               SpannableStringBuilder var7 = new SpannableStringBuilder();
               boolean var2;
               if (this.mConversationTitle == null && !this.hasMessagesWithoutSender()) {
                  var2 = false;
               } else {
                  var2 = true;
               }

               for(int var3 = this.mMessages.size() - 1; var3 >= 0; --var3) {
                  var8 = (Message)this.mMessages.get(var3);
                  if (var2) {
                     var9 = this.makeMessageLine(var8);
                  } else {
                     var9 = var8.getText();
                  }

                  if (var3 != this.mMessages.size() - 1) {
                     var7.insert(0, "\n");
                  }

                  var7.insert(0, var9);
               }

               (new Notification.BigTextStyle(var1.getBuilder())).setBigContentTitle((CharSequence)null).bigText(var7);
            }
         }

      }

      protected void clearCompatExtraKeys(Bundle var1) {
         super.clearCompatExtraKeys(var1);
         var1.remove("android.messagingStyleUser");
         var1.remove("android.selfDisplayName");
         var1.remove("android.conversationTitle");
         var1.remove("android.hiddenConversationTitle");
         var1.remove("android.messages");
         var1.remove("android.messages.historic");
         var1.remove("android.isGroupConversation");
      }

      protected String getClassName() {
         return "androidx.core.app.NotificationCompat$MessagingStyle";
      }

      public CharSequence getConversationTitle() {
         return this.mConversationTitle;
      }

      public List getHistoricMessages() {
         return this.mHistoricMessages;
      }

      public List getMessages() {
         return this.mMessages;
      }

      public Person getUser() {
         return this.mUser;
      }

      @Deprecated
      public CharSequence getUserDisplayName() {
         return this.mUser.getName();
      }

      public boolean isGroupConversation() {
         Builder var3 = this.mBuilder;
         boolean var2 = false;
         boolean var1 = false;
         if (var3 != null && this.mBuilder.mContext.getApplicationInfo().targetSdkVersion < 28 && this.mIsGroupConversation == null) {
            if (this.mConversationTitle != null) {
               var1 = true;
            }

            return var1;
         } else {
            Boolean var4 = this.mIsGroupConversation;
            var1 = var2;
            if (var4 != null) {
               var1 = var4;
            }

            return var1;
         }
      }

      protected void restoreFromCompatExtras(Bundle var1) {
         super.restoreFromCompatExtras(var1);
         this.mMessages.clear();
         if (var1.containsKey("android.messagingStyleUser")) {
            this.mUser = Person.fromBundle(var1.getBundle("android.messagingStyleUser"));
         } else {
            this.mUser = (new Person.Builder()).setName(var1.getString("android.selfDisplayName")).build();
         }

         CharSequence var2 = var1.getCharSequence("android.conversationTitle");
         this.mConversationTitle = var2;
         if (var2 == null) {
            this.mConversationTitle = var1.getCharSequence("android.hiddenConversationTitle");
         }

         Parcelable[] var3 = var1.getParcelableArray("android.messages");
         if (var3 != null) {
            this.mMessages.addAll(NotificationCompat.MessagingStyle.Message.getMessagesFromBundleArray(var3));
         }

         var3 = var1.getParcelableArray("android.messages.historic");
         if (var3 != null) {
            this.mHistoricMessages.addAll(NotificationCompat.MessagingStyle.Message.getMessagesFromBundleArray(var3));
         }

         if (var1.containsKey("android.isGroupConversation")) {
            this.mIsGroupConversation = var1.getBoolean("android.isGroupConversation");
         }

      }

      public MessagingStyle setConversationTitle(CharSequence var1) {
         this.mConversationTitle = var1;
         return this;
      }

      public MessagingStyle setGroupConversation(boolean var1) {
         this.mIsGroupConversation = var1;
         return this;
      }

      public static final class Message {
         static final String KEY_DATA_MIME_TYPE = "type";
         static final String KEY_DATA_URI = "uri";
         static final String KEY_EXTRAS_BUNDLE = "extras";
         static final String KEY_NOTIFICATION_PERSON = "sender_person";
         static final String KEY_PERSON = "person";
         static final String KEY_SENDER = "sender";
         static final String KEY_TEXT = "text";
         static final String KEY_TIMESTAMP = "time";
         private String mDataMimeType;
         private Uri mDataUri;
         private Bundle mExtras;
         private final Person mPerson;
         private final CharSequence mText;
         private final long mTimestamp;

         public Message(CharSequence var1, long var2, Person var4) {
            this.mExtras = new Bundle();
            this.mText = var1;
            this.mTimestamp = var2;
            this.mPerson = var4;
         }

         @Deprecated
         public Message(CharSequence var1, long var2, CharSequence var4) {
            this(var1, var2, (new Person.Builder()).setName(var4).build());
         }

         static Bundle[] getBundleArrayForMessages(List var0) {
            Bundle[] var3 = new Bundle[var0.size()];
            int var2 = var0.size();

            for(int var1 = 0; var1 < var2; ++var1) {
               var3[var1] = ((Message)var0.get(var1)).toBundle();
            }

            return var3;
         }

         static Message getMessageFromBundle(Bundle param0) {
            // $FF: Couldn't be decompiled
         }

         static List getMessagesFromBundleArray(Parcelable[] var0) {
            ArrayList var2 = new ArrayList(var0.length);

            for(int var1 = 0; var1 < var0.length; ++var1) {
               Parcelable var3 = var0[var1];
               if (var3 instanceof Bundle) {
                  Message var4 = getMessageFromBundle((Bundle)var3);
                  if (var4 != null) {
                     var2.add(var4);
                  }
               }
            }

            return var2;
         }

         private Bundle toBundle() {
            Bundle var1 = new Bundle();
            CharSequence var2 = this.mText;
            if (var2 != null) {
               var1.putCharSequence("text", var2);
            }

            var1.putLong("time", this.mTimestamp);
            Person var3 = this.mPerson;
            if (var3 != null) {
               var1.putCharSequence("sender", var3.getName());
               if (VERSION.SDK_INT >= 28) {
                  var1.putParcelable("sender_person", this.mPerson.toAndroidPerson());
               } else {
                  var1.putBundle("person", this.mPerson.toBundle());
               }
            }

            String var4 = this.mDataMimeType;
            if (var4 != null) {
               var1.putString("type", var4);
            }

            Uri var5 = this.mDataUri;
            if (var5 != null) {
               var1.putParcelable("uri", var5);
            }

            Bundle var6 = this.mExtras;
            if (var6 != null) {
               var1.putBundle("extras", var6);
            }

            return var1;
         }

         public String getDataMimeType() {
            return this.mDataMimeType;
         }

         public Uri getDataUri() {
            return this.mDataUri;
         }

         public Bundle getExtras() {
            return this.mExtras;
         }

         public Person getPerson() {
            return this.mPerson;
         }

         @Deprecated
         public CharSequence getSender() {
            Person var1 = this.mPerson;
            CharSequence var2;
            if (var1 == null) {
               var2 = null;
            } else {
               var2 = var1.getName();
            }

            return var2;
         }

         public CharSequence getText() {
            return this.mText;
         }

         public long getTimestamp() {
            return this.mTimestamp;
         }

         public Message setData(String var1, Uri var2) {
            this.mDataMimeType = var1;
            this.mDataUri = var2;
            return this;
         }

         Notification.Message toAndroidMessage() {
            Person var7 = this.getPerson();
            int var1 = VERSION.SDK_INT;
            CharSequence var4 = null;
            CharSequence var5 = null;
            long var2;
            Notification.Message var8;
            if (var1 >= 28) {
               CharSequence var6 = this.getText();
               var2 = this.getTimestamp();
               android.app.Person var9;
               if (var7 == null) {
                  var9 = var5;
               } else {
                  var9 = var7.toAndroidPerson();
               }

               var8 = new Notification.Message(var6, var2, var9);
            } else {
               var5 = this.getText();
               var2 = this.getTimestamp();
               if (var7 != null) {
                  var4 = var7.getName();
               }

               var8 = new Notification.Message(var5, var2, var4);
            }

            if (this.getDataMimeType() != null) {
               var8.setData(this.getDataMimeType(), this.getDataUri());
            }

            return var8;
         }
      }
   }

   @Retention(RetentionPolicy.SOURCE)
   public @interface NotificationVisibility {
   }

   @Retention(RetentionPolicy.SOURCE)
   public @interface ServiceNotificationBehavior {
   }

   @Retention(RetentionPolicy.SOURCE)
   public @interface StreamType {
   }

   public abstract static class Style {
      CharSequence mBigContentTitle;
      protected Builder mBuilder;
      CharSequence mSummaryText;
      boolean mSummaryTextSet = false;

      private int calculateTopPadding() {
         Resources var4 = this.mBuilder.mContext.getResources();
         int var3 = var4.getDimensionPixelSize(R.dimen.notification_top_pad);
         int var2 = var4.getDimensionPixelSize(R.dimen.notification_top_pad_large_text);
         float var1 = (constrain(var4.getConfiguration().fontScale, 1.0F, 1.3F) - 1.0F) / 0.29999995F;
         return Math.round((1.0F - var1) * (float)var3 + var1 * (float)var2);
      }

      private static float constrain(float var0, float var1, float var2) {
         if (!(var0 < var1)) {
            var1 = var0;
            if (var0 > var2) {
               var1 = var2;
            }
         }

         return var1;
      }

      static Style constructCompatStyleByName(String var0) {
         if (var0 != null) {
            var0.hashCode();
            switch (var0) {
               case "androidx.core.app.NotificationCompat$DecoratedCustomViewStyle":
                  return new DecoratedCustomViewStyle();
               case "androidx.core.app.NotificationCompat$BigPictureStyle":
                  return new BigPictureStyle();
               case "androidx.core.app.NotificationCompat$InboxStyle":
                  return new InboxStyle();
               case "androidx.core.app.NotificationCompat$BigTextStyle":
                  return new BigTextStyle();
               case "androidx.core.app.NotificationCompat$MessagingStyle":
                  return new MessagingStyle();
            }
         }

         return null;
      }

      private static Style constructCompatStyleByPlatformName(String var0) {
         if (var0 == null) {
            return null;
         } else {
            if (VERSION.SDK_INT >= 16) {
               if (var0.equals(Notification.BigPictureStyle.class.getName())) {
                  return new BigPictureStyle();
               }

               if (var0.equals(Notification.BigTextStyle.class.getName())) {
                  return new BigTextStyle();
               }

               if (var0.equals(Notification.InboxStyle.class.getName())) {
                  return new InboxStyle();
               }

               if (VERSION.SDK_INT >= 24) {
                  if (var0.equals(Notification.MessagingStyle.class.getName())) {
                     return new MessagingStyle();
                  }

                  if (var0.equals(Notification.DecoratedCustomViewStyle.class.getName())) {
                     return new DecoratedCustomViewStyle();
                  }
               }
            }

            return null;
         }
      }

      static Style constructCompatStyleForBundle(Bundle var0) {
         Style var1 = constructCompatStyleByName(var0.getString("androidx.core.app.extra.COMPAT_TEMPLATE"));
         if (var1 != null) {
            return var1;
         } else if (!var0.containsKey("android.selfDisplayName") && !var0.containsKey("android.messagingStyleUser")) {
            if (var0.containsKey("android.picture")) {
               return new BigPictureStyle();
            } else if (var0.containsKey("android.bigText")) {
               return new BigTextStyle();
            } else {
               return (Style)(var0.containsKey("android.textLines") ? new InboxStyle() : constructCompatStyleByPlatformName(var0.getString("android.template")));
            }
         } else {
            return new MessagingStyle();
         }
      }

      static Style constructStyleForExtras(Bundle var0) {
         Style var1 = constructCompatStyleForBundle(var0);
         if (var1 == null) {
            return null;
         } else {
            try {
               var1.restoreFromCompatExtras(var0);
               return var1;
            } catch (ClassCastException var2) {
               return null;
            }
         }
      }

      private Bitmap createColoredBitmap(int var1, int var2, int var3) {
         return this.createColoredBitmap(IconCompat.createWithResource(this.mBuilder.mContext, var1), var2, var3);
      }

      private Bitmap createColoredBitmap(IconCompat var1, int var2, int var3) {
         Drawable var7 = var1.loadDrawable(this.mBuilder.mContext);
         int var4;
         if (var3 == 0) {
            var4 = var7.getIntrinsicWidth();
         } else {
            var4 = var3;
         }

         int var5 = var3;
         if (var3 == 0) {
            var5 = var7.getIntrinsicHeight();
         }

         Bitmap var6 = Bitmap.createBitmap(var4, var5, Config.ARGB_8888);
         var7.setBounds(0, 0, var4, var5);
         if (var2 != 0) {
            var7.mutate().setColorFilter(new PorterDuffColorFilter(var2, Mode.SRC_IN));
         }

         var7.draw(new Canvas(var6));
         return var6;
      }

      private Bitmap createIconWithBackground(int var1, int var2, int var3, int var4) {
         int var6 = R.drawable.notification_icon_background;
         int var5 = var4;
         if (var4 == 0) {
            var5 = 0;
         }

         Bitmap var9 = this.createColoredBitmap(var6, var5, var2);
         Canvas var8 = new Canvas(var9);
         Drawable var7 = this.mBuilder.mContext.getResources().getDrawable(var1).mutate();
         var7.setFilterBitmap(true);
         var1 = (var2 - var3) / 2;
         var2 = var3 + var1;
         var7.setBounds(var1, var1, var2, var2);
         var7.setColorFilter(new PorterDuffColorFilter(-1, Mode.SRC_ATOP));
         var7.draw(var8);
         return var9;
      }

      public static Style extractStyleFromNotification(Notification var0) {
         Bundle var1 = NotificationCompat.getExtras(var0);
         return var1 == null ? null : constructStyleForExtras(var1);
      }

      private void hideNormalContent(RemoteViews var1) {
         var1.setViewVisibility(R.id.title, 8);
         var1.setViewVisibility(R.id.text2, 8);
         var1.setViewVisibility(R.id.text, 8);
      }

      public void addCompatExtras(Bundle var1) {
         if (this.mSummaryTextSet) {
            var1.putCharSequence("android.summaryText", this.mSummaryText);
         }

         CharSequence var2 = this.mBigContentTitle;
         if (var2 != null) {
            var1.putCharSequence("android.title.big", var2);
         }

         String var3 = this.getClassName();
         if (var3 != null) {
            var1.putString("androidx.core.app.extra.COMPAT_TEMPLATE", var3);
         }

      }

      public void apply(NotificationBuilderWithBuilderAccessor var1) {
      }

      public RemoteViews applyStandardTemplate(boolean var1, int var2, boolean var3) {
         Resources var10 = this.mBuilder.mContext.getResources();
         RemoteViews var9 = new RemoteViews(this.mBuilder.mContext.getPackageName(), var2);
         var2 = this.mBuilder.getPriority();
         boolean var8 = true;
         byte var7 = 0;
         boolean var12;
         if (var2 < -1) {
            var12 = true;
         } else {
            var12 = false;
         }

         if (VERSION.SDK_INT >= 16 && VERSION.SDK_INT < 21) {
            if (var12) {
               var9.setInt(R.id.notification_background, "setBackgroundResource", R.drawable.notification_bg_low);
               var9.setInt(R.id.icon, "setBackgroundResource", R.drawable.notification_template_icon_low_bg);
            } else {
               var9.setInt(R.id.notification_background, "setBackgroundResource", R.drawable.notification_bg);
               var9.setInt(R.id.icon, "setBackgroundResource", R.drawable.notification_template_icon_bg);
            }
         }

         int var5;
         int var6;
         Bitmap var11;
         if (this.mBuilder.mLargeIcon != null) {
            if (VERSION.SDK_INT >= 16) {
               var9.setViewVisibility(R.id.icon, 0);
               var9.setImageViewBitmap(R.id.icon, this.mBuilder.mLargeIcon);
            } else {
               var9.setViewVisibility(R.id.icon, 8);
            }

            if (var1 && this.mBuilder.mNotification.icon != 0) {
               var5 = var10.getDimensionPixelSize(R.dimen.notification_right_icon_size);
               var2 = var10.getDimensionPixelSize(R.dimen.notification_small_icon_background_padding);
               if (VERSION.SDK_INT >= 21) {
                  var11 = this.createIconWithBackground(this.mBuilder.mNotification.icon, var5, var5 - var2 * 2, this.mBuilder.getColor());
                  var9.setImageViewBitmap(R.id.right_icon, var11);
               } else {
                  var9.setImageViewBitmap(R.id.right_icon, this.createColoredBitmap(this.mBuilder.mNotification.icon, -1));
               }

               var9.setViewVisibility(R.id.right_icon, 0);
            }
         } else if (var1 && this.mBuilder.mNotification.icon != 0) {
            var9.setViewVisibility(R.id.icon, 0);
            if (VERSION.SDK_INT >= 21) {
               var6 = var10.getDimensionPixelSize(R.dimen.notification_large_icon_width);
               var5 = var10.getDimensionPixelSize(R.dimen.notification_big_circle_margin);
               var2 = var10.getDimensionPixelSize(R.dimen.notification_small_icon_size_as_large);
               var11 = this.createIconWithBackground(this.mBuilder.mNotification.icon, var6 - var5, var2, this.mBuilder.getColor());
               var9.setImageViewBitmap(R.id.icon, var11);
            } else {
               var9.setImageViewBitmap(R.id.icon, this.createColoredBitmap(this.mBuilder.mNotification.icon, -1));
            }
         }

         if (this.mBuilder.mContentTitle != null) {
            var9.setTextViewText(R.id.title, this.mBuilder.mContentTitle);
         }

         boolean var13;
         if (this.mBuilder.mContentText != null) {
            var9.setTextViewText(R.id.text, this.mBuilder.mContentText);
            var13 = true;
         } else {
            var13 = false;
         }

         if (VERSION.SDK_INT < 21 && this.mBuilder.mLargeIcon != null) {
            var12 = true;
         } else {
            var12 = false;
         }

         label114: {
            if (this.mBuilder.mContentInfo != null) {
               var9.setTextViewText(R.id.info, this.mBuilder.mContentInfo);
               var9.setViewVisibility(R.id.info, 0);
            } else {
               if (this.mBuilder.mNumber <= 0) {
                  var9.setViewVisibility(R.id.info, 8);
                  break label114;
               }

               var2 = var10.getInteger(R.integer.status_bar_notification_info_maxnum);
               if (this.mBuilder.mNumber > var2) {
                  var9.setTextViewText(R.id.info, var10.getString(R.string.status_bar_notification_info_overflow));
               } else {
                  NumberFormat var16 = NumberFormat.getIntegerInstance();
                  var9.setTextViewText(R.id.info, var16.format((long)this.mBuilder.mNumber));
               }

               var9.setViewVisibility(R.id.info, 0);
            }

            var13 = true;
            var12 = true;
         }

         boolean var14;
         label108: {
            if (this.mBuilder.mSubText != null && VERSION.SDK_INT >= 16) {
               var9.setTextViewText(R.id.text, this.mBuilder.mSubText);
               if (this.mBuilder.mContentText != null) {
                  var9.setTextViewText(R.id.text2, this.mBuilder.mContentText);
                  var9.setViewVisibility(R.id.text2, 0);
                  var14 = true;
                  break label108;
               }

               var9.setViewVisibility(R.id.text2, 8);
            }

            var14 = false;
         }

         if (var14 && VERSION.SDK_INT >= 16) {
            if (var3) {
               float var4 = (float)var10.getDimensionPixelSize(R.dimen.notification_subtext_size);
               var9.setTextViewTextSize(R.id.text, 0, var4);
            }

            var9.setViewPadding(R.id.line1, 0, 0, 0, 0);
         }

         if (this.mBuilder.getWhenIfShowing() != 0L) {
            if (this.mBuilder.mUseChronometer && VERSION.SDK_INT >= 16) {
               var9.setViewVisibility(R.id.chronometer, 0);
               var9.setLong(R.id.chronometer, "setBase", this.mBuilder.getWhenIfShowing() + (SystemClock.elapsedRealtime() - System.currentTimeMillis()));
               var9.setBoolean(R.id.chronometer, "setStarted", true);
               var12 = var8;
               if (this.mBuilder.mChronometerCountDown) {
                  var12 = var8;
                  if (VERSION.SDK_INT >= 24) {
                     var9.setChronometerCountDown(R.id.chronometer, this.mBuilder.mChronometerCountDown);
                     var12 = var8;
                  }
               }
            } else {
               var9.setViewVisibility(R.id.time, 0);
               var9.setLong(R.id.time, "setTime", this.mBuilder.getWhenIfShowing());
               var12 = var8;
            }
         }

         var6 = R.id.right_side;
         byte var15;
         if (var12) {
            var15 = 0;
         } else {
            var15 = 8;
         }

         var9.setViewVisibility(var6, var15);
         var6 = R.id.line3;
         if (var13) {
            var15 = var7;
         } else {
            var15 = 8;
         }

         var9.setViewVisibility(var6, var15);
         return var9;
      }

      public Notification build() {
         Builder var1 = this.mBuilder;
         Notification var2;
         if (var1 != null) {
            var2 = var1.build();
         } else {
            var2 = null;
         }

         return var2;
      }

      public void buildIntoRemoteViews(RemoteViews var1, RemoteViews var2) {
         this.hideNormalContent(var1);
         var1.removeAllViews(R.id.notification_main_column);
         var1.addView(R.id.notification_main_column, var2.clone());
         var1.setViewVisibility(R.id.notification_main_column, 0);
         if (VERSION.SDK_INT >= 21) {
            var1.setViewPadding(R.id.notification_main_column_container, 0, this.calculateTopPadding(), 0, 0);
         }

      }

      protected void clearCompatExtraKeys(Bundle var1) {
         var1.remove("android.summaryText");
         var1.remove("android.title.big");
         var1.remove("androidx.core.app.extra.COMPAT_TEMPLATE");
      }

      public Bitmap createColoredBitmap(int var1, int var2) {
         return this.createColoredBitmap(var1, var2, 0);
      }

      Bitmap createColoredBitmap(IconCompat var1, int var2) {
         return this.createColoredBitmap(var1, var2, 0);
      }

      public boolean displayCustomViewInline() {
         return false;
      }

      protected String getClassName() {
         return null;
      }

      public RemoteViews makeBigContentView(NotificationBuilderWithBuilderAccessor var1) {
         return null;
      }

      public RemoteViews makeContentView(NotificationBuilderWithBuilderAccessor var1) {
         return null;
      }

      public RemoteViews makeHeadsUpContentView(NotificationBuilderWithBuilderAccessor var1) {
         return null;
      }

      protected void restoreFromCompatExtras(Bundle var1) {
         if (var1.containsKey("android.summaryText")) {
            this.mSummaryText = var1.getCharSequence("android.summaryText");
            this.mSummaryTextSet = true;
         }

         this.mBigContentTitle = var1.getCharSequence("android.title.big");
      }

      public void setBuilder(Builder var1) {
         if (this.mBuilder != var1) {
            this.mBuilder = var1;
            if (var1 != null) {
               var1.setStyle(this);
            }
         }

      }
   }

   public static final class WearableExtender implements Extender {
      private static final int DEFAULT_CONTENT_ICON_GRAVITY = 8388613;
      private static final int DEFAULT_FLAGS = 1;
      private static final int DEFAULT_GRAVITY = 80;
      private static final String EXTRA_WEARABLE_EXTENSIONS = "android.wearable.EXTENSIONS";
      private static final int FLAG_BIG_PICTURE_AMBIENT = 32;
      private static final int FLAG_CONTENT_INTENT_AVAILABLE_OFFLINE = 1;
      private static final int FLAG_HINT_AVOID_BACKGROUND_CLIPPING = 16;
      private static final int FLAG_HINT_CONTENT_INTENT_LAUNCHES_ACTIVITY = 64;
      private static final int FLAG_HINT_HIDE_ICON = 2;
      private static final int FLAG_HINT_SHOW_BACKGROUND_ONLY = 4;
      private static final int FLAG_START_SCROLL_BOTTOM = 8;
      private static final String KEY_ACTIONS = "actions";
      private static final String KEY_BACKGROUND = "background";
      private static final String KEY_BRIDGE_TAG = "bridgeTag";
      private static final String KEY_CONTENT_ACTION_INDEX = "contentActionIndex";
      private static final String KEY_CONTENT_ICON = "contentIcon";
      private static final String KEY_CONTENT_ICON_GRAVITY = "contentIconGravity";
      private static final String KEY_CUSTOM_CONTENT_HEIGHT = "customContentHeight";
      private static final String KEY_CUSTOM_SIZE_PRESET = "customSizePreset";
      private static final String KEY_DISMISSAL_ID = "dismissalId";
      private static final String KEY_DISPLAY_INTENT = "displayIntent";
      private static final String KEY_FLAGS = "flags";
      private static final String KEY_GRAVITY = "gravity";
      private static final String KEY_HINT_SCREEN_TIMEOUT = "hintScreenTimeout";
      private static final String KEY_PAGES = "pages";
      @Deprecated
      public static final int SCREEN_TIMEOUT_LONG = -1;
      @Deprecated
      public static final int SCREEN_TIMEOUT_SHORT = 0;
      @Deprecated
      public static final int SIZE_DEFAULT = 0;
      @Deprecated
      public static final int SIZE_FULL_SCREEN = 5;
      @Deprecated
      public static final int SIZE_LARGE = 4;
      @Deprecated
      public static final int SIZE_MEDIUM = 3;
      @Deprecated
      public static final int SIZE_SMALL = 2;
      @Deprecated
      public static final int SIZE_XSMALL = 1;
      public static final int UNSET_ACTION_INDEX = -1;
      private ArrayList mActions = new ArrayList();
      private Bitmap mBackground;
      private String mBridgeTag;
      private int mContentActionIndex = -1;
      private int mContentIcon;
      private int mContentIconGravity = 8388613;
      private int mCustomContentHeight;
      private int mCustomSizePreset = 0;
      private String mDismissalId;
      private PendingIntent mDisplayIntent;
      private int mFlags = 1;
      private int mGravity = 80;
      private int mHintScreenTimeout;
      private ArrayList mPages = new ArrayList();

      public WearableExtender() {
      }

      public WearableExtender(Notification var1) {
         Bundle var6 = NotificationCompat.getExtras(var1);
         if (var6 != null) {
            var6 = var6.getBundle("android.wearable.EXTENSIONS");
         } else {
            var6 = null;
         }

         if (var6 != null) {
            ArrayList var5 = var6.getParcelableArrayList("actions");
            if (VERSION.SDK_INT >= 16 && var5 != null) {
               int var3 = var5.size();
               Action[] var4 = new Action[var3];

               for(int var2 = 0; var2 < var3; ++var2) {
                  if (VERSION.SDK_INT >= 20) {
                     var4[var2] = NotificationCompat.getActionCompatFromAction((Notification.Action)var5.get(var2));
                  } else if (VERSION.SDK_INT >= 16) {
                     var4[var2] = NotificationCompatJellybean.getActionFromBundle((Bundle)var5.get(var2));
                  }
               }

               Collections.addAll(this.mActions, var4);
            }

            this.mFlags = var6.getInt("flags", 1);
            this.mDisplayIntent = (PendingIntent)var6.getParcelable("displayIntent");
            Notification[] var7 = NotificationCompat.getNotificationArrayFromBundle(var6, "pages");
            if (var7 != null) {
               Collections.addAll(this.mPages, var7);
            }

            this.mBackground = (Bitmap)var6.getParcelable("background");
            this.mContentIcon = var6.getInt("contentIcon");
            this.mContentIconGravity = var6.getInt("contentIconGravity", 8388613);
            this.mContentActionIndex = var6.getInt("contentActionIndex", -1);
            this.mCustomSizePreset = var6.getInt("customSizePreset", 0);
            this.mCustomContentHeight = var6.getInt("customContentHeight");
            this.mGravity = var6.getInt("gravity", 80);
            this.mHintScreenTimeout = var6.getInt("hintScreenTimeout");
            this.mDismissalId = var6.getString("dismissalId");
            this.mBridgeTag = var6.getString("bridgeTag");
         }

      }

      private static Notification.Action getActionFromActionCompat(Action var0) {
         int var1 = VERSION.SDK_INT;
         byte var2 = 0;
         IconCompat var4;
         Notification.Action.Builder var9;
         if (var1 >= 23) {
            var4 = var0.getIconCompat();
            Icon var8;
            if (var4 == null) {
               var8 = null;
            } else {
               var8 = var4.toIcon();
            }

            var9 = new Notification.Action.Builder(var8, var0.getTitle(), var0.getActionIntent());
         } else {
            var4 = var0.getIconCompat();
            if (var4 != null && var4.getType() == 2) {
               var1 = var4.getResId();
            } else {
               var1 = 0;
            }

            var9 = new Notification.Action.Builder(var1, var0.getTitle(), var0.getActionIntent());
         }

         Bundle var5;
         if (var0.getExtras() != null) {
            var5 = new Bundle(var0.getExtras());
         } else {
            var5 = new Bundle();
         }

         var5.putBoolean("android.support.allowGeneratedReplies", var0.getAllowGeneratedReplies());
         if (VERSION.SDK_INT >= 24) {
            var9.setAllowGeneratedReplies(var0.getAllowGeneratedReplies());
         }

         var9.addExtras(var5);
         RemoteInput[] var6 = var0.getRemoteInputs();
         if (var6 != null) {
            android.app.RemoteInput[] var7 = RemoteInput.fromCompat(var6);
            int var3 = var7.length;

            for(var1 = var2; var1 < var3; ++var1) {
               var9.addRemoteInput(var7[var1]);
            }
         }

         return var9.build();
      }

      private void setFlag(int var1, boolean var2) {
         if (var2) {
            this.mFlags |= var1;
         } else {
            this.mFlags &= ~var1;
         }

      }

      public WearableExtender addAction(Action var1) {
         this.mActions.add(var1);
         return this;
      }

      public WearableExtender addActions(List var1) {
         this.mActions.addAll(var1);
         return this;
      }

      @Deprecated
      public WearableExtender addPage(Notification var1) {
         this.mPages.add(var1);
         return this;
      }

      @Deprecated
      public WearableExtender addPages(List var1) {
         this.mPages.addAll(var1);
         return this;
      }

      public WearableExtender clearActions() {
         this.mActions.clear();
         return this;
      }

      @Deprecated
      public WearableExtender clearPages() {
         this.mPages.clear();
         return this;
      }

      public WearableExtender clone() {
         WearableExtender var1 = new WearableExtender();
         var1.mActions = new ArrayList(this.mActions);
         var1.mFlags = this.mFlags;
         var1.mDisplayIntent = this.mDisplayIntent;
         var1.mPages = new ArrayList(this.mPages);
         var1.mBackground = this.mBackground;
         var1.mContentIcon = this.mContentIcon;
         var1.mContentIconGravity = this.mContentIconGravity;
         var1.mContentActionIndex = this.mContentActionIndex;
         var1.mCustomSizePreset = this.mCustomSizePreset;
         var1.mCustomContentHeight = this.mCustomContentHeight;
         var1.mGravity = this.mGravity;
         var1.mHintScreenTimeout = this.mHintScreenTimeout;
         var1.mDismissalId = this.mDismissalId;
         var1.mBridgeTag = this.mBridgeTag;
         return var1;
      }

      public Builder extend(Builder var1) {
         Bundle var3 = new Bundle();
         if (!this.mActions.isEmpty()) {
            if (VERSION.SDK_INT >= 16) {
               ArrayList var6 = new ArrayList(this.mActions.size());
               Iterator var5 = this.mActions.iterator();

               while(var5.hasNext()) {
                  Action var4 = (Action)var5.next();
                  if (VERSION.SDK_INT >= 20) {
                     var6.add(getActionFromActionCompat(var4));
                  } else if (VERSION.SDK_INT >= 16) {
                     var6.add(NotificationCompatJellybean.getBundleForAction(var4));
                  }
               }

               var3.putParcelableArrayList("actions", var6);
            } else {
               var3.putParcelableArrayList("actions", (ArrayList)null);
            }
         }

         int var2 = this.mFlags;
         if (var2 != 1) {
            var3.putInt("flags", var2);
         }

         PendingIntent var7 = this.mDisplayIntent;
         if (var7 != null) {
            var3.putParcelable("displayIntent", var7);
         }

         if (!this.mPages.isEmpty()) {
            ArrayList var8 = this.mPages;
            var3.putParcelableArray("pages", (Parcelable[])var8.toArray(new Notification[var8.size()]));
         }

         Bitmap var9 = this.mBackground;
         if (var9 != null) {
            var3.putParcelable("background", var9);
         }

         var2 = this.mContentIcon;
         if (var2 != 0) {
            var3.putInt("contentIcon", var2);
         }

         var2 = this.mContentIconGravity;
         if (var2 != 8388613) {
            var3.putInt("contentIconGravity", var2);
         }

         var2 = this.mContentActionIndex;
         if (var2 != -1) {
            var3.putInt("contentActionIndex", var2);
         }

         var2 = this.mCustomSizePreset;
         if (var2 != 0) {
            var3.putInt("customSizePreset", var2);
         }

         var2 = this.mCustomContentHeight;
         if (var2 != 0) {
            var3.putInt("customContentHeight", var2);
         }

         var2 = this.mGravity;
         if (var2 != 80) {
            var3.putInt("gravity", var2);
         }

         var2 = this.mHintScreenTimeout;
         if (var2 != 0) {
            var3.putInt("hintScreenTimeout", var2);
         }

         String var10 = this.mDismissalId;
         if (var10 != null) {
            var3.putString("dismissalId", var10);
         }

         var10 = this.mBridgeTag;
         if (var10 != null) {
            var3.putString("bridgeTag", var10);
         }

         var1.getExtras().putBundle("android.wearable.EXTENSIONS", var3);
         return var1;
      }

      public List getActions() {
         return this.mActions;
      }

      @Deprecated
      public Bitmap getBackground() {
         return this.mBackground;
      }

      public String getBridgeTag() {
         return this.mBridgeTag;
      }

      public int getContentAction() {
         return this.mContentActionIndex;
      }

      @Deprecated
      public int getContentIcon() {
         return this.mContentIcon;
      }

      @Deprecated
      public int getContentIconGravity() {
         return this.mContentIconGravity;
      }

      public boolean getContentIntentAvailableOffline() {
         int var1 = this.mFlags;
         boolean var2 = true;
         if ((var1 & 1) == 0) {
            var2 = false;
         }

         return var2;
      }

      @Deprecated
      public int getCustomContentHeight() {
         return this.mCustomContentHeight;
      }

      @Deprecated
      public int getCustomSizePreset() {
         return this.mCustomSizePreset;
      }

      public String getDismissalId() {
         return this.mDismissalId;
      }

      @Deprecated
      public PendingIntent getDisplayIntent() {
         return this.mDisplayIntent;
      }

      @Deprecated
      public int getGravity() {
         return this.mGravity;
      }

      @Deprecated
      public boolean getHintAmbientBigPicture() {
         boolean var1;
         if ((this.mFlags & 32) != 0) {
            var1 = true;
         } else {
            var1 = false;
         }

         return var1;
      }

      @Deprecated
      public boolean getHintAvoidBackgroundClipping() {
         boolean var1;
         if ((this.mFlags & 16) != 0) {
            var1 = true;
         } else {
            var1 = false;
         }

         return var1;
      }

      public boolean getHintContentIntentLaunchesActivity() {
         boolean var1;
         if ((this.mFlags & 64) != 0) {
            var1 = true;
         } else {
            var1 = false;
         }

         return var1;
      }

      @Deprecated
      public boolean getHintHideIcon() {
         boolean var1;
         if ((this.mFlags & 2) != 0) {
            var1 = true;
         } else {
            var1 = false;
         }

         return var1;
      }

      @Deprecated
      public int getHintScreenTimeout() {
         return this.mHintScreenTimeout;
      }

      @Deprecated
      public boolean getHintShowBackgroundOnly() {
         boolean var1;
         if ((this.mFlags & 4) != 0) {
            var1 = true;
         } else {
            var1 = false;
         }

         return var1;
      }

      @Deprecated
      public List getPages() {
         return this.mPages;
      }

      public boolean getStartScrollBottom() {
         boolean var1;
         if ((this.mFlags & 8) != 0) {
            var1 = true;
         } else {
            var1 = false;
         }

         return var1;
      }

      @Deprecated
      public WearableExtender setBackground(Bitmap var1) {
         this.mBackground = var1;
         return this;
      }

      public WearableExtender setBridgeTag(String var1) {
         this.mBridgeTag = var1;
         return this;
      }

      public WearableExtender setContentAction(int var1) {
         this.mContentActionIndex = var1;
         return this;
      }

      @Deprecated
      public WearableExtender setContentIcon(int var1) {
         this.mContentIcon = var1;
         return this;
      }

      @Deprecated
      public WearableExtender setContentIconGravity(int var1) {
         this.mContentIconGravity = var1;
         return this;
      }

      public WearableExtender setContentIntentAvailableOffline(boolean var1) {
         this.setFlag(1, var1);
         return this;
      }

      @Deprecated
      public WearableExtender setCustomContentHeight(int var1) {
         this.mCustomContentHeight = var1;
         return this;
      }

      @Deprecated
      public WearableExtender setCustomSizePreset(int var1) {
         this.mCustomSizePreset = var1;
         return this;
      }

      public WearableExtender setDismissalId(String var1) {
         this.mDismissalId = var1;
         return this;
      }

      @Deprecated
      public WearableExtender setDisplayIntent(PendingIntent var1) {
         this.mDisplayIntent = var1;
         return this;
      }

      @Deprecated
      public WearableExtender setGravity(int var1) {
         this.mGravity = var1;
         return this;
      }

      @Deprecated
      public WearableExtender setHintAmbientBigPicture(boolean var1) {
         this.setFlag(32, var1);
         return this;
      }

      @Deprecated
      public WearableExtender setHintAvoidBackgroundClipping(boolean var1) {
         this.setFlag(16, var1);
         return this;
      }

      public WearableExtender setHintContentIntentLaunchesActivity(boolean var1) {
         this.setFlag(64, var1);
         return this;
      }

      @Deprecated
      public WearableExtender setHintHideIcon(boolean var1) {
         this.setFlag(2, var1);
         return this;
      }

      @Deprecated
      public WearableExtender setHintScreenTimeout(int var1) {
         this.mHintScreenTimeout = var1;
         return this;
      }

      @Deprecated
      public WearableExtender setHintShowBackgroundOnly(boolean var1) {
         this.setFlag(4, var1);
         return this;
      }

      public WearableExtender setStartScrollBottom(boolean var1) {
         this.setFlag(8, var1);
         return this;
      }
   }
}
