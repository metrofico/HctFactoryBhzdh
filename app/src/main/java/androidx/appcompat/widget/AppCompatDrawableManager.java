package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Bitmap.Config;
import android.graphics.PorterDuff.Mode;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import androidx.appcompat.R;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.graphics.ColorUtils;

public final class AppCompatDrawableManager {
   private static final boolean DEBUG = false;
   private static final PorterDuff.Mode DEFAULT_MODE;
   private static AppCompatDrawableManager INSTANCE;
   private static final String TAG = "AppCompatDrawableManag";
   private ResourceManagerInternal mResourceManager;

   static {
      DEFAULT_MODE = Mode.SRC_IN;
   }

   public static AppCompatDrawableManager get() {
      synchronized(AppCompatDrawableManager.class){}

      AppCompatDrawableManager var0;
      try {
         if (INSTANCE == null) {
            preload();
         }

         var0 = INSTANCE;
      } finally {
         ;
      }

      return var0;
   }

   public static PorterDuffColorFilter getPorterDuffColorFilter(int var0, PorterDuff.Mode var1) {
      synchronized(AppCompatDrawableManager.class){}

      PorterDuffColorFilter var4;
      try {
         var4 = ResourceManagerInternal.getPorterDuffColorFilter(var0, var1);
      } finally {
         ;
      }

      return var4;
   }

