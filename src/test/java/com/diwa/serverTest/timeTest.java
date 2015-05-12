package com.diwa.serverTest;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by di on 12/5/15.
 */
public class timeTest {
    public static void main(String[] args) {
        System.out.println(new Timestamp(System.currentTimeMillis()).getTime());

        System.out.println(Timestamp.valueOf("2015-05-12 16:37:57.466"));
    }
}
