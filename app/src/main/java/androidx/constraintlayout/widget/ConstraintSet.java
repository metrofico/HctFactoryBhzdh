package androidx.constraintlayout.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import android.util.Xml;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import org.xmlpull.v1.XmlPullParserException;

public class ConstraintSet {
   private static final int ALPHA = 43;
   private static final int BARRIER_ALLOWS_GONE_WIDGETS = 74;
   private static final int BARRIER_DIRECTION = 72;
   private static final int BARRIER_TYPE = 1;
   public static final int BASELINE = 5;
   private static final int BASELINE_TO_BASELINE = 1;
   public static final int BOTTOM = 4;
   private static final int BOTTOM_MARGIN = 2;
   private static final int BOTTOM_TO_BOTTOM = 3;
   private static final int BOTTOM_TO_TOP = 4;
   public static final int CHAIN_PACKED = 2;
   public static final int CHAIN_SPREAD = 0;
   public static final int CHAIN_SPREAD_INSIDE = 1;
   private static final int CHAIN_USE_RTL = 71;
   private static final int CIRCLE = 61;
   private static final int CIRCLE_ANGLE = 63;
   private static final int CIRCLE_RADIUS = 62;
   private static final int CONSTRAINT_REFERENCED_IDS = 73;
   private static final boolean DEBUG = false;
   private static final int DIMENSION_RATIO = 5;
   private static final int EDITOR_ABSOLUTE_X = 6;
   private static final int EDITOR_ABSOLUTE_Y = 7;
   private static final int ELEVATION = 44;
   public static final int END = 7;
   private static final int END_MARGIN = 8;
   private static final int END_TO_END = 9;
   private static final int END_TO_START = 10;
   public static final int GONE = 8;
   private static final int GONE_BOTTOM_MARGIN = 11;
   private static final int GONE_END_MARGIN = 12;
   private static final int GONE_LEFT_MARGIN = 13;
   private static final int GONE_RIGHT_MARGIN = 14;
   private static final int GONE_START_MARGIN = 15;
   private static final int GONE_TOP_MARGIN = 16;
   private static final int GUIDE_BEGIN = 17;
   private static final int GUIDE_END = 18;
   private static final int GUIDE_PERCENT = 19;
   private static final int HEIGHT_DEFAULT = 55;
   private static final int HEIGHT_MAX = 57;
   private static final int HEIGHT_MIN = 59;
   private static final int HEIGHT_PERCENT = 70;
   public static final int HORIZONTAL = 0;
   private static final int HORIZONTAL_BIAS = 20;
   public static final int HORIZONTAL_GUIDELINE = 0;
   private static final int HORIZONTAL_STYLE = 41;
   private static final int HORIZONTAL_WEIGHT = 39;
   public static final int INVISIBLE = 4;
   private static final int LAYOUT_HEIGHT = 21;
   private static final int LAYOUT_VISIBILITY = 22;
   private static final int LAYOUT_WIDTH = 23;
   public static final int LEFT = 1;
   private static final int LEFT_MARGIN = 24;
   private static final int LEFT_TO_LEFT = 25;
   private static final int LEFT_TO_RIGHT = 26;
   public static final int MATCH_CONSTRAINT = 0;
   public static final int MATCH_CONSTRAINT_SPREAD = 0;
   public static final int MATCH_CONSTRAINT_WRAP = 1;
   private static final int ORIENTATION = 27;
   public static final int PARENT_ID = 0;
   public static final int RIGHT = 2;
   private static final int RIGHT_MARGIN = 28;
   private static final int RIGHT_TO_LEFT = 29;
   private static final int RIGHT_TO_RIGHT = 30;
   private static final int ROTATION = 60;
   private static final int ROTATION_X = 45;
   private static final int ROTATION_Y = 46;
   private static final int SCALE_X = 47;
   private static final int SCALE_Y = 48;
   public static final int START = 6;
   private static final int START_MARGIN = 31;
   private static final int START_TO_END = 32;
   private static final int START_TO_START = 33;
   private static final String TAG = "ConstraintSet";
   public static final int TOP = 3;
   private static final int TOP_MARGIN = 34;
   private static final int TOP_TO_BOTTOM = 35;
   private static final int TOP_TO_TOP = 36;
   private static final int TRANSFORM_PIVOT_X = 49;
   private static final int TRANSFORM_PIVOT_Y = 50;
   private static final int TRANSLATION_X = 51;
   private static final int TRANSLATION_Y = 52;
   private static final int TRANSLATION_Z = 53;
   public static final int UNSET = -1;
   private static final int UNUSED = 75;
   public static final int VERTICAL = 1;
   private static final int VERTICAL_BIAS = 37;
   public static final int VERTICAL_GUIDELINE = 1;
   private static final int VERTICAL_STYLE = 42;
   private static final int VERTICAL_WEIGHT = 40;
   private static final int VIEW_ID = 38;
   private static final int[] VISIBILITY_FLAGS = new int[]{0, 4, 8};
   public static final int VISIBLE = 0;
   private static final int WIDTH_DEFAULT = 54;
   private static final int WIDTH_MAX = 56;
   private static final int WIDTH_MIN = 58;
   private static final int WIDTH_PERCENT = 69;
   public static final int WRAP_CONTENT = -2;
   private static SparseIntArray mapToConstant;
   private HashMap mConstraints = new HashMap();

