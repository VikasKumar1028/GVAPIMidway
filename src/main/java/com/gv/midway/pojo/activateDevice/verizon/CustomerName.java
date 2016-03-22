package com.gv.midway.pojo.activateDevice.verizon;

public class CustomerName
{
	 private String firstName;
	 
	
    private String lastName;

    private String middleName;

    private String title;

    public String getMiddleName ()
    {
        return middleName;
    }

    public void setMiddleName (String middleName)
    {
        this.middleName = middleName;
    }

    public String getLastName ()
    {
        return lastName;
    }

    public void setLastName (String lastName)
    {
        this.lastName = lastName;
    }

    public String getTitle ()
    {
        return title;
    }

    public void setTitle (String title)
    {
        this.title = title;
    }

    public String getFirstName ()
    {
        return firstName;
    }

    public void setFirstName (String firstName)
    {
        this.firstName = firstName;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [middleName = "+middleName+", lastName = "+lastName+", title = "+title+", firstName = "+firstName+"]";
    }
}