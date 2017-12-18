/*

    Licensed to the Apache Software Foundation (ASF) under one or more
    contributor license agreements.  See the NOTICE file distributed with
    this work for additional information regarding copyright ownership.
    The ASF licenses this file to You under the Apache License, Version 2.0
    (the "License"); you may not use this file except in compliance with
    the License.  You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
*/
package org.superbiz.struts;

import org.springframework.stereotype.Repository;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Repository
public class UserServiceImpl implements UserService {

    @PersistenceContext
    private EntityManager manager;

    public void add(User user) {
        System.out.println("add user service..");
        manager.persist(user);
        System.out.println("add user serive end..");
    }

    public User find(int id) {
        System.out.println("UserServiceImpl action.." + manager);
        User user = null;
        try {
             user = manager.find(User.class, id);
        } catch (Exception e) {
            e.printStackTrace();;
        }
        System.out.println("UserServiceImpl action end.." + user);
        return user;
    }

    public List<User> findAll() {
        List<User> users = null;
        try {
            users  = manager.createQuery("select u from User u").getResultList();
        } catch (Exception e) {
            e.printStackTrace();;
        }
        return users;
    }

}
