package androidx.core.content.pm;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ShortcutInfo;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.PersistableBundle;
import android.os.UserHandle;
import android.os.Build.VERSION;
import android.text.TextUtils;
import androidx.core.app.Person;
import androidx.core.content.LocusIdCompat;
import androidx.core.graphics.drawable.IconCompat;
import androidx.core.net.UriCompat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ShortcutInfoCompat {
   private static final String EXTRA_LOCUS_ID = "extraLocusId";
   private static final String EXTRA_LONG_LIVED = "extraLongLived";
   private static final String EXTRA_PERSON_ = "extraPerson_";
   private static final String EXTRA_PERSON_COUNT = "extraPersonCount";
   private static final String EXTRA_SLICE_URI = "extraSliceUri";
   ComponentName mActivity;
   Set mCategories;
   Context mContext;
   CharSequence mDisabledMessage;
   int mDisabledReason;
   PersistableBundle mExtras;
   boolean mHasKeyFieldsOnly;
   IconCompat mIcon;
   String mId;
   Intent[] mIntents;
   boolean mIsAlwaysBadged;
   boolean mIsCached;
   boolean mIsDeclaredInManifest;
   boolean mIsDynamic;
   boolean mIsEnabled = true;
   boolean mIsImmutable;
   boolean mIsLongLived;
   boolean mIsPinned;
   CharSequence mLabel;
   long mLastChangedTimestamp;
   LocusIdCompat mLocusId;
   CharSequence mLongLabel;
   String mPackageName;
   Person[] mPersons;
   int mRank;
   UserHandle mUser;

   ShortcutInfoCompat() {
   }

   private PersistableBundle buildLegacyExtrasBundle() {
      if (this.mExtras == null) {
         this.mExtras = new PersistableBundle();
      }

      Person[] var3 = this.mPersons;
      if (var3 != null && var3.length > 0) {
         this.mExtras.putInt("extraPersonCount", var3.length);

         int var2;
         for(int var1 = 0; var1 < this.mPersons.length; var1 = var2) {
            PersistableBundle var5 = this.mExtras;
            StringBuilder var4 = (new StringBuilder()).append("extraPerson_");
            var2 = var1 + 1;
            var5.putPersistableBundle(var4.append(var2).toString(), this.mPersons[var1].toPersistableBundle());
         }
      }

      LocusIdCompat var6 = this.mLocusId;
      if (var6 != null) {
         this.mExtras.putString("extraLocusId", var6.getId());
      }

      this.mExtras.putBoolean("extraLongLived", this.mIsLongLived);
      return this.mExtras;
   }

   static List fromShortcuts(Context var0, List var1) {
      ArrayList var2 = new ArrayList(var1.size());
      Iterator var3 = var1.iterator();

      while(var3.hasNext()) {
         var2.add((new Builder(var0, (ShortcutInfo)var3.next())).build());
      }

      return var2;
   }

   static LocusIdCompat getLocusId(ShortcutInfo var0) {
      if (VERSION.SDK_INT >= 29) {
         return var0.getLocusId() == null ? null : LocusIdCompat.toLocusIdCompat(var0.getLocusId());
      } else {
         return getLocusIdFromExtra(var0.getExtras());
      }
   }

   private static LocusIdCompat getLocusIdFromExtra(PersistableBundle var0) {
      Object var1 = null;
      if (var0 == null) {
         return null;
      } else {
         String var2 = var0.getString("extraLocusId");
         LocusIdCompat var3;
         if (var2 == null) {
            var3 = (LocusIdCompat)var1;
         } else {
            var3 = new LocusIdCompat(var2);
         }

         return var3;
      }
   }

   static boolean getLongLivedFromExtra(PersistableBundle var0) {
      return var0 != null && var0.containsKey("extraLongLived") ? var0.getBoolean("extraLongLived") : false;
   }

   static Person[] getPersonsFromExtra(PersistableBundle var0) {
      if (var0 != null && var0.containsKey("extraPersonCount")) {
         int var3 = var0.getInt("extraPersonCount");
         Person[] var4 = new Person[var3];

         int var2;
         for(int var1 = 0; var1 < var3; var1 = var2) {
            StringBuilder var5 = (new StringBuilder()).append("extraPerson_");
            var2 = var1 + 1;
            var4[var1] = Person.fromPersistableBundle(var0.getPersistableBundle(var5.append(var2).toString()));
         }

         return var4;
      } else {
         return null;
      }
   }

   Intent addToIntent(Intent var1) {
      Intent[] var2 = this.mIntents;
      var1.putExtra("android.intent.extra.shortcut.INTENT", var2[var2.length - 1]).putExtra("android.intent.extra.shortcut.NAME", this.mLabel.toString());
      if (this.mIcon != null) {
         Drawable var3 = null;
         Object var4 = null;
         if (this.mIsAlwaysBadged) {
            PackageManager var5 = this.mContext.getPackageManager();
            ComponentName var8 = this.mActivity;
            Drawable var7 = (Drawable)var4;
            if (var8 != null) {
               try {
                  var7 = var5.getActivityIcon(var8);
               } catch (PackageManager.NameNotFoundException var6) {
                  var7 = (Drawable)var4;
               }
            }

            var3 = var7;
            if (var7 == null) {
               var3 = this.mContext.getApplicationInfo().loadIcon(var5);
            }
         }

         this.mIcon.addToShortcutIntent(var1, var3, this.mContext);
      }

      return var1;
   }

   public ComponentName getActivity() {
      return this.mActivity;
   }

   public Set getCategories() {
      return this.mCategories;
   }

   public CharSequence getDisabledMessage() {
      return this.mDisabledMessage;
   }

   public int getDisabledReason() {
      return this.mDisabledReason;
   }

   public PersistableBundle getExtras() {
      return this.mExtras;
   }

   public IconCompat getIcon() {
      return this.mIcon;
   }

   public String getId() {
      return this.mId;
   }

   public Intent getIntent() {
      Intent[] var1 = this.mIntents;
      return var1[var1.length - 1];
   }

   public Intent[] getIntents() {
      Intent[] var1 = this.mIntents;
      return (Intent[])Arrays.copyOf(var1, var1.length);
   }

   public long getLastChangedTimestamp() {
      return this.mLastChangedTimestamp;
   }

   public LocusIdCompat getLocusId() {
      return this.mLocusId;
   }

   public CharSequence getLongLabel() {
      return this.mLongLabel;
   }

   public String getPackage() {
      return this.mPackageName;
   }

   public int getRank() {
      return this.mRank;
   }

   public CharSequence getShortLabel() {
      return this.mLabel;
   }

   public UserHandle getUserHandle() {
      return this.mUser;
   }

   public boolean hasKeyFieldsOnly() {
      return this.mHasKeyFieldsOnly;
   }

   public boolean isCached() {
      return this.mIsCached;
   }

   public boolean isDeclaredInManifest() {
      return this.mIsDeclaredInManifest;
   }

   public boolean isDynamic() {
      return this.mIsDynamic;
   }

   public boolean isEnabled() {
      return this.mIsEnabled;
   }

   public boolean isImmutable() {
      return this.mIsImmutable;
   }

   public boolean isPinned() {
      return this.mIsPinned;
   }

   public ShortcutInfo toShortcutInfo() {
      ShortcutInfo.Builder var3 = (new ShortcutInfo.Builder(this.mContext, this.mId)).setShortLabel(this.mLabel).setIntents(this.mIntents);
      IconCompat var4 = this.mIcon;
      if (var4 != null) {
         var3.setIcon(var4.toIcon(this.mContext));
      }

      if (!TextUtils.isEmpty(this.mLongLabel)) {
         var3.setLongLabel(this.mLongLabel);
      }

      if (!TextUtils.isEmpty(this.mDisabledMessage)) {
         var3.setDisabledMessage(this.mDisabledMessage);
      }

      ComponentName var5 = this.mActivity;
      if (var5 != null) {
         var3.setActivity(var5);
      }

      Set var6 = this.mCategories;
      if (var6 != null) {
         var3.setCategories(var6);
      }

      var3.setRank(this.mRank);
      PersistableBundle var7 = this.mExtras;
      if (var7 != null) {
         var3.setExtras(var7);
      }

      if (VERSION.SDK_INT >= 29) {
         Person[] var8 = this.mPersons;
         if (var8 != null && var8.length > 0) {
            int var2 = var8.length;
            android.app.Person[] var9 = new android.app.Person[var2];

            for(int var1 = 0; var1 < var2; ++var1) {
               var9[var1] = this.mPersons[var1].toAndroidPerson();
            }

            var3.setPersons(var9);
         }

         LocusIdCompat var10 = this.mLocusId;
         if (var10 != null) {
            var3.setLocusId(var10.toLocusId());
         }

         var3.setLongLived(this.mIsLongLived);
      } else {
         var3.setExtras(this.buildLegacyExtrasBundle());
      }

      return var3.build();
   }

   public static class Builder {
      private Map mCapabilityBindingParams;
      private Set mCapabilityBindings;
      private final ShortcutInfoCompat mInfo;
      private boolean mIsConversation;
      private Uri mSliceUri;

      public Builder(Context var1, ShortcutInfo var2) {
         ShortcutInfoCompat var4 = new ShortcutInfoCompat();
         this.mInfo = var4;
         var4.mContext = var1;
         var4.mId = var2.getId();
         var4.mPackageName = var2.getPackage();
         Intent[] var5 = var2.getIntents();
         var4.mIntents = (Intent[])Arrays.copyOf(var5, var5.length);
         var4.mActivity = var2.getActivity();
         var4.mLabel = var2.getShortLabel();
         var4.mLongLabel = var2.getLongLabel();
         var4.mDisabledMessage = var2.getDisabledMessage();
         if (VERSION.SDK_INT >= 28) {
            var4.mDisabledReason = var2.getDisabledReason();
         } else {
            byte var3;
            if (var2.isEnabled()) {
               var3 = 0;
            } else {
               var3 = 3;
            }

            var4.mDisabledReason = var3;
         }

         var4.mCategories = var2.getCategories();
         var4.mPersons = ShortcutInfoCompat.getPersonsFromExtra(var2.getExtras());
         var4.mUser = var2.getUserHandle();
         var4.mLastChangedTimestamp = var2.getLastChangedTimestamp();
         if (VERSION.SDK_INT >= 30) {
            var4.mIsCached = var2.isCached();
         }

         var4.mIsDynamic = var2.isDynamic();
         var4.mIsPinned = var2.isPinned();
         var4.mIsDeclaredInManifest = var2.isDeclaredInManifest();
         var4.mIsImmutable = var2.isImmutable();
         var4.mIsEnabled = var2.isEnabled();
         var4.mHasKeyFieldsOnly = var2.hasKeyFieldsOnly();
         var4.mLocusId = ShortcutInfoCompat.getLocusId(var2);
         var4.mRank = var2.getRank();
         var4.mExtras = var2.getExtras();
      }

      public Builder(Context var1, String var2) {
         ShortcutInfoCompat var3 = new ShortcutInfoCompat();
         this.mInfo = var3;
         var3.mContext = var1;
         var3.mId = var2;
      }

      public Builder(ShortcutInfoCompat var1) {
         ShortcutInfoCompat var2 = new ShortcutInfoCompat();
         this.mInfo = var2;
         var2.mContext = var1.mContext;
         var2.mId = var1.mId;
         var2.mPackageName = var1.mPackageName;
         var2.mIntents = (Intent[])Arrays.copyOf(var1.mIntents, var1.mIntents.length);
         var2.mActivity = var1.mActivity;
         var2.mLabel = var1.mLabel;
         var2.mLongLabel = var1.mLongLabel;
         var2.mDisabledMessage = var1.mDisabledMessage;
         var2.mDisabledReason = var1.mDisabledReason;
         var2.mIcon = var1.mIcon;
         var2.mIsAlwaysBadged = var1.mIsAlwaysBadged;
         var2.mUser = var1.mUser;
         var2.mLastChangedTimestamp = var1.mLastChangedTimestamp;
         var2.mIsCached = var1.mIsCached;
         var2.mIsDynamic = var1.mIsDynamic;
         var2.mIsPinned = var1.mIsPinned;
         var2.mIsDeclaredInManifest = var1.mIsDeclaredInManifest;
         var2.mIsImmutable = var1.mIsImmutable;
         var2.mIsEnabled = var1.mIsEnabled;
         var2.mLocusId = var1.mLocusId;
         var2.mIsLongLived = var1.mIsLongLived;
         var2.mHasKeyFieldsOnly = var1.mHasKeyFieldsOnly;
         var2.mRank = var1.mRank;
         if (var1.mPersons != null) {
            var2.mPersons = (Person[])Arrays.copyOf(var1.mPersons, var1.mPersons.length);
         }

         if (var1.mCategories != null) {
            var2.mCategories = new HashSet(var1.mCategories);
         }

         if (var1.mExtras != null) {
            var2.mExtras = var1.mExtras;
         }

      }

      public Builder addCapabilityBinding(String var1) {
         if (this.mCapabilityBindings == null) {
            this.mCapabilityBindings = new HashSet();
         }

         this.mCapabilityBindings.add(var1);
         return this;
      }

      public Builder addCapabilityBinding(String var1, String var2, List var3) {
         this.addCapabilityBinding(var1);
         if (!var3.isEmpty()) {
            if (this.mCapabilityBindingParams == null) {
               this.mCapabilityBindingParams = new HashMap();
            }

            if (this.mCapabilityBindingParams.get(var1) == null) {
               this.mCapabilityBindingParams.put(var1, new HashMap());
            }

            ((Map)this.mCapabilityBindingParams.get(var1)).put(var2, var3);
         }

         return this;
      }

      public ShortcutInfoCompat build() {
         if (TextUtils.isEmpty(this.mInfo.mLabel)) {
            throw new IllegalArgumentException("Shortcut must have a non-empty label");
         } else if (this.mInfo.mIntents != null && this.mInfo.mIntents.length != 0) {
            if (this.mIsConversation) {
               if (this.mInfo.mLocusId == null) {
                  this.mInfo.mLocusId = new LocusIdCompat(this.mInfo.mId);
               }

               this.mInfo.mIsLongLived = true;
            }

            if (this.mCapabilityBindings != null) {
               if (this.mInfo.mCategories == null) {
                  this.mInfo.mCategories = new HashSet();
               }

               this.mInfo.mCategories.addAll(this.mCapabilityBindings);
            }

            if (VERSION.SDK_INT >= 21) {
               if (this.mCapabilityBindingParams != null) {
                  if (this.mInfo.mExtras == null) {
                     this.mInfo.mExtras = new PersistableBundle();
                  }

                  Iterator var4 = this.mCapabilityBindingParams.keySet().iterator();

                  while(var4.hasNext()) {
                     String var3 = (String)var4.next();
                     Map var2 = (Map)this.mCapabilityBindingParams.get(var3);
                     Set var1 = var2.keySet();
                     this.mInfo.mExtras.putStringArray(var3, (String[])var1.toArray(new String[0]));

                     PersistableBundle var6;
                     String var7;
                     String[] var9;
                     for(Iterator var5 = var2.keySet().iterator(); var5.hasNext(); var6.putStringArray(var7, var9)) {
                        var7 = (String)var5.next();
                        List var8 = (List)var2.get(var7);
                        var6 = this.mInfo.mExtras;
                        var7 = var3 + "/" + var7;
                        if (var8 == null) {
                           var9 = new String[0];
                        } else {
                           var9 = (String[])var8.toArray(new String[0]);
                        }
                     }
                  }
               }

               if (this.mSliceUri != null) {
                  if (this.mInfo.mExtras == null) {
                     this.mInfo.mExtras = new PersistableBundle();
                  }

                  this.mInfo.mExtras.putString("extraSliceUri", UriCompat.toSafeString(this.mSliceUri));
               }
            }

            return this.mInfo;
         } else {
            throw new IllegalArgumentException("Shortcut must have an intent");
         }
      }

      public Builder setActivity(ComponentName var1) {
         this.mInfo.mActivity = var1;
         return this;
      }

      public Builder setAlwaysBadged() {
         this.mInfo.mIsAlwaysBadged = true;
         return this;
      }

      public Builder setCategories(Set var1) {
         this.mInfo.mCategories = var1;
         return this;
      }

      public Builder setDisabledMessage(CharSequence var1) {
         this.mInfo.mDisabledMessage = var1;
         return this;
      }

      public Builder setExtras(PersistableBundle var1) {
         this.mInfo.mExtras = var1;
         return this;
      }

      public Builder setIcon(IconCompat var1) {
         this.mInfo.mIcon = var1;
         return this;
      }

      public Builder setIntent(Intent var1) {
         return this.setIntents(new Intent[]{var1});
      }

      public Builder setIntents(Intent[] var1) {
         this.mInfo.mIntents = var1;
         return this;
      }

      public Builder setIsConversation() {
         this.mIsConversation = true;
         return this;
      }

      public Builder setLocusId(LocusIdCompat var1) {
         this.mInfo.mLocusId = var1;
         return this;
      }

      public Builder setLongLabel(CharSequence var1) {
         this.mInfo.mLongLabel = var1;
         return this;
      }

      @Deprecated
      public Builder setLongLived() {
         this.mInfo.mIsLongLived = true;
         return this;
      }

      public Builder setLongLived(boolean var1) {
         this.mInfo.mIsLongLived = var1;
         return this;
      }

      public Builder setPerson(Person var1) {
         return this.setPersons(new Person[]{var1});
      }

      public Builder setPersons(Person[] var1) {
         this.mInfo.mPersons = var1;
         return this;
      }

      public Builder setRank(int var1) {
         this.mInfo.mRank = var1;
         return this;
      }

      public Builder setShortLabel(CharSequence var1) {
         this.mInfo.mLabel = var1;
         return this;
      }

      public Builder setSliceUri(Uri var1) {
         this.mSliceUri = var1;
         return this;
      }
   }
}
