package kotlin.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;

@Metadata(
   d1 = {"\u0000\u0082\u0001\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010&\n\u0002\b\u0002\n\u0002\u0010\u001c\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\u001f\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\u000f\n\u0002\b\u0005\n\u0002\u0010\u0006\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\u001aJ\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u001e\u0010\u0005\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u00020\u00010\u0006H\u0086\bø\u0001\u0000\u001a$\u0010\b\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0004\u001aJ\u0010\b\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u001e\u0010\u0005\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u00020\u00010\u0006H\u0086\bø\u0001\u0000\u001a9\u0010\t\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00070\n\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0004H\u0087\b\u001a6\u0010\u000b\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00070\f\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0004\u001a'\u0010\r\u001a\u00020\u000e\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0004H\u0087\b\u001aJ\u0010\r\u001a\u00020\u000e\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u001e\u0010\u0005\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u00020\u00010\u0006H\u0086\bø\u0001\u0000\u001a[\u0010\u000f\u001a\u0002H\u0010\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\b\b\u0002\u0010\u0010*\u00020\u0011*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042 \u0010\u0012\u001a\u001c\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0006\u0012\u0004\u0018\u0001H\u00100\u0006H\u0087\bø\u0001\u0000¢\u0006\u0002\u0010\u0013\u001a]\u0010\u0014\u001a\u0004\u0018\u0001H\u0010\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\b\b\u0002\u0010\u0010*\u00020\u0011*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042 \u0010\u0012\u001a\u001c\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0006\u0012\u0004\u0018\u0001H\u00100\u0006H\u0087\bø\u0001\u0000¢\u0006\u0002\u0010\u0013\u001a\\\u0010\u0015\u001a\b\u0012\u0004\u0012\u0002H\u00100\u0016\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0004\b\u0002\u0010\u0010*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042$\u0010\u0012\u001a \u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00100\n0\u0006H\u0086\bø\u0001\u0000\u001aa\u0010\u0015\u001a\b\u0012\u0004\u0012\u0002H\u00100\u0016\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0004\b\u0002\u0010\u0010*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042$\u0010\u0012\u001a \u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00100\f0\u0006H\u0087\bø\u0001\u0000¢\u0006\u0002\b\u0017\u001au\u0010\u0018\u001a\u0002H\u0019\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0004\b\u0002\u0010\u0010\"\u0010\b\u0003\u0010\u0019*\n\u0012\u0006\b\u0000\u0012\u0002H\u00100\u001a*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u0006\u0010\u001b\u001a\u0002H\u00192$\u0010\u0012\u001a \u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00100\n0\u0006H\u0086\bø\u0001\u0000¢\u0006\u0002\u0010\u001c\u001aw\u0010\u0018\u001a\u0002H\u0019\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0004\b\u0002\u0010\u0010\"\u0010\b\u0003\u0010\u0019*\n\u0012\u0006\b\u0000\u0012\u0002H\u00100\u001a*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u0006\u0010\u001b\u001a\u0002H\u00192$\u0010\u0012\u001a \u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00100\f0\u0006H\u0087\bø\u0001\u0000¢\u0006\u0004\b\u001d\u0010\u001c\u001aJ\u0010\u001e\u001a\u00020\u001f\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u001e\u0010 \u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u00020\u001f0\u0006H\u0087\bø\u0001\u0000\u001aV\u0010!\u001a\b\u0012\u0004\u0012\u0002H\u00100\u0016\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0004\b\u0002\u0010\u0010*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u001e\u0010\u0012\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u0002H\u00100\u0006H\u0086\bø\u0001\u0000\u001a\\\u0010\"\u001a\b\u0012\u0004\u0012\u0002H\u00100\u0016\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\b\b\u0002\u0010\u0010*\u00020\u0011*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042 \u0010\u0012\u001a\u001c\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0006\u0012\u0004\u0018\u0001H\u00100\u0006H\u0086\bø\u0001\u0000\u001au\u0010#\u001a\u0002H\u0019\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\b\b\u0002\u0010\u0010*\u00020\u0011\"\u0010\b\u0003\u0010\u0019*\n\u0012\u0006\b\u0000\u0012\u0002H\u00100\u001a*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u0006\u0010\u001b\u001a\u0002H\u00192 \u0010\u0012\u001a\u001c\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0006\u0012\u0004\u0018\u0001H\u00100\u0006H\u0086\bø\u0001\u0000¢\u0006\u0002\u0010\u001c\u001ao\u0010$\u001a\u0002H\u0019\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0004\b\u0002\u0010\u0010\"\u0010\b\u0003\u0010\u0019*\n\u0012\u0006\b\u0000\u0012\u0002H\u00100\u001a*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u0006\u0010\u001b\u001a\u0002H\u00192\u001e\u0010\u0012\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u0002H\u00100\u0006H\u0086\bø\u0001\u0000¢\u0006\u0002\u0010\u001c\u001ak\u0010%\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u000e\b\u0002\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100&*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u001e\u0010'\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u0002H\u00100\u0006H\u0087\bø\u0001\u0000¢\u0006\u0002\b(\u001ah\u0010)\u001a\u0010\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u0003\u0018\u00010\u0007\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u000e\b\u0002\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100&*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u001e\u0010'\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u0002H\u00100\u0006H\u0087\bø\u0001\u0000\u001a_\u0010*\u001a\u0002H\u0010\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u000e\b\u0002\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100&*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u001e\u0010'\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u0002H\u00100\u0006H\u0087\bø\u0001\u0000¢\u0006\u0002\u0010+\u001aJ\u0010*\u001a\u00020,\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u001e\u0010'\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u00020,0\u0006H\u0087\bø\u0001\u0000\u001aJ\u0010*\u001a\u00020-\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u001e\u0010'\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u00020-0\u0006H\u0087\bø\u0001\u0000\u001aa\u0010.\u001a\u0004\u0018\u0001H\u0010\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u000e\b\u0002\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100&*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u001e\u0010'\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u0002H\u00100\u0006H\u0087\bø\u0001\u0000¢\u0006\u0002\u0010+\u001aQ\u0010.\u001a\u0004\u0018\u00010,\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u001e\u0010'\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u00020,0\u0006H\u0087\bø\u0001\u0000¢\u0006\u0002\u0010/\u001aQ\u0010.\u001a\u0004\u0018\u00010-\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u001e\u0010'\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u00020-0\u0006H\u0087\bø\u0001\u0000¢\u0006\u0002\u00100\u001aq\u00101\u001a\u0002H\u0010\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0004\b\u0002\u0010\u0010*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u001a\u00102\u001a\u0016\u0012\u0006\b\u0000\u0012\u0002H\u001003j\n\u0012\u0006\b\u0000\u0012\u0002H\u0010`42\u001e\u0010'\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u0002H\u00100\u0006H\u0087\bø\u0001\u0000¢\u0006\u0002\u00105\u001as\u00106\u001a\u0004\u0018\u0001H\u0010\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0004\b\u0002\u0010\u0010*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u001a\u00102\u001a\u0016\u0012\u0006\b\u0000\u0012\u0002H\u001003j\n\u0012\u0006\b\u0000\u0012\u0002H\u0010`42\u001e\u0010'\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u0002H\u00100\u0006H\u0087\bø\u0001\u0000¢\u0006\u0002\u00105\u001al\u00107\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u000422\u00102\u001a.\u0012\u0012\b\u0000\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u000703j\u0016\u0012\u0012\b\u0000\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007`4H\u0087\b¢\u0006\u0002\b8\u001ai\u00109\u001a\u0010\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u0003\u0018\u00010\u0007\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u000422\u00102\u001a.\u0012\u0012\b\u0000\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u000703j\u0016\u0012\u0012\b\u0000\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007`4H\u0087\b\u001ak\u0010:\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u000e\b\u0002\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100&*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u001e\u0010'\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u0002H\u00100\u0006H\u0087\bø\u0001\u0000¢\u0006\u0002\b;\u001ah\u0010<\u001a\u0010\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u0003\u0018\u00010\u0007\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u000e\b\u0002\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100&*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u001e\u0010'\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u0002H\u00100\u0006H\u0087\bø\u0001\u0000\u001a_\u0010=\u001a\u0002H\u0010\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u000e\b\u0002\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100&*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u001e\u0010'\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u0002H\u00100\u0006H\u0087\bø\u0001\u0000¢\u0006\u0002\u0010+\u001aJ\u0010=\u001a\u00020,\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u001e\u0010'\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u00020,0\u0006H\u0087\bø\u0001\u0000\u001aJ\u0010=\u001a\u00020-\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u001e\u0010'\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u00020-0\u0006H\u0087\bø\u0001\u0000\u001aa\u0010>\u001a\u0004\u0018\u0001H\u0010\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u000e\b\u0002\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100&*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u001e\u0010'\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u0002H\u00100\u0006H\u0087\bø\u0001\u0000¢\u0006\u0002\u0010+\u001aQ\u0010>\u001a\u0004\u0018\u00010,\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u001e\u0010'\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u00020,0\u0006H\u0087\bø\u0001\u0000¢\u0006\u0002\u0010/\u001aQ\u0010>\u001a\u0004\u0018\u00010-\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u001e\u0010'\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u00020-0\u0006H\u0087\bø\u0001\u0000¢\u0006\u0002\u00100\u001aq\u0010?\u001a\u0002H\u0010\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0004\b\u0002\u0010\u0010*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u001a\u00102\u001a\u0016\u0012\u0006\b\u0000\u0012\u0002H\u001003j\n\u0012\u0006\b\u0000\u0012\u0002H\u0010`42\u001e\u0010'\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u0002H\u00100\u0006H\u0087\bø\u0001\u0000¢\u0006\u0002\u00105\u001as\u0010@\u001a\u0004\u0018\u0001H\u0010\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0004\b\u0002\u0010\u0010*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u001a\u00102\u001a\u0016\u0012\u0006\b\u0000\u0012\u0002H\u001003j\n\u0012\u0006\b\u0000\u0012\u0002H\u0010`42\u001e\u0010'\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u0002H\u00100\u0006H\u0087\bø\u0001\u0000¢\u0006\u0002\u00105\u001al\u0010A\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u000422\u00102\u001a.\u0012\u0012\b\u0000\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u000703j\u0016\u0012\u0012\b\u0000\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007`4H\u0087\b¢\u0006\u0002\bB\u001ai\u0010C\u001a\u0010\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u0003\u0018\u00010\u0007\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u000422\u00102\u001a.\u0012\u0012\b\u0000\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u000703j\u0016\u0012\u0012\b\u0000\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007`4H\u0087\b\u001a$\u0010D\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0004\u001aJ\u0010D\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u001e\u0010\u0005\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u00020\u00010\u0006H\u0086\bø\u0001\u0000\u001aY\u0010E\u001a\u0002HF\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0016\b\u0002\u0010F*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0004*\u0002HF2\u001e\u0010 \u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u00020\u001f0\u0006H\u0087\bø\u0001\u0000¢\u0006\u0002\u0010G\u001an\u0010H\u001a\u0002HF\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0016\b\u0002\u0010F*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0004*\u0002HF23\u0010 \u001a/\u0012\u0013\u0012\u00110\u000e¢\u0006\f\bJ\u0012\b\bK\u0012\u0004\b\b(L\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u00020\u001f0IH\u0087\bø\u0001\u0000¢\u0006\u0002\u0010M\u001a6\u0010N\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030O0\u0016\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0004\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006P"},
   d2 = {"all", "", "K", "V", "", "predicate", "Lkotlin/Function1;", "", "any", "asIterable", "", "asSequence", "Lkotlin/sequences/Sequence;", "count", "", "firstNotNullOf", "R", "", "transform", "(Ljava/util/Map;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "firstNotNullOfOrNull", "flatMap", "", "flatMapSequence", "flatMapTo", "C", "", "destination", "(Ljava/util/Map;Ljava/util/Collection;Lkotlin/jvm/functions/Function1;)Ljava/util/Collection;", "flatMapSequenceTo", "forEach", "", "action", "map", "mapNotNull", "mapNotNullTo", "mapTo", "maxBy", "", "selector", "maxByOrThrow", "maxByOrNull", "maxOf", "(Ljava/util/Map;Lkotlin/jvm/functions/Function1;)Ljava/lang/Comparable;", "", "", "maxOfOrNull", "(Ljava/util/Map;Lkotlin/jvm/functions/Function1;)Ljava/lang/Double;", "(Ljava/util/Map;Lkotlin/jvm/functions/Function1;)Ljava/lang/Float;", "maxOfWith", "comparator", "Ljava/util/Comparator;", "Lkotlin/Comparator;", "(Ljava/util/Map;Ljava/util/Comparator;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "maxOfWithOrNull", "maxWith", "maxWithOrThrow", "maxWithOrNull", "minBy", "minByOrThrow", "minByOrNull", "minOf", "minOfOrNull", "minOfWith", "minOfWithOrNull", "minWith", "minWithOrThrow", "minWithOrNull", "none", "onEach", "M", "(Ljava/util/Map;Lkotlin/jvm/functions/Function1;)Ljava/util/Map;", "onEachIndexed", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "index", "(Ljava/util/Map;Lkotlin/jvm/functions/Function2;)Ljava/util/Map;", "toList", "Lkotlin/Pair;", "kotlin-stdlib"},
   k = 5,
   mv = {1, 7, 1},
   xi = 49,
   xs = "kotlin/collections/MapsKt"
)
class MapsKt___MapsKt extends MapsKt___MapsJvmKt {
   public MapsKt___MapsKt() {
   }

   public static final boolean all(Map var0, Function1 var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "predicate");
      if (var0.isEmpty()) {
         return true;
      } else {
         Iterator var2 = var0.entrySet().iterator();

         do {
            if (!var2.hasNext()) {
               return true;
            }
         } while((Boolean)var1.invoke((Map.Entry)var2.next()));

         return false;
      }
   }

   public static final boolean any(Map var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      return var0.isEmpty() ^ true;
   }

   public static final boolean any(Map var0, Function1 var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "predicate");
      if (var0.isEmpty()) {
         return false;
      } else {
         Iterator var2 = var0.entrySet().iterator();

         do {
            if (!var2.hasNext()) {
               return false;
            }
         } while(!(Boolean)var1.invoke((Map.Entry)var2.next()));

         return true;
      }
   }

   private static final Iterable asIterable(Map var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      return (Iterable)var0.entrySet();
   }

   public static final Sequence asSequence(Map var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      return CollectionsKt.asSequence((Iterable)var0.entrySet());
   }

   private static final int count(Map var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      return var0.size();
   }

   public static final int count(Map var0, Function1 var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "predicate");
      boolean var3 = var0.isEmpty();
      int var2 = 0;
      if (var3) {
         return 0;
      } else {
         Iterator var4 = var0.entrySet().iterator();

         while(var4.hasNext()) {
            if ((Boolean)var1.invoke((Map.Entry)var4.next())) {
               ++var2;
            }
         }

         return var2;
      }
   }

   private static final Object firstNotNullOf(Map var0, Function1 var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "transform");
      Iterator var3 = var0.entrySet().iterator();

      Object var4;
      while(true) {
         if (var3.hasNext()) {
            Object var2 = var1.invoke((Map.Entry)var3.next());
            var4 = var2;
            if (var2 == null) {
               continue;
            }
            break;
         }

         var4 = null;
         break;
      }

      if (var4 != null) {
         return var4;
      } else {
         throw new NoSuchElementException("No element of the map was transformed to a non-null value.");
      }
   }

   private static final Object firstNotNullOfOrNull(Map var0, Function1 var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "transform");
      Iterator var3 = var0.entrySet().iterator();

      Object var2;
      do {
         if (!var3.hasNext()) {
            return null;
         }

         var2 = var1.invoke((Map.Entry)var3.next());
      } while(var2 == null);

      return var2;
   }

   public static final List flatMap(Map var0, Function1 var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "transform");
      Collection var2 = (Collection)(new ArrayList());
      Iterator var3 = var0.entrySet().iterator();

      while(var3.hasNext()) {
         CollectionsKt.addAll(var2, (Iterable)var1.invoke((Map.Entry)var3.next()));
      }

      return (List)var2;
   }

   public static final List flatMapSequence(Map var0, Function1 var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "transform");
      Collection var2 = (Collection)(new ArrayList());
      Iterator var3 = var0.entrySet().iterator();

      while(var3.hasNext()) {
         CollectionsKt.addAll(var2, (Sequence)var1.invoke((Map.Entry)var3.next()));
      }

      return (List)var2;
   }

   public static final Collection flatMapSequenceTo(Map var0, Collection var1, Function1 var2) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "destination");
      Intrinsics.checkNotNullParameter(var2, "transform");
      Iterator var3 = var0.entrySet().iterator();

      while(var3.hasNext()) {
         CollectionsKt.addAll(var1, (Sequence)var2.invoke((Map.Entry)var3.next()));
      }

      return var1;
   }

   public static final Collection flatMapTo(Map var0, Collection var1, Function1 var2) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "destination");
      Intrinsics.checkNotNullParameter(var2, "transform");
      Iterator var3 = var0.entrySet().iterator();

      while(var3.hasNext()) {
         CollectionsKt.addAll(var1, (Iterable)var2.invoke((Map.Entry)var3.next()));
      }

      return var1;
   }

   public static final void forEach(Map var0, Function1 var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "action");
      Iterator var2 = var0.entrySet().iterator();

      while(var2.hasNext()) {
         var1.invoke((Map.Entry)var2.next());
      }

   }

   public static final List map(Map var0, Function1 var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "transform");
      Collection var2 = (Collection)(new ArrayList(var0.size()));
      Iterator var3 = var0.entrySet().iterator();

      while(var3.hasNext()) {
         var2.add(var1.invoke((Map.Entry)var3.next()));
      }

      return (List)var2;
   }

   public static final List mapNotNull(Map var0, Function1 var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "transform");
      Collection var2 = (Collection)(new ArrayList());
      Iterator var3 = var0.entrySet().iterator();

      while(var3.hasNext()) {
         Object var4 = var1.invoke((Map.Entry)var3.next());
         if (var4 != null) {
            var2.add(var4);
         }
      }

      return (List)var2;
   }

   public static final Collection mapNotNullTo(Map var0, Collection var1, Function1 var2) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "destination");
      Intrinsics.checkNotNullParameter(var2, "transform");
      Iterator var3 = var0.entrySet().iterator();

      while(var3.hasNext()) {
         Object var4 = var2.invoke((Map.Entry)var3.next());
         if (var4 != null) {
            var1.add(var4);
         }
      }

      return var1;
   }

   public static final Collection mapTo(Map var0, Collection var1, Function1 var2) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "destination");
      Intrinsics.checkNotNullParameter(var2, "transform");
      Iterator var3 = var0.entrySet().iterator();

      while(var3.hasNext()) {
         var1.add(var2.invoke((Map.Entry)var3.next()));
      }

      return var1;
   }

   private static final Map.Entry maxByOrNull(Map var0, Function1 var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "selector");
      Iterator var7 = ((Iterable)var0.entrySet()).iterator();
      Object var8;
      if (!var7.hasNext()) {
         var8 = null;
      } else {
         var8 = var7.next();
         if (var7.hasNext()) {
            Comparable var2 = (Comparable)var1.invoke(var8);
            Object var4 = var8;

            do {
               Object var6 = var7.next();
               Comparable var5 = (Comparable)var1.invoke(var6);
               var8 = var4;
               Comparable var3 = var2;
               if (var2.compareTo(var5) < 0) {
                  var8 = var6;
                  var3 = var5;
               }

               var4 = var8;
               var2 = var3;
            } while(var7.hasNext());
         }
      }

      return (Map.Entry)var8;
   }

   private static final Map.Entry maxByOrThrow(Map var0, Function1 var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "selector");
      Iterator var7 = ((Iterable)var0.entrySet()).iterator();
      if (!var7.hasNext()) {
         throw new NoSuchElementException();
      } else {
         Object var4 = var7.next();
         Object var8;
         if (!var7.hasNext()) {
            var8 = var4;
         } else {
            Comparable var2 = (Comparable)var1.invoke(var4);

            do {
               Object var6 = var7.next();
               Comparable var5 = (Comparable)var1.invoke(var6);
               var8 = var4;
               Comparable var3 = var2;
               if (var2.compareTo(var5) < 0) {
                  var8 = var6;
                  var3 = var5;
               }

               var4 = var8;
               var2 = var3;
            } while(var7.hasNext());
         }

         return (Map.Entry)var8;
      }
   }

   private static final double maxOf(Map var0, Function1 var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "selector");
      Iterator var4 = ((Iterable)var0.entrySet()).iterator();
      if (!var4.hasNext()) {
         throw new NoSuchElementException();
      } else {
         double var2;
         for(var2 = ((Number)var1.invoke(var4.next())).doubleValue(); var4.hasNext(); var2 = Math.max(var2, ((Number)var1.invoke(var4.next())).doubleValue())) {
         }

         return var2;
      }
   }

   private static final float maxOf(Map var0, Function1 var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "selector");
      Iterator var3 = ((Iterable)var0.entrySet()).iterator();
      if (!var3.hasNext()) {
         throw new NoSuchElementException();
      } else {
         float var2;
         for(var2 = ((Number)var1.invoke(var3.next())).floatValue(); var3.hasNext(); var2 = Math.max(var2, ((Number)var1.invoke(var3.next())).floatValue())) {
         }

         return var2;
      }
   }

   private static final Comparable maxOf(Map var0, Function1 var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "selector");
      Iterator var3 = ((Iterable)var0.entrySet()).iterator();
      if (var3.hasNext()) {
         Comparable var4 = (Comparable)var1.invoke(var3.next());

         while(var3.hasNext()) {
            Comparable var2 = (Comparable)var1.invoke(var3.next());
            if (var4.compareTo(var2) < 0) {
               var4 = var2;
            }
         }

         return var4;
      } else {
         throw new NoSuchElementException();
      }
   }

   private static final Comparable maxOfOrNull(Map var0, Function1 var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "selector");
      Iterator var3 = ((Iterable)var0.entrySet()).iterator();
      Comparable var4;
      if (!var3.hasNext()) {
         var4 = null;
      } else {
         var4 = (Comparable)var1.invoke(var3.next());

         while(var3.hasNext()) {
            Comparable var2 = (Comparable)var1.invoke(var3.next());
            if (var4.compareTo(var2) < 0) {
               var4 = var2;
            }
         }
      }

      return var4;
   }

   private static final Double maxOfOrNull(Map var0, Function1 var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "selector");
      Iterator var4 = ((Iterable)var0.entrySet()).iterator();
      Double var5;
      if (!var4.hasNext()) {
         var5 = null;
      } else {
         double var2;
         for(var2 = ((Number)var1.invoke(var4.next())).doubleValue(); var4.hasNext(); var2 = Math.max(var2, ((Number)var1.invoke(var4.next())).doubleValue())) {
         }

         var5 = var2;
      }

      return var5;
   }

   private static final Float maxOfOrNull(Map var0, Function1 var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "selector");
      Iterator var3 = ((Iterable)var0.entrySet()).iterator();
      Float var4;
      if (!var3.hasNext()) {
         var4 = null;
      } else {
         float var2;
         for(var2 = ((Number)var1.invoke(var3.next())).floatValue(); var3.hasNext(); var2 = Math.max(var2, ((Number)var1.invoke(var3.next())).floatValue())) {
         }

         var4 = var2;
      }

      return var4;
   }

   private static final Object maxOfWith(Map var0, Comparator var1, Function1 var2) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "comparator");
      Intrinsics.checkNotNullParameter(var2, "selector");
      Iterator var4 = ((Iterable)var0.entrySet()).iterator();
      if (var4.hasNext()) {
         Object var5 = var2.invoke(var4.next());

         while(var4.hasNext()) {
            Object var3 = var2.invoke(var4.next());
            if (var1.compare(var5, var3) < 0) {
               var5 = var3;
            }
         }

         return var5;
      } else {
         throw new NoSuchElementException();
      }
   }

   private static final Object maxOfWithOrNull(Map var0, Comparator var1, Function1 var2) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "comparator");
      Intrinsics.checkNotNullParameter(var2, "selector");
      Iterator var4 = ((Iterable)var0.entrySet()).iterator();
      Object var5;
      if (!var4.hasNext()) {
         var5 = null;
      } else {
         var5 = var2.invoke(var4.next());

         while(var4.hasNext()) {
            Object var3 = var2.invoke(var4.next());
            if (var1.compare(var5, var3) < 0) {
               var5 = var3;
            }
         }
      }

      return var5;
   }

   private static final Map.Entry maxWithOrNull(Map var0, Comparator var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "comparator");
      return (Map.Entry)CollectionsKt.maxWithOrNull((Iterable)var0.entrySet(), var1);
   }

   private static final Map.Entry maxWithOrThrow(Map var0, Comparator var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "comparator");
      return (Map.Entry)CollectionsKt.maxWithOrThrow((Iterable)var0.entrySet(), var1);
   }

   private static final Map.Entry minByOrNull(Map var0, Function1 var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "selector");
      Iterator var7 = ((Iterable)var0.entrySet()).iterator();
      Object var8;
      if (!var7.hasNext()) {
         var8 = null;
      } else {
         var8 = var7.next();
         if (var7.hasNext()) {
            Comparable var2 = (Comparable)var1.invoke(var8);
            Object var4 = var8;

            do {
               Object var6 = var7.next();
               Comparable var5 = (Comparable)var1.invoke(var6);
               var8 = var4;
               Comparable var3 = var2;
               if (var2.compareTo(var5) > 0) {
                  var8 = var6;
                  var3 = var5;
               }

               var4 = var8;
               var2 = var3;
            } while(var7.hasNext());
         }
      }

      return (Map.Entry)var8;
   }

   private static final Map.Entry minByOrThrow(Map var0, Function1 var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "selector");
      Iterator var7 = ((Iterable)var0.entrySet()).iterator();
      if (!var7.hasNext()) {
         throw new NoSuchElementException();
      } else {
         Object var4 = var7.next();
         Object var8;
         if (!var7.hasNext()) {
            var8 = var4;
         } else {
            Comparable var3 = (Comparable)var1.invoke(var4);

            do {
               Object var6 = var7.next();
               Comparable var5 = (Comparable)var1.invoke(var6);
               var8 = var4;
               Comparable var2 = var3;
               if (var3.compareTo(var5) > 0) {
                  var8 = var6;
                  var2 = var5;
               }

               var4 = var8;
               var3 = var2;
            } while(var7.hasNext());
         }

         return (Map.Entry)var8;
      }
   }

   private static final double minOf(Map var0, Function1 var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "selector");
      Iterator var4 = ((Iterable)var0.entrySet()).iterator();
      if (!var4.hasNext()) {
         throw new NoSuchElementException();
      } else {
         double var2;
         for(var2 = ((Number)var1.invoke(var4.next())).doubleValue(); var4.hasNext(); var2 = Math.min(var2, ((Number)var1.invoke(var4.next())).doubleValue())) {
         }

         return var2;
      }
   }

   private static final float minOf(Map var0, Function1 var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "selector");
      Iterator var3 = ((Iterable)var0.entrySet()).iterator();
      if (!var3.hasNext()) {
         throw new NoSuchElementException();
      } else {
         float var2;
         for(var2 = ((Number)var1.invoke(var3.next())).floatValue(); var3.hasNext(); var2 = Math.min(var2, ((Number)var1.invoke(var3.next())).floatValue())) {
         }

         return var2;
      }
   }

   private static final Comparable minOf(Map var0, Function1 var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "selector");
      Iterator var3 = ((Iterable)var0.entrySet()).iterator();
      if (var3.hasNext()) {
         Comparable var4 = (Comparable)var1.invoke(var3.next());

         while(var3.hasNext()) {
            Comparable var2 = (Comparable)var1.invoke(var3.next());
            if (var4.compareTo(var2) > 0) {
               var4 = var2;
            }
         }

         return var4;
      } else {
         throw new NoSuchElementException();
      }
   }

   private static final Comparable minOfOrNull(Map var0, Function1 var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "selector");
      Iterator var3 = ((Iterable)var0.entrySet()).iterator();
      Comparable var4;
      if (!var3.hasNext()) {
         var4 = null;
      } else {
         var4 = (Comparable)var1.invoke(var3.next());

         while(var3.hasNext()) {
            Comparable var2 = (Comparable)var1.invoke(var3.next());
            if (var4.compareTo(var2) > 0) {
               var4 = var2;
            }
         }
      }

      return var4;
   }

   private static final Double minOfOrNull(Map var0, Function1 var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "selector");
      Iterator var4 = ((Iterable)var0.entrySet()).iterator();
      Double var5;
      if (!var4.hasNext()) {
         var5 = null;
      } else {
         double var2;
         for(var2 = ((Number)var1.invoke(var4.next())).doubleValue(); var4.hasNext(); var2 = Math.min(var2, ((Number)var1.invoke(var4.next())).doubleValue())) {
         }

         var5 = var2;
      }

      return var5;
   }

   private static final Float minOfOrNull(Map var0, Function1 var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "selector");
      Iterator var3 = ((Iterable)var0.entrySet()).iterator();
      Float var4;
      if (!var3.hasNext()) {
         var4 = null;
      } else {
         float var2;
         for(var2 = ((Number)var1.invoke(var3.next())).floatValue(); var3.hasNext(); var2 = Math.min(var2, ((Number)var1.invoke(var3.next())).floatValue())) {
         }

         var4 = var2;
      }

      return var4;
   }

   private static final Object minOfWith(Map var0, Comparator var1, Function1 var2) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "comparator");
      Intrinsics.checkNotNullParameter(var2, "selector");
      Iterator var4 = ((Iterable)var0.entrySet()).iterator();
      if (var4.hasNext()) {
         Object var5 = var2.invoke(var4.next());

         while(var4.hasNext()) {
            Object var3 = var2.invoke(var4.next());
            if (var1.compare(var5, var3) > 0) {
               var5 = var3;
            }
         }

         return var5;
      } else {
         throw new NoSuchElementException();
      }
   }

   private static final Object minOfWithOrNull(Map var0, Comparator var1, Function1 var2) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "comparator");
      Intrinsics.checkNotNullParameter(var2, "selector");
      Iterator var4 = ((Iterable)var0.entrySet()).iterator();
      Object var5;
      if (!var4.hasNext()) {
         var5 = null;
      } else {
         var5 = var2.invoke(var4.next());

         while(var4.hasNext()) {
            Object var3 = var2.invoke(var4.next());
            if (var1.compare(var5, var3) > 0) {
               var5 = var3;
            }
         }
      }

      return var5;
   }

   private static final Map.Entry minWithOrNull(Map var0, Comparator var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "comparator");
      return (Map.Entry)CollectionsKt.minWithOrNull((Iterable)var0.entrySet(), var1);
   }

   private static final Map.Entry minWithOrThrow(Map var0, Comparator var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "comparator");
      return (Map.Entry)CollectionsKt.minWithOrThrow((Iterable)var0.entrySet(), var1);
   }

   public static final boolean none(Map var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      return var0.isEmpty();
   }

   public static final boolean none(Map var0, Function1 var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "predicate");
      if (var0.isEmpty()) {
         return true;
      } else {
         Iterator var2 = var0.entrySet().iterator();

         do {
            if (!var2.hasNext()) {
               return true;
            }
         } while(!(Boolean)var1.invoke((Map.Entry)var2.next()));

         return false;
      }
   }

   public static final Map onEach(Map var0, Function1 var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "action");
      Iterator var2 = var0.entrySet().iterator();

      while(var2.hasNext()) {
         var1.invoke((Map.Entry)var2.next());
      }

      return var0;
   }

   public static final Map onEachIndexed(Map var0, Function2 var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "action");
      Iterator var3 = ((Iterable)var0.entrySet()).iterator();

      for(int var2 = 0; var3.hasNext(); ++var2) {
         Object var4 = var3.next();
         if (var2 < 0) {
            CollectionsKt.throwIndexOverflow();
         }

         var1.invoke(var2, var4);
      }

      return var0;
   }

   public static final List toList(Map var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      if (var0.size() == 0) {
         return CollectionsKt.emptyList();
      } else {
         Iterator var1 = var0.entrySet().iterator();
         if (!var1.hasNext()) {
            return CollectionsKt.emptyList();
         } else {
            Map.Entry var2 = (Map.Entry)var1.next();
            if (!var1.hasNext()) {
               return CollectionsKt.listOf(new Pair(var2.getKey(), var2.getValue()));
            } else {
               ArrayList var3 = new ArrayList(var0.size());
               var3.add(new Pair(var2.getKey(), var2.getValue()));

               do {
                  var2 = (Map.Entry)var1.next();
                  var3.add(new Pair(var2.getKey(), var2.getValue()));
               } while(var1.hasNext());

               return (List)var3;
            }
         }
      }
   }
}
