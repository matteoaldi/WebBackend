package com.glucoseguardian.webbackend.application.service.farmaco;

import com.glucoseguardian.webbackend.storage.dao.FarmacoDao;
import com.glucoseguardian.webbackend.storage.dto.FarmacoDto;
import com.glucoseguardian.webbackend.storage.entity.Farmaco;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.collections4.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This is an implementation of FarmacoServiceInterface.
 */
@Service
public class FarmacoServiceConcrete implements FarmacoServiceInterface {

  @Autowired
  FarmacoDao farmacoDao;

  @Override
  public FarmacoDto findById(Long id) {
    Farmaco result = farmacoDao.findById(id).orElse(null);
    if (result != null) {
      return FarmacoDto.valueOf(result);
    } else {
      throw new RuntimeException("Farmaco non trovato.");
    }
  }

  @Override
  public List<FarmacoDto> findFarmaco(String ricerca) {
    List<Farmaco> listNome = farmacoDao.findByNomeFarmacoContainingIgnoreCase(ricerca);
    List<Farmaco> listPrincipio = farmacoDao.findByPrincipioAttivoContainingIgnoreCase(ricerca);
    List<Farmaco> listConfezione = farmacoDao.findByConfezioneContainingIgnoreCase(ricerca);
    List<Farmaco> list = ListUtils.union(listNome, ListUtils.union(listPrincipio, listConfezione));

    List<FarmacoDto> ricercaFarmaci = new ArrayList<>();
    for (Farmaco farmaco : list) {
      ricercaFarmaci.add(FarmacoDto.valueOf(farmaco));
    }
    return ricercaFarmaci;
  }
}