/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mdalsoft.test;

import java.util.Map;


import java.util.Map;

/**
 *
 * @author ibrahim
 */
public interface TestLogger {
        public final static String teststarttime="teststarttime";
         public final static String testcase="testcase"; 
        public final static String testreference="testreference"; 
        public final static String lfm="lfm";
        public final static String testobjet="testobjet";
        public final static String testresult="testresult";
        public final static String testerror="testerror";   
        public final static String printErrorTrace="printErrorTrace";
        public final static String testendtime="testendtime";
        void init(Map parTest) throws Exception;
        void log(Map parTest, String event, boolean closeLogs) throws Exception;    
        }

