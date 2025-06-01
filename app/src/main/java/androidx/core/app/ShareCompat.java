package androidx.core.app;

import android.app.Activity;
import android.content.ClipData;
import android.content.ComponentName;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Parcelable;
import android.os.Build.VERSION;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.ActionProvider;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ShareActionProvider;
import androidx.core.util.Preconditions;
import java.util.ArrayList;

public final class ShareCompat {
   public static final String EXTRA_CALLING_ACTIVITY = "androidx.core.app.EXTRA_CALLING_ACTIVITY";
   public static final String EXTRA_CALLING_ACTIVITY_INTEROP = "android.support.v4.app.EXTRA_CALLING_ACTIVITY";
   public static final String EXTRA_CALLING_PACKAGE = "androidx.core.app.EXTRA_CALLING_PACKAGE";
   public static final String EXTRA_CALLING_PACKAGE_INTEROP = "android.support.v4.app.EXTRA_CALLING_PACKAGE";
   private static final String HISTORY_FILENAME_PREFIX = ".sharecompat_";

   private ShareCompat() {
   }

   @Deprecated
   public static void configureMenuItem(Menu var0, int var1, IntentBuilder var2) {
      MenuItem var3 = var0.findItem(var1);
      if (var3 != null) {
         configureMenuItem(var3, var2);
      } else {
         throw new IllegalArgumentException("Could not find menu item with id " + var1 + " in the supplied menu");
      }
   }

   @Deprecated
   public static void configureMenuItem(MenuItem var0, IntentBuilder var1) {
      ActionProvider var2 = var0.getActionProvider();
      ShareActionProvider var3;
      if (!(var2 instanceof ShareActionProvider)) {
         var3 = new ShareActionProvider(var1.getContext());
      } else {
         var3 = (ShareActionProvider)var2;
      }

      var3.setShareHistoryFileName(".sharecompat_" + var1.getContext().getClass().getName());
      var3.setShareIntent(var1.getIntent());
      var0.setActionProvider(var3);
      if (VERSION.SDK_INT < 16 && !var0.hasSubMenu()) {
         var0.setIntent(var1.createChooserIntent());
      }

   }

   public static ComponentName getCallingActivity(Activity var0) {
      Intent var2 = var0.getIntent();
      ComponentName var1 = var0.getCallingActivity();
      ComponentName var3 = var1;
      if (var1 == null) {
         var3 = getCallingActivity(var2);
      }

      return var3;
   }

   static ComponentName getCallingActivity(Intent var0) {
      ComponentName var2 = (ComponentName)var0.getParcelableExtra("androidx.core.app.EXTRA_CALLING_ACTIVITY");
      ComponentName var1 = var2;
      if (var2 == null) {
         var1 = (ComponentName)var0.getParcelableExtra("android.support.v4.app.EXTRA_CALLING_ACTIVITY");
      }

      return var1;
   }

   public static String getCallingPackage(Activity var0) {
      Intent var2 = var0.getIntent();
      String var1 = var0.getCallingPackage();
      String var3 = var1;
      if (var1 == null) {
         var3 = var1;
         if (var2 != null) {
            var3 = getCallingPackage(var2);
         }
      }

      return var3;
   }

   static String getCallingPackage(Intent var0) {
      String var2 = var0.getStringExtra("androidx.core.app.EXTRA_CALLING_PACKAGE");
      String var1 = var2;
      if (var2 == null) {
         var1 = var0.getStringExtra("android.support.v4.app.EXTRA_CALLING_PACKAGE");
      }

      return var1;
   }

   private static class Api16Impl {
      static void migrateExtraStreamToClipData(Intent var0, ArrayList var1) {
         CharSequence var6 = var0.getCharSequenceExtra("android.intent.extra.TEXT");
         String var5 = var0.getStringExtra("android.intent.extra.HTML_TEXT");
         String var4 = var0.getType();
         ClipData.Item var8 = new ClipData.Item(var6, var5, (Intent)null, (Uri)var1.get(0));
         ClipData var7 = new ClipData((CharSequence)null, new String[]{var4}, var8);
         int var3 = var1.size();

         for(int var2 = 1; var2 < var3; ++var2) {
            var7.addItem(new ClipData.Item((Uri)var1.get(var2)));
         }

         var0.setClipData(var7);
         var0.addFlags(1);
      }

      static void removeClipData(Intent var0) {
         var0.setClipData((ClipData)null);
         var0.setFlags(var0.getFlags() & -2);
      }
   }

