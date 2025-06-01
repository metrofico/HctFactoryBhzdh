package androidx.appcompat.app;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.TypedArray;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.util.Log;
import android.view.InflateException;
import android.view.View;
import androidx.appcompat.R;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.appcompat.widget.AppCompatAutoCompleteTextView;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.AppCompatCheckedTextView;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatMultiAutoCompleteTextView;
import androidx.appcompat.widget.AppCompatRadioButton;
import androidx.appcompat.widget.AppCompatRatingBar;
import androidx.appcompat.widget.AppCompatSeekBar;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.AppCompatToggleButton;
import androidx.appcompat.widget.TintContextWrapper;
import androidx.collection.SimpleArrayMap;
import androidx.core.view.ViewCompat;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class AppCompatViewInflater {
   private static final String LOG_TAG = "AppCompatViewInflater";
   private static final int[] sAccessibilityHeading = new int[]{16844160};
   private static final int[] sAccessibilityPaneTitle = new int[]{16844156};
   private static final String[] sClassPrefixList = new String[]{"android.widget.", "android.view.", "android.webkit."};
   private static final SimpleArrayMap sConstructorMap = new SimpleArrayMap();
   private static final Class[] sConstructorSignature = new Class[]{Context.class, AttributeSet.class};
   private static final int[] sOnClickAttrs = new int[]{16843375};
   private static final int[] sScreenReaderFocusable = new int[]{16844148};
   private final Object[] mConstructorArgs = new Object[2];

   private void backportAccessibilityAttributes(Context var1, View var2, AttributeSet var3) {
      if (VERSION.SDK_INT >= 19 && VERSION.SDK_INT <= 28) {
         TypedArray var4 = var1.obtainStyledAttributes(var3, sAccessibilityHeading);
         if (var4.hasValue(0)) {
            ViewCompat.setAccessibilityHeading(var2, var4.getBoolean(0, false));
         }

         var4.recycle();
         var4 = var1.obtainStyledAttributes(var3, sAccessibilityPaneTitle);
         if (var4.hasValue(0)) {
            ViewCompat.setAccessibilityPaneTitle(var2, var4.getString(0));
         }

         var4.recycle();
         TypedArray var5 = var1.obtainStyledAttributes(var3, sScreenReaderFocusable);
         if (var5.hasValue(0)) {
            ViewCompat.setScreenReaderFocusable(var2, var5.getBoolean(0, false));
         }

         var5.recycle();
      }

   }

   private void checkOnClickListener(View var1, AttributeSet var2) {
      Context var3 = var1.getContext();
      if (var3 instanceof ContextWrapper && (VERSION.SDK_INT < 15 || ViewCompat.hasOnClickListeners(var1))) {
         TypedArray var5 = var3.obtainStyledAttributes(var2, sOnClickAttrs);
         String var4 = var5.getString(0);
         if (var4 != null) {
            var1.setOnClickListener(new DeclaredOnClickListener(var1, var4));
         }

         var5.recycle();
      }

   }

   private View createViewByPrefix(Context var1, String var2, String var3) throws ClassNotFoundException, InflateException {
      SimpleArrayMap var6 = sConstructorMap;
      Constructor var5 = (Constructor)var6.get(var2);
      Constructor var4 = var5;
      boolean var10001;
      if (var5 == null) {
         if (var3 != null) {
            try {
               StringBuilder var11 = new StringBuilder();
               var3 = var11.append(var3).append(var2).toString();
            } catch (Exception var9) {
               var10001 = false;
               return null;
            }
         } else {
            var3 = var2;
         }

         try {
            var4 = Class.forName(var3, false, var1.getClassLoader()).asSubclass(View.class).getConstructor(sConstructorSignature);
            var6.put(var2, var4);
         } catch (Exception var8) {
            var10001 = false;
            return null;
         }
      }

      try {
         var4.setAccessible(true);
         View var10 = (View)var4.newInstance(this.mConstructorArgs);
         return var10;
      } catch (Exception var7) {
         var10001 = false;
         return null;
      }
   }

   private View createViewFromTag(Context param1, String param2, AttributeSet param3) {
      // $FF: Couldn't be decompiled
   }

   private static Context themifyContext(Context var0, AttributeSet var1, boolean var2, boolean var3) {
      TypedArray var6 = var0.obtainStyledAttributes(var1, R.styleable.View, 0, 0);
      int var4;
      if (var2) {
         var4 = var6.getResourceId(R.styleable.View_android_theme, 0);
      } else {
         var4 = 0;
      }

      int var5 = var4;
      if (var3) {
         var5 = var4;
         if (var4 == 0) {
            var4 = var6.getResourceId(R.styleable.View_theme, 0);
            var5 = var4;
            if (var4 != 0) {
               Log.i("AppCompatViewInflater", "app:theme is now deprecated. Please move to using android:theme instead.");
               var5 = var4;
            }
         }
      }

      var6.recycle();
      Object var7 = var0;
      if (var5 != 0) {
         if (var0 instanceof ContextThemeWrapper) {
            var7 = var0;
            if (((ContextThemeWrapper)var0).getThemeResId() == var5) {
               return (Context)var7;
            }
         }

         var7 = new ContextThemeWrapper(var0, var5);
      }

      return (Context)var7;
   }

   private void verifyNotNull(View var1, String var2) {
      if (var1 == null) {
         throw new IllegalStateException(this.getClass().getName() + " asked to inflate view for <" + var2 + ">, but returned null");
      }
   }

   protected AppCompatAutoCompleteTextView createAutoCompleteTextView(Context var1, AttributeSet var2) {
      return new AppCompatAutoCompleteTextView(var1, var2);
   }

   protected AppCompatButton createButton(Context var1, AttributeSet var2) {
      return new AppCompatButton(var1, var2);
   }

   protected AppCompatCheckBox createCheckBox(Context var1, AttributeSet var2) {
      return new AppCompatCheckBox(var1, var2);
   }

   protected AppCompatCheckedTextView createCheckedTextView(Context var1, AttributeSet var2) {
      return new AppCompatCheckedTextView(var1, var2);
   }

   protected AppCompatEditText createEditText(Context var1, AttributeSet var2) {
      return new AppCompatEditText(var1, var2);
   }

   protected AppCompatImageButton createImageButton(Context var1, AttributeSet var2) {
      return new AppCompatImageButton(var1, var2);
   }

   protected AppCompatImageView createImageView(Context var1, AttributeSet var2) {
      return new AppCompatImageView(var1, var2);
   }

   protected AppCompatMultiAutoCompleteTextView createMultiAutoCompleteTextView(Context var1, AttributeSet var2) {
      return new AppCompatMultiAutoCompleteTextView(var1, var2);
   }

   protected AppCompatRadioButton createRadioButton(Context var1, AttributeSet var2) {
      return new AppCompatRadioButton(var1, var2);
   }

   protected AppCompatRatingBar createRatingBar(Context var1, AttributeSet var2) {
      return new AppCompatRatingBar(var1, var2);
   }

   protected AppCompatSeekBar createSeekBar(Context var1, AttributeSet var2) {
      return new AppCompatSeekBar(var1, var2);
   }

   protected AppCompatSpinner createSpinner(Context var1, AttributeSet var2) {
      return new AppCompatSpinner(var1, var2);
   }

   protected AppCompatTextView createTextView(Context var1, AttributeSet var2) {
      return new AppCompatTextView(var1, var2);
   }

   protected AppCompatToggleButton createToggleButton(Context var1, AttributeSet var2) {
      return new AppCompatToggleButton(var1, var2);
   }

   protected View createView(Context var1, String var2, AttributeSet var3) {
      return null;
   }

   final View createView(View var1, String var2, Context var3, AttributeSet var4, boolean var5, boolean var6, boolean var7, boolean var8) {
      Context var10;
      if (var5 && var1 != null) {
         var10 = var1.getContext();
      } else {
         var10 = var3;
      }

      Context var12;
      label109: {
         if (!var6) {
            var12 = var10;
            if (!var7) {
               break label109;
            }
         }

         var12 = themifyContext(var10, var4, var6, var7);
      }

      var10 = var12;
      if (var8) {
         var10 = TintContextWrapper.wrap(var12);
      }

      var2.hashCode();
      Object var13;
      switch (var2) {
         case "RatingBar":
            var13 = this.createRatingBar(var10, var4);
            this.verifyNotNull((View)var13, var2);
            break;
         case "CheckedTextView":
            var13 = this.createCheckedTextView(var10, var4);
            this.verifyNotNull((View)var13, var2);
            break;
         case "MultiAutoCompleteTextView":
            var13 = this.createMultiAutoCompleteTextView(var10, var4);
            this.verifyNotNull((View)var13, var2);
            break;
         case "TextView":
            var13 = this.createTextView(var10, var4);
            this.verifyNotNull((View)var13, var2);
            break;
         case "ImageButton":
            var13 = this.createImageButton(var10, var4);
            this.verifyNotNull((View)var13, var2);
            break;
         case "SeekBar":
            var13 = this.createSeekBar(var10, var4);
            this.verifyNotNull((View)var13, var2);
            break;
         case "Spinner":
            var13 = this.createSpinner(var10, var4);
            this.verifyNotNull((View)var13, var2);
            break;
         case "RadioButton":
            var13 = this.createRadioButton(var10, var4);
            this.verifyNotNull((View)var13, var2);
            break;
         case "ToggleButton":
            var13 = this.createToggleButton(var10, var4);
            this.verifyNotNull((View)var13, var2);
            break;
         case "ImageView":
            var13 = this.createImageView(var10, var4);
            this.verifyNotNull((View)var13, var2);
            break;
         case "AutoCompleteTextView":
            var13 = this.createAutoCompleteTextView(var10, var4);
            this.verifyNotNull((View)var13, var2);
            break;
         case "CheckBox":
            var13 = this.createCheckBox(var10, var4);
            this.verifyNotNull((View)var13, var2);
            break;
         case "EditText":
            var13 = this.createEditText(var10, var4);
            this.verifyNotNull((View)var13, var2);
            break;
         case "Button":
            var13 = this.createButton(var10, var4);
            this.verifyNotNull((View)var13, var2);
            break;
         default:
            var13 = this.createView(var10, var2, var4);
      }

      Object var11 = var13;
      if (var13 == null) {
         var11 = var13;
         if (var3 != var10) {
            var11 = this.createViewFromTag(var10, var2, var4);
         }
      }

      if (var11 != null) {
         this.checkOnClickListener((View)var11, var4);
         this.backportAccessibilityAttributes(var10, (View)var11, var4);
      }

      return (View)var11;
   }

   private static class DeclaredOnClickListener implements View.OnClickListener {
      private final View mHostView;
      private final String mMethodName;
      private Context mResolvedContext;
      private Method mResolvedMethod;

      public DeclaredOnClickListener(View var1, String var2) {
         this.mHostView = var1;
         this.mMethodName = var2;
      }

      private void resolveMethod(Context var1) {
         while(var1 != null) {
            label44: {
               boolean var10001;
               Method var3;
               try {
                  if (var1.isRestricted()) {
                     break label44;
                  }

                  var3 = var1.getClass().getMethod(this.mMethodName, View.class);
               } catch (NoSuchMethodException var5) {
                  var10001 = false;
                  break label44;
               }

               if (var3 != null) {
                  try {
                     this.mResolvedMethod = var3;
                     this.mResolvedContext = var1;
                     return;
                  } catch (NoSuchMethodException var4) {
                     var10001 = false;
                  }
               }
            }

            if (var1 instanceof ContextWrapper) {
               var1 = ((ContextWrapper)var1).getBaseContext();
            } else {
               var1 = null;
            }
         }

         int var2 = this.mHostView.getId();
         String var6;
         if (var2 == -1) {
            var6 = "";
         } else {
            var6 = " with id '" + this.mHostView.getContext().getResources().getResourceEntryName(var2) + "'";
         }

         throw new IllegalStateException("Could not find method " + this.mMethodName + "(View) in a parent or ancestor Context for android:onClick attribute defined on view " + this.mHostView.getClass() + var6);
      }

      public void onClick(View var1) {
         if (this.mResolvedMethod == null) {
            this.resolveMethod(this.mHostView.getContext());
         }

         try {
            this.mResolvedMethod.invoke(this.mResolvedContext, var1);
         } catch (IllegalAccessException var2) {
            throw new IllegalStateException("Could not execute non-public method for android:onClick", var2);
         } catch (InvocationTargetException var3) {
            throw new IllegalStateException("Could not execute method for android:onClick", var3);
         }
      }
   }
}
