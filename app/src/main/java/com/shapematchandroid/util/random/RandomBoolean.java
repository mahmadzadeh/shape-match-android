package com.shapematchandroid.util.random;

import com.shapematchandroid.util.IntegerRange;

public class  RandomBoolean {

    public static Boolean  nextRandomTrueWithOneOutOfNChance(Integer n ) {

        return RandomNumberGenerator.next(new IntegerRange(1, n)) == 1;

    }
    
}
