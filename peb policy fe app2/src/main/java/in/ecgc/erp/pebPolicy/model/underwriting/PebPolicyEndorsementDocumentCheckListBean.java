package in.ecgc.erp.pebPolicy.model.underwriting;

public class PebPolicyEndorsementDocumentCheckListBean {
	
	private String inwardNumber;
	 
	private Integer inwardItemNumber;
	
	private Integer id;
	
	private String documentName;
	
	private String dmsId;
	
	private Integer versionNo;

	public PebPolicyEndorsementDocumentCheckListBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PebPolicyEndorsementDocumentCheckListBean(int id, String documentName) {
		super();
		this.id=id;
		this.documentName=documentName;
	}

	public String getInwardNumber() {
		return inwardNumber;
	}

	public void setInwardNumber(String inwardNumber) {
		this.inwardNumber = inwardNumber;
	}

	public Integer getInwardItemNumber() {
		return inwardItemNumber;
	}

	public void setInwardItemNumber(Integer inwardItemNumber) {
		this.inwardItemNumber = inwardItemNumber;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDocumentName() {
		return documentName;
	}

	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}

	public String getDmsId() {
		return dmsId;
	}

	public void setDmsId(String dmsId) {
		this.dmsId = dmsId;
	}

	public Integer getVersionNo() {
		return versionNo;
	}

	public void setVersionNo(Integer versionNo) {
		this.versionNo = versionNo;
	}

	@Override
	public String toString() {
		return "PebPolicyEndorsementDocumentCheckListBean [inwardNumber=" + inwardNumber + ", inwardItemNumber="
				+ inwardItemNumber + ", id=" + id + ", documentName=" + documentName + ", dmsId=" + dmsId
				+ ", versionNo=" + versionNo + "]";
	}

	

}
