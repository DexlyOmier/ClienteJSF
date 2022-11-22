package com.jsf.examen.view;

import com.jsf.examen.entity.MusicBand;
import com.jsf.examen.service.MusicBandService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Scope(value = "session")
@Component
@Data
public class MusicBandController implements Serializable {

    private List<MusicBand> musicBandList;
    private MusicBandService musicBandService;
    private MusicBand newMusicBand;
    private MusicBand selectedMusicBand;

    @Autowired
    public MusicBandController(MusicBandService musicBandService) {
        this.musicBandService = musicBandService;
    }

    @PostConstruct
    public void init(){
        loadMusicBandList();
        newMusicBand = new MusicBand();
    }

    public List<MusicBand> loadMusicBandList() {
        musicBandList = new ArrayList<>();
        musicBandList = musicBandService.getAllMusicBand();
        return musicBandList;
    }

    public void deleteMusicBand(){
        if(selectedMusicBand != null){
            musicBandService.deleteMusicBand(selectedMusicBand);
            addMessage(FacesMessage.SEVERITY_INFO, "ELIMINADO", "ELIMINADO EXITOSAMENTE");
            loadMusicBandList();
        }
    }

    public void createNewMusicBand(){
        if(newMusicBand.getBandName() == null || newMusicBand.getBandName().equals("")){
            addMessage(FacesMessage.SEVERITY_WARN, "ERROR", "El campo nombre no puede quedar vacio");
            return;
        }
        if(newMusicBand.getCountry() == null || newMusicBand.getCountry().equals("")){
            addMessage(FacesMessage.SEVERITY_WARN, "ELIMINADO", "El campo country no puede quedar vacio");
            return;
        }

        musicBandService.saveNewMusicBand(newMusicBand);
        newMusicBand = new MusicBand();
        addMessage(FacesMessage.SEVERITY_INFO, "GUARDADO", "GUARDADO EXITOSAMENTE");
        loadMusicBandList();
    }

    public void updateMusicBand(){
        if(selectedMusicBand != null) {
            if(newMusicBand.getBandName() == null || newMusicBand.getBandName().equals("")){
                addMessage(FacesMessage.SEVERITY_WARN, "ERROR", "El campo nombre no puede quedar vacio");
                return;
            }
            if(newMusicBand.getCountry() == null || newMusicBand.getCountry().equals("")){
                addMessage(FacesMessage.SEVERITY_WARN, "ELIMINADO", "El campo country no puede quedar vacio");
                return;
            }
            selectedMusicBand.setBandName(newMusicBand.getBandName());
            selectedMusicBand.setCountry(newMusicBand.getCountry());
            musicBandService.updateMusicBand(selectedMusicBand);
            newMusicBand = new MusicBand();
            addMessage(FacesMessage.SEVERITY_INFO, "ACTUALIZADO", "ACTUALIZADO EXITOSAMENTE");
            loadMusicBandList();
        }
    }

    public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(severity, summary, detail));
    }
}
