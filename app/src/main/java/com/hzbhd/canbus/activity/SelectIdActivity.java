package com.hzbhd.canbus.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.hzbhd.canbus.adapter.SelectCanTypeIdAdapter;
import com.hzbhd.canbus.adapter.bean.CanTypeAllEntity;
import com.hzbhd.canbus.comm.MyApplication;
import com.hzbhd.canbus.config.CanbusConfig;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.cantype.AllCanType;
import com.hzbhd.cantype.CanTypeUtil;
import com.hzbhd.config.use.CanBus;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class SelectIdActivity extends Activity implements View.OnClickListener {
   private final int COUNT = 6;
   private SelectCanTypeIdAdapter mAdapter;
   private SelectCanTypeIdAdapter.ItemCallBackInterface mCarModelClick = new SelectCanTypeIdAdapter.ItemCallBackInterface(this) {
      final SelectIdActivity this$0;

      {
         this.this$0 = var1;
      }

      public void select(int var1) {
         CanTypeAllEntity var2 = (CanTypeAllEntity)CanTypeUtil.INSTANCE.getCanType(var1).getList().get(0);
         SelectCanTypeUtil.showDialogToUpdate(this.this$0, var2, (String)null);
      }
   };
   private List mCarTypeEntityList;
   private RecyclerView mListRv;
   private TextView mSelectedTv;

   private void findViews() {
      this.mListRv = (RecyclerView)this.findViewById(2131363220);
      this.mSelectedTv = (TextView)this.findViewById(2131363700);
   }

   private CanTypeAllEntity getCurCanTypeEntity() {
      ArrayList var2 = CanTypeUtil.INSTANCE.getCanType(this.getFactoryCanType()).getList();
      int var1 = CanBus.INSTANCE.getDifferentId();
      if (var2.size() == 0) {
         CommUtil.showToast(this, "数据未导入，请重启后尝试");
         return null;
      } else {
         Iterator var3 = var2.iterator();

         CanTypeAllEntity var4;
         do {
            if (!var3.hasNext()) {
               return (CanTypeAllEntity)var2.get(0);
            }

            var4 = (CanTypeAllEntity)var3.next();
         } while(var4.getCan_different_id() != var1);

         return var4;
      }
   }

   private int getFactoryCanType() {
      return CanbusConfig.INSTANCE.getCanType();
   }

   private void initViews() {
      ArrayList var1 = new ArrayList();
      this.mCarTypeEntityList = var1;
      var1.addAll((new AllCanType()).getCanTypeList());
      Collections.sort(this.mCarTypeEntityList);
      this.mAdapter = new SelectCanTypeIdAdapter(this, this.mCarTypeEntityList, this.mCarModelClick, this.getFactoryCanType());
      this.mListRv.setLayoutManager(new GridLayoutManager(this, 6, 1, false));
      this.mListRv.setAdapter(this.mAdapter);
      CanTypeAllEntity var2 = this.getCurCanTypeEntity();
      if (var2 != null) {
         this.mSelectedTv.setText(var2.getProtocol_company() + " " + var2.getCar_category() + " " + var2.getCan_type_id());
      }

   }

   public void onClick(View var1) {
   }

   protected void onCreate(Bundle var1) {
      super.onCreate(var1);
      ((MyApplication)this.getApplication()).addActivity(this);
      this.setContentView(2131558691);
      this.findViews();
      this.initViews();
   }
}
