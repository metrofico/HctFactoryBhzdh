package androidx.core.app;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Build.VERSION;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public final class RemoteInput {
   public static final int EDIT_CHOICES_BEFORE_SENDING_AUTO = 0;
   public static final int EDIT_CHOICES_BEFORE_SENDING_DISABLED = 1;
   public static final int EDIT_CHOICES_BEFORE_SENDING_ENABLED = 2;
   private static final String EXTRA_DATA_TYPE_RESULTS_DATA = "android.remoteinput.dataTypeResultsData";
   public static final String EXTRA_RESULTS_DATA = "android.remoteinput.resultsData";
   private static final String EXTRA_RESULTS_SOURCE = "android.remoteinput.resultsSource";
   public static final String RESULTS_CLIP_LABEL = "android.remoteinput.results";
   public static final int SOURCE_CHOICE = 1;
   public static final int SOURCE_FREE_FORM_INPUT = 0;
   private static final String TAG = "RemoteInput";
   private final boolean mAllowFreeFormTextInput;
   private final Set mAllowedDataTypes;
   private final CharSequence[] mChoices;
   private final int mEditChoicesBeforeSending;
   private final Bundle mExtras;
   private final CharSequence mLabel;
   private final String mResultKey;

   RemoteInput(String var1, CharSequence var2, CharSequence[] var3, boolean var4, int var5, Bundle var6, Set var7) {
      this.mResultKey = var1;
      this.mLabel = var2;
      this.mChoices = var3;
      this.mAllowFreeFormTextInput = var4;
      this.mEditChoicesBeforeSending = var5;
      this.mExtras = var6;
      this.mAllowedDataTypes = var7;
      if (this.getEditChoicesBeforeSending() == 2 && !this.getAllowFreeFormInput()) {
         throw new IllegalArgumentException("setEditChoicesBeforeSending requires setAllowFreeFormInput");
      }
   }

   public static void addDataResultToIntent(RemoteInput var0, Intent var1, Map var2) {
      if (VERSION.SDK_INT >= 26) {
         android.app.RemoteInput.addDataResultToIntent(fromCompat(var0), var1, var2);
      } else if (VERSION.SDK_INT >= 16) {
         Intent var4 = getClipDataIntentFromIntent(var1);
         Intent var3 = var4;
         if (var4 == null) {
            var3 = new Intent();
         }

         Iterator var5 = var2.entrySet().iterator();

         while(var5.hasNext()) {
            Map.Entry var8 = (Map.Entry)var5.next();
            String var6 = (String)var8.getKey();
            Uri var7 = (Uri)var8.getValue();
            if (var6 != null) {
               Bundle var10 = var3.getBundleExtra(getExtraResultsKeyForData(var6));
               Bundle var9 = var10;
               if (var10 == null) {
                  var9 = new Bundle();
               }

               var9.putString(var0.getResultKey(), var7.toString());
               var3.putExtra(getExtraResultsKeyForData(var6), var9);
            }
         }

         var1.setClipData(ClipData.newIntent("android.remoteinput.results", var3));
      }

   }

   public static void addResultsToIntent(RemoteInput[] var0, Intent var1, Bundle var2) {
      if (VERSION.SDK_INT >= 26) {
         android.app.RemoteInput.addResultsToIntent(fromCompat(var0), var1, var2);
      } else {
         int var4 = VERSION.SDK_INT;
         int var3 = 0;
         if (var4 >= 20) {
            Bundle var6 = getResultsFromIntent(var1);
            var4 = getResultsSource(var1);
            if (var6 != null) {
               var6.putAll(var2);
               var2 = var6;
            }

            int var5 = var0.length;

            for(var3 = 0; var3 < var5; ++var3) {
               RemoteInput var7 = var0[var3];
               Map var10 = getDataResultsFromIntent(var1, var7.getResultKey());
               android.app.RemoteInput.addResultsToIntent(fromCompat(new RemoteInput[]{var7}), var1, var2);
               if (var10 != null) {
                  addDataResultToIntent(var7, var1, var10);
               }
            }

            setResultsSource(var1, var4);
         } else if (VERSION.SDK_INT >= 16) {
            Intent var12 = getClipDataIntentFromIntent(var1);
            Intent var11 = var12;
            if (var12 == null) {
               var11 = new Intent();
            }

            Bundle var8 = var11.getBundleExtra("android.remoteinput.resultsData");
            Bundle var13 = var8;
            if (var8 == null) {
               var13 = new Bundle();
            }

            for(var4 = var0.length; var3 < var4; ++var3) {
               RemoteInput var14 = var0[var3];
               Object var9 = var2.get(var14.getResultKey());
               if (var9 instanceof CharSequence) {
                  var13.putCharSequence(var14.getResultKey(), (CharSequence)var9);
               }
            }

            var11.putExtra("android.remoteinput.resultsData", var13);
            var1.setClipData(ClipData.newIntent("android.remoteinput.results", var11));
         }
      }

   }

   static android.app.RemoteInput fromCompat(RemoteInput var0) {
      android.app.RemoteInput.Builder var1 = (new android.app.RemoteInput.Builder(var0.getResultKey())).setLabel(var0.getLabel()).setChoices(var0.getChoices()).setAllowFreeFormInput(var0.getAllowFreeFormInput()).addExtras(var0.getExtras());
      if (VERSION.SDK_INT >= 26) {
         Set var2 = var0.getAllowedDataTypes();
         if (var2 != null) {
            Iterator var3 = var2.iterator();

            while(var3.hasNext()) {
               var1.setAllowDataType((String)var3.next(), true);
            }
         }
      }

      if (VERSION.SDK_INT >= 29) {
         var1.setEditChoicesBeforeSending(var0.getEditChoicesBeforeSending());
      }

      return var1.build();
   }

   static android.app.RemoteInput[] fromCompat(RemoteInput[] var0) {
      if (var0 == null) {
         return null;
      } else {
         android.app.RemoteInput[] var2 = new android.app.RemoteInput[var0.length];

         for(int var1 = 0; var1 < var0.length; ++var1) {
            var2[var1] = fromCompat(var0[var1]);
         }

         return var2;
      }
   }

   static RemoteInput fromPlatform(android.app.RemoteInput var0) {
      Builder var1 = (new Builder(var0.getResultKey())).setLabel(var0.getLabel()).setChoices(var0.getChoices()).setAllowFreeFormInput(var0.getAllowFreeFormInput()).addExtras(var0.getExtras());
      if (VERSION.SDK_INT >= 26) {
         Set var2 = var0.getAllowedDataTypes();
         if (var2 != null) {
            Iterator var3 = var2.iterator();

            while(var3.hasNext()) {
               var1.setAllowDataType((String)var3.next(), true);
            }
         }
      }

      if (VERSION.SDK_INT >= 29) {
         var1.setEditChoicesBeforeSending(var0.getEditChoicesBeforeSending());
      }

      return var1.build();
   }

   private static Intent getClipDataIntentFromIntent(Intent var0) {
      ClipData var2 = var0.getClipData();
      if (var2 == null) {
         return null;
      } else {
         ClipDescription var1 = var2.getDescription();
         if (!var1.hasMimeType("text/vnd.android.intent")) {
            return null;
         } else {
            return !var1.getLabel().toString().contentEquals("android.remoteinput.results") ? null : var2.getItemAt(0).getIntent();
         }
      }
   }

   public static Map getDataResultsFromIntent(Intent var0, String var1) {
      if (VERSION.SDK_INT >= 26) {
         return android.app.RemoteInput.getDataResultsFromIntent(var0, var1);
      } else {
         int var2 = VERSION.SDK_INT;
         Object var4 = null;
         HashMap var3 = (HashMap)var4;
         if (var2 >= 16) {
            Intent var6 = getClipDataIntentFromIntent(var0);
            if (var6 == null) {
               return null;
            }

            var3 = new HashMap();
            Iterator var8 = var6.getExtras().keySet().iterator();

            while(var8.hasNext()) {
               String var7 = (String)var8.next();
               if (var7.startsWith("android.remoteinput.dataTypeResultsData")) {
                  String var5 = var7.substring(39);
                  if (!var5.isEmpty()) {
                     var7 = var6.getBundleExtra(var7).getString(var1);
                     if (var7 != null && !var7.isEmpty()) {
                        var3.put(var5, Uri.parse(var7));
                     }
                  }
               }
            }

            if (var3.isEmpty()) {
               var3 = (HashMap)var4;
            }
         }

         return var3;
      }
   }

   private static String getExtraResultsKeyForData(String var0) {
      return "android.remoteinput.dataTypeResultsData" + var0;
   }

   public static Bundle getResultsFromIntent(Intent var0) {
      if (VERSION.SDK_INT >= 20) {
         return android.app.RemoteInput.getResultsFromIntent(var0);
      } else if (VERSION.SDK_INT >= 16) {
         var0 = getClipDataIntentFromIntent(var0);
         return var0 == null ? null : (Bundle)var0.getExtras().getParcelable("android.remoteinput.resultsData");
      } else {
         return null;
      }
   }

   public static int getResultsSource(Intent var0) {
      if (VERSION.SDK_INT >= 28) {
         return android.app.RemoteInput.getResultsSource(var0);
      } else if (VERSION.SDK_INT >= 16) {
         var0 = getClipDataIntentFromIntent(var0);
         return var0 == null ? 0 : var0.getExtras().getInt("android.remoteinput.resultsSource", 0);
      } else {
         return 0;
      }
   }

   public static void setResultsSource(Intent var0, int var1) {
      if (VERSION.SDK_INT >= 28) {
         android.app.RemoteInput.setResultsSource(var0, var1);
      } else if (VERSION.SDK_INT >= 16) {
         Intent var3 = getClipDataIntentFromIntent(var0);
         Intent var2 = var3;
         if (var3 == null) {
            var2 = new Intent();
         }

         var2.putExtra("android.remoteinput.resultsSource", var1);
         var0.setClipData(ClipData.newIntent("android.remoteinput.results", var2));
      }

   }

   public boolean getAllowFreeFormInput() {
      return this.mAllowFreeFormTextInput;
   }

   public Set getAllowedDataTypes() {
      return this.mAllowedDataTypes;
   }

   public CharSequence[] getChoices() {
      return this.mChoices;
   }

   public int getEditChoicesBeforeSending() {
      return this.mEditChoicesBeforeSending;
   }

   public Bundle getExtras() {
      return this.mExtras;
   }

   public CharSequence getLabel() {
      return this.mLabel;
   }

   public String getResultKey() {
      return this.mResultKey;
   }

   public boolean isDataOnly() {
      boolean var1;
      if (!this.getAllowFreeFormInput() && (this.getChoices() == null || this.getChoices().length == 0) && this.getAllowedDataTypes() != null && !this.getAllowedDataTypes().isEmpty()) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public static final class Builder {
      private boolean mAllowFreeFormTextInput = true;
      private final Set mAllowedDataTypes = new HashSet();
      private CharSequence[] mChoices;
      private int mEditChoicesBeforeSending = 0;
      private final Bundle mExtras = new Bundle();
      private CharSequence mLabel;
      private final String mResultKey;

      public Builder(String var1) {
         if (var1 != null) {
            this.mResultKey = var1;
         } else {
            throw new IllegalArgumentException("Result key can't be null");
         }
      }

      public Builder addExtras(Bundle var1) {
         if (var1 != null) {
            this.mExtras.putAll(var1);
         }

         return this;
      }

      public RemoteInput build() {
         return new RemoteInput(this.mResultKey, this.mLabel, this.mChoices, this.mAllowFreeFormTextInput, this.mEditChoicesBeforeSending, this.mExtras, this.mAllowedDataTypes);
      }

      public Bundle getExtras() {
         return this.mExtras;
      }

      public Builder setAllowDataType(String var1, boolean var2) {
         if (var2) {
            this.mAllowedDataTypes.add(var1);
         } else {
            this.mAllowedDataTypes.remove(var1);
         }

         return this;
      }

      public Builder setAllowFreeFormInput(boolean var1) {
         this.mAllowFreeFormTextInput = var1;
         return this;
      }

      public Builder setChoices(CharSequence[] var1) {
         this.mChoices = var1;
         return this;
      }

      public Builder setEditChoicesBeforeSending(int var1) {
         this.mEditChoicesBeforeSending = var1;
         return this;
      }

      public Builder setLabel(CharSequence var1) {
         this.mLabel = var1;
         return this;
      }
   }

   @Retention(RetentionPolicy.SOURCE)
   public @interface EditChoicesBeforeSending {
   }

   @Retention(RetentionPolicy.SOURCE)
   public @interface Source {
   }
}
