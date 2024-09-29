package com.example.commands;

import com.example.Command;
import com.example.model.Thermostat;

public class ThermostatOffCommand implements Command {
    private final Thermostat thermostat;

    public ThermostatOffCommand(Thermostat thermostat) {
        this.thermostat = thermostat;
    }
    @Override
    public void execute() {
        this.thermostat.off();
    }

    @Override
    public void undo() {
        this.thermostat.on();
    }
}