   public static void preload() {
      synchronized(AppCompatDrawableManager.class){}

      try {
         if (INSTANCE == null) {
            AppCompatDrawableManager var0 = new AppCompatDrawableManager();
            INSTANCE = var0;
            var0.mResourceManager = ResourceManagerInternal.get();
            ResourceManagerInternal var4 = INSTANCE.mResourceManager;
            ResourceManagerInternal.ResourceManagerHooks var1 = new ResourceManagerInternal.ResourceManagerHooks() {
               private final int[] COLORFILTER_COLOR_BACKGROUND_MULTIPLY;
               private final int[] COLORFILTER_COLOR_CONTROL_ACTIVATED;
               private final int[] COLORFILTER_TINT_COLOR_CONTROL_NORMAL;
               private final int[] TINT_CHECKABLE_BUTTON_LIST;
               private final int[] TINT_COLOR_CONTROL_NORMAL;
               private final int[] TINT_COLOR_CONTROL_STATE_LIST;

               {
                  this.COLORFILTER_TINT_COLOR_CONTROL_NORMAL = new int[]{R.drawable.abc_textfield_search_default_mtrl_alpha, R.drawable.abc_textfield_default_mtrl_alpha, R.drawable.abc_ab_share_pack_mtrl_alpha};
                  this.TINT_COLOR_CONTROL_NORMAL = new int[]{R.drawable.abc_ic_commit_search_api_mtrl_alpha, R.drawable.abc_seekbar_tick_mark_material, R.drawable.abc_ic_menu_share_mtrl_alpha, R.drawable.abc_ic_menu_copy_mtrl_am_alpha, R.drawable.abc_ic_menu_cut_mtrl_alpha, R.drawable.abc_ic_menu_selectall_mtrl_alpha, R.drawable.abc_ic_menu_paste_mtrl_am_alpha};
                  this.COLORFILTER_COLOR_CONTROL_ACTIVATED = new int[]{R.drawable.abc_textfield_activated_mtrl_alpha, R.drawable.abc_textfield_search_activated_mtrl_alpha, R.drawable.abc_cab_background_top_mtrl_alpha, R.drawable.abc_text_cursor_material, R.drawable.abc_text_select_handle_left_mtrl, R.drawable.abc_text_select_handle_middle_mtrl, R.drawable.abc_text_select_handle_right_mtrl};
                  this.COLORFILTER_COLOR_BACKGROUND_MULTIPLY = new int[]{R.drawable.abc_popup_background_mtrl_mult, R.drawable.abc_cab_background_internal_bg, R.drawable.abc_menu_hardkey_panel_mtrl_mult};
                  this.TINT_COLOR_CONTROL_STATE_LIST = new int[]{R.drawable.abc_tab_indicator_material, R.drawable.abc_textfield_search_material};
                  this.TINT_CHECKABLE_BUTTON_LIST = new int[]{R.drawable.abc_btn_check_material, R.drawable.abc_btn_radio_material, R.drawable.abc_btn_check_material_anim, R.drawable.abc_btn_radio_material_anim};
               }

               private boolean arrayContains(int[] var1, int var2) {
                  int var4 = var1.length;

                  for(int var3 = 0; var3 < var4; ++var3) {
                     if (var1[var3] == var2) {
                        return true;
                     }
                  }

                  return false;
               }

               private ColorStateList createBorderlessButtonColorStateList(Context var1) {
                  return this.createButtonColorStateList(var1, 0);
               }

               private ColorStateList createButtonColorStateList(Context var1, int var2) {
                  int var5 = ThemeUtils.getThemeAttrColor(var1, R.attr.colorControlHighlight);
                  int var3 = ThemeUtils.getDisabledThemeAttrColor(var1, R.attr.colorButtonNormal);
                  int[] var8 = ThemeUtils.DISABLED_STATE_SET;
                  int[] var7 = ThemeUtils.PRESSED_STATE_SET;
                  int var4 = ColorUtils.compositeColors(var5, var2);
                  int[] var6 = ThemeUtils.FOCUSED_STATE_SET;
                  var5 = ColorUtils.compositeColors(var5, var2);
                  return new ColorStateList(new int[][]{var8, var7, var6, ThemeUtils.EMPTY_STATE_SET}, new int[]{var3, var4, var5, var2});
               }

               private ColorStateList createColoredButtonColorStateList(Context var1) {
                  return this.createButtonColorStateList(var1, ThemeUtils.getThemeAttrColor(var1, R.attr.colorAccent));
               }

               private ColorStateList createDefaultButtonColorStateList(Context var1) {
                  return this.createButtonColorStateList(var1, ThemeUtils.getThemeAttrColor(var1, R.attr.colorButtonNormal));
               }

               private ColorStateList createSwitchThumbColorStateList(Context var1) {
                  int[][] var5 = new int[3][];
                  int[] var3 = new int[3];
                  ColorStateList var4 = ThemeUtils.getThemeAttrColorStateList(var1, R.attr.colorSwitchThumbNormal);
                  if (var4 != null && var4.isStateful()) {
                     int[] var2 = ThemeUtils.DISABLED_STATE_SET;
                     var5[0] = var2;
                     var3[0] = var4.getColorForState(var2, 0);
                     var5[1] = ThemeUtils.CHECKED_STATE_SET;
                     var3[1] = ThemeUtils.getThemeAttrColor(var1, R.attr.colorControlActivated);
                     var5[2] = ThemeUtils.EMPTY_STATE_SET;
                     var3[2] = var4.getDefaultColor();
                  } else {
                     var5[0] = ThemeUtils.DISABLED_STATE_SET;
                     var3[0] = ThemeUtils.getDisabledThemeAttrColor(var1, R.attr.colorSwitchThumbNormal);
                     var5[1] = ThemeUtils.CHECKED_STATE_SET;
                     var3[1] = ThemeUtils.getThemeAttrColor(var1, R.attr.colorControlActivated);
                     var5[2] = ThemeUtils.EMPTY_STATE_SET;
                     var3[2] = ThemeUtils.getThemeAttrColor(var1, R.attr.colorSwitchThumbNormal);
                  }

                  return new ColorStateList(var5, var3);
               }

               private LayerDrawable getRatingBarLayerDrawable(ResourceManagerInternal var1, Context var2, int var3) {
                  var3 = var2.getResources().getDimensionPixelSize(var3);
                  Drawable var5 = var1.getDrawable(var2, R.drawable.abc_star_black_48dp);
                  Drawable var4 = var1.getDrawable(var2, R.drawable.abc_star_half_black_48dp);
                  BitmapDrawable var8;
                  BitmapDrawable var10;
                  if (var5 instanceof BitmapDrawable && var5.getIntrinsicWidth() == var3 && var5.getIntrinsicHeight() == var3) {
                     var8 = (BitmapDrawable)var5;
                     var10 = new BitmapDrawable(var8.getBitmap());
                  } else {
                     Bitmap var9 = Bitmap.createBitmap(var3, var3, Config.ARGB_8888);
                     Canvas var7 = new Canvas(var9);
                     var5.setBounds(0, 0, var3, var3);
                     var5.draw(var7);
                     var8 = new BitmapDrawable(var9);
                     var10 = new BitmapDrawable(var9);
                  }

                  var10.setTileModeX(TileMode.REPEAT);
                  BitmapDrawable var12;
                  if (var4 instanceof BitmapDrawable && var4.getIntrinsicWidth() == var3 && var4.getIntrinsicHeight() == var3) {
                     var12 = (BitmapDrawable)var4;
                  } else {
                     Bitmap var13 = Bitmap.createBitmap(var3, var3, Config.ARGB_8888);
                     Canvas var6 = new Canvas(var13);
                     var4.setBounds(0, 0, var3, var3);
                     var4.draw(var6);
                     var12 = new BitmapDrawable(var13);
                  }

                  LayerDrawable var11 = new LayerDrawable(new Drawable[]{var8, var12, var10});
                  var11.setId(0, 16908288);
                  var11.setId(1, 16908303);
                  var11.setId(2, 16908301);
                  return var11;
               }

               private void setPorterDuffColorFilter(Drawable var1, int var2, PorterDuff.Mode var3) {
                  Drawable var4 = var1;
                  if (DrawableUtils.canSafelyMutateDrawable(var1)) {
                     var4 = var1.mutate();
                  }

                  PorterDuff.Mode var5 = var3;
                  if (var3 == null) {
                     var5 = AppCompatDrawableManager.DEFAULT_MODE;
                  }

                  var4.setColorFilter(AppCompatDrawableManager.getPorterDuffColorFilter(var2, var5));
               }

               public Drawable createDrawableFor(ResourceManagerInternal var1, Context var2, int var3) {
                  if (var3 == R.drawable.abc_cab_background_top_material) {
                     return new LayerDrawable(new Drawable[]{var1.getDrawable(var2, R.drawable.abc_cab_background_internal_bg), var1.getDrawable(var2, R.drawable.abc_cab_background_top_mtrl_alpha)});
                  } else if (var3 == R.drawable.abc_ratingbar_material) {
                     return this.getRatingBarLayerDrawable(var1, var2, R.dimen.abc_star_big);
                  } else if (var3 == R.drawable.abc_ratingbar_indicator_material) {
                     return this.getRatingBarLayerDrawable(var1, var2, R.dimen.abc_star_medium);
                  } else {
                     return var3 == R.drawable.abc_ratingbar_small_material ? this.getRatingBarLayerDrawable(var1, var2, R.dimen.abc_star_small) : null;
                  }
               }

               public ColorStateList getTintListForDrawableRes(Context var1, int var2) {
                  if (var2 == R.drawable.abc_edit_text_material) {
                     return AppCompatResources.getColorStateList(var1, R.color.abc_tint_edittext);
                  } else if (var2 == R.drawable.abc_switch_track_mtrl_alpha) {
                     return AppCompatResources.getColorStateList(var1, R.color.abc_tint_switch_track);
                  } else if (var2 == R.drawable.abc_switch_thumb_material) {
                     return this.createSwitchThumbColorStateList(var1);
                  } else if (var2 == R.drawable.abc_btn_default_mtrl_shape) {
                     return this.createDefaultButtonColorStateList(var1);
                  } else if (var2 == R.drawable.abc_btn_borderless_material) {
                     return this.createBorderlessButtonColorStateList(var1);
                  } else if (var2 == R.drawable.abc_btn_colored_material) {
                     return this.createColoredButtonColorStateList(var1);
                  } else if (var2 != R.drawable.abc_spinner_mtrl_am_alpha && var2 != R.drawable.abc_spinner_textfield_background_material) {
                     if (this.arrayContains(this.TINT_COLOR_CONTROL_NORMAL, var2)) {
                        return ThemeUtils.getThemeAttrColorStateList(var1, R.attr.colorControlNormal);
                     } else if (this.arrayContains(this.TINT_COLOR_CONTROL_STATE_LIST, var2)) {
                        return AppCompatResources.getColorStateList(var1, R.color.abc_tint_default);
                     } else if (this.arrayContains(this.TINT_CHECKABLE_BUTTON_LIST, var2)) {
                        return AppCompatResources.getColorStateList(var1, R.color.abc_tint_btn_checkable);
                     } else {
                        return var2 == R.drawable.abc_seekbar_thumb_material ? AppCompatResources.getColorStateList(var1, R.color.abc_tint_seek_thumb) : null;
                     }
                  } else {
                     return AppCompatResources.getColorStateList(var1, R.color.abc_tint_spinner);
                  }
               }

               public PorterDuff.Mode getTintModeForDrawableRes(int var1) {
                  PorterDuff.Mode var2;
                  if (var1 == R.drawable.abc_switch_thumb_material) {
                     var2 = Mode.MULTIPLY;
                  } else {
                     var2 = null;
                  }

                  return var2;
               }

               public boolean tintDrawable(Context var1, int var2, Drawable var3) {
                  LayerDrawable var4;
                  if (var2 == R.drawable.abc_seekbar_track_material) {
                     var4 = (LayerDrawable)var3;
                     this.setPorterDuffColorFilter(var4.findDrawableByLayerId(16908288), ThemeUtils.getThemeAttrColor(var1, R.attr.colorControlNormal), AppCompatDrawableManager.DEFAULT_MODE);
                     this.setPorterDuffColorFilter(var4.findDrawableByLayerId(16908303), ThemeUtils.getThemeAttrColor(var1, R.attr.colorControlNormal), AppCompatDrawableManager.DEFAULT_MODE);
                     this.setPorterDuffColorFilter(var4.findDrawableByLayerId(16908301), ThemeUtils.getThemeAttrColor(var1, R.attr.colorControlActivated), AppCompatDrawableManager.DEFAULT_MODE);
                     return true;
                  } else if (var2 != R.drawable.abc_ratingbar_material && var2 != R.drawable.abc_ratingbar_indicator_material && var2 != R.drawable.abc_ratingbar_small_material) {
                     return false;
                  } else {
                     var4 = (LayerDrawable)var3;
                     this.setPorterDuffColorFilter(var4.findDrawableByLayerId(16908288), ThemeUtils.getDisabledThemeAttrColor(var1, R.attr.colorControlNormal), AppCompatDrawableManager.DEFAULT_MODE);
                     this.setPorterDuffColorFilter(var4.findDrawableByLayerId(16908303), ThemeUtils.getThemeAttrColor(var1, R.attr.colorControlActivated), AppCompatDrawableManager.DEFAULT_MODE);
                     this.setPorterDuffColorFilter(var4.findDrawableByLayerId(16908301), ThemeUtils.getThemeAttrColor(var1, R.attr.colorControlActivated), AppCompatDrawableManager.DEFAULT_MODE);
                     return true;
                  }
               }

               public boolean tintDrawableUsingColorFilter(Context var1, int var2, Drawable var3) {
                  int var4;
                  boolean var5;
                  PorterDuff.Mode var7;
                  label40: {
                     label39: {
                        var7 = AppCompatDrawableManager.DEFAULT_MODE;
                        boolean var6 = this.arrayContains(this.COLORFILTER_TINT_COLOR_CONTROL_NORMAL, var2);
                        var4 = 16842801;
                        if (var6) {
                           var2 = R.attr.colorControlNormal;
                        } else if (this.arrayContains(this.COLORFILTER_COLOR_CONTROL_ACTIVATED, var2)) {
                           var2 = R.attr.colorControlActivated;
                        } else if (this.arrayContains(this.COLORFILTER_COLOR_BACKGROUND_MULTIPLY, var2)) {
                           var7 = Mode.MULTIPLY;
                           var2 = var4;
                        } else {
                           if (var2 == R.drawable.abc_list_divider_mtrl_alpha) {
                              var2 = 16842800;
                              var4 = Math.round(40.8F);
                              break label39;
                           }

                           if (var2 != R.drawable.abc_dialog_material_background) {
                              var4 = -1;
                              var5 = false;
                              var2 = 0;
                              break label40;
                           }

                           var2 = var4;
                        }

                        var4 = -1;
                     }

                     var5 = true;
                  }

                  if (var5) {
                     Drawable var8 = var3;
                     if (DrawableUtils.canSafelyMutateDrawable(var3)) {
                        var8 = var3.mutate();
                     }

                     var8.setColorFilter(AppCompatDrawableManager.getPorterDuffColorFilter(ThemeUtils.getThemeAttrColor(var1, var2), var7));
                     if (var4 != -1) {
                        var8.setAlpha(var4);
                     }

                     return true;
                  } else {
                     return false;
                  }
               }
            };
            var4.setHooks(var1);
         }
      } finally {
         ;
      }

   }

