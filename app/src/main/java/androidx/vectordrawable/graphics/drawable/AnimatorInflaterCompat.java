package androidx.vectordrawable.graphics.drawable;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.util.Xml;
import android.view.InflateException;
import androidx.core.content.res.TypedArrayUtils;
import androidx.core.graphics.PathParser;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class AnimatorInflaterCompat {
   private static final boolean DBG_ANIMATOR_INFLATER = false;
   private static final int MAX_NUM_POINTS = 100;
   private static final String TAG = "AnimatorInflater";
   private static final int TOGETHER = 0;
   private static final int VALUE_TYPE_COLOR = 3;
   private static final int VALUE_TYPE_FLOAT = 0;
   private static final int VALUE_TYPE_INT = 1;
   private static final int VALUE_TYPE_PATH = 2;
   private static final int VALUE_TYPE_UNDEFINED = 4;

   private AnimatorInflaterCompat() {
   }

   private static Animator createAnimatorFromXml(Context var0, Resources var1, Resources.Theme var2, XmlPullParser var3, float var4) throws XmlPullParserException, IOException {
      return createAnimatorFromXml(var0, var1, var2, var3, Xml.asAttributeSet(var3), (AnimatorSet)null, 0, var4);
   }

   private static Animator createAnimatorFromXml(Context var0, Resources var1, Resources.Theme var2, XmlPullParser var3, AttributeSet var4, AnimatorSet var5, int var6, float var7) throws XmlPullParserException, IOException {
      int var10 = var3.getDepth();
      Object var14 = null;
      ArrayList var13 = null;

      while(true) {
         int var11 = var3.next();
         int var9 = 0;
         boolean var8 = false;
         if (var11 == 3 && var3.getDepth() <= var10 || var11 == 1) {
            if (var5 != null && var13 != null) {
               Animator[] var16 = new Animator[var13.size()];
               Iterator var17 = var13.iterator();

               for(int var18 = var9; var17.hasNext(); ++var18) {
                  var16[var18] = (Animator)var17.next();
               }

               if (var6 == 0) {
                  var5.playTogether(var16);
               } else {
                  var5.playSequentially(var16);
               }
            }

            return (Animator)var14;
         }

         if (var11 == 2) {
            String var12 = var3.getName();
            Object var19;
            if (var12.equals("objectAnimator")) {
               var19 = loadObjectAnimator(var0, var1, var2, var4, var7, var3);
            } else if (var12.equals("animator")) {
               var19 = loadAnimator(var0, var1, var2, var4, (ValueAnimator)null, var7, var3);
            } else if (var12.equals("set")) {
               var19 = new AnimatorSet();
               TypedArray var21 = TypedArrayUtils.obtainAttributes(var1, var2, var4, AndroidResources.STYLEABLE_ANIMATOR_SET);
               var9 = TypedArrayUtils.getNamedInt(var21, var3, "ordering", 0, 0);
               AnimatorSet var15 = (AnimatorSet)var19;
               createAnimatorFromXml(var0, var1, var2, var3, var4, (AnimatorSet)var19, var9, var7);
               var21.recycle();
            } else {
               if (!var12.equals("propertyValuesHolder")) {
                  throw new RuntimeException("Unknown animator name: " + var3.getName());
               }

               PropertyValuesHolder[] var20 = loadValues(var0, var1, var2, var3, Xml.asAttributeSet(var3));
               if (var20 != null && var14 instanceof ValueAnimator) {
                  ((ValueAnimator)var14).setValues(var20);
               }

               var8 = true;
               var19 = var14;
            }

            var14 = var19;
            if (var5 != null) {
               var14 = var19;
               if (!var8) {
                  ArrayList var22 = var13;
                  if (var13 == null) {
                     var22 = new ArrayList();
                  }

                  var22.add(var19);
                  var14 = var19;
                  var13 = var22;
               }
            }
         }
      }
   }

   private static Keyframe createNewKeyframe(Keyframe var0, float var1) {
      if (var0.getType() == Float.TYPE) {
         var0 = Keyframe.ofFloat(var1);
      } else if (var0.getType() == Integer.TYPE) {
         var0 = Keyframe.ofInt(var1);
      } else {
         var0 = Keyframe.ofObject(var1);
      }

      return var0;
   }

   private static void distributeKeyframes(Keyframe[] var0, float var1, int var2, int var3) {
      for(var1 /= (float)(var3 - var2 + 2); var2 <= var3; ++var2) {
         var0[var2].setFraction(var0[var2 - 1].getFraction() + var1);
      }

   }

   private static void dumpKeyframes(Object[] var0, String var1) {
      if (var0 != null && var0.length != 0) {
         Log.d("AnimatorInflater", var1);
         int var4 = var0.length;

         for(int var3 = 0; var3 < var4; ++var3) {
            Keyframe var6 = (Keyframe)var0[var3];
            StringBuilder var7 = (new StringBuilder()).append("Keyframe ").append(var3).append(": fraction ");
            float var2 = var6.getFraction();
            String var5 = "null";
            Object var8;
            if (var2 < 0.0F) {
               var8 = "null";
            } else {
               var8 = var6.getFraction();
            }

            var7 = var7.append(var8).append(", , value : ");
            var8 = var5;
            if (var6.hasValue()) {
               var8 = var6.getValue();
            }

            Log.d("AnimatorInflater", var7.append(var8).toString());
         }
      }

   }

   private static PropertyValuesHolder getPVH(TypedArray var0, int var1, int var2, int var3, String var4) {
      TypedValue var12 = var0.peekValue(var2);
      boolean var8;
      if (var12 != null) {
         var8 = true;
      } else {
         var8 = false;
      }

      int var10;
      if (var8) {
         var10 = var12.type;
      } else {
         var10 = 0;
      }

      var12 = var0.peekValue(var3);
      boolean var9;
      if (var12 != null) {
         var9 = true;
      } else {
         var9 = false;
      }

      int var11;
      if (var9) {
         var11 = var12.type;
      } else {
         var11 = 0;
      }

      int var7 = var1;
      if (var1 == 4) {
         if ((!var8 || !isColorType(var10)) && (!var9 || !isColorType(var11))) {
            var7 = 0;
         } else {
            var7 = 3;
         }
      }

      boolean var19;
      if (var7 == 0) {
         var19 = true;
      } else {
         var19 = false;
      }

      var12 = null;
      PathParser.PathDataNode[] var14 = null;
      PropertyValuesHolder var17;
      if (var7 == 2) {
         String var13 = var0.getString(var2);
         String var16 = var0.getString(var3);
         var14 = PathParser.createNodesFromPathData(var13);
         PathParser.PathDataNode[] var15 = PathParser.createNodesFromPathData(var16);
         if (var14 == null) {
            var17 = var12;
            if (var15 == null) {
               return var17;
            }
         }

         if (var14 != null) {
            PathDataEvaluator var18 = new PathDataEvaluator();
            if (var15 != null) {
               if (!PathParser.canMorph(var14, var15)) {
                  throw new InflateException(" Can't morph from " + var13 + " to " + var16);
               }

               var17 = PropertyValuesHolder.ofObject(var4, var18, new Object[]{var14, var15});
            } else {
               var17 = PropertyValuesHolder.ofObject(var4, var18, new Object[]{var14});
            }
         } else {
            var17 = var12;
            if (var15 != null) {
               var17 = PropertyValuesHolder.ofObject(var4, new PathDataEvaluator(), new Object[]{var15});
            }
         }
      } else {
         ArgbEvaluator var21;
         if (var7 == 3) {
            var21 = ArgbEvaluator.getInstance();
         } else {
            var21 = null;
         }

         PropertyValuesHolder var20;
         if (var19) {
            float var5;
            if (var8) {
               if (var10 == 5) {
                  var5 = var0.getDimension(var2, 0.0F);
               } else {
                  var5 = var0.getFloat(var2, 0.0F);
               }

               if (var9) {
                  float var6;
                  if (var11 == 5) {
                     var6 = var0.getDimension(var3, 0.0F);
                  } else {
                     var6 = var0.getFloat(var3, 0.0F);
                  }

                  var17 = PropertyValuesHolder.ofFloat(var4, new float[]{var5, var6});
               } else {
                  var17 = PropertyValuesHolder.ofFloat(var4, new float[]{var5});
               }
            } else {
               if (var11 == 5) {
                  var5 = var0.getDimension(var3, 0.0F);
               } else {
                  var5 = var0.getFloat(var3, 0.0F);
               }

               var17 = PropertyValuesHolder.ofFloat(var4, new float[]{var5});
            }

            var20 = var17;
         } else if (var8) {
            if (var10 == 5) {
               var1 = (int)var0.getDimension(var2, 0.0F);
            } else if (isColorType(var10)) {
               var1 = var0.getColor(var2, 0);
            } else {
               var1 = var0.getInt(var2, 0);
            }

            if (var9) {
               if (var11 == 5) {
                  var2 = (int)var0.getDimension(var3, 0.0F);
               } else if (isColorType(var11)) {
                  var2 = var0.getColor(var3, 0);
               } else {
                  var2 = var0.getInt(var3, 0);
               }

               var20 = PropertyValuesHolder.ofInt(var4, new int[]{var1, var2});
            } else {
               var20 = PropertyValuesHolder.ofInt(var4, new int[]{var1});
            }
         } else {
            var20 = var14;
            if (var9) {
               if (var11 == 5) {
                  var1 = (int)var0.getDimension(var3, 0.0F);
               } else if (isColorType(var11)) {
                  var1 = var0.getColor(var3, 0);
               } else {
                  var1 = var0.getInt(var3, 0);
               }

               var20 = PropertyValuesHolder.ofInt(var4, new int[]{var1});
            }
         }

         var17 = var20;
         if (var20 != null) {
            var17 = var20;
            if (var21 != null) {
               var20.setEvaluator(var21);
               var17 = var20;
            }
         }
      }

      return var17;
   }

   private static int inferValueTypeFromValues(TypedArray var0, int var1, int var2) {
      TypedValue var6 = var0.peekValue(var1);
      boolean var4 = true;
      byte var5 = 0;
      boolean var8;
      if (var6 != null) {
         var8 = true;
      } else {
         var8 = false;
      }

      int var3;
      if (var8) {
         var3 = var6.type;
      } else {
         var3 = 0;
      }

      TypedValue var7 = var0.peekValue(var2);
      boolean var9;
      if (var7 != null) {
         var9 = var4;
      } else {
         var9 = false;
      }

      int var11;
      if (var9) {
         var11 = var7.type;
      } else {
         var11 = 0;
      }

      byte var10;
      if (!var8 || !isColorType(var3)) {
         var10 = var5;
         if (!var9) {
            return var10;
         }

         var10 = var5;
         if (!isColorType(var11)) {
            return var10;
         }
      }

      var10 = 3;
      return var10;
   }

   private static int inferValueTypeOfKeyframe(Resources var0, Resources.Theme var1, AttributeSet var2, XmlPullParser var3) {
      TypedArray var7 = TypedArrayUtils.obtainAttributes(var0, var1, var2, AndroidResources.STYLEABLE_KEYFRAME);
      byte var6 = 0;
      TypedValue var8 = TypedArrayUtils.peekNamedValue(var7, var3, "value", 0);
      boolean var5;
      if (var8 != null) {
         var5 = true;
      } else {
         var5 = false;
      }

      byte var4 = var6;
      if (var5) {
         var4 = var6;
         if (isColorType(var8.type)) {
            var4 = 3;
         }
      }

      var7.recycle();
      return var4;
   }

   private static boolean isColorType(int var0) {
      boolean var1;
      if (var0 >= 28 && var0 <= 31) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public static Animator loadAnimator(Context var0, int var1) throws Resources.NotFoundException {
      Animator var2;
      if (VERSION.SDK_INT >= 24) {
         var2 = AnimatorInflater.loadAnimator(var0, var1);
      } else {
         var2 = loadAnimator(var0, var0.getResources(), var0.getTheme(), var1);
      }

      return var2;
   }

   public static Animator loadAnimator(Context var0, Resources var1, Resources.Theme var2, int var3) throws Resources.NotFoundException {
      return loadAnimator(var0, var1, var2, var3, 1.0F);
   }

   public static Animator loadAnimator(Context param0, Resources param1, Resources.Theme param2, int param3, float param4) throws Resources.NotFoundException {
      // $FF: Couldn't be decompiled
   }

   private static ValueAnimator loadAnimator(Context var0, Resources var1, Resources.Theme var2, AttributeSet var3, ValueAnimator var4, float var5, XmlPullParser var6) throws Resources.NotFoundException {
      TypedArray var8 = TypedArrayUtils.obtainAttributes(var1, var2, var3, AndroidResources.STYLEABLE_ANIMATOR);
      TypedArray var10 = TypedArrayUtils.obtainAttributes(var1, var2, var3, AndroidResources.STYLEABLE_PROPERTY_ANIMATOR);
      ValueAnimator var9 = var4;
      if (var4 == null) {
         var9 = new ValueAnimator();
      }

      parseAnimatorFromTypeArray(var9, var8, var10, var5, var6);
      int var7 = TypedArrayUtils.getNamedResourceId(var8, var6, "interpolator", 0, 0);
      if (var7 > 0) {
         var9.setInterpolator(AnimationUtilsCompat.loadInterpolator(var0, var7));
      }

      var8.recycle();
      if (var10 != null) {
         var10.recycle();
      }

      return var9;
   }

   private static Keyframe loadKeyframe(Context var0, Resources var1, Resources.Theme var2, AttributeSet var3, int var4, XmlPullParser var5) throws XmlPullParserException, IOException {
      TypedArray var11 = TypedArrayUtils.obtainAttributes(var1, var2, var3, AndroidResources.STYLEABLE_KEYFRAME);
      float var6 = TypedArrayUtils.getNamedFloat(var11, var5, "fraction", 3, -1.0F);
      TypedValue var9 = TypedArrayUtils.peekNamedValue(var11, var5, "value", 0);
      boolean var8;
      if (var9 != null) {
         var8 = true;
      } else {
         var8 = false;
      }

      int var7 = var4;
      if (var4 == 4) {
         if (var8 && isColorType(var9.type)) {
            var7 = 3;
         } else {
            var7 = 0;
         }
      }

      Keyframe var10;
      if (var8) {
         if (var7 != 0) {
            if (var7 != 1 && var7 != 3) {
               var10 = null;
            } else {
               var10 = Keyframe.ofInt(var6, TypedArrayUtils.getNamedInt(var11, var5, "value", 0, 0));
            }
         } else {
            var10 = Keyframe.ofFloat(var6, TypedArrayUtils.getNamedFloat(var11, var5, "value", 0, 0.0F));
         }
      } else if (var7 == 0) {
         var10 = Keyframe.ofFloat(var6);
      } else {
         var10 = Keyframe.ofInt(var6);
      }

      var4 = TypedArrayUtils.getNamedResourceId(var11, var5, "interpolator", 1, 0);
      if (var4 > 0) {
         var10.setInterpolator(AnimationUtilsCompat.loadInterpolator(var0, var4));
      }

      var11.recycle();
      return var10;
   }

   private static ObjectAnimator loadObjectAnimator(Context var0, Resources var1, Resources.Theme var2, AttributeSet var3, float var4, XmlPullParser var5) throws Resources.NotFoundException {
      ObjectAnimator var6 = new ObjectAnimator();
      loadAnimator(var0, var1, var2, var3, var6, var4, var5);
      return var6;
   }

   private static PropertyValuesHolder loadPvh(Context var0, Resources var1, Resources.Theme var2, XmlPullParser var3, String var4, int var5) throws XmlPullParserException, IOException {
      Object var14 = null;
      ArrayList var12 = null;
      int var8 = var5;

      while(true) {
         var5 = var3.next();
         if (var5 == 3 || var5 == 1) {
            PropertyValuesHolder var16 = (PropertyValuesHolder)var14;
            if (var12 != null) {
               int var9 = var12.size();
               var16 = (PropertyValuesHolder)var14;
               if (var9 > 0) {
                  int var7 = 0;
                  Keyframe var18 = (Keyframe)var12.get(0);
                  Keyframe var17 = (Keyframe)var12.get(var9 - 1);
                  float var6 = var17.getFraction();
                  var5 = var9;
                  if (var6 < 1.0F) {
                     if (var6 < 0.0F) {
                        var17.setFraction(1.0F);
                        var5 = var9;
                     } else {
                        var12.add(var12.size(), createNewKeyframe(var17, 1.0F));
                        var5 = var9 + 1;
                     }
                  }

                  var6 = var18.getFraction();
                  var9 = var5;
                  if (var6 != 0.0F) {
                     if (var6 < 0.0F) {
                        var18.setFraction(0.0F);
                        var9 = var5;
                     } else {
                        var12.add(0, createNewKeyframe(var18, 0.0F));
                        var9 = var5 + 1;
                     }
                  }

                  Keyframe[] var19 = new Keyframe[var9];
                  var12.toArray(var19);

                  for(var5 = var7; var5 < var9; ++var5) {
                     var18 = var19[var5];
                     if (var18.getFraction() < 0.0F) {
                        if (var5 == 0) {
                           var18.setFraction(0.0F);
                        } else {
                           int var11 = var9 - 1;
                           if (var5 == var11) {
                              var18.setFraction(1.0F);
                           } else {
                              var7 = var5 + 1;

                              int var10;
                              for(var10 = var5; var7 < var11 && !(var19[var7].getFraction() >= 0.0F); var10 = var7++) {
                              }

                              distributeKeyframes(var19, var19[var10 + 1].getFraction() - var19[var5 - 1].getFraction(), var5, var10);
                           }
                        }
                     }
                  }

                  PropertyValuesHolder var20 = PropertyValuesHolder.ofKeyframe(var4, var19);
                  var16 = var20;
                  if (var8 == 3) {
                     var20.setEvaluator(ArgbEvaluator.getInstance());
                     var16 = var20;
                  }
               }
            }

            return var16;
         }

         if (var3.getName().equals("keyframe")) {
            var5 = var8;
            if (var8 == 4) {
               var5 = inferValueTypeOfKeyframe(var1, var2, Xml.asAttributeSet(var3), var3);
            }

            Keyframe var15 = loadKeyframe(var0, var1, var2, Xml.asAttributeSet(var3), var5, var3);
            ArrayList var13 = var12;
            if (var15 != null) {
               var13 = var12;
               if (var12 == null) {
                  var13 = new ArrayList();
               }

               var13.add(var15);
            }

            var3.next();
            var12 = var13;
            var8 = var5;
         }
      }
   }

   private static PropertyValuesHolder[] loadValues(Context var0, Resources var1, Resources.Theme var2, XmlPullParser var3, AttributeSet var4) throws XmlPullParserException, IOException {
      Object var10 = null;
      ArrayList var7 = null;

      while(true) {
         int var6 = var3.getEventType();
         int var5 = 0;
         if (var6 == 3 || var6 == 1) {
            PropertyValuesHolder[] var13 = (PropertyValuesHolder[])var10;
            if (var7 != null) {
               var6 = var7.size();
               PropertyValuesHolder[] var14 = new PropertyValuesHolder[var6];

               while(true) {
                  var13 = var14;
                  if (var5 >= var6) {
                     break;
                  }

                  var14[var5] = (PropertyValuesHolder)var7.get(var5);
                  ++var5;
               }
            }

            return var13;
         }

         if (var6 != 2) {
            var3.next();
         } else {
            if (var3.getName().equals("propertyValuesHolder")) {
               TypedArray var11 = TypedArrayUtils.obtainAttributes(var1, var2, var4, AndroidResources.STYLEABLE_PROPERTY_VALUES_HOLDER);
               String var12 = TypedArrayUtils.getNamedString(var11, var3, "propertyName", 3);
               var5 = TypedArrayUtils.getNamedInt(var11, var3, "valueType", 2, 4);
               PropertyValuesHolder var8 = loadPvh(var0, var1, var2, var3, var12, var5);
               PropertyValuesHolder var9 = var8;
               if (var8 == null) {
                  var9 = getPVH(var11, var5, 0, 1, var12);
               }

               ArrayList var15 = var7;
               if (var9 != null) {
                  var15 = var7;
                  if (var7 == null) {
                     var15 = new ArrayList();
                  }

                  var15.add(var9);
               }

               var11.recycle();
               var7 = var15;
            }

            var3.next();
         }
      }
   }

   private static void parseAnimatorFromTypeArray(ValueAnimator var0, TypedArray var1, TypedArray var2, float var3, XmlPullParser var4) {
      long var8 = (long)TypedArrayUtils.getNamedInt(var1, var4, "duration", 1, 300);
      long var10 = (long)TypedArrayUtils.getNamedInt(var1, var4, "startOffset", 2, 0);
      int var6 = TypedArrayUtils.getNamedInt(var1, var4, "valueType", 7, 4);
      int var7 = var6;
      if (TypedArrayUtils.hasAttribute(var4, "valueFrom")) {
         var7 = var6;
         if (TypedArrayUtils.hasAttribute(var4, "valueTo")) {
            int var5 = var6;
            if (var6 == 4) {
               var5 = inferValueTypeFromValues(var1, 5, 6);
            }

            PropertyValuesHolder var12 = getPVH(var1, var5, 5, 6, "");
            var7 = var5;
            if (var12 != null) {
               var0.setValues(new PropertyValuesHolder[]{var12});
               var7 = var5;
            }
         }
      }

      var0.setDuration(var8);
      var0.setStartDelay(var10);
      var0.setRepeatCount(TypedArrayUtils.getNamedInt(var1, var4, "repeatCount", 3, 0));
      var0.setRepeatMode(TypedArrayUtils.getNamedInt(var1, var4, "repeatMode", 4, 1));
      if (var2 != null) {
         setupObjectAnimator(var0, var2, var7, var3, var4);
      }

   }

   private static void setupObjectAnimator(ValueAnimator var0, TypedArray var1, int var2, float var3, XmlPullParser var4) {
      ObjectAnimator var5 = (ObjectAnimator)var0;
      String var7 = TypedArrayUtils.getNamedString(var1, var4, "pathData", 1);
      if (var7 != null) {
         String var6 = TypedArrayUtils.getNamedString(var1, var4, "propertyXName", 2);
         String var8 = TypedArrayUtils.getNamedString(var1, var4, "propertyYName", 3);
         if (var2 != 2) {
         }

         if (var6 == null && var8 == null) {
            throw new InflateException(var1.getPositionDescription() + " propertyXName or propertyYName is needed for PathData");
         }

         setupPathMotion(PathParser.createPathFromPathData(var7), var5, var3 * 0.5F, var6, var8);
      } else {
         var5.setPropertyName(TypedArrayUtils.getNamedString(var1, var4, "propertyName", 0));
      }

   }

   private static void setupPathMotion(Path var0, ObjectAnimator var1, float var2, String var3, String var4) {
      PathMeasure var13 = new PathMeasure(var0, false);
      ArrayList var15 = new ArrayList();
      float var6 = 0.0F;
      var15.add(0.0F);
      float var5 = 0.0F;

      float var7;
      do {
         var7 = var5 + var13.getLength();
         var15.add(var7);
         var5 = var7;
      } while(var13.nextContour());

      PathMeasure var17 = new PathMeasure(var0, false);
      int var12 = Math.min(100, (int)(var7 / var2) + 1);
      float[] var18 = new float[var12];
      float[] var14 = new float[var12];
      float[] var16 = new float[2];
      var5 = var7 / (float)(var12 - 1);
      int var8 = 0;
      int var9 = 0;
      var2 = var6;

      while(true) {
         var13 = null;
         if (var8 >= var12) {
            PropertyValuesHolder var19;
            if (var3 != null) {
               var19 = PropertyValuesHolder.ofFloat(var3, var18);
            } else {
               var19 = null;
            }

            PropertyValuesHolder var20 = var13;
            if (var4 != null) {
               var20 = PropertyValuesHolder.ofFloat(var4, var14);
            }

            if (var19 == null) {
               var1.setValues(new PropertyValuesHolder[]{var20});
            } else if (var20 == null) {
               var1.setValues(new PropertyValuesHolder[]{var19});
            } else {
               var1.setValues(new PropertyValuesHolder[]{var19, var20});
            }

            return;
         }

         var17.getPosTan(var2 - (Float)var15.get(var9), var16, (float[])null);
         var18[var8] = var16[0];
         var14[var8] = var16[1];
         var2 += var5;
         int var11 = var9 + 1;
         int var10 = var9;
         if (var11 < var15.size()) {
            var10 = var9;
            if (var2 > (Float)var15.get(var11)) {
               var17.nextContour();
               var10 = var11;
            }
         }

         ++var8;
         var9 = var10;
      }
   }

   private static class PathDataEvaluator implements TypeEvaluator {
      private PathParser.PathDataNode[] mNodeArray;

      PathDataEvaluator() {
      }

      PathDataEvaluator(PathParser.PathDataNode[] var1) {
         this.mNodeArray = var1;
      }

      public PathParser.PathDataNode[] evaluate(float var1, PathParser.PathDataNode[] var2, PathParser.PathDataNode[] var3) {
         if (!PathParser.canMorph(var2, var3)) {
            throw new IllegalArgumentException("Can't interpolate between two incompatible pathData");
         } else {
            if (!PathParser.canMorph(this.mNodeArray, var2)) {
               this.mNodeArray = PathParser.deepCopyNodes(var2);
            }

            for(int var4 = 0; var4 < var2.length; ++var4) {
               this.mNodeArray[var4].interpolatePathDataNode(var2[var4], var3[var4], var1);
            }

            return this.mNodeArray;
         }
      }
   }
}