   public static class IntentBuilder {
      private ArrayList mBccAddresses;
      private ArrayList mCcAddresses;
      private CharSequence mChooserTitle;
      private final Context mContext;
      private final Intent mIntent;
      private ArrayList mStreams;
      private ArrayList mToAddresses;

      public IntentBuilder(Context var1) {
         this.mContext = (Context)Preconditions.checkNotNull(var1);
         Intent var2 = (new Intent()).setAction("android.intent.action.SEND");
         this.mIntent = var2;
         var2.putExtra("androidx.core.app.EXTRA_CALLING_PACKAGE", var1.getPackageName());
         var2.putExtra("android.support.v4.app.EXTRA_CALLING_PACKAGE", var1.getPackageName());
         var2.addFlags(524288);

         Activity var3;
         while(true) {
            if (!(var1 instanceof ContextWrapper)) {
               var3 = null;
               break;
            }

            if (var1 instanceof Activity) {
               var3 = (Activity)var1;
               break;
            }

            var1 = ((ContextWrapper)var1).getBaseContext();
         }

         if (var3 != null) {
            ComponentName var4 = var3.getComponentName();
            this.mIntent.putExtra("androidx.core.app.EXTRA_CALLING_ACTIVITY", var4);
            this.mIntent.putExtra("android.support.v4.app.EXTRA_CALLING_ACTIVITY", var4);
         }

      }

      private void combineArrayExtra(String var1, ArrayList var2) {
         String[] var5 = this.mIntent.getStringArrayExtra(var1);
         int var3;
         if (var5 != null) {
            var3 = var5.length;
         } else {
            var3 = 0;
         }

         String[] var4 = new String[var2.size() + var3];
         var2.toArray(var4);
         if (var5 != null) {
            System.arraycopy(var5, 0, var4, var2.size(), var3);
         }

         this.mIntent.putExtra(var1, var4);
      }

      private void combineArrayExtra(String var1, String[] var2) {
         Intent var6 = this.getIntent();
         String[] var5 = var6.getStringArrayExtra(var1);
         int var3;
         if (var5 != null) {
            var3 = var5.length;
         } else {
            var3 = 0;
         }

         String[] var4 = new String[var2.length + var3];
         if (var5 != null) {
            System.arraycopy(var5, 0, var4, 0, var3);
         }

         System.arraycopy(var2, 0, var4, var3, var2.length);
         var6.putExtra(var1, var4);
      }

      @Deprecated
      public static IntentBuilder from(Activity var0) {
         return new IntentBuilder(var0);
      }

      public IntentBuilder addEmailBcc(String var1) {
         if (this.mBccAddresses == null) {
            this.mBccAddresses = new ArrayList();
         }

         this.mBccAddresses.add(var1);
         return this;
      }

      public IntentBuilder addEmailBcc(String[] var1) {
         this.combineArrayExtra("android.intent.extra.BCC", var1);
         return this;
      }

      public IntentBuilder addEmailCc(String var1) {
         if (this.mCcAddresses == null) {
            this.mCcAddresses = new ArrayList();
         }

         this.mCcAddresses.add(var1);
         return this;
      }

      public IntentBuilder addEmailCc(String[] var1) {
         this.combineArrayExtra("android.intent.extra.CC", var1);
         return this;
      }

      public IntentBuilder addEmailTo(String var1) {
         if (this.mToAddresses == null) {
            this.mToAddresses = new ArrayList();
         }

         this.mToAddresses.add(var1);
         return this;
      }

      public IntentBuilder addEmailTo(String[] var1) {
         this.combineArrayExtra("android.intent.extra.EMAIL", var1);
         return this;
      }

      public IntentBuilder addStream(Uri var1) {
         if (this.mStreams == null) {
            this.mStreams = new ArrayList();
         }

         this.mStreams.add(var1);
         return this;
      }

      public Intent createChooserIntent() {
         return Intent.createChooser(this.getIntent(), this.mChooserTitle);
      }

      Context getContext() {
         return this.mContext;
      }

