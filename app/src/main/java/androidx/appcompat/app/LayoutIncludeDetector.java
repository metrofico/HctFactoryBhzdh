package androidx.appcompat.app;

import android.util.AttributeSet;
import java.lang.ref.WeakReference;
import java.util.ArrayDeque;
import java.util.Deque;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

class LayoutIncludeDetector {
   private final Deque mXmlParserStack = new ArrayDeque();

   private static boolean isParserOutdated(XmlPullParser var0) {
      boolean var3 = true;
      boolean var2 = var3;
      if (var0 != null) {
         var2 = var3;

         int var1;
         try {
            if (var0.getEventType() == 3) {
               return var2;
            }

            var1 = var0.getEventType();
         } catch (XmlPullParserException var4) {
            var2 = var3;
            return var2;
         }

         if (var1 == 1) {
            var2 = var3;
         } else {
            var2 = false;
         }
      }

      return var2;
   }

   private static XmlPullParser popOutdatedAttrHolders(Deque var0) {
      while(true) {
         if (!var0.isEmpty()) {
            XmlPullParser var1 = (XmlPullParser)((WeakReference)var0.peek()).get();
            if (isParserOutdated(var1)) {
               var0.pop();
               continue;
            }

            return var1;
         }

         return null;
      }
   }

   private static boolean shouldInheritContext(XmlPullParser var0, XmlPullParser var1) {
      if (var1 != null && var0 != var1) {
         try {
            if (var1.getEventType() == 2) {
               boolean var2 = "include".equals(var1.getName());
               return var2;
            }
         } catch (XmlPullParserException var3) {
         }
      }

      return false;
   }

   boolean detect(AttributeSet var1) {
      if (var1 instanceof XmlPullParser) {
         XmlPullParser var3 = (XmlPullParser)var1;
         if (var3.getDepth() == 1) {
            XmlPullParser var2 = popOutdatedAttrHolders(this.mXmlParserStack);
            this.mXmlParserStack.push(new WeakReference(var3));
            if (shouldInheritContext(var3, var2)) {
               return true;
            }
         }
      }

      return false;
   }
}
