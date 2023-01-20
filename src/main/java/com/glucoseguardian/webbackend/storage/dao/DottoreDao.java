package com.glucoseguardian.webbackend.storage.dao;

import com.glucoseguardian.webbackend.storage.entity.Dottore;
import org.springframework.data.repository.CrudRepository;
/**
 * This will be AUTO IMPLEMENTED by Spring into a Bean called DottoreDao.
 * CRUD refers Create, Read, Update, Delete.
 */

public interface DottoreDao extends CrudRepository<Dottore, String> {

}