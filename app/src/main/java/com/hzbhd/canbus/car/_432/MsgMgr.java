package com.hzbhd.canbus.car._432;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.SyncListUpdateEntity;
import com.hzbhd.canbus.entity.SyncSoftKeyUpdateEntity;
import com.hzbhd.canbus.interfaces.UiMgrInterface;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_datas.GeneralSyncData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;

@Metadata(
   d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u001a\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b&\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0010\u0015\n\u0002\b\u000b\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\bE\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0015\u0010\u009c\u0001\u001a\u00030\u009d\u00012\t\u0010\u009e\u0001\u001a\u0004\u0018\u00010wH\u0016J\u0019\u0010\u009f\u0001\u001a\u0002042\u0007\u0010 \u0001\u001a\u0002042\u0007\u0010\u009f\u0001\u001a\u000204J \u0010¡\u0001\u001a\u00030\u009d\u00012\t\u0010\u009e\u0001\u001a\u0004\u0018\u00010w2\t\u0010¢\u0001\u001a\u0004\u0018\u00010tH\u0016J\u001b\u0010£\u0001\u001a\u00030\u009d\u00012\u0007\u0010¤\u0001\u001a\u0002042\u0006\u0010Z\u001a\u00020[H\u0016J\u000b\u0010¥\u0001\u001a\u0004\u0018\u00010\u0004H\u0002J\u0012\u0010¦\u0001\u001a\u0004\u0018\u00010\u00042\u0007\u0010¢\u0001\u001a\u00020tJ\u0014\u0010§\u0001\u001a\u0004\u0018\u00010\u00042\u0007\u0010¨\u0001\u001a\u000204H\u0002J\u0014\u0010©\u0001\u001a\u0004\u0018\u00010\u00042\u0007\u0010¨\u0001\u001a\u000204H\u0002J\u0014\u0010ª\u0001\u001a\u0004\u0018\u00010\u00042\u0007\u0010¨\u0001\u001a\u000204H\u0002J\u0014\u0010«\u0001\u001a\u0004\u0018\u00010\u00042\u0007\u0010¬\u0001\u001a\u00020[H\u0002J\u000b\u0010\u00ad\u0001\u001a\u0004\u0018\u00010\u0004H\u0002J\u0019\u0010®\u0001\u001a\u0002042\u0007\u0010¯\u0001\u001a\u0002042\u0007\u0010°\u0001\u001a\u000204J\u000b\u0010±\u0001\u001a\u0004\u0018\u00010\u0004H\u0002J\u0014\u0010²\u0001\u001a\u0004\u0018\u00010\u00042\u0007\u0010³\u0001\u001a\u000204H\u0002J\u0014\u0010´\u0001\u001a\u0004\u0018\u00010\u00042\u0007\u0010¨\u0001\u001a\u000204H\u0002J\u001d\u0010µ\u0001\u001a\u0002042\t\u0010\u009e\u0001\u001a\u0004\u0018\u00010w2\u0007\u0010¶\u0001\u001a\u00020\u0004H\u0002J\u001d\u0010·\u0001\u001a\u0002042\t\u0010\u009e\u0001\u001a\u0004\u0018\u00010w2\u0007\u0010¶\u0001\u001a\u00020\u0004H\u0002J\u0014\u0010¸\u0001\u001a\u0004\u0018\u00010\u00042\u0007\u0010³\u0001\u001a\u000204H\u0002J\u0013\u0010¹\u0001\u001a\u00030\u0088\u00012\u0007\u0010\u009e\u0001\u001a\u00020wH\u0002J\u0015\u0010º\u0001\u001a\u00030\u009d\u00012\t\u0010\u009e\u0001\u001a\u0004\u0018\u00010wH\u0016J\t\u0010»\u0001\u001a\u00020[H\u0002J\t\u0010¼\u0001\u001a\u00020[H\u0002J\t\u0010½\u0001\u001a\u00020[H\u0002J\u0012\u0010¾\u0001\u001a\u00020[2\u0007\u0010¿\u0001\u001a\u000204H\u0002J\t\u0010À\u0001\u001a\u00020[H\u0002J\u0012\u0010Á\u0001\u001a\u00020[2\u0007\u0010Â\u0001\u001a\u000204H\u0002J\t\u0010Ã\u0001\u001a\u00020[H\u0002J\t\u0010Ä\u0001\u001a\u00020[H\u0002J\n\u0010Å\u0001\u001a\u00030\u009d\u0001H\u0002J\n\u0010Æ\u0001\u001a\u00030\u009d\u0001H\u0002J\n\u0010Ç\u0001\u001a\u00030\u009d\u0001H\u0002J\n\u0010È\u0001\u001a\u00030\u009d\u0001H\u0002J\n\u0010É\u0001\u001a\u00030\u009d\u0001H\u0002J\n\u0010Ê\u0001\u001a\u00030\u009d\u0001H\u0002J\n\u0010Ë\u0001\u001a\u00030\u009d\u0001H\u0002J\n\u0010Ì\u0001\u001a\u00030\u009d\u0001H\u0002J\n\u0010Í\u0001\u001a\u00030\u009d\u0001H\u0002J\n\u0010Î\u0001\u001a\u00030\u009d\u0001H\u0003J\n\u0010Ï\u0001\u001a\u00030\u009d\u0001H\u0002J\n\u0010Ð\u0001\u001a\u00030\u009d\u0001H\u0002J\n\u0010Ñ\u0001\u001a\u00030\u009d\u0001H\u0002J\n\u0010Ò\u0001\u001a\u00030\u009d\u0001H\u0002J\n\u0010Ó\u0001\u001a\u00030\u009d\u0001H\u0002J\n\u0010Ô\u0001\u001a\u00030\u009d\u0001H\u0002J\n\u0010Õ\u0001\u001a\u00030\u009d\u0001H\u0002J\n\u0010Ö\u0001\u001a\u00030\u009d\u0001H\u0002J\n\u0010×\u0001\u001a\u00030\u009d\u0001H\u0002J\u0013\u0010Ø\u0001\u001a\u00030\u009d\u00012\u0007\u0010\u009e\u0001\u001a\u00020wH\u0002J\u0013\u0010Ù\u0001\u001a\u00030\u009d\u00012\u0007\u0010\u009e\u0001\u001a\u00020wH\u0002J\u0013\u0010Ú\u0001\u001a\u00030\u009d\u00012\u0007\u0010\u009e\u0001\u001a\u00020wH\u0002J\u0013\u0010Û\u0001\u001a\u00030\u009d\u00012\u0007\u0010\u009e\u0001\u001a\u00020wH\u0002J\n\u0010Ü\u0001\u001a\u00030\u009d\u0001H\u0002J\n\u0010Ý\u0001\u001a\u00030\u009d\u0001H\u0002J\n\u0010Þ\u0001\u001a\u00030\u009d\u0001H\u0002J\n\u0010ß\u0001\u001a\u00030\u009d\u0001H\u0002J\n\u0010à\u0001\u001a\u00030\u009d\u0001H\u0002J\b\u0010á\u0001\u001a\u00030\u009d\u0001R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\bR\u001a\u0010\f\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u0006\"\u0004\b\u000e\u0010\bR\u001a\u0010\u000f\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0006\"\u0004\b\u0011\u0010\bR\u001a\u0010\u0012\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0006\"\u0004\b\u0014\u0010\bR\u001a\u0010\u0015\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0006\"\u0004\b\u0017\u0010\bR\u001a\u0010\u0018\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0006\"\u0004\b\u001a\u0010\bR\u001a\u0010\u001b\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0006\"\u0004\b\u001d\u0010\bR\u001c\u0010\u001e\u001a\u0004\u0018\u00010\u001fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R\u001c\u0010$\u001a\u0004\u0018\u00010\u001fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010!\"\u0004\b&\u0010#R\u0011\u0010'\u001a\u00020(¢\u0006\b\n\u0000\u001a\u0004\b)\u0010*R\u0011\u0010+\u001a\u00020(¢\u0006\b\n\u0000\u001a\u0004\b,\u0010*R\u001c\u0010-\u001a\u0004\u0018\u00010.X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b/\u00100\"\u0004\b1\u00102R\u001a\u00103\u001a\u000204X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b5\u00106\"\u0004\b7\u00108R\u001a\u00109\u001a\u000204X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b:\u00106\"\u0004\b;\u00108R\u001a\u0010<\u001a\u000204X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b=\u00106\"\u0004\b>\u00108R\u001a\u0010?\u001a\u000204X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b@\u00106\"\u0004\bA\u00108R\u001a\u0010B\u001a\u000204X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bC\u00106\"\u0004\bD\u00108R\u001a\u0010E\u001a\u000204X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bF\u00106\"\u0004\bG\u00108R\u001a\u0010H\u001a\u000204X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bI\u00106\"\u0004\bJ\u00108R\u001a\u0010K\u001a\u000204X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bL\u00106\"\u0004\bM\u00108R\u001a\u0010N\u001a\u000204X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bO\u00106\"\u0004\bP\u00108R\u001a\u0010Q\u001a\u000204X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bR\u00106\"\u0004\bS\u00108R\u001a\u0010T\u001a\u000204X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bU\u00106\"\u0004\bV\u00108R\u001a\u0010W\u001a\u000204X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bX\u00106\"\u0004\bY\u00108R\u001a\u0010Z\u001a\u00020[X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bZ\u0010\\\"\u0004\b]\u0010^R\u001a\u0010_\u001a\u00020[X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b_\u0010\\\"\u0004\b`\u0010^R\u001a\u0010a\u001a\u00020[X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\ba\u0010\\\"\u0004\bb\u0010^R\u001a\u0010c\u001a\u00020[X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bc\u0010\\\"\u0004\bd\u0010^R\u001a\u0010e\u001a\u00020[X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\be\u0010\\\"\u0004\bf\u0010^R\u001a\u0010g\u001a\u00020hX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bi\u0010j\"\u0004\bk\u0010lR\u001a\u0010m\u001a\u00020hX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bn\u0010j\"\u0004\bo\u0010lR\u001a\u0010p\u001a\u00020hX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bq\u0010j\"\u0004\br\u0010lR\u000e\u0010s\u001a\u00020tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010u\u001a\u00020hX\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010v\u001a\u0004\u0018\u00010wX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bx\u0010y\"\u0004\bz\u0010{R\u001a\u0010|\u001a\u00020hX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b}\u0010j\"\u0004\b~\u0010lR\u001c\u0010\u007f\u001a\u00020hX\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0080\u0001\u0010j\"\u0005\b\u0081\u0001\u0010lR\u001f\u0010\u0082\u0001\u001a\u00020tX\u0086\u000e¢\u0006\u0012\n\u0000\u001a\u0006\b\u0083\u0001\u0010\u0084\u0001\"\u0006\b\u0085\u0001\u0010\u0086\u0001R\u0012\u0010\u0087\u0001\u001a\u0005\u0018\u00010\u0088\u0001X\u0082\u000e¢\u0006\u0002\n\u0000R\u001d\u0010\u0089\u0001\u001a\u000204X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u008a\u0001\u00106\"\u0005\b\u008b\u0001\u00108R\u001d\u0010\u008c\u0001\u001a\u000204X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u008d\u0001\u00106\"\u0005\b\u008e\u0001\u00108R\u001d\u0010\u008f\u0001\u001a\u000204X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0090\u0001\u00106\"\u0005\b\u0091\u0001\u00108R\u001d\u0010\u0092\u0001\u001a\u00020\u0004X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0093\u0001\u0010\u0006\"\u0005\b\u0094\u0001\u0010\bR\u0012\u0010\u0095\u0001\u001a\u0005\u0018\u00010\u0096\u0001X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0097\u0001\u001a\u0005\u0018\u00010\u0096\u0001X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0098\u0001\u001a\u0005\u0018\u00010\u0096\u0001X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0099\u0001\u001a\u0005\u0018\u00010\u0096\u0001X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u009a\u0001\u001a\u0005\u0018\u00010\u009b\u0001X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006â\u0001"},
   d2 = {"Lcom/hzbhd/canbus/car/_432/MsgMgr;", "Lcom/hzbhd/canbus/msg_mgr/AbstractMsgMgr;", "()V", "SoftKey0", "", "getSoftKey0", "()Ljava/lang/String;", "setSoftKey0", "(Ljava/lang/String;)V", "SoftKey1", "getSoftKey1", "setSoftKey1", "SoftKey2", "getSoftKey2", "setSoftKey2", "SoftKey3", "getSoftKey3", "setSoftKey3", "content0x78WorkingMode", "getContent0x78WorkingMode", "setContent0x78WorkingMode", "contentStr0x70", "getContentStr0x70", "setContentStr0x70", "contentStr0x71", "getContentStr0x71", "setContentStr0x71", "contentStr0x72", "getContentStr0x72", "setContentStr0x72", "countDownTimer", "Landroid/os/CountDownTimer;", "getCountDownTimer", "()Landroid/os/CountDownTimer;", "setCountDownTimer", "(Landroid/os/CountDownTimer;)V", "countDownTimerAutoPacking", "getCountDownTimerAutoPacking", "setCountDownTimerAutoPacking", "df_2Integer", "Ljava/text/DecimalFormat;", "getDf_2Integer", "()Ljava/text/DecimalFormat;", "df_2float", "getDf_2float", "dialog", "Landroid/app/AlertDialog;", "getDialog", "()Landroid/app/AlertDialog;", "setDialog", "(Landroid/app/AlertDialog;)V", "icon1", "", "getIcon1", "()I", "setIcon1", "(I)V", "icon2", "getIcon2", "setIcon2", "icon3", "getIcon3", "setIcon3", "icon4", "getIcon4", "setIcon4", "icon5", "getIcon5", "setIcon5", "icon6", "getIcon6", "setIcon6", "icon7", "getIcon7", "setIcon7", "icon8", "getIcon8", "setIcon8", "image0", "getImage0", "setImage0", "image1", "getImage1", "setImage1", "image2", "getImage2", "setImage2", "image3", "getImage3", "setImage3", "isMute", "", "()Z", "setMute", "(Z)V", "isSelected0", "setSelected0", "isSelected1", "setSelected1", "isSelected2", "setSelected2", "isSelected3", "setSelected3", "m0x79Data", "", "getM0x79Data", "()[I", "setM0x79Data", "([I)V", "mAirData", "getMAirData", "setMAirData", "mBacklightInfo", "getMBacklightInfo", "setMBacklightInfo", "mCanBusByte", "", "mCanBusInfoInt", "mContext", "Landroid/content/Context;", "getMContext", "()Landroid/content/Context;", "setMContext", "(Landroid/content/Context;)V", "mFrontRadarData", "getMFrontRadarData", "setMFrontRadarData", "mRearRadarData", "getMRearRadarData", "setMRearRadarData", "mTrackData", "getMTrackData", "()[B", "setMTrackData", "([B)V", "mUiMgr", "Lcom/hzbhd/canbus/car/_432/UiMgr;", "now0x24Data0", "getNow0x24Data0", "setNow0x24Data0", "nowData6", "getNowData6", "setNowData6", "nowIndex", "getNowIndex", "setNowIndex", "nowSyncTime", "getNowSyncTime", "setNowSyncTime", "park_1", "Landroid/widget/Button;", "park_2", "park_3", "park_4", "parking_content", "Landroid/widget/TextView;", "afterServiceNormalSetting", "", "context", "blockBit", "canBusInfo", "canbusInfoChange", "canbusInfo", "currentVolumeInfoChange", "volValue", "get0x61Str", "getAsciiStr", "getBandState", "i", "getCdPlayState", "getCdState", "getEqData5Bit7Info", "boolBit7", "getFrequencyState", "getMsbLsbResult", "MSB", "LSB", "getPlayMode", "getRearTemperature", "tem", "getRunningState", "getStringIdByName", "name", "getSyncIconResId", "getTemperature", "getUigMgr", "initCommand", "is0x79DataNoChange", "isAirDataChange", "isBacklightInfoChange", "isDoorInfoChange", "data0", "isFrontRadarDataChange", "isRearAirChange", "data6", "isRearRadarDataChange", "isTrackInfoChange", "set0x14BackLightInfo", "set0x16SpeedInfo", "set0x20SwcInfo", "set0x21AirInfo", "set0x22RearRadar", "set0x23FrontRadar", "set0x24BasicInfo", "set0x25RadarState", "set0x27Languge", "set0x28AutoPacking", "set0x29CarInfo", "set0x2bSeatInfo", "set0x30VersionInfo", "set0x35EspInfo", "set0x40SyncVersion", "set0x50SyncInfo", "set0x51SyncInfo", "set0x52SyncInfo", "set0x53SyncInfo", "set0x60RadioInfo", "set0x61RadioInfo", "set0x62CdInfo", "set0x63EqInfo", "set0x70SyncLastStr", "set0x71SyncNextStr", "set0x72SyncTimeInfo", "set0x78SyncState", "set0x79SyncAudio", "updateSYNC", "CanBusInfo_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class MsgMgr extends AbstractMsgMgr {
   private String SoftKey0 = "Audio:";
   private String SoftKey1 = "";
   private String SoftKey2 = "";
   private String SoftKey3 = "";
   private String content0x78WorkingMode = "Working mode: STATELESS";
   private String contentStr0x70 = "SYNC SET UP";
   private String contentStr0x71 = "SYNC SET DOWN";
   private String contentStr0x72 = "SYNC STR SHORT";
   private CountDownTimer countDownTimer;
   private CountDownTimer countDownTimerAutoPacking;
   private final DecimalFormat df_2Integer = new DecimalFormat("00");
   private final DecimalFormat df_2float = new DecimalFormat("#0.00");
   private AlertDialog dialog;
   private int icon1 = 2131233649;
   private int icon2 = 2131233649;
   private int icon3 = 2131233649;
   private int icon4 = 2131233649;
   private int icon5 = 2131233649;
   private int icon6 = 2131233649;
   private int icon7 = 2131233649;
   private int icon8 = 2131233649;
   private int image0 = 2131233649;
   private int image1 = 2131233649;
   private int image2 = 2131233649;
   private int image3 = 2131233649;
   private boolean isMute;
   private boolean isSelected0;
   private boolean isSelected1;
   private boolean isSelected2;
   private boolean isSelected3;
   private int[] m0x79Data = new int[0];
   private int[] mAirData = new int[0];
   private int[] mBacklightInfo = new int[0];
   private byte[] mCanBusByte = new byte[0];
   private int[] mCanBusInfoInt = new int[0];
   private Context mContext;
   private int[] mFrontRadarData = new int[0];
   private int[] mRearRadarData = new int[0];
   private byte[] mTrackData = new byte[0];
   private UiMgr mUiMgr;
   private int now0x24Data0;
   private int nowData6;
   private int nowIndex = -1;
   private String nowSyncTime = "";
   private Button park_1;
   private Button park_2;
   private Button park_3;
   private Button park_4;
   private TextView parking_content;

   private final String get0x61Str() {
      int var2 = 0;
      byte[] var5 = new byte[0];
      byte[] var6 = this.mCanBusByte;
      if (var6.length < 6) {
         return " ";
      } else {
         int var3 = var6.length;

         for(int var1 = 0; var2 < var3; ++var1) {
            byte var10000 = var6[var2];
            if (var1 != 0 && var1 != 1 && var1 != 2 && var1 != 3) {
               var5[var1 - 4] = this.mCanBusByte[4];
            }

            ++var2;
         }

         return this.getAsciiStr(var5);
      }
   }

   private final String getBandState(int var1) {
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  return var1 != 4 ? "NONE" : "AM2";
               } else {
                  return "AM1";
               }
            } else {
               return "FM2";
            }
         } else {
            return "FM1";
         }
      } else {
         return "FM";
      }
   }

   private final String getCdPlayState(int var1) {
      Context var3 = null;
      Object var5 = null;
      Context var4 = null;
      String var2 = null;
      Object var6 = null;
      if (var1 != 1) {
         if (var1 != 2) {
            if (var1 != 3) {
               if (var1 != 4) {
                  if (var1 != 5) {
                     return "NONE";
                  } else {
                     var3 = this.mContext;
                     var2 = (String)var6;
                     if (var3 != null) {
                        var2 = var3.getString(2131765760);
                     }

                     return var2;
                  }
               } else {
                  var4 = this.mContext;
                  var2 = var3;
                  if (var4 != null) {
                     var2 = var4.getString(2131765759);
                  }

                  return var2;
               }
            } else {
               var3 = this.mContext;
               var2 = (String)var5;
               if (var3 != null) {
                  var2 = var3.getString(2131765772);
               }

               return var2;
            }
         } else {
            var3 = this.mContext;
            var2 = var4;
            if (var3 != null) {
               var2 = var3.getString(2131765771);
            }

            return var2;
         }
      } else {
         var3 = this.mContext;
         if (var3 != null) {
            var2 = var3.getString(2131765770);
         }

         return var2;
      }
   }

   private final String getCdState(int var1) {
      Object var7 = null;
      Object var5 = null;
      String var2 = null;
      Context var3 = null;
      Object var6 = null;
      Context var4 = null;
      switch (var1) {
         case 1:
            var3 = this.mContext;
            var2 = (String)var6;
            if (var3 != null) {
               var2 = var3.getString(2131765758);
            }

            return var2;
         case 2:
            var4 = this.mContext;
            var2 = var3;
            if (var4 != null) {
               var2 = var4.getString(2131765765);
            }

            return var2;
         case 3:
            var3 = this.mContext;
            if (var3 != null) {
               var2 = var3.getString(2131765766);
            }

            return var2;
         case 4:
            var3 = this.mContext;
            var2 = (String)var5;
            if (var3 != null) {
               var2 = var3.getString(2131765767);
            }

            return var2;
         case 5:
            var3 = this.mContext;
            var2 = (String)var7;
            if (var3 != null) {
               var2 = var3.getString(2131765768);
            }

            return var2;
         case 6:
            var3 = this.mContext;
            var2 = var4;
            if (var3 != null) {
               var2 = var3.getString(2131765769);
            }

            return var2;
         default:
            return "NONE";
      }
   }

   private final String getEqData5Bit7Info(boolean var1) {
      String var2 = null;
      Context var3 = null;
      if (var1) {
         Context var4 = this.mContext;
         var2 = var3;
         if (var4 != null) {
            var2 = var4.getString(2131765782);
         }

         return var2;
      } else {
         var3 = this.mContext;
         if (var3 != null) {
            var2 = var3.getString(2131765783);
         }

         return var2;
      }
   }

   private final String getFrequencyState() {
      int var1 = this.mCanBusInfoInt[2];
      int[] var3;
      DecimalFormat var4;
      if (var1 != 0) {
         int[] var5;
         DecimalFormat var6;
         StringBuilder var9;
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  if (var1 != 4) {
                     return "NONE";
                  } else {
                     var9 = new StringBuilder();
                     DecimalFormat var7 = this.df_2Integer;
                     var3 = this.mCanBusInfoInt;
                     return var9.append(var7.format(this.getMsbLsbResult(var3[4], var3[5]))).append("KHZ").toString();
                  }
               } else {
                  var9 = new StringBuilder();
                  var6 = this.df_2Integer;
                  var5 = this.mCanBusInfoInt;
                  return var9.append(var6.format(this.getMsbLsbResult(var5[4], var5[5]))).append("KHZ").toString();
               }
            } else {
               StringBuilder var8 = new StringBuilder();
               var4 = this.df_2float;
               var5 = this.mCanBusInfoInt;
               return var8.append(var4.format((float)this.getMsbLsbResult(var5[4], var5[5]) / 100.0F)).append("MHZ").toString();
            }
         } else {
            var9 = new StringBuilder();
            var6 = this.df_2float;
            var5 = this.mCanBusInfoInt;
            return var9.append(var6.format((float)this.getMsbLsbResult(var5[4], var5[5]) / 100.0F)).append("MHZ").toString();
         }
      } else {
         StringBuilder var2 = new StringBuilder();
         var4 = this.df_2float;
         var3 = this.mCanBusInfoInt;
         return var2.append(var4.format((float)this.getMsbLsbResult(var3[4], var3[5]) / 100.0F)).append("MHZ").toString();
      }
   }

   private final String getPlayMode() {
      String var1;
      if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4])) {
         var1 = "【Repeat：ON】";
      } else {
         var1 = "【Repeat：OFF】";
      }

      String var2;
      if (DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4])) {
         var2 = "【Random：ON】";
      } else {
         var2 = "【Random：OFF】";
      }

      String var3;
      if (DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4])) {
         var3 = "【Quick browse：ON】";
      } else {
         var3 = "【Quick browse：OFF】";
      }

      return var1 + var2 + var3;
   }

   private final String getRearTemperature(int var1) {
      String var2;
      if (var1 != 0) {
         if (var1 != 4) {
            if (var1 != 8) {
               var2 = var1 + "LEVEL";
            } else {
               var2 = "HI";
            }
         } else {
            var2 = "MID";
         }
      } else {
         var2 = "LOW";
      }

      return var2;
   }

   private final String getRunningState(int var1) {
      Context var4 = null;
      String var2 = null;
      Context var3 = null;
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               return "NONE";
            } else {
               var4 = this.mContext;
               var2 = var3;
               if (var4 != null) {
                  var2 = var4.getString(2131765805);
               }

               return var2;
            }
         } else {
            var3 = this.mContext;
            var2 = var4;
            if (var3 != null) {
               var2 = var3.getString(2131765804);
            }

            return var2;
         }
      } else {
         var3 = this.mContext;
         if (var3 != null) {
            var2 = var3.getString(2131765803);
         }

         return var2;
      }
   }

   private final int getStringIdByName(Context var1, String var2) {
      return CommUtil.getStrIdByResId(var1, var2);
   }

   private final int getSyncIconResId(Context var1, String var2) {
      int var4 = CommUtil.getImgIdByResId(var1, var2);
      int var3 = var4;
      if (var4 == 2131234410) {
         var3 = 2131233621;
      }

      return var3;
   }

   private final String getTemperature(int var1) {
      String var2;
      if (var1 != 0) {
         if (var1 != 127) {
            if (DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[6])) {
               var2 = "" + (float)var1 / 2.0F + ' ' + this.getTempUnitF(this.mContext);
            } else {
               var2 = "" + (float)var1 / 2.0F + ' ' + this.getTempUnitC(this.mContext);
            }
         } else {
            var2 = "HI";
         }
      } else {
         var2 = "LOW";
      }

      return var2;
   }

   private final UiMgr getUigMgr(Context var1) {
      if (this.mUiMgr == null) {
         UiMgrInterface var2 = UiMgrFactory.getCanUiMgr(var1);
         Intrinsics.checkNotNull(var2, "null cannot be cast to non-null type com.hzbhd.canbus.car._432.UiMgr");
         this.mUiMgr = (UiMgr)var2;
      }

      UiMgr var3 = this.mUiMgr;
      Intrinsics.checkNotNull(var3, "null cannot be cast to non-null type com.hzbhd.canbus.car._432.UiMgr");
      return var3;
   }

   private final boolean is0x79DataNoChange() {
      if (Arrays.equals(this.m0x79Data, this.mCanBusInfoInt)) {
         return true;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         var1 = Arrays.copyOf(var1, var1.length);
         Intrinsics.checkNotNullExpressionValue(var1, "copyOf(mCanBusInfoInt, mCanBusInfoInt.size)");
         this.m0x79Data = var1;
         return false;
      }
   }

   private final boolean isAirDataChange() {
      if (Arrays.equals(this.mAirData, this.mCanBusInfoInt)) {
         return true;
      } else {
         this.mAirData = this.mCanBusInfoInt;
         return false;
      }
   }

   private final boolean isBacklightInfoChange() {
      if (Arrays.equals(this.mBacklightInfo, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         var1 = Arrays.copyOf(var1, var1.length);
         Intrinsics.checkNotNullExpressionValue(var1, "copyOf(mCanBusInfoInt, mCanBusInfoInt.size)");
         this.mBacklightInfo = var1;
         return true;
      }
   }

   private final boolean isDoorInfoChange(int var1) {
      if (this.now0x24Data0 == var1) {
         return false;
      } else {
         this.now0x24Data0 = var1;
         return true;
      }
   }

   private final boolean isFrontRadarDataChange() {
      if (Arrays.equals(this.mFrontRadarData, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         var1 = Arrays.copyOf(var1, var1.length);
         Intrinsics.checkNotNullExpressionValue(var1, "copyOf(mCanBusInfoInt, mCanBusInfoInt.size)");
         this.mFrontRadarData = var1;
         return true;
      }
   }

   private final boolean isRearAirChange(int var1) {
      if (this.nowData6 == var1) {
         return false;
      } else {
         this.nowData6 = var1;
         return true;
      }
   }

   private final boolean isRearRadarDataChange() {
      if (Arrays.equals(this.mRearRadarData, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         var1 = Arrays.copyOf(var1, var1.length);
         Intrinsics.checkNotNullExpressionValue(var1, "copyOf(mCanBusInfoInt, mCanBusInfoInt.size)");
         this.mRearRadarData = var1;
         return true;
      }
   }

   private final boolean isTrackInfoChange() {
      if (Arrays.equals(this.mTrackData, this.mCanBusByte)) {
         return true;
      } else {
         byte[] var1 = this.mCanBusByte;
         var1 = Arrays.copyOf(var1, var1.length);
         Intrinsics.checkNotNullExpressionValue(var1, "copyOf(mCanBusByte, mCanBusByte.size)");
         this.mTrackData = var1;
         return false;
      }
   }

   private final void set0x14BackLightInfo() {
      if (this.isBacklightInfoChange()) {
         this.setBacklightLevel(this.mCanBusInfoInt[2] / 51 + 1);
      }

   }

   private final void set0x16SpeedInfo() {
      if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4])) {
         DecimalFormat var1 = this.df_2Integer;
         int[] var2 = this.mCanBusInfoInt;
         this.updateSpeedInfo(Integer.parseInt(var1.format((double)this.getMsbLsbResult(var2[3], var2[2]) * 1.6)));
      } else {
         int[] var3 = this.mCanBusInfoInt;
         this.updateSpeedInfo(this.getMsbLsbResult(var3[3], var3[2]));
      }

   }

   private final void set0x20SwcInfo() {
      int[] var2 = this.mCanBusInfoInt;
      int var1 = var2[2];
      if (var1 != 61) {
         if (var1 != 63) {
            if (var1 != 111) {
               if (var1 != 134) {
                  if (var1 != 86) {
                     if (var1 != 87) {
                        switch (var1) {
                           case 0:
                              this.realKeyLongClick1(this.mContext, 0, var2[3]);
                              break;
                           case 1:
                              this.realKeyLongClick1(this.mContext, 7, var2[3]);
                              break;
                           case 2:
                              this.realKeyLongClick1(this.mContext, 8, var2[3]);
                              break;
                           case 3:
                              this.realKeyLongClick1(this.mContext, 46, var2[3]);
                              break;
                           case 4:
                              this.realKeyLongClick1(this.mContext, 45, var2[3]);
                              break;
                           case 5:
                              this.realKeyLongClick1(this.mContext, 188, var2[3]);
                              break;
                           case 6:
                              this.realKeyLongClick1(this.mContext, 3, var2[3]);
                              break;
                           case 7:
                              this.realKeyLongClick1(this.mContext, 2, var2[3]);
                              break;
                           case 8:
                              this.realKeyLongClick1(this.mContext, 136, var2[3]);
                              break;
                           case 9:
                              this.realKeyLongClick1(this.mContext, 128, var2[3]);
                              break;
                           case 10:
                              this.realKeyLongClick1(this.mContext, 15, var2[3]);
                              break;
                           case 11:
                              this.realKeyLongClick1(this.mContext, 187, var2[3]);
                              break;
                           default:
                              switch (var1) {
                                 case 14:
                                    this.realKeyLongClick1(this.mContext, 45, var2[3]);
                                    break;
                                 case 15:
                                    this.realKeyLongClick1(this.mContext, 46, var2[3]);
                                    break;
                                 case 16:
                                    this.realKeyLongClick1(this.mContext, 47, var2[3]);
                                    break;
                                 case 17:
                                    this.realKeyLongClick1(this.mContext, 48, var2[3]);
                                    break;
                                 case 18:
                                    this.realKeyLongClick1(this.mContext, 49, var2[3]);
                                    break;
                                 default:
                                    switch (var1) {
                                       case 32:
                                          this.realKeyLongClick1(this.mContext, 42, var2[3]);
                                          break;
                                       case 33:
                                          this.realKeyLongClick1(this.mContext, 33, var2[3]);
                                          break;
                                       case 34:
                                          this.realKeyLongClick1(this.mContext, 34, var2[3]);
                                          break;
                                       case 35:
                                          this.realKeyLongClick1(this.mContext, 35, var2[3]);
                                          break;
                                       case 36:
                                          this.realKeyLongClick1(this.mContext, 36, var2[3]);
                                          break;
                                       case 37:
                                          this.realKeyLongClick1(this.mContext, 37, var2[3]);
                                          break;
                                       case 38:
                                          this.realKeyLongClick1(this.mContext, 38, var2[3]);
                                          break;
                                       case 39:
                                          this.realKeyLongClick1(this.mContext, 39, var2[3]);
                                          break;
                                       case 40:
                                          this.realKeyLongClick1(this.mContext, 40, var2[3]);
                                          break;
                                       case 41:
                                          this.realKeyLongClick1(this.mContext, 41, var2[3]);
                                          break;
                                       case 42:
                                          this.realKeyLongClick1(this.mContext, 63, var2[3]);
                                          break;
                                       case 43:
                                          this.realKeyLongClick1(this.mContext, 13, var2[3]);
                                          break;
                                       default:
                                          switch (var1) {
                                             case 51:
                                                this.realKeyLongClick1(this.mContext, 198, var2[3]);
                                                break;
                                             case 52:
                                                this.realKeyLongClick1(this.mContext, 4112, var2[3]);
                                                break;
                                             case 53:
                                                this.realKeyLongClick1(this.mContext, 4113, var2[3]);
                                                break;
                                             case 54:
                                                this.realKeyLongClick1(this.mContext, 141, var2[3]);
                                                break;
                                             case 55:
                                                this.realKeyLongClick1(this.mContext, 151, var2[3]);
                                                break;
                                             case 56:
                                                this.realKeyLongClick1(this.mContext, 3, var2[3]);
                                                break;
                                             case 57:
                                                this.realKeyLongClick1(this.mContext, 15, var2[3]);
                                                break;
                                             default:
                                                switch (var1) {
                                                   case 72:
                                                      this.realKeyLongClick1(this.mContext, 49, var2[3]);
                                                      break;
                                                   case 73:
                                                      this.realKeyLongClick1(this.mContext, 47, var2[3]);
                                                      break;
                                                   case 74:
                                                      this.realKeyLongClick1(this.mContext, 48, var2[3]);
                                                      break;
                                                   case 75:
                                                      this.realKeyLongClick1(this.mContext, 45, var2[3]);
                                                      break;
                                                   case 76:
                                                      this.realKeyLongClick1(this.mContext, 46, var2[3]);
                                                      break;
                                                   default:
                                                      switch (var1) {
                                                         case 82:
                                                            this.realKeyLongClick1(this.mContext, 206, var2[3]);
                                                            break;
                                                         case 83:
                                                            this.realKeyLongClick1(this.mContext, 207, var2[3]);
                                                            break;
                                                         case 84:
                                                            this.realKeyLongClick1(this.mContext, 31, var2[3]);
                                                            break;
                                                         default:
                                                            switch (var1) {
                                                               case 89:
                                                                  this.realKeyLongClick1(this.mContext, 182, var2[3]);
                                                                  break;
                                                               case 90:
                                                                  this.realKeyLongClick1(this.mContext, 3, var2[3]);
                                                                  break;
                                                               case 91:
                                                                  this.realKeyLongClick1(this.mContext, 57, var2[3]);
                                                                  break;
                                                               case 92:
                                                                  this.realKeyLongClick1(this.mContext, 45, var2[3]);
                                                                  break;
                                                               case 93:
                                                                  this.realKeyLongClick1(this.mContext, 94, var2[3]);
                                                                  break;
                                                               case 94:
                                                                  this.realKeyLongClick1(this.mContext, 91, var2[3]);
                                                                  break;
                                                               case 95:
                                                                  this.realKeyLongClick1(this.mContext, 46, var2[3]);
                                                                  break;
                                                               default:
                                                                  switch (var1) {
                                                                     case 240:
                                                                        this.realKeyLongClick1(this.mContext, 7, var2[3]);
                                                                        break;
                                                                     case 241:
                                                                        this.realKeyLongClick1(this.mContext, 8, var2[3]);
                                                                        break;
                                                                     case 242:
                                                                        this.realKeyLongClick1(this.mContext, 45, var2[3]);
                                                                        break;
                                                                     case 243:
                                                                        this.realKeyLongClick1(this.mContext, 46, var2[3]);
                                                                  }
                                                            }
                                                      }
                                                }
                                          }
                                    }
                              }
                        }
                     } else {
                        this.realKeyLongClick1(this.mContext, 58, var2[3]);
                     }
                  } else {
                     this.realKeyLongClick1(this.mContext, 94, var2[3]);
                  }
               } else {
                  this.realKeyLongClick1(this.mContext, 95, var2[3]);
               }
            } else {
               this.realKeyLongClick1(this.mContext, 2, var2[3]);
            }
         } else {
            this.realKeyLongClick1(this.mContext, 1, var2[3]);
         }
      } else {
         this.realKeyLongClick1(this.mContext, 196, var2[3]);
      }

   }

   private final void set0x21AirInfo() {
      int[] var1 = this.mCanBusInfoInt;
      var1[3] = this.blockBit(var1[3], 4);
      if (DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[6])) {
         this.updateOutDoorTemp(this.mContext, this.mCanBusInfoInt[7] + this.getTempUnitF(this.mContext));
      } else {
         this.updateOutDoorTemp(this.mContext, this.mCanBusInfoInt[7] + this.getTempUnitC(this.mContext));
      }

      this.mCanBusInfoInt[7] = 0;
      if (!this.isAirDataChange()) {
         GeneralAirData.power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
         GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
         GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]) ^ true;
         GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
         GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
         GeneralAirData.max_front = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
         GeneralAirData.front_left_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
         GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
         GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
         GeneralAirData.front_right_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
         GeneralAirData.front_right_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
         GeneralAirData.front_right_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
         GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4);
         GeneralAirData.front_left_temperature = this.getTemperature(this.mCanBusInfoInt[4]);
         GeneralAirData.front_right_temperature = this.getTemperature(this.mCanBusInfoInt[5]);
         GeneralAirData.rear = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[6]);
         GeneralAirData.steering_wheel_heating = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[6]);
         GeneralAirData.ac_max = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[6]);
         GeneralAirData.front_defog = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[6]);
         GeneralAirData.rear_temperature = this.getRearTemperature(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 0, 4));
         GeneralAirData.rear_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 4, 3);
         GeneralAirData.rear_power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[8]) ^ true;
         GeneralAirData.front_left_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 0, 2);
         GeneralAirData.front_right_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 2, 2);
         GeneralAirData.front_left_seat_cold_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 4, 2);
         GeneralAirData.front_right_seat_cold_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 6, 2);
         if (this.isRearAirChange(this.mCanBusInfoInt[8])) {
            this.updateAirActivity(this.mContext, 1002);
         } else {
            this.updateAirActivity(this.mContext, 1001);
         }

      }
   }

   private final void set0x22RearRadar() {
      if (this.isRearRadarDataChange()) {
         RadarInfoUtil.mMinIsClose = true;
         int[] var1 = this.mCanBusInfoInt;
         RadarInfoUtil.setRearRadarLocationData(31, var1[2], var1[3], var1[4], var1[5]);
         GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
         this.updateParkUi((Bundle)null, this.mContext);
      }

   }

   private final void set0x23FrontRadar() {
      if (this.isFrontRadarDataChange()) {
         RadarInfoUtil.mMinIsClose = true;
         int[] var1 = this.mCanBusInfoInt;
         RadarInfoUtil.setFrontRadarLocationData(31, var1[2], var1[3], var1[4], var1[5]);
         GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
         this.updateParkUi((Bundle)null, this.mContext);
      }

   }

   private final void set0x24BasicInfo() {
      if (this.isDoorInfoChange(this.mCanBusInfoInt[2])) {
         GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
         GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
         GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
         GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
         GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
         this.updateDoorView(this.mContext);
      }

      this.updateReverseGear(DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[3]));
      this.updateHandbrakeState(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3]));
      this.forceReverse(this.mContext, DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]));
   }

   private final void set0x25RadarState() {
   }

   private final void set0x27Languge() {
   }

   private final void set0x28AutoPacking() {
      LayoutInflater var1 = LayoutInflater.from(this.mContext);
      Object var2 = null;
      View var4 = var1.inflate(2131558516, (ViewGroup)null, true);
      if (this.dialog == null) {
         this.dialog = (new AlertDialog.Builder(this.mContext)).setView(var4).create();
      }

      if (this.parking_content == null) {
         this.parking_content = (TextView)var4.findViewById(2131362967);
      }

      if (this.park_1 == null) {
         this.park_1 = (Button)var4.findViewById(2131362963);
      }

      if (this.park_2 == null) {
         this.park_2 = (Button)var4.findViewById(2131362964);
      }

      if (this.park_3 == null) {
         this.park_3 = (Button)var4.findViewById(2131362965);
      }

      if (this.park_4 == null) {
         this.park_4 = (Button)var4.findViewById(2131362966);
      }

      TextView var5 = this.parking_content;
      if (var5 != null) {
         var5.setText(this.getStringIdByName(this.mContext, "_432_car_park_state_" + this.mCanBusInfoInt[3]));
      }

      Button var6;
      if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 1, 2) == 0) {
         var6 = this.park_1;
         if (var6 != null) {
            var6.setBackgroundColor(2131099974);
         }

         var6 = this.park_2;
         if (var6 != null) {
            var6.setBackgroundColor(2131100046);
         }

         var6 = this.park_3;
         if (var6 != null) {
            var6.setBackgroundColor(2131100046);
         }

         var6 = this.park_4;
         if (var6 != null) {
            var6.setBackgroundColor(2131100046);
         }
      } else if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 1, 2) == 1) {
         var6 = this.park_1;
         if (var6 != null) {
            var6.setBackgroundColor(2131100046);
         }

         var6 = this.park_2;
         if (var6 != null) {
            var6.setBackgroundColor(2131099974);
         }

         var6 = this.park_3;
         if (var6 != null) {
            var6.setBackgroundColor(2131100046);
         }

         var6 = this.park_4;
         if (var6 != null) {
            var6.setBackgroundColor(2131100046);
         }
      } else if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 1, 2) == 2) {
         var6 = this.park_1;
         if (var6 != null) {
            var6.setBackgroundColor(2131100046);
         }

         var6 = this.park_2;
         if (var6 != null) {
            var6.setBackgroundColor(2131100046);
         }

         var6 = this.park_3;
         if (var6 != null) {
            var6.setBackgroundColor(2131099974);
         }

         var6 = this.park_4;
         if (var6 != null) {
            var6.setBackgroundColor(2131100046);
         }
      } else if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 1, 2) == 3) {
         var6 = this.park_1;
         if (var6 != null) {
            var6.setBackgroundColor(2131100046);
         }

         var6 = this.park_2;
         if (var6 != null) {
            var6.setBackgroundColor(2131100046);
         }

         var6 = this.park_3;
         if (var6 != null) {
            var6.setBackgroundColor(2131100046);
         }

         var6 = this.park_4;
         if (var6 != null) {
            var6.setBackgroundColor(2131099974);
         }
      }

      AlertDialog var7 = this.dialog;
      Window var8;
      if (var7 != null) {
         var8 = var7.getWindow();
      } else {
         var8 = null;
      }

      Intrinsics.checkNotNull(var8);
      var8.setBackgroundDrawableResource(17170445);
      AlertDialog var3 = this.dialog;
      var8 = (Window)var2;
      if (var3 != null) {
         var8 = var3.getWindow();
      }

      Intrinsics.checkNotNull(var8);
      var8.setType(2003);
      if (DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2])) {
         var7 = this.dialog;
         if (var7 != null) {
            var7.show();
         }
      } else {
         var7 = this.dialog;
         if (var7 != null) {
            var7.dismiss();
         }
      }

      CountDownTimer var9 = this.countDownTimerAutoPacking;
      if (var9 != null) {
         var9.cancel();
      }

      this.countDownTimerAutoPacking = (new CountDownTimer(this) {
         final MsgMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onFinish() {
            AlertDialog var1 = this.this$0.getDialog();
            if (var1 != null) {
               var1.dismiss();
            }

         }

         public void onTick(long var1) {
         }
      }).start();
   }

   private final void set0x29CarInfo() {
   }

   private final void set0x2bSeatInfo() {
   }

   private final void set0x30VersionInfo() {
      this.updateVersionInfo(this.mContext, this.getVersionStr(this.mCanBusByte));
   }

   private final void set0x35EspInfo() {
      if (!this.isTrackInfoChange()) {
         byte[] var1 = this.mCanBusByte;
         GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(var1[2], var1[3], 0, 4608, 16);
         this.updateParkUi((Bundle)null, this.mContext);
      }
   }

   private final void set0x40SyncVersion() {
   }

   private final void set0x50SyncInfo() {
   }

   private final void set0x51SyncInfo() {
   }

   private final void set0x52SyncInfo() {
      this.nowSyncTime = this.df_2Integer.format(this.mCanBusInfoInt[2]) + ':' + this.df_2Integer.format(this.mCanBusInfoInt[3]) + ':' + this.df_2Integer.format(this.mCanBusInfoInt[4]);
      this.updateSYNC();
   }

   private final void set0x53SyncInfo() {
      this.nowSyncTime = this.df_2Integer.format(this.mCanBusInfoInt[3]) + ':' + this.df_2Integer.format(this.mCanBusInfoInt[4]);
      this.updateSYNC();
   }

   private final void set0x60RadioInfo(Context var1) {
      List var2 = (List)(new ArrayList());
      var2.add(new DriverUpdateEntity(this.getUigMgr(var1).getDrivingPageIndexes(this.mContext, "_432_radio_info"), this.getUigMgr(var1).getDrivingItemIndexes(this.mContext, "_432_radio_info1"), this.getBandState(this.mCanBusInfoInt[2])));
      var2.add(new DriverUpdateEntity(this.getUigMgr(var1).getDrivingPageIndexes(this.mContext, "_432_radio_info"), this.getUigMgr(var1).getDrivingItemIndexes(this.mContext, "_432_radio_info2"), this.getRunningState(this.mCanBusInfoInt[3])));
      var2.add(new DriverUpdateEntity(this.getUigMgr(var1).getDrivingPageIndexes(this.mContext, "_432_radio_info"), this.getUigMgr(var1).getDrivingItemIndexes(this.mContext, "_432_radio_info3"), this.getFrequencyState()));
      var2.add(new DriverUpdateEntity(this.getUigMgr(var1).getDrivingPageIndexes(this.mContext, "_432_radio_info"), this.getUigMgr(var1).getDrivingItemIndexes(this.mContext, "_432_radio_info4"), String.valueOf(this.mCanBusInfoInt[6])));
      this.updateGeneralDriveData(var2);
      this.updateDriveDataActivity((Bundle)null);
   }

   private final void set0x61RadioInfo(Context var1) {
      List var2 = (List)(new ArrayList());
      var2.add(new DriverUpdateEntity(this.getUigMgr(var1).getDrivingPageIndexes(this.mContext, "_432_radio_info"), this.getUigMgr(var1).getDrivingItemIndexes(this.mContext, "_432_radio_info5"), this.getBandState(this.mCanBusInfoInt[2])));
      var2.add(new DriverUpdateEntity(this.getUigMgr(var1).getDrivingPageIndexes(this.mContext, "_432_radio_info"), this.getUigMgr(var1).getDrivingItemIndexes(this.mContext, "_432_radio_info6"), String.valueOf(this.mCanBusInfoInt[3])));
      this.updateGeneralDriveData(var2);
      this.updateDriveDataActivity((Bundle)null);
   }

   private final void set0x62CdInfo(Context var1) {
      List var2 = (List)(new ArrayList());
      var2.add(new DriverUpdateEntity(this.getUigMgr(var1).getDrivingPageIndexes(this.mContext, "_432_CD_info"), this.getUigMgr(var1).getDrivingItemIndexes(this.mContext, "_432_CD_info1"), this.getCdState(this.mCanBusInfoInt[2])));
      var2.add(new DriverUpdateEntity(this.getUigMgr(var1).getDrivingPageIndexes(this.mContext, "_432_CD_info"), this.getUigMgr(var1).getDrivingItemIndexes(this.mContext, "_432_CD_info2"), this.getCdPlayState(this.mCanBusInfoInt[3])));
      var2.add(new DriverUpdateEntity(this.getUigMgr(var1).getDrivingPageIndexes(this.mContext, "_432_CD_info"), this.getUigMgr(var1).getDrivingItemIndexes(this.mContext, "_432_CD_info3"), this.getPlayMode()));
      var2.add(new DriverUpdateEntity(this.getUigMgr(var1).getDrivingPageIndexes(this.mContext, "_432_CD_info"), this.getUigMgr(var1).getDrivingItemIndexes(this.mContext, "_432_CD_info4"), String.valueOf(this.mCanBusInfoInt[5])));
      var2.add(new DriverUpdateEntity(this.getUigMgr(var1).getDrivingPageIndexes(this.mContext, "_432_CD_info"), this.getUigMgr(var1).getDrivingItemIndexes(this.mContext, "_432_CD_info5"), String.valueOf(this.mCanBusInfoInt[6])));
      var2.add(new DriverUpdateEntity(this.getUigMgr(var1).getDrivingPageIndexes(this.mContext, "_432_CD_info"), this.getUigMgr(var1).getDrivingItemIndexes(this.mContext, "_432_CD_info6"), this.df_2Integer.format(this.mCanBusInfoInt[7]) + " : " + this.df_2Integer.format(this.mCanBusInfoInt[8])));
      this.updateGeneralDriveData(var2);
      this.updateDriveDataActivity((Bundle)null);
   }

   private final void set0x63EqInfo(Context var1) {
      List var2 = (List)(new ArrayList());
      var2.add(new DriverUpdateEntity(this.getUigMgr(var1).getDrivingPageIndexes(this.mContext, "_432_EQ_info"), this.getUigMgr(var1).getDrivingItemIndexes(this.mContext, "_432_EQ_info0"), String.valueOf(this.mCanBusInfoInt[2])));
      var2.add(new DriverUpdateEntity(this.getUigMgr(var1).getDrivingPageIndexes(this.mContext, "_432_EQ_info"), this.getUigMgr(var1).getDrivingItemIndexes(this.mContext, "_432_EQ_info1"), String.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 4))));
      var2.add(new DriverUpdateEntity(this.getUigMgr(var1).getDrivingPageIndexes(this.mContext, "_432_EQ_info"), this.getUigMgr(var1).getDrivingItemIndexes(this.mContext, "_432_EQ_info2"), String.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4))));
      var2.add(new DriverUpdateEntity(this.getUigMgr(var1).getDrivingPageIndexes(this.mContext, "_432_EQ_info"), this.getUigMgr(var1).getDrivingItemIndexes(this.mContext, "_432_EQ_info3"), String.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 4))));
      var2.add(new DriverUpdateEntity(this.getUigMgr(var1).getDrivingPageIndexes(this.mContext, "_432_EQ_info"), this.getUigMgr(var1).getDrivingItemIndexes(this.mContext, "_432_EQ_info4"), String.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 4))));
      var2.add(new DriverUpdateEntity(this.getUigMgr(var1).getDrivingPageIndexes(this.mContext, "_432_EQ_info"), this.getUigMgr(var1).getDrivingItemIndexes(this.mContext, "_432_EQ_info5"), String.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 4, 4))));
      var2.add(new DriverUpdateEntity(this.getUigMgr(var1).getDrivingPageIndexes(this.mContext, "_432_EQ_info"), this.getUigMgr(var1).getDrivingItemIndexes(this.mContext, "_432_EQ_info6"), String.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 4))));
      var2.add(new DriverUpdateEntity(this.getUigMgr(var1).getDrivingPageIndexes(this.mContext, "_432_EQ_info"), this.getUigMgr(var1).getDrivingItemIndexes(this.mContext, "_432_EQ_info7"), this.getEqData5Bit7Info(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[7]))));
      this.updateGeneralDriveData(var2);
      this.updateDriveDataActivity((Bundle)null);
   }

   private final void set0x70SyncLastStr() {
      this.nowIndex = 1;
      this.contentStr0x70 = String.valueOf(this.getAsciiStr(this.mCanBusByte));
      this.updateSYNC();
   }

   private final void set0x71SyncNextStr() {
      this.nowIndex = 2;
      this.contentStr0x71 = String.valueOf(this.getAsciiStr(this.mCanBusByte));
      this.updateSYNC();
   }

   private final void set0x72SyncTimeInfo() {
      this.nowIndex = 3;
      this.contentStr0x72 = String.valueOf(this.getAsciiStr(this.mCanBusByte));
      this.updateSYNC();
   }

   private final void set0x78SyncState() {
      int[] var2 = this.mCanBusInfoInt;
      int var1 = var2[2];
      if (var1 == 0) {
         this.content0x78WorkingMode = "Working mode: OFF";
      } else if (var1 == 1) {
         this.content0x78WorkingMode = "Working mode: ON";
      } else if (var1 == 2) {
         this.content0x78WorkingMode = "Working mode: MEDIA";
      } else if (var1 == 3) {
         this.content0x78WorkingMode = "Working mode: PHONE";
      }

      if (DataHandleUtils.getBoolBit0(var2[3])) {
         this.icon1 = 2131233453;
      } else {
         this.icon1 = 2131233649;
      }

      if (DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3])) {
         this.icon2 = 2131233549;
      } else {
         this.icon2 = 2131233649;
      }

      if (DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3])) {
         this.icon3 = 2131233615;
      } else {
         this.icon3 = 2131233649;
      }

      if (DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3])) {
         this.icon4 = 2131233519;
      } else {
         this.icon4 = 2131233649;
      }

      if (DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3])) {
         this.icon5 = 2131233584;
      } else {
         this.icon5 = 2131233649;
      }

      if (DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3])) {
         this.icon6 = 2131233633;
      } else {
         this.icon6 = 2131233649;
      }

      if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 4) == 0) {
         this.icon7 = 2131233456;
      } else if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 4) == 1) {
         this.icon7 = 2131233457;
      } else if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 4) == 2) {
         this.icon7 = 2131233458;
      } else if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 4) == 3) {
         this.icon7 = 2131233459;
      } else if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 4) == 4) {
         this.icon7 = 2131233460;
      } else if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 4) == 5) {
         this.icon7 = 2131233460;
      }

      if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 4) == 0) {
         this.icon8 = 2131233618;
      } else if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 4) == 1) {
         this.icon8 = 2131233619;
      } else if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 4) == 2) {
         this.icon8 = 2131233620;
      } else if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 4) == 3) {
         this.icon8 = 2131233454;
      } else if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 4) == 4) {
         this.icon8 = 2131233455;
      } else if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 4) == 5) {
         this.icon8 = 2131233455;
      }

      this.updateSYNC();
   }

   private final void set0x79SyncAudio() {
      if (!this.is0x79DataNoChange()) {
         int var1 = this.mCanBusInfoInt[2];
         if (var1 == 0) {
            this.SoftKey1 = "NONE";
            this.SoftKey2 = "NONE";
            this.SoftKey3 = "NONE";
            this.image1 = 2131233649;
            this.image2 = 2131233649;
            this.image3 = 2131233649;
            this.isSelected1 = false;
            this.isSelected2 = false;
            this.isSelected3 = false;
         } else if (var1 == 1) {
            this.SoftKey1 = "";
            this.SoftKey2 = "";
            this.SoftKey3 = "";
            this.image1 = 2131233584;
            this.image2 = 2131233649;
            this.image3 = 2131233649;
            this.isSelected1 = true;
            this.isSelected2 = false;
            this.isSelected3 = false;
         } else if (var1 == 2) {
            this.SoftKey1 = "";
            this.SoftKey2 = "";
            this.SoftKey3 = "";
            this.image1 = 2131233585;
            this.image2 = 2131233649;
            this.image3 = 2131233649;
            this.isSelected1 = true;
            this.isSelected2 = false;
            this.isSelected3 = false;
         } else if (var1 == 3) {
            this.SoftKey1 = "";
            this.SoftKey2 = "";
            this.SoftKey3 = "";
            this.image1 = 2131233649;
            this.image2 = 2131233597;
            this.image3 = 2131233649;
            this.isSelected1 = false;
            this.isSelected2 = true;
            this.isSelected3 = false;
         } else if (var1 == 4) {
            this.SoftKey1 = "";
            this.SoftKey2 = "";
            this.SoftKey3 = "";
            this.image1 = 2131233649;
            this.image2 = 2131233649;
            this.image3 = 2131233519;
            this.isSelected1 = false;
            this.isSelected2 = false;
            this.isSelected3 = true;
         } else if (var1 == 5) {
            this.SoftKey1 = "";
            this.SoftKey2 = "";
            this.SoftKey3 = "";
            this.image1 = 2131233649;
            this.image2 = 2131233649;
            this.image3 = 2131233579;
            this.isSelected1 = false;
            this.isSelected2 = false;
            this.isSelected3 = true;
         }

         if (!this.isMute) {
            Toast.makeText(this.mContext, (CharSequence)"Auto mute for 3 seconds", 0);
            this.realKeyClick4(this.mContext, 3);
            CountDownTimer var2 = this.countDownTimer;
            if (var2 != null) {
               var2.cancel();
            }

            this.countDownTimer = (new CountDownTimer(this) {
               final MsgMgr this$0;

               {
                  this.this$0 = var1;
               }

               public void onFinish() {
                  MsgMgr var1 = this.this$0;
                  var1.realKeyClick4(var1.getMContext(), 3);
               }

               public void onTick(long var1) {
               }
            }).start();
         }

         this.updateSYNC();
      }
   }

   public void afterServiceNormalSetting(Context var1) {
      super.afterServiceNormalSetting(var1);
      if (var1 != null) {
         this.mContext = var1;
         SelectCanTypeUtil.enableApp(var1, Constant.SyncActivity);
      }

      GeneralSyncData.mSyncTopIconCount = 8;
   }

   public final int blockBit(int var1, int var2) {
      if (var2 != 0) {
         if (var2 != 7) {
            int var3 = var2 + 1;
            int var4 = DataHandleUtils.getIntFromByteWithBit(var1, var3, 7 - var2);
            var1 = DataHandleUtils.getIntFromByteWithBit(var1, 0, var2) & 255 & 255 ^ (var4 & 255) << var3;
         } else {
            var1 = DataHandleUtils.getIntFromByteWithBit(var1, 0, 7);
         }
      } else {
         var1 = (DataHandleUtils.getIntFromByteWithBit(var1, 1, 7) & 255) << 1;
      }

      return var1;
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      if (var1 != null) {
         if (var2 != null) {
            this.mCanBusByte = var2;
            int[] var4 = this.getByteArrayToIntArray(var2);
            Intrinsics.checkNotNullExpressionValue(var4, "getByteArrayToIntArray(canbusInfo)");
            this.mCanBusInfoInt = var4;
            int var3 = var4[1];
            if (var3 != 20) {
               if (var3 != 22) {
                  if (var3 != 43) {
                     if (var3 != 48) {
                        if (var3 != 53) {
                           if (var3 != 64) {
                              if (var3 != 120) {
                                 if (var3 != 121) {
                                    switch (var3) {
                                       case 32:
                                          this.set0x20SwcInfo();
                                          break;
                                       case 33:
                                          this.set0x21AirInfo();
                                          break;
                                       case 34:
                                          this.set0x22RearRadar();
                                          break;
                                       case 35:
                                          this.set0x23FrontRadar();
                                          break;
                                       case 36:
                                          this.set0x24BasicInfo();
                                          break;
                                       case 37:
                                          this.set0x25RadarState();
                                          break;
                                       default:
                                          switch (var3) {
                                             case 39:
                                                this.set0x27Languge();
                                                break;
                                             case 40:
                                                this.set0x28AutoPacking();
                                                break;
                                             case 41:
                                                this.set0x29CarInfo();
                                                break;
                                             default:
                                                switch (var3) {
                                                   case 80:
                                                      this.set0x50SyncInfo();
                                                      break;
                                                   case 81:
                                                      this.set0x51SyncInfo();
                                                      break;
                                                   case 82:
                                                      this.set0x52SyncInfo();
                                                      break;
                                                   case 83:
                                                      this.set0x53SyncInfo();
                                                      break;
                                                   default:
                                                      switch (var3) {
                                                         case 96:
                                                            this.set0x60RadioInfo(var1);
                                                            break;
                                                         case 97:
                                                            this.set0x61RadioInfo(var1);
                                                            break;
                                                         case 98:
                                                            this.set0x62CdInfo(var1);
                                                            break;
                                                         case 99:
                                                            this.set0x63EqInfo(var1);
                                                            break;
                                                         default:
                                                            switch (var3) {
                                                               case 112:
                                                                  this.set0x70SyncLastStr();
                                                                  break;
                                                               case 113:
                                                                  this.set0x71SyncNextStr();
                                                                  break;
                                                               case 114:
                                                                  this.set0x72SyncTimeInfo();
                                                            }
                                                      }
                                                }
                                          }
                                    }
                                 } else {
                                    this.set0x79SyncAudio();
                                 }
                              } else {
                                 this.set0x78SyncState();
                              }
                           } else {
                              this.set0x40SyncVersion();
                           }
                        } else {
                           this.set0x35EspInfo();
                        }
                     } else {
                        this.set0x30VersionInfo();
                     }
                  } else {
                     this.set0x2bSeatInfo();
                  }
               } else {
                  this.set0x16SpeedInfo();
               }
            } else {
               this.set0x14BackLightInfo();
            }
         }

      }
   }

   public void currentVolumeInfoChange(int var1, boolean var2) {
      super.currentVolumeInfoChange(var1, var2);
      this.isMute = var2;
   }

   public final String getAsciiStr(byte[] var1) {
      Intrinsics.checkNotNullParameter(var1, "canbusInfo");
      int var3 = var1.length - 2;
      byte[] var4 = new byte[var3];

      for(int var2 = 0; var2 < var3; ++var2) {
         var4[var2] = var1[var2 + 2];
      }

      return new String(var4, Charsets.UTF_8);
   }

   public final String getContent0x78WorkingMode() {
      return this.content0x78WorkingMode;
   }

   public final String getContentStr0x70() {
      return this.contentStr0x70;
   }

   public final String getContentStr0x71() {
      return this.contentStr0x71;
   }

   public final String getContentStr0x72() {
      return this.contentStr0x72;
   }

   public final CountDownTimer getCountDownTimer() {
      return this.countDownTimer;
   }

   public final CountDownTimer getCountDownTimerAutoPacking() {
      return this.countDownTimerAutoPacking;
   }

   public final DecimalFormat getDf_2Integer() {
      return this.df_2Integer;
   }

   public final DecimalFormat getDf_2float() {
      return this.df_2float;
   }

   public final AlertDialog getDialog() {
      return this.dialog;
   }

   public final int getIcon1() {
      return this.icon1;
   }

   public final int getIcon2() {
      return this.icon2;
   }

   public final int getIcon3() {
      return this.icon3;
   }

   public final int getIcon4() {
      return this.icon4;
   }

   public final int getIcon5() {
      return this.icon5;
   }

   public final int getIcon6() {
      return this.icon6;
   }

   public final int getIcon7() {
      return this.icon7;
   }

   public final int getIcon8() {
      return this.icon8;
   }

   public final int getImage0() {
      return this.image0;
   }

   public final int getImage1() {
      return this.image1;
   }

   public final int getImage2() {
      return this.image2;
   }

   public final int getImage3() {
      return this.image3;
   }

   public final int[] getM0x79Data() {
      return this.m0x79Data;
   }

   public final int[] getMAirData() {
      return this.mAirData;
   }

   public final int[] getMBacklightInfo() {
      return this.mBacklightInfo;
   }

   public final Context getMContext() {
      return this.mContext;
   }

   public final int[] getMFrontRadarData() {
      return this.mFrontRadarData;
   }

   public final int[] getMRearRadarData() {
      return this.mRearRadarData;
   }

   public final byte[] getMTrackData() {
      return this.mTrackData;
   }

   public final int getMsbLsbResult(int var1, int var2) {
      return (var1 & 255) << 8 | var2 & 255;
   }

   public final int getNow0x24Data0() {
      return this.now0x24Data0;
   }

   public final int getNowData6() {
      return this.nowData6;
   }

   public final int getNowIndex() {
      return this.nowIndex;
   }

   public final String getNowSyncTime() {
      return this.nowSyncTime;
   }

   public final String getSoftKey0() {
      return this.SoftKey0;
   }

   public final String getSoftKey1() {
      return this.SoftKey1;
   }

   public final String getSoftKey2() {
      return this.SoftKey2;
   }

   public final String getSoftKey3() {
      return this.SoftKey3;
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      if (var1 != null) {
         this.mContext = var1;
      }

   }

   public final boolean isMute() {
      return this.isMute;
   }

   public final boolean isSelected0() {
      return this.isSelected0;
   }

   public final boolean isSelected1() {
      return this.isSelected1;
   }

   public final boolean isSelected2() {
      return this.isSelected2;
   }

   public final boolean isSelected3() {
      return this.isSelected3;
   }

   public final void setContent0x78WorkingMode(String var1) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.content0x78WorkingMode = var1;
   }

   public final void setContentStr0x70(String var1) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.contentStr0x70 = var1;
   }

   public final void setContentStr0x71(String var1) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.contentStr0x71 = var1;
   }

   public final void setContentStr0x72(String var1) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.contentStr0x72 = var1;
   }

   public final void setCountDownTimer(CountDownTimer var1) {
      this.countDownTimer = var1;
   }

   public final void setCountDownTimerAutoPacking(CountDownTimer var1) {
      this.countDownTimerAutoPacking = var1;
   }

   public final void setDialog(AlertDialog var1) {
      this.dialog = var1;
   }

   public final void setIcon1(int var1) {
      this.icon1 = var1;
   }

   public final void setIcon2(int var1) {
      this.icon2 = var1;
   }

   public final void setIcon3(int var1) {
      this.icon3 = var1;
   }

   public final void setIcon4(int var1) {
      this.icon4 = var1;
   }

   public final void setIcon5(int var1) {
      this.icon5 = var1;
   }

   public final void setIcon6(int var1) {
      this.icon6 = var1;
   }

   public final void setIcon7(int var1) {
      this.icon7 = var1;
   }

   public final void setIcon8(int var1) {
      this.icon8 = var1;
   }

   public final void setImage0(int var1) {
      this.image0 = var1;
   }

   public final void setImage1(int var1) {
      this.image1 = var1;
   }

   public final void setImage2(int var1) {
      this.image2 = var1;
   }

   public final void setImage3(int var1) {
      this.image3 = var1;
   }

   public final void setM0x79Data(int[] var1) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.m0x79Data = var1;
   }

   public final void setMAirData(int[] var1) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.mAirData = var1;
   }

   public final void setMBacklightInfo(int[] var1) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.mBacklightInfo = var1;
   }

   public final void setMContext(Context var1) {
      this.mContext = var1;
   }

   public final void setMFrontRadarData(int[] var1) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.mFrontRadarData = var1;
   }

   public final void setMRearRadarData(int[] var1) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.mRearRadarData = var1;
   }

   public final void setMTrackData(byte[] var1) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.mTrackData = var1;
   }

   public final void setMute(boolean var1) {
      this.isMute = var1;
   }

   public final void setNow0x24Data0(int var1) {
      this.now0x24Data0 = var1;
   }

   public final void setNowData6(int var1) {
      this.nowData6 = var1;
   }

   public final void setNowIndex(int var1) {
      this.nowIndex = var1;
   }

   public final void setNowSyncTime(String var1) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.nowSyncTime = var1;
   }

   public final void setSelected0(boolean var1) {
      this.isSelected0 = var1;
   }

   public final void setSelected1(boolean var1) {
      this.isSelected1 = var1;
   }

   public final void setSelected2(boolean var1) {
      this.isSelected2 = var1;
   }

   public final void setSelected3(boolean var1) {
      this.isSelected3 = var1;
   }

   public final void setSoftKey0(String var1) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.SoftKey0 = var1;
   }

   public final void setSoftKey1(String var1) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.SoftKey1 = var1;
   }

   public final void setSoftKey2(String var1) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.SoftKey2 = var1;
   }

   public final void setSoftKey3(String var1) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.SoftKey3 = var1;
   }

   public final void updateSYNC() {
      GeneralSyncData.mIsShowSoftKey = true;
      GeneralSyncData.mIsSyncTimeChange = true;
      GeneralSyncData.mInfoLineShowAmount = 5;
      GeneralSyncData.mSelectedLineIndex = this.nowIndex;
      List var1 = GeneralSyncData.mInfoUpdateEntityList;
      SyncListUpdateEntity var2 = new SyncListUpdateEntity(0);
      var2.setLeftIconResId(2131233582);
      var2.setRightIconResId(2131233649);
      var2.setSelected(true);
      var2.setInfo(this.content0x78WorkingMode);
      var2.setEnable(false);
      var1.add(var2);
      var2 = new SyncListUpdateEntity(1);
      var2.setLeftIconResId(2131233587);
      var2.setRightIconResId(2131233649);
      var2.setSelected(true);
      var2.setInfo(this.contentStr0x70);
      var2.setEnable(false);
      var1.add(var2);
      var2 = new SyncListUpdateEntity(2);
      var2.setLeftIconResId(2131233587);
      var2.setRightIconResId(2131233649);
      var2.setSelected(true);
      var2.setInfo(this.contentStr0x71);
      var2.setEnable(false);
      var1.add(var2);
      var2 = new SyncListUpdateEntity(3);
      var2.setLeftIconResId(2131233587);
      var2.setRightIconResId(2131233649);
      var2.setSelected(true);
      var2.setInfo(this.contentStr0x72);
      var2.setEnable(false);
      var1.add(var2);
      GeneralSyncData.mSyncTopIconResIdArray = new int[]{this.icon1, this.icon2, this.icon3, this.icon4, this.icon5, this.icon6, this.icon7, this.icon8};
      List var4 = GeneralSyncData.mSoftKeyUpdateEntityList;
      SyncSoftKeyUpdateEntity var3 = new SyncSoftKeyUpdateEntity(0);
      var3.setName(this.SoftKey0);
      var3.setImageResId(this.image0);
      var3.setSelected(this.isSelected0);
      var3.setVisible(true);
      var4.add(var3);
      var1 = GeneralSyncData.mSoftKeyUpdateEntityList;
      SyncSoftKeyUpdateEntity var5 = new SyncSoftKeyUpdateEntity(1);
      var5.setName(this.SoftKey1);
      var5.setImageResId(this.image1);
      var5.setSelected(this.isSelected1);
      var5.setVisible(true);
      var1.add(var5);
      var4 = GeneralSyncData.mSoftKeyUpdateEntityList;
      var3 = new SyncSoftKeyUpdateEntity(2);
      var3.setName(this.SoftKey2);
      var3.setImageResId(this.image2);
      var3.setSelected(this.isSelected2);
      var3.setVisible(true);
      var4.add(var3);
      var4 = GeneralSyncData.mSoftKeyUpdateEntityList;
      var3 = new SyncSoftKeyUpdateEntity(3);
      var3.setName(this.SoftKey3);
      var3.setImageResId(this.image3);
      var3.setSelected(this.isSelected3);
      var3.setVisible(true);
      var4.add(var3);
      GeneralSyncData.mSyncTime = this.nowSyncTime;
      this.updateSyncActivity((Bundle)null);
   }
}
