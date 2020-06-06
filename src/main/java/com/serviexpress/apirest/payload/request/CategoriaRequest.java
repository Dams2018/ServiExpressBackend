package com.serviexpress.apirest.payload.request;

import java.io.Serializable;

public class CategoriaRequest implements Serializable{

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    
    private long id;
    private String name;
    private String description;

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @param name
     * @param description
     */
    public CategoriaRequest(String name, String description) {
        this.name = name;
        this.description = description;
    }

    

    /**
     * 
     */
    public CategoriaRequest() {
    }

    /**
     * @param id
     * @param name
     * @param description
     */
    public CategoriaRequest(long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }



    
    
    

    /**
     * @return int return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }

}