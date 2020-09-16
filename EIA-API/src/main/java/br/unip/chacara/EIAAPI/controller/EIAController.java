package br.unip.chacara.EIAAPI.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.cloud.sdk.core.security.IamAuthenticator;
import com.ibm.watson.assistant.v2.Assistant;
import com.ibm.watson.assistant.v2.model.CreateSessionOptions;
import com.ibm.watson.assistant.v2.model.MessageInput;
import com.ibm.watson.assistant.v2.model.MessageOptions;
import com.ibm.watson.assistant.v2.model.MessageResponse;
import com.ibm.watson.assistant.v2.model.SessionResponse;

import br.unip.chacara.EIAAPI.controller.dto.WatsonSessionDTO;
import br.unip.chacara.EIAAPI.controller.util.EIAAPIConstants;

@RestController
public class EIAController {

	@RequestMapping("/watson")
	public String watson() {
		return callWatson("Oi");
	}
	
	private String callWatson(String message) {
		IamAuthenticator authenticator = new IamAuthenticator(EIAAPIConstants.APIKey);
		Assistant assistant = new Assistant("2020-04-01", authenticator);
		assistant.setServiceUrl(EIAAPIConstants.APIUrl);

		CreateSessionOptions sessionOptions = new CreateSessionOptions.Builder(EIAAPIConstants.idAssistant).build();

		SessionResponse sessionResponse = assistant.createSession(sessionOptions).execute().getResult();
		
		WatsonSessionDTO session = new WatsonSessionDTO();
		session.setSession_id(sessionResponse.getSessionId());
		
		MessageInput input = new MessageInput.Builder()
		  .messageType("text")
		  .text(message)
		  .build();

		MessageOptions options = new MessageOptions.Builder(EIAAPIConstants.idAssistant, session.getSession_id())
		  .input(input)
		  .build();

		MessageResponse response = assistant.message(options).execute().getResult();	

		return response.toString();
	}
}
