package com.zjh.website;

import com.zjh.website.utils.MD5Util;
import org.junit.Test;

import java.util.Scanner;

public class MD5Test {

    @Test
    public void testMD5(){
        System.out.println(MD5Util.getMd5("123456"));
    }

}
