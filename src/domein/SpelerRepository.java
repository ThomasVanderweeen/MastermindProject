/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import persistentie.SpelerMapper;

/**
 *
 * @author Groep 77
 */
public class SpelerRepository {
    
    private final SpelerMapper mapper;

    public SpelerRepository() {
        this.mapper = new SpelerMapper();
    }
}
