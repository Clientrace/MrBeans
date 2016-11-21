package main;

import system_manager.SystemManager;

/**
 * Created by clientrace on 11/21/16.
 */
public class Main {
    public static void main(String[] args){
        SystemManager systemManager = new SystemManager();
        systemManager.init_SystemManager();
        systemManager.execute_SystemManager();
    }
}
