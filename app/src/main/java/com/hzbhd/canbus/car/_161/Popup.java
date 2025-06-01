package com.hzbhd.canbus.car._161;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.hzbhd.canbus.R;
import com.hzbhd.canbus.util.TimerUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\u0018\u0000 \u00142\u00020\u0001:\u0003\u0014\u0015\u0016B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0011\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u000bH\u0086\u0002J\u0011\u0010\u0013\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u000bH\u0086\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"},
   d2 = {"Lcom/hzbhd/canbus/car/_161/Popup;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "mIsShowing", "", "mLayoutParams", "Landroid/view/WindowManager$LayoutParams;", "mPlayList", "Ljava/util/ArrayList;", "Lcom/hzbhd/canbus/car/_161/Popup$Ops$Update;", "mView", "Landroid/view/View;", "mWindowManager", "Landroid/view/WindowManager;", "minus", "", "bean", "plus", "Companion", "InfoBean", "Ops", "CanBusInfo_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class Popup {
   public static final Companion Companion = new Companion((DefaultConstructorMarker)null);
   private static final String TAG = "_161_Popup";
   private static final long WARNING_LOOP_INTERVAL = 3000L;
   private boolean mIsShowing;
   private final WindowManager.LayoutParams mLayoutParams;
   private final ArrayList mPlayList;
   private View mView;
   private final WindowManager mWindowManager;

   // $FF: synthetic method
   public static void $r8$lambda$vdOa3vQqS7LI2z9nqaiw3c5SLwQ(Object var0, Popup var1, View var2) {
      lambda_3$lambda_2(var0, var1, var2);
   }

   public Popup(Context var1) {
      Intrinsics.checkNotNullParameter(var1, "context");
      super();
      Object var2 = var1.getSystemService("window");
      Intrinsics.checkNotNull(var2, "null cannot be cast to non-null type android.view.WindowManager");
      this.mWindowManager = (WindowManager)var2;
      WindowManager.LayoutParams var5 = new WindowManager.LayoutParams();
      var5.type = 2002;
      var5.gravity = 17;
      var5.width = -2;
      var5.height = -2;
      this.mLayoutParams = var5;
      View var3 = LayoutInflater.from(var1).inflate(2131558401, (ViewGroup)null);
      Intrinsics.checkNotNullExpressionValue(var3, "from(context).inflate(R.…._161_layout_popup, null)");
      this.mView = var3;
      this.mPlayList = new ArrayList();
      Handler var4 = new Handler(this, Looper.getMainLooper()) {
         final Popup this$0;

         {
            this.this$0 = var1;
         }

         public void handleMessage(Message var1) {
            Intrinsics.checkNotNullParameter(var1, "msg");
            super.handleMessage(var1);
            Object var2 = var1.obj;
            if (Intrinsics.areEqual((Object)var2, (Object) Ops.Show.INSTANCE)) {
               if (!this.this$0.mIsShowing) {
                  this.this$0.mWindowManager.addView(this.this$0.mView, (ViewGroup.LayoutParams)this.this$0.mLayoutParams);
                  this.this$0.mIsShowing = true;
               }
            } else if (Intrinsics.areEqual((Object)var2, (Object) Ops.Hide.INSTANCE)) {
               if (this.this$0.mIsShowing) {
                  this.this$0.mWindowManager.removeView(this.this$0.mView);
                  this.this$0.mIsShowing = false;
               }
            } else if (var2 instanceof Ops.Update) {
               Object var3 = var1.obj;
               Intrinsics.checkNotNull(var3, "null cannot be cast to non-null type com.hzbhd.canbus.car._161.Popup.Ops.Update");
               InfoBean var4 = ((Ops.Update)var3).getBean();
               Popup var5 = this.this$0;
               ((ImageView)var5.mView.findViewById(R.id.iv_alert_icon)).setImageResource(var4.getImageResId());
               ((TextView)var5.mView.findViewById(R.id.tv_alert_message)).setText(var4.getMessageResId());
            }

         }
      };
      ((ImageButton)this.mView.findViewById(R.id.ib_close)).setOnClickListener(new Popup$$ExternalSyntheticLambda0(var4, this));
      (new TimerUtil()).startTimer((TimerTask)(new TimerTask(this, var4) {
         final <undefinedtype> $this_run;
         final Popup this$0;

         {
            this.this$0 = var1;
            this.$this_run = var2;
         }

         public void run() {
            Log.i("_161_Popup", "run: play:" + this.this$0.mPlayList.size());
            Message var1;
            <undefinedtype> var2;
            if (this.this$0.mPlayList.size() == 0) {
               var2 = this.$this_run;
               var1 = var2.obtainMessage();
               var1.obj = Ops.Hide.INSTANCE;
               var2.sendMessage(var1);
            } else {
               ArrayList var3 = this.this$0.mPlayList;
               <undefinedtype> var5 = this.$this_run;
               Message var4 = var5.obtainMessage();
               List var6 = (List)var3;
               var4.obj = CollectionsKt.first(var6);
               var5.sendMessage(var4);
               if (CollectionsKt.first(var6) instanceof Ops.WarningBean) {
                  var3.add(CollectionsKt.first(var6));
               }

               var3.remove(CollectionsKt.first(var6));
               var2 = this.$this_run;
               var1 = var2.obtainMessage();
               var1.obj = Ops.Show.INSTANCE;
               var2.sendMessage(var1);
            }

         }
      }), 0L, 3000L);
   }

   private static final void lambda_3$lambda_2(Object var0, Popup var1, View var2) {
      Intrinsics.checkNotNullParameter(var0, "$this_run");
      Intrinsics.checkNotNullParameter(var1, "this$0");
      Message var3 = var0.obtainMessage();
      var3.obj = Ops.Hide.INSTANCE;
      var0.sendMessage(var3);
      var1.mPlayList.clear();
   }

   public final void minus(Ops.Update var1) {
      Intrinsics.checkNotNullParameter(var1, "bean");
      this.mPlayList.remove(var1);
      Log.i("_161_Popup", "minus: " + this.mPlayList.size());
   }

   public final void plus(Ops.Update var1) {
      Intrinsics.checkNotNullParameter(var1, "bean");
      this.mPlayList.add(0, var1);
      Log.i("_161_Popup", "plus: " + this.mPlayList.size());
   }

   @Metadata(
      d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0007"},
      d2 = {"Lcom/hzbhd/canbus/car/_161/Popup$Companion;", "", "()V", "TAG", "", "WARNING_LOOP_INTERVAL", "", "CanBusInfo_release"},
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

   @Metadata(
      d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B!\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J'\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u001a\u0010\u0005\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\b\"\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\b¨\u0006\u0016"},
      d2 = {"Lcom/hzbhd/canbus/car/_161/Popup$InfoBean;", "", "imageResId", "", "messageResId", "isNeedShow", "(III)V", "getImageResId", "()I", "setNeedShow", "(I)V", "getMessageResId", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "", "CanBusInfo_release"},
      k = 1,
      mv = {1, 7, 1},
      xi = 48
   )
   public static final class InfoBean {
      private final int imageResId;
      private int isNeedShow;
      private final int messageResId;

      public InfoBean(int var1, int var2, int var3) {
         this.imageResId = var1;
         this.messageResId = var2;
         this.isNeedShow = var3;
      }

      // $FF: synthetic method
      public InfoBean(int var1, int var2, int var3, int var4, DefaultConstructorMarker var5) {
         if ((var4 & 1) != 0) {
            var1 = 2131232783;
         }

         if ((var4 & 4) != 0) {
            var3 = 0;
         }

         this(var1, var2, var3);
      }

      // $FF: synthetic method
      public static InfoBean copy$default(InfoBean var0, int var1, int var2, int var3, int var4, Object var5) {
         if ((var4 & 1) != 0) {
            var1 = var0.imageResId;
         }

         if ((var4 & 2) != 0) {
            var2 = var0.messageResId;
         }

         if ((var4 & 4) != 0) {
            var3 = var0.isNeedShow;
         }

         return var0.copy(var1, var2, var3);
      }

      public final int component1() {
         return this.imageResId;
      }

      public final int component2() {
         return this.messageResId;
      }

      public final int component3() {
         return this.isNeedShow;
      }

      public final InfoBean copy(int var1, int var2, int var3) {
         return new InfoBean(var1, var2, var3);
      }

      public boolean equals(Object var1) {
         if (this == var1) {
            return true;
         } else if (!(var1 instanceof InfoBean)) {
            return false;
         } else {
            InfoBean var2 = (InfoBean)var1;
            if (this.imageResId != var2.imageResId) {
               return false;
            } else if (this.messageResId != var2.messageResId) {
               return false;
            } else {
               return this.isNeedShow == var2.isNeedShow;
            }
         }
      }

      public final int getImageResId() {
         return this.imageResId;
      }

      public final int getMessageResId() {
         return this.messageResId;
      }

      public int hashCode() {
         return (Integer.hashCode(this.imageResId) * 31 + Integer.hashCode(this.messageResId)) * 31 + Integer.hashCode(this.isNeedShow);
      }

      public final int isNeedShow() {
         return this.isNeedShow;
      }

      public final void setNeedShow(int var1) {
         this.isNeedShow = var1;
      }

      public String toString() {
         return "InfoBean(imageResId=" + this.imageResId + ", messageResId=" + this.messageResId + ", isNeedShow=" + this.isNeedShow + ')';
      }
   }

   @Metadata(
      d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0005\u0003\u0004\u0005\u0006\u0007B\u0007\b\u0004¢\u0006\u0002\u0010\u0002\u0082\u0001\u0003\b\t\n¨\u0006\u000b"},
      d2 = {"Lcom/hzbhd/canbus/car/_161/Popup$Ops;", "", "()V", "FunctionBean", "Hide", "Show", "Update", "WarningBean", "Lcom/hzbhd/canbus/car/_161/Popup$Ops$Hide;", "Lcom/hzbhd/canbus/car/_161/Popup$Ops$Show;", "Lcom/hzbhd/canbus/car/_161/Popup$Ops$Update;", "CanBusInfo_release"},
      k = 1,
      mv = {1, 7, 1},
      xi = 48
   )
   public abstract static class Ops {
      private Ops() {
      }

      // $FF: synthetic method
      public Ops(DefaultConstructorMarker var1) {
         this();
      }

      @Metadata(
         d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\t"},
         d2 = {"Lcom/hzbhd/canbus/car/_161/Popup$Ops$FunctionBean;", "Lcom/hzbhd/canbus/car/_161/Popup$Ops$Update;", "imageResId", "", "messageResId", "(II)V", "getImageResId", "()I", "getMessageResId", "CanBusInfo_release"},
         k = 1,
         mv = {1, 7, 1},
         xi = 48
      )
      public static final class FunctionBean extends Update {
         private final int imageResId;
         private final int messageResId;

         public FunctionBean(int var1, int var2) {
            super(new InfoBean(var1, var2, 0, 4, (DefaultConstructorMarker)null));
            this.imageResId = var1;
            this.messageResId = var2;
         }

         public final int getImageResId() {
            return this.imageResId;
         }

         public final int getMessageResId() {
            return this.messageResId;
         }
      }

      @Metadata(
         d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"},
         d2 = {"Lcom/hzbhd/canbus/car/_161/Popup$Ops$Hide;", "Lcom/hzbhd/canbus/car/_161/Popup$Ops;", "()V", "CanBusInfo_release"},
         k = 1,
         mv = {1, 7, 1},
         xi = 48
      )
      public static final class Hide extends Ops {
         public static final Hide INSTANCE = new Hide();

         private Hide() {
            super((DefaultConstructorMarker)null);
         }
      }

      @Metadata(
         d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"},
         d2 = {"Lcom/hzbhd/canbus/car/_161/Popup$Ops$Show;", "Lcom/hzbhd/canbus/car/_161/Popup$Ops;", "()V", "CanBusInfo_release"},
         k = 1,
         mv = {1, 7, 1},
         xi = 48
      )
      public static final class Show extends Ops {
         public static final Show INSTANCE = new Show();

         private Show() {
            super((DefaultConstructorMarker)null);
         }
      }

      @Metadata(
         d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0016\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"},
         d2 = {"Lcom/hzbhd/canbus/car/_161/Popup$Ops$Update;", "Lcom/hzbhd/canbus/car/_161/Popup$Ops;", "bean", "Lcom/hzbhd/canbus/car/_161/Popup$InfoBean;", "(Lcom/hzbhd/canbus/car/_161/Popup$InfoBean;)V", "getBean", "()Lcom/hzbhd/canbus/car/_161/Popup$InfoBean;", "CanBusInfo_release"},
         k = 1,
         mv = {1, 7, 1},
         xi = 48
      )
      public static class Update extends Ops {
         private final InfoBean bean;

         public Update(InfoBean var1) {
            Intrinsics.checkNotNullParameter(var1, "bean");
            super((DefaultConstructorMarker)null);
            this.bean = var1;
         }

         public final InfoBean getBean() {
            return this.bean;
         }
      }

      @Metadata(
         d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"},
         d2 = {"Lcom/hzbhd/canbus/car/_161/Popup$Ops$WarningBean;", "Lcom/hzbhd/canbus/car/_161/Popup$Ops$Update;", "messageResId", "", "(I)V", "getMessageResId", "()I", "CanBusInfo_release"},
         k = 1,
         mv = {1, 7, 1},
         xi = 48
      )
      public static final class WarningBean extends Update {
         private final int messageResId;

         public WarningBean(int var1) {
            super(new InfoBean(0, var1, 0, 5, (DefaultConstructorMarker)null));
            this.messageResId = var1;
         }

         public final int getMessageResId() {
            return this.messageResId;
         }
      }
   }
}
