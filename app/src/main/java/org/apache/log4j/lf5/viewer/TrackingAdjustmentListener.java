package org.apache.log4j.lf5.viewer;

import java.awt.Adjustable;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

public class TrackingAdjustmentListener implements AdjustmentListener {
   protected int _lastMaximum = -1;

   public void adjustmentValueChanged(AdjustmentEvent var1) {
      Adjustable var3 = var1.getAdjustable();
      int var2 = var3.getMaximum();
      if (var3.getMaximum() != this._lastMaximum) {
         if (var3.getValue() + var3.getVisibleAmount() + var3.getUnitIncrement() >= this._lastMaximum) {
            var3.setValue(var3.getMaximum());
         }

         this._lastMaximum = var2;
      }
   }
}
