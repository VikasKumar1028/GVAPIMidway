package com.gv.midway.pojo.callback;
import com.wordnik.swagger.annotations.ApiModelProperty;
public class CallbackSuspendResponse {
	
	@ApiModelProperty(value = "No. of maximum days allowed to suspend device.")
	private String maxSuspendDaysAllowed;

	@ApiModelProperty(value = "Expected Resume Date")
	private String expectedResumeDate;

	@ApiModelProperty(value = "No. of suspended days allowed in current 12 months.")
	private String numDaysSuspendAllowedCurrent12Months;

	@ApiModelProperty(value = "No. of suspended days in last 12 months.")
	private String numDaysSuspendedLast12Months;

    public String getMaxSuspendDaysAllowed ()
    {
        return maxSuspendDaysAllowed;
    }

    public void setMaxSuspendDaysAllowed (String maxSuspendDaysAllowed)
    {
        this.maxSuspendDaysAllowed = maxSuspendDaysAllowed;
    }

    public String getExpectedResumeDate ()
    {
        return expectedResumeDate;
    }

    public void setExpectedResumeDate (String expectedResumeDate)
    {
        this.expectedResumeDate = expectedResumeDate;
    }

    public String getNumDaysSuspendAllowedCurrent12Months ()
    {
        return numDaysSuspendAllowedCurrent12Months;
    }

    public void setNumDaysSuspendAllowedCurrent12Months (String numDaysSuspendAllowedCurrent12Months)
    {
        this.numDaysSuspendAllowedCurrent12Months = numDaysSuspendAllowedCurrent12Months;
    }

    public String getNumDaysSuspendedLast12Months ()
    {
        return numDaysSuspendedLast12Months;
    }

    public void setNumDaysSuspendedLast12Months (String numDaysSuspendedLast12Months)
    {
        this.numDaysSuspendedLast12Months = numDaysSuspendedLast12Months;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [maxSuspendDaysAllowed = "+maxSuspendDaysAllowed+", expectedResumeDate = "+expectedResumeDate+", numDaysSuspendAllowedCurrent12Months = "+numDaysSuspendAllowedCurrent12Months+", numDaysSuspendedLast12Months = "+numDaysSuspendedLast12Months+"]";
    }

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((expectedResumeDate == null) ? 0 : expectedResumeDate
						.hashCode());
		result = prime
				* result
				+ ((maxSuspendDaysAllowed == null) ? 0 : maxSuspendDaysAllowed
						.hashCode());
		result = prime
				* result
				+ ((numDaysSuspendAllowedCurrent12Months == null) ? 0
						: numDaysSuspendAllowedCurrent12Months.hashCode());
		result = prime
				* result
				+ ((numDaysSuspendedLast12Months == null) ? 0
						: numDaysSuspendedLast12Months.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof CallbackSuspendResponse))
			return false;
		CallbackSuspendResponse other = (CallbackSuspendResponse) obj;
		if (expectedResumeDate == null) {
			if (other.expectedResumeDate != null)
				return false;
		} else if (!expectedResumeDate.equals(other.expectedResumeDate))
			return false;
		if (maxSuspendDaysAllowed == null) {
			if (other.maxSuspendDaysAllowed != null)
				return false;
		} else if (!maxSuspendDaysAllowed.equals(other.maxSuspendDaysAllowed))
			return false;
		if (numDaysSuspendAllowedCurrent12Months == null) {
			if (other.numDaysSuspendAllowedCurrent12Months != null)
				return false;
		} else if (!numDaysSuspendAllowedCurrent12Months
				.equals(other.numDaysSuspendAllowedCurrent12Months))
			return false;
		if (numDaysSuspendedLast12Months == null) {
			if (other.numDaysSuspendedLast12Months != null)
				return false;
		} else if (!numDaysSuspendedLast12Months
				.equals(other.numDaysSuspendedLast12Months))
			return false;
		return true;
	}
    
}
