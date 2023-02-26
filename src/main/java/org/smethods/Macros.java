package org.smethods;

public class Macros {
    static String projectFolder = "ekstazi_projects";
    static String projectFolderPath = System.getProperty("user.home") + "/" + projectFolder;
    static String resultFolder = "result";
    static String resultFolderPath = projectFolderPath + "/" + resultFolder;
    static int numSHA = 1;
    static String SKIPS = " -Djacoco.skip -Dcheckstyle.skip -Drat.skip -Denforcer.skip -Danimal.sniffer.skip " +
            "-Dmaven.javadoc.skip -Dfindbugs.skip -Dwarbucks.skip -Dmodernizer.skip -Dimpsort.skip -Dpmd.skip -Dxjc.skip";
    public static final String SMETHODS_ROOT_DIR_NAME = ".smethods";
    public static String PROJECT_PACKAGE = "org/smethods/";
}