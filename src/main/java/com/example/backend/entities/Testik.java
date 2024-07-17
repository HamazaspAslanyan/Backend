package com.example.backend.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.Set;

public class Testik {


//    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JoinTable(name = "PAPA_BALA",
//            joinColumns = {
//                    @JoinColumn(name = "papa_id", referencedColumnName = "id")
//            },
//            inverseJoinColumns = {
//                    @JoinColumn(name = "bala_id", referencedColumnName = "id")
//            })
//    @JsonManagedReference("balaRef")
//    private Set<Bala> bala_list;
//
//
//
//    @ManyToMany(mappedBy = "bala_list", fetch = FetchType.LAZY)
//    @JsonBackReference
//    private Set<Papa> papaList;




}
