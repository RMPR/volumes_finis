/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mdalsoft.test;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author ibrahim
 */
public class DefaultTestLogger implements TestLogger{
      static Map<String,TestLogger> loggers=new HashMap();
    public static void logTest(Map parTest, String event, boolean closeLogs) throws Exception{
        for(int i=0;i>-1;i++){
            String cl=(String) parTest.get("testLogger"+i);
            if(cl==null) break;
            TestLogger tl=loggers.get(cl);
            if(tl==null){
                tl=((TestLogger)Class.forName(cl).newInstance());
                tl.init(parTest);
                }
            tl.log(parTest,event,closeLogs);
            }
        }

    @Override
    public void init(Map parTest) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void log(Map parTest, String event, boolean closeLogs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
