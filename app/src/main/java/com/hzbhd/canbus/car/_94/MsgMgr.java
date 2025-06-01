package com.hzbhd.canbus.car._94;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.util.SparseArray;
import android.util.SparseIntArray;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.SyncActivity;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.PanoramicBtnUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.entity.SyncListUpdateEntity;
import com.hzbhd.canbus.entity.SyncSoftKeyUpdateEntity;
import com.hzbhd.canbus.entity.TireUpdateEntity;
import com.hzbhd.canbus.interfaces.OnParseListener;
import com.hzbhd.canbus.interfaces.UiMgrInterface;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralHybirdData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_datas.GeneralSyncData;
import com.hzbhd.canbus.ui_datas.GeneralTireData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.SystemUtil;
import com.hzbhd.canbus.util.TimerUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.commontools.SourceConstantsDef;
import com.hzbhd.constant.disc.MpegConstantsDef;
import com.hzbhd.proxy.share.ShareDataManager;
import com.hzbhd.util.LogUtil;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimerTask;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.internal.ProgressionUtilKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.ArrayIteratorKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;

@Metadata(
   d1 = {"\u0000Ì\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0015\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b&\n\u0002\u0010\u0005\n\u0002\b'\n\u0002\u0010\t\n\u0002\b\u0016\n\u0002\u0018\u0002\n\u0002\b\u000e\u0018\u0000 É\u00012\u00020\u0001:\nÉ\u0001Ê\u0001Ë\u0001Ì\u0001Í\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010E\u001a\u00020F2\b\u0010G\u001a\u0004\u0018\u00010!H\u0016J\b\u0010H\u001a\u00020FH\u0016J&\u0010I\u001a\u00020F2\b\u0010J\u001a\u0004\u0018\u00010&2\b\u0010K\u001a\u0004\u0018\u00010&2\b\u0010L\u001a\u0004\u0018\u00010&H\u0016J \u0010M\u001a\u00020F2\u0006\u0010N\u001a\u00020\u00122\u0006\u0010O\u001a\u00020P2\u0006\u0010Q\u001a\u00020PH\u0016J \u0010R\u001a\u00020F2\u0006\u0010N\u001a\u00020\u00122\u0006\u0010O\u001a\u00020P2\u0006\u0010Q\u001a\u00020PH\u0016JT\u0010S\u001a\u00020F2\u0006\u0010T\u001a\u00020\u00042\b\u0010N\u001a\u0004\u0018\u00010\u00122\u0006\u0010U\u001a\u00020P2\u0006\u0010V\u001a\u00020P2\u0006\u0010O\u001a\u00020P2\u0006\u0010Q\u001a\u00020P2\u0006\u0010W\u001a\u00020\u00042\u0006\u0010X\u001a\u00020\u00042\b\u0010Y\u001a\u0004\u0018\u00010ZH\u0016J*\u0010[\u001a\u00020F2\b\u0010N\u001a\u0004\u0018\u00010\u00122\u0006\u0010O\u001a\u00020P2\u0006\u0010Q\u001a\u00020P2\u0006\u0010\\\u001a\u00020\u0004H\u0016J\u001a\u0010]\u001a\u00020F2\b\u0010G\u001a\u0004\u0018\u00010!2\u0006\u0010^\u001a\u00020\u0012H\u0016J\u0018\u0010_\u001a\u00020F2\u0006\u0010`\u001a\u00020\u00042\u0006\u0010a\u001a\u00020PH\u0016J\u001a\u0010b\u001a\u00020P2\b\u0010G\u001a\u0004\u0018\u00010!2\u0006\u0010c\u001a\u00020\u0004H\u0016Jp\u0010d\u001a\u00020F2\u0006\u0010e\u001a\u00020\u00042\u0006\u0010f\u001a\u00020\u00042\u0006\u0010g\u001a\u00020\u00042\u0006\u0010h\u001a\u00020\u00042\u0006\u0010i\u001a\u00020\u00042\u0006\u0010j\u001a\u00020\u00042\u0006\u0010k\u001a\u00020\u00042\u0006\u0010l\u001a\u00020\u00042\u0006\u0010m\u001a\u00020\u00042\u0006\u0010n\u001a\u00020P2\u0006\u0010o\u001a\u00020P2\u0006\u0010p\u001a\u00020P2\u0006\u0010q\u001a\u00020\u0004H\u0016Jh\u0010r\u001a\u00020F2\u0006\u0010s\u001a\u00020\u00042\u0006\u0010t\u001a\u00020\u00042\u0006\u0010u\u001a\u00020\u00042\u0006\u0010v\u001a\u00020\u00042\u0006\u0010w\u001a\u00020\u00042\u0006\u0010x\u001a\u00020\u00042\u0006\u0010y\u001a\u00020\u00042\u0006\u0010z\u001a\u00020\u00042\u0006\u0010{\u001a\u00020\u00042\u0006\u0010|\u001a\u00020\u00042\u0006\u0010}\u001a\u00020\u00042\u0006\u0010~\u001a\u00020\u0004H\u0016J\u0082\u0001\u0010\u007f\u001a\u00020F2\b\u0010\u0080\u0001\u001a\u00030\u0081\u00012\u0007\u0010\u0082\u0001\u001a\u00020\u00042\u0007\u0010\u0083\u0001\u001a\u00020\u00042\u0007\u0010\u0084\u0001\u001a\u00020\u00042\u0007\u0010\u0085\u0001\u001a\u00020\u00042\u0007\u0010\u0086\u0001\u001a\u00020\u00042\u0007\u0010\u0087\u0001\u001a\u00020\u00042\u0007\u0010\u0088\u0001\u001a\u00020P2\u0007\u0010\u0089\u0001\u001a\u00020P2\u0007\u0010\u008a\u0001\u001a\u00020\u00042\t\u0010\u008b\u0001\u001a\u0004\u0018\u00010&2\b\u0010L\u001a\u0004\u0018\u00010&2\b\u0010K\u001a\u0004\u0018\u00010&H\u0016J\u000f\u0010\u008c\u0001\u001a\u00020\u00172\u0006\u0010G\u001a\u00020!J\u0012\u0010\u008d\u0001\u001a\u00020\u00042\u0007\u0010\u008e\u0001\u001a\u00020\u0004H\u0002J\u0012\u0010\u008f\u0001\u001a\u00020\u00042\u0007\u0010\u008e\u0001\u001a\u00020\u0004H\u0002J\u001b\u0010\u0090\u0001\u001a\u00020\u00042\u0007\u0010\u0091\u0001\u001a\u00020\u00042\u0007\u0010\u0092\u0001\u001a\u00020\u0004H\u0002J\u001a\u0010\u0093\u0001\u001a\u00020\u00042\u0006\u0010G\u001a\u00020!2\u0007\u0010\u0094\u0001\u001a\u00020&H\u0002J\t\u0010\u0095\u0001\u001a\u00020FH\u0002J\u0013\u0010\u0096\u0001\u001a\u00020F2\b\u0010G\u001a\u0004\u0018\u00010!H\u0002J\u0013\u0010\u0097\u0001\u001a\u00020F2\b\u0010G\u001a\u0004\u0018\u00010!H\u0016J\u0013\u0010\u0098\u0001\u001a\u00020F2\b\u0010G\u001a\u0004\u0018\u00010!H\u0002J\u0011\u0010\u0099\u0001\u001a\u00020F2\u0006\u0010G\u001a\u00020!H\u0002Jæ\u0001\u0010\u009a\u0001\u001a\u00020F2\b\u0010\u009b\u0001\u001a\u00030\u0081\u00012\b\u0010\u009c\u0001\u001a\u00030\u0081\u00012\u0007\u0010\u009d\u0001\u001a\u00020\u00042\u0007\u0010\u009e\u0001\u001a\u00020\u00042\b\u0010\u009f\u0001\u001a\u00030\u0081\u00012\b\u0010 \u0001\u001a\u00030\u0081\u00012\b\u0010¡\u0001\u001a\u00030\u0081\u00012\b\u0010¢\u0001\u001a\u00030\u0081\u00012\b\u0010£\u0001\u001a\u00030\u0081\u00012\b\u0010¤\u0001\u001a\u00030\u0081\u00012\t\u0010¥\u0001\u001a\u0004\u0018\u00010&2\t\u0010¦\u0001\u001a\u0004\u0018\u00010&2\t\u0010§\u0001\u001a\u0004\u0018\u00010&2\b\u0010¨\u0001\u001a\u00030©\u00012\b\u0010\u0080\u0001\u001a\u00030\u0081\u00012\u0007\u0010ª\u0001\u001a\u00020\u00042\u0007\u0010\u0089\u0001\u001a\u00020P2\b\u0010«\u0001\u001a\u00030©\u00012\t\u0010¬\u0001\u001a\u0004\u0018\u00010&2\t\u0010\u00ad\u0001\u001a\u0004\u0018\u00010&2\t\u0010®\u0001\u001a\u0004\u0018\u00010&2\u0007\u0010¯\u0001\u001a\u00020PH\u0016J<\u0010°\u0001\u001a\u00020F2\u0007\u0010±\u0001\u001a\u00020\u00042\t\u0010²\u0001\u001a\u0004\u0018\u00010&2\t\u0010³\u0001\u001a\u0004\u0018\u00010&2\t\u0010´\u0001\u001a\u0004\u0018\u00010&2\u0007\u0010µ\u0001\u001a\u00020\u0004H\u0016J\u0010\u0010¶\u0001\u001a\u00020F2\u0007\u0010·\u0001\u001a\u00020\u0004J\u0019\u0010¸\u0001\u001a\u00020F2\u0007\u0010·\u0001\u001a\u00020\u001e2\u0007\u0010¹\u0001\u001a\u00020\u0004J\u0007\u0010º\u0001\u001a\u00020FJ\t\u0010»\u0001\u001a\u00020FH\u0002J\u0018\u0010¼\u0001\u001a\u00020F2\u0006\u0010G\u001a\u00020!2\u0007\u0010½\u0001\u001a\u00020\u0004J\u001c\u0010¾\u0001\u001a\u00020F2\b\u0010¿\u0001\u001a\u00030À\u00012\u0007\u0010\u008e\u0001\u001a\u00020\u0004H\u0002J\u0012\u0010Á\u0001\u001a\u00020F2\u0007\u0010Â\u0001\u001a\u00020PH\u0016J\u0012\u0010Ã\u0001\u001a\u00020&2\u0007\u0010½\u0001\u001a\u00020&H\u0002J\u0018\u0010Ä\u0001\u001a\u00020F2\u0006\u0010J\u001a\u00020&2\u0007\u0010½\u0001\u001a\u00020:J\u0011\u0010Å\u0001\u001a\u00020F2\b\u0010Y\u001a\u0004\u0018\u00010ZJ²\u0001\u0010Æ\u0001\u001a\u00020F2\b\u0010\u009b\u0001\u001a\u00030\u0081\u00012\b\u0010\u009c\u0001\u001a\u00030\u0081\u00012\u0007\u0010\u009d\u0001\u001a\u00020\u00042\u0007\u0010\u009e\u0001\u001a\u00020\u00042\b\u0010\u009f\u0001\u001a\u00030\u0081\u00012\b\u0010 \u0001\u001a\u00030\u0081\u00012\b\u0010¡\u0001\u001a\u00030\u0081\u00012\t\u0010¢\u0001\u001a\u0004\u0018\u00010&2\b\u0010£\u0001\u001a\u00030\u0081\u00012\b\u0010¤\u0001\u001a\u00030\u0081\u00012\t\u0010¥\u0001\u001a\u0004\u0018\u00010&2\t\u0010¦\u0001\u001a\u0004\u0018\u00010&2\t\u0010§\u0001\u001a\u0004\u0018\u00010&2\u0007\u0010¨\u0001\u001a\u00020\u00042\b\u0010Ç\u0001\u001a\u00030\u0081\u00012\u0007\u0010\u0089\u0001\u001a\u00020P2\u0007\u0010È\u0001\u001a\u00020\u0004H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00040\u000b¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\rR\u001e\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0011\u001a\u00020\u0012@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u001a0\u0019X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u001eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010 \u001a\u0004\u0018\u00010!X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R*\u0010$\u001a\u001e\u0012\u0004\u0012\u00020&\u0012\u0004\u0012\u00020'0%j\u000e\u0012\u0004\u0012\u00020&\u0012\u0004\u0012\u00020'`(X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020+X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010,\u001a\b\u0012\u0004\u0012\u00020.0-X\u0082\u0004¢\u0006\u0004\n\u0002\u0010/R\u000e\u00100\u001a\u000201X\u0082\u0004¢\u0006\u0002\n\u0000R\u0018\u00102\u001a\f\u0012\b\u0012\u000603R\u00020\u00000\u0019X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u00104\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u00105\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u0004@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b6\u00107R6\u00108\u001a*\u0012\u0004\u0012\u00020&\u0012\n\u0012\b\u0012\u0004\u0012\u00020:090%j\u0014\u0012\u0004\u0012\u00020&\u0012\n\u0012\b\u0012\u0004\u0012\u00020:09`(X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010;\u001a\u00020<X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010=\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010>\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010?\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b@\u00107\"\u0004\bA\u0010BR\u0011\u0010C\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\bD\u0010\t¨\u0006Î\u0001"},
   d2 = {"Lcom/hzbhd/canbus/car/_94/MsgMgr;", "Lcom/hzbhd/canbus/msg_mgr/AbstractMsgMgr;", "()V", "allLsb", "", "allMsb", "driver", "Lcom/hzbhd/canbus/car/_94/MsgMgr$SeatData;", "getDriver", "()Lcom/hzbhd/canbus/car/_94/MsgMgr$SeatData;", "listGeneralParkBigData", "", "getListGeneralParkBigData", "()Ljava/util/List;", "listGeneralParkData", "Lcom/hzbhd/canbus/entity/PanoramicBtnUpdateEntity;", "getListGeneralParkData", "<set-?>", "", "m0x61DataRecord", "getM0x61DataRecord", "()[B", "mActivePark", "Lcom/hzbhd/canbus/car/_94/ActivePark;", "mActiveParkBeamArray", "Landroid/util/SparseArray;", "Lcom/hzbhd/canbus/car/_94/ActivePark$ActiveParkBeam;", "mCanId", "mCanbusInfoByte", "mCanbusInfoInt", "", "mConfiguration", "mContext", "Landroid/content/Context;", "mDifferent", "mDiscExist", "mDriveItemIndexHashMap", "Ljava/util/HashMap;", "", "Lcom/hzbhd/canbus/entity/DriverUpdateEntity;", "Lkotlin/collections/HashMap;", "mEachId", "mHandler", "Landroid/os/Handler;", "mId3Array", "", "Lcom/hzbhd/canbus/car/_94/MsgMgr$Id3;", "[Lcom/hzbhd/canbus/car/_94/MsgMgr$Id3;", "mId3Timer", "Lcom/hzbhd/canbus/util/TimerUtil;", "mParserArray", "Lcom/hzbhd/canbus/car/_94/MsgMgr$Parser;", "mPhoneInfoCommand", "mReversingValues", "getMReversingValues", "()I", "mSettingItemIndexHashMap", "Lcom/hzbhd/canbus/entity/SettingUpdateEntity;", "", "mUiMgr", "Lcom/hzbhd/canbus/car/_94/UiMgr;", "nowLsb", "nowMsb", "number", "getNumber", "setNumber", "(I)V", "passenger", "getPassenger", "afterServiceNormalSetting", "", "context", "auxInInfoChange", "btMusicId3InfoChange", "title", "artist", "album", "btPhoneIncomingInfoChange", "phoneNumber", "isMicMute", "", "isAudioTransferTowardsAG", "btPhoneOutGoingInfoChange", "btPhoneStatusInfoChange", "callStatus", "isHfpConnected", "isCallingFlag", "batteryStatus", "signalValue", "bundle", "Landroid/os/Bundle;", "btPhoneTalkingWithTimeInfoChange", "time", "canbusInfoChange", "canbusInfo", "currentVolumeInfoChange", "volValue", "isMute", "customLongClick", "key", "dateTimeRepCanbus", "bYearTotal", "bYear2Dig", "bMonth", "bDay", "bHours", "bMins", "bSecond", "bHours24H", "systemDateFormat", "isFormat24H", "isFormatPm", "isGpsTime", "dayOfWeek", "deviceStatusInfoChange", "btOn", "discRadom", "discRepeat", "discExsit", "sdCardIn", "usbIn", "radioSt", "mute", "singleCycle", "fullCurve", "folderLoop", "randomFolder", "discInfoChange", "playModel", "", "currentTime", "playTitleNo", "position", "currentPlayNo", "currentTotalNo", "discType", "isUnMuted", "isPlaying", "playStat", "song", "getActivePark", "getLsb", "data", "getMsb", "getMsbLsbResult", "MSB", "LSB", "getSyncIconResId", "name", "initActivePark", "initAmplifier", "initCommand", "initItemsIndexHashMap", "initParsers", "musicInfoChange", "stoagePath", "playRatio", "currentPlayingIndexLow", "totalCount", "currentHour", "currentMinute", "currentSecond", "currentAllMinuteStr", "currentPlayingIndexHigh", "currentAllMinute", "currentHourStr", "currentMinuteStr", "currentSecondStr", "currentPos", "", "playIndex", "totalTime", "songTitle", "songAlbum", "songArtist", "isReportFromPlay", "radioInfoChange", "currClickPresetIndex", "currBand", "currentFreq", "psName", "isStereo", "refreshPanoramicParkUi", "canInt", "refreshPanoramicParkUiBig", "type", "refreshParkUi", "reportId3", "setConfiguration", "value", "setRadarData", "direct", "Lcom/hzbhd/canbus/comm/Constant$RadarLocation;", "sourceSwitchNoMediaInfoChange", "isPowerOff", "stringFixLength", "updateSettings", "updateSync", "videoInfoChange", "playMode", "duration", "Companion", "Id3", "Parser", "SeatData", "SyncKey", "CanBusInfo_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class MsgMgr extends AbstractMsgMgr {
   public static final int AMPLIFIER_FADE_OFFSET = 7;
   public static final Companion Companion = new Companion((DefaultConstructorMarker)null);
   private static final int INVALID_VALUE = -1;
   private static final int MSG_DISMISS_WARNING_ACTIVITY = 11;
   private static final int RADAR_RANGE = 31;
   public static final String SHARE_94_VEHICLE_CONFIG = "share_94_vehicle_config";
   public static final String TAG = "_1094_MsgMgr";
   public static final int VEHICLE_TYPE_19_TERRITORY = 13;
   public static final int VEHICLE_TYPE_20_ESCAPE = 22;
   public static final int VEHICLE_TYPE_EXPLORER = 14;
   private static final int[] m0xC90x21ItemValues = new int[]{0, 10, 20, 120};
   private static final int[] m0xC90x26ItemValues = new int[]{0, 1, 2};
   private static final int[] mLanguageIndexs = new int[]{2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 14, 15, 18, 19, 20, 21, 22, 23, 25, 26, 27, 28, 29, 31};
   private int allLsb;
   private int allMsb;
   private final SeatData driver;
   private final List listGeneralParkBigData;
   private final List listGeneralParkData;
   private byte[] m0x61DataRecord;
   private ActivePark mActivePark;
   private final SparseArray mActiveParkBeamArray;
   private int mCanId;
   private byte[] mCanbusInfoByte;
   private int[] mCanbusInfoInt;
   private int mConfiguration;
   private Context mContext;
   private int mDifferent;
   private int mDiscExist;
   private HashMap mDriveItemIndexHashMap;
   private int mEachId;
   private final Handler mHandler;
   private final Id3[] mId3Array;
   private final TimerUtil mId3Timer;
   private final SparseArray mParserArray;
   private final byte[] mPhoneInfoCommand;
   private int mReversingValues;
   private HashMap mSettingItemIndexHashMap;
   private UiMgr mUiMgr;
   private int nowLsb;
   private int nowMsb;
   private int number;
   private final SeatData passenger;

   // $FF: synthetic method
   public static void $r8$lambda$UkqHWuCME6_7TD_K8Im_YuFkYJI() {
      dateTimeRepCanbus$lambda_4();
   }

   // $FF: synthetic method
   public static void $r8$lambda$d0kkLvhmm6CJ78MTX0z_21AOytE(byte[] var0) {
      btPhoneIncomingInfoChange$lambda_16(var0);
   }

   // $FF: synthetic method
   public static void $r8$lambda$muFymPy3c53NV5_FXlW9lFMH920() {
      btPhoneStatusInfoChange$lambda_14$lambda_13();
   }

   // $FF: synthetic method
   public static void $r8$lambda$s02VAYjAjvL6P1Oiph3GXh7_Rp8(byte[] var0) {
      btPhoneOutGoingInfoChange$lambda_15(var0);
   }

   // $FF: synthetic method
   public static void $r8$lambda$xcJz2XpPQRUExELSzJ_BCG1qxQE(MsgMgr var0, int var1, int var2, int var3, boolean var4) {
      btPhoneStatusInfoChange$lambda_14(var0, var1, var2, var3, var4);
   }

   public MsgMgr() {
      Integer var1 = 0;
      this.mCanbusInfoInt = new int[0];
      this.mCanbusInfoByte = new byte[0];
      this.mDifferent = -1;
      this.mCanId = -1;
      this.mEachId = -1;
      this.mConfiguration = -1;
      this.m0x61DataRecord = new byte[]{0, 0, 0, 0};
      this.mPhoneInfoCommand = new byte[]{22, -57, 0, 0, 0, 0};
      this.mParserArray = new SparseArray();
      this.mActiveParkBeamArray = new SparseArray();
      this.mSettingItemIndexHashMap = new HashMap();
      this.mDriveItemIndexHashMap = new HashMap();
      this.driver = new SeatData();
      this.passenger = new SeatData();
      this.mId3Array = new Id3[]{new Id3(1), new Id3(2)};
      this.mId3Timer = new TimerUtil();
      this.mHandler = new Handler(Looper.getMainLooper());
      this.listGeneralParkData = (List)(new ArrayList());
      this.listGeneralParkBigData = (List)CollectionsKt.arrayListOf(new Integer[]{var1, var1, var1, var1, var1, var1});
   }

   private static final void btPhoneIncomingInfoChange$lambda_16(byte[] var0) {
      Intrinsics.checkNotNullParameter(var0, "$phoneNumber");
      String var1 = new String(var0, Charsets.UTF_8);
      Charset var2 = StandardCharsets.UTF_16;
      Intrinsics.checkNotNullExpressionValue(var2, "UTF_16");
      var0 = var1.getBytes(var2);
      Intrinsics.checkNotNullExpressionValue(var0, "this as java.lang.String).getBytes(charset)");
      CanbusMsgSender.sendMsg(ArraysKt.plus(new byte[]{22, -54, 3}, var0));
   }

   private static final void btPhoneOutGoingInfoChange$lambda_15(byte[] var0) {
      Intrinsics.checkNotNullParameter(var0, "$phoneNumber");
      String var1 = new String(var0, Charsets.UTF_8);
      Charset var2 = StandardCharsets.UTF_16;
      Intrinsics.checkNotNullExpressionValue(var2, "UTF_16");
      var0 = var1.getBytes(var2);
      Intrinsics.checkNotNullExpressionValue(var0, "this as java.lang.String).getBytes(charset)");
      CanbusMsgSender.sendMsg(ArraysKt.plus(new byte[]{22, -54, 3}, var0));
   }

   private static final void btPhoneStatusInfoChange$lambda_14(MsgMgr var0, int var1, int var2, int var3, boolean var4) {
      Intrinsics.checkNotNullParameter(var0, "this$0");
      byte[] var5 = var0.mPhoneInfoCommand;
      var5[2] = (byte)(var1 << 4 & 240 | var2 & 15);
      if (var3 == 0) {
         if (var4 == 0) {
            var5[2] = 0;
         }

         var5[3] = var4;
         var5[4] = 0;
         var5[5] = 0;
      }

      CanbusMsgSender.sendMsg(var5);
      var0.mHandler.postDelayed(new MsgMgr$$ExternalSyntheticLambda3(), 100L);
   }

   private static final void btPhoneStatusInfoChange$lambda_14$lambda_13() {
      CanbusMsgSender.sendMsg(new byte[]{22, -54, 3, 0, 0});
   }

   private static final void dateTimeRepCanbus$lambda_4() {
      CanbusMsgSender.sendMsg(new byte[]{22, -112, 48, 0});
   }

   private final int getLsb(int var1) {
      return (var1 & '\uffff') >> 0 & 255;
   }

   private final int getMsb(int var1) {
      return (var1 & '\uffff') >> 8 & 255;
   }

   private final int getMsbLsbResult(int var1, int var2) {
      return (var1 & 255) << 8 | var2 & 255;
   }

   private final int getSyncIconResId(Context var1, String var2) {
      int var4 = CommUtil.getImgIdByResId(var1, var2);
      int var3 = var4;
      if (var4 == 2131234410) {
         var3 = 2131233621;
      }

      return var3;
   }

   private final void initActivePark() {
      SparseArray var1 = this.mActiveParkBeamArray;
      var1.put(2, new ActivePark.ActiveParkBeam(2131767018));
      var1.append(3, new ActivePark.ActiveParkBeam(2131767019));
      var1.append(4, new ActivePark.ActiveParkBeam(2131767020, (Integer)null, 2131233416, "right_radar"));
      var1.append(5, new ActivePark.ActiveParkBeam(2131767021, 2131233417, (Integer)null, "left_radar"));
      var1.append(6, new ActivePark.ActiveParkBeam(2131767022));
      Integer var3 = 2131231352;
      var1.append(7, new ActivePark.ActiveParkBeam(2131767023, var3, (Integer)null, "left_forward"));
      Integer var2 = 2131231353;
      var1.append(8, new ActivePark.ActiveParkBeam(2131767024, (Integer)null, var2, "left_forward"));
      var1.append(9, new ActivePark.ActiveParkBeam(2131767025, var3, (Integer)null, "left_forward"));
      var1.append(10, new ActivePark.ActiveParkBeam(2131767026, (Integer)null, var2, "left_forward"));
      var2 = 2131231354;
      var1.append(11, new ActivePark.ActiveParkBeam(2131767027, var2, (Integer)null, "left_stop"));
      var3 = 2131231355;
      var1.append(12, new ActivePark.ActiveParkBeam(2131767028, (Integer)null, var3, "left_stop"));
      var1.append(13, new ActivePark.ActiveParkBeam(2131767029, var2, (Integer)null, "left_reverse"));
      var1.append(14, new ActivePark.ActiveParkBeam(2131767030, (Integer)null, var3, "left_reverse"));
      var2 = 2131233427;
      var1.append(15, new ActivePark.ActiveParkBeam(2131767031, var2, (Integer)null, "left_backward"));
      var3 = 2131233428;
      var1.append(16, new ActivePark.ActiveParkBeam(2131767032, (Integer)null, var3, "left_backward"));
      var1.append(17, new ActivePark.ActiveParkBeam(2131767033, var2, (Integer)null, "left_backward"));
      var1.append(18, new ActivePark.ActiveParkBeam(2131767034, (Integer)null, var3, "left_backward"));
      var1.append(19, new ActivePark.ActiveParkBeam(2131767035, 2131231356, (Integer)null, "left_stop"));
      var1.append(20, new ActivePark.ActiveParkBeam(2131767036, (Integer)null, 2131231357, "left_stop"));
      Integer var5 = 2131231358;
      var1.append(21, new ActivePark.ActiveParkBeam(2131767037, var5, (Integer)null, "left_forward"));
      Integer var4 = 2131231359;
      var1.append(22, new ActivePark.ActiveParkBeam(2131767038, (Integer)null, var4, "left_forward"));
      var1.append(23, new ActivePark.ActiveParkBeam(2131767039, 2131231360, (Integer)null, "left_stop"));
      var1.append(24, new ActivePark.ActiveParkBeam(2131767040, (Integer)null, 2131231361, "left_stop"));
      var3 = 2131231362;
      var1.append(25, new ActivePark.ActiveParkBeam(2131767041, var3, (Integer)null, "left_backward"));
      var2 = 2131231363;
      var1.append(26, new ActivePark.ActiveParkBeam(2131767042, (Integer)null, var2, "left_backward"));
      var1.append(27, new ActivePark.ActiveParkBeam(2131767043));
      var1.append(28, new ActivePark.ActiveParkBeam(2131767044));
      var1.append(29, new ActivePark.ActiveParkBeam(2131767045));
      var1.append(30, new ActivePark.ActiveParkBeam(2131767046));
      var1.append(31, new ActivePark.ActiveParkBeam(2131767047));
      var1.append(32, new ActivePark.ActiveParkBeam(2131767048));
      var1.append(33, new ActivePark.ActiveParkBeam(2131767049));
      var1.append(34, new ActivePark.ActiveParkBeam(2131767050));
      var1.append(35, new ActivePark.ActiveParkBeam(2131767037, var5, (Integer)null, "left_forward"));
      var1.append(36, new ActivePark.ActiveParkBeam(2131767038, (Integer)null, var4, "left_forward"));
      var1.append(37, new ActivePark.ActiveParkBeam(2131767041, var3, (Integer)null, "left_backward"));
      var1.append(38, new ActivePark.ActiveParkBeam(2131767042, (Integer)null, var2, "left_backward"));
      var1.append(39, new ActivePark.ActiveParkBeam(2131767045));
   }

   private final void initAmplifier(Context var1) {
      if (var1 != null) {
         this.getAmplifierData(var1, this.getCanId(), UiMgrFactory.getCanUiMgr(var1).getAmplifierPageUiSet(var1));
      }

      byte var4 = (byte)GeneralAmplifierData.bandTreble;
      byte[] var7 = new byte[]{22, -61, 1, (byte)GeneralAmplifierData.bandMiddle};
      byte var5 = (byte)GeneralAmplifierData.bandBass;
      byte var2 = (byte)(GeneralAmplifierData.frontRear + 7);
      byte var3 = (byte)(GeneralAmplifierData.leftRight + 7);
      byte[] var6 = new byte[]{22, -62, (byte)GeneralAmplifierData.volume};
      Iterator var8 = ArrayIteratorKt.iterator((Object[])(new byte[][]{{22, -127, 1}, {22, -58, -94, 6, 0}, {22, -61, 0, var4}, var7, {22, -61, 2, var5}, {22, -61, 3, var2}, {22, -61, 4, var3}, var6}));
      TimerUtil var9 = new TimerUtil();
      var9.startTimer((TimerTask)(new TimerTask(var8, var9) {
         final Iterator $this_run;
         final TimerUtil $this_run$1;

         {
            this.$this_run = var1;
            this.$this_run$1 = var2;
         }

         public void run() {
            if (this.$this_run.hasNext()) {
               CanbusMsgSender.sendMsg((byte[])this.$this_run.next());
            } else {
               this.$this_run$1.stopTimer();
            }

         }
      }), 0L, 100L);
   }

   private final void initItemsIndexHashMap(Context var1) {
      Log.i("_1094_MsgMgr", "initItems: ");
      UiMgr var5 = this.mUiMgr;
      UiMgr var4 = var5;
      if (var5 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mUiMgr");
         var4 = null;
      }

      Iterator var7 = var4.getSettingUiSet(var1).getList().iterator();

      int var2;
      int var3;
      Iterator var11;
      for(var2 = 0; var7.hasNext(); ++var2) {
         var11 = ((SettingPageUiSet.ListBean)var7.next()).getItemList().iterator();

         for(var3 = 0; var11.hasNext(); ++var3) {
            SettingPageUiSet.ListBean.ItemListBean var8 = (SettingPageUiSet.ListBean.ItemListBean)var11.next();
            Map var6 = (Map)this.mSettingItemIndexHashMap;
            String var14 = var8.getTitleSrn();
            Intrinsics.checkNotNullExpressionValue(var14, "itemListBean.titleSrn");
            var6.put(var14, new SettingUpdateEntity(var2, var3));
         }
      }

      var11 = var4.getDriverDataPageUiSet(var1).getList().iterator();

      for(var2 = 0; var11.hasNext(); ++var2) {
         Iterator var10 = ((DriverDataPageUiSet.Page)var11.next()).getItemList().iterator();

         for(var3 = 0; var10.hasNext(); ++var3) {
            DriverDataPageUiSet.Page.Item var12 = (DriverDataPageUiSet.Page.Item)var10.next();
            Map var9 = (Map)this.mDriveItemIndexHashMap;
            String var13 = var12.getTitleSrn();
            Intrinsics.checkNotNullExpressionValue(var13, "item.titleSrn");
            var9.put(var13, new DriverUpdateEntity(var2, var3, "null_value"));
         }
      }

   }

   private final void initParsers(Context var1) {
      SparseArray var2 = this.mParserArray;
      var2.append(20, new Parser(this) {
         final MsgMgr this$0;

         {
            this.this$0 = var1;
         }

         public void parse(int var1) {
            var1 = this.this$0.mCanbusInfoInt[2];
            MsgMgr var4 = this.this$0;
            int var3 = var4.mCanbusInfoInt[2];
            boolean var2 = true;
            boolean var5;
            if (1 <= var3 && var3 < 52) {
               var5 = true;
            } else {
               var5 = false;
            }

            if (var5) {
               var4.setBacklightLevel(1);
            } else {
               if (52 <= var3 && var3 < 103) {
                  var5 = true;
               } else {
                  var5 = false;
               }

               if (var5) {
                  var4.setBacklightLevel(2);
               } else {
                  if (103 <= var3 && var3 < 154) {
                     var5 = true;
                  } else {
                     var5 = false;
                  }

                  if (var5) {
                     var4.setBacklightLevel(3);
                  } else {
                     if (154 <= var3 && var3 < 205) {
                        var5 = true;
                     } else {
                        var5 = false;
                     }

                     if (var5) {
                        var4.setBacklightLevel(4);
                     } else {
                        if (205 <= var3 && var3 < 256) {
                           var5 = var2;
                        } else {
                           var5 = false;
                        }

                        if (var5) {
                           var4.setBacklightLevel(5);
                        }
                     }
                  }
               }
            }

         }
      });
      var2.put(32, new Parser(this, var1) {
         final Context $context;
         private int normalKeyStatus;
         private int syncKeyStatus;
         final MsgMgr this$0;

         {
            this.this$0 = var1;
            this.$context = var2;
         }

         private final void dealSyncKey(int var1) {
            this.dealSyncKey(var1, -1, 0);
         }

         private final void dealSyncKey(int var1, int var2, int var3) {
            if (SystemUtil.isForeground(this.$context, Reflection.getOrCreateKotlinClass(SyncActivity.class).getQualifiedName())) {
               if (this.syncKeyStatus == 1) {
                  if (this.this$0.mCanbusInfoInt[3] == 0) {
                     CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, (byte)var1});
                  } else if (this.this$0.mCanbusInfoInt[3] == 2 && var2 != -1) {
                     CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, (byte)var2});
                  }
               }
            } else {
               this.realKeyLongClick1(var3);
            }

            this.syncKeyStatus = this.this$0.mCanbusInfoInt[3];
         }

         private final void realKeyClick31(int var1) {
            if (this.this$0.mCanbusInfoInt[3] != 0) {
               MsgMgr var2 = this.this$0;
               var2.realKeyClick3_1(this.$context, var1, var2.mCanbusInfoInt[2], this.this$0.mCanbusInfoInt[3]);
            }
         }

         private final void realKeyClick32(int var1) {
            if (this.this$0.mCanbusInfoInt[3] != 0) {
               MsgMgr var2 = this.this$0;
               var2.realKeyClick3_2(this.$context, var1, var2.mCanbusInfoInt[2], this.this$0.mCanbusInfoInt[3]);
            }
         }

         private final void realKeyLongClick1(int var1) {
            MsgMgr var2 = this.this$0;
            var2.realKeyLongClick1(this.$context, var1, var2.mCanbusInfoInt[3]);
         }

         private final void switchPanoramic() {
            MsgMgr var2 = this.this$0;
            Context var3 = this.$context;
            boolean var1;
            if (ShareDataManager.getInstance().getInt("misc.Reverse") == 0) {
               var1 = true;
            } else {
               var1 = false;
            }

            var2.forceReverse(var3, var1);
         }

         private final void switchSyncActivity() {
            if (this.this$0.mCanbusInfoInt[3] == 1) {
               if (SystemUtil.isForeground(this.$context, Reflection.getOrCreateKotlinClass(SyncActivity.class).getQualifiedName())) {
                  this.this$0.realKeyClick(this.$context, 50);
               } else {
                  Context var1 = this.$context;
                  Intent var2 = new Intent();
                  var2.setComponent(Constant.SyncActivity);
                  var2.setFlags(268435456);
                  var1.startActivity(var2);
               }
            }

         }

         public void parse(int var1) {
            int var2 = this.this$0.mCanbusInfoInt[2];
            if (var2 != 0) {
               if (var2 != 1) {
                  if (var2 != 2) {
                     if (var2 != 3) {
                        if (var2 != 4) {
                           if (var2 != 5) {
                              if (var2 != 6) {
                                 label131: {
                                    if (var2 != 7) {
                                       if (var2 == 86) {
                                          this.realKeyLongClick1(94);
                                          break label131;
                                       }

                                       if (var2 == 87) {
                                          this.realKeyLongClick1(185);
                                          break label131;
                                       }

                                       if (var2 == 112) {
                                          this.realKeyClick32(48);
                                          break label131;
                                       }

                                       if (var2 == 113) {
                                          this.realKeyClick32(47);
                                          break label131;
                                       }

                                       switch (var2) {
                                          case 7:
                                             break;
                                          case 8:
                                             this.dealSyncKey(SyncKey.Companion.getWell().getShort());
                                             break label131;
                                          case 9:
                                             this.realKeyLongClick1(76);
                                             break label131;
                                          case 10:
                                             this.realKeyLongClick1(128);
                                             break label131;
                                          case 11:
                                             this.realKeyLongClick1(14);
                                             break label131;
                                          case 12:
                                             this.realKeyLongClick1(15);
                                             break label131;
                                          case 61:
                                             this.realKeyLongClick1(196);
                                             break label131;
                                          case 63:
                                             this.realKeyLongClick1(134);
                                             break label131;
                                          case 80:
                                             this.realKeyLongClick1(152);
                                             break label131;
                                          case 240:
                                             this.realKeyClick31(7);
                                             break label131;
                                          case 241:
                                             this.realKeyClick31(8);
                                             break label131;
                                          default:
                                             switch (var2) {
                                                case 14:
                                                   this.realKeyLongClick1(45);
                                                   break label131;
                                                case 15:
                                                   this.realKeyLongClick1(46);
                                                   break label131;
                                                case 16:
                                                   this.realKeyLongClick1(47);
                                                   break label131;
                                                case 17:
                                                   this.realKeyLongClick1(48);
                                                   break label131;
                                                case 18:
                                                   this.realKeyLongClick1(49);
                                                   break label131;
                                                case 19:
                                                   this.realKeyLongClick1(128);
                                                   break label131;
                                                case 20:
                                                   this.realKeyLongClick1(6);
                                                   break label131;
                                                case 21:
                                                   this.realKeyLongClick1(13);
                                                   break label131;
                                                case 22:
                                                   this.realKeyLongClick1(52);
                                                   break label131;
                                                case 23:
                                                   this.realKeyLongClick1(73);
                                                   break label131;
                                                default:
                                                   switch (var2) {
                                                      case 32:
                                                         this.dealSyncKey(SyncKey.Companion.getNum0().getShort(), SyncKey.Companion.getNum0().getLong(), 32);
                                                         break label131;
                                                      case 33:
                                                         this.dealSyncKey(SyncKey.Companion.getNum1().getShort(), SyncKey.Companion.getNum1().getLong(), 33);
                                                         break label131;
                                                      case 34:
                                                         this.dealSyncKey(SyncKey.Companion.getNum2().getShort(), SyncKey.Companion.getNum2().getLong(), 34);
                                                         break label131;
                                                      case 35:
                                                         this.dealSyncKey(SyncKey.Companion.getNum3().getShort(), SyncKey.Companion.getNum3().getLong(), 35);
                                                         break label131;
                                                      case 36:
                                                         this.dealSyncKey(SyncKey.Companion.getNum4().getShort(), SyncKey.Companion.getNum4().getLong(), 36);
                                                         break label131;
                                                      case 37:
                                                         this.dealSyncKey(SyncKey.Companion.getNum5().getShort(), SyncKey.Companion.getNum5().getLong(), 37);
                                                         break label131;
                                                      case 38:
                                                         this.dealSyncKey(SyncKey.Companion.getNum6().getShort(), SyncKey.Companion.getNum6().getLong(), 38);
                                                         break label131;
                                                      case 39:
                                                         this.dealSyncKey(SyncKey.Companion.getNum7().getShort(), SyncKey.Companion.getNum7().getLong(), 39);
                                                         break label131;
                                                      case 40:
                                                         this.dealSyncKey(SyncKey.Companion.getNum8().getShort(), SyncKey.Companion.getNum8().getLong(), 40);
                                                         break label131;
                                                      case 41:
                                                         this.dealSyncKey(SyncKey.Companion.getNum9().getShort(), SyncKey.Companion.getNum9().getLong(), 41);
                                                         break label131;
                                                      case 42:
                                                         this.dealSyncKey(SyncKey.Companion.getStar().getShort());
                                                         break label131;
                                                      case 43:
                                                         this.dealSyncKey(SyncKey.Companion.getWell().getShort());
                                                         break label131;
                                                      default:
                                                         switch (var2) {
                                                            case 46:
                                                               this.realKeyClick31(8);
                                                               break label131;
                                                            case 47:
                                                               this.realKeyClick31(7);
                                                               break label131;
                                                            case 48:
                                                               this.realKeyLongClick1(59);
                                                               break label131;
                                                            case 49:
                                                               this.realKeyLongClick1(50);
                                                               break label131;
                                                            case 50:
                                                               this.realKeyLongClick1(128);
                                                               break label131;
                                                            case 51:
                                                               this.switchSyncActivity();
                                                               break label131;
                                                            case 52:
                                                               this.realKeyLongClick1(76);
                                                               break label131;
                                                            case 53:
                                                               this.realKeyLongClick1(130);
                                                               break label131;
                                                            case 54:
                                                               this.realKeyLongClick1(141);
                                                               break label131;
                                                            case 55:
                                                               this.realKeyLongClick1(52);
                                                               break label131;
                                                            case 56:
                                                               this.realKeyLongClick1(4);
                                                               break label131;
                                                            case 57:
                                                               this.realKeyLongClick1(14);
                                                               break label131;
                                                            default:
                                                               switch (var2) {
                                                                  case 72:
                                                                     this.realKeyLongClick1(49);
                                                                     break label131;
                                                                  case 73:
                                                                     this.realKeyLongClick1(47);
                                                                     break label131;
                                                                  case 74:
                                                                     this.realKeyLongClick1(48);
                                                                     break label131;
                                                                  case 75:
                                                                     this.realKeyLongClick1(45);
                                                                     break label131;
                                                                  case 76:
                                                                     this.realKeyLongClick1(46);
                                                                     break label131;
                                                                  default:
                                                                     switch (var2) {
                                                                        case 82:
                                                                           this.realKeyLongClick1(465);
                                                                           break label131;
                                                                        case 83:
                                                                           this.realKeyLongClick1(466);
                                                                           break label131;
                                                                        case 84:
                                                                           this.realKeyLongClick1(31);
                                                                           break label131;
                                                                        default:
                                                                           switch (var2) {
                                                                              case 89:
                                                                                 this.realKeyLongClick1(4);
                                                                                 break label131;
                                                                              case 90:
                                                                                 this.realKeyLongClick1(3);
                                                                                 break label131;
                                                                              case 91:
                                                                                 this.realKeyLongClick1(152);
                                                                                 break label131;
                                                                              case 92:
                                                                                 this.dealSyncKey(SyncKey.Companion.getK1().getShort());
                                                                                 break label131;
                                                                              case 93:
                                                                                 this.dealSyncKey(SyncKey.Companion.getK2().getShort());
                                                                                 break label131;
                                                                              case 94:
                                                                                 this.dealSyncKey(SyncKey.Companion.getK3().getShort());
                                                                                 break label131;
                                                                              case 95:
                                                                                 this.dealSyncKey(SyncKey.Companion.getK4().getShort());
                                                                                 break label131;
                                                                              default:
                                                                                 switch (var2) {
                                                                                    case 102:
                                                                                       this.realKeyLongClick1(17);
                                                                                       break label131;
                                                                                    case 103:
                                                                                       this.realKeyLongClick1(59);
                                                                                       break label131;
                                                                                    case 104:
                                                                                       this.realKeyLongClick1(58);
                                                                                       break label131;
                                                                                    case 105:
                                                                                       if (this.this$0.mCanbusInfoInt[3] == 1) {
                                                                                          this.switchPanoramic();
                                                                                       }

                                                                                       if (this.this$0.mCanbusInfoInt[2] != 255) {
                                                                                          MsgMgr var3 = this.this$0;
                                                                                          var3.updateSpeedInfo(var3.mCanbusInfoInt[2]);
                                                                                       }
                                                                                    default:
                                                                                       break label131;
                                                                                 }
                                                                           }
                                                                     }
                                                               }
                                                         }
                                                   }
                                             }
                                       }
                                    }

                                    this.realKeyLongClick1(2);
                                 }
                              } else {
                                 this.realKeyLongClick1(187);
                              }
                           } else {
                              this.realKeyLongClick1(187);
                           }
                        } else {
                           this.realKeyLongClick1(21);
                        }
                     } else {
                        this.realKeyLongClick1(20);
                     }
                  } else {
                     this.realKeyLongClick1(8);
                  }
               } else {
                  this.realKeyLongClick1(7);
               }
            } else if (this.normalKeyStatus != 0) {
               this.this$0.mCanbusInfoInt[2] = this.normalKeyStatus;
               this.parse(var1);
               this.normalKeyStatus = 0;
            }

            this.normalKeyStatus = this.this$0.mCanbusInfoInt[2];
         }
      });
      var2.append(33, var1.new Parser(var1, this) {
         final Context $context;
         private final String close;
         private final String level;
         private byte outdoorTemperature;
         private int[] rearAirDatas;
         final MsgMgr this$0;

         // $FF: synthetic method
         public static void $r8$lambda$56IDskS0VenDX9zG24MHb0UOfPc(MsgMgr var0) {
            setOnParseListeners$lambda_1(var0);
         }

         // $FF: synthetic method
         public static void $r8$lambda$7hy8t8d72DkAIBHrjpFW2B1byuo(MsgMgr var0) {
            setOnParseListeners$lambda_4(var0);
         }

         // $FF: synthetic method
         public static void $r8$lambda$D8Ntcx1HG_m30jMIbkpOyNT4YB8(MsgMgr var0, Object var1) {
            setOnParseListeners$lambda_7(var0, var1);
         }

         // $FF: synthetic method
         public static void $r8$lambda$Nm_XwhWko1Spq8tuko9eJML7_3k(MsgMgr var0) {
            setOnParseListeners$lambda_0(var0);
         }

         // $FF: synthetic method
         public static void $r8$lambda$PKaWGhK5zlWhingJQGK__BwHOV8(Object var0, MsgMgr var1) {
            setOnParseListeners$lambda_3(var0, var1);
         }

         // $FF: synthetic method
         public static void $r8$lambda$YwYUEjCFv_M85LpC19vSnyBnO6Y(MsgMgr var0) {
            setOnParseListeners$lambda_11(var0);
         }

         // $FF: synthetic method
         public static void $r8$lambda$Zd38PBJlcvCcLJS_MbBQdPA3y4I() {
            setOnParseListeners$lambda_5();
         }

         // $FF: synthetic method
         public static void $r8$lambda$Zyla5Lw3bdKPU2xqm1PdRuAJzvw(MsgMgr var0) {
            setOnParseListeners$lambda_10(var0);
         }

         // $FF: synthetic method
         public static void $r8$lambda$bo67Hh_e4ZNRSZfcZuWrdw4jN0o(MsgMgr var0) {
            setOnParseListeners$lambda_6(var0);
         }

         // $FF: synthetic method
         public static void $r8$lambda$xo4wqZX_TKbXFRSHPNzQ0m6oRtc(Object var0, MsgMgr var1) {
            setOnParseListeners$lambda_2(var0, var1);
         }

         {
            this.$context = var1;
            this.this$0 = var2;
            this.outdoorTemperature = -1;
            String var4 = CommUtil.getStrByResId(var1, "_318_level");
            Intrinsics.checkNotNullExpressionValue(var4, "getStrByResId(context, \"_318_level\")");
            this.level = var4;
            String var3 = CommUtil.getStrByResId(var1, "close");
            Intrinsics.checkNotNullExpressionValue(var3, "getStrByResId(context, \"close\")");
            this.close = var3;
         }

         private final String resolveTemperature(int var1) {
            String var3;
            if (var1 == 0) {
               var3 = "LO";
            } else if (var1 == 127) {
               var3 = "HI";
            } else {
               boolean var2;
               if (31 <= var1 && var1 < 60) {
                  var2 = true;
               } else {
                  var2 = false;
               }

               if (var2) {
                  if (GeneralAirData.fahrenheit_celsius) {
                     StringBuilder var5 = new StringBuilder();
                     StringCompanionObject var4 = StringCompanionObject.INSTANCE;
                     String var6 = String.format("%.1f", Arrays.copyOf(new Object[]{(float)var1 / 2.0F * (float)9 / (float)5 + (float)32}, 1));
                     Intrinsics.checkNotNullExpressionValue(var6, "format(format, *args)");
                     var3 = var5.append(var6).append(this.this$0.getTempUnitF(this.$context)).toString();
                  } else {
                     var3 = (float)var1 / 2.0F + this.this$0.getTempUnitC(this.$context);
                  }
               } else {
                  var3 = "";
               }
            }

            return var3;
         }

         private static final void setOnParseListeners$lambda_0(MsgMgr var0) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            int var1 = var0.mCanbusInfoInt[2];
            boolean var3 = true;
            boolean var2;
            if ((var1 >> 7 & 1) == 1) {
               var2 = true;
            } else {
               var2 = false;
            }

            GeneralAirData.power = var2;
            if ((var0.mCanbusInfoInt[2] >> 6 & 1) == 1) {
               var2 = true;
            } else {
               var2 = false;
            }

            GeneralAirData.ac = var2;
            if ((var0.mCanbusInfoInt[2] >> 5 & 1) == 0) {
               var2 = true;
            } else {
               var2 = false;
            }

            GeneralAirData.in_out_cycle = var2;
            if ((var0.mCanbusInfoInt[2] >> 3 & 1) == 1) {
               var2 = true;
            } else {
               var2 = false;
            }

            GeneralAirData.auto = var2;
            if ((var0.mCanbusInfoInt[2] >> 2 & 1) == 1) {
               var2 = true;
            } else {
               var2 = false;
            }

            GeneralAirData.dual = var2;
            if ((var0.mCanbusInfoInt[2] >> 1 & 1) == 1) {
               var2 = true;
            } else {
               var2 = false;
            }

            GeneralAirData.max_front = var2;
            if ((var0.mCanbusInfoInt[2] & 1) == 1) {
               var2 = var3;
            } else {
               var2 = false;
            }

            GeneralAirData.rear_defog = var2;
         }

         private static final void setOnParseListeners$lambda_1(MsgMgr var0) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            int var1 = var0.mCanbusInfoInt[3];
            boolean var3 = true;
            boolean var2;
            if ((var1 >> 7 & 1) == 1) {
               var2 = true;
            } else {
               var2 = false;
            }

            GeneralAirData.front_left_blow_window = var2;
            if ((var0.mCanbusInfoInt[3] >> 6 & 1) == 1) {
               var2 = true;
            } else {
               var2 = false;
            }

            GeneralAirData.front_left_blow_head = var2;
            if ((var0.mCanbusInfoInt[3] >> 5 & 1) == 1) {
               var2 = true;
            } else {
               var2 = false;
            }

            GeneralAirData.front_left_blow_foot = var2;
            GeneralAirData.front_wind_level = var0.mCanbusInfoInt[3] & 15;
            if (GeneralAirData.front_wind_level == 15) {
               var2 = var3;
            } else {
               var2 = false;
            }

            GeneralAirData.front_auto_wind_speed = var2;
         }

         private static final void setOnParseListeners$lambda_10(MsgMgr var0) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            int var1 = var0.mCanbusInfoInt[10] >> 4 & 15;
            if (var1 > 3) {
               GeneralAirData.front_right_seat_cold_level = 0;
               GeneralAirData.front_right_seat_heat_level = var1 - 3;
            } else {
               GeneralAirData.front_right_seat_cold_level = var1;
               GeneralAirData.front_right_seat_heat_level = 0;
            }

            var1 = var0.mCanbusInfoInt[10] & 15;
            if (var1 > 3) {
               GeneralAirData.front_left_seat_cold_level = 0;
               GeneralAirData.front_left_seat_heat_level = var1 - 3;
            } else {
               GeneralAirData.front_left_seat_cold_level = var1;
               GeneralAirData.front_left_seat_heat_level = 0;
            }

         }

         private static final void setOnParseListeners$lambda_11(MsgMgr var0) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            GeneralAirData.auto_wind_lv = var0.mCanbusInfoInt[11] >> 6 & 3;
         }

         private static final void setOnParseListeners$lambda_2(Object var0, MsgMgr var1) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            Intrinsics.checkNotNullParameter(var1, "this$1");
            GeneralAirData.front_left_temperature = var0.resolveTemperature(var1.mCanbusInfoInt[4]);
         }

         private static final void setOnParseListeners$lambda_3(Object var0, MsgMgr var1) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            Intrinsics.checkNotNullParameter(var1, "this$1");
            GeneralAirData.front_right_temperature = var0.resolveTemperature(var1.mCanbusInfoInt[5]);
         }

         private static final void setOnParseListeners$lambda_4(MsgMgr var0) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            int var1 = var0.mCanbusInfoInt[6];
            boolean var3 = true;
            boolean var2;
            if ((var1 >> 7 & 1) == 1) {
               var2 = true;
            } else {
               var2 = false;
            }

            GeneralAirData.rear_power = var2;
            if ((var0.mCanbusInfoInt[6] >> 6 & 1) == 1) {
               var2 = true;
            } else {
               var2 = false;
            }

            GeneralAirData.fahrenheit_celsius = var2;
            if ((var0.mCanbusInfoInt[6] >> 5 & 1) == 1) {
               var2 = true;
            } else {
               var2 = false;
            }

            GeneralAirData.front_defog = var2;
            if ((var0.mCanbusInfoInt[6] >> 4 & 1) == 1) {
               var2 = true;
            } else {
               var2 = false;
            }

            GeneralAirData.rear_lock = var2;
            if ((var0.mCanbusInfoInt[6] >> 3 & 1) == 1) {
               var2 = true;
            } else {
               var2 = false;
            }

            GeneralAirData.steering_wheel_heating = var2;
            if ((var0.mCanbusInfoInt[6] >> 2 & 1) == 1) {
               var2 = true;
            } else {
               var2 = false;
            }

            GeneralAirData.ac_max = var2;
            if ((var0.mCanbusInfoInt[6] >> 1 & 1) == 1) {
               var2 = true;
            } else {
               var2 = false;
            }

            GeneralAirData.rear_left_blow_head = var2;
            if ((var0.mCanbusInfoInt[6] >> 0 & 1) == 1) {
               var2 = var3;
            } else {
               var2 = false;
            }

            GeneralAirData.rear_left_blow_foot = var2;
         }

         private static final void setOnParseListeners$lambda_5() {
         }

         private static final void setOnParseListeners$lambda_6(MsgMgr var0) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            GeneralAirData.rear_wind_level = var0.mCanbusInfoInt[8];
         }

         private static final void setOnParseListeners$lambda_7(MsgMgr var0, Object var1) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            Intrinsics.checkNotNullParameter(var1, "this$1");
            String var2;
            if (var0.mCanbusInfoInt[9] == 0) {
               var2 = var1.close;
            } else {
               var2 = var1.level + var0.mCanbusInfoInt[9];
            }

            GeneralAirData.rear_temperature = var2;
         }

         public void parse(int var1) {
            if (this.outdoorTemperature != this.this$0.mCanbusInfoByte[7]) {
               byte var2 = this.this$0.mCanbusInfoByte[7];
               this.outdoorTemperature = var2;
               MsgMgr var6 = this.this$0;
               Context var5 = this.$context;
               boolean var3;
               if (-40 <= var2 && var2 < 87) {
                  var3 = true;
               } else {
                  var3 = false;
               }

               String var4;
               if (var3) {
                  var4 = this.outdoorTemperature + this.this$0.getTempUnitC(this.$context);
               } else {
                  var4 = "--";
               }

               var6.updateOutDoorTemp(var5, var4);
            }

            this.this$0.mCanbusInfoInt[3] |= 16;
            this.this$0.mCanbusInfoInt[7] = 0;
            if (this.isDataChange()) {
               super.parse(var1);
               GeneralAirData.front_right_blow_window = GeneralAirData.front_left_blow_window;
               GeneralAirData.front_right_blow_head = GeneralAirData.front_left_blow_head;
               GeneralAirData.front_right_blow_foot = GeneralAirData.front_left_blow_foot;
               GeneralAirData.rear_right_blow_head = GeneralAirData.rear_left_blow_head;
               GeneralAirData.rear_right_blow_foot = GeneralAirData.rear_left_blow_foot;
               MsgMgr var10 = this.this$0;
               Context var9 = this.$context;
               int[] var11 = new int[]{var10.mCanbusInfoInt[6] & 147, this.this$0.mCanbusInfoInt[8], this.this$0.mCanbusInfoInt[9]};
               int[] var7 = this.rearAirDatas;
               short var8;
               if (var7 != null && !Arrays.equals(var7, var11)) {
                  var8 = 1002;
               } else {
                  var8 = 1001;
               }

               var11 = Arrays.copyOf(var11, 3);
               Intrinsics.checkNotNullExpressionValue(var11, "copyOf(this, size)");
               this.rearAirDatas = var11;
               var10.updateAirActivity(var9, var8);
            }

         }

         public OnParseListener[] setOnParseListeners() {
            return new OnParseListener[]{new MsgMgr$initParsers$1$3$$ExternalSyntheticLambda0(this.this$0), new MsgMgr$initParsers$1$3$$ExternalSyntheticLambda1(this.this$0), new MsgMgr$initParsers$1$3$$ExternalSyntheticLambda2(this, this.this$0), new MsgMgr$initParsers$1$3$$ExternalSyntheticLambda3(this, this.this$0), new MsgMgr$initParsers$1$3$$ExternalSyntheticLambda4(this.this$0), new MsgMgr$initParsers$1$3$$ExternalSyntheticLambda5(), new MsgMgr$initParsers$1$3$$ExternalSyntheticLambda6(this.this$0), new MsgMgr$initParsers$1$3$$ExternalSyntheticLambda7(this.this$0, this), new MsgMgr$initParsers$1$3$$ExternalSyntheticLambda8(this.this$0), new MsgMgr$initParsers$1$3$$ExternalSyntheticLambda9(this.this$0)};
         }
      });
      var2.append(34, new Parser(this, var1) {
         final Context $context;
         final MsgMgr this$0;

         // $FF: synthetic method
         public static void $r8$lambda$__UFzSrlltvBxr0Oa8_sk7KnQkE(MsgMgr var0) {
            setOnParseListeners$lambda_4(var0);
         }

         // $FF: synthetic method
         public static void $r8$lambda$2yjdpXEFlMsebcE_iGO6Jwf4bpk(MsgMgr var0) {
            setOnParseListeners$lambda_2(var0);
         }

         // $FF: synthetic method
         public static void $r8$lambda$9x4v6_6NatxG7AGF7o2io1fTpWI(MsgMgr var0) {
            setOnParseListeners$lambda_6(var0);
         }

         // $FF: synthetic method
         public static void $r8$lambda$KJSWKEafcd9O3M60SttGfmZ_SH4(MsgMgr var0) {
            setOnParseListeners$lambda_1(var0);
         }

         // $FF: synthetic method
         public static void $r8$lambda$QTjiNfqnklIIgEjgvEV_Oo_Mbys(MsgMgr var0) {
            setOnParseListeners$lambda_5(var0);
         }

         // $FF: synthetic method
         public static void $r8$lambda$YnJGv4N3apC2URe29YdtYXE7R4M(MsgMgr var0) {
            setOnParseListeners$lambda_7(var0);
         }

         // $FF: synthetic method
         public static void $r8$lambda$jJnw0Zf0_jBzPkXikVKmlA59rz8(MsgMgr var0) {
            setOnParseListeners$lambda_3(var0);
         }

         // $FF: synthetic method
         public static void $r8$lambda$jLXr_2S9_N9vXMH0tTxW6lN8eLk(MsgMgr var0) {
            setOnParseListeners$lambda_0(var0);
         }

         {
            this.this$0 = var1;
            this.$context = var2;
         }

         private static final void setOnParseListeners$lambda_0(MsgMgr var0) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            var0.setRadarData(Constant.RadarLocation.REAR_LEFT, var0.mCanbusInfoInt[2]);
         }

         private static final void setOnParseListeners$lambda_1(MsgMgr var0) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            var0.setRadarData(Constant.RadarLocation.REAR_MID_LEFT, var0.mCanbusInfoInt[3]);
         }

         private static final void setOnParseListeners$lambda_2(MsgMgr var0) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            var0.setRadarData(Constant.RadarLocation.REAR_MID_RIGHT, var0.mCanbusInfoInt[4]);
         }

         private static final void setOnParseListeners$lambda_3(MsgMgr var0) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            var0.setRadarData(Constant.RadarLocation.REAR_RIGHT, var0.mCanbusInfoInt[5]);
         }

         private static final void setOnParseListeners$lambda_4(MsgMgr var0) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            var0.setRadarData(Constant.RadarLocation.LEFT_REAR, var0.mCanbusInfoInt[6]);
         }

         private static final void setOnParseListeners$lambda_5(MsgMgr var0) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            var0.setRadarData(Constant.RadarLocation.LEFT_MID_REAR, var0.mCanbusInfoInt[7]);
         }

         private static final void setOnParseListeners$lambda_6(MsgMgr var0) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            var0.setRadarData(Constant.RadarLocation.RIGHT_MID_REAR, var0.mCanbusInfoInt[8]);
         }

         private static final void setOnParseListeners$lambda_7(MsgMgr var0) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            var0.setRadarData(Constant.RadarLocation.RIGHT_REAR, var0.mCanbusInfoInt[9]);
         }

         public void parse(int var1) {
            if (this.isDataChange()) {
               RadarInfoUtil.mMinIsClose = true;
               super.parse(var1);
               GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
               this.this$0.updateParkUi((Bundle)null, this.$context);
            }

         }

         public OnParseListener[] setOnParseListeners() {
            return new OnParseListener[]{new MsgMgr$initParsers$1$4$$ExternalSyntheticLambda0(this.this$0), new MsgMgr$initParsers$1$4$$ExternalSyntheticLambda1(this.this$0), new MsgMgr$initParsers$1$4$$ExternalSyntheticLambda2(this.this$0), new MsgMgr$initParsers$1$4$$ExternalSyntheticLambda3(this.this$0), new MsgMgr$initParsers$1$4$$ExternalSyntheticLambda4(this.this$0), new MsgMgr$initParsers$1$4$$ExternalSyntheticLambda5(this.this$0), new MsgMgr$initParsers$1$4$$ExternalSyntheticLambda6(this.this$0), new MsgMgr$initParsers$1$4$$ExternalSyntheticLambda7(this.this$0)};
         }
      });
      var2.append(35, new Parser(this, var1) {
         final Context $context;
         final MsgMgr this$0;

         // $FF: synthetic method
         public static void $r8$lambda$486ytpn13eCpPVm0g_yPaf9IdNg(MsgMgr var0) {
            setOnParseListeners$lambda_4(var0);
         }

         // $FF: synthetic method
         public static void $r8$lambda$AzVHSsDJuz07kSGZypnq3FikX2U(MsgMgr var0) {
            setOnParseListeners$lambda_2(var0);
         }

         // $FF: synthetic method
         public static void $r8$lambda$Bw4AkLPKWQZHueTqfagFtaPCORU(MsgMgr var0) {
            setOnParseListeners$lambda_7(var0);
         }

         // $FF: synthetic method
         public static void $r8$lambda$CKUXbgQnrSZRvA2hdmYqj4haTQw(MsgMgr var0) {
            setOnParseListeners$lambda_0(var0);
         }

         // $FF: synthetic method
         public static void $r8$lambda$Lx77S_Jvt9CiE1rddFjBlEHC5GM(MsgMgr var0) {
            setOnParseListeners$lambda_6(var0);
         }

         // $FF: synthetic method
         public static void $r8$lambda$RarJjCBT9VNNeVvyjCdIw5chRxY(MsgMgr var0) {
            setOnParseListeners$lambda_1(var0);
         }

         // $FF: synthetic method
         public static void $r8$lambda$dAh_zM6kT_WNzOlzDmUdAYe4_1o(MsgMgr var0) {
            setOnParseListeners$lambda_3(var0);
         }

         // $FF: synthetic method
         public static void $r8$lambda$mJO4Z9torUNoOEKxRjFHUOD5H50(MsgMgr var0) {
            setOnParseListeners$lambda_5(var0);
         }

         {
            this.this$0 = var1;
            this.$context = var2;
         }

         private static final void setOnParseListeners$lambda_0(MsgMgr var0) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            var0.setRadarData(Constant.RadarLocation.FRONT_LEFT, var0.mCanbusInfoInt[2]);
         }

         private static final void setOnParseListeners$lambda_1(MsgMgr var0) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            var0.setRadarData(Constant.RadarLocation.FRONT_MID_LEFT, var0.mCanbusInfoInt[3]);
         }

         private static final void setOnParseListeners$lambda_2(MsgMgr var0) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            var0.setRadarData(Constant.RadarLocation.FRONT_MID_RIGHT, var0.mCanbusInfoInt[4]);
         }

         private static final void setOnParseListeners$lambda_3(MsgMgr var0) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            var0.setRadarData(Constant.RadarLocation.FRONT_RIGHT, var0.mCanbusInfoInt[5]);
         }

         private static final void setOnParseListeners$lambda_4(MsgMgr var0) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            var0.setRadarData(Constant.RadarLocation.RIGHT_FRONT, var0.mCanbusInfoInt[6]);
         }

         private static final void setOnParseListeners$lambda_5(MsgMgr var0) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            var0.setRadarData(Constant.RadarLocation.LEFT_FRONT, var0.mCanbusInfoInt[7]);
         }

         private static final void setOnParseListeners$lambda_6(MsgMgr var0) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            var0.setRadarData(Constant.RadarLocation.LEFT_MID_FRONT, var0.mCanbusInfoInt[8]);
         }

         private static final void setOnParseListeners$lambda_7(MsgMgr var0) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            var0.setRadarData(Constant.RadarLocation.RIGHT_MID_FRONT, var0.mCanbusInfoInt[9]);
         }

         public void parse(int var1) {
            if (this.isDataChange()) {
               RadarInfoUtil.mMinIsClose = true;
               super.parse(var1);
               GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
               this.this$0.updateParkUi((Bundle)null, this.$context);
            }

         }

         public OnParseListener[] setOnParseListeners() {
            return new OnParseListener[]{new MsgMgr$initParsers$1$5$$ExternalSyntheticLambda0(this.this$0), new MsgMgr$initParsers$1$5$$ExternalSyntheticLambda1(this.this$0), new MsgMgr$initParsers$1$5$$ExternalSyntheticLambda2(this.this$0), new MsgMgr$initParsers$1$5$$ExternalSyntheticLambda3(this.this$0), new MsgMgr$initParsers$1$5$$ExternalSyntheticLambda4(this.this$0), new MsgMgr$initParsers$1$5$$ExternalSyntheticLambda5(this.this$0), new MsgMgr$initParsers$1$5$$ExternalSyntheticLambda6(this.this$0), new MsgMgr$initParsers$1$5$$ExternalSyntheticLambda7(this.this$0)};
         }
      });
      var2.append(36, new Parser(this, var1) {
         final Context $context;
         private int doorStatus;
         private ArrayList list;
         final MsgMgr this$0;

         // $FF: synthetic method
         public static void $r8$lambda$IOyQT0oHjck33Wa3LoUPJPV__AU(Object var0, MsgMgr var1) {
            setOnParseListeners$lambda_23(var0, var1);
         }

         // $FF: synthetic method
         public static void $r8$lambda$LXAv49ZUWrX9ByI6IZAamuK3U3E(Object var0, MsgMgr var1) {
            setOnParseListeners$lambda_35(var0, var1);
         }

         // $FF: synthetic method
         public static void $r8$lambda$Pc_O3WJPZ8KQaYcH7fTSki6s0QY(Object var0, MsgMgr var1) {
            setOnParseListeners$lambda_16(var0, var1);
         }

         // $FF: synthetic method
         public static void $r8$lambda$bVBq76GCMEIXldxw8wRwiRspeGA(Object var0, MsgMgr var1) {
            setOnParseListeners$lambda_8(var0, var1);
         }

         // $FF: synthetic method
         public static void $r8$lambda$xs9Ff8ORMQS31xexjXsbqnb_XJM(Object var0, MsgMgr var1) {
            setOnParseListeners$lambda_30(var0, var1);
         }

         {
            this.this$0 = var1;
            this.$context = var2;
         }

         private static final void setOnParseListeners$lambda_16(Object var0, MsgMgr var1) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            Intrinsics.checkNotNullParameter(var1, "this$1");
            ArrayList var2 = var0.list;
            ArrayList var3 = var2;
            if (var2 == null) {
               Intrinsics.throwUninitializedPropertyAccessException("list");
               var3 = null;
            }

            SettingUpdateEntity var4 = (SettingUpdateEntity)var1.mSettingItemIndexHashMap.get("_18_vehicle_setting_item_3_210");
            if (var4 != null) {
               var3.add(var4.setValue(var1.mCanbusInfoInt[5] >> 6 & 3));
            }

            var4 = (SettingUpdateEntity)var1.mSettingItemIndexHashMap.get("_94_intelligent_unlock_lock");
            if (var4 != null) {
               var3.add(var4.setValue(var1.mCanbusInfoInt[5] >> 5 & 1));
            }

            var4 = (SettingUpdateEntity)var1.mSettingItemIndexHashMap.get("_94_welcome_lamp_duration");
            if (var4 != null) {
               var3.add(var4.setValue(var1.mCanbusInfoInt[5] >> 3 & 3));
            }

            var4 = (SettingUpdateEntity)var1.mSettingItemIndexHashMap.get("remote_window_control");
            if (var4 != null) {
               var3.add(var4.setValue(var1.mCanbusInfoInt[5] >> 2 & 1));
            }

            var4 = (SettingUpdateEntity)var1.mSettingItemIndexHashMap.get("_284_setting_item_49");
            if (var4 != null) {
               var3.add(var4.setValue(var1.mCanbusInfoInt[5] >> 1 & 1));
            }

            var4 = (SettingUpdateEntity)var1.mSettingItemIndexHashMap.get("speed_lock");
            if (var4 != null) {
               var3.add(var4.setValue(var1.mCanbusInfoInt[5] & 1));
            }

         }

         private static final void setOnParseListeners$lambda_23(Object var0, MsgMgr var1) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            Intrinsics.checkNotNullParameter(var1, "this$1");
            ArrayList var2 = var0.list;
            ArrayList var3 = var2;
            if (var2 == null) {
               Intrinsics.throwUninitializedPropertyAccessException("list");
               var3 = null;
            }

            SettingUpdateEntity var4 = (SettingUpdateEntity)var1.mSettingItemIndexHashMap.get("_94_indoor_lamp_duration");
            if (var4 != null) {
               var3.add(var4.setValue(var1.mCanbusInfoInt[6] >> 6 & 3));
            }

            var4 = (SettingUpdateEntity)var1.mSettingItemIndexHashMap.get("_94_mirror_fold_mode");
            if (var4 != null) {
               var3.add(var4.setValue(var1.mCanbusInfoInt[6] >> 5 & 1));
            }

            var4 = (SettingUpdateEntity)var1.mSettingItemIndexHashMap.get("_94_rear_defog_duration");
            if (var4 != null) {
               var3.add(var4.setValue(var1.mCanbusInfoInt[6] >> 4 & 1));
            }

            var4 = (SettingUpdateEntity)var1.mSettingItemIndexHashMap.get("_94_go_home_with_me_duration");
            if (var4 != null) {
               var3.add(var4.setValue(var1.mCanbusInfoInt[6] >> 2 & 3));
            }

            var4 = (SettingUpdateEntity)var1.mSettingItemIndexHashMap.get("_94_instrument_direction_key");
            if (var4 != null) {
               var3.add(var4.setValue(var1.mCanbusInfoInt[6] & 1));
            }

         }

         private static final void setOnParseListeners$lambda_30(Object var0, MsgMgr var1) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            Intrinsics.checkNotNullParameter(var1, "this$1");
            ArrayList var2 = var0.list;
            ArrayList var3 = var2;
            if (var2 == null) {
               Intrinsics.throwUninitializedPropertyAccessException("list");
               var3 = null;
            }

            SettingUpdateEntity var4 = (SettingUpdateEntity)var1.mSettingItemIndexHashMap.get("_94_charging_indicator");
            if (var4 != null) {
               var3.add(var4.setValue(var1.mCanbusInfoInt[7] >> 5 & 3));
            }

            var4 = (SettingUpdateEntity)var1.mSettingItemIndexHashMap.get("_81_hill_start_assist");
            if (var4 != null) {
               var3.add(var4.setValue(var1.mCanbusInfoInt[7] >> 4 & 1));
            }

            var4 = (SettingUpdateEntity)var1.mSettingItemIndexHashMap.get("_218_setting_0_4");
            if (var4 != null) {
               var3.add(var4.setValue(var1.mCanbusInfoInt[7] >> 2 & 1));
            }

            var4 = (SettingUpdateEntity)var1.mSettingItemIndexHashMap.get("_118_setting_title_49");
            if (var4 != null) {
               var3.add(var4.setValue(var1.mCanbusInfoInt[7] >> 1 & 1));
            }

            var4 = (SettingUpdateEntity)var1.mSettingItemIndexHashMap.get("_81_rain_sensor");
            if (var4 != null) {
               var3.add(var4.setValue(var1.mCanbusInfoInt[7] & 1));
            }

         }

         private static final void setOnParseListeners$lambda_35(Object var0, MsgMgr var1) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            Intrinsics.checkNotNullParameter(var1, "this$1");
            ArrayList var2 = var0.list;
            ArrayList var3 = var2;
            if (var2 == null) {
               Intrinsics.throwUninitializedPropertyAccessException("list");
               var3 = null;
            }

            SettingUpdateEntity var4 = (SettingUpdateEntity)var1.mSettingItemIndexHashMap.get("_94_active_city");
            if (var4 != null) {
               var3.add(var4.setValue(var1.mCanbusInfoInt[8] >> 7 & 1));
            }

            var4 = (SettingUpdateEntity)var1.mSettingItemIndexHashMap.get("_94_ambient_light");
            if (var4 != null) {
               var3.add(var4.setValue(var1.mCanbusInfoInt[8] >> 6 & 1));
            }

            var4 = (SettingUpdateEntity)var1.mSettingItemIndexHashMap.get("_94_powerfold_mirrors");
            if (var4 != null) {
               var3.add(var4.setValue(var1.mCanbusInfoInt[8] >> 5 & 1));
            }

         }

         private static final void setOnParseListeners$lambda_8(Object var0, MsgMgr var1) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            Intrinsics.checkNotNullParameter(var1, "this$1");
            ArrayList var2 = var0.list;
            ArrayList var3 = var2;
            if (var2 == null) {
               Intrinsics.throwUninitializedPropertyAccessException("list");
               var3 = null;
            }

            SettingUpdateEntity var4 = (SettingUpdateEntity)var1.mSettingItemIndexHashMap.get("_81_traction_control_system");
            if (var4 != null) {
               var3.add(var4.setValue(var1.mCanbusInfoInt[4] & 1));
            }

            var4 = (SettingUpdateEntity)var1.mSettingItemIndexHashMap.get("_81_turn_signals_setup");
            if (var4 != null) {
               var3.add(var4.setValue(var1.mCanbusInfoInt[4] >> 1 & 1));
            }

            var4 = (SettingUpdateEntity)var1.mSettingItemIndexHashMap.get("ford_message_tone");
            if (var4 != null) {
               var3.add(var4.setValue(var1.mCanbusInfoInt[4] >> 2 & 1));
            }

            var4 = (SettingUpdateEntity)var1.mSettingItemIndexHashMap.get("ford_alert_tone");
            if (var4 != null) {
               var3.add(var4.setValue(var1.mCanbusInfoInt[4] >> 3 & 1));
            }

            var4 = (SettingUpdateEntity)var1.mSettingItemIndexHashMap.get("parking_assistance");
            if (var4 != null) {
               var3.add(var4.setValue(var1.mCanbusInfoInt[4] >> 4 & 1));
            }

            var4 = (SettingUpdateEntity)var1.mSettingItemIndexHashMap.get("_94_reversing_video_hold");
            if (var4 != null) {
               var3.add(var4.setValue(var1.mCanbusInfoInt[4] >> 6 & 1));
            }

            var4 = (SettingUpdateEntity)var1.mSettingItemIndexHashMap.get("ford_range_unit");
            if (var4 != null) {
               var3.add(var4.setValue(var1.mCanbusInfoInt[4] >> 7 & 1));
            }

         }

         public void parse(int var1) {
            int var2 = this.this$0.mCanbusInfoInt[2];
            MsgMgr var7 = this.this$0;
            Context var6 = this.$context;
            int var3 = this.doorStatus;
            boolean var5 = true;
            boolean var4;
            if (var3 != var2) {
               this.doorStatus = var2;
               if ((var2 >> 7 & 1) == 1) {
                  var4 = true;
               } else {
                  var4 = false;
               }

               GeneralDoorData.isRightFrontDoorOpen = var4;
               if ((var2 >> 6 & 1) == 1) {
                  var4 = true;
               } else {
                  var4 = false;
               }

               GeneralDoorData.isLeftFrontDoorOpen = var4;
               if ((var2 >> 5 & 1) == 1) {
                  var4 = true;
               } else {
                  var4 = false;
               }

               GeneralDoorData.isRightRearDoorOpen = var4;
               if ((var2 >> 4 & 1) == 1) {
                  var4 = true;
               } else {
                  var4 = false;
               }

               GeneralDoorData.isLeftRearDoorOpen = var4;
               if ((var2 >> 3 & 1) == 1) {
                  var4 = true;
               } else {
                  var4 = false;
               }

               GeneralDoorData.isBackOpen = var4;
               var7.updateDoorView(var6);
            }

            var2 = this.this$0.mCanbusInfoInt[4] & 32 | this.this$0.mCanbusInfoInt[7] & 8;
            MsgMgr var9 = this.this$0;
            if (var9.getMReversingValues() != var2) {
               var9.mReversingValues = var2;
               var2 = var9.getMReversingValues() >> 5 & 1;
               List var11 = var9.getListGeneralParkData();
               if (var2 == 0) {
                  var4 = true;
               } else {
                  var4 = false;
               }

               var11.add(new PanoramicBtnUpdateEntity(0, var4));
               var11 = var9.getListGeneralParkData();
               if (var2 == 1) {
                  var4 = true;
               } else {
                  var4 = false;
               }

               var11.add(new PanoramicBtnUpdateEntity(1, var4));
               var2 = var9.getMReversingValues();
               var11 = var9.getListGeneralParkData();
               if ((var2 >> 3 & 1) == 1) {
                  var4 = var5;
               } else {
                  var4 = false;
               }

               var11.add(new PanoramicBtnUpdateEntity(2, var4));
            }

            var9.refreshParkUi();
            this.this$0.mCanbusInfoInt[2] = 0;
            this.this$0.mCanbusInfoInt[3] = 0;
            this.this$0.mCanbusInfoInt[4] &= 223;
            if (this.isDataChange()) {
               this.list = new ArrayList();
               super.parse(var1);
               MsgMgr var8 = this.this$0;
               ArrayList var12 = this.list;
               ArrayList var10 = var12;
               if (var12 == null) {
                  Intrinsics.throwUninitializedPropertyAccessException("list");
                  var10 = null;
               }

               var8.updateGeneralSettingData((List)var10);
               this.this$0.updateSettingActivity((Bundle)null);
            }

         }

         public OnParseListener[] setOnParseListeners() {
            return new OnParseListener[]{null, null, new MsgMgr$initParsers$1$6$$ExternalSyntheticLambda0(this, this.this$0), new MsgMgr$initParsers$1$6$$ExternalSyntheticLambda1(this, this.this$0), new MsgMgr$initParsers$1$6$$ExternalSyntheticLambda2(this, this.this$0), new MsgMgr$initParsers$1$6$$ExternalSyntheticLambda3(this, this.this$0), new MsgMgr$initParsers$1$6$$ExternalSyntheticLambda4(this, this.this$0)};
         }
      });
      var2.append(37, new Parser(this, var1) {
         final Context $context;
         private ArrayList list;
         final MsgMgr this$0;

         // $FF: synthetic method
         public static void $r8$lambda$PDUt7esW34Fs645plIiHrbzs_4Q(Object var0, MsgMgr var1, Context var2) {
            setOnParseListeners$lambda_6(var0, var1, var2);
         }

         // $FF: synthetic method
         public static void $r8$lambda$mrD_ND7IPuwu_J18_xfJXyqT2Ys(Object var0, MsgMgr var1) {
            setOnParseListeners$lambda_3(var0, var1);
         }

         {
            this.this$0 = var1;
            this.$context = var2;
         }

         private final String getOpenOrClose(int var1) {
            Context var3 = this.$context;
            String var2;
            if (var1 != 0) {
               if (var1 != 1) {
                  var2 = "null_value";
               } else {
                  var2 = "open";
               }
            } else {
               var2 = "close";
            }

            var2 = CommUtil.getStrByResId(var3, var2);
            Intrinsics.checkNotNullExpressionValue(var2, "getStrByResId(context, w…value\"\n                })");
            return var2;
         }

         private static final void setOnParseListeners$lambda_3(Object var0, MsgMgr var1) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            Intrinsics.checkNotNullParameter(var1, "this$1");
            ArrayList var3 = var0.list;
            ArrayList var2 = var3;
            if (var3 == null) {
               Intrinsics.throwUninitializedPropertyAccessException("list");
               var2 = null;
            }

            DriverUpdateEntity var4 = (DriverUpdateEntity)var1.mDriveItemIndexHashMap.get("_41_rear_radar");
            if (var4 != null) {
               var2.add(var4.setValue(var0.getOpenOrClose(var1.mCanbusInfoInt[2] >> 3 & 1)));
            }

            var4 = (DriverUpdateEntity)var1.mDriveItemIndexHashMap.get("_41_front_radar");
            if (var4 != null) {
               var2.add(var4.setValue(var0.getOpenOrClose(var1.mCanbusInfoInt[2] >> 2 & 1)));
            }

         }

         private static final void setOnParseListeners$lambda_6(Object var0, MsgMgr var1, Context var2) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            Intrinsics.checkNotNullParameter(var1, "this$1");
            Intrinsics.checkNotNullParameter(var2, "$context");
            ArrayList var6 = var0.list;
            ArrayList var4 = var6;
            if (var6 == null) {
               Intrinsics.throwUninitializedPropertyAccessException("list");
               var4 = null;
            }

            DriverUpdateEntity var5 = (DriverUpdateEntity)var1.mDriveItemIndexHashMap.get("_94_radar_status");
            if (var5 != null) {
               int var3 = var1.mCanbusInfoInt[3];
               String var7;
               if (var3 != 0) {
                  if (var3 != 1) {
                     if (var3 != 2) {
                        if (var3 != 3) {
                           var7 = "null_value";
                        } else {
                           var7 = "_94_rear_radar_fault";
                        }
                     } else {
                        var7 = "_94_front_radar_fault";
                     }
                  } else {
                     var7 = "_94_external_radar_fault";
                  }
               } else {
                  var7 = "_253_normal";
               }

               var4.add(var5.setValue(CommUtil.getStrByResId(var2, var7)));
            }

         }

         public void parse(int var1) {
            if (this.isDataChange()) {
               this.list = new ArrayList();
               super.parse(var1);
               MsgMgr var4 = this.this$0;
               ArrayList var3 = this.list;
               ArrayList var2 = var3;
               if (var3 == null) {
                  Intrinsics.throwUninitializedPropertyAccessException("list");
                  var2 = null;
               }

               var4.updateGeneralDriveData((List)var2);
               this.this$0.updateDriveDataActivity((Bundle)null);
            }

         }

         public OnParseListener[] setOnParseListeners() {
            return new OnParseListener[]{new MsgMgr$initParsers$1$7$$ExternalSyntheticLambda0(this, this.this$0), new MsgMgr$initParsers$1$7$$ExternalSyntheticLambda1(this, this.this$0, this.$context)};
         }
      });
      var2.append(38, new Parser(this) {
         private int day;
         private int hour;
         private int minute;
         private int minuteRecord;
         private int month;
         private int second;
         final MsgMgr this$0;
         private int year;

         // $FF: synthetic method
         public static void $r8$lambda$6z9_1mTJbB0GAB6oGXXEBW4xZZk(Object var0, MsgMgr var1) {
            setOnParseListeners$lambda_0(var0, var1);
         }

         // $FF: synthetic method
         public static void $r8$lambda$M8i5p2y0QuFiDpF4YqG_JqVzj2E(Object var0, MsgMgr var1) {
            setOnParseListeners$lambda_1(var0, var1);
         }

         // $FF: synthetic method
         public static void $r8$lambda$O0qeCOgmxWw62IVk0omMuMfdsDI(Object var0, MsgMgr var1) {
            setOnParseListeners$lambda_3(var0, var1);
         }

         // $FF: synthetic method
         public static void $r8$lambda$QqOQT_eIV8JvBC1pwOuicVdqEYo(Object var0, MsgMgr var1) {
            setOnParseListeners$lambda_4(var0, var1);
         }

         // $FF: synthetic method
         public static void $r8$lambda$U2ZMHfUuOfQj868g59ygP9jxwzw(Object var0, MsgMgr var1) {
            setOnParseListeners$lambda_2(var0, var1);
         }

         // $FF: synthetic method
         public static void $r8$lambda$aZJ4yR3tceDOQjEypv0e64CyqGM(Object var0, MsgMgr var1) {
            setOnParseListeners$lambda_5(var0, var1);
         }

         {
            this.this$0 = var1;
            this.minuteRecord = -1;
         }

         private static final void setOnParseListeners$lambda_0(Object var0, MsgMgr var1) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            Intrinsics.checkNotNullParameter(var1, "this$1");
            var0.year = var1.mCanbusInfoInt[2];
         }

         private static final void setOnParseListeners$lambda_1(Object var0, MsgMgr var1) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            Intrinsics.checkNotNullParameter(var1, "this$1");
            var0.month = var1.mCanbusInfoInt[3];
         }

         private static final void setOnParseListeners$lambda_2(Object var0, MsgMgr var1) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            Intrinsics.checkNotNullParameter(var1, "this$1");
            var0.day = var1.mCanbusInfoInt[4];
         }

         private static final void setOnParseListeners$lambda_3(Object var0, MsgMgr var1) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            Intrinsics.checkNotNullParameter(var1, "this$1");
            var0.hour = var1.mCanbusInfoInt[5];
         }

         private static final void setOnParseListeners$lambda_4(Object var0, MsgMgr var1) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            Intrinsics.checkNotNullParameter(var1, "this$1");
            var0.minute = var1.mCanbusInfoInt[6];
         }

         private static final void setOnParseListeners$lambda_5(Object var0, MsgMgr var1) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            Intrinsics.checkNotNullParameter(var1, "this$1");
            var0.second = var1.mCanbusInfoInt[7];
         }

         public void parse(int var1) {
            super.parse(var1);
            var1 = this.minuteRecord;
            int var2 = this.minute;
            if (var1 != var2) {
               this.minuteRecord = var2;
               StringCompanionObject var3 = StringCompanionObject.INSTANCE;
               String var4 = String.format("parse: %d年%d月%d日%d时%d分%d秒", Arrays.copyOf(new Object[]{this.year, this.month, this.day, this.hour, this.minute, this.second}, 6));
               Intrinsics.checkNotNullExpressionValue(var4, "format(format, *args)");
               Log.i("_1094_MsgMgr", var4);
            }

         }

         public OnParseListener[] setOnParseListeners() {
            return new OnParseListener[]{new MsgMgr$initParsers$1$8$$ExternalSyntheticLambda0(this, this.this$0), new MsgMgr$initParsers$1$8$$ExternalSyntheticLambda1(this, this.this$0), new MsgMgr$initParsers$1$8$$ExternalSyntheticLambda2(this, this.this$0), new MsgMgr$initParsers$1$8$$ExternalSyntheticLambda3(this, this.this$0), new MsgMgr$initParsers$1$8$$ExternalSyntheticLambda4(this, this.this$0), new MsgMgr$initParsers$1$8$$ExternalSyntheticLambda5(this, this.this$0)};
         }
      });
      var2.append(39, new Parser(this) {
         final MsgMgr this$0;

         {
            this.this$0 = var1;
         }

         public void parse(int var1) {
            if (this.isDataChange()) {
               MsgMgr var5 = this.this$0;
               ArrayList var3 = new ArrayList();
               MsgMgr var4 = this.this$0;
               SettingUpdateEntity var2 = (SettingUpdateEntity)var4.mSettingItemIndexHashMap.get("language_setup");
               if (var2 != null) {
                  var3.add(var2.setValue(ArraysKt.indexOf(MsgMgr.Companion.getMLanguageIndexs(), var4.mCanbusInfoInt[2])));
               }

               var5.updateGeneralSettingData((List)var3);
               this.this$0.updateSettingActivity((Bundle)null);
            }

         }
      });
      var2.append(40, new Parser(this) {
         final MsgMgr this$0;

         {
            this.this$0 = var1;
         }

         public void parse(int var1) {
            if (this.isDataChange()) {
               MsgMgr var4 = this.this$0;
               ArrayList var5 = new ArrayList();
               MsgMgr var6 = this.this$0;
               Map var7 = (Map)var6.mSettingItemIndexHashMap;
               int[] var3 = var6.mCanbusInfoInt;
               var1 = 2;
               int var2 = var3[2];
               Object var8;
               if (var2 != 48) {
                  if (var2 != 64) {
                     if (var2 != 144) {
                        if (var2 != 160) {
                           if (var2 != 176) {
                              if (var2 != 177) {
                                 switch (var2) {
                                    case 0:
                                       var8 = "_94_lane_keeping_mode";
                                       break;
                                    case 1:
                                       var8 = "_94_warning_intensity";
                                       break;
                                    case 2:
                                       var8 = "_94_reverse_gear_incoming_car_warning";
                                       break;
                                    case 3:
                                       var8 = "_94_tsc_control";
                                       break;
                                    case 4:
                                       var8 = "_94_cruise_control";
                                       break;
                                    case 5:
                                       var8 = "_94_automatic_engine_shutdown";
                                       break;
                                    case 6:
                                       var8 = "_94_esp_state";
                                       break;
                                    case 7:
                                       var8 = "_94_remote_control";
                                       break;
                                    case 8:
                                       var8 = "_94_auto_hold";
                                       break;
                                    case 9:
                                       var8 = "_94_lane_centring";
                                       break;
                                    case 10:
                                       var8 = "_94_sensitivity_assist";
                                       break;
                                    case 11:
                                       var8 = "_94_pre_collision_assist_drive";
                                       break;
                                    case 12:
                                       var8 = "_94_evasive_steering_assist";
                                       break;
                                    case 13:
                                       var8 = "_94_reverse_brake_assist";
                                       break;
                                    case 14:
                                       var8 = "_94_parking_sensirs";
                                       break;
                                    default:
                                       switch (var2) {
                                          case 16:
                                             var8 = "_94_distance_prompt";
                                             break;
                                          case 17:
                                             var8 = "_94_active_braking";
                                             break;
                                          case 18:
                                             var8 = "_94_sensitivity";
                                             break;
                                          case 19:
                                             var8 = "_94_blind_spot_monitoring";
                                             break;
                                          case 20:
                                             var8 = "_94_fatigue_driving_warning";
                                             break;
                                          default:
                                             switch (var2) {
                                                case 32:
                                                   var8 = "_94_atmosphere_lamp";
                                                   break;
                                                case 33:
                                                   var8 = "_94_headlight_delay";
                                                   break;
                                                case 34:
                                                   var8 = "_94_automatic_high_beam";
                                                   break;
                                                case 35:
                                                   var8 = "_334_day_light";
                                                   break;
                                                case 36:
                                                   var8 = "_250_i_went_home_with";
                                                   break;
                                                case 37:
                                                   var8 = "_94_indoor_lamp";
                                                   break;
                                                case 38:
                                                   var8 = "_94_welcome_lamp";
                                                   break;
                                                case 39:
                                                   var8 = "_94_fog_light_steering_assist";
                                                   break;
                                                default:
                                                   switch (var2) {
                                                      case 80:
                                                         var8 = "_94_switch_prohibited";
                                                         break;
                                                      case 81:
                                                         var8 = "_94_voice_feedback";
                                                         break;
                                                      case 82:
                                                         var8 = "_94_false_lock_warning";
                                                         break;
                                                      case 83:
                                                         var8 = "_94_remote_unlock";
                                                         break;
                                                      case 84:
                                                         var8 = "_94_automatically_unlock";
                                                         break;
                                                      case 85:
                                                         var8 = "_94_remote_control_on";
                                                         break;
                                                      case 86:
                                                         var8 = "_94_remote_control_off";
                                                         break;
                                                      case 87:
                                                         var8 = "_94_activate_remote_start";
                                                         break;
                                                      case 88:
                                                         var8 = "_94_air_conditioning_control";
                                                         break;
                                                      case 89:
                                                         var8 = "_94_steering_air";
                                                         break;
                                                      case 90:
                                                         var8 = "_94_cycle";
                                                         break;
                                                      case 91:
                                                         var8 = "_94_speed_lock";
                                                         break;
                                                      case 92:
                                                         var8 = "_94_one_click";
                                                         break;
                                                      case 93:
                                                         var8 = "_94_key_detection_alert";
                                                         break;
                                                      case 94:
                                                         var8 = "_94_auto_lock";
                                                         break;
                                                      case 95:
                                                         var8 = "_94_key_free";
                                                         break;
                                                      case 96:
                                                         var8 = "_94_rain_sensing_wiper";
                                                         break;
                                                      case 97:
                                                         var8 = "_94_repeat_wiper_once";
                                                         break;
                                                      case 98:
                                                         var8 = "_94_rear_wiper";
                                                         break;
                                                      case 99:
                                                         var8 = "_94_auto_wiper";
                                                         break;
                                                      case 100:
                                                         var8 = "_94_automatic_maintenance";
                                                         break;
                                                      default:
                                                         switch (var2) {
                                                            case 112:
                                                               var8 = "_94_tire_pressure_unit";
                                                               break;
                                                            case 113:
                                                               var8 = "_94_measure_unit";
                                                               break;
                                                            case 114:
                                                               var8 = "_94_temperature_unit";
                                                               break;
                                                            default:
                                                               switch (var2) {
                                                                  case 192:
                                                                     var6.refreshPanoramicParkUi(var6.mCanbusInfoInt[3]);
                                                                     var8 = Unit.INSTANCE;
                                                                     break;
                                                                  case 193:
                                                                     var6.refreshPanoramicParkUiBig(var6.mCanbusInfoInt, 1);
                                                                     var8 = Unit.INSTANCE;
                                                                     break;
                                                                  case 194:
                                                                     var6.refreshPanoramicParkUiBig(var6.mCanbusInfoInt, 2);
                                                                     var8 = Unit.INSTANCE;
                                                                     break;
                                                                  case 195:
                                                                     var6.refreshPanoramicParkUiBig(var6.mCanbusInfoInt, 3);
                                                                     var8 = Unit.INSTANCE;
                                                                     break;
                                                                  case 196:
                                                                     var6.refreshPanoramicParkUiBig(var6.mCanbusInfoInt, 4);
                                                                     var8 = Unit.INSTANCE;
                                                                     break;
                                                                  case 197:
                                                                     var6.refreshPanoramicParkUiBig(var6.mCanbusInfoInt, 5);
                                                                     var8 = Unit.INSTANCE;
                                                                     break;
                                                                  case 198:
                                                                     var6.refreshPanoramicParkUiBig(var6.mCanbusInfoInt, 6);
                                                                     var8 = Unit.INSTANCE;
                                                                     break;
                                                                  default:
                                                                     var8 = "";
                                                               }
                                                         }
                                                   }
                                             }
                                       }
                                 }
                              } else {
                                 var8 = "_94_rear_drive_acceleration_lock";
                              }
                           } else {
                              var8 = "_94_hill_descent_control";
                           }
                        } else {
                           var8 = "_94_passenger_airbag";
                        }
                     } else {
                        var8 = "_94_park_lock_control";
                     }
                  } else {
                     var8 = "_94_automatic_folding";
                  }
               } else {
                  var8 = "_94_electric_trunk";
               }

               SettingUpdateEntity var9 = (SettingUpdateEntity)var7.get(var8);
               if (var9 != null) {
                  var2 = var6.mCanbusInfoInt[2];
                  if (var2 != 33) {
                     if (var2 != 38) {
                        if (var2 != 113) {
                           var1 = var6.mCanbusInfoInt[3];
                        } else {
                           var2 = var6.mCanbusInfoInt[3];
                           if (var2 != 2) {
                              if (var2 != 3) {
                                 var1 = 0;
                              }
                           } else {
                              var1 = 1;
                           }
                        }
                     } else {
                        var1 = ArraysKt.indexOf(MsgMgr.Companion.getM0xC90x26ItemValues(), var6.mCanbusInfoInt[3]);
                     }
                  } else {
                     var1 = ArraysKt.indexOf(MsgMgr.Companion.getM0xC90x21ItemValues(), var6.mCanbusInfoInt[3]);
                  }

                  var5.add(var9.setValue(var1));
               }

               var4.updateGeneralSettingData((List)var5);
               this.this$0.updateSettingActivity((Bundle)null);
            }

         }
      });
      var2.append(41, new Parser(this, var1) {
         final Context $context;
         final MsgMgr this$0;

         {
            this.this$0 = var1;
            this.$context = var2;
         }

         public void parse(int var1) {
            if (this.isDataChange()) {
               var1 = TrackInfoUtil.getTrackAngle0(this.this$0.mCanbusInfoByte[3], this.this$0.mCanbusInfoByte[2], 0, 540, 16);
               MsgMgr var3 = this.this$0;
               Context var2 = this.$context;
               if (GeneralParkData.trackAngle != var1) {
                  GeneralParkData.trackAngle = var1;
                  var3.updateParkUi((Bundle)null, var2);
               }
            }

         }
      });
      var2.append(42, new Parser(this) {
         private ArrayList list;
         final MsgMgr this$0;

         // $FF: synthetic method
         public static void $r8$lambda$eOdby_h6kVHHqBD_YWHxli9JAxQ(MsgMgr var0, Object var1) {
            setOnParseListeners$lambda_1(var0, var1);
         }

         // $FF: synthetic method
         public static void $r8$lambda$wy7OIE_U3lmjlzAi4gP9CMpaOKE(MsgMgr var0, Object var1) {
            setOnParseListeners$lambda_3(var0, var1);
         }

         {
            this.this$0 = var1;
         }

         private static final void setOnParseListeners$lambda_1(MsgMgr var0, Object var1) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            Intrinsics.checkNotNullParameter(var1, "this$1");
            SettingUpdateEntity var4 = (SettingUpdateEntity)var0.mSettingItemIndexHashMap.get("_94_cockpit_fresh_air");
            if (var4 != null) {
               ArrayList var3 = var1.list;
               ArrayList var6 = var3;
               if (var3 == null) {
                  Intrinsics.throwUninitializedPropertyAccessException("list");
                  var6 = null;
               }

               int var2 = var0.mCanbusInfoInt[2];
               String var5;
               if (var2 != 0) {
                  if (var2 != 1) {
                     var5 = "null_value";
                  } else {
                     var5 = "disable";
                  }
               } else {
                  var5 = "enable";
               }

               var6.add(var4.setValue(var5));
            }

         }

         private static final void setOnParseListeners$lambda_3(MsgMgr var0, Object var1) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            Intrinsics.checkNotNullParameter(var1, "this$1");
            SettingUpdateEntity var6 = (SettingUpdateEntity)var0.mSettingItemIndexHashMap.get("_94_pm_2_5_in_vehicle");
            if (var6 != null) {
               ArrayList var5 = var1.list;
               ArrayList var8 = var5;
               if (var5 == null) {
                  Intrinsics.throwUninitializedPropertyAccessException("list");
                  var8 = null;
               }

               int var2 = var0.mCanbusInfoInt[3];
               int var4 = var0.mCanbusInfoInt[4] | var2 << 8;
               boolean var3 = true;
               boolean var9;
               if (var4 >= 0 && var4 < 36) {
                  var9 = true;
               } else {
                  var9 = false;
               }

               String var7;
               if (var9) {
                  var7 = "pm_excellent";
               } else {
                  if (36 <= var4 && var4 < 76) {
                     var9 = true;
                  } else {
                     var9 = false;
                  }

                  if (var9) {
                     var7 = "pm_good";
                  } else {
                     if (76 <= var4 && var4 < 116) {
                        var9 = true;
                     } else {
                        var9 = false;
                     }

                     if (var9) {
                        var7 = "pm_mild_pollution";
                     } else {
                        if (116 <= var4 && var4 < 151) {
                           var9 = true;
                        } else {
                           var9 = false;
                        }

                        if (var9) {
                           var7 = "pm_moderately_polluted";
                        } else {
                           if (151 <= var4 && var4 < 251) {
                              var9 = true;
                           } else {
                              var9 = false;
                           }

                           if (var9) {
                              var7 = "pm_heavy_pollution";
                           } else {
                              if (251 <= var4 && var4 < 510) {
                                 var9 = var3;
                              } else {
                                 var9 = false;
                              }

                              if (var9) {
                                 var7 = "pm_severe_pollution";
                              } else {
                                 var7 = "set_default";
                              }
                           }
                        }
                     }
                  }
               }

               var8.add(var6.setValue(var7));
            }

         }

         public void parse(int var1) {
            if (this.isDataChange()) {
               this.list = new ArrayList();
               super.parse(var1);
               MsgMgr var4 = this.this$0;
               ArrayList var3 = this.list;
               ArrayList var2 = var3;
               if (var3 == null) {
                  Intrinsics.throwUninitializedPropertyAccessException("list");
                  var2 = null;
               }

               var4.updateGeneralSettingData((List)var2);
               this.this$0.updateSettingActivity((Bundle)null);
            }

         }

         public OnParseListener[] setOnParseListeners() {
            return new OnParseListener[]{new MsgMgr$initParsers$1$12$$ExternalSyntheticLambda0(this.this$0, this), null, new MsgMgr$initParsers$1$12$$ExternalSyntheticLambda1(this.this$0, this)};
         }
      });
      var2.append(48, new Parser(this, var1) {
         final Context $context;
         final MsgMgr this$0;

         {
            this.this$0 = var1;
            this.$context = var2;
         }

         public void parse(int var1) {
            MsgMgr var2 = this.this$0;
            var2.updateVersionInfo(this.$context, var2.getVersionStr(var2.mCanbusInfoByte));
         }
      });
      var2.append(49, new Parser(this) {
         final MsgMgr this$0;

         // $FF: synthetic method
         public static void $r8$lambda$DNNIwRIyD33OruAo9XrJlJEAt_o(MsgMgr var0) {
            setOnParseListeners$lambda_1(var0);
         }

         // $FF: synthetic method
         public static void $r8$lambda$ieYAoIZal65KugRflZdRJoS9Pzo(MsgMgr var0) {
            setOnParseListeners$lambda_0(var0);
         }

         {
            this.this$0 = var1;
         }

         private static final void setOnParseListeners$lambda_0(MsgMgr var0) {
            Intrinsics.checkNotNullParameter(var0, "this$0");

            for(int var1 = 2; var1 < 8; ++var1) {
               if ((var0.mCanbusInfoInt[2] >> var1 & 1) == 1) {
                  if (var1 != 6) {
                     if (var1 == 7) {
                        GeneralHybirdData.isMotorDriveBattery = true;
                     }
                  } else {
                     GeneralHybirdData.isBatteryDriveMotor = true;
                  }

                  StringBuilder var3 = (new StringBuilder()).append("【0x31】混动示意图 {");
                  String var2;
                  switch (var1) {
                     case 2:
                        var2 = "电池-插电";
                        break;
                     case 3:
                        var2 = "电池-空调";
                        break;
                     case 4:
                        var2 = "电池-其他";
                        break;
                     case 5:
                        var2 = "电池-电机";
                        break;
                     case 6:
                        var2 = "电机-引擎";
                        break;
                     case 7:
                        var2 = "驱动-电机";
                        break;
                     default:
                        var2 = String.valueOf(var1);
                  }

                  Log.i("_1094_MsgMgr", var3.append(var2).append('}').toString());
               }
            }

         }

         private static final void setOnParseListeners$lambda_1(MsgMgr var0) {
            Intrinsics.checkNotNullParameter(var0, "this$0");

            for(int var1 = 5; var1 < 8; ++var1) {
               if ((var0.mCanbusInfoInt[2] >> var1 & 1) == 1) {
                  StringBuilder var3 = (new StringBuilder()).append("【0x31】混动示意图 {");
                  String var2;
                  if (var1 != 5) {
                     if (var1 != 6) {
                        if (var1 != 7) {
                           var2 = String.valueOf(var1);
                        } else {
                           var2 = "燃油-引擎";
                        }
                     } else {
                        var2 = "驱动-引擎";
                     }
                  } else {
                     var2 = "电机-引擎";
                  }

                  Log.i("_1094_MsgMgr", var3.append(var2).append('}').toString());
               }
            }

         }

         public void parse(int var1) {
            if (this.isDataChange()) {
               super.parse(var1);
            }

         }

         public OnParseListener[] setOnParseListeners() {
            GeneralHybirdData.isBatteryDriveMotor = false;
            GeneralHybirdData.isMotorDriveBattery = false;
            return new OnParseListener[]{new MsgMgr$initParsers$1$14$$ExternalSyntheticLambda0(this.this$0), new MsgMgr$initParsers$1$14$$ExternalSyntheticLambda1(this.this$0)};
         }
      });
      var2.append(50, new Parser(this, var1) {
         final Context $context;
         private List hybridUpdateList;
         final MsgMgr this$0;

         // $FF: synthetic method
         public static void $r8$lambda$8_M2DfX92yb4kG2ggS7gZxbeTto(MsgMgr var0, Context var1, Object var2) {
            setOnParseListeners$lambda_2(var0, var1, var2);
         }

         // $FF: synthetic method
         public static void $r8$lambda$OJgzjHe9GS9ehCumsUB_10PP2fk(MsgMgr var0, Context var1, Object var2) {
            setOnParseListeners$lambda_4(var0, var1, var2);
         }

         // $FF: synthetic method
         public static void $r8$lambda$_qvRheUyRq6fMKth4C6_tFkiXFw(MsgMgr var0, Object var1, Context var2) {
            setOnParseListeners$lambda_3(var0, var1, var2);
         }

         // $FF: synthetic method
         public static void $r8$lambda$hWw644h78OxycH8alEij1C4eZwI(MsgMgr var0, Context var1, Object var2) {
            setOnParseListeners$lambda_5(var0, var1, var2);
         }

         // $FF: synthetic method
         public static void $r8$lambda$kJcoSqAZ6aBlnxt9biDs0AN5mVc(MsgMgr var0, Context var1, Object var2) {
            setOnParseListeners$lambda_0(var0, var1, var2);
         }

         {
            this.this$0 = var1;
            this.$context = var2;
            this.hybridUpdateList = CollectionsKt.mutableListOf(new String[]{"-", "-", "-", "-", "-"});
         }

         private final String getValue(int var1) {
            String var2;
            switch (var1) {
               case 1:
                  var2 = "驱动力需求";
                  break;
               case 2:
                  var2 = "速度过快";
                  break;
               case 3:
                  var2 = "加热器需求";
                  break;
               case 4:
                  var2 = "空挡";
                  break;
               case 5:
                  var2 = "引擎温度过低";
                  break;
               case 6:
                  var2 = "蓄电池充电中";
                  break;
               case 7:
                  var2 = "低速档";
                  break;
               case 8:
                  var2 = "正常运行";
                  break;
               case 9:
                  var2 = "低利用率";
                  break;
               case 10:
                  var2 = "燃油保养";
                  break;
               case 11:
                  var2 = "引擎制动启动";
                  break;
               case 12:
                  var2 = "蓄电池温度";
                  break;
               default:
                  var2 = "无";
            }

            return var2;
         }

         private static final void setOnParseListeners$lambda_0(MsgMgr var0, Context var1, Object var2) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            Intrinsics.checkNotNullParameter(var1, "$context");
            Intrinsics.checkNotNullParameter(var2, "this$1");
            String var3;
            switch (var0.mCanbusInfoInt[2]) {
               case 1:
                  var3 = CommUtil.getStrByResId(var1, "_94_values_18");
                  Intrinsics.checkNotNullExpressionValue(var3, "getStrByResId(context,\"_94_values_18\")");
                  break;
               case 2:
                  var3 = CommUtil.getStrByResId(var1, "_94_values_19");
                  Intrinsics.checkNotNullExpressionValue(var3, "getStrByResId(context,\"_94_values_19\")");
                  break;
               case 3:
                  var3 = CommUtil.getStrByResId(var1, "_94_values_20");
                  Intrinsics.checkNotNullExpressionValue(var3, "getStrByResId(context,\"_94_values_20\")");
                  break;
               case 4:
                  var3 = CommUtil.getStrByResId(var1, "_94_values_21");
                  Intrinsics.checkNotNullExpressionValue(var3, "getStrByResId(context,\"_94_values_21\")");
                  break;
               case 5:
                  var3 = CommUtil.getStrByResId(var1, "_94_values_22");
                  Intrinsics.checkNotNullExpressionValue(var3, "getStrByResId(context,\"_94_values_22\")");
                  break;
               case 6:
                  var3 = CommUtil.getStrByResId(var1, "_94_values_23");
                  Intrinsics.checkNotNullExpressionValue(var3, "getStrByResId(context,\"_94_values_23\")");
                  break;
               case 7:
                  var3 = CommUtil.getStrByResId(var1, "_94_values_24");
                  Intrinsics.checkNotNullExpressionValue(var3, "getStrByResId(context,\"_94_values_24\")");
                  break;
               case 8:
                  var3 = CommUtil.getStrByResId(var1, "_94_values_25");
                  Intrinsics.checkNotNullExpressionValue(var3, "getStrByResId(context,\"_94_values_25\")");
                  break;
               case 9:
                  var3 = CommUtil.getStrByResId(var1, "_94_values_26");
                  Intrinsics.checkNotNullExpressionValue(var3, "getStrByResId(context,\"_94_values_26\")");
                  break;
               case 10:
                  var3 = CommUtil.getStrByResId(var1, "_94_values_27");
                  Intrinsics.checkNotNullExpressionValue(var3, "getStrByResId(context,\"_94_values_27\")");
                  break;
               default:
                  var3 = CommUtil.getStrByResId(var1, "_94_values_17");
                  Intrinsics.checkNotNullExpressionValue(var3, "getStrByResId(context,\"_94_values_17\")");
            }

            var2.hybridUpdateList.set(0, CommUtil.getStrByResId(var1, "_94_values_40") + ':' + var3);
            StringBuilder var5 = (new StringBuilder()).append("【0x32】整车能量状态  状态  {");
            String var4;
            switch (var0.mCanbusInfoInt[2]) {
               case 1:
                  var4 = "混合驱动";
                  break;
               case 2:
                  var4 = "正在为高压蓄电池充电";
                  break;
               case 3:
                  var4 = "怠速";
                  break;
               case 4:
                  var4 = "怠速充电";
                  break;
               case 5:
                  var4 = "电力驱动";
                  break;
               case 6:
                  var4 = "引擎驱动";
                  break;
               case 7:
                  var4 = "已遥控启动";
                  break;
               case 8:
                  var4 = "充电完成";
                  break;
               case 9:
                  var4 = "快速充电完成";
                  break;
               case 10:
                  var4 = "正在快速充电";
                  break;
               default:
                  var4 = "无";
            }

            Log.i("_1094_MsgMgr", var5.append(var4).append('}').toString());
         }

         private static final void setOnParseListeners$lambda_2(MsgMgr var0, Context var1, Object var2) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            Intrinsics.checkNotNullParameter(var1, "$context");
            Intrinsics.checkNotNullParameter(var2, "this$1");
            int var3 = var0.mCanbusInfoInt[3] << 8 | var0.mCanbusInfoInt[4];
            String var4 = (float)var0.getMsbLsbResult(var0.mCanbusInfoInt[3], var0.mCanbusInfoInt[4]) / 10.0F + CommUtil.getStrByResId(var1, "hour");
            var2.hybridUpdateList.set(1, CommUtil.getStrByResId(var1, "_94_values_41") + ':' + var4);
            Log.i("_1094_MsgMgr", "setOnParseListeners: 【0x32】整车能量状态  充满电所需时间  {" + var3 / 10 + '.' + var3 % 10 + "小时}");
         }

         private static final void setOnParseListeners$lambda_3(MsgMgr var0, Object var1, Context var2) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            Intrinsics.checkNotNullParameter(var1, "this$1");
            Intrinsics.checkNotNullParameter(var2, "$context");
            String var3 = "" + var0.mCanbusInfoInt[5] + '%';
            var1.hybridUpdateList.set(2, CommUtil.getStrByResId(var2, "_94_values_42") + ':' + var3);
            GeneralHybirdData.powerBatteryValue = var0.mCanbusInfoInt[5] / 10;
            GeneralHybirdData.powerBatteryLevel = var0.mCanbusInfoInt[5] / 10;
            Log.i("_1094_MsgMgr", "setOnParseListeners: 【0x32】整车能量状态  电池能量  {" + var0.mCanbusInfoInt[5] + "%}");
         }

         private static final void setOnParseListeners$lambda_4(MsgMgr var0, Context var1, Object var2) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            Intrinsics.checkNotNullParameter(var1, "$context");
            Intrinsics.checkNotNullParameter(var2, "this$1");
            String var3;
            switch (var0.mCanbusInfoInt[6]) {
               case 1:
                  var3 = CommUtil.getStrByResId(var1, "_94_values_28");
                  Intrinsics.checkNotNullExpressionValue(var3, "getStrByResId(context,\"_94_values_28\")");
                  break;
               case 2:
                  var3 = CommUtil.getStrByResId(var1, "_94_values_29");
                  Intrinsics.checkNotNullExpressionValue(var3, "getStrByResId(context,\"_94_values_29\")");
                  break;
               case 3:
                  var3 = CommUtil.getStrByResId(var1, "_94_values_30");
                  Intrinsics.checkNotNullExpressionValue(var3, "getStrByResId(context,\"_94_values_30\")");
                  break;
               case 4:
                  var3 = CommUtil.getStrByResId(var1, "_94_values_31");
                  Intrinsics.checkNotNullExpressionValue(var3, "getStrByResId(context,\"_94_values_31\")");
                  break;
               case 5:
                  var3 = CommUtil.getStrByResId(var1, "_94_values_32");
                  Intrinsics.checkNotNullExpressionValue(var3, "getStrByResId(context,\"_94_values_32\")");
                  break;
               case 6:
                  var3 = CommUtil.getStrByResId(var1, "_94_values_33");
                  Intrinsics.checkNotNullExpressionValue(var3, "getStrByResId(context,\"_94_values_33\")");
                  break;
               case 7:
                  var3 = CommUtil.getStrByResId(var1, "_94_values_34");
                  Intrinsics.checkNotNullExpressionValue(var3, "getStrByResId(context,\"_94_values_34\")");
                  break;
               case 8:
                  var3 = CommUtil.getStrByResId(var1, "_94_values_35");
                  Intrinsics.checkNotNullExpressionValue(var3, "getStrByResId(context,\"_94_values_35\")");
                  break;
               case 9:
                  var3 = CommUtil.getStrByResId(var1, "_94_values_36");
                  Intrinsics.checkNotNullExpressionValue(var3, "getStrByResId(context,\"_94_values_36\")");
                  break;
               case 10:
                  var3 = CommUtil.getStrByResId(var1, "_94_values_37");
                  Intrinsics.checkNotNullExpressionValue(var3, "getStrByResId(context,\"_94_values_37\")");
                  break;
               case 11:
                  var3 = CommUtil.getStrByResId(var1, "_94_values_38");
                  Intrinsics.checkNotNullExpressionValue(var3, "getStrByResId(context,\"_94_values_38\")");
                  break;
               case 12:
                  var3 = CommUtil.getStrByResId(var1, "_94_values_39");
                  Intrinsics.checkNotNullExpressionValue(var3, "getStrByResId(context,\"_94_values_39\")");
                  break;
               default:
                  var3 = CommUtil.getStrByResId(var1, "_94_values_17");
                  Intrinsics.checkNotNullExpressionValue(var3, "getStrByResId(context,\"_94_values_17\")");
            }

            var2.hybridUpdateList.set(3, CommUtil.getStrByResId(var1, "_94_values_43") + ':' + var3);
            Log.i("_1094_MsgMgr", "setOnParseListeners: 【0x32】整车能量状态  启动原因1  {" + var2.getValue(var0.mCanbusInfoInt[6]) + '}');
         }

         private static final void setOnParseListeners$lambda_5(MsgMgr var0, Context var1, Object var2) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            Intrinsics.checkNotNullParameter(var1, "$context");
            Intrinsics.checkNotNullParameter(var2, "this$1");
            String var3;
            switch (var0.mCanbusInfoInt[7]) {
               case 1:
                  var3 = CommUtil.getStrByResId(var1, "_94_values_28");
                  Intrinsics.checkNotNullExpressionValue(var3, "getStrByResId(context,\"_94_values_28\")");
                  break;
               case 2:
                  var3 = CommUtil.getStrByResId(var1, "_94_values_29");
                  Intrinsics.checkNotNullExpressionValue(var3, "getStrByResId(context,\"_94_values_29\")");
                  break;
               case 3:
                  var3 = CommUtil.getStrByResId(var1, "_94_values_30");
                  Intrinsics.checkNotNullExpressionValue(var3, "getStrByResId(context,\"_94_values_30\")");
                  break;
               case 4:
                  var3 = CommUtil.getStrByResId(var1, "_94_values_31");
                  Intrinsics.checkNotNullExpressionValue(var3, "getStrByResId(context,\"_94_values_31\")");
                  break;
               case 5:
                  var3 = CommUtil.getStrByResId(var1, "_94_values_32");
                  Intrinsics.checkNotNullExpressionValue(var3, "getStrByResId(context,\"_94_values_32\")");
                  break;
               case 6:
                  var3 = CommUtil.getStrByResId(var1, "_94_values_33");
                  Intrinsics.checkNotNullExpressionValue(var3, "getStrByResId(context,\"_94_values_33\")");
                  break;
               case 7:
                  var3 = CommUtil.getStrByResId(var1, "_94_values_34");
                  Intrinsics.checkNotNullExpressionValue(var3, "getStrByResId(context,\"_94_values_34\")");
                  break;
               case 8:
                  var3 = CommUtil.getStrByResId(var1, "_94_values_35");
                  Intrinsics.checkNotNullExpressionValue(var3, "getStrByResId(context,\"_94_values_35\")");
                  break;
               case 9:
                  var3 = CommUtil.getStrByResId(var1, "_94_values_36");
                  Intrinsics.checkNotNullExpressionValue(var3, "getStrByResId(context,\"_94_values_36\")");
                  break;
               case 10:
                  var3 = CommUtil.getStrByResId(var1, "_94_values_37");
                  Intrinsics.checkNotNullExpressionValue(var3, "getStrByResId(context,\"_94_values_37\")");
                  break;
               case 11:
                  var3 = CommUtil.getStrByResId(var1, "_94_values_38");
                  Intrinsics.checkNotNullExpressionValue(var3, "getStrByResId(context,\"_94_values_38\")");
                  break;
               case 12:
                  var3 = CommUtil.getStrByResId(var1, "_94_values_39");
                  Intrinsics.checkNotNullExpressionValue(var3, "getStrByResId(context,\"_94_values_39\")");
                  break;
               default:
                  var3 = CommUtil.getStrByResId(var1, "_94_values_17");
                  Intrinsics.checkNotNullExpressionValue(var3, "getStrByResId(context,\"_94_values_17\")");
            }

            var2.hybridUpdateList.set(4, CommUtil.getStrByResId(var1, "_94_values_44") + ':' + var3);
            Log.i("_1094_MsgMgr", "setOnParseListeners: 【0x32】整车能量状态  启动原因2  {" + var2.getValue(var0.mCanbusInfoInt[7]) + '}');
         }

         public final List getHybridUpdateList() {
            return this.hybridUpdateList;
         }

         public void parse(int var1) {
            if (this.isDataChange()) {
               super.parse(var1);
               GeneralHybirdData.valueList = this.hybridUpdateList;
               this.this$0.updateHybirdActivity((Bundle)null);
            }

         }

         public final void setHybridUpdateList(List var1) {
            Intrinsics.checkNotNullParameter(var1, "<set-?>");
            this.hybridUpdateList = var1;
         }

         public OnParseListener[] setOnParseListeners() {
            return new OnParseListener[]{new MsgMgr$initParsers$1$15$$ExternalSyntheticLambda0(this.this$0, this.$context, this), null, new MsgMgr$initParsers$1$15$$ExternalSyntheticLambda1(this.this$0, this.$context, this), new MsgMgr$initParsers$1$15$$ExternalSyntheticLambda2(this.this$0, this, this.$context), new MsgMgr$initParsers$1$15$$ExternalSyntheticLambda3(this.this$0, this.$context, this), new MsgMgr$initParsers$1$15$$ExternalSyntheticLambda4(this.this$0, this.$context, this)};
         }
      });
      var2.append(64, new Parser(this) {
         final MsgMgr this$0;

         {
            this.this$0 = var1;
         }

         public void parse(int var1) {
            StringBuilder var3 = (new StringBuilder()).append("parse: SYNC_VERSION_");
            var1 = this.this$0.mCanbusInfoInt[2];
            String var2;
            if (var1 != 0) {
               if (var1 != 1) {
                  if (var1 != 2) {
                     if (var1 != 3) {
                        var2 = "";
                     } else {
                        var2 = "V3";
                     }
                  } else {
                     var2 = "V2";
                  }
               } else {
                  var2 = "V1";
               }
            } else {
               var2 = "NONE";
            }

            Log.i("_1094_MsgMgr", var3.append(var2).toString());
         }
      });
      var2.append(80, new Parser(this) {
         private final SparseIntArray syncMenuIconResIdArray;
         final MsgMgr this$0;

         // $FF: synthetic method
         public static void $r8$lambda$0QvOAExW_0ClvtgKWmmOacVBIx8() {
            setOnParseListeners$lambda_4();
         }

         // $FF: synthetic method
         public static void $r8$lambda$7z1Y_lPzWQ_oCs1omot3Cm3wd_o() {
            setOnParseListeners$lambda_3();
         }

         // $FF: synthetic method
         public static void $r8$lambda$LWOh_3O_BmCSBBiHBJq5x8hgPaM(MsgMgr var0) {
            setOnParseListeners$lambda_5(var0);
         }

         // $FF: synthetic method
         public static void $r8$lambda$NzRRAu5jxdKTYLKYQepA0TQJr3g(Object var0, MsgMgr var1) {
            setOnParseListeners$lambda_6(var0, var1);
         }

         // $FF: synthetic method
         public static void $r8$lambda$aWK41oFt6rvTwemQ9KKDTdZc6_Q(MsgMgr var0) {
            setOnParseListeners$lambda_2(var0);
         }

         // $FF: synthetic method
         public static void $r8$lambda$bIyZ69cuNJcMfghf0QzEC3B3OyA() {
            setOnParseListeners$lambda_7();
         }

         // $FF: synthetic method
         public static void $r8$lambda$dAl2mgOYNsyE3MKFcYHNd65Pfcs() {
            setOnParseListeners$lambda_1();
         }

         // $FF: synthetic method
         public static void $r8$lambda$h_wsOe_9IU_IbmxPLH18WnDVT6c() {
            setOnParseListeners$lambda_8();
         }

         {
            this.this$0 = var1;
            SparseIntArray var2 = new SparseIntArray();
            this.syncMenuIconResIdArray = var2;
            var2.put(0, 2131233621);
            var2.append(2, 2131233573);
            var2.append(10, 2131233461);
            var2.append(8, 2131233611);
            var2.append(5, 2131233586);
            var2.append(12, 2131233478);
         }

         private static final void setOnParseListeners$lambda_1() {
         }

         private static final void setOnParseListeners$lambda_2(MsgMgr var0) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            GeneralSyncData.mSelectedLineIndex = var0.mCanbusInfoInt[3] - 1;
         }

         private static final void setOnParseListeners$lambda_3() {
         }

         private static final void setOnParseListeners$lambda_4() {
         }

         private static final void setOnParseListeners$lambda_5(MsgMgr var0) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            if (var0.mCanbusInfoInt[5] != 0) {
               GeneralSyncData.mSelectedLineIndex = var0.mCanbusInfoInt[6] - 1;
            }

         }

         private static final void setOnParseListeners$lambda_6(Object var0, MsgMgr var1) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            Intrinsics.checkNotNullParameter(var1, "this$1");
            GeneralSyncData.mSyncTopIconResIdArray[0] = var0.syncMenuIconResIdArray.get(var1.mCanbusInfoInt[7]);
         }

         private static final void setOnParseListeners$lambda_7() {
         }

         private static final void setOnParseListeners$lambda_8() {
         }

         public void parse(int var1) {
            if (this.isDataChange()) {
               super.parse(var1);
               this.this$0.updateSyncActivity((Bundle)null);
            }

         }

         public OnParseListener[] setOnParseListeners() {
            return new OnParseListener[]{new MsgMgr$initParsers$1$17$$ExternalSyntheticLambda0(), new MsgMgr$initParsers$1$17$$ExternalSyntheticLambda1(this.this$0), new MsgMgr$initParsers$1$17$$ExternalSyntheticLambda2(), new MsgMgr$initParsers$1$17$$ExternalSyntheticLambda3(), new MsgMgr$initParsers$1$17$$ExternalSyntheticLambda4(this.this$0), new MsgMgr$initParsers$1$17$$ExternalSyntheticLambda5(this, this.this$0), new MsgMgr$initParsers$1$17$$ExternalSyntheticLambda6(), new MsgMgr$initParsers$1$17$$ExternalSyntheticLambda7()};
         }
      });
      var2.append(81, new Parser(this, var1) {
         final Context $context;
         private final List datasBuffer;
         private byte[] datasRecord;
         final MsgMgr this$0;

         {
            this.this$0 = var1;
            this.$context = var2;
            this.datasBuffer = (List)(new ArrayList());
            (new TimerUtil()).startTimer((TimerTask)(new TimerTask(this) {
               final <undefinedtype> this$0;

               {
                  this.this$0 = var1;
               }

               public void run() {
                  List var2 = this.this$0.datasBuffer;
                  <undefinedtype> var1 = this.this$0;
                  if (var2.size() != 0) {
                     var1.parse((byte[])CollectionsKt.first(var2));
                     var2.remove(CollectionsKt.first(var2));
                  }

               }
            }), 0L, 32L);
         }

         private final String getSyncInfo(byte[] var1) {
            String var7;
            if (var1.length < 8) {
               var7 = "";
            } else {
               int var4 = var1.length;
               int var3 = var1.length;
               int var2 = 7;
               int var5 = ProgressionUtilKt.getProgressionLastElement(7, var3, 2);
               var3 = var4;
               if (7 <= var5) {
                  while(true) {
                     var3 = var2 - 1;
                     if (var1[var3] == 0 && var1[var2] == 0) {
                        break;
                     }

                     var3 = var4;
                     if (var2 == var5) {
                        break;
                     }

                     var2 += 2;
                  }
               }

               var1 = ArraysKt.copyOfRange(var1, 6, var3);
               Charset var6 = Charset.forName("unicode");
               Intrinsics.checkNotNullExpressionValue(var6, "forName(\"unicode\")");
               var7 = new String(var1, var6);
            }

            return var7;
         }

         private final boolean isDataChange(byte[] var1) {
            boolean var2;
            if (Arrays.equals(this.datasRecord, var1)) {
               var2 = false;
            } else {
               var1 = Arrays.copyOf(var1, var1.length);
               Intrinsics.checkNotNullExpressionValue(var1, "copyOf(this, size)");
               this.datasRecord = var1;
               var2 = true;
            }

            return var2;
         }

         private final void parse(byte[] var1) {
            MsgMgr var9 = this.this$0;
            Context var10 = this.$context;
            if (this.isDataChange(var1)) {
               int var3 = var1[2];
               boolean var5 = false;
               boolean var8 = false;
               boolean var6 = true;
               boolean var7 = true;
               boolean var2;
               if (1 <= var3 && var3 < 11) {
                  var2 = true;
               } else {
                  var2 = false;
               }

               int var4;
               int var15;
               if (var2) {
                  var15 = var1[3];
                  var4 = (var3 - 1) % 5;
                  var3 = var9.getSyncIconResId(var10, "ford_sync_icon_" + var1[4]);
                  var15 = var9.getSyncIconResId(var10, "ford_sync_icon_" + var1[5]);
                  String var17 = this.getSyncInfo(var1);
                  var5 = var8;
                  if ((var1[3] >> 4 & 1) == 1) {
                     var5 = true;
                  }

                  List var12 = GeneralSyncData.mInfoUpdateEntityList;
                  Iterator var13 = var12.iterator();

                  while(var13.hasNext()) {
                     SyncListUpdateEntity var11 = (SyncListUpdateEntity)var13.next();
                     if (var11.getIndex() == var4) {
                        var11.setLeftIconResId(var3).setRightIconResId(var15).setInfo(var17).setEnable(var5);
                        var9.updateSyncActivity((Bundle)null);
                        return;
                     }
                  }

                  var12.add((new SyncListUpdateEntity(var4)).setLeftIconResId(var3).setRightIconResId(var15).setInfo(var17).setEnable(var5));
               } else {
                  if (11 <= var3 && var3 < 19) {
                     var2 = true;
                  } else {
                     var2 = false;
                  }

                  if (var2) {
                     String var14;
                     label64: {
                        label89: {
                           var3 = (var3 - 11) % 4;
                           var15 = 2131233621;
                           var4 = var1[3] & 15;
                           if (var4 != 0) {
                              label60: {
                                 if (var4 != 2) {
                                    if (var4 == 3) {
                                       var15 = var9.getSyncIconResId(var10, "ford_sync_icon_" + var1[4]);
                                       var5 = true;
                                       var6 = var7;
                                       break label60;
                                    }

                                    if (var4 == 10) {
                                       var14 = this.getSyncInfo(var1);
                                       break label89;
                                    }

                                    if (var4 == 11) {
                                       var14 = this.getSyncInfo(var1);
                                       var5 = true;
                                       break label64;
                                    }
                                 } else {
                                    var15 = var9.getSyncIconResId(var10, "ford_sync_icon_" + var1[4]);
                                 }

                                 var14 = "";
                                 break label89;
                              }
                           } else {
                              var6 = false;
                           }

                           var14 = "";
                           break label64;
                        }

                        var6 = false;
                        var5 = true;
                     }

                     List var18 = GeneralSyncData.mSoftKeyUpdateEntityList;
                     Intrinsics.checkNotNullExpressionValue(var18, "");
                     Iterator var19 = ((Iterable)var18).iterator();

                     while(var19.hasNext()) {
                        SyncSoftKeyUpdateEntity var20 = (SyncSoftKeyUpdateEntity)var19.next();
                        if (var20.getIndex() == var3) {
                           var20.setName(var14);
                           var20.setImageResId(var15);
                           var20.setSelected(var6);
                           var20.setVisible(var5);
                           var9.updateSyncActivity((Bundle)null);
                           return;
                        }
                     }

                     SyncSoftKeyUpdateEntity var16 = new SyncSoftKeyUpdateEntity(var3);
                     var16.setName(var14);
                     var16.setImageResId(var15);
                     var16.setSelected(var6);
                     var16.setVisible(var5);
                     var18.add(var16);
                  }
               }
            }

         }

         public void parse(int var1) {
            this.datasBuffer.add(this.this$0.mCanbusInfoByte);
         }
      });
      var2.append(82, new Parser(this) {
         final MsgMgr this$0;

         {
            this.this$0 = var1;
         }

         public void parse(int var1) {
            if (this.isDataChange()) {
               int[] var3 = this.this$0.mCanbusInfoInt;
               StringCompanionObject var2 = StringCompanionObject.INSTANCE;
               String var4 = String.format("%02d:%02d:%02d", Arrays.copyOf(new Object[]{var3[2], var3[3], var3[4]}, 3));
               Intrinsics.checkNotNullExpressionValue(var4, "format(format, *args)");
               GeneralSyncData.mSyncTime = var4;
               GeneralSyncData.mIsSyncTimeChange = true;
               this.this$0.updateSyncActivity((Bundle)null);
            }

         }
      });
      var2.append(83, new Parser(this) {
         final MsgMgr this$0;

         {
            this.this$0 = var1;
         }

         public void parse(int var1) {
            if (this.isDataChange()) {
               int[] var2 = this.this$0.mCanbusInfoInt;
               StringCompanionObject var3 = StringCompanionObject.INSTANCE;
               String var4 = String.format("%02d:%02d:%02d", Arrays.copyOf(new Object[]{var2[2], var2[3], var2[4]}, 3));
               Intrinsics.checkNotNullExpressionValue(var4, "format(format, *args)");
               GeneralSyncData.mSyncTime = var4;
               GeneralSyncData.mIsSyncTimeChange = true;
               this.this$0.updateSyncActivity((Bundle)null);
            }

         }
      });
      var2.append(85, new Parser(this, var1) {
         final Context $context;
         final MsgMgr this$0;

         {
            this.this$0 = var1;
            this.$context = var2;
         }

         public void parse(int var1) {
            if (this.isDataChange()) {
               var1 = this.this$0.mCanbusInfoInt[2];
               if (var1 != 0) {
                  if (var1 != 1) {
                     if (var1 == 2) {
                        this.this$0.realKeyClick(this.$context, 130);
                     }
                  } else {
                     if (this.this$0.getSourceId() != SourceConstantsDef.SOURCE_ID.FM) {
                        this.this$0.realKeyClick(this.$context, 62);
                     }

                     var1 = this.this$0.mCanbusInfoInt[3];
                     if (var1 != 1) {
                        if (var1 != 2) {
                           if (var1 != 17) {
                              if (var1 == 18) {
                                 this.this$0.changeBandFm2();
                              }
                           } else {
                              this.this$0.changeBandFm1();
                           }
                        } else {
                           this.this$0.changeBandAm2();
                        }
                     } else {
                        this.this$0.changeBandAm1();
                     }
                  }
               } else {
                  this.this$0.enterNoSource();
                  this.this$0.realKeyClick(this.$context, 52);
               }
            }

         }
      });
      var2.append(86, new Parser(this, var1) {
         final Context $context;
         final MsgMgr this$0;

         {
            this.this$0 = var1;
            this.$context = var2;
         }

         public void parse(int var1) {
            if (this.isDataChange()) {
               int var4 = this.this$0.mCanbusInfoInt[2];
               boolean var2 = false;
               boolean var3 = false;
               var1 = 1;
               boolean var8;
               MsgMgr var11;
               if (var4 != 1) {
                  int var9;
                  if (var4 != 2) {
                     if (var4 != 17) {
                        if (var4 == 18) {
                           MsgMgr var7 = this.this$0;
                           Context var5 = this.$context;
                           MpegConstantsDef.K_SELECT var6 = MpegConstantsDef.K_SELECT.TITLE_SELECT;
                           var9 = this.this$0.mCanbusInfoInt[3];
                           if (var9 >= 1) {
                              var1 = var9;
                           }

                           var7.mpegDiscGoto(var5, var6, var1);
                        }
                     } else {
                        switch (this.this$0.mCanbusInfoInt[3]) {
                           case 1:
                           case 2:
                              this.this$0.mpegPlay(this.$context);
                              break;
                           case 3:
                              this.this$0.mpegShuffle(this.$context);
                              break;
                           case 4:
                              this.this$0.mpegRepeat(this.$context);
                              break;
                           case 5:
                              this.this$0.mpegPrev(this.$context);
                              break;
                           case 6:
                              this.this$0.mpegNext(this.$context);
                        }
                     }
                  } else {
                     var9 = this.this$0.mCanbusInfoInt[3];
                     var11 = this.this$0;
                     Context var12 = this.$context;
                     var8 = var3;
                     if (1 <= var9) {
                        var8 = var3;
                        if (var9 < 7) {
                           var8 = true;
                        }
                     }

                     if (var8) {
                        var11.realKeyClick(var12, var9 + 32);
                     }
                  }
               } else {
                  int var10 = this.this$0.mCanbusInfoInt[4] << 8 | this.this$0.mCanbusInfoInt[3];
                  var11 = this.this$0;
                  if (8750 <= var10 && var10 < 10801) {
                     var8 = true;
                  } else {
                     var8 = false;
                  }

                  if (var8) {
                     var11.setCurrentFreqFm(String.valueOf((float)var10 / (float)100));
                  } else {
                     var8 = var2;
                     if (522 <= var10) {
                        var8 = var2;
                        if (var10 < 1621) {
                           var8 = true;
                        }
                     }

                     if (var8) {
                        var11.setCurrentFreqAm(String.valueOf(var10));
                     }
                  }
               }
            }

         }
      });
      var2.append(96, new Parser(this, var1) {
         final Context $context;
         final MsgMgr this$0;

         // $FF: synthetic method
         public static void $r8$lambda$CHUCpQkyZ_fSE49SJmX07XYEFWs(ActivePark var0, MsgMgr var1, Context var2) {
            parse$lambda_3$lambda_2(var0, var1, var2);
         }

         {
            this.this$0 = var1;
            this.$context = var2;
         }

         private static final void parse$lambda_3$lambda_2(ActivePark var0, MsgMgr var1, Context var2) {
            Intrinsics.checkNotNullParameter(var0, "$this_run");
            Intrinsics.checkNotNullParameter(var1, "this$0");
            Intrinsics.checkNotNullParameter(var2, "$context");
            var0.stopTimer();
            int var5 = var1.mCanbusInfoInt[2];
            boolean var4 = true;
            boolean var3 = var4;
            if (var5 != 0) {
               var3 = var4;
               if (var1.mCanbusInfoInt[3] != 0) {
                  if (var1.mCanbusInfoInt[3] == 1) {
                     var3 = var4;
                  } else {
                     var3 = false;
                  }
               }
            }

            var0.setActiveParkActive(var3 ^ true);
            var1.updateParkUi((Bundle)null, var2);
            if (!var3) {
               Log.i("_1094_MsgMgr", "parse: CallBackInterface   continue  " + var1.mCanbusInfoInt[3] + "   " + var1.mActiveParkBeamArray.get(var1.mCanbusInfoInt[3]));
               ActivePark.ActiveParkBeam var6 = (ActivePark.ActiveParkBeam)var1.mActiveParkBeamArray.get(var1.mCanbusInfoInt[3]);
               if (var6 != null) {
                  var0.update(var6);
                  if (var1.mCanbusInfoInt[3] == 28) {
                     var0.countDown(var2, var6.getMessageResId());
                  }
               }

            }
         }

         public void parse(int var1) {
            ActivePark var2 = this.this$0.mActivePark;
            if (var2 != null) {
               MsgMgr var4 = this.this$0;
               Context var3 = this.$context;
               if (this.isDataChange()) {
                  var4.runOnUi(new MsgMgr$initParsers$1$23$$ExternalSyntheticLambda0(var2, var4, var3));
               }
            }

         }
      });
      var2.append(97, new Parser(this) {
         private ArrayList list;
         final MsgMgr this$0;

         // $FF: synthetic method
         public static void $r8$lambda$BaDVQEQ6uUilFPQTgOusoPtd3OQ(MsgMgr var0, Object var1) {
            setOnParseListeners$lambda_7(var0, var1);
         }

         // $FF: synthetic method
         public static void $r8$lambda$qTBcAwV12JMWwXqsWgE1q_PhBqI(Object var0, MsgMgr var1) {
            setOnParseListeners$lambda_4(var0, var1);
         }

         {
            this.this$0 = var1;
         }

         private static final void setOnParseListeners$lambda_4(Object var0, MsgMgr var1) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            Intrinsics.checkNotNullParameter(var1, "this$1");
            ArrayList var6 = var0.list;
            ArrayList var4 = var6;
            if (var6 == null) {
               Intrinsics.throwUninitializedPropertyAccessException("list");
               var4 = null;
            }

            SettingUpdateEntity var5 = (SettingUpdateEntity)var1.mSettingItemIndexHashMap.get("_94_self_adaption");
            int var2;
            if (var5 != null) {
               var2 = var1.mCanbusInfoInt[2] >> 7 & 1;
               String var7;
               if (var2 != 0) {
                  if (var2 != 1) {
                     var7 = "null_value";
                  } else {
                     var7 = "enable";
                  }
               } else {
                  var7 = "disable";
               }

               var4.add(var5.setValue(var7));
            }

            SettingUpdateEntity var8 = (SettingUpdateEntity)var1.mSettingItemIndexHashMap.get("_270_setting_12");
            if (var8 != null) {
               int var3 = var1.mCanbusInfoInt[2] & 127;
               var2 = var3;
               if (var1.mDifferent != 13) {
                  if (var3 == 0) {
                     var2 = var3;
                  } else {
                     var2 = var3 - 1;
                  }
               }

               var4.add(var8.setValue(var2));
            }

         }

         private static final void setOnParseListeners$lambda_7(MsgMgr var0, Object var1) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            Intrinsics.checkNotNullParameter(var1, "this$1");
            SettingUpdateEntity var3 = (SettingUpdateEntity)var0.mSettingItemIndexHashMap.get("_270_setting_13");
            if (var3 != null) {
               int var2 = var0.mCanbusInfoInt[3];
               if (var2 % 5 == 0) {
                  ArrayList var5 = var1.list;
                  ArrayList var4 = var5;
                  if (var5 == null) {
                     Intrinsics.throwUninitializedPropertyAccessException("list");
                     var4 = null;
                  }

                  var2 /= 5;
                  var4.add(var3.setValue(var2).setProgress(var2));
               }
            }

         }

         public void parse(int var1) {
            if (this.isDataChange()) {
               MsgMgr var2 = this.this$0;
               byte[] var3 = Arrays.copyOf(var2.mCanbusInfoByte, this.this$0.mCanbusInfoByte.length);
               Intrinsics.checkNotNullExpressionValue(var3, "copyOf(this, newSize)");
               var2.m0x61DataRecord = var3;
               this.list = new ArrayList();
               super.parse(var1);
               MsgMgr var4 = this.this$0;
               ArrayList var6 = this.list;
               ArrayList var5 = var6;
               if (var6 == null) {
                  Intrinsics.throwUninitializedPropertyAccessException("list");
                  var5 = null;
               }

               var4.updateGeneralSettingData((List)var5);
               this.this$0.updateSettingActivity((Bundle)null);
            }

         }

         public OnParseListener[] setOnParseListeners() {
            return new OnParseListener[]{new MsgMgr$initParsers$1$24$$ExternalSyntheticLambda0(this, this.this$0), new MsgMgr$initParsers$1$24$$ExternalSyntheticLambda1(this.this$0, this)};
         }
      });
      var2.append(98, new Parser(this, var1) {
         final Context $context;
         private int[] amplfierDataRecord;
         private ArrayList list;
         private int[] settingsDataRecord;
         final MsgMgr this$0;

         // $FF: synthetic method
         public static void $r8$lambda$2EI9Ak56JSIe57MGWJVTwe8tHio(MsgMgr var0) {
            setOnParseListeners$lambda_7(var0);
         }

         // $FF: synthetic method
         public static void $r8$lambda$58MHS4_kVXHDXmS2bJmhxfmYs1I(MsgMgr var0, Object var1) {
            setOnParseListeners$lambda_6(var0, var1);
         }

         // $FF: synthetic method
         public static void $r8$lambda$CRhJ3z1M3Ou1siAJfd8E_OHaXtA(MsgMgr var0) {
            setOnParseListeners$lambda_0(var0);
         }

         // $FF: synthetic method
         public static void $r8$lambda$KlUdY3vPACtp4P1txK_aR3EbD4s(MsgMgr var0) {
            setOnParseListeners$lambda_1(var0);
         }

         // $FF: synthetic method
         public static void $r8$lambda$uodrN0kl6bzLgbRRqc0xjHLCytQ(MsgMgr var0, Object var1) {
            setOnParseListeners$lambda_3(var0, var1);
         }

         {
            this.this$0 = var1;
            this.$context = var2;
         }

         private final boolean isNeedUpdateAmplifierActivity() {
            int[] var3 = new int[4];
            int var1 = this.this$0.mCanbusInfoInt[2];
            boolean var2 = false;
            var3[0] = var1;
            var3[1] = this.this$0.mCanbusInfoInt[3];
            var3[2] = this.this$0.mCanbusInfoInt[4] & 240;
            var3[3] = this.this$0.mCanbusInfoInt[6];
            if (!Arrays.equals(this.amplfierDataRecord, var3)) {
               var3 = Arrays.copyOf(var3, 4);
               Intrinsics.checkNotNullExpressionValue(var3, "copyOf(this, newSize)");
               this.amplfierDataRecord = var3;
               var2 = true;
            }

            return var2;
         }

         private final boolean isNeedUpdateSettingActivity() {
            int[] var3 = new int[2];
            int var1 = this.this$0.mCanbusInfoInt[4];
            boolean var2 = false;
            var3[0] = var1 & 15;
            var3[1] = this.this$0.mCanbusInfoInt[5];
            if (!Arrays.equals(this.settingsDataRecord, var3)) {
               var3 = Arrays.copyOf(var3, 2);
               Intrinsics.checkNotNullExpressionValue(var3, "copyOf(this, newSize)");
               this.settingsDataRecord = var3;
               var2 = true;
            }

            return var2;
         }

         private static final void setOnParseListeners$lambda_0(MsgMgr var0) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            GeneralAmplifierData.bandTreble = var0.mCanbusInfoInt[2] >> 4 & 15;
            GeneralAmplifierData.bandMiddle = var0.mCanbusInfoInt[2] & 15;
         }

         private static final void setOnParseListeners$lambda_1(MsgMgr var0) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            GeneralAmplifierData.bandBass = var0.mCanbusInfoInt[3] >> 4 & 15;
            GeneralAmplifierData.frontRear = (var0.mCanbusInfoInt[3] & 15) - 7;
         }

         private static final void setOnParseListeners$lambda_3(MsgMgr var0, Object var1) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            Intrinsics.checkNotNullParameter(var1, "this$1");
            GeneralAmplifierData.leftRight = (var0.mCanbusInfoInt[4] >> 4 & 15) - 7;
            SettingUpdateEntity var3 = (SettingUpdateEntity)var0.mSettingItemIndexHashMap.get("speed_linkage_volume_adjustment");
            if (var3 != null) {
               ArrayList var2 = var1.list;
               ArrayList var4 = var2;
               if (var2 == null) {
                  Intrinsics.throwUninitializedPropertyAccessException("list");
                  var4 = null;
               }

               var4.add(var3.setValue(var0.mCanbusInfoInt[4] & 15));
            }

         }

         private static final void setOnParseListeners$lambda_6(MsgMgr var0, Object var1) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            Intrinsics.checkNotNullParameter(var1, "this$1");
            SettingUpdateEntity var5 = (SettingUpdateEntity)var0.mSettingItemIndexHashMap.get("_94_sound_effect_mode");
            Object var3 = null;
            if (var5 != null) {
               ArrayList var4 = var1.list;
               ArrayList var2 = var4;
               if (var4 == null) {
                  Intrinsics.throwUninitializedPropertyAccessException("list");
                  var2 = null;
               }

               var2.add(var5.setValue(var0.mCanbusInfoInt[5] >> 7 & 1));
            }

            SettingUpdateEntity var7 = (SettingUpdateEntity)var0.mSettingItemIndexHashMap.get("_94_position");
            if (var7 != null) {
               ArrayList var6 = var1.list;
               if (var6 == null) {
                  Intrinsics.throwUninitializedPropertyAccessException("list");
                  var6 = (ArrayList)var3;
               }

               var6.add(var7.setValue(var0.mCanbusInfoInt[5] >> 5 & 3));
            }

         }

         private static final void setOnParseListeners$lambda_7(MsgMgr var0) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            GeneralAmplifierData.volume = var0.mCanbusInfoInt[6];
         }

         public void parse(int var1) {
            if (this.isDataChange()) {
               this.list = new ArrayList();
               super.parse(var1);
               if (this.isNeedUpdateAmplifierActivity()) {
                  this.this$0.updateAmplifierActivity((Bundle)null);
                  MsgMgr var2 = this.this$0;
                  var2.saveAmplifierData(this.$context, var2.mCanId);
               }

               if (this.isNeedUpdateSettingActivity()) {
                  MsgMgr var4 = this.this$0;
                  ArrayList var3 = this.list;
                  ArrayList var5 = var3;
                  if (var3 == null) {
                     Intrinsics.throwUninitializedPropertyAccessException("list");
                     var5 = null;
                  }

                  var4.updateGeneralSettingData((List)var5);
                  this.this$0.updateSettingActivity((Bundle)null);
               }
            }

         }

         public OnParseListener[] setOnParseListeners() {
            return new OnParseListener[]{new MsgMgr$initParsers$1$25$$ExternalSyntheticLambda0(this.this$0), new MsgMgr$initParsers$1$25$$ExternalSyntheticLambda1(this.this$0), new MsgMgr$initParsers$1$25$$ExternalSyntheticLambda2(this.this$0, this), new MsgMgr$initParsers$1$25$$ExternalSyntheticLambda3(this.this$0, this), new MsgMgr$initParsers$1$25$$ExternalSyntheticLambda4(this.this$0)};
         }
      });
      var2.append(99, new Parser(this, var1) {
         final Context $context;
         private ArrayList driveUpdateList;
         final MsgMgr this$0;
         private ArrayList tireUpdateList;

         // $FF: synthetic method
         public static void $r8$lambda$3wYRfnlj7DveLnWFpcwRyltMqnM(MsgMgr var0, Object var1) {
            setOnParseListeners$lambda_4(var0, var1);
         }

         // $FF: synthetic method
         public static void $r8$lambda$HlDX3S86ySWrxXhbO6AvBiyYB_Y(MsgMgr var0, Object var1) {
            setOnParseListeners$lambda_12(var0, var1);
         }

         // $FF: synthetic method
         public static void $r8$lambda$SzXgkBOBysryl1DxX2Ma7OIe7gA(MsgMgr var0, Object var1) {
            setOnParseListeners$lambda_7(var0, var1);
         }

         // $FF: synthetic method
         public static void $r8$lambda$Zx5bZeDVdzS_ZVY_2pLvaOGira4(MsgMgr var0, Object var1) {
            setOnParseListeners$lambda_14(var0, var1);
         }

         // $FF: synthetic method
         public static void $r8$lambda$tiv5nxPB3dk_XZrBEZ94AjuhgaU(MsgMgr var0, Object var1) {
            setOnParseListeners$lambda_10(var0, var1);
         }

         // $FF: synthetic method
         public static void $r8$lambda$w5SroMpKCkkJ0NCXa5_kaLrtjn8(MsgMgr var0, Object var1) {
            setOnParseListeners$lambda_1(var0, var1);
         }

         {
            this.this$0 = var1;
            this.$context = var2;
         }

         private final String getState(int var1) {
            String var2;
            switch (var1) {
               case 0:
                  var2 = CommUtil.getStrByResId(this.$context, "_253_normal");
                  break;
               case 1:
                  var2 = CommUtil.getStrByResId(this.$context, "high_pressure_warning");
                  break;
               case 2:
                  var2 = CommUtil.getStrByResId(this.$context, "low_pressure_warning");
                  break;
               case 3:
                  var2 = CommUtil.getStrByResId(this.$context, "quick_leak");
                  break;
               case 4:
                  var2 = CommUtil.getStrByResId(this.$context, "loss_signal");
                  break;
               case 5:
                  var2 = CommUtil.getStrByResId(this.$context, "low_sensor_power");
                  break;
               case 6:
                  var2 = CommUtil.getStrByResId(this.$context, "sensor_error");
                  break;
               case 7:
                  var2 = CommUtil.getStrByResId(this.$context, "low_pressure_and_leak");
                  break;
               default:
                  var2 = "ERROR";
            }

            return var2;
         }

         private final String getTemperature(int var1) {
            String var2;
            if (var1 == 255) {
               var2 = "--";
            } else {
               var2 = var1 - 60 + " ℃";
            }

            return var2;
         }

         private final int getTireStatus(int var1) {
            byte var2;
            switch (var1) {
               case 1:
               case 2:
               case 3:
               case 4:
               case 5:
               case 6:
               case 7:
                  var2 = 1;
                  break;
               default:
                  var2 = 0;
            }

            return var2;
         }

         private final String getTirepress(int var1) {
            String var6;
            if (var1 == 255) {
               var6 = "--";
            } else {
               StringBuilder var7 = new StringBuilder();
               double var2 = (double)((float)var1);
               double var4 = (double)10;
               var6 = var7.append(Math.floor(var2 * 2.75 * var4) / var4).append(" KPA").toString();
            }

            return var6;
         }

         private static final void setOnParseListeners$lambda_1(MsgMgr var0, Object var1) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            Intrinsics.checkNotNullParameter(var1, "this$1");
            DriverUpdateEntity var5 = (DriverUpdateEntity)var0.mDriveItemIndexHashMap.get("_306_value_20");
            if (var5 != null) {
               ArrayList var4 = var1.driveUpdateList;
               ArrayList var6 = var4;
               if (var4 == null) {
                  Intrinsics.throwUninitializedPropertyAccessException("driveUpdateList");
                  var6 = null;
               }

               StringBuilder var7 = new StringBuilder();
               int var2 = var0.mCanbusInfoInt[2];
               int var3 = var0.mCanbusInfoInt[3];
               var6.add(var5.setValue(var7.append(var0.mCanbusInfoInt[4] | (var2 << 8 | var3) << 8).append(" KM").toString()));
            }

         }

         private static final void setOnParseListeners$lambda_10(MsgMgr var0, Object var1) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            Intrinsics.checkNotNullParameter(var1, "this$1");
            DriverUpdateEntity var4 = (DriverUpdateEntity)var0.mDriveItemIndexHashMap.get("remaining_oil");
            if (var4 != null) {
               ArrayList var3 = var1.driveUpdateList;
               ArrayList var6 = var3;
               if (var3 == null) {
                  Intrinsics.throwUninitializedPropertyAccessException("driveUpdateList");
                  var6 = null;
               }

               float var2 = (float)var0.mCanbusInfoInt[8] / (float)2;
               String var5;
               if (var2 > 100.0F) {
                  var5 = "--";
               } else {
                  var5 = "" + var2 + '%';
               }

               var6.add(var4.setValue(var5));
            }

         }

         private static final void setOnParseListeners$lambda_12(MsgMgr var0, Object var1) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            Intrinsics.checkNotNullParameter(var1, "this$1");
            int[] var2 = var0.mCanbusInfoInt;
            var1.tireUpdateList = CollectionsKt.arrayListOf(new TireUpdateEntity[]{new TireUpdateEntity(0, var1.getTireStatus(var2[17] >> 4 & 15), new String[]{var1.getTirepress(var2[9]), var1.getTemperature(var2[13]), var1.getState(var2[17] >> 4 & 15)}), new TireUpdateEntity(1, var1.getTireStatus(var2[17] & 15), new String[]{var1.getTirepress(var2[10]), var1.getTemperature(var2[14]), var1.getState(var2[17] & 15)}), new TireUpdateEntity(2, var1.getTireStatus(var2[18] >> 4 & 15), new String[]{var1.getTirepress(var2[11]), var1.getTemperature(var2[15]), var1.getState(var2[18] >> 4 & 15)}), new TireUpdateEntity(3, var1.getTireStatus(var2[18] & 15), new String[]{var1.getTirepress(var2[12]), var1.getTemperature(var2[16]), var1.getState(var2[18] & 15)})});
         }

         private static final void setOnParseListeners$lambda_14(MsgMgr var0, Object var1) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            Intrinsics.checkNotNullParameter(var1, "this$1");
            DriverUpdateEntity var3 = (DriverUpdateEntity)var0.mDriveItemIndexHashMap.get("_94_oil_life");
            if (var3 != null) {
               ArrayList var2 = var1.driveUpdateList;
               ArrayList var4 = var2;
               if (var2 == null) {
                  Intrinsics.throwUninitializedPropertyAccessException("driveUpdateList");
                  var4 = null;
               }

               var4.add(var3.setValue("" + var0.mCanbusInfoInt[19] + '%'));
            }

         }

         private static final void setOnParseListeners$lambda_4(MsgMgr var0, Object var1) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            Intrinsics.checkNotNullParameter(var1, "this$1");
            DriverUpdateEntity var4 = (DriverUpdateEntity)var0.mDriveItemIndexHashMap.get("_306_value_21");
            if (var4 != null) {
               ArrayList var3 = var1.driveUpdateList;
               ArrayList var6 = var3;
               if (var3 == null) {
                  Intrinsics.throwUninitializedPropertyAccessException("driveUpdateList");
                  var6 = null;
               }

               int var2 = var0.mCanbusInfoInt[5];
               var2 = var0.mCanbusInfoInt[6] | var2 << 8;
               String var5;
               if (var2 > 999) {
                  var5 = "--";
               } else {
                  var5 = var2 + " KM";
               }

               var6.add(var4.setValue(var5));
            }

         }

         private static final void setOnParseListeners$lambda_7(MsgMgr var0, Object var1) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            Intrinsics.checkNotNullParameter(var1, "this$1");
            DriverUpdateEntity var4 = (DriverUpdateEntity)var0.mDriveItemIndexHashMap.get("_283_meter_value_5");
            if (var4 != null) {
               ArrayList var3 = var1.driveUpdateList;
               ArrayList var6 = var3;
               if (var3 == null) {
                  Intrinsics.throwUninitializedPropertyAccessException("driveUpdateList");
                  var6 = null;
               }

               float var2 = ((float)var0.mCanbusInfoInt[7] * (float)2 + (float)1) / (float)10;
               String var5;
               if (var2 > 30.0F) {
                  var5 = "--";
               } else {
                  var5 = var2 + " L/100km";
               }

               var6.add(var4.setValue(var5));
            }

         }

         public void parse(int var1) {
            if (this.isDataChange()) {
               GeneralTireData.isHaveSpareTire = false;
               this.driveUpdateList = new ArrayList();
               super.parse(var1);
               MsgMgr var4 = this.this$0;
               ArrayList var3 = this.driveUpdateList;
               ArrayList var2 = var3;
               if (var3 == null) {
                  Intrinsics.throwUninitializedPropertyAccessException("driveUpdateList");
                  var2 = null;
               }

               var4.updateGeneralDriveData((List)var2);
               this.this$0.updateDriveDataActivity((Bundle)null);
               var3 = this.tireUpdateList;
               var2 = var3;
               if (var3 == null) {
                  Intrinsics.throwUninitializedPropertyAccessException("tireUpdateList");
                  var2 = null;
               }

               GeneralTireData.dataList = (List)var2;
               this.this$0.updateTirePressureActivity((Bundle)null);
            }

         }

         public OnParseListener[] setOnParseListeners() {
            return new OnParseListener[]{null, null, new MsgMgr$initParsers$1$26$$ExternalSyntheticLambda0(this.this$0, this), null, new MsgMgr$initParsers$1$26$$ExternalSyntheticLambda1(this.this$0, this), new MsgMgr$initParsers$1$26$$ExternalSyntheticLambda2(this.this$0, this), new MsgMgr$initParsers$1$26$$ExternalSyntheticLambda3(this.this$0, this), null, null, null, null, null, null, null, null, null, new MsgMgr$initParsers$1$26$$ExternalSyntheticLambda4(this.this$0, this), new MsgMgr$initParsers$1$26$$ExternalSyntheticLambda5(this.this$0, this)};
         }
      });
      var2.append(105, new Parser(this) {
         private ArrayList driveUpdateList;
         final MsgMgr this$0;

         // $FF: synthetic method
         public static void $r8$lambda$PaeywV5_IH0wJrjFBcux3m4_mCo(MsgMgr var0, Object var1) {
            setOnParseListeners$lambda_3(var0, var1);
         }

         // $FF: synthetic method
         public static void $r8$lambda$dQt0buCP1oHxXL9kQnJdFBuJqvE(MsgMgr var0, Object var1) {
            setOnParseListeners$lambda_1(var0, var1);
         }

         // $FF: synthetic method
         public static void $r8$lambda$et0mm8Uk70BP_nyv2lg2YC6MfLU(MsgMgr var0, Object var1) {
            setOnParseListeners$lambda_5(var0, var1);
         }

         // $FF: synthetic method
         public static void $r8$lambda$jRyx4j1lrRA2R5IDME75Yf6o__k(MsgMgr var0, Object var1) {
            setOnParseListeners$lambda_7(var0, var1);
         }

         {
            this.this$0 = var1;
         }

         private static final void setOnParseListeners$lambda_1(MsgMgr var0, Object var1) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            Intrinsics.checkNotNullParameter(var1, "this$1");
            DriverUpdateEntity var3 = (DriverUpdateEntity)var0.mDriveItemIndexHashMap.get("average_speed");
            if (var3 != null) {
               ArrayList var2 = var1.driveUpdateList;
               ArrayList var5 = var2;
               if (var2 == null) {
                  Intrinsics.throwUninitializedPropertyAccessException("driveUpdateList");
                  var5 = null;
               }

               String var4;
               if (var0.mCanbusInfoInt[2] != 255) {
                  var4 = var0.mCanbusInfoInt[2] + " Km/H";
               } else {
                  var4 = "--";
               }

               var5.add(var3.setValue(var4));
            }

         }

         private static final void setOnParseListeners$lambda_3(MsgMgr var0, Object var1) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            Intrinsics.checkNotNullParameter(var1, "this$1");
            DriverUpdateEntity var3 = (DriverUpdateEntity)var0.mDriveItemIndexHashMap.get("_94_maintenance_mileage");
            if (var3 != null) {
               ArrayList var2 = var1.driveUpdateList;
               ArrayList var4 = var2;
               if (var2 == null) {
                  Intrinsics.throwUninitializedPropertyAccessException("driveUpdateList");
                  var4 = null;
               }

               var4.add(var3.setValue((var0.mCanbusInfoInt[3] * 256 + var0.mCanbusInfoInt[4]) / 2 + " Km"));
            }

         }

         private static final void setOnParseListeners$lambda_5(MsgMgr var0, Object var1) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            Intrinsics.checkNotNullParameter(var1, "this$1");
            DriverUpdateEntity var3 = (DriverUpdateEntity)var0.mDriveItemIndexHashMap.get("_94_battery_capacity");
            if (var3 != null) {
               ArrayList var2 = var1.driveUpdateList;
               ArrayList var4 = var2;
               if (var2 == null) {
                  Intrinsics.throwUninitializedPropertyAccessException("driveUpdateList");
                  var4 = null;
               }

               var4.add(var3.setValue(var0.mCanbusInfoInt[5] + " %"));
            }

         }

         private static final void setOnParseListeners$lambda_7(MsgMgr var0, Object var1) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            Intrinsics.checkNotNullParameter(var1, "this$1");
            DriverUpdateEntity var7 = (DriverUpdateEntity)var0.mDriveItemIndexHashMap.get("_94_battery_voltage");
            if (var7 != null) {
               ArrayList var6 = var1.driveUpdateList;
               ArrayList var9 = var6;
               if (var6 == null) {
                  Intrinsics.throwUninitializedPropertyAccessException("driveUpdateList");
                  var9 = null;
               }

               StringBuilder var8 = new StringBuilder();
               double var2 = (double)(var0.mCanbusInfoInt[6] * 47) / 755.0;
               double var4 = (double)10;
               var9.add(var7.setValue(var8.append(Math.rint(var2 * var4) / var4 + (double)3).append(" V").toString()));
            }

         }

         public void parse(int var1) {
            if (this.isDataChange()) {
               this.driveUpdateList = new ArrayList();
               super.parse(var1);
               MsgMgr var4 = this.this$0;
               ArrayList var3 = this.driveUpdateList;
               ArrayList var2 = var3;
               if (var3 == null) {
                  Intrinsics.throwUninitializedPropertyAccessException("driveUpdateList");
                  var2 = null;
               }

               var4.updateGeneralDriveData((List)var2);
               this.this$0.updateDriveDataActivity((Bundle)null);
            }

         }

         public OnParseListener[] setOnParseListeners() {
            return new OnParseListener[]{new MsgMgr$initParsers$1$27$$ExternalSyntheticLambda0(this.this$0, this), new MsgMgr$initParsers$1$27$$ExternalSyntheticLambda1(this.this$0, this), new MsgMgr$initParsers$1$27$$ExternalSyntheticLambda2(this.this$0, this), new MsgMgr$initParsers$1$27$$ExternalSyntheticLambda3(this.this$0, this)};
         }
      });
      var2.append(100, new Parser(this) {
         private final ArrayList list;
         final MsgMgr this$0;

         // $FF: synthetic method
         public static void $r8$lambda$Bd6Lj8vgLzEWMR6DNGblaxmwaVE(Object var0, MsgMgr var1) {
            setOnParseListeners$lambda_20(var0, var1);
         }

         // $FF: synthetic method
         public static void $r8$lambda$K24k7fcctNCIpqezhA9FN9sK2Ws(MsgMgr var0) {
            setOnParseListeners$lambda_23(var0);
         }

         // $FF: synthetic method
         public static void $r8$lambda$VgTuRiXT8Gz21VugvHa0Ck2x5bA(MsgMgr var0) {
            setOnParseListeners$lambda_24(var0);
         }

         // $FF: synthetic method
         public static void $r8$lambda$e17CMM0KCSfAHcsqxvvXnheFTNI(MsgMgr var0) {
            setOnParseListeners$lambda_21(var0);
         }

         // $FF: synthetic method
         public static void $r8$lambda$qn6yqNmksijxQ3GUuwHttV7j_mQ(MsgMgr var0) {
            setOnParseListeners$lambda_22(var0);
         }

         {
            this.this$0 = var1;
            this.list = new ArrayList();
         }

         private static final void setOnParseListeners$lambda_20(Object var0, MsgMgr var1) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            Intrinsics.checkNotNullParameter(var1, "this$1");
            ArrayList var7 = var0.list;
            int var2 = var1.mCanbusInfoInt[2];
            boolean var5 = true;
            boolean var3;
            if ((var2 >> 7 & 1) == 1) {
               var3 = true;
            } else {
               var3 = false;
            }

            var2 = var1.mCanbusInfoInt[2] >> 5 & 3;
            SettingUpdateEntity var6 = (SettingUpdateEntity)var1.mSettingItemIndexHashMap.get("_94_driver");
            if (var6 != null) {
               var7.add(var6.setValue(var2).setEnable(var3));
            }

            boolean var4;
            if (var3 && var2 == 1) {
               var4 = true;
            } else {
               var4 = false;
            }

            var6 = (SettingUpdateEntity)var1.mSettingItemIndexHashMap.get("_94_driver_high");
            if (var6 != null) {
               var7.add(var6.setValue(var1.getDriver().getHigh()).setProgress(var1.getDriver().getHigh()).setEnable(var4));
            }

            var6 = (SettingUpdateEntity)var1.mSettingItemIndexHashMap.get("_94_driver_mid");
            if (var6 != null) {
               var7.add(var6.setValue(var1.getDriver().getMiddle()).setProgress(var1.getDriver().getMiddle()).setEnable(var4));
            }

            var6 = (SettingUpdateEntity)var1.mSettingItemIndexHashMap.get("_94_driver_low");
            if (var6 != null) {
               var7.add(var6.setValue(var1.getDriver().getLow()).setProgress(var1.getDriver().getLow()).setEnable(var4));
            }

            if (var3 && var2 == 2) {
               var4 = true;
            } else {
               var4 = false;
            }

            var6 = (SettingUpdateEntity)var1.mSettingItemIndexHashMap.get("_94_driver_backrest");
            if (var6 != null) {
               var7.add(var6.setValue(var1.getDriver().getBackseat()).setEnable(var4));
            }

            var6 = (SettingUpdateEntity)var1.mSettingItemIndexHashMap.get("_94_driver_cushion");
            if (var6 != null) {
               var7.add(var6.setValue(var1.getDriver().getCushion()).setEnable(var4));
            }

            var2 = var1.mCanbusInfoInt[2] >> 3 & 3;
            var6 = (SettingUpdateEntity)var1.mSettingItemIndexHashMap.get("_94_passenger");
            if (var6 != null) {
               var7.add(var6.setValue(var2).setEnable(var3));
            }

            if (var3 && var2 == 1) {
               var4 = true;
            } else {
               var4 = false;
            }

            var6 = (SettingUpdateEntity)var1.mSettingItemIndexHashMap.get("_94_passenger_high");
            if (var6 != null) {
               var7.add(var6.setValue(var1.getPassenger().getHigh()).setProgress(var1.getPassenger().getHigh()).setEnable(var4));
            }

            var6 = (SettingUpdateEntity)var1.mSettingItemIndexHashMap.get("_94_passenger_mid");
            if (var6 != null) {
               var7.add(var6.setValue(var1.getPassenger().getMiddle()).setProgress(var1.getPassenger().getMiddle()).setEnable(var4));
            }

            var6 = (SettingUpdateEntity)var1.mSettingItemIndexHashMap.get("_94_passenger_low");
            if (var6 != null) {
               var7.add(var6.setValue(var1.getPassenger().getLow()).setProgress(var1.getPassenger().getLow()).setEnable(var4));
            }

            if (var3 && var2 == 2) {
               var3 = var5;
            } else {
               var3 = false;
            }

            var6 = (SettingUpdateEntity)var1.mSettingItemIndexHashMap.get("_94_passenger_backrest");
            if (var6 != null) {
               var7.add(var6.setValue(var1.getPassenger().getBackseat()).setEnable(var3));
            }

            var6 = (SettingUpdateEntity)var1.mSettingItemIndexHashMap.get("_94_passenger_cushion");
            if (var6 != null) {
               var7.add(var6.setValue(var1.getPassenger().getCushion()).setEnable(var3));
            }

         }

         private static final void setOnParseListeners$lambda_21(MsgMgr var0) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            var0.getDriver().setHigh(var0.mCanbusInfoInt[3] >> 4 & 15);
            var0.getDriver().setMiddle(var0.mCanbusInfoInt[3] & 15);
         }

         private static final void setOnParseListeners$lambda_22(MsgMgr var0) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            var0.getDriver().setLow(var0.mCanbusInfoInt[4] >> 4 & 15);
            var0.getDriver().setBackseat(var0.mCanbusInfoInt[4] >> 2 & 3);
            var0.getDriver().setCushion(var0.mCanbusInfoInt[4] & 3);
         }

         private static final void setOnParseListeners$lambda_23(MsgMgr var0) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            var0.getPassenger().setHigh(var0.mCanbusInfoInt[5] >> 4 & 15);
            var0.getPassenger().setMiddle(var0.mCanbusInfoInt[5] & 15);
         }

         private static final void setOnParseListeners$lambda_24(MsgMgr var0) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            var0.getPassenger().setLow(var0.mCanbusInfoInt[6] >> 4 & 15);
            var0.getPassenger().setBackseat(var0.mCanbusInfoInt[6] >> 2 & 3);
            var0.getPassenger().setCushion(var0.mCanbusInfoInt[6] & 3);
         }

         public void parse(int var1) {
            if (this.isDataChange()) {
               this.list.clear();
               super.parse(var1);
               this.this$0.updateGeneralSettingData((List)this.list);
               this.this$0.updateSettingActivity((Bundle)null);
            }

         }

         public OnParseListener[] setOnParseListeners() {
            return new OnParseListener[]{new MsgMgr$initParsers$1$28$$ExternalSyntheticLambda0(this, this.this$0), new MsgMgr$initParsers$1$28$$ExternalSyntheticLambda1(this.this$0), new MsgMgr$initParsers$1$28$$ExternalSyntheticLambda2(this.this$0), new MsgMgr$initParsers$1$28$$ExternalSyntheticLambda3(this.this$0), new MsgMgr$initParsers$1$28$$ExternalSyntheticLambda4(this.this$0)};
         }
      });
      var2.append(101, new Parser(this) {
      });
      var2.append(102, new Parser(this) {
      });
      var2.append(103, new Parser(this) {
      });
      var2.append(104, new Parser(this) {
      });
      var2.append(120, new Parser(this, var1) {
         final Context $context;
         final MsgMgr this$0;
         private final SparseIntArray topIconArray;

         {
            this.this$0 = var1;
            this.$context = var2;
            SparseIntArray var3 = new SparseIntArray();
            this.topIconArray = var3;
            var3.put(0, 2131233453);
            var3.append(1, 2131233549);
            var3.append(3, 2131233615);
            var3.append(4, 2131233479);
            var3.append(5, 2131233584);
            var3.append(6, 2131233633);
         }

         private final int getSyncIconResId(int var1) {
            if ((this.this$0.mCanbusInfoInt[3] >> var1 & 1) == 1) {
               var1 = this.topIconArray.get(var1);
            } else {
               var1 = 2131233649;
            }

            return var1;
         }

         public void parse(int var1) {
            if (this.isDataChange()) {
               int[] var4 = GeneralSyncData.mSyncTopIconResIdArray;
               MsgMgr var5 = this.this$0;
               Context var3 = this.$context;
               var4[1] = this.getSyncIconResId(0);
               var4[2] = this.getSyncIconResId(1);
               var4[3] = this.getSyncIconResId(3);
               var4[4] = this.getSyncIconResId(4);
               var4[5] = this.getSyncIconResId(5);
               var4[6] = this.getSyncIconResId(6);
               var1 = var5.mCanbusInfoInt[4] & 15;
               int var2 = 2131233621;
               if (var1 > 4) {
                  var1 = 2131233621;
               } else {
                  var1 = var5.getSyncIconResId(var3, "ford_sync_icon_" + (var1 + 103));
               }

               var4[7] = var1;
               var1 = var5.mCanbusInfoInt[4] >> 4 & 15;
               if (var1 > 4) {
                  var1 = var2;
               } else {
                  var1 = var5.getSyncIconResId(var3, "ford_sync_icon_" + (var1 + 97));
               }

               var4[8] = var1;
               this.this$0.updateSyncActivity((Bundle)null);
            }

         }
      });
      var2.append(121, new Parser(this, var1) {
         final Context $context;
         final MsgMgr this$0;

         {
            this.this$0 = var1;
            this.$context = var2;
         }

         public void parse(int var1) {
            var1 = this.this$0.mCanbusInfoInt[2];
            if (var1 != 0) {
               label39: {
                  if (var1 != 1) {
                     if (var1 == 2) {
                        break label39;
                     }

                     if (var1 != 3 && var1 != 4) {
                        if (var1 != 5) {
                           return;
                        }
                        break label39;
                     }
                  }

                  Context var2 = this.$context;
                  Intent var3 = new Intent();
                  var3.setComponent(Constant.SyncActivity);
                  var3.setFlags(268435456);
                  var2.startActivity(var3);
                  return;
               }
            }

            if (SystemUtil.isForeground(this.$context, Reflection.getOrCreateKotlinClass(SyncActivity.class).getQualifiedName())) {
               this.this$0.realKeyClick(this.$context, 50);
            } else {
               this.this$0.enterNoSource();
            }

         }
      });
      var2.append(128, new Parser(this, var1) {
         final Context $context;
         private long realTime;
         final MsgMgr this$0;

         {
            this.this$0 = var1;
            this.$context = var2;
         }

         public void parse(int var1) {
            if (this.isDataChange()) {
               int var2 = this.this$0.mCanbusInfoInt[2];
               boolean var5 = true;
               MsgMgr var3;
               switch (var2) {
                  case 1:
                     if (this.this$0.getSourceId() != SourceConstantsDef.SOURCE_ID.FM) {
                        this.this$0.realKeyClick(this.$context, 62);
                     }

                     var2 = this.this$0.mCanbusInfoInt[3];
                     if (var2 != 0) {
                        if (var2 != 1) {
                           if (var2 != 2) {
                              String var6;
                              if (var2 != 3) {
                                 if (var2 != 16) {
                                    if (var2 != 17) {
                                       if (var2 == 19) {
                                          var6 = this.this$0.getCurrentBand();
                                          Intrinsics.checkNotNullExpressionValue(var6, "currentBand");
                                          if (!StringsKt.contains$default((CharSequence)var6, (CharSequence)"AM", false, 2, (Object)null)) {
                                             this.this$0.changeBandAm1();
                                          }

                                          this.this$0.realKeyClick(this.$context, 30);
                                       }
                                    } else {
                                       this.this$0.changeBandAm2();
                                    }
                                 } else {
                                    this.this$0.changeBandAm1();
                                 }
                              } else {
                                 var6 = this.this$0.getCurrentBand();
                                 Intrinsics.checkNotNullExpressionValue(var6, "currentBand");
                                 if (!StringsKt.contains$default((CharSequence)var6, (CharSequence)"FM", false, 2, (Object)null)) {
                                    this.this$0.changeBandFm1();
                                 }

                                 this.this$0.realKeyClick(this.$context, 30);
                              }
                           } else {
                              this.this$0.changeBandFm3();
                           }
                        } else {
                           this.this$0.changeBandFm2();
                        }
                     } else {
                        this.this$0.changeBandFm1();
                     }

                     var2 = this.this$0.mCanbusInfoInt[4];
                     if (1 > var2 || var2 >= 7) {
                        var5 = false;
                     }

                     if (var5) {
                        var3 = this.this$0;
                        var3.playPresetFreq(var3.mCanbusInfoInt[4]);
                     }
                     break;
                  case 2:
                     if (this.this$0.getSourceId() != SourceConstantsDef.SOURCE_ID.MUSIC) {
                        this.this$0.realKeyClick(this.$context, 59);
                     }
                     break;
                  case 3:
                     var1 = this.this$0.mCanbusInfoInt[3];
                     if (var1 != 0) {
                        if (var1 != 1) {
                           if (var1 != 3) {
                              if (var1 != 4) {
                                 if (var1 != 5) {
                                    if (var1 == 6) {
                                       this.this$0.realKeyClick(this.$context, 138);
                                    }
                                 } else {
                                    this.this$0.realKeyClick(this.$context, 137);
                                 }
                              } else {
                                 this.this$0.realKeyClick(this.$context, 136);
                              }
                           } else {
                              this.this$0.realKeyClick(this.$context, 135);
                           }
                        } else {
                           this.this$0.realKeyClick(this.$context, 14);
                        }
                     } else {
                        this.this$0.realKeyClick(this.$context, 15);
                     }
                     break;
                  case 4:
                     if (this.this$0.getSourceId() != SourceConstantsDef.SOURCE_ID.BTAUDIO) {
                        this.this$0.realKeyClick(this.$context, 140);
                     }
                     break;
                  case 5:
                     var1 = this.this$0.mCanbusInfoInt[4] << 4 | this.this$0.mCanbusInfoInt[3];
                     var3 = this.this$0;
                     Context var4 = this.$context;
                     if (var1 > 0) {
                        var3.mpegDiscGoto(var4, MpegConstantsDef.K_SELECT.TITLE_SELECT, var1);
                     }
                  case 6:
                  default:
                     break;
                  case 7:
                     if (this.this$0.getSourceId() != SourceConstantsDef.SOURCE_ID.AUX1) {
                        this.this$0.realKeyClick(this.$context, 141);
                     }
                     break;
                  case 8:
                     this.this$0.realKeyClick(this.$context, 128);
               }
            }

         }
      });
   }

   private final void reportId3() {
      Id3[] var3 = this.mId3Array;
      int var2 = var3.length;

      for(int var1 = 0; var1 < var2; ++var1) {
         if (var3[var1].isInfoChange()) {
            TimerUtil var4 = this.mId3Timer;
            var4.stopTimer();
            var4.startTimer((TimerTask)(new TimerTask(this, var4) {
               final TimerUtil $this_run;
               private int i;
               private final int size;
               final MsgMgr this$0;

               {
                  this.this$0 = var1;
                  this.$this_run = var2;
                  this.size = var1.mId3Array.length;
               }

               public final int getI() {
                  return this.i;
               }

               public final int getSize() {
                  return this.size;
               }

               public void run() {
                  if (this.i < this.size) {
                     Id3[] var7 = this.this$0.mId3Array;
                     int var6 = this.i++;
                     Id3 var9 = var7[var6];
                     MsgMgr var8 = this.this$0;
                     byte var1 = (byte)var9.getType();
                     byte var5 = (byte)var8.nowLsb;
                     byte var4 = (byte)var8.nowMsb;
                     byte var2 = (byte)var8.allLsb;
                     byte var3 = (byte)var8.allMsb;
                     String var10 = var8.stringFixLength(var9.getInfo());
                     Charset var12 = StandardCharsets.UTF_16;
                     Intrinsics.checkNotNullExpressionValue(var12, "UTF_16");
                     byte[] var11 = var10.getBytes(var12);
                     Intrinsics.checkNotNullExpressionValue(var11, "this as java.lang.String).getBytes(charset)");
                     var11 = ArraysKt.copyOfRange(var11, 2, var11.length);
                     CanbusMsgSender.sendMsg(ArraysKt.plus(new byte[]{22, -63, var1, var5, var4, var2, var3}, var11));
                  } else {
                     this.$this_run.stopTimer();
                  }

               }

               public final void setI(int var1) {
                  this.i = var1;
               }
            }), 100L, 100L);
            break;
         }
      }

   }

   private final void setRadarData(Constant.RadarLocation var1, int var2) {
      RadarInfoUtil.setGeneralRadarData(var1, var2, 31);
   }

   private final String stringFixLength(String var1) {
      StringBuffer var3 = new StringBuffer(var1);
      if (var3.length() > 10) {
         var1 = StringsKt.removeRange((CharSequence)var3, 10, var3.length()).toString();
      } else {
         for(int var2 = var3.length(); var2 < 10; ++var2) {
            var3.append(" ");
         }

         var1 = var3.toString();
         Intrinsics.checkNotNullExpressionValue(var1, "{\n                for (i… toString()\n            }");
      }

      return var1;
   }

   public void afterServiceNormalSetting(Context var1) {
      super.afterServiceNormalSetting(var1);
      int var2 = this.getCurrentEachCanId();
      this.mEachId = var2;
      if (var2 != 3) {
         SelectCanTypeUtil.enableApp(var1, Constant.SyncActivity);
      }

      Intrinsics.checkNotNull(var1);
      this.mContext = var1;
      this.mCanId = SelectCanTypeUtil.getCurrentCanTypeId(var1);
      this.mDifferent = this.getCurrentCanDifferentId();
      this.mConfiguration = SharePreUtil.getIntValue(var1, "share_94_vehicle_config", 0);
      GeneralTireData.isHaveSpareTire = false;
      UiMgrInterface var3 = UiMgrFactory.getCanUiMgr(var1);
      Intrinsics.checkNotNull(var3, "null cannot be cast to non-null type com.hzbhd.canbus.car._94.UiMgr");
      this.mUiMgr = (UiMgr)var3;
      this.initItemsIndexHashMap(var1);
      this.initParsers(var1);
      this.initActivePark();
      GeneralSyncData.mInfoLineShowAmount = 5;
      GeneralSyncData.mIsShowSoftKey = true;
      GeneralSyncData.mSyncTopIconCount = 9;
      GeneralSyncData.mSyncTopIconResIdArray = new int[GeneralSyncData.mSyncTopIconCount];
      GeneralSyncData.mSyncTime = "";
      this.mActivePark = this.getActivePark(var1);
      CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
      (new TimerUtil()).startTimer((TimerTask)(new TimerTask(this) {
         final MsgMgr this$0;
         private final byte[] vehicleTypeCommand;

         {
            this.this$0 = var1;
            this.vehicleTypeCommand = new byte[]{22, -111, (byte)var1.mEachId, (byte)var1.mConfiguration};
         }

         public final byte[] getVehicleTypeCommand() {
            return this.vehicleTypeCommand;
         }

         public void run() {
            MsgMgr var1 = this.this$0;
            var1.setNumber(var1.getNumber() + 1);
            if (this.this$0.getNumber() <= 5) {
               this.vehicleTypeCommand[3] = (byte)this.this$0.mConfiguration;
               CanbusMsgSender.sendMsg(this.vehicleTypeCommand);
            }
         }
      }), 0L, 1000L);
   }

   public void auxInInfoChange() {
      CanbusMsgSender.sendMsg(new byte[]{22, -64, 7});
   }

   public void btMusicId3InfoChange(String var1, String var2, String var3) {
      Id3 var5 = this.mId3Array[0];
      Intrinsics.checkNotNull(var1);
      var5.setInfo(var1);
      Id3 var4 = this.mId3Array[1];
      Intrinsics.checkNotNull(var2);
      var4.setInfo(var2);
      this.nowMsb = 0;
      this.nowLsb = 0;
      this.allMsb = 0;
      this.allLsb = 0;
      this.reportId3();
   }

   public void btPhoneIncomingInfoChange(byte[] var1, boolean var2, boolean var3) {
      Intrinsics.checkNotNullParameter(var1, "phoneNumber");
      byte[] var4 = this.mPhoneInfoCommand;
      var4[3] = 2;
      CanbusMsgSender.sendMsg(var4);
      this.mHandler.postDelayed(new MsgMgr$$ExternalSyntheticLambda2(var1), 100L);
   }

   public void btPhoneOutGoingInfoChange(byte[] var1, boolean var2, boolean var3) {
      Intrinsics.checkNotNullParameter(var1, "phoneNumber");
      byte[] var4 = this.mPhoneInfoCommand;
      var4[3] = 2;
      CanbusMsgSender.sendMsg(var4);
      this.mHandler.postDelayed(new MsgMgr$$ExternalSyntheticLambda4(var1), 100L);
   }

   public void btPhoneStatusInfoChange(int var1, byte[] var2, boolean var3, boolean var4, boolean var5, boolean var6, int var7, int var8, Bundle var9) {
      this.mHandler.postDelayed(new MsgMgr$$ExternalSyntheticLambda0(this, var8, var7, var1, var3), 2000L);
   }

   public void btPhoneTalkingWithTimeInfoChange(byte[] var1, boolean var2, boolean var3, int var4) {
      var1 = this.mPhoneInfoCommand;
      var1[3] = 3;
      var1[4] = (byte)(var4 >> 8 & 255);
      var1[5] = (byte)(var4 & 255);
      CanbusMsgSender.sendMsg(var1);
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      Intrinsics.checkNotNullParameter(var2, "canbusInfo");
      super.canbusInfoChange(var1, var2);
      int[] var3 = this.getByteArrayToIntArray(var2);
      Intrinsics.checkNotNullExpressionValue(var3, "getByteArrayToIntArray(canbusInfo)");
      this.mCanbusInfoInt = var3;
      this.mCanbusInfoByte = var2;
      Parser var4 = (Parser)this.mParserArray.get(var3[1]);
      if (var4 != null) {
         var4.parse(this.mCanbusInfoInt.length - 2);
      }

   }

   public void currentVolumeInfoChange(int var1, boolean var2) {
      int var3 = var1;
      if (var1 > 30) {
         var3 = 30;
      }

      CanbusMsgSender.sendMsg(new byte[]{22, -62, (byte)var3});
   }

   public boolean customLongClick(Context var1, int var2) {
      if (var2 == 187) {
         this.realKeyClick(var1, 14);
         return true;
      } else {
         return false;
      }
   }

   public void dateTimeRepCanbus(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, boolean var10, boolean var11, boolean var12, int var13) {
      CanbusMsgSender.sendMsg(new byte[]{22, -126, (byte)var2, (byte)var3, (byte)var4, (byte)var8, (byte)var6, 0});
      this.mHandler.postDelayed(new MsgMgr$$ExternalSyntheticLambda1(), 1000L);
   }

   public void deviceStatusInfoChange(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, int var10, int var11, int var12) {
      if (this.mDiscExist != var4) {
         this.mDiscExist = var4;
         CanbusMsgSender.sendMsg(new byte[]{22, -58, -94, (byte)(1 - var4 | 2 | 0), 0});
      }

   }

   public void discInfoChange(byte var1, int var2, int var3, int var4, int var5, int var6, int var7, boolean var8, boolean var9, int var10, String var11, String var12, String var13) {
      if (var7 != 1 && var7 != 5) {
         var1 = var4;
      } else {
         var1 = var5;
      }

      int[] var15 = new int[3];
      var4 = var2 / 60;
      var15[0] = var4 / 60;
      var15[1] = var4 % 60;
      var15[2] = var2 % 60;
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MPEG.name(), new byte[]{22, -64, 2, (byte)(var1 >> 8 & 255), (byte)(var1 & 255), (byte)(var6 >> 8 & 255), (byte)(var6 & 255), (byte)var15[0], (byte)var15[1], (byte)var15[2]});
      Id3 var16 = this.mId3Array[0];
      Intrinsics.checkNotNull(var11);
      var16.setInfo(var11);
      Id3 var14 = this.mId3Array[1];
      Intrinsics.checkNotNull(var13);
      var14.setInfo(var13);
      this.nowMsb = this.getMsb(var2);
      this.nowLsb = this.getLsb(var2);
      this.allMsb = this.getMsb(var3);
      this.allLsb = this.getLsb(var3);
      this.reportId3();
   }

   public final ActivePark getActivePark(Context var1) {
      Intrinsics.checkNotNullParameter(var1, "context");
      if (this.mActivePark == null) {
         this.mActivePark = new ActivePark(var1);
      }

      ActivePark var2 = this.mActivePark;
      Intrinsics.checkNotNull(var2);
      return var2;
   }

   public final SeatData getDriver() {
      return this.driver;
   }

   public final List getListGeneralParkBigData() {
      return this.listGeneralParkBigData;
   }

   public final List getListGeneralParkData() {
      return this.listGeneralParkData;
   }

   public final byte[] getM0x61DataRecord() {
      return this.m0x61DataRecord;
   }

   public final int getMReversingValues() {
      return this.mReversingValues;
   }

   public final int getNumber() {
      return this.number;
   }

   public final SeatData getPassenger() {
      return this.passenger;
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      this.initAmplifier(var1);
   }

   public void musicInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, byte var8, byte var9, byte var10, String var11, String var12, String var13, long var14, byte var16, int var17, boolean var18, long var19, String var21, String var22, String var23, boolean var24) {
      Id3 var25 = this.mId3Array[0];
      Intrinsics.checkNotNull(var21);
      var25.setInfo(var21);
      var25 = this.mId3Array[1];
      Intrinsics.checkNotNull(var23);
      var25.setInfo(var23);
      this.nowMsb = this.getMsb(var3);
      this.nowLsb = this.getLsb(var3);
      this.allMsb = this.getMsb(var4);
      this.allLsb = this.getLsb(var4);
      this.reportId3();
   }

   public void radioInfoChange(int var1, String var2, String var3, String var4, int var5) {
      byte var9;
      label31: {
         Intrinsics.checkNotNull(var2);
         if (!var2.equals("FM1")) {
            if (var2.equals("FM2")) {
               var9 = 1;
               break label31;
            }

            if (var2.equals("FM3")) {
               var9 = 2;
               break label31;
            }

            if (StringsKt.contains$default((CharSequence)var2, (CharSequence)"AM", false, 2, (Object)null)) {
               var9 = 16;
               break label31;
            }
         }

         var9 = 0;
      }

      Intrinsics.checkNotNull(var3);
      CharSequence var7 = (CharSequence)var2;
      int var6;
      if (StringsKt.contains$default(var7, (CharSequence)"FM", false, 2, (Object)null)) {
         var6 = (int)(Float.parseFloat(var3) * (float)100);
      } else {
         if (!StringsKt.contains$default(var7, (CharSequence)"AM", false, 2, (Object)null)) {
            return;
         }

         var6 = Integer.parseInt(var3);
      }

      int[] var8 = new int[]{var6 & 255, var6 >> 8 & 255};
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.FM.name(), new byte[]{22, -64, 1, (byte)var9, (byte)var8[0], (byte)var8[1], (byte)var1});
   }

   public final void refreshPanoramicParkUi(int var1) {
      List var4 = this.listGeneralParkData;
      boolean var3 = false;
      boolean var2;
      if (var1 == 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      var4.add(new PanoramicBtnUpdateEntity(3, var2));
      var4 = this.listGeneralParkData;
      if (var1 == 1) {
         var2 = true;
      } else {
         var2 = false;
      }

      var4.add(new PanoramicBtnUpdateEntity(4, var2));
      var4 = this.listGeneralParkData;
      if (var1 == 2) {
         var2 = true;
      } else {
         var2 = false;
      }

      var4.add(new PanoramicBtnUpdateEntity(5, var2));
      var4 = this.listGeneralParkData;
      if (var1 == 3) {
         var2 = true;
      } else {
         var2 = false;
      }

      var4.add(new PanoramicBtnUpdateEntity(6, var2));
      var4 = this.listGeneralParkData;
      var2 = var3;
      if (var1 == 4) {
         var2 = true;
      }

      var4.add(new PanoramicBtnUpdateEntity(7, var2));
      this.refreshParkUi();
   }

   public final void refreshPanoramicParkUiBig(int[] var1, int var2) {
      Intrinsics.checkNotNullParameter(var1, "canInt");
      if (LogUtil.log5()) {
         LogUtil.d("updateInitFinish: " + ArraysKt.joinToString$default(var1, (CharSequence)null, (CharSequence)null, (CharSequence)null, 0, (CharSequence)null, (Function1)null, 63, (Object)null) + ' ' + var2);
      }

      List var6 = this.listGeneralParkBigData;
      int var4 = var2 - 1;
      int var3 = ((Number)var6.get(var4)).intValue();
      boolean var5 = false;
      byte var7;
      if (var3 == 0) {
         var7 = 1;
      } else {
         var7 = 0;
      }

      var6.set(var4, Integer.valueOf(var7));
      var6 = this.listGeneralParkData;
      if (((Number)this.listGeneralParkBigData.get(var4)).intValue() == 1) {
         var5 = true;
      }

      var6.add(new PanoramicBtnUpdateEntity(var2 + 7, var5));
      this.refreshParkUi();
   }

   public final void refreshParkUi() {
      GeneralParkData.dataList = this.listGeneralParkData;
      this.updateParkUi((Bundle)null, this.mContext);
   }

   public final void setConfiguration(Context var1, int var2) {
      Intrinsics.checkNotNullParameter(var1, "context");
      this.mConfiguration = var2;
      SharePreUtil.setIntValue(var1, "share_94_vehicle_config", var2);
   }

   public final void setNumber(int var1) {
      this.number = var1;
   }

   public void sourceSwitchNoMediaInfoChange(boolean var1) {
      super.sourceSwitchNoMediaInfoChange(var1);
      if (!var1) {
         this.initAmplifier(this.mContext);
      }

   }

   public final void updateSettings(String var1, Object var2) {
      Intrinsics.checkNotNullParameter(var1, "title");
      Intrinsics.checkNotNullParameter(var2, "value");
      SettingUpdateEntity var3 = (SettingUpdateEntity)this.mSettingItemIndexHashMap.get(var1);
      if (var3 != null) {
         var3.setValue(var2);
         this.updateGeneralSettingData((List)CollectionsKt.arrayListOf(new SettingUpdateEntity[]{var3}));
         this.updateSettingActivity((Bundle)null);
      }

   }

   public final void updateSync(Bundle var1) {
      this.updateSyncActivity(var1);
   }

   public void videoInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, String var8, byte var9, byte var10, String var11, String var12, String var13, int var14, byte var15, boolean var16, int var17) {
      super.videoInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var15, var16, var17);
      this.mId3Array[0].setInfo("USB Media");
      this.mId3Array[1].setInfo("Video Playing");
      this.nowMsb = this.getMsb(var17);
      this.nowLsb = this.getLsb(var17);
      this.allMsb = this.getMsb(var4);
      this.allLsb = this.getLsb(var4);
      this.reportId3();
   }

   @Metadata(
      d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0015\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u0011\u0010\u000e\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0012\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0011R\u0011\u0010\u0014\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0011¨\u0006\u0016"},
      d2 = {"Lcom/hzbhd/canbus/car/_94/MsgMgr$Companion;", "", "()V", "AMPLIFIER_FADE_OFFSET", "", "INVALID_VALUE", "MSG_DISMISS_WARNING_ACTIVITY", "RADAR_RANGE", "SHARE_94_VEHICLE_CONFIG", "", "TAG", "VEHICLE_TYPE_19_TERRITORY", "VEHICLE_TYPE_20_ESCAPE", "VEHICLE_TYPE_EXPLORER", "m0xC90x21ItemValues", "", "getM0xC90x21ItemValues", "()[I", "m0xC90x26ItemValues", "getM0xC90x26ItemValues", "mLanguageIndexs", "getMLanguageIndexs", "CanBusInfo_release"},
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

      public final int[] getM0xC90x21ItemValues() {
         return MsgMgr.m0xC90x21ItemValues;
      }

      public final int[] getM0xC90x26ItemValues() {
         return MsgMgr.m0xC90x26ItemValues;
      }

      public final int[] getMLanguageIndexs() {
         return MsgMgr.mLanguageIndexs;
      }
   }

   @Metadata(
      d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\f\u001a\u00020\rR&\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00068F@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0010"},
      d2 = {"Lcom/hzbhd/canbus/car/_94/MsgMgr$Id3;", "", "type", "", "(I)V", "value", "", "info", "getInfo", "()Ljava/lang/String;", "setInfo", "(Ljava/lang/String;)V", "isInfoChange", "", "getType", "()I", "CanBusInfo_release"},
      k = 1,
      mv = {1, 7, 1},
      xi = 48
   )
   private static final class Id3 {
      private String info;
      private boolean isInfoChange;
      private final int type;

      public Id3(int var1) {
         this.type = var1;
         this.info = "";
      }

      public final String getInfo() {
         this.isInfoChange = false;
         return this.info;
      }

      public final int getType() {
         return this.type;
      }

      public final boolean isInfoChange() {
         return this.isInfoChange;
      }

      public final void setInfo(String var1) {
         Intrinsics.checkNotNullParameter(var1, "value");
         if (!Intrinsics.areEqual((Object)this.info, (Object)var1)) {
            this.isInfoChange = true;
         }

         this.info = var1;
      }
   }

   @Metadata(
      d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\b¢\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0011\u001a\u00020\u0012J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0006H\u0016J\u0015\u0010\u0016\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\u000eH\u0016¢\u0006\u0002\u0010\u0017R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u0018\u0010\r\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\u000eX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0010¨\u0006\u0018"},
      d2 = {"Lcom/hzbhd/canbus/car/_94/MsgMgr$Parser;", "", "msg", "", "(Lcom/hzbhd/canbus/car/_94/MsgMgr;Ljava/lang/String;)V", "PARSE_LISTENERS_LENGTH", "", "canbusInfo", "", "getCanbusInfo", "()[I", "setCanbusInfo", "([I)V", "onParseListeners", "", "Lcom/hzbhd/canbus/interfaces/OnParseListener;", "[Lcom/hzbhd/canbus/interfaces/OnParseListener;", "isDataChange", "", "parse", "", "dataLength", "setOnParseListeners", "()[Lcom/hzbhd/canbus/interfaces/OnParseListener;", "CanBusInfo_release"},
      k = 1,
      mv = {1, 7, 1},
      xi = 48
   )
   private abstract class Parser {
      private final int PARSE_LISTENERS_LENGTH;
      private int[] canbusInfo;
      private final OnParseListener[] onParseListeners;
      final MsgMgr this$0;

      public Parser(MsgMgr var1, String var2) {
         Intrinsics.checkNotNullParameter(var2, "msg");
         this.this$0 = var1;
         super();
         OnParseListener[] var4 = this.setOnParseListeners();
         this.onParseListeners = var4;
         int var3 = var4.length;
         this.PARSE_LISTENERS_LENGTH = var3;
         Log.i("_1094_MsgMgr", "Parser: " + var2 + " length " + var3);
      }

      public final int[] getCanbusInfo() {
         return this.canbusInfo;
      }

      public final boolean isDataChange() {
         boolean var1;
         if (Arrays.equals(this.canbusInfo, this.this$0.mCanbusInfoInt)) {
            var1 = false;
         } else {
            int[] var2 = this.this$0.mCanbusInfoInt;
            var2 = Arrays.copyOf(var2, var2.length);
            Intrinsics.checkNotNullExpressionValue(var2, "copyOf(this, size)");
            this.canbusInfo = var2;
            var1 = true;
         }

         return var1;
      }

      public void parse(int var1) {
         for(var1 = Math.min(var1, this.PARSE_LISTENERS_LENGTH); var1 > 0; --var1) {
            OnParseListener var2 = this.onParseListeners[var1 - 1];
            if (var2 != null) {
               var2.onParse();
            }
         }

      }

      public final void setCanbusInfo(int[] var1) {
         this.canbusInfo = var1;
      }

      public OnParseListener[] setOnParseListeners() {
         return (OnParseListener[])((Object[])(new OnParseListener[0]));
      }
   }

   @Metadata(
      d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0018\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0007\b\u0016¢\u0006\u0002\u0010\u0002B-\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0004\u0012\u0006\u0010\b\u001a\u00020\u0004¢\u0006\u0002\u0010\tJ\t\u0010\u0016\u001a\u00020\u0004HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0004HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0004HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0004HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0004HÆ\u0003J;\u0010\u001b\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\u0004HÆ\u0001J\u0013\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001f\u001a\u00020\u0004HÖ\u0001J\t\u0010 \u001a\u00020!HÖ\u0001R\u001a\u0010\u0007\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001a\u0010\b\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000b\"\u0004\b\u000f\u0010\rR\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u000b\"\u0004\b\u0011\u0010\rR\u001a\u0010\u0006\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u000b\"\u0004\b\u0013\u0010\rR\u001a\u0010\u0005\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u000b\"\u0004\b\u0015\u0010\r¨\u0006\""},
      d2 = {"Lcom/hzbhd/canbus/car/_94/MsgMgr$SeatData;", "", "()V", "high", "", "middle", "low", "backseat", "cushion", "(IIIII)V", "getBackseat", "()I", "setBackseat", "(I)V", "getCushion", "setCushion", "getHigh", "setHigh", "getLow", "setLow", "getMiddle", "setMiddle", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "toString", "", "CanBusInfo_release"},
      k = 1,
      mv = {1, 7, 1},
      xi = 48
   )
   public static final class SeatData {
      private int backseat;
      private int cushion;
      private int high;
      private int low;
      private int middle;

      public SeatData() {
         this(0, 0, 0, 0, 0);
      }

      public SeatData(int var1, int var2, int var3, int var4, int var5) {
         this.high = var1;
         this.middle = var2;
         this.low = var3;
         this.backseat = var4;
         this.cushion = var5;
      }

      // $FF: synthetic method
      public static SeatData copy$default(SeatData var0, int var1, int var2, int var3, int var4, int var5, int var6, Object var7) {
         if ((var6 & 1) != 0) {
            var1 = var0.high;
         }

         if ((var6 & 2) != 0) {
            var2 = var0.middle;
         }

         if ((var6 & 4) != 0) {
            var3 = var0.low;
         }

         if ((var6 & 8) != 0) {
            var4 = var0.backseat;
         }

         if ((var6 & 16) != 0) {
            var5 = var0.cushion;
         }

         return var0.copy(var1, var2, var3, var4, var5);
      }

      public final int component1() {
         return this.high;
      }

      public final int component2() {
         return this.middle;
      }

      public final int component3() {
         return this.low;
      }

      public final int component4() {
         return this.backseat;
      }

      public final int component5() {
         return this.cushion;
      }

      public final SeatData copy(int var1, int var2, int var3, int var4, int var5) {
         return new SeatData(var1, var2, var3, var4, var5);
      }

      public boolean equals(Object var1) {
         if (this == var1) {
            return true;
         } else if (!(var1 instanceof SeatData)) {
            return false;
         } else {
            SeatData var2 = (SeatData)var1;
            if (this.high != var2.high) {
               return false;
            } else if (this.middle != var2.middle) {
               return false;
            } else if (this.low != var2.low) {
               return false;
            } else if (this.backseat != var2.backseat) {
               return false;
            } else {
               return this.cushion == var2.cushion;
            }
         }
      }

      public final int getBackseat() {
         return this.backseat;
      }

      public final int getCushion() {
         return this.cushion;
      }

      public final int getHigh() {
         return this.high;
      }

      public final int getLow() {
         return this.low;
      }

      public final int getMiddle() {
         return this.middle;
      }

      public int hashCode() {
         return (((Integer.hashCode(this.high) * 31 + Integer.hashCode(this.middle)) * 31 + Integer.hashCode(this.low)) * 31 + Integer.hashCode(this.backseat)) * 31 + Integer.hashCode(this.cushion);
      }

      public final void setBackseat(int var1) {
         this.backseat = var1;
      }

      public final void setCushion(int var1) {
         this.cushion = var1;
      }

      public final void setHigh(int var1) {
         this.high = var1;
      }

      public final void setLow(int var1) {
         this.low = var1;
      }

      public final void setMiddle(int var1) {
         this.middle = var1;
      }

      public String toString() {
         return "SeatData(high=" + this.high + ", middle=" + this.middle + ", low=" + this.low + ", backseat=" + this.backseat + ", cushion=" + this.cushion + ')';
      }
   }

   @Metadata(
      d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\u0018\u0000 \u00032\u00020\u0001:\u0003\u0003\u0004\u0005B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0006"},
      d2 = {"Lcom/hzbhd/canbus/car/_94/MsgMgr$SyncKey;", "", "()V", "Companion", "Long", "Short", "CanBusInfo_release"},
      k = 1,
      mv = {1, 7, 1},
      xi = 48
   )
   public static final class SyncKey {
      public static final Companion Companion = new Companion((DefaultConstructorMarker)null);
      private static final Short k1 = new Short(28);
      private static final Short k2 = new Short(29);
      private static final Short k3 = new Short(30);
      private static final Short k4 = new Short(31);
      private static final Long num0 = new Long(13, 48);
      private static final Long num1 = new Long(14, 49);
      private static final Long num2 = new Long(15, 50);
      private static final Long num3 = new Long(16, 51);
      private static final Long num4 = new Long(17, 52);
      private static final Long num5 = new Long(18, 53);
      private static final Long num6 = new Long(19, 54);
      private static final Long num7 = new Long(20, 55);
      private static final Long num8 = new Long(21, 56);
      private static final Long num9 = new Long(22, 57);
      private static final Short star = new Short(23);
      private static final Short well = new Short(24);

      @Metadata(
         d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0019\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006R\u0011\u0010\t\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0006R\u0011\u0010\u000b\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u0006R\u0011\u0010\r\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0011\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0010R\u0011\u0010\u0013\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0010R\u0011\u0010\u0015\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0010R\u0011\u0010\u0017\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0010R\u0011\u0010\u0019\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0010R\u0011\u0010\u001b\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0010R\u0011\u0010\u001d\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0010R\u0011\u0010\u001f\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u0010R\u0011\u0010!\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u0010R\u0011\u0010#\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u0006R\u0011\u0010%\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\u0006¨\u0006'"},
         d2 = {"Lcom/hzbhd/canbus/car/_94/MsgMgr$SyncKey$Companion;", "", "()V", "k1", "Lcom/hzbhd/canbus/car/_94/MsgMgr$SyncKey$Short;", "getK1", "()Lcom/hzbhd/canbus/car/_94/MsgMgr$SyncKey$Short;", "k2", "getK2", "k3", "getK3", "k4", "getK4", "num0", "Lcom/hzbhd/canbus/car/_94/MsgMgr$SyncKey$Long;", "getNum0", "()Lcom/hzbhd/canbus/car/_94/MsgMgr$SyncKey$Long;", "num1", "getNum1", "num2", "getNum2", "num3", "getNum3", "num4", "getNum4", "num5", "getNum5", "num6", "getNum6", "num7", "getNum7", "num8", "getNum8", "num9", "getNum9", "star", "getStar", "well", "getWell", "CanBusInfo_release"},
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

         public final Short getK1() {
            return SyncKey.k1;
         }

         public final Short getK2() {
            return SyncKey.k2;
         }

         public final Short getK3() {
            return SyncKey.k3;
         }

         public final Short getK4() {
            return SyncKey.k4;
         }

         public final Long getNum0() {
            return SyncKey.num0;
         }

         public final Long getNum1() {
            return SyncKey.num1;
         }

         public final Long getNum2() {
            return SyncKey.num2;
         }

         public final Long getNum3() {
            return SyncKey.num3;
         }

         public final Long getNum4() {
            return SyncKey.num4;
         }

         public final Long getNum5() {
            return SyncKey.num5;
         }

         public final Long getNum6() {
            return SyncKey.num6;
         }

         public final Long getNum7() {
            return SyncKey.num7;
         }

         public final Long getNum8() {
            return SyncKey.num8;
         }

         public final Long getNum9() {
            return SyncKey.num9;
         }

         public final Short getStar() {
            return SyncKey.star;
         }

         public final Short getWell() {
            return SyncKey.well;
         }
      }

      @Metadata(
         d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0012"},
         d2 = {"Lcom/hzbhd/canbus/car/_94/MsgMgr$SyncKey$Long;", "", "short", "", "long", "(II)V", "getLong", "()I", "getShort", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "", "CanBusInfo_release"},
         k = 1,
         mv = {1, 7, 1},
         xi = 48
      )
      public static final class Long {
         private final int long;
         private final int short;

         public Long(int var1, int var2) {
            this.short = var1;
            this.long = var2;
         }

         // $FF: synthetic method
         public static Long copy$default(Long var0, int var1, int var2, int var3, Object var4) {
            if ((var3 & 1) != 0) {
               var1 = var0.short;
            }

            if ((var3 & 2) != 0) {
               var2 = var0.long;
            }

            return var0.copy(var1, var2);
         }

         public final int component1() {
            return this.short;
         }

         public final int component2() {
            return this.long;
         }

         public final Long copy(int var1, int var2) {
            return new Long(var1, var2);
         }

         public boolean equals(Object var1) {
            if (this == var1) {
               return true;
            } else if (!(var1 instanceof Long)) {
               return false;
            } else {
               Long var2 = (Long)var1;
               if (this.short != var2.short) {
                  return false;
               } else {
                  return this.long == var2.long;
               }
            }
         }

         public final int getLong() {
            return this.long;
         }

         public final int getShort() {
            return this.short;
         }

         public int hashCode() {
            return Integer.hashCode(this.short) * 31 + Integer.hashCode(this.long);
         }

         public String toString() {
            return "Long(short=" + this.short + ", long=" + this.long + ')';
         }
      }

      @Metadata(
         d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\f\u001a\u00020\u0003HÖ\u0001J\t\u0010\r\u001a\u00020\u000eHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u000f"},
         d2 = {"Lcom/hzbhd/canbus/car/_94/MsgMgr$SyncKey$Short;", "", "short", "", "(I)V", "getShort", "()I", "component1", "copy", "equals", "", "other", "hashCode", "toString", "", "CanBusInfo_release"},
         k = 1,
         mv = {1, 7, 1},
         xi = 48
      )
      public static final class Short {
         private final int short;

         public Short(int var1) {
            this.short = var1;
         }

         // $FF: synthetic method
         public static Short copy$default(Short var0, int var1, int var2, Object var3) {
            if ((var2 & 1) != 0) {
               var1 = var0.short;
            }

            return var0.copy(var1);
         }

         public final int component1() {
            return this.short;
         }

         public final Short copy(int var1) {
            return new Short(var1);
         }

         public boolean equals(Object var1) {
            if (this == var1) {
               return true;
            } else if (!(var1 instanceof Short)) {
               return false;
            } else {
               Short var2 = (Short)var1;
               return this.short == var2.short;
            }
         }

         public final int getShort() {
            return this.short;
         }

         public int hashCode() {
            return Integer.hashCode(this.short);
         }

         public String toString() {
            return "Short(short=" + this.short + ')';
         }
      }
   }
}
