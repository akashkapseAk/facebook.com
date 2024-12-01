package com.visitor.visitor.exception;

public class ResourceNotFoundException extends RuntimeException{

    String rname;

    String fname;

    long fvalue;

    public ResourceNotFoundException(String rname, String fname, long fvalue) {

        super(String.format("%s not found with %s:%s",rname,fname,fvalue));
        this.rname = rname;
        this.fname = fname;
        this.fvalue = fvalue;
    }
}
