package net.azisaba.bungeeLog4j2Fix.util;

@FunctionalInterface
public interface ThrowableFunction<T, R> {
    R apply(T t) throws Exception;
}
