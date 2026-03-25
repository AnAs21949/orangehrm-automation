package com.orangehrm.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
    private static ExtentReports extent;

    public static ExtentReports createInstance() {
        // 1. Définir le chemin du rapport
        String path = "reports/TestReport.html";

        // 2. Créer le reporter HTML (ExtentSparkReporter = format moderne)
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(path);
        sparkReporter.config().setReportName("OrangeHRM Test Report");
        sparkReporter.config().setDocumentTitle("Test Results");

        // 3. Créer l'instance principale et attacher le reporter
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);

        // 4. Ajouter des infos système (visibles dans le rapport)
        extent.setSystemInfo("Tester", "Anas");
        extent.setSystemInfo("Environment", "Demo");

        return extent;
    }

    public static ExtentReports getInstance() {
        if (extent == null) {
            createInstance();
        }
        return extent;
    }

    public static void flush() {
        extent.flush();
    }
}