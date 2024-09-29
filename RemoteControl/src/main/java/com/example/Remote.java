package com.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Remote {
    Map<CommandName, Command> commands = new HashMap();

    public Remote(Map<CommandName, Command> commands) {
        this.commands = commands;
    }

    public void addCommand(CommandName commandName, Command onCommand) {
        this.commands.put(commandName, onCommand);
    }

    public void executeCommand(CommandName commandName){
        Command command = this.commands.get(commandName);
        if(Objects.isNull(command)){
            throw new RuntimeException("Command not found");
        }
        command.execute();
    }

    public void undoCommand(CommandName commandName){
        Command command = this.commands.get(commandName);
        if(Objects.isNull(command)){
            throw new RuntimeException("Command not found");
        }
        command.undo();
    }
}