   static {
      SparseIntArray var0 = new SparseIntArray();
      mapToConstant = var0;
      var0.append(R.styleable.ConstraintSet_layout_constraintLeft_toLeftOf, 25);
      mapToConstant.append(R.styleable.ConstraintSet_layout_constraintLeft_toRightOf, 26);
      mapToConstant.append(R.styleable.ConstraintSet_layout_constraintRight_toLeftOf, 29);
      mapToConstant.append(R.styleable.ConstraintSet_layout_constraintRight_toRightOf, 30);
      mapToConstant.append(R.styleable.ConstraintSet_layout_constraintTop_toTopOf, 36);
      mapToConstant.append(R.styleable.ConstraintSet_layout_constraintTop_toBottomOf, 35);
      mapToConstant.append(R.styleable.ConstraintSet_layout_constraintBottom_toTopOf, 4);
      mapToConstant.append(R.styleable.ConstraintSet_layout_constraintBottom_toBottomOf, 3);
      mapToConstant.append(R.styleable.ConstraintSet_layout_constraintBaseline_toBaselineOf, 1);
      mapToConstant.append(R.styleable.ConstraintSet_layout_editor_absoluteX, 6);
      mapToConstant.append(R.styleable.ConstraintSet_layout_editor_absoluteY, 7);
      mapToConstant.append(R.styleable.ConstraintSet_layout_constraintGuide_begin, 17);
      mapToConstant.append(R.styleable.ConstraintSet_layout_constraintGuide_end, 18);
      mapToConstant.append(R.styleable.ConstraintSet_layout_constraintGuide_percent, 19);
      mapToConstant.append(R.styleable.ConstraintSet_android_orientation, 27);
      mapToConstant.append(R.styleable.ConstraintSet_layout_constraintStart_toEndOf, 32);
      mapToConstant.append(R.styleable.ConstraintSet_layout_constraintStart_toStartOf, 33);
      mapToConstant.append(R.styleable.ConstraintSet_layout_constraintEnd_toStartOf, 10);
      mapToConstant.append(R.styleable.ConstraintSet_layout_constraintEnd_toEndOf, 9);
      mapToConstant.append(R.styleable.ConstraintSet_layout_goneMarginLeft, 13);
      mapToConstant.append(R.styleable.ConstraintSet_layout_goneMarginTop, 16);
      mapToConstant.append(R.styleable.ConstraintSet_layout_goneMarginRight, 14);
      mapToConstant.append(R.styleable.ConstraintSet_layout_goneMarginBottom, 11);
      mapToConstant.append(R.styleable.ConstraintSet_layout_goneMarginStart, 15);
      mapToConstant.append(R.styleable.ConstraintSet_layout_goneMarginEnd, 12);
      mapToConstant.append(R.styleable.ConstraintSet_layout_constraintVertical_weight, 40);
      mapToConstant.append(R.styleable.ConstraintSet_layout_constraintHorizontal_weight, 39);
      mapToConstant.append(R.styleable.ConstraintSet_layout_constraintHorizontal_chainStyle, 41);
      mapToConstant.append(R.styleable.ConstraintSet_layout_constraintVertical_chainStyle, 42);
      mapToConstant.append(R.styleable.ConstraintSet_layout_constraintHorizontal_bias, 20);
      mapToConstant.append(R.styleable.ConstraintSet_layout_constraintVertical_bias, 37);
      mapToConstant.append(R.styleable.ConstraintSet_layout_constraintDimensionRatio, 5);
      mapToConstant.append(R.styleable.ConstraintSet_layout_constraintLeft_creator, 75);
      mapToConstant.append(R.styleable.ConstraintSet_layout_constraintTop_creator, 75);
      mapToConstant.append(R.styleable.ConstraintSet_layout_constraintRight_creator, 75);
      mapToConstant.append(R.styleable.ConstraintSet_layout_constraintBottom_creator, 75);
      mapToConstant.append(R.styleable.ConstraintSet_layout_constraintBaseline_creator, 75);
      mapToConstant.append(R.styleable.ConstraintSet_android_layout_marginLeft, 24);
      mapToConstant.append(R.styleable.ConstraintSet_android_layout_marginRight, 28);
      mapToConstant.append(R.styleable.ConstraintSet_android_layout_marginStart, 31);
      mapToConstant.append(R.styleable.ConstraintSet_android_layout_marginEnd, 8);
      mapToConstant.append(R.styleable.ConstraintSet_android_layout_marginTop, 34);
      mapToConstant.append(R.styleable.ConstraintSet_android_layout_marginBottom, 2);
      mapToConstant.append(R.styleable.ConstraintSet_android_layout_width, 23);
      mapToConstant.append(R.styleable.ConstraintSet_android_layout_height, 21);
      mapToConstant.append(R.styleable.ConstraintSet_android_visibility, 22);
      mapToConstant.append(R.styleable.ConstraintSet_android_alpha, 43);
      mapToConstant.append(R.styleable.ConstraintSet_android_elevation, 44);
      mapToConstant.append(R.styleable.ConstraintSet_android_rotationX, 45);
      mapToConstant.append(R.styleable.ConstraintSet_android_rotationY, 46);
      mapToConstant.append(R.styleable.ConstraintSet_android_rotation, 60);
      mapToConstant.append(R.styleable.ConstraintSet_android_scaleX, 47);
      mapToConstant.append(R.styleable.ConstraintSet_android_scaleY, 48);
      mapToConstant.append(R.styleable.ConstraintSet_android_transformPivotX, 49);
      mapToConstant.append(R.styleable.ConstraintSet_android_transformPivotY, 50);
      mapToConstant.append(R.styleable.ConstraintSet_android_translationX, 51);
      mapToConstant.append(R.styleable.ConstraintSet_android_translationY, 52);
      mapToConstant.append(R.styleable.ConstraintSet_android_translationZ, 53);
      mapToConstant.append(R.styleable.ConstraintSet_layout_constraintWidth_default, 54);
      mapToConstant.append(R.styleable.ConstraintSet_layout_constraintHeight_default, 55);
      mapToConstant.append(R.styleable.ConstraintSet_layout_constraintWidth_max, 56);
      mapToConstant.append(R.styleable.ConstraintSet_layout_constraintHeight_max, 57);
      mapToConstant.append(R.styleable.ConstraintSet_layout_constraintWidth_min, 58);
      mapToConstant.append(R.styleable.ConstraintSet_layout_constraintHeight_min, 59);
      mapToConstant.append(R.styleable.ConstraintSet_layout_constraintCircle, 61);
      mapToConstant.append(R.styleable.ConstraintSet_layout_constraintCircleRadius, 62);
      mapToConstant.append(R.styleable.ConstraintSet_layout_constraintCircleAngle, 63);
      mapToConstant.append(R.styleable.ConstraintSet_android_id, 38);
      mapToConstant.append(R.styleable.ConstraintSet_layout_constraintWidth_percent, 69);
      mapToConstant.append(R.styleable.ConstraintSet_layout_constraintHeight_percent, 70);
      mapToConstant.append(R.styleable.ConstraintSet_chainUseRtl, 71);
      mapToConstant.append(R.styleable.ConstraintSet_barrierDirection, 72);
      mapToConstant.append(R.styleable.ConstraintSet_constraint_referenced_ids, 73);
      mapToConstant.append(R.styleable.ConstraintSet_barrierAllowsGoneWidgets, 74);
   }

   private int[] convertReferenceString(View var1, String var2) {
      String[] var7 = var2.split(",");
      Context var8 = var1.getContext();
      int[] var13 = new int[var7.length];
      int var6 = 0;

      int var5;
      for(var5 = 0; var6 < var7.length; ++var5) {
         String var10 = var7[var6].trim();

         int var4;
         try {
            var4 = R.id.class.getField(var10).getInt((Object)null);
         } catch (Exception var11) {
            var4 = 0;
         }

         int var3 = var4;
         if (var4 == 0) {
            var3 = var8.getResources().getIdentifier(var10, "id", var8.getPackageName());
         }

         var4 = var3;
         if (var3 == 0) {
            var4 = var3;
            if (var1.isInEditMode()) {
               var4 = var3;
               if (var1.getParent() instanceof ConstraintLayout) {
                  Object var9 = ((ConstraintLayout)var1.getParent()).getDesignInformation(0, var10);
                  var4 = var3;
                  if (var9 != null) {
                     var4 = var3;
                     if (var9 instanceof Integer) {
                        var4 = (Integer)var9;
                     }
                  }
               }
            }
         }

         var13[var5] = var4;
         ++var6;
      }

      int[] var12 = var13;
      if (var5 != var7.length) {
         var12 = Arrays.copyOf(var13, var5);
      }

      return var12;
   }

   private void createHorizontalChain(int var1, int var2, int var3, int var4, int[] var5, float[] var6, int var7, int var8, int var9) {
      if (var5.length >= 2) {
         if (var6 != null && var6.length != var5.length) {
            throw new IllegalArgumentException("must have 2 or more widgets in a chain");
         } else {
            if (var6 != null) {
               this.get(var5[0]).horizontalWeight = var6[0];
            }

            this.get(var5[0]).horizontalChainStyle = var7;
            this.connect(var5[0], var8, var1, var2, -1);

            for(var1 = 1; var1 < var5.length; ++var1) {
               var2 = var5[var1];
               var7 = var1 - 1;
               this.connect(var2, var8, var5[var7], var9, -1);
               this.connect(var5[var7], var9, var5[var1], var8, -1);
               if (var6 != null) {
                  this.get(var5[var1]).horizontalWeight = var6[var1];
               }
            }

            this.connect(var5[var5.length - 1], var9, var3, var4, -1);
         }
      } else {
         throw new IllegalArgumentException("must have 2 or more widgets in a chain");
      }
   }

   private Constraint fillFromAttributeList(Context var1, AttributeSet var2) {
      Constraint var3 = new Constraint();
      TypedArray var4 = var1.obtainStyledAttributes(var2, R.styleable.ConstraintSet);
      this.populateConstraint(var3, var4);
      var4.recycle();
      return var3;
   }

   private Constraint get(int var1) {
      if (!this.mConstraints.containsKey(var1)) {
         this.mConstraints.put(var1, new Constraint());
      }

      return (Constraint)this.mConstraints.get(var1);
   }

   private static int lookupID(TypedArray var0, int var1, int var2) {
      int var3 = var0.getResourceId(var1, var2);
      var2 = var3;
      if (var3 == -1) {
         var2 = var0.getInt(var1, -1);
      }

      return var2;
   }

