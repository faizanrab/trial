package in.ecgc.erp.pebPolicy.model.claim;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import in.ecgc.erp.pebPolicy.exceptions.ProcessInvoiceInfo;

public class ClaimInvoiceProcessingBean {
	
	private Long invoiceProcessingBeanId;
	private Long claimNo;
	private Long crId;
	private String dmsId;
	private String invoiceNo;
	private BigDecimal grossInvoiceValueINR;
	
	@NotNull(groups = ProcessInvoiceInfo.class,message = "Please Enter Amount Approved")
	private BigDecimal amountApproved;
	private String remarks;
	
	@NotEmpty(groups = ProcessInvoiceInfo.class, message = "Please Select Invoice Decision")
	private String invoiceDecision;
	
	private String processInvoiceStatus;
	
	private Double exchangeRate;
	
	//@NotEmpty(groups = ProcessInvoiceInfo.class, message = "Please Select Invalidate Invoice ")
	//private String invalidate;
	private String remarksByExporter;
	
	@NotEmpty(groups = ProcessInvoiceInfo.class, message = "Please Enter Remarks")
	private String remarksByECGC;
	
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date invoiceDate;
	
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date originalDueDate;
	
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date extendedDueDate;
	
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date dateOfBillSubmitted;
	private BigDecimal amountDue;
	
	private String processedBy;
	private Date processedOn;
	private Integer version;
	
	
	
	public Long getInvoiceProcessingBeanId() {
		return invoiceProcessingBeanId;
	}
	public void setInvoiceProcessingBeanId(Long invoiceProcessingBeanId) {
		this.invoiceProcessingBeanId = invoiceProcessingBeanId;
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
	public String getDmsId() {
		return dmsId;
	}
	public void setDmsId(String dmsId) {
		this.dmsId = dmsId;
	}
	public String getInvoiceNo() {
		return invoiceNo;
	}
	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
	public BigDecimal getGrossInvoiceValueINR() {
		return grossInvoiceValueINR;
	}
	public void setGrossInvoiceValueINR(BigDecimal grossInvoiceValueINR) {
		this.grossInvoiceValueINR = grossInvoiceValueINR;
	}
	public BigDecimal getAmountApproved() {
		return amountApproved;
	}
	public void setAmountApproved(BigDecimal amountApproved) {
		this.amountApproved = amountApproved;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getInvoiceDecision() {
		return invoiceDecision;
	}
	public void setInvoiceDecision(String invoiceDecision) {
		this.invoiceDecision = invoiceDecision;
	}
	public Double getExchangeRate() {
		return exchangeRate;
	}
	public void setExchangeRate(Double exchangeRate) {
		this.exchangeRate = exchangeRate;
	}
	public String getRemarksByExporter() {
		return remarksByExporter;
	}
	public void setRemarksByExporter(String remarksByExporter) {
		this.remarksByExporter = remarksByExporter;
	}
	public String getRemarksByECGC() {
		return remarksByECGC;
	}
	public void setRemarksByECGC(String remarksByECGC) {
		this.remarksByECGC = remarksByECGC;
	}
	public Date getInvoiceDate() {
		return invoiceDate;
	}
	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}
	public Date getOriginalDueDate() {
		return originalDueDate;
	}
	public void setOriginalDueDate(Date originalDueDate) {
		this.originalDueDate = originalDueDate;
	}
	public Date getExtendedDueDate() {
		return extendedDueDate;
	}
	public void setExtendedDueDate(Date extendedDueDate) {
		this.extendedDueDate = extendedDueDate;
	}
	public Date getDateOfBillSubmitted() {
		return dateOfBillSubmitted;
	}
	public void setDateOfBillSubmitted(Date dateOfBillSubmitted) {
		this.dateOfBillSubmitted = dateOfBillSubmitted;
	}
	public BigDecimal getAmountDue() {
		return amountDue;
	}
	public void setAmountDue(BigDecimal amountDue) {
		this.amountDue = amountDue;
	}
	public String getProcessedBy() {
		return processedBy;
	}
	public void setProcessedBy(String processedBy) {
		this.processedBy = processedBy;
	}
	public Date getProcessedOn() {
		return processedOn;
	}
	public void setProcessedOn(Date processedOn) {
		this.processedOn = processedOn;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	public String getProcessInvoiceStatus() {
		return processInvoiceStatus;
	}
	public void setProcessInvoiceStatus(String processInvoiceStatus) {
		this.processInvoiceStatus = processInvoiceStatus;
	}
	@Override
	public String toString() {
		return "ClaimInvoiceProcessingBean [invoiceProcessingBeanId=" + invoiceProcessingBeanId + ", claimNo=" + claimNo
				+ ", crId=" + crId + ", dmsId=" + dmsId + ", invoiceNo=" + invoiceNo + ", grossInvoiceValueINR="
				+ grossInvoiceValueINR + ", amountApproved=" + amountApproved + ", remarks=" + remarks
				+ ", invoiceDecision=" + invoiceDecision + ", processInvoiceStatus=" + processInvoiceStatus
				+ ", exchangeRate=" + exchangeRate + ", remarksByExporter=" + remarksByExporter + ", remarksByECGC="
				+ remarksByECGC + ", invoiceDate=" + invoiceDate + ", originalDueDate=" + originalDueDate
				+ ", extendedDueDate=" + extendedDueDate + ", dateOfBillSubmitted=" + dateOfBillSubmitted
				+ ", amountDue=" + amountDue + ", processedBy=" + processedBy + ", processedOn=" + processedOn
				+ ", version=" + version + "]";
	}
	
}
