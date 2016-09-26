package com.dji.utils;

import java.io.IOException;

/**
 * 设计AppiumServer类来进行Server的批量启动控制
 * 
 * @author Charlie.chen
 *
 */
public class AppiumServer {
    
	LogUtil log = new LogUtil(this.getClass());

    public AppiumServer(){      
        killTask("node.exe");
        log.info("init appium server...");
    }

    public void killTask(String taskname){
        String Command = "taskkill /F /im " + taskname;
        log.info("kill " + taskname + " task ...");
        runCommand(Command);
    }

    public void runServer(int port,String udid) {
        log.info("run " + udid + " Appium Server in port " + port + "...");
        int bpport = port +1;
        int chromeport = port + 4792;
        //多设备server端需要手动指定每台设备的udid,安卓无线连接下就是设备的ip:port..
        String Command = "appium.cmd -p " + port + " -bp " + bpport + " --session-override --chromedriver-port "+ chromeport +" -U " 
                         + udid +  " >c://" + port + ".txt";
        log.info(Command);
        runCommand(Command);
    }

    private void runCommand(String command){
        try {
            Runtime.getRuntime().exec(command);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
