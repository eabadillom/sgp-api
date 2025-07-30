package com.ferbo.sgp.api.config;

import java.io.FileInputStream;
import java.io.InputStream;

import javax.annotation.PostConstruct;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

@Configuration
public class FirebaseConfig {

    private static Logger log = LogManager.getLogger(FirebaseConfig.class);

    @Value("${firebase.config.path}")
    private String firebaseConfigPath;

    @PostConstruct
    public void init() throws Exception {
        try {
            InputStream serviceAcount;

            if (firebaseConfigPath.startsWith("classpath:")) {
                String path = firebaseConfigPath.replace("classpath:", "");
                serviceAcount = getClass().getClassLoader().getResourceAsStream(path);
            } else {
                serviceAcount = new FileInputStream(firebaseConfigPath);
            }

            if (serviceAcount == null) {
                log.info( "No se pudo cargar el archivo de credenciales Firebase desde: " + firebaseConfigPath);
                throw new IllegalStateException("No se encontro el archivo de notificaciones");
            }

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAcount)).build();

            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
                log.info("Modulo de notificaciones inicializado correctamente");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception(ex.getMessage());
        }
    }
}
