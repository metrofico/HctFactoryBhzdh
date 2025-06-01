package androidx.emoji2.text;

import android.os.Build.VERSION;
import android.text.Editable;
import android.text.Selection;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.MetaKeyKeyListener;
import android.view.KeyEvent;
import android.view.inputmethod.InputConnection;
import androidx.core.graphics.PaintCompat;
import java.util.Arrays;

final class EmojiProcessor {
   private static final int ACTION_ADVANCE_BOTH = 1;
   private static final int ACTION_ADVANCE_END = 2;
   private static final int ACTION_FLUSH = 3;
   private final int[] mEmojiAsDefaultStyleExceptions;
   private EmojiCompat.GlyphChecker mGlyphChecker;
   private final MetadataRepo mMetadataRepo;
   private final EmojiCompat.SpanFactory mSpanFactory;
   private final boolean mUseEmojiAsDefaultStyle;

   EmojiProcessor(MetadataRepo var1, EmojiCompat.SpanFactory var2, EmojiCompat.GlyphChecker var3, boolean var4, int[] var5) {
      this.mSpanFactory = var2;
      this.mMetadataRepo = var1;
      this.mGlyphChecker = var3;
      this.mUseEmojiAsDefaultStyle = var4;
      this.mEmojiAsDefaultStyleExceptions = var5;
   }

   private void addEmoji(Spannable var1, EmojiMetadata var2, int var3, int var4) {
      var1.setSpan(this.mSpanFactory.createSpan(var2), var3, var4, 33);
   }

   private static boolean delete(Editable var0, KeyEvent var1, boolean var2) {
      if (hasModifiers(var1)) {
         return false;
      } else {
         int var4 = Selection.getSelectionStart(var0);
         int var3 = Selection.getSelectionEnd(var0);
         if (hasInvalidSelection(var4, var3)) {
            return false;
         } else {
            EmojiSpan[] var8 = (EmojiSpan[])var0.getSpans(var4, var3, EmojiSpan.class);
            if (var8 != null && var8.length > 0) {
               int var5 = var8.length;

               for(var3 = 0; var3 < var5; ++var3) {
                  EmojiSpan var9 = var8[var3];
                  int var7 = var0.getSpanStart(var9);
                  int var6 = var0.getSpanEnd(var9);
                  if (var2 && var7 == var4 || !var2 && var6 == var4 || var4 > var7 && var4 < var6) {
                     var0.delete(var7, var6);
                     return true;
                  }
               }
            }

            return false;
         }
      }
   }

   static boolean handleDeleteSurroundingText(InputConnection var0, Editable var1, int var2, int var3, boolean var4) {
      if (var1 != null && var0 != null && var2 >= 0 && var3 >= 0) {
         int var5 = Selection.getSelectionStart(var1);
         int var6 = Selection.getSelectionEnd(var1);
         if (hasInvalidSelection(var5, var6)) {
            return false;
         }

         if (var4) {
            label46: {
               var2 = EmojiProcessor.CodepointIndexFinder.findIndexBackward(var1, var5, Math.max(var2, 0));
               var5 = EmojiProcessor.CodepointIndexFinder.findIndexForward(var1, var6, Math.max(var3, 0));
               if (var2 != -1) {
                  var3 = var2;
                  var2 = var5;
                  if (var5 != -1) {
                     break label46;
                  }
               }

               return false;
            }
         } else {
            var5 = Math.max(var5 - var2, 0);
            var2 = Math.min(var6 + var3, var1.length());
            var3 = var5;
         }

         EmojiSpan[] var9 = (EmojiSpan[])var1.getSpans(var3, var2, EmojiSpan.class);
         if (var9 != null && var9.length > 0) {
            int var7 = var9.length;
            byte var11 = 0;
            var5 = var3;

            for(var3 = var11; var3 < var7; ++var3) {
               EmojiSpan var10 = var9[var3];
               int var8 = var1.getSpanStart(var10);
               var6 = var1.getSpanEnd(var10);
               var5 = Math.min(var8, var5);
               var2 = Math.max(var6, var2);
            }

            var3 = Math.max(var5, 0);
            var2 = Math.min(var2, var1.length());
            var0.beginBatchEdit();
            var1.delete(var3, var2);
            var0.endBatchEdit();
            return true;
         }
      }

      return false;
   }