   private void populateConstraint(Constraint var1, TypedArray var2) {
      int var4 = var2.getIndexCount();

      for(int var3 = 0; var3 < var4; ++var3) {
         int var6 = var2.getIndex(var3);
         int var5 = mapToConstant.get(var6);
         switch (var5) {
            case 1:
               var1.baselineToBaseline = lookupID(var2, var6, var1.baselineToBaseline);
               break;
            case 2:
               var1.bottomMargin = var2.getDimensionPixelSize(var6, var1.bottomMargin);
               break;
            case 3:
               var1.bottomToBottom = lookupID(var2, var6, var1.bottomToBottom);
               break;
            case 4:
               var1.bottomToTop = lookupID(var2, var6, var1.bottomToTop);
               break;
            case 5:
               var1.dimensionRatio = var2.getString(var6);
               break;
            case 6:
               var1.editorAbsoluteX = var2.getDimensionPixelOffset(var6, var1.editorAbsoluteX);
               break;
            case 7:
               var1.editorAbsoluteY = var2.getDimensionPixelOffset(var6, var1.editorAbsoluteY);
               break;
            case 8:
               var1.endMargin = var2.getDimensionPixelSize(var6, var1.endMargin);
               break;
            case 9:
               var1.endToEnd = lookupID(var2, var6, var1.endToEnd);
               break;
            case 10:
               var1.endToStart = lookupID(var2, var6, var1.endToStart);
               break;
            case 11:
               var1.goneBottomMargin = var2.getDimensionPixelSize(var6, var1.goneBottomMargin);
               break;
            case 12:
               var1.goneEndMargin = var2.getDimensionPixelSize(var6, var1.goneEndMargin);
               break;
            case 13:
               var1.goneLeftMargin = var2.getDimensionPixelSize(var6, var1.goneLeftMargin);
               break;
            case 14:
               var1.goneRightMargin = var2.getDimensionPixelSize(var6, var1.goneRightMargin);
               break;
            case 15:
               var1.goneStartMargin = var2.getDimensionPixelSize(var6, var1.goneStartMargin);
               break;
            case 16:
               var1.goneTopMargin = var2.getDimensionPixelSize(var6, var1.goneTopMargin);
               break;
            case 17:
               var1.guideBegin = var2.getDimensionPixelOffset(var6, var1.guideBegin);
               break;
            case 18:
               var1.guideEnd = var2.getDimensionPixelOffset(var6, var1.guideEnd);
               break;
            case 19:
               var1.guidePercent = var2.getFloat(var6, var1.guidePercent);
               break;
            case 20:
               var1.horizontalBias = var2.getFloat(var6, var1.horizontalBias);
               break;
            case 21:
               var1.mHeight = var2.getLayoutDimension(var6, var1.mHeight);
               break;
            case 22:
               var1.visibility = var2.getInt(var6, var1.visibility);
               var1.visibility = VISIBILITY_FLAGS[var1.visibility];
               break;
            case 23:
               var1.mWidth = var2.getLayoutDimension(var6, var1.mWidth);
               break;
            case 24:
               var1.leftMargin = var2.getDimensionPixelSize(var6, var1.leftMargin);
               break;
            case 25:
               var1.leftToLeft = lookupID(var2, var6, var1.leftToLeft);
               break;
            case 26:
               var1.leftToRight = lookupID(var2, var6, var1.leftToRight);
               break;
            case 27:
               var1.orientation = var2.getInt(var6, var1.orientation);
               break;
            case 28:
               var1.rightMargin = var2.getDimensionPixelSize(var6, var1.rightMargin);
               break;
            case 29:
               var1.rightToLeft = lookupID(var2, var6, var1.rightToLeft);
               break;
            case 30:
               var1.rightToRight = lookupID(var2, var6, var1.rightToRight);
               break;
            case 31:
               var1.startMargin = var2.getDimensionPixelSize(var6, var1.startMargin);
               break;
            case 32:
               var1.startToEnd = lookupID(var2, var6, var1.startToEnd);
               break;
            case 33:
               var1.startToStart = lookupID(var2, var6, var1.startToStart);
               break;
            case 34:
               var1.topMargin = var2.getDimensionPixelSize(var6, var1.topMargin);
               break;
            case 35:
               var1.topToBottom = lookupID(var2, var6, var1.topToBottom);
               break;
            case 36:
               var1.topToTop = lookupID(var2, var6, var1.topToTop);
               break;
            case 37:
               var1.verticalBias = var2.getFloat(var6, var1.verticalBias);
               break;
            case 38:
               var1.mViewId = var2.getResourceId(var6, var1.mViewId);
               break;
            case 39:
               var1.horizontalWeight = var2.getFloat(var6, var1.horizontalWeight);
               break;
            case 40:
               var1.verticalWeight = var2.getFloat(var6, var1.verticalWeight);
               break;
            case 41:
               var1.horizontalChainStyle = var2.getInt(var6, var1.horizontalChainStyle);
               break;
            case 42:
               var1.verticalChainStyle = var2.getInt(var6, var1.verticalChainStyle);
               break;
            case 43:
               var1.alpha = var2.getFloat(var6, var1.alpha);
               break;
            case 44:
               var1.applyElevation = true;
               var1.elevation = var2.getDimension(var6, var1.elevation);
               break;
            case 45:
               var1.rotationX = var2.getFloat(var6, var1.rotationX);
               break;
            case 46:
               var1.rotationY = var2.getFloat(var6, var1.rotationY);
               break;
            case 47:
               var1.scaleX = var2.getFloat(var6, var1.scaleX);
               break;
            case 48:
               var1.scaleY = var2.getFloat(var6, var1.scaleY);
               break;
            case 49:
               var1.transformPivotX = var2.getFloat(var6, var1.transformPivotX);
               break;
            case 50:
               var1.transformPivotY = var2.getFloat(var6, var1.transformPivotY);
               break;
            case 51:
               var1.translationX = var2.getDimension(var6, var1.translationX);
               break;
            case 52:
               var1.translationY = var2.getDimension(var6, var1.translationY);
               break;
            case 53:
               var1.translationZ = var2.getDimension(var6, var1.translationZ);
               break;
            default:
               switch (var5) {
                  case 60:
                     var1.rotation = var2.getFloat(var6, var1.rotation);
                     break;
                  case 61:
                     var1.circleConstraint = lookupID(var2, var6, var1.circleConstraint);
                     break;
                  case 62:
                     var1.circleRadius = var2.getDimensionPixelSize(var6, var1.circleRadius);
                     break;
                  case 63:
                     var1.circleAngle = var2.getFloat(var6, var1.circleAngle);
                     break;
                  default:
                     switch (var5) {
                        case 69:
                           var1.widthPercent = var2.getFloat(var6, 1.0F);
                           break;
                        case 70:
                           var1.heightPercent = var2.getFloat(var6, 1.0F);
                           break;
                        case 71:
                           Log.e("ConstraintSet", "CURRENTLY UNSUPPORTED");
                           break;
                        case 72:
                           var1.mBarrierDirection = var2.getInt(var6, var1.mBarrierDirection);
                           break;
                        case 73:
                           var1.mReferenceIdString = var2.getString(var6);
                           break;
                        case 74:
                           var1.mBarrierAllowsGoneWidgets = var2.getBoolean(var6, var1.mBarrierAllowsGoneWidgets);
                           break;
                        case 75:
                           Log.w("ConstraintSet", "unused attribute 0x" + Integer.toHexString(var6) + "   " + mapToConstant.get(var6));
                           break;
                        default:
                           Log.w("ConstraintSet", "Unknown attribute 0x" + Integer.toHexString(var6) + "   " + mapToConstant.get(var6));
                     }
               }
         }
      }

   }

   private String sideToString(int var1) {
      switch (var1) {
         case 1:
            return "left";
         case 2:
            return "right";
         case 3:
            return "top";
         case 4:
            return "bottom";
         case 5:
            return "baseline";
         case 6:
            return "start";
         case 7:
            return "end";
         default:
            return "undefined";
      }
   }

   public void addToHorizontalChain(int var1, int var2, int var3) {
      byte var4;
      if (var2 == 0) {
         var4 = 1;
      } else {
         var4 = 2;
      }

      this.connect(var1, 1, var2, var4, 0);
      if (var3 == 0) {
         var4 = 2;
      } else {
         var4 = 1;
      }

      this.connect(var1, 2, var3, var4, 0);
      if (var2 != 0) {
         this.connect(var2, 2, var1, 1, 0);
      }

      if (var3 != 0) {
         this.connect(var3, 1, var1, 2, 0);
      }

   }

   public void addToHorizontalChainRTL(int var1, int var2, int var3) {
      byte var4;
      if (var2 == 0) {
         var4 = 6;
      } else {
         var4 = 7;
      }

      this.connect(var1, 6, var2, var4, 0);
      if (var3 == 0) {
         var4 = 7;
      } else {
         var4 = 6;
      }

      this.connect(var1, 7, var3, var4, 0);
      if (var2 != 0) {
         this.connect(var2, 7, var1, 6, 0);
      }

      if (var3 != 0) {
         this.connect(var3, 6, var1, 7, 0);
      }

   }

   public void addToVerticalChain(int var1, int var2, int var3) {
      byte var4;
      if (var2 == 0) {
         var4 = 3;
      } else {
         var4 = 4;
      }

      this.connect(var1, 3, var2, var4, 0);
      if (var3 == 0) {
         var4 = 4;
      } else {
         var4 = 3;
      }

      this.connect(var1, 4, var3, var4, 0);
      if (var2 != 0) {
         this.connect(var2, 4, var1, 3, 0);
      }

      if (var2 != 0) {
         this.connect(var3, 3, var1, 4, 0);
      }

   }

   public void applyTo(ConstraintLayout var1) {
      this.applyToInternal(var1);
      var1.setConstraintSet((ConstraintSet)null);
   }

