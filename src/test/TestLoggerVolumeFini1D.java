/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import ananum.calculnumerique.ConstantFunction;
import ananum.calculnumerique.EDSolver;
import ananum.calculnumerique.EDSolverDiffFini;
import ananum.calculnumerique.EDSolverVolFini;
import ananum.calculnumerique.Function;
import ananum.calculnumerique.PolynomialFunction;

import static com.mdalsoft.test.DefaultTestLogger.logTest;
import example.test_data;
import java.awt.BorderLayout;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author User
 */
public class TestLoggerVolumeFini1D {

    String sep = "\\";
    String testFileLogger = "com.mdalsoft.test.FileTestLogger";
    String dbTestLogger = "com.mdalsoft.test.DbTestLogger";

    //méthode à tester
    String firstMethodToTest = "EDsolver.solve";

    //classe à tester
    static String classeATester = "calculnum.EDsolver";

    public static void main(String[] args) throws Exception {

        long startTime = System.currentTimeMillis();
        Map parTest = new HashMap();
        String methodToTest = "TestLoggerVolumeFini1D.main";
        String testFileLogger = "com.mdalsoft.test.FileTestLogger";
        try {
            parTest.put("class_to_test", classeATester);
            parTest.put("teststarttime", "" + startTime);
            parTest.put("testreference", methodToTest);
            parTest.put("testLogger0", testFileLogger);
            logTest(parTest, "start", false);

            TestLoggerVolumeFini1D cas = new TestLoggerVolumeFini1D();
            boolean res = cas.test_cas_f_0_n100();
            res = res && cas.test_cas_f_1_n200();
            parTest.put("testresult", res);
            System.out.println("Result: "+res);
        } catch (Throwable exx) {
            exx.printStackTrace();
            parTest.put("testresult", false);
            parTest.put("testerror", exx);
        } finally {
            parTest.put("testendtime", "" + System.currentTimeMillis());
            logTest(parTest, "end", true);
        }

    }

    private boolean test_cas_f_1_n200() throws Exception {
        long startTime = System.currentTimeMillis();
        Map parTest = new HashMap();
        parTest.put("teststarttime", "" + startTime);
        parTest.put("testreference", "TestLoggerDifferenceFinie1D."+new Throwable() 
                                      .getStackTrace()[0] 
                                      .getMethodName());
        parTest.put("testLogger0", testFileLogger);
        logTest(parTest, "start", false);
        
        try{
            EDSolver sd = new EDSolverVolFini();
            double a = 1.0;
            double b = 2.0;
            Function f = new ConstantFunction(1);
            Function RA = new PolynomialFunction(new double[]{1., 1.5, -0.5}, 0., 1.);
            test_data data = new test_data(sd, f, RA, "f=1 n=200", 200, 0.0, a, b);

            parTest.put("testendtime", "" + System.currentTimeMillis());
            parTest.put("testresult", data.oracle());
            logTest(parTest, "end", false);
            return data.oracle();
        } catch (Throwable exx) {
            exx.printStackTrace();
            parTest.put("testresult", false);
            parTest.put("testerror", exx);
            return false;
        }
    }
    
    private boolean test_cas_f_0_n100() throws Exception {
        long startTime = System.currentTimeMillis();
        Map parTest = new HashMap();
        parTest.put("teststarttime", "" + startTime);
        parTest.put("testreference", "TestLoggerDifferenceFinie1D."+new Throwable() 
                                      .getStackTrace()[0] 
                                      .getMethodName());
        parTest.put("testLogger0", testFileLogger);
        logTest(parTest, "start", false);
        
        try{
            EDSolver sd = new EDSolverVolFini();
            double a = 1.0;
            double b = 2.0;
            Function f = new ConstantFunction(0.0);
            Function RA = new PolynomialFunction(new double[]{1., 1.}, 0., 1.);
            test_data data = new test_data(sd, f, RA, "f=0 n=100", 100, 0.0, a, b);

            parTest.put("testendtime", "" + System.currentTimeMillis());
            parTest.put("testresult", data.oracle());
            logTest(parTest, "end", false);
            return data.oracle();
        } catch (Throwable exx) {
            exx.printStackTrace();
            parTest.put("testresult", false);
            parTest.put("testerror", exx);
            return false;
        }
    }
    
    private boolean test_cas_f_3_n0() throws Exception {
        long startTime = System.currentTimeMillis();
        Map parTest = new HashMap();
        parTest.put("teststarttime", "" + startTime);
        parTest.put("testreference", "TestLoggerDifferenceFinie1D."+new Throwable() 
                                      .getStackTrace()[0] 
                                      .getMethodName());
        parTest.put("testLogger0", testFileLogger);
        logTest(parTest, "start", false);
        
        try{
            EDSolver sd = new EDSolverVolFini();
            double a = 1.0;
            double b = 2.0;
            Function f = new ConstantFunction(3.0);
            Function RA = new PolynomialFunction(new double[]{1., 1.}, 0., 1.);
            test_data data = new test_data(sd, f, RA, "f=3 n=0", 0, 0.0, a, b);

            parTest.put("testendtime", "" + System.currentTimeMillis());
            parTest.put("testresult", data.oracle());
            logTest(parTest, "end", false);
            return data.oracle();
        } catch (Throwable exx) {
            exx.printStackTrace();
            parTest.put("testresult", false);
            parTest.put("testerror", exx);
            return false;
        }
    }
}
