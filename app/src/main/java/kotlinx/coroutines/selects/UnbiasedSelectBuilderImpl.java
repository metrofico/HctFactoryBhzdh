package kotlinx.coroutines.selects;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0001\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00002\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0013\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004¢\u0006\u0002\u0010\u0005J\u0010\u0010\u0011\u001a\u00020\t2\u0006\u0010\u0012\u001a\u00020\u0013H\u0001J\n\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0001J6\u0010\u0016\u001a\u00020\t2\u0006\u0010\u0017\u001a\u00020\u00182\u001c\u0010\u0019\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00150\u001aH\u0016ø\u0001\u0000¢\u0006\u0002\u0010\u001bJ3\u0010\u001c\u001a\u00020\t*\u00020\u001d2\u001c\u0010\u0019\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00150\u001aH\u0096\u0002ø\u0001\u0000¢\u0006\u0002\u0010\u001eJE\u0010\u001c\u001a\u00020\t\"\u0004\b\u0001\u0010\u001f*\b\u0012\u0004\u0012\u0002H\u001f0 2\"\u0010\u0019\u001a\u001e\b\u0001\u0012\u0004\u0012\u0002H\u001f\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00150!H\u0096\u0002ø\u0001\u0000¢\u0006\u0002\u0010\"JY\u0010\u001c\u001a\u00020\t\"\u0004\b\u0001\u0010#\"\u0004\b\u0002\u0010\u001f*\u000e\u0012\u0004\u0012\u0002H#\u0012\u0004\u0012\u0002H\u001f0$2\u0006\u0010%\u001a\u0002H#2\"\u0010\u0019\u001a\u001e\b\u0001\u0012\u0004\u0012\u0002H\u001f\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00150!H\u0096\u0002ø\u0001\u0000¢\u0006\u0002\u0010&R-\u0010\u0006\u001a\u001e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b0\u0007j\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b`\n¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00028\u00000\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006'"},
   d2 = {"Lkotlinx/coroutines/selects/UnbiasedSelectBuilderImpl;", "R", "Lkotlinx/coroutines/selects/SelectBuilder;", "uCont", "Lkotlin/coroutines/Continuation;", "(Lkotlin/coroutines/Continuation;)V", "clauses", "Ljava/util/ArrayList;", "Lkotlin/Function0;", "", "Lkotlin/collections/ArrayList;", "getClauses", "()Ljava/util/ArrayList;", "instance", "Lkotlinx/coroutines/selects/SelectBuilderImpl;", "getInstance", "()Lkotlinx/coroutines/selects/SelectBuilderImpl;", "handleBuilderException", "e", "", "initSelectResult", "", "onTimeout", "timeMillis", "", "block", "Lkotlin/Function1;", "(JLkotlin/jvm/functions/Function1;)V", "invoke", "Lkotlinx/coroutines/selects/SelectClause0;", "(Lkotlinx/coroutines/selects/SelectClause0;Lkotlin/jvm/functions/Function1;)V", "Q", "Lkotlinx/coroutines/selects/SelectClause1;", "Lkotlin/Function2;", "(Lkotlinx/coroutines/selects/SelectClause1;Lkotlin/jvm/functions/Function2;)V", "P", "Lkotlinx/coroutines/selects/SelectClause2;", "param", "(Lkotlinx/coroutines/selects/SelectClause2;Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)V", "kotlinx-coroutines-core"},
   k = 1,
   mv = {1, 4, 0}
)
public final class UnbiasedSelectBuilderImpl implements SelectBuilder {
   private final ArrayList clauses;
   private final SelectBuilderImpl instance;

   public UnbiasedSelectBuilderImpl(Continuation var1) {
      this.instance = new SelectBuilderImpl(var1);
      this.clauses = new ArrayList();
   }

   public final ArrayList getClauses() {
      return this.clauses;
   }

   public final SelectBuilderImpl getInstance() {
      return this.instance;
   }

   public final void handleBuilderException(Throwable var1) {
      this.instance.handleBuilderException(var1);
   }

   public final Object initSelectResult() {
      if (!this.instance.isSelected()) {
         Throwable var10000;
         label75: {
            Iterator var1;
            boolean var10001;
            try {
               Collections.shuffle((List)this.clauses);
               var1 = ((Iterable)this.clauses).iterator();
            } catch (Throwable var7) {
               var10000 = var7;
               var10001 = false;
               break label75;
            }

            while(true) {
               try {
                  if (!var1.hasNext()) {
                     return this.instance.getResult();
                  }

                  ((Function0)var1.next()).invoke();
               } catch (Throwable var6) {
                  var10000 = var6;
                  var10001 = false;
                  break;
               }
            }
         }

         Throwable var8 = var10000;
         this.instance.handleBuilderException(var8);
      }

      return this.instance.getResult();
   }

   public void invoke(SelectClause0 var1, Function1 var2) {
      ((Collection)this.clauses).add(new Function0(this, var1, var2) {
         final Function1 $block;
         final SelectClause0 $this_invoke;
         final UnbiasedSelectBuilderImpl this$0;

         {
            this.this$0 = var1;
            this.$this_invoke = var2;
            this.$block = var3;
         }

         public final void invoke() {
            this.$this_invoke.registerSelectClause0((SelectInstance)this.this$0.getInstance(), this.$block);
         }
      });
   }

   public void invoke(SelectClause1 var1, Function2 var2) {
      ((Collection)this.clauses).add(new Function0(this, var1, var2) {
         final Function2 $block;
         final SelectClause1 $this_invoke;
         final UnbiasedSelectBuilderImpl this$0;

         {
            this.this$0 = var1;
            this.$this_invoke = var2;
            this.$block = var3;
         }

         public final void invoke() {
            this.$this_invoke.registerSelectClause1((SelectInstance)this.this$0.getInstance(), this.$block);
         }
      });
   }

   public void invoke(SelectClause2 var1, Object var2, Function2 var3) {
      ((Collection)this.clauses).add(new Function0(this, var1, var2, var3) {
         final Function2 $block;
         final Object $param;
         final SelectClause2 $this_invoke;
         final UnbiasedSelectBuilderImpl this$0;

         {
            this.this$0 = var1;
            this.$this_invoke = var2;
            this.$param = var3;
            this.$block = var4;
         }

         public final void invoke() {
            this.$this_invoke.registerSelectClause2((SelectInstance)this.this$0.getInstance(), this.$param, this.$block);
         }
      });
   }

   public void invoke(SelectClause2 var1, Function2 var2) {
      DefaultImpls.invoke(this, var1, var2);
   }

   public void onTimeout(long var1, Function1 var3) {
      ((Collection)this.clauses).add(new Function0(this, var1, var3) {
         final Function1 $block;
         final long $timeMillis;
         final UnbiasedSelectBuilderImpl this$0;

         {
            this.this$0 = var1;
            this.$timeMillis = var2;
            this.$block = var4;
         }

         public final void invoke() {
            this.this$0.getInstance().onTimeout(this.$timeMillis, this.$block);
         }
      });
   }
}
