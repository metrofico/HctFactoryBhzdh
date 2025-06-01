package com.hzbhd.ui.view.paged;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.View.MeasureSpec;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeInfo.AccessibilityAction;
import android.view.animation.Interpolator;
import com.hzbhd.util.LogUtil;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000Ü\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u001a\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010\u0015\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u001e\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0010\u0014\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u001d\n\u0002\u0018\u0002\n\u0002\b*\n\u0002\u0018\u0002\n\u0002\b\u0014\b\u0016\u0018\u0000 ë\u00022\u00020\u00012\u00020\u0002:\në\u0002ì\u0002í\u0002î\u0002ï\u0002B\u000f\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005B\u0017\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bB!\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bB)\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\f\u001a\u00020\n¢\u0006\u0002\u0010\rJ\u0013\u0010Å\u0001\u001a\u00030Æ\u00012\u0007\u0010Ç\u0001\u001a\u00020\u0017H\u0002J\u0014\u0010È\u0001\u001a\u00030Æ\u00012\b\u0010É\u0001\u001a\u00030Ê\u0001H\u0002J,\u0010Ë\u0001\u001a\u00030Æ\u00012\u000e\u0010Ì\u0001\u001a\t\u0012\u0004\u0012\u00020=0Í\u00012\u0007\u0010Î\u0001\u001a\u00020\n2\u0007\u0010Ï\u0001\u001a\u00020\nH\u0016J\u0013\u0010Ð\u0001\u001a\u00030Æ\u00012\t\u0010Ñ\u0001\u001a\u0004\u0018\u00010=J\n\u0010Ò\u0001\u001a\u00030Æ\u0001H\u0002J\n\u0010Ó\u0001\u001a\u00030Æ\u0001H\u0004J\u0013\u0010Ô\u0001\u001a\u00020\u00172\b\u0010Õ\u0001\u001a\u00030Ö\u0001H\u0014J\n\u0010×\u0001\u001a\u00030Æ\u0001H\u0016J\t\u0010Ø\u0001\u001a\u00020\u0017H\u0004J\u001f\u0010Ù\u0001\u001a\u00030Æ\u00012\b\u0010É\u0001\u001a\u00030Ê\u00012\t\b\u0002\u0010Ú\u0001\u001a\u000204H\u0004J\b\u0010Û\u0001\u001a\u00030Æ\u0001J\b\u0010Ü\u0001\u001a\u00030Æ\u0001J\u0014\u0010Ý\u0001\u001a\u00030Æ\u00012\b\u0010Þ\u0001\u001a\u00030ß\u0001H\u0014J\u0013\u0010à\u0001\u001a\u00020\u00172\b\u0010É\u0001\u001a\u00030Ê\u0001H\u0016J\u001b\u0010á\u0001\u001a\u00020\u00172\u0007\u0010â\u0001\u001a\u00020=2\u0007\u0010Î\u0001\u001a\u00020\nH\u0016J\u0012\u0010ã\u0001\u001a\u0002042\u0007\u0010ä\u0001\u001a\u000204H\u0002J\b\u0010å\u0001\u001a\u00030Æ\u0001J\b\u0010æ\u0001\u001a\u00030Æ\u0001J\b\u0010ç\u0001\u001a\u00030Æ\u0001J\u0013\u0010è\u0001\u001a\u00030Æ\u00012\u0007\u0010â\u0001\u001a\u00020=H\u0016J\n\u0010é\u0001\u001a\u00030Æ\u0001H\u0002J\n\u0010ê\u0001\u001a\u00030ë\u0001H\u0014J\u0012\u0010ì\u0001\u001a\u00030ë\u00012\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0014\u0010ì\u0001\u001a\u00030Ö\u00012\b\u0010Õ\u0001\u001a\u00030Ö\u0001H\u0014J\u0012\u0010í\u0001\u001a\u00020\n2\u0007\u0010î\u0001\u001a\u00020\nH\u0004J\u0013\u0010ï\u0001\u001a\u00030Æ\u00012\u0007\u0010ð\u0001\u001a\u00020{H\u0004J\u0010\u0010ñ\u0001\u001a\u00020\n2\u0007\u0010î\u0001\u001a\u00020\nJ\u0012\u0010ò\u0001\u001a\u0004\u0018\u00010=2\u0007\u0010î\u0001\u001a\u00020\nJ\u0012\u0010ó\u0001\u001a\u00020\n2\t\u0010ô\u0001\u001a\u0004\u0018\u00010=J\u0010\u0010õ\u0001\u001a\u00020\n2\u0007\u0010î\u0001\u001a\u00020\nJ$\u0010ö\u0001\u001a\u0002042\u0007\u0010÷\u0001\u001a\u00020\n2\u0007\u0010ô\u0001\u001a\u00020=2\u0007\u0010Ñ\u0001\u001a\u00020\nH\u0004J\u0013\u0010ø\u0001\u001a\u00030Æ\u00012\u0007\u0010ð\u0001\u001a\u00020{H\u0004J\u001b\u0010ù\u0001\u001a\u00020\u00172\u0007\u0010ú\u0001\u001a\u0002042\u0007\u0010û\u0001\u001a\u000204H\u0004J\u001b\u0010ü\u0001\u001a\u00020\u00172\u0007\u0010ú\u0001\u001a\u0002042\u0007\u0010û\u0001\u001a\u000204H\u0004J\u0012\u0010ý\u0001\u001a\u00020\n2\u0007\u0010î\u0001\u001a\u00020\nH\u0004J\n\u0010þ\u0001\u001a\u00030Æ\u0001H\u0004J\u0010\u0010ÿ\u0001\u001a\u00020\u00172\u0007\u0010\u0080\u0002\u001a\u00020\u0017J\u001b\u0010\u0081\u0002\u001a\u00020\u00172\u0007\u0010ú\u0001\u001a\u00020\n2\u0007\u0010û\u0001\u001a\u00020\nH\u0002J%\u0010\u0082\u0002\u001a\u00030\u0083\u00022\u0007\u0010ô\u0001\u001a\u00020=2\u0007\u0010ú\u0001\u001a\u0002042\u0007\u0010û\u0001\u001a\u000204H\u0002J%\u0010\u0084\u0002\u001a\u00030\u0083\u00022\u0007\u0010ô\u0001\u001a\u00020=2\u0007\u0010ú\u0001\u001a\u0002042\u0007\u0010û\u0001\u001a\u000204H\u0002J\n\u0010\u0085\u0002\u001a\u00030Æ\u0001H\u0004J\n\u0010\u0086\u0002\u001a\u00030Æ\u0001H\u0014J\u001c\u0010\u0087\u0002\u001a\u00030Æ\u00012\u0007\u0010\u0088\u0002\u001a\u00020=2\u0007\u0010\u0089\u0002\u001a\u00020=H\u0016J\u001c\u0010\u008a\u0002\u001a\u00030Æ\u00012\u0007\u0010\u0088\u0002\u001a\u00020=2\u0007\u0010\u0089\u0002\u001a\u00020=H\u0016J\b\u0010\u008b\u0002\u001a\u00030Æ\u0001J\u0013\u0010\u008c\u0002\u001a\u00020\u00172\b\u0010\u008d\u0002\u001a\u00030Ê\u0001H\u0016J\u0013\u0010\u008e\u0002\u001a\u00020\u00172\b\u0010\u008d\u0002\u001a\u00030Ê\u0001H\u0016J\u0014\u0010\u008f\u0002\u001a\u00030Æ\u00012\b\u0010\u008d\u0002\u001a\u00030\u0090\u0002H\u0016J\u0014\u0010\u0091\u0002\u001a\u00030Æ\u00012\b\u0010\u0092\u0002\u001a\u00030\u0093\u0002H\u0017J\u0013\u0010\u0094\u0002\u001a\u00020\u00172\b\u0010É\u0001\u001a\u00030Ê\u0001H\u0016J7\u0010\u0095\u0002\u001a\u00030Æ\u00012\u0007\u0010\u0096\u0002\u001a\u00020\u00172\u0007\u0010\u0097\u0002\u001a\u00020\n2\u0007\u0010\u0098\u0002\u001a\u00020\n2\u0007\u0010\u0099\u0002\u001a\u00020\n2\u0007\u0010\u009a\u0002\u001a\u00020\nH\u0014J\u001c\u0010\u009b\u0002\u001a\u00030Æ\u00012\u0007\u0010\u009c\u0002\u001a\u00020\n2\u0007\u0010\u009d\u0002\u001a\u00020\nH\u0014J\n\u0010\u009e\u0002\u001a\u00030Æ\u0001H\u0014J\n\u0010\u009f\u0002\u001a\u00030Æ\u0001H\u0014J\b\u0010 \u0002\u001a\u00030Æ\u0001J\u001d\u0010¡\u0002\u001a\u00020\u00172\u0007\u0010Î\u0001\u001a\u00020\n2\t\u0010¢\u0002\u001a\u0004\u0018\u00010VH\u0014J\n\u0010£\u0002\u001a\u00030Æ\u0001H\u0004J\n\u0010¤\u0002\u001a\u00030Æ\u0001H\u0004J\u0014\u0010¥\u0002\u001a\u00030Æ\u00012\b\u0010É\u0001\u001a\u00030Ê\u0001H\u0002J\b\u0010¦\u0002\u001a\u00030Æ\u0001J\u0013\u0010§\u0002\u001a\u00020\u00172\b\u0010É\u0001\u001a\u00030Ê\u0001H\u0016J\u0016\u0010¨\u0002\u001a\u00030Æ\u00012\n\u0010É\u0001\u001a\u0005\u0018\u00010Ê\u0001H\u0004J\u001c\u0010©\u0002\u001a\u00030Æ\u00012\u0007\u0010ª\u0002\u001a\u00020=2\u0007\u0010«\u0002\u001a\u00020\nH\u0014J\n\u0010¬\u0002\u001a\u00030Æ\u0001H\u0004J\n\u0010\u00ad\u0002\u001a\u00030Æ\u0001H\u0004J\u001c\u0010®\u0002\u001a\u00020\u00172\u0007\u0010¯\u0002\u001a\u00020\n2\b\u0010°\u0002\u001a\u00030±\u0002H\u0016J\t\u0010²\u0002\u001a\u00020\u0017H\u0016J\n\u0010³\u0002\u001a\u00030Æ\u0001H\u0002J\n\u0010´\u0002\u001a\u00030Æ\u0001H\u0016J\u0013\u0010µ\u0002\u001a\u00030Æ\u00012\u0007\u0010î\u0001\u001a\u00020\nH\u0002J\u0013\u0010¶\u0002\u001a\u00030Æ\u00012\u0007\u0010ô\u0001\u001a\u00020=H\u0016J\u0013\u0010·\u0002\u001a\u00030Æ\u00012\u0007\u0010î\u0001\u001a\u00020\nH\u0016J\u0013\u0010¸\u0002\u001a\u00030Æ\u00012\u0007\u0010ô\u0001\u001a\u00020=H\u0016J\u001c\u0010¹\u0002\u001a\u00030Æ\u00012\u0007\u0010\u0089\u0002\u001a\u00020=2\u0007\u0010â\u0001\u001a\u00020=H\u0016J$\u0010º\u0002\u001a\u00020\u00172\u0007\u0010\u0089\u0002\u001a\u00020=2\u0007\u0010»\u0002\u001a\u00020V2\u0007\u0010¼\u0002\u001a\u00020\u0017H\u0016J\u0013\u0010½\u0002\u001a\u00030Æ\u00012\u0007\u0010¾\u0002\u001a\u00020\u0017H\u0016J\n\u0010¿\u0002\u001a\u00030Æ\u0001H\u0002J\u0013\u0010À\u0002\u001a\u00030Æ\u00012\u0007\u0010÷\u0001\u001a\u00020\nH\u0004J\u001c\u0010Á\u0002\u001a\u00030Æ\u00012\u0007\u0010ú\u0001\u001a\u00020\n2\u0007\u0010û\u0001\u001a\u00020\nH\u0016J\b\u0010Â\u0002\u001a\u00030Æ\u0001J\u0007\u0010Ã\u0002\u001a\u00020\u0017J\u001c\u0010Ä\u0002\u001a\u00030Æ\u00012\u0007\u0010ú\u0001\u001a\u00020\n2\u0007\u0010û\u0001\u001a\u00020\nH\u0016J\u0013\u0010Å\u0002\u001a\u00030Æ\u00012\u0007\u0010Æ\u0002\u001a\u00020\nH\u0016J\n\u0010Ç\u0002\u001a\u00030Æ\u0001H\u0002J\u0015\u0010È\u0002\u001a\u00030Æ\u00012\t\u0010É\u0002\u001a\u0004\u0018\u000102H\u0004J\u0013\u0010Ê\u0002\u001a\u00030Æ\u00012\u0007\u0010Ë\u0002\u001a\u00020\u0017H\u0002J\u0013\u0010Ì\u0002\u001a\u00030Æ\u00012\u0007\u0010Í\u0002\u001a\u00020\u0017H\u0004J\u0011\u0010Î\u0002\u001a\u00030Æ\u00012\u0007\u0010ä\u0001\u001a\u000204J\u0015\u0010Ï\u0002\u001a\u00030Æ\u00012\t\u0010Ð\u0002\u001a\u0004\u0018\u00010gH\u0016J\u0011\u0010Ñ\u0002\u001a\u00030Æ\u00012\u0007\u0010Ò\u0002\u001a\u00020\nJ\u0014\u0010Ó\u0002\u001a\u00030Æ\u00012\n\u0010Ô\u0002\u001a\u0005\u0018\u00010\u0080\u0001J\u0013\u0010Õ\u0002\u001a\u00030Æ\u00012\u0007\u0010Ö\u0002\u001a\u000204H\u0016J\u0012\u0010×\u0002\u001a\u00020\u00172\u0007\u0010\u0089\u0002\u001a\u00020=H\u0004J\n\u0010Ø\u0002\u001a\u00030Æ\u0001H\u0004J\u0011\u0010Ù\u0002\u001a\u00030Æ\u00012\u0007\u0010Ú\u0002\u001a\u00020\nJ(\u0010Ù\u0002\u001a\u00030Æ\u00012\u0007\u0010Ú\u0002\u001a\u00020\n2\u0007\u0010Û\u0002\u001a\u00020\n2\n\u0010É\u0002\u001a\u0005\u0018\u00010Ü\u0002H\u0004J5\u0010Ù\u0002\u001a\u00030Æ\u00012\u0007\u0010Ú\u0002\u001a\u00020\n2\u0007\u0010Û\u0002\u001a\u00020\n2\t\b\u0002\u0010¼\u0002\u001a\u00020\u00172\f\b\u0002\u0010É\u0002\u001a\u0005\u0018\u00010Ü\u0002H\u0004J>\u0010Ù\u0002\u001a\u00030Æ\u00012\u0007\u0010Ú\u0002\u001a\u00020\n2\u0007\u0010Ý\u0002\u001a\u00020\n2\u0007\u0010Û\u0002\u001a\u00020\n2\t\b\u0002\u0010¼\u0002\u001a\u00020\u00172\f\b\u0002\u0010É\u0002\u001a\u0005\u0018\u00010Ü\u0002H\u0004J\u0011\u0010Þ\u0002\u001a\u00030Æ\u00012\u0007\u0010Ú\u0002\u001a\u00020\nJ\u001c\u0010ß\u0002\u001a\u00030Æ\u00012\u0007\u0010Ú\u0002\u001a\u00020\n2\u0007\u0010à\u0002\u001a\u00020\nH\u0004J\u0012\u0010á\u0002\u001a\u00020\u00172\t\u0010ô\u0001\u001a\u0004\u0018\u00010=J\n\u0010â\u0002\u001a\u00030Æ\u0001H\u0004J\n\u0010ã\u0002\u001a\u00030Æ\u0001H\u0002J\b\u0010ä\u0002\u001a\u00030Æ\u0001J\b\u0010å\u0002\u001a\u00030Æ\u0001J\n\u0010æ\u0002\u001a\u00030Æ\u0001H\u0002J\u001c\u0010æ\u0002\u001a\u00030Æ\u00012\u0007\u0010ç\u0002\u001a\u00020\n2\u0007\u0010è\u0002\u001a\u00020\nH\u0014J\u0012\u0010é\u0002\u001a\u00020\n2\u0007\u0010ê\u0002\u001a\u00020\nH\u0002R\u000e\u0010\u000e\u001a\u00020\nX\u0082D¢\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\u00020\n8DX\u0084\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R$\u0010\u0012\u001a\u00020\n2\u0006\u0010\u0012\u001a\u00020\n8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u0013\u0010\u0011\"\u0004\b\u0014\u0010\u0015R\u001a\u0010\u0016\u001a\u00020\u0017X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u001a\u0010\u001b\u001a\u00020\nX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0011\"\u0004\b\u001d\u0010\u0015R\u001a\u0010\u001e\u001a\u00020\u0017X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u0018\"\u0004\b \u0010\u001aR\u000e\u0010!\u001a\u00020\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\"\u001a\u00020\nX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010\u0011\"\u0004\b$\u0010\u0015R\u001a\u0010%\u001a\u00020\nX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010\u0011\"\u0004\b'\u0010\u0015R\u001a\u0010(\u001a\u00020\u0017X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010\u0018\"\u0004\b*\u0010\u001aR\u001a\u0010+\u001a\u00020\nX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b,\u0010\u0011\"\u0004\b-\u0010\u0015R\u001a\u0010.\u001a\u00020\nX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b/\u0010\u0011\"\u0004\b0\u0010\u0015R\u0010\u00101\u001a\u0004\u0018\u000102X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u00103\u001a\u000204X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b5\u00106\"\u0004\b7\u00108R\u000e\u00109\u001a\u000204X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010:\u001a\u000204X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010;\u001a\u000204X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010<\u001a\u0004\u0018\u00010=X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b>\u0010?\"\u0004\b@\u0010AR\u000e\u0010B\u001a\u000204X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010C\u001a\u00020\u0017X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bD\u0010\u0018\"\u0004\bE\u0010\u001aR\u001a\u0010F\u001a\u00020\u0017X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bG\u0010\u0018\"\u0004\bH\u0010\u001aR\u001a\u0010I\u001a\u00020\nX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bJ\u0010\u0011\"\u0004\bK\u0010\u0015R\u001a\u0010L\u001a\u00020\u0017X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bM\u0010\u0018\"\u0004\bN\u0010\u001aR\u001a\u0010O\u001a\u00020\u0017X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bP\u0010\u0018\"\u0004\bQ\u0010\u001aR\u000e\u0010R\u001a\u00020\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010S\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010T\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010U\u001a\u00020VX\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\bW\u0010XR\u000e\u0010Y\u001a\u00020\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010Z\u001a\u00020\u0017X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b[\u0010\u0018R\u001a\u0010\\\u001a\u000204X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b]\u00106\"\u0004\b^\u00108R\u001a\u0010_\u001a\u000204X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b`\u00106\"\u0004\ba\u00108R\u001a\u0010b\u001a\u000204X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bc\u00106\"\u0004\bd\u00108R\u000e\u0010e\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010f\u001a\u0004\u0018\u00010gX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bh\u0010i\"\u0004\bj\u0010kR\u001a\u0010l\u001a\u00020\nX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bm\u0010\u0011\"\u0004\bn\u0010\u0015R\u000e\u0010o\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010p\u001a\u00020\nX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bq\u0010\u0011\"\u0004\br\u0010\u0015R\u000e\u0010s\u001a\u000204X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010t\u001a\u00020\nX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bu\u0010\u0011\"\u0004\bv\u0010\u0015R\u001a\u0010w\u001a\u00020\nX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bx\u0010\u0011\"\u0004\by\u0010\u0015R\u0010\u0010z\u001a\u0004\u0018\u00010{X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010|\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b}\u0010\u0011\"\u0004\b~\u0010\u0015R\u0011\u0010\u007f\u001a\u0005\u0018\u00010\u0080\u0001X\u0082\u000e¢\u0006\u0002\n\u0000R\u000f\u0010\u0081\u0001\u001a\u000204X\u0082\u000e¢\u0006\u0002\n\u0000R\u000f\u0010\u0082\u0001\u001a\u000204X\u0082\u000e¢\u0006\u0002\n\u0000R\u000f\u0010\u0083\u0001\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0084\u0001\u001a\u0005\u0018\u00010\u0085\u0001X\u0082\u000e¢\u0006\u0002\n\u0000R\u000f\u0010\u0086\u0001\u001a\u00020\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u000f\u0010\u0087\u0001\u001a\u00020\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\"\u0010\u0088\u0001\u001a\u0005\u0018\u00010\u0089\u0001X\u0084\u000e¢\u0006\u0012\n\u0000\u001a\u0006\b\u008a\u0001\u0010\u008b\u0001\"\u0006\b\u008c\u0001\u0010\u008d\u0001R\u001d\u0010\u008e\u0001\u001a\u00020\nX\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u008f\u0001\u0010\u0011\"\u0005\b\u0090\u0001\u0010\u0015R\u0012\u0010\u0091\u0001\u001a\u0005\u0018\u00010\u0085\u0001X\u0082\u000e¢\u0006\u0002\n\u0000R\u001d\u0010\u0092\u0001\u001a\u000204X\u0084\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0093\u0001\u00106\"\u0005\b\u0094\u0001\u00108R\u001f\u0010\u0095\u0001\u001a\u00020{X\u0084\u000e¢\u0006\u0012\n\u0000\u001a\u0006\b\u0096\u0001\u0010\u0097\u0001\"\u0006\b\u0098\u0001\u0010\u0099\u0001R\u001d\u0010\u009a\u0001\u001a\u000204X\u0084\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u009b\u0001\u00106\"\u0005\b\u009c\u0001\u00108R\u001d\u0010\u009d\u0001\u001a\u00020\nX\u0084\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u009e\u0001\u0010\u0011\"\u0005\b\u009f\u0001\u0010\u0015R\u001d\u0010 \u0001\u001a\u00020\nX\u0084\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b¡\u0001\u0010\u0011\"\u0005\b¢\u0001\u0010\u0015R\u001d\u0010£\u0001\u001a\u000204X\u0084\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b¤\u0001\u00106\"\u0005\b¥\u0001\u00108R\u000f\u0010¦\u0001\u001a\u00020\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010§\u0001\u001a\u0005\u0018\u00010¨\u0001X\u0082\u000e¢\u0006\u0002\n\u0000R\u000f\u0010©\u0001\u001a\u00020VX\u0082\u0004¢\u0006\u0002\n\u0000R\u000f\u0010ª\u0001\u001a\u00020\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010«\u0001\u001a\u00020\n8BX\u0082\u0004¢\u0006\u0007\u001a\u0005\b¬\u0001\u0010\u0011R\u0013\u0010\u00ad\u0001\u001a\u00020\n8F¢\u0006\u0007\u001a\u0005\b®\u0001\u0010\u0011R!\u0010°\u0001\u001a\u00020\n2\u0007\u0010¯\u0001\u001a\u00020\n@BX\u0086\u000e¢\u0006\t\n\u0000\u001a\u0005\b±\u0001\u0010\u0011R\u0013\u0010²\u0001\u001a\u00020\n8F¢\u0006\u0007\u001a\u0005\b³\u0001\u0010\u0011R\u001a\u0010´\u0001\u001a\u0005\u0018\u00010µ\u00018DX\u0084\u0004¢\u0006\b\u001a\u0006\b¶\u0001\u0010·\u0001R\u0013\u0010¸\u0001\u001a\u00020\n8F¢\u0006\u0007\u001a\u0005\b¹\u0001\u0010\u0011R\u001d\u0010º\u0001\u001a\u00020\nX\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b»\u0001\u0010\u0011\"\u0005\b¼\u0001\u0010\u0015R\u0013\u0010½\u0001\u001a\u00020\n8F¢\u0006\u0007\u001a\u0005\b¾\u0001\u0010\u0011R\u0013\u0010¿\u0001\u001a\u00020\n8F¢\u0006\u0007\u001a\u0005\bÀ\u0001\u0010\u0011R\u0013\u0010Á\u0001\u001a\u00020\n8F¢\u0006\u0007\u001a\u0005\bÂ\u0001\u0010\u0011R\u0013\u0010Ã\u0001\u001a\u00020\n8F¢\u0006\u0007\u001a\u0005\bÄ\u0001\u0010\u0011¨\u0006ð\u0002"},
   d2 = {"Lcom/hzbhd/ui/view/paged/PagedView;", "Landroid/view/ViewGroup;", "Landroid/view/ViewGroup$OnHierarchyChangeListener;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "defStyleRes", "(Landroid/content/Context;Landroid/util/AttributeSet;II)V", "NUM_ANIMATIONS_RUNNING_BEFORE_ZOOM_OUT", "childGap", "getChildGap", "()I", "currentPage", "getCurrentPage", "setCurrentPage", "(I)V", "isPageMoving", "", "()Z", "setPageMoving", "(Z)V", "mActivePointerId", "getMActivePointerId", "setMActivePointerId", "mAllowOverScroll", "getMAllowOverScroll", "setMAllowOverScroll", "mCancelTap", "mCellCountX", "getMCellCountX", "setMCellCountX", "mCellCountY", "getMCellCountY", "setMCellCountY", "mCenterPagesVertically", "getMCenterPagesVertically", "setMCenterPagesVertically", "mChildCountOnLastLayout", "getMChildCountOnLastLayout", "setMChildCountOnLastLayout", "mCurrentPage", "getMCurrentPage", "setMCurrentPage", "mDefaultInterpolator", "Landroid/view/animation/Interpolator;", "mDensity", "", "getMDensity", "()F", "setMDensity", "(F)V", "mDownMotionX", "mDownMotionY", "mDownScrollX", "mDragView", "Landroid/view/View;", "getMDragView", "()Landroid/view/View;", "setMDragView", "(Landroid/view/View;)V", "mDragViewBaselineLeft", "mFadeInAdjacentScreens", "getMFadeInAdjacentScreens", "setMFadeInAdjacentScreens", "mFirstLayout", "getMFirstLayout", "setMFirstLayout", "mFlingThresholdVelocity", "getMFlingThresholdVelocity", "setMFlingThresholdVelocity", "mForceDrawAllChildrenNextFrame", "getMForceDrawAllChildrenNextFrame", "setMForceDrawAllChildrenNextFrame", "mForceScreenScrolled", "getMForceScreenScrolled", "setMForceScreenScrolled", "mFreeScroll", "mFreeScrollMaxScrollX", "mFreeScrollMinScrollX", "mInsets", "Landroid/graphics/Rect;", "getMInsets", "()Landroid/graphics/Rect;", "mIsReordering", "mIsRtl", "getMIsRtl", "mLastMotionX", "getMLastMotionX", "setMLastMotionX", "mLastMotionXRemainder", "getMLastMotionXRemainder", "setMLastMotionXRemainder", "mLastMotionY", "getMLastMotionY", "setMLastMotionY", "mLastScreenCenter", "mLongClickListener", "Landroid/view/View$OnLongClickListener;", "getMLongClickListener", "()Landroid/view/View$OnLongClickListener;", "setMLongClickListener", "(Landroid/view/View$OnLongClickListener;)V", "mMaxScrollX", "getMMaxScrollX", "setMMaxScrollX", "mMaximumVelocity", "mMinFlingVelocity", "getMMinFlingVelocity", "setMMinFlingVelocity", "mMinScale", "mMinSnapVelocity", "getMMinSnapVelocity", "setMMinSnapVelocity", "mNextPage", "getMNextPage", "setMNextPage", "mPageScrolls", "", "mPageSpacing", "getMPageSpacing", "setMPageSpacing", "mPageSwitchListener", "Lcom/hzbhd/ui/view/paged/PagedView$PageSwitchListener;", "mParentDownMotionX", "mParentDownMotionY", "mPostReorderingPreZoomInRemainingAnimationCount", "mPostReorderingPreZoomInRunnable", "Ljava/lang/Runnable;", "mReorderingStarted", "mScrollAble", "mScroller", "Lcom/hzbhd/ui/view/paged/LauncherScroller;", "getMScroller", "()Lcom/hzbhd/ui/view/paged/LauncherScroller;", "setMScroller", "(Lcom/hzbhd/ui/view/paged/LauncherScroller;)V", "mSidePageHoverIndex", "getMSidePageHoverIndex", "setMSidePageHoverIndex", "mSidePageHoverRunnable", "mSmoothingTime", "getMSmoothingTime", "setMSmoothingTime", "mTempVisiblePagesRange", "getMTempVisiblePagesRange", "()[I", "setMTempVisiblePagesRange", "([I)V", "mTotalMotionX", "getMTotalMotionX", "setMTotalMotionX", "mTouchSlop", "getMTouchSlop", "setMTouchSlop", "mTouchState", "getMTouchState", "setMTouchState", "mTouchX", "getMTouchX", "setMTouchX", "mUseMinScale", "mVelocityTracker", "Landroid/view/VelocityTracker;", "mViewport", "mWasInOverscroll", "nearestHoverOverPageIndex", "getNearestHoverOverPageIndex", "nextPage", "getNextPage", "<set-?>", "normalChildHeight", "getNormalChildHeight", "pageCount", "getPageCount", "pageIndicatorClickListener", "Landroid/view/View$OnClickListener;", "getPageIndicatorClickListener", "()Landroid/view/View$OnClickListener;", "pageNearestToCenterOfScreen", "getPageNearestToCenterOfScreen", "restorePage", "getRestorePage", "setRestorePage", "viewportHeight", "getViewportHeight", "viewportOffsetX", "getViewportOffsetX", "viewportOffsetY", "getViewportOffsetY", "viewportWidth", "getViewportWidth", "abortScrollerAnimation", "", "resetNextPage", "acquireVelocityTrackerAndAddMovement", "ev", "Landroid/view/MotionEvent;", "addFocusables", "views", "Ljava/util/ArrayList;", "direction", "focusableMode", "addFullScreenPage", "page", "animateDragViewToOriginalPosition", "cancelCurrentPageLongPress", "checkLayoutParams", "p", "Landroid/view/ViewGroup$LayoutParams;", "computeScroll", "computeScrollHelper", "determineScrollingStart", "touchSlopScale", "didAbleScroll", "disableFreeScroll", "dispatchDraw", "canvas", "Landroid/graphics/Canvas;", "dispatchTouchEvent", "dispatchUnhandledMove", "focused", "distanceInfluenceForSnapDuration", "f", "enableFreeScroll", "enableScroll", "endReordering", "focusableViewAvailable", "forceFinishScroller", "generateDefaultLayoutParams", "Lcom/hzbhd/ui/view/paged/PagedView$LayoutParams;", "generateLayoutParams", "getChildOffset", "index", "getFreeScrollPageRange", "range", "getLayoutTransitionOffsetForPage", "getPageAt", "getPageForView", "v", "getScrollForPage", "getScrollProgress", "screenCenter", "getVisiblePages", "hitsNextPage", "x", "y", "hitsPreviousPage", "indexToPage", "init", "isReordering", "testTouchState", "isTouchPointInViewportWithBuffer", "mapPointFromParentToView", "", "mapPointFromViewToParent", "notifyPageSwitchListener", "onAttachedToWindow", "onChildViewAdded", "parent", "child", "onChildViewRemoved", "onEndReordering", "onGenericMotionEvent", "event", "onHoverEvent", "onInitializeAccessibilityEvent", "Landroid/view/accessibility/AccessibilityEvent;", "onInitializeAccessibilityNodeInfo", "info", "Landroid/view/accessibility/AccessibilityNodeInfo;", "onInterceptTouchEvent", "onLayout", "changed", "left", "top", "right", "bottom", "onMeasure", "widthMeasureSpec", "heightMeasureSpec", "onPageBeginMoving", "onPageEndMoving", "onPostReorderingAnimationCompleted", "onRequestFocusInDescendants", "previouslyFocusedRect", "onScrollInteractionBegin", "onScrollInteractionEnd", "onSecondaryPointerUp", "onStartReordering", "onTouchEvent", "onUnhandledTap", "onVisibilityChanged", "changedView", "visibility", "pageBeginMoving", "pageEndMoving", "performAccessibilityAction", "action", "arguments", "Landroid/os/Bundle;", "performLongClick", "releaseVelocityTracker", "removeAllViewsInLayout", "removeMarkerForView", "removeView", "removeViewAt", "removeViewInLayout", "requestChildFocus", "requestChildRectangleOnScreen", "rectangle", "immediate", "requestDisallowInterceptTouchEvent", "disallowIntercept", "resetTouchState", "screenScrolled", "scrollBy", "scrollLeft", "scrollRight", "scrollTo", "sendAccessibilityEvent", "eventType", "sendScrollAccessibilityEvent", "setDefaultInterpolator", "interpolator", "setEnableFreeScroll", "freeScroll", "setEnableOverscroll", "enable", "setMinScale", "setOnLongClickListener", "l", "setPageSpacing", "pageSpacing", "setPageSwitchListener", "pageSwitchListener", "setScaleX", "scaleX", "shouldDrawChild", "snapToDestination", "snapToPage", "whichPage", "duration", "Landroid/animation/TimeInterpolator;", "delta", "snapToPageImmediately", "snapToPageWithVelocity", "velocity", "startReordering", "updateCurrentPageScroll", "updateDragViewTranslationDuringDrag", "updateFreescrollBounds", "updateMaxScrollX", "updatePageIndicator", "curr", "max", "validateNewPage", "newPage", "Companion", "LayoutParams", "PageSwitchListener", "SavedState", "ScrollInterpolator", "commonview-base_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public class PagedView extends ViewGroup implements ViewGroup.OnHierarchyChangeListener {
   protected static final float ALPHA_QUANTIZE_LEVEL = 1.0E-4F;
   private static final int ANIM_TAG_KEY = 100;
   public static final int AUTOMATIC_PAGE_SPACING = -1;
   public static final Companion Companion = new Companion((DefaultConstructorMarker)null);
   private static final boolean DEBUG = false;
   private static final int FLING_THRESHOLD_VELOCITY = 500;
   protected static final int INVALID_PAGE = -1;
   protected static final int INVALID_POINTER = -1;
   public static final int INVALID_RESTORE_PAGE = -1001;
   private static final float MAX_SCROLL_PROGRESS = 1.0F;
   private static final int MIN_FLING_VELOCITY = 250;
   private static final int MIN_LENGTH_FOR_FLING = 25;
   private static final int MIN_SNAP_VELOCITY = 1500;
   protected static final float NANOTIME_DIV = 1.0E9F;
   protected static final int PAGE_SNAP_ANIMATION_DURATION = 300;
   private static final int REORDERING_DROP_REPOSITION_DURATION = 200;
   private static int REORDERING_REORDER_REPOSITION_DURATION = 300;
   private static final int REORDERING_SIDE_PAGE_HOVER_TIMEOUT = 80;
   private static final float RETURN_TO_ORIGINAL_PAGE_THRESHOLD = 0.33F;
   private static final float SIGNIFICANT_MOVE_THRESHOLD = 0.4F;
   protected static final int SLOW_PAGE_SNAP_ANIMATION_DURATION = 300;
   private static final String TAG = "PagedView";
   protected static final int TOUCH_STATE_NEXT_PAGE = 3;
   protected static final int TOUCH_STATE_PREV_PAGE = 2;
   protected static final int TOUCH_STATE_REORDERING = 4;
   protected static final int TOUCH_STATE_REST = 0;
   protected static final int TOUCH_STATE_SCROLLING = 1;
   private static final int[] sTmpIntPoint = new int[2];
   private static final Matrix sTmpInvMatrix = new Matrix();
   private static final float[] sTmpPoint = new float[2];
   private static final Rect sTmpRect = new Rect();
   private final int NUM_ANIMATIONS_RUNNING_BEFORE_ZOOM_OUT;
   private boolean isPageMoving;
   private int mActivePointerId;
   private boolean mAllowOverScroll;
   private boolean mCancelTap;
   private int mCellCountX;
   private int mCellCountY;
   private boolean mCenterPagesVertically;
   private int mChildCountOnLastLayout;
   private int mCurrentPage;
   private Interpolator mDefaultInterpolator;
   private float mDensity;
   private float mDownMotionX;
   private float mDownMotionY;
   private float mDownScrollX;
   private View mDragView;
   private float mDragViewBaselineLeft;
   private boolean mFadeInAdjacentScreens;
   private boolean mFirstLayout;
   private int mFlingThresholdVelocity;
   private boolean mForceDrawAllChildrenNextFrame;
   private boolean mForceScreenScrolled;
   private boolean mFreeScroll;
   private int mFreeScrollMaxScrollX;
   private int mFreeScrollMinScrollX;
   private final Rect mInsets;
   private boolean mIsReordering;
   private final boolean mIsRtl;
   private float mLastMotionX;
   private float mLastMotionXRemainder;
   private float mLastMotionY;
   private int mLastScreenCenter;
   private OnLongClickListener mLongClickListener;
   private int mMaxScrollX;
   private int mMaximumVelocity;
   private int mMinFlingVelocity;
   private float mMinScale;
   private int mMinSnapVelocity;
   private int mNextPage;
   private int[] mPageScrolls;
   private int mPageSpacing;
   private PageSwitchListener mPageSwitchListener;
   private float mParentDownMotionX;
   private float mParentDownMotionY;
   private int mPostReorderingPreZoomInRemainingAnimationCount;
   private Runnable mPostReorderingPreZoomInRunnable;
   private boolean mReorderingStarted;
   private boolean mScrollAble;
   private LauncherScroller mScroller;
   private int mSidePageHoverIndex;
   private Runnable mSidePageHoverRunnable;
   private float mSmoothingTime;
   private int[] mTempVisiblePagesRange;
   private float mTotalMotionX;
   private int mTouchSlop;
   private int mTouchState;
   private float mTouchX;
   private boolean mUseMinScale;
   private VelocityTracker mVelocityTracker;
   private final Rect mViewport;
   private boolean mWasInOverscroll;
   private int normalChildHeight;
   private int restorePage;

   // $FF: synthetic method
   public static void $r8$lambda$2ah2WgrF5JzDijWgMT2nr4vZiKk(PagedView var0) {
      endReordering$lambda_4(var0);
   }

   // $FF: synthetic method
   public static void $r8$lambda$9hJwaIxe_yGkJ3A5p_kNtfUigLk(Runnable var0, PagedView var1) {
      endReordering$lambda_5(var0, var1);
   }

   // $FF: synthetic method
   public static void $r8$lambda$Uy_MsRV8Ri9w_iLOaxJGUhGM5OI(PagedView var0, int var1, int var2) {
      onTouchEvent$lambda_3(var0, var1, var2);
   }

   public PagedView(Context var1) {
      Intrinsics.checkNotNullParameter(var1, "context");
      super(var1);
      this.mFreeScrollMinScrollX = -1;
      this.mFreeScrollMaxScrollX = -1;
      this.mFirstLayout = true;
      this.restorePage = -1001;
      this.mNextPage = -1;
      this.mLastScreenCenter = -1;
      this.mAllowOverScroll = true;
      this.mTempVisiblePagesRange = new int[2];
      this.mActivePointerId = -1;
      this.mViewport = new Rect();
      this.mMinScale = 1.0F;
      this.mSidePageHoverIndex = -1;
      this.NUM_ANIMATIONS_RUNNING_BEFORE_ZOOM_OUT = 2;
      this.mInsets = new Rect();
      this.setHapticFeedbackEnabled(false);
      this.mIsRtl = false;
      this.init();
   }

   public PagedView(Context var1, AttributeSet var2) {
      Intrinsics.checkNotNullParameter(var1, "context");
      Intrinsics.checkNotNullParameter(var2, "attrs");
      super(var1, var2);
      this.mFreeScrollMinScrollX = -1;
      this.mFreeScrollMaxScrollX = -1;
      this.mFirstLayout = true;
      this.restorePage = -1001;
      this.mNextPage = -1;
      this.mLastScreenCenter = -1;
      this.mAllowOverScroll = true;
      this.mTempVisiblePagesRange = new int[2];
      this.mActivePointerId = -1;
      this.mViewport = new Rect();
      this.mMinScale = 1.0F;
      this.mSidePageHoverIndex = -1;
      this.NUM_ANIMATIONS_RUNNING_BEFORE_ZOOM_OUT = 2;
      this.mInsets = new Rect();
      this.setHapticFeedbackEnabled(false);
      this.mIsRtl = false;
      this.init();
   }

   public PagedView(Context var1, AttributeSet var2, int var3) {
      Intrinsics.checkNotNullParameter(var1, "context");
      super(var1, var2, var3);
      this.mFreeScrollMinScrollX = -1;
      this.mFreeScrollMaxScrollX = -1;
      this.mFirstLayout = true;
      this.restorePage = -1001;
      this.mNextPage = -1;
      this.mLastScreenCenter = -1;
      this.mAllowOverScroll = true;
      this.mTempVisiblePagesRange = new int[2];
      this.mActivePointerId = -1;
      this.mViewport = new Rect();
      this.mMinScale = 1.0F;
      this.mSidePageHoverIndex = -1;
      this.NUM_ANIMATIONS_RUNNING_BEFORE_ZOOM_OUT = 2;
      this.mInsets = new Rect();
      this.setHapticFeedbackEnabled(false);
      this.mIsRtl = false;
      this.init();
   }

   public PagedView(Context var1, AttributeSet var2, int var3, int var4) {
      Intrinsics.checkNotNullParameter(var1, "context");
      super(var1, var2, var3, var4);
      this.mFreeScrollMinScrollX = -1;
      this.mFreeScrollMaxScrollX = -1;
      this.mFirstLayout = true;
      this.restorePage = -1001;
      this.mNextPage = -1;
      this.mLastScreenCenter = -1;
      this.mAllowOverScroll = true;
      this.mTempVisiblePagesRange = new int[2];
      this.mActivePointerId = -1;
      this.mViewport = new Rect();
      this.mMinScale = 1.0F;
      this.mSidePageHoverIndex = -1;
      this.NUM_ANIMATIONS_RUNNING_BEFORE_ZOOM_OUT = 2;
      this.mInsets = new Rect();
      this.setHapticFeedbackEnabled(false);
      this.mIsRtl = false;
      this.init();
   }

   private final void abortScrollerAnimation(boolean var1) {
      LauncherScroller var2 = this.mScroller;
      Intrinsics.checkNotNull(var2);
      var2.abortAnimation();
      if (var1) {
         this.mNextPage = -1;
      }

   }

   private final void acquireVelocityTrackerAndAddMovement(MotionEvent var1) {
      if (this.mVelocityTracker == null) {
         this.mVelocityTracker = VelocityTracker.obtain();
      }

      VelocityTracker var2 = this.mVelocityTracker;
      Intrinsics.checkNotNull(var2);
      var2.addMovement(var1);
   }

   private final void animateDragViewToOriginalPosition() {
      if (this.mDragView != null) {
         AnimatorSet var1 = new AnimatorSet();
         var1.setDuration(200L);
         var1.playTogether(new Animator[]{(Animator)ObjectAnimator.ofFloat(this.mDragView, "translationX", new float[]{0.0F}), (Animator)ObjectAnimator.ofFloat(this.mDragView, "translationY", new float[]{0.0F}), (Animator)ObjectAnimator.ofFloat(this.mDragView, "scaleX", new float[]{1.0F}), (Animator)ObjectAnimator.ofFloat(this.mDragView, "scaleY", new float[]{1.0F})});
         var1.addListener((Animator.AnimatorListener)(new AnimatorListenerAdapter(this) {
            final PagedView this$0;

            {
               this.this$0 = var1;
            }

            public void onAnimationEnd(Animator var1) {
               Intrinsics.checkNotNullParameter(var1, "animation");
               this.this$0.onPostReorderingAnimationCompleted();
            }
         }));
         var1.start();
      }

   }

   // $FF: synthetic method
   public static void determineScrollingStart$default(PagedView var0, MotionEvent var1, float var2, int var3, Object var4) {
      if (var4 == null) {
         if ((var3 & 2) != 0) {
            var2 = 1.0F;
         }

         var0.determineScrollingStart(var1, var2);
      } else {
         throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: determineScrollingStart");
      }
   }

   private final float distanceInfluenceForSnapDuration(float var1) {
      return (float)Math.sin((double)((var1 - 0.5F) * 0.4712389F));
   }

   private static final void endReordering$lambda_4(PagedView var0) {
      Intrinsics.checkNotNullParameter(var0, "this$0");
      var0.onEndReordering();
   }

   private static final void endReordering$lambda_5(Runnable var0, PagedView var1) {
      Intrinsics.checkNotNullParameter(var0, "$onCompleteRunnable");
      Intrinsics.checkNotNullParameter(var1, "this$0");
      var0.run();
      var1.enableFreeScroll();
   }

   private final void forceFinishScroller() {
      LauncherScroller var1 = this.mScroller;
      Intrinsics.checkNotNull(var1);
      var1.forceFinished(true);
      this.mNextPage = -1;
   }

   private final int getNearestHoverOverPageIndex() {
      View var9 = this.mDragView;
      if (var9 == null) {
         return -1;
      } else {
         Intrinsics.checkNotNull(var9);
         int var2 = var9.getLeft();
         var9 = this.mDragView;
         Intrinsics.checkNotNull(var9);
         float var1 = (float)(var2 + var9.getMeasuredWidth() / 2);
         var9 = this.mDragView;
         Intrinsics.checkNotNull(var9);
         int var7 = (int)(var1 + var9.getTranslationX());
         this.getFreeScrollPageRange(this.mTempVisiblePagesRange);
         int var4 = Integer.MAX_VALUE;
         int var3 = this.indexOfChild(this.mDragView);
         int[] var10 = this.mTempVisiblePagesRange;
         var2 = var10[0];
         int var8 = var10[1];
         int var5 = var3;
         if (var2 <= var8) {
            var5 = var4;

            while(true) {
               var9 = this.getPageAt(var2);
               Intrinsics.checkNotNull(var9);
               int var6 = Math.abs(var7 - (var9.getLeft() + var9.getMeasuredWidth() / 2));
               var4 = var5;
               if (var6 < var5) {
                  var3 = var2;
                  var4 = var6;
               }

               var5 = var3;
               if (var2 == var8) {
                  break;
               }

               ++var2;
               var5 = var4;
            }
         }

         return var5;
      }
   }

   private final boolean isTouchPointInViewportWithBuffer(int var1, int var2) {
      Rect var3 = sTmpRect;
      var3.set(this.mViewport.left - this.mViewport.width() / 2, this.mViewport.top, this.mViewport.right + this.mViewport.width() / 2, this.mViewport.bottom);
      return var3.contains(var1, var2);
   }

   private final float[] mapPointFromParentToView(View var1, float var2, float var3) {
      float[] var4 = sTmpPoint;
      var4[0] = var2 - (float)var1.getLeft();
      var4[1] = var3 - (float)var1.getTop();
      Matrix var6 = var1.getMatrix();
      Matrix var5 = sTmpInvMatrix;
      var6.invert(var5);
      var5.mapPoints(var4);
      return var4;
   }

   private final float[] mapPointFromViewToParent(View var1, float var2, float var3) {
      float[] var4 = sTmpPoint;
      var4[0] = var2;
      var4[1] = var3;
      var1.getMatrix().mapPoints(var4);
      var2 = var4[0];
      var1.getLeft();
      var2 = var4[1];
      var1.getTop();
      return var4;
   }

   private final void onSecondaryPointerUp(MotionEvent var1) {
      int var3 = (var1.getAction() & '\uff00') >> 8;
      if (var1.getPointerId(var3) == this.mActivePointerId) {
         byte var5;
         if (var3 == 0) {
            var5 = 1;
         } else {
            var5 = 0;
         }

         float var2 = var1.getX(var5);
         this.mDownMotionX = var2;
         this.mLastMotionX = var2;
         this.mLastMotionY = var1.getY(var5);
         this.mLastMotionXRemainder = 0.0F;
         this.mActivePointerId = var1.getPointerId(var5);
         VelocityTracker var4 = this.mVelocityTracker;
         if (var4 != null) {
            Intrinsics.checkNotNull(var4);
            var4.clear();
         }
      }

   }

   private static final void onTouchEvent$lambda_3(PagedView var0, int var1, int var2) {
      Intrinsics.checkNotNullParameter(var0, "this$0");
      var0.snapToPage(var1);
      byte var4;
      if (var2 < var1) {
         var4 = -1;
      } else {
         var4 = 1;
      }

      int var3;
      if (var2 < var1) {
         var3 = var2 + 1;
      } else {
         var3 = var1;
      }

      if (var2 > var1) {
         --var2;
      } else {
         var2 = var1;
      }

      View var9;
      while(var3 <= var2) {
         var9 = var0.getChildAt(var3);
         int var6 = var0.getViewportOffsetX();
         int var5 = var0.getChildOffset(var3);
         int var8 = var0.getViewportOffsetX();
         int var7 = var0.getChildOffset(var3 + var4);
         Object var10 = var9.getTag(100);
         Intrinsics.checkNotNull(var10, "null cannot be cast to non-null type android.animation.AnimatorSet");
         ((AnimatorSet)var10).cancel();
         var9.setTranslationX((float)(var6 + var5 - (var8 + var7)));
         AnimatorSet var11 = new AnimatorSet();
         var11.setDuration((long)REORDERING_REORDER_REPOSITION_DURATION);
         var11.playTogether(new Animator[]{(Animator)ObjectAnimator.ofFloat(var9, "translationX", new float[]{0.0F})});
         var11.start();
         var9.setTag(var11);
         ++var3;
      }

      var9 = var0.mDragView;
      Intrinsics.checkNotNull(var9);
      var0.removeView(var9);
      var0.addView(var0.mDragView, var1);
      var0.mSidePageHoverIndex = -1;
      var0.updatePageIndicator(var0.getNextPage(), var0.getChildCount());
   }

   private final void releaseVelocityTracker() {
      VelocityTracker var1 = this.mVelocityTracker;
      if (var1 != null) {
         Intrinsics.checkNotNull(var1);
         var1.clear();
         var1 = this.mVelocityTracker;
         Intrinsics.checkNotNull(var1);
         var1.recycle();
         this.mVelocityTracker = null;
      }

   }

   private final void removeMarkerForView(int var1) {
      this.updatePageIndicator(var1, this.getChildCount());
   }

   private final void resetTouchState() {
      this.releaseVelocityTracker();
      this.endReordering();
      this.mCancelTap = false;
      this.mTouchState = 0;
      this.mActivePointerId = -1;
   }

   private final void sendScrollAccessibilityEvent() {
      Object var1 = this.getContext().getSystemService("accessibility");
      Intrinsics.checkNotNull(var1, "null cannot be cast to non-null type android.view.accessibility.AccessibilityManager");
      if (((AccessibilityManager)var1).isEnabled() && this.mCurrentPage != this.getNextPage()) {
         AccessibilityEvent var2 = AccessibilityEvent.obtain(4096);
         var2.setScrollable(true);
         var2.setScrollX(this.getScrollX());
         var2.setScrollY(this.getScrollY());
         var2.setMaxScrollX(this.mMaxScrollX);
         var2.setMaxScrollY(0);
         this.sendAccessibilityEventUnchecked(var2);
      }

   }

   private final void setEnableFreeScroll(boolean var1) {
      this.mFreeScroll = var1;
      if (var1) {
         this.updateFreescrollBounds();
         this.getFreeScrollPageRange(this.mTempVisiblePagesRange);
         int var3 = this.getCurrentPage();
         int var2 = this.mTempVisiblePagesRange[0];
         if (var3 < var2) {
            this.setCurrentPage(var2);
         } else {
            var2 = this.getCurrentPage();
            var3 = this.mTempVisiblePagesRange[1];
            if (var2 > var3) {
               this.setCurrentPage(var3);
            }
         }
      }

      this.setEnableOverscroll(var1 ^ true);
   }

   // $FF: synthetic method
   public static void snapToPage$default(PagedView var0, int var1, int var2, int var3, boolean var4, TimeInterpolator var5, int var6, Object var7) {
      if (var7 == null) {
         if ((var6 & 8) != 0) {
            var4 = false;
         }

         if ((var6 & 16) != 0) {
            var5 = null;
         }

         var0.snapToPage(var1, var2, var3, var4, var5);
      } else {
         throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: snapToPage");
      }
   }

   // $FF: synthetic method
   public static void snapToPage$default(PagedView var0, int var1, int var2, boolean var3, TimeInterpolator var4, int var5, Object var6) {
      if (var6 == null) {
         if ((var5 & 4) != 0) {
            var3 = false;
         }

         if ((var5 & 8) != 0) {
            var4 = null;
         }

         var0.snapToPage(var1, var2, var3, var4);
      } else {
         throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: snapToPage");
      }
   }

   private final void updateDragViewTranslationDuringDrag() {
      if (this.mDragView != null) {
         float var1 = this.mLastMotionX;
         float var2 = this.mDownMotionX;
         float var4 = (float)this.getScrollX();
         float var5 = this.mDownScrollX;
         float var3 = this.mDragViewBaselineLeft;
         View var6 = this.mDragView;
         Intrinsics.checkNotNull(var6);
         var1 = var1 - var2 + (var4 - var5) + (var3 - (float)var6.getLeft());
         var2 = this.mLastMotionY - this.mDownMotionY;
         var6 = this.mDragView;
         Intrinsics.checkNotNull(var6);
         var6.setTranslationX(var1);
         var6 = this.mDragView;
         Intrinsics.checkNotNull(var6);
         var6.setTranslationY(var2);
         if (LogUtil.log5()) {
            LogUtil.d("PagedView.updateDragViewTranslationDuringDrag(): " + var1 + ", " + var2);
         }
      }

   }

   private final void updatePageIndicator() {
      this.updatePageIndicator(this.getNextPage(), this.getChildCount());
   }

   private final int validateNewPage(int var1) {
      int var2 = var1;
      if (this.mFreeScroll) {
         this.getFreeScrollPageRange(this.mTempVisiblePagesRange);
         int[] var3 = this.mTempVisiblePagesRange;
         var2 = Math.max(var3[0], Math.min(var1, var3[1]));
      }

      return Math.max(0, Math.min(var2, this.getPageCount() - 1));
   }

   public void addFocusables(ArrayList var1, int var2, int var3) {
      Intrinsics.checkNotNullParameter(var1, "views");
      int var4 = this.mCurrentPage;
      View var5;
      if (var4 >= 0 && var4 < this.getPageCount()) {
         var5 = this.getPageAt(this.mCurrentPage);
         if (var5 != null) {
            var5.addFocusables(var1, var2, var3);
         }
      }

      if (var2 != 17) {
         if (var2 == 66 && this.mCurrentPage < this.getPageCount() - 1) {
            var5 = this.getPageAt(this.mCurrentPage + 1);
            if (var5 != null) {
               var5.addFocusables(var1, var2, var3);
            }
         }
      } else {
         var4 = this.mCurrentPage;
         if (var4 > 0) {
            var5 = this.getPageAt(var4 - 1);
            if (var5 != null) {
               var5.addFocusables(var1, var2, var3);
            }
         }
      }

   }

   public final void addFullScreenPage(View var1) {
      LayoutParams var2 = this.generateDefaultLayoutParams();
      var2.setFullScreenPage(true);
      super.addView(var1, -1, (ViewGroup.LayoutParams)var2);
   }

   protected final void cancelCurrentPageLongPress() {
      View var1 = this.getPageAt(this.mCurrentPage);
      if (var1 != null) {
         var1.cancelLongPress();
      }

   }

   protected boolean checkLayoutParams(ViewGroup.LayoutParams var1) {
      Intrinsics.checkNotNullParameter(var1, "p");
      return var1 instanceof LayoutParams;
   }

   public void computeScroll() {
      this.computeScrollHelper();
   }

   protected final boolean computeScrollHelper() {
      LauncherScroller var5 = this.mScroller;
      Intrinsics.checkNotNull(var5);
      boolean var4 = var5.computeScrollOffset();
      boolean var3 = false;
      if (!var4) {
         if (this.mNextPage != -1) {
            this.sendScrollAccessibilityEvent();
            this.mCurrentPage = this.validateNewPage(this.mNextPage);
            this.mNextPage = -1;
            this.notifyPageSwitchListener();
            if (this.mTouchState == 0) {
               this.pageEndMoving();
            }

            this.onPostReorderingAnimationCompleted();
            return true;
         } else {
            return false;
         }
      } else {
         var5 = this.mScroller;
         boolean var2;
         if (var5 != null && this.getScrollX() == var5.getCurrX()) {
            var2 = true;
         } else {
            var2 = false;
         }

         label48: {
            if (var2) {
               var5 = this.mScroller;
               var2 = var3;
               if (var5 != null) {
                  var2 = var3;
                  if (this.getScrollY() == var5.getCurrY()) {
                     var2 = true;
                  }
               }

               if (var2) {
                  break label48;
               }
            }

            float var1;
            if (this.mFreeScroll) {
               var1 = this.getScaleX();
            } else {
               var1 = 1.0F;
            }

            var5 = this.mScroller;
            Intrinsics.checkNotNull(var5);
            int var6 = (int)((float)var5.getCurrX() * ((float)1 / var1));
            var5 = this.mScroller;
            if (var5 != null) {
               this.scrollTo(var6, var5.getCurrY());
            }
         }

         this.invalidate();
         return true;
      }
   }

   protected final void determineScrollingStart(MotionEvent var1, float var2) {
      Intrinsics.checkNotNullParameter(var1, "ev");
      int var5 = var1.findPointerIndex(this.mActivePointerId);
      if (var5 != -1) {
         float var4 = var1.getX(var5);
         float var3 = var1.getY(var5);
         if (this.isTouchPointInViewportWithBuffer((int)var4, (int)var3)) {
            boolean var6;
            if ((int)Math.abs(var4 - this.mLastMotionX) > Math.round(var2 * (float)this.mTouchSlop)) {
               var6 = true;
            } else {
               var6 = false;
            }

            if (var6) {
               this.mTouchState = 1;
               this.mTotalMotionX += Math.abs(this.mLastMotionX - var4);
               this.mLastMotionX = var4;
               this.mLastMotionXRemainder = 0.0F;
               this.mTouchX = (float)(this.getViewportOffsetX() + this.getScrollX());
               this.mSmoothingTime = (float)System.nanoTime() / 1.0E9F;
               this.onScrollInteractionBegin();
               this.pageBeginMoving();
            }

         }
      }
   }

   public final void didAbleScroll() {
      this.mScrollAble = false;
   }

   public final void disableFreeScroll() {
      this.setEnableFreeScroll(false);
   }

   protected void dispatchDraw(Canvas var1) {
      Intrinsics.checkNotNullParameter(var1, "canvas");
      int var2 = this.getChildCount();
      if (var2 > 0) {
         int var3 = this.getViewportWidth() / 2;
         var3 += this.getScrollX();
         if (var3 != this.mLastScreenCenter || this.mForceScreenScrolled) {
            this.mForceScreenScrolled = false;
            this.screenScrolled(var3);
            this.mLastScreenCenter = var3;
         }

         this.getVisiblePages(this.mTempVisiblePagesRange);
         int[] var7 = this.mTempVisiblePagesRange;
         int var4 = var7[0];
         var3 = var7[1];
         if (var4 != -1 && var3 != -1) {
            long var5 = this.getDrawingTime();
            var1.save();
            var1.clipRect(this.getScrollX(), this.getScrollY(), this.getScrollX() + this.getRight() - this.getLeft(), this.getScrollY() + this.getBottom() - this.getTop());
            --var2;

            View var8;
            for(; -1 < var2; --var2) {
               var8 = this.getPageAt(var2);
               if (var8 != this.mDragView) {
                  if (!this.mForceDrawAllChildrenNextFrame) {
                     if (var4 > var2 || var2 > var3) {
                        continue;
                     }

                     Intrinsics.checkNotNull(var8);
                     if (!this.shouldDrawChild(var8)) {
                        continue;
                     }
                  }

                  this.drawChild(var1, var8, var5);
               }
            }

            var8 = this.mDragView;
            if (var8 != null) {
               this.drawChild(var1, var8, var5);
            }

            this.mForceDrawAllChildrenNextFrame = false;
            var1.restore();
         }
      }

   }

   public boolean dispatchTouchEvent(MotionEvent var1) {
      Intrinsics.checkNotNullParameter(var1, "ev");
      if (var1.getAction() == 0) {
         this.enableScroll();
      }

      boolean var2;
      if (this.mScrollAble) {
         var2 = super.dispatchTouchEvent(var1);
      } else {
         var2 = true;
      }

      return var2;
   }

   public boolean dispatchUnhandledMove(View var1, int var2) {
      Intrinsics.checkNotNullParameter(var1, "focused");
      if (var2 != 17) {
         if (var2 == 66 && this.getCurrentPage() < this.getPageCount() - 1) {
            this.snapToPage(this.getCurrentPage() + 1);
            return true;
         }
      } else if (this.getCurrentPage() > 0) {
         this.snapToPage(this.getCurrentPage() - 1);
         return true;
      }

      return super.dispatchUnhandledMove(var1, var2);
   }

   public final void enableFreeScroll() {
      this.setEnableFreeScroll(true);
   }

   public final void enableScroll() {
      this.mScrollAble = true;
   }

   public final void endReordering() {
      if (this.mReorderingStarted) {
         this.mReorderingStarted = false;
         this.mPostReorderingPreZoomInRunnable = new PagedView$$ExternalSyntheticLambda2(new PagedView$$ExternalSyntheticLambda1(this), this);
         this.mPostReorderingPreZoomInRemainingAnimationCount = this.NUM_ANIMATIONS_RUNNING_BEFORE_ZOOM_OUT;
         snapToPage$default(this, this.indexOfChild(this.mDragView), 0, false, (TimeInterpolator)null, 12, (Object)null);
         this.animateDragViewToOriginalPosition();
      }
   }

   public void focusableViewAvailable(View var1) {
      Intrinsics.checkNotNullParameter(var1, "focused");
      View var3 = this.getPageAt(this.mCurrentPage);

      ViewParent var4;
      for(View var2 = var1; var2 != var3; var2 = (View)var4) {
         if (var2 == this) {
            return;
         }

         if (!(var2.getParent() instanceof View)) {
            return;
         }

         var4 = var2.getParent();
         Intrinsics.checkNotNull(var4, "null cannot be cast to non-null type android.view.View");
      }

      super.focusableViewAvailable(var1);
   }

   protected LayoutParams generateDefaultLayoutParams() {
      return new LayoutParams(-1, -1);
   }

   protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams var1) {
      Intrinsics.checkNotNullParameter(var1, "p");
      return (ViewGroup.LayoutParams)(new LayoutParams(var1));
   }

   public LayoutParams generateLayoutParams(AttributeSet var1) {
      Intrinsics.checkNotNullParameter(var1, "attrs");
      return new LayoutParams(this.getContext(), var1);
   }

   protected final int getChildGap() {
      return 0;
   }

   protected final int getChildOffset(int var1) {
      if (var1 >= 0 && var1 <= this.getChildCount() - 1) {
         View var2 = this.getPageAt(var1);
         Intrinsics.checkNotNull(var2);
         var1 = var2.getLeft() - this.getViewportOffsetX();
      } else {
         var1 = 0;
      }

      return var1;
   }

   public final int getCurrentPage() {
      return this.mCurrentPage;
   }

   protected final void getFreeScrollPageRange(int[] var1) {
      Intrinsics.checkNotNullParameter(var1, "range");
      var1[0] = 0;
      var1[1] = Math.max(0, this.getChildCount() - 1);
   }

   public final int getLayoutTransitionOffsetForPage(int var1) {
      int[] var5 = this.mPageScrolls;
      byte var4 = 0;
      int var3 = 0;
      int var2 = var4;
      if (var5 != null) {
         Intrinsics.checkNotNull(var5);
         var2 = var4;
         if (var1 < var5.length) {
            if (var1 < 0) {
               var2 = var4;
            } else {
               View var7 = this.getChildAt(var1);
               ViewGroup.LayoutParams var6 = var7.getLayoutParams();
               Intrinsics.checkNotNull(var6, "null cannot be cast to non-null type com.hzbhd.ui.view.paged.PagedView.LayoutParams");
               var2 = var3;
               if (!((LayoutParams)var6).isFullScreenPage()) {
                  if (this.mIsRtl) {
                     var2 = this.getPaddingRight();
                  } else {
                     var2 = this.getPaddingLeft();
                  }
               }

               int[] var8 = this.mPageScrolls;
               Intrinsics.checkNotNull(var8);
               var3 = var8[var1];
               var1 = this.getViewportOffsetX();
               var2 = (int)(var7.getX() - (float)(var3 + var2 + var1));
            }
         }
      }

      return var2;
   }

   protected final int getMActivePointerId() {
      return this.mActivePointerId;
   }

   protected final boolean getMAllowOverScroll() {
      return this.mAllowOverScroll;
   }

   protected final int getMCellCountX() {
      return this.mCellCountX;
   }

   protected final int getMCellCountY() {
      return this.mCellCountY;
   }

   protected final boolean getMCenterPagesVertically() {
      return this.mCenterPagesVertically;
   }

   protected final int getMChildCountOnLastLayout() {
      return this.mChildCountOnLastLayout;
   }

   protected final int getMCurrentPage() {
      return this.mCurrentPage;
   }

   protected final float getMDensity() {
      return this.mDensity;
   }

   protected final View getMDragView() {
      return this.mDragView;
   }

   protected final boolean getMFadeInAdjacentScreens() {
      return this.mFadeInAdjacentScreens;
   }

   protected final boolean getMFirstLayout() {
      return this.mFirstLayout;
   }

   protected final int getMFlingThresholdVelocity() {
      return this.mFlingThresholdVelocity;
   }

   protected final boolean getMForceDrawAllChildrenNextFrame() {
      return this.mForceDrawAllChildrenNextFrame;
   }

   protected final boolean getMForceScreenScrolled() {
      return this.mForceScreenScrolled;
   }

   protected final Rect getMInsets() {
      return this.mInsets;
   }

   protected final boolean getMIsRtl() {
      return this.mIsRtl;
   }

   protected final float getMLastMotionX() {
      return this.mLastMotionX;
   }

   protected final float getMLastMotionXRemainder() {
      return this.mLastMotionXRemainder;
   }

   protected final float getMLastMotionY() {
      return this.mLastMotionY;
   }

   protected final OnLongClickListener getMLongClickListener() {
      return this.mLongClickListener;
   }

   protected final int getMMaxScrollX() {
      return this.mMaxScrollX;
   }

   protected final int getMMinFlingVelocity() {
      return this.mMinFlingVelocity;
   }

   protected final int getMMinSnapVelocity() {
      return this.mMinSnapVelocity;
   }

   protected final int getMNextPage() {
      return this.mNextPage;
   }

   public final int getMPageSpacing() {
      return this.mPageSpacing;
   }

   protected final LauncherScroller getMScroller() {
      return this.mScroller;
   }

   public final int getMSidePageHoverIndex() {
      return this.mSidePageHoverIndex;
   }

   protected final float getMSmoothingTime() {
      return this.mSmoothingTime;
   }

   protected final int[] getMTempVisiblePagesRange() {
      return this.mTempVisiblePagesRange;
   }

   protected final float getMTotalMotionX() {
      return this.mTotalMotionX;
   }

   protected final int getMTouchSlop() {
      return this.mTouchSlop;
   }

   protected final int getMTouchState() {
      return this.mTouchState;
   }

   protected final float getMTouchX() {
      return this.mTouchX;
   }

   public final int getNextPage() {
      int var1 = this.mNextPage;
      if (var1 == -1) {
         var1 = this.mCurrentPage;
      }

      return var1;
   }

   public final int getNormalChildHeight() {
      return this.normalChildHeight;
   }

   public final View getPageAt(int var1) {
      return this.getChildAt(var1);
   }

   public final int getPageCount() {
      return this.getChildCount();
   }

   public final int getPageForView(View var1) {
      if (var1 != null) {
         ViewParent var4 = var1.getParent();
         int var3 = this.getChildCount();

         for(int var2 = 0; var2 < var3; ++var2) {
            if (var4 == this.getPageAt(var2)) {
               return var2;
            }
         }
      }

      return -1;
   }

   protected final OnClickListener getPageIndicatorClickListener() {
      return null;
   }

   public final int getPageNearestToCenterOfScreen() {
      int var9 = this.getViewportOffsetX();
      int var7 = this.getScrollX();
      int var8 = this.getViewportWidth() / 2;
      int var6 = this.getChildCount();
      int var2 = Integer.MAX_VALUE;
      int var3 = -1;

      int var4;
      for(int var1 = 0; var1 < var6; var2 = var4) {
         View var10 = this.getPageAt(var1);
         Intrinsics.checkNotNull(var10);
         var4 = var10.getMeasuredWidth() / 2;
         int var5 = Math.abs(this.getViewportOffsetX() + this.getChildOffset(var1) + var4 - (var9 + var7 + var8));
         var4 = var2;
         if (var5 < var2) {
            var3 = var1;
            var4 = var5;
         }

         ++var1;
      }

      return var3;
   }

   public final int getRestorePage() {
      return this.restorePage;
   }

   public final int getScrollForPage(int var1) {
      int[] var2 = this.mPageScrolls;
      if (var2 != null) {
         Intrinsics.checkNotNull(var2);
         if (var1 < var2.length && var1 >= 0) {
            var2 = this.mPageScrolls;
            Intrinsics.checkNotNull(var2);
            var1 = var2[var1];
            return var1;
         }
      }

      var1 = 0;
      return var1;
   }

   protected final float getScrollProgress(int var1, View var2, int var3) {
      int var5;
      int var6;
      label34: {
         Intrinsics.checkNotNullParameter(var2, "v");
         int var4 = this.getViewportWidth() / 2;
         var5 = var1 - (this.getScrollForPage(var3) + var4);
         var6 = this.getChildCount();
         var4 = var3 + 1;
         if (var5 >= 0 || this.mIsRtl) {
            var1 = var4;
            if (var5 <= 0) {
               break label34;
            }

            var1 = var4;
            if (!this.mIsRtl) {
               break label34;
            }
         }

         var1 = var3 - 1;
      }

      if (var1 >= 0 && var1 <= var6 - 1) {
         var1 = Math.abs(this.getScrollForPage(var1) - this.getScrollForPage(var3));
      } else {
         var1 = var2.getMeasuredWidth() + this.mPageSpacing;
      }

      return Math.max(Math.min((float)var5 / ((float)var1 * 1.0F), 1.0F), -1.0F);
   }

   public final int getViewportHeight() {
      return this.mViewport.height();
   }

   public final int getViewportOffsetX() {
      return (this.getMeasuredWidth() - this.getViewportWidth()) / 2;
   }

   public final int getViewportOffsetY() {
      return (this.getMeasuredHeight() - this.getViewportHeight()) / 2;
   }

   public final int getViewportWidth() {
      return this.mViewport.width();
   }

   protected final void getVisiblePages(int[] var1) {
      Intrinsics.checkNotNullParameter(var1, "range");
      int var2 = this.getChildCount();
      int[] var7 = sTmpIntPoint;
      var7[1] = 0;
      var7[0] = 0;
      var1[0] = -1;
      var1[1] = -1;
      if (var2 > 0) {
         int var5 = this.getViewportWidth();
         int var4 = this.getChildCount();
         var2 = 0;

         int var3;
         for(var3 = 0; var2 < var4; ++var2) {
            View var9 = this.getPageAt(var2);
            int[] var8 = sTmpIntPoint;
            var8[0] = 0;
            if (var5 < 0) {
               if (var1[0] != -1) {
                  break;
               }
            } else {
               Intrinsics.checkNotNull(var9);
               int var6 = var9.getMeasuredWidth();
               var8[0] = var6;
               if (var6 < 0) {
                  if (var1[0] != -1) {
                     break;
                  }
               } else {
                  if (var1[0] < 0) {
                     var1[0] = var2;
                  }

                  var3 = var2;
               }
            }
         }

         var1[1] = var3;
      } else {
         var1[0] = -1;
         var1[1] = -1;
      }

   }

   protected final boolean hitsNextPage(float var1, float var2) {
      boolean var4 = this.mIsRtl;
      boolean var3 = true;
      if (var4) {
         if (var1 < (float)(this.getViewportOffsetX() + this.getPaddingLeft() + this.mPageSpacing)) {
            return var3;
         }
      } else if (var1 > (float)(this.getViewportOffsetX() + this.getViewportWidth() - this.getPaddingRight() - this.mPageSpacing)) {
         return var3;
      }

      var3 = false;
      return var3;
   }

   protected final boolean hitsPreviousPage(float var1, float var2) {
      boolean var4 = this.mIsRtl;
      boolean var3 = true;
      if (var4) {
         if (var1 > (float)(this.getViewportOffsetX() + this.getViewportWidth() - this.getPaddingRight() - this.mPageSpacing)) {
            return var3;
         }
      } else if (var1 < (float)(this.getViewportOffsetX() + this.getPaddingLeft() + this.mPageSpacing)) {
         return var3;
      }

      var3 = false;
      return var3;
   }

   protected final int indexToPage(int var1) {
      return var1;
   }

   protected final void init() {
      Context var2 = this.getContext();
      Intrinsics.checkNotNullExpressionValue(var2, "context");
      this.mScroller = new LauncherScroller(var2, (Interpolator)null, false, 6, (DefaultConstructorMarker)null);
      this.setDefaultInterpolator((Interpolator)(new ScrollInterpolator()));
      this.mCurrentPage = 0;
      this.mCenterPagesVertically = true;
      ViewConfiguration var3 = ViewConfiguration.get(this.getContext());
      this.mTouchSlop = var3.getScaledPagingTouchSlop();
      this.mMaximumVelocity = var3.getScaledMaximumFlingVelocity();
      float var1 = this.getResources().getDisplayMetrics().density;
      this.mDensity = var1;
      this.mFlingThresholdVelocity = (int)((float)500 * var1);
      this.mMinFlingVelocity = (int)((float)250 * var1);
      this.mMinSnapVelocity = (int)((float)1500 * var1);
      this.setOnHierarchyChangeListener((OnHierarchyChangeListener)this);
      this.setWillNotDraw(false);
   }

   protected final boolean isPageMoving() {
      return this.isPageMoving;
   }

   public final boolean isReordering(boolean var1) {
      boolean var4 = this.mIsReordering;
      boolean var3 = var4;
      if (var1) {
         boolean var2;
         if (this.mTouchState == 4) {
            var2 = true;
         } else {
            var2 = false;
         }

         var3 = var4 & var2;
      }

      return var3;
   }

   protected final void notifyPageSwitchListener() {
      PageSwitchListener var1 = this.mPageSwitchListener;
      if (var1 != null) {
         Intrinsics.checkNotNull(var1);
         var1.onPageSwitch(this.getPageAt(this.getNextPage()), this.getNextPage());
      }

      this.updatePageIndicator();
   }

   protected void onAttachedToWindow() {
      super.onAttachedToWindow();
      ViewParent var1 = this.getParent();
      Intrinsics.checkNotNull(var1, "null cannot be cast to non-null type android.view.ViewGroup");
      var1 = ((ViewGroup)var1).getParent();
      Intrinsics.checkNotNull(var1, "null cannot be cast to non-null type android.view.ViewGroup");
      ViewGroup var2 = (ViewGroup)var1;
   }

   public void onChildViewAdded(View var1, View var2) {
      Intrinsics.checkNotNullParameter(var1, "parent");
      Intrinsics.checkNotNullParameter(var2, "child");
      this.updatePageIndicator(this.getNextPage(), this.getChildCount());
      this.mForceScreenScrolled = true;
      this.updateFreescrollBounds();
      this.invalidate();
   }

   public void onChildViewRemoved(View var1, View var2) {
      Intrinsics.checkNotNullParameter(var1, "parent");
      Intrinsics.checkNotNullParameter(var2, "child");
      this.mForceScreenScrolled = true;
      this.updateFreescrollBounds();
      this.invalidate();
   }

   public final void onEndReordering() {
      this.mIsReordering = false;
   }

   public boolean onGenericMotionEvent(MotionEvent var1) {
      Intrinsics.checkNotNullParameter(var1, "event");
      if ((var1.getSource() & 2) != 0 && var1.getAction() == 8) {
         float var2;
         float var3;
         if ((var1.getMetaState() & 1) != 0) {
            var3 = var1.getAxisValue(9);
            var2 = 0.0F;
         } else {
            var2 = -var1.getAxisValue(9);
            var3 = var1.getAxisValue(10);
         }

         float var7;
         int var6 = (var7 = var3 - 0.0F) == 0.0F ? 0 : (var7 < 0.0F ? -1 : 1);
         boolean var5 = false;
         boolean var4;
         if (var6 == 0) {
            var4 = true;
         } else {
            var4 = false;
         }

         if (var4) {
            if (var2 == 0.0F) {
               var4 = true;
            } else {
               var4 = false;
            }

            if (var4) {
               return super.onGenericMotionEvent(var1);
            }
         }

         label41: {
            if (this.mIsRtl) {
               if (var6 >= 0) {
                  var4 = var5;
                  if (!(var2 < 0.0F)) {
                     break label41;
                  }
               }
            } else if (!(var3 > 0.0F)) {
               var4 = var5;
               if (!(var2 > 0.0F)) {
                  break label41;
               }
            }

            var4 = true;
         }

         if (var4) {
            this.scrollRight();
         } else {
            this.scrollLeft();
         }

         return true;
      } else {
         return super.onGenericMotionEvent(var1);
      }
   }

   public boolean onHoverEvent(MotionEvent var1) {
      Intrinsics.checkNotNullParameter(var1, "event");
      return true;
   }

   public void onInitializeAccessibilityEvent(AccessibilityEvent var1) {
      Intrinsics.checkNotNullParameter(var1, "event");
      super.onInitializeAccessibilityEvent(var1);
      int var2 = this.getPageCount();
      boolean var3 = true;
      if (var2 <= 1) {
         var3 = false;
      }

      var1.setScrollable(var3);
   }

   public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo var1) {
      Intrinsics.checkNotNullParameter(var1, "info");
      super.onInitializeAccessibilityNodeInfo(var1);
      boolean var2;
      if (this.getPageCount() > 1) {
         var2 = true;
      } else {
         var2 = false;
      }

      var1.setScrollable(var2);
      if (this.getCurrentPage() < this.getPageCount() - 1) {
         var1.addAction(4096);
      }

      if (this.getCurrentPage() > 0) {
         var1.addAction(8192);
      }

      var1.setClassName((CharSequence)this.getClass().getName());
      var1.setLongClickable(false);
      var1.removeAction(AccessibilityAction.ACTION_LONG_CLICK);
   }

   public boolean onInterceptTouchEvent(MotionEvent var1) {
      Intrinsics.checkNotNullParameter(var1, "ev");
      this.acquireVelocityTrackerAndAddMovement(var1);
      if (this.getChildCount() <= 0) {
         return super.onInterceptTouchEvent(var1);
      } else {
         int var4 = var1.getAction();
         boolean var5 = true;
         if (var4 == 2 && this.mTouchState == 1) {
            return true;
         } else {
            var4 &= 255;
            if (var4 != 0) {
               label52: {
                  if (var4 != 1) {
                     if (var4 == 2) {
                        if (this.mActivePointerId != -1) {
                           determineScrollingStart$default(this, var1, 0.0F, 2, (Object)null);
                        }
                        break label52;
                     }

                     if (var4 != 3) {
                        if (var4 == 6) {
                           this.onSecondaryPointerUp(var1);
                           this.releaseVelocityTracker();
                        }
                        break label52;
                     }
                  }

                  this.resetTouchState();
               }
            } else {
               float var2 = var1.getX();
               float var3 = var1.getY();
               this.mDownMotionX = var2;
               this.mDownMotionY = var3;
               this.mDownScrollX = (float)this.getScrollX();
               this.mLastMotionX = var2;
               this.mLastMotionY = var3;
               float[] var6 = this.mapPointFromViewToParent((View)this, var2, var3);
               this.mParentDownMotionX = var6[0];
               this.mParentDownMotionY = var6[1];
               this.mLastMotionXRemainder = 0.0F;
               this.mTotalMotionX = 0.0F;
               this.mActivePointerId = var1.getPointerId(0);
               LauncherScroller var7 = this.mScroller;
               Intrinsics.checkNotNull(var7);
               var4 = var7.getFinalX();
               var7 = this.mScroller;
               Intrinsics.checkNotNull(var7);
               var4 = Math.abs(var4 - var7.getCurrX());
               var7 = this.mScroller;
               Intrinsics.checkNotNull(var7);
               boolean var8;
               if (!var7.isFinished() && var4 >= this.mTouchSlop / 3) {
                  var8 = false;
               } else {
                  var8 = true;
               }

               if (var8) {
                  this.mTouchState = 0;
                  var7 = this.mScroller;
                  Intrinsics.checkNotNull(var7);
                  if (!var7.isFinished() && !this.mFreeScroll) {
                     this.setCurrentPage(this.getNextPage());
                     this.pageEndMoving();
                  }
               } else {
                  this.mTouchState = this.isTouchPointInViewportWithBuffer((int)this.mDownMotionX, (int)this.mDownMotionY);
               }
            }

            if (this.mTouchState == 0) {
               var5 = false;
            }

            return var5;
         }
      }
   }

   protected void onLayout(boolean var1, int var2, int var3, int var4, int var5) {
      if (this.getChildCount() != 0) {
         if (LogUtil.log5()) {
            LogUtil.d("PagedView.onLayout()");
         }

         int var8 = this.getChildCount();
         int var11 = this.getViewportOffsetX();
         int var9 = this.getViewportOffsetY();
         this.mViewport.offset(var11, var9);
         var1 = this.mIsRtl;
         if (var1) {
            var3 = var8 - 1;
         } else {
            var3 = 0;
         }

         byte var18 = -1;
         if (var1) {
            var4 = -1;
         } else {
            var4 = var8;
         }

         if (!var1) {
            var18 = 1;
         }

         int var12 = this.getPaddingTop();
         int var13 = this.getPaddingBottom();
         ViewGroup.LayoutParams var14 = this.getChildAt(var3).getLayoutParams();
         Intrinsics.checkNotNull(var14, "null cannot be cast to non-null type com.hzbhd.ui.view.paged.PagedView.LayoutParams");
         if (((LayoutParams)var14).isFullScreenPage()) {
            var2 = 0;
         } else {
            var2 = this.getPaddingLeft();
         }

         int var6;
         int var7;
         int var10;
         label120: {
            var10 = var2 + var11;
            if (this.mPageScrolls != null) {
               var2 = var9;
               var6 = var3;
               var7 = var10;
               if (var8 == this.mChildCountOnLastLayout) {
                  break label120;
               }
            }

            this.mPageScrolls = new int[var8];
            var7 = var10;
            var6 = var3;
         }

         for(var2 = var9; var6 != var4; var6 += var18) {
            View var16 = this.getPageAt(var6);
            boolean var17;
            if (var16 != null && var16.getVisibility() == 8) {
               var17 = true;
            } else {
               var17 = false;
            }

            if (!var17) {
               if (var16 != null) {
                  var14 = var16.getLayoutParams();
               } else {
                  var14 = null;
               }

               Intrinsics.checkNotNull(var14, "null cannot be cast to non-null type com.hzbhd.ui.view.paged.PagedView.LayoutParams");
               LayoutParams var15 = (LayoutParams)var14;
               if (var15.isFullScreenPage()) {
                  var3 = var2;
               } else {
                  var9 = this.getPaddingTop() + var2 + this.mInsets.top;
                  var3 = var9;
                  if (this.mCenterPagesVertically) {
                     var3 = var9 + (this.getViewportHeight() - this.mInsets.top - this.mInsets.bottom - (var12 + var13) - var16.getMeasuredHeight()) / 2;
                  }
               }

               var10 = var16.getMeasuredWidth();
               var9 = var16.getMeasuredHeight();
               if (LogUtil.log5()) {
                  LogUtil.d("\tlayout-child" + var6 + ": " + var7 + ", " + var3);
               }

               var16.layout(var7, var3, var16.getMeasuredWidth() + var7, var9 + var3);
               if (var15.isFullScreenPage()) {
                  var3 = 0;
               } else {
                  var3 = this.getPaddingLeft();
               }

               int[] var19 = this.mPageScrolls;
               Intrinsics.checkNotNull(var19);
               var19[var6] = var7 - var3 - var11;
               var9 = this.mPageSpacing;
               var3 = var6 + var18;
               LayoutParams var21;
               if (var3 != var4) {
                  View var20 = this.getPageAt(var3);
                  if (var20 != null) {
                     var14 = var20.getLayoutParams();
                  } else {
                     var14 = null;
                  }

                  Intrinsics.checkNotNull(var14, "null cannot be cast to non-null type com.hzbhd.ui.view.paged.PagedView.LayoutParams");
                  var21 = (LayoutParams)var14;
               } else {
                  var21 = (LayoutParams)null;
                  var21 = null;
               }

               if (var15.isFullScreenPage()) {
                  var3 = this.getPaddingLeft();
               } else {
                  var3 = var9;
                  if (var21 != null) {
                     var3 = var9;
                     if (var21.isFullScreenPage()) {
                        var3 = this.getPaddingRight();
                     }
                  }
               }

               var7 += var10 + var3 + this.getChildGap();
            }
         }

         LayoutTransition var22 = this.getLayoutTransition();
         if (var22 != null && var22.isRunning()) {
            var22.addTransitionListener((LayoutTransition.TransitionListener)(new LayoutTransition.TransitionListener(this) {
               final PagedView this$0;

               {
                  this.this$0 = var1;
               }

               public void endTransition(LayoutTransition var1, ViewGroup var2, View var3, int var4) {
                  Intrinsics.checkNotNullParameter(var1, "transition");
                  Intrinsics.checkNotNullParameter(var2, "container");
                  Intrinsics.checkNotNullParameter(var3, "view");
                  if (!var1.isRunning()) {
                     var1.removeTransitionListener((LayoutTransition.TransitionListener)this);
                     this.this$0.updateMaxScrollX();
                  }

               }

               public void startTransition(LayoutTransition var1, ViewGroup var2, View var3, int var4) {
                  Intrinsics.checkNotNullParameter(var1, "transition");
                  Intrinsics.checkNotNullParameter(var2, "container");
                  Intrinsics.checkNotNullParameter(var3, "view");
               }
            }));
         } else {
            this.updateMaxScrollX();
         }

         if (this.mFirstLayout) {
            var2 = this.mCurrentPage;
            if (var2 >= 0 && var2 < var8) {
               this.updateCurrentPageScroll();
               this.mFirstLayout = false;
            }
         }

         LauncherScroller var23 = this.mScroller;
         Intrinsics.checkNotNull(var23);
         if (var23.isFinished() && this.mChildCountOnLastLayout != var8) {
            var2 = this.restorePage;
            if (var2 != -1001) {
               this.setCurrentPage(var2);
               this.restorePage = -1001;
            } else {
               this.setCurrentPage(this.getNextPage());
            }
         }

         this.mChildCountOnLastLayout = var8;
         if (this.isReordering(true)) {
            this.updateDragViewTranslationDuringDrag();
         }

      }
   }

   protected void onMeasure(int var1, int var2) {
      if (this.getChildCount() == 0) {
         super.onMeasure(var1, var2);
      } else {
         int var10 = MeasureSpec.getMode(var1);
         int var8 = MeasureSpec.getSize(var1);
         int var11 = MeasureSpec.getMode(var2);
         int var5 = MeasureSpec.getSize(var2);
         DisplayMetrics var16 = this.getResources().getDisplayMetrics();
         int var9 = (int)((float)Math.max(var16.widthPixels + this.mInsets.left + this.mInsets.right, var16.heightPixels + this.mInsets.top + this.mInsets.bottom) * 2.0F);
         int var6;
         int var7;
         if (this.mUseMinScale) {
            float var3 = (float)var9;
            float var4 = this.mMinScale;
            var7 = (int)(var3 / var4);
            var6 = (int)(var3 / var4);
         } else {
            var7 = var8;
            var6 = var5;
         }

         this.mViewport.set(0, 0, var8, var5);
         if (var10 != 0 && var11 != 0) {
            if (var8 > 0 && var5 > 0) {
               int var12 = this.getPaddingTop() + this.getPaddingBottom();
               int var13 = this.getPaddingLeft() + this.getPaddingRight();
               if (LogUtil.log3()) {
                  LogUtil.d("PagedView.onMeasure(): " + var8 + ", " + var5);
               }

               if (LogUtil.log3()) {
                  LogUtil.d("PagedView.scaledSize: " + var7 + ", " + var6);
               }

               if (LogUtil.log3()) {
                  LogUtil.d("PagedView.parentSize: " + var9 + ", " + var9);
               }

               if (LogUtil.log3()) {
                  LogUtil.d("PagedView.horizontalPadding: " + var13);
               }

               if (LogUtil.log3()) {
                  LogUtil.d("PagedView.verticalPadding: " + var12);
               }

               int var14 = this.getChildCount();
               var8 = 0;

               for(var1 = 0; var8 < var14; var1 = var2) {
                  View var17 = this.getPageAt(var8);
                  boolean var18;
                  if (var17 != null && var17.getVisibility() == 8) {
                     var18 = true;
                  } else {
                     var18 = false;
                  }

                  var2 = var1;
                  if (!var18) {
                     ViewGroup.LayoutParams var19;
                     if (var17 != null) {
                        var19 = var17.getLayoutParams();
                     } else {
                        var19 = null;
                     }

                     Intrinsics.checkNotNull(var19, "null cannot be cast to non-null type com.hzbhd.ui.view.paged.PagedView.LayoutParams");
                     LayoutParams var20 = (LayoutParams)var19;
                     boolean var15 = var20.isFullScreenPage();
                     var5 = 1073741824;
                     if (!var15) {
                        if (var20.width == -2) {
                           var2 = Integer.MIN_VALUE;
                        } else {
                           var2 = 1073741824;
                        }

                        if (var20.height == -2) {
                           var5 = Integer.MIN_VALUE;
                        }

                        var9 = this.getViewportWidth() - var13 - this.mInsets.left - this.mInsets.right;
                        var10 = this.getViewportHeight() - var12 - this.mInsets.top - this.mInsets.bottom;
                        this.normalChildHeight = var10;
                        var11 = var2;
                        var2 = var9;
                        var9 = var5;
                     } else {
                        var2 = this.getViewportWidth() - this.mInsets.left - this.mInsets.right;
                        var10 = this.getViewportHeight();
                        var9 = 1073741824;
                        var11 = var5;
                     }

                     var5 = var1;
                     if (var1 == 0) {
                        var5 = var2;
                     }

                     var1 = MeasureSpec.makeMeasureSpec(var2, var11);
                     var9 = MeasureSpec.makeMeasureSpec(var10, var9);
                     var2 = var5;
                     if (var17 != null) {
                        var17.measure(var1, var9);
                        var2 = var5;
                     }
                  }

                  ++var8;
               }

               this.setMeasuredDimension(var7, var6);
            } else {
               super.onMeasure(var1, var2);
            }
         } else {
            super.onMeasure(var1, var2);
         }
      }
   }

   protected void onPageBeginMoving() {
   }

   protected void onPageEndMoving() {
      this.mWasInOverscroll = false;
   }

   public final void onPostReorderingAnimationCompleted() {
      int var1 = this.mPostReorderingPreZoomInRemainingAnimationCount - 1;
      this.mPostReorderingPreZoomInRemainingAnimationCount = var1;
      Runnable var2 = this.mPostReorderingPreZoomInRunnable;
      if (var2 != null && var1 == 0) {
         Intrinsics.checkNotNull(var2);
         var2.run();
         this.mPostReorderingPreZoomInRunnable = null;
      }

   }

   protected boolean onRequestFocusInDescendants(int var1, Rect var2) {
      int var3 = this.mNextPage;
      if (var3 == -1) {
         var3 = this.mCurrentPage;
      }

      View var5 = this.getPageAt(var3);
      boolean var4;
      if (var5 != null) {
         var4 = var5.requestFocus(var1, var2);
      } else {
         var4 = false;
      }

      return var4;
   }

   protected final void onScrollInteractionBegin() {
   }

   protected final void onScrollInteractionEnd() {
   }

   public final void onStartReordering() {
      this.mTouchState = 4;
      this.mIsReordering = true;
      this.invalidate();
   }

   public boolean onTouchEvent(MotionEvent var1) {
      Intrinsics.checkNotNullParameter(var1, "ev");
      super.onTouchEvent(var1);
      if (this.getChildCount() <= 0) {
         return super.onTouchEvent(var1);
      } else {
         this.acquireVelocityTrackerAndAddMovement(var1);
         int var5 = var1.getAction() & 255;
         boolean var9 = false;
         float var2;
         if (var5 != 0) {
            float var3;
            int var6;
            float[] var14;
            LauncherScroller var16;
            if (var5 != 1) {
               if (var5 != 2) {
                  if (var5 != 3) {
                     if (var5 == 6) {
                        this.onSecondaryPointerUp(var1);
                        this.releaseVelocityTracker();
                     }
                  } else {
                     if (this.mTouchState == 1) {
                        this.snapToDestination();
                     }

                     this.resetTouchState();
                  }
               } else {
                  var5 = this.mTouchState;
                  if (var5 == 1) {
                     var5 = var1.findPointerIndex(this.mActivePointerId);
                     if (var5 == -1) {
                        return true;
                     }

                     var2 = var1.getX(var5);
                     var3 = this.mLastMotionX + this.mLastMotionXRemainder - var2;
                     this.mTotalMotionX += Math.abs(var3);
                     if (Math.abs(var3) >= 1.0F) {
                        this.mTouchX += var3;
                        this.mSmoothingTime = (float)System.nanoTime() / 1.0E9F;
                        var5 = (int)var3;
                        this.scrollBy(var5, 0);
                        this.mLastMotionX = var2;
                        this.mLastMotionXRemainder = var3 - (float)var5;
                     } else {
                        this.awakenScrollBars();
                     }
                  } else if (var5 == 4) {
                     this.mLastMotionX = var1.getX();
                     var2 = var1.getY();
                     this.mLastMotionY = var2;
                     var14 = this.mapPointFromViewToParent((View)this, this.mLastMotionX, var2);
                     this.mParentDownMotionX = var14[0];
                     this.mParentDownMotionY = var14[1];
                     this.updateDragViewTranslationDuringDrag();
                     var6 = this.indexOfChild(this.mDragView);
                     if (LogUtil.log5()) {
                        LogUtil.d("mLastMotionX: " + this.mLastMotionX);
                     }

                     if (LogUtil.log5()) {
                        LogUtil.d("mLastMotionY: " + this.mLastMotionY);
                     }

                     if (LogUtil.log5()) {
                        LogUtil.d("mParentDownMotionX: " + this.mParentDownMotionX);
                     }

                     if (LogUtil.log5()) {
                        LogUtil.d("mParentDownMotionY: " + this.mParentDownMotionY);
                     }

                     var5 = this.getNearestHoverOverPageIndex();
                     if (var5 > -1 && var5 != this.indexOfChild(this.mDragView)) {
                        int[] var15 = this.mTempVisiblePagesRange;
                        var15[0] = 0;
                        var15[1] = this.getPageCount() - 1;
                        this.getFreeScrollPageRange(this.mTempVisiblePagesRange);
                        var15 = this.mTempVisiblePagesRange;
                        if (var15[0] <= var5 && var5 <= var15[1] && var5 != this.mSidePageHoverIndex) {
                           var16 = this.mScroller;
                           Intrinsics.checkNotNull(var16);
                           if (var16.isFinished()) {
                              this.mSidePageHoverIndex = var5;
                              PagedView$$ExternalSyntheticLambda0 var17 = new PagedView$$ExternalSyntheticLambda0(this, var5, var6);
                              this.mSidePageHoverRunnable = var17;
                              this.postDelayed(var17, 80L);
                           }
                        }
                     } else {
                        this.removeCallbacks(this.mSidePageHoverRunnable);
                        this.mSidePageHoverIndex = -1;
                     }
                  } else {
                     determineScrollingStart$default(this, var1, 0.0F, 2, (Object)null);
                  }
               }
            } else {
               var5 = this.mTouchState;
               if (var5 == 1) {
                  var5 = this.mActivePointerId;
                  float var4 = var1.getX(var1.findPointerIndex(var5));
                  VelocityTracker var18 = this.mVelocityTracker;
                  Intrinsics.checkNotNull(var18);
                  var18.computeCurrentVelocity(1000, (float)this.mMaximumVelocity);
                  int var11 = (int)var18.getXVelocity(var5);
                  int var8 = (int)(var4 - this.mDownMotionX);
                  View var19 = this.getPageAt(this.mCurrentPage);
                  Intrinsics.checkNotNull(var19);
                  var5 = var19.getMeasuredWidth();
                  var3 = (float)Math.abs(var8);
                  var2 = (float)var5;
                  boolean var21;
                  if (var3 > 0.4F * var2) {
                     var21 = true;
                  } else {
                     var21 = false;
                  }

                  var3 = this.mTotalMotionX + Math.abs(this.mLastMotionX + this.mLastMotionXRemainder - var4);
                  this.mTotalMotionX = var3;
                  boolean var20;
                  if (var3 > 25.0F && Math.abs(var11) > this.mFlingThresholdVelocity) {
                     var20 = true;
                  } else {
                     var20 = false;
                  }

                  if (!this.mFreeScroll) {
                     label231: {
                        boolean var7;
                        label188: {
                           if ((float)Math.abs(var8) > var2 * 0.33F) {
                              if (Math.signum((float)var11) == Math.signum((float)var8)) {
                                 var7 = true;
                              } else {
                                 var7 = false;
                              }

                              if (!var7 && var20) {
                                 var7 = true;
                                 break label188;
                              }
                           }

                           var7 = false;
                        }

                        boolean var12;
                        boolean var22;
                        label181: {
                           label180: {
                              var12 = this.mIsRtl;
                              if (var12) {
                                 if (var8 > 0) {
                                    break label180;
                                 }
                              } else if (var8 < 0) {
                                 break label180;
                              }

                              var22 = false;
                              break label181;
                           }

                           var22 = true;
                        }

                        label173: {
                           if (var12) {
                              if (var11 <= 0) {
                                 break label173;
                              }
                           } else if (var11 >= 0) {
                              break label173;
                           }

                           var9 = true;
                        }

                        if (var21 && !var22 && !var20 || var20 && !var9) {
                           int var10 = this.mCurrentPage;
                           if (var10 > 0) {
                              if (var7) {
                                 var5 = var10;
                              } else {
                                 var5 = var10 - 1;
                              }

                              this.snapToPageWithVelocity(var5, var11);
                              break label231;
                           }
                        }

                        if ((var21 && var22 && !var20 || var20 && var9) && this.mCurrentPage < this.getChildCount() - 1) {
                           var5 = this.mCurrentPage;
                           if (!var7) {
                              ++var5;
                           }

                           this.snapToPageWithVelocity(var5, var11);
                        } else {
                           this.snapToDestination();
                        }
                     }
                  } else {
                     var16 = this.mScroller;
                     Intrinsics.checkNotNull(var16);
                     if (!var16.isFinished()) {
                        this.abortScrollerAnimation(true);
                     }

                     var2 = this.getScaleX();
                     var6 = (int)((float)(-var11) * var2);
                     var5 = (int)((float)this.getScrollX() * var2);
                     var16 = this.mScroller;
                     Intrinsics.checkNotNull(var16);
                     var16.setInterpolator((TimeInterpolator)this.mDefaultInterpolator);
                     var16 = this.mScroller;
                     Intrinsics.checkNotNull(var16);
                     var16.fling(var5, this.getScrollY(), var6, 0, Integer.MIN_VALUE, Integer.MAX_VALUE, 0, 0);
                     this.invalidate();
                  }

                  this.onScrollInteractionEnd();
               } else if (var5 == 2) {
                  var5 = Math.max(0, this.mCurrentPage - 1);
                  if (var5 != this.mCurrentPage) {
                     this.snapToPage(var5);
                  } else {
                     this.snapToDestination();
                  }
               } else if (var5 == 3) {
                  var5 = Math.min(this.getChildCount() - 1, this.mCurrentPage + 1);
                  if (var5 != this.mCurrentPage) {
                     this.snapToPage(var5);
                  } else {
                     this.snapToDestination();
                  }
               } else if (var5 == 4) {
                  this.mLastMotionX = var1.getX();
                  var2 = var1.getY();
                  this.mLastMotionY = var2;
                  var14 = this.mapPointFromViewToParent((View)this, this.mLastMotionX, var2);
                  this.mParentDownMotionX = var14[0];
                  this.mParentDownMotionY = var14[1];
                  this.updateDragViewTranslationDuringDrag();
               } else if (!this.mCancelTap) {
                  this.onUnhandledTap(var1);
               }

               this.removeCallbacks(this.mSidePageHoverRunnable);
               this.resetTouchState();
            }
         } else {
            LauncherScroller var13 = this.mScroller;
            Intrinsics.checkNotNull(var13);
            if (!var13.isFinished()) {
               this.abortScrollerAnimation(false);
            }

            PagedView var23 = (PagedView)this;
            var2 = var1.getX();
            this.mLastMotionX = var2;
            this.mDownMotionX = var2;
            var2 = var1.getY();
            this.mLastMotionY = var2;
            this.mDownMotionY = var2;
            this.mDownScrollX = (float)this.getScrollX();
            float[] var24 = this.mapPointFromViewToParent((View)this, this.mLastMotionX, this.mLastMotionY);
            this.mParentDownMotionX = var24[0];
            this.mParentDownMotionY = var24[1];
            this.mLastMotionXRemainder = 0.0F;
            this.mTotalMotionX = 0.0F;
            this.mActivePointerId = var1.getPointerId(0);
            if (this.mTouchState == 1) {
               this.onScrollInteractionBegin();
               this.pageBeginMoving();
            }
         }

         return true;
      }
   }

   protected final void onUnhandledTap(MotionEvent var1) {
   }

   protected void onVisibilityChanged(View var1, int var2) {
      Intrinsics.checkNotNullParameter(var1, "changedView");
      super.onVisibilityChanged(var1, var2);
      int var4 = this.getChildCount();

      for(int var3 = 0; var3 < var4; ++var3) {
         this.getChildAt(var3).setVisibility(var2);
      }

      this.updatePageIndicator();
   }

   protected final void pageBeginMoving() {
      if (!this.isPageMoving) {
         this.isPageMoving = true;
         this.onPageBeginMoving();
      }

   }

   protected final void pageEndMoving() {
      if (this.isPageMoving) {
         this.isPageMoving = false;
         this.onPageEndMoving();
      }

   }

   public boolean performAccessibilityAction(int var1, Bundle var2) {
      Intrinsics.checkNotNullParameter(var2, "arguments");
      if (super.performAccessibilityAction(var1, var2)) {
         return true;
      } else {
         if (var1 != 4096) {
            if (var1 == 8192 && this.getCurrentPage() > 0) {
               this.scrollLeft();
               return true;
            }
         } else if (this.getCurrentPage() < this.getPageCount() - 1) {
            this.scrollRight();
            return true;
         }

         return false;
      }
   }

   public boolean performLongClick() {
      this.mCancelTap = true;
      return super.performLongClick();
   }

   public void removeAllViewsInLayout() {
      this.updatePageIndicator(0, this.getChildCount());
      super.removeAllViewsInLayout();
   }

   public void removeView(View var1) {
      Intrinsics.checkNotNullParameter(var1, "v");
      this.removeMarkerForView(this.indexOfChild(var1));
      super.removeView(var1);
   }

   public void removeViewAt(int var1) {
      this.removeMarkerForView(var1);
      super.removeViewAt(var1);
   }

   public void removeViewInLayout(View var1) {
      Intrinsics.checkNotNullParameter(var1, "v");
      this.removeMarkerForView(this.indexOfChild(var1));
      super.removeViewInLayout(var1);
   }

   public void requestChildFocus(View var1, View var2) {
      Intrinsics.checkNotNullParameter(var1, "child");
      Intrinsics.checkNotNullParameter(var2, "focused");
      super.requestChildFocus(var1, var2);
      int var3 = this.indexToPage(this.indexOfChild(var1));
      if (var3 >= 0 && var3 != this.getCurrentPage() && !this.isInTouchMode()) {
         this.snapToPage(var3);
      }

   }

   public boolean requestChildRectangleOnScreen(View var1, Rect var2, boolean var3) {
      Intrinsics.checkNotNullParameter(var1, "child");
      Intrinsics.checkNotNullParameter(var2, "rectangle");
      int var4 = this.indexToPage(this.indexOfChild(var1));
      if (var4 == this.mCurrentPage) {
         LauncherScroller var5 = this.mScroller;
         Intrinsics.checkNotNull(var5);
         if (var5.isFinished()) {
            return false;
         }
      }

      this.snapToPage(var4);
      return true;
   }

   public void requestDisallowInterceptTouchEvent(boolean var1) {
      if (var1) {
         View var2 = this.getPageAt(this.mCurrentPage);
         if (var2 != null) {
            var2.cancelLongPress();
         }
      }

      super.requestDisallowInterceptTouchEvent(var1);
   }

   protected final void screenScrolled(int var1) {
   }

   public void scrollBy(int var1, int var2) {
      this.scrollTo(this.getScrollX() + var1, this.getScrollY() + var2);
   }

   public final void scrollLeft() {
      if (this.getNextPage() > 0) {
         this.snapToPage(this.getNextPage() - 1);
      }

   }

   public final boolean scrollRight() {
      int var1 = this.getNextPage();
      int var2 = this.getChildCount();
      boolean var3 = true;
      if (var1 < var2 - 1) {
         this.snapToPage(this.getNextPage() + 1);
      } else {
         var3 = false;
      }

      return var3;
   }

   public void scrollTo(int var1, int var2) {
      int var3 = var1;
      if (this.mFreeScroll) {
         LauncherScroller var6 = this.mScroller;
         Intrinsics.checkNotNull(var6);
         if (!var6.isFinished() && (var1 > this.mFreeScrollMaxScrollX || var1 < this.mFreeScrollMinScrollX)) {
            this.forceFinishScroller();
         }

         var3 = Math.max(Math.min(var1, this.mFreeScrollMaxScrollX), this.mFreeScrollMinScrollX);
      }

      boolean var5;
      boolean var7;
      label67: {
         label66: {
            var5 = this.mIsRtl;
            if (var5) {
               if (var3 > this.mMaxScrollX) {
                  break label66;
               }
            } else if (var3 < 0) {
               break label66;
            }

            var7 = false;
            break label67;
         }

         var7 = true;
      }

      boolean var4;
      label59: {
         label58: {
            if (var5) {
               if (var3 < 0) {
                  break label58;
               }
            } else if (var3 > this.mMaxScrollX) {
               break label58;
            }

            var4 = false;
            break label59;
         }

         var4 = true;
      }

      if (var7) {
         if (var5) {
            var1 = this.mMaxScrollX;
         } else {
            var1 = 0;
         }

         super.scrollTo(var1, var2);
         if (this.mAllowOverScroll) {
            this.mWasInOverscroll = true;
         }
      } else if (var4) {
         if (var5) {
            var1 = 0;
         } else {
            var1 = this.mMaxScrollX;
         }

         super.scrollTo(var1, var2);
         if (this.mAllowOverScroll) {
            this.mWasInOverscroll = true;
         }
      } else {
         if (this.mWasInOverscroll) {
            this.mWasInOverscroll = false;
         }

         super.scrollTo(var3, var2);
      }

      this.mTouchX = (float)var3;
      this.mSmoothingTime = (float)System.nanoTime() / 1.0E9F;
      if (this.isReordering(true)) {
         float[] var8 = this.mapPointFromParentToView((View)this, this.mParentDownMotionX, this.mParentDownMotionY);
         this.mLastMotionX = var8[0];
         this.mLastMotionY = var8[1];
         this.updateDragViewTranslationDuringDrag();
      }

   }

   public void sendAccessibilityEvent(int var1) {
      if (var1 != 4096) {
         super.sendAccessibilityEvent(var1);
      }

   }

   public final void setCurrentPage(int var1) {
      LauncherScroller var2 = this.mScroller;
      Intrinsics.checkNotNull(var2);
      if (!var2.isFinished()) {
         this.abortScrollerAnimation(true);
      }

      if (this.getChildCount() != 0) {
         this.mForceScreenScrolled = true;
         this.mCurrentPage = this.validateNewPage(var1);
         this.updateCurrentPageScroll();
         this.notifyPageSwitchListener();
         this.invalidate();
      }
   }

   protected final void setDefaultInterpolator(Interpolator var1) {
      this.mDefaultInterpolator = var1;
      LauncherScroller var2 = this.mScroller;
      Intrinsics.checkNotNull(var2);
      var2.setInterpolator((TimeInterpolator)this.mDefaultInterpolator);
   }

   protected final void setEnableOverscroll(boolean var1) {
      this.mAllowOverScroll = var1;
   }

   protected final void setMActivePointerId(int var1) {
      this.mActivePointerId = var1;
   }

   protected final void setMAllowOverScroll(boolean var1) {
      this.mAllowOverScroll = var1;
   }

   protected final void setMCellCountX(int var1) {
      this.mCellCountX = var1;
   }

   protected final void setMCellCountY(int var1) {
      this.mCellCountY = var1;
   }

   protected final void setMCenterPagesVertically(boolean var1) {
      this.mCenterPagesVertically = var1;
   }

   protected final void setMChildCountOnLastLayout(int var1) {
      this.mChildCountOnLastLayout = var1;
   }

   protected final void setMCurrentPage(int var1) {
      this.mCurrentPage = var1;
   }

   protected final void setMDensity(float var1) {
      this.mDensity = var1;
   }

   protected final void setMDragView(View var1) {
      this.mDragView = var1;
   }

   protected final void setMFadeInAdjacentScreens(boolean var1) {
      this.mFadeInAdjacentScreens = var1;
   }

   protected final void setMFirstLayout(boolean var1) {
      this.mFirstLayout = var1;
   }

   protected final void setMFlingThresholdVelocity(int var1) {
      this.mFlingThresholdVelocity = var1;
   }

   protected final void setMForceDrawAllChildrenNextFrame(boolean var1) {
      this.mForceDrawAllChildrenNextFrame = var1;
   }

   protected final void setMForceScreenScrolled(boolean var1) {
      this.mForceScreenScrolled = var1;
   }

   protected final void setMLastMotionX(float var1) {
      this.mLastMotionX = var1;
   }

   protected final void setMLastMotionXRemainder(float var1) {
      this.mLastMotionXRemainder = var1;
   }

   protected final void setMLastMotionY(float var1) {
      this.mLastMotionY = var1;
   }

   protected final void setMLongClickListener(OnLongClickListener var1) {
      this.mLongClickListener = var1;
   }

   protected final void setMMaxScrollX(int var1) {
      this.mMaxScrollX = var1;
   }

   protected final void setMMinFlingVelocity(int var1) {
      this.mMinFlingVelocity = var1;
   }

   protected final void setMMinSnapVelocity(int var1) {
      this.mMinSnapVelocity = var1;
   }

   protected final void setMNextPage(int var1) {
      this.mNextPage = var1;
   }

   public final void setMPageSpacing(int var1) {
      this.mPageSpacing = var1;
   }

   protected final void setMScroller(LauncherScroller var1) {
      this.mScroller = var1;
   }

   public final void setMSidePageHoverIndex(int var1) {
      this.mSidePageHoverIndex = var1;
   }

   protected final void setMSmoothingTime(float var1) {
      this.mSmoothingTime = var1;
   }

   protected final void setMTempVisiblePagesRange(int[] var1) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.mTempVisiblePagesRange = var1;
   }

   protected final void setMTotalMotionX(float var1) {
      this.mTotalMotionX = var1;
   }

   protected final void setMTouchSlop(int var1) {
      this.mTouchSlop = var1;
   }

   protected final void setMTouchState(int var1) {
      this.mTouchState = var1;
   }

   protected final void setMTouchX(float var1) {
      this.mTouchX = var1;
   }

   public final void setMinScale(float var1) {
      this.mMinScale = var1;
      this.mUseMinScale = true;
      this.requestLayout();
   }

   public void setOnLongClickListener(OnLongClickListener var1) {
      this.mLongClickListener = var1;
      int var3 = this.getPageCount();

      for(int var2 = 0; var2 < var3; ++var2) {
         View var4 = this.getPageAt(var2);
         if (var4 != null) {
            var4.setOnLongClickListener(var1);
         }
      }

      super.setOnLongClickListener(var1);
   }

   protected final void setPageMoving(boolean var1) {
      this.isPageMoving = var1;
   }

   public final void setPageSpacing(int var1) {
      this.mPageSpacing = var1;
      this.requestLayout();
   }

   public final void setPageSwitchListener(PageSwitchListener var1) {
      this.mPageSwitchListener = var1;
      if (var1 != null) {
         Intrinsics.checkNotNull(var1);
         var1.onPageSwitch(this.getPageAt(this.mCurrentPage), this.mCurrentPage);
      }

   }

   public final void setRestorePage(int var1) {
      this.restorePage = var1;
   }

   public void setScaleX(float var1) {
      super.setScaleX(var1);
      if (this.isReordering(true)) {
         float[] var2 = this.mapPointFromParentToView((View)this, this.mParentDownMotionX, this.mParentDownMotionY);
         this.mLastMotionX = var2[0];
         this.mLastMotionY = var2[1];
         this.updateDragViewTranslationDuringDrag();
      }

   }

   protected final boolean shouldDrawChild(View var1) {
      Intrinsics.checkNotNullParameter(var1, "child");
      boolean var2;
      if (var1.getVisibility() == 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      return var2;
   }

   protected final void snapToDestination() {
      snapToPage$default(this, this.getPageNearestToCenterOfScreen(), 300, false, (TimeInterpolator)null, 12, (Object)null);
   }

   public final void snapToPage(int var1) {
      snapToPage$default(this, var1, 300, false, (TimeInterpolator)null, 12, (Object)null);
   }

   protected final void snapToPage(int var1, int var2, int var3, boolean var4, TimeInterpolator var5) {
      var1 = this.validateNewPage(var1);
      this.mNextPage = var1;
      View var7 = this.getFocusedChild();
      if (var7 != null) {
         int var6 = this.mCurrentPage;
         if (var1 != var6 && var7 == this.getPageAt(var6)) {
            var7.clearFocus();
         }
      }

      this.pageBeginMoving();
      this.awakenScrollBars(var3);
      if (var4) {
         var1 = 0;
      } else {
         var1 = var3;
         if (var3 == 0) {
            var1 = Math.abs(var2);
         }
      }

      LauncherScroller var9 = this.mScroller;
      Intrinsics.checkNotNull(var9);
      if (!var9.isFinished()) {
         this.abortScrollerAnimation(false);
      }

      LauncherScroller var8;
      if (var5 != null) {
         var9 = this.mScroller;
         Intrinsics.checkNotNull(var9);
         var9.setInterpolator(var5);
      } else {
         var8 = this.mScroller;
         Intrinsics.checkNotNull(var8);
         var8.setInterpolator((TimeInterpolator)this.mDefaultInterpolator);
      }

      var8 = this.mScroller;
      Intrinsics.checkNotNull(var8);
      var8.startScroll(this.getScrollX(), 0, var2, 0, var1);
      this.updatePageIndicator();
      if (var4) {
         this.computeScroll();
      }

      this.mForceScreenScrolled = true;
      this.invalidate();
   }

   protected final void snapToPage(int var1, int var2, TimeInterpolator var3) {
      this.snapToPage(var1, var2, false, var3);
   }

   protected final void snapToPage(int var1, int var2, boolean var3, TimeInterpolator var4) {
      var1 = this.validateNewPage(var1);
      this.snapToPage(var1, this.getScrollForPage(var1) - this.getScrollX(), var2, var3, var4);
   }

   public final void snapToPageImmediately(int var1) {
      this.snapToPage(var1, 300, true, (TimeInterpolator)null);
   }

   protected final void snapToPageWithVelocity(int var1, int var2) {
      var1 = this.validateNewPage(var1);
      int var6 = this.getViewportWidth() / 2;
      int var5 = this.getScrollForPage(var1) - this.getScrollX();
      if (Math.abs(var2) < this.mMinFlingVelocity) {
         snapToPage$default(this, var1, 300, false, (TimeInterpolator)null, 12, (Object)null);
      } else {
         float var4 = Math.min(1.0F, (float)Math.abs(var5) * 1.0F / (float)(var6 * 2));
         float var3 = (float)var6;
         var4 = this.distanceInfluenceForSnapDuration(var4);
         var2 = Math.abs(var2);
         var2 = Math.max(this.mMinSnapVelocity, var2);
         snapToPage$default(this, var1, var5, Math.round((float)1000 * Math.abs((var3 + var4 * var3) / (float)var2)) * 2, false, (TimeInterpolator)null, 24, (Object)null);
      }
   }

   public final boolean startReordering(View var1) {
      int var2 = this.indexOfChild(var1);
      if (this.mTouchState == 0 && var2 != -1) {
         int[] var3 = this.mTempVisiblePagesRange;
         var3[0] = 0;
         var3[1] = this.getPageCount() - 1;
         this.getFreeScrollPageRange(this.mTempVisiblePagesRange);
         this.mReorderingStarted = true;
         var3 = this.mTempVisiblePagesRange;
         if (var3[0] <= var2 && var2 <= var3[1]) {
            var1 = this.getChildAt(var2);
            this.mDragView = var1;
            Intrinsics.checkNotNull(var1);
            var1.animate().scaleX(1.15F).scaleY(1.15F).setDuration(100L).start();
            var1 = this.mDragView;
            Intrinsics.checkNotNull(var1);
            this.mDragViewBaselineLeft = (float)var1.getLeft();
            this.snapToPage(this.getPageNearestToCenterOfScreen());
            this.disableFreeScroll();
            this.onStartReordering();
            return true;
         }
      }

      return false;
   }

   protected final void updateCurrentPageScroll() {
      int var1 = this.mCurrentPage;
      if (var1 >= 0 && var1 < this.getPageCount()) {
         var1 = this.getScrollForPage(this.mCurrentPage);
      } else {
         var1 = 0;
      }

      this.scrollTo(var1, 0);
      LauncherScroller var2 = this.mScroller;
      if (var2 != null) {
         var2.setFinalX(var1);
      }

      this.forceFinishScroller();
   }

   public final void updateFreescrollBounds() {
      this.getFreeScrollPageRange(this.mTempVisiblePagesRange);
      if (this.mIsRtl) {
         this.mFreeScrollMinScrollX = this.getScrollForPage(this.mTempVisiblePagesRange[1]);
         this.mFreeScrollMaxScrollX = this.getScrollForPage(this.mTempVisiblePagesRange[0]);
      } else {
         this.mFreeScrollMinScrollX = this.getScrollForPage(this.mTempVisiblePagesRange[0]);
         this.mFreeScrollMaxScrollX = this.getScrollForPage(this.mTempVisiblePagesRange[1]);
      }

   }

   public final void updateMaxScrollX() {
      int var3 = this.getChildCount();
      int var1 = 0;
      byte var2 = 0;
      if (var3 > 0) {
         if (this.mIsRtl) {
            var1 = var2;
         } else {
            var1 = var3 - 1;
         }

         var1 = this.getScrollForPage(var1);
      }

      this.mMaxScrollX = var1;
   }

   protected void updatePageIndicator(int var1, int var2) {
   }

   @Metadata(
      d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0015\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u0015\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0014\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0084T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0006X\u0084T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0006X\u0084T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0004X\u0084T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0006X\u0084T¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u001a\u0010\u0015\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u000e\u0010\u001a\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u0006X\u0084T¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u001fX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u0006X\u0084T¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u0006X\u0084T¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020\u0006X\u0084T¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\u0006X\u0084T¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020\u0006X\u0084T¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020&X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020(X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020*X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010+\u001a\u00020,X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006-"},
      d2 = {"Lcom/hzbhd/ui/view/paged/PagedView$Companion;", "", "()V", "ALPHA_QUANTIZE_LEVEL", "", "ANIM_TAG_KEY", "", "AUTOMATIC_PAGE_SPACING", "DEBUG", "", "FLING_THRESHOLD_VELOCITY", "INVALID_PAGE", "INVALID_POINTER", "INVALID_RESTORE_PAGE", "MAX_SCROLL_PROGRESS", "MIN_FLING_VELOCITY", "MIN_LENGTH_FOR_FLING", "MIN_SNAP_VELOCITY", "NANOTIME_DIV", "PAGE_SNAP_ANIMATION_DURATION", "REORDERING_DROP_REPOSITION_DURATION", "REORDERING_REORDER_REPOSITION_DURATION", "getREORDERING_REORDER_REPOSITION_DURATION", "()I", "setREORDERING_REORDER_REPOSITION_DURATION", "(I)V", "REORDERING_SIDE_PAGE_HOVER_TIMEOUT", "RETURN_TO_ORIGINAL_PAGE_THRESHOLD", "SIGNIFICANT_MOVE_THRESHOLD", "SLOW_PAGE_SNAP_ANIMATION_DURATION", "TAG", "", "TOUCH_STATE_NEXT_PAGE", "TOUCH_STATE_PREV_PAGE", "TOUCH_STATE_REORDERING", "TOUCH_STATE_REST", "TOUCH_STATE_SCROLLING", "sTmpIntPoint", "", "sTmpInvMatrix", "Landroid/graphics/Matrix;", "sTmpPoint", "", "sTmpRect", "Landroid/graphics/Rect;", "commonview-base_release"},
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

      public final int getREORDERING_REORDER_REPOSITION_DURATION() {
         return PagedView.REORDERING_REORDER_REPOSITION_DURATION;
      }

      public final void setREORDERING_REORDER_REPOSITION_DURATION(int var1) {
         PagedView.REORDERING_REORDER_REPOSITION_DURATION = var1;
      }
   }

   @Metadata(
      d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005B\u001b\b\u0016\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\nB\u0011\b\u0016\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001¢\u0006\u0002\u0010\fR\u001a\u0010\r\u001a\u00020\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000f\"\u0004\b\u0010\u0010\u0011¨\u0006\u0012"},
      d2 = {"Lcom/hzbhd/ui/view/paged/PagedView$LayoutParams;", "Landroid/view/ViewGroup$LayoutParams;", "width", "", "height", "(II)V", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "source", "(Landroid/view/ViewGroup$LayoutParams;)V", "isFullScreenPage", "", "()Z", "setFullScreenPage", "(Z)V", "commonview-base_release"},
      k = 1,
      mv = {1, 7, 1},
      xi = 48
   )
   public static final class LayoutParams extends ViewGroup.LayoutParams {
      private boolean isFullScreenPage;

      public LayoutParams(int var1, int var2) {
         super(var1, var2);
      }

      public LayoutParams(Context var1, AttributeSet var2) {
         super(var1, var2);
      }

      public LayoutParams(ViewGroup.LayoutParams var1) {
         super(var1);
      }

      public final boolean isFullScreenPage() {
         return this.isFullScreenPage;
      }

      public final void setFullScreenPage(boolean var1) {
         this.isFullScreenPage = var1;
      }
   }

   @Metadata(
      d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\bf\u0018\u00002\u00020\u0001J\u001a\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&¨\u0006\b"},
      d2 = {"Lcom/hzbhd/ui/view/paged/PagedView$PageSwitchListener;", "", "onPageSwitch", "", "newPage", "Landroid/view/View;", "newPageIndex", "", "commonview-base_release"},
      k = 1,
      mv = {1, 7, 1},
      xi = 48
   )
   public interface PageSwitchListener {
      void onPageSwitch(View var1, int var2);
   }

   @Metadata(
      d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B\u0011\b\u0010\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004B\u000f\b\u0010\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0018\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00062\u0006\u0010\u0011\u001a\u00020\tH\u0016R\u001a\u0010\b\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\r¨\u0006\u0013"},
      d2 = {"Lcom/hzbhd/ui/view/paged/PagedView$SavedState;", "Landroid/view/View$BaseSavedState;", "superState", "Landroid/os/Parcelable;", "(Landroid/os/Parcelable;)V", "in", "Landroid/os/Parcel;", "(Landroid/os/Parcel;)V", "currentPage", "", "getCurrentPage", "()I", "setCurrentPage", "(I)V", "writeToParcel", "", "out", "flags", "Companion", "commonview-base_release"},
      k = 1,
      mv = {1, 7, 1},
      xi = 48
   )
   public static final class SavedState extends BaseSavedState {
      private static final Creator CREATOR = (Creator)(new Creator() {
         public SavedState createFromParcel(Parcel var1) {
            Intrinsics.checkNotNullParameter(var1, "in");
            return new SavedState(var1);
         }

         public SavedState[] newArray(int var1) {
            return new SavedState[var1];
         }
      });
      public static final Companion Companion = new Companion((DefaultConstructorMarker)null);
      private int currentPage;

      public SavedState(Parcel var1) {
         Intrinsics.checkNotNullParameter(var1, "in");
         super(var1);
         this.currentPage = -1;
         this.currentPage = var1.readInt();
      }

      public SavedState(Parcelable var1) {
         super(var1);
         this.currentPage = -1;
      }

      public final int getCurrentPage() {
         return this.currentPage;
      }

      public final void setCurrentPage(int var1) {
         this.currentPage = var1;
      }

      public void writeToParcel(Parcel var1, int var2) {
         Intrinsics.checkNotNullParameter(var1, "out");
         super.writeToParcel(var1, var2);
         var1.writeInt(this.currentPage);
      }

      @Metadata(
         d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0019\u0010\u0003\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"},
         d2 = {"Lcom/hzbhd/ui/view/paged/PagedView$SavedState$Companion;", "", "()V", "CREATOR", "Landroid/os/Parcelable$Creator;", "Lcom/hzbhd/ui/view/paged/PagedView$SavedState;", "getCREATOR", "()Landroid/os/Parcelable$Creator;", "commonview-base_release"},
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

         public final Creator getCREATOR() {
            return SavedState.CREATOR;
         }
      }
   }

   @Metadata(
      d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0016¨\u0006\u0006"},
      d2 = {"Lcom/hzbhd/ui/view/paged/PagedView$ScrollInterpolator;", "Landroid/view/animation/Interpolator;", "()V", "getInterpolation", "", "t", "commonview-base_release"},
      k = 1,
      mv = {1, 7, 1},
      xi = 48
   )
   private static final class ScrollInterpolator implements Interpolator {
      public ScrollInterpolator() {
      }

      public float getInterpolation(float var1) {
         --var1;
         return var1 * var1 * var1 * var1 * var1 + (float)1;
      }
   }
}
