package com.vehicle.model;

import jakarta.persistence.*;

@Entity
@Table(name = "motorcycles")
@PrimaryKeyJoinColumn(name = "vehicle_id")
public class Motorcycle extends Vehicle {
    private String chainType;
    private Boolean hasKickstand;

    public String getChainType() { return chainType; }
    public Boolean getHasKickstand() { return hasKickstand; }

    public void setChainType(String chainType) { this.chainType = chainType; }
    public void setHasKickstand(Boolean hasKickstand) { this.hasKickstand = hasKickstand; }

}