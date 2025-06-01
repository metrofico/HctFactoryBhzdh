package android.support.v4.media.session;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.AudioManager;
import android.media.Rating;
import android.media.RemoteControlClient;
import android.media.session.MediaSession;
import android.net.Uri;
import android.os.BadParcelableException;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.os.ResultReceiver;
import android.os.SystemClock;
import android.os.Build.VERSION;
import android.support.v4.media.MediaDescriptionCompat;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.RatingCompat;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.ViewConfiguration;
import androidx.core.app.BundleCompat;
import androidx.media.MediaSessionManager;
import androidx.media.VolumeProviderCompat;
import androidx.media.session.MediaButtonReceiver;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MediaSessionCompat {
   public static final String ACTION_ARGUMENT_CAPTIONING_ENABLED = "android.support.v4.media.session.action.ARGUMENT_CAPTIONING_ENABLED";
   public static final String ACTION_ARGUMENT_EXTRAS = "android.support.v4.media.session.action.ARGUMENT_EXTRAS";
   public static final String ACTION_ARGUMENT_MEDIA_ID = "android.support.v4.media.session.action.ARGUMENT_MEDIA_ID";
   public static final String ACTION_ARGUMENT_QUERY = "android.support.v4.media.session.action.ARGUMENT_QUERY";
   public static final String ACTION_ARGUMENT_RATING = "android.support.v4.media.session.action.ARGUMENT_RATING";
   public static final String ACTION_ARGUMENT_REPEAT_MODE = "android.support.v4.media.session.action.ARGUMENT_REPEAT_MODE";
   public static final String ACTION_ARGUMENT_SHUFFLE_MODE = "android.support.v4.media.session.action.ARGUMENT_SHUFFLE_MODE";
   public static final String ACTION_ARGUMENT_URI = "android.support.v4.media.session.action.ARGUMENT_URI";
   public static final String ACTION_FLAG_AS_INAPPROPRIATE = "android.support.v4.media.session.action.FLAG_AS_INAPPROPRIATE";
   public static final String ACTION_FOLLOW = "android.support.v4.media.session.action.FOLLOW";
   public static final String ACTION_PLAY_FROM_URI = "android.support.v4.media.session.action.PLAY_FROM_URI";
   public static final String ACTION_PREPARE = "android.support.v4.media.session.action.PREPARE";
   public static final String ACTION_PREPARE_FROM_MEDIA_ID = "android.support.v4.media.session.action.PREPARE_FROM_MEDIA_ID";
   public static final String ACTION_PREPARE_FROM_SEARCH = "android.support.v4.media.session.action.PREPARE_FROM_SEARCH";
   public static final String ACTION_PREPARE_FROM_URI = "android.support.v4.media.session.action.PREPARE_FROM_URI";
   public static final String ACTION_SET_CAPTIONING_ENABLED = "android.support.v4.media.session.action.SET_CAPTIONING_ENABLED";
   public static final String ACTION_SET_RATING = "android.support.v4.media.session.action.SET_RATING";
   public static final String ACTION_SET_REPEAT_MODE = "android.support.v4.media.session.action.SET_REPEAT_MODE";
   public static final String ACTION_SET_SHUFFLE_MODE = "android.support.v4.media.session.action.SET_SHUFFLE_MODE";
   public static final String ACTION_SKIP_AD = "android.support.v4.media.session.action.SKIP_AD";
   public static final String ACTION_UNFOLLOW = "android.support.v4.media.session.action.UNFOLLOW";
   public static final String ARGUMENT_MEDIA_ATTRIBUTE = "android.support.v4.media.session.ARGUMENT_MEDIA_ATTRIBUTE";
   public static final String ARGUMENT_MEDIA_ATTRIBUTE_VALUE = "android.support.v4.media.session.ARGUMENT_MEDIA_ATTRIBUTE_VALUE";
   private static final String DATA_CALLING_PACKAGE = "data_calling_pkg";
   private static final String DATA_CALLING_PID = "data_calling_pid";
   private static final String DATA_CALLING_UID = "data_calling_uid";
   private static final String DATA_EXTRAS = "data_extras";
   public static final int FLAG_HANDLES_MEDIA_BUTTONS = 1;
   public static final int FLAG_HANDLES_QUEUE_COMMANDS = 4;
   public static final int FLAG_HANDLES_TRANSPORT_CONTROLS = 2;
   public static final String KEY_EXTRA_BINDER = "android.support.v4.media.session.EXTRA_BINDER";
   public static final String KEY_SESSION_TOKEN2_BUNDLE = "android.support.v4.media.session.SESSION_TOKEN2_BUNDLE";
   public static final String KEY_TOKEN = "android.support.v4.media.session.TOKEN";
   private static final int MAX_BITMAP_SIZE_IN_DP = 320;
   public static final int MEDIA_ATTRIBUTE_ALBUM = 1;
   public static final int MEDIA_ATTRIBUTE_ARTIST = 0;
   public static final int MEDIA_ATTRIBUTE_PLAYLIST = 2;
   static final String TAG = "MediaSessionCompat";
   static int sMaxBitmapSize;
   private final ArrayList mActiveListeners;
   private final MediaControllerCompat mController;
   private final MediaSessionImpl mImpl;

   private MediaSessionCompat(Context var1, MediaSessionImpl var2) {
      this.mActiveListeners = new ArrayList();
      this.mImpl = var2;
      if (VERSION.SDK_INT >= 21 && !MediaSessionCompatApi21.hasCallback(var2.getMediaSession())) {
         this.setCallback(new Callback(this) {
            final MediaSessionCompat this$0;

            {
               this.this$0 = var1;
            }
         });
      }

      this.mController = new MediaControllerCompat(var1, this);
   }

   public MediaSessionCompat(Context var1, String var2) {
      this(var1, var2, (ComponentName)null, (PendingIntent)null);
   }

   public MediaSessionCompat(Context var1, String var2, ComponentName var3, PendingIntent var4) {
      this(var1, var2, var3, var4, (Bundle)null);
   }

   private MediaSessionCompat(Context var1, String var2, ComponentName var3, PendingIntent var4, Bundle var5) {
      this.mActiveListeners = new ArrayList();
      if (var1 != null) {
         if (!TextUtils.isEmpty(var2)) {
            ComponentName var6 = var3;
            if (var3 == null) {
               var3 = MediaButtonReceiver.getMediaButtonReceiverComponent(var1);
               var6 = var3;
               if (var3 == null) {
                  Log.w("MediaSessionCompat", "Couldn't find a unique registered media button receiver in the given context.");
                  var6 = var3;
               }
            }

            PendingIntent var9 = var4;
            if (var6 != null) {
               var9 = var4;
               if (var4 == null) {
                  Intent var10 = new Intent("android.intent.action.MEDIA_BUTTON");
                  var10.setComponent(var6);
                  var9 = PendingIntent.getBroadcast(var1, 0, var10, 0);
               }
            }

            if (VERSION.SDK_INT >= 28) {
               MediaSessionImplApi28 var7 = new MediaSessionImplApi28(var1, var2, var5);
               this.mImpl = var7;
               this.setCallback(new Callback(this) {
                  final MediaSessionCompat this$0;

                  {
                     this.this$0 = var1;
                  }
               });
               var7.setMediaButtonReceiver(var9);
            } else if (VERSION.SDK_INT >= 21) {
               MediaSessionImplApi21 var8 = new MediaSessionImplApi21(var1, var2, var5);
               this.mImpl = var8;
               this.setCallback(new Callback(this) {
                  final MediaSessionCompat this$0;

                  {
                     this.this$0 = var1;
                  }
               });
               var8.setMediaButtonReceiver(var9);
            } else if (VERSION.SDK_INT >= 19) {
               this.mImpl = new MediaSessionImplApi19(var1, var2, var6, var9);
            } else if (VERSION.SDK_INT >= 18) {
               this.mImpl = new MediaSessionImplApi18(var1, var2, var6, var9);
            } else {
               this.mImpl = new MediaSessionImplBase(var1, var2, var6, var9);
            }

            this.mController = new MediaControllerCompat(var1, this);
            if (sMaxBitmapSize == 0) {
               sMaxBitmapSize = (int)(TypedValue.applyDimension(1, 320.0F, var1.getResources().getDisplayMetrics()) + 0.5F);
            }

         } else {
            throw new IllegalArgumentException("tag must not be null or empty");
         }
      } else {
         throw new IllegalArgumentException("context must not be null");
      }
   }

   public MediaSessionCompat(Context var1, String var2, Bundle var3) {
      this(var1, var2, (ComponentName)null, (PendingIntent)null, var3);
   }

   public static void ensureClassLoader(Bundle var0) {
      if (var0 != null) {
         var0.setClassLoader(MediaSessionCompat.class.getClassLoader());
      }

   }

   public static MediaSessionCompat fromMediaSession(Context var0, Object var1) {
      return var0 != null && var1 != null && VERSION.SDK_INT >= 21 ? new MediaSessionCompat(var0, new MediaSessionImplApi21(var1)) : null;
   }

   static PlaybackStateCompat getStateWithUpdatedPosition(PlaybackStateCompat var0, MediaMetadataCompat var1) {
      PlaybackStateCompat var10 = var0;
      if (var0 != null) {
         long var2 = var0.getPosition();
         long var4 = -1L;
         if (var2 == -1L) {
            var10 = var0;
         } else {
            if (var0.getState() != 3 && var0.getState() != 4) {
               var10 = var0;
               if (var0.getState() != 5) {
                  return var10;
               }
            }

            var2 = var0.getLastPositionUpdateTime();
            var10 = var0;
            if (var2 > 0L) {
               long var8 = SystemClock.elapsedRealtime();
               long var6 = (long)(var0.getPlaybackSpeed() * (float)(var8 - var2)) + var0.getPosition();
               var2 = var4;
               if (var1 != null) {
                  var2 = var4;
                  if (var1.containsKey("android.media.metadata.DURATION")) {
                     var2 = var1.getLong("android.media.metadata.DURATION");
                  }
               }

               if (var2 < 0L || var6 <= var2) {
                  if (var6 < 0L) {
                     var2 = 0L;
                  } else {
                     var2 = var6;
                  }
               }

               var10 = (new PlaybackStateCompat.Builder(var0)).setState(var0.getState(), var2, var0.getPlaybackSpeed(), var8).build();
            }
         }
      }

      return var10;
   }

   public void addOnActiveChangeListener(OnActiveChangeListener var1) {
      if (var1 != null) {
         this.mActiveListeners.add(var1);
      } else {
         throw new IllegalArgumentException("Listener may not be null");
      }
   }

   public String getCallingPackage() {
      return this.mImpl.getCallingPackage();
   }

   public MediaControllerCompat getController() {
      return this.mController;
   }

   public final MediaSessionManager.RemoteUserInfo getCurrentControllerInfo() {
      return this.mImpl.getCurrentControllerInfo();
   }

   public Object getMediaSession() {
      return this.mImpl.getMediaSession();
   }

   public Object getRemoteControlClient() {
      return this.mImpl.getRemoteControlClient();
   }

   public Token getSessionToken() {
      return this.mImpl.getSessionToken();
   }

   public boolean isActive() {
      return this.mImpl.isActive();
   }

   public void release() {
      this.mImpl.release();
   }

   public void removeOnActiveChangeListener(OnActiveChangeListener var1) {
      if (var1 != null) {
         this.mActiveListeners.remove(var1);
      } else {
         throw new IllegalArgumentException("Listener may not be null");
      }
   }

   public void sendSessionEvent(String var1, Bundle var2) {
      if (!TextUtils.isEmpty(var1)) {
         this.mImpl.sendSessionEvent(var1, var2);
      } else {
         throw new IllegalArgumentException("event cannot be null or empty");
      }
   }

   public void setActive(boolean var1) {
      this.mImpl.setActive(var1);
      Iterator var2 = this.mActiveListeners.iterator();

      while(var2.hasNext()) {
         ((OnActiveChangeListener)var2.next()).onActiveChanged();
      }

   }

   public void setCallback(Callback var1) {
      this.setCallback(var1, (Handler)null);
   }

   public void setCallback(Callback var1, Handler var2) {
      if (var1 == null) {
         this.mImpl.setCallback((Callback)null, (Handler)null);
      } else {
         MediaSessionImpl var3 = this.mImpl;
         if (var2 == null) {
            var2 = new Handler();
         }

         var3.setCallback(var1, var2);
      }

   }

   public void setCaptioningEnabled(boolean var1) {
      this.mImpl.setCaptioningEnabled(var1);
   }

   public void setExtras(Bundle var1) {
      this.mImpl.setExtras(var1);
   }

   public void setFlags(int var1) {
      this.mImpl.setFlags(var1);
   }

   public void setMediaButtonReceiver(PendingIntent var1) {
      this.mImpl.setMediaButtonReceiver(var1);
   }

   public void setMetadata(MediaMetadataCompat var1) {
      this.mImpl.setMetadata(var1);
   }

   public void setPlaybackState(PlaybackStateCompat var1) {
      this.mImpl.setPlaybackState(var1);
   }

   public void setPlaybackToLocal(int var1) {
      this.mImpl.setPlaybackToLocal(var1);
   }

   public void setPlaybackToRemote(VolumeProviderCompat var1) {
      if (var1 != null) {
         this.mImpl.setPlaybackToRemote(var1);
      } else {
         throw new IllegalArgumentException("volumeProvider may not be null!");
      }
   }

   public void setQueue(List var1) {
      this.mImpl.setQueue(var1);
   }

   public void setQueueTitle(CharSequence var1) {
      this.mImpl.setQueueTitle(var1);
   }

   public void setRatingType(int var1) {
      this.mImpl.setRatingType(var1);
   }

   public void setRepeatMode(int var1) {
      this.mImpl.setRepeatMode(var1);
   }

   public void setSessionActivity(PendingIntent var1) {
      this.mImpl.setSessionActivity(var1);
   }

   public void setShuffleMode(int var1) {
      this.mImpl.setShuffleMode(var1);
   }

   public abstract static class Callback {
      private CallbackHandler mCallbackHandler = null;
      final Object mCallbackObj;
      private boolean mMediaPlayPauseKeyPending;
      WeakReference mSessionImpl;

      public Callback() {
         if (VERSION.SDK_INT >= 24) {
            this.mCallbackObj = MediaSessionCompatApi24.createCallback(new StubApi24(this));
         } else if (VERSION.SDK_INT >= 23) {
            this.mCallbackObj = MediaSessionCompatApi23.createCallback(new StubApi23(this));
         } else if (VERSION.SDK_INT >= 21) {
            this.mCallbackObj = MediaSessionCompatApi21.createCallback(new StubApi21(this));
         } else {
            this.mCallbackObj = null;
         }

      }

      void handleMediaPlayPauseKeySingleTapIfPending(MediaSessionManager.RemoteUserInfo var1) {
         if (this.mMediaPlayPauseKeyPending) {
            boolean var4 = false;
            this.mMediaPlayPauseKeyPending = false;
            this.mCallbackHandler.removeMessages(1);
            MediaSessionImpl var7 = (MediaSessionImpl)this.mSessionImpl.get();
            if (var7 != null) {
               PlaybackStateCompat var8 = var7.getPlaybackState();
               long var5;
               if (var8 == null) {
                  var5 = 0L;
               } else {
                  var5 = var8.getActions();
               }

               boolean var2;
               if (var8 != null && var8.getState() == 3) {
                  var2 = true;
               } else {
                  var2 = false;
               }

               boolean var3;
               if ((516L & var5) != 0L) {
                  var3 = true;
               } else {
                  var3 = false;
               }

               if ((var5 & 514L) != 0L) {
                  var4 = true;
               }

               var7.setCurrentControllerInfo(var1);
               if (var2 && var4) {
                  this.onPause();
               } else if (!var2 && var3) {
                  this.onPlay();
               }

               var7.setCurrentControllerInfo((MediaSessionManager.RemoteUserInfo)null);
            }
         }
      }

      public void onAddQueueItem(MediaDescriptionCompat var1) {
      }

      public void onAddQueueItem(MediaDescriptionCompat var1, int var2) {
      }

      public void onCommand(String var1, Bundle var2, ResultReceiver var3) {
      }

      public void onCustomAction(String var1, Bundle var2) {
      }

      public void onFastForward() {
      }

      public boolean onMediaButtonEvent(Intent var1) {
         if (VERSION.SDK_INT >= 27) {
            return false;
         } else {
            MediaSessionImpl var5 = (MediaSessionImpl)this.mSessionImpl.get();
            if (var5 != null && this.mCallbackHandler != null) {
               KeyEvent var6 = (KeyEvent)var1.getParcelableExtra("android.intent.extra.KEY_EVENT");
               if (var6 != null && var6.getAction() == 0) {
                  MediaSessionManager.RemoteUserInfo var7 = var5.getCurrentControllerInfo();
                  int var2 = var6.getKeyCode();
                  if (var2 != 79 && var2 != 85) {
                     this.handleMediaPlayPauseKeySingleTapIfPending(var7);
                     return false;
                  }

                  if (var6.getRepeatCount() > 0) {
                     this.handleMediaPlayPauseKeySingleTapIfPending(var7);
                  } else if (this.mMediaPlayPauseKeyPending) {
                     this.mCallbackHandler.removeMessages(1);
                     this.mMediaPlayPauseKeyPending = false;
                     PlaybackStateCompat var8 = var5.getPlaybackState();
                     long var3;
                     if (var8 == null) {
                        var3 = 0L;
                     } else {
                        var3 = var8.getActions();
                     }

                     if ((var3 & 32L) != 0L) {
                        this.onSkipToNext();
                     }
                  } else {
                     this.mMediaPlayPauseKeyPending = true;
                     CallbackHandler var9 = this.mCallbackHandler;
                     var9.sendMessageDelayed(var9.obtainMessage(1, var7), (long)ViewConfiguration.getDoubleTapTimeout());
                  }

                  return true;
               }
            }

            return false;
         }
      }

      public void onPause() {
      }

      public void onPlay() {
      }

      public void onPlayFromMediaId(String var1, Bundle var2) {
      }

      public void onPlayFromSearch(String var1, Bundle var2) {
      }

      public void onPlayFromUri(Uri var1, Bundle var2) {
      }

      public void onPrepare() {
      }

      public void onPrepareFromMediaId(String var1, Bundle var2) {
      }

      public void onPrepareFromSearch(String var1, Bundle var2) {
      }

      public void onPrepareFromUri(Uri var1, Bundle var2) {
      }

      public void onRemoveQueueItem(MediaDescriptionCompat var1) {
      }

      @Deprecated
      public void onRemoveQueueItemAt(int var1) {
      }

      public void onRewind() {
      }

      public void onSeekTo(long var1) {
      }

      public void onSetCaptioningEnabled(boolean var1) {
      }

      public void onSetRating(RatingCompat var1) {
      }

      public void onSetRating(RatingCompat var1, Bundle var2) {
      }

      public void onSetRepeatMode(int var1) {
      }

      public void onSetShuffleMode(int var1) {
      }

      public void onSkipToNext() {
      }

      public void onSkipToPrevious() {
      }

      public void onSkipToQueueItem(long var1) {
      }

      public void onStop() {
      }

      void setSessionImpl(MediaSessionImpl var1, Handler var2) {
         this.mSessionImpl = new WeakReference(var1);
         CallbackHandler var3 = this.mCallbackHandler;
         if (var3 != null) {
            var3.removeCallbacksAndMessages((Object)null);
         }

         this.mCallbackHandler = new CallbackHandler(this, var2.getLooper());
      }

      private class CallbackHandler extends Handler {
         private static final int MSG_MEDIA_PLAY_PAUSE_KEY_DOUBLE_TAP_TIMEOUT = 1;
         final Callback this$0;

         CallbackHandler(Callback var1, Looper var2) {
            super(var2);
            this.this$0 = var1;
         }

         public void handleMessage(Message var1) {
            if (var1.what == 1) {
               this.this$0.handleMediaPlayPauseKeySingleTapIfPending((MediaSessionManager.RemoteUserInfo)var1.obj);
            }

         }
      }

      private class StubApi21 implements MediaSessionCompatApi21.Callback {
         final Callback this$0;

         StubApi21(Callback var1) {
            this.this$0 = var1;
         }

         public void onCommand(String var1, Bundle var2, ResultReceiver var3) {
            label133: {
               boolean var5;
               boolean var10001;
               try {
                  var5 = var1.equals("android.support.v4.media.session.command.GET_EXTRA_BINDER");
               } catch (BadParcelableException var22) {
                  var10001 = false;
                  break label133;
               }

               Token var7 = null;
               Object var6 = null;
               if (var5) {
                  label136: {
                     MediaSessionImplApi21 var23;
                     try {
                        var23 = (MediaSessionImplApi21)this.this$0.mSessionImpl.get();
                     } catch (BadParcelableException var11) {
                        var10001 = false;
                        break label136;
                     }

                     if (var23 == null) {
                        return;
                     }

                     IMediaSession var24;
                     try {
                        var2 = new Bundle();
                        var7 = var23.getSessionToken();
                        var24 = var7.getExtraBinder();
                     } catch (BadParcelableException var10) {
                        var10001 = false;
                        break label136;
                     }

                     IBinder var25;
                     if (var24 == null) {
                        var25 = (IBinder)var6;
                     } else {
                        try {
                           var25 = var24.asBinder();
                        } catch (BadParcelableException var9) {
                           var10001 = false;
                           break label136;
                        }
                     }

                     try {
                        BundleCompat.putBinder(var2, "android.support.v4.media.session.EXTRA_BINDER", var25);
                        var2.putBundle("android.support.v4.media.session.SESSION_TOKEN2_BUNDLE", var7.getSessionToken2Bundle());
                        var3.send(0, var2);
                        return;
                     } catch (BadParcelableException var8) {
                        var10001 = false;
                     }
                  }
               } else {
                  label129: {
                     try {
                        var5 = var1.equals("android.support.v4.media.session.command.ADD_QUEUE_ITEM");
                     } catch (BadParcelableException var21) {
                        var10001 = false;
                        break label129;
                     }

                     if (var5) {
                        try {
                           this.this$0.onAddQueueItem((MediaDescriptionCompat)var2.getParcelable("android.support.v4.media.session.command.ARGUMENT_MEDIA_DESCRIPTION"));
                           return;
                        } catch (BadParcelableException var12) {
                           var10001 = false;
                        }
                     } else {
                        label125: {
                           try {
                              var5 = var1.equals("android.support.v4.media.session.command.ADD_QUEUE_ITEM_AT");
                           } catch (BadParcelableException var20) {
                              var10001 = false;
                              break label125;
                           }

                           if (var5) {
                              try {
                                 this.this$0.onAddQueueItem((MediaDescriptionCompat)var2.getParcelable("android.support.v4.media.session.command.ARGUMENT_MEDIA_DESCRIPTION"), var2.getInt("android.support.v4.media.session.command.ARGUMENT_INDEX"));
                                 return;
                              } catch (BadParcelableException var13) {
                                 var10001 = false;
                              }
                           } else {
                              label137: {
                                 try {
                                    if (var1.equals("android.support.v4.media.session.command.REMOVE_QUEUE_ITEM")) {
                                       this.this$0.onRemoveQueueItem((MediaDescriptionCompat)var2.getParcelable("android.support.v4.media.session.command.ARGUMENT_MEDIA_DESCRIPTION"));
                                       return;
                                    }
                                 } catch (BadParcelableException var19) {
                                    var10001 = false;
                                    break label137;
                                 }

                                 MediaSessionImplApi21 var27;
                                 label117: {
                                    try {
                                       if (var1.equals("android.support.v4.media.session.command.REMOVE_QUEUE_ITEM_AT")) {
                                          var27 = (MediaSessionImplApi21)this.this$0.mSessionImpl.get();
                                          break label117;
                                       }
                                    } catch (BadParcelableException var18) {
                                       var10001 = false;
                                       break label137;
                                    }

                                    try {
                                       this.this$0.onCommand(var1, var2, var3);
                                       return;
                                    } catch (BadParcelableException var17) {
                                       var10001 = false;
                                       break label137;
                                    }
                                 }

                                 if (var27 == null) {
                                    return;
                                 }

                                 int var4;
                                 try {
                                    if (var27.mQueue == null) {
                                       return;
                                    }

                                    var4 = var2.getInt("android.support.v4.media.session.command.ARGUMENT_INDEX", -1);
                                 } catch (BadParcelableException var16) {
                                    var10001 = false;
                                    break label137;
                                 }

                                 QueueItem var26 = var7;
                                 if (var4 >= 0) {
                                    var26 = var7;

                                    try {
                                       if (var4 < var27.mQueue.size()) {
                                          var26 = (QueueItem)var27.mQueue.get(var4);
                                       }
                                    } catch (BadParcelableException var15) {
                                       var10001 = false;
                                       break label137;
                                    }
                                 }

                                 if (var26 == null) {
                                    return;
                                 }

                                 try {
                                    this.this$0.onRemoveQueueItem(var26.getDescription());
                                    return;
                                 } catch (BadParcelableException var14) {
                                    var10001 = false;
                                 }
                              }
                           }
                        }
                     }
                  }
               }
            }

            Log.e("MediaSessionCompat", "Could not unparcel the extra data.");
         }

         public void onCustomAction(String var1, Bundle var2) {
            Bundle var5 = var2.getBundle("android.support.v4.media.session.action.ARGUMENT_EXTRAS");
            MediaSessionCompat.ensureClassLoader(var5);
            Uri var6;
            if (var1.equals("android.support.v4.media.session.action.PLAY_FROM_URI")) {
               var6 = (Uri)var2.getParcelable("android.support.v4.media.session.action.ARGUMENT_URI");
               this.this$0.onPlayFromUri(var6, var5);
            } else if (var1.equals("android.support.v4.media.session.action.PREPARE")) {
               this.this$0.onPrepare();
            } else if (var1.equals("android.support.v4.media.session.action.PREPARE_FROM_MEDIA_ID")) {
               var1 = var2.getString("android.support.v4.media.session.action.ARGUMENT_MEDIA_ID");
               this.this$0.onPrepareFromMediaId(var1, var5);
            } else if (var1.equals("android.support.v4.media.session.action.PREPARE_FROM_SEARCH")) {
               var1 = var2.getString("android.support.v4.media.session.action.ARGUMENT_QUERY");
               this.this$0.onPrepareFromSearch(var1, var5);
            } else if (var1.equals("android.support.v4.media.session.action.PREPARE_FROM_URI")) {
               var6 = (Uri)var2.getParcelable("android.support.v4.media.session.action.ARGUMENT_URI");
               this.this$0.onPrepareFromUri(var6, var5);
            } else if (var1.equals("android.support.v4.media.session.action.SET_CAPTIONING_ENABLED")) {
               boolean var4 = var2.getBoolean("android.support.v4.media.session.action.ARGUMENT_CAPTIONING_ENABLED");
               this.this$0.onSetCaptioningEnabled(var4);
            } else {
               int var3;
               if (var1.equals("android.support.v4.media.session.action.SET_REPEAT_MODE")) {
                  var3 = var2.getInt("android.support.v4.media.session.action.ARGUMENT_REPEAT_MODE");
                  this.this$0.onSetRepeatMode(var3);
               } else if (var1.equals("android.support.v4.media.session.action.SET_SHUFFLE_MODE")) {
                  var3 = var2.getInt("android.support.v4.media.session.action.ARGUMENT_SHUFFLE_MODE");
                  this.this$0.onSetShuffleMode(var3);
               } else if (var1.equals("android.support.v4.media.session.action.SET_RATING")) {
                  RatingCompat var7 = (RatingCompat)var2.getParcelable("android.support.v4.media.session.action.ARGUMENT_RATING");
                  this.this$0.onSetRating(var7, var5);
               } else {
                  this.this$0.onCustomAction(var1, var2);
               }
            }

         }

         public void onFastForward() {
            this.this$0.onFastForward();
         }

         public boolean onMediaButtonEvent(Intent var1) {
            return this.this$0.onMediaButtonEvent(var1);
         }

         public void onPause() {
            this.this$0.onPause();
         }

         public void onPlay() {
            this.this$0.onPlay();
         }

         public void onPlayFromMediaId(String var1, Bundle var2) {
            this.this$0.onPlayFromMediaId(var1, var2);
         }

         public void onPlayFromSearch(String var1, Bundle var2) {
            this.this$0.onPlayFromSearch(var1, var2);
         }

         public void onRewind() {
            this.this$0.onRewind();
         }

         public void onSeekTo(long var1) {
            this.this$0.onSeekTo(var1);
         }

         public void onSetRating(Object var1) {
            this.this$0.onSetRating(RatingCompat.fromRating(var1));
         }

         public void onSetRating(Object var1, Bundle var2) {
         }

         public void onSkipToNext() {
            this.this$0.onSkipToNext();
         }

         public void onSkipToPrevious() {
            this.this$0.onSkipToPrevious();
         }

         public void onSkipToQueueItem(long var1) {
            this.this$0.onSkipToQueueItem(var1);
         }

         public void onStop() {
            this.this$0.onStop();
         }
      }

      private class StubApi23 extends StubApi21 implements MediaSessionCompatApi23.Callback {
         final Callback this$0;

         StubApi23(Callback var1) {
            super(var1);
            this.this$0 = var1;
         }

         public void onPlayFromUri(Uri var1, Bundle var2) {
            this.this$0.onPlayFromUri(var1, var2);
         }
      }

      private class StubApi24 extends StubApi23 implements MediaSessionCompatApi24.Callback {
         final Callback this$0;

         StubApi24(Callback var1) {
            super(var1);
            this.this$0 = var1;
         }

         public void onPrepare() {
            this.this$0.onPrepare();
         }

         public void onPrepareFromMediaId(String var1, Bundle var2) {
            this.this$0.onPrepareFromMediaId(var1, var2);
         }

         public void onPrepareFromSearch(String var1, Bundle var2) {
            this.this$0.onPrepareFromSearch(var1, var2);
         }

         public void onPrepareFromUri(Uri var1, Bundle var2) {
            this.this$0.onPrepareFromUri(var1, var2);
         }
      }
   }

   interface MediaSessionImpl {
      String getCallingPackage();

      MediaSessionManager.RemoteUserInfo getCurrentControllerInfo();

      Object getMediaSession();

      PlaybackStateCompat getPlaybackState();

      Object getRemoteControlClient();

      Token getSessionToken();

      boolean isActive();

      void release();

      void sendSessionEvent(String var1, Bundle var2);

      void setActive(boolean var1);

      void setCallback(Callback var1, Handler var2);

      void setCaptioningEnabled(boolean var1);

      void setCurrentControllerInfo(MediaSessionManager.RemoteUserInfo var1);

      void setExtras(Bundle var1);

      void setFlags(int var1);

      void setMediaButtonReceiver(PendingIntent var1);

      void setMetadata(MediaMetadataCompat var1);

      void setPlaybackState(PlaybackStateCompat var1);

      void setPlaybackToLocal(int var1);

      void setPlaybackToRemote(VolumeProviderCompat var1);

      void setQueue(List var1);

      void setQueueTitle(CharSequence var1);

      void setRatingType(int var1);

      void setRepeatMode(int var1);

      void setSessionActivity(PendingIntent var1);

      void setShuffleMode(int var1);
   }

   static class MediaSessionImplApi18 extends MediaSessionImplBase {
      private static boolean sIsMbrPendingIntentSupported;

      MediaSessionImplApi18(Context var1, String var2, ComponentName var3, PendingIntent var4) {
         super(var1, var2, var3, var4);
      }

      int getRccTransportControlFlagsFromActions(long var1) {
         int var4 = super.getRccTransportControlFlagsFromActions(var1);
         int var3 = var4;
         if ((var1 & 256L) != 0L) {
            var3 = var4 | 256;
         }

         return var3;
      }

      void registerMediaButtonEventReceiver(PendingIntent var1, ComponentName var2) {
         if (sIsMbrPendingIntentSupported) {
            try {
               this.mAudioManager.registerMediaButtonEventReceiver(var1);
            } catch (NullPointerException var4) {
               Log.w("MediaSessionCompat", "Unable to register media button event receiver with PendingIntent, falling back to ComponentName.");
               sIsMbrPendingIntentSupported = false;
            }
         }

         if (!sIsMbrPendingIntentSupported) {
            super.registerMediaButtonEventReceiver(var1, var2);
         }

      }

      public void setCallback(Callback var1, Handler var2) {
         super.setCallback(var1, var2);
         if (var1 == null) {
            this.mRcc.setPlaybackPositionUpdateListener((RemoteControlClient.OnPlaybackPositionUpdateListener)null);
         } else {
            RemoteControlClient.OnPlaybackPositionUpdateListener var3 = new RemoteControlClient.OnPlaybackPositionUpdateListener(this) {
               final MediaSessionImplApi18 this$0;

               {
                  this.this$0 = var1;
               }

               public void onPlaybackPositionUpdate(long var1) {
                  this.this$0.postToHandler(18, -1, -1, var1, (Bundle)null);
               }
            };
            this.mRcc.setPlaybackPositionUpdateListener(var3);
         }

      }

      void setRccState(PlaybackStateCompat var1) {
         long var5 = var1.getPosition();
         float var2 = var1.getPlaybackSpeed();
         long var11 = var1.getLastPositionUpdateTime();
         long var9 = SystemClock.elapsedRealtime();
         long var3 = var5;
         if (var1.getState() == 3) {
            long var7 = 0L;
            var3 = var5;
            if (var5 > 0L) {
               var3 = var7;
               if (var11 > 0L) {
                  var7 = var9 - var11;
                  var3 = var7;
                  if (var2 > 0.0F) {
                     var3 = var7;
                     if (var2 != 1.0F) {
                        var3 = (long)((float)var7 * var2);
                     }
                  }
               }

               var3 += var5;
            }
         }

         this.mRcc.setPlaybackState(this.getRccStateFromState(var1.getState()), var3, var2);
      }

      void unregisterMediaButtonEventReceiver(PendingIntent var1, ComponentName var2) {
         if (sIsMbrPendingIntentSupported) {
            this.mAudioManager.unregisterMediaButtonEventReceiver(var1);
         } else {
            super.unregisterMediaButtonEventReceiver(var1, var2);
         }

      }
   }

   static class MediaSessionImplApi19 extends MediaSessionImplApi18 {
      MediaSessionImplApi19(Context var1, String var2, ComponentName var3, PendingIntent var4) {
         super(var1, var2, var3, var4);
      }

      RemoteControlClient.MetadataEditor buildRccMetadata(Bundle var1) {
         RemoteControlClient.MetadataEditor var4 = super.buildRccMetadata(var1);
         long var2;
         if (this.mState == null) {
            var2 = 0L;
         } else {
            var2 = this.mState.getActions();
         }

         if ((var2 & 128L) != 0L) {
            var4.addEditableKey(268435457);
         }

         if (var1 == null) {
            return var4;
         } else {
            if (var1.containsKey("android.media.metadata.YEAR")) {
               var4.putLong(8, var1.getLong("android.media.metadata.YEAR"));
            }

            if (var1.containsKey("android.media.metadata.RATING")) {
               var4.putObject(101, var1.getParcelable("android.media.metadata.RATING"));
            }

            if (var1.containsKey("android.media.metadata.USER_RATING")) {
               var4.putObject(268435457, var1.getParcelable("android.media.metadata.USER_RATING"));
            }

            return var4;
         }
      }

      int getRccTransportControlFlagsFromActions(long var1) {
         int var4 = super.getRccTransportControlFlagsFromActions(var1);
         int var3 = var4;
         if ((var1 & 128L) != 0L) {
            var3 = var4 | 512;
         }

         return var3;
      }

      public void setCallback(Callback var1, Handler var2) {
         super.setCallback(var1, var2);
         if (var1 == null) {
            this.mRcc.setMetadataUpdateListener((RemoteControlClient.OnMetadataUpdateListener)null);
         } else {
            RemoteControlClient.OnMetadataUpdateListener var3 = new RemoteControlClient.OnMetadataUpdateListener(this) {
               final MediaSessionImplApi19 this$0;

               {
                  this.this$0 = var1;
               }

               public void onMetadataUpdate(int var1, Object var2) {
                  if (var1 == 268435457 && var2 instanceof Rating) {
                     this.this$0.postToHandler(19, -1, -1, RatingCompat.fromRating(var2), (Bundle)null);
                  }

               }
            };
            this.mRcc.setMetadataUpdateListener(var3);
         }

      }
   }

   static class MediaSessionImplApi21 implements MediaSessionImpl {
      boolean mCaptioningEnabled;
      boolean mDestroyed = false;
      final RemoteCallbackList mExtraControllerCallbacks = new RemoteCallbackList();
      MediaMetadataCompat mMetadata;
      PlaybackStateCompat mPlaybackState;
      List mQueue;
      int mRatingType;
      int mRepeatMode;
      final Object mSessionObj;
      int mShuffleMode;
      final Token mToken;

      MediaSessionImplApi21(Context var1, String var2, Bundle var3) {
         Object var4 = MediaSessionCompatApi21.createSession(var1, var2);
         this.mSessionObj = var4;
         this.mToken = new Token(MediaSessionCompatApi21.getSessionToken(var4), new ExtraSession(this), var3);
      }

      MediaSessionImplApi21(Object var1) {
         var1 = MediaSessionCompatApi21.verifySession(var1);
         this.mSessionObj = var1;
         this.mToken = new Token(MediaSessionCompatApi21.getSessionToken(var1), new ExtraSession(this));
      }

      public String getCallingPackage() {
         return VERSION.SDK_INT < 24 ? null : MediaSessionCompatApi24.getCallingPackage(this.mSessionObj);
      }

      public MediaSessionManager.RemoteUserInfo getCurrentControllerInfo() {
         return null;
      }

      public Object getMediaSession() {
         return this.mSessionObj;
      }

      public PlaybackStateCompat getPlaybackState() {
         return this.mPlaybackState;
      }

      public Object getRemoteControlClient() {
         return null;
      }

      public Token getSessionToken() {
         return this.mToken;
      }

      public boolean isActive() {
         return MediaSessionCompatApi21.isActive(this.mSessionObj);
      }

      public void release() {
         this.mDestroyed = true;
         MediaSessionCompatApi21.release(this.mSessionObj);
      }

      public void sendSessionEvent(String var1, Bundle var2) {
         if (VERSION.SDK_INT < 23) {
            for(int var3 = this.mExtraControllerCallbacks.beginBroadcast() - 1; var3 >= 0; --var3) {
               IMediaControllerCallback var4 = (IMediaControllerCallback)this.mExtraControllerCallbacks.getBroadcastItem(var3);

               try {
                  var4.onEvent(var1, var2);
               } catch (RemoteException var5) {
               }
            }

            this.mExtraControllerCallbacks.finishBroadcast();
         }

         MediaSessionCompatApi21.sendSessionEvent(this.mSessionObj, var1, var2);
      }

      public void setActive(boolean var1) {
         MediaSessionCompatApi21.setActive(this.mSessionObj, var1);
      }

      public void setCallback(Callback var1, Handler var2) {
         Object var4 = this.mSessionObj;
         Object var3;
         if (var1 == null) {
            var3 = null;
         } else {
            var3 = var1.mCallbackObj;
         }

         MediaSessionCompatApi21.setCallback(var4, var3, var2);
         if (var1 != null) {
            var1.setSessionImpl(this, var2);
         }

      }

      public void setCaptioningEnabled(boolean var1) {
         if (this.mCaptioningEnabled != var1) {
            this.mCaptioningEnabled = var1;

            for(int var2 = this.mExtraControllerCallbacks.beginBroadcast() - 1; var2 >= 0; --var2) {
               IMediaControllerCallback var3 = (IMediaControllerCallback)this.mExtraControllerCallbacks.getBroadcastItem(var2);

               try {
                  var3.onCaptioningEnabledChanged(var1);
               } catch (RemoteException var4) {
               }
            }

            this.mExtraControllerCallbacks.finishBroadcast();
         }

      }

      public void setCurrentControllerInfo(MediaSessionManager.RemoteUserInfo var1) {
      }

      public void setExtras(Bundle var1) {
         MediaSessionCompatApi21.setExtras(this.mSessionObj, var1);
      }

      public void setFlags(int var1) {
         MediaSessionCompatApi21.setFlags(this.mSessionObj, var1);
      }

      public void setMediaButtonReceiver(PendingIntent var1) {
         MediaSessionCompatApi21.setMediaButtonReceiver(this.mSessionObj, var1);
      }

      public void setMetadata(MediaMetadataCompat var1) {
         this.mMetadata = var1;
         Object var2 = this.mSessionObj;
         Object var3;
         if (var1 == null) {
            var3 = null;
         } else {
            var3 = var1.getMediaMetadata();
         }

         MediaSessionCompatApi21.setMetadata(var2, var3);
      }

      public void setPlaybackState(PlaybackStateCompat var1) {
         this.mPlaybackState = var1;

         for(int var2 = this.mExtraControllerCallbacks.beginBroadcast() - 1; var2 >= 0; --var2) {
            IMediaControllerCallback var3 = (IMediaControllerCallback)this.mExtraControllerCallbacks.getBroadcastItem(var2);

            try {
               var3.onPlaybackStateChanged(var1);
            } catch (RemoteException var4) {
            }
         }

         this.mExtraControllerCallbacks.finishBroadcast();
         Object var6 = this.mSessionObj;
         Object var5;
         if (var1 == null) {
            var5 = null;
         } else {
            var5 = var1.getPlaybackState();
         }

         MediaSessionCompatApi21.setPlaybackState(var6, var5);
      }

      public void setPlaybackToLocal(int var1) {
         MediaSessionCompatApi21.setPlaybackToLocal(this.mSessionObj, var1);
      }

      public void setPlaybackToRemote(VolumeProviderCompat var1) {
         MediaSessionCompatApi21.setPlaybackToRemote(this.mSessionObj, var1.getVolumeProvider());
      }

      public void setQueue(List var1) {
         this.mQueue = var1;
         ArrayList var4;
         if (var1 != null) {
            ArrayList var2 = new ArrayList();
            Iterator var3 = var1.iterator();

            while(true) {
               var4 = var2;
               if (!var3.hasNext()) {
                  break;
               }

               var2.add(((QueueItem)var3.next()).getQueueItem());
            }
         } else {
            var4 = null;
         }

         MediaSessionCompatApi21.setQueue(this.mSessionObj, var4);
      }

      public void setQueueTitle(CharSequence var1) {
         MediaSessionCompatApi21.setQueueTitle(this.mSessionObj, var1);
      }

      public void setRatingType(int var1) {
         if (VERSION.SDK_INT < 22) {
            this.mRatingType = var1;
         } else {
            MediaSessionCompatApi22.setRatingType(this.mSessionObj, var1);
         }

      }

      public void setRepeatMode(int var1) {
         if (this.mRepeatMode != var1) {
            this.mRepeatMode = var1;

            for(int var2 = this.mExtraControllerCallbacks.beginBroadcast() - 1; var2 >= 0; --var2) {
               IMediaControllerCallback var3 = (IMediaControllerCallback)this.mExtraControllerCallbacks.getBroadcastItem(var2);

               try {
                  var3.onRepeatModeChanged(var1);
               } catch (RemoteException var4) {
               }
            }

            this.mExtraControllerCallbacks.finishBroadcast();
         }

      }

      public void setSessionActivity(PendingIntent var1) {
         MediaSessionCompatApi21.setSessionActivity(this.mSessionObj, var1);
      }

      public void setShuffleMode(int var1) {
         if (this.mShuffleMode != var1) {
            this.mShuffleMode = var1;

            for(int var2 = this.mExtraControllerCallbacks.beginBroadcast() - 1; var2 >= 0; --var2) {
               IMediaControllerCallback var3 = (IMediaControllerCallback)this.mExtraControllerCallbacks.getBroadcastItem(var2);

               try {
                  var3.onShuffleModeChanged(var1);
               } catch (RemoteException var4) {
               }
            }

            this.mExtraControllerCallbacks.finishBroadcast();
         }

      }

      class ExtraSession extends IMediaSession.Stub {
         final MediaSessionImplApi21 this$0;

         ExtraSession(MediaSessionImplApi21 var1) {
            this.this$0 = var1;
         }

         public void addQueueItem(MediaDescriptionCompat var1) {
            throw new AssertionError();
         }

         public void addQueueItemAt(MediaDescriptionCompat var1, int var2) {
            throw new AssertionError();
         }

         public void adjustVolume(int var1, int var2, String var3) {
            throw new AssertionError();
         }

         public void fastForward() throws RemoteException {
            throw new AssertionError();
         }

         public Bundle getExtras() {
            throw new AssertionError();
         }

         public long getFlags() {
            throw new AssertionError();
         }

         public PendingIntent getLaunchPendingIntent() {
            throw new AssertionError();
         }

         public MediaMetadataCompat getMetadata() {
            throw new AssertionError();
         }

         public String getPackageName() {
            throw new AssertionError();
         }

         public PlaybackStateCompat getPlaybackState() {
            return MediaSessionCompat.getStateWithUpdatedPosition(this.this$0.mPlaybackState, this.this$0.mMetadata);
         }

         public List getQueue() {
            return null;
         }

         public CharSequence getQueueTitle() {
            throw new AssertionError();
         }

         public int getRatingType() {
            return this.this$0.mRatingType;
         }

         public int getRepeatMode() {
            return this.this$0.mRepeatMode;
         }

         public int getShuffleMode() {
            return this.this$0.mShuffleMode;
         }

         public String getTag() {
            throw new AssertionError();
         }

         public ParcelableVolumeInfo getVolumeAttributes() {
            throw new AssertionError();
         }

         public boolean isCaptioningEnabled() {
            return this.this$0.mCaptioningEnabled;
         }

         public boolean isShuffleModeEnabledRemoved() {
            return false;
         }

         public boolean isTransportControlEnabled() {
            throw new AssertionError();
         }

         public void next() throws RemoteException {
            throw new AssertionError();
         }

         public void pause() throws RemoteException {
            throw new AssertionError();
         }

         public void play() throws RemoteException {
            throw new AssertionError();
         }

         public void playFromMediaId(String var1, Bundle var2) throws RemoteException {
            throw new AssertionError();
         }

         public void playFromSearch(String var1, Bundle var2) throws RemoteException {
            throw new AssertionError();
         }

         public void playFromUri(Uri var1, Bundle var2) throws RemoteException {
            throw new AssertionError();
         }

         public void prepare() throws RemoteException {
            throw new AssertionError();
         }

         public void prepareFromMediaId(String var1, Bundle var2) throws RemoteException {
            throw new AssertionError();
         }

         public void prepareFromSearch(String var1, Bundle var2) throws RemoteException {
            throw new AssertionError();
         }

         public void prepareFromUri(Uri var1, Bundle var2) throws RemoteException {
            throw new AssertionError();
         }

         public void previous() throws RemoteException {
            throw new AssertionError();
         }

         public void rate(RatingCompat var1) throws RemoteException {
            throw new AssertionError();
         }

         public void rateWithExtras(RatingCompat var1, Bundle var2) throws RemoteException {
            throw new AssertionError();
         }

         public void registerCallbackListener(IMediaControllerCallback var1) {
            if (!this.this$0.mDestroyed) {
               String var3 = this.this$0.getCallingPackage();
               String var2 = var3;
               if (var3 == null) {
                  var2 = "android.media.session.MediaController";
               }

               MediaSessionManager.RemoteUserInfo var4 = new MediaSessionManager.RemoteUserInfo(var2, getCallingPid(), getCallingUid());
               this.this$0.mExtraControllerCallbacks.register(var1, var4);
            }

         }

         public void removeQueueItem(MediaDescriptionCompat var1) {
            throw new AssertionError();
         }

         public void removeQueueItemAt(int var1) {
            throw new AssertionError();
         }

         public void rewind() throws RemoteException {
            throw new AssertionError();
         }

         public void seekTo(long var1) throws RemoteException {
            throw new AssertionError();
         }

         public void sendCommand(String var1, Bundle var2, ResultReceiverWrapper var3) {
            throw new AssertionError();
         }

         public void sendCustomAction(String var1, Bundle var2) throws RemoteException {
            throw new AssertionError();
         }

         public boolean sendMediaButton(KeyEvent var1) {
            throw new AssertionError();
         }

         public void setCaptioningEnabled(boolean var1) throws RemoteException {
            throw new AssertionError();
         }

         public void setRepeatMode(int var1) throws RemoteException {
            throw new AssertionError();
         }

         public void setShuffleMode(int var1) throws RemoteException {
            throw new AssertionError();
         }

         public void setShuffleModeEnabledRemoved(boolean var1) throws RemoteException {
         }

         public void setVolumeTo(int var1, int var2, String var3) {
            throw new AssertionError();
         }

         public void skipToQueueItem(long var1) {
            throw new AssertionError();
         }

         public void stop() throws RemoteException {
            throw new AssertionError();
         }

         public void unregisterCallbackListener(IMediaControllerCallback var1) {
            this.this$0.mExtraControllerCallbacks.unregister(var1);
         }
      }
   }

   static class MediaSessionImplApi28 extends MediaSessionImplApi21 {
      MediaSessionImplApi28(Context var1, String var2, Bundle var3) {
         super(var1, var2, var3);
      }

      MediaSessionImplApi28(Object var1) {
         super(var1);
      }

      public final MediaSessionManager.RemoteUserInfo getCurrentControllerInfo() {
         return new MediaSessionManager.RemoteUserInfo(((MediaSession)this.mSessionObj).getCurrentControllerInfo());
      }

      public void setCurrentControllerInfo(MediaSessionManager.RemoteUserInfo var1) {
      }
   }

   static class MediaSessionImplBase implements MediaSessionImpl {
      static final int RCC_PLAYSTATE_NONE = 0;
      final AudioManager mAudioManager;
      volatile Callback mCallback;
      boolean mCaptioningEnabled;
      private final Context mContext;
      final RemoteCallbackList mControllerCallbacks = new RemoteCallbackList();
      boolean mDestroyed = false;
      Bundle mExtras;
      int mFlags;
      private MessageHandler mHandler;
      boolean mIsActive = false;
      private boolean mIsMbrRegistered = false;
      private boolean mIsRccRegistered = false;
      int mLocalStream;
      final Object mLock = new Object();
      private final ComponentName mMediaButtonReceiverComponentName;
      private final PendingIntent mMediaButtonReceiverIntent;
      MediaMetadataCompat mMetadata;
      final String mPackageName;
      List mQueue;
      CharSequence mQueueTitle;
      int mRatingType;
      final RemoteControlClient mRcc;
      private MediaSessionManager.RemoteUserInfo mRemoteUserInfo;
      int mRepeatMode;
      PendingIntent mSessionActivity;
      int mShuffleMode;
      PlaybackStateCompat mState;
      private final MediaSessionStub mStub;
      final String mTag;
      private final Token mToken;
      private VolumeProviderCompat.Callback mVolumeCallback = new VolumeProviderCompat.Callback(this) {
         final MediaSessionImplBase this$0;

         {
            this.this$0 = var1;
         }

         public void onVolumeChanged(VolumeProviderCompat var1) {
            if (this.this$0.mVolumeProvider == var1) {
               ParcelableVolumeInfo var2 = new ParcelableVolumeInfo(this.this$0.mVolumeType, this.this$0.mLocalStream, var1.getVolumeControl(), var1.getMaxVolume(), var1.getCurrentVolume());
               this.this$0.sendVolumeInfoChanged(var2);
            }
         }
      };
      VolumeProviderCompat mVolumeProvider;
      int mVolumeType;

      public MediaSessionImplBase(Context var1, String var2, ComponentName var3, PendingIntent var4) {
         if (var3 != null) {
            this.mContext = var1;
            this.mPackageName = var1.getPackageName();
            this.mAudioManager = (AudioManager)var1.getSystemService("audio");
            this.mTag = var2;
            this.mMediaButtonReceiverComponentName = var3;
            this.mMediaButtonReceiverIntent = var4;
            MediaSessionStub var5 = new MediaSessionStub(this);
            this.mStub = var5;
            this.mToken = new Token(var5);
            this.mRatingType = 0;
            this.mVolumeType = 1;
            this.mLocalStream = 3;
            this.mRcc = new RemoteControlClient(var4);
         } else {
            throw new IllegalArgumentException("MediaButtonReceiver component may not be null.");
         }
      }

      private void sendCaptioningEnabled(boolean var1) {
         for(int var2 = this.mControllerCallbacks.beginBroadcast() - 1; var2 >= 0; --var2) {
            IMediaControllerCallback var3 = (IMediaControllerCallback)this.mControllerCallbacks.getBroadcastItem(var2);

            try {
               var3.onCaptioningEnabledChanged(var1);
            } catch (RemoteException var4) {
            }
         }

         this.mControllerCallbacks.finishBroadcast();
      }

      private void sendEvent(String var1, Bundle var2) {
         for(int var3 = this.mControllerCallbacks.beginBroadcast() - 1; var3 >= 0; --var3) {
            IMediaControllerCallback var4 = (IMediaControllerCallback)this.mControllerCallbacks.getBroadcastItem(var3);

            try {
               var4.onEvent(var1, var2);
            } catch (RemoteException var5) {
            }
         }

         this.mControllerCallbacks.finishBroadcast();
      }

      private void sendExtras(Bundle var1) {
         for(int var2 = this.mControllerCallbacks.beginBroadcast() - 1; var2 >= 0; --var2) {
            IMediaControllerCallback var3 = (IMediaControllerCallback)this.mControllerCallbacks.getBroadcastItem(var2);

            try {
               var3.onExtrasChanged(var1);
            } catch (RemoteException var4) {
            }
         }

         this.mControllerCallbacks.finishBroadcast();
      }

      private void sendMetadata(MediaMetadataCompat var1) {
         for(int var2 = this.mControllerCallbacks.beginBroadcast() - 1; var2 >= 0; --var2) {
            IMediaControllerCallback var3 = (IMediaControllerCallback)this.mControllerCallbacks.getBroadcastItem(var2);

            try {
               var3.onMetadataChanged(var1);
            } catch (RemoteException var4) {
            }
         }

         this.mControllerCallbacks.finishBroadcast();
      }

      private void sendQueue(List var1) {
         for(int var2 = this.mControllerCallbacks.beginBroadcast() - 1; var2 >= 0; --var2) {
            IMediaControllerCallback var3 = (IMediaControllerCallback)this.mControllerCallbacks.getBroadcastItem(var2);

            try {
               var3.onQueueChanged(var1);
            } catch (RemoteException var4) {
            }
         }

         this.mControllerCallbacks.finishBroadcast();
      }

      private void sendQueueTitle(CharSequence var1) {
         for(int var2 = this.mControllerCallbacks.beginBroadcast() - 1; var2 >= 0; --var2) {
            IMediaControllerCallback var3 = (IMediaControllerCallback)this.mControllerCallbacks.getBroadcastItem(var2);

            try {
               var3.onQueueTitleChanged(var1);
            } catch (RemoteException var4) {
            }
         }

         this.mControllerCallbacks.finishBroadcast();
      }

      private void sendRepeatMode(int var1) {
         for(int var2 = this.mControllerCallbacks.beginBroadcast() - 1; var2 >= 0; --var2) {
            IMediaControllerCallback var3 = (IMediaControllerCallback)this.mControllerCallbacks.getBroadcastItem(var2);

            try {
               var3.onRepeatModeChanged(var1);
            } catch (RemoteException var4) {
            }
         }

         this.mControllerCallbacks.finishBroadcast();
      }

      private void sendSessionDestroyed() {
         for(int var1 = this.mControllerCallbacks.beginBroadcast() - 1; var1 >= 0; --var1) {
            IMediaControllerCallback var2 = (IMediaControllerCallback)this.mControllerCallbacks.getBroadcastItem(var1);

            try {
               var2.onSessionDestroyed();
            } catch (RemoteException var3) {
            }
         }

         this.mControllerCallbacks.finishBroadcast();
         this.mControllerCallbacks.kill();
      }

      private void sendShuffleMode(int var1) {
         for(int var2 = this.mControllerCallbacks.beginBroadcast() - 1; var2 >= 0; --var2) {
            IMediaControllerCallback var3 = (IMediaControllerCallback)this.mControllerCallbacks.getBroadcastItem(var2);

            try {
               var3.onShuffleModeChanged(var1);
            } catch (RemoteException var4) {
            }
         }

         this.mControllerCallbacks.finishBroadcast();
      }

      private void sendState(PlaybackStateCompat var1) {
         for(int var2 = this.mControllerCallbacks.beginBroadcast() - 1; var2 >= 0; --var2) {
            IMediaControllerCallback var3 = (IMediaControllerCallback)this.mControllerCallbacks.getBroadcastItem(var2);

            try {
               var3.onPlaybackStateChanged(var1);
            } catch (RemoteException var4) {
            }
         }

         this.mControllerCallbacks.finishBroadcast();
      }

      void adjustVolume(int var1, int var2) {
         if (this.mVolumeType == 2) {
            VolumeProviderCompat var3 = this.mVolumeProvider;
            if (var3 != null) {
               var3.onAdjustVolume(var1);
            }
         } else {
            this.mAudioManager.adjustStreamVolume(this.mLocalStream, var1, var2);
         }

      }

      RemoteControlClient.MetadataEditor buildRccMetadata(Bundle var1) {
         RemoteControlClient.MetadataEditor var4 = this.mRcc.editMetadata(true);
         if (var1 == null) {
            return var4;
         } else {
            Bitmap var2;
            Bitmap var3;
            if (var1.containsKey("android.media.metadata.ART")) {
               var3 = (Bitmap)var1.getParcelable("android.media.metadata.ART");
               var2 = var3;
               if (var3 != null) {
                  var2 = var3.copy(var3.getConfig(), false);
               }

               var4.putBitmap(100, var2);
            } else if (var1.containsKey("android.media.metadata.ALBUM_ART")) {
               var3 = (Bitmap)var1.getParcelable("android.media.metadata.ALBUM_ART");
               var2 = var3;
               if (var3 != null) {
                  var2 = var3.copy(var3.getConfig(), false);
               }

               var4.putBitmap(100, var2);
            }

            if (var1.containsKey("android.media.metadata.ALBUM")) {
               var4.putString(1, var1.getString("android.media.metadata.ALBUM"));
            }

            if (var1.containsKey("android.media.metadata.ALBUM_ARTIST")) {
               var4.putString(13, var1.getString("android.media.metadata.ALBUM_ARTIST"));
            }

            if (var1.containsKey("android.media.metadata.ARTIST")) {
               var4.putString(2, var1.getString("android.media.metadata.ARTIST"));
            }

            if (var1.containsKey("android.media.metadata.AUTHOR")) {
               var4.putString(3, var1.getString("android.media.metadata.AUTHOR"));
            }

            if (var1.containsKey("android.media.metadata.COMPILATION")) {
               var4.putString(15, var1.getString("android.media.metadata.COMPILATION"));
            }

            if (var1.containsKey("android.media.metadata.COMPOSER")) {
               var4.putString(4, var1.getString("android.media.metadata.COMPOSER"));
            }

            if (var1.containsKey("android.media.metadata.DATE")) {
               var4.putString(5, var1.getString("android.media.metadata.DATE"));
            }

            if (var1.containsKey("android.media.metadata.DISC_NUMBER")) {
               var4.putLong(14, var1.getLong("android.media.metadata.DISC_NUMBER"));
            }

            if (var1.containsKey("android.media.metadata.DURATION")) {
               var4.putLong(9, var1.getLong("android.media.metadata.DURATION"));
            }

            if (var1.containsKey("android.media.metadata.GENRE")) {
               var4.putString(6, var1.getString("android.media.metadata.GENRE"));
            }

            if (var1.containsKey("android.media.metadata.TITLE")) {
               var4.putString(7, var1.getString("android.media.metadata.TITLE"));
            }

            if (var1.containsKey("android.media.metadata.TRACK_NUMBER")) {
               var4.putLong(0, var1.getLong("android.media.metadata.TRACK_NUMBER"));
            }

            if (var1.containsKey("android.media.metadata.WRITER")) {
               var4.putString(11, var1.getString("android.media.metadata.WRITER"));
            }

            return var4;
         }
      }

      public String getCallingPackage() {
         return null;
      }

      public MediaSessionManager.RemoteUserInfo getCurrentControllerInfo() {
         // $FF: Couldn't be decompiled
      }

      public Object getMediaSession() {
         return null;
      }

      public PlaybackStateCompat getPlaybackState() {
         // $FF: Couldn't be decompiled
      }

      int getRccStateFromState(int var1) {
         switch (var1) {
            case 0:
               return 0;
            case 1:
               return 1;
            case 2:
               return 2;
            case 3:
               return 3;
            case 4:
               return 4;
            case 5:
               return 5;
            case 6:
            case 8:
               return 8;
            case 7:
               return 9;
            case 9:
               return 7;
            case 10:
            case 11:
               return 6;
            default:
               return -1;
         }
      }

      int getRccTransportControlFlagsFromActions(long var1) {
         int var3;
         if ((1L & var1) != 0L) {
            var3 = 32;
         } else {
            var3 = 0;
         }

         int var4 = var3;
         if ((2L & var1) != 0L) {
            var4 = var3 | 16;
         }

         var3 = var4;
         if ((4L & var1) != 0L) {
            var3 = var4 | 4;
         }

         var4 = var3;
         if ((8L & var1) != 0L) {
            var4 = var3 | 2;
         }

         var3 = var4;
         if ((16L & var1) != 0L) {
            var3 = var4 | 1;
         }

         var4 = var3;
         if ((32L & var1) != 0L) {
            var4 = var3 | 128;
         }

         var3 = var4;
         if ((64L & var1) != 0L) {
            var3 = var4 | 64;
         }

         var4 = var3;
         if ((var1 & 512L) != 0L) {
            var4 = var3 | 8;
         }

         return var4;
      }

      public Object getRemoteControlClient() {
         return null;
      }

      public Token getSessionToken() {
         return this.mToken;
      }

      public boolean isActive() {
         return this.mIsActive;
      }

      void postToHandler(int var1, int var2, int var3, Object var4, Bundle var5) {
         Object var6 = this.mLock;
         synchronized(var6){}

         Throwable var10000;
         boolean var10001;
         label322: {
            MessageHandler var7;
            try {
               var7 = this.mHandler;
            } catch (Throwable var49) {
               var10000 = var49;
               var10001 = false;
               break label322;
            }

            if (var7 != null) {
               Bundle var50;
               Message var52;
               try {
                  var52 = var7.obtainMessage(var1, var2, var3, var4);
                  var50 = new Bundle();
                  var50.putString("data_calling_pkg", "android.media.session.MediaController");
                  var50.putInt("data_calling_pid", Binder.getCallingPid());
                  var50.putInt("data_calling_uid", Binder.getCallingUid());
               } catch (Throwable var48) {
                  var10000 = var48;
                  var10001 = false;
                  break label322;
               }

               if (var5 != null) {
                  try {
                     var50.putBundle("data_extras", var5);
                  } catch (Throwable var47) {
                     var10000 = var47;
                     var10001 = false;
                     break label322;
                  }
               }

               try {
                  var52.setData(var50);
                  var52.sendToTarget();
               } catch (Throwable var46) {
                  var10000 = var46;
                  var10001 = false;
                  break label322;
               }
            }

            label306:
            try {
               return;
            } catch (Throwable var45) {
               var10000 = var45;
               var10001 = false;
               break label306;
            }
         }

         while(true) {
            Throwable var51 = var10000;

            try {
               throw var51;
            } catch (Throwable var44) {
               var10000 = var44;
               var10001 = false;
               continue;
            }
         }
      }

      void registerMediaButtonEventReceiver(PendingIntent var1, ComponentName var2) {
         this.mAudioManager.registerMediaButtonEventReceiver(var2);
      }

      public void release() {
         this.mIsActive = false;
         this.mDestroyed = true;
         this.update();
         this.sendSessionDestroyed();
      }

      public void sendSessionEvent(String var1, Bundle var2) {
         this.sendEvent(var1, var2);
      }

      void sendVolumeInfoChanged(ParcelableVolumeInfo var1) {
         for(int var2 = this.mControllerCallbacks.beginBroadcast() - 1; var2 >= 0; --var2) {
            IMediaControllerCallback var3 = (IMediaControllerCallback)this.mControllerCallbacks.getBroadcastItem(var2);

            try {
               var3.onVolumeInfoChanged(var1);
            } catch (RemoteException var4) {
            }
         }

         this.mControllerCallbacks.finishBroadcast();
      }

      public void setActive(boolean var1) {
         if (var1 != this.mIsActive) {
            this.mIsActive = var1;
            if (this.update()) {
               this.setMetadata(this.mMetadata);
               this.setPlaybackState(this.mState);
            }

         }
      }

      public void setCallback(Callback var1, Handler var2) {
         this.mCallback = var1;
         if (var1 != null) {
            Handler var24 = var2;
            if (var2 == null) {
               var24 = new Handler();
            }

            Object var26 = this.mLock;
            synchronized(var26){}

            Throwable var10000;
            boolean var10001;
            label226: {
               MessageHandler var3;
               try {
                  var3 = this.mHandler;
               } catch (Throwable var23) {
                  var10000 = var23;
                  var10001 = false;
                  break label226;
               }

               if (var3 != null) {
                  try {
                     var3.removeCallbacksAndMessages((Object)null);
                  } catch (Throwable var22) {
                     var10000 = var22;
                     var10001 = false;
                     break label226;
                  }
               }

               label211:
               try {
                  var3 = new MessageHandler(this, var24.getLooper());
                  this.mHandler = var3;
                  this.mCallback.setSessionImpl(this, var24);
                  return;
               } catch (Throwable var21) {
                  var10000 = var21;
                  var10001 = false;
                  break label211;
               }
            }

            while(true) {
               Throwable var25 = var10000;

               try {
                  throw var25;
               } catch (Throwable var20) {
                  var10000 = var20;
                  var10001 = false;
                  continue;
               }
            }
         }
      }

      public void setCaptioningEnabled(boolean var1) {
         if (this.mCaptioningEnabled != var1) {
            this.mCaptioningEnabled = var1;
            this.sendCaptioningEnabled(var1);
         }

      }

      public void setCurrentControllerInfo(MediaSessionManager.RemoteUserInfo param1) {
         // $FF: Couldn't be decompiled
      }

      public void setExtras(Bundle var1) {
         this.mExtras = var1;
         this.sendExtras(var1);
      }

      public void setFlags(int param1) {
         // $FF: Couldn't be decompiled
      }

      public void setMediaButtonReceiver(PendingIntent var1) {
      }

      public void setMetadata(MediaMetadataCompat param1) {
         // $FF: Couldn't be decompiled
      }

      public void setPlaybackState(PlaybackStateCompat param1) {
         // $FF: Couldn't be decompiled
      }

      public void setPlaybackToLocal(int var1) {
         VolumeProviderCompat var3 = this.mVolumeProvider;
         if (var3 != null) {
            var3.setCallback((VolumeProviderCompat.Callback)null);
         }

         this.mLocalStream = var1;
         this.mVolumeType = 1;
         var1 = this.mVolumeType;
         int var2 = this.mLocalStream;
         this.sendVolumeInfoChanged(new ParcelableVolumeInfo(var1, var2, 2, this.mAudioManager.getStreamMaxVolume(var2), this.mAudioManager.getStreamVolume(this.mLocalStream)));
      }

      public void setPlaybackToRemote(VolumeProviderCompat var1) {
         if (var1 != null) {
            VolumeProviderCompat var2 = this.mVolumeProvider;
            if (var2 != null) {
               var2.setCallback((VolumeProviderCompat.Callback)null);
            }

            this.mVolumeType = 2;
            this.mVolumeProvider = var1;
            this.sendVolumeInfoChanged(new ParcelableVolumeInfo(this.mVolumeType, this.mLocalStream, this.mVolumeProvider.getVolumeControl(), this.mVolumeProvider.getMaxVolume(), this.mVolumeProvider.getCurrentVolume()));
            var1.setCallback(this.mVolumeCallback);
         } else {
            throw new IllegalArgumentException("volumeProvider may not be null");
         }
      }

      public void setQueue(List var1) {
         this.mQueue = var1;
         this.sendQueue(var1);
      }

      public void setQueueTitle(CharSequence var1) {
         this.mQueueTitle = var1;
         this.sendQueueTitle(var1);
      }

      public void setRatingType(int var1) {
         this.mRatingType = var1;
      }

      void setRccState(PlaybackStateCompat var1) {
         this.mRcc.setPlaybackState(this.getRccStateFromState(var1.getState()));
      }

      public void setRepeatMode(int var1) {
         if (this.mRepeatMode != var1) {
            this.mRepeatMode = var1;
            this.sendRepeatMode(var1);
         }

      }

      public void setSessionActivity(PendingIntent param1) {
         // $FF: Couldn't be decompiled
      }

      public void setShuffleMode(int var1) {
         if (this.mShuffleMode != var1) {
            this.mShuffleMode = var1;
            this.sendShuffleMode(var1);
         }

      }

      void setVolumeTo(int var1, int var2) {
         if (this.mVolumeType == 2) {
            VolumeProviderCompat var3 = this.mVolumeProvider;
            if (var3 != null) {
               var3.onSetVolumeTo(var1);
            }
         } else {
            this.mAudioManager.setStreamVolume(this.mLocalStream, var1, var2);
         }

      }

      void unregisterMediaButtonEventReceiver(PendingIntent var1, ComponentName var2) {
         this.mAudioManager.unregisterMediaButtonEventReceiver(var2);
      }

      boolean update() {
         boolean var2 = this.mIsActive;
         boolean var1 = true;
         if (var2) {
            var2 = this.mIsMbrRegistered;
            if (!var2 && (this.mFlags & 1) != 0) {
               this.registerMediaButtonEventReceiver(this.mMediaButtonReceiverIntent, this.mMediaButtonReceiverComponentName);
               this.mIsMbrRegistered = true;
            } else if (var2 && (this.mFlags & 1) == 0) {
               this.unregisterMediaButtonEventReceiver(this.mMediaButtonReceiverIntent, this.mMediaButtonReceiverComponentName);
               this.mIsMbrRegistered = false;
            }

            var2 = this.mIsRccRegistered;
            if (!var2 && (this.mFlags & 2) != 0) {
               this.mAudioManager.registerRemoteControlClient(this.mRcc);
               this.mIsRccRegistered = true;
               return var1;
            }

            if (var2 && (this.mFlags & 2) == 0) {
               this.mRcc.setPlaybackState(0);
               this.mAudioManager.unregisterRemoteControlClient(this.mRcc);
               this.mIsRccRegistered = false;
            }
         } else {
            if (this.mIsMbrRegistered) {
               this.unregisterMediaButtonEventReceiver(this.mMediaButtonReceiverIntent, this.mMediaButtonReceiverComponentName);
               this.mIsMbrRegistered = false;
            }

            if (this.mIsRccRegistered) {
               this.mRcc.setPlaybackState(0);
               this.mAudioManager.unregisterRemoteControlClient(this.mRcc);
               this.mIsRccRegistered = false;
            }
         }

         var1 = false;
         return var1;
      }

      private static final class Command {
         public final String command;
         public final Bundle extras;
         public final ResultReceiver stub;

         public Command(String var1, Bundle var2, ResultReceiver var3) {
            this.command = var1;
            this.extras = var2;
            this.stub = var3;
         }
      }

      class MediaSessionStub extends IMediaSession.Stub {
         final MediaSessionImplBase this$0;

         MediaSessionStub(MediaSessionImplBase var1) {
            this.this$0 = var1;
         }

         public void addQueueItem(MediaDescriptionCompat var1) {
            this.postToHandler(25, var1);
         }

         public void addQueueItemAt(MediaDescriptionCompat var1, int var2) {
            this.postToHandler(26, var1, var2);
         }

         public void adjustVolume(int var1, int var2, String var3) {
            this.this$0.adjustVolume(var1, var2);
         }

         public void fastForward() throws RemoteException {
            this.postToHandler(16);
         }

         public Bundle getExtras() {
            // $FF: Couldn't be decompiled
         }

         public long getFlags() {
            // $FF: Couldn't be decompiled
         }

         public PendingIntent getLaunchPendingIntent() {
            // $FF: Couldn't be decompiled
         }

         public MediaMetadataCompat getMetadata() {
            return this.this$0.mMetadata;
         }

         public String getPackageName() {
            return this.this$0.mPackageName;
         }

         public PlaybackStateCompat getPlaybackState() {
            // $FF: Couldn't be decompiled
         }

         public List getQueue() {
            // $FF: Couldn't be decompiled
         }

         public CharSequence getQueueTitle() {
            return this.this$0.mQueueTitle;
         }

         public int getRatingType() {
            return this.this$0.mRatingType;
         }

         public int getRepeatMode() {
            return this.this$0.mRepeatMode;
         }

         public int getShuffleMode() {
            return this.this$0.mShuffleMode;
         }

         public String getTag() {
            return this.this$0.mTag;
         }

         public ParcelableVolumeInfo getVolumeAttributes() {
            Object var6 = this.this$0.mLock;
            synchronized(var6){}

            Throwable var10000;
            boolean var10001;
            label259: {
               int var4;
               int var5;
               VolumeProviderCompat var7;
               try {
                  var4 = this.this$0.mVolumeType;
                  var5 = this.this$0.mLocalStream;
                  var7 = this.this$0.mVolumeProvider;
               } catch (Throwable var37) {
                  var10000 = var37;
                  var10001 = false;
                  break label259;
               }

               int var3 = 2;
               int var1;
               int var2;
               if (var4 == 2) {
                  try {
                     var3 = var7.getVolumeControl();
                     var1 = var7.getMaxVolume();
                     var2 = var7.getCurrentVolume();
                  } catch (Throwable var36) {
                     var10000 = var36;
                     var10001 = false;
                     break label259;
                  }
               } else {
                  try {
                     var1 = this.this$0.mAudioManager.getStreamMaxVolume(var5);
                     var2 = this.this$0.mAudioManager.getStreamVolume(var5);
                  } catch (Throwable var35) {
                     var10000 = var35;
                     var10001 = false;
                     break label259;
                  }
               }

               label244:
               try {
                  return new ParcelableVolumeInfo(var4, var5, var3, var1, var2);
               } catch (Throwable var34) {
                  var10000 = var34;
                  var10001 = false;
                  break label244;
               }
            }

            while(true) {
               Throwable var38 = var10000;

               try {
                  throw var38;
               } catch (Throwable var33) {
                  var10000 = var33;
                  var10001 = false;
                  continue;
               }
            }
         }

         public boolean isCaptioningEnabled() {
            return this.this$0.mCaptioningEnabled;
         }

         public boolean isShuffleModeEnabledRemoved() {
            return false;
         }

         public boolean isTransportControlEnabled() {
            boolean var1;
            if ((this.this$0.mFlags & 2) != 0) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         public void next() throws RemoteException {
            this.postToHandler(14);
         }

         public void pause() throws RemoteException {
            this.postToHandler(12);
         }

         public void play() throws RemoteException {
            this.postToHandler(7);
         }

         public void playFromMediaId(String var1, Bundle var2) throws RemoteException {
            this.postToHandler(8, var1, var2);
         }

         public void playFromSearch(String var1, Bundle var2) throws RemoteException {
            this.postToHandler(9, var1, var2);
         }

         public void playFromUri(Uri var1, Bundle var2) throws RemoteException {
            this.postToHandler(10, var1, var2);
         }

         void postToHandler(int var1) {
            this.this$0.postToHandler(var1, 0, 0, (Object)null, (Bundle)null);
         }

         void postToHandler(int var1, int var2) {
            this.this$0.postToHandler(var1, var2, 0, (Object)null, (Bundle)null);
         }

         void postToHandler(int var1, Object var2) {
            this.this$0.postToHandler(var1, 0, 0, var2, (Bundle)null);
         }

         void postToHandler(int var1, Object var2, int var3) {
            this.this$0.postToHandler(var1, var3, 0, var2, (Bundle)null);
         }

         void postToHandler(int var1, Object var2, Bundle var3) {
            this.this$0.postToHandler(var1, 0, 0, var2, var3);
         }

         public void prepare() throws RemoteException {
            this.postToHandler(3);
         }

         public void prepareFromMediaId(String var1, Bundle var2) throws RemoteException {
            this.postToHandler(4, var1, var2);
         }

         public void prepareFromSearch(String var1, Bundle var2) throws RemoteException {
            this.postToHandler(5, var1, var2);
         }

         public void prepareFromUri(Uri var1, Bundle var2) throws RemoteException {
            this.postToHandler(6, var1, var2);
         }

         public void previous() throws RemoteException {
            this.postToHandler(15);
         }

         public void rate(RatingCompat var1) throws RemoteException {
            this.postToHandler(19, var1);
         }

         public void rateWithExtras(RatingCompat var1, Bundle var2) throws RemoteException {
            this.postToHandler(31, var1, var2);
         }

         public void registerCallbackListener(IMediaControllerCallback var1) {
            if (this.this$0.mDestroyed) {
               try {
                  var1.onSessionDestroyed();
               } catch (Exception var3) {
               }

            } else {
               MediaSessionManager.RemoteUserInfo var2 = new MediaSessionManager.RemoteUserInfo("android.media.session.MediaController", getCallingPid(), getCallingUid());
               this.this$0.mControllerCallbacks.register(var1, var2);
            }
         }

         public void removeQueueItem(MediaDescriptionCompat var1) {
            this.postToHandler(27, var1);
         }

         public void removeQueueItemAt(int var1) {
            this.postToHandler(28, var1);
         }

         public void rewind() throws RemoteException {
            this.postToHandler(17);
         }

         public void seekTo(long var1) throws RemoteException {
            this.postToHandler(18, var1);
         }

         public void sendCommand(String var1, Bundle var2, ResultReceiverWrapper var3) {
            this.postToHandler(1, new Command(var1, var2, var3.mResultReceiver));
         }

         public void sendCustomAction(String var1, Bundle var2) throws RemoteException {
            this.postToHandler(20, var1, var2);
         }

         public boolean sendMediaButton(KeyEvent var1) {
            int var2 = this.this$0.mFlags;
            boolean var3 = true;
            if ((var2 & 1) == 0) {
               var3 = false;
            }

            if (var3) {
               this.postToHandler(21, var1);
            }

            return var3;
         }

         public void setCaptioningEnabled(boolean var1) throws RemoteException {
            this.postToHandler(29, var1);
         }

         public void setRepeatMode(int var1) throws RemoteException {
            this.postToHandler(23, var1);
         }

         public void setShuffleMode(int var1) throws RemoteException {
            this.postToHandler(30, var1);
         }

         public void setShuffleModeEnabledRemoved(boolean var1) throws RemoteException {
         }

         public void setVolumeTo(int var1, int var2, String var3) {
            this.this$0.setVolumeTo(var1, var2);
         }

         public void skipToQueueItem(long var1) {
            this.postToHandler(11, var1);
         }

         public void stop() throws RemoteException {
            this.postToHandler(13);
         }

         public void unregisterCallbackListener(IMediaControllerCallback var1) {
            this.this$0.mControllerCallbacks.unregister(var1);
         }
      }

      class MessageHandler extends Handler {
         private static final int KEYCODE_MEDIA_PAUSE = 127;
         private static final int KEYCODE_MEDIA_PLAY = 126;
         private static final int MSG_ADD_QUEUE_ITEM = 25;
         private static final int MSG_ADD_QUEUE_ITEM_AT = 26;
         private static final int MSG_ADJUST_VOLUME = 2;
         private static final int MSG_COMMAND = 1;
         private static final int MSG_CUSTOM_ACTION = 20;
         private static final int MSG_FAST_FORWARD = 16;
         private static final int MSG_MEDIA_BUTTON = 21;
         private static final int MSG_NEXT = 14;
         private static final int MSG_PAUSE = 12;
         private static final int MSG_PLAY = 7;
         private static final int MSG_PLAY_MEDIA_ID = 8;
         private static final int MSG_PLAY_SEARCH = 9;
         private static final int MSG_PLAY_URI = 10;
         private static final int MSG_PREPARE = 3;
         private static final int MSG_PREPARE_MEDIA_ID = 4;
         private static final int MSG_PREPARE_SEARCH = 5;
         private static final int MSG_PREPARE_URI = 6;
         private static final int MSG_PREVIOUS = 15;
         private static final int MSG_RATE = 19;
         private static final int MSG_RATE_EXTRA = 31;
         private static final int MSG_REMOVE_QUEUE_ITEM = 27;
         private static final int MSG_REMOVE_QUEUE_ITEM_AT = 28;
         private static final int MSG_REWIND = 17;
         private static final int MSG_SEEK_TO = 18;
         private static final int MSG_SET_CAPTIONING_ENABLED = 29;
         private static final int MSG_SET_REPEAT_MODE = 23;
         private static final int MSG_SET_SHUFFLE_MODE = 30;
         private static final int MSG_SET_VOLUME = 22;
         private static final int MSG_SKIP_TO_ITEM = 11;
         private static final int MSG_STOP = 13;
         final MediaSessionImplBase this$0;

         public MessageHandler(MediaSessionImplBase var1, Looper var2) {
            super(var2);
            this.this$0 = var1;
         }

         private void onMediaButtonEvent(KeyEvent var1, Callback var2) {
            if (var1 != null && var1.getAction() == 0) {
               long var4;
               if (this.this$0.mState == null) {
                  var4 = 0L;
               } else {
                  var4 = this.this$0.mState.getActions();
               }

               int var3 = var1.getKeyCode();
               if (var3 != 79) {
                  if (var3 == 126) {
                     if ((var4 & 4L) != 0L) {
                        var2.onPlay();
                     }

                     return;
                  }

                  if (var3 == 127) {
                     if ((var4 & 2L) != 0L) {
                        var2.onPause();
                     }

                     return;
                  }

                  switch (var3) {
                     case 85:
                        break;
                     case 86:
                        if ((var4 & 1L) != 0L) {
                           var2.onStop();
                        }

                        return;
                     case 87:
                        if ((var4 & 32L) != 0L) {
                           var2.onSkipToNext();
                        }

                        return;
                     case 88:
                        if ((var4 & 16L) != 0L) {
                           var2.onSkipToPrevious();
                        }

                        return;
                     case 89:
                        if ((var4 & 8L) != 0L) {
                           var2.onRewind();
                        }

                        return;
                     case 90:
                        if ((var4 & 64L) != 0L) {
                           var2.onFastForward();
                        }

                        return;
                     default:
                        return;
                  }
               }

               Log.w("MediaSessionCompat", "KEYCODE_MEDIA_PLAY_PAUSE and KEYCODE_HEADSETHOOK are handled already");
            }

         }

         public void handleMessage(Message var1) {
            Callback var2 = this.this$0.mCallback;
            if (var2 != null) {
               Bundle var3 = var1.getData();
               MediaSessionCompat.ensureClassLoader(var3);
               this.this$0.setCurrentControllerInfo(new MediaSessionManager.RemoteUserInfo(var3.getString("data_calling_pkg"), var3.getInt("data_calling_pid"), var3.getInt("data_calling_uid")));
               var3 = var3.getBundle("data_extras");
               MediaSessionCompat.ensureClassLoader(var3);

               label9495: {
                  Throwable var10000;
                  label9494: {
                     boolean var10001;
                     label9493: {
                        label9492: {
                           label9491: {
                              label9490: {
                                 label9489: {
                                    label9488: {
                                       label9487: {
                                          label9486: {
                                             label9485: {
                                                label9484: {
                                                   label9483: {
                                                      label9482: {
                                                         label9481: {
                                                            label9480: {
                                                               label9479: {
                                                                  label9501: {
                                                                     label9477: {
                                                                        label9476: {
                                                                           label9475: {
                                                                              label9474: {
                                                                                 label9473: {
                                                                                    label9472: {
                                                                                       label9471: {
                                                                                          label9470: {
                                                                                             label9469: {
                                                                                                label9468: {
                                                                                                   label9467: {
                                                                                                      label9466: {
                                                                                                         label9465: {
                                                                                                            try {
                                                                                                               switch (var1.what) {
                                                                                                                  case 1:
                                                                                                                     break label9501;
                                                                                                                  case 2:
                                                                                                                     break label9480;
                                                                                                                  case 3:
                                                                                                                     break label9482;
                                                                                                                  case 4:
                                                                                                                     break label9484;
                                                                                                                  case 5:
                                                                                                                     break label9485;
                                                                                                                  case 6:
                                                                                                                     break label9486;
                                                                                                                  case 7:
                                                                                                                     break label9487;
                                                                                                                  case 8:
                                                                                                                     break label9488;
                                                                                                                  case 9:
                                                                                                                     break label9489;
                                                                                                                  case 10:
                                                                                                                     break label9490;
                                                                                                                  case 11:
                                                                                                                     break label9491;
                                                                                                                  case 12:
                                                                                                                     break label9492;
                                                                                                                  case 13:
                                                                                                                     break label9493;
                                                                                                                  case 14:
                                                                                                                     break;
                                                                                                                  case 15:
                                                                                                                     break label9465;
                                                                                                                  case 16:
                                                                                                                     break label9466;
                                                                                                                  case 17:
                                                                                                                     break label9467;
                                                                                                                  case 18:
                                                                                                                     break label9468;
                                                                                                                  case 19:
                                                                                                                     break label9469;
                                                                                                                  case 20:
                                                                                                                     break label9470;
                                                                                                                  case 21:
                                                                                                                     break label9471;
                                                                                                                  case 22:
                                                                                                                     break label9472;
                                                                                                                  case 23:
                                                                                                                     break label9473;
                                                                                                                  case 24:
                                                                                                                  default:
                                                                                                                     break label9495;
                                                                                                                  case 25:
                                                                                                                     break label9474;
                                                                                                                  case 26:
                                                                                                                     break label9475;
                                                                                                                  case 27:
                                                                                                                     break label9476;
                                                                                                                  case 28:
                                                                                                                     break label9477;
                                                                                                                  case 29:
                                                                                                                     break label9479;
                                                                                                                  case 30:
                                                                                                                     break label9481;
                                                                                                                  case 31:
                                                                                                                     break label9483;
                                                                                                               }
                                                                                                            } catch (Throwable var1059) {
                                                                                                               var10000 = var1059;
                                                                                                               var10001 = false;
                                                                                                               break label9494;
                                                                                                            }

                                                                                                            try {
                                                                                                               var2.onSkipToNext();
                                                                                                               break label9495;
                                                                                                            } catch (Throwable var1045) {
                                                                                                               var10000 = var1045;
                                                                                                               var10001 = false;
                                                                                                               break label9494;
                                                                                                            }
                                                                                                         }

                                                                                                         try {
                                                                                                            var2.onSkipToPrevious();
                                                                                                            break label9495;
                                                                                                         } catch (Throwable var1044) {
                                                                                                            var10000 = var1044;
                                                                                                            var10001 = false;
                                                                                                            break label9494;
                                                                                                         }
                                                                                                      }

                                                                                                      try {
                                                                                                         var2.onFastForward();
                                                                                                         break label9495;
                                                                                                      } catch (Throwable var1043) {
                                                                                                         var10000 = var1043;
                                                                                                         var10001 = false;
                                                                                                         break label9494;
                                                                                                      }
                                                                                                   }

                                                                                                   try {
                                                                                                      var2.onRewind();
                                                                                                      break label9495;
                                                                                                   } catch (Throwable var1042) {
                                                                                                      var10000 = var1042;
                                                                                                      var10001 = false;
                                                                                                      break label9494;
                                                                                                   }
                                                                                                }

                                                                                                try {
                                                                                                   var2.onSeekTo((Long)var1.obj);
                                                                                                   break label9495;
                                                                                                } catch (Throwable var1041) {
                                                                                                   var10000 = var1041;
                                                                                                   var10001 = false;
                                                                                                   break label9494;
                                                                                                }
                                                                                             }

                                                                                             try {
                                                                                                var2.onSetRating((RatingCompat)var1.obj);
                                                                                                break label9495;
                                                                                             } catch (Throwable var1040) {
                                                                                                var10000 = var1040;
                                                                                                var10001 = false;
                                                                                                break label9494;
                                                                                             }
                                                                                          }

                                                                                          try {
                                                                                             var2.onCustomAction((String)var1.obj, var3);
                                                                                             break label9495;
                                                                                          } catch (Throwable var1039) {
                                                                                             var10000 = var1039;
                                                                                             var10001 = false;
                                                                                             break label9494;
                                                                                          }
                                                                                       }

                                                                                       try {
                                                                                          KeyEvent var1064 = (KeyEvent)var1.obj;
                                                                                          Intent var1061 = new Intent("android.intent.action.MEDIA_BUTTON");
                                                                                          var1061.putExtra("android.intent.extra.KEY_EVENT", var1064);
                                                                                          if (!var2.onMediaButtonEvent(var1061)) {
                                                                                             this.onMediaButtonEvent(var1064, var2);
                                                                                          }
                                                                                          break label9495;
                                                                                       } catch (Throwable var1038) {
                                                                                          var10000 = var1038;
                                                                                          var10001 = false;
                                                                                          break label9494;
                                                                                       }
                                                                                    }

                                                                                    try {
                                                                                       this.this$0.setVolumeTo(var1.arg1, 0);
                                                                                       break label9495;
                                                                                    } catch (Throwable var1037) {
                                                                                       var10000 = var1037;
                                                                                       var10001 = false;
                                                                                       break label9494;
                                                                                    }
                                                                                 }

                                                                                 try {
                                                                                    var2.onSetRepeatMode(var1.arg1);
                                                                                    break label9495;
                                                                                 } catch (Throwable var1036) {
                                                                                    var10000 = var1036;
                                                                                    var10001 = false;
                                                                                    break label9494;
                                                                                 }
                                                                              }

                                                                              try {
                                                                                 var2.onAddQueueItem((MediaDescriptionCompat)var1.obj);
                                                                                 break label9495;
                                                                              } catch (Throwable var1035) {
                                                                                 var10000 = var1035;
                                                                                 var10001 = false;
                                                                                 break label9494;
                                                                              }
                                                                           }

                                                                           try {
                                                                              var2.onAddQueueItem((MediaDescriptionCompat)var1.obj, var1.arg1);
                                                                              break label9495;
                                                                           } catch (Throwable var1034) {
                                                                              var10000 = var1034;
                                                                              var10001 = false;
                                                                              break label9494;
                                                                           }
                                                                        }

                                                                        try {
                                                                           var2.onRemoveQueueItem((MediaDescriptionCompat)var1.obj);
                                                                           break label9495;
                                                                        } catch (Throwable var1033) {
                                                                           var10000 = var1033;
                                                                           var10001 = false;
                                                                           break label9494;
                                                                        }
                                                                     }

                                                                     QueueItem var1060;
                                                                     label9316: {
                                                                        try {
                                                                           if (this.this$0.mQueue == null) {
                                                                              break label9495;
                                                                           }

                                                                           if (var1.arg1 >= 0 && var1.arg1 < this.this$0.mQueue.size()) {
                                                                              var1060 = (QueueItem)this.this$0.mQueue.get(var1.arg1);
                                                                              break label9316;
                                                                           }
                                                                        } catch (Throwable var1032) {
                                                                           var10000 = var1032;
                                                                           var10001 = false;
                                                                           break label9494;
                                                                        }

                                                                        var1060 = null;
                                                                     }

                                                                     if (var1060 == null) {
                                                                        break label9495;
                                                                     }

                                                                     try {
                                                                        var2.onRemoveQueueItem(var1060.getDescription());
                                                                        break label9495;
                                                                     } catch (Throwable var1031) {
                                                                        var10000 = var1031;
                                                                        var10001 = false;
                                                                        break label9494;
                                                                     }
                                                                  }

                                                                  try {
                                                                     Command var1063 = (Command)var1.obj;
                                                                     var2.onCommand(var1063.command, var1063.extras, var1063.stub);
                                                                     break label9495;
                                                                  } catch (Throwable var1058) {
                                                                     var10000 = var1058;
                                                                     var10001 = false;
                                                                     break label9494;
                                                                  }
                                                               }

                                                               try {
                                                                  var2.onSetCaptioningEnabled((Boolean)var1.obj);
                                                                  break label9495;
                                                               } catch (Throwable var1030) {
                                                                  var10000 = var1030;
                                                                  var10001 = false;
                                                                  break label9494;
                                                               }
                                                            }

                                                            try {
                                                               this.this$0.adjustVolume(var1.arg1, 0);
                                                               break label9495;
                                                            } catch (Throwable var1057) {
                                                               var10000 = var1057;
                                                               var10001 = false;
                                                               break label9494;
                                                            }
                                                         }

                                                         try {
                                                            var2.onSetShuffleMode(var1.arg1);
                                                            break label9495;
                                                         } catch (Throwable var1029) {
                                                            var10000 = var1029;
                                                            var10001 = false;
                                                            break label9494;
                                                         }
                                                      }

                                                      try {
                                                         var2.onPrepare();
                                                         break label9495;
                                                      } catch (Throwable var1056) {
                                                         var10000 = var1056;
                                                         var10001 = false;
                                                         break label9494;
                                                      }
                                                   }

                                                   try {
                                                      var2.onSetRating((RatingCompat)var1.obj, var3);
                                                      break label9495;
                                                   } catch (Throwable var1028) {
                                                      var10000 = var1028;
                                                      var10001 = false;
                                                      break label9494;
                                                   }
                                                }

                                                try {
                                                   var2.onPrepareFromMediaId((String)var1.obj, var3);
                                                   break label9495;
                                                } catch (Throwable var1055) {
                                                   var10000 = var1055;
                                                   var10001 = false;
                                                   break label9494;
                                                }
                                             }

                                             try {
                                                var2.onPrepareFromSearch((String)var1.obj, var3);
                                                break label9495;
                                             } catch (Throwable var1054) {
                                                var10000 = var1054;
                                                var10001 = false;
                                                break label9494;
                                             }
                                          }

                                          try {
                                             var2.onPrepareFromUri((Uri)var1.obj, var3);
                                             break label9495;
                                          } catch (Throwable var1053) {
                                             var10000 = var1053;
                                             var10001 = false;
                                             break label9494;
                                          }
                                       }

                                       try {
                                          var2.onPlay();
                                          break label9495;
                                       } catch (Throwable var1052) {
                                          var10000 = var1052;
                                          var10001 = false;
                                          break label9494;
                                       }
                                    }

                                    try {
                                       var2.onPlayFromMediaId((String)var1.obj, var3);
                                       break label9495;
                                    } catch (Throwable var1051) {
                                       var10000 = var1051;
                                       var10001 = false;
                                       break label9494;
                                    }
                                 }

                                 try {
                                    var2.onPlayFromSearch((String)var1.obj, var3);
                                    break label9495;
                                 } catch (Throwable var1050) {
                                    var10000 = var1050;
                                    var10001 = false;
                                    break label9494;
                                 }
                              }

                              try {
                                 var2.onPlayFromUri((Uri)var1.obj, var3);
                                 break label9495;
                              } catch (Throwable var1049) {
                                 var10000 = var1049;
                                 var10001 = false;
                                 break label9494;
                              }
                           }

                           try {
                              var2.onSkipToQueueItem((Long)var1.obj);
                              break label9495;
                           } catch (Throwable var1048) {
                              var10000 = var1048;
                              var10001 = false;
                              break label9494;
                           }
                        }

                        try {
                           var2.onPause();
                           break label9495;
                        } catch (Throwable var1047) {
                           var10000 = var1047;
                           var10001 = false;
                           break label9494;
                        }
                     }

                     label9345:
                     try {
                        var2.onStop();
                        break label9495;
                     } catch (Throwable var1046) {
                        var10000 = var1046;
                        var10001 = false;
                        break label9345;
                     }
                  }

                  Throwable var1062 = var10000;
                  this.this$0.setCurrentControllerInfo((MediaSessionManager.RemoteUserInfo)null);
                  throw var1062;
               }

               this.this$0.setCurrentControllerInfo((MediaSessionManager.RemoteUserInfo)null);
            }
         }
      }
   }

   public interface OnActiveChangeListener {
      void onActiveChanged();
   }

   public static final class QueueItem implements Parcelable {
      public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
         public QueueItem createFromParcel(Parcel var1) {
            return new QueueItem(var1);
         }

         public QueueItem[] newArray(int var1) {
            return new QueueItem[var1];
         }
      };
      public static final int UNKNOWN_ID = -1;
      private final MediaDescriptionCompat mDescription;
      private final long mId;
      private Object mItem;

      QueueItem(Parcel var1) {
         this.mDescription = (MediaDescriptionCompat)MediaDescriptionCompat.CREATOR.createFromParcel(var1);
         this.mId = var1.readLong();
      }

      public QueueItem(MediaDescriptionCompat var1, long var2) {
         this((Object)null, var1, var2);
      }

      private QueueItem(Object var1, MediaDescriptionCompat var2, long var3) {
         if (var2 != null) {
            if (var3 != -1L) {
               this.mDescription = var2;
               this.mId = var3;
               this.mItem = var1;
            } else {
               throw new IllegalArgumentException("Id cannot be QueueItem.UNKNOWN_ID");
            }
         } else {
            throw new IllegalArgumentException("Description cannot be null.");
         }
      }

      public static QueueItem fromQueueItem(Object var0) {
         return var0 != null && VERSION.SDK_INT >= 21 ? new QueueItem(var0, MediaDescriptionCompat.fromMediaDescription(MediaSessionCompatApi21.QueueItem.getDescription(var0)), MediaSessionCompatApi21.QueueItem.getQueueId(var0)) : null;
      }

      public static List fromQueueItemList(List var0) {
         if (var0 != null && VERSION.SDK_INT >= 21) {
            ArrayList var1 = new ArrayList();
            Iterator var2 = var0.iterator();

            while(var2.hasNext()) {
               var1.add(fromQueueItem(var2.next()));
            }

            return var1;
         } else {
            return null;
         }
      }

      public int describeContents() {
         return 0;
      }

      public MediaDescriptionCompat getDescription() {
         return this.mDescription;
      }

      public long getQueueId() {
         return this.mId;
      }

      public Object getQueueItem() {
         if (this.mItem == null && VERSION.SDK_INT >= 21) {
            Object var1 = MediaSessionCompatApi21.QueueItem.createItem(this.mDescription.getMediaDescription(), this.mId);
            this.mItem = var1;
            return var1;
         } else {
            return this.mItem;
         }
      }

      public String toString() {
         return "MediaSession.QueueItem {Description=" + this.mDescription + ", Id=" + this.mId + " }";
      }

      public void writeToParcel(Parcel var1, int var2) {
         this.mDescription.writeToParcel(var1, var2);
         var1.writeLong(this.mId);
      }
   }

   public static final class ResultReceiverWrapper implements Parcelable {
      public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
         public ResultReceiverWrapper createFromParcel(Parcel var1) {
            return new ResultReceiverWrapper(var1);
         }

         public ResultReceiverWrapper[] newArray(int var1) {
            return new ResultReceiverWrapper[var1];
         }
      };
      ResultReceiver mResultReceiver;

      ResultReceiverWrapper(Parcel var1) {
         this.mResultReceiver = (ResultReceiver)ResultReceiver.CREATOR.createFromParcel(var1);
      }

      public ResultReceiverWrapper(ResultReceiver var1) {
         this.mResultReceiver = var1;
      }

      public int describeContents() {
         return 0;
      }

      public void writeToParcel(Parcel var1, int var2) {
         this.mResultReceiver.writeToParcel(var1, var2);
      }
   }

   @Retention(RetentionPolicy.SOURCE)
   public @interface SessionFlags {
   }

   public static final class Token implements Parcelable {
      public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
         public Token createFromParcel(Parcel var1) {
            Object var2;
            if (VERSION.SDK_INT >= 21) {
               var2 = var1.readParcelable((ClassLoader)null);
            } else {
               var2 = var1.readStrongBinder();
            }

            return new Token(var2);
         }

         public Token[] newArray(int var1) {
            return new Token[var1];
         }
      };
      private IMediaSession mExtraBinder;
      private final Object mInner;
      private Bundle mSessionToken2Bundle;

      Token(Object var1) {
         this(var1, (IMediaSession)null, (Bundle)null);
      }

      Token(Object var1, IMediaSession var2) {
         this(var1, var2, (Bundle)null);
      }

      Token(Object var1, IMediaSession var2, Bundle var3) {
         this.mInner = var1;
         this.mExtraBinder = var2;
         this.mSessionToken2Bundle = var3;
      }

      public static Token fromBundle(Bundle var0) {
         Object var1 = null;
         if (var0 == null) {
            return null;
         } else {
            IMediaSession var3 = IMediaSession.Stub.asInterface(BundleCompat.getBinder(var0, "android.support.v4.media.session.EXTRA_BINDER"));
            Bundle var2 = var0.getBundle("android.support.v4.media.session.SESSION_TOKEN2_BUNDLE");
            Token var4 = (Token)var0.getParcelable("android.support.v4.media.session.TOKEN");
            if (var4 == null) {
               var4 = (Token)var1;
            } else {
               var4 = new Token(var4.mInner, var3, var2);
            }

            return var4;
         }
      }

      public static Token fromToken(Object var0) {
         return fromToken(var0, (IMediaSession)null);
      }

      public static Token fromToken(Object var0, IMediaSession var1) {
         return var0 != null && VERSION.SDK_INT >= 21 ? new Token(MediaSessionCompatApi21.verifyToken(var0), var1) : null;
      }

      public int describeContents() {
         return 0;
      }

      public boolean equals(Object var1) {
         boolean var2 = true;
         if (this == var1) {
            return true;
         } else if (!(var1 instanceof Token)) {
            return false;
         } else {
            Token var3 = (Token)var1;
            var1 = this.mInner;
            if (var1 == null) {
               if (var3.mInner != null) {
                  var2 = false;
               }

               return var2;
            } else {
               Object var4 = var3.mInner;
               return var4 == null ? false : var1.equals(var4);
            }
         }
      }

      public IMediaSession getExtraBinder() {
         return this.mExtraBinder;
      }

      public Bundle getSessionToken2Bundle() {
         return this.mSessionToken2Bundle;
      }

      public Object getToken() {
         return this.mInner;
      }

      public int hashCode() {
         Object var1 = this.mInner;
         return var1 == null ? 0 : var1.hashCode();
      }

      public void setExtraBinder(IMediaSession var1) {
         this.mExtraBinder = var1;
      }

      public void setSessionToken2Bundle(Bundle var1) {
         this.mSessionToken2Bundle = var1;
      }

      public Bundle toBundle() {
         Bundle var1 = new Bundle();
         var1.putParcelable("android.support.v4.media.session.TOKEN", this);
         IMediaSession var2 = this.mExtraBinder;
         if (var2 != null) {
            BundleCompat.putBinder(var1, "android.support.v4.media.session.EXTRA_BINDER", var2.asBinder());
         }

         Bundle var3 = this.mSessionToken2Bundle;
         if (var3 != null) {
            var1.putBundle("android.support.v4.media.session.SESSION_TOKEN2_BUNDLE", var3);
         }

         return var1;
      }

      public void writeToParcel(Parcel var1, int var2) {
         if (VERSION.SDK_INT >= 21) {
            var1.writeParcelable((Parcelable)this.mInner, var2);
         } else {
            var1.writeStrongBinder((IBinder)this.mInner);
         }

      }
   }
}
