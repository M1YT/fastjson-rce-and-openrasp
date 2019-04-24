package xbear.javaopenrasp.filters.rce;

import org.apache.commons.lang3.StringUtils;
import xbear.javaopenrasp.config.Config;
import xbear.javaopenrasp.filters.SecurityFilterI;
import xbear.javaopenrasp.util.Console;
import xbear.javaopenrasp.util.StackTrace;

import java.util.List;

/**
 * Created by xbear on 2016/11/13.
 */
public class PrcessBuilderFilter implements SecurityFilterI {

    @Override
    public boolean filter(Object forCheck) {
        String moduleName = "java/lang/ProcessBuilder";
        List<String> commandList = (List<String>) forCheck;
        String command = StringUtils.join(commandList, " ").trim().toLowerCase();
        Console.log("即将执行命令：" + command);
        String mode = (String) Config.moduleMap.get(moduleName).get("mode");
        switch (mode) {
            case "block":
                Console.log("> 阻止执行命令：" + command);
                return false;
            case "white":
                if (Config.isWhite(moduleName, command)) {
                    Console.log("> 允许执行命令：" + command);
                    return true;
                }
                Console.log("> 阻止执行命令：" + command);
                return false;
            case "black":
                if (Config.isBlack(moduleName, command)) {
                    Console.log("> 阻止执行命令：" + command);
                    return false;
                }
                Console.log("> 允许执行命令：" + command);
                return true;
            case "log":
            default:
                Console.log("> 允许执行命令：" + command);
                Console.log("> 输出打印调用栈\r\n" + StackTrace.getStackTrace());
                return true;
        }
    }

}
