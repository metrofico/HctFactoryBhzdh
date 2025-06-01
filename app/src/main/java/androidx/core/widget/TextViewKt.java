package androidx.core.widget;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TextView;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u00008\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\r\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a\u0080\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022d\b\u0006\u0010\u0003\u001a^\u0012\u0015\u0012\u0013\u0018\u00010\u0005¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0013\u0012\u00110\t¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\n\u0012\u0013\u0012\u00110\t¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\u000b\u0012\u0013\u0012\u00110\t¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\f\u0012\u0004\u0012\u00020\r0\u00042d\b\u0006\u0010\u000e\u001a^\u0012\u0015\u0012\u0013\u0018\u00010\u0005¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0013\u0012\u00110\t¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\n\u0012\u0013\u0012\u00110\t¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\u000b\u0012\u0013\u0012\u00110\t¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\f\u0012\u0004\u0012\u00020\r0\u00042%\b\u0006\u0010\u000f\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\u0011¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0004\u0012\u00020\r0\u0010H\u0086\b\u001a4\u0010\u0012\u001a\u00020\u0001*\u00020\u00022%\b\u0004\u0010\u0013\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\u0011¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0004\u0012\u00020\r0\u0010H\u0086\b\u001as\u0010\u0014\u001a\u00020\u0001*\u00020\u00022d\b\u0004\u0010\u0013\u001a^\u0012\u0015\u0012\u0013\u0018\u00010\u0005¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0013\u0012\u00110\t¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\n\u0012\u0013\u0012\u00110\t¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\u000b\u0012\u0013\u0012\u00110\t¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\f\u0012\u0004\u0012\u00020\r0\u0004H\u0086\b\u001as\u0010\u0015\u001a\u00020\u0001*\u00020\u00022d\b\u0004\u0010\u0013\u001a^\u0012\u0015\u0012\u0013\u0018\u00010\u0005¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0013\u0012\u00110\t¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\n\u0012\u0013\u0012\u00110\t¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\u000b\u0012\u0013\u0012\u00110\t¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\f\u0012\u0004\u0012\u00020\r0\u0004H\u0086\b¨\u0006\u0016"},
   d2 = {"addTextChangedListener", "Landroid/text/TextWatcher;", "Landroid/widget/TextView;", "beforeTextChanged", "Lkotlin/Function4;", "", "Lkotlin/ParameterName;", "name", "text", "", "start", "count", "after", "", "onTextChanged", "afterTextChanged", "Lkotlin/Function1;", "Landroid/text/Editable;", "doAfterTextChanged", "action", "doBeforeTextChanged", "doOnTextChanged", "core-ktx_release"},
   k = 2,
   mv = {1, 1, 15}
)
public final class TextViewKt {
   public static final TextWatcher addTextChangedListener(TextView var0, Function4 var1, Function4 var2, Function1 var3) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$addTextChangedListener");
      Intrinsics.checkParameterIsNotNull(var1, "beforeTextChanged");
      Intrinsics.checkParameterIsNotNull(var2, "onTextChanged");
      Intrinsics.checkParameterIsNotNull(var3, "afterTextChanged");
      TextWatcher var4 = (TextWatcher)(new TextWatcher(var3, var1, var2) {
         final Function1 $afterTextChanged;
         final Function4 $beforeTextChanged;
         final Function4 $onTextChanged;

         public {
            this.$afterTextChanged = var1;
            this.$beforeTextChanged = var2;
            this.$onTextChanged = var3;
         }

         public void afterTextChanged(Editable var1) {
            this.$afterTextChanged.invoke(var1);
         }

         public void beforeTextChanged(CharSequence var1, int var2, int var3, int var4) {
            this.$beforeTextChanged.invoke(var1, var2, var3, var4);
         }

         public void onTextChanged(CharSequence var1, int var2, int var3, int var4) {
            this.$onTextChanged.invoke(var1, var2, var3, var4);
         }
      });
      var0.addTextChangedListener(var4);
      return var4;
   }

   // $FF: synthetic method
   public static TextWatcher addTextChangedListener$default(TextView var0, Function4 var1, Function4 var2, Function1 var3, int var4, Object var5) {
      if ((var4 & 1) != 0) {
         var1 = (Function4)null.INSTANCE;
      }

      if ((var4 & 2) != 0) {
         var2 = (Function4)null.INSTANCE;
      }

      if ((var4 & 4) != 0) {
         var3 = (Function1)null.INSTANCE;
      }

      Intrinsics.checkParameterIsNotNull(var0, "$this$addTextChangedListener");
      Intrinsics.checkParameterIsNotNull(var1, "beforeTextChanged");
      Intrinsics.checkParameterIsNotNull(var2, "onTextChanged");
      Intrinsics.checkParameterIsNotNull(var3, "afterTextChanged");
      TextWatcher var6 = (TextWatcher)(new TextWatcher(var3, var1, var2) {
         final Function1 $afterTextChanged;
         final Function4 $beforeTextChanged;
         final Function4 $onTextChanged;

         public {
            this.$afterTextChanged = var1;
            this.$beforeTextChanged = var2;
            this.$onTextChanged = var3;
         }

         public void afterTextChanged(Editable var1) {
            this.$afterTextChanged.invoke(var1);
         }

         public void beforeTextChanged(CharSequence var1, int var2, int var3, int var4) {
            this.$beforeTextChanged.invoke(var1, var2, var3, var4);
         }

         public void onTextChanged(CharSequence var1, int var2, int var3, int var4) {
            this.$onTextChanged.invoke(var1, var2, var3, var4);
         }
      });
      var0.addTextChangedListener(var6);
      return var6;
   }

   public static final TextWatcher doAfterTextChanged(TextView var0, Function1 var1) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$doAfterTextChanged");
      Intrinsics.checkParameterIsNotNull(var1, "action");
      TextWatcher var2 = (TextWatcher)(new TextWatcher(var1) {
         final Function1 $afterTextChanged;

         public {
            this.$afterTextChanged = var1;
         }

         public void afterTextChanged(Editable var1) {
            this.$afterTextChanged.invoke(var1);
         }

         public void beforeTextChanged(CharSequence var1, int var2, int var3, int var4) {
         }

         public void onTextChanged(CharSequence var1, int var2, int var3, int var4) {
         }
      });
      var0.addTextChangedListener(var2);
      return var2;
   }

   public static final TextWatcher doBeforeTextChanged(TextView var0, Function4 var1) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$doBeforeTextChanged");
      Intrinsics.checkParameterIsNotNull(var1, "action");
      TextWatcher var2 = (TextWatcher)(new TextWatcher(var1) {
         final Function4 $beforeTextChanged;

         public {
            this.$beforeTextChanged = var1;
         }

         public void afterTextChanged(Editable var1) {
         }

         public void beforeTextChanged(CharSequence var1, int var2, int var3, int var4) {
            this.$beforeTextChanged.invoke(var1, var2, var3, var4);
         }

         public void onTextChanged(CharSequence var1, int var2, int var3, int var4) {
         }
      });
      var0.addTextChangedListener(var2);
      return var2;
   }

   public static final TextWatcher doOnTextChanged(TextView var0, Function4 var1) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$doOnTextChanged");
      Intrinsics.checkParameterIsNotNull(var1, "action");
      TextWatcher var2 = (TextWatcher)(new TextWatcher(var1) {
         final Function4 $onTextChanged;

         public {
            this.$onTextChanged = var1;
         }

         public void afterTextChanged(Editable var1) {
         }

         public void beforeTextChanged(CharSequence var1, int var2, int var3, int var4) {
         }

         public void onTextChanged(CharSequence var1, int var2, int var3, int var4) {
            this.$onTextChanged.invoke(var1, var2, var3, var4);
         }
      });
      var0.addTextChangedListener(var2);
      return var2;
   }
}
