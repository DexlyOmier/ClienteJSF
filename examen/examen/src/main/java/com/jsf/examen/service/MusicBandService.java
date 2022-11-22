package com.jsf.examen.service;

import com.jsf.examen.entity.MusicBand;
import com.jsf.examen.repo.MusicBandDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MusicBandService {

    private MusicBandDao musicBandDao;

    @Autowired
    public MusicBandService(MusicBandDao musicBandDao) {
        this.musicBandDao = musicBandDao;
    }

    public List<MusicBand> getAllMusicBand(){
        return (List<MusicBand>) musicBandDao.findAll();
    }

    public void saveNewMusicBand(MusicBand newMusicBand){
        musicBandDao.save(newMusicBand);
    }

    public void updateMusicBand(MusicBand musicBand){
        musicBandDao.save(musicBand);
    }

    public void deleteMusicBand(MusicBand musicBand){
        musicBandDao.delete(musicBand);
    }
}