   void applyToInternal(ConstraintLayout var1) {
      int var3 = var1.getChildCount();
      HashSet var6 = new HashSet(this.mConstraints.keySet());

      Barrier var8;
      for(int var2 = 0; var2 < var3; ++var2) {
         View var5 = var1.getChildAt(var2);
         int var4 = var5.getId();
         if (var4 == -1) {
            throw new RuntimeException("All children of ConstraintLayout must have ids to use ConstraintSet");
         }

         if (this.mConstraints.containsKey(var4)) {
            var6.remove(var4);
            Constraint var7 = (Constraint)this.mConstraints.get(var4);
            if (var5 instanceof Barrier) {
               var7.mHelperType = 1;
            }

            if (var7.mHelperType != -1 && var7.mHelperType == 1) {
               var8 = (Barrier)var5;
               var8.setId(var4);
               var8.setType(var7.mBarrierDirection);
               var8.setAllowsGoneWidget(var7.mBarrierAllowsGoneWidgets);
               if (var7.mReferenceIds != null) {
                  var8.setReferencedIds(var7.mReferenceIds);
               } else if (var7.mReferenceIdString != null) {
                  var7.mReferenceIds = this.convertReferenceString(var8, var7.mReferenceIdString);
                  var8.setReferencedIds(var7.mReferenceIds);
               }
            }

            ConstraintLayout.LayoutParams var14 = (ConstraintLayout.LayoutParams)var5.getLayoutParams();
            var7.applyTo(var14);
            var5.setLayoutParams(var14);
            var5.setVisibility(var7.visibility);
            if (VERSION.SDK_INT >= 17) {
               var5.setAlpha(var7.alpha);
               var5.setRotation(var7.rotation);
               var5.setRotationX(var7.rotationX);
               var5.setRotationY(var7.rotationY);
               var5.setScaleX(var7.scaleX);
               var5.setScaleY(var7.scaleY);
               if (!Float.isNaN(var7.transformPivotX)) {
                  var5.setPivotX(var7.transformPivotX);
               }

               if (!Float.isNaN(var7.transformPivotY)) {
                  var5.setPivotY(var7.transformPivotY);
               }

               var5.setTranslationX(var7.translationX);
               var5.setTranslationY(var7.translationY);
               if (VERSION.SDK_INT >= 21) {
                  var5.setTranslationZ(var7.translationZ);
                  if (var7.applyElevation) {
                     var5.setElevation(var7.elevation);
                  }
               }
            }
         }
      }

      Iterator var11 = var6.iterator();

      while(var11.hasNext()) {
         Integer var12 = (Integer)var11.next();
         Constraint var10 = (Constraint)this.mConstraints.get(var12);
         if (var10.mHelperType != -1 && var10.mHelperType == 1) {
            var8 = new Barrier(var1.getContext());
            var8.setId(var12);
            if (var10.mReferenceIds != null) {
               var8.setReferencedIds(var10.mReferenceIds);
            } else if (var10.mReferenceIdString != null) {
               var10.mReferenceIds = this.convertReferenceString(var8, var10.mReferenceIdString);
               var8.setReferencedIds(var10.mReferenceIds);
            }

            var8.setType(var10.mBarrierDirection);
            ConstraintLayout.LayoutParams var9 = var1.generateDefaultLayoutParams();
            var8.validateParams();
            var10.applyTo(var9);
            var1.addView(var8, var9);
         }

         if (var10.mIsGuideline) {
            Guideline var15 = new Guideline(var1.getContext());
            var15.setId(var12);
            ConstraintLayout.LayoutParams var13 = var1.generateDefaultLayoutParams();
            var10.applyTo(var13);
            var1.addView(var15, var13);
         }
      }

   }

   public void center(int var1, int var2, int var3, int var4, int var5, int var6, int var7, float var8) {
      if (var4 >= 0) {
         if (var7 < 0) {
            throw new IllegalArgumentException("margin must be > 0");
         } else if (!(var8 <= 0.0F) && !(var8 > 1.0F)) {
            if (var3 != 1 && var3 != 2) {
               if (var3 != 6 && var3 != 7) {
                  this.connect(var1, 3, var2, var3, var4);
                  this.connect(var1, 4, var5, var6, var7);
                  ((Constraint)this.mConstraints.get(var1)).verticalBias = var8;
               } else {
                  this.connect(var1, 6, var2, var3, var4);
                  this.connect(var1, 7, var5, var6, var7);
                  ((Constraint)this.mConstraints.get(var1)).horizontalBias = var8;
               }
            } else {
               this.connect(var1, 1, var2, var3, var4);
               this.connect(var1, 2, var5, var6, var7);
               ((Constraint)this.mConstraints.get(var1)).horizontalBias = var8;
            }

         } else {
            throw new IllegalArgumentException("bias must be between 0 and 1 inclusive");
         }
      } else {
         throw new IllegalArgumentException("margin must be > 0");
      }
   }

   public void centerHorizontally(int var1, int var2) {
      if (var2 == 0) {
         this.center(var1, 0, 1, 0, 0, 2, 0, 0.5F);
      } else {
         this.center(var1, var2, 2, 0, var2, 1, 0, 0.5F);
      }

   }

   public void centerHorizontally(int var1, int var2, int var3, int var4, int var5, int var6, int var7, float var8) {
      this.connect(var1, 1, var2, var3, var4);
      this.connect(var1, 2, var5, var6, var7);
      ((Constraint)this.mConstraints.get(var1)).horizontalBias = var8;
   }

   public void centerHorizontallyRtl(int var1, int var2) {
      if (var2 == 0) {
         this.center(var1, 0, 6, 0, 0, 7, 0, 0.5F);
      } else {
         this.center(var1, var2, 7, 0, var2, 6, 0, 0.5F);
      }

   }

   public void centerHorizontallyRtl(int var1, int var2, int var3, int var4, int var5, int var6, int var7, float var8) {
      this.connect(var1, 6, var2, var3, var4);
      this.connect(var1, 7, var5, var6, var7);
      ((Constraint)this.mConstraints.get(var1)).horizontalBias = var8;
   }

   public void centerVertically(int var1, int var2) {
      if (var2 == 0) {
         this.center(var1, 0, 3, 0, 0, 4, 0, 0.5F);
      } else {
         this.center(var1, var2, 4, 0, var2, 3, 0, 0.5F);
      }

   }

   public void centerVertically(int var1, int var2, int var3, int var4, int var5, int var6, int var7, float var8) {
      this.connect(var1, 3, var2, var3, var4);
      this.connect(var1, 4, var5, var6, var7);
      ((Constraint)this.mConstraints.get(var1)).verticalBias = var8;
   }

   public void clear(int var1) {
      this.mConstraints.remove(var1);
   }

   public void clear(int var1, int var2) {
      if (this.mConstraints.containsKey(var1)) {
         Constraint var3 = (Constraint)this.mConstraints.get(var1);
         switch (var2) {
            case 1:
               var3.leftToRight = -1;
               var3.leftToLeft = -1;
               var3.leftMargin = -1;
               var3.goneLeftMargin = -1;
               break;
            case 2:
               var3.rightToRight = -1;
               var3.rightToLeft = -1;
               var3.rightMargin = -1;
               var3.goneRightMargin = -1;
               break;
            case 3:
               var3.topToBottom = -1;
               var3.topToTop = -1;
               var3.topMargin = -1;
               var3.goneTopMargin = -1;
               break;
            case 4:
               var3.bottomToTop = -1;
               var3.bottomToBottom = -1;
               var3.bottomMargin = -1;
               var3.goneBottomMargin = -1;
               break;
            case 5:
               var3.baselineToBaseline = -1;
               break;
            case 6:
               var3.startToEnd = -1;
               var3.startToStart = -1;
               var3.startMargin = -1;
               var3.goneStartMargin = -1;
               break;
            case 7:
               var3.endToStart = -1;
               var3.endToEnd = -1;
               var3.endMargin = -1;
               var3.goneEndMargin = -1;
               break;
            default:
               throw new IllegalArgumentException("unknown constraint");
         }
      }

   }

   public void clone(Context var1, int var2) {
      this.clone((ConstraintLayout)LayoutInflater.from(var1).inflate(var2, (ViewGroup)null));
   }

   public void clone(ConstraintLayout var1) {
      int var5 = var1.getChildCount();
      this.mConstraints.clear();

      for(int var4 = 0; var4 < var5; ++var4) {
         View var8 = var1.getChildAt(var4);
         ConstraintLayout.LayoutParams var9 = (ConstraintLayout.LayoutParams)var8.getLayoutParams();
         int var6 = var8.getId();
         if (var6 == -1) {
            throw new RuntimeException("All children of ConstraintLayout must have ids to use ConstraintSet");
         }

         if (!this.mConstraints.containsKey(var6)) {
            this.mConstraints.put(var6, new Constraint());
         }

         Constraint var7 = (Constraint)this.mConstraints.get(var6);
         var7.fillFrom(var6, var9);
         var7.visibility = var8.getVisibility();
         if (VERSION.SDK_INT >= 17) {
            var7.alpha = var8.getAlpha();
            var7.rotation = var8.getRotation();
            var7.rotationX = var8.getRotationX();
            var7.rotationY = var8.getRotationY();
            var7.scaleX = var8.getScaleX();
            var7.scaleY = var8.getScaleY();
            float var3 = var8.getPivotX();
            float var2 = var8.getPivotY();
            if ((double)var3 != 0.0 || (double)var2 != 0.0) {
               var7.transformPivotX = var3;
               var7.transformPivotY = var2;
            }

            var7.translationX = var8.getTranslationX();
            var7.translationY = var8.getTranslationY();
            if (VERSION.SDK_INT >= 21) {
               var7.translationZ = var8.getTranslationZ();
               if (var7.applyElevation) {
                  var7.elevation = var8.getElevation();
               }
            }
         }

         if (var8 instanceof Barrier) {
            Barrier var10 = (Barrier)var8;
            var7.mBarrierAllowsGoneWidgets = var10.allowsGoneWidget();
            var7.mReferenceIds = var10.getReferencedIds();
            var7.mBarrierDirection = var10.getType();
         }
      }

   }

