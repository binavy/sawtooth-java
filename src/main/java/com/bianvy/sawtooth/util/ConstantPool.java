package com.bianvy.sawtooth.util;

import org.apache.commons.lang3.ObjectUtils;

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * A pool of {@link Constant}s.
 *
 * @param <T> the type of the constant
 */
public abstract class ConstantPool<T extends Constant<T>> {

    private final ConcurrentMap<String, T> constants = new ConcurrentHashMap<>();

    private static final AtomicInteger idGenerator = new AtomicInteger(1);

    /**
     * Returns the {@link Constant} which is assigned to the specified name.
     * If there's no such {@link Constant}, a new one will be created and returned.
     * Once created, the subsequent calls with the same name will always return the previously created one
     * (i.e. singleton.)
     *
     * @param name the name of the {@link Constant}
     * @return the constant
     */
    public T valueOf(String name) {
        ObjectUtils.requireNonEmpty(name, "name");
        return getOrCreate(name);
    }

    /**
     * Get existing constant by name or creates new one if not exists.
     * Threadsafe.
     *
     * @param name the name of the constant
     * @return
     */
    private T getOrCreate(String name) {
        T constant = constants.get(name);
        if (constant == null) {
            final T newConstant = newConstant(idGenerator.getAndIncrement(), name);
            constant = constants.putIfAbsent(name, newConstant);
            if (constant == null) {
                return newConstant;
            }
        }

        return constant;
    }

    /**
     * Returns true if a constant exists for the given name.
     *
     * @param name the name of constant
     * @return true or false
     */
    public boolean exists(String name) {
        ObjectUtils.requireNonEmpty(name);
        return constants.containsKey(name);
    }

    /**
     * Creates a new {@link Constant} for the given name or fail with an {@link IllegalArgumentException}
     * if a {@link Constant} for the given name exists.
     *
     * @param name the name of the constant
     * @return constant
     */
    public T newInstant(String name) {
        ObjectUtils.requireNonEmpty(name);
        return createOrThrow(name);
    }

    /**
     * Creates constant by name or throws exception.
     * Threadsafe.
     *
     * @param name the name of the constant
     * @return constant
     */
    private T createOrThrow(String name) {
        T constant = constants.get(name);
        if (constant == null) {
            final T newConstant = newConstant(idGenerator.getAndIncrement(), name);
            constant = constants.putIfAbsent(name, newConstant);
            if (constant == null) {
                return newConstant;
            }
        }

        throw new IllegalArgumentException(String.format("'%s' is already in use", name));
    }

    protected abstract T newConstant(int id, String name);
}
