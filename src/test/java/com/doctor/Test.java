package com.doctor;


import org.apache.commons.codec.binary.Hex;

//@SpringBootTest
//@RunWith(SpringRunner.class)
public class Test {

    @org.junit.Test
    public void test() {
        byte[] b = {-1};
        int i = b[0];
        System.out.println(Integer.toHexString(i));
        System.out.println(Integer.toHexString(-1 & 0xff));
        System.out.println(Hex.encodeHexString(b));
    }
}