   public void clone(ConstraintSet var1) {
      this.mConstraints.clear();
      Iterator var2 = var1.mConstraints.keySet().iterator();

      while(var2.hasNext()) {
         Integer var3 = (Integer)var2.next();
         this.mConstraints.put(var3, ((Constraint)var1.mConstraints.get(var3)).clone());
      }

   }

   public void clone(Constraints var1) {
      int var3 = var1.getChildCount();
      this.mConstraints.clear();

      for(int var2 = 0; var2 < var3; ++var2) {
         View var5 = var1.getChildAt(var2);
         Constraints.LayoutParams var6 = (Constraints.LayoutParams)var5.getLayoutParams();
         int var4 = var5.getId();
         if (var4 == -1) {
            throw new RuntimeException("All children of ConstraintLayout must have ids to use ConstraintSet");
         }

         if (!this.mConstraints.containsKey(var4)) {
            this.mConstraints.put(var4, new Constraint());
         }

         Constraint var7 = (Constraint)this.mConstraints.get(var4);
         if (var5 instanceof ConstraintHelper) {
            var7.fillFromConstraints((ConstraintHelper)var5, var4, var6);
         }

         var7.fillFromConstraints(var4, var6);
      }

   }

   public void connect(int var1, int var2, int var3, int var4) {
      if (!this.mConstraints.containsKey(var1)) {
         this.mConstraints.put(var1, new Constraint());
      }

      Constraint var5 = (Constraint)this.mConstraints.get(var1);
      switch (var2) {
         case 1:
            if (var4 == 1) {
               var5.leftToLeft = var3;
               var5.leftToRight = -1;
            } else {
               if (var4 != 2) {
                  throw new IllegalArgumentException("left to " + this.sideToString(var4) + " undefined");
               }

               var5.leftToRight = var3;
               var5.leftToLeft = -1;
            }
            break;
         case 2:
            if (var4 == 1) {
               var5.rightToLeft = var3;
               var5.rightToRight = -1;
            } else {
               if (var4 != 2) {
                  throw new IllegalArgumentException("right to " + this.sideToString(var4) + " undefined");
               }

               var5.rightToRight = var3;
               var5.rightToLeft = -1;
            }
            break;
         case 3:
            if (var4 == 3) {
               var5.topToTop = var3;
               var5.topToBottom = -1;
               var5.baselineToBaseline = -1;
            } else {
               if (var4 != 4) {
                  throw new IllegalArgumentException("right to " + this.sideToString(var4) + " undefined");
               }

               var5.topToBottom = var3;
               var5.topToTop = -1;
               var5.baselineToBaseline = -1;
            }
            break;
         case 4:
            if (var4 == 4) {
               var5.bottomToBottom = var3;
               var5.bottomToTop = -1;
               var5.baselineToBaseline = -1;
            } else {
               if (var4 != 3) {
                  throw new IllegalArgumentException("right to " + this.sideToString(var4) + " undefined");
               }

               var5.bottomToTop = var3;
               var5.bottomToBottom = -1;
               var5.baselineToBaseline = -1;
            }
            break;
         case 5:
            if (var4 != 5) {
               throw new IllegalArgumentException("right to " + this.sideToString(var4) + " undefined");
            }

            var5.baselineToBaseline = var3;
            var5.bottomToBottom = -1;
            var5.bottomToTop = -1;
            var5.topToTop = -1;
            var5.topToBottom = -1;
            break;
         case 6:
            if (var4 == 6) {
               var5.startToStart = var3;
               var5.startToEnd = -1;
            } else {
               if (var4 != 7) {
                  throw new IllegalArgumentException("right to " + this.sideToString(var4) + " undefined");
               }

               var5.startToEnd = var3;
               var5.startToStart = -1;
            }
            break;
         case 7:
            if (var4 == 7) {
               var5.endToEnd = var3;
               var5.endToStart = -1;
            } else {
               if (var4 != 6) {
                  throw new IllegalArgumentException("right to " + this.sideToString(var4) + " undefined");
               }

               var5.endToStart = var3;
               var5.endToEnd = -1;
            }
            break;
         default:
            throw new IllegalArgumentException(this.sideToString(var2) + " to " + this.sideToString(var4) + " unknown");
      }

   }

   public void connect(int var1, int var2, int var3, int var4, int var5) {
      if (!this.mConstraints.containsKey(var1)) {
         this.mConstraints.put(var1, new Constraint());
      }

      Constraint var6 = (Constraint)this.mConstraints.get(var1);
      switch (var2) {
         case 1:
            if (var4 == 1) {
               var6.leftToLeft = var3;
               var6.leftToRight = -1;
            } else {
               if (var4 != 2) {
                  throw new IllegalArgumentException("Left to " + this.sideToString(var4) + " undefined");
               }

               var6.leftToRight = var3;
               var6.leftToLeft = -1;
            }

            var6.leftMargin = var5;
            break;
         case 2:
            if (var4 == 1) {
               var6.rightToLeft = var3;
               var6.rightToRight = -1;
            } else {
               if (var4 != 2) {
                  throw new IllegalArgumentException("right to " + this.sideToString(var4) + " undefined");
               }

               var6.rightToRight = var3;
               var6.rightToLeft = -1;
            }

            var6.rightMargin = var5;
            break;
         case 3:
            if (var4 == 3) {
               var6.topToTop = var3;
               var6.topToBottom = -1;
               var6.baselineToBaseline = -1;
            } else {
               if (var4 != 4) {
                  throw new IllegalArgumentException("right to " + this.sideToString(var4) + " undefined");
               }

               var6.topToBottom = var3;
               var6.topToTop = -1;
               var6.baselineToBaseline = -1;
            }

            var6.topMargin = var5;
            break;
         case 4:
            if (var4 == 4) {
               var6.bottomToBottom = var3;
               var6.bottomToTop = -1;
               var6.baselineToBaseline = -1;
            } else {
               if (var4 != 3) {
                  throw new IllegalArgumentException("right to " + this.sideToString(var4) + " undefined");
               }

               var6.bottomToTop = var3;
               var6.bottomToBottom = -1;
               var6.baselineToBaseline = -1;
            }

            var6.bottomMargin = var5;
            break;
         case 5:
            if (var4 != 5) {
               throw new IllegalArgumentException("right to " + this.sideToString(var4) + " undefined");
            }

            var6.baselineToBaseline = var3;
            var6.bottomToBottom = -1;
            var6.bottomToTop = -1;
            var6.topToTop = -1;
            var6.topToBottom = -1;
            break;
         case 6:
            if (var4 == 6) {
               var6.startToStart = var3;
               var6.startToEnd = -1;
            } else {
               if (var4 != 7) {
                  throw new IllegalArgumentException("right to " + this.sideToString(var4) + " undefined");
               }

               var6.startToEnd = var3;
               var6.startToStart = -1;
            }

            var6.startMargin = var5;
            break;
         case 7:
            if (var4 == 7) {
               var6.endToEnd = var3;
               var6.endToStart = -1;
            } else {
               if (var4 != 6) {
                  throw new IllegalArgumentException("right to " + this.sideToString(var4) + " undefined");
               }

               var6.endToStart = var3;
               var6.endToEnd = -1;
            }

            var6.endMargin = var5;
            break;
         default:
            throw new IllegalArgumentException(this.sideToString(var2) + " to " + this.sideToString(var4) + " unknown");
      }

   }

   public void constrainCircle(int var1, int var2, int var3, float var4) {
      Constraint var5 = this.get(var1);
      var5.circleConstraint = var2;
      var5.circleRadius = var3;
      var5.circleAngle = var4;
   }

   public void constrainDefaultHeight(int var1, int var2) {
      this.get(var1).heightDefault = var2;
   }

   public void constrainDefaultWidth(int var1, int var2) {
      this.get(var1).widthDefault = var2;
   }

   public void constrainHeight(int var1, int var2) {
      this.get(var1).mHeight = var2;
   }

   public void constrainMaxHeight(int var1, int var2) {
      this.get(var1).heightMax = var2;
   }

   public void constrainMaxWidth(int var1, int var2) {
      this.get(var1).widthMax = var2;
   }

   public void constrainMinHeight(int var1, int var2) {
      this.get(var1).heightMin = var2;
   }

   public void constrainMinWidth(int var1, int var2) {
      this.get(var1).widthMin = var2;
   }

