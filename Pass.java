public class Pass {
	private String sitio;
	private String log;
	private String password;

	public Pass() {
	}
	public Pass(String sitio, String log, String password) {
		this.sitio = sitio;
		this.log = log;
		this.password = password;
	}
	
	public String getSitio() {
		return sitio;
	}
	
	public String getLog() {
		return log;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setSitio(String sitio) {
		this.sitio = sitio;
	}
	public void setLog(String log) {
		this.log = log;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
}
