package androidx.appcompat.app;

import android.app.Activity;
import android.app.Dialog;
import android.app.UiModeManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.os.LocaleList;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.PowerManager;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.util.AndroidRuntimeException;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import androidx.appcompat.R;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.view.ActionMode;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.appcompat.view.StandaloneActionMode;
import androidx.appcompat.view.SupportActionModeWrapper;
import androidx.appcompat.view.SupportMenuInflater;
import androidx.appcompat.view.WindowCallbackWrapper;
import androidx.appcompat.view.menu.ListMenuPresenter;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuPresenter;
import androidx.appcompat.view.menu.MenuView;
import androidx.appcompat.widget.ActionBarContextView;
import androidx.appcompat.widget.AppCompatDrawableManager;
import androidx.appcompat.widget.ContentFrameLayout;
import androidx.appcompat.widget.DecorContentParent;
import androidx.appcompat.widget.FitWindowsViewGroup;
import androidx.appcompat.widget.TintTypedArray;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.widget.VectorEnabledTintResources;
import androidx.appcompat.widget.ViewStubCompat;
import androidx.appcompat.widget.ViewUtils;
import androidx.collection.SimpleArrayMap;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NavUtils;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.util.ObjectsCompat;
import androidx.core.view.KeyEventDispatcher;
import androidx.core.view.LayoutInflaterCompat;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewPropertyAnimatorCompat;
import androidx.core.view.ViewPropertyAnimatorListener;
import androidx.core.view.ViewPropertyAnimatorListenerAdapter;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.widget.PopupWindowCompat;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;

class AppCompatDelegateImpl extends AppCompatDelegate implements MenuBuilder.Callback, LayoutInflater.Factory2 {
   static final String EXCEPTION_HANDLER_MESSAGE_SUFFIX = ". If the resource you are trying to use is a vector resource, you may be referencing it in an unsupported way. See AppCompatDelegate.setCompatVectorFromResourcesEnabled() for more info.";
   private static final boolean IS_PRE_LOLLIPOP;
   private static final boolean sCanApplyOverrideConfiguration;
   private static final boolean sCanReturnDifferentContext;
   private static boolean sInstalledExceptionHandler;
   private static final SimpleArrayMap sLocalNightModes = new SimpleArrayMap();
   private static final int[] sWindowBackgroundStyleable;
   ActionBar mActionBar;
   private ActionMenuPresenterCallback mActionMenuPresenterCallback;
   ActionMode mActionMode;
   PopupWindow mActionModePopup;
   ActionBarContextView mActionModeView;
   private boolean mActivityHandlesUiMode;
   private boolean mActivityHandlesUiModeChecked;
   final AppCompatCallback mAppCompatCallback;
   private AppCompatViewInflater mAppCompatViewInflater;
   private AppCompatWindowCallback mAppCompatWindowCallback;
   private AutoNightModeManager mAutoBatteryNightModeManager;
   private AutoNightModeManager mAutoTimeNightModeManager;
   private boolean mBaseContextAttached;
   private boolean mClosingActionMenu;
   final Context mContext;
   private boolean mCreated;
   private DecorContentParent mDecorContentParent;
   boolean mDestroyed;
   private Configuration mEffectiveConfiguration;
   private boolean mEnableDefaultActionBarUp;
   ViewPropertyAnimatorCompat mFadeAnim;
   private boolean mFeatureIndeterminateProgress;
   private boolean mFeatureProgress;
   private boolean mHandleNativeActionModes;
   boolean mHasActionBar;
   final Object mHost;
   int mInvalidatePanelMenuFeatures;
   boolean mInvalidatePanelMenuPosted;
   private final Runnable mInvalidatePanelMenuRunnable;
   boolean mIsFloating;
   private LayoutIncludeDetector mLayoutIncludeDetector;
   private int mLocalNightMode;
   private boolean mLongPressBackDown;
   MenuInflater mMenuInflater;
   boolean mOverlayActionBar;
   boolean mOverlayActionMode;
   private PanelMenuPresenterCallback mPanelMenuPresenterCallback;
   private PanelFeatureState[] mPanels;
   private PanelFeatureState mPreparedPanel;
   Runnable mShowActionModePopup;
   private View mStatusGuard;
   ViewGroup mSubDecor;
   private boolean mSubDecorInstalled;
   private Rect mTempRect1;
   private Rect mTempRect2;
   private int mThemeResId;
   private CharSequence mTitle;
   private TextView mTitleView;
   Window mWindow;
   boolean mWindowNoTitle;

