package com.saveheart.dto;

public class UpdateDto {

    private String email;
    private String fname;
    private String lname;
    private String address;


    public UpdateDto() {
        super();
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }



    public UpdateDto(String email, String fname, String lname, String address) {
        super();

        this.email = email;
        this.fname = fname;
        this.lname = lname;
        this.address = address;
    }
}
