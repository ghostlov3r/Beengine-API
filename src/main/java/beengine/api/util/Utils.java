package beengine.api.util;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

import javax.annotation.Nullable;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.*;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Queue;
import java.util.*;
import java.util.function.*;
import java.util.regex.Pattern;

@UtilityClass
public class Utils {
    
    public final String[] EMPTY_STRING_ARRAY = new String[0];
    
    public void unsupported() {
        throw new UnsupportedOperationException();
    }
    
    public void noSuch() {
        throw new NoSuchElementException();
    }
    
    public void error() {
        throw new Error();
    }
    
    public static void checkLength (String string, int limit) {
        checkLength(string.length(), limit);
    }
    
    public static void checkLength (int length, int limit) {
        if (length > limit) {
            throw new IllegalArgumentException("length: "+length+", limit: "+limit);
        }
    }
    
    public <T> T[] removeAndCompact (T[] src, T value, IntFunction<T[]> generator) {
        int i = 0;
        for (T t : src) if (t != value) ++i;
        T[] dst = generator.apply(i);
        i = 0;
        for (T t : src) if (t != value) dst[i++] = t;
        return dst;
    }
    
    public <T> T[] removeNulls (T[] src, IntFunction<T[]> generator) {
        int i = 0;
        for (T t : src) if (t != null) ++i;
        T[] dst = generator.apply(i);
        i = 0;
        for (T t : src) if (t != null) dst[i++] = t;
        return dst;
    }
    
    public int notNullCount (Object[] array) {
        int i = 0;
        for (Object t : array) if (t != null) ++i;
        return i;
    }
    
    public <T> T[] merge (IntFunction<T[]> generator, T[] array, T element) {
        T[] result = generator.apply(array.length + 1);
        for (int i = 0; i < array.length; i++) {
            result[i] = array[i];
        }
        result[array.length] = element;
        return result;
    }
    
    public <T> T[] merge (IntFunction<T[]> generator, T[] first, T... second) {
        if (first.length == 0)
            return second;
        if (second.length == 0)
            return first;
        T[] result = generator.apply(first.length + second.length);
        int index = 0;
        for (T t : first)  result[index++] = t;
        for (T t : second) result[index++] = t;
        return result;
    }
    
    @SafeVarargs
    public <T> T[] merge (IntFunction<T[]> generator, T[]... arrays) {
        if (arrays.length == 0)
            return generator.apply(0);
        int len = 0;
        for (T[] array : arrays) len += array.length;
        if (len == 0)
            return arrays[0];
        T[] result = generator.apply(len);
        int index = 0;
        for (T[] array : arrays) for (T t : array) result[index++] = t;
        return result;
    }
    
    public <T, R> R[] map (T[] src, IntFunction<R[]> generator, Function<T, R> mapper) {
        int len = src.length;
        R[] result = generator.apply(len);
        for (int i = 0; i < len; i++) {
            result[i] = mapper.apply(src[i]);
        }
        return result;
    }
    
    public <T> void collectQueue (Queue<T> queue, Collection<T> dst) {
        T e;
        while ((e = queue.poll()) != null)
            dst.add(e);
    }
    
    public int[] makeArray (int length, IntUnaryOperator valueProducer) {
        int[] array = new int[length];
        for (int i = 0; i < length; ++i)
            array[i] = valueProducer.applyAsInt(i);
        return array;
    }
    
    public Object[] makeArray (int length, IntFunction<Object> valueProducer) {
        Object[] array = new Object[length];
        for (int i = 0; i < length; ++i)
            array[i] = valueProducer.apply(i);
        return array;
    }
    
    public <T> T[] makeArray (int length, IntFunction<T[]> generator, IntFunction<T> valueProducer) {
        T[] array = generator.apply(length);
        for (int i = 0; i < length; ++i)
            array[i] = valueProducer.apply(i);
        return array;
    }
    
    public <T> T[] fillArray (T[] array, IntFunction<T> valueProducer) {
        int len = array.length;
        for (int i = 0; i < len; ++i)
            array[i] = valueProducer.apply(i);
        return array;
    }
    
    public boolean isValidIndex (int idx, int len) {
        return idx >= 0 && idx < len;
    }
    
    public <T> boolean isValidIndex (int idx, T[] array) {
        return idx >= 0 && idx < array.length;
    }
    
    public boolean isValidIndex (int idx, float[] array) {
        return idx >= 0 && idx < array.length;
    }
    
