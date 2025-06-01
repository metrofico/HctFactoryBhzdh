package androidx.core.view;

import android.content.ClipData;
import android.content.ClipDescription;
import android.net.Uri;
import android.os.Bundle;
import android.os.Build.VERSION;
import android.util.Pair;
import android.view.ContentInfo;
import androidx.core.util.Preconditions;
import androidx.core.util.Predicate;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class ContentInfoCompat {
   public static final int FLAG_CONVERT_TO_PLAIN_TEXT = 1;
   public static final int SOURCE_APP = 0;
   public static final int SOURCE_AUTOFILL = 4;
   public static final int SOURCE_CLIPBOARD = 1;
   public static final int SOURCE_DRAG_AND_DROP = 3;
   public static final int SOURCE_INPUT_METHOD = 2;
   public static final int SOURCE_PROCESS_TEXT = 5;
   private final Compat mCompat;

   ContentInfoCompat(Compat var1) {
      this.mCompat = var1;
   }

   static ClipData buildClipData(ClipDescription var0, List var1) {
      ClipData var3 = new ClipData(new ClipDescription(var0), (ClipData.Item)var1.get(0));

      for(int var2 = 1; var2 < var1.size(); ++var2) {
         var3.addItem((ClipData.Item)var1.get(var2));
      }

      return var3;
   }

   static String flagsToString(int var0) {
      return (var0 & 1) != 0 ? "FLAG_CONVERT_TO_PLAIN_TEXT" : String.valueOf(var0);
   }

   static Pair partition(ClipData var0, Predicate var1) {
      int var2 = 0;
      ArrayList var4 = null;

      ArrayList var3;
      for(var3 = null; var2 < var0.getItemCount(); ++var2) {
         ClipData.Item var6 = var0.getItemAt(var2);
         ArrayList var5;
         if (var1.test(var6)) {
            var5 = var4;
            if (var4 == null) {
               var5 = new ArrayList();
            }

            var5.add(var6);
            var4 = var5;
         } else {
            var5 = var3;
            if (var3 == null) {
               var5 = new ArrayList();
            }

            var5.add(var6);
            var3 = var5;
         }
      }

      if (var4 == null) {
         return Pair.create((Object)null, var0);
      } else if (var3 == null) {
         return Pair.create(var0, (Object)null);
      } else {
         return Pair.create(buildClipData(var0.getDescription(), var4), buildClipData(var0.getDescription(), var3));
      }
   }

   public static Pair partition(ContentInfo var0, java.util.function.Predicate var1) {
      return ContentInfoCompat.Api31Impl.partition(var0, var1);
   }

   static String sourceToString(int var0) {
      if (var0 != 0) {
         if (var0 != 1) {
            if (var0 != 2) {
               if (var0 != 3) {
                  if (var0 != 4) {
                     return var0 != 5 ? String.valueOf(var0) : "SOURCE_PROCESS_TEXT";
                  } else {
                     return "SOURCE_AUTOFILL";
                  }
               } else {
                  return "SOURCE_DRAG_AND_DROP";
               }
            } else {
               return "SOURCE_INPUT_METHOD";
            }
         } else {
            return "SOURCE_CLIPBOARD";
         }
      } else {
         return "SOURCE_APP";
      }
   }

   public static ContentInfoCompat toContentInfoCompat(ContentInfo var0) {
      return new ContentInfoCompat(new Compat31Impl(var0));
   }

   public ClipData getClip() {
      return this.mCompat.getClip();
   }

   public Bundle getExtras() {
      return this.mCompat.getExtras();
   }

   public int getFlags() {
      return this.mCompat.getFlags();
   }

   public Uri getLinkUri() {
      return this.mCompat.getLinkUri();
   }

   public int getSource() {
      return this.mCompat.getSource();
   }

   public Pair partition(Predicate var1) {
      ClipData var5 = this.mCompat.getClip();
      int var2 = var5.getItemCount();
      ContentInfoCompat var4 = null;
      if (var2 == 1) {
         boolean var3 = var1.test(var5.getItemAt(0));
         ContentInfoCompat var7;
         if (var3) {
            var7 = this;
         } else {
            var7 = null;
         }

         if (!var3) {
            var4 = this;
         }

         return Pair.create(var7, var4);
      } else {
         Pair var6 = partition(var5, var1);
         if (var6.first == null) {
            return Pair.create((Object)null, this);
         } else {
            return var6.second == null ? Pair.create(this, (Object)null) : Pair.create((new Builder(this)).setClip((ClipData)var6.first).build(), (new Builder(this)).setClip((ClipData)var6.second).build());
         }
      }
   }

   public ContentInfo toContentInfo() {
      return this.mCompat.getWrapped();
   }

   public String toString() {
      return this.mCompat.toString();
   }

   private static final class Api31Impl {
      public static Pair partition(ContentInfo var0, java.util.function.Predicate var1) {
         ClipData var3 = var0.getClip();
         if (var3.getItemCount() == 1) {
            boolean var2 = var1.test(var3.getItemAt(0));
            ContentInfo var5;
            if (var2) {
               var5 = var0;
            } else {
               var5 = null;
            }

            if (var2) {
               var0 = null;
            }

            return Pair.create(var5, var0);
         } else {
            Objects.requireNonNull(var1);
            Pair var4 = ContentInfoCompat.partition((ClipData)var3, (Predicate)(new ContentInfoCompat$Api31Impl$$ExternalSyntheticLambda0(var1)));
            if (var4.first == null) {
               return Pair.create((Object)null, var0);
            } else {
               return var4.second == null ? Pair.create(var0, (Object)null) : Pair.create((new ContentInfo.Builder(var0)).setClip((ClipData)var4.first).build(), (new ContentInfo.Builder(var0)).setClip((ClipData)var4.second).build());
            }
         }
      }
   }

   public static final class Builder {
      private final BuilderCompat mBuilderCompat;

      public Builder(ClipData var1, int var2) {
         if (VERSION.SDK_INT >= 31) {
            this.mBuilderCompat = new BuilderCompat31Impl(var1, var2);
         } else {
            this.mBuilderCompat = new BuilderCompatImpl(var1, var2);
         }

      }

      public Builder(ContentInfoCompat var1) {
         if (VERSION.SDK_INT >= 31) {
            this.mBuilderCompat = new BuilderCompat31Impl(var1);
         } else {
            this.mBuilderCompat = new BuilderCompatImpl(var1);
         }

      }

      public ContentInfoCompat build() {
         return this.mBuilderCompat.build();
      }

      public Builder setClip(ClipData var1) {
         this.mBuilderCompat.setClip(var1);
         return this;
      }

      public Builder setExtras(Bundle var1) {
         this.mBuilderCompat.setExtras(var1);
         return this;
      }

      public Builder setFlags(int var1) {
         this.mBuilderCompat.setFlags(var1);
         return this;
      }

      public Builder setLinkUri(Uri var1) {
         this.mBuilderCompat.setLinkUri(var1);
         return this;
      }

      public Builder setSource(int var1) {
         this.mBuilderCompat.setSource(var1);
         return this;
      }
   }

   private interface BuilderCompat {
      ContentInfoCompat build();

      void setClip(ClipData var1);

      void setExtras(Bundle var1);

      void setFlags(int var1);

      void setLinkUri(Uri var1);

      void setSource(int var1);
   }

   private static final class BuilderCompat31Impl implements BuilderCompat {
      private final ContentInfo.Builder mPlatformBuilder;

      BuilderCompat31Impl(ClipData var1, int var2) {
         this.mPlatformBuilder = new ContentInfo.Builder(var1, var2);
      }

      BuilderCompat31Impl(ContentInfoCompat var1) {
         this.mPlatformBuilder = new ContentInfo.Builder(var1.toContentInfo());
      }

      public ContentInfoCompat build() {
         return new ContentInfoCompat(new Compat31Impl(this.mPlatformBuilder.build()));
      }

      public void setClip(ClipData var1) {
         this.mPlatformBuilder.setClip(var1);
      }

      public void setExtras(Bundle var1) {
         this.mPlatformBuilder.setExtras(var1);
      }

      public void setFlags(int var1) {
         this.mPlatformBuilder.setFlags(var1);
      }

      public void setLinkUri(Uri var1) {
         this.mPlatformBuilder.setLinkUri(var1);
      }

      public void setSource(int var1) {
         this.mPlatformBuilder.setSource(var1);
      }
   }

   private static final class BuilderCompatImpl implements BuilderCompat {
      ClipData mClip;
      Bundle mExtras;
      int mFlags;
      Uri mLinkUri;
      int mSource;

      BuilderCompatImpl(ClipData var1, int var2) {
         this.mClip = var1;
         this.mSource = var2;
      }

      BuilderCompatImpl(ContentInfoCompat var1) {
         this.mClip = var1.getClip();
         this.mSource = var1.getSource();
         this.mFlags = var1.getFlags();
         this.mLinkUri = var1.getLinkUri();
         this.mExtras = var1.getExtras();
      }

      public ContentInfoCompat build() {
         return new ContentInfoCompat(new CompatImpl(this));
      }

      public void setClip(ClipData var1) {
         this.mClip = var1;
      }

      public void setExtras(Bundle var1) {
         this.mExtras = var1;
      }

      public void setFlags(int var1) {
         this.mFlags = var1;
      }

      public void setLinkUri(Uri var1) {
         this.mLinkUri = var1;
      }

      public void setSource(int var1) {
         this.mSource = var1;
      }
   }

   private interface Compat {
      ClipData getClip();

      Bundle getExtras();

      int getFlags();

      Uri getLinkUri();

      int getSource();

      ContentInfo getWrapped();
   }

   private static final class Compat31Impl implements Compat {
      private final ContentInfo mWrapped;

      Compat31Impl(ContentInfo var1) {
         this.mWrapped = (ContentInfo)Preconditions.checkNotNull(var1);
      }

      public ClipData getClip() {
         return this.mWrapped.getClip();
      }

      public Bundle getExtras() {
         return this.mWrapped.getExtras();
      }

      public int getFlags() {
         return this.mWrapped.getFlags();
      }

      public Uri getLinkUri() {
         return this.mWrapped.getLinkUri();
      }

      public int getSource() {
         return this.mWrapped.getSource();
      }

      public ContentInfo getWrapped() {
         return this.mWrapped;
      }

      public String toString() {
         return "ContentInfoCompat{" + this.mWrapped + "}";
      }
   }

   private static final class CompatImpl implements Compat {
      private final ClipData mClip;
      private final Bundle mExtras;
      private final int mFlags;
      private final Uri mLinkUri;
      private final int mSource;

      CompatImpl(BuilderCompatImpl var1) {
         this.mClip = (ClipData)Preconditions.checkNotNull(var1.mClip);
         this.mSource = Preconditions.checkArgumentInRange(var1.mSource, 0, 5, "source");
         this.mFlags = Preconditions.checkFlagsArgument(var1.mFlags, 1);
         this.mLinkUri = var1.mLinkUri;
         this.mExtras = var1.mExtras;
      }

      public ClipData getClip() {
         return this.mClip;
      }

      public Bundle getExtras() {
         return this.mExtras;
      }

      public int getFlags() {
         return this.mFlags;
      }

      public Uri getLinkUri() {
         return this.mLinkUri;
      }

      public int getSource() {
         return this.mSource;
      }

      public ContentInfo getWrapped() {
         return null;
      }

      public String toString() {
         StringBuilder var3 = (new StringBuilder()).append("ContentInfoCompat{clip=").append(this.mClip.getDescription()).append(", source=").append(ContentInfoCompat.sourceToString(this.mSource)).append(", flags=").append(ContentInfoCompat.flagsToString(this.mFlags));
         Uri var1 = this.mLinkUri;
         String var2 = "";
         String var4;
         if (var1 == null) {
            var4 = "";
         } else {
            var4 = ", hasLinkUri(" + this.mLinkUri.toString().length() + ")";
         }

         var3 = var3.append(var4);
         if (this.mExtras == null) {
            var4 = var2;
         } else {
            var4 = ", hasExtras";
         }

         return var3.append(var4).append("}").toString();
      }
   }

   @Retention(RetentionPolicy.SOURCE)
   public @interface Flags {
   }

   @Retention(RetentionPolicy.SOURCE)
   public @interface Source {
   }
}
