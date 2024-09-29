package com.example.commands;

import com.example.Command;
import com.example.model.Light;

public class LightOffCommand implements Command {
    private final Light light;

    public LightOffCommand(Light light) {
        this.light = light;
    }
    @Override
    public void execute() {
        this.light.offLight();
    }

    @Override
    public void undo() {
        this.light.onLight();
    }
}
