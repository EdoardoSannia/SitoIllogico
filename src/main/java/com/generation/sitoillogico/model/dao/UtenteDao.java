package com.generation.sitoillogico.model.dao;

import com.generation.sitoillogico.model.entities.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UtenteDao extends JpaRepository<Utente, Long>
{
    Utente findByUsernameAndPassword(String username,String password);
}
