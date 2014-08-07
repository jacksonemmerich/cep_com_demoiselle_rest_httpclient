package br.com.jacksonemmerich.webservicerest.persistence;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.codehaus.jackson.map.ObjectMapper;

import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.JPACrud;
import br.com.jacksonemmerich.webservicerest.domain.Endereco;

@PersistenceController
public class EnderecoDAO extends JPACrud<Endereco, Long> {

	private static final long serialVersionUID = 1L;
	Endereco endereco;

	public Endereco getBuscaEndPorCEP(String cep) {
		HttpClient httpClient = new HttpClient();

		HttpMethod method = new GetMethod(
				"http://correiosapi.apphb.com/cep/" + cep);
		

		try {
			httpClient.executeMethod(method);
		} catch (HttpException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		String responseBody;
		
		try {
			responseBody = method.getResponseBodyAsString();
			
			//2. Convert JSON to Java object
			ObjectMapper mapper = new ObjectMapper();
			endereco = mapper.readValue(responseBody, Endereco.class);

			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return endereco;
	}
}
