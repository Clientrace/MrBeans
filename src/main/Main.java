package main;

import org.opencv.core.Core;
import system_manager.SystemManager;

/**
 * Created by clientrace on 11/21/16.
 */
public class Main {
    public static void main(String[] args){
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        System.out.println("");
        System.out.println("" +
                "\t\t\t        .--.\n" +
                "\t\t\t       /    \\     .)\n" +
                "\t\t\t      ## -  -      (.\n" +
                "\t\t\t      (   '._)    ___\n" +
                "\t\t\t       |'-- |     | | \n" +
                "\t\t\t     _.\\____/_ ___|_|___\n" +
                "\t\t\t   .\"\\> \\Y/|<'.  '._.-'\n" +
                "\t\t\t   /  \\ \\_\\/ /  '-' /\n" +
                "\t\t-------------------------------------------\n" +
                "\t\t    | \\/ | |  )   |  ) | _  /\\  | \\ | (\n" +
                "\t\t    |    | |  \\ o |  ) | _ /  \\ |  \\|  )\n" +
                "\t\t-------------------------------------------\n" +
                "\t\tMR. BEANS (c) 2016 [ Coffee Bean Analyzer ]\n");
        SystemManager systemManager = new SystemManager();
        systemManager.init_SystemManager();
        systemManager.execute_SystemManager();
    }
}