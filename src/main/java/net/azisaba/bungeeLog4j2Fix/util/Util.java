package net.azisaba.bungeeLog4j2Fix.util;

import javassist.ClassPool;
import javassist.CtClass;
import net.blueberrymc.native_util.ClassDefinition;
import net.blueberrymc.native_util.NativeUtil;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Util {
    private static final Map<String, ThrowableFunction<CtClass, byte[]>> queue = new HashMap<>();

    static {
        NativeUtil.registerClassLoadHook((classLoader, s, aClass, protectionDomain, bytes) -> {
            String cn = s.replace('/', '.');
            if (queue.containsKey(cn)) {
                try {
                    byte[] b = queue.remove(cn).apply(ClassPool.getDefault().get(cn));
                    System.out.println("Transformed " + cn);
                    return b;
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            return null;
        });
    }

    /**
     * @param clazz class name which contains dot, not slash.
     */
    public static void redefineClass(String clazz, ThrowableFunction<CtClass, byte[]> function) {
        Class<?> loaded = Arrays.stream(NativeUtil.getLoadedClasses()).filter(c -> c.getTypeName().equals(clazz)).findFirst().orElse(null);
        if (loaded != null) {
            try {
                NativeUtil.redefineClasses(new ClassDefinition[] {new ClassDefinition(loaded, function.apply(ClassPool.getDefault().get(clazz)))});
                System.out.println("Redefined " + clazz);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            queue.put(clazz, function);
        }
    }
}
