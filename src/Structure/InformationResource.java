package Structure;

public abstract class InformationResource {
	private int id;
	private String name;
	private String informationType;
	private String code;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getInformationType() {
		return informationType;
	}
	public void setInformationType(String informationType) {
		this.informationType = informationType;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	
}
