package com.glucoseguardian.webbackend.storage.dao;

import com.glucoseguardian.webbackend.storage.entity.Admin;
import org.springframework.data.repository.CrudRepository;

/**
 * This will be AUTO IMPLEMENTED by Spring into a Bean called AdminDao.
 * CRUD refers Create, Read, Update, Delete.
 */
public interface AdminDao extends CrudRepository<Admin, String> {

}