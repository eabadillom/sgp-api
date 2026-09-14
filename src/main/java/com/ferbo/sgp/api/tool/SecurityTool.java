package com.ferbo.sgp.api.tool;

import com.ferbo.tools.exception.SystemException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.LengthRule;
import org.passay.PasswordData;
import org.passay.PasswordGenerator;
import org.passay.PasswordValidator;
import org.passay.Rule;
import org.passay.RuleResult;
import org.passay.WhitespaceRule;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ferbo.tools.exception.ToolException;
import com.ferbo.tools.exception.ValidationException;

@Service
public class SecurityTool {
	
private static Logger log = LogManager.getLogger(SecurityTool.class);
	
	public String getRandomString() {
		String randomString = null;
		CharacterRule alphabets  = null;
		CharacterRule digits     = null;
		PasswordGenerator pwdGen = null;
		
		//Generar cadena de caracteres aleatoria en claro
		alphabets = new CharacterRule(EnglishCharacterData.Alphabetical);
		digits    = new CharacterRule(EnglishCharacterData.Digit);
		pwdGen    = new PasswordGenerator();
		
		randomString = pwdGen.generatePassword(20, alphabets, digits);
		log.debug("Random String: {}", randomString);
		
		return randomString;
	}
	
	public String getSHA512(String texto) {
		String hash = null;
		hash = DigestUtils.sha512Hex(texto);
		return hash;
	}

	public void checkPassword(String password)
	throws Exception {
		List<Rule> rules = new ArrayList<>();
		//Rule 1: Password length should be in between 
        //8 and 16 characters
        rules.add(new LengthRule(8, 16));
        //Rule 2: No whitespace allowed
        rules.add(new WhitespaceRule());        
        //Rule 3.a: At least one Upper-case character
        rules.add(new CharacterRule(EnglishCharacterData.UpperCase, 1));        
        //Rule 3.b: At least one Lower-case character
        rules.add(new CharacterRule(EnglishCharacterData.LowerCase, 1));        
        //Rule 3.c: At least one digit
        rules.add(new CharacterRule(EnglishCharacterData.Digit, 1));        
        //Rule 3.d: At least one special character
        rules.add(new CharacterRule(EnglishCharacterData.Special, 1));
        
        PasswordValidator validator = new PasswordValidator(rules);        
        PasswordData pwdData = new PasswordData(password);        
        RuleResult result = validator.validate(pwdData);
        
        if(result.isValid() == false){
        	throw new Exception("Contraseña no válida: " + validator.getMessages(result));
        }
	}

	public String[] extractCredentials(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        
        if (authorizationHeader != null && authorizationHeader.startsWith("Basic ")) {
            
            String base64Credentials = authorizationHeader.substring(6);
            String credentials = new String(Base64.getDecoder().decode(base64Credentials));

            String[] values = credentials.split(":", 2);
            return values;
        }

        return null; 
    }

    public String cifrarBCrypt(String psw) throws ToolException{
        String  cifrada = "";
        try{
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        cifrada = passwordEncoder.encode(psw);
        }
        catch(Exception ex){
            log.info("Error: no se pudo cifrar la contrasenia dada.");
            throw new ToolException("La contrasenia no se pudo cifrar");
        }
        return cifrada;
    }
    
    public String extractBearerToken(HttpServletRequest request) {

        if (request == null) {
            throw new ValidationException("La solicitud no puede ser vacía");
        }

        String authorization = request.getHeader("Authorization");

        if (authorization == null || authorization.trim().isEmpty()) {
            throw new SystemException("La solicitud no incluye el header Authorization");
        }

        if (!authorization.startsWith("Bearer ")) {
            throw new ToolException("La solicitud no incluye un bearer token");
        }

        String token = authorization.substring(7).trim();

        if (token.isEmpty()) {
            throw new ToolException("La solicitud no incluye un bearer token");
        }

        return token;
    }

}
