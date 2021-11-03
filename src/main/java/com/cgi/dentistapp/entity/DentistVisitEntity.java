package com.cgi.dentistapp.entity;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@Table(name = "dentist_visit")
public class DentistVisitEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String dentistName;
    private Calendar visitTime;

    public DentistVisitEntity(String dentistName, Calendar visitTime) {
        this.dentistName = dentistName;
        this.visitTime = visitTime;
    }
    public DentistVisitEntity(){
        super();
    }

    public Long getId() {
        return id;
    }

    public Calendar getVisitTime() {
        return visitTime;
    }

    public String getDentistName() {
        return dentistName;
    }

    public void setDentistName(String dentistName) {
        this.dentistName = dentistName;
    }

    public void setVisitTime(Calendar visitTime) {
        this.visitTime = visitTime;
    }
}
