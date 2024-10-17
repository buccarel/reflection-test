package com.example;

import java.util.Arrays;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class FairlyBigObject {
    private final int N;
    private final String[] strings;
    private final boolean useReflectiveEquals;

    public FairlyBigObject(int N, boolean useReflectiveEquals) {
        this.useReflectiveEquals = useReflectiveEquals;

        this.N = N;
        this.strings = new String[this.N];
        for(int i=0; i<this.N; i++) {
            strings[i] = "value_" + i;
        }
    }

    @Override
    public boolean equals(final Object o) {
        if(this.useReflectiveEquals) {
            return this.reflectiveEquals(o);
        } else {
            return this.staticEquals(o);
        }
    }

    @Override
    public int hashCode() {
        if(this.useReflectiveEquals) {
            return this.reflectiveHashCode();
        } else {
            return this.staticHashCode();
        }
    }

    private int reflectiveHashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    private int staticHashCode() {
        int result = 1;
        for(String string : strings) {
            result = result*31 + (string != null ? string.hashCode() : 0);
        }
        return result;
    }

    private boolean reflectiveEquals(Object o) {
        return EqualsBuilder.reflectionEquals(this, o);
    }

    private boolean staticEquals(Object o) {
    	if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;

        FairlyBigObject that = (FairlyBigObject) o;
        return Arrays.equals(this.strings, that.strings);
    }
}