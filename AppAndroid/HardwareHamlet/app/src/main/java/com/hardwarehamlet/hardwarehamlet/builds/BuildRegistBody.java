package com.hardwarehamlet.hardwarehamlet.builds;

import com.hardwarehamlet.hardwarehamlet.model.Build_Components;
import com.hardwarehamlet.hardwarehamlet.model.Builds;
import com.hardwarehamlet.hardwarehamlet.model.Components;

import java.util.List;

public class BuildRegistBody {
    private Builds build;
    private List<Build_Components> components;

    public BuildRegistBody(Builds build, List<Build_Components> components) {
        this.build = build;
        this.components = components;
    }

    public Builds getBuild() {
        return build;
    }

    public void setBuild(Builds build) {
        this.build = build;
    }

    public List<Build_Components> getComponents() {
        return components;
    }

    public void setComponents(List<Build_Components> components) {
        this.components = components;
    }
}
