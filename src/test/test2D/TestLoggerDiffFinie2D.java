/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.test2D;

import ananum.calculnumerique.functions.ConstantFunction;
import ananum.calculnumerique.solvers.EDSolver;
import ananum.calculnumerique.solvers.EDSolverDiffFini;
import ananum.calculnumerique.Function;
import ananum.calculnumerique.Function2D;
import ananum.calculnumerique.functions.ConstantFunction2D;
import ananum.calculnumerique.functions.PolynomialFunction;
import ananum.calculnumerique.functions.PolynomialFunction2D;
import ananum.calculnumerique.solvers2d.EDRSolver2D;
import ananum.calculnumerique.solvers2d.EDRSolverDiff2D;
import ananum.matrice.Matrice;
import ananum.matrice.MatriceCRS;

import static com.mdalsoft.test.DefaultTestLogger.logTest;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author User
 */
public class TestLoggerDiffFinie2D {

    String sep = "\\";
    String testFileLogger = "com.mdalsoft.test.FileTestLogger";
    String dbTestLogger = "com.mdalsoft.test.DbTestLogger";

    //méthode à tester
    String firstMethodToTest = "EDRsolver2D.solve";

    //classe à tester
    static String classeATester = "calculnum.EDRsolver2D";

    public static void main(String[] args) throws Exception {

        long startTime = System.currentTimeMillis();
        Map parTest = new HashMap();
        String methodToTest = "TestLoggerDiffFinie2D.main";
        String testFileLogger = "com.mdalsoft.test.FileTestLogger";
        try {
            parTest.put("class_to_test", classeATester);
            parTest.put("teststarttime", "" + startTime);
            parTest.put("testreference", methodToTest);
            parTest.put("testLogger0", testFileLogger);
            logTest(parTest, "start", false);

            TestLoggerDiffFinie2D cas = new TestLoggerDiffFinie2D();
            boolean res = cas.test_cas_f_0_n15_m_150();
            res = res && cas.test_cas_f_1_n_neg_m_20();
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
    
    private boolean test_cas_f_0_n15_m_150() throws Exception {
        long startTime = System.currentTimeMillis();
        Map parTest = new HashMap();
        parTest.put("teststarttime", "" + startTime);
        parTest.put("testreference", "TestLoggerDiffFinie2D."+new Throwable() 
                                      .getStackTrace()[0] 
                                      .getMethodName());
        parTest.put("testLogger0", testFileLogger);
        logTest(parTest, "start", false);
        
        try{
            /* u(x,y) = x+y */
            int n = 15, m=150;
            EDRSolver2D sd = new EDRSolverDiff2D();
            Matrice mat = new MatriceCRS(2, 2);
            mat.set(0, 1, 1);
            mat.set(1, 0, 1);
            Function2D u = new PolynomialFunction2D(mat, 0, 1, 0, 1);
            Function2D f = new ConstantFunction2D(0);
            test_data_2d data = new test_data_2d(sd, "f=0 n=15 m=150", f, n, m, u, null, u);

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
    
    private boolean test_cas_f_1_n_neg_m_20() throws Exception {
        long startTime = System.currentTimeMillis();
        Map parTest = new HashMap();
        parTest.put("teststarttime", "" + startTime);
        parTest.put("testreference", "TestLoggerDiffFinie2D."+new Throwable() 
                                      .getStackTrace()[0] 
                                      .getMethodName());
        parTest.put("testLogger0", testFileLogger);
        logTest(parTest, "start", false);
        
        try{
            /* u(x,y) = x+xy+3x^2 */
            int n=-1, m=10;
            EDRSolver2D sd = new EDRSolverDiff2D();
            Matrice mat = new MatriceCRS(3, 3);
            mat.set(0, 1, 1);
            mat.set(1, 1, 1);
            mat.set(2, 2, 3);
            Function2D u = new PolynomialFunction2D(mat, 0, 1, 0, 1);
            Function2D f = new ConstantFunction2D(1.0);
            test_data_2d data = new test_data_2d(sd, "f=0 n=-1 m=10", f, -1, 10, u, null, u);

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
