package com.gv.midway.pojo.deactivateDevice.request;
 
import java.util.Arrays;
 
public class DeactivateDevices {
      
       private DeactivateDeviceId[] deviceIds;
 
       public DeactivateDeviceId[] getDeviceIds() {
              return deviceIds;
       }
 
       public void setDeviceIds(DeactivateDeviceId[] deviceIds) {
              this.deviceIds = deviceIds;
       }
 
       @Override
       public int hashCode() {
              final int prime = 31;
              int result = 1;
              result = prime * result + Arrays.hashCode(deviceIds);
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
              DeactivateDevices other = (DeactivateDevices) obj;
              if (!Arrays.equals(deviceIds, other.deviceIds))
                     return false;
              return true;
       }
 
       @Override
       public String toString() {
              return "DeactivateDevices [deviceIds=" + Arrays.toString(deviceIds)
                           + "]";
       }
 
}

