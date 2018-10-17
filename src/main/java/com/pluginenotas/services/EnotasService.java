package com.pluginenotas.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.sql.ResultSet;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.pluginenotas.utils.MYSQLAccess;

@Service
public class EnotasService {
	
	private String apiKey = "NGUyY2UwODItZTRmNi00ZThjLTkwNjMtZjgwYTU2ZjUwMjAw";
	private String vincularLogotipo = "https://api.enotasgw.com.br/v1/empresas/%s/logo";
	private String incluirEmpresa = "https://api.enotasgw.com.br/v1/empresas";
	private String listarEmpresa = "https://api.enotasgw.com.br/v2/empresas";
	private String idEmpresa = "3979C6D4-FD67-4B3F-A720-3E5EEE100400";
	
	@Autowired
	IbgeService ibgeService;
	
public String enviarlogo (String idempresa ) throws ClientProtocolException, IOException {
		
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost post = new HttpPost(String.format(vincularLogotipo, idEmpresa));
		MultipartEntityBuilder builder = MultipartEntityBuilder.create();
		post.addHeader("Authorization", "Basic " + apiKey);

		File f = new File("C:/volumes/logo.img.jpg");
		builder.addBinaryBody(
		    "logotipo",
		    new FileInputStream(f),
		    ContentType.APPLICATION_OCTET_STREAM,
		    f.getName()
		);

		HttpEntity multipart = builder.build();
		post.setEntity(multipart);
		CloseableHttpResponse response = httpClient.execute(post);
		
		if(response.getStatusLine().getStatusCode() !=200) {
			throw new RuntimeException("Servidor retornou erro :" + response.getStatusLine().getStatusCode() 
					+ "-" + response.getStatusLine().getStatusCode());
		}
		
		String resposta = response.getStatusLine().getStatusCode() + " " + response.getStatusLine().getReasonPhrase();
		response.close();
		return resposta;
	}
	
	public String consultaCaracteristicas() throws ClientProtocolException, IOException{
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet get = new HttpGet("https://api.enotasgw.com.br/v1/estados/cidades/1501402/provedor");
		get.addHeader("Authorization", "Basic " + apiKey);
		
		try {
			CloseableHttpResponse response = httpClient.execute(get);
			BufferedReader rd = new BufferedReader(
					new InputStreamReader(response.getEntity().getContent()));

				StringBuffer result = new StringBuffer();
				String line = "";
				while ((line = rd.readLine()) != null) {
					result.append(line);
					System.out.println(line);
				}
		} catch (Exception e) {
			throw new RuntimeException("Servidor retornou erro :" + e.getMessage()); 
		}
		
		return null;
	}
	
	public String enviarEmpresa(String json)  {
		String idEmpresa=null;
		ObjectMapper mapper = new ObjectMapper();
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost post = new HttpPost(incluirEmpresa);
		post.setHeader("Content-Type", "application/json");
		post.setHeader("Accept", "application/json");
		post.setHeader("Authorization", "Basic " + apiKey);
		try {
			StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
			post.setEntity(entity);
			System.out.println("Json enviado para o enotas:");
			System.out.println(json);
			CloseableHttpResponse response = httpClient.execute(post);
			System.out.println("Resposta do servidor: " + response.getStatusLine().getStatusCode() + " - " + response.getStatusLine().getReasonPhrase());
			
			JsonNode rootArray = mapper.readTree(response.getEntity().getContent());
			for(JsonNode root : rootArray) {
				if(root.has("idEmpresa")) {
					idEmpresa = root.path("idEmpresa").asText();
				}
				else {
					idEmpresa = root.path("mensagem").asText();
				}
			}
		}
		catch (Exception e) {
			throw new RuntimeException("Erro ao cadastrar empresa: " + e.getMessage());
		}
		return idEmpresa ;
	}
	
	public String buildJsonEmpresa(Map<String, Object> payload) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
		ObjectNode root = mapper.createObjectNode();
		ResultSet dao = new MYSQLAccess().readDatabase(
				Integer.parseInt(payload.get("id").toString()),
				payload.get("host").toString(),
				payload.get("dbName").toString(),
				payload.get("user").toString(),
				payload.get("pass").toString(),
				payload.get("port").toString());
		