   static boolean handleOnKeyDown(Editable var0, int var1, KeyEvent var2) {
      boolean var3;
      if (var1 != 67) {
         if (var1 != 112) {
            var3 = false;
         } else {
            var3 = delete(var0, var2, true);
         }
      } else {
         var3 = delete(var0, var2, false);
      }

      if (var3) {
         MetaKeyKeyListener.adjustMetaAfterKeypress(var0);
         return true;
      } else {
         return false;
      }
   }

   private boolean hasGlyph(CharSequence var1, int var2, int var3, EmojiMetadata var4) {
      if (var4.getHasGlyph() == 0) {
         var4.setHasGlyph(this.mGlyphChecker.hasGlyph(var1, var2, var3, var4.getSdkAdded()));
      }

      boolean var5;
      if (var4.getHasGlyph() == 2) {
         var5 = true;
      } else {
         var5 = false;
      }

      return var5;
   }

   private static boolean hasInvalidSelection(int var0, int var1) {
      boolean var2;
      if (var0 != -1 && var1 != -1 && var0 == var1) {
         var2 = false;
      } else {
         var2 = true;
      }

      return var2;
   }

   private static boolean hasModifiers(KeyEvent var0) {
      return KeyEvent.metaStateHasNoModifiers(var0.getMetaState()) ^ true;
   }

   EmojiMetadata getEmojiMetadata(CharSequence var1) {
      ProcessorSm var5 = new ProcessorSm(this.mMetadataRepo.getRootNode(), this.mUseEmojiAsDefaultStyle, this.mEmojiAsDefaultStyleExceptions);
      int var3 = var1.length();

      int var4;
      for(int var2 = 0; var2 < var3; var2 += Character.charCount(var4)) {
         var4 = Character.codePointAt(var1, var2);
         if (var5.check(var4) != 2) {
            return null;
         }
      }

      if (var5.isInFlushableState()) {
         return var5.getCurrentMetadata();
      } else {
         return null;
      }
   }

