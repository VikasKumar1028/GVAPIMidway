package com.gv.midway.pojo.request;

public class Bs_plan {
	
	 private String data_measure;

	    private Features[] features;

	    private String data_amt;

	    private String bill_day;

	    private String plan_name;

	    private String plan_id;

	    public String getData_measure ()
	    {
	        return data_measure;
	    }

	    public void setData_measure (String data_measure)
	    {
	        this.data_measure = data_measure;
	    }

	    public Features[] getFeatures ()
	    {
	        return features;
	    }

	    public void setFeatures (Features[] features)
	    {
	        this.features = features;
	    }

	    public String getData_amt ()
	    {
	        return data_amt;
	    }

	    public void setData_amt (String data_amt)
	    {
	        this.data_amt = data_amt;
	    }

	    public String getBill_day ()
	    {
	        return bill_day;
	    }

	    public void setBill_day (String bill_day)
	    {
	        this.bill_day = bill_day;
	    }

	    public String getPlan_name ()
	    {
	        return plan_name;
	    }

	    public void setPlan_name (String plan_name)
	    {
	        this.plan_name = plan_name;
	    }

	    public String getPlan_id ()
	    {
	        return plan_id;
	    }

	    public void setPlan_id (String plan_id)
	    {
	        this.plan_id = plan_id;
	    }

	    @Override
	    public String toString()
	    {
	        return "ClassPojo [data_measure = "+data_measure+", features = "+features+", data_amt = "+data_amt+", bill_day = "+bill_day+", plan_name = "+plan_name+", plan_id = "+plan_id+"]";
	    }

}
