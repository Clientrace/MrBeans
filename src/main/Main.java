package main;

import system_manager.SystemManager;

/**
 * Created by clientrace on 11/21/16.
 */
public class Main {
    public static void main(String[] args){
        System.out.println("\t\t\t\t.--.\n" +
                "\t\t\t   /    \\\t\t . .)\n" +
                "\t\t\t  ## -  -       (.\n" +
                "\t\t\t  (   '._)     ___\n" +
                "\t\t\t   |'-- |      | | \n" +
                "\t\t\t _.\\___/_   ___|_|___\n" +
                "\t\t   .\"\\> \\Y/|<'.  '._.-'\n" +
                "\t\t  /  \\ \\_\\/ /  '-' /\n" +
                "\t\tMR. BEANS (c) 2016\n" +
                "\t\tCoffee Bean Analyzer\n");

        SystemManager systemManager = new SystemManager();
        systemManager.init_SystemManager();
        systemManager.execute_SystemManager();
    }
}