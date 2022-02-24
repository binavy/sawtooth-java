package com.bianvy.sawtooth.config;

import com.bianvy.sawtooth.util.AbstractConstant;
import com.bianvy.sawtooth.util.ConstantPool;

/**
 * A {@link SawtoothOption} allows to configure a {@link SawtoothConfig} in a type-safe way.
 * Which {@link SawtoothOption} is supported depends on the actual implementation of {@link SawtoothConfig} and may
 * depend on the work mode of sawtooth component.
 *
 * @param <T> the type of the value which is valid for the {@link SawtoothOption}
 */
public class SawtoothOption<T> extends AbstractConstant<SawtoothOption<T>> {

    private static final ConstantPool<SawtoothOption<Object>> pool = new ConstantPool<>() {
        @Override
        protected SawtoothOption<Object> newConstant(int id, String name) {
            return new SawtoothOption<>(id, name);
        }
    };

    /**
     * Creates a new ChannelOption with the specified unique name.
     *
     * @param id
     * @param name
     */
    private SawtoothOption(int id, String name) {
        super(id, name);
    }

    /**
     * Returns the {@link SawtoothOption} of the specified name.
     *
     * @param name
     * @param <T>  the type of the option
     * @return option
     */
    public static <T> SawtoothOption<T> valueOf(String name) {
        return (SawtoothOption<T>) pool.valueOf(name);
    }

    /**
     * Returns true if a {@link SawtoothOption} exists for the given name.
     *
     * @param name
     * @return
     */
    public static boolean exists(String name) {
        return pool.exists(name);
    }

    public static final SawtoothOption<Integer> THREAD_COUNT = valueOf("THREAD_COUNT");
    public static final SawtoothOption<Integer> MAX_QUEUE_SIZE = valueOf("MAX_QUEUE_SIZE");
}
