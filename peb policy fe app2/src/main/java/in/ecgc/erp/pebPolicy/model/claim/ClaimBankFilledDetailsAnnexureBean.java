package in.ecgc.erp.pebPolicy.model.claim;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

public class ClaimBankFilledDetailsAnnexureBean {
	
	private Long claimNo;
	private String dmsId;
	
	@NotBlank(message = "{invoiceNo.empty}")
	private String invoiceNo;
	
	@NotBlank(message = "{bankRefNo.empty}")
	private String bankRefNo;
	
	@NotBlank(message = "{termsOfPayment.empty}")
	private String termsOfPayment;
	
	@NotNull(message = "{dateOfShipment.empty}")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date dateOfShipment;
	
	@NotNull(message = "{exchangeRate.empty}")
	private Double exchangeRate;
	
	@NotNull(message = "{grossInvoiceValueINR.empty}")
	private Double grossInvoiceValueINR;
	
	@NotNull(message = "{grossInvoiceValueUSD.empty}")
	private Double grossInvoiceValueUSD;
	
	private String remarks;
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
	public String getDmsId() {
		return dmsId;
	}
	public void setDmsId(String dmsId) {
		this.dmsId = dmsId;
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
	public Date getDateOfShipment() {
		return dateOfShipment;
	}
	public void setDateOfShipment(Date dateOfShipment) {
		this.dateOfShipment = dateOfShipment;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Double getExchangeRate() {
		return exchangeRate;
	}
	public void setExchangeRate(Double exchangeRate) {
		this.exchangeRate = exchangeRate;
	}
	public Double getGrossInvoiceValueINR() {
		return grossInvoiceValueINR;
	}
	public void setGrossInvoiceValueINR(Double grossInvoiceValueINR) {
		this.grossInvoiceValueINR = grossInvoiceValueINR;
	}
	public Double getGrossInvoiceValueUSD() {
		return grossInvoiceValueUSD;
	}
	public void setGrossInvoiceValueUSD(Double grossInvoiceValueUSD) {
		this.grossInvoiceValueUSD = grossInvoiceValueUSD;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	@Override
	public String toString() {
		return "ClaimBankFilledDetailsAnnexureBean [claimNo=" + claimNo + ", invoiceNo=" + invoiceNo + ", dmsId="
				+ dmsId + ", bankRefNo=" + bankRefNo + ", termsOfPayment=" + termsOfPayment + ", dateOfShipment="
				+ dateOfShipment + ", remarks=" + remarks + ", exchangeRate=" + exchangeRate + ", grossInvoiceValueINR="
				+ grossInvoiceValueINR + ", grossInvoiceValueUSD=" + grossInvoiceValueUSD + ", version=" + version
				+ "]";
	}
	
	

}
