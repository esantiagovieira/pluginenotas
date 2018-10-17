package com.pluginenotas.services;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class IbgeService {

	private String apiUFs = "https://servicodados.ibge.gov.br/api/v1/localidades/estados";
	private String apiMunicipioPorUf = "https://servicodados.ibge.gov.br/api/v1/localidades/estados/%s/municipios";
	
	public String buscaUFs(String uf) {
		String idUF="";
		ObjectMapper mapper = new ObjectMapper();
		
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet get = new HttpGet(apiUFs);
		
		CloseableHttpResponse response;
		try {
			response = httpClient.execute(get);
			if(response.getStatusLine().getStatusCode()!=200) {
				//TODO criar handler para erros de retorno
				throw new RuntimeException("Erro na busca de UF: " +  response.getStatusLine().getStatusCode());
			}
			JsonNode rootArray = mapper.readTree(response.getEntity().getContent()); 
			for (JsonNode root : rootArray) {
				if(root.path("sigla").asText().equals(uf)) {
					idUF = root.path("id").asText();
					break;
				}
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return idUF;
	}
	
	public String buscaMunicipioPorUF(Integer idUF, String municipio) {
		String idMunicipio="";
		ObjectMapper mapper = new ObjectMapper();
		
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet get = new HttpGet(String.format(apiMunicipioPorUf,idUF));
		
		try {
			CloseableHttpResponse response = httpClient.execute(get);
			if(response.getStatusLine().getStatusCode()!=200) {
				//TODO criar handler para erros de retorno
				throw new RuntimeException("Erro na busca de munícipio: " +  response.getStatusLine().getStatusCode());
			}
			JsonNode rootArray = mapper.readTree(response.getEntity().getContent());
			for(JsonNode root : rootArray) {
				if(root.path("nome").asText().equals(municipio)) {
					idMunicipio = root.path("id").asText();
				}
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return idMunicipio;
	}
	
}
