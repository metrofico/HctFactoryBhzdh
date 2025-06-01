package androidx.core.widget;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.ColorStateList;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.icu.text.DecimalFormatSymbols;
import android.os.Build.VERSION;
import android.text.Editable;
import android.text.TextDirectionHeuristic;
import android.text.TextDirectionHeuristics;
import android.text.TextPaint;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import androidx.core.text.PrecomputedTextCompat;
import androidx.core.util.Preconditions;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class TextViewCompat {
   public static final int AUTO_SIZE_TEXT_TYPE_NONE = 0;
   public static final int AUTO_SIZE_TEXT_TYPE_UNIFORM = 1;
   private static final int LINES = 1;
   private static final String LOG_TAG = "TextViewCompat";
   private static Field sMaxModeField;
   private static boolean sMaxModeFieldFetched;
   private static Field sMaximumField;
   private static boolean sMaximumFieldFetched;
   private static Field sMinModeField;
   private static boolean sMinModeFieldFetched;
   private static Field sMinimumField;
   private static boolean sMinimumFieldFetched;

   private TextViewCompat() {
   }

   public static int getAutoSizeMaxTextSize(TextView var0) {
      if (VERSION.SDK_INT >= 27) {
         return var0.getAutoSizeMaxTextSize();
      } else {
         return var0 instanceof AutoSizeableTextView ? ((AutoSizeableTextView)var0).getAutoSizeMaxTextSize() : -1;
      }
   }

   public static int getAutoSizeMinTextSize(TextView var0) {
      if (VERSION.SDK_INT >= 27) {
         return var0.getAutoSizeMinTextSize();
      } else {
         return var0 instanceof AutoSizeableTextView ? ((AutoSizeableTextView)var0).getAutoSizeMinTextSize() : -1;
      }
   }

   public static int getAutoSizeStepGranularity(TextView var0) {
      if (VERSION.SDK_INT >= 27) {
         return var0.getAutoSizeStepGranularity();
      } else {
         return var0 instanceof AutoSizeableTextView ? ((AutoSizeableTextView)var0).getAutoSizeStepGranularity() : -1;
      }
   }

   public static int[] getAutoSizeTextAvailableSizes(TextView var0) {
      if (VERSION.SDK_INT >= 27) {
         return var0.getAutoSizeTextAvailableSizes();
      } else {
         return var0 instanceof AutoSizeableTextView ? ((AutoSizeableTextView)var0).getAutoSizeTextAvailableSizes() : new int[0];
      }
   }

   public static int getAutoSizeTextType(TextView var0) {
      if (VERSION.SDK_INT >= 27) {
         return var0.getAutoSizeTextType();
      } else {
         return var0 instanceof AutoSizeableTextView ? ((AutoSizeableTextView)var0).getAutoSizeTextType() : 0;
      }
   }

   public static ColorStateList getCompoundDrawableTintList(TextView var0) {
      Preconditions.checkNotNull(var0);
      if (VERSION.SDK_INT >= 24) {
         return var0.getCompoundDrawableTintList();
      } else {
         return var0 instanceof TintableCompoundDrawablesView ? ((TintableCompoundDrawablesView)var0).getSupportCompoundDrawablesTintList() : null;
      }
   }

   public static PorterDuff.Mode getCompoundDrawableTintMode(TextView var0) {
      Preconditions.checkNotNull(var0);
      if (VERSION.SDK_INT >= 24) {
         return var0.getCompoundDrawableTintMode();
      } else {
         return var0 instanceof TintableCompoundDrawablesView ? ((TintableCompoundDrawablesView)var0).getSupportCompoundDrawablesTintMode() : null;
      }
   }

   public static Drawable[] getCompoundDrawablesRelative(TextView var0) {
      if (VERSION.SDK_INT >= 18) {
         return var0.getCompoundDrawablesRelative();
      } else if (VERSION.SDK_INT >= 17) {
         int var2 = var0.getLayoutDirection();
         boolean var1 = true;
         if (var2 != 1) {
            var1 = false;
         }

         Drawable[] var3 = var0.getCompoundDrawables();
         if (var1) {
            Drawable var5 = var3[2];
            Drawable var4 = var3[0];
            var3[0] = var5;
            var3[2] = var4;
         }

         return var3;
      } else {
         return var0.getCompoundDrawables();
      }
   }

   public static int getFirstBaselineToTopHeight(TextView var0) {
      return var0.getPaddingTop() - var0.getPaint().getFontMetricsInt().top;
   }

   public static int getLastBaselineToBottomHeight(TextView var0) {
      return var0.getPaddingBottom() + var0.getPaint().getFontMetricsInt().bottom;
   }

   public static int getMaxLines(TextView var0) {
      if (VERSION.SDK_INT >= 16) {
         return var0.getMaxLines();
      } else {
         if (!sMaxModeFieldFetched) {
            sMaxModeField = retrieveField("mMaxMode");
            sMaxModeFieldFetched = true;
         }

         Field var1 = sMaxModeField;
         if (var1 != null && retrieveIntFromField(var1, var0) == 1) {
            if (!sMaximumFieldFetched) {
               sMaximumField = retrieveField("mMaximum");
               sMaximumFieldFetched = true;
            }

            var1 = sMaximumField;
            if (var1 != null) {
               return retrieveIntFromField(var1, var0);
            }
         }

         return -1;
      }
   }

   public static int getMinLines(TextView var0) {
      if (VERSION.SDK_INT >= 16) {
         return var0.getMinLines();
      } else {
         if (!sMinModeFieldFetched) {
            sMinModeField = retrieveField("mMinMode");
            sMinModeFieldFetched = true;
         }

         Field var1 = sMinModeField;
         if (var1 != null && retrieveIntFromField(var1, var0) == 1) {
            if (!sMinimumFieldFetched) {
               sMinimumField = retrieveField("mMinimum");
               sMinimumFieldFetched = true;
            }

            var1 = sMinimumField;
            if (var1 != null) {
               return retrieveIntFromField(var1, var0);
            }
         }

         return -1;
      }
   }

   private static int getTextDirection(TextDirectionHeuristic var0) {
      if (var0 == TextDirectionHeuristics.FIRSTSTRONG_RTL) {
         return 1;
      } else if (var0 == TextDirectionHeuristics.FIRSTSTRONG_LTR) {
         return 1;
      } else if (var0 == TextDirectionHeuristics.ANYRTL_LTR) {
         return 2;
      } else if (var0 == TextDirectionHeuristics.LTR) {
         return 3;
      } else if (var0 == TextDirectionHeuristics.RTL) {
         return 4;
      } else if (var0 == TextDirectionHeuristics.LOCALE) {
         return 5;
      } else if (var0 == TextDirectionHeuristics.FIRSTSTRONG_LTR) {
         return 6;
      } else {
         return var0 == TextDirectionHeuristics.FIRSTSTRONG_RTL ? 7 : 1;
      }
   }

   private static TextDirectionHeuristic getTextDirectionHeuristic(TextView var0) {
      if (var0.getTransformationMethod() instanceof PasswordTransformationMethod) {
         return TextDirectionHeuristics.LTR;
      } else {
         int var2 = VERSION.SDK_INT;
         boolean var1 = false;
         if (var2 >= 28 && (var0.getInputType() & 15) == 3) {
            byte var4 = Character.getDirectionality(DecimalFormatSymbols.getInstance(var0.getTextLocale()).getDigitStrings()[0].codePointAt(0));
            return var4 != 1 && var4 != 2 ? TextDirectionHeuristics.LTR : TextDirectionHeuristics.RTL;
         } else {
            if (var0.getLayoutDirection() == 1) {
               var1 = true;
            }

            switch (var0.getTextDirection()) {
               case 2:
                  return TextDirectionHeuristics.ANYRTL_LTR;
               case 3:
                  return TextDirectionHeuristics.LTR;
               case 4:
                  return TextDirectionHeuristics.RTL;
               case 5:
                  return TextDirectionHeuristics.LOCALE;
               case 6:
                  return TextDirectionHeuristics.FIRSTSTRONG_LTR;
               case 7:
                  return TextDirectionHeuristics.FIRSTSTRONG_RTL;
               default:
                  TextDirectionHeuristic var3;
                  if (var1) {
                     var3 = TextDirectionHeuristics.FIRSTSTRONG_RTL;
                  } else {
                     var3 = TextDirectionHeuristics.FIRSTSTRONG_LTR;
                  }

                  return var3;
            }
         }
      }
   }

   public static PrecomputedTextCompat.Params getTextMetricsParams(TextView var0) {
      if (VERSION.SDK_INT >= 28) {
         return new PrecomputedTextCompat.Params(var0.getTextMetricsParams());
      } else {
         PrecomputedTextCompat.Params.Builder var1 = new PrecomputedTextCompat.Params.Builder(new TextPaint(var0.getPaint()));
         if (VERSION.SDK_INT >= 23) {
            var1.setBreakStrategy(var0.getBreakStrategy());
            var1.setHyphenationFrequency(var0.getHyphenationFrequency());
         }

         if (VERSION.SDK_INT >= 18) {
            var1.setTextDirection(getTextDirectionHeuristic(var0));
         }

         return var1.build();
      }
   }

   private static Field retrieveField(String var0) {
      Field var1 = null;

      Field var2;
      label34: {
         label29: {
            boolean var10001;
            try {
               var2 = TextView.class.getDeclaredField(var0);
            } catch (NoSuchFieldException var4) {
               var10001 = false;
               break label29;
            }

            var1 = var2;

            try {
               var2.setAccessible(true);
               break label34;
            } catch (NoSuchFieldException var3) {
               var10001 = false;
            }
         }

         Log.e("TextViewCompat", "Could not retrieve " + var0 + " field.");
         return var1;
      }

      var1 = var2;
      return var1;
   }

   private static int retrieveIntFromField(Field var0, TextView var1) {
      try {
         int var2 = var0.getInt(var1);
         return var2;
      } catch (IllegalAccessException var3) {
         Log.d("TextViewCompat", "Could not retrieve value of " + var0.getName() + " field.");
         return -1;
      }
   }

   public static void setAutoSizeTextTypeUniformWithConfiguration(TextView var0, int var1, int var2, int var3, int var4) throws IllegalArgumentException {
      if (VERSION.SDK_INT >= 27) {
         var0.setAutoSizeTextTypeUniformWithConfiguration(var1, var2, var3, var4);
      } else if (var0 instanceof AutoSizeableTextView) {
         ((AutoSizeableTextView)var0).setAutoSizeTextTypeUniformWithConfiguration(var1, var2, var3, var4);
      }

   }

   public static void setAutoSizeTextTypeUniformWithPresetSizes(TextView var0, int[] var1, int var2) throws IllegalArgumentException {
      if (VERSION.SDK_INT >= 27) {
         var0.setAutoSizeTextTypeUniformWithPresetSizes(var1, var2);
      } else if (var0 instanceof AutoSizeableTextView) {
         ((AutoSizeableTextView)var0).setAutoSizeTextTypeUniformWithPresetSizes(var1, var2);
      }

   }

   public static void setAutoSizeTextTypeWithDefaults(TextView var0, int var1) {
      if (VERSION.SDK_INT >= 27) {
         var0.setAutoSizeTextTypeWithDefaults(var1);
      } else if (var0 instanceof AutoSizeableTextView) {
         ((AutoSizeableTextView)var0).setAutoSizeTextTypeWithDefaults(var1);
      }

   }

   public static void setCompoundDrawableTintList(TextView var0, ColorStateList var1) {
      Preconditions.checkNotNull(var0);
      if (VERSION.SDK_INT >= 24) {
         var0.setCompoundDrawableTintList(var1);
      } else if (var0 instanceof TintableCompoundDrawablesView) {
         ((TintableCompoundDrawablesView)var0).setSupportCompoundDrawablesTintList(var1);
      }

   }

   public static void setCompoundDrawableTintMode(TextView var0, PorterDuff.Mode var1) {
      Preconditions.checkNotNull(var0);
      if (VERSION.SDK_INT >= 24) {
         var0.setCompoundDrawableTintMode(var1);
      } else if (var0 instanceof TintableCompoundDrawablesView) {
         ((TintableCompoundDrawablesView)var0).setSupportCompoundDrawablesTintMode(var1);
      }

   }

   public static void setCompoundDrawablesRelative(TextView var0, Drawable var1, Drawable var2, Drawable var3, Drawable var4) {
      if (VERSION.SDK_INT >= 18) {
         var0.setCompoundDrawablesRelative(var1, var2, var3, var4);
      } else if (VERSION.SDK_INT >= 17) {
         int var6 = var0.getLayoutDirection();
         boolean var5 = true;
         if (var6 != 1) {
            var5 = false;
         }

         Drawable var7;
         if (var5) {
            var7 = var3;
         } else {
            var7 = var1;
         }

         if (!var5) {
            var1 = var3;
         }

         var0.setCompoundDrawables(var7, var2, var1, var4);
      } else {
         var0.setCompoundDrawables(var1, var2, var3, var4);
      }

   }

   public static void setCompoundDrawablesRelativeWithIntrinsicBounds(TextView var0, int var1, int var2, int var3, int var4) {
      if (VERSION.SDK_INT >= 18) {
         var0.setCompoundDrawablesRelativeWithIntrinsicBounds(var1, var2, var3, var4);
      } else if (VERSION.SDK_INT >= 17) {
         int var6 = var0.getLayoutDirection();
         boolean var5 = true;
         if (var6 != 1) {
            var5 = false;
         }

         if (var5) {
            var6 = var3;
         } else {
            var6 = var1;
         }

         if (!var5) {
            var1 = var3;
         }

         var0.setCompoundDrawablesWithIntrinsicBounds(var6, var2, var1, var4);
      } else {
         var0.setCompoundDrawablesWithIntrinsicBounds(var1, var2, var3, var4);
      }

   }

   public static void setCompoundDrawablesRelativeWithIntrinsicBounds(TextView var0, Drawable var1, Drawable var2, Drawable var3, Drawable var4) {
      if (VERSION.SDK_INT >= 18) {
         var0.setCompoundDrawablesRelativeWithIntrinsicBounds(var1, var2, var3, var4);
      } else if (VERSION.SDK_INT >= 17) {
         int var6 = var0.getLayoutDirection();
         boolean var5 = true;
         if (var6 != 1) {
            var5 = false;
         }

         Drawable var7;
         if (var5) {
            var7 = var3;
         } else {
            var7 = var1;
         }

         if (!var5) {
            var1 = var3;
         }

         var0.setCompoundDrawablesWithIntrinsicBounds(var7, var2, var1, var4);
      } else {
         var0.setCompoundDrawablesWithIntrinsicBounds(var1, var2, var3, var4);
      }

   }

   public static void setCustomSelectionActionModeCallback(TextView var0, ActionMode.Callback var1) {
      var0.setCustomSelectionActionModeCallback(wrapCustomSelectionActionModeCallback(var0, var1));
   }

   public static void setFirstBaselineToTopHeight(TextView var0, int var1) {
      Preconditions.checkArgumentNonnegative(var1);
      if (VERSION.SDK_INT >= 28) {
         var0.setFirstBaselineToTopHeight(var1);
      } else {
         Paint.FontMetricsInt var3 = var0.getPaint().getFontMetricsInt();
         int var2;
         if (VERSION.SDK_INT >= 16 && !var0.getIncludeFontPadding()) {
            var2 = var3.ascent;
         } else {
            var2 = var3.top;
         }

         if (var1 > Math.abs(var2)) {
            var0.setPadding(var0.getPaddingLeft(), var1 + var2, var0.getPaddingRight(), var0.getPaddingBottom());
         }

      }
   }

   public static void setLastBaselineToBottomHeight(TextView var0, int var1) {
      Preconditions.checkArgumentNonnegative(var1);
      Paint.FontMetricsInt var3 = var0.getPaint().getFontMetricsInt();
      int var2;
      if (VERSION.SDK_INT >= 16 && !var0.getIncludeFontPadding()) {
         var2 = var3.descent;
      } else {
         var2 = var3.bottom;
      }

      if (var1 > Math.abs(var2)) {
         var0.setPadding(var0.getPaddingLeft(), var0.getPaddingTop(), var0.getPaddingRight(), var1 - var2);
      }

   }

   public static void setLineHeight(TextView var0, int var1) {
      Preconditions.checkArgumentNonnegative(var1);
      int var2 = var0.getPaint().getFontMetricsInt((Paint.FontMetricsInt)null);
      if (var1 != var2) {
         var0.setLineSpacing((float)(var1 - var2), 1.0F);
      }

   }

   public static void setPrecomputedText(TextView var0, PrecomputedTextCompat var1) {
      if (VERSION.SDK_INT >= 29) {
         var0.setText(var1.getPrecomputedText());
      } else {
         if (!getTextMetricsParams(var0).equalsWithoutTextDirection(var1.getParams())) {
            throw new IllegalArgumentException("Given text can not be applied to TextView.");
         }

         var0.setText(var1);
      }

   }

   public static void setTextAppearance(TextView var0, int var1) {
      if (VERSION.SDK_INT >= 23) {
         var0.setTextAppearance(var1);
      } else {
         var0.setTextAppearance(var0.getContext(), var1);
      }

   }

   public static void setTextMetricsParams(TextView var0, PrecomputedTextCompat.Params var1) {
      if (VERSION.SDK_INT >= 18) {
         var0.setTextDirection(getTextDirection(var1.getTextDirection()));
      }

      if (VERSION.SDK_INT < 23) {
         float var2 = var1.getTextPaint().getTextScaleX();
         var0.getPaint().set(var1.getTextPaint());
         if (var2 == var0.getTextScaleX()) {
            var0.setTextScaleX(var2 / 2.0F + 1.0F);
         }

         var0.setTextScaleX(var2);
      } else {
         var0.getPaint().set(var1.getTextPaint());
         var0.setBreakStrategy(var1.getBreakStrategy());
         var0.setHyphenationFrequency(var1.getHyphenationFrequency());
      }

   }

   public static ActionMode.Callback unwrapCustomSelectionActionModeCallback(ActionMode.Callback var0) {
      ActionMode.Callback var1 = var0;
      if (var0 instanceof OreoCallback) {
         var1 = var0;
         if (VERSION.SDK_INT >= 26) {
            var1 = ((OreoCallback)var0).getWrappedCallback();
         }
      }

      return var1;
   }

   public static ActionMode.Callback wrapCustomSelectionActionModeCallback(TextView var0, ActionMode.Callback var1) {
      return (ActionMode.Callback)(VERSION.SDK_INT >= 26 && VERSION.SDK_INT <= 27 && !(var1 instanceof OreoCallback) && var1 != null ? new OreoCallback(var1, var0) : var1);
   }

   @Retention(RetentionPolicy.SOURCE)
   public @interface AutoSizeTextType {
   }

   private static class OreoCallback implements ActionMode.Callback {
      private static final int MENU_ITEM_ORDER_PROCESS_TEXT_INTENT_ACTIONS_START = 100;
      private final ActionMode.Callback mCallback;
      private boolean mCanUseMenuBuilderReferences;
      private boolean mInitializedMenuBuilderReferences;
      private Class mMenuBuilderClass;
      private Method mMenuBuilderRemoveItemAtMethod;
      private final TextView mTextView;

      OreoCallback(ActionMode.Callback var1, TextView var2) {
         this.mCallback = var1;
         this.mTextView = var2;
         this.mInitializedMenuBuilderReferences = false;
      }

      private Intent createProcessTextIntent() {
         return (new Intent()).setAction("android.intent.action.PROCESS_TEXT").setType("text/plain");
      }

      private Intent createProcessTextIntentForResolveInfo(ResolveInfo var1, TextView var2) {
         return this.createProcessTextIntent().putExtra("android.intent.extra.PROCESS_TEXT_READONLY", this.isEditable(var2) ^ true).setClassName(var1.activityInfo.packageName, var1.activityInfo.name);
      }

      private List getSupportedActivities(Context var1, PackageManager var2) {
         ArrayList var3 = new ArrayList();
         if (!(var1 instanceof Activity)) {
            return var3;
         } else {
            Iterator var5 = var2.queryIntentActivities(this.createProcessTextIntent(), 0).iterator();

            while(var5.hasNext()) {
               ResolveInfo var4 = (ResolveInfo)var5.next();
               if (this.isSupportedActivity(var4, var1)) {
                  var3.add(var4);
               }
            }

            return var3;
         }
      }

      private boolean isEditable(TextView var1) {
         boolean var2;
         if (var1 instanceof Editable && var1.onCheckIsTextEditor() && var1.isEnabled()) {
            var2 = true;
         } else {
            var2 = false;
         }

         return var2;
      }

      private boolean isSupportedActivity(ResolveInfo var1, Context var2) {
         boolean var3 = var2.getPackageName().equals(var1.activityInfo.packageName);
         boolean var4 = true;
         if (var3) {
            return true;
         } else if (!var1.activityInfo.exported) {
            return false;
         } else {
            var3 = var4;
            if (var1.activityInfo.permission != null) {
               if (var2.checkSelfPermission(var1.activityInfo.permission) == 0) {
                  var3 = var4;
               } else {
                  var3 = false;
               }
            }

            return var3;
         }
      }

      private void recomputeProcessTextMenuItems(Menu var1) {
         Context var5 = this.mTextView.getContext();
         PackageManager var4 = var5.getPackageManager();
         if (!this.mInitializedMenuBuilderReferences) {
            this.mInitializedMenuBuilderReferences = true;

            try {
               Class var3 = Class.forName("com.android.internal.view.menu.MenuBuilder");
               this.mMenuBuilderClass = var3;
               this.mMenuBuilderRemoveItemAtMethod = var3.getDeclaredMethod("removeItemAt", Integer.TYPE);
               this.mCanUseMenuBuilderReferences = true;
            } catch (NoSuchMethodException | ClassNotFoundException var7) {
               this.mMenuBuilderClass = null;
               this.mMenuBuilderRemoveItemAtMethod = null;
               this.mCanUseMenuBuilderReferences = false;
            }
         }

         boolean var10001;
         Method var12;
         label66: {
            try {
               if (this.mCanUseMenuBuilderReferences && this.mMenuBuilderClass.isInstance(var1)) {
                  var12 = this.mMenuBuilderRemoveItemAtMethod;
                  break label66;
               }
            } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException var11) {
               var10001 = false;
               return;
            }

            try {
               var12 = var1.getClass().getDeclaredMethod("removeItemAt", Integer.TYPE);
            } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException var10) {
               var10001 = false;
               return;
            }
         }

         int var2;
         try {
            var2 = var1.size() - 1;
         } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException var9) {
            var10001 = false;
            return;
         }

         for(; var2 >= 0; --var2) {
            try {
               MenuItem var6 = var1.getItem(var2);
               if (var6.getIntent() != null && "android.intent.action.PROCESS_TEXT".equals(var6.getIntent().getAction())) {
                  var12.invoke(var1, var2);
               }
            } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException var8) {
               var10001 = false;
               return;
            }
         }

         List var14 = this.getSupportedActivities(var5, var4);

         for(var2 = 0; var2 < var14.size(); ++var2) {
            ResolveInfo var13 = (ResolveInfo)var14.get(var2);
            var1.add(0, 0, var2 + 100, var13.loadLabel(var4)).setIntent(this.createProcessTextIntentForResolveInfo(var13, this.mTextView)).setShowAsAction(1);
         }

      }

      ActionMode.Callback getWrappedCallback() {
         return this.mCallback;
      }

      public boolean onActionItemClicked(ActionMode var1, MenuItem var2) {
         return this.mCallback.onActionItemClicked(var1, var2);
      }

      public boolean onCreateActionMode(ActionMode var1, Menu var2) {
         return this.mCallback.onCreateActionMode(var1, var2);
      }

      public void onDestroyActionMode(ActionMode var1) {
         this.mCallback.onDestroyActionMode(var1);
      }

      public boolean onPrepareActionMode(ActionMode var1, Menu var2) {
         this.recomputeProcessTextMenuItems(var2);
         return this.mCallback.onPrepareActionMode(var1, var2);
      }
   }
}