   static {
      int var0 = VERSION.SDK_INT;
      boolean var2 = false;
      boolean var1;
      if (var0 < 21) {
         var1 = true;
      } else {
         var1 = false;
      }

      IS_PRE_LOLLIPOP = var1;
      sWindowBackgroundStyleable = new int[]{16842836};
      sCanReturnDifferentContext = "robolectric".equals(Build.FINGERPRINT) ^ true;
      if (VERSION.SDK_INT >= 17) {
         var2 = true;
      }

      sCanApplyOverrideConfiguration = var2;
      if (var1 && !sInstalledExceptionHandler) {
         Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler(Thread.getDefaultUncaughtExceptionHandler()) {
            final Thread.UncaughtExceptionHandler val$defHandler;

            {
               this.val$defHandler = var1;
            }

            private boolean shouldWrapException(Throwable var1) {
               boolean var4 = var1 instanceof Resources.NotFoundException;
               boolean var3 = false;
               boolean var2 = var3;
               if (var4) {
                  String var5 = var1.getMessage();
                  var2 = var3;
                  if (var5 != null) {
                     if (!var5.contains("drawable")) {
                        var2 = var3;
                        if (!var5.contains("Drawable")) {
                           return var2;
                        }
                     }

                     var2 = true;
                  }
               }

               return var2;
            }

            public void uncaughtException(Thread var1, Throwable var2) {
               if (this.shouldWrapException(var2)) {
                  Resources.NotFoundException var3 = new Resources.NotFoundException(var2.getMessage() + ". If the resource you are trying to use is a vector resource, you may be referencing it in an unsupported way. See AppCompatDelegate.setCompatVectorFromResourcesEnabled() for more info.");
                  var3.initCause(var2.getCause());
                  var3.setStackTrace(var2.getStackTrace());
                  this.val$defHandler.uncaughtException(var1, var3);
               } else {
                  this.val$defHandler.uncaughtException(var1, var2);
               }

            }
         });
         sInstalledExceptionHandler = true;
      }

   }

   AppCompatDelegateImpl(Activity var1, AppCompatCallback var2) {
      this(var1, (Window)null, var2, var1);
   }

   AppCompatDelegateImpl(Dialog var1, AppCompatCallback var2) {
      this(var1.getContext(), var1.getWindow(), var2, var1);
   }

   AppCompatDelegateImpl(Context var1, Activity var2, AppCompatCallback var3) {
      this(var1, (Window)null, var3, var2);
   }

   AppCompatDelegateImpl(Context var1, Window var2, AppCompatCallback var3) {
      this(var1, var2, var3, var1);
   }

   private AppCompatDelegateImpl(Context var1, Window var2, AppCompatCallback var3, Object var4) {
      this.mFadeAnim = null;
      this.mHandleNativeActionModes = true;
      this.mLocalNightMode = -100;
      this.mInvalidatePanelMenuRunnable = new Runnable(this) {
         final AppCompatDelegateImpl this$0;

         {
            this.this$0 = var1;
         }

         public void run() {
            if ((this.this$0.mInvalidatePanelMenuFeatures & 1) != 0) {
               this.this$0.doInvalidatePanelMenu(0);
            }

            if ((this.this$0.mInvalidatePanelMenuFeatures & 4096) != 0) {
               this.this$0.doInvalidatePanelMenu(108);
            }

            this.this$0.mInvalidatePanelMenuPosted = false;
            this.this$0.mInvalidatePanelMenuFeatures = 0;
         }
      };
      this.mContext = var1;
      this.mAppCompatCallback = var3;
      this.mHost = var4;
      if (this.mLocalNightMode == -100 && var4 instanceof Dialog) {
         AppCompatActivity var5 = this.tryUnwrapContext();
         if (var5 != null) {
            this.mLocalNightMode = var5.getDelegate().getLocalNightMode();
         }
      }

      if (this.mLocalNightMode == -100) {
         SimpleArrayMap var6 = sLocalNightModes;
         Integer var7 = (Integer)var6.get(var4.getClass().getName());
         if (var7 != null) {
            this.mLocalNightMode = var7;
            var6.remove(var4.getClass().getName());
         }
      }

      if (var2 != null) {
         this.attachToWindow(var2);
      }

      AppCompatDrawableManager.preload();
   }

   private boolean applyDayNight(boolean var1) {
      if (this.mDestroyed) {
         return false;
      } else {
         int var2 = this.calculateNightMode();
         var1 = this.updateForNightMode(this.mapNightMode(this.mContext, var2), var1);
         AutoNightModeManager var3;
         if (var2 == 0) {
            this.getAutoTimeNightModeManager(this.mContext).setup();
         } else {
            var3 = this.mAutoTimeNightModeManager;
            if (var3 != null) {
               var3.cleanup();
            }
         }

         if (var2 == 3) {
            this.getAutoBatteryNightModeManager(this.mContext).setup();
         } else {
            var3 = this.mAutoBatteryNightModeManager;
            if (var3 != null) {
               var3.cleanup();
            }
         }

         return var1;
      }
   }

   private void applyFixedSizeWindow() {
      ContentFrameLayout var1 = (ContentFrameLayout)this.mSubDecor.findViewById(16908290);
      View var2 = this.mWindow.getDecorView();
      var1.setDecorPadding(var2.getPaddingLeft(), var2.getPaddingTop(), var2.getPaddingRight(), var2.getPaddingBottom());
      TypedArray var3 = this.mContext.obtainStyledAttributes(R.styleable.AppCompatTheme);
      var3.getValue(R.styleable.AppCompatTheme_windowMinWidthMajor, var1.getMinWidthMajor());
      var3.getValue(R.styleable.AppCompatTheme_windowMinWidthMinor, var1.getMinWidthMinor());
      if (var3.hasValue(R.styleable.AppCompatTheme_windowFixedWidthMajor)) {
         var3.getValue(R.styleable.AppCompatTheme_windowFixedWidthMajor, var1.getFixedWidthMajor());
      }

      if (var3.hasValue(R.styleable.AppCompatTheme_windowFixedWidthMinor)) {
         var3.getValue(R.styleable.AppCompatTheme_windowFixedWidthMinor, var1.getFixedWidthMinor());
      }

      if (var3.hasValue(R.styleable.AppCompatTheme_windowFixedHeightMajor)) {
         var3.getValue(R.styleable.AppCompatTheme_windowFixedHeightMajor, var1.getFixedHeightMajor());
      }

      if (var3.hasValue(R.styleable.AppCompatTheme_windowFixedHeightMinor)) {
         var3.getValue(R.styleable.AppCompatTheme_windowFixedHeightMinor, var1.getFixedHeightMinor());
      }

      var3.recycle();
      var1.requestLayout();
   }

   private void attachToWindow(Window var1) {
      if (this.mWindow == null) {
         Window.Callback var2 = var1.getCallback();
         if (!(var2 instanceof AppCompatWindowCallback)) {
            AppCompatWindowCallback var4 = new AppCompatWindowCallback(this, var2);
            this.mAppCompatWindowCallback = var4;
            var1.setCallback(var4);
            TintTypedArray var3 = TintTypedArray.obtainStyledAttributes(this.mContext, (AttributeSet)null, sWindowBackgroundStyleable);
            Drawable var5 = var3.getDrawableIfKnown(0);
            if (var5 != null) {
               var1.setBackgroundDrawable(var5);
            }

            var3.recycle();
            this.mWindow = var1;
         } else {
            throw new IllegalStateException("AppCompat has already installed itself into the Window");
         }
      } else {
         throw new IllegalStateException("AppCompat has already installed itself into the Window");
      }
   }

   private int calculateNightMode() {
      int var1 = this.mLocalNightMode;
      if (var1 == -100) {
         var1 = getDefaultNightMode();
      }

      return var1;
   }

   private void cleanupAutoManagers() {
      AutoNightModeManager var1 = this.mAutoTimeNightModeManager;
      if (var1 != null) {
         var1.cleanup();
      }

      var1 = this.mAutoBatteryNightModeManager;
      if (var1 != null) {
         var1.cleanup();
      }

   }

   private Configuration createOverrideConfigurationForDayNight(Context var1, int var2, Configuration var3) {
      if (var2 != 1) {
         if (var2 != 2) {
            var2 = var1.getApplicationContext().getResources().getConfiguration().uiMode & 48;
         } else {
            var2 = 32;
         }
      } else {
         var2 = 16;
      }

      Configuration var4 = new Configuration();
      var4.fontScale = 0.0F;
      if (var3 != null) {
         var4.setTo(var3);
      }

      var4.uiMode = var2 | var4.uiMode & -49;
      return var4;
   }

   private ViewGroup createSubDecor() {
      TypedArray var1 = this.mContext.obtainStyledAttributes(R.styleable.AppCompatTheme);
      if (var1.hasValue(R.styleable.AppCompatTheme_windowActionBar)) {
         if (var1.getBoolean(R.styleable.AppCompatTheme_windowNoTitle, false)) {
            this.requestWindowFeature(1);
         } else if (var1.getBoolean(R.styleable.AppCompatTheme_windowActionBar, false)) {
            this.requestWindowFeature(108);
         }

         if (var1.getBoolean(R.styleable.AppCompatTheme_windowActionBarOverlay, false)) {
            this.requestWindowFeature(109);
         }

         if (var1.getBoolean(R.styleable.AppCompatTheme_windowActionModeOverlay, false)) {
            this.requestWindowFeature(10);
         }

         this.mIsFloating = var1.getBoolean(R.styleable.AppCompatTheme_android_windowIsFloating, false);
         var1.recycle();
         this.ensureWindow();
         this.mWindow.getDecorView();
         LayoutInflater var5 = LayoutInflater.from(this.mContext);
         ViewGroup var6;
         if (!this.mWindowNoTitle) {
            if (this.mIsFloating) {
               var6 = (ViewGroup)var5.inflate(R.layout.abc_dialog_title_material, (ViewGroup)null);
               this.mOverlayActionBar = false;
               this.mHasActionBar = false;
            } else if (this.mHasActionBar) {
               TypedValue var7 = new TypedValue();
               this.mContext.getTheme().resolveAttribute(R.attr.actionBarTheme, var7, true);
               Object var8;
               if (var7.resourceId != 0) {
                  var8 = new ContextThemeWrapper(this.mContext, var7.resourceId);
               } else {
                  var8 = this.mContext;
               }

               ViewGroup var2 = (ViewGroup)LayoutInflater.from((Context)var8).inflate(R.layout.abc_screen_toolbar, (ViewGroup)null);
               DecorContentParent var10 = (DecorContentParent)var2.findViewById(R.id.decor_content_parent);
               this.mDecorContentParent = var10;
               var10.setWindowCallback(this.getWindowCallback());
               if (this.mOverlayActionBar) {
                  this.mDecorContentParent.initFeature(109);
               }

               if (this.mFeatureProgress) {
                  this.mDecorContentParent.initFeature(2);
               }

               var6 = var2;
               if (this.mFeatureIndeterminateProgress) {
                  this.mDecorContentParent.initFeature(5);
                  var6 = var2;
               }
            } else {
               var6 = null;
            }
         } else if (this.mOverlayActionMode) {
            var6 = (ViewGroup)var5.inflate(R.layout.abc_screen_simple_overlay_action_mode, (ViewGroup)null);
         } else {
            var6 = (ViewGroup)var5.inflate(R.layout.abc_screen_simple, (ViewGroup)null);
         }

         if (var6 != null) {
            if (VERSION.SDK_INT >= 21) {
               ViewCompat.setOnApplyWindowInsetsListener(var6, new OnApplyWindowInsetsListener(this) {
                  final AppCompatDelegateImpl this$0;

                  {
                     this.this$0 = var1;
                  }

                  public WindowInsetsCompat onApplyWindowInsets(View var1, WindowInsetsCompat var2) {
                     int var4 = var2.getSystemWindowInsetTop();
                     int var3 = this.this$0.updateStatusGuard(var2, (Rect)null);
                     WindowInsetsCompat var5 = var2;
                     if (var4 != var3) {
                        var5 = var2.replaceSystemWindowInsets(var2.getSystemWindowInsetLeft(), var3, var2.getSystemWindowInsetRight(), var2.getSystemWindowInsetBottom());
                     }

                     return ViewCompat.onApplyWindowInsets(var1, var5);
                  }
               });
            } else if (var6 instanceof FitWindowsViewGroup) {
               ((FitWindowsViewGroup)var6).setOnFitSystemWindowsListener(new FitWindowsViewGroup.OnFitSystemWindowsListener(this) {
                  final AppCompatDelegateImpl this$0;

                  {
                     this.this$0 = var1;
                  }

                  public void onFitSystemWindows(Rect var1) {
                     var1.top = this.this$0.updateStatusGuard((WindowInsetsCompat)null, var1);
                  }
               });
            }

            if (this.mDecorContentParent == null) {
               this.mTitleView = (TextView)var6.findViewById(R.id.title);
            }

            ViewUtils.makeOptionalFitsSystemWindows(var6);
            ContentFrameLayout var9 = (ContentFrameLayout)var6.findViewById(R.id.action_bar_activity_content);
            ViewGroup var3 = (ViewGroup)this.mWindow.findViewById(16908290);
            if (var3 != null) {
               while(var3.getChildCount() > 0) {
                  View var4 = var3.getChildAt(0);
                  var3.removeViewAt(0);
                  var9.addView(var4);
               }

               var3.setId(-1);
               var9.setId(16908290);
               if (var3 instanceof FrameLayout) {
                  ((FrameLayout)var3).setForeground((Drawable)null);
               }
            }

            this.mWindow.setContentView(var6);
            var9.setAttachListener(new ContentFrameLayout.OnAttachListener(this) {
               final AppCompatDelegateImpl this$0;

               {
                  this.this$0 = var1;
               }

               public void onAttachedFromWindow() {
               }

               public void onDetachedFromWindow() {
                  this.this$0.dismissPopups();
               }
            });
            return var6;
         } else {
            throw new IllegalArgumentException("AppCompat does not support the current theme features: { windowActionBar: " + this.mHasActionBar + ", windowActionBarOverlay: " + this.mOverlayActionBar + ", android:windowIsFloating: " + this.mIsFloating + ", windowActionModeOverlay: " + this.mOverlayActionMode + ", windowNoTitle: " + this.mWindowNoTitle + " }");
         }
      } else {
         var1.recycle();
         throw new IllegalStateException("You need to use a Theme.AppCompat theme (or descendant) with this activity.");
      }
   }

   private void ensureSubDecor() {
      if (!this.mSubDecorInstalled) {
         this.mSubDecor = this.createSubDecor();
         CharSequence var1 = this.getTitle();
         if (!TextUtils.isEmpty(var1)) {
            DecorContentParent var2 = this.mDecorContentParent;
            if (var2 != null) {
               var2.setWindowTitle(var1);
            } else if (this.peekSupportActionBar() != null) {
               this.peekSupportActionBar().setWindowTitle(var1);
            } else {
               TextView var4 = this.mTitleView;
               if (var4 != null) {
                  var4.setText(var1);
               }
            }
         }

         this.applyFixedSizeWindow();
         this.onSubDecorInstalled(this.mSubDecor);
         this.mSubDecorInstalled = true;
         PanelFeatureState var3 = this.getPanelState(0, false);
         if (!this.mDestroyed && (var3 == null || var3.menu == null)) {
            this.invalidatePanelMenu(108);
         }
      }

   }

   private void ensureWindow() {
      if (this.mWindow == null) {
         Object var1 = this.mHost;
         if (var1 instanceof Activity) {
            this.attachToWindow(((Activity)var1).getWindow());
         }
      }

      if (this.mWindow == null) {
         throw new IllegalStateException("We have not been given a Window");
      }
   }

   private static Configuration generateConfigDelta(Configuration var0, Configuration var1) {
      Configuration var2 = new Configuration();
      var2.fontScale = 0.0F;
      if (var1 != null && var0.diff(var1) != 0) {
         if (var0.fontScale != var1.fontScale) {
            var2.fontScale = var1.fontScale;
         }

         if (var0.mcc != var1.mcc) {
            var2.mcc = var1.mcc;
         }

         if (var0.mnc != var1.mnc) {
            var2.mnc = var1.mnc;
         }

         if (VERSION.SDK_INT >= 24) {
            AppCompatDelegateImpl.Api24Impl.generateConfigDelta_locale(var0, var1, var2);
         } else if (!ObjectsCompat.equals(var0.locale, var1.locale)) {
            var2.locale = var1.locale;
         }

         if (var0.touchscreen != var1.touchscreen) {
            var2.touchscreen = var1.touchscreen;
         }

         if (var0.keyboard != var1.keyboard) {
            var2.keyboard = var1.keyboard;
         }

         if (var0.keyboardHidden != var1.keyboardHidden) {
            var2.keyboardHidden = var1.keyboardHidden;
         }

         if (var0.navigation != var1.navigation) {
            var2.navigation = var1.navigation;
         }

         if (var0.navigationHidden != var1.navigationHidden) {
            var2.navigationHidden = var1.navigationHidden;
         }

         if (var0.orientation != var1.orientation) {
            var2.orientation = var1.orientation;
         }

         if ((var0.screenLayout & 15) != (var1.screenLayout & 15)) {
            var2.screenLayout |= var1.screenLayout & 15;
         }

         if ((var0.screenLayout & 192) != (var1.screenLayout & 192)) {
            var2.screenLayout |= var1.screenLayout & 192;
         }

         if ((var0.screenLayout & 48) != (var1.screenLayout & 48)) {
            var2.screenLayout |= var1.screenLayout & 48;
         }

         if ((var0.screenLayout & 768) != (var1.screenLayout & 768)) {
            var2.screenLayout |= var1.screenLayout & 768;
         }

         if (VERSION.SDK_INT >= 26) {
            AppCompatDelegateImpl.Api26Impl.generateConfigDelta_colorMode(var0, var1, var2);
         }

         if ((var0.uiMode & 15) != (var1.uiMode & 15)) {
            var2.uiMode |= var1.uiMode & 15;
         }

         if ((var0.uiMode & 48) != (var1.uiMode & 48)) {
            var2.uiMode |= var1.uiMode & 48;
         }

         if (var0.screenWidthDp != var1.screenWidthDp) {
            var2.screenWidthDp = var1.screenWidthDp;
         }

         if (var0.screenHeightDp != var1.screenHeightDp) {
            var2.screenHeightDp = var1.screenHeightDp;
         }

         if (var0.smallestScreenWidthDp != var1.smallestScreenWidthDp) {
            var2.smallestScreenWidthDp = var1.smallestScreenWidthDp;
         }

         if (VERSION.SDK_INT >= 17) {
            AppCompatDelegateImpl.Api17Impl.generateConfigDelta_densityDpi(var0, var1, var2);
         }
      }

      return var2;
   }

   private AutoNightModeManager getAutoBatteryNightModeManager(Context var1) {
      if (this.mAutoBatteryNightModeManager == null) {
         this.mAutoBatteryNightModeManager = new AutoBatteryNightModeManager(this, var1);
      }

      return this.mAutoBatteryNightModeManager;
   }

   private AutoNightModeManager getAutoTimeNightModeManager(Context var1) {
      if (this.mAutoTimeNightModeManager == null) {
         this.mAutoTimeNightModeManager = new AutoTimeNightModeManager(this, TwilightManager.getInstance(var1));
      }

      return this.mAutoTimeNightModeManager;
   }

   private void initWindowDecorActionBar() {
      this.ensureSubDecor();
      if (this.mHasActionBar && this.mActionBar == null) {
         Object var1 = this.mHost;
         if (var1 instanceof Activity) {
            this.mActionBar = new WindowDecorActionBar((Activity)this.mHost, this.mOverlayActionBar);
         } else if (var1 instanceof Dialog) {
            this.mActionBar = new WindowDecorActionBar((Dialog)this.mHost);
         }

         ActionBar var2 = this.mActionBar;
         if (var2 != null) {
            var2.setDefaultDisplayHomeAsUpEnabled(this.mEnableDefaultActionBarUp);
         }
      }

   }

   private boolean initializePanelContent(PanelFeatureState var1) {
      View var3 = var1.createdPanelView;
      boolean var2 = true;
      if (var3 != null) {
         var1.shownPanelView = var1.createdPanelView;
         return true;
      } else if (var1.menu == null) {
         return false;
      } else {
         if (this.mPanelMenuPresenterCallback == null) {
            this.mPanelMenuPresenterCallback = new PanelMenuPresenterCallback(this);
         }

         var1.shownPanelView = (View)var1.getListMenuView(this.mPanelMenuPresenterCallback);
         if (var1.shownPanelView == null) {
            var2 = false;
         }

         return var2;
      }
   }

   private boolean initializePanelDecor(PanelFeatureState var1) {
      var1.setStyle(this.getActionBarThemedContext());
      var1.decorView = new ListMenuDecorView(this, var1.listPresenterContext);
      var1.gravity = 81;
      return true;
   }

   private boolean initializePanelMenu(PanelFeatureState var1) {
      Object var2;
      label28: {
         Context var4 = this.mContext;
         if (var1.featureId != 0) {
            var2 = var4;
            if (var1.featureId != 108) {
               break label28;
            }
         }

         var2 = var4;
         if (this.mDecorContentParent != null) {
            TypedValue var5 = new TypedValue();
            Resources.Theme var6 = var4.getTheme();
            var6.resolveAttribute(R.attr.actionBarTheme, var5, true);
            Resources.Theme var7 = null;
            if (var5.resourceId != 0) {
               var7 = var4.getResources().newTheme();
               var7.setTo(var6);
               var7.applyStyle(var5.resourceId, true);
               var7.resolveAttribute(R.attr.actionBarWidgetTheme, var5, true);
            } else {
               var6.resolveAttribute(R.attr.actionBarWidgetTheme, var5, true);
            }

            Resources.Theme var3 = var7;
            if (var5.resourceId != 0) {
               var3 = var7;
               if (var7 == null) {
                  var3 = var4.getResources().newTheme();
                  var3.setTo(var6);
               }

               var3.applyStyle(var5.resourceId, true);
            }

            var2 = var4;
            if (var3 != null) {
               var2 = new ContextThemeWrapper(var4, 0);
               ((Context)var2).getTheme().setTo(var3);
            }
         }
      }

      MenuBuilder var8 = new MenuBuilder((Context)var2);
      var8.setCallback(this);
      var1.setMenu(var8);
      return true;
   }

   private void invalidatePanelMenu(int var1) {
      this.mInvalidatePanelMenuFeatures |= 1 << var1;
      if (!this.mInvalidatePanelMenuPosted) {
         ViewCompat.postOnAnimation(this.mWindow.getDecorView(), this.mInvalidatePanelMenuRunnable);
         this.mInvalidatePanelMenuPosted = true;
      }

   }

   private boolean isActivityManifestHandlingUiMode() {
      if (!this.mActivityHandlesUiModeChecked && this.mHost instanceof Activity) {
         label68: {
            PackageManager var3 = this.mContext.getPackageManager();
            if (var3 == null) {
               return false;
            }

            PackageManager.NameNotFoundException var10000;
            label69: {
               int var1;
               boolean var10001;
               label61: {
                  label70: {
                     try {
                        if (VERSION.SDK_INT >= 29) {
                           break label70;
                        }
                     } catch (PackageManager.NameNotFoundException var9) {
                        var10000 = var9;
                        var10001 = false;
                        break label69;
                     }

                     label55: {
                        try {
                           if (VERSION.SDK_INT >= 24) {
                              break label55;
                           }
                        } catch (PackageManager.NameNotFoundException var8) {
                           var10000 = var8;
                           var10001 = false;
                           break label69;
                        }

                        var1 = 0;
                        break label61;
                     }

                     var1 = 786432;
                     break label61;
                  }

                  var1 = 269221888;
               }

               ActivityInfo var10;
               try {
                  ComponentName var4 = new ComponentName(this.mContext, this.mHost.getClass());
                  var10 = var3.getActivityInfo(var4, var1);
               } catch (PackageManager.NameNotFoundException var7) {
                  var10000 = var7;
                  var10001 = false;
                  break label69;
               }

               boolean var2;
               label44: {
                  label43: {
                     if (var10 != null) {
                        try {
                           if ((var10.configChanges & 512) != 0) {
                              break label43;
                           }
                        } catch (PackageManager.NameNotFoundException var6) {
                           var10000 = var6;
                           var10001 = false;
                           break label69;
                        }
                     }

                     var2 = false;
                     break label44;
                  }

                  var2 = true;
               }

               try {
                  this.mActivityHandlesUiMode = var2;
                  break label68;
               } catch (PackageManager.NameNotFoundException var5) {
                  var10000 = var5;
                  var10001 = false;
               }
            }

            PackageManager.NameNotFoundException var11 = var10000;
            Log.d("AppCompatDelegate", "Exception while getting ActivityInfo", var11);
            this.mActivityHandlesUiMode = false;
         }
      }

      this.mActivityHandlesUiModeChecked = true;
      return this.mActivityHandlesUiMode;
   }

   private boolean onKeyDownPanel(int var1, KeyEvent var2) {
      if (var2.getRepeatCount() == 0) {
         PanelFeatureState var3 = this.getPanelState(var1, true);
         if (!var3.isOpen) {
            return this.preparePanel(var3, var2);
         }
      }

      return false;
   }

   private boolean onKeyUpPanel(int var1, KeyEvent var2) {
      if (this.mActionMode != null) {
         return false;
      } else {
         boolean var3;
         label53: {
            label57: {
               boolean var4 = true;
               PanelFeatureState var5 = this.getPanelState(var1, true);
               if (var1 == 0) {
                  DecorContentParent var6 = this.mDecorContentParent;
                  if (var6 != null && var6.canShowOverflowMenu() && !ViewConfiguration.get(this.mContext).hasPermanentMenuKey()) {
                     if (this.mDecorContentParent.isOverflowMenuShowing()) {
                        var3 = this.mDecorContentParent.hideOverflowMenu();
                        break label53;
                     }

                     if (!this.mDestroyed && this.preparePanel(var5, var2)) {
                        var3 = this.mDecorContentParent.showOverflowMenu();
                        break label53;
                     }
                     break label57;
                  }
               }

               if (var5.isOpen || var5.isHandled) {
                  var3 = var5.isOpen;
                  this.closePanel(var5, true);
                  break label53;
               }

               if (var5.isPrepared) {
                  if (var5.refreshMenuContent) {
                     var5.isPrepared = false;
                     var3 = this.preparePanel(var5, var2);
                  } else {
                     var3 = true;
                  }

                  if (var3) {
                     this.openPanel(var5, var2);
                     var3 = var4;
                     break label53;
                  }
               }
            }

            var3 = false;
         }

         if (var3) {
            AudioManager var7 = (AudioManager)this.mContext.getApplicationContext().getSystemService("audio");
            if (var7 != null) {
               var7.playSoundEffect(0);
            } else {
               Log.w("AppCompatDelegate", "Couldn't get audio manager");
            }
         }

         return var3;
      }
   }

   private void openPanel(PanelFeatureState var1, KeyEvent var2) {
      if (!var1.isOpen && !this.mDestroyed) {
         WindowManager var5;
         byte var9;
         label100: {
            if (var1.featureId == 0) {
               boolean var3;
               if ((this.mContext.getResources().getConfiguration().screenLayout & 15) == 4) {
                  var3 = true;
               } else {
                  var3 = false;
               }

               if (var3) {
                  return;
               }
            }

            Window.Callback var4 = this.getWindowCallback();
            if (var4 != null && !var4.onMenuOpened(var1.featureId, var1.menu)) {
               this.closePanel(var1, true);
               return;
            }

            var5 = (WindowManager)this.mContext.getSystemService("window");
            if (var5 == null) {
               return;
            }

            if (!this.preparePanel(var1, var2)) {
               return;
            }

            ViewGroup.LayoutParams var6;
            if (var1.decorView != null && !var1.refreshDecorView) {
               if (var1.createdPanelView != null) {
                  var6 = var1.createdPanelView.getLayoutParams();
                  if (var6 != null && var6.width == -1) {
                     var9 = -1;
                     break label100;
                  }
               }
            } else {
               if (var1.decorView == null) {
                  if (!this.initializePanelDecor(var1) || var1.decorView == null) {
                     return;
                  }
               } else if (var1.refreshDecorView && var1.decorView.getChildCount() > 0) {
                  var1.decorView.removeAllViews();
               }

               if (!this.initializePanelContent(var1) || !var1.hasPanelItems()) {
                  var1.refreshDecorView = true;
                  return;
               }

               ViewGroup.LayoutParams var10 = var1.shownPanelView.getLayoutParams();
               var6 = var10;
               if (var10 == null) {
                  var6 = new ViewGroup.LayoutParams(-2, -2);
               }

               int var8 = var1.background;
               var1.decorView.setBackgroundResource(var8);
               ViewParent var11 = var1.shownPanelView.getParent();
               if (var11 instanceof ViewGroup) {
                  ((ViewGroup)var11).removeView(var1.shownPanelView);
               }

               var1.decorView.addView(var1.shownPanelView, var6);
               if (!var1.shownPanelView.hasFocus()) {
                  var1.shownPanelView.requestFocus();
               }
            }

            var9 = -2;
         }

         var1.isHandled = false;
         WindowManager.LayoutParams var7 = new WindowManager.LayoutParams(var9, -2, var1.x, var1.y, 1002, 8519680, -3);
         var7.gravity = var1.gravity;
         var7.windowAnimations = var1.windowAnimations;
         var5.addView(var1.decorView, var7);
         var1.isOpen = true;
      }
   }

   private boolean performPanelShortcut(PanelFeatureState var1, int var2, KeyEvent var3, int var4) {
      boolean var5 = var3.isSystem();
      boolean var6 = false;
      if (var5) {
         return false;
      } else {
         label24: {
            if (!var1.isPrepared) {
               var5 = var6;
               if (!this.preparePanel(var1, var3)) {
                  break label24;
               }
            }

            var5 = var6;
            if (var1.menu != null) {
               var5 = var1.menu.performShortcut(var2, var3, var4);
            }
         }

         if (var5 && (var4 & 1) == 0 && this.mDecorContentParent == null) {
            this.closePanel(var1, true);
         }

         return var5;
      }
   }

   private boolean preparePanel(PanelFeatureState var1, KeyEvent var2) {
      if (this.mDestroyed) {
         return false;
      } else if (var1.isPrepared) {
         return true;
      } else {
         PanelFeatureState var5 = this.mPreparedPanel;
         if (var5 != null && var5 != var1) {
            this.closePanel(var5, false);
         }

         Window.Callback var6 = this.getWindowCallback();
         if (var6 != null) {
            var1.createdPanelView = var6.onCreatePanelView(var1.featureId);
         }

         boolean var3;
         if (var1.featureId != 0 && var1.featureId != 108) {
            var3 = false;
         } else {
            var3 = true;
         }

         if (var3) {
            DecorContentParent var10 = this.mDecorContentParent;
            if (var10 != null) {
               var10.setMenuPrepared();
            }
         }

         if (var1.createdPanelView == null && (!var3 || !(this.peekSupportActionBar() instanceof ToolbarActionBar))) {
            if (var1.menu == null || var1.refreshMenuContent) {
               if (var1.menu == null && (!this.initializePanelMenu(var1) || var1.menu == null)) {
                  return false;
               }

               if (var3 && this.mDecorContentParent != null) {
                  if (this.mActionMenuPresenterCallback == null) {
                     this.mActionMenuPresenterCallback = new ActionMenuPresenterCallback(this);
                  }

                  this.mDecorContentParent.setMenu(var1.menu, this.mActionMenuPresenterCallback);
               }

               var1.menu.stopDispatchingItemsChanged();
               if (!var6.onCreatePanelMenu(var1.featureId, var1.menu)) {
                  var1.setMenu((MenuBuilder)null);
                  if (var3) {
                     DecorContentParent var7 = this.mDecorContentParent;
                     if (var7 != null) {
                        var7.setMenu((Menu)null, this.mActionMenuPresenterCallback);
                     }
                  }

                  return false;
               }

               var1.refreshMenuContent = false;
            }

            var1.menu.stopDispatchingItemsChanged();
            if (var1.frozenActionViewState != null) {
               var1.menu.restoreActionViewStates(var1.frozenActionViewState);
               var1.frozenActionViewState = null;
            }

            if (!var6.onPreparePanel(0, var1.createdPanelView, var1.menu)) {
               if (var3) {
                  DecorContentParent var8 = this.mDecorContentParent;
                  if (var8 != null) {
                     var8.setMenu((Menu)null, this.mActionMenuPresenterCallback);
                  }
               }

               var1.menu.startDispatchingItemsChanged();
               return false;
            }

            int var9;
            if (var2 != null) {
               var9 = var2.getDeviceId();
            } else {
               var9 = -1;
            }

            boolean var4;
            if (KeyCharacterMap.load(var9).getKeyboardType() != 1) {
               var4 = true;
            } else {
               var4 = false;
            }

            var1.qwertyMode = var4;
            var1.menu.setQwertyMode(var1.qwertyMode);
            var1.menu.startDispatchingItemsChanged();
         }

         var1.isPrepared = true;
         var1.isHandled = false;
         this.mPreparedPanel = var1;
         return true;
      }
   }

   private void reopenMenu(boolean var1) {
      DecorContentParent var2 = this.mDecorContentParent;
      if (var2 != null && var2.canShowOverflowMenu() && (!ViewConfiguration.get(this.mContext).hasPermanentMenuKey() || this.mDecorContentParent.isOverflowMenuShowPending())) {
         Window.Callback var5 = this.getWindowCallback();
         if (this.mDecorContentParent.isOverflowMenuShowing() && var1) {
            this.mDecorContentParent.hideOverflowMenu();
            if (!this.mDestroyed) {
               var5.onPanelClosed(108, this.getPanelState(0, true).menu);
            }
         } else if (var5 != null && !this.mDestroyed) {
            if (this.mInvalidatePanelMenuPosted && (this.mInvalidatePanelMenuFeatures & 1) != 0) {
               this.mWindow.getDecorView().removeCallbacks(this.mInvalidatePanelMenuRunnable);
               this.mInvalidatePanelMenuRunnable.run();
            }

            PanelFeatureState var3 = this.getPanelState(0, true);
            if (var3.menu != null && !var3.refreshMenuContent && var5.onPreparePanel(0, var3.createdPanelView, var3.menu)) {
               var5.onMenuOpened(108, var3.menu);
               this.mDecorContentParent.showOverflowMenu();
            }
         }

      } else {
         PanelFeatureState var4 = this.getPanelState(0, true);
         var4.refreshDecorView = true;
         this.closePanel(var4, false);
         this.openPanel(var4, (KeyEvent)null);
      }
   }

   private int sanitizeWindowFeatureId(int var1) {
      if (var1 == 8) {
         Log.i("AppCompatDelegate", "You should now use the AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR id when requesting this feature.");
         return 108;
      } else {
         int var2 = var1;
         if (var1 == 9) {
            Log.i("AppCompatDelegate", "You should now use the AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR_OVERLAY id when requesting this feature.");
            var2 = 109;
         }

         return var2;
      }
   }

   private boolean shouldInheritContext(ViewParent var1) {
      if (var1 == null) {
         return false;
      } else {
         for(View var2 = this.mWindow.getDecorView(); var1 != null; var1 = var1.getParent()) {
            if (var1 == var2 || !(var1 instanceof View) || ViewCompat.isAttachedToWindow((View)var1)) {
               return false;
            }
         }

         return true;
      }
   }

   private void throwFeatureRequestIfSubDecorInstalled() {
      if (this.mSubDecorInstalled) {
         throw new AndroidRuntimeException("Window feature must be requested before adding content");
      }
   }

   private AppCompatActivity tryUnwrapContext() {
      for(Context var1 = this.mContext; var1 != null; var1 = ((ContextWrapper)var1).getBaseContext()) {
         if (var1 instanceof AppCompatActivity) {
            return (AppCompatActivity)var1;
         }

         if (!(var1 instanceof ContextWrapper)) {
            break;
         }
      }

      return null;
   }

   private boolean updateForNightMode(int var1, boolean var2) {
      Configuration var9 = this.createOverrideConfigurationForDayNight(this.mContext, var1, (Configuration)null);
      boolean var6 = this.isActivityManifestHandlingUiMode();
      Configuration var8 = this.mEffectiveConfiguration;
      Configuration var7 = var8;
      if (var8 == null) {
         var7 = this.mContext.getResources().getConfiguration();
      }

      int var3;
      int var4;
      boolean var5;
      Object var10;
      label41: {
         var3 = var7.uiMode & 48;
         var4 = var9.uiMode & 48;
         var5 = true;
         if (var3 != var4 && var2 && !var6 && this.mBaseContextAttached && (sCanReturnDifferentContext || this.mCreated)) {
            var10 = this.mHost;
            if (var10 instanceof Activity && !((Activity)var10).isChild()) {
               ActivityCompat.recreate((Activity)this.mHost);
               var2 = true;
               break label41;
            }
         }

         var2 = false;
      }

      if (!var2 && var3 != var4) {
         this.updateResourcesConfigurationForNightMode(var4, var6, (Configuration)null);
         var2 = var5;
      }

      if (var2) {
         var10 = this.mHost;
         if (var10 instanceof AppCompatActivity) {
            ((AppCompatActivity)var10).onNightModeChanged(var1);
         }
      }

      return var2;
   }

   private void updateResourcesConfigurationForNightMode(int var1, boolean var2, Configuration var3) {
      Resources var5 = this.mContext.getResources();
      Configuration var4 = new Configuration(var5.getConfiguration());
      if (var3 != null) {
         var4.updateFrom(var3);
      }

      var4.uiMode = var1 | var5.getConfiguration().uiMode & -49;
      var5.updateConfiguration(var4, (DisplayMetrics)null);
      if (VERSION.SDK_INT < 26) {
         ResourcesFlusher.flush(var5);
      }

      var1 = this.mThemeResId;
      if (var1 != 0) {
         this.mContext.setTheme(var1);
         if (VERSION.SDK_INT >= 23) {
            this.mContext.getTheme().applyStyle(this.mThemeResId, true);
         }
      }

      if (var2) {
         Object var6 = this.mHost;
         if (var6 instanceof Activity) {
            Activity var7 = (Activity)var6;
            if (var7 instanceof LifecycleOwner) {
               if (((LifecycleOwner)var7).getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.CREATED)) {
                  var7.onConfigurationChanged(var4);
               }
            } else if (this.mCreated && !this.mDestroyed) {
               var7.onConfigurationChanged(var4);
            }
         }
      }

   }

   private void updateStatusGuardColor(View var1) {
      boolean var2;
      if ((ViewCompat.getWindowSystemUiVisibility(var1) & 8192) != 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      int var3;
      if (var2) {
         var3 = ContextCompat.getColor(this.mContext, R.color.abc_decor_view_status_guard_light);
      } else {
         var3 = ContextCompat.getColor(this.mContext, R.color.abc_decor_view_status_guard);
      }

      var1.setBackgroundColor(var3);
   }

   public void addContentView(View var1, ViewGroup.LayoutParams var2) {
      this.ensureSubDecor();
      ((ViewGroup)this.mSubDecor.findViewById(16908290)).addView(var1, var2);
      this.mAppCompatWindowCallback.getWrapped().onContentChanged();
   }

   public boolean applyDayNight() {
      return this.applyDayNight(true);
   }

   public Context attachBaseContext2(Context var1) {
      boolean var2 = true;
      this.mBaseContextAttached = true;
      int var3 = this.mapNightMode(var1, this.calculateNightMode());
      boolean var4 = sCanApplyOverrideConfiguration;
      Configuration var6 = null;
      Configuration var5;
      if (var4 && var1 instanceof android.view.ContextThemeWrapper) {
         var5 = this.createOverrideConfigurationForDayNight(var1, var3, (Configuration)null);

         try {
            ((android.view.ContextThemeWrapper)var1).applyOverrideConfiguration(var5);
            return var1;
         } catch (IllegalStateException var11) {
         }
      }

      if (var1 instanceof ContextThemeWrapper) {
         var5 = this.createOverrideConfigurationForDayNight(var1, var3, (Configuration)null);

         try {
            ((ContextThemeWrapper)var1).applyOverrideConfiguration(var5);
            return var1;
         } catch (IllegalStateException var10) {
         }
      }

      if (!sCanReturnDifferentContext) {
         return super.attachBaseContext2(var1);
      } else {
         var5 = var6;
         if (VERSION.SDK_INT >= 17) {
            var5 = new Configuration();
            var5.uiMode = -1;
            var5.fontScale = 0.0F;
            Configuration var7 = var1.createConfigurationContext(var5).getResources().getConfiguration();
            Configuration var8 = var1.getResources().getConfiguration();
            var7.uiMode = var8.uiMode;
            var5 = var6;
            if (!var7.equals(var8)) {
               var5 = generateConfigDelta(var7, var8);
            }
         }

         var6 = this.createOverrideConfigurationForDayNight(var1, var3, var5);
         ContextThemeWrapper var14 = new ContextThemeWrapper(var1, R.style.Theme_AppCompat_Empty);
         var14.applyOverrideConfiguration(var6);
         boolean var13 = false;

         label40: {
            Resources.Theme var12;
            try {
               var12 = var1.getTheme();
            } catch (NullPointerException var9) {
               var2 = var13;
               break label40;
            }

            if (var12 == null) {
               var2 = false;
            }
         }

         if (var2) {
            ResourcesCompat.ThemeCompat.rebase(var14.getTheme());
         }

         return super.attachBaseContext2(var14);
      }
   }

   void callOnPanelClosed(int var1, PanelFeatureState var2, Menu var3) {
      PanelFeatureState var5 = var2;
      Object var6 = var3;
      if (var3 == null) {
         PanelFeatureState var4 = var2;
         if (var2 == null) {
            var4 = var2;
            if (var1 >= 0) {
               PanelFeatureState[] var7 = this.mPanels;
               var4 = var2;
               if (var1 < var7.length) {
                  var4 = var7[var1];
               }
            }
         }

         var5 = var4;
         var6 = var3;
         if (var4 != null) {
            var6 = var4.menu;
            var5 = var4;
         }
      }

      if (var5 == null || var5.isOpen) {
         if (!this.mDestroyed) {
            this.mAppCompatWindowCallback.getWrapped().onPanelClosed(var1, (Menu)var6);
         }

      }
   }

   void checkCloseActionMenu(MenuBuilder var1) {
      if (!this.mClosingActionMenu) {
         this.mClosingActionMenu = true;
         this.mDecorContentParent.dismissPopups();
         Window.Callback var2 = this.getWindowCallback();
         if (var2 != null && !this.mDestroyed) {
            var2.onPanelClosed(108, var1);
         }

         this.mClosingActionMenu = false;
      }
   }

   void closePanel(int var1) {
      this.closePanel(this.getPanelState(var1, true), true);
   }

   void closePanel(PanelFeatureState var1, boolean var2) {
      if (var2 && var1.featureId == 0) {
         DecorContentParent var3 = this.mDecorContentParent;
         if (var3 != null && var3.isOverflowMenuShowing()) {
            this.checkCloseActionMenu(var1.menu);
            return;
         }
      }

      WindowManager var4 = (WindowManager)this.mContext.getSystemService("window");
      if (var4 != null && var1.isOpen && var1.decorView != null) {
         var4.removeView(var1.decorView);
         if (var2) {
            this.callOnPanelClosed(var1.featureId, var1, (Menu)null);
         }
      }

      var1.isPrepared = false;
      var1.isHandled = false;
      var1.isOpen = false;
      var1.shownPanelView = null;
      var1.refreshDecorView = true;
      if (this.mPreparedPanel == var1) {
         this.mPreparedPanel = null;
      }

   }

   public View createView(View var1, String var2, Context var3, AttributeSet var4) {
      AppCompatViewInflater var9 = this.mAppCompatViewInflater;
      boolean var7 = false;
      if (var9 == null) {
         String var13 = this.mContext.obtainStyledAttributes(R.styleable.AppCompatTheme).getString(R.styleable.AppCompatTheme_viewInflaterClass);
         if (var13 == null) {
            this.mAppCompatViewInflater = new AppCompatViewInflater();
         } else {
            label61:
            try {
               this.mAppCompatViewInflater = (AppCompatViewInflater)this.mContext.getClassLoader().loadClass(var13).getDeclaredConstructor().newInstance();
            } catch (Throwable var12) {
               Log.i("AppCompatDelegate", "Failed to instantiate custom view inflater " + var13 + ". Falling back to default.", var12);
               this.mAppCompatViewInflater = new AppCompatViewInflater();
               break label61;
            }
         }
      }

      boolean var8 = IS_PRE_LOLLIPOP;
      boolean var6 = true;
      boolean var5 = var7;
      if (var8) {
         if (this.mLayoutIncludeDetector == null) {
            this.mLayoutIncludeDetector = new LayoutIncludeDetector();
         }

         if (this.mLayoutIncludeDetector.detect(var4)) {
            var5 = true;
         } else if (var4 instanceof XmlPullParser) {
            var5 = var7;
            if (((XmlPullParser)var4).getDepth() > 1) {
               var5 = var6;
            }
         } else {
            var5 = this.shouldInheritContext((ViewParent)var1);
         }
      }

      return this.mAppCompatViewInflater.createView(var1, var2, var3, var4, var5, var8, true, VectorEnabledTintResources.shouldBeUsed());
   }

   void dismissPopups() {
      DecorContentParent var1 = this.mDecorContentParent;
      if (var1 != null) {
         var1.dismissPopups();
      }

      if (this.mActionModePopup != null) {
         this.mWindow.getDecorView().removeCallbacks(this.mShowActionModePopup);
         if (this.mActionModePopup.isShowing()) {
            try {
               this.mActionModePopup.dismiss();
            } catch (IllegalArgumentException var2) {
            }
         }

         this.mActionModePopup = null;
      }

      this.endOnGoingFadeAnimation();
      PanelFeatureState var3 = this.getPanelState(0, false);
      if (var3 != null && var3.menu != null) {
         var3.menu.close();
      }

   }

   boolean dispatchKeyEvent(KeyEvent var1) {
      Object var5 = this.mHost;
      boolean var4 = var5 instanceof KeyEventDispatcher.Component;
      boolean var2 = true;
      if (var4 || var5 instanceof AppCompatDialog) {
         View var6 = this.mWindow.getDecorView();
         if (var6 != null && KeyEventDispatcher.dispatchBeforeHierarchy(var6, var1)) {
            return true;
         }
      }

      if (var1.getKeyCode() == 82 && this.mAppCompatWindowCallback.getWrapped().dispatchKeyEvent(var1)) {
         return true;
      } else {
         int var3 = var1.getKeyCode();
         if (var1.getAction() != 0) {
            var2 = false;
         }

         if (var2) {
            var4 = this.onKeyDown(var3, var1);
         } else {
            var4 = this.onKeyUp(var3, var1);
         }

         return var4;
      }
   }

   void doInvalidatePanelMenu(int var1) {
      PanelFeatureState var3 = this.getPanelState(var1, true);
      if (var3.menu != null) {
         Bundle var2 = new Bundle();
         var3.menu.saveActionViewStates(var2);
         if (var2.size() > 0) {
            var3.frozenActionViewState = var2;
         }

         var3.menu.stopDispatchingItemsChanged();
         var3.menu.clear();
      }

      var3.refreshMenuContent = true;
      var3.refreshDecorView = true;
      if ((var1 == 108 || var1 == 0) && this.mDecorContentParent != null) {
         PanelFeatureState var4 = this.getPanelState(0, false);
         if (var4 != null) {
            var4.isPrepared = false;
            this.preparePanel(var4, (KeyEvent)null);
         }
      }

   }

   void endOnGoingFadeAnimation() {
      ViewPropertyAnimatorCompat var1 = this.mFadeAnim;
      if (var1 != null) {
         var1.cancel();
      }

   }

   PanelFeatureState findMenuPanel(Menu var1) {
      PanelFeatureState[] var5 = this.mPanels;
      int var3 = 0;
      int var2;
      if (var5 != null) {
         var2 = var5.length;
      } else {
         var2 = 0;
      }

      while(var3 < var2) {
         PanelFeatureState var4 = var5[var3];
         if (var4 != null && var4.menu == var1) {
            return var4;
         }

         ++var3;
      }

      return null;
   }

   public View findViewById(int var1) {
      this.ensureSubDecor();
      return this.mWindow.findViewById(var1);
   }

   final Context getActionBarThemedContext() {
      ActionBar var1 = this.getSupportActionBar();
      Context var3;
      if (var1 != null) {
         var3 = var1.getThemedContext();
      } else {
         var3 = null;
      }

      Context var2 = var3;
      if (var3 == null) {
         var2 = this.mContext;
      }

      return var2;
   }

   final AutoNightModeManager getAutoTimeNightModeManager() {
      return this.getAutoTimeNightModeManager(this.mContext);
   }

   public final ActionBarDrawerToggle.Delegate getDrawerToggleDelegate() {
      return new ActionBarDrawableToggleImpl(this);
   }

   public int getLocalNightMode() {
      return this.mLocalNightMode;
   }

   public MenuInflater getMenuInflater() {
      if (this.mMenuInflater == null) {
         this.initWindowDecorActionBar();
         ActionBar var1 = this.mActionBar;
         Context var2;
         if (var1 != null) {
            var2 = var1.getThemedContext();
         } else {
            var2 = this.mContext;
         }

         this.mMenuInflater = new SupportMenuInflater(var2);
      }

      return this.mMenuInflater;
   }

   protected PanelFeatureState getPanelState(int var1, boolean var2) {
      PanelFeatureState[] var3;
      label22: {
         PanelFeatureState[] var4 = this.mPanels;
         if (var4 != null) {
            var3 = var4;
            if (var4.length > var1) {
               break label22;
            }
         }

         var3 = new PanelFeatureState[var1 + 1];
         if (var4 != null) {
            System.arraycopy(var4, 0, var3, 0, var4.length);
         }

         this.mPanels = var3;
      }

      PanelFeatureState var5 = var3[var1];
      PanelFeatureState var6 = var5;
      if (var5 == null) {
         var6 = new PanelFeatureState(var1);
         var3[var1] = var6;
      }

      return var6;
   }

   ViewGroup getSubDecor() {
      return this.mSubDecor;
   }

   public ActionBar getSupportActionBar() {
      this.initWindowDecorActionBar();
      return this.mActionBar;
   }

   final CharSequence getTitle() {
      Object var1 = this.mHost;
      return var1 instanceof Activity ? ((Activity)var1).getTitle() : this.mTitle;
   }

   final Window.Callback getWindowCallback() {
      return this.mWindow.getCallback();
   }

   public boolean hasWindowFeature(int var1) {
      int var2 = this.sanitizeWindowFeatureId(var1);
      boolean var5 = true;
      boolean var3;
      if (var2 != 1) {
         if (var2 != 2) {
            if (var2 != 5) {
               if (var2 != 10) {
                  if (var2 != 108) {
                     if (var2 != 109) {
                        var3 = false;
                     } else {
                        var3 = this.mOverlayActionBar;
                     }
                  } else {
                     var3 = this.mHasActionBar;
                  }
               } else {
                  var3 = this.mOverlayActionMode;
               }
            } else {
               var3 = this.mFeatureIndeterminateProgress;
            }
         } else {
            var3 = this.mFeatureProgress;
         }
      } else {
         var3 = this.mWindowNoTitle;
      }

      boolean var4 = var5;
      if (!var3) {
         if (this.mWindow.hasFeature(var1)) {
            var4 = var5;
         } else {
            var4 = false;
         }
      }

      return var4;
   }

   public void installViewFactory() {
      LayoutInflater var1 = LayoutInflater.from(this.mContext);
      if (var1.getFactory() == null) {
         LayoutInflaterCompat.setFactory2(var1, this);
      } else if (!(var1.getFactory2() instanceof AppCompatDelegateImpl)) {
         Log.i("AppCompatDelegate", "The Activity's LayoutInflater already has a Factory installed so we can not install AppCompat's");
      }

   }

   public void invalidateOptionsMenu() {
      ActionBar var1 = this.getSupportActionBar();
      if (var1 == null || !var1.invalidateOptionsMenu()) {
         this.invalidatePanelMenu(0);
      }
   }

   public boolean isHandleNativeActionModesEnabled() {
      return this.mHandleNativeActionModes;
   }

   int mapNightMode(Context var1, int var2) {
      if (var2 != -100) {
         if (var2 != -1) {
            if (var2 == 0) {
               if (VERSION.SDK_INT >= 23 && ((UiModeManager)var1.getApplicationContext().getSystemService("uimode")).getNightMode() == 0) {
                  return -1;
               }

               return this.getAutoTimeNightModeManager(var1).getApplyableNightMode();
            }

            if (var2 != 1 && var2 != 2) {
               if (var2 == 3) {
                  return this.getAutoBatteryNightModeManager(var1).getApplyableNightMode();
               }

               throw new IllegalStateException("Unknown value set for night mode. Please use one of the MODE_NIGHT values from AppCompatDelegate.");
            }
         }

         return var2;
      } else {
         return -1;
      }
   }

   boolean onBackPressed() {
      ActionMode var1 = this.mActionMode;
      if (var1 != null) {
         var1.finish();
         return true;
      } else {
         ActionBar var2 = this.getSupportActionBar();
         return var2 != null && var2.collapseActionView();
      }
   }

   public void onConfigurationChanged(Configuration var1) {
      if (this.mHasActionBar && this.mSubDecorInstalled) {
         ActionBar var2 = this.getSupportActionBar();
         if (var2 != null) {
            var2.onConfigurationChanged(var1);
         }
      }

      AppCompatDrawableManager.get().onConfigurationChanged(this.mContext);
      this.mEffectiveConfiguration = new Configuration(this.mContext.getResources().getConfiguration());
      this.applyDayNight(false);
   }

   public void onCreate(Bundle var1) {
      this.mBaseContextAttached = true;
      this.applyDayNight(false);
      this.ensureWindow();
      Object var2 = this.mHost;
      if (var2 instanceof Activity) {
         String var4 = null;

         label22: {
            String var5;
            try {
               var5 = NavUtils.getParentActivityName((Activity)var2);
            } catch (IllegalArgumentException var3) {
               break label22;
            }

            var4 = var5;
         }

         if (var4 != null) {
            ActionBar var6 = this.peekSupportActionBar();
            if (var6 == null) {
               this.mEnableDefaultActionBarUp = true;
            } else {
               var6.setDefaultDisplayHomeAsUpEnabled(true);
            }
         }

         addActiveDelegate(this);
      }

      this.mEffectiveConfiguration = new Configuration(this.mContext.getResources().getConfiguration());
      this.mCreated = true;
   }

   public final View onCreateView(View var1, String var2, Context var3, AttributeSet var4) {
      return this.createView(var1, var2, var3, var4);
   }

   public View onCreateView(String var1, Context var2, AttributeSet var3) {
      return this.onCreateView((View)null, var1, var2, var3);
   }

   public void onDestroy() {
      if (this.mHost instanceof Activity) {
         removeActivityDelegate(this);
      }

      if (this.mInvalidatePanelMenuPosted) {
         this.mWindow.getDecorView().removeCallbacks(this.mInvalidatePanelMenuRunnable);
      }

      label23: {
         this.mDestroyed = true;
         if (this.mLocalNightMode != -100) {
            Object var1 = this.mHost;
            if (var1 instanceof Activity && ((Activity)var1).isChangingConfigurations()) {
               sLocalNightModes.put(this.mHost.getClass().getName(), this.mLocalNightMode);
               break label23;
            }
         }

         sLocalNightModes.remove(this.mHost.getClass().getName());
      }

      ActionBar var2 = this.mActionBar;
      if (var2 != null) {
         var2.onDestroy();
      }

      this.cleanupAutoManagers();
   }

   boolean onKeyDown(int var1, KeyEvent var2) {
      boolean var3 = true;
      if (var1 != 4) {
         if (var1 == 82) {
            this.onKeyDownPanel(0, var2);
            return true;
         }
      } else {
         if ((var2.getFlags() & 128) == 0) {
            var3 = false;
         }

         this.mLongPressBackDown = var3;
      }

      return false;
   }

   boolean onKeyShortcut(int var1, KeyEvent var2) {
      ActionBar var4 = this.getSupportActionBar();
      if (var4 != null && var4.onKeyShortcut(var1, var2)) {
         return true;
      } else {
         PanelFeatureState var6 = this.mPreparedPanel;
         if (var6 != null && this.performPanelShortcut(var6, var2.getKeyCode(), var2, 1)) {
            PanelFeatureState var5 = this.mPreparedPanel;
            if (var5 != null) {
               var5.isHandled = true;
            }

            return true;
         } else {
            if (this.mPreparedPanel == null) {
               var6 = this.getPanelState(0, true);
               this.preparePanel(var6, var2);
               boolean var3 = this.performPanelShortcut(var6, var2.getKeyCode(), var2, 1);
               var6.isPrepared = false;
               if (var3) {
                  return true;
               }
            }

            return false;
         }
      }
   }

   boolean onKeyUp(int var1, KeyEvent var2) {
      if (var1 != 4) {
         if (var1 == 82) {
            this.onKeyUpPanel(0, var2);
            return true;
         }
      } else {
         boolean var3 = this.mLongPressBackDown;
         this.mLongPressBackDown = false;
         PanelFeatureState var4 = this.getPanelState(0, false);
         if (var4 != null && var4.isOpen) {
            if (!var3) {
               this.closePanel(var4, true);
            }

            return true;
         }

         if (this.onBackPressed()) {
            return true;
         }
      }

      return false;
   }

   public boolean onMenuItemSelected(MenuBuilder var1, MenuItem var2) {
      Window.Callback var3 = this.getWindowCallback();
      if (var3 != null && !this.mDestroyed) {
         PanelFeatureState var4 = this.findMenuPanel(var1.getRootMenu());
         if (var4 != null) {
            return var3.onMenuItemSelected(var4.featureId, var2);
         }
      }

      return false;
   }

   public void onMenuModeChange(MenuBuilder var1) {
      this.reopenMenu(true);
   }

   void onMenuOpened(int var1) {
      if (var1 == 108) {
         ActionBar var2 = this.getSupportActionBar();
         if (var2 != null) {
            var2.dispatchMenuVisibilityChanged(true);
         }
      }

   }

   void onPanelClosed(int var1) {
      if (var1 == 108) {
         ActionBar var2 = this.getSupportActionBar();
         if (var2 != null) {
            var2.dispatchMenuVisibilityChanged(false);
         }
      } else if (var1 == 0) {
         PanelFeatureState var3 = this.getPanelState(var1, true);
         if (var3.isOpen) {
            this.closePanel(var3, false);
         }
      }

   }

   public void onPostCreate(Bundle var1) {
      this.ensureSubDecor();
   }

   public void onPostResume() {
      ActionBar var1 = this.getSupportActionBar();
      if (var1 != null) {
         var1.setShowHideAnimationEnabled(true);
      }

   }

   public void onSaveInstanceState(Bundle var1) {
   }

   public void onStart() {
      this.applyDayNight();
   }

   public void onStop() {
      ActionBar var1 = this.getSupportActionBar();
      if (var1 != null) {
         var1.setShowHideAnimationEnabled(false);
      }

   }

   void onSubDecorInstalled(ViewGroup var1) {
   }

   final ActionBar peekSupportActionBar() {
      return this.mActionBar;
   }

   public boolean requestWindowFeature(int var1) {
      var1 = this.sanitizeWindowFeatureId(var1);
      if (this.mWindowNoTitle && var1 == 108) {
         return false;
      } else {
         if (this.mHasActionBar && var1 == 1) {
            this.mHasActionBar = false;
         }

         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 5) {
                  if (var1 != 10) {
                     if (var1 != 108) {
                        if (var1 != 109) {
                           return this.mWindow.requestFeature(var1);
                        } else {
                           this.throwFeatureRequestIfSubDecorInstalled();
                           this.mOverlayActionBar = true;
                           return true;
                        }
                     } else {
                        this.throwFeatureRequestIfSubDecorInstalled();
                        this.mHasActionBar = true;
                        return true;
                     }
                  } else {
                     this.throwFeatureRequestIfSubDecorInstalled();
                     this.mOverlayActionMode = true;
                     return true;
                  }
               } else {
                  this.throwFeatureRequestIfSubDecorInstalled();
                  this.mFeatureIndeterminateProgress = true;
                  return true;
               }
            } else {
               this.throwFeatureRequestIfSubDecorInstalled();
               this.mFeatureProgress = true;
               return true;
            }
         } else {
            this.throwFeatureRequestIfSubDecorInstalled();
            this.mWindowNoTitle = true;
            return true;
         }
      }
   }

   public void setContentView(int var1) {
      this.ensureSubDecor();
      ViewGroup var2 = (ViewGroup)this.mSubDecor.findViewById(16908290);
      var2.removeAllViews();
      LayoutInflater.from(this.mContext).inflate(var1, var2);
      this.mAppCompatWindowCallback.getWrapped().onContentChanged();
   }

   public void setContentView(View var1) {
      this.ensureSubDecor();
      ViewGroup var2 = (ViewGroup)this.mSubDecor.findViewById(16908290);
      var2.removeAllViews();
      var2.addView(var1);
      this.mAppCompatWindowCallback.getWrapped().onContentChanged();
   }

   public void setContentView(View var1, ViewGroup.LayoutParams var2) {
      this.ensureSubDecor();
      ViewGroup var3 = (ViewGroup)this.mSubDecor.findViewById(16908290);
      var3.removeAllViews();
      var3.addView(var1, var2);
      this.mAppCompatWindowCallback.getWrapped().onContentChanged();
   }

   public void setHandleNativeActionModesEnabled(boolean var1) {
      this.mHandleNativeActionModes = var1;
   }

   public void setLocalNightMode(int var1) {
      if (this.mLocalNightMode != var1) {
         this.mLocalNightMode = var1;
         if (this.mBaseContextAttached) {
            this.applyDayNight();
         }
      }

   }

   public void setSupportActionBar(Toolbar var1) {
      if (this.mHost instanceof Activity) {
         ActionBar var2 = this.getSupportActionBar();
         if (!(var2 instanceof WindowDecorActionBar)) {
            this.mMenuInflater = null;
            if (var2 != null) {
               var2.onDestroy();
            }

            this.mActionBar = null;
            if (var1 != null) {
               ToolbarActionBar var3 = new ToolbarActionBar(var1, this.getTitle(), this.mAppCompatWindowCallback);
               this.mActionBar = var3;
               this.mAppCompatWindowCallback.setActionBarCallback(var3.mMenuCallback);
            } else {
               this.mAppCompatWindowCallback.setActionBarCallback((ActionBarMenuCallback)null);
            }

            this.invalidateOptionsMenu();
         } else {
            throw new IllegalStateException("This Activity already has an action bar supplied by the window decor. Do not request Window.FEATURE_SUPPORT_ACTION_BAR and set windowActionBar to false in your theme to use a Toolbar instead.");
         }
      }
   }

   public void setTheme(int var1) {
      this.mThemeResId = var1;
   }

   public final void setTitle(CharSequence var1) {
      this.mTitle = var1;
      DecorContentParent var2 = this.mDecorContentParent;
      if (var2 != null) {
         var2.setWindowTitle(var1);
      } else if (this.peekSupportActionBar() != null) {
         this.peekSupportActionBar().setWindowTitle(var1);
      } else {
         TextView var3 = this.mTitleView;
         if (var3 != null) {
            var3.setText(var1);
         }
      }

   }

   final boolean shouldAnimateActionModeView() {
      boolean var1;
      if (this.mSubDecorInstalled) {
         ViewGroup var2 = this.mSubDecor;
         if (var2 != null && ViewCompat.isLaidOut(var2)) {
            var1 = true;
            return var1;
         }
      }

      var1 = false;
      return var1;
   }

   public ActionMode startSupportActionMode(ActionMode.Callback var1) {
      if (var1 != null) {
         ActionMode var2 = this.mActionMode;
         if (var2 != null) {
            var2.finish();
         }

         ActionModeCallbackWrapperV9 var4 = new ActionModeCallbackWrapperV9(this, var1);
         ActionBar var5 = this.getSupportActionBar();
         if (var5 != null) {
            ActionMode var3 = var5.startActionMode(var4);
            this.mActionMode = var3;
            if (var3 != null) {
               AppCompatCallback var6 = this.mAppCompatCallback;
               if (var6 != null) {
                  var6.onSupportActionModeStarted(var3);
               }
            }
         }

         if (this.mActionMode == null) {
            this.mActionMode = this.startSupportActionModeFromWindow(var4);
         }

         return this.mActionMode;
      } else {
         throw new IllegalArgumentException("ActionMode callback can not be null.");
      }
   }

   ActionMode startSupportActionModeFromWindow(ActionMode.Callback var1) {
      this.endOnGoingFadeAnimation();
      ActionMode var4 = this.mActionMode;
      if (var4 != null) {
         var4.finish();
      }

      Object var13 = var1;
      if (!(var1 instanceof ActionModeCallbackWrapperV9)) {
         var13 = new ActionModeCallbackWrapperV9(this, var1);
      }

      AppCompatCallback var8;
      ActionMode var9;
      label67: {
         var8 = this.mAppCompatCallback;
         if (var8 != null && !this.mDestroyed) {
            try {
               var9 = var8.onWindowStartingSupportActionMode((ActionMode.Callback)var13);
               break label67;
            } catch (AbstractMethodError var7) {
            }
         }

         var9 = null;
      }

      if (var9 != null) {
         this.mActionMode = var9;
      } else {
         ActionBarContextView var10 = this.mActionModeView;
         boolean var3 = true;
         if (var10 == null) {
            if (this.mIsFloating) {
               TypedValue var5 = new TypedValue();
               Resources.Theme var11 = this.mContext.getTheme();
               var11.resolveAttribute(R.attr.actionBarTheme, var5, true);
               Object var12;
               if (var5.resourceId != 0) {
                  Resources.Theme var6 = this.mContext.getResources().newTheme();
                  var6.setTo(var11);
                  var6.applyStyle(var5.resourceId, true);
                  var12 = new ContextThemeWrapper(this.mContext, 0);
                  ((Context)var12).getTheme().setTo(var6);
               } else {
                  var12 = this.mContext;
               }

               this.mActionModeView = new ActionBarContextView((Context)var12);
               PopupWindow var19 = new PopupWindow((Context)var12, (AttributeSet)null, R.attr.actionModePopupWindowStyle);
               this.mActionModePopup = var19;
               PopupWindowCompat.setWindowLayoutType(var19, 2);
               this.mActionModePopup.setContentView(this.mActionModeView);
               this.mActionModePopup.setWidth(-1);
               ((Context)var12).getTheme().resolveAttribute(R.attr.actionBarSize, var5, true);
               int var2 = TypedValue.complexToDimensionPixelSize(var5.data, ((Context)var12).getResources().getDisplayMetrics());
               this.mActionModeView.setContentHeight(var2);
               this.mActionModePopup.setHeight(-2);
               this.mShowActionModePopup = new Runnable(this) {
                  final AppCompatDelegateImpl this$0;

                  {
                     this.this$0 = var1;
                  }

                  public void run() {
                     this.this$0.mActionModePopup.showAtLocation(this.this$0.mActionModeView, 55, 0, 0);
                     this.this$0.endOnGoingFadeAnimation();
                     if (this.this$0.shouldAnimateActionModeView()) {
                        this.this$0.mActionModeView.setAlpha(0.0F);
                        AppCompatDelegateImpl var1 = this.this$0;
                        var1.mFadeAnim = ViewCompat.animate(var1.mActionModeView).alpha(1.0F);
                        this.this$0.mFadeAnim.setListener(new ViewPropertyAnimatorListenerAdapter(this) {
                           final <undefinedtype> this$1;

                           {
                              this.this$1 = var1;
                           }

                           public void onAnimationEnd(View var1) {
                              this.this$1.this$0.mActionModeView.setAlpha(1.0F);
                              this.this$1.this$0.mFadeAnim.setListener((ViewPropertyAnimatorListener)null);
                              this.this$1.this$0.mFadeAnim = null;
                           }

                           public void onAnimationStart(View var1) {
                              this.this$1.this$0.mActionModeView.setVisibility(0);
                           }
                        });
                     } else {
                        this.this$0.mActionModeView.setAlpha(1.0F);
                        this.this$0.mActionModeView.setVisibility(0);
                     }

                  }
               };
            } else {
               ViewStubCompat var14 = (ViewStubCompat)this.mSubDecor.findViewById(R.id.action_mode_bar_stub);
               if (var14 != null) {
                  var14.setLayoutInflater(LayoutInflater.from(this.getActionBarThemedContext()));
                  this.mActionModeView = (ActionBarContextView)var14.inflate();
               }
            }
         }

         if (this.mActionModeView != null) {
            this.endOnGoingFadeAnimation();
            this.mActionModeView.killMode();
            Context var15 = this.mActionModeView.getContext();
            ActionBarContextView var17 = this.mActionModeView;
            if (this.mActionModePopup != null) {
               var3 = false;
            }

            StandaloneActionMode var16 = new StandaloneActionMode(var15, var17, (ActionMode.Callback)var13, var3);
            if (((ActionMode.Callback)var13).onCreateActionMode(var16, var16.getMenu())) {
               var16.invalidate();
               this.mActionModeView.initForMode(var16);
               this.mActionMode = var16;
               if (this.shouldAnimateActionModeView()) {
                  this.mActionModeView.setAlpha(0.0F);
                  ViewPropertyAnimatorCompat var18 = ViewCompat.animate(this.mActionModeView).alpha(1.0F);
                  this.mFadeAnim = var18;
                  var18.setListener(new ViewPropertyAnimatorListenerAdapter(this) {
                     final AppCompatDelegateImpl this$0;

                     {
                        this.this$0 = var1;
                     }

                     public void onAnimationEnd(View var1) {
                        this.this$0.mActionModeView.setAlpha(1.0F);
                        this.this$0.mFadeAnim.setListener((ViewPropertyAnimatorListener)null);
                        this.this$0.mFadeAnim = null;
                     }

                     public void onAnimationStart(View var1) {
                        this.this$0.mActionModeView.setVisibility(0);
                        if (this.this$0.mActionModeView.getParent() instanceof View) {
                           ViewCompat.requestApplyInsets((View)this.this$0.mActionModeView.getParent());
                        }

                     }
                  });
               } else {
                  this.mActionModeView.setAlpha(1.0F);
                  this.mActionModeView.setVisibility(0);
                  if (this.mActionModeView.getParent() instanceof View) {
                     ViewCompat.requestApplyInsets((View)this.mActionModeView.getParent());
                  }
               }

               if (this.mActionModePopup != null) {
                  this.mWindow.getDecorView().post(this.mShowActionModePopup);
               }
            } else {
               this.mActionMode = null;
            }
         }
      }

      var4 = this.mActionMode;
      if (var4 != null) {
         var8 = this.mAppCompatCallback;
         if (var8 != null) {
            var8.onSupportActionModeStarted(var4);
         }
      }

      return this.mActionMode;
   }

   final int updateStatusGuard(WindowInsetsCompat var1, Rect var2) {
      byte var8 = 0;
      int var3;
      if (var1 != null) {
         var3 = var1.getSystemWindowInsetTop();
      } else if (var2 != null) {
         var3 = var2.top;
      } else {
         var3 = 0;
      }

      ActionBarContextView var12 = this.mActionModeView;
      int var6;
      boolean var7;
      View var15;
      if (var12 != null && var12.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
         ViewGroup.MarginLayoutParams var22 = (ViewGroup.MarginLayoutParams)this.mActionModeView.getLayoutParams();
         boolean var11 = this.mActionModeView.isShown();
         boolean var5 = true;
         var7 = true;
         boolean var18;
         if (!var11) {
            if (var22.topMargin != 0) {
               var22.topMargin = 0;
               var18 = false;
            } else {
               var18 = false;
               var5 = false;
            }
         } else {
            if (this.mTempRect1 == null) {
               this.mTempRect1 = new Rect();
               this.mTempRect2 = new Rect();
            }

            Rect var14 = this.mTempRect1;
            Rect var13 = this.mTempRect2;
            if (var1 == null) {
               var14.set(var2);
            } else {
               var14.set(var1.getSystemWindowInsetLeft(), var1.getSystemWindowInsetTop(), var1.getSystemWindowInsetRight(), var1.getSystemWindowInsetBottom());
            }

            ViewUtils.computeFitSystemWindows(this.mSubDecor, var14, var13);
            int var9 = var14.top;
            int var4 = var14.left;
            int var10 = var14.right;
            var1 = ViewCompat.getRootWindowInsets(this.mSubDecor);
            int var20;
            if (var1 == null) {
               var20 = 0;
            } else {
               var20 = var1.getSystemWindowInsetLeft();
            }

            if (var1 == null) {
               var6 = 0;
            } else {
               var6 = var1.getSystemWindowInsetRight();
            }

            if (var22.topMargin == var9 && var22.leftMargin == var4 && var22.rightMargin == var10) {
               var18 = false;
            } else {
               var22.topMargin = var9;
               var22.leftMargin = var4;
               var22.rightMargin = var10;
               var18 = true;
            }

            if (var9 > 0 && this.mStatusGuard == null) {
               var15 = new View(this.mContext);
               this.mStatusGuard = var15;
               var15.setVisibility(8);
               FrameLayout.LayoutParams var17 = new FrameLayout.LayoutParams(-1, var22.topMargin, 51);
               var17.leftMargin = var20;
               var17.rightMargin = var6;
               this.mSubDecor.addView(this.mStatusGuard, -1, var17);
            } else {
               var15 = this.mStatusGuard;
               if (var15 != null) {
                  ViewGroup.MarginLayoutParams var16 = (ViewGroup.MarginLayoutParams)var15.getLayoutParams();
                  if (var16.height != var22.topMargin || var16.leftMargin != var20 || var16.rightMargin != var6) {
                     var16.height = var22.topMargin;
                     var16.leftMargin = var20;
                     var16.rightMargin = var6;
                     this.mStatusGuard.setLayoutParams(var16);
                  }
               }
            }

            var15 = this.mStatusGuard;
            boolean var21;
            if (var15 != null) {
               var21 = var7;
            } else {
               var21 = false;
            }

            if (var21 && var15.getVisibility() != 0) {
               this.updateStatusGuardColor(this.mStatusGuard);
            }

            var20 = var3;
            if (!this.mOverlayActionMode) {
               var20 = var3;
               if (var21) {
                  var20 = 0;
               }
            }

            var3 = var20;
            var5 = var18;
            var18 = var21;
         }

         var6 = var3;
         var7 = var18;
         if (var5) {
            this.mActionModeView.setLayoutParams(var22);
            var6 = var3;
            var7 = var18;
         }
      } else {
         var7 = false;
         var6 = var3;
      }

      var15 = this.mStatusGuard;
      if (var15 != null) {
         byte var19;
         if (var7) {
            var19 = var8;
         } else {
            var19 = 8;
         }

         var15.setVisibility(var19);
      }

      return var6;
   }

   private class ActionBarDrawableToggleImpl implements ActionBarDrawerToggle.Delegate {
      final AppCompatDelegateImpl this$0;

      ActionBarDrawableToggleImpl(AppCompatDelegateImpl var1) {
         this.this$0 = var1;
      }

      public Context getActionBarThemedContext() {
         return this.this$0.getActionBarThemedContext();
      }

      public Drawable getThemeUpIndicator() {
         TintTypedArray var1 = TintTypedArray.obtainStyledAttributes(this.getActionBarThemedContext(), (AttributeSet)null, new int[]{R.attr.homeAsUpIndicator});
         Drawable var2 = var1.getDrawable(0);
         var1.recycle();
         return var2;
      }

      public boolean isNavigationVisible() {
         ActionBar var2 = this.this$0.getSupportActionBar();
         boolean var1;
         if (var2 != null && (var2.getDisplayOptions() & 4) != 0) {
            var1 = true;
         } else {
            var1 = false;
         }

         return var1;
      }

      public void setActionBarDescription(int var1) {
         ActionBar var2 = this.this$0.getSupportActionBar();
         if (var2 != null) {
            var2.setHomeActionContentDescription(var1);
         }

      }

      public void setActionBarUpIndicator(Drawable var1, int var2) {
         ActionBar var3 = this.this$0.getSupportActionBar();
         if (var3 != null) {
            var3.setHomeAsUpIndicator(var1);
            var3.setHomeActionContentDescription(var2);
         }

      }
   }

   interface ActionBarMenuCallback {
      View onCreatePanelView(int var1);

      boolean onPreparePanel(int var1);
   }

   private final class ActionMenuPresenterCallback implements MenuPresenter.Callback {
      final AppCompatDelegateImpl this$0;

      ActionMenuPresenterCallback(AppCompatDelegateImpl var1) {
         this.this$0 = var1;
      }

      public void onCloseMenu(MenuBuilder var1, boolean var2) {
         this.this$0.checkCloseActionMenu(var1);
      }

      public boolean onOpenSubMenu(MenuBuilder var1) {
         Window.Callback var2 = this.this$0.getWindowCallback();
         if (var2 != null) {
            var2.onMenuOpened(108, var1);
         }

         return true;
      }
   }

   class ActionModeCallbackWrapperV9 implements ActionMode.Callback {
      private ActionMode.Callback mWrapped;
      final AppCompatDelegateImpl this$0;

      public ActionModeCallbackWrapperV9(AppCompatDelegateImpl var1, ActionMode.Callback var2) {
         this.this$0 = var1;
         this.mWrapped = var2;
      }

      public boolean onActionItemClicked(ActionMode var1, MenuItem var2) {
         return this.mWrapped.onActionItemClicked(var1, var2);
      }

      public boolean onCreateActionMode(ActionMode var1, Menu var2) {
         return this.mWrapped.onCreateActionMode(var1, var2);
      }

      public void onDestroyActionMode(ActionMode var1) {
         this.mWrapped.onDestroyActionMode(var1);
         if (this.this$0.mActionModePopup != null) {
            this.this$0.mWindow.getDecorView().removeCallbacks(this.this$0.mShowActionModePopup);
         }

         if (this.this$0.mActionModeView != null) {
            this.this$0.endOnGoingFadeAnimation();
            AppCompatDelegateImpl var2 = this.this$0;
            var2.mFadeAnim = ViewCompat.animate(var2.mActionModeView).alpha(0.0F);
            this.this$0.mFadeAnim.setListener(new ViewPropertyAnimatorListenerAdapter(this) {
               final ActionModeCallbackWrapperV9 this$1;

               {
                  this.this$1 = var1;
               }

               public void onAnimationEnd(View var1) {
                  this.this$1.this$0.mActionModeView.setVisibility(8);
                  if (this.this$1.this$0.mActionModePopup != null) {
                     this.this$1.this$0.mActionModePopup.dismiss();
                  } else if (this.this$1.this$0.mActionModeView.getParent() instanceof View) {
                     ViewCompat.requestApplyInsets((View)this.this$1.this$0.mActionModeView.getParent());
                  }

                  this.this$1.this$0.mActionModeView.killMode();
                  this.this$1.this$0.mFadeAnim.setListener((ViewPropertyAnimatorListener)null);
                  this.this$1.this$0.mFadeAnim = null;
                  ViewCompat.requestApplyInsets(this.this$1.this$0.mSubDecor);
               }
            });
         }

         if (this.this$0.mAppCompatCallback != null) {
            this.this$0.mAppCompatCallback.onSupportActionModeFinished(this.this$0.mActionMode);
         }

         this.this$0.mActionMode = null;
         ViewCompat.requestApplyInsets(this.this$0.mSubDecor);
      }

      public boolean onPrepareActionMode(ActionMode var1, Menu var2) {
         ViewCompat.requestApplyInsets(this.this$0.mSubDecor);
         return this.mWrapped.onPrepareActionMode(var1, var2);
      }
   }

   static class Api17Impl {
      private Api17Impl() {
      }

      static Context createConfigurationContext(Context var0, Configuration var1) {
         return var0.createConfigurationContext(var1);
      }

      static void generateConfigDelta_densityDpi(Configuration var0, Configuration var1, Configuration var2) {
         if (var0.densityDpi != var1.densityDpi) {
            var2.densityDpi = var1.densityDpi;
         }

      }
   }

   static class Api21Impl {
      private Api21Impl() {
      }

      static boolean isPowerSaveMode(PowerManager var0) {
         return var0.isPowerSaveMode();
      }
   }

   static class Api24Impl {
      private Api24Impl() {
      }

      static void generateConfigDelta_locale(Configuration var0, Configuration var1, Configuration var2) {
         LocaleList var3 = var0.getLocales();
         LocaleList var4 = var1.getLocales();
         if (!var3.equals(var4)) {
            var2.setLocales(var4);
            var2.locale = var1.locale;
         }

      }
   }

   static class Api26Impl {
      private Api26Impl() {
      }

      static void generateConfigDelta_colorMode(Configuration var0, Configuration var1, Configuration var2) {
         if ((var0.colorMode & 3) != (var1.colorMode & 3)) {
            var2.colorMode |= var1.colorMode & 3;
         }

         if ((var0.colorMode & 12) != (var1.colorMode & 12)) {
            var2.colorMode |= var1.colorMode & 12;
         }

      }
   }

   class AppCompatWindowCallback extends WindowCallbackWrapper {
      private ActionBarMenuCallback mActionBarCallback;
      final AppCompatDelegateImpl this$0;

      AppCompatWindowCallback(AppCompatDelegateImpl var1, Window.Callback var2) {
         super(var2);
         this.this$0 = var1;
      }

      public boolean dispatchKeyEvent(KeyEvent var1) {
         boolean var2;
         if (!this.this$0.dispatchKeyEvent(var1) && !super.dispatchKeyEvent(var1)) {
            var2 = false;
         } else {
            var2 = true;
         }

         return var2;
      }

      public boolean dispatchKeyShortcutEvent(KeyEvent var1) {
         boolean var2;
         if (!super.dispatchKeyShortcutEvent(var1) && !this.this$0.onKeyShortcut(var1.getKeyCode(), var1)) {
            var2 = false;
         } else {
            var2 = true;
         }

         return var2;
      }

      public void onContentChanged() {
      }

      public boolean onCreatePanelMenu(int var1, Menu var2) {
         return var1 == 0 && !(var2 instanceof MenuBuilder) ? false : super.onCreatePanelMenu(var1, var2);
      }

      public View onCreatePanelView(int var1) {
         ActionBarMenuCallback var2 = this.mActionBarCallback;
         if (var2 != null) {
            View var3 = var2.onCreatePanelView(var1);
            if (var3 != null) {
               return var3;
            }
         }

         return super.onCreatePanelView(var1);
      }

      public boolean onMenuOpened(int var1, Menu var2) {
         super.onMenuOpened(var1, var2);
         this.this$0.onMenuOpened(var1);
         return true;
      }

      public void onPanelClosed(int var1, Menu var2) {
         super.onPanelClosed(var1, var2);
         this.this$0.onPanelClosed(var1);
      }

      public boolean onPreparePanel(int var1, View var2, Menu var3) {
         MenuBuilder var6;
         if (var3 instanceof MenuBuilder) {
            var6 = (MenuBuilder)var3;
         } else {
            var6 = null;
         }

         if (var1 == 0 && var6 == null) {
            return false;
         } else {
            boolean var4 = true;
            if (var6 != null) {
               var6.setOverrideVisibleItems(true);
            }

            ActionBarMenuCallback var7 = this.mActionBarCallback;
            if (var7 == null || !var7.onPreparePanel(var1)) {
               var4 = false;
            }

            boolean var5 = var4;
            if (!var4) {
               var5 = super.onPreparePanel(var1, var2, var3);
            }

            if (var6 != null) {
               var6.setOverrideVisibleItems(false);
            }

            return var5;
         }
      }

      public void onProvideKeyboardShortcuts(List var1, Menu var2, int var3) {
         PanelFeatureState var4 = this.this$0.getPanelState(0, true);
         if (var4 != null && var4.menu != null) {
            super.onProvideKeyboardShortcuts(var1, var4.menu, var3);
         } else {
            super.onProvideKeyboardShortcuts(var1, var2, var3);
         }

      }

      public android.view.ActionMode onWindowStartingActionMode(android.view.ActionMode.Callback var1) {
         if (VERSION.SDK_INT >= 23) {
            return null;
         } else {
            return this.this$0.isHandleNativeActionModesEnabled() ? this.startAsSupportActionMode(var1) : super.onWindowStartingActionMode(var1);
         }
      }

      public android.view.ActionMode onWindowStartingActionMode(android.view.ActionMode.Callback var1, int var2) {
         return this.this$0.isHandleNativeActionModesEnabled() && var2 == 0 ? this.startAsSupportActionMode(var1) : super.onWindowStartingActionMode(var1, var2);
      }

      void setActionBarCallback(ActionBarMenuCallback var1) {
         this.mActionBarCallback = var1;
      }

      final android.view.ActionMode startAsSupportActionMode(android.view.ActionMode.Callback var1) {
         SupportActionModeWrapper.CallbackWrapper var2 = new SupportActionModeWrapper.CallbackWrapper(this.this$0.mContext, var1);
         ActionMode var3 = this.this$0.startSupportActionMode(var2);
         return var3 != null ? var2.getActionModeWrapper(var3) : null;
      }
   }

   private class AutoBatteryNightModeManager extends AutoNightModeManager {
      private final PowerManager mPowerManager;
      final AppCompatDelegateImpl this$0;

      AutoBatteryNightModeManager(AppCompatDelegateImpl var1, Context var2) {
         super(var1);
         this.this$0 = var1;
         this.mPowerManager = (PowerManager)var2.getApplicationContext().getSystemService("power");
      }

      IntentFilter createIntentFilterForBroadcastReceiver() {
         if (VERSION.SDK_INT >= 21) {
            IntentFilter var1 = new IntentFilter();
            var1.addAction("android.os.action.POWER_SAVE_MODE_CHANGED");
            return var1;
         } else {
            return null;
         }
      }

      public int getApplyableNightMode() {
         int var3 = VERSION.SDK_INT;
         byte var2 = 1;
         byte var1 = var2;
         if (var3 >= 21) {
            var1 = var2;
            if (this.mPowerManager.isPowerSaveMode()) {
               var1 = 2;
            }
         }

         return var1;
      }

      public void onChange() {
         this.this$0.applyDayNight();
      }
   }

   abstract class AutoNightModeManager {
      private BroadcastReceiver mReceiver;
      final AppCompatDelegateImpl this$0;

      AutoNightModeManager(AppCompatDelegateImpl var1) {
         this.this$0 = var1;
      }

      void cleanup() {
         if (this.mReceiver != null) {
            try {
               this.this$0.mContext.unregisterReceiver(this.mReceiver);
            } catch (IllegalArgumentException var2) {
            }

            this.mReceiver = null;
         }

      }

      abstract IntentFilter createIntentFilterForBroadcastReceiver();

      abstract int getApplyableNightMode();

      boolean isListening() {
         boolean var1;
         if (this.mReceiver != null) {
            var1 = true;
         } else {
            var1 = false;
         }

         return var1;
      }

      abstract void onChange();

      void setup() {
         this.cleanup();
         IntentFilter var1 = this.createIntentFilterForBroadcastReceiver();
         if (var1 != null && var1.countActions() != 0) {
            if (this.mReceiver == null) {
               this.mReceiver = new BroadcastReceiver(this) {
                  final AutoNightModeManager this$1;

                  {
                     this.this$1 = var1;
                  }

                  public void onReceive(Context var1, Intent var2) {
                     this.this$1.onChange();
                  }
               };
            }

            this.this$0.mContext.registerReceiver(this.mReceiver, var1);
         }

      }
   }

   private class AutoTimeNightModeManager extends AutoNightModeManager {
      private final TwilightManager mTwilightManager;
      final AppCompatDelegateImpl this$0;

      AutoTimeNightModeManager(AppCompatDelegateImpl var1, TwilightManager var2) {
         super(var1);
         this.this$0 = var1;
         this.mTwilightManager = var2;
      }

      IntentFilter createIntentFilterForBroadcastReceiver() {
         IntentFilter var1 = new IntentFilter();
         var1.addAction("android.intent.action.TIME_SET");
         var1.addAction("android.intent.action.TIMEZONE_CHANGED");
         var1.addAction("android.intent.action.TIME_TICK");
         return var1;
      }

      public int getApplyableNightMode() {
         byte var1;
         if (this.mTwilightManager.isNight()) {
            var1 = 2;
         } else {
            var1 = 1;
         }

         return var1;
      }

      public void onChange() {
         this.this$0.applyDayNight();
      }
   }

   private static class ContextThemeWrapperCompatApi17Impl {
      static void applyOverrideConfiguration(android.view.ContextThemeWrapper var0, Configuration var1) {
         var0.applyOverrideConfiguration(var1);
      }
   }

   private class ListMenuDecorView extends ContentFrameLayout {
      final AppCompatDelegateImpl this$0;

      public ListMenuDecorView(AppCompatDelegateImpl var1, Context var2) {
         super(var2);
         this.this$0 = var1;
      }

      private boolean isOutOfBounds(int var1, int var2) {
         boolean var3;
         if (var1 >= -5 && var2 >= -5 && var1 <= this.getWidth() + 5 && var2 <= this.getHeight() + 5) {
            var3 = false;
         } else {
            var3 = true;
         }

         return var3;
      }

      public boolean dispatchKeyEvent(KeyEvent var1) {
         boolean var2;
         if (!this.this$0.dispatchKeyEvent(var1) && !super.dispatchKeyEvent(var1)) {
            var2 = false;
         } else {
            var2 = true;
         }

         return var2;
      }

      public boolean onInterceptTouchEvent(MotionEvent var1) {
         if (var1.getAction() == 0 && this.isOutOfBounds((int)var1.getX(), (int)var1.getY())) {
            this.this$0.closePanel(0);
            return true;
         } else {
            return super.onInterceptTouchEvent(var1);
         }
      }

      public void setBackgroundResource(int var1) {
         this.setBackgroundDrawable(AppCompatResources.getDrawable(this.getContext(), var1));
      }
   }

   protected static final class PanelFeatureState {
      int background;
      View createdPanelView;
      ViewGroup decorView;
      int featureId;
      Bundle frozenActionViewState;
      Bundle frozenMenuState;
      int gravity;
      boolean isHandled;
      boolean isOpen;
      boolean isPrepared;
      ListMenuPresenter listMenuPresenter;
      Context listPresenterContext;
      MenuBuilder menu;
      public boolean qwertyMode;
      boolean refreshDecorView;
      boolean refreshMenuContent;
      View shownPanelView;
      boolean wasLastOpen;
      int windowAnimations;
      int x;
      int y;

      PanelFeatureState(int var1) {
         this.featureId = var1;
         this.refreshDecorView = false;
      }

      void applyFrozenState() {
         MenuBuilder var1 = this.menu;
         if (var1 != null) {
            Bundle var2 = this.frozenMenuState;
            if (var2 != null) {
               var1.restorePresenterStates(var2);
               this.frozenMenuState = null;
            }
         }

      }

      public void clearMenuPresenters() {
         MenuBuilder var1 = this.menu;
         if (var1 != null) {
            var1.removeMenuPresenter(this.listMenuPresenter);
         }

         this.listMenuPresenter = null;
      }

      MenuView getListMenuView(MenuPresenter.Callback var1) {
         if (this.menu == null) {
            return null;
         } else {
            if (this.listMenuPresenter == null) {
               ListMenuPresenter var2 = new ListMenuPresenter(this.listPresenterContext, R.layout.abc_list_menu_item_layout);
               this.listMenuPresenter = var2;
               var2.setCallback(var1);
               this.menu.addMenuPresenter(this.listMenuPresenter);
            }

            return this.listMenuPresenter.getMenuView(this.decorView);
         }
      }

      public boolean hasPanelItems() {
         View var2 = this.shownPanelView;
         boolean var1 = false;
         if (var2 == null) {
            return false;
         } else if (this.createdPanelView != null) {
            return true;
         } else {
            if (this.listMenuPresenter.getAdapter().getCount() > 0) {
               var1 = true;
            }

            return var1;
         }
      }

      void onRestoreInstanceState(Parcelable var1) {
         SavedState var2 = (SavedState)var1;
         this.featureId = var2.featureId;
         this.wasLastOpen = var2.isOpen;
         this.frozenMenuState = var2.menuState;
         this.shownPanelView = null;
         this.decorView = null;
      }

      Parcelable onSaveInstanceState() {
         SavedState var1 = new SavedState();
         var1.featureId = this.featureId;
         var1.isOpen = this.isOpen;
         if (this.menu != null) {
            var1.menuState = new Bundle();
            this.menu.savePresenterStates(var1.menuState);
         }

         return var1;
      }

      void setMenu(MenuBuilder var1) {
         MenuBuilder var2 = this.menu;
         if (var1 != var2) {
            if (var2 != null) {
               var2.removeMenuPresenter(this.listMenuPresenter);
            }

            this.menu = var1;
            if (var1 != null) {
               ListMenuPresenter var3 = this.listMenuPresenter;
               if (var3 != null) {
                  var1.addMenuPresenter(var3);
               }
            }

         }
      }

      void setStyle(Context var1) {
         TypedValue var3 = new TypedValue();
         Resources.Theme var2 = var1.getResources().newTheme();
         var2.setTo(var1.getTheme());
         var2.resolveAttribute(R.attr.actionBarPopupTheme, var3, true);
         if (var3.resourceId != 0) {
            var2.applyStyle(var3.resourceId, true);
         }

         var2.resolveAttribute(R.attr.panelMenuListTheme, var3, true);
         if (var3.resourceId != 0) {
            var2.applyStyle(var3.resourceId, true);
         } else {
            var2.applyStyle(R.style.Theme_AppCompat_CompactMenu, true);
         }

         ContextThemeWrapper var4 = new ContextThemeWrapper(var1, 0);
         var4.getTheme().setTo(var2);
         this.listPresenterContext = var4;
         TypedArray var5 = var4.obtainStyledAttributes(R.styleable.AppCompatTheme);
         this.background = var5.getResourceId(R.styleable.AppCompatTheme_panelBackground, 0);
         this.windowAnimations = var5.getResourceId(R.styleable.AppCompatTheme_android_windowAnimationStyle, 0);
         var5.recycle();
      }

      private static class SavedState implements Parcelable {
         public static final Parcelable.Creator CREATOR = new Parcelable.ClassLoaderCreator() {
            public SavedState createFromParcel(Parcel var1) {
               return AppCompatDelegateImpl.PanelFeatureState.SavedState.readFromParcel(var1, (ClassLoader)null);
            }

            public SavedState createFromParcel(Parcel var1, ClassLoader var2) {
               return AppCompatDelegateImpl.PanelFeatureState.SavedState.readFromParcel(var1, var2);
            }

            public SavedState[] newArray(int var1) {
               return new SavedState[var1];
            }
         };
         int featureId;
         boolean isOpen;
         Bundle menuState;

         SavedState() {
         }

         static SavedState readFromParcel(Parcel var0, ClassLoader var1) {
            SavedState var4 = new SavedState();
            var4.featureId = var0.readInt();
            int var2 = var0.readInt();
            boolean var3 = true;
            if (var2 != 1) {
               var3 = false;
            }

            var4.isOpen = var3;
            if (var3) {
               var4.menuState = var0.readBundle(var1);
            }

            return var4;
         }

         public int describeContents() {
            return 0;
         }

         public void writeToParcel(Parcel var1, int var2) {
            var1.writeInt(this.featureId);
            var1.writeInt(this.isOpen);
            if (this.isOpen) {
               var1.writeBundle(this.menuState);
            }

         }
      }
   }

   private final class PanelMenuPresenterCallback implements MenuPresenter.Callback {
      final AppCompatDelegateImpl this$0;

      PanelMenuPresenterCallback(AppCompatDelegateImpl var1) {
         this.this$0 = var1;
      }

      public void onCloseMenu(MenuBuilder var1, boolean var2) {
         MenuBuilder var4 = var1.getRootMenu();
         boolean var3;
         if (var4 != var1) {
            var3 = true;
         } else {
            var3 = false;
         }

         AppCompatDelegateImpl var5 = this.this$0;
         if (var3) {
            var1 = var4;
         }

         PanelFeatureState var6 = var5.findMenuPanel(var1);
         if (var6 != null) {
            if (var3) {
               this.this$0.callOnPanelClosed(var6.featureId, var6, var4);
               this.this$0.closePanel(var6, true);
            } else {
               this.this$0.closePanel(var6, var2);
            }
         }

      }

      public boolean onOpenSubMenu(MenuBuilder var1) {
         if (var1 == var1.getRootMenu() && this.this$0.mHasActionBar) {
            Window.Callback var2 = this.this$0.getWindowCallback();
            if (var2 != null && !this.this$0.mDestroyed) {
               var2.onMenuOpened(108, var1);
            }
         }

         return true;
      }
   }
}