   CharSequence process(CharSequence var1, int var2, int var3, int var4, boolean var5) {
      boolean var11 = var1 instanceof SpannableBuilder;
      if (var11) {
         ((SpannableBuilder)var1).beginBatchEdit();
      }

      label6015: {
         Throwable var10000;
         label6016: {
            Object var12;
            EmojiSpan[] var13;
            boolean var10001;
            label6027: {
               var13 = null;
               if (!var11) {
                  label6026: {
                     try {
                        if (var1 instanceof Spannable) {
                           break label6026;
                        }
                     } catch (Throwable var614) {
                        var10000 = var614;
                        var10001 = false;
                        break label6016;
                     }

                     var12 = var13;

                     try {
                        if (!(var1 instanceof Spanned)) {
                           break label6027;
                        }
                     } catch (Throwable var613) {
                        var10000 = var613;
                        var10001 = false;
                        break label6016;
                     }

                     var12 = var13;

                     try {
                        if (((Spanned)var1).nextSpanTransition(var2 - 1, var3 + 1, EmojiSpan.class) <= var3) {
                           var12 = new SpannableString(var1);
                        }
                        break label6027;
                     } catch (Throwable var611) {
                        var10000 = var611;
                        var10001 = false;
                        break label6016;
                     }
                  }
               }

               try {
                  var12 = (Spannable)var1;
               } catch (Throwable var612) {
                  var10000 = var612;
                  var10001 = false;
                  break label6016;
               }
            }

            int var6 = var2;
            int var7 = var3;
            int var8;
            int var9;
            if (var12 != null) {
               try {
                  var13 = (EmojiSpan[])((Spannable)var12).getSpans(var2, var3, EmojiSpan.class);
               } catch (Throwable var609) {
                  var10000 = var609;
                  var10001 = false;
                  break label6016;
               }

               var6 = var2;
               var7 = var3;
               if (var13 != null) {
                  label6019: {
                     var6 = var2;
                     var7 = var3;

                     try {
                        if (var13.length <= 0) {
                           break label6019;
                        }

                        var9 = var13.length;
                     } catch (Throwable var610) {
                        var10000 = var610;
                        var10001 = false;
                        break label6016;
                     }

                     var8 = 0;

                     while(true) {
                        var6 = var2;
                        var7 = var3;
                        if (var8 >= var9) {
                           break;
                        }

                        EmojiSpan var14 = var13[var8];

                        try {
                           var7 = ((Spannable)var12).getSpanStart(var14);
                           var6 = ((Spannable)var12).getSpanEnd(var14);
                        } catch (Throwable var608) {
                           var10000 = var608;
                           var10001 = false;
                           break label6016;
                        }

                        if (var7 != var3) {
                           try {
                              ((Spannable)var12).removeSpan(var14);
                           } catch (Throwable var607) {
                              var10000 = var607;
                              var10001 = false;
                              break label6016;
                           }
                        }

                        try {
                           var2 = Math.min(var7, var2);
                           var3 = Math.max(var6, var3);
                        } catch (Throwable var606) {
                           var10000 = var606;
                           var10001 = false;
                           break label6016;
                        }

                        ++var8;
                     }
                  }
               }
            }

            if (var6 == var7) {
               break label6015;
            }

            try {
               if (var6 >= var1.length()) {
                  break label6015;
               }
            } catch (Throwable var605) {
               var10000 = var605;
               var10001 = false;
               break label6016;
            }

            var8 = var4;
            if (var4 != Integer.MAX_VALUE) {
               var8 = var4;
               if (var12 != null) {
                  try {
                     var8 = var4 - ((EmojiSpan[])((Spannable)var12).getSpans(0, ((Spannable)var12).length(), EmojiSpan.class)).length;
                  } catch (Throwable var604) {
                     var10000 = var604;
                     var10001 = false;
                     break label6016;
                  }
               }
            }

            int var10;
            ProcessorSm var617;
            try {
               var617 = new ProcessorSm(this.mMetadataRepo.getRootNode(), this.mUseEmojiAsDefaultStyle, this.mEmojiAsDefaultStyleExceptions);
               var10 = Character.codePointAt(var1, var6);
            } catch (Throwable var603) {
               var10000 = var603;
               var10001 = false;
               break label6016;
            }

            var4 = 0;

            label5948:
            while(true) {
               var9 = var6;
               var3 = var6;
               var2 = var10;

               Object var615;
               while(var3 < var7 && var4 < var8) {
                  try {
                     var6 = var617.check(var2);
                  } catch (Throwable var601) {
                     var10000 = var601;
                     var10001 = false;
                     break label5948;
                  }

                  if (var6 != 1) {
                     if (var6 != 2) {
                        if (var6 == 3) {
                           if (!var5) {
                              var10 = var2;
                              var6 = var3;

                              try {
                                 if (this.hasGlyph(var1, var9, var3, var617.getFlushMetadata())) {
                                    continue label5948;
                                 }
                              } catch (Throwable var602) {
                                 var10000 = var602;
                                 var10001 = false;
                                 break label5948;
                              }
                           }

                           var615 = var12;
                           if (var12 == null) {
                              try {
                                 var615 = new SpannableString(var1);
                              } catch (Throwable var600) {
                                 var10000 = var600;
                                 var10001 = false;
                                 break label5948;
                              }
                           }

                           try {
                              this.addEmoji((Spannable)var615, var617.getFlushMetadata(), var9, var3);
                           } catch (Throwable var599) {
                              var10000 = var599;
                              var10001 = false;
                              break label5948;
                           }

                           ++var4;
                           var12 = var615;
                           var10 = var2;
                           var6 = var3;
                           continue label5948;
                        }
                     } else {
                        try {
                           var6 = var3 + Character.charCount(var2);
                        } catch (Throwable var598) {
                           var10000 = var598;
                           var10001 = false;
                           break label5948;
                        }

                        var3 = var6;
                        if (var6 < var7) {
                           try {
                              var2 = Character.codePointAt(var1, var6);
                           } catch (Throwable var597) {
                              var10000 = var597;
                              var10001 = false;
                              break label5948;
                           }

                           var3 = var6;
                        }
                     }
                  } else {
                     try {
                        var9 += Character.charCount(Character.codePointAt(var1, var9));
                     } catch (Throwable var596) {
                        var10000 = var596;
                        var10001 = false;
                        break label5948;
                     }

                     if (var9 < var7) {
                        try {
                           var2 = Character.codePointAt(var1, var9);
                        } catch (Throwable var595) {
                           var10000 = var595;
                           var10001 = false;
                           break label5948;
                        }
                     }

                     var3 = var9;
                  }
               }

               var615 = var12;

               label5912: {
                  try {
                     if (!var617.isInFlushableState()) {
                        break label5912;
                     }
                  } catch (Throwable var594) {
                     var10000 = var594;
                     var10001 = false;
                     break;
                  }

                  var615 = var12;
                  if (var4 < var8) {
                     label6024: {
                        if (!var5) {
                           var615 = var12;

                           try {
                              if (this.hasGlyph(var1, var9, var3, var617.getCurrentMetadata())) {
                                 break label6024;
                              }
                           } catch (Throwable var593) {
                              var10000 = var593;
                              var10001 = false;
                              break;
                           }
                        }

                        var615 = var12;
                        if (var12 == null) {
                           try {
                              var615 = new SpannableString(var1);
                           } catch (Throwable var592) {
                              var10000 = var592;
                              var10001 = false;
                              break;
                           }
                        }

                        try {
                           this.addEmoji((Spannable)var615, var617.getCurrentMetadata(), var9, var3);
                        } catch (Throwable var591) {
                           var10000 = var591;
                           var10001 = false;
                           break;
                        }
                     }
                  }
               }

               var12 = var615;
               if (var615 == null) {
                  var12 = var1;
               }

               if (var11) {
                  ((SpannableBuilder)var1).endBatchEdit();
               }

               return (CharSequence)var12;
            }
         }

         Throwable var616 = var10000;
         if (var11) {
            ((SpannableBuilder)var1).endBatchEdit();
         }

         throw var616;
      }

      if (var11) {
         ((SpannableBuilder)var1).endBatchEdit();
      }

      return var1;
   }

