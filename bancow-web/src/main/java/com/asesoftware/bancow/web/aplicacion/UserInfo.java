/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asesoftware.bancow.web.aplicacion;

/**
 *
 * @author Asesoftware
 */
class UserInfo implements com.jcraft.jsch.UserInfo {

    private final String password;
    private final String passPhrase;

    public UserInfo(String password, String passPhrase) {
        this.password = password;
        this.passPhrase = passPhrase;
    }

    public String getPassphrase() {
        return passPhrase;
    }

    public String getPassword() {
        return password;
    }

    public boolean promptPassphrase(String arg0) {
        return true;
    }

    public boolean promptPassword(String arg0) {
        return false;
    }

    public boolean promptYesNo(String arg0) {
        return true;
    }

    public void showMessage(String arg0) {
        System.out.println("SUserInfo.showMessage()");
    }
    
}