    public boolean isValidIndex (int idx, int[] array) {
        return idx >= 0 && idx < array.length;
    }
    
    public boolean isValidIndex (int idx, short[] array) {
        return idx >= 0 && idx < array.length;
    }
    
    public @Nullable <T> T getOrNull (int idx, T[] array) {
        if (idx >= 0 && idx < array.length) return array[idx];
        return null;
    }
    
    public <T> InputStream resourceStream(String path, Object obj) {
        return resourceStream(path, obj.getClass());
    }
    
    public <T> InputStream resourceStream(String path, Class<T> clazz) {
        InputStream s = clazz.getClassLoader().getResourceAsStream(path);
        if (s == null)
            throw new RuntimeException("Missing required resource file: " + path);
        return s;
    }
    
    public String capitalize(String str) {
        if (str.length() < 1) return str;
        char[] ch = str.toCharArray();
        ch[0] = Character.toUpperCase(ch[0]);
        return new String(ch);
    }
    
    private final String PATTERN = Pattern.quote("_");
    
    public String upperToCamel(String str) {
        return upperToCamel(str, "");
    }
    
    public String upperToCamel(String str, String joinSeparator) {
        String[] parts = str.split(PATTERN);
        for (int i = 0; i < parts.length; i++) {
            parts[i] = capitalize(parts[i].toLowerCase());
        }
        return String.join(joinSeparator, parts);
    }
    
    public <T extends Enum<T>> String camelName(Enum<T> e) {
        return upperToCamel(e.name());
    }
    
    public <T extends Enum<T>> String camelName(Enum<T> e, String joinSeparator) {
        return upperToCamel(e.name(), joinSeparator);
    }
    
    @SneakyThrows
    public void replaceFinalValue(Field f, Object obj, Object value) {
        makeNonFinal(f);
        f.set(obj, value);
    }
    
    @SneakyThrows
    public void makeNonFinal(Field f) {
        f.setAccessible(true);
        Field modifiersField = f.getClass().getDeclaredField("modifiers");
        modifiersField.setAccessible(true);
        modifiersField.setInt(f, f.getModifiers() & ~Modifier.FINAL);
    }
    
    public void checkArgument(boolean arg, Supplier<String> message) {
        if (!arg) {
            throw new IllegalArgumentException(message.get());
        }
    }
    
    public boolean renameFile(File file, String newName, String ext) {
        return renameFile(file, newName + '.' + ext);
    }
    
    public boolean renameFile(File file, String newName) {
        var parent = file.getParentFile();
        return file.renameTo(new File(parent, newName));
    }
    
    public String toBase64String(byte[] bytes,
                                 int from, int count) {
        if (from != 0 || count != bytes.length)
            bytes = Arrays.copyOfRange(bytes, from, from + count);
        return Base64.getEncoder().encodeToString(bytes);
    }
    
    public static boolean inRange (float value, float from, float to) {
        return value >= from && value <= to;
    }
    
    public boolean inRangeExcl (float value, float from, float to) {
        return value >= from && value < to;
    }
    
    public static boolean insideRange (float value, float from, float to) {
        return value > from && value < to;
    }
    
    public void checkRange (String what, int value, int min, int max) {
        if (!inRange(value, min, max)) {
            throw new IllegalArgumentException(what + " must be in range "+min+" - "+max+", got "+value);
        }
    }
    
    public boolean inRange (int value, int from, int to) {
        return value >= from && value <= to;
    }
    
    public boolean inRangeExcl (int value, int from, int to) {
        return value >= from && value < to;
    }
    
    public boolean insideRange (int value, int from, int to) {
        return value > from && value < to;
    }
    
    public void ensureAssignable (Class<?> subClass, Class<?> superClass) {
        if (!superClass.isAssignableFrom(subClass)) {
            throw new IllegalArgumentException(subClass + " should be subclass of " + superClass.getName());
        }
    }
    
    public interface ThrowsRunnable {
        
        void run () throws Exception;
    }
    
