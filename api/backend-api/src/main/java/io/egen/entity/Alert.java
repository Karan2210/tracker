package io.egen.entity;

import java.util.*;
import javax.persistence.*;

@Entity
@NamedQueries({
        @NamedQuery(name = "Alert.findAll",
                query = "SELECT rea FROM Alert rea order by rea.alertCreationTime desc"),
        @NamedQuery(name = "Alert.findByVin",
                query = "SELECT rea FROM Alert rea WHERE rea.vin=:pVin order by rea.alertCreationTime desc")
})
public class Alert {

    @Id
    @Column(columnDefinition = "varchar(36)")
    private String alertId;
    private String alertPriority;
    private Date alertCreationTime;
    private String alertType;
    private boolean alertCleared;
    private String vin;

    public Alert() {
        this.alertId = UUID.randomUUID()
                .toString();
    }

    public String getAlertId() {
        return alertId;
    }

    public void setAlertId(String alertId) {
        this.alertId = alertId;
    }

    public String getAlertPriority() {
        return alertPriority;
    }

    public void setAlertPriority(String alertPriority) {
        this.alertPriority = alertPriority;
    }

    public String getAlertType() {
        return alertType;
    }

    public void setAlertType(String alertType) {
        this.alertType = alertType;
    }

    public Date getAlertCreationTime() {
        return alertCreationTime;
    }

    public void setAlertCreationTime(Date alertCreationTime) {
        this.alertCreationTime = alertCreationTime;
    }

    public boolean isAlertCleared() {
        return alertCleared;
    }

    public void setAlertCleared(boolean alertCleared) {
        this.alertCleared = alertCleared;
    }

    public String getVin() {
        return vin;

    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    @Override
    public String toString() {
        return "Alert{" +
                "alertId='" + alertId + '\'' +
                ", alertPriority='" + alertPriority + '\'' +
                ", alertCreationTime=" + alertCreationTime +
                ", alertType='" + alertType + '\'' +
                ", alertCleared=" + alertCleared +
                ", vin='" + vin + '\'' +
                '}';
    }
}
