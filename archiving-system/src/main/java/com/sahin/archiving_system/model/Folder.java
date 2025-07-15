package com.sahin.archiving_system.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
public class Folder extends BaseEntity{

    private String name;

    private boolean isInBox;

    public boolean isInBox() {
        return isInBox;
    }

    public void setInBox(boolean inBox) {
        isInBox = inBox;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Folder parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private List<Folder> children = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @OneToMany(mappedBy = "folder", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<File> files;
    public Folder() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<File> getFiles() {
        return files;
    }

    public void setFiles(Set<File> files) {
        this.files = files;
    }

    public Folder(String name, Folder parent, List<Folder> children, User user, Set<File> files, Boolean isInBox) {
        this.name = name;
        this.parent = parent;
        this.isInBox = isInBox;
        this.children = children;
        this.user = user;
        this.files = files;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Folder getParent() {
        return parent;
    }

    public void setParent(Folder parent) {
        this.parent = parent;
    }

    public List<Folder> getChildren() {
        return children;
    }

    public void setChildren(List<Folder> children) {
        this.children = children;
    }
}
