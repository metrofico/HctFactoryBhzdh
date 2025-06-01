package com.hzbhd.canbus.car._30;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.R;
import com.hzbhd.canbus.util.CommUtil;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;

@Metadata(
   d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0001\u0012B\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\n\u001a\u00020\u000bH\u0014J\u0006\u0010\f\u001a\u00020\u000bJ\u000e\u0010\r\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\u000fJ\u000e\u0010\u0010\u001a\u00020\u000b2\u0006\u0010\u0011\u001a\u00020\u000fR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0007\u001a\n \t*\u0004\u0018\u00010\b0\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0013"},
   d2 = {"Lcom/hzbhd/canbus/car/_30/MyPanoramicView;", "Landroid/widget/RelativeLayout;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "TAG", "", "mCurrentBtns", "Landroid/widget/LinearLayout;", "kotlin.jvm.PlatformType", "onAttachedToWindow", "", "showIbRearDown", "updateBtns", "index", "", "updateSide", "side", "SIDE", "CanBusInfo_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class MyPanoramicView extends RelativeLayout {
   private String TAG;
   public Map _$_findViewCache;
   private LinearLayout mCurrentBtns;

   public MyPanoramicView(Context var1) {
      this._$_findViewCache = (Map)(new LinkedHashMap());
      super(var1);
      this.TAG = "_30_MyPanoramicView";
      this.mCurrentBtns = (LinearLayout)this._$_findCachedViewById(R.id.ll_front);
      LayoutInflater.from(var1).inflate(2131558790, (ViewGroup)this);
      OnClickListener var2 = new OnClickListener(this) {
         final MyPanoramicView this$0;

         {
            this.this$0 = var1;
         }

         public void onClick(View var1) {
            if (Intrinsics.areEqual((Object)var1, (Object)((ImageButton)this.this$0._$_findCachedViewById(R.id.ib_front_all)))) {
               CanbusMsgSender.sendMsg(new byte[]{22, -124, 1});
            } else if (Intrinsics.areEqual((Object)var1, (Object)((ImageButton)this.this$0._$_findCachedViewById(R.id.ib_front_only)))) {
               CanbusMsgSender.sendMsg(new byte[]{22, -124, 2});
            } else if (Intrinsics.areEqual((Object)var1, (Object)((ImageButton)this.this$0._$_findCachedViewById(R.id.ib_front_left)))) {
               CanbusMsgSender.sendMsg(new byte[]{22, -124, 3});
            } else if (Intrinsics.areEqual((Object)var1, (Object)((ImageButton)this.this$0._$_findCachedViewById(R.id.ib_front_right)))) {
               CanbusMsgSender.sendMsg(new byte[]{22, -124, 4});
            } else if (Intrinsics.areEqual((Object)var1, (Object)((ImageButton)this.this$0._$_findCachedViewById(R.id.ib_front_back)))) {
               CanbusMsgSender.sendMsg(new byte[]{22, -124, 5});
            } else if (Intrinsics.areEqual((Object)var1, (Object)((ImageButton)this.this$0._$_findCachedViewById(R.id.ib_rear_all)))) {
               CanbusMsgSender.sendMsg(new byte[]{22, -124, 6});
            } else if (Intrinsics.areEqual((Object)var1, (Object)((ImageButton)this.this$0._$_findCachedViewById(R.id.ib_rear_only)))) {
               CanbusMsgSender.sendMsg(new byte[]{22, -124, 7});
            } else if (Intrinsics.areEqual((Object)var1, (Object)((ImageButton)this.this$0._$_findCachedViewById(R.id.ib_rear_left)))) {
               CanbusMsgSender.sendMsg(new byte[]{22, -124, 8});
            } else if (Intrinsics.areEqual((Object)var1, (Object)((ImageButton)this.this$0._$_findCachedViewById(R.id.ib_rear_right)))) {
               CanbusMsgSender.sendMsg(new byte[]{22, -124, 9});
            } else if (Intrinsics.areEqual((Object)var1, (Object)((ImageButton)this.this$0._$_findCachedViewById(R.id.ib_rear_down)))) {
               CanbusMsgSender.sendMsg(new byte[]{22, -124, 10});
            } else if (Intrinsics.areEqual((Object)var1, (Object)((ImageButton)this.this$0._$_findCachedViewById(R.id.ib_front_right_left)))) {
               CanbusMsgSender.sendMsg(new byte[]{22, -124, 11});
            }

         }
      };
      ImageButton var3 = (ImageButton)this._$_findCachedViewById(R.id.ib_front_all);
      var2 = (OnClickListener)var2;
      var3.setOnClickListener(var2);
      ((ImageButton)this._$_findCachedViewById(R.id.ib_front_only)).setOnClickListener(var2);
      ((ImageButton)this._$_findCachedViewById(R.id.ib_front_left)).setOnClickListener(var2);
      ((ImageButton)this._$_findCachedViewById(R.id.ib_front_right)).setOnClickListener(var2);
      ((ImageButton)this._$_findCachedViewById(R.id.ib_front_back)).setOnClickListener(var2);
      ((ImageButton)this._$_findCachedViewById(R.id.ib_rear_all)).setOnClickListener(var2);
      ((ImageButton)this._$_findCachedViewById(R.id.ib_rear_only)).setOnClickListener(var2);
      ((ImageButton)this._$_findCachedViewById(R.id.ib_rear_left)).setOnClickListener(var2);
      ((ImageButton)this._$_findCachedViewById(R.id.ib_rear_right)).setOnClickListener(var2);
      ((ImageButton)this._$_findCachedViewById(R.id.ib_rear_down)).setOnClickListener(var2);
      ((ImageButton)this._$_findCachedViewById(R.id.ib_front_right_left)).setOnClickListener(var2);
   }

   public void _$_clearFindViewByIdCache() {
      this._$_findViewCache.clear();
   }

   public View _$_findCachedViewById(int var1) {
      Map var4 = this._$_findViewCache;
      View var3 = (View)var4.get(var1);
      View var2 = var3;
      if (var3 == null) {
         var2 = this.findViewById(var1);
         if (var2 != null) {
            var4.put(var1, var2);
         } else {
            var2 = null;
         }
      }

      return var2;
   }

   protected void onAttachedToWindow() {
      super.onAttachedToWindow();
      boolean var2 = CommUtil.isPanoramic(this.getContext());
      boolean var1 = CommUtil.isBackCamera(this.getContext());
      String var3 = this.TAG;
      StringCompanionObject var4 = StringCompanionObject.INSTANCE;
      String var5 = String.format("onAttachedToWindow: isPanoramic %s|  isBackCamera %s|", Arrays.copyOf(new Object[]{var2, var1}, 2));
      Intrinsics.checkNotNullExpressionValue(var5, "format(format, *args)");
      Log.i(var3, var5);
      if (var2) {
         this.updateSide(SIDE.FRONT.ordinal());
      }

      if (var1) {
         this.updateSide(SIDE.REAR.ordinal());
      }

   }

   public final void showIbRearDown() {
      ((ImageButton)this._$_findCachedViewById(R.id.ib_rear_down)).setVisibility(0);
      Log.i(this.TAG, "showIbRearDown: ib_rear_down View.VISIBLE");
   }

   public final void updateBtns(int var1) {
      int var2 = 0;

      Exception var10000;
      label60: {
         int var3;
         boolean var10001;
         try {
            var3 = this.mCurrentBtns.getChildCount();
         } catch (Exception var11) {
            var10000 = var11;
            var10001 = false;
            break label60;
         }

         while(true) {
            if (var2 >= var3) {
               return;
            }

            LinearLayout var4;
            View var12;
            if (var2 == var1) {
               try {
                  var4 = this.mCurrentBtns;
               } catch (Exception var10) {
                  var10000 = var10;
                  var10001 = false;
                  break;
               }

               if (var4 != null) {
                  try {
                     var12 = var4.getChildAt(var2);
                  } catch (Exception var9) {
                     var10000 = var9;
                     var10001 = false;
                     break;
                  }

                  if (var12 != null) {
                     try {
                        var12.setBackgroundResource(2131232782);
                     } catch (Exception var8) {
                        var10000 = var8;
                        var10001 = false;
                        break;
                     }
                  }
               }
            } else {
               try {
                  var4 = this.mCurrentBtns;
               } catch (Exception var7) {
                  var10000 = var7;
                  var10001 = false;
                  break;
               }

               if (var4 != null) {
                  try {
                     var12 = var4.getChildAt(var2);
                  } catch (Exception var6) {
                     var10000 = var6;
                     var10001 = false;
                     break;
                  }

                  if (var12 != null) {
                     try {
                        var12.setBackgroundResource(2131232781);
                     } catch (Exception var5) {
                        var10000 = var5;
                        var10001 = false;
                        break;
                     }
                  }
               }
            }

            ++var2;
         }
      }

      Exception var13 = var10000;
      Log.e("CanBusError", var13.toString());
   }

   public final void updateSide(int var1) {
      if (var1 == SIDE.FRONT.ordinal()) {
         ((LinearLayout)this._$_findCachedViewById(R.id.ll_front)).setVisibility(0);
         ((LinearLayout)this._$_findCachedViewById(R.id.ll_rear)).setVisibility(4);
         this.mCurrentBtns = (LinearLayout)this._$_findCachedViewById(R.id.ll_front);
      } else if (var1 == SIDE.REAR.ordinal()) {
         ((LinearLayout)this._$_findCachedViewById(R.id.ll_front)).setVisibility(4);
         ((LinearLayout)this._$_findCachedViewById(R.id.ll_rear)).setVisibility(0);
         this.mCurrentBtns = (LinearLayout)this._$_findCachedViewById(R.id.ll_rear);
      }

   }

   @Metadata(
      d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004¨\u0006\u0005"},
      d2 = {"Lcom/hzbhd/canbus/car/_30/MyPanoramicView$SIDE;", "", "(Ljava/lang/String;I)V", "FRONT", "REAR", "CanBusInfo_release"},
      k = 1,
      mv = {1, 7, 1},
      xi = 48
   )
   public static enum SIDE {
      private static final SIDE[] $VALUES = $values();
      FRONT,
      REAR;

      // $FF: synthetic method
      private static final SIDE[] $values() {
         return new SIDE[]{FRONT, REAR};
      }
   }
}