   private static final class CodepointIndexFinder {
      private static final int INVALID_INDEX = -1;

      static int findIndexBackward(CharSequence var0, int var1, int var2) {
         int var4 = var0.length();
         if (var1 >= 0 && var4 >= var1) {
            if (var2 < 0) {
               return -1;
            } else {
               label43:
               while(true) {
                  boolean var5 = false;

                  while(var2 != 0) {
                     --var1;
                     if (var1 < 0) {
                        if (var5) {
                           return -1;
                        }

                        return 0;
                     }

                     char var3 = var0.charAt(var1);
                     if (var5) {
                        if (!Character.isHighSurrogate(var3)) {
                           return -1;
                        }

                        --var2;
                        continue label43;
                     }

                     if (!Character.isSurrogate(var3)) {
                        --var2;
                     } else {
                        if (Character.isHighSurrogate(var3)) {
                           return -1;
                        }

                        var5 = true;
                     }
                  }

                  return var1;
               }
            }
         } else {
            return -1;
         }
      }

      static int findIndexForward(CharSequence var0, int var1, int var2) {
         int var5 = var0.length();
         if (var1 >= 0 && var5 >= var1) {
            if (var2 < 0) {
               return -1;
            } else {
               label43:
               while(true) {
                  boolean var4 = false;

                  while(var2 != 0) {
                     if (var1 >= var5) {
                        if (var4) {
                           return -1;
                        }

                        return var5;
                     }

                     char var3 = var0.charAt(var1);
                     if (var4) {
                        if (!Character.isLowSurrogate(var3)) {
                           return -1;
                        }

                        --var2;
                        ++var1;
                        continue label43;
                     }

                     if (!Character.isSurrogate(var3)) {
                        --var2;
                        ++var1;
                     } else {
                        if (Character.isLowSurrogate(var3)) {
                           return -1;
                        }

                        ++var1;
                        var4 = true;
                     }
                  }

                  return var1;
               }
            }
         } else {
            return -1;
         }
      }
   }

   public static class DefaultGlyphChecker implements EmojiCompat.GlyphChecker {
      private static final int PAINT_TEXT_SIZE = 10;
      private static final ThreadLocal sStringBuilder = new ThreadLocal();
      private final TextPaint mTextPaint;

      DefaultGlyphChecker() {
         TextPaint var1 = new TextPaint();
         this.mTextPaint = var1;
         var1.setTextSize(10.0F);
      }

      private static StringBuilder getStringBuilder() {
         ThreadLocal var0 = sStringBuilder;
         if (var0.get() == null) {
            var0.set(new StringBuilder());
         }

         return (StringBuilder)var0.get();
      }

