package xbear.javaopenrasp;

import xbear.javaopenrasp.config.Config;
import xbear.javaopenrasp.util.Console;

import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;

/**
 * Created by xbear on 2016/11/13.
 */
public class Agent {

    public static void premain(String agentArgs, Instrumentation inst)
            throws ClassNotFoundException, UnmodifiableClassException {
        Console.log("初始化 RASP 防御模块");
        init();
        inst.addTransformer(new ClassTransformer());
    }

    private static boolean init() {
        Config.initConfig();
        return true;
    }
} 
