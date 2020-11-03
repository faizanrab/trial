package in.ecgc.erp.pebPolicy.model.claim;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

public class ClaimExperienceWithBuyerAnnexureBean {
	
	private Long claimNo;
	private String dmsId;
	
	@NotBlank(message = "{invoiceNo.empty}")
	private String invoiceNo;
	
	@NotNull(message = "{invoiceDate.empty}")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date invoiceDate;
	
	@NotNull(message = "{grossInvoiceValueINR.empty}")
	private Double grossInvoiceValueINR;
	
	@NotBlank(message = "{bankRefNo.empty}")
	private String bankRefNo;
	
	@NotBlank(message = "{termsOfPayment.empty}")
	private String termsOfPayment;
	
	@NotNull(message = "{dueDate.empty}")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date dueDate;
	
	@NotNull(message = "{dateOfRealisation.empty}")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date dateOfRealisation;
	
	private Integer version;
	public Long getClaimNo() {
		return claimNo;
	}
	public void setClaimNo(Long claimNo) {
		this.claimNo = claimNo;
	}
	public String getInvoiceNo() {
		return invoiceNo;
	}
	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
	
	public Date getInvoiceDate() {
		return invoiceDate;
	}
	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}
	public String getDmsId() {
		return dmsId;
	}
	public void setDmsId(String dmsId) {
		this.dmsId = dmsId;
	}
	
	public Double getGrossInvoiceValueINR() {
		return grossInvoiceValueINR;
	}
	public void setGrossInvoiceValueINR(Double grossInvoiceValueINR) {
		this.grossInvoiceValueINR = grossInvoiceValueINR;
	}
	public String getBankRefNo() {
		return bankRefNo;
	}
	public void setBankRefNo(String bankRefNo) {
		this.bankRefNo = bankRefNo;
	}
	public String getTermsOfPayment() {
		return termsOfPayment;
	}
	public void setTermsOfPayment(String termsOfPayment) {
		this.termsOfPayment = termsOfPayment;
	}
	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	public Date getDateOfRealisation() {
		return dateOfRealisation;
	}
	public void setDateOfRealisation(Date dateOfRealisation) {
		this.dateOfRealisation = dateOfRealisation;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	@Override
	public String toString() {
		return "ClaimExperienceWithBuyerAnnexureBean [claimNo=" + claimNo + ", invoiceNo=" + invoiceNo
				+ ", invoiceDate=" + invoiceDate + ", dmsId=" + dmsId + ", grossInvoiceValueINR=" + grossInvoiceValueINR
				+ ", bankRefNo=" + bankRefNo + ", termsOfPayment=" + termsOfPayment + ", dueDate=" + dueDate
				+ ", dateOfRealisation=" + dateOfRealisation + ", version=" + version + "]";
	}
	
	
	
	

}
