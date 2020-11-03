package in.ecgc.erp.pebPolicy.model.underwriting;


public class PebPolicyProposalDocumentCheckListBean {
	
	private Integer id;
	
	private String documentName;

	
	public PebPolicyProposalDocumentCheckListBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PebPolicyProposalDocumentCheckListBean(Integer id, String documentName) {
		super();
		this.id = id;
		this.documentName = documentName;
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

	@Override
	public String toString() {
		return "PebPolicyProposalDocumentCheckListBean [id=" + id + ", documentName=" + documentName + "]";
	}
	
	

}