      public Intent getIntent() {
         ArrayList var2 = this.mToAddresses;
         if (var2 != null) {
            this.combineArrayExtra("android.intent.extra.EMAIL", var2);
            this.mToAddresses = null;
         }

         var2 = this.mCcAddresses;
         if (var2 != null) {
            this.combineArrayExtra("android.intent.extra.CC", var2);
            this.mCcAddresses = null;
         }

         var2 = this.mBccAddresses;
         if (var2 != null) {
            this.combineArrayExtra("android.intent.extra.BCC", var2);
            this.mBccAddresses = null;
         }

         var2 = this.mStreams;
         boolean var1 = true;
         if (var2 == null || var2.size() <= 1) {
            var1 = false;
         }

         if (!var1) {
            this.mIntent.setAction("android.intent.action.SEND");
            var2 = this.mStreams;
            if (var2 != null && !var2.isEmpty()) {
               this.mIntent.putExtra("android.intent.extra.STREAM", (Parcelable)this.mStreams.get(0));
               if (VERSION.SDK_INT >= 16) {
                  ShareCompat.Api16Impl.migrateExtraStreamToClipData(this.mIntent, this.mStreams);
               }
            } else {
               this.mIntent.removeExtra("android.intent.extra.STREAM");
               if (VERSION.SDK_INT >= 16) {
                  ShareCompat.Api16Impl.removeClipData(this.mIntent);
               }
            }
         } else {
            this.mIntent.setAction("android.intent.action.SEND_MULTIPLE");
            this.mIntent.putParcelableArrayListExtra("android.intent.extra.STREAM", this.mStreams);
            if (VERSION.SDK_INT >= 16) {
               ShareCompat.Api16Impl.migrateExtraStreamToClipData(this.mIntent, this.mStreams);
            }
         }

         return this.mIntent;
      }

      public IntentBuilder setChooserTitle(int var1) {
         return this.setChooserTitle(this.mContext.getText(var1));
      }

      public IntentBuilder setChooserTitle(CharSequence var1) {
         this.mChooserTitle = var1;
         return this;
      }

      public IntentBuilder setEmailBcc(String[] var1) {
         this.mIntent.putExtra("android.intent.extra.BCC", var1);
         return this;
      }

      public IntentBuilder setEmailCc(String[] var1) {
         this.mIntent.putExtra("android.intent.extra.CC", var1);
         return this;
      }

      public IntentBuilder setEmailTo(String[] var1) {
         if (this.mToAddresses != null) {
            this.mToAddresses = null;
         }

         this.mIntent.putExtra("android.intent.extra.EMAIL", var1);
         return this;
      }

      public IntentBuilder setHtmlText(String var1) {
         this.mIntent.putExtra("android.intent.extra.HTML_TEXT", var1);
         if (!this.mIntent.hasExtra("android.intent.extra.TEXT")) {
            this.setText(Html.fromHtml(var1));
         }

         return this;
      }

      public IntentBuilder setStream(Uri var1) {
         this.mStreams = null;
         if (var1 != null) {
            this.addStream(var1);
         }

         return this;
      }

      public IntentBuilder setSubject(String var1) {
         this.mIntent.putExtra("android.intent.extra.SUBJECT", var1);
         return this;
      }

      public IntentBuilder setText(CharSequence var1) {
         this.mIntent.putExtra("android.intent.extra.TEXT", var1);
         return this;
      }

      public IntentBuilder setType(String var1) {
         this.mIntent.setType(var1);
         return this;
      }

      public void startChooser() {
         this.mContext.startActivity(this.createChooserIntent());
      }
   }

   public static class IntentReader {
      private static final String TAG = "IntentReader";
      private final ComponentName mCallingActivity;
      private final String mCallingPackage;
      private final Context mContext;
      private final Intent mIntent;
      private ArrayList mStreams;

      public IntentReader(Activity var1) {
         this((Context)Preconditions.checkNotNull(var1), var1.getIntent());
      }

      public IntentReader(Context var1, Intent var2) {
         this.mContext = (Context)Preconditions.checkNotNull(var1);
         this.mIntent = (Intent)Preconditions.checkNotNull(var2);
         this.mCallingPackage = ShareCompat.getCallingPackage(var2);
         this.mCallingActivity = ShareCompat.getCallingActivity(var2);
      }

      @Deprecated
      public static IntentReader from(Activity var0) {
         return new IntentReader(var0);
      }

      private static void withinStyle(StringBuilder var0, CharSequence var1, int var2, int var3) {
         for(; var2 < var3; ++var2) {
            char var4 = var1.charAt(var2);
            if (var4 == '<') {
               var0.append("&lt;");
            } else if (var4 == '>') {
               var0.append("&gt;");
            } else if (var4 == '&') {
               var0.append("&amp;");
            } else if (var4 <= '~' && var4 >= ' ') {
               if (var4 != ' ') {
                  var0.append(var4);
               } else {
                  while(true) {
                     int var5 = var2 + 1;
                     if (var5 >= var3 || var1.charAt(var5) != ' ') {
                        var0.append(' ');
                        break;
                     }

                     var0.append("&nbsp;");
                     var2 = var5;
                  }
               }
            } else {
               var0.append("&#").append(var4).append(";");
            }
         }

      }

