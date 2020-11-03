package in.ecgc.erp.pebPolicy.model.claim;

public class ClaimDocumentChecklistBean {

	private String inwardNo;
	private Integer inwardItemNo;
	private Long policyNo;
	private Long claimNo;
	private Long crId;
	private Long checklistId;
	private String documentName;
	private String observation;
	private Integer version;
	private String dmsId;

	public ClaimDocumentChecklistBean() {

	}

	public ClaimDocumentChecklistBean(Long id, String name) {
		this.checklistId = id;
		this.documentName = name;
	}

	public String getInwardNo() {
		return inwardNo;
	}

	public void setInwardNo(String inwardNo) {
		this.inwardNo = inwardNo;
	}

	public Integer getInwardItemNo() {
		return inwardItemNo;
	}

	public void setInwardItemNo(Integer inwardItemNo) {
		this.inwardItemNo = inwardItemNo;
	}

	public Long getPolicyNo() {
		return policyNo;
	}

	public void setPolicyNo(Long policyNo) {
		this.policyNo = policyNo;
	}

	public Long getClaimNo() {
		return claimNo;
	}

	public void setClaimNo(Long claimNo) {
		this.claimNo = claimNo;
	}

	public Long getCrId() {
		return crId;
	}

	public void setCrId(Long crId) {
		this.crId = crId;
	}

	public Long getChecklistId() {
		return checklistId;
	}

	public void setChecklistId(Long checklistId) {
		this.checklistId = checklistId;
	}

	public String getDocumentName() {
		return documentName;
	}

	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}

	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getDmsId() {
		return dmsId;
	}

	public void setDmsId(String dmsId) {
		this.dmsId = dmsId;
	}

	@Override
	public String toString() {
		return "ClaimDocumentChecklistBean [inwardNo=" + inwardNo + ", inwardItemNo=" + inwardItemNo + ", policyNo="
				+ policyNo + ", claimNo=" + claimNo + ", crId=" + crId + ", checklistId=" + checklistId
				+ ", documentName=" + documentName + ", observation=" + observation + ", version=" + version
				+ ", dmsId=" + dmsId + "]";
	}

}
