/**
 * Class for doing Radix sort
 *
 * @author Akhil Batra
 * @version 1.4 - April 14, 2016
 *
 **/

import static org.junit.Assert.*;
import org.junit.Test;

public class RadixSort
{
    /**
     * Does Radix sort on the passed in array with the following restrictions:
     *  The array can only have ASCII Strings (sequence of 1 byte characters)
     *  The sorting is stable and non-destructive
     *  The Strings can be variable length (all Strings are not constrained to 1 length)
     *
     * @param asciis String[] that needs to be sorted
     *
     * @return String[] the sorted array
     **/
    public static String[] sort(String[] asciis)
    {
        // find max length
        int maxLength = Integer.MIN_VALUE;
        for (String i : asciis) {
            if (i.length() > maxLength) {
                maxLength = i.length();
            }
        }

        String[] copy = new String[asciis.length];
        for(int i = 0; i < asciis.length; i++) {
            copy[i] = asciis[i];
        }
        return sort(copy, maxLength);
    }

    /* source: https://algs4.cs.princeton.edu/code/edu/princeton/cs/algs4/LSD.java.html*/
    private static String[] sort(String[] a, int w ) {
        int n = a.length;
        int R = 256;   // extend ASCII alphabet size
        String[] aux = new String[n];

        for (int d = w-1; d >= 0; d--) {
            // sort by key-indexed counting on dth character

            // compute frequency counts
            int[] count = new int[R+1];
            for (int i = 0; i < n; i++)
                count[charAt(a[i], d, w) + 1]++;

            // compute cumulates
            for (int r = 0; r < R; r++)
                count[r+1] += count[r];

            // move data
            for (int i = 0; i < n; i++)
                aux[count[charAt(a[i], d, w)]++] = a[i];
            // copy back
            for (int i = 0; i < n; i++)
                a[i] = aux[i];
        }
        return a;
    }

    // return dth character of s, 0 if d > length of string
    private static int charAt(String s, int d, int maxLength) {
        assert d >= 0;
        if (d >= s.length() && d <= maxLength) return 0;
//        System.out.print(s);
//        System.out.print(",  ");
//        System.out.print(d);
//        System.out.print(",  ");
//        System.out.println(s.charAt(d));
        return s.charAt(d);
    }

    /**
     * Radix sort helper function that recursively calls itself to achieve the sorted array
     *  destructive method that changes the passed in array, asciis
     *
     * @param asciis String[] to be sorted
     * @param start int for where to start sorting in this method (includes String at start)
     * @param end int for where to end sorting in this method (does not include String at end)
     * @param index the index of the character the method is currently sorting on
     *
     **/
    private static void sortHelper(String[] asciis, int start, int end, int index)
    {
        //TODO use if you want to
    }

    public static void main(String args[]) {
        String[] a = {"tr", "cr", "y", "ace", "a", "bbe", "ui", "aca", "oacd", "ac"};

        // sort the strings
        String[] b = sort(a);

        // print previous
        for (String i : a)
            StdOut.println(i);
        System.out.println("****************************");
        // print results
        for (String i : b)
            StdOut.println(i);
    }
}
