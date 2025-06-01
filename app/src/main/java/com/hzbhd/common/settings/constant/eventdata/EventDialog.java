package com.hzbhd.common.settings.constant.eventdata;

import android.view.View;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0007HÆ\u0003J)\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÆ\u0001J\u0013\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001J\t\u0010\u001a\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010¨\u0006\u001b"},
   d2 = {"Lcom/hzbhd/common/settings/constant/eventdata/EventDialog;", "", "click_id", "", "action", "Lcom/hzbhd/common/settings/constant/eventdata/Action;", "view", "Landroid/view/View;", "(Ljava/lang/String;Lcom/hzbhd/common/settings/constant/eventdata/Action;Landroid/view/View;)V", "getAction", "()Lcom/hzbhd/common/settings/constant/eventdata/Action;", "getClick_id", "()Ljava/lang/String;", "getView", "()Landroid/view/View;", "setView", "(Landroid/view/View;)V", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "settings-constant_release"},
   k = 1,
   mv = {1, 6, 0},
   xi = 48
)
public final class EventDialog {
   private final Action action;
   private final String click_id;
   private View view;

   public EventDialog(String var1, Action var2, View var3) {
      Intrinsics.checkNotNullParameter(var1, "click_id");
      Intrinsics.checkNotNullParameter(var2, "action");
      super();
      this.click_id = var1;
      this.action = var2;
      this.view = var3;
   }

   // $FF: synthetic method
   public EventDialog(String var1, Action var2, View var3, int var4, DefaultConstructorMarker var5) {
      if ((var4 & 4) != 0) {
         var3 = null;
      }

      this(var1, var2, var3);
   }

   // $FF: synthetic method
   public static EventDialog copy$default(EventDialog var0, String var1, Action var2, View var3, int var4, Object var5) {
      if ((var4 & 1) != 0) {
         var1 = var0.click_id;
      }

      if ((var4 & 2) != 0) {
         var2 = var0.action;
      }

      if ((var4 & 4) != 0) {
         var3 = var0.view;
      }

      return var0.copy(var1, var2, var3);
   }

   public final String component1() {
      return this.click_id;
   }

   public final Action component2() {
      return this.action;
   }

   public final View component3() {
      return this.view;
   }

   public final EventDialog copy(String var1, Action var2, View var3) {
      Intrinsics.checkNotNullParameter(var1, "click_id");
      Intrinsics.checkNotNullParameter(var2, "action");
      return new EventDialog(var1, var2, var3);
   }

   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (!(var1 instanceof EventDialog)) {
         return false;
      } else {
         EventDialog var2 = (EventDialog)var1;
         if (!Intrinsics.areEqual((Object)this.click_id, (Object)var2.click_id)) {
            return false;
         } else if (this.action != var2.action) {
            return false;
         } else {
            return Intrinsics.areEqual((Object)this.view, (Object)var2.view);
         }
      }
   }

   public final Action getAction() {
      return this.action;
   }

   public final String getClick_id() {
      return this.click_id;
   }

   public final View getView() {
      return this.view;
   }

   public int hashCode() {
      int var2 = this.click_id.hashCode();
      int var3 = this.action.hashCode();
      View var4 = this.view;
      int var1;
      if (var4 == null) {
         var1 = 0;
      } else {
         var1 = var4.hashCode();
      }

      return (var2 * 31 + var3) * 31 + var1;
   }

   public final void setView(View var1) {
      this.view = var1;
   }

   public String toString() {
      return "EventDialog(click_id=" + this.click_id + ", action=" + this.action + ", view=" + this.view + ')';
   }
}
