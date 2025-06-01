package com.hzbhd.ui.view.colorview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.TextView;
import com.hzbhd.ui.view.R;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\b\u0016\u0018\u00002\u00020\u00012\u00020\u0002B\u0019\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007B!\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\b\u0010!\u001a\u00020\"H\u0014J\u001a\u0010#\u001a\u00020\"2\u0006\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0002J\b\u0010$\u001a\u00020\"H\u0014J\b\u0010%\u001a\u00020\"H\u0016J\b\u0010&\u001a\u00020\"H\u0014J\u0010\u0010'\u001a\u00020\"2\u0006\u0010(\u001a\u00020)H\u0016R\u001a\u0010\u000b\u001a\u00020\tX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0010\u001a\u00020\tX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\r\"\u0004\b\u0012\u0010\u000fR\u001a\u0010\u0013\u001a\u00020\tX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\r\"\u0004\b\u0015\u0010\u000fR\u001a\u0010\u0016\u001a\u00020\tX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\r\"\u0004\b\u0018\u0010\u000fR\u001a\u0010\u0019\u001a\u00020\u001aX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u000e\u0010\u001e\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006*"},
   d2 = {"Lcom/hzbhd/ui/view/colorview/ColorView;", "Landroid/widget/TextView;", "Lcom/hzbhd/ui/view/colorview/ColorUtil$ColorViewInterface;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "id_d", "getId_d", "()I", "setId_d", "(I)V", "id_n", "getId_n", "setId_n", "id_p", "getId_p", "setId_p", "id_s", "getId_s", "setId_s", "isColorImage", "", "()Z", "setColorImage", "(Z)V", "mix_type", "n_mix_color", "p_mix_color", "drawableStateChanged", "", "init", "onAttachedToWindow", "onColorChange", "onDetachedFromWindow", "setBackground", "background", "Landroid/graphics/drawable/Drawable;", "commonview-base_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public class ColorView extends TextView implements ColorUtil.ColorViewInterface {
   private int id_d;
   private int id_n;
   private int id_p;
   private int id_s;
   private boolean isColorImage;
   private int mix_type;
   private int n_mix_color;
   private int p_mix_color;

   public ColorView(Context var1, AttributeSet var2) {
      Intrinsics.checkNotNullParameter(var1, "context");
      super(var1, var2);
      this.init(var1, var2);
   }

   public ColorView(Context var1, AttributeSet var2, int var3) {
      Intrinsics.checkNotNullParameter(var1, "context");
      super(var1, var2, var3);
      this.init(var1, var2);
   }

   private final void init(Context var1, AttributeSet var2) {
      TypedArray var5 = var1.obtainStyledAttributes(var2, R.styleable.color_attr);
      boolean var4 = var5.getBoolean(R.styleable.color_attr_is_color_view, false);
      this.isColorImage = var4;
      if (var4) {
         this.mix_type = var5.getInt(R.styleable.color_attr_mix_type, 0);
         this.n_mix_color = var5.getColor(R.styleable.color_attr_n_mix_color, 0);
         this.p_mix_color = var5.getColor(R.styleable.color_attr_p_mix_color, 0);
      } else {
         this.id_n = var5.getResourceId(R.styleable.color_attr_n, 0);
         this.id_p = var5.getResourceId(R.styleable.color_attr_p, 0);
         this.id_s = var5.getResourceId(R.styleable.color_attr_s, 0);
         int var3 = var5.getResourceId(R.styleable.color_attr_d, 0);
         this.id_d = var3;
         if (this.id_n != 0 || this.id_p != 0 || this.id_s != 0 || var3 != 0) {
            this.setBackground((Drawable)(new PressedDrawable(var1, this.id_n, this.id_p, this.id_s, this.id_d)));
         }
      }

      var5.recycle();
   }

   protected void drawableStateChanged() {
      super.drawableStateChanged();
      if (this.isColorImage && this.getBackground() != null) {
         ColorUtil var2 = ColorUtil.instance;
         Drawable var1 = this.getBackground().getCurrent();
         Intrinsics.checkNotNullExpressionValue(var1, "background.current");
         var2.viewStateChange(var1, this.isPressed());
      }

   }

   protected final int getId_d() {
      return this.id_d;
   }

   protected final int getId_n() {
      return this.id_n;
   }

   protected final int getId_p() {
      return this.id_p;
   }

   protected final int getId_s() {
      return this.id_s;
   }

   protected final boolean isColorImage() {
      return this.isColorImage;
   }

   protected void onAttachedToWindow() {
      super.onAttachedToWindow();
      if (this.isColorImage) {
         ColorUtil.instance.addColorInterface((ColorUtil.ColorViewInterface)this);
      }

   }

   public void onColorChange() {
      if (this.getBackground() != null && this.isColorImage) {
         ColorUtil var1 = ColorUtil.instance;
         Drawable var2 = this.getBackground().getCurrent();
         Intrinsics.checkNotNullExpressionValue(var2, "background.current");
         var1.viewStateChange(var2, this.isPressed());
      }

   }

   protected void onDetachedFromWindow() {
      super.onDetachedFromWindow();
      if (this.isColorImage) {
         ColorUtil.instance.removeColorInterface((ColorUtil.ColorViewInterface)this);
      }

   }

   public void setBackground(Drawable var1) {
      Intrinsics.checkNotNullParameter(var1, "background");
      super.setBackground(var1);
      if (this.isColorImage) {
         ColorUtil.instance.viewStateChange(var1, this.isPressed());
      }

   }

   protected final void setColorImage(boolean var1) {
      this.isColorImage = var1;
   }

   protected final void setId_d(int var1) {
      this.id_d = var1;
   }

   protected final void setId_n(int var1) {
      this.id_n = var1;
   }

   protected final void setId_p(int var1) {
      this.id_p = var1;
   }

   protected final void setId_s(int var1) {
      this.id_s = var1;
   }
}
