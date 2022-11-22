package com.jsf.examen.entity;

import lombok.Data;
import javax.persistence.*;

@Entity
@Data
@Table(name = "MusicBand")
public class MusicBand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BandID")
    private Long bandId;

    @Column(name = "BandName")
    private String bandName;

    @Column(name = "Country")
    private String country;
}
