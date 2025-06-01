package com.hzbhd.ui.base;

import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings.System;
import com.hzbhd.util.LogUtil;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeSet;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001AB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020\u00162\b\b\u0002\u0010,\u001a\u00020\u0006J\u0006\u0010-\u001a\u00020*J\u0006\u0010.\u001a\u00020*J\b\u0010/\u001a\u00020\u0006H\u0002J\u000e\u00100\u001a\u00020*2\u0006\u00101\u001a\u000202J\u0010\u00103\u001a\u00020*2\u0006\u00101\u001a\u000202H\u0002J\u0010\u00104\u001a\u00020*2\u0006\u00105\u001a\u00020\u0006H\u0002J\u0018\u00106\u001a\u00020*2\u0006\u0010+\u001a\u00020\u00162\b\b\u0002\u0010,\u001a\u00020\u0006J\u0014\u00107\u001a\u00020*2\f\u00108\u001a\b\u0012\u0004\u0012\u00020*09J\u001c\u0010:\u001a\u00020*2\f\u00108\u001a\b\u0012\u0004\u0012\u00020*092\u0006\u0010;\u001a\u00020<J\u0014\u0010=\u001a\u00020*2\f\u00108\u001a\b\u0012\u0004\u0012\u00020*09J\u001c\u0010>\u001a\u00020*2\f\u00108\u001a\b\u0012\u0004\u0012\u00020*092\u0006\u0010;\u001a\u00020<J\u0016\u0010?\u001a\u00020*2\u0006\u00101\u001a\u0002022\u0006\u0010@\u001a\u00020\u0006R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\b\"\u0004\b\r\u0010\nR \u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00060\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R-\u0010\u0014\u001a\u001e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00160\u0015j\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0016`\u0017¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u001a\u0010\u001a\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\b\"\u0004\b\u001c\u0010\nR\u001e\u0010\u001d\u001a\u0012\u0012\u0004\u0012\u00020\u00160\u001ej\b\u0012\u0004\u0012\u00020\u0016`\u001fX\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010 \u001a\u00020!8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b$\u0010%\u001a\u0004\b\"\u0010#R\u001b\u0010&\u001a\u00020!8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b(\u0010%\u001a\u0004\b'\u0010#¨\u0006B"},
   d2 = {"Lcom/hzbhd/ui/base/BhdViewUtil;", "", "()V", "COLOR_VIEW_COLOR_STYLE", "", "colorStyle", "", "getColorStyle", "()I", "setColorStyle", "(I)V", "currFocusId", "getCurrFocusId", "setCurrFocusId", "focusIds", "Ljava/util/TreeSet;", "getFocusIds", "()Ljava/util/TreeSet;", "setFocusIds", "(Ljava/util/TreeSet;)V", "focusMap", "Ljava/util/HashMap;", "Lcom/hzbhd/ui/base/BhdViewUtil$ViewUtilListener;", "Lkotlin/collections/HashMap;", "getFocusMap", "()Ljava/util/HashMap;", "handlerWhatIndex", "getHandlerWhatIndex", "setHandlerWhatIndex", "listeners", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "threadHandler", "Landroid/os/Handler;", "getThreadHandler", "()Landroid/os/Handler;", "threadHandler$delegate", "Lkotlin/Lazy;", "uiHandler", "getUiHandler", "uiHandler$delegate", "addListener", "", "listener", "focusId", "focusAdd", "focusCut", "getHandlerWhat", "init", "context", "Landroid/content/Context;", "initColor", "notifyFocusChange", "index", "removeListener", "runBack", "action", "Lkotlin/Function0;", "runBackDelay", "time", "", "runUi", "runUiDelay", "updateColorStyle", "style", "ViewUtilListener", "bhdview_release"},
   k = 1,
   mv = {1, 6, 0},
   xi = 48
)
public final class BhdViewUtil {
   private static final String COLOR_VIEW_COLOR_STYLE = "color_view_style";
   public static final BhdViewUtil INSTANCE = new BhdViewUtil();
   private static int colorStyle = 1;
   private static int currFocusId;
   private static TreeSet focusIds = new TreeSet();
   private static final HashMap focusMap = new HashMap();
   private static int handlerWhatIndex;
   private static final ArrayList listeners = new ArrayList();
   private static final Lazy threadHandler$delegate;
   private static final Lazy uiHandler$delegate;

   static {
      uiHandler$delegate = LazyKt.lazy((Function0)null.INSTANCE);
      threadHandler$delegate = LazyKt.lazy((Function0)null.INSTANCE);
   }

   private BhdViewUtil() {
   }

   // $FF: synthetic method
   public static void addListener$default(BhdViewUtil var0, ViewUtilListener var1, int var2, int var3, Object var4) {
      if ((var3 & 2) != 0) {
         var2 = 0;
      }

      var0.addListener(var1, var2);
   }

   private final int getHandlerWhat() {
      int var1 = handlerWhatIndex + 1;
      handlerWhatIndex = var1;
      return var1;
   }

   private final Handler getThreadHandler() {
      return (Handler)threadHandler$delegate.getValue();
   }

   private final Handler getUiHandler() {
      return (Handler)uiHandler$delegate.getValue();
   }

