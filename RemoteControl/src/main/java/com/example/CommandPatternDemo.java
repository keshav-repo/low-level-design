package com.example;

import com.example.commands.LightOffCommand;
import com.example.commands.LightonCommand;
import com.example.commands.ThermostatOffCommand;
import com.example.commands.ThermostatOnCommand;
import com.example.model.Light;
import com.example.model.Thermostat;

import java.util.HashMap;
import java.util.Map;

public class CommandPatternDemo {
    public static void main(String[] args) {
        Map<CommandName, Command> map = new HashMap<>();

        Light beedroomLight = new Light("Beedroom light");
        Thermostat thermostat = new Thermostat();
        map.put(CommandName.BEDROOM_LIGHT_ON, new LightonCommand(beedroomLight));
        map.put(CommandName.BEDROOM_LIGHT_OFF, new LightOffCommand(beedroomLight));
        map.put(CommandName.THERMOSTAT_OFF, new ThermostatOnCommand(thermostat));
        map.put(CommandName.THERMOSTAT_ON, new ThermostatOffCommand(thermostat));

        Remote remote = new Remote(map);

        remote.executeCommand(CommandName.BEDROOM_LIGHT_ON);
        remote.undoCommand(CommandName.BEDROOM_LIGHT_ON);

        remote.executeCommand(CommandName.THERMOSTAT_ON);

        Light kitchenLight  = new Light("Kitchen light");
        remote.addCommand(CommandName.KITCHEN_LIGHT_ON, new LightonCommand(kitchenLight));
        remote.addCommand(CommandName.KITCHEN_LIGHT_OFF, new LightOffCommand(kitchenLight));

        remote.executeCommand(CommandName.KITCHEN_LIGHT_ON);
    }
}
