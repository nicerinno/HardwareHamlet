package com.hardwarehamlet.hardwarehamlet.model;

import java.util.List;

public class BuildsResponseBody {
    private Builds build;
    private List<Build_Components> components;
    private List<Comments> comments;

    public BuildsResponseBody(Builds build, List<Build_Components> components, List<Comments> comments) {
        this.build = build;
        this.components = components;
        this.comments = comments;
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

    public List<Comments> getComments() {
        return comments;
    }

    public void setComments(List<Comments> comments) {
        this.comments = comments;
    }
}
