package androidx.appcompat.widget;

import android.graphics.Insets;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ScaleDrawable;
import android.os.Build.VERSION;
import androidx.appcompat.graphics.drawable.DrawableWrapper;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.graphics.drawable.WrappedDrawable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class DrawableUtils {
   private static final int[] CHECKED_STATE_SET = new int[]{16842912};
   private static final int[] EMPTY_STATE_SET = new int[0];
   public static final Rect INSETS_NONE = new Rect();

   private DrawableUtils() {
   }

   public static boolean canSafelyMutateDrawable(Drawable var0) {
      if (VERSION.SDK_INT < 15 && var0 instanceof InsetDrawable) {
         return false;
      } else if (VERSION.SDK_INT < 15 && var0 instanceof GradientDrawable) {
         return false;
      } else if (VERSION.SDK_INT < 17 && var0 instanceof LayerDrawable) {
         return false;
      } else {
         if (var0 instanceof DrawableContainer) {
            Drawable.ConstantState var3 = var0.getConstantState();
            if (var3 instanceof DrawableContainer.DrawableContainerState) {
               Drawable[] var4 = ((DrawableContainer.DrawableContainerState)var3).getChildren();
               int var2 = var4.length;

               for(int var1 = 0; var1 < var2; ++var1) {
                  if (!canSafelyMutateDrawable(var4[var1])) {
                     return false;
                  }
               }
            }
         } else {
            if (var0 instanceof WrappedDrawable) {
               return canSafelyMutateDrawable(((WrappedDrawable)var0).getWrappedDrawable());
            }

            if (var0 instanceof DrawableWrapper) {
               return canSafelyMutateDrawable(((DrawableWrapper)var0).getWrappedDrawable());
            }

            if (var0 instanceof ScaleDrawable) {
               return canSafelyMutateDrawable(((ScaleDrawable)var0).getDrawable());
            }
         }

         return true;
      }
   }

   static void fixDrawable(Drawable var0) {
      String var1 = var0.getClass().getName();
      if (VERSION.SDK_INT == 21 && "android.graphics.drawable.VectorDrawable".equals(var1)) {
         forceDrawableStateChange(var0);
      } else if (VERSION.SDK_INT >= 29 && VERSION.SDK_INT < 31 && "android.graphics.drawable.ColorStateListDrawable".equals(var1)) {
         forceDrawableStateChange(var0);
      }

   }

   private static void forceDrawableStateChange(Drawable var0) {
      int[] var1 = var0.getState();
      if (var1 != null && var1.length != 0) {
         var0.setState(EMPTY_STATE_SET);
      } else {
         var0.setState(CHECKED_STATE_SET);
      }

      var0.setState(var1);
   }

   public static Rect getOpticalBounds(Drawable var0) {
      if (VERSION.SDK_INT >= 29) {
         Insets var1 = var0.getOpticalInsets();
         return new Rect(var1.left, var1.top, var1.right, var1.bottom);
      } else {
         return VERSION.SDK_INT >= 18 ? DrawableUtils.Api18Impl.getOpticalInsets(DrawableCompat.unwrap(var0)) : INSETS_NONE;
      }
   }

   public static PorterDuff.Mode parseTintMode(int var0, PorterDuff.Mode var1) {
      if (var0 != 3) {
         if (var0 != 5) {
            if (var0 != 9) {
               switch (var0) {
                  case 14:
                     return Mode.MULTIPLY;
                  case 15:
                     return Mode.SCREEN;
                  case 16:
                     return Mode.ADD;
                  default:
                     return var1;
               }
            } else {
               return Mode.SRC_ATOP;
            }
         } else {
            return Mode.SRC_IN;
         }
      } else {
         return Mode.SRC_OVER;
      }
   }

   static class Api18Impl {
      private static final Field sBottom;
      private static final Method sGetOpticalInsets;
      private static final Field sLeft;
      private static final boolean sReflectionSuccessful;
      private static final Field sRight;
      private static final Field sTop;

      static {
         boolean var0;
         Method var1;
         Field var17;
         Field var2;
         Field var3;
         Field var4;
         label70: {
            label69: {
               label73: {
                  Class var5;
                  label67: {
                     label66: {
                        label65: {
                           label64: {
                              label63: {
                                 label62: {
                                    try {
                                       var5 = Class.forName("android.graphics.Insets");
                                       var1 = Drawable.class.getMethod("getOpticalInsets");
                                    } catch (NoSuchMethodException var14) {
                                       var1 = null;
                                       break label63;
                                    } catch (ClassNotFoundException var15) {
                                       var1 = null;
                                       break label64;
                                    } catch (NoSuchFieldException var16) {
                                       var1 = null;
                                       break label62;
                                    }

                                    try {
                                       var2 = var5.getField("left");
                                       break label65;
                                    } catch (NoSuchMethodException var11) {
                                       break label63;
                                    } catch (ClassNotFoundException var12) {
                                       break label64;
                                    } catch (NoSuchFieldException var13) {
                                    }
                                 }

                                 var2 = null;
                                 break label66;
                              }

                              var2 = null;
                              break label66;
                           }

                           var2 = null;
                           break label66;
                        }

                        try {
                           var4 = var5.getField("top");
                           break label67;
                        } catch (NoSuchMethodException var8) {
                        } catch (ClassNotFoundException var9) {
                        } catch (NoSuchFieldException var10) {
                        }
                     }

                     var4 = null;
                     var3 = null;
                     break label73;
                  }

                  try {
                     var3 = var5.getField("right");
                  } catch (ClassNotFoundException | NoSuchFieldException | NoSuchMethodException var7) {
                     var3 = null;
                     break label73;
                  }

                  try {
                     var17 = var5.getField("bottom");
                     break label69;
                  } catch (ClassNotFoundException | NoSuchFieldException | NoSuchMethodException var6) {
                  }
               }

               var0 = false;
               var17 = null;
               break label70;
            }

            var0 = true;
         }

         if (var0) {
            sGetOpticalInsets = var1;
            sLeft = var2;
            sTop = var4;
            sRight = var3;
            sBottom = var17;
            sReflectionSuccessful = true;
         } else {
            sGetOpticalInsets = null;
            sLeft = null;
            sTop = null;
            sRight = null;
            sBottom = null;
            sReflectionSuccessful = false;
         }

      }

      private Api18Impl() {
      }

      static Rect getOpticalInsets(Drawable var0) {
         if (VERSION.SDK_INT < 29 && sReflectionSuccessful) {
            boolean var10001;
            Object var3;
            try {
               var3 = sGetOpticalInsets.invoke(var0);
            } catch (InvocationTargetException | IllegalAccessException var2) {
               var10001 = false;
               return DrawableUtils.INSETS_NONE;
            }

            if (var3 != null) {
               try {
                  Rect var4 = new Rect(sLeft.getInt(var3), sTop.getInt(var3), sRight.getInt(var3), sBottom.getInt(var3));
                  return var4;
               } catch (InvocationTargetException | IllegalAccessException var1) {
                  var10001 = false;
               }
            }
         }

         return DrawableUtils.INSETS_NONE;
      }
   }

   static class Api29Impl {
      private Api29Impl() {
      }

      static Insets getOpticalInsets(Drawable var0) {
         return var0.getOpticalInsets();
      }
   }
}
