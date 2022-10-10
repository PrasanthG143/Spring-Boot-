/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.model;

/**
 *
 * @author glodeveloper
 */

import java.util.List;

import com.example.dao.Views;
import com.fasterxml.jackson.annotation.JsonView;

import org.springframework.web.multipart.MultipartFile;

public class AjaxResponseBody {

	private MultipartFile file;

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

}