    public void rethrow(ThrowsRunnable action) {
        try {
            action.run();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public void ignoreThrown (ThrowsRunnable action) {
        try {
            action.run();
        } catch (Exception e) {
            // ignore
        }
    }
    
    public interface ThrowsConsumer<T> {
        
        void accept (T t) throws Exception;
    }
    
    public <T> Consumer<T> wrap (ThrowsConsumer<T> consumer) {
        return t -> {
            try {
                consumer.accept(t);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }
    
    public String rtrim(String s, char character) {
        int i = s.length() - 1;
        while (i >= 0 && (s.charAt(i)) == character) {
            i--;
        }
        return s.substring(0, i + 1);
    }
    
    public byte[] merge (byte[]... arrays) {
        int i = 0;
        for (byte[] d : arrays) {
            i += d.length;
        }
    
        final byte[] result = new byte[i];
    
        i = 0;
    
        for (byte[] d : arrays) {
            for (byte b : d) {
                result[i++] = b;
            }
        }
        
        return result;
    }
    
    private MessageDigest md;
    
    static {
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    
    public UUID uuidFromData (byte[]... data) {
        // Копия кода из UUID, оптимизораванная сохраненным
        // инстанстом MessageDigest
        
        byte[] md5Bytes = md.digest(merge(data));
        
        md5Bytes[6]  &= 0x0f;  /* clear version        */
        md5Bytes[6]  |= 0x30;  /* set to version 3     */
        md5Bytes[8]  &= 0x3f;  /* clear variant        */
        md5Bytes[8]  |= 0x80;  /* set to IETF variant  */
    
        long msb = 0;
        long lsb = 0;
        assert md5Bytes.length == 16 : "data must be 16 bytes in length";
        for (int i=0; i<8; i++)
            msb = (msb << 8) | (md5Bytes[i] & 0xff);
        for (int i=8; i<16; i++)
            lsb = (lsb << 8) | (md5Bytes[i] & 0xff);
        
        return new UUID(msb, lsb);
    }
    
    public boolean containsOnlyAZaz09_ (String name) {
        for (int i = 0; i < name.length(); i++) {
            char c = name.charAt(i);
            if (!((c >= 'A' && c <= 'Z') ||
                (c >= 'a' && c <= 'z') ||
                (c >= '0' && c <= '9') ||
                c == '_')) {
            
                return false;
            }
        }
        return true;
    }
    
    public <T> List<T> reverseList (List<T> list) {
        if (list == null) return null;
        List<T> reversed = new ArrayList<>();
        if (list.isEmpty()) return reversed;
        
        for (int i = list.size() - 1; i > -1; i--) {
            reversed.add(list.get(i));
        }
        return reversed;
    }
    
    public <K, V> Map<K, V> filterMap (Map<K, V> map, BiPredicate<K, V> cond) {
        Map<K, V> newMap = new HashMap<>();
        for (var entry : map.entrySet()) {
            K k = entry.getKey();
            V v = entry.getValue();
            if (cond.test(k, v))
                newMap.put(k, v);
        }
        return newMap;
    }
    
    /** Возвращает типы дженериков, переданных в класс-родитель (Child extends Parent<Gen>) */
    public Type[] getSuperGenericTypes (Object obj) {
        return getSuperGenericTypes(obj.getClass());
    }
    
    /** Возвращает типы дженериков, переданных в класс-родитель (Child extends Parent<Gen>) */
    public Type[] getSuperGenericTypes (Class<?> clazz) {
        return ((ParameterizedType) clazz.getGenericSuperclass()).getActualTypeArguments();
    }
    
    /** Возвращает строковые названия дженериков суперкласса (Child extends Parent<Gen>) */
    public String[] getSuperGenericNames (Object obj) {
        return getSuperGenericNames(obj.getClass());
    }
    
    /** Возвращает строковые названия дженериков суперкласса (Child extends Parent<Gen>) */
    public String[] getSuperGenericNames (Class<?> clazz) {
        var types = getSuperGenericTypes(clazz);
        String[] names = new String[types.length];
        
        if (types.length > 0) {
            for (int i = 0; i < types.length; i++) {
                names[i] = types[i].getTypeName();
            }
        }
        return names;
    }
    
    /** Возвращает классы дженериков суперкласса (Child extends Parent<Gen>) */
    @Deprecated
    public Class<?>[] getSuperGenericClasses (Object obj) {
        return getSuperGenericClasses(obj.getClass());
    }
    
    /** Возвращает классы дженериков суперкласса (Child extends Parent<Gen>) */
    @Deprecated
    public Class<?>[] getSuperGenericClasses (Class<?> clazz) {
        var names = getSuperGenericNames(clazz);
        Class<?>[] classes = new Class<?>[names.length];
        
        if (names.length > 0) {
            for (int i = 0; i < names.length; i++) {
                try {
                    classes[i] = Class.forName(names[i], true, clazz.getClassLoader());
                }
                catch (ClassNotFoundException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }
        return classes;
    }
    
    /**
     * Для вызова все дженерики должны быть заполнены реальными классами
     * @param num Порядковый номер дженерика
     * @return Класс дженерика, переданного в суперкласс
     */
    public Class<?> superGenericClass (Object obj, int num) {
        return superGenericClass(obj.getClass(), num);
    }
    
    /**
     * Для вызова все дженерики должны быть заполнены реальными классами
     * @param num Порядковый номер дженерика
     * @return Класс дженерика, переданного в суперкласс
     */
    public Class<?> superGenericClass (Class<?> clazz, int num) {
        var generics = getSuperGenericClasses(clazz);
        if (generics == null) {
            throw new RuntimeException("Trying get generics of " + clazz.getSimpleName() + " returned null");
        }
        try {
            return generics[num - 1];
        } catch (Exception e) {
            throw new RuntimeException("Array with generics of " + clazz.getSimpleName() + " not consists num " + num);
        }
    }
    
    public Object newInstanceFromGeneric (Object obj, int num) {
        return newInstanceFromGeneric(obj.getClass(), num);
    }
    
    public Object newInstanceFromGeneric (Class<?> clazz, int num) {
        return newInstance(superGenericClass(clazz, num));
    }
    
    public Class<?>[] getParamTypes (Object... params) {
        Class<?>[] types = new Class<?>[params.length];
        int length = params.length;
        if (length > 0) {
            for (int i = 0; i < length; i++) {
                types[i] = params[i].getClass();
            }
        }
        return types;
    }
    
    /** Возвращает значение статичного свойства */
    public Object getFieldValue (Class clazz, String field) {
        return getFieldValue(clazz, null, field);
    }
    
    /** Возвращает значение свойства */
    public Object getFieldValue (Object obj, String field) {
        return getFieldValue(obj.getClass(), obj, field);
    }
    
    /** Возвращает значение свойства */
    public Object getFieldValue (Class clazz, Object obj, String field) {
        Object object = null;
        try {
            object = clazz.getField(field).get(obj);
        }
        catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return object;
    }
    
    /** Ставит значение статичного свойства */
    public void setFieldValue (Class clazz, String field, Object value) {
        setFieldValue(clazz, null, field, value);
    }
    
    /** Ставит значение свойства */
    public void setFieldValue (Object obj, String field, Object value) {
        setFieldValue(obj.getClass(), obj, field, value);
    }
    
    /** Ставит значение свойства */
    public void setFieldValue (Class clazz, Object obj, String field, Object value) {
        try {
            clazz.getField(field).set(null, value);
        }
        catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    
    public Object invoke (Class clazz, String method, Object... params) {
        return invoke(clazz, null, method, params);
    }
    
    public Object invoke (Object obj, String method, Object... params) {
        return invoke(obj.getClass(), obj, method, params);
    }
    
    // Закончить изменение онЕнабле, создать LordManager, заменить transient, кончить киты
    @SuppressWarnings("unchecked")
    public Object invoke (Class clazz, Object obj, String method, Object... params) {
        Object result = null;
        try {
            Method meth = clazz.getDeclaredMethod(method, getParamTypes(params));
            meth.setAccessible(true);
            result = meth.invoke(obj, params);
        }
        catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return result;
    }
    
    public <T> T newInstance (Class<T> clazz, Object... params) {
        T obj = null;
        try {
            obj = clazz.getConstructor(getParamTypes(params)).newInstance(params);
        }
        catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return obj;
    }
    
    public byte[] decodeHexDump(CharSequence hexDump) {
        return decodeHexDump(hexDump, 0, hexDump.length());
    }
    
    public byte[] decodeHexDump(CharSequence hexDump, int fromIndex, int length) {
        if (length >= 0 && (length & 1) == 0) {
            if (length == 0) {
                return new byte[0];
            } else {
                byte[] bytes = new byte[length >>> 1];
                
                for(int i = 0; i < length; i += 2) {
                    bytes[i >>> 1] = decodeHexByte(hexDump, fromIndex + i);
                }
                
                return bytes;
            }
        } else {
            throw new IllegalArgumentException("length: " + length);
        }
    }
    
    public byte decodeHexByte(CharSequence s, int pos) {
        int hi = decodeHexNibble(s.charAt(pos));
        int lo = decodeHexNibble(s.charAt(pos + 1));
        if (hi != -1 && lo != -1) {
            return (byte)((hi << 4) + lo);
        } else {
            throw new IllegalArgumentException(String.format("invalid hex byte '%s' at index %d of '%s'", s.subSequence(pos, pos + 2), pos, s));
        }
    }
    
    public int decodeHexNibble(char c) {
        assert HEX2B.length == 65536;
        
        return HEX2B[c];
    }
    
    public static String toHexString(byte[] src) {
        return toHexString(src, 0, src.length);
    }
    
    public static String toHexString(byte[] src, int offset, int length) {
        return ((StringBuilder)toHexString(new StringBuilder(length << 1), src, offset, length)).toString();
    }
    
    public static <T extends Appendable> T toHexString(T dst, byte[] src) {
        return toHexString(dst, src, 0, src.length);
    }
    
    public static <T extends Appendable> T toHexString(T dst, byte[] src, int offset, int length) {
        assert length >= 0;
        
        if (length == 0) {
            return dst;
        } else {
            int end = offset + length;
            int endMinusOne = end - 1;
            
            int i;
            for(i = offset; i < endMinusOne && src[i] == 0; ++i) {
            }
            
            byteToHexString(dst, src[i++]);
            int remaining = end - i;
            toHexStringPadded(dst, src, i, remaining);
            return dst;
        }
    }
    
    public <T extends Appendable> T toHexStringPadded(T dst, byte[] src, int offset, int length) {
        int end = offset + length;
        
        for(int i = offset; i < end; ++i) {
            byteToHexStringPadded(dst, src[i]);
        }
        
        return dst;
    }
    
    public static <T extends Appendable> T byteToHexStringPadded(T buf, int value) {
        try {
            buf.append(byteToHexStringPadded(value));
        } catch (IOException var3) {
            throw new RuntimeException(var3);
        }
        
        return buf;
    }
    
    public static String byteToHexStringPadded(int value) {
        return BYTE2HEX_PAD[value & 255];
    }
    
    public <T extends Appendable> T byteToHexString(T buf, int value) {
        try {
            buf.append(byteToHexString(value));
        } catch (IOException var3) {
           throw new RuntimeException(var3);
        }
        
        return buf;
    }
    
    public static String byteToHexString(int value) {
        return BYTE2HEX_NOPAD[value & 255];
    }
    
    private final byte[] HEX2B;
    private final String[] BYTE2HEX_PAD = new String[256];
    private final String[] BYTE2HEX_NOPAD = new String[256];
    
    static {
        for(int i = 0; i < BYTE2HEX_PAD.length; ++i) {
            String str = Integer.toHexString(i);
            BYTE2HEX_PAD[i] = i > 15 ? str : '0' + str;
            BYTE2HEX_NOPAD[i] = str;
        }
        
        HEX2B = new byte[65536];
        Arrays.fill(HEX2B, (byte)-1);
        HEX2B[48] = 0;
        HEX2B[49] = 1;
        HEX2B[50] = 2;
        HEX2B[51] = 3;
        HEX2B[52] = 4;
        HEX2B[53] = 5;
        HEX2B[54] = 6;
        HEX2B[55] = 7;
        HEX2B[56] = 8;
        HEX2B[57] = 9;
        HEX2B[65] = 10;
        HEX2B[66] = 11;
        HEX2B[67] = 12;
        HEX2B[68] = 13;
        HEX2B[69] = 14;
        HEX2B[70] = 15;
        HEX2B[97] = 10;
        HEX2B[98] = 11;
        HEX2B[99] = 12;
        HEX2B[100] = 13;
        HEX2B[101] = 14;
        HEX2B[102] = 15;
    }
    
    @SneakyThrows
    public static void writeAsPng (byte[] bytes, int height, int width, Path path) {
        DataBuffer buffer = new DataBufferByte(bytes, bytes.length);

//3 bytes per pixel: red, green, blue
        WritableRaster raster = Raster.createInterleavedRaster(buffer, width, height, 4 * width, 4, new int[] {0, 1, 2, 3}, (Point)null);
        ColorModel cm = new ComponentColorModel(ColorModel.getRGBdefault().getColorSpace(), true, true, Transparency.OPAQUE, DataBuffer.TYPE_BYTE);
        BufferedImage bufferedImage = new BufferedImage(cm, raster, true, null);
    
        ImageIO.write(bufferedImage, "png", path.toFile());
    }
}