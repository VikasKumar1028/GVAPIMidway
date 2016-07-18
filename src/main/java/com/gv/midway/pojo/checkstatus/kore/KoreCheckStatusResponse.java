package com.gv.midway.pojo.checkstatus.kore;

public class KoreCheckStatusResponse {

    private DKoreCheckStatus d;

    public DKoreCheckStatus getD() {
        return d;
    }

    public void setD(DKoreCheckStatus d) {
        this.d = d;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((d == null) ? 0 : d.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        KoreCheckStatusResponse other = (KoreCheckStatusResponse) obj;
        if (d == null) {
            if (other.d != null)
                return false;
        } else if (!d.equals(other.d))
            return false;
        return true;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("KoreCheckStatusResponse [d=");
        builder.append(d);
        builder.append("]");
        return builder.toString();
    }

}
