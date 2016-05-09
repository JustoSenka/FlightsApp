package com.justing.flights.commons;

/**
 * Created by Justas on 05/09/2016.
 */
public class Tuple<X, Y> {

    public final X left;
    public final Y right;

    public Tuple(X left, Y right) {
        this.left = left;
        this.right = right;
    }

    public static <X, Y> Tuple<X, Y> get(X left, Y right){
        return new Tuple<X, Y>(left, right);
    }

    public static <X, Y> Tuple<X, Y> getArray(X left, Y right){
        return new Tuple<X, Y>(left, right);
    }
}
