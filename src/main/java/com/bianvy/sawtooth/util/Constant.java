package com.bianvy.sawtooth.util;

/**
 * A singleton which is safe to compare via the == operator. Created and managed by {@link ConstantPool}.
 *
 * @param <T> the type of the constant
 */
public interface Constant<T extends Constant<T>> extends Comparable<T> {

    /**
     * Returns the unique number assigned to this {@link Constant}
     *
     * @return id
     */
    int id();

    /**
     * Returns the name of this {@link Constant}
     *
     * @return name
     */
    String name();
}
