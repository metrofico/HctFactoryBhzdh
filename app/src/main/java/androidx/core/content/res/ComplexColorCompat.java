package androidx.core.content.res;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Xml;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParserException;

public final class ComplexColorCompat {
   private static final String LOG_TAG = "ComplexColorCompat";
   private int mColor;
   private final ColorStateList mColorStateList;
   private final Shader mShader;

   private ComplexColorCompat(Shader var1, ColorStateList var2, int var3) {
      this.mShader = var1;
      this.mColorStateList = var2;
      this.mColor = var3;
   }

   private static ComplexColorCompat createFromXml(Resources var0, int var1, Resources.Theme var2) throws IOException, XmlPullParserException {
      XmlResourceParser var5 = var0.getXml(var1);
      AttributeSet var4 = Xml.asAttributeSet(var5);

      do {
         var1 = var5.next();
      } while(var1 != 2 && var1 != 1);

      if (var1 == 2) {
         String var3 = var5.getName();
         var3.hashCode();
         if (!var3.equals("gradient")) {
            if (var3.equals("selector")) {
               return from(ColorStateListInflaterCompat.createFromXmlInner(var0, var5, var4, var2));
            } else {
               throw new XmlPullParserException(var5.getPositionDescription() + ": unsupported complex color tag " + var3);
            }
         } else {
            return from(GradientColorInflaterCompat.createFromXmlInner(var0, var5, var4, var2));
         }
      } else {
         throw new XmlPullParserException("No start tag found");
      }
   }

   static ComplexColorCompat from(int var0) {
      return new ComplexColorCompat((Shader)null, (ColorStateList)null, var0);
   }

   static ComplexColorCompat from(ColorStateList var0) {
      return new ComplexColorCompat((Shader)null, var0, var0.getDefaultColor());
   }

   static ComplexColorCompat from(Shader var0) {
      return new ComplexColorCompat(var0, (ColorStateList)null, 0);
   }

   public static ComplexColorCompat inflate(Resources var0, int var1, Resources.Theme var2) {
      try {
         ComplexColorCompat var4 = createFromXml(var0, var1, var2);
         return var4;
      } catch (Exception var3) {
         Log.e("ComplexColorCompat", "Failed to inflate ComplexColor.", var3);
         return null;
      }
   }

   public int getColor() {
      return this.mColor;
   }

   public Shader getShader() {
      return this.mShader;
   }

   public boolean isGradient() {
      boolean var1;
      if (this.mShader != null) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public boolean isStateful() {
      boolean var1;
      if (this.mShader == null) {
         ColorStateList var2 = this.mColorStateList;
         if (var2 != null && var2.isStateful()) {
            var1 = true;
            return var1;
         }
      }

      var1 = false;
      return var1;
   }

   public boolean onStateChanged(int[] var1) {
      boolean var3;
      if (this.isStateful()) {
         ColorStateList var4 = this.mColorStateList;
         int var2 = var4.getColorForState(var1, var4.getDefaultColor());
         if (var2 != this.mColor) {
            var3 = true;
            this.mColor = var2;
            return var3;
         }
      }

      var3 = false;
      return var3;
   }

   public void setColor(int var1) {
      this.mColor = var1;
   }

   public boolean willDraw() {
      boolean var1;
      if (!this.isGradient() && this.mColor == 0) {
         var1 = false;
      } else {
         var1 = true;
      }

      return var1;
   }
}
