package com.example.commands;

import com.example.Command;
import com.example.model.Thermostat;

public class ThermostatOnCommand implements Command {
    private final Thermostat thermostat;

    public ThermostatOnCommand(Thermostat thermostat) {
        this.thermostat = thermostat;
    }
    @Override
    public void execute() {
        this.thermostat.on();
    }

    @Override
    public void undo() {
        this.thermostat.off();
    }
}