   public void constrainPercentHeight(int var1, float var2) {
      this.get(var1).heightPercent = var2;
   }

   public void constrainPercentWidth(int var1, float var2) {
      this.get(var1).widthPercent = var2;
   }

   public void constrainWidth(int var1, int var2) {
      this.get(var1).mWidth = var2;
   }

   public void create(int var1, int var2) {
      Constraint var3 = this.get(var1);
      var3.mIsGuideline = true;
      var3.orientation = var2;
   }

   public void createBarrier(int var1, int var2, int... var3) {
      Constraint var4 = this.get(var1);
      var4.mHelperType = 1;
      var4.mBarrierDirection = var2;
      var4.mIsGuideline = false;
      var4.mReferenceIds = var3;
   }

   public void createHorizontalChain(int var1, int var2, int var3, int var4, int[] var5, float[] var6, int var7) {
      this.createHorizontalChain(var1, var2, var3, var4, var5, var6, var7, 1, 2);
   }

   public void createHorizontalChainRtl(int var1, int var2, int var3, int var4, int[] var5, float[] var6, int var7) {
      this.createHorizontalChain(var1, var2, var3, var4, var5, var6, var7, 6, 7);
   }

   public void createVerticalChain(int var1, int var2, int var3, int var4, int[] var5, float[] var6, int var7) {
      if (var5.length >= 2) {
         if (var6 != null && var6.length != var5.length) {
            throw new IllegalArgumentException("must have 2 or more widgets in a chain");
         } else {
            if (var6 != null) {
               this.get(var5[0]).verticalWeight = var6[0];
            }

            this.get(var5[0]).verticalChainStyle = var7;
            this.connect(var5[0], 3, var1, var2, 0);

            for(var1 = 1; var1 < var5.length; ++var1) {
               var7 = var5[var1];
               var2 = var1 - 1;
               this.connect(var7, 3, var5[var2], 4, 0);
               this.connect(var5[var2], 4, var5[var1], 3, 0);
               if (var6 != null) {
                  this.get(var5[var1]).verticalWeight = var6[var1];
               }
            }

            this.connect(var5[var5.length - 1], 4, var3, var4, 0);
         }
      } else {
         throw new IllegalArgumentException("must have 2 or more widgets in a chain");
      }
   }

   public boolean getApplyElevation(int var1) {
      return this.get(var1).applyElevation;
   }

   public Constraint getParameters(int var1) {
      return this.get(var1);
   }

   public void load(Context var1, int var2) {
      XmlResourceParser var4 = var1.getResources().getXml(var2);

      XmlPullParserException var18;
      label75: {
         IOException var10000;
         label61: {
            boolean var10001;
            try {
               var2 = var4.getEventType();
            } catch (XmlPullParserException var14) {
               var18 = var14;
               var10001 = false;
               break label75;
            } catch (IOException var15) {
               var10000 = var15;
               var10001 = false;
               break label61;
            }

            while(true) {
               if (var2 == 1) {
                  return;
               }

               if (var2 != 0) {
                  if (var2 == 2) {
                     Constraint var3;
                     try {
                        String var5 = var4.getName();
                        var3 = this.fillFromAttributeList(var1, Xml.asAttributeSet(var4));
                        if (var5.equalsIgnoreCase("Guideline")) {
                           var3.mIsGuideline = true;
                        }
                     } catch (XmlPullParserException var12) {
                        var18 = var12;
                        var10001 = false;
                        break label75;
                     } catch (IOException var13) {
                        var10000 = var13;
                        var10001 = false;
                        break;
                     }

                     try {
                        this.mConstraints.put(var3.mViewId, var3);
                     } catch (XmlPullParserException var10) {
                        var18 = var10;
                        var10001 = false;
                        break label75;
                     } catch (IOException var11) {
                        var10000 = var11;
                        var10001 = false;
                        break;
                     }
                  }
               } else {
                  try {
                     var4.getName();
                  } catch (XmlPullParserException var8) {
                     var18 = var8;
                     var10001 = false;
                     break label75;
                  } catch (IOException var9) {
                     var10000 = var9;
                     var10001 = false;
                     break;
                  }
               }

               try {
                  var2 = var4.next();
               } catch (XmlPullParserException var6) {
                  var18 = var6;
                  var10001 = false;
                  break label75;
               } catch (IOException var7) {
                  var10000 = var7;
                  var10001 = false;
                  break;
               }
            }
         }

         IOException var16 = var10000;
         var16.printStackTrace();
         return;
      }

      XmlPullParserException var17 = var18;
      var17.printStackTrace();
   }

   public void removeFromHorizontalChain(int var1) {
      if (this.mConstraints.containsKey(var1)) {
         Constraint var5 = (Constraint)this.mConstraints.get(var1);
         int var2 = var5.leftToRight;
         int var3 = var5.rightToLeft;
         if (var2 == -1 && var3 == -1) {
            var3 = var5.startToEnd;
            int var4 = var5.endToStart;
            if (var3 != -1 || var4 != -1) {
               if (var3 != -1 && var4 != -1) {
                  this.connect(var3, 7, var4, 6, 0);
                  this.connect(var4, 6, var2, 7, 0);
               } else if (var2 != -1 || var4 != -1) {
                  if (var5.rightToRight != -1) {
                     this.connect(var2, 7, var5.rightToRight, 7, 0);
                  } else if (var5.leftToLeft != -1) {
                     this.connect(var4, 6, var5.leftToLeft, 6, 0);
                  }
               }
            }

            this.clear(var1, 6);
            this.clear(var1, 7);
         } else {
            if (var2 != -1 && var3 != -1) {
               this.connect(var2, 2, var3, 1, 0);
               this.connect(var3, 1, var2, 2, 0);
            } else if (var2 != -1 || var3 != -1) {
               if (var5.rightToRight != -1) {
                  this.connect(var2, 2, var5.rightToRight, 2, 0);
               } else if (var5.leftToLeft != -1) {
                  this.connect(var3, 1, var5.leftToLeft, 1, 0);
               }
            }

            this.clear(var1, 1);
            this.clear(var1, 2);
         }
      }

   }

   public void removeFromVerticalChain(int var1) {
      if (this.mConstraints.containsKey(var1)) {
         Constraint var4 = (Constraint)this.mConstraints.get(var1);
         int var3 = var4.topToBottom;
         int var2 = var4.bottomToTop;
         if (var3 != -1 || var2 != -1) {
            if (var3 != -1 && var2 != -1) {
               this.connect(var3, 4, var2, 3, 0);
               this.connect(var2, 3, var3, 4, 0);
            } else if (var3 != -1 || var2 != -1) {
               if (var4.bottomToBottom != -1) {
                  this.connect(var3, 4, var4.bottomToBottom, 4, 0);
               } else if (var4.topToTop != -1) {
                  this.connect(var2, 3, var4.topToTop, 3, 0);
               }
            }
         }
      }

      this.clear(var1, 3);
      this.clear(var1, 4);
   }

   public void setAlpha(int var1, float var2) {
      this.get(var1).alpha = var2;
   }

   public void setApplyElevation(int var1, boolean var2) {
      this.get(var1).applyElevation = var2;
   }

   public void setBarrierType(int var1, int var2) {
   }

   public void setDimensionRatio(int var1, String var2) {
      this.get(var1).dimensionRatio = var2;
   }

   public void setElevation(int var1, float var2) {
      this.get(var1).elevation = var2;
      this.get(var1).applyElevation = true;
   }

   public void setGoneMargin(int var1, int var2, int var3) {
      Constraint var4 = this.get(var1);
      switch (var2) {
         case 1:
            var4.goneLeftMargin = var3;
            break;
         case 2:
            var4.goneRightMargin = var3;
            break;
         case 3:
            var4.goneTopMargin = var3;
            break;
         case 4:
            var4.goneBottomMargin = var3;
            break;
         case 5:
            throw new IllegalArgumentException("baseline does not support margins");
         case 6:
            var4.goneStartMargin = var3;
            break;
         case 7:
            var4.goneEndMargin = var3;
            break;
         default:
            throw new IllegalArgumentException("unknown constraint");
      }

   }

   public void setGuidelineBegin(int var1, int var2) {
      this.get(var1).guideBegin = var2;
      this.get(var1).guideEnd = -1;
      this.get(var1).guidePercent = -1.0F;
   }

   public void setGuidelineEnd(int var1, int var2) {
      this.get(var1).guideEnd = var2;
      this.get(var1).guideBegin = -1;
      this.get(var1).guidePercent = -1.0F;
   }

   public void setGuidelinePercent(int var1, float var2) {
      this.get(var1).guidePercent = var2;
      this.get(var1).guideEnd = -1;
      this.get(var1).guideBegin = -1;
   }

   public void setHorizontalBias(int var1, float var2) {
      this.get(var1).horizontalBias = var2;
   }

   public void setHorizontalChainStyle(int var1, int var2) {
      this.get(var1).horizontalChainStyle = var2;
   }

