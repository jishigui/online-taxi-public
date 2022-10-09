package com.yix.internalcommon.dto;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class PassengerUserDTO implements Serializable {
    /**
     * 
     */
    private Long id;

    /**
     * 
     */
    private Date gmtCreate;

    /**
     * 
     */
    private Date gmtModifiled;

    /**
     * 
     */
    private String passengerPhone;

    /**
     * 
     */
    private String passengerName;

    /**
     * 
     */
    private Integer passengerGender;

    /**
     * 
     */
    private Integer state;

    private String profilePhone;

    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        PassengerUserDTO other = (PassengerUserDTO) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getGmtCreate() == null ? other.getGmtCreate() == null : this.getGmtCreate().equals(other.getGmtCreate()))
            && (this.getGmtModifiled() == null ? other.getGmtModifiled() == null : this.getGmtModifiled().equals(other.getGmtModifiled()))
            && (this.getPassengerPhone() == null ? other.getPassengerPhone() == null : this.getPassengerPhone().equals(other.getPassengerPhone()))
            && (this.getPassengerName() == null ? other.getPassengerName() == null : this.getPassengerName().equals(other.getPassengerName()))
            && (this.getPassengerGender() == null ? other.getPassengerGender() == null : this.getPassengerGender().equals(other.getPassengerGender()))
            && (this.getState() == null ? other.getState() == null : this.getState().equals(other.getState()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getGmtCreate() == null) ? 0 : getGmtCreate().hashCode());
        result = prime * result + ((getGmtModifiled() == null) ? 0 : getGmtModifiled().hashCode());
        result = prime * result + ((getPassengerPhone() == null) ? 0 : getPassengerPhone().hashCode());
        result = prime * result + ((getPassengerName() == null) ? 0 : getPassengerName().hashCode());
        result = prime * result + ((getPassengerGender() == null) ? 0 : getPassengerGender().hashCode());
        result = prime * result + ((getState() == null) ? 0 : getState().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", gmtCreate=").append(gmtCreate);
        sb.append(", gmtModifiled=").append(gmtModifiled);
        sb.append(", passengerPhone=").append(passengerPhone);
        sb.append(", passengerName=").append(passengerName);
        sb.append(", passengerGender=").append(passengerGender);
        sb.append(", state=").append(state);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}