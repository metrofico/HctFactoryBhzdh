package com.hzbhd.canbus.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.hzbhd.canbus.R;
import com.hzbhd.canbus.adapter.FactoryItemAdapter;
import com.hzbhd.canbus.config.CanbusConfig;
import com.hzbhd.canbus.config.GeneralSettingsConfig;
import com.hzbhd.canbus.factory.Dependency;
import com.hzbhd.canbus.factory.proxy.CanSettingProxy;
import com.hzbhd.canbus.util.TouchpadEvents;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\u0010\u000b\n\u0002\b\u0003\u0018\u0000 \u00122\u00020\u0001:\u0002\u0012\u0013B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\n\u001a\u00020\u000bH\u0002J\u0012\u0010\f\u001a\u00020\u000b2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0014J\f\u0010\u000f\u001a\u00020\u0010*\u00020\u0011H\u0002R\u001b\u0010\u0003\u001a\u00020\u00008FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0006\u0010\u0007\u001a\u0004\b\u0004\u0010\u0005R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"},
   d2 = {"Lcom/hzbhd/canbus/activity/FactoryActivity;", "Landroid/app/Activity;", "()V", "instance", "getInstance", "()Lcom/hzbhd/canbus/activity/FactoryActivity;", "instance$delegate", "Lkotlin/Lazy;", "mProxy", "Lcom/hzbhd/canbus/factory/proxy/CanSettingProxy;", "initView", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "toInt", "", "", "Companion", "ItemUiSet", "CanBusInfo_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class FactoryActivity extends Activity {
   public static final Companion Companion = new Companion((DefaultConstructorMarker)null);
   public static final int STYLE_0 = 0;
   public static final int STYLE_1 = 1;
   public static final int STYLE_4 = 4;
   public Map _$_findViewCache;
   private final Lazy instance$delegate;
   private final CanSettingProxy mProxy;

   public FactoryActivity() {
      this._$_findViewCache = (Map)(new LinkedHashMap());
      super();
      Object var1 = Dependency.get(CanSettingProxy.class);
      Intrinsics.checkNotNullExpressionValue(var1, "get(CanSettingProxy::class.java)");
      this.mProxy = (CanSettingProxy)var1;
      this.instance$delegate = LazyKt.lazy((Function0)(new Function0(this) {
         final FactoryActivity this$0;

         {
            this.this$0 = var1;
         }

         public final FactoryActivity invoke() {
            return this.this$0;
         }
      }));
   }

   private final void initView() {
      RecyclerView var2 = (RecyclerView)this._$_findCachedViewById(R.id.rv_setting_items);
      Context var1 = (Context)this;
      var2.setLayoutManager((RecyclerView.LayoutManager)(new LinearLayoutManager(var1)));
      RecyclerView var4 = (RecyclerView)this._$_findCachedViewById(R.id.rv_setting_items);
      ArrayList var3 = new ArrayList();
      if (GeneralSettingsConfig.getInstance(CanbusConfig.INSTANCE.getCanType()).RADAR_SHOW_TAG) {
         var3.add(new ItemUiSet("radar_display", 4, (Function1)(new Function1(this) {
            final FactoryActivity this$0;

            {
               this.this$0 = var1;
            }

            public final void invoke(int var1) {
               CanSettingProxy var3 = this.this$0.mProxy;
               boolean var2 = true;
               if (var1 != 1) {
                  var2 = false;
               }

               var3.setRadarDispCheck(var2);
            }
         }), (Function0)(new Function0(this) {
            final FactoryActivity this$0;

            {
               this.this$0 = var1;
            }

            public final Integer invoke() {
               FactoryActivity var1 = this.this$0;
               return var1.toInt(var1.mProxy.getRadarDispCheck());
            }
         }), (List)null, (Function0)null, 48, (DefaultConstructorMarker)null));
      }

      if (GeneralSettingsConfig.getInstance(CanbusConfig.INSTANCE.getCanType()).BACK_TRAJECTORY_REVERSAL) {
         var3.add(new ItemUiSet("back_trajectiry_reversal", 4, (Function1)(new Function1(this) {
            final FactoryActivity this$0;

            {
               this.this$0 = var1;
            }

            public final void invoke(int var1) {
               CanSettingProxy var3 = this.this$0.mProxy;
               boolean var2 = true;
               if (var1 != 1) {
                  var2 = false;
               }

               var3.setBackTrajectiryDispCheck(var2);
            }
         }), (Function0)(new Function0(this) {
            final FactoryActivity this$0;

            {
               this.this$0 = var1;
            }

            public final Integer invoke() {
               FactoryActivity var1 = this.this$0;
               return var1.toInt(var1.mProxy.getBackTrajectiryDispCheck());
            }
         }), (List)null, (Function0)null, 48, (DefaultConstructorMarker)null));
      }

      if (GeneralSettingsConfig.getInstance(CanbusConfig.INSTANCE.getCanType()).DOOR_SHOW_TAG) {
         var3.add(new ItemUiSet("show_door_info", 4, (Function1)(new Function1(this) {
            final FactoryActivity this$0;

            {
               this.this$0 = var1;
            }

            public final void invoke(int var1) {
               CanSettingProxy var3 = this.this$0.mProxy;
               boolean var2 = true;
               if (var1 != 1) {
                  var2 = false;
               }

               var3.setShowDoorInfo(var2);
            }
         }), (Function0)(new Function0(this) {
            final FactoryActivity this$0;

            {
               this.this$0 = var1;
            }

            public final Integer invoke() {
               FactoryActivity var1 = this.this$0;
               return var1.toInt(var1.mProxy.getShowDoorInfo());
            }
         }), (List)null, (Function0)null, 48, (DefaultConstructorMarker)null));
      }

      if (GeneralSettingsConfig.getInstance(CanbusConfig.INSTANCE.getCanType()).FRONT_DOOR_LEFT_RIGHT_EXCHANGE_TAG) {
         var3.add(new ItemUiSet("swap_front_door", 4, (Function1)(new Function1(this) {
            final FactoryActivity this$0;

            {
               this.this$0 = var1;
            }

            public final void invoke(int var1) {
               CanSettingProxy var3 = this.this$0.mProxy;
               boolean var2 = true;
               if (var1 != 1) {
                  var2 = false;
               }

               var3.setDoorSwapFront(var2);
            }
         }), (Function0)(new Function0(this) {
            final FactoryActivity this$0;

            {
               this.this$0 = var1;
            }

            public final Integer invoke() {
               FactoryActivity var1 = this.this$0;
               return var1.toInt(var1.mProxy.getDoorSwapFront());
            }
         }), (List)null, (Function0)null, 48, (DefaultConstructorMarker)null));
      }

      if (GeneralSettingsConfig.getInstance(CanbusConfig.INSTANCE.getCanType()).REAR_DOOR_LEFT_RIGHT_EXCHANGE_TAG) {
         var3.add(new ItemUiSet("swap_rear_door", 4, (Function1)(new Function1(this) {
            final FactoryActivity this$0;

            {
               this.this$0 = var1;
            }

            public final void invoke(int var1) {
               CanSettingProxy var3 = this.this$0.mProxy;
               boolean var2 = true;
               if (var1 != 1) {
                  var2 = false;
               }

               var3.setDoorSwapRear(var2);
            }
         }), (Function0)(new Function0(this) {
            final FactoryActivity this$0;

            {
               this.this$0 = var1;
            }

            public final Integer invoke() {
               FactoryActivity var1 = this.this$0;
               return var1.toInt(var1.mProxy.getDoorSwapRear());
            }
         }), (List)null, (Function0)null, 48, (DefaultConstructorMarker)null));
      }

      if (GeneralSettingsConfig.getInstance(CanbusConfig.INSTANCE.getCanType()).COUNT_DOWN_TIMER_REMOVE_DOOR_VIEW_SWITCH) {
         var3.add(new ItemUiSet("_factoryActivity_add_function1", 4, (Function1)(new Function1(this) {
            final FactoryActivity this$0;

            {
               this.this$0 = var1;
            }

            public final void invoke(int var1) {
               CanSettingProxy var3 = this.this$0.mProxy;
               boolean var2 = true;
               if (var1 != 1) {
                  var2 = false;
               }

               var3.setDoorCountDownTimerState(var2);
            }
         }), (Function0)(new Function0(this) {
            final FactoryActivity this$0;

            {
               this.this$0 = var1;
            }

            public final Integer invoke() {
               FactoryActivity var1 = this.this$0;
               return var1.toInt(var1.mProxy.getDoorCountDownTimerState());
            }
         }), (List)null, (Function0)null, 48, (DefaultConstructorMarker)null));
      }

      if (GeneralSettingsConfig.getInstance(CanbusConfig.INSTANCE.getCanType()).THE_HOOD_SHOW_TAG) {
         var3.add(new ItemUiSet("show_the_hood", 4, (Function1)(new Function1(this) {
            final FactoryActivity this$0;

            {
               this.this$0 = var1;
            }

            public final void invoke(int var1) {
               CanSettingProxy var3 = this.this$0.mProxy;
               boolean var2 = true;
               if (var1 != 1) {
                  var2 = false;
               }

               var3.setShowHood(var2);
            }
         }), (Function0)(new Function0(this) {
            final FactoryActivity this$0;

            {
               this.this$0 = var1;
            }

            public final Integer invoke() {
               FactoryActivity var1 = this.this$0;
               return var1.toInt(var1.mProxy.getShowHood());
            }
         }), (List)null, (Function0)null, 48, (DefaultConstructorMarker)null));
      }

      if (GeneralSettingsConfig.getInstance(CanbusConfig.INSTANCE.getCanType()).TRUNK_SHOW_TAG) {
         var3.add(new ItemUiSet("show_the_trunk", 4, (Function1)(new Function1(this) {
            final FactoryActivity this$0;

            {
               this.this$0 = var1;
            }

            public final void invoke(int var1) {
               CanSettingProxy var3 = this.this$0.mProxy;
               boolean var2 = true;
               if (var1 != 1) {
                  var2 = false;
               }

               var3.setShowTrunk(var2);
            }
         }), (Function0)(new Function0(this) {
            final FactoryActivity this$0;

            {
               this.this$0 = var1;
            }

            public final Integer invoke() {
               FactoryActivity var1 = this.this$0;
               return var1.toInt(var1.mProxy.getShowTrunk());
            }
         }), (List)null, (Function0)null, 48, (DefaultConstructorMarker)null));
      }

      if (GeneralSettingsConfig.getInstance(CanbusConfig.INSTANCE.getCanType()).AIR_LEFT_RIGHT_TEMPERATURE_EXCHANGE_TAG) {
         var3.add(new ItemUiSet("hzbhd_switch_ac_temperature", 4, (Function1)(new Function1(this) {
            final FactoryActivity this$0;

            {
               this.this$0 = var1;
            }

            public final void invoke(int var1) {
               CanSettingProxy var3 = this.this$0.mProxy;
               boolean var2 = true;
               if (var1 != 1) {
                  var2 = false;
               }

               var3.setSwitchAcTemperature(var2);
            }
         }), (Function0)(new Function0(this) {
            final FactoryActivity this$0;

            {
               this.this$0 = var1;
            }

            public final Integer invoke() {
               FactoryActivity var1 = this.this$0;
               return var1.toInt(var1.mProxy.getSwitchAcTemperature());
            }
         }), (List)null, (Function0)null, 48, (DefaultConstructorMarker)null));
      }

      if (GeneralSettingsConfig.getInstance(CanbusConfig.INSTANCE.getCanType()).AIR_SHOW_TAG) {
         var3.add(new ItemUiSet("air_display_setup", 1, (Function1)(new Function1(this) {
            final FactoryActivity this$0;

            {
               this.this$0 = var1;
            }

            public final void invoke(int var1) {
               this.this$0.mProxy.setAirDisplaySetup(var1);
            }
         }), (Function0)(new Function0(this) {
            final FactoryActivity this$0;

            {
               this.this$0 = var1;
            }

            public final Integer invoke() {
               return this.this$0.mProxy.getAirDisplaySetup();
            }
         }), CollectionsKt.mutableListOf(new String[]{"air_popup_status_0", "air_popup_status_1", "air_popup_status_2"}), (Function0)null, 32, (DefaultConstructorMarker)null));
      }

      if (GeneralSettingsConfig.getInstance(CanbusConfig.INSTANCE.getCanType()).OUT_TEMPERATURE_SHOW_TAG) {
         var3.add(new ItemUiSet("show_outdoor_temperature", 4, (Function1)(new Function1(this) {
            final FactoryActivity this$0;

            {
               this.this$0 = var1;
            }

            public final void invoke(int var1) {
               CanSettingProxy var3 = this.this$0.mProxy;
               boolean var2 = true;
               if (var1 != 1) {
                  var2 = false;
               }

               var3.setShowOutdoorTemperature(var2);
            }
         }), (Function0)(new Function0(this) {
            final FactoryActivity this$0;

            {
               this.this$0 = var1;
            }

            public final Integer invoke() {
               FactoryActivity var1 = this.this$0;
               return var1.toInt(var1.mProxy.getShowOutdoorTemperature());
            }
         }), (List)null, (Function0)null, 48, (DefaultConstructorMarker)null));
      }

      if (GeneralSettingsConfig.getInstance(CanbusConfig.INSTANCE.getCanType()).TEMPERATURE_UNIT_TAG) {
         var3.add(new ItemUiSet("temperature_unit", 1, (Function1)(new Function1(this) {
            final FactoryActivity this$0;

            {
               this.this$0 = var1;
            }

            public final void invoke(int var1) {
               this.this$0.mProxy.setTemperatureUnit(var1);
            }
         }), (Function0)(new Function0(this) {
            final FactoryActivity this$0;

            {
               this.this$0 = var1;
            }

            public final Integer invoke() {
               return this.this$0.mProxy.getTemperatureUnit();
            }
         }), CollectionsKt.mutableListOf(new String[]{"str_temp_c_unit", "str_temp_f_unit"}), (Function0)null, 32, (DefaultConstructorMarker)null));
      }

      if (GeneralSettingsConfig.getInstance(CanbusConfig.INSTANCE.getCanType()).SWC_STUDY_TAG) {
         var3.add(new ItemUiSet("swc_app_name", 0, (Function1)null, (Function0)null, (List)null, (Function0)null.INSTANCE, 28, (DefaultConstructorMarker)null));
      }

      if (GeneralSettingsConfig.getInstance(CanbusConfig.INSTANCE.getCanType()).FRONT_CAMERA_TAG) {
         var3.add(new ItemUiSet("Factory_activity_open_camera", 0, (Function1)null, (Function0)null, (List)null, (Function0)null.INSTANCE, 28, (DefaultConstructorMarker)null));
      }

      if (GeneralSettingsConfig.getInstance(CanbusConfig.INSTANCE.getCanType()).TOUCH_PAD_SETTING_TAG) {
         var3.add(new ItemUiSet("car_pannale_control", 0, (Function1)null, (Function0)null, (List)null, (Function0)(new Function0(this) {
            final FactoryActivity this$0;

            {
               this.this$0 = var1;
            }

            public final void invoke() {
               (new TouchpadEvents()).showAdjustView((Context)this.this$0.getInstance());
            }
         }), 28, (DefaultConstructorMarker)null));
      }

      if (GeneralSettingsConfig.getInstance(CanbusConfig.INSTANCE.getCanType()).CAN_BUS_TEST_TAG) {
         var3.add(new ItemUiSet("_open_bugly_item", 0, (Function1)null, (Function0)null, (List)null, (Function0)null.INSTANCE, 28, (DefaultConstructorMarker)null));
      }

      if (GeneralSettingsConfig.getInstance(CanbusConfig.INSTANCE.getCanType()).SHOW_HIDE_SMART_PANEL_PAGE) {
         var3.add(new ItemUiSet("_439_panel_app_show_hide_switch", 0, (Function1)null, (Function0)null, (List)null, (Function0)null.INSTANCE, 28, (DefaultConstructorMarker)null));
      }

      if (GeneralSettingsConfig.getInstance(CanbusConfig.INSTANCE.getCanType()).SHOW_P_KEY_RADAR_WINDOW) {
         var3.add(new ItemUiSet("p_key_radar_switch", 4, (Function1)(new Function1(this) {
            final FactoryActivity this$0;

            {
               this.this$0 = var1;
            }

            public final void invoke(int var1) {
               CanSettingProxy var3 = this.this$0.mProxy;
               boolean var2 = true;
               if (var1 != 1) {
                  var2 = false;
               }

               var3.setPKeyRadarDispCheck(var2);
            }
         }), (Function0)(new Function0(this) {
            final FactoryActivity this$0;

            {
               this.this$0 = var1;
            }

            public final Integer invoke() {
               FactoryActivity var1 = this.this$0;
               return var1.toInt(var1.mProxy.getPKeyRadarDispCheck());
            }
         }), (List)null, (Function0)null, 48, (DefaultConstructorMarker)null));
      }

      if (GeneralSettingsConfig.getInstance(CanbusConfig.INSTANCE.getCanType()).WIKA_CAR_SELECT) {
         var3.add(new ItemUiSet("_439_car_select", 0, (Function1)null, (Function0)null, (List)null, (Function0)null.INSTANCE, 28, (DefaultConstructorMarker)null));
      }

      if (GeneralSettingsConfig.getInstance(CanbusConfig.INSTANCE.getCanType()).OPEN_CAN_DATA_LOG) {
         var3.add(new ItemUiSet("open_can_data_log_switch", 4, (Function1)(new Function1(this) {
            final FactoryActivity this$0;

            {
               this.this$0 = var1;
            }

            public final void invoke(int var1) {
               CanSettingProxy var3 = this.this$0.mProxy;
               boolean var2 = true;
               if (var1 != 1) {
                  var2 = false;
               }

               var3.setCanDataLogSwith(var2);
            }
         }), (Function0)(new Function0(this) {
            final FactoryActivity this$0;

            {
               this.this$0 = var1;
            }

            public final Integer invoke() {
               FactoryActivity var1 = this.this$0;
               return var1.toInt(var1.mProxy.getCanDataLogSwith());
            }
         }), (List)null, (Function0)null, 48, (DefaultConstructorMarker)null));
      }

      Unit var5 = Unit.INSTANCE;
      var4.setAdapter((RecyclerView.Adapter)(new FactoryItemAdapter(var1, (List)var3)));
   }

   private final int toInt(boolean var1) {
      return var1;
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

   public final FactoryActivity getInstance() {
      return (FactoryActivity)this.instance$delegate.getValue();
   }

   protected void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.setContentView(2131558660);
      this.initView();
   }

   @Metadata(
      d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0007"},
      d2 = {"Lcom/hzbhd/canbus/activity/FactoryActivity$Companion;", "", "()V", "STYLE_0", "", "STYLE_1", "STYLE_4", "CanBusInfo_release"},
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
      d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B]\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0014\b\u0002\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\b0\u0007\u0012\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00050\n\u0012\u0010\b\u0002\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\f\u0012\u000e\b\u0002\u0010\r\u001a\b\u0012\u0004\u0012\u00020\b0\n¢\u0006\u0002\u0010\u000eJ\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0005HÆ\u0003J\u0015\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\b0\u0007HÆ\u0003J\u000f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00050\nHÆ\u0003J\u0011\u0010\u001e\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\fHÆ\u0003J\u000f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\b0\nHÆ\u0003Je\u0010 \u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u0014\b\u0002\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\b0\u00072\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00050\n2\u0010\b\u0002\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\f2\u000e\b\u0002\u0010\r\u001a\b\u0012\u0004\u0012\u00020\b0\nHÆ\u0001J\u0013\u0010!\u001a\u00020\"2\b\u0010#\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010$\u001a\u00020\u0005HÖ\u0001J\t\u0010%\u001a\u00020\u0003HÖ\u0001R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00050\n¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0019\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\f¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\b0\n¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0010R\u001d\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019¨\u0006&"},
      d2 = {"Lcom/hzbhd/canbus/activity/FactoryActivity$ItemUiSet;", "", "titleResName", "", "style", "", "setValue", "Lkotlin/Function1;", "", "getValue", "Lkotlin/Function0;", "listValues", "", "onClick", "(Ljava/lang/String;ILkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;Ljava/util/List;Lkotlin/jvm/functions/Function0;)V", "getGetValue", "()Lkotlin/jvm/functions/Function0;", "getListValues", "()Ljava/util/List;", "getOnClick", "getSetValue", "()Lkotlin/jvm/functions/Function1;", "getStyle", "()I", "getTitleResName", "()Ljava/lang/String;", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "toString", "CanBusInfo_release"},
      k = 1,
      mv = {1, 7, 1},
      xi = 48
   )
   public static final class ItemUiSet {
      private final Function0 getValue;
      private final List listValues;
      private final Function0 onClick;
      private final Function1 setValue;
      private final int style;
      private final String titleResName;

      public ItemUiSet(String var1, int var2, Function1 var3, Function0 var4, List var5, Function0 var6) {
         Intrinsics.checkNotNullParameter(var1, "titleResName");
         Intrinsics.checkNotNullParameter(var3, "setValue");
         Intrinsics.checkNotNullParameter(var4, "getValue");
         Intrinsics.checkNotNullParameter(var6, "onClick");
         super();
         this.titleResName = var1;
         this.style = var2;
         this.setValue = var3;
         this.getValue = var4;
         this.listValues = var5;
         this.onClick = var6;
      }

      // $FF: synthetic method
      public ItemUiSet(String var1, int var2, Function1 var3, Function0 var4, List var5, Function0 var6, int var7, DefaultConstructorMarker var8) {
         if ((var7 & 4) != 0) {
            var3 = (Function1)null.INSTANCE;
         }

         if ((var7 & 8) != 0) {
            var4 = (Function0)null.INSTANCE;
         }

         if ((var7 & 16) != 0) {
            var5 = null;
         }

         if ((var7 & 32) != 0) {
            var6 = (Function0)null.INSTANCE;
         }

         this(var1, var2, var3, var4, var5, var6);
      }

      // $FF: synthetic method
      public static ItemUiSet copy$default(ItemUiSet var0, String var1, int var2, Function1 var3, Function0 var4, List var5, Function0 var6, int var7, Object var8) {
         if ((var7 & 1) != 0) {
            var1 = var0.titleResName;
         }

         if ((var7 & 2) != 0) {
            var2 = var0.style;
         }

         if ((var7 & 4) != 0) {
            var3 = var0.setValue;
         }

         if ((var7 & 8) != 0) {
            var4 = var0.getValue;
         }

         if ((var7 & 16) != 0) {
            var5 = var0.listValues;
         }

         if ((var7 & 32) != 0) {
            var6 = var0.onClick;
         }

         return var0.copy(var1, var2, var3, var4, var5, var6);
      }

      public final String component1() {
         return this.titleResName;
      }

      public final int component2() {
         return this.style;
      }

      public final Function1 component3() {
         return this.setValue;
      }

      public final Function0 component4() {
         return this.getValue;
      }

      public final List component5() {
         return this.listValues;
      }

      public final Function0 component6() {
         return this.onClick;
      }

      public final ItemUiSet copy(String var1, int var2, Function1 var3, Function0 var4, List var5, Function0 var6) {
         Intrinsics.checkNotNullParameter(var1, "titleResName");
         Intrinsics.checkNotNullParameter(var3, "setValue");
         Intrinsics.checkNotNullParameter(var4, "getValue");
         Intrinsics.checkNotNullParameter(var6, "onClick");
         return new ItemUiSet(var1, var2, var3, var4, var5, var6);
      }

      public boolean equals(Object var1) {
         if (this == var1) {
            return true;
         } else if (!(var1 instanceof ItemUiSet)) {
            return false;
         } else {
            ItemUiSet var2 = (ItemUiSet)var1;
            if (!Intrinsics.areEqual((Object)this.titleResName, (Object)var2.titleResName)) {
               return false;
            } else if (this.style != var2.style) {
               return false;
            } else if (!Intrinsics.areEqual((Object)this.setValue, (Object)var2.setValue)) {
               return false;
            } else if (!Intrinsics.areEqual((Object)this.getValue, (Object)var2.getValue)) {
               return false;
            } else if (!Intrinsics.areEqual((Object)this.listValues, (Object)var2.listValues)) {
               return false;
            } else {
               return Intrinsics.areEqual((Object)this.onClick, (Object)var2.onClick);
            }
         }
      }

      public final Function0 getGetValue() {
         return this.getValue;
      }

      public final List getListValues() {
         return this.listValues;
      }

      public final Function0 getOnClick() {
         return this.onClick;
      }

      public final Function1 getSetValue() {
         return this.setValue;
      }

      public final int getStyle() {
         return this.style;
      }

      public final String getTitleResName() {
         return this.titleResName;
      }

      public int hashCode() {
         int var4 = this.titleResName.hashCode();
         int var5 = Integer.hashCode(this.style);
         int var2 = this.setValue.hashCode();
         int var3 = this.getValue.hashCode();
         List var6 = this.listValues;
         int var1;
         if (var6 == null) {
            var1 = 0;
         } else {
            var1 = var6.hashCode();
         }

         return ((((var4 * 31 + var5) * 31 + var2) * 31 + var3) * 31 + var1) * 31 + this.onClick.hashCode();
      }

      public String toString() {
         return "ItemUiSet(titleResName=" + this.titleResName + ", style=" + this.style + ", setValue=" + this.setValue + ", getValue=" + this.getValue + ", listValues=" + this.listValues + ", onClick=" + this.onClick + ')';
      }
   }
}