   public void setHorizontalWeight(int var1, float var2) {
      this.get(var1).horizontalWeight = var2;
   }

   public void setMargin(int var1, int var2, int var3) {
      Constraint var4 = this.get(var1);
      switch (var2) {
         case 1:
            var4.leftMargin = var3;
            break;
         case 2:
            var4.rightMargin = var3;
            break;
         case 3:
            var4.topMargin = var3;
            break;
         case 4:
            var4.bottomMargin = var3;
            break;
         case 5:
            throw new IllegalArgumentException("baseline does not support margins");
         case 6:
            var4.startMargin = var3;
            break;
         case 7:
            var4.endMargin = var3;
            break;
         default:
            throw new IllegalArgumentException("unknown constraint");
      }

   }

   public void setRotation(int var1, float var2) {
      this.get(var1).rotation = var2;
   }

   public void setRotationX(int var1, float var2) {
      this.get(var1).rotationX = var2;
   }

   public void setRotationY(int var1, float var2) {
      this.get(var1).rotationY = var2;
   }

   public void setScaleX(int var1, float var2) {
      this.get(var1).scaleX = var2;
   }

   public void setScaleY(int var1, float var2) {
      this.get(var1).scaleY = var2;
   }

   public void setTransformPivot(int var1, float var2, float var3) {
      Constraint var4 = this.get(var1);
      var4.transformPivotY = var3;
      var4.transformPivotX = var2;
   }

   public void setTransformPivotX(int var1, float var2) {
      this.get(var1).transformPivotX = var2;
   }

   public void setTransformPivotY(int var1, float var2) {
      this.get(var1).transformPivotY = var2;
   }

   public void setTranslation(int var1, float var2, float var3) {
      Constraint var4 = this.get(var1);
      var4.translationX = var2;
      var4.translationY = var3;
   }

   public void setTranslationX(int var1, float var2) {
      this.get(var1).translationX = var2;
   }

   public void setTranslationY(int var1, float var2) {
      this.get(var1).translationY = var2;
   }

   public void setTranslationZ(int var1, float var2) {
      this.get(var1).translationZ = var2;
   }

   public void setVerticalBias(int var1, float var2) {
      this.get(var1).verticalBias = var2;
   }

   public void setVerticalChainStyle(int var1, int var2) {
      this.get(var1).verticalChainStyle = var2;
   }

   public void setVerticalWeight(int var1, float var2) {
      this.get(var1).verticalWeight = var2;
   }

   public void setVisibility(int var1, int var2) {
      this.get(var1).visibility = var2;
   }

   private static class Constraint {
      static final int UNSET = -1;
      public float alpha;
      public boolean applyElevation;
      public int baselineToBaseline;
      public int bottomMargin;
      public int bottomToBottom;
      public int bottomToTop;
      public float circleAngle;
      public int circleConstraint;
      public int circleRadius;
      public boolean constrainedHeight;
      public boolean constrainedWidth;
      public String dimensionRatio;
      public int editorAbsoluteX;
      public int editorAbsoluteY;
      public float elevation;
      public int endMargin;
      public int endToEnd;
      public int endToStart;
      public int goneBottomMargin;
      public int goneEndMargin;
      public int goneLeftMargin;
      public int goneRightMargin;
      public int goneStartMargin;
      public int goneTopMargin;
      public int guideBegin;
      public int guideEnd;
      public float guidePercent;
      public int heightDefault;
      public int heightMax;
      public int heightMin;
      public float heightPercent;
      public float horizontalBias;
      public int horizontalChainStyle;
      public float horizontalWeight;
      public int leftMargin;
      public int leftToLeft;
      public int leftToRight;
      public boolean mBarrierAllowsGoneWidgets;
      public int mBarrierDirection;
      public int mHeight;
      public int mHelperType;
      boolean mIsGuideline;
      public String mReferenceIdString;
      public int[] mReferenceIds;
      int mViewId;
      public int mWidth;
      public int orientation;
      public int rightMargin;
      public int rightToLeft;
      public int rightToRight;
      public float rotation;
      public float rotationX;
      public float rotationY;
      public float scaleX;
      public float scaleY;
      public int startMargin;
      public int startToEnd;
      public int startToStart;
      public int topMargin;
      public int topToBottom;
      public int topToTop;
      public float transformPivotX;
      public float transformPivotY;
      public float translationX;
      public float translationY;
      public float translationZ;
      public float verticalBias;
      public int verticalChainStyle;
      public float verticalWeight;
      public int visibility;
      public int widthDefault;
      public int widthMax;
      public int widthMin;
      public float widthPercent;

      private Constraint() {
         this.mIsGuideline = false;
         this.guideBegin = -1;
         this.guideEnd = -1;
         this.guidePercent = -1.0F;
         this.leftToLeft = -1;
         this.leftToRight = -1;
         this.rightToLeft = -1;
         this.rightToRight = -1;
         this.topToTop = -1;
         this.topToBottom = -1;
         this.bottomToTop = -1;
         this.bottomToBottom = -1;
         this.baselineToBaseline = -1;
         this.startToEnd = -1;
         this.startToStart = -1;
         this.endToStart = -1;
         this.endToEnd = -1;
         this.horizontalBias = 0.5F;
         this.verticalBias = 0.5F;
         this.dimensionRatio = null;
         this.circleConstraint = -1;
         this.circleRadius = 0;
         this.circleAngle = 0.0F;
         this.editorAbsoluteX = -1;
         this.editorAbsoluteY = -1;
         this.orientation = -1;
         this.leftMargin = -1;
         this.rightMargin = -1;
         this.topMargin = -1;
         this.bottomMargin = -1;
         this.endMargin = -1;
         this.startMargin = -1;
         this.visibility = 0;
         this.goneLeftMargin = -1;
         this.goneTopMargin = -1;
         this.goneRightMargin = -1;
         this.goneBottomMargin = -1;
         this.goneEndMargin = -1;
         this.goneStartMargin = -1;
         this.verticalWeight = 0.0F;
         this.horizontalWeight = 0.0F;
         this.horizontalChainStyle = 0;
         this.verticalChainStyle = 0;
         this.alpha = 1.0F;
         this.applyElevation = false;
         this.elevation = 0.0F;
         this.rotation = 0.0F;
         this.rotationX = 0.0F;
         this.rotationY = 0.0F;
         this.scaleX = 1.0F;
         this.scaleY = 1.0F;
         this.transformPivotX = Float.NaN;
         this.transformPivotY = Float.NaN;
         this.translationX = 0.0F;
         this.translationY = 0.0F;
         this.translationZ = 0.0F;
         this.constrainedWidth = false;
         this.constrainedHeight = false;
         this.widthDefault = 0;
         this.heightDefault = 0;
         this.widthMax = -1;
         this.heightMax = -1;
         this.widthMin = -1;
         this.heightMin = -1;
         this.widthPercent = 1.0F;
         this.heightPercent = 1.0F;
         this.mBarrierAllowsGoneWidgets = false;
         this.mBarrierDirection = -1;
         this.mHelperType = -1;
      }

      // $FF: synthetic method
      Constraint(Object var1) {
         this();
      }

      private void fillFrom(int var1, ConstraintLayout.LayoutParams var2) {
         this.mViewId = var1;
         this.leftToLeft = var2.leftToLeft;
         this.leftToRight = var2.leftToRight;
         this.rightToLeft = var2.rightToLeft;
         this.rightToRight = var2.rightToRight;
         this.topToTop = var2.topToTop;
         this.topToBottom = var2.topToBottom;
         this.bottomToTop = var2.bottomToTop;
         this.bottomToBottom = var2.bottomToBottom;
         this.baselineToBaseline = var2.baselineToBaseline;
         this.startToEnd = var2.startToEnd;
         this.startToStart = var2.startToStart;
         this.endToStart = var2.endToStart;
         this.endToEnd = var2.endToEnd;
         this.horizontalBias = var2.horizontalBias;
         this.verticalBias = var2.verticalBias;
         this.dimensionRatio = var2.dimensionRatio;
         this.circleConstraint = var2.circleConstraint;
         this.circleRadius = var2.circleRadius;
         this.circleAngle = var2.circleAngle;
         this.editorAbsoluteX = var2.editorAbsoluteX;
         this.editorAbsoluteY = var2.editorAbsoluteY;
         this.orientation = var2.orientation;
         this.guidePercent = var2.guidePercent;
         this.guideBegin = var2.guideBegin;
         this.guideEnd = var2.guideEnd;
         this.mWidth = var2.width;
         this.mHeight = var2.height;
         this.leftMargin = var2.leftMargin;
         this.rightMargin = var2.rightMargin;
         this.topMargin = var2.topMargin;
         this.bottomMargin = var2.bottomMargin;
         this.verticalWeight = var2.verticalWeight;
         this.horizontalWeight = var2.horizontalWeight;
         this.verticalChainStyle = var2.verticalChainStyle;
         this.horizontalChainStyle = var2.horizontalChainStyle;
         this.constrainedWidth = var2.constrainedWidth;
         this.constrainedHeight = var2.constrainedHeight;
         this.widthDefault = var2.matchConstraintDefaultWidth;
         this.heightDefault = var2.matchConstraintDefaultHeight;
         this.constrainedWidth = var2.constrainedWidth;
         this.widthMax = var2.matchConstraintMaxWidth;
         this.heightMax = var2.matchConstraintMaxHeight;
         this.widthMin = var2.matchConstraintMinWidth;
         this.heightMin = var2.matchConstraintMinHeight;
         this.widthPercent = var2.matchConstraintPercentWidth;
         this.heightPercent = var2.matchConstraintPercentHeight;
         if (VERSION.SDK_INT >= 17) {
            this.endMargin = var2.getMarginEnd();
            this.startMargin = var2.getMarginStart();
         }

      }

