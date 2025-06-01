package kotlinx.coroutines.android;

import android.os.Build.VERSION;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import kotlin.Metadata;
import kotlin.coroutines.AbstractCoroutineContextElement;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.CoroutineExceptionHandler;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0001\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u0018\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\n\u0010\f\u001a\u0004\u0018\u00010\rH\u0002R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000e"},
   d2 = {"Lkotlinx/coroutines/android/AndroidExceptionPreHandler;", "Lkotlin/coroutines/AbstractCoroutineContextElement;", "Lkotlinx/coroutines/CoroutineExceptionHandler;", "()V", "_preHandler", "", "handleException", "", "context", "Lkotlin/coroutines/CoroutineContext;", "exception", "", "preHandler", "Ljava/lang/reflect/Method;", "kotlinx-coroutines-android"},
   k = 1,
   mv = {1, 4, 0}
)
public final class AndroidExceptionPreHandler extends AbstractCoroutineContextElement implements CoroutineExceptionHandler {
   private volatile Object _preHandler = this;

   public AndroidExceptionPreHandler() {
      super((CoroutineContext.Key)CoroutineExceptionHandler.Key);
   }

   private final Method preHandler() {
      Object var5 = this._preHandler;
      AndroidExceptionPreHandler var4 = (AndroidExceptionPreHandler)this;
      if (var5 != this) {
         return (Method)var5;
      } else {
         Method var12 = null;
         boolean var2 = false;

         label114: {
            boolean var1;
            Method var13;
            label122: {
               boolean var10001;
               try {
                  var13 = Thread.class.getDeclaredMethod("getUncaughtExceptionPreHandler");
               } catch (Throwable var11) {
                  var10001 = false;
                  break label114;
               }

               var1 = var2;

               boolean var3;
               try {
                  if (!Modifier.isPublic(var13.getModifiers())) {
                     break label122;
                  }

                  var3 = Modifier.isStatic(var13.getModifiers());
               } catch (Throwable var10) {
                  var10001 = false;
                  break label114;
               }

               var1 = var2;
               if (var3) {
                  var1 = true;
               }
            }

            if (var1) {
               var12 = var13;
            }
         }

         this._preHandler = var12;
         return var12;
      }
   }

   public void handleException(CoroutineContext var1, Throwable var2) {
      Thread var4 = Thread.currentThread();
      if (VERSION.SDK_INT >= 28) {
         var4.getUncaughtExceptionHandler().uncaughtException(var4, var2);
      } else {
         Method var5 = this.preHandler();
         Object var3 = null;
         Object var6;
         if (var5 != null) {
            var6 = var5.invoke((Object)null);
         } else {
            var6 = null;
         }

         if (!(var6 instanceof Thread.UncaughtExceptionHandler)) {
            var6 = var3;
         }

         Thread.UncaughtExceptionHandler var7 = (Thread.UncaughtExceptionHandler)var6;
         if (var7 != null) {
            var7.uncaughtException(var4, var2);
         }
      }

   }
}
