package banhobom.ws.entidades;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Estacao {
	private String nome;
	private int id;
	private double latitude;
	private double longitude;
	private boolean status;
	private Date data;
	
	public Estacao(){
	}
	
	public Estacao(int id, String nome, double latitude, double longitude, boolean status, Date data){
		this.id = id;
		this.nome = nome;
		this.latitude = latitude;
		this.longitude = longitude;
		this.status = status;
		this.data = data;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "Estacao [nome=" + nome + ", id=" + id + ", latitude="
				+ latitude + ", longitude=" + longitude + ", status=" + status
				+ ", data=" + data + "]";
	}




	private static Logger logger = Logger.getLogger("ifrn.tad");
	private static String DB_URI = "jdbc:postgresql://localhost:5432/BanhoBom";
	private static String DB_LOGINID = "postgres";
	private static String DB_PASSWORD = "12345";
	
	
	public static List<Estacao> retornarLista(){
		List<Estacao> resultado = new ArrayList<Estacao>();
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		
		try{
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection(DB_URI, DB_LOGINID, DB_PASSWORD);
			connection.setAutoCommit(false);
			
			statement = connection.createStatement();
			resultSet = statement.executeQuery( "SELECT coleta.id, coleta.data, coleta.nomeestacao, "
					+ "coleta.status, estacao.latitude, estacao.longitude FROM coleta, estacao WHERE "
					+ "coleta.idestacao = estacao.id" );
			
			while(resultSet.next()){
				resultado.add(new Estacao(resultSet.getInt("id"), resultSet.getString("nomeestacao"),
						resultSet.getDouble("latitude"), resultSet.getDouble("longitude"), 
						resultSet.getBoolean("status"), resultSet.getDate("data")));
			}
			
			resultSet.close();
			statement.close();
			connection.close();
		}catch(Exception e){
			logger.info(e.getMessage());
		}
		return resultado;
	}
	
}
