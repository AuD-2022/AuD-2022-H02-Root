package h02;

import java.lang.reflect.Field;

import sun.misc.Unsafe;

/**
 * Some java magic to modify private final static fields.
 * <br><br>
 * Stolen from:
 * <a href="https://stackoverflow.com/questions/56039341/get-declared-fields-of-java-lang-reflect-fields-in-jdk12">https://stackoverflow.com/questions/56039341/get-declared-fields-of-java-lang-reflect-fields-in-jdk12</a>
 **/
public class FieldHelper {

    /**
     * Hmm yes unsafe, but it's a good way to get the field.
     */
    private static Unsafe unsafe;

    static {
        try {
            final Field unsafeField = Unsafe.class.getDeclaredField("theUnsafe");
            unsafeField.setAccessible(true);
            unsafe = (Unsafe) unsafeField.get(null);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void setFinalStatic(Field field, Object value) throws Exception {
        // yes this is ugly, but it works
        Object fieldBase = unsafe.staticFieldBase(field);
        long fieldOffset = unsafe.staticFieldOffset(field);
        // Why would you need bytecode transformations if you can just do this? ( ͡° ͜ʖ ͡°)
        unsafe.putObject(fieldBase, fieldOffset, value);
    }
}