      private void fillFromConstraints(int var1, Constraints.LayoutParams var2) {
         this.fillFrom(var1, var2);
         this.alpha = var2.alpha;
         this.rotation = var2.rotation;
         this.rotationX = var2.rotationX;
         this.rotationY = var2.rotationY;
         this.scaleX = var2.scaleX;
         this.scaleY = var2.scaleY;
         this.transformPivotX = var2.transformPivotX;
         this.transformPivotY = var2.transformPivotY;
         this.translationX = var2.translationX;
         this.translationY = var2.translationY;
         this.translationZ = var2.translationZ;
         this.elevation = var2.elevation;
         this.applyElevation = var2.applyElevation;
      }

      private void fillFromConstraints(ConstraintHelper var1, int var2, Constraints.LayoutParams var3) {
         this.fillFromConstraints(var2, var3);
         if (var1 instanceof Barrier) {
            this.mHelperType = 1;
            Barrier var4 = (Barrier)var1;
            this.mBarrierDirection = var4.getType();
            this.mReferenceIds = var4.getReferencedIds();
         }

      }

      public void applyTo(ConstraintLayout.LayoutParams var1) {
         var1.leftToLeft = this.leftToLeft;
         var1.leftToRight = this.leftToRight;
         var1.rightToLeft = this.rightToLeft;
         var1.rightToRight = this.rightToRight;
         var1.topToTop = this.topToTop;
         var1.topToBottom = this.topToBottom;
         var1.bottomToTop = this.bottomToTop;
         var1.bottomToBottom = this.bottomToBottom;
         var1.baselineToBaseline = this.baselineToBaseline;
         var1.startToEnd = this.startToEnd;
         var1.startToStart = this.startToStart;
         var1.endToStart = this.endToStart;
         var1.endToEnd = this.endToEnd;
         var1.leftMargin = this.leftMargin;
         var1.rightMargin = this.rightMargin;
         var1.topMargin = this.topMargin;
         var1.bottomMargin = this.bottomMargin;
         var1.goneStartMargin = this.goneStartMargin;
         var1.goneEndMargin = this.goneEndMargin;
         var1.horizontalBias = this.horizontalBias;
         var1.verticalBias = this.verticalBias;
         var1.circleConstraint = this.circleConstraint;
         var1.circleRadius = this.circleRadius;
         var1.circleAngle = this.circleAngle;
         var1.dimensionRatio = this.dimensionRatio;
         var1.editorAbsoluteX = this.editorAbsoluteX;
         var1.editorAbsoluteY = this.editorAbsoluteY;
         var1.verticalWeight = this.verticalWeight;
         var1.horizontalWeight = this.horizontalWeight;
         var1.verticalChainStyle = this.verticalChainStyle;
         var1.horizontalChainStyle = this.horizontalChainStyle;
         var1.constrainedWidth = this.constrainedWidth;
         var1.constrainedHeight = this.constrainedHeight;
         var1.matchConstraintDefaultWidth = this.widthDefault;
         var1.matchConstraintDefaultHeight = this.heightDefault;
         var1.matchConstraintMaxWidth = this.widthMax;
         var1.matchConstraintMaxHeight = this.heightMax;
         var1.matchConstraintMinWidth = this.widthMin;
         var1.matchConstraintMinHeight = this.heightMin;
         var1.matchConstraintPercentWidth = this.widthPercent;
         var1.matchConstraintPercentHeight = this.heightPercent;
         var1.orientation = this.orientation;
         var1.guidePercent = this.guidePercent;
         var1.guideBegin = this.guideBegin;
         var1.guideEnd = this.guideEnd;
         var1.width = this.mWidth;
         var1.height = this.mHeight;
         if (VERSION.SDK_INT >= 17) {
            var1.setMarginStart(this.startMargin);
            var1.setMarginEnd(this.endMargin);
         }

         var1.validate();
      }

      public Constraint clone() {
         Constraint var2 = new Constraint();
         var2.mIsGuideline = this.mIsGuideline;
         var2.mWidth = this.mWidth;
         var2.mHeight = this.mHeight;
         var2.guideBegin = this.guideBegin;
         var2.guideEnd = this.guideEnd;
         var2.guidePercent = this.guidePercent;
         var2.leftToLeft = this.leftToLeft;
         var2.leftToRight = this.leftToRight;
         var2.rightToLeft = this.rightToLeft;
         var2.rightToRight = this.rightToRight;
         var2.topToTop = this.topToTop;
         var2.topToBottom = this.topToBottom;
         var2.bottomToTop = this.bottomToTop;
         var2.bottomToBottom = this.bottomToBottom;
         var2.baselineToBaseline = this.baselineToBaseline;
         var2.startToEnd = this.startToEnd;
         var2.startToStart = this.startToStart;
         var2.endToStart = this.endToStart;
         var2.endToEnd = this.endToEnd;
         var2.horizontalBias = this.horizontalBias;
         var2.verticalBias = this.verticalBias;
         var2.dimensionRatio = this.dimensionRatio;
         var2.editorAbsoluteX = this.editorAbsoluteX;
         var2.editorAbsoluteY = this.editorAbsoluteY;
         var2.horizontalBias = this.horizontalBias;
         var2.horizontalBias = this.horizontalBias;
         var2.horizontalBias = this.horizontalBias;
         var2.horizontalBias = this.horizontalBias;
         var2.horizontalBias = this.horizontalBias;
         var2.orientation = this.orientation;
         var2.leftMargin = this.leftMargin;
         var2.rightMargin = this.rightMargin;
         var2.topMargin = this.topMargin;
         var2.bottomMargin = this.bottomMargin;
         var2.endMargin = this.endMargin;
         var2.startMargin = this.startMargin;
         var2.visibility = this.visibility;
         var2.goneLeftMargin = this.goneLeftMargin;
         var2.goneTopMargin = this.goneTopMargin;
         var2.goneRightMargin = this.goneRightMargin;
         var2.goneBottomMargin = this.goneBottomMargin;
         var2.goneEndMargin = this.goneEndMargin;
         var2.goneStartMargin = this.goneStartMargin;
         var2.verticalWeight = this.verticalWeight;
         var2.horizontalWeight = this.horizontalWeight;
         var2.horizontalChainStyle = this.horizontalChainStyle;
         var2.verticalChainStyle = this.verticalChainStyle;
         var2.alpha = this.alpha;
         var2.applyElevation = this.applyElevation;
         var2.elevation = this.elevation;
         var2.rotation = this.rotation;
         var2.rotationX = this.rotationX;
         var2.rotationY = this.rotationY;
         var2.scaleX = this.scaleX;
         var2.scaleY = this.scaleY;
         var2.transformPivotX = this.transformPivotX;
         var2.transformPivotY = this.transformPivotY;
         var2.translationX = this.translationX;
         var2.translationY = this.translationY;
         var2.translationZ = this.translationZ;
         var2.constrainedWidth = this.constrainedWidth;
         var2.constrainedHeight = this.constrainedHeight;
         var2.widthDefault = this.widthDefault;
         var2.heightDefault = this.heightDefault;
         var2.widthMax = this.widthMax;
         var2.heightMax = this.heightMax;
         var2.widthMin = this.widthMin;
         var2.heightMin = this.heightMin;
         var2.widthPercent = this.widthPercent;
         var2.heightPercent = this.heightPercent;
         var2.mBarrierDirection = this.mBarrierDirection;
         var2.mHelperType = this.mHelperType;
         int[] var1 = this.mReferenceIds;
         if (var1 != null) {
            var2.mReferenceIds = Arrays.copyOf(var1, var1.length);
         }

         var2.circleConstraint = this.circleConstraint;
         var2.circleRadius = this.circleRadius;
         var2.circleAngle = this.circleAngle;
         var2.mBarrierAllowsGoneWidgets = this.mBarrierAllowsGoneWidgets;
         return var2;
      }
   }
}
