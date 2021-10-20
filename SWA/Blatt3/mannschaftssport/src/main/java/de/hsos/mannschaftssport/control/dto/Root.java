package de.hsos.mannschaftssport.control.dto;

public class Root {
    
    private Data data;
    private Data[] dataArray;
    private Links links;
    private Data[] included;
    private Error[] errors;

    public Root(){};
    
    public Root(Data data, Links links, Data[] included) {
        this(links,included);
        this.data = data;
        this.dataArray = null;
    }

    public Root(Data[] data, Links links, Data[] included) {
        this(links,included);
        this.data = null;
        this.dataArray = data;
    }

    private Root(Links links, Data[] included){
        this.links = links;
        this.included = included;
    }


    public Object getData() {
        if(data != null){
            return data;
        }
        return dataArray;
    }


    public void setData(Data data) {
        this.data = data;
        this.dataArray = null;
    }

    public void setData(Data[] data){
        this.dataArray = data;
        this.data = null;
    }


    public Links getLinks() {
        return links;
    }


    public void setLinks(Links links) {
        this.links = links;
    }




    public Data[] getIncluded() {
        return included;
    }


    public void setIncluded(Data[] included) {
        this.included = included;
    }

    public Error[] getErrors() {
        return errors;
    }

    public void setErrors(Error[] errors) {
        this.errors = errors;
    }

    

}
