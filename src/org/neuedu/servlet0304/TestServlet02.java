package org.neuedu.servlet0304;

import javax.servlet.annotation.WebServlet;

//@WebServlet(value = "/test02",loadOnStartup = 1)
public class TestServlet02 extends MyHttpServlet {
    @Override
    public void doPost() {
        System.out.println(456);
    }

    @Override
    public void doGet() {
        System.out.println(123);
    }
}
