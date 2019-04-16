/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mdalsoft.test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.RandomAccessFile;
import java.util.Map;

/**
 *
 * @author ibrahim
 */
public class FileTestLogger extends DefaultTestLogger implements TestLogger{
     static BufferedWriter  raf;
     static Object sync=new Object();
     public void init(Map parTest) throws Exception{
        synchronized(sync){
             if(raf==null) raf = new BufferedWriter( 
            new FileWriter("mdaltestlogs.log", true)); 
            }         
        }
    public void log(Map parTest, String event, boolean closeLogs) throws Exception{
        synchronized(sync){
            StringBuilder sb=new StringBuilder();
            sb.append("---;");
            String temp=(String) parTest.get(testcase);
            sb.append(temp==null?"---":temp).append(";");
            temp=(String) parTest.get(testreference);
            sb.append(temp==null?"---":temp).append(";");
            temp=(String) parTest.get(teststarttime);
            sb.append(temp==null?"---":temp).append(";");
            long start=Long.parseLong(temp);
            temp=(String) parTest.get(testendtime);
            long end=temp==null?-1:Long.parseLong(temp);
            long duration=temp==null?-1:(end-start);
            sb.append(temp==null?"-1":duration).append(";");
            sb.append(event).append(";");
            Boolean bool=(Boolean) parTest.get(testresult);
            sb.append(bool==null?"---":bool.toString()).append(";");
            Throwable th=(Throwable) parTest.get(testerror);
            if(th!=null) temp=th.getMessage();
            sb.append(temp==null?"---":temp).append(";");
            temp=(String) parTest.get(teststarttime);
            sb.append(temp==null?"---":temp).append(";");
            raf.write(sb.toString());
            raf.write(System.getProperty("line.separator"));
            raf.flush();
            if(closeLogs) raf.close();
            }        
        }   
    
}
