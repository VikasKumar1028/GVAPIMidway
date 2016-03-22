package com.gv.midway.pojo.activateDevice.verizon;

public class PrimaryPlaceOfUse
{
    private Address address;
    
    private CustomerName customerName;
    
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public CustomerName getCustomerName() {
		return customerName;
	}
	public void setCustomerName(CustomerName customerName) {
		this.customerName = customerName;
	}

	@Override
	public String toString() {
		return "PrimaryPlaceOfUse [address = " + address + ", customerName = " + customerName + "]";
	}

}