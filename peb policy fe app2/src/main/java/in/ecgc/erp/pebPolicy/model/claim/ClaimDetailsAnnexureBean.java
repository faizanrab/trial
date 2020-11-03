package in.ecgc.erp.pebPolicy.model.claim;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import in.ecgc.erp.pebPolicy.utils.DependencyFieldConstraint;


@DependencyFieldConstraint(fieldName = "invoiceSanctionedByBank", neededValue = "yes", dependantFieldName = "bankName", message = "Please Provide Bank Details")
@DependencyFieldConstraint(fieldName = "invoiceSanctionedByBank", neededValue = "yes", dependantFieldName = "bankBranch", message = "Please Provide Bank Details")
@DependencyFieldConstraint(fieldName = "invoiceSanctionedByBank", neededValue = "yes", dependantFieldName = "bankRefNo", message = "Please Provide Bank Details")
@DependencyFieldConstraint(fieldName = "invoiceSanctionedByBank", neededValue = "no", dependantFieldName = "reasonForInvoiceNotSanctionByBank", message = "Provide Reason for Invoice not Sanctioned.")
public class ClaimDetailsAnnexureBean {
	
	private Long claimNo;
	private String dmsId;
	
	@NotBlank(message = "{invoiceNo.empty}")
	private String invoiceNo;
	
	//@NotBlank(message = "{invoiceDescription.empty}")
	private String invoiceDescription;
	
	//@NotNull(message = "{billCertifiedDate.empty}")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date billCertifiedDate;
	
	//@NotNull(message = "{originalDueDate.empty}")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date originalDueDate;
	
	//@NotNull(message = "{extendedDueDate.empty}")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date extendedDueDate;
	
	//@NotNull(message = "{invoiceDate.empty}")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date invoiceDate;
	
	@NotNull(message = "{grossInvoiceValueINR.empty}")
	private BigDecimal grossInvoiceValueINR;
	
	//@NotNull(message = "{paymentReleased.empty}")
	private BigDecimal paymentReleased;
	
	//@NotNull(message = "{amountDue.empty}")
	private BigDecimal amountDue;
	
	//@NotBlank(message = "{reasonForNonPayment.empty}")
	private String reasonForNonPayment;
	
	@NotBlank(message = "{invoiceSanctionedByBank.empty}")
	private String invoiceSanctionedByBank;
	
	//@NotBlank(message = "{bankName.empty}")
	private String bankName;
	
	//@NotBlank(message = "{bankBranch.empty}")
	private String bankBranch;
	
	//@NotBlank(message = "{bankRefNo.empty}")
	private String bankRefNo;
	
	@NotNull(message = "{dateOfShipment.empty}")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date dateOfShipment;
	
	//@NotBlank(message = "{reasonForInvoiceNotSanctionByBank.empty}")
	private String reasonForInvoiceNotSanctionByBank;
	private Integer version;
	private String invoiceUploadedStatus;
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
	public String getDmsId() {
		return dmsId;
	}
	public void setDmsId(String dmsId) {
		this.dmsId = dmsId;
	}
	public String getInvoiceDescription() {
		return invoiceDescription;
	}
	public void setInvoiceDescription(String invoiceDescription) {
		this.invoiceDescription = invoiceDescription;
	}
	public Date getBillCertifiedDate() {
		return billCertifiedDate;
	}
	public void setBillCertifiedDate(Date billCertifiedDate) {
		this.billCertifiedDate = billCertifiedDate;
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
	public Date getInvoiceDate() {
		return invoiceDate;
	}
	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}
	public BigDecimal getGrossInvoiceValueINR() {
		return grossInvoiceValueINR;
	}
	public void setGrossInvoiceValueINR(BigDecimal grossInvoiceValueINR) {
		this.grossInvoiceValueINR = grossInvoiceValueINR;
	}
	public BigDecimal getPaymentReleased() {
		return paymentReleased;
	}
	public void setPaymentReleased(BigDecimal paymentReleased) {
		this.paymentReleased = paymentReleased;
	}
	public BigDecimal getAmountDue() {
		return amountDue;
	}
	public void setAmountDue(BigDecimal amountDue) {
		this.amountDue = amountDue;
	}
	public String getReasonForNonPayment() {
		return reasonForNonPayment;
	}
	public void setReasonForNonPayment(String reasonForNonPayment) {
		this.reasonForNonPayment = reasonForNonPayment;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	public Date getDateOfShipment() {
		return dateOfShipment;
	}
	public void setDateOfShipment(Date dateOfShipment) {
		this.dateOfShipment = dateOfShipment;
	}
	public String getInvoiceSanctionedByBank() {
		return invoiceSanctionedByBank;
	}
	public void setInvoiceSanctionedByBank(String invoiceSanctionedByBank) {
		this.invoiceSanctionedByBank = invoiceSanctionedByBank;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getBankBranch() {
		return bankBranch;
	}
	public void setBankBranch(String bankBranch) {
		this.bankBranch = bankBranch;
	}
	public String getBankRefNo() {
		return bankRefNo;
	}
	public void setBankRefNo(String bankRefNo) {
		this.bankRefNo = bankRefNo;
	}
	
	
	public String getReasonForInvoiceNotSanctionByBank() {
		return reasonForInvoiceNotSanctionByBank;
	}
	public void setReasonForInvoiceNotSanctionByBank(String reasonForInvoiceNotSanctionByBank) {
		this.reasonForInvoiceNotSanctionByBank = reasonForInvoiceNotSanctionByBank;
	}
	
	public String getInvoiceUploadedStatus() {
		return invoiceUploadedStatus;
	}
	public void setInvoiceUploadedStatus(String invoiceUploadedStatus) {
		this.invoiceUploadedStatus = invoiceUploadedStatus;
	}
	@Override
	public String toString() {
		return "ClaimDetailsAnnexureBean [claimNo=" + claimNo + ", dmsId=" + dmsId + ", invoiceNo=" + invoiceNo
				+ ", invoiceDescription=" + invoiceDescription + ", billCertifiedDate=" + billCertifiedDate
				+ ", originalDueDate=" + originalDueDate + ", extendedDueDate=" + extendedDueDate + ", invoiceDate="
				+ invoiceDate + ", grossInvoiceValueINR=" + grossInvoiceValueINR + ", paymentReleased="
				+ paymentReleased + ", amountDue=" + amountDue + ", reasonForNonPayment=" + reasonForNonPayment
				+ ", invoiceSanctionedByBank=" + invoiceSanctionedByBank + ", bankName=" + bankName + ", bankBranch="
				+ bankBranch + ", bankRefNo=" + bankRefNo + ", dateOfShipment=" + dateOfShipment
				+ ", reasonForInvoiceNotSanctionByBank=" + reasonForInvoiceNotSanctionByBank + ", version=" + version
				+ ", invoiceUploadedStatus=" + invoiceUploadedStatus + "]";
	}
	

}
