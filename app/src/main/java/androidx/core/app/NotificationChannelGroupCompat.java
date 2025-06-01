package androidx.core.app;

import android.app.NotificationChannel;
import android.app.NotificationChannelGroup;
import android.os.Build.VERSION;
import androidx.core.util.Preconditions;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class NotificationChannelGroupCompat {
   private boolean mBlocked;
   private List mChannels;
   String mDescription;
   final String mId;
   CharSequence mName;

   NotificationChannelGroupCompat(NotificationChannelGroup var1) {
      this(var1, Collections.emptyList());
   }

   NotificationChannelGroupCompat(NotificationChannelGroup var1, List var2) {
      this(var1.getId());
      this.mName = var1.getName();
      if (VERSION.SDK_INT >= 28) {
         this.mDescription = var1.getDescription();
      }

      if (VERSION.SDK_INT >= 28) {
         this.mBlocked = var1.isBlocked();
         this.mChannels = this.getChannelsCompat(var1.getChannels());
      } else {
         this.mChannels = this.getChannelsCompat(var2);
      }

   }

   NotificationChannelGroupCompat(String var1) {
      this.mChannels = Collections.emptyList();
      this.mId = (String)Preconditions.checkNotNull(var1);
   }

   private List getChannelsCompat(List var1) {
      ArrayList var2 = new ArrayList();
      Iterator var3 = var1.iterator();

      while(var3.hasNext()) {
         NotificationChannel var4 = (NotificationChannel)var3.next();
         if (this.mId.equals(var4.getGroup())) {
            var2.add(new NotificationChannelCompat(var4));
         }
      }

      return var2;
   }

   public List getChannels() {
      return this.mChannels;
   }

   public String getDescription() {
      return this.mDescription;
   }

   public String getId() {
      return this.mId;
   }

   public CharSequence getName() {
      return this.mName;
   }

   NotificationChannelGroup getNotificationChannelGroup() {
      if (VERSION.SDK_INT < 26) {
         return null;
      } else {
         NotificationChannelGroup var1 = new NotificationChannelGroup(this.mId, this.mName);
         if (VERSION.SDK_INT >= 28) {
            var1.setDescription(this.mDescription);
         }

         return var1;
      }
   }

   public boolean isBlocked() {
      return this.mBlocked;
   }

   public Builder toBuilder() {
      return (new Builder(this.mId)).setName(this.mName).setDescription(this.mDescription);
   }

   public static class Builder {
      final NotificationChannelGroupCompat mGroup;

      public Builder(String var1) {
         this.mGroup = new NotificationChannelGroupCompat(var1);
      }

      public NotificationChannelGroupCompat build() {
         return this.mGroup;
      }

      public Builder setDescription(String var1) {
         this.mGroup.mDescription = var1;
         return this;
      }

      public Builder setName(CharSequence var1) {
         this.mGroup.mName = var1;
         return this;
      }
   }
}
