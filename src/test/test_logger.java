/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package example;

import java.util.logging.Level;
import java.util.logging.Logger;

import static com.mdalsoft.test.DefaultTestLogger.logTest;
import com.mdalsoft.test.TestLogger;
import com.mdalsoft.test.Testable;

import java.io.File;
import java.util.HashMap;
import java.util.Map;



/**
 *
 * @author User
 */
public class test_logger {
    
    String sep="\\";
    String testFileLogger="com.mdalsoft.test.FileTestLogger";
    String dbTestLogger="com.mdalsoft.test.DbTestLogger";  
    
    //méthode à tester
    String firstMethodToTest="EDsolver.solve";
     
    //classe à tester
    static String classeATester="calculnum.EDsolver";
    
    public static void main(String [] args) throws Exception{
        
        for(int i=0; i<15; i++){
            long startTime=System.currentTimeMillis();
            Map parTest= new HashMap();
            String methodToTest="test_logger.main";
            String testFileLogger="com.mdalsoft.test.FileTestLogger";
            try{
                parTest.put("class to test", classeATester);
                parTest.put("teststarttime",""+startTime);
                parTest.put("testreference",methodToTest);
                parTest.put("testLogger0",testFileLogger);
                logTest(parTest,"start",false);
                
                test_logger cas= new test_logger();
                boolean res= cas.test_cas(i);
                parTest.put("testresult", res);
                
            }catch(Throwable exx){
                exx.printStackTrace();
                parTest.put("testresult",false);
                parTest.put("testerror", exx);
            }finally{
                parTest.put("testendtime",""+System.currentTimeMillis());
                logTest(parTest,"end",true);
            }
        }
        
    }

//Il ne reste plus qu'à completer la partie ci, en ajoutant les différents cas de test....
    private boolean test_cas(int i) {
         switch (i){
             case 0:
                Fonction f=new Fonction(0);
                Fonction RA= new Fonction(0);
                test_data data=new test_data(f, RA,"f=0 n=9000", 9000, 1, 2, 0); 
                return data.oracle();
                break;
             case 1:
                f=new Fonction(n);
                RA= null;
                test_data data=new test_data(f, RA,"f=1 n=0", 0, 1, 2, 0); 
                return data.oracle();
             case 2:
                Fonction f=new Fonction(n);
                Fonction RA= null;
                test_data data=new test_data(f, RA,"f=1 n=0", 0, 1, 2, 0); 
                return data.oracle();
             case 3:
                 
         }
    }
    
    
}
