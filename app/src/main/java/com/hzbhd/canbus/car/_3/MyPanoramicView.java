package com.hzbhd.canbus.car._3;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.R;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.ranges.ClosedRange;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;

@Metadata(
   d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B\u0011\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004B\u001b\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007B#\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nB+\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\u000b\u001a\u00020\t¢\u0006\u0002\u0010\fJ\u0006\u0010\u000f\u001a\u00020\u0010J\u001e\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\t2\u0006\u0010\u0013\u001a\u00020\t2\u0006\u0010\u0014\u001a\u00020\tR\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"},
   d2 = {"Lcom/hzbhd/canbus/car/_3/MyPanoramicView;", "Landroid/widget/RelativeLayout;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "defStyleRes", "(Landroid/content/Context;Landroid/util/AttributeSet;II)V", "m0xC6Command", "", "showHideWindow", "", "update", "brightness", "contrast", "saturation", "Companion", "CanBusInfo_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class MyPanoramicView extends RelativeLayout {
   public static final Companion Companion = new Companion((DefaultConstructorMarker)null);
   private static final String TAG = "_3_MyPanoramicView";
   public Map _$_findViewCache;
   private final byte[] m0xC6Command;

   public MyPanoramicView(Context var1) {
      this._$_findViewCache = (Map)(new LinkedHashMap());
      super(var1);
      this.m0xC6Command = new byte[]{22, -58, 71, 0};
      LayoutInflater.from(this.getContext()).inflate(2131558791, (ViewGroup)this);
      ((SeekBar)this._$_findCachedViewById(R.id.sb_brightness)).setOnSeekBarChangeListener((SeekBar.OnSeekBarChangeListener)(new SeekBar.OnSeekBarChangeListener(this) {
         final MyPanoramicView this$0;

         {
            this.this$0 = var1;
         }

         public void onProgressChanged(SeekBar var1, int var2, boolean var3) {
            byte[] var4 = this.this$0.m0xC6Command;
            var4[2] = 71;
            var4[3] = (byte)var2;
            CanbusMsgSender.sendMsg(var4);
         }

         public void onStartTrackingTouch(SeekBar var1) {
         }

         public void onStopTrackingTouch(SeekBar var1) {
         }
      }));
      ((SeekBar)this._$_findCachedViewById(R.id.sb_contrast)).setOnSeekBarChangeListener((SeekBar.OnSeekBarChangeListener)(new SeekBar.OnSeekBarChangeListener(this) {
         final MyPanoramicView this$0;

         {
            this.this$0 = var1;
         }

         public void onProgressChanged(SeekBar var1, int var2, boolean var3) {
            byte[] var4 = this.this$0.m0xC6Command;
            var4[2] = 72;
            var4[3] = (byte)var2;
            CanbusMsgSender.sendMsg(var4);
         }

         public void onStartTrackingTouch(SeekBar var1) {
         }

         public void onStopTrackingTouch(SeekBar var1) {
         }
      }));
      ((SeekBar)this._$_findCachedViewById(R.id.sb_saturation)).setOnSeekBarChangeListener((SeekBar.OnSeekBarChangeListener)(new SeekBar.OnSeekBarChangeListener(this) {
         final MyPanoramicView this$0;

         {
            this.this$0 = var1;
         }

         public void onProgressChanged(SeekBar var1, int var2, boolean var3) {
            byte[] var4 = this.this$0.m0xC6Command;
            var4[2] = 73;
            var4[3] = (byte)var2;
            CanbusMsgSender.sendMsg(var4);
         }

         public void onStartTrackingTouch(SeekBar var1) {
         }

         public void onStopTrackingTouch(SeekBar var1) {
         }
      }));
   }

   public MyPanoramicView(Context var1, AttributeSet var2) {
      this._$_findViewCache = (Map)(new LinkedHashMap());
      super(var1, var2);
      this.m0xC6Command = new byte[]{22, -58, 71, 0};
      LayoutInflater.from(this.getContext()).inflate(2131558791, (ViewGroup)this);
      ((SeekBar)this._$_findCachedViewById(R.id.sb_brightness)).setOnSeekBarChangeListener((SeekBar.OnSeekBarChangeListener)(new SeekBar.OnSeekBarChangeListener(this) {
         final MyPanoramicView this$0;

         {
            this.this$0 = var1;
         }

         public void onProgressChanged(SeekBar var1, int var2, boolean var3) {
            byte[] var4 = this.this$0.m0xC6Command;
            var4[2] = 71;
            var4[3] = (byte)var2;
            CanbusMsgSender.sendMsg(var4);
         }

         public void onStartTrackingTouch(SeekBar var1) {
         }

         public void onStopTrackingTouch(SeekBar var1) {
         }
      }));
      ((SeekBar)this._$_findCachedViewById(R.id.sb_contrast)).setOnSeekBarChangeListener((SeekBar.OnSeekBarChangeListener)(new SeekBar.OnSeekBarChangeListener(this) {
         final MyPanoramicView this$0;

         {
            this.this$0 = var1;
         }

         public void onProgressChanged(SeekBar var1, int var2, boolean var3) {
            byte[] var4 = this.this$0.m0xC6Command;
            var4[2] = 72;
            var4[3] = (byte)var2;
            CanbusMsgSender.sendMsg(var4);
         }

         public void onStartTrackingTouch(SeekBar var1) {
         }

         public void onStopTrackingTouch(SeekBar var1) {
         }
      }));
      ((SeekBar)this._$_findCachedViewById(R.id.sb_saturation)).setOnSeekBarChangeListener((SeekBar.OnSeekBarChangeListener)(new SeekBar.OnSeekBarChangeListener(this) {
         final MyPanoramicView this$0;

         {
            this.this$0 = var1;
         }

         public void onProgressChanged(SeekBar var1, int var2, boolean var3) {
            byte[] var4 = this.this$0.m0xC6Command;
            var4[2] = 73;
            var4[3] = (byte)var2;
            CanbusMsgSender.sendMsg(var4);
         }

         public void onStartTrackingTouch(SeekBar var1) {
         }

         public void onStopTrackingTouch(SeekBar var1) {
         }
      }));
   }

   public MyPanoramicView(Context var1, AttributeSet var2, int var3) {
      this._$_findViewCache = (Map)(new LinkedHashMap());
      super(var1, var2, var3);
      this.m0xC6Command = new byte[]{22, -58, 71, 0};
      LayoutInflater.from(this.getContext()).inflate(2131558791, (ViewGroup)this);
      ((SeekBar)this._$_findCachedViewById(R.id.sb_brightness)).setOnSeekBarChangeListener((SeekBar.OnSeekBarChangeListener)(new SeekBar.OnSeekBarChangeListener(this) {
         final MyPanoramicView this$0;

         {
            this.this$0 = var1;
         }

         public void onProgressChanged(SeekBar var1, int var2, boolean var3) {
            byte[] var4 = this.this$0.m0xC6Command;
            var4[2] = 71;
            var4[3] = (byte)var2;
            CanbusMsgSender.sendMsg(var4);
         }

         public void onStartTrackingTouch(SeekBar var1) {
         }

         public void onStopTrackingTouch(SeekBar var1) {
         }
      }));
      ((SeekBar)this._$_findCachedViewById(R.id.sb_contrast)).setOnSeekBarChangeListener((SeekBar.OnSeekBarChangeListener)(new SeekBar.OnSeekBarChangeListener(this) {
         final MyPanoramicView this$0;

         {
            this.this$0 = var1;
         }

         public void onProgressChanged(SeekBar var1, int var2, boolean var3) {
            byte[] var4 = this.this$0.m0xC6Command;
            var4[2] = 72;
            var4[3] = (byte)var2;
            CanbusMsgSender.sendMsg(var4);
         }

         public void onStartTrackingTouch(SeekBar var1) {
         }

         public void onStopTrackingTouch(SeekBar var1) {
         }
      }));
      ((SeekBar)this._$_findCachedViewById(R.id.sb_saturation)).setOnSeekBarChangeListener((SeekBar.OnSeekBarChangeListener)(new SeekBar.OnSeekBarChangeListener(this) {
         final MyPanoramicView this$0;

         {
            this.this$0 = var1;
         }

         public void onProgressChanged(SeekBar var1, int var2, boolean var3) {
            byte[] var4 = this.this$0.m0xC6Command;
            var4[2] = 73;
            var4[3] = (byte)var2;
            CanbusMsgSender.sendMsg(var4);
         }

         public void onStartTrackingTouch(SeekBar var1) {
         }

         public void onStopTrackingTouch(SeekBar var1) {
         }
      }));
   }

   public MyPanoramicView(Context var1, AttributeSet var2, int var3, int var4) {
      this._$_findViewCache = (Map)(new LinkedHashMap());
      super(var1, var2, var3, var4);
      this.m0xC6Command = new byte[]{22, -58, 71, 0};
      LayoutInflater.from(this.getContext()).inflate(2131558791, (ViewGroup)this);
      ((SeekBar)this._$_findCachedViewById(R.id.sb_brightness)).setOnSeekBarChangeListener((SeekBar.OnSeekBarChangeListener)(new SeekBar.OnSeekBarChangeListener(this) {
         final MyPanoramicView this$0;

         {
            this.this$0 = var1;
         }

         public void onProgressChanged(SeekBar var1, int var2, boolean var3) {
            byte[] var4 = this.this$0.m0xC6Command;
            var4[2] = 71;
            var4[3] = (byte)var2;
            CanbusMsgSender.sendMsg(var4);
         }

         public void onStartTrackingTouch(SeekBar var1) {
         }

         public void onStopTrackingTouch(SeekBar var1) {
         }
      }));
      ((SeekBar)this._$_findCachedViewById(R.id.sb_contrast)).setOnSeekBarChangeListener((SeekBar.OnSeekBarChangeListener)(new SeekBar.OnSeekBarChangeListener(this) {
         final MyPanoramicView this$0;

         {
            this.this$0 = var1;
         }

         public void onProgressChanged(SeekBar var1, int var2, boolean var3) {
            byte[] var4 = this.this$0.m0xC6Command;
            var4[2] = 72;
            var4[3] = (byte)var2;
            CanbusMsgSender.sendMsg(var4);
         }

         public void onStartTrackingTouch(SeekBar var1) {
         }

         public void onStopTrackingTouch(SeekBar var1) {
         }
      }));
      ((SeekBar)this._$_findCachedViewById(R.id.sb_saturation)).setOnSeekBarChangeListener((SeekBar.OnSeekBarChangeListener)(new SeekBar.OnSeekBarChangeListener(this) {
         final MyPanoramicView this$0;

         {
            this.this$0 = var1;
         }

         public void onProgressChanged(SeekBar var1, int var2, boolean var3) {
            byte[] var4 = this.this$0.m0xC6Command;
            var4[2] = 73;
            var4[3] = (byte)var2;
            CanbusMsgSender.sendMsg(var4);
         }

         public void onStartTrackingTouch(SeekBar var1) {
         }

         public void onStopTrackingTouch(SeekBar var1) {
         }
      }));
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

   public final void showHideWindow() {
      GridLayout var2 = (GridLayout)this._$_findCachedViewById(R.id.gl_color_set);
      byte var1;
      if (((GridLayout)this._$_findCachedViewById(R.id.gl_color_set)).getVisibility() == 0) {
         var1 = 8;
      } else {
         var1 = 0;
      }

      var2.setVisibility(var1);
   }

   public final void update(int var1, int var2, int var3) {
      ((TextView)this._$_findCachedViewById(R.id.tv_brightness)).setText((CharSequence)String.valueOf(RangesKt.coerceIn(var1, (ClosedRange)(new IntRange(30, 70)))));
      ((TextView)this._$_findCachedViewById(R.id.tv_contrast)).setText((CharSequence)String.valueOf(RangesKt.coerceIn(var2, (ClosedRange)(new IntRange(30, 70)))));
      ((TextView)this._$_findCachedViewById(R.id.tv_saturation)).setText((CharSequence)String.valueOf(RangesKt.coerceIn(var3, (ClosedRange)(new IntRange(30, 70)))));
   }

   @Metadata(
      d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0005"},
      d2 = {"Lcom/hzbhd/canbus/car/_3/MyPanoramicView$Companion;", "", "()V", "TAG", "", "CanBusInfo_release"},
      k = 1,
      mv = {1, 7, 1},
      xi = 48
   )
   public static final class Companion {
      private Companion() {
      }

      // $FF: synthetic method
      public Companion(DefaultConstructorMarker var1) {
         this();
      }
   }
}