      public ComponentName getCallingActivity() {
         return this.mCallingActivity;
      }

      public Drawable getCallingActivityIcon() {
         if (this.mCallingActivity == null) {
            return null;
         } else {
            PackageManager var1 = this.mContext.getPackageManager();

            try {
               Drawable var3 = var1.getActivityIcon(this.mCallingActivity);
               return var3;
            } catch (PackageManager.NameNotFoundException var2) {
               Log.e("IntentReader", "Could not retrieve icon for calling activity", var2);
               return null;
            }
         }
      }

      public Drawable getCallingApplicationIcon() {
         if (this.mCallingPackage == null) {
            return null;
         } else {
            PackageManager var1 = this.mContext.getPackageManager();

            try {
               Drawable var3 = var1.getApplicationIcon(this.mCallingPackage);
               return var3;
            } catch (PackageManager.NameNotFoundException var2) {
               Log.e("IntentReader", "Could not retrieve icon for calling application", var2);
               return null;
            }
         }
      }

      public CharSequence getCallingApplicationLabel() {
         if (this.mCallingPackage == null) {
            return null;
         } else {
            PackageManager var1 = this.mContext.getPackageManager();

            try {
               CharSequence var3 = var1.getApplicationLabel(var1.getApplicationInfo(this.mCallingPackage, 0));
               return var3;
            } catch (PackageManager.NameNotFoundException var2) {
               Log.e("IntentReader", "Could not retrieve label for calling application", var2);
               return null;
            }
         }
      }

      public String getCallingPackage() {
         return this.mCallingPackage;
      }

      public String[] getEmailBcc() {
         return this.mIntent.getStringArrayExtra("android.intent.extra.BCC");
      }

      public String[] getEmailCc() {
         return this.mIntent.getStringArrayExtra("android.intent.extra.CC");
      }

      public String[] getEmailTo() {
         return this.mIntent.getStringArrayExtra("android.intent.extra.EMAIL");
      }

      public String getHtmlText() {
         String var2 = this.mIntent.getStringExtra("android.intent.extra.HTML_TEXT");
         String var1 = var2;
         if (var2 == null) {
            CharSequence var3 = this.getText();
            if (var3 instanceof Spanned) {
               var1 = Html.toHtml((Spanned)var3);
            } else {
               var1 = var2;
               if (var3 != null) {
                  if (VERSION.SDK_INT >= 16) {
                     var1 = Html.escapeHtml(var3);
                  } else {
                     StringBuilder var4 = new StringBuilder();
                     withinStyle(var4, var3, 0, var3.length());
                     var1 = var4.toString();
                  }
               }
            }
         }

         return var1;
      }

      public Uri getStream() {
         return (Uri)this.mIntent.getParcelableExtra("android.intent.extra.STREAM");
      }

      public Uri getStream(int var1) {
         if (this.mStreams == null && this.isMultipleShare()) {
            this.mStreams = this.mIntent.getParcelableArrayListExtra("android.intent.extra.STREAM");
         }

         ArrayList var2 = this.mStreams;
         if (var2 != null) {
            return (Uri)var2.get(var1);
         } else if (var1 == 0) {
            return (Uri)this.mIntent.getParcelableExtra("android.intent.extra.STREAM");
         } else {
            throw new IndexOutOfBoundsException("Stream items available: " + this.getStreamCount() + " index requested: " + var1);
         }
      }

      public int getStreamCount() {
         if (this.mStreams == null && this.isMultipleShare()) {
            this.mStreams = this.mIntent.getParcelableArrayListExtra("android.intent.extra.STREAM");
         }

         ArrayList var1 = this.mStreams;
         return var1 != null ? var1.size() : this.mIntent.hasExtra("android.intent.extra.STREAM");
      }

      public String getSubject() {
         return this.mIntent.getStringExtra("android.intent.extra.SUBJECT");
      }

      public CharSequence getText() {
         return this.mIntent.getCharSequenceExtra("android.intent.extra.TEXT");
      }

      public String getType() {
         return this.mIntent.getType();
      }

      public boolean isMultipleShare() {
         return "android.intent.action.SEND_MULTIPLE".equals(this.mIntent.getAction());
      }

      public boolean isShareIntent() {
         String var2 = this.mIntent.getAction();
         boolean var1;
         if (!"android.intent.action.SEND".equals(var2) && !"android.intent.action.SEND_MULTIPLE".equals(var2)) {
            var1 = false;
         } else {
            var1 = true;
         }

         return var1;
      }

      public boolean isSingleShare() {
         return "android.intent.action.SEND".equals(this.mIntent.getAction());
      }
   }
}