      public boolean hasGlyph(CharSequence var1, int var2, int var3, int var4) {
         if (VERSION.SDK_INT < 23 && var4 > VERSION.SDK_INT) {
            return false;
         } else {
            StringBuilder var5 = getStringBuilder();
            var5.setLength(0);

            while(var2 < var3) {
               var5.append(var1.charAt(var2));
               ++var2;
            }

            return PaintCompat.hasGlyph(this.mTextPaint, var5.toString());
         }
      }
   }

   static final class ProcessorSm {
      private static final int STATE_DEFAULT = 1;
      private static final int STATE_WALKING = 2;
      private int mCurrentDepth;
      private MetadataRepo.Node mCurrentNode;
      private final int[] mEmojiAsDefaultStyleExceptions;
      private MetadataRepo.Node mFlushNode;
      private int mLastCodepoint;
      private final MetadataRepo.Node mRootNode;
      private int mState = 1;
      private final boolean mUseEmojiAsDefaultStyle;

      ProcessorSm(MetadataRepo.Node var1, boolean var2, int[] var3) {
         this.mRootNode = var1;
         this.mCurrentNode = var1;
         this.mUseEmojiAsDefaultStyle = var2;
         this.mEmojiAsDefaultStyleExceptions = var3;
      }

      private static boolean isEmojiStyle(int var0) {
         boolean var1;
         if (var0 == 65039) {
            var1 = true;
         } else {
            var1 = false;
         }

         return var1;
      }

      private static boolean isTextStyle(int var0) {
         boolean var1;
         if (var0 == 65038) {
            var1 = true;
         } else {
            var1 = false;
         }

         return var1;
      }

      private int reset() {
         this.mState = 1;
         this.mCurrentNode = this.mRootNode;
         this.mCurrentDepth = 0;
         return 1;
      }

      private boolean shouldUseEmojiPresentationStyleForSingleCodepoint() {
         if (this.mCurrentNode.getData().isDefaultEmoji()) {
            return true;
         } else if (isEmojiStyle(this.mLastCodepoint)) {
            return true;
         } else {
            if (this.mUseEmojiAsDefaultStyle) {
               if (this.mEmojiAsDefaultStyleExceptions == null) {
                  return true;
               }

               int var1 = this.mCurrentNode.getData().getCodepointAt(0);
               if (Arrays.binarySearch(this.mEmojiAsDefaultStyleExceptions, var1) < 0) {
                  return true;
               }
            }

            return false;
         }
      }

      int check(int var1) {
         int var2;
         label34: {
            MetadataRepo.Node var4 = this.mCurrentNode.get(var1);
            int var3 = this.mState;
            var2 = 3;
            if (var3 != 2) {
               if (var4 == null) {
                  var2 = this.reset();
                  break label34;
               }

               this.mState = 2;
               this.mCurrentNode = var4;
               this.mCurrentDepth = 1;
            } else if (var4 != null) {
               this.mCurrentNode = var4;
               ++this.mCurrentDepth;
            } else {
               if (isTextStyle(var1)) {
                  var2 = this.reset();
                  break label34;
               }

               if (!isEmojiStyle(var1)) {
                  if (this.mCurrentNode.getData() != null) {
                     if (this.mCurrentDepth == 1) {
                        if (this.shouldUseEmojiPresentationStyleForSingleCodepoint()) {
                           this.mFlushNode = this.mCurrentNode;
                           this.reset();
                        } else {
                           var2 = this.reset();
                        }
                     } else {
                        this.mFlushNode = this.mCurrentNode;
                        this.reset();
                     }
                  } else {
                     var2 = this.reset();
                  }
                  break label34;
               }
            }

            var2 = 2;
         }

         this.mLastCodepoint = var1;
         return var2;
      }

      EmojiMetadata getCurrentMetadata() {
         return this.mCurrentNode.getData();
      }

      EmojiMetadata getFlushMetadata() {
         return this.mFlushNode.getData();
      }

      boolean isInFlushableState() {
         int var1 = this.mState;
         boolean var3 = true;
         boolean var2;
         if (var1 == 2 && this.mCurrentNode.getData() != null) {
            var2 = var3;
            if (this.mCurrentDepth > 1) {
               return var2;
            }

            if (this.shouldUseEmojiPresentationStyleForSingleCodepoint()) {
               var2 = var3;
               return var2;
            }
         }

         var2 = false;
         return var2;
      }
   }
}