		try {
			Integer codigoIbgeUf =Integer.parseInt(ibgeService.buscaUFs(dao.getString("sigla_uf"))) ;
			Integer codigoIbgeCidade = Integer.parseInt(ibgeService.buscaMunicipioPorUF(codigoIbgeUf, dao.getString("cidade")));
			
			root.putNull("id");
			root.put("cnpj", dao.getString("cnpj").replaceAll("[^0-9]", ""));
			root.put("inscricaoMunicipal", dao.getString("insc_municipal").replaceAll("[^0-9]", ""));
			root.put("inscricaoEstadual", dao.getString("insc_estadual").replaceAll("[^0-9]", ""));
			root.put("razaoSocial", dao.getString("razao_social"));
			root.put("nomeFantasia", dao.getString("nome_fantasia"));
			
			int crt = Integer.parseInt(dao.getString("crt"));
			if(crt>1) {
				root.put("optanteSimplesNacional", true);
			}
			else {
				root.put("optanteSimplesNacional", true);
				}
			
			root.put("email", dao.getString("email"));
			root.put("telefoneComercial", dao.getString("ddd_telefone") + dao.getLong("telefone"));
			root.put("incentivadorCultural", false);
			root.put("regimeEspecialTributacao",dao.getString("cod_regime_tributacao_rps"));
			root.put("codigoServicoMunicipal", payload.get("codigoServicoMunicipal").toString());
			root.putNull("itemListaServicoLC116");
			root.putNull("cnae");
			root.put("aliquotaIss", 0.02);
			root.put("descricaoServico",".   ");
			root.put("enviarEmailCliente",true);
			
			ObjectNode endereco = mapper.createObjectNode();
			endereco.put("codigoIbgeUf", codigoIbgeUf);
			endereco.put("codigoIbgeCidade", codigoIbgeCidade);
			endereco.put("pais", dao.getString("pais"));
			endereco.put("uf", dao.getString("sigla_uf"));
			endereco.put("cidade", dao.getString("cidade"));
			endereco.put("logradouro", dao.getString("endereco"));
			endereco.put("numero", dao.getString("numero"));
			endereco.put("complemento", dao.getString("complemento_end"));
			endereco.put("bairro", dao.getString("bairro"));
			endereco.put("cep", dao.getString("cep"));
			
			ObjectNode confProducao = mapper.createObjectNode();
			confProducao.put("sequencialNFe", 1);
			confProducao.put("serieNFe", 1);
			confProducao.put("sequencialLoteNFe", 1);
			confProducao.putNull("usuarioAcessoProvedor");
			confProducao.putNull("senhaAcessoProvedor");
			confProducao.putNull("tokenAcessoProvedor");
			
			root.set("endereco", endereco);
			root.set("ConfiguracoesNFSeHomologacao", confProducao);
			root.set("ConfiguracoesNFSeProducao", confProducao);
			}
			catch (Exception e) {
				throw new RuntimeException("Falha ao criar o json: ");
			}
			return root.toString();
	}

	public String findByCNPJ(String cnpj) {
		ObjectMapper mapper = new ObjectMapper();
		CloseableHttpClient httpClient = HttpClients.createDefault();
		String id="";
		try {
			URIBuilder builder = new URIBuilder(listarEmpresa);
			builder.setParameter("pageNumber", "0").
			setParameter("pageSize", "6").
			setParameter("searchBy", "cnpj").
			setParameter("searchTerm", cnpj).
			setParameter("sortBy", "cnpj").
			setParameter("sortDirection", "asc");
			HttpGet get = new HttpGet(builder.build());
			get.setHeader("Accept", "application/json");
			get.addHeader("Authorization", "Basic " + apiKey);
			
			CloseableHttpResponse response = httpClient.execute(get);
			JsonNode rootArray = mapper.readTree(response.getEntity().getContent());
			System.out.println(rootArray);
			
			for(JsonNode root : rootArray) {
				if(root.has("id")) {
					id = root.path("id").asText();
				}
				else {
					id = root.path("mensagem").asText();
				}
			}
			
		} catch (URISyntaxException e1) {
			e1.printStackTrace();
		} catch(Exception e) {
			throw new RuntimeException("Servidor retornou erro :" + e.getMessage());
		}
		
		return id;
	}
	
}
