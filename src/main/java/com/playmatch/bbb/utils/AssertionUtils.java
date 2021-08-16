package com.playmatch.bbb.utils;

import lombok.RequiredArgsConstructor;

import java.util.function.Supplier;

public abstract class AssertionUtils {

    @RequiredArgsConstructor
    public static class Assertion {

        private final boolean value;

        public <X extends Throwable> void orElseThrow(Supplier<? extends X> exceptionSupplier) throws X {
            if (!value) {
                throw exceptionSupplier.get();
            }
        }
    }

    public static Assertion assertTrue(boolean value) {
        return new Assertion(value);
    }

    public static Assertion assertFalse(boolean value) {
        return new Assertion(!value);
    }
}
