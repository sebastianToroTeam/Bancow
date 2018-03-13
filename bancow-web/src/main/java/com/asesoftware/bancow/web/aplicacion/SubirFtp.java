/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asesoftware.bancow.web.aplicacion;

import Utilidades.Constantes;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import java.io.InputStream;

/**
 *
 * @author Asesoftware
 */
public class SubirFtp {

    private static final Integer port = 22;

    
    public static boolean subirArchivoFtp(String nombreArchivo, InputStream inputstreamFile) {
        System.out.println("------------------- INICIO");
        boolean respuesta = false;
        ChannelSftp sftp = null;
        Session session = null;
        try {
            JSch jsch = new JSch();
            session = jsch.getSession(Constantes.USER, Constantes.SERVER, port);
            UserInfo ui = new UserInfo(Constantes.PASSWORD, null);

            session.setUserInfo(ui);
            session.setPassword(Constantes.PASSWORD);
            session.connect();

            sftp = (ChannelSftp) session.openChannel("sftp");
            sftp.connect();

            sftp.cd(Constantes.RUTA_SERVER);
            System.out.println(nombreArchivo);
            sftp.put(inputstreamFile, nombreArchivo);

            System.out.println("Archivos subidos.");           
            respuesta = true;
            System.out.println("----------------- FIN");
        } catch (Exception ex) {
            respuesta = false;
        } finally {
            if (sftp != null) {
                sftp.exit();
                sftp.disconnect();
            }
            if (session != null) {
                session.disconnect();
            }
        }
        return respuesta;
    }

   
}
