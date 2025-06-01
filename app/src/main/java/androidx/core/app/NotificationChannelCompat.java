package androidx.core.app;

import android.app.Notification;
import android.app.NotificationChannel;
import android.media.AudioAttributes;
import android.net.Uri;
import android.os.Build.VERSION;
import android.provider.Settings.System;
import androidx.core.util.Preconditions;

public class NotificationChannelCompat {
   public static final String DEFAULT_CHANNEL_ID = "miscellaneous";
   private static final int DEFAULT_LIGHT_COLOR = 0;
   private static final boolean DEFAULT_SHOW_BADGE = true;
   AudioAttributes mAudioAttributes;
   private boolean mBypassDnd;
   private boolean mCanBubble;
   String mConversationId;
   String mDescription;
   String mGroupId;
   final String mId;
   int mImportance;
   private boolean mImportantConversation;
   int mLightColor;
   boolean mLights;
   private int mLockscreenVisibility;
   CharSequence mName;
   String mParentId;
   boolean mShowBadge;
   Uri mSound;
   boolean mVibrationEnabled;
   long[] mVibrationPattern;

   NotificationChannelCompat(NotificationChannel var1) {
      this(var1.getId(), var1.getImportance());
      this.mName = var1.getName();
      this.mDescription = var1.getDescription();
      this.mGroupId = var1.getGroup();
      this.mShowBadge = var1.canShowBadge();
      this.mSound = var1.getSound();
      this.mAudioAttributes = var1.getAudioAttributes();
      this.mLights = var1.shouldShowLights();
      this.mLightColor = var1.getLightColor();
      this.mVibrationEnabled = var1.shouldVibrate();
      this.mVibrationPattern = var1.getVibrationPattern();
      if (VERSION.SDK_INT >= 30) {
         this.mParentId = var1.getParentChannelId();
         this.mConversationId = var1.getConversationId();
      }

      this.mBypassDnd = var1.canBypassDnd();
      this.mLockscreenVisibility = var1.getLockscreenVisibility();
      if (VERSION.SDK_INT >= 29) {
         this.mCanBubble = var1.canBubble();
      }

      if (VERSION.SDK_INT >= 30) {
         this.mImportantConversation = var1.isImportantConversation();
      }

   }

   NotificationChannelCompat(String var1, int var2) {
      this.mShowBadge = true;
      this.mSound = System.DEFAULT_NOTIFICATION_URI;
      this.mLightColor = 0;
      this.mId = (String)Preconditions.checkNotNull(var1);
      this.mImportance = var2;
      if (VERSION.SDK_INT >= 21) {
         this.mAudioAttributes = Notification.AUDIO_ATTRIBUTES_DEFAULT;
      }

   }

   public boolean canBubble() {
      return this.mCanBubble;
   }

   public boolean canBypassDnd() {
      return this.mBypassDnd;
   }

   public boolean canShowBadge() {
      return this.mShowBadge;
   }

   public AudioAttributes getAudioAttributes() {
      return this.mAudioAttributes;
   }

   public String getConversationId() {
      return this.mConversationId;
   }

   public String getDescription() {
      return this.mDescription;
   }

   public String getGroup() {
      return this.mGroupId;
   }

   public String getId() {
      return this.mId;
   }

   public int getImportance() {
      return this.mImportance;
   }

   public int getLightColor() {
      return this.mLightColor;
   }

   public int getLockscreenVisibility() {
      return this.mLockscreenVisibility;
   }

   public CharSequence getName() {
      return this.mName;
   }

   NotificationChannel getNotificationChannel() {
      if (VERSION.SDK_INT < 26) {
         return null;
      } else {
         NotificationChannel var1 = new NotificationChannel(this.mId, this.mName, this.mImportance);
         var1.setDescription(this.mDescription);
         var1.setGroup(this.mGroupId);
         var1.setShowBadge(this.mShowBadge);
         var1.setSound(this.mSound, this.mAudioAttributes);
         var1.enableLights(this.mLights);
         var1.setLightColor(this.mLightColor);
         var1.setVibrationPattern(this.mVibrationPattern);
         var1.enableVibration(this.mVibrationEnabled);
         if (VERSION.SDK_INT >= 30) {
            String var3 = this.mParentId;
            if (var3 != null) {
               String var2 = this.mConversationId;
               if (var2 != null) {
                  var1.setConversationId(var3, var2);
               }
            }
         }

         return var1;
      }
   }

   public String getParentChannelId() {
      return this.mParentId;
   }

   public Uri getSound() {
      return this.mSound;
   }

   public long[] getVibrationPattern() {
      return this.mVibrationPattern;
   }

   public boolean isImportantConversation() {
      return this.mImportantConversation;
   }

   public boolean shouldShowLights() {
      return this.mLights;
   }

   public boolean shouldVibrate() {
      return this.mVibrationEnabled;
   }

   public Builder toBuilder() {
      return (new Builder(this.mId, this.mImportance)).setName(this.mName).setDescription(this.mDescription).setGroup(this.mGroupId).setShowBadge(this.mShowBadge).setSound(this.mSound, this.mAudioAttributes).setLightsEnabled(this.mLights).setLightColor(this.mLightColor).setVibrationEnabled(this.mVibrationEnabled).setVibrationPattern(this.mVibrationPattern).setConversationId(this.mParentId, this.mConversationId);
   }

   public static class Builder {
      private final NotificationChannelCompat mChannel;

      public Builder(String var1, int var2) {
         this.mChannel = new NotificationChannelCompat(var1, var2);
      }

      public NotificationChannelCompat build() {
         return this.mChannel;
      }

      public Builder setConversationId(String var1, String var2) {
         if (VERSION.SDK_INT >= 30) {
            this.mChannel.mParentId = var1;
            this.mChannel.mConversationId = var2;
         }

         return this;
      }

      public Builder setDescription(String var1) {
         this.mChannel.mDescription = var1;
         return this;
      }

      public Builder setGroup(String var1) {
         this.mChannel.mGroupId = var1;
         return this;
      }

      public Builder setImportance(int var1) {
         this.mChannel.mImportance = var1;
         return this;
      }

      public Builder setLightColor(int var1) {
         this.mChannel.mLightColor = var1;
         return this;
      }

      public Builder setLightsEnabled(boolean var1) {
         this.mChannel.mLights = var1;
         return this;
      }

      public Builder setName(CharSequence var1) {
         this.mChannel.mName = var1;
         return this;
      }

      public Builder setShowBadge(boolean var1) {
         this.mChannel.mShowBadge = var1;
         return this;
      }

      public Builder setSound(Uri var1, AudioAttributes var2) {
         this.mChannel.mSound = var1;
         this.mChannel.mAudioAttributes = var2;
         return this;
      }

      public Builder setVibrationEnabled(boolean var1) {
         this.mChannel.mVibrationEnabled = var1;
         return this;
      }

      public Builder setVibrationPattern(long[] var1) {
         NotificationChannelCompat var3 = this.mChannel;
         boolean var2;
         if (var1 != null && var1.length > 0) {
            var2 = true;
         } else {
            var2 = false;
         }

         var3.mVibrationEnabled = var2;
         this.mChannel.mVibrationPattern = var1;
         return this;
      }
   }
}
