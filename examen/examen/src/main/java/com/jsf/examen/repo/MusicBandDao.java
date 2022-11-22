package com.jsf.examen.repo;

import com.jsf.examen.entity.MusicBand;
import org.springframework.data.repository.CrudRepository;

public interface MusicBandDao extends CrudRepository<MusicBand,Long> {
}
