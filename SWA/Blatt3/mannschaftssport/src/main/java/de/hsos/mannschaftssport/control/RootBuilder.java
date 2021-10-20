package de.hsos.mannschaftssport.control;

import java.util.ArrayList;
import java.util.Arrays;

import de.hsos.mannschaftssport.control.dto.Data;
import de.hsos.mannschaftssport.control.dto.Error;
import de.hsos.mannschaftssport.control.dto.Links;
import de.hsos.mannschaftssport.control.dto.Root;

public class RootBuilder {

    private Root root;

    private ArrayList<Error> errors;
    private ArrayList<Data> included;

    public RootBuilder() {
        this.root = new Root();
        this.errors = new ArrayList<>();
        this.included = new ArrayList<>();
    }

    public RootBuilder data(Data data) {
        this.root.setData(data);
        return this;
    }

    public RootBuilder data(Data[] data) {
        this.root.setData(data);
        return this;
    }

    public RootBuilder error(Error... errors) {
        this.errors.addAll(Arrays.asList(errors));
        return this;
    }

    public RootBuilder included(Data... included) {
        this.included.addAll(Arrays.asList(included));
        return this;
    }

    public RootBuilder links(Links links) {
        this.root.setLinks(links);
        return this;
    }


    public Root build() {
        if (this.errors.size() > 0) {
            this.root.setErrors(this.errors.toArray(new Error[0]));
        }
        if (this.included.size() > 0) {
            this.root.setIncluded(this.included.toArray(new Data[0]));
        }
        return root;
    }
}
