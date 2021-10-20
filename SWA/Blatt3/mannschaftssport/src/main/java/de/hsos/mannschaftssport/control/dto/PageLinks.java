package de.hsos.mannschaftssport.control.dto;

public class PageLinks extends Links {
    private String first;
    private String prev;
    private String next;
    private String last;

    public PageLinks(String first, String prev, String next, String last) {
        this.first = first;
        this.prev = prev;
        this.next = next;
        this.last = last;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getPrev() {
        return prev;
    }

    public void setPrev(String prev) {
        this.prev = prev;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

}