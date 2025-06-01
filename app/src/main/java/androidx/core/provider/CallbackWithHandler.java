package androidx.core.provider;

import android.graphics.Typeface;
import android.os.Handler;

class CallbackWithHandler {
   private final FontsContractCompat.FontRequestCallback mCallback;
   private final Handler mCallbackHandler;

   CallbackWithHandler(FontsContractCompat.FontRequestCallback var1) {
      this.mCallback = var1;
      this.mCallbackHandler = CalleeHandler.create();
   }

   CallbackWithHandler(FontsContractCompat.FontRequestCallback var1, Handler var2) {
      this.mCallback = var1;
      this.mCallbackHandler = var2;
   }

   private void onTypefaceRequestFailed(int var1) {
      FontsContractCompat.FontRequestCallback var2 = this.mCallback;
      this.mCallbackHandler.post(new Runnable(this, var2, var1) {
         final CallbackWithHandler this$0;
         final FontsContractCompat.FontRequestCallback val$callback;
         final int val$reason;

         {
            this.this$0 = var1;
            this.val$callback = var2;
            this.val$reason = var3;
         }

         public void run() {
            this.val$callback.onTypefaceRequestFailed(this.val$reason);
         }
      });
   }

   private void onTypefaceRetrieved(Typeface var1) {
      FontsContractCompat.FontRequestCallback var2 = this.mCallback;
      this.mCallbackHandler.post(new Runnable(this, var2, var1) {
         final CallbackWithHandler this$0;
         final FontsContractCompat.FontRequestCallback val$callback;
         final Typeface val$typeface;

         {
            this.this$0 = var1;
            this.val$callback = var2;
            this.val$typeface = var3;
         }

         public void run() {
            this.val$callback.onTypefaceRetrieved(this.val$typeface);
         }
      });
   }

   void onTypefaceResult(FontRequestWorker.TypefaceResult var1) {
      if (var1.isSuccess()) {
         this.onTypefaceRetrieved(var1.mTypeface);
      } else {
         this.onTypefaceRequestFailed(var1.mResult);
      }

   }
}
