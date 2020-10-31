package br.unip.chacara.EIAAPI.service;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.cloud.sdk.core.security.IamAuthenticator;
import com.ibm.watson.assistant.v2.Assistant;
import com.ibm.watson.assistant.v2.model.CreateSessionOptions;
import com.ibm.watson.assistant.v2.model.MessageInput;
import com.ibm.watson.assistant.v2.model.MessageOptions;
import com.ibm.watson.assistant.v2.model.MessageResponse;
import com.ibm.watson.assistant.v2.model.RuntimeResponseGeneric;
import com.ibm.watson.assistant.v2.model.SessionResponse;

import br.unip.chacara.EIAAPI.controller.dto.MensagemWatsonDTO;
import br.unip.chacara.EIAAPI.controller.util.EIAAPIConstants;
import br.unip.chacara.EIAAPI.model.Humor;
import br.unip.chacara.EIAAPI.model.User;
import br.unip.chacara.EIAAPI.model.WatsonSession;

@Service
public class WatsonService {

	@Autowired
	private HumorService humorService;
	
	@Autowired
	private WatsonSessionService watsonSessionService;
	
	public MensagemWatsonDTO callWatson(MensagemWatsonDTO message) {
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
		
		message.setMensagemRetorno(trataMensagens(response, message.getIdUser()));
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
	private ArrayList<String> trataMensagens(MessageResponse response, long idUser) {
		ArrayList<String> respostas = new ArrayList<>();
		for (RuntimeResponseGeneric resp : response.getOutput().getGeneric()) {
			if(resp.text()!=null) {
				String rep = resp.text();
				for (int i = 0; i < EIAAPIConstants.EMOTIONS.length; i++) {
					if(rep.contains(EIAAPIConstants.EMOTIONS[i])) {
						rep.replace(EIAAPIConstants.EMOTIONS[i], "");
						Humor hm = new Humor();
						hm.setDtGravacao(new Date());
						hm.setCodHumor(i+1);
						hm.setIdUser(idUser);
						humorService.saveHumor(hm);
					}
				}
				if(!rep.equals("")) {
					respostas.add(rep);
				}
			}
		}
		return respostas;
	}
}
