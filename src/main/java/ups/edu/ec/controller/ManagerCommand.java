package ups.edu.ec.controller;

/**
 * 
 * @author grupo_3
 */
import ups.edu.ec.model.ICommand;
import java.util.HashMap;
import java.util.Map;

public class ManagerCommand {
    private Map<String, ICommand> commandMap = new HashMap<>();

    public void registerCommand(String commandName, ICommand command) {
        commandMap.put(commandName, command);
    }

    public ICommand getCommand(String commandName) {
        return commandMap.get(commandName);
    }
}