package com.kinztech.os.utilities;

/**
 * Created by Allen Kinzalow on 6/2/2015.
 */
public class Utils {

    public static void reverse(byte[] array) {
        for(int start=0, end=array.length-1; start<=end; start++, end--){
            byte aux = array[start];
            array[start]=array[end];
            array[end] = aux;
        }
    }

}
