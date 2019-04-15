/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import ananum.calculnumerique.ConstantFunction;
import ananum.calculnumerique.EDSolver;
import ananum.calculnumerique.EDSolverDiffFini;
import ananum.calculnumerique.Function;

import static com.mdalsoft.test.DefaultTestLogger.logTest;
import example.test_data;
import java.awt.BorderLayout;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author User
 */
public class test_logger {

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
        String methodToTest = "test_logger.main";
        String testFileLogger = "com.mdalsoft.test.FileTestLogger";
        try {
            parTest.put("class to test", classeATester);
            parTest.put("teststarttime", "" + startTime);
            parTest.put("testreference", methodToTest);
            parTest.put("testLogger0", testFileLogger);
            logTest(parTest, "start", false);

            test_logger cas = new test_logger();
            boolean res = cas.test_cas_fNulle_n10();
            System.out.println("Test results: "+ res);
            parTest.put("testresult", res);

        } catch (Throwable exx) {
            exx.printStackTrace();
            parTest.put("testresult", false);
            parTest.put("testerror", exx);
        } finally {
            parTest.put("testendtime", "" + System.currentTimeMillis());
            logTest(parTest, "end", true);
        }

    }

//Il ne reste plus qu'à completer la partie ci, en ajoutant les différents cas de test....
    private boolean test_cas_fNulle_n10() {
        EDSolver sd = new EDSolverDiffFini();

        Function f = new ConstantFunction(0);
        Function RA = new ConstantFunction(0);
        test_data data = new test_data(sd, f, RA, "f=0 n=10", 10, 1, 2, 0);
        return data.oracle();
    }
    
    private boolean test_cas_f1_n2() {
        EDSolver sd = new EDSolverDiffFini();

        Function f = new ConstantFunction(1);
        Function RA = new ConstantFunction(1);
        test_data data = new test_data(sd, f, RA, "f=1 n=10", 10, 1, 2, 0);
        return data.oracle();
    }

}
