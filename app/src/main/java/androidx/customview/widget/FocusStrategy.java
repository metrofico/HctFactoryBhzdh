package androidx.customview.widget;

import android.graphics.Rect;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

class FocusStrategy {
   private FocusStrategy() {
   }

   private static boolean beamBeats(int var0, Rect var1, Rect var2, Rect var3) {
      boolean var6 = beamsOverlap(var0, var1, var2);
      boolean var5 = beamsOverlap(var0, var1, var3);
      boolean var4 = false;
      if (!var5 && var6) {
         if (!isToDirectionOf(var0, var1, var3)) {
            return true;
         } else if (var0 != 17 && var0 != 66) {
            if (majorAxisDistance(var0, var1, var2) < majorAxisDistanceToFarEdge(var0, var1, var3)) {
               var4 = true;
            }

            return var4;
         } else {
            return true;
         }
      } else {
         return false;
      }
   }

   private static boolean beamsOverlap(int var0, Rect var1, Rect var2) {
      boolean var3;
      boolean var4;
      label41: {
         var3 = true;
         var4 = true;
         if (var0 != 17) {
            if (var0 == 33) {
               break label41;
            }

            if (var0 != 66) {
               if (var0 != 130) {
                  throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
               }
               break label41;
            }
         }

         if (var2.bottom < var1.top || var2.top > var1.bottom) {
            var3 = false;
         }

         return var3;
      }

      if (var2.right >= var1.left && var2.left <= var1.right) {
         var3 = var4;
      } else {
         var3 = false;
      }

      return var3;
   }

   public static Object findNextFocusInAbsoluteDirection(Object var0, CollectionAdapter var1, BoundsAdapter var2, Object var3, Rect var4, int var5) {
      Rect var11 = new Rect(var4);
      int var6 = 0;
      if (var5 != 17) {
         if (var5 != 33) {
            if (var5 != 66) {
               if (var5 != 130) {
                  throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
               }

               var11.offset(0, -(var4.height() + 1));
            } else {
               var11.offset(-(var4.width() + 1), 0);
            }
         } else {
            var11.offset(0, var4.height() + 1);
         }
      } else {
         var11.offset(var4.width() + 1, 0);
      }

      Object var8 = null;
      int var7 = var1.size(var0);

      for(Rect var10 = new Rect(); var6 < var7; ++var6) {
         Object var9 = var1.get(var0, var6);
         if (var9 != var3) {
            var2.obtainBounds(var9, var10);
            if (isBetterCandidate(var5, var4, var10, var11)) {
               var11.set(var10);
               var8 = var9;
            }
         }
      }

      return var8;
   }

   public static Object findNextFocusInRelativeDirection(Object var0, CollectionAdapter var1, BoundsAdapter var2, Object var3, int var4, boolean var5, boolean var6) {
      int var8 = var1.size(var0);
      ArrayList var9 = new ArrayList(var8);

      for(int var7 = 0; var7 < var8; ++var7) {
         var9.add(var1.get(var0, var7));
      }

      Collections.sort(var9, new SequentialComparator(var5, var2));
      if (var4 != 1) {
         if (var4 == 2) {
            return getNextFocusable(var3, var9, var6);
         } else {
            throw new IllegalArgumentException("direction must be one of {FOCUS_FORWARD, FOCUS_BACKWARD}.");
         }
      } else {
         return getPreviousFocusable(var3, var9, var6);
      }
   }

   private static Object getNextFocusable(Object var0, ArrayList var1, boolean var2) {
      int var4 = var1.size();
      int var3;
      if (var0 == null) {
         var3 = -1;
      } else {
         var3 = var1.lastIndexOf(var0);
      }

      ++var3;
      if (var3 < var4) {
         return var1.get(var3);
      } else {
         return var2 && var4 > 0 ? var1.get(0) : null;
      }
   }

   private static Object getPreviousFocusable(Object var0, ArrayList var1, boolean var2) {
      int var4 = var1.size();
      int var3;
      if (var0 == null) {
         var3 = var4;
      } else {
         var3 = var1.indexOf(var0);
      }

      --var3;
      if (var3 >= 0) {
         return var1.get(var3);
      } else {
         return var2 && var4 > 0 ? var1.get(var4 - 1) : null;
      }
   }

   private static int getWeightedDistanceFor(int var0, int var1) {
      return var0 * 13 * var0 + var1 * var1;
   }

   private static boolean isBetterCandidate(int var0, Rect var1, Rect var2, Rect var3) {
      boolean var5 = isCandidate(var1, var2, var0);
      boolean var4 = false;
      if (!var5) {
         return false;
      } else if (!isCandidate(var1, var3, var0)) {
         return true;
      } else if (beamBeats(var0, var1, var2, var3)) {
         return true;
      } else if (beamBeats(var0, var1, var3, var2)) {
         return false;
      } else {
         if (getWeightedDistanceFor(majorAxisDistance(var0, var1, var2), minorAxisDistance(var0, var1, var2)) < getWeightedDistanceFor(majorAxisDistance(var0, var1, var3), minorAxisDistance(var0, var1, var3))) {
            var4 = true;
         }

         return var4;
      }
   }