   private final void initColor(Context var1) {
      ContentResolver var2 = var1.getContentResolver();
      String var3 = COLOR_VIEW_COLOR_STYLE;
      colorStyle = System.getInt(var2, var3, colorStyle);
      var1.getContentResolver().registerContentObserver(System.getUriFor(var3), false, (ContentObserver)(new ContentObserver(var1, new Handler(Looper.getMainLooper())) {
         final Context $context;

         {
            this.$context = var1;
         }

         public void onChange(boolean var1) {
            ContentResolver var5 = this.$context.getContentResolver();
            String var4 = BhdViewUtil.COLOR_VIEW_COLOR_STYLE;
            int var2 = 0;
            int var3 = System.getInt(var5, var4, 0);
            if (LogUtil.log5()) {
               LogUtil.d("onChange: newColor = " + var3 + " , colorStyle=" + BhdViewUtil.INSTANCE.getColorStyle());
            }

            if (BhdViewUtil.INSTANCE.getColorStyle() != var3) {
               BhdViewUtil.INSTANCE.setColorStyle(var3);
               if (LogUtil.log5()) {
                  LogUtil.d(Intrinsics.stringPlus("onChange(): listeners.size = ", BhdViewUtil.listeners.size()));
               }

               for(var3 = BhdViewUtil.listeners.size(); var2 < var3; ++var2) {
                  ((ViewUtilListener)BhdViewUtil.listeners.get(var2)).onColorChange();
               }
            }

         }
      }));
   }

   private final void notifyFocusChange(int var1) {
      if (((Collection)focusIds).isEmpty() ^ true) {
         Iterator var2 = listeners.iterator();

         while(var2.hasNext()) {
            ViewUtilListener var3 = (ViewUtilListener)var2.next();
            var3.onFocusChange(Intrinsics.areEqual((Object)var3, (Object)((Map)focusMap).get(focusIds.toArray()[var1])));
         }
      }

   }

   // $FF: synthetic method
   public static void removeListener$default(BhdViewUtil var0, ViewUtilListener var1, int var2, int var3, Object var4) {
      if ((var3 & 2) != 0) {
         var2 = 0;
      }

      var0.removeListener(var1, var2);
   }

   public final void addListener(ViewUtilListener var1, int var2) {
      Intrinsics.checkNotNullParameter(var1, "listener");
      ArrayList var3 = listeners;
      if (!var3.contains(var1)) {
         var3.add(var1);
         var1.onColorChange();
      }

      if (var2 > 0) {
         if (!focusIds.contains(var2)) {
            focusIds.add(var2);
         }

         focusMap.put(var2, var1);
      }

   }

   public final void focusAdd() {
      int var2 = CollectionsKt.indexOf((Iterable)focusIds, currFocusId) + 1;
      int var1 = var2;
      if (var2 >= focusIds.size()) {
         var1 = 0;
      }

      this.notifyFocusChange(var1);
   }

   public final void focusCut() {
      int var2 = CollectionsKt.indexOf((Iterable)focusIds, currFocusId) - 1;
      int var1 = var2;
      if (var2 < 0) {
         var1 = 0;
      }

      this.notifyFocusChange(var1);
   }

   public final int getColorStyle() {
      return colorStyle;
   }

   public final int getCurrFocusId() {
      return currFocusId;
   }

   public final TreeSet getFocusIds() {
      return focusIds;
   }

   public final HashMap getFocusMap() {
      return focusMap;
   }

   public final int getHandlerWhatIndex() {
      return handlerWhatIndex;
   }

   public final void init(Context var1) {
      Intrinsics.checkNotNullParameter(var1, "context");
      this.initColor(var1);
   }

   public final void removeListener(ViewUtilListener var1, int var2) {
      Intrinsics.checkNotNullParameter(var1, "listener");
      ArrayList var3 = listeners;
      if (var3.contains(var1)) {
         var3.remove(var1);
      }

      if (var2 > 0) {
         if (focusIds.contains(var2)) {
            focusIds.remove(var2);
         }

         focusMap.remove(var2);
      }

   }

   public final void runBack(Function0 var1) {
      Intrinsics.checkNotNullParameter(var1, "action");
      this.getThreadHandler().sendMessage(this.getThreadHandler().obtainMessage(this.getHandlerWhat(), var1));
   }

   public final void runBackDelay(Function0 var1, long var2) {
      Intrinsics.checkNotNullParameter(var1, "action");
      this.getThreadHandler().sendMessageDelayed(this.getThreadHandler().obtainMessage(this.getHandlerWhat(), var1), var2);
   }

   public final void runUi(Function0 var1) {
      Intrinsics.checkNotNullParameter(var1, "action");
      this.getUiHandler().sendMessage(this.getUiHandler().obtainMessage(this.getHandlerWhat(), var1));
   }

   public final void runUiDelay(Function0 var1, long var2) {
      Intrinsics.checkNotNullParameter(var1, "action");
      this.getUiHandler().sendMessageDelayed(this.getUiHandler().obtainMessage(this.getHandlerWhat(), var1), var2);
   }

   public final void setColorStyle(int var1) {
      colorStyle = var1;
   }

   public final void setCurrFocusId(int var1) {
      currFocusId = var1;
   }

   public final void setFocusIds(TreeSet var1) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      focusIds = var1;
   }

   public final void setHandlerWhatIndex(int var1) {
      handlerWhatIndex = var1;
   }

   public final void updateColorStyle(Context var1, int var2) {
      Intrinsics.checkNotNullParameter(var1, "context");
      System.putInt(var1.getContentResolver(), COLOR_VIEW_COLOR_STYLE, var2);
   }

   @Metadata(
      d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\u0010\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H&¨\u0006\u0007"},
      d2 = {"Lcom/hzbhd/ui/base/BhdViewUtil$ViewUtilListener;", "", "onColorChange", "", "onFocusChange", "isFocus", "", "bhdview_release"},
      k = 1,
      mv = {1, 6, 0},
      xi = 48
   )
   public interface ViewUtilListener {
      void onColorChange();

      void onFocusChange(boolean var1);
   }
}
