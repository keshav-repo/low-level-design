package com.example.commands;

import com.example.Command;
import com.example.model.Light;

public class LightonCommand implements Command {
    private final Light light;

    public LightonCommand(Light light) {
        this.light = light;
    }
    @Override
    public void execute() {
        this.light.onLight();
    }

    @Override
    public void undo() {
        this.light.offLight();
    }
}
