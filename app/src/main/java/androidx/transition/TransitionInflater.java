package androidx.transition;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.util.AttributeSet;
import android.util.Xml;
import android.view.InflateException;
import android.view.ViewGroup;
import androidx.collection.ArrayMap;
import androidx.core.content.res.TypedArrayUtils;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class TransitionInflater {
   private static final ArrayMap CONSTRUCTORS = new ArrayMap();
   private static final Class[] CONSTRUCTOR_SIGNATURE = new Class[]{Context.class, AttributeSet.class};
   private final Context mContext;

   private TransitionInflater(Context var1) {
      this.mContext = var1;
   }

   private Object createCustom(AttributeSet param1, Class param2, String param3) {
      // $FF: Couldn't be decompiled
   }

   private Transition createTransitionFromXml(XmlPullParser var1, AttributeSet var2, Transition var3) throws XmlPullParserException, IOException {
      int var5 = var1.getDepth();
      TransitionSet var7;
      if (var3 instanceof TransitionSet) {
         var7 = (TransitionSet)var3;
      } else {
         var7 = null;
      }

      while(true) {
         Object var8 = null;

         while(true) {
            int var4 = var1.next();
            if (var4 == 3 && var1.getDepth() <= var5 || var4 == 1) {
               return (Transition)var8;
            }

            if (var4 == 2) {
               String var6 = var1.getName();
               Object var9;
               if ("fade".equals(var6)) {
                  var9 = new Fade(this.mContext, var2);
               } else if ("changeBounds".equals(var6)) {
                  var9 = new ChangeBounds(this.mContext, var2);
               } else if ("slide".equals(var6)) {
                  var9 = new Slide(this.mContext, var2);
               } else if ("explode".equals(var6)) {
                  var9 = new Explode(this.mContext, var2);
               } else if ("changeImageTransform".equals(var6)) {
                  var9 = new ChangeImageTransform(this.mContext, var2);
               } else if ("changeTransform".equals(var6)) {
                  var9 = new ChangeTransform(this.mContext, var2);
               } else if ("changeClipBounds".equals(var6)) {
                  var9 = new ChangeClipBounds(this.mContext, var2);
               } else if ("autoTransition".equals(var6)) {
                  var9 = new AutoTransition(this.mContext, var2);
               } else if ("changeScroll".equals(var6)) {
                  var9 = new ChangeScroll(this.mContext, var2);
               } else if ("transitionSet".equals(var6)) {
                  var9 = new TransitionSet(this.mContext, var2);
               } else if ("transition".equals(var6)) {
                  var9 = (Transition)this.createCustom(var2, Transition.class, "transition");
               } else if ("targets".equals(var6)) {
                  this.getTargetIds(var1, var2, var3);
                  var9 = var8;
               } else if ("arcMotion".equals(var6)) {
                  if (var3 == null) {
                     throw new RuntimeException("Invalid use of arcMotion element");
                  }

                  var3.setPathMotion(new ArcMotion(this.mContext, var2));
                  var9 = var8;
               } else if ("pathMotion".equals(var6)) {
                  if (var3 == null) {
                     throw new RuntimeException("Invalid use of pathMotion element");
                  }

                  var3.setPathMotion((PathMotion)this.createCustom(var2, PathMotion.class, "pathMotion"));
                  var9 = var8;
               } else {
                  if (!"patternPathMotion".equals(var6)) {
                     throw new RuntimeException("Unknown scene name: " + var1.getName());
                  }

                  if (var3 == null) {
                     throw new RuntimeException("Invalid use of patternPathMotion element");
                  }

                  var3.setPathMotion(new PatternPathMotion(this.mContext, var2));
                  var9 = var8;
               }

               var8 = var9;
               if (var9 != null) {
                  if (!var1.isEmptyElementTag()) {
                     this.createTransitionFromXml(var1, var2, (Transition)var9);
                  }

                  if (var7 != null) {
                     var7.addTransition((Transition)var9);
                     break;
                  }

                  if (var3 != null) {
                     throw new InflateException("Could not add transition to another transition.");
                  }

                  var8 = var9;
               }
            }
         }
      }
   }

   private TransitionManager createTransitionManagerFromXml(XmlPullParser var1, AttributeSet var2, ViewGroup var3) throws XmlPullParserException, IOException {
      int var4 = var1.getDepth();
      TransitionManager var6 = null;

      while(true) {
         int var5 = var1.next();
         if (var5 == 3 && var1.getDepth() <= var4 || var5 == 1) {
            return var6;
         }

         if (var5 == 2) {
            String var7 = var1.getName();
            if (var7.equals("transitionManager")) {
               var6 = new TransitionManager();
            } else {
               if (!var7.equals("transition") || var6 == null) {
                  throw new RuntimeException("Unknown scene name: " + var1.getName());
               }

               this.loadTransition(var2, var1, var3, var6);
            }
         }
      }
   }

   public static TransitionInflater from(Context var0) {
      return new TransitionInflater(var0);
   }

   private void getTargetIds(XmlPullParser var1, AttributeSet var2, Transition var3) throws XmlPullParserException, IOException {
      int var4 = var1.getDepth();

      while(true) {
         int var5 = var1.next();
         if (var5 == 3 && var1.getDepth() <= var4 || var5 == 1) {
            return;
         }

         if (var5 == 2) {
            if (!var1.getName().equals("target")) {
               throw new RuntimeException("Unknown scene name: " + var1.getName());
            }

            TypedArray var8 = this.mContext.obtainStyledAttributes(var2, Styleable.TRANSITION_TARGET);
            var5 = TypedArrayUtils.getNamedResourceId(var8, var1, "targetId", 1, 0);
            if (var5 != 0) {
               var3.addTarget(var5);
            } else {
               var5 = TypedArrayUtils.getNamedResourceId(var8, var1, "excludeId", 2, 0);
               if (var5 != 0) {
                  var3.excludeTarget(var5, true);
               } else {
                  String var6 = TypedArrayUtils.getNamedString(var8, var1, "targetName", 4);
                  if (var6 != null) {
                     var3.addTarget(var6);
                  } else {
                     var6 = TypedArrayUtils.getNamedString(var8, var1, "excludeName", 5);
                     if (var6 != null) {
                        var3.excludeTarget(var6, true);
                     } else {
                        label85: {
                           String var7 = TypedArrayUtils.getNamedString(var8, var1, "excludeClass", 3);
                           ClassNotFoundException var10000;
                           boolean var10001;
                           if (var7 != null) {
                              var6 = var7;

                              try {
                                 var3.excludeTarget(Class.forName(var7), true);
                                 break label85;
                              } catch (ClassNotFoundException var9) {
                                 var10000 = var9;
                                 var10001 = false;
                              }
                           } else {
                              label80: {
                                 var6 = var7;

                                 try {
                                    var7 = TypedArrayUtils.getNamedString(var8, var1, "targetClass", 0);
                                 } catch (ClassNotFoundException var11) {
                                    var10000 = var11;
                                    var10001 = false;
                                    break label80;
                                 }

                                 if (var7 == null) {
                                    break label85;
                                 }

                                 var6 = var7;

                                 try {
                                    var3.addTarget(Class.forName(var7));
                                    break label85;
                                 } catch (ClassNotFoundException var10) {
                                    var10000 = var10;
                                    var10001 = false;
                                 }
                              }
                           }

                           ClassNotFoundException var12 = var10000;
                           var8.recycle();
                           throw new RuntimeException("Could not create " + var6, var12);
                        }
                     }
                  }
               }
            }

            var8.recycle();
         }
      }
   }

   private void loadTransition(AttributeSet var1, XmlPullParser var2, ViewGroup var3, TransitionManager var4) throws Resources.NotFoundException {
      TypedArray var8 = this.mContext.obtainStyledAttributes(var1, Styleable.TRANSITION_MANAGER);
      int var5 = TypedArrayUtils.getNamedResourceId(var8, var2, "transition", 2, -1);
      int var6 = TypedArrayUtils.getNamedResourceId(var8, var2, "fromScene", 0, -1);
      Object var7 = null;
      Scene var9;
      if (var6 < 0) {
         var9 = null;
      } else {
         var9 = Scene.getSceneForLayout(var3, var6, this.mContext);
      }

      var6 = TypedArrayUtils.getNamedResourceId(var8, var2, "toScene", 1, -1);
      Scene var10;
      if (var6 < 0) {
         var10 = (Scene)var7;
      } else {
         var10 = Scene.getSceneForLayout(var3, var6, this.mContext);
      }

      if (var5 >= 0) {
         Transition var11 = this.inflateTransition(var5);
         if (var11 != null) {
            if (var10 == null) {
               throw new RuntimeException("No toScene for transition ID " + var5);
            }

            if (var9 == null) {
               var4.setTransition(var10, var11);
            } else {
               var4.setTransition(var9, var10, var11);
            }
         }
      }

      var8.recycle();
   }

   public Transition inflateTransition(int var1) {
      XmlResourceParser var2 = this.mContext.getResources().getXml(var1);

      Transition var12;
      try {
         var12 = this.createTransitionFromXml(var2, Xml.asAttributeSet(var2), (Transition)null);
      } catch (XmlPullParserException var9) {
         InflateException var3 = new InflateException(var9.getMessage(), var9);
         throw var3;
      } catch (IOException var10) {
         StringBuilder var4 = new StringBuilder();
         InflateException var5 = new InflateException(var4.append(var2.getPositionDescription()).append(": ").append(var10.getMessage()).toString(), var10);
         throw var5;
      } finally {
         var2.close();
      }

      return var12;
   }

   public TransitionManager inflateTransitionManager(int var1, ViewGroup var2) {
      XmlResourceParser var3 = this.mContext.getResources().getXml(var1);

      TransitionManager var13;
      try {
         var13 = this.createTransitionManagerFromXml(var3, Xml.asAttributeSet(var3), var2);
      } catch (XmlPullParserException var9) {
         InflateException var4 = new InflateException(var9.getMessage());
         var4.initCause(var9);
         throw var4;
      } catch (IOException var10) {
         StringBuilder var5 = new StringBuilder();
         InflateException var12 = new InflateException(var5.append(var3.getPositionDescription()).append(": ").append(var10.getMessage()).toString());
         var12.initCause(var10);
         throw var12;
      } finally {
         var3.close();
      }

      return var13;
   }
}