   static void tintDrawable(Drawable var0, TintInfo var1, int[] var2) {
      ResourceManagerInternal.tintDrawable(var0, var1, var2);
   }

   public Drawable getDrawable(Context var1, int var2) {
      synchronized(this){}

      Drawable var5;
      try {
         var5 = this.mResourceManager.getDrawable(var1, var2);
      } finally {
         ;
      }

      return var5;
   }

   Drawable getDrawable(Context var1, int var2, boolean var3) {
      synchronized(this){}

      Drawable var6;
      try {
         var6 = this.mResourceManager.getDrawable(var1, var2, var3);
      } finally {
         ;
      }

      return var6;
   }

   ColorStateList getTintList(Context var1, int var2) {
      synchronized(this){}

      ColorStateList var5;
      try {
         var5 = this.mResourceManager.getTintList(var1, var2);
      } finally {
         ;
      }

      return var5;
   }

   public void onConfigurationChanged(Context var1) {
      synchronized(this){}

      try {
         this.mResourceManager.onConfigurationChanged(var1);
      } finally {
         ;
      }

   }

   Drawable onDrawableLoadedFromResources(Context var1, VectorEnabledTintResources var2, int var3) {
      synchronized(this){}

      Drawable var6;
      try {
         var6 = this.mResourceManager.onDrawableLoadedFromResources(var1, var2, var3);
      } finally {
         ;
      }

      return var6;
   }

   boolean tintDrawableUsingColorFilter(Context var1, int var2, Drawable var3) {
      return this.mResourceManager.tintDrawableUsingColorFilter(var1, var2, var3);
   }
}
