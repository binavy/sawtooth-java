package com.bianvy.sawtooth.config;

import java.util.Map;

/**
 * A set of configuration properties of a Sawtooth blockchain.
 */
public interface SawtoothConfig {

    /**
     * Return all set {@link SawtoothOption}'s.
     *
     * @return
     */
    Map<SawtoothOption<?>, ?> getOptions();

    /**
     * Sets the configuration properties from the specified {@link Map}.
     *
     * @param options
     * @return true if success
     */
    boolean setOptions(Map<SawtoothOption<?>, ?> options);

    /**
     * Return the value of the given {@link SawtoothOption}.
     *
     * @param option
     * @param <T>
     * @return
     */
    <T> T getOption(SawtoothOption<T> option);

    /**
     * Sets a configuration property with the specified name and value.
     *
     * @param option
     * @param value
     * @param <T>
     * @return true if and only if the property has been set
     */
    <T> boolean setOption(SawtoothOption<T> option, T value);
}