   private static boolean isCandidate(Rect var0, Rect var1, int var2) {
      boolean var3 = true;
      boolean var5 = true;
      boolean var4 = true;
      boolean var6 = true;
      if (var2 != 17) {
         if (var2 != 33) {
            if (var2 != 66) {
               if (var2 != 130) {
                  throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
               } else {
                  if ((var0.top < var1.top || var0.bottom <= var1.top) && var0.bottom < var1.bottom) {
                     var3 = var6;
                  } else {
                     var3 = false;
                  }

                  return var3;
               }
            } else {
               if (var0.left >= var1.left && var0.right > var1.left || var0.right >= var1.right) {
                  var3 = false;
               }

               return var3;
            }
         } else {
            if ((var0.bottom > var1.bottom || var0.top >= var1.bottom) && var0.top > var1.top) {
               var3 = var5;
            } else {
               var3 = false;
            }

            return var3;
         }
      } else {
         if ((var0.right > var1.right || var0.left >= var1.right) && var0.left > var1.left) {
            var3 = var4;
         } else {
            var3 = false;
         }

         return var3;
      }
   }

   private static boolean isToDirectionOf(int var0, Rect var1, Rect var2) {
      boolean var5 = true;
      boolean var6 = true;
      boolean var3 = true;
      boolean var4 = true;
      if (var0 != 17) {
         if (var0 != 33) {
            if (var0 != 66) {
               if (var0 == 130) {
                  if (var1.bottom <= var2.top) {
                     var3 = var4;
                  } else {
                     var3 = false;
                  }

                  return var3;
               } else {
                  throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
               }
            } else {
               if (var1.right <= var2.left) {
                  var3 = var5;
               } else {
                  var3 = false;
               }

               return var3;
            }
         } else {
            if (var1.top >= var2.bottom) {
               var3 = var6;
            } else {
               var3 = false;
            }

            return var3;
         }
      } else {
         if (var1.left < var2.right) {
            var3 = false;
         }

         return var3;
      }
   }

   private static int majorAxisDistance(int var0, Rect var1, Rect var2) {
      return Math.max(0, majorAxisDistanceRaw(var0, var1, var2));
   }

   private static int majorAxisDistanceRaw(int var0, Rect var1, Rect var2) {
      int var3;
      if (var0 != 17) {
         if (var0 != 33) {
            if (var0 != 66) {
               if (var0 != 130) {
                  throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
               }

               var0 = var2.top;
               var3 = var1.bottom;
            } else {
               var0 = var2.left;
               var3 = var1.right;
            }
         } else {
            var0 = var1.top;
            var3 = var2.bottom;
         }
      } else {
         var0 = var1.left;
         var3 = var2.right;
      }

      return var0 - var3;
   }

   private static int majorAxisDistanceToFarEdge(int var0, Rect var1, Rect var2) {
      return Math.max(1, majorAxisDistanceToFarEdgeRaw(var0, var1, var2));
   }

   private static int majorAxisDistanceToFarEdgeRaw(int var0, Rect var1, Rect var2) {
      int var3;
      if (var0 != 17) {
         if (var0 != 33) {
            if (var0 != 66) {
               if (var0 != 130) {
                  throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
               }

               var0 = var2.bottom;
               var3 = var1.bottom;
            } else {
               var0 = var2.right;
               var3 = var1.right;
            }
         } else {
            var0 = var1.top;
            var3 = var2.top;
         }
      } else {
         var0 = var1.left;
         var3 = var2.left;
      }

      return var0 - var3;
   }

   private static int minorAxisDistance(int var0, Rect var1, Rect var2) {
      if (var0 != 17) {
         if (var0 == 33) {
            return Math.abs(var1.left + var1.width() / 2 - (var2.left + var2.width() / 2));
         }

         if (var0 != 66) {
            if (var0 != 130) {
               throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
            }

            return Math.abs(var1.left + var1.width() / 2 - (var2.left + var2.width() / 2));
         }
      }

      return Math.abs(var1.top + var1.height() / 2 - (var2.top + var2.height() / 2));
   }

   public interface BoundsAdapter {
      void obtainBounds(Object var1, Rect var2);
   }

   public interface CollectionAdapter {
      Object get(Object var1, int var2);

      int size(Object var1);
   }

   private static class SequentialComparator implements Comparator {
      private final BoundsAdapter mAdapter;
      private final boolean mIsLayoutRtl;
      private final Rect mTemp1 = new Rect();
      private final Rect mTemp2 = new Rect();

      SequentialComparator(boolean var1, BoundsAdapter var2) {
         this.mIsLayoutRtl = var1;
         this.mAdapter = var2;
      }

      public int compare(Object var1, Object var2) {
         Rect var6 = this.mTemp1;
         Rect var7 = this.mTemp2;
         this.mAdapter.obtainBounds(var1, var6);
         this.mAdapter.obtainBounds(var2, var7);
         int var5 = var6.top;
         int var4 = var7.top;
         byte var3 = -1;
         if (var5 < var4) {
            return -1;
         } else if (var6.top > var7.top) {
            return 1;
         } else if (var6.left < var7.left) {
            if (this.mIsLayoutRtl) {
               var3 = 1;
            }

            return var3;
         } else if (var6.left > var7.left) {
            if (!this.mIsLayoutRtl) {
               var3 = 1;
            }

            return var3;
         } else if (var6.bottom < var7.bottom) {
            return -1;
         } else if (var6.bottom > var7.bottom) {
            return 1;
         } else if (var6.right < var7.right) {
            if (this.mIsLayoutRtl) {
               var3 = 1;
            }

            return var3;
         } else if (var6.right > var7.right) {
            if (!this.mIsLayoutRtl) {
               var3 = 1;
            }

            return var3;
         } else {
            return 0;
         }
      }
   }
}
