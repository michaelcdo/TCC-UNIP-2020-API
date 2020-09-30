package br.unip.chacara.EIAAPI.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.cloud.sdk.core.security.IamAuthenticator;
import com.ibm.watson.assistant.v2.Assistant;
import com.ibm.watson.assistant.v2.model.CreateSessionOptions;
import com.ibm.watson.assistant.v2.model.MessageInput;
import com.ibm.watson.assistant.v2.model.MessageOptions;
import com.ibm.watson.assistant.v2.model.MessageResponse;
import com.ibm.watson.assistant.v2.model.SessionResponse;

import br.unip.chacara.EIAAPI.controller.dto.MensagemWatsonDTO;
import br.unip.chacara.EIAAPI.controller.util.EIAAPIConstants;
import br.unip.chacara.EIAAPI.model.User;
import br.unip.chacara.EIAAPI.model.WatsonSession;

@Service
public class WatsonService {

	@Autowired
	private UserService userService;
	
	@Autowired
	private WatsonSessionService watsonSessionService;
	
	public MensagemWatsonDTO callWatson(MensagemWatsonDTO message) {
		if(message.getIdUser() == 0){
			User user = new User();			
			user.setEmail("");
			user.setName("");
			user = userService.saveUser(user);
			message.setIdUser(user.getId());
		}
		
		String watsonSession = getSession(message.getIdUser());
		
		IamAuthenticator authenticator = new IamAuthenticator(EIAAPIConstants.APIKey);
		Assistant assistant = new Assistant("2020-04-01", authenticator);
		assistant.setServiceUrl(EIAAPIConstants.APIUrl);
		
		MessageInput input = new MessageInput.Builder()
		  .messageType("text")
		  .text(message.getMensagemEntrada())
		  .build();

		MessageOptions options = new MessageOptions.Builder(EIAAPIConstants.idAssistant, watsonSession)
		  .input(input)
		  .build();

		MessageResponse response = assistant.message(options).execute().getResult();	
		if(response.getOutput().getGeneric().size() > 0) {
			message.setMensagemRetorno(response.getOutput().getGeneric().get(0).text());
		}else {
			message.setMensagemRetorno("");
		}
		return message;
	}
	
	public String getSession(long idUser) {
		try {
			WatsonSession watsonSession = watsonSessionService.getWatsonSessionByIsUser(idUser);
			if(watsonSession != null && validaData(watsonSession.getDtSession())) {
				return watsonSession.getSessionHash();
			}else {
				String sessionHash = generateSession();
				if(watsonSession == null) {
					watsonSession = new WatsonSession();
				}
				watsonSession.setIdUser(idUser);
				watsonSession.setDtSession(new Date());
				watsonSession.setSessionHash(sessionHash);
				watsonSessionService.saveWatsonSession(watsonSession);
				
				return watsonSession.getSessionHash();
			}
		}catch (Exception e) {
			return null;
		}
	}
	
	private String generateSession() {
		IamAuthenticator authenticator = new IamAuthenticator(EIAAPIConstants.APIKey);
		Assistant assistant = new Assistant("2020-04-01", authenticator);
		assistant.setServiceUrl(EIAAPIConstants.APIUrl);

		CreateSessionOptions sessionOptions = new CreateSessionOptions.Builder(EIAAPIConstants.idAssistant).build();

		SessionResponse sessionResponse = assistant.createSession(sessionOptions).execute().getResult();
		
		return sessionResponse.getSessionId();
	}
	
	private static Boolean validaData(Date data) {
		Date dataAtual = new Date();
		
		// Calculate time difference 
        // in milliseconds 
        long difference_In_Time 
            = dataAtual.getTime() - data.getTime(); 
        
        //Calculate time difference 
        // in minutes
        long difference_In_Minutes 
        = (difference_In_Time 
           / (1000 * 60)) 
          % 60; 
        
        if(difference_In_Minutes > 4)
        	return false;
        
        return true;
	}
}
