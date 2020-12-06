/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package silab.nst.dan9.dataaccess.service;

import silab.nst.dan9.dataaccess.domain.User;

import java.util.List;

/**
 *
 * @author laptop-02
 */
public interface UserService {
    User add(User user) throws Exception;
    void delete(Long id) throws Exception;
    User update(User user)throws Exception;
    List<User> getAll() throws Exception;
}
