package in.ecgc.erp.pebPolicy.controller.claim;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import in.ecgc.erp.pebPolicy.exceptions.BasicClaimInfo;
import in.ecgc.erp.pebPolicy.exceptions.ClaimDecisionInfo;
import in.ecgc.erp.pebPolicy.exceptions.ClaimScrutinyInfo;
import in.ecgc.erp.pebPolicy.exceptions.ProcessInvoiceInfo;
import in.ecgc.erp.pebPolicy.model.claim.ClaimApplicationBean;
import in.ecgc.erp.pebPolicy.model.claim.ClaimBankFilledDetailsAnnexureBean;
import in.ecgc.erp.pebPolicy.model.claim.ClaimDetailsAnnexureBean;
import in.ecgc.erp.pebPolicy.model.claim.ClaimExperienceWithBuyerAnnexureBean;
import in.ecgc.erp.pebPolicy.model.claim.ClaimInvoiceProcessingBean;
import in.ecgc.erp.pebPolicy.model.claim.ClaimPartPaymentScheduleMasterBean;
import in.ecgc.erp.pebPolicy.model.claim.ClaimProcessingBean;
import in.ecgc.erp.pebPolicy.model.claim.ClaimRepresentationBean;
import in.ecgc.erp.pebPolicy.model.underwriting.DummyPolicyDetailsBean;
import in.ecgc.erp.pebPolicy.service.claim.PolicyClaimService;

@Controller
@RequestMapping("/claim")
public class ClaimApplicationController {

	public static String uploadDirectory = System.getProperty("user.dir") + "/uploads";
	
	@Autowired
	private PolicyClaimService policyClaimService;

	// maintain three list for annexures
	List<ClaimDetailsAnnexureBean> ann1List = null;
	List<ClaimExperienceWithBuyerAnnexureBean> ann2List = null;
	List<ClaimBankFilledDetailsAnnexureBean> ann3List = null;
	List<ClaimInvoiceProcessingBean> invoiceList = null;

	//list for maintaining uploaded document via ajax
	List<String> documentCheck = null;
	
	static char num[] = { '0', '1', '2', '3', '4', '5' };

	/**
	 * Show Claim form
	 */
	@GetMapping("getClaimApplicationForm")
	public String getBasicClaimForm(Model model) {

		// tab 1 models
		model.addAttribute("claimApplicationBean", new ClaimApplicationBean());
		model.addAttribute("id", 1);

		// tab 2 annexure models
		model.addAttribute("ann1", new ClaimDetailsAnnexureBean());
		model.addAttribute("ann2", new ClaimExperienceWithBuyerAnnexureBean());
		model.addAttribute("ann3", new ClaimBankFilledDetailsAnnexureBean());

		// tab 3 models
		model.addAttribute("claimChecklist", policyClaimService.getClaimDocumentCheckList());

		// Assessment/Processing of Invoices Tab
		model.addAttribute("claimInvoiceProcessingBean", new ClaimInvoiceProcessingBean());
		model.addAttribute("claimPartPaymentScheduleBean", new ClaimPartPaymentScheduleMasterBean());

		// Claim Decision Tab
		model.addAttribute("claimProcessingBean", new ClaimProcessingBean());

		return "claim/claimApplicationForm";
	}

	/**
	 * Save Basic Claim Details Data
	 * 
	 * @param claimBean
	 */
	@PostMapping("saveBasicClaimForm")
	public String saveBasicClaimForm(
			@Validated(BasicClaimInfo.class) @ModelAttribute("claimApplicationBean") ClaimApplicationBean claimBean,
			BindingResult result, Model model, HttpSession session) {

		// validate here
		if (result.hasErrors()) {
			// tab 2 annexure models
			model.addAttribute("ann1", new ClaimDetailsAnnexureBean());
			model.addAttribute("ann2", new ClaimExperienceWithBuyerAnnexureBean());
			model.addAttribute("ann3", new ClaimBankFilledDetailsAnnexureBean());

			// tab 3 models
			model.addAttribute("claimChecklist", policyClaimService.getClaimDocumentCheckList());

			// pass id for current tabs
			model.addAttribute("id", 1);

			// Assessment/Processing of Invoices Tab
			model.addAttribute("claimInvoiceProcessingBean", new ClaimInvoiceProcessingBean());
			model.addAttribute("claimPartPaymentScheduleBean", new ClaimPartPaymentScheduleMasterBean());

			// Claim Decision Tab
			model.addAttribute("claimProcessingBean", new ClaimProcessingBean());

			return "claim/claimApplicationForm";
		} else {
			ClaimApplicationBean claim = (ClaimApplicationBean) session.getAttribute("policyClaimBean");
			// generate claim id here
			if (claim == null) {
				int claimNo = new Random().nextInt(1001);
				claimBean.setClaimNo((long) claimNo);

				// and store it in session
				session.setAttribute("policyClaimBean", claimBean);

				// pass id for current tab
				model.addAttribute("id", 1);

				// tab 1 models
				model.addAttribute("claimApplicationBean", claimBean);

				// tab 2 annexure models
				model.addAttribute("ann1", new ClaimDetailsAnnexureBean());
				model.addAttribute("ann2", new ClaimExperienceWithBuyerAnnexureBean());
				model.addAttribute("ann3", new ClaimBankFilledDetailsAnnexureBean());

				// tab 3 models
				model.addAttribute("claimChecklist", policyClaimService.getClaimDocumentCheckList());
				// Assessment/Processing of Invoices Tab
				model.addAttribute("claimInvoiceProcessingBean", new ClaimInvoiceProcessingBean());
				model.addAttribute("claimPartPaymentScheduleBean", new ClaimPartPaymentScheduleMasterBean());

				// Claim Decision Tab
				model.addAttribute("claimProcessingBean", new ClaimProcessingBean());

				// Success Saved Message
				model.addAttribute("BasicClaimDetailsFlag", "success");

			} else {
				ClaimApplicationBean sessionClaimData = (ClaimApplicationBean) session.getAttribute("policyClaimBean");
				// claimBean.setClaimNo(claim.getClaimNo());

				// pass id for current tab
				model.addAttribute("id", 1);

				// Set updated values in session variable
				sessionClaimData.setInwardNo(claimBean.getInwardNo());
				sessionClaimData.setInwardItemNo(claimBean.getInwardItemNo());
				sessionClaimData.setPolicyNo(claimBean.getPolicyNo());
				sessionClaimData.setRodId(claimBean.getRodId());
				sessionClaimData.setRodDate(claimBean.getRodDate());
				sessionClaimData.setClaimApplicationDate(claimBean.getClaimApplicationDate());
				sessionClaimData.setReasonForClaim(claimBean.getReasonForClaim());
				sessionClaimData.setRemarks(claimBean.getRemarks());

				// and store it in session
				session.setAttribute("policyClaimBean", sessionClaimData);

				// tab 1 models
				model.addAttribute("claimApplicationBean", sessionClaimData);

				// tab 2 annexure models
				model.addAttribute("ann1", new ClaimDetailsAnnexureBean());
				model.addAttribute("ann2", new ClaimExperienceWithBuyerAnnexureBean());
				model.addAttribute("ann3", new ClaimBankFilledDetailsAnnexureBean());

				// pass list of annexures in model
				model.addAttribute("ann1list", sessionClaimData.getClaimDetailsAnnexureBean());
				model.addAttribute("ann2list", sessionClaimData.getClaimExperienceWithBuyerAnnexureBean());
				model.addAttribute("ann3list", sessionClaimData.getClaimBankFilledDetailsAnnexureBean());

				// tab 3 models
				model.addAttribute("claimChecklist", policyClaimService.getClaimDocumentCheckList());

				// Assessment/Processing of Invoices Tab
				model.addAttribute("ListOfInvoicesRaisedButNotPaid",
						sessionClaimData.getClaimProcessingBean().getClaimInvoiceProcessingBean());
				model.addAttribute("claimInvoiceProcessingBean", new ClaimInvoiceProcessingBean());
				model.addAttribute("claimPartPaymentScheduleBean", new ClaimPartPaymentScheduleMasterBean());

				// Claim Decision Tab
				model.addAttribute("claimProcessingBean", new ClaimProcessingBean());
				
				// Success Saved Message
				model.addAttribute("BasicClaimDetailsFlag", "success");
			}

		}
		return "claim/claimApplicationForm";
	}

	/**
	 * Save Annexure 1 details
	 * 
	 * @param ann1
	 */
	@PostMapping("saveAnnexure1")
	public String saveAnnexureClaimDetails(@Valid @ModelAttribute("ann1") ClaimDetailsAnnexureBean ann1,
			BindingResult result, @RequestParam("annexure1Invoice") MultipartFile file, Model model,
			HttpSession session) {

		// fetch session object and update it
		ClaimApplicationBean claimBean = (ClaimApplicationBean) session.getAttribute("policyClaimBean");
		System.out.println(result.getAllErrors());
		if (result.hasErrors()) {
			// pass list of annexures in model
			model.addAttribute("ann1list", claimBean.getClaimDetailsAnnexureBean());
			model.addAttribute("ann2list", claimBean.getClaimExperienceWithBuyerAnnexureBean());
			model.addAttribute("ann3list", claimBean.getClaimBankFilledDetailsAnnexureBean());

			// pass id for current tab
			model.addAttribute("id", 2);

			// tab 1 models
			model.addAttribute("claimApplicationBean", claimBean);

			// tab 2 annexure models
			model.addAttribute("ann2", new ClaimExperienceWithBuyerAnnexureBean());
			model.addAttribute("ann3", new ClaimBankFilledDetailsAnnexureBean());

			// tab 3 models
			model.addAttribute("claimChecklist", policyClaimService.getClaimDocumentCheckList());

			// Assessment/Processing of Invoices Tab
			model.addAttribute("ListOfInvoicesRaisedButNotPaid",
					claimBean.getClaimProcessingBean().getClaimInvoiceProcessingBean());
			model.addAttribute("claimInvoiceProcessingBean", new ClaimInvoiceProcessingBean());
			model.addAttribute("claimPartPaymentScheduleBean", new ClaimPartPaymentScheduleMasterBean());

			// Claim Decision Tab
			model.addAttribute("claimProcessingBean", claimBean.getClaimProcessingBean());

			// keep modal open if contains error
			model.addAttribute("ann1Error", "yes");

			if (ann1.getInvoiceSanctionedByBank() != null) {
				if (ann1.getInvoiceSanctionedByBank().equals("yes")) {
					model.addAttribute("sanction", "yes");
					model.addAttribute("showreasonfield", "yes");
				} else {
					model.addAttribute("sanction", "no");
					model.addAttribute("showbankfield", "yes");
				}
			}

			return "claim/claimApplicationForm";
		}

		// store it in list if first or update list if already present
		if (ann1List == null)
			ann1List = new ArrayList<>();
		
		//set claim no to ClaimDetailsAnnexureBean
		ann1.setClaimNo(claimBean.getClaimNo());
		
		// Set random dms id if file selected
		if (!file.isEmpty()) {
			int len = 4;
			StringBuilder strBuilder = new StringBuilder();
			for (int i = 0; i < len; i++) {
				strBuilder.append(num[(int) Math.floor(Math.random() * 5)]);
			}
			ann1.setDmsId(strBuilder.toString());
			ann1.setInvoiceUploadedStatus("Invoice Uploaded");
		} else {
			ann1.setInvoiceUploadedStatus("Invoice Not Uploaded");
		}

		// add Annexure 1 data in ann1list for 1st invoice
		if (claimBean.getClaimDetailsAnnexureBean() == null) {
			ann1List = new ArrayList<>();
			ann1List.add(ann1);

			// bind list to session object
			claimBean.setClaimDetailsAnnexureBean(ann1List);
		} else {

			// get Annexure 1 List from session variable
			List<ClaimDetailsAnnexureBean> list1 = claimBean.getClaimDetailsAnnexureBean();

			// Iterator to check for same Invoice No
			Iterator<ClaimDetailsAnnexureBean> iterator = list1.iterator();
			ClaimDetailsAnnexureBean claimDetailsAnnexureBean = new ClaimDetailsAnnexureBean();
			while (iterator.hasNext()) {
				claimDetailsAnnexureBean = iterator.next();
				if (claimDetailsAnnexureBean.getInvoiceNo().equals(ann1.getInvoiceNo())) {
					model.addAttribute("ann1Error", "yes");
					model.addAttribute("invoiceNoExistError", true);

					// pass list in model for datatable
					model.addAttribute("ann1list", claimBean.getClaimDetailsAnnexureBean());
					// model.addAttribute("ann2list",
					// claimBean.getClaimExperienceWithBuyerAnnexureBean());
					// model.addAttribute("ann3list",
					// claimBean.getClaimBankFilledDetailsAnnexureBean());

					// pass id in model
					model.addAttribute("id", 2);

					// and other tab models
					// tab 1 models
					model.addAttribute("claimApplicationBean", claimBean);

					// tab 2 annexure models
					model.addAttribute("ann1", new ClaimDetailsAnnexureBean());
					model.addAttribute("ann2", new ClaimExperienceWithBuyerAnnexureBean());
					model.addAttribute("ann3", new ClaimBankFilledDetailsAnnexureBean());

					// tab 3 models
					model.addAttribute("claimChecklist", policyClaimService.getClaimDocumentCheckList());

					// Assessment/Processing of Invoices Tab
					model.addAttribute("ListOfInvoicesRaisedButNotPaid",
							claimBean.getClaimProcessingBean().getClaimInvoiceProcessingBean());
					model.addAttribute("claimInvoiceProcessingBean", new ClaimInvoiceProcessingBean());
					model.addAttribute("claimPartPaymentScheduleBean", new ClaimPartPaymentScheduleMasterBean());

					// Claim Decision Tab
					model.addAttribute("claimProcessingBean", claimBean.getClaimProcessingBean());
					return "claim/claimApplicationForm";
				}
			}

			// add Annexure1 Data in list1
			list1.add(ann1);

			// bind list to session object
			claimBean.setClaimDetailsAnnexureBean(list1);
		}

		if (invoiceList == null)
			invoiceList = new ArrayList<>();

		// Get List of Annexure1 Details
		List<ClaimDetailsAnnexureBean> claimDetailsAnnexureList = claimBean.getClaimDetailsAnnexureBean();

		int ListSize = claimDetailsAnnexureList.size();
		ListSize--;
		ClaimInvoiceProcessingBean claimInvoiceProcessing = new ClaimInvoiceProcessingBean();

		// set Annexure 1 data in Invoice Processing Bean
		claimInvoiceProcessing.setClaimNo(claimDetailsAnnexureList.get(ListSize).getClaimNo());
		claimInvoiceProcessing.setDmsId(claimDetailsAnnexureList.get(ListSize).getDmsId());
		claimInvoiceProcessing.setInvoiceNo(claimDetailsAnnexureList.get(ListSize).getInvoiceNo());
		claimInvoiceProcessing
				.setGrossInvoiceValueINR(claimDetailsAnnexureList.get(ListSize).getGrossInvoiceValueINR());
		claimInvoiceProcessing.setInvoiceDate(claimDetailsAnnexureList.get(ListSize).getInvoiceDate());
		claimInvoiceProcessing.setOriginalDueDate(claimDetailsAnnexureList.get(ListSize).getOriginalDueDate());
		claimInvoiceProcessing.setExtendedDueDate(claimDetailsAnnexureList.get(ListSize).getExtendedDueDate());
		claimInvoiceProcessing.setAmountDue(claimDetailsAnnexureList.get(ListSize).getAmountDue());
		claimInvoiceProcessing.setProcessInvoiceStatus("Pending");

		// Add in InvoiceProcessingBean in list of Invoice Processing Bean
		invoiceList.add(claimInvoiceProcessing);

		// set Invoices details from Annexure1 to claimInvoiceprocessingBean
		/*
		 * List<ClaimInvoiceProcessingBean> invoiceList =
		 * claimDetailsAnnexureList.stream().map(u -> { ClaimInvoiceProcessingBean
		 * claimInvoiceProcessing = new ClaimInvoiceProcessingBean();
		 * claimInvoiceProcessing.setClaimNo(u.getClaimNo());
		 * claimInvoiceProcessing.setDmsId(u.getDmsId());
		 * claimInvoiceProcessing.setInvoiceNo(u.getInvoiceNo());
		 * claimInvoiceProcessing.setGrossInvoiceValueINR(u.getGrossInvoiceValueINR());
		 * claimInvoiceProcessing.setInvoiceDate(u.getInvoiceDate());
		 * //claimInvoiceProcessing.setDmsId(u.getDmsId());
		 * claimInvoiceProcessing.setOriginalDueDate(u.getOriginalDueDate());
		 * claimInvoiceProcessing.setExtendedDueDate(u.getExtendedDueDate());
		 * claimInvoiceProcessing.setAmountDue(u.getAmountDue());
		 * claimInvoiceProcessing.setProcessInvoiceStatus("Pending"); return
		 * claimInvoiceProcessing; }).collect(Collectors.toList());
		 */

		// Set List of Invoice Processing Bean to claimProcessing Object
		if (claimBean.getClaimProcessingBean() == null) {
			ClaimProcessingBean claimProcessingBean = new ClaimProcessingBean();
			claimProcessingBean.setClaimInvoiceProcessingBean(invoiceList);
			claimBean.setClaimProcessingBean(claimProcessingBean);
		} else {
			ClaimProcessingBean claimProcessingBean = claimBean.getClaimProcessingBean();
			claimProcessingBean.setClaimInvoiceProcessingBean(invoiceList);
			claimBean.setClaimProcessingBean(claimProcessingBean);
		}

		// To sum the amount due of all invoice in Gross Loss Value
		BigDecimal sumGrossLossValue = claimBean.getClaimDetailsAnnexureBean().stream()
				.map(ClaimDetailsAnnexureBean::getAmountDue).reduce(BigDecimal.ZERO, BigDecimal::add);

		// set Sum value to Gross Loss Value to session variable
		claimBean.setGrossLossValue(sumGrossLossValue);

		// update session vaiable
		session.setAttribute("policyClaimBean", claimBean);

		// pass list in model for datatable
		model.addAttribute("ann1list", claimBean.getClaimDetailsAnnexureBean());

		// pass id in model
		model.addAttribute("id", 2);

		// and other tab models
		// tab 1 models
		model.addAttribute("claimApplicationBean", claimBean);

		// tab 2 annexure models
		model.addAttribute("ann1", new ClaimDetailsAnnexureBean());
		model.addAttribute("ann2", new ClaimExperienceWithBuyerAnnexureBean());
		model.addAttribute("ann3", new ClaimBankFilledDetailsAnnexureBean());

		// tab 3 models
		model.addAttribute("claimChecklist", policyClaimService.getClaimDocumentCheckList());

		// Assessment/Processing of Invoices Tab
		model.addAttribute("ListOfInvoicesRaisedButNotPaid", invoiceList);
		model.addAttribute("claimInvoiceProcessingBean", new ClaimInvoiceProcessingBean());
		model.addAttribute("claimPartPaymentScheduleBean", new ClaimPartPaymentScheduleMasterBean());

		// Claim Decision Tab
		model.addAttribute("claimProcessingBean", claimBean.getClaimProcessingBean());

		// keep modal close if no error
		model.addAttribute("ann1Error", "no");

		return "claim/claimApplicationForm";
	}

	/**
	 * show Update Invoice Annexure 1 Modal with data
	 * 
	 * @param invoiceNo
	 */
	@PostMapping("viewUpdateAnnexure1")
	public String editAnnexureClaimDetails(@RequestParam("invoiceNo") String invoiceNo, Model model, HttpSession session) {
		// get session
		ClaimApplicationBean claimBean = (ClaimApplicationBean) session.getAttribute("policyClaimBean");

		// find invoice no and add to model
		List<ClaimDetailsAnnexureBean> list1 = claimBean.getClaimDetailsAnnexureBean();
		ClaimDetailsAnnexureBean annexure1 = list1.stream().filter(a1 -> a1.getInvoiceNo().equals(invoiceNo))
				.findFirst().get();

		// model for prepopulating data into modal
		model.addAttribute("annexure1", annexure1);

		// pass id in model
		model.addAttribute("id", 2);

		// all datatable list of annexures
		model.addAttribute("ann1list", claimBean.getClaimDetailsAnnexureBean());
		model.addAttribute("ann2list", claimBean.getClaimExperienceWithBuyerAnnexureBean());
		model.addAttribute("ann3list", claimBean.getClaimBankFilledDetailsAnnexureBean());

		// tab 1 models
		model.addAttribute("claimApplicationBean", claimBean);

		// tab 2 annexure models
		model.addAttribute("ann1", new ClaimDetailsAnnexureBean());
		model.addAttribute("ann2", new ClaimExperienceWithBuyerAnnexureBean());
		model.addAttribute("ann3", new ClaimBankFilledDetailsAnnexureBean());

		// tab 3 models
		model.addAttribute("claimChecklist", policyClaimService.getClaimDocumentCheckList());

		// Assessment/Processing of Invoices Tab
		model.addAttribute("ListOfInvoicesRaisedButNotPaid",
				claimBean.getClaimProcessingBean().getClaimInvoiceProcessingBean());
		model.addAttribute("claimInvoiceProcessingBean", new ClaimInvoiceProcessingBean());
		model.addAttribute("claimPartPaymentScheduleBean", new ClaimPartPaymentScheduleMasterBean());

		// Claim Decision Tab
		model.addAttribute("claimProcessingBean", claimBean.getClaimProcessingBean());

		// pass id to pop modal for update
		model.addAttribute("updateAnn1", "yes");

		return "claim/claimApplicationForm";
	}

	/**
	 * Update Invoice Details From Annexure 1
	 * 
	 * @param ann1
	 */
	@PostMapping("updateAnnexure1")
	public String updateAnnexureClaimDetails(@Valid @ModelAttribute("annexure1") ClaimDetailsAnnexureBean ann1,
			BindingResult result, @RequestParam("annexure1Invoice") MultipartFile file, Model model,
			HttpSession session) {

		// fetch session object and update it
		ClaimApplicationBean claimBean = (ClaimApplicationBean) session.getAttribute("policyClaimBean");

		if (result.hasErrors()) {
			// pass list of annexures in model
			model.addAttribute("ann1list", claimBean.getClaimDetailsAnnexureBean());
			model.addAttribute("ann2list", claimBean.getClaimExperienceWithBuyerAnnexureBean());
			model.addAttribute("ann3list", claimBean.getClaimBankFilledDetailsAnnexureBean());

			// pass id for current tab
			model.addAttribute("id", 2);

			// tab 1 models
			model.addAttribute("claimApplicationBean", claimBean);

			// tab 2 annexure models
			model.addAttribute("ann1", new ClaimDetailsAnnexureBean());
			model.addAttribute("ann2", new ClaimExperienceWithBuyerAnnexureBean());
			model.addAttribute("ann3", new ClaimBankFilledDetailsAnnexureBean());

			// tab 3 models
			model.addAttribute("claimChecklist", policyClaimService.getClaimDocumentCheckList());

			// Assessment/Processing of Invoices Tab
			model.addAttribute("ListOfInvoicesRaisedButNotPaid",
					claimBean.getClaimProcessingBean().getClaimInvoiceProcessingBean());
			model.addAttribute("claimInvoiceProcessingBean", new ClaimInvoiceProcessingBean());
			model.addAttribute("claimPartPaymentScheduleBean", new ClaimPartPaymentScheduleMasterBean());

			// Claim Decision Tab
			model.addAttribute("claimProcessingBean", claimBean.getClaimProcessingBean());

			// keep modal open if contains error
			model.addAttribute("updateAnn1", "error");

			return "claim/claimApplicationForm";
		}
		ann1.setClaimNo(claimBean.getClaimNo());
		// update list if already present
		int counter = 0;
		String invoice = ann1.getInvoiceNo();
		for (ClaimDetailsAnnexureBean a1 : ann1List) {

			if (a1.getInvoiceNo().equals(invoice)) {

				ann1.setDmsId(a1.getDmsId());
				if (ann1.getDmsId() == null) {
					ann1.setInvoiceUploadedStatus("Invoice Not Uploaded");
				} else {
					ann1.setInvoiceUploadedStatus("Invoice Uploaded");
				}
				ann1List.set(counter, ann1);
				break;
			}
			counter++;
		}

		// update list to session object
		claimBean.setClaimDetailsAnnexureBean(ann1List);

		// To update Gross Loss Value if update amount due value
		BigDecimal sumGrossLossValue = claimBean.getClaimDetailsAnnexureBean().stream()
				.map(ClaimDetailsAnnexureBean::getAmountDue).reduce(BigDecimal.ZERO, BigDecimal::add);

		// set sum value to Gross Loss Value in session variable
		claimBean.setGrossLossValue(sumGrossLossValue);

		// Update invoice Processing Bean value if invoice details gets updated
		int counter1 = 0;
		// Get List of Annexure1 Details
		List<ClaimInvoiceProcessingBean> invoiceList = claimBean.getClaimProcessingBean()
				.getClaimInvoiceProcessingBean();

		for (ClaimInvoiceProcessingBean claimInvoiceProcessingBean : invoiceList) {

			if (claimInvoiceProcessingBean.getInvoiceNo().equals(invoice)) {

				ClaimInvoiceProcessingBean claimInvoiceProcessing = new ClaimInvoiceProcessingBean();
				claimInvoiceProcessing.setClaimNo(ann1.getClaimNo());
				claimInvoiceProcessing.setDmsId(ann1.getDmsId());
				claimInvoiceProcessing.setInvoiceNo(ann1.getInvoiceNo());
				claimInvoiceProcessing.setGrossInvoiceValueINR(ann1.getGrossInvoiceValueINR());
				claimInvoiceProcessing.setInvoiceDate(ann1.getInvoiceDate());
				claimInvoiceProcessing.setOriginalDueDate(ann1.getOriginalDueDate());
				claimInvoiceProcessing.setExtendedDueDate(ann1.getExtendedDueDate());
				claimInvoiceProcessing.setAmountDue(ann1.getAmountDue());
				claimInvoiceProcessing.setProcessInvoiceStatus("Pending");
				invoiceList.set(counter1, claimInvoiceProcessing);
				break;
			}
			counter1++;
		}

		// set Invoices details from Annexure1 to claimInvoiceprocessingBean
		/*
		 * List<ClaimInvoiceProcessingBean> invoiceList =
		 * claimDetailsAnnexureList.stream().map(u -> { ClaimInvoiceProcessingBean
		 * claimInvoiceProcessing = new ClaimInvoiceProcessingBean();
		 * claimInvoiceProcessing.setClaimNo(u.getClaimNo());
		 * claimInvoiceProcessing.setDmsId(u.getDmsId());
		 * claimInvoiceProcessing.setInvoiceNo(u.getInvoiceNo());
		 * claimInvoiceProcessing.setGrossInvoiceValueINR(u.getGrossInvoiceValueINR());
		 * claimInvoiceProcessing.setInvoiceDate(u.getInvoiceDate());
		 * claimInvoiceProcessing.setOriginalDueDate(u.getOriginalDueDate());
		 * claimInvoiceProcessing.setExtendedDueDate(u.getExtendedDueDate());
		 * claimInvoiceProcessing.setAmountDue(u.getAmountDue());
		 * claimInvoiceProcessing.setProcessInvoiceStatus("Pending"); return
		 * claimInvoiceProcessing; }).collect(Collectors.toList());
		 */

		// set updated list of invoiceProcessingBean to claim Processing Bean object
		if (claimBean.getClaimProcessingBean() == null) {
			ClaimProcessingBean claimProcessingBean = new ClaimProcessingBean();
			claimProcessingBean.setClaimInvoiceProcessingBean(invoiceList);
			claimBean.setClaimProcessingBean(claimProcessingBean);
		} else {
			ClaimProcessingBean claimProcessingBean = claimBean.getClaimProcessingBean();
			claimProcessingBean.setClaimInvoiceProcessingBean(invoiceList);
			claimBean.setClaimProcessingBean(claimProcessingBean);
		}
		session.setAttribute("policyClaimBean", claimBean);

		// pass list in model for datatable
		model.addAttribute("ann1list", claimBean.getClaimDetailsAnnexureBean());
		model.addAttribute("ann2list", claimBean.getClaimExperienceWithBuyerAnnexureBean());
		model.addAttribute("ann3list", claimBean.getClaimBankFilledDetailsAnnexureBean());

		// pass id in model
		model.addAttribute("id", 2);

		// and other tab models
		// tab 1 models
		model.addAttribute("claimApplicationBean", claimBean);

		// tab 2 annexure models
		model.addAttribute("ann1", new ClaimDetailsAnnexureBean());
		model.addAttribute("ann2", new ClaimExperienceWithBuyerAnnexureBean());
		model.addAttribute("ann3", new ClaimBankFilledDetailsAnnexureBean());

		// tab 3 models
		model.addAttribute("claimChecklist", policyClaimService.getClaimDocumentCheckList());

		// Assessment/Processing of Invoices Tab

		model.addAttribute("ListOfInvoicesRaisedButNotPaid", invoiceList);
		model.addAttribute("claimInvoiceProcessingBean", new ClaimInvoiceProcessingBean());
		model.addAttribute("claimPartPaymentScheduleBean", new ClaimPartPaymentScheduleMasterBean());

		// Claim Decision Tab
		model.addAttribute("claimProcessingBean", claimBean.getClaimProcessingBean());

		// keep modal close if no error
		// model.addAttribute("ann1Error", "no");

		return "claim/claimApplicationForm";
	}

	/**
	 * Delete Invoice From Annexure 1
	 * 
	 * @param invoiceNo
	 */
	@GetMapping("deleteAnnexure1/{invoiceNo}")
	public String deleteAnnexureClaimDetails(@PathVariable String invoiceNo, HttpSession session, Model model) {

		// fetch session object and delete it
		ClaimApplicationBean claimBean = (ClaimApplicationBean) session.getAttribute("policyClaimBean");

		// remove annexure1 from global list
		ann1List.removeIf(a1 -> a1.getInvoiceNo().equals(invoiceNo));

		// update in session object
		claimBean.setClaimDetailsAnnexureBean(ann1List);

		// update session
		session.setAttribute("policyClaimBean", claimBean);

		// pass all models here
		// pass list in model for datatable
		model.addAttribute("ann1list", claimBean.getClaimDetailsAnnexureBean());
		model.addAttribute("ann2list", claimBean.getClaimExperienceWithBuyerAnnexureBean());
		model.addAttribute("ann3list", claimBean.getClaimBankFilledDetailsAnnexureBean());

		// pass id in model
		model.addAttribute("id", 2);

		// tab 1 models
		model.addAttribute("claimApplicationBean", claimBean);

		// tab 2 annexure models
		model.addAttribute("ann1", new ClaimDetailsAnnexureBean());
		model.addAttribute("ann2", new ClaimExperienceWithBuyerAnnexureBean());
		model.addAttribute("ann3", new ClaimBankFilledDetailsAnnexureBean());

		// tab 3 models
		model.addAttribute("claimChecklist", policyClaimService.getClaimDocumentCheckList());

		// Assessment/Processing of Invoices Tab
		model.addAttribute("claimInvoiceProcessingBean", new ClaimInvoiceProcessingBean());
		model.addAttribute("claimPartPaymentScheduleBean", new ClaimPartPaymentScheduleMasterBean());

		// Claim Decision Tab
		model.addAttribute("claimProcessingBean", new ClaimProcessingBean());

		return "claim/claimApplicationForm";
	}

	// annexure 2
	@PostMapping("saveAnnexure2")
	public String saveAnnexureTwo(@Valid @ModelAttribute("ann2") ClaimExperienceWithBuyerAnnexureBean ann2,
			BindingResult result, Model model, HttpSession session) {

		if (result.hasErrors()) {

			return "claim/claimApplicationForm";
		}

		return "claim/claimApplicationForm";
	}

	// annexure 3
	@PostMapping("saveAnnexure3")
	public String saveAnnexureThree(@Valid @ModelAttribute("ann3") ClaimBankFilledDetailsAnnexureBean ann3,
			BindingResult result, Model model, HttpSession session) {
		if (result.hasErrors()) {

			return "claim/claimApplicationForm";
		}

		return "claim/claimApplicationForm";
	}

	/**
	 * Save Scrutiny Details
	 * 
	 * @param claim
	 */
	@PostMapping("saveClaimScrutiny")
	public String saveClaimScrutinyDetails(
			@Validated(ClaimScrutinyInfo.class) @ModelAttribute("claimApplicationBean") ClaimApplicationBean claim,
			BindingResult result, Model model, HttpSession session) {

		// fetch session object and delete it
		ClaimApplicationBean claimBean = (ClaimApplicationBean) session.getAttribute("policyClaimBean");

//		if (result.hasErrors()) {
//
//			// set remaining tab values in claim varibale
//			claim.setClaimNo(claimBean.getClaimNo());
//			claim.setInwardNo(claimBean.getInwardNo());
//			claim.setInwardItemNo(claimBean.getInwardItemNo());
//			claim.setPolicyNo(claimBean.getPolicyNo());
//			claim.setRodId(claimBean.getRodId());
//			claim.setRodDate(claimBean.getRodDate());
//			claim.setClaimApplicationDate(claimBean.getClaimApplicationDate());
//			claim.setReasonForClaim(claimBean.getReasonForClaim());
//			claim.setRemarks(claimBean.getRemarks());
//
//			// pass list of annexures in model
//			model.addAttribute("ann1list", claimBean.getClaimDetailsAnnexureBean());
//			model.addAttribute("ann2list", claimBean.getClaimExperienceWithBuyerAnnexureBean());
//			model.addAttribute("ann3list", claimBean.getClaimBankFilledDetailsAnnexureBean());
//
//			// pass id for current tab
//			model.addAttribute("id", 3);
//
//			// tab 1 models
//			model.addAttribute("claimApplicationBean", claim);
//
//			// tab 2 annexure models
//			model.addAttribute("ann1", new ClaimDetailsAnnexureBean());
//			model.addAttribute("ann2", new ClaimExperienceWithBuyerAnnexureBean());
//			model.addAttribute("ann3", new ClaimBankFilledDetailsAnnexureBean());
//
//			// tab 3 models
//			model.addAttribute("claimChecklist", policyClaimService.getClaimDocumentCheckList());
//
//			// Assessment/Processing of Invoices Tab
//			model.addAttribute("ListOfInvoicesRaisedButNotPaid",
//					claimBean.getClaimProcessingBean().getClaimInvoiceProcessingBean());
//			model.addAttribute("claimInvoiceProcessingBean", new ClaimInvoiceProcessingBean());
//			model.addAttribute("claimPartPaymentScheduleBean", new ClaimPartPaymentScheduleMasterBean());
//
//			// Claim Decision Tab
//			model.addAttribute("claimProcessingBean", claimBean.getClaimProcessingBean());
//
//			return "claim/claimApplicationForm";
//		}

		// update the session object
		//claimBean.setClaimDocumentChecklistBean(claim.getClaimDocumentChecklistBean());
		claimBean.setClaimDocumentChecklistBean(documentCheck);
		session.setAttribute("policyClaimBean", claimBean);

		// pass list in model for datatable
		model.addAttribute("ann1list", claimBean.getClaimDetailsAnnexureBean());
		model.addAttribute("ann2list", claimBean.getClaimExperienceWithBuyerAnnexureBean());
		model.addAttribute("ann3list", claimBean.getClaimBankFilledDetailsAnnexureBean());

		// pass id in model
		model.addAttribute("id", 3);

		// tab 1 models
		model.addAttribute("claimApplicationBean", claimBean);

		// tab 2 annexure models
		model.addAttribute("ann1", new ClaimDetailsAnnexureBean());
		model.addAttribute("ann2", new ClaimExperienceWithBuyerAnnexureBean());
		model.addAttribute("ann3", new ClaimBankFilledDetailsAnnexureBean());

		// tab 3 models
		model.addAttribute("claimChecklist", policyClaimService.getClaimDocumentCheckList());

		// Assessment/Processing of Invoices Tab
		model.addAttribute("ListOfInvoicesRaisedButNotPaid",claimBean.getClaimProcessingBean().getClaimInvoiceProcessingBean());
		
		model.addAttribute("claimInvoiceProcessingBean", new ClaimInvoiceProcessingBean());
		model.addAttribute("claimPartPaymentScheduleBean", new ClaimPartPaymentScheduleMasterBean());

		// Claim Decision Tab
		model.addAttribute("claimProcessingBean", claimBean.getClaimProcessingBean());

		// Success Saved Message
		model.addAttribute("ScrutinyUploadDocumentsFlag", "success");

		return "claim/claimApplicationForm";
	}
 
	/**
	 * Show Process Invoice Div For Invoice Processing
	 * 
	 * @param invoiceNo
	 */
	@PostMapping("/processInvoice")
	public String processInvoice(Model model, @RequestParam("invoiceNo") String invoiceNo,
			@ModelAttribute("claimApplicationBean") ClaimApplicationBean claimApplicationBean, HttpSession session) {

		ClaimApplicationBean claimBean = (ClaimApplicationBean) session.getAttribute("policyClaimBean");
		// System.out.println(session.getAttribute("policyClaimBean"));

		// Get Invoice Data based on Invoice No from List of Invoices
		List<ClaimInvoiceProcessingBean> claimInvoiceProcessingList = claimBean.getClaimProcessingBean()
				.getClaimInvoiceProcessingBean();
		Iterator<ClaimInvoiceProcessingBean> iterator = claimInvoiceProcessingList.iterator();
		ClaimInvoiceProcessingBean claimInvoiceProcessingBean = new ClaimInvoiceProcessingBean();
		while (iterator.hasNext()) {
			claimInvoiceProcessingBean = iterator.next();
			if (claimInvoiceProcessingBean.getInvoiceNo().equals(invoiceNo)) {
				model.addAttribute("claimInvoiceProcessingBean", claimInvoiceProcessingBean);
			}
		}

		// update session value
		session.setAttribute("policyClaimBean", claimBean);
		model.addAttribute("claimApplicationBean", claimBean);

		// Annexure Details tab
		model.addAttribute("ann1", new ClaimDetailsAnnexureBean());
		model.addAttribute("ann2", new ClaimExperienceWithBuyerAnnexureBean());

		// pass list of annexures in model
		model.addAttribute("ann1list", claimBean.getClaimDetailsAnnexureBean());

		// tab 3 models
		model.addAttribute("claimChecklist", policyClaimService.getClaimDocumentCheckList());

		// Assessment/Processing of Invoices Tab
		model.addAttribute("ListOfInvoicesRaisedButNotPaid",
				claimBean.getClaimProcessingBean().getClaimInvoiceProcessingBean());
		model.addAttribute("claimPartPaymentScheduleBean", new ClaimPartPaymentScheduleMasterBean());
		model.addAttribute("invoiceNo", invoiceNo);

		// Claim Decision Tab
		model.addAttribute("claimProcessingBean", claimBean.getClaimProcessingBean());

		model.addAttribute("id", 4);

		// show Process Invoice Form
		model.addAttribute("showProcessInvoiceForm", "yes");
		return "claim/claimApplicationForm";

	}

	/**
	 * Save Invoice Processing Details
	 * 
	 * @param claimInvoiceProcessingBean
	 */
	@PostMapping("/saveProcessInvoiceDecision")
	public String InvoiceDecision(Model model,
			@Validated(ProcessInvoiceInfo.class) @ModelAttribute("claimInvoiceProcessingBean") ClaimInvoiceProcessingBean claimInvoiceProcessingBean,
			BindingResult result, HttpSession session) {

		// get session varibale value
		ClaimApplicationBean claimBean = (ClaimApplicationBean) session.getAttribute("policyClaimBean");
		if (result.hasErrors()) {
			model.addAttribute("claimApplicationBean", claimBean);

			// Annexure Details tab
			model.addAttribute("ann1", new ClaimDetailsAnnexureBean());
			model.addAttribute("ann2", new ClaimExperienceWithBuyerAnnexureBean());

			// tab 3 models
			model.addAttribute("claimChecklist", policyClaimService.getClaimDocumentCheckList());

			// pass list of annexures in model
			model.addAttribute("ann1list", claimBean.getClaimDetailsAnnexureBean());

			// Assessment/Processing of Invoices Tab
			model.addAttribute("ListOfInvoicesRaisedButNotPaid",
					claimBean.getClaimProcessingBean().getClaimInvoiceProcessingBean());
			model.addAttribute("claimPartPaymentScheduleBean", new ClaimPartPaymentScheduleMasterBean());
			model.addAttribute("invoiceNo", claimInvoiceProcessingBean.getInvoiceNo());

			// Claim Decision Tab
			model.addAttribute("claimProcessingBean", claimBean.getClaimProcessingBean());

			model.addAttribute("id", 4);
			model.addAttribute("showProcessInvoiceForm", "yes");
			return "claim/claimApplicationForm";
		}
		
		if(claimInvoiceProcessingBean.getAmountApproved().compareTo(claimInvoiceProcessingBean.getAmountDue())==1) {
			model.addAttribute("claimApplicationBean", claimBean);

			// Annexure Details tab
			model.addAttribute("ann1", new ClaimDetailsAnnexureBean());
			model.addAttribute("ann2", new ClaimExperienceWithBuyerAnnexureBean());

			// pass list of annexures in model
			model.addAttribute("ann1list", claimBean.getClaimDetailsAnnexureBean());

			// tab 3 models
			model.addAttribute("claimChecklist", policyClaimService.getClaimDocumentCheckList());

			// Assessment/Processing of Invoices Tab
			model.addAttribute("ListOfInvoicesRaisedButNotPaid",
					claimBean.getClaimProcessingBean().getClaimInvoiceProcessingBean());
			model.addAttribute("claimPartPaymentScheduleBean", new ClaimPartPaymentScheduleMasterBean());

			// Claim Decision Tab
			model.addAttribute("claimProcessingBean", claimBean.getClaimProcessingBean());

			model.addAttribute("id", 4);
			
			model.addAttribute("invoiceNo", claimInvoiceProcessingBean.getInvoiceNo());
			
			// to keep open the process invoice div
			model.addAttribute("showProcessInvoiceForm", "yes");
			
			// Error Message
			model.addAttribute("AmountExceedsError", true);
			
			return "claim/claimApplicationForm";
		}

		// set ProcessInvoice status value
		claimInvoiceProcessingBean.setProcessInvoiceStatus("Processed");

		// set update invoice Processing bean with remaining values
		int counter = 0;
		for (ClaimInvoiceProcessingBean c : claimBean.getClaimProcessingBean().getClaimInvoiceProcessingBean()) {
			if (c.getInvoiceNo().equals(claimInvoiceProcessingBean.getInvoiceNo())) {
				claimBean.getClaimProcessingBean().getClaimInvoiceProcessingBean().set(counter,
						claimInvoiceProcessingBean);
			}
			counter++;
		}

		// update session varibale
		session.setAttribute("policyClaimBean", claimBean);
		model.addAttribute("claimApplicationBean", claimBean);

		// Annexure Details tab
		model.addAttribute("ann1", new ClaimDetailsAnnexureBean());
		model.addAttribute("ann2", new ClaimExperienceWithBuyerAnnexureBean());

		// pass list of annexures in model
		model.addAttribute("ann1list", claimBean.getClaimDetailsAnnexureBean());

		// tab 3 models
		model.addAttribute("claimChecklist", policyClaimService.getClaimDocumentCheckList());

		// Assessment/Processing of Invoices Tab
		model.addAttribute("ListOfInvoicesRaisedButNotPaid",
				claimBean.getClaimProcessingBean().getClaimInvoiceProcessingBean());
		model.addAttribute("claimPartPaymentScheduleBean", new ClaimPartPaymentScheduleMasterBean());

		// Claim Decision Tab
		model.addAttribute("claimProcessingBean", claimBean.getClaimProcessingBean());

		model.addAttribute("id", 4);
		return "claim/claimApplicationForm";
	}

	/**
	 * Save Claim Decision Details
	 * 
	 * @param claimProcessingBean
	 */
	@PostMapping("/saveClaimDecisionDetails")
	public String saveClaimDecisionDetails(Model model,
			@Validated(ClaimDecisionInfo.class) @ModelAttribute("claimProcessingBean") ClaimProcessingBean claimProcessingBean,
			BindingResult result, HttpSession session, HttpServletRequest request) {

		// get session varibale value
		ClaimApplicationBean claimBean = (ClaimApplicationBean) session.getAttribute("policyClaimBean");

		if (result.hasErrors()) {

			model.addAttribute("claimApplicationBean", claimBean);

			// Annexure Details tab
			model.addAttribute("ann1", new ClaimDetailsAnnexureBean());
			model.addAttribute("ann2", new ClaimExperienceWithBuyerAnnexureBean());

			// pass list of annexures in model
			model.addAttribute("ann1list", claimBean.getClaimDetailsAnnexureBean());

			// tab 3 models
			model.addAttribute("claimChecklist", policyClaimService.getClaimDocumentCheckList());

			// Assessment/Processing of Invoices Tab
			model.addAttribute("ListOfInvoicesRaisedButNotPaid",
					claimBean.getClaimProcessingBean().getClaimInvoiceProcessingBean());
			model.addAttribute("claimPartPaymentScheduleBean", new ClaimPartPaymentScheduleMasterBean());

			model.addAttribute("id", 5);
			return "claim/claimApplicationForm";
		}

		ClaimProcessingBean processingBean = new ClaimProcessingBean();
		processingBean.setClaimNo(claimBean.getClaimNo());
		processingBean.setClaimAmountApproved(claimProcessingBean.getClaimAmountApproved());
		processingBean.setProcessingOfficerComments(claimProcessingBean.getProcessingOfficerComments());
		processingBean.setProcessingOfficerCommentedDate(claimProcessingBean.getProcessingOfficerCommentedDate());
		processingBean.setObserverComments(claimProcessingBean.getObserverComments());
		processingBean.setObserverCommentedDate(claimProcessingBean.getObserverCommentedDate());
		processingBean.setRecommenderComments(claimProcessingBean.getRecommenderComments());
		processingBean.setRecommenderCommentedDate(claimProcessingBean.getRecommenderCommentedDate());

		processingBean.setEcgcDecision(claimProcessingBean.getEcgcDecision());
		processingBean.setEcgcRemarks(claimProcessingBean.getEcgcRemarks());
		processingBean.setEcgcRecommendation(claimProcessingBean.getEcgcRecommendation());
		processingBean.setEcgcDecisionDate(claimProcessingBean.getEcgcDecisionDate());

		processingBean.setCodDecision(claimProcessingBean.getCodDecision());
		processingBean.setCodRemarks(claimProcessingBean.getCodRemarks());
		processingBean.setCodRecommendation(claimProcessingBean.getCodRecommendation());
		processingBean.setCodDecisionDate(claimProcessingBean.getCodDecisionDate());
		processingBean.setCodMeetingDate(claimProcessingBean.getCodMeetingDate());
		processingBean
				.setClaimInvoiceProcessingBean(claimBean.getClaimProcessingBean().getClaimInvoiceProcessingBean());

		claimBean.setClaimProcessingBean(processingBean);

		session.setAttribute("policyClaimBean", claimBean);
		
		/*
		 * if(claimProcessingBean.getClaimAmountApproved().compareTo(claimBean.
		 * getGrossLossValue())== 1) {
		 * 
		 * model.addAttribute("claimApplicationBean", claimBean);
		 * 
		 * // Annexure Details tab model.addAttribute("ann1", new
		 * ClaimDetailsAnnexureBean()); model.addAttribute("ann2", new
		 * ClaimExperienceWithBuyerAnnexureBean());
		 * 
		 * // pass list of annexures in model model.addAttribute("ann1list",
		 * claimBean.getClaimDetailsAnnexureBean());
		 * 
		 * // tab 3 models model.addAttribute("claimChecklist",
		 * policyClaimService.getClaimDocumentCheckList());
		 * 
		 * // Assessment/Processing of Invoices Tab
		 * model.addAttribute("ListOfInvoicesRaisedButNotPaid",
		 * claimBean.getClaimProcessingBean().getClaimInvoiceProcessingBean());
		 * model.addAttribute("claimPartPaymentScheduleBean", new
		 * ClaimPartPaymentScheduleMasterBean()); model.addAttribute("id", 5);
		 * 
		 * // Error Message model.addAttribute("ClaimAmountApprovedExceedsError", true);
		 * return "claim/claimApplicationForm"; }
		 */

		model.addAttribute("claimApplicationBean", claimBean);

		// Annexure Details tab
		model.addAttribute("ann1", new ClaimDetailsAnnexureBean());
		model.addAttribute("ann2", new ClaimExperienceWithBuyerAnnexureBean());

		// pass list of annexures in model
		model.addAttribute("ann1list", claimBean.getClaimDetailsAnnexureBean());

		// tab 3 models
		model.addAttribute("claimChecklist", policyClaimService.getClaimDocumentCheckList());

		// Assessment/Processing of Invoices Tab
		model.addAttribute("ListOfInvoicesRaisedButNotPaid",
				claimBean.getClaimProcessingBean().getClaimInvoiceProcessingBean());
		model.addAttribute("claimPartPaymentScheduleBean", new ClaimPartPaymentScheduleMasterBean());
		model.addAttribute("id", 5);

		// Success Saved Message
		model.addAttribute("ClaimDecisionDetailsFlag", "success");
		return "claim/claimApplicationForm";
	}

	/**
	 * Save Claim Form Details in JSON Mock File After Final Submit
	 * 
	 * @session policyClaimBean
	 */
	@GetMapping("submitClaimForm")
	public String submitClaimFormDetails(Model model,
			@ModelAttribute("claimApplicationBean") ClaimApplicationBean claimApplicationBean, HttpSession session,
			RedirectAttributes redirectAttributes) {
		// get session variable value
		ClaimApplicationBean claimBean = (ClaimApplicationBean) session.getAttribute("policyClaimBean");

		ClaimRepresentationBean cRBean=new ClaimRepresentationBean();
		List<ClaimRepresentationBean> claimRepresentation=new ArrayList<ClaimRepresentationBean>();
		cRBean.setClaimNo(claimBean.getClaimNo());
		cRBean.setCrId(new Long(0));
		claimRepresentation.add(cRBean);

		claimBean.setClaimRepresentationBeanList(claimRepresentation);
		// save the claim from details in mock json file
		int result1 = policyClaimService.saveClaimApplicationDetails(claimBean);

		// show success message if saved in json file
		if (result1 == 1) {
			session.removeAttribute("policyClaimBean");
			redirectAttributes.addFlashAttribute("finalSubmitMsg", "Claim Form Details For Claim No :"+claimBean.getClaimNo()+" Saved Successfully");
		}

		return "redirect:/claim/getClaimApplicationForm";
	}

	/**
	 * Show List of Claims Submitted
	 * 
	 */
	@GetMapping("listClaimForm")
	public String listOfClaimForms(Model model) {
		System.out.println("list::"+policyClaimService.getClaimApplicationBeanList());
		model.addAttribute("listOfClaimSubmitted", policyClaimService.getClaimApplicationBeanList());
		return "claim/listClaimForm";
	}

	/**
	 * View Particular Claim Form Details
	 * 
	 * @param claimNo
	 */
	@PostMapping("viewClaimDetails")
	public String viewClaimApplicationForm(@RequestParam("claimNo") Long claimNo, Model model) {
		
		// get the Claim Details based on claim No
		ClaimApplicationBean claimApplicationBean = policyClaimService.getClaimApplicationBeanById(claimNo);

		model.addAttribute("claimNo", claimNo); // Document checklist values
		model.addAttribute("claimChecklist", policyClaimService.getClaimDocumentCheckList());

		model.addAttribute("claimApplicationBean", claimApplicationBean);
		 
		 
		return "claim/viewClaimApplicationForm";
	}
	
	
	/**
	 * View Particular Invoice Details
	 * 
	 * @param invoiceNo
	 * @param claimNo
	 */
	@GetMapping("viewAnnexure1/{invoiceNo}/{claimNo}")
	public @ResponseBody ClaimDetailsAnnexureBean findAnnexureDetailsByInvoiceNo(@PathVariable String invoiceNo,
			@PathVariable Long claimNo) {
		return policyClaimService.getInvoiceDetailsUsingInvoiceNo(invoiceNo, claimNo);
	}
	
	
	/**
	 * View Particular Process Invoice Details
	 * 
	 * @param invoiceNo
	 * @param claimNo
	 */
	@GetMapping("viewProcessInvoice/{invoiceNo}/{claimNo}")
	public @ResponseBody ClaimInvoiceProcessingBean findInvoicesRaisedButNotPaid(@PathVariable String invoiceNo,
			@PathVariable Long claimNo) {

		// get the Claim Details based on claim No
		ClaimApplicationBean claimApplicationBean = policyClaimService.getClaimApplicationBeanById(claimNo);
		ClaimProcessingBean claimProcessingBean = claimApplicationBean.getClaimProcessingBean();
		
		List<ClaimInvoiceProcessingBean> invoices = claimProcessingBean.getClaimInvoiceProcessingBean();

		ClaimInvoiceProcessingBean invoice = invoices.stream().filter(i -> i.getInvoiceNo().equals(invoiceNo))
				.findFirst().get();

		return invoice;
	}

	/**
	 * Show Particular Claim Form Details On Edit Claim form Html page
	 * 
	 * @param claimNo
	 */
	@PostMapping("editClaimDetails")
	public String editClaimApplicationForm(Model model, @RequestParam("claimNo") Long claimNo,HttpSession session) {
		// get the Claim Details based on claim No
		ClaimApplicationBean claimApplicationBean = policyClaimService.getClaimApplicationBeanById(claimNo);
		
		//start the session for edit here
		session.setAttribute("editPolicyClaimBean", claimApplicationBean);
		
		List<ClaimInvoiceProcessingBean> invoiceList = claimApplicationBean.getClaimProcessingBean()
				.getClaimInvoiceProcessingBean();
		model.addAttribute("claimApplicationBean", claimApplicationBean);

		// Annexure Details tab
		model.addAttribute("ann1", new ClaimDetailsAnnexureBean());
		model.addAttribute("ann2", new ClaimExperienceWithBuyerAnnexureBean());

		// pass list of annexures in model
		model.addAttribute("ann1list", claimApplicationBean.getClaimDetailsAnnexureBean());

		// Document Checklist values
		model.addAttribute("documentChecklist", policyClaimService.getClaimDocumentCheckList());

		// Assessment/Processing of Invoices Tab
		model.addAttribute("ListOfInvoicesRaisedButNotPaid", invoiceList);
		model.addAttribute("claimInvoiceProcessingBean", new ClaimInvoiceProcessingBean());
		model.addAttribute("claimPartPaymentScheduleBean", new ClaimPartPaymentScheduleMasterBean());

		// Claim Decision Tab
		model.addAttribute("claimProcessingBean", claimApplicationBean.getClaimProcessingBean());

		model.addAttribute("id", 1);
		return "claim/editClaimApplicationForm";
	}
	
	/**
	 * Update Basic Claim Details
	 * 
	 * @param claimBean
	 */
	@PostMapping("/updateBasicClaimDetails")
	public String updateBasicClaimDetail(
			@Validated(BasicClaimInfo.class) @ModelAttribute("claimApplicationBean") ClaimApplicationBean claimBean,
			BindingResult result, Model model, HttpSession session) {

		// get data from mock json file
		ClaimApplicationBean claim = policyClaimService.getClaimApplicationBeanById(claimBean.getClaimNo());

		// if basic claim form has errors
		if (result.hasErrors()) {

			// set all model here------------------------
			List<ClaimInvoiceProcessingBean> invoiceList = claim.getClaimProcessingBean()
					.getClaimInvoiceProcessingBean();

			List<ClaimDetailsAnnexureBean> annexure1 = claim.getClaimDetailsAnnexureBean();

			// annexure1 list
			model.addAttribute("ann1list", annexure1);

			// for tab 1 prefilled details
			claimBean.setClaimDocumentChecklistBean(claim.getClaimDocumentChecklistBean());
			model.addAttribute("claimApplicationBean", claimBean);

			// Annexure Details tab
			model.addAttribute("ann1", new ClaimDetailsAnnexureBean());
			model.addAttribute("ann2", new ClaimExperienceWithBuyerAnnexureBean());

			// Assessment/Processing of Invoices Tab
			model.addAttribute("ListOfInvoicesRaisedButNotPaid", invoiceList);
			model.addAttribute("claimInvoiceProcessingBean", new ClaimInvoiceProcessingBean());
			model.addAttribute("claimPartPaymentScheduleBean", new ClaimPartPaymentScheduleMasterBean());
			model.addAttribute("documentChecklist", policyClaimService.getClaimDocumentCheckList());

			// Claim Decision Tab
			model.addAttribute("claimProcessingBean", claim.getClaimProcessingBean());

			model.addAttribute("id", 1);

			// set all model here------------------------

			return "claim/editClaimApplicationForm";
		}

		// store submitted object value in dummy claim bean and then store in session
		// BeanUtils.copyProperties(claimBean,claim);
		claim.setInwardNo(claimBean.getInwardNo());
		claim.setInwardItemNo(claimBean.getInwardItemNo());
		claim.setPolicyNo(claimBean.getPolicyNo());
		claim.setRodDate(claimBean.getRodDate());
		claim.setRodId(claimBean.getRodId());
		claim.setClaimApplicationDate(claimBean.getClaimApplicationDate());
		claim.setGrossLossValue(claimBean.getGrossLossValue());
		claim.setReasonForClaim(claimBean.getReasonForClaim());
		claim.setRemarks(claimBean.getRemarks());

		// session attribute
		session.setAttribute("editPolicyClaimBean", claim);

		// set all model here------------------------
		List<ClaimInvoiceProcessingBean> invoiceList = claim.getClaimProcessingBean().getClaimInvoiceProcessingBean();

		List<ClaimDetailsAnnexureBean> annexure1 = claim.getClaimDetailsAnnexureBean();

		// annexure1 list
		model.addAttribute("ann1list", annexure1);

		// for tab 1 prefilled details
		model.addAttribute("claimApplicationBean", claim);

		// Annexure Details tab
		model.addAttribute("ann1", new ClaimDetailsAnnexureBean());
		model.addAttribute("ann2", new ClaimExperienceWithBuyerAnnexureBean());

		// Assessment/Processing of Invoices Tab
		model.addAttribute("ListOfInvoicesRaisedButNotPaid", invoiceList);
		model.addAttribute("claimInvoiceProcessingBean", new ClaimInvoiceProcessingBean());
		model.addAttribute("claimPartPaymentScheduleBean", new ClaimPartPaymentScheduleMasterBean());
		model.addAttribute("documentChecklist", policyClaimService.getClaimDocumentCheckList());

		// Claim Decision Tab
		model.addAttribute("claimProcessingBean", claim.getClaimProcessingBean());

		model.addAttribute("id", 1);

		// Success updated Message
		model.addAttribute("updateBasicClaimDetailsFlag", "success");
		// set all model here------------------------

		return "claim/editClaimApplicationForm";

	}

	/**
	 * Show Particular Invoice Details On Edit Claim form Html page
	 * 
	 * @param invoiceNo
	 * @param claimNo
	 */
	@PostMapping("viewEditAnnexure1")
	public String editAnnexureClaimDetails(@RequestParam String invoiceNo, @RequestParam Long claimNo, Model model,
			HttpSession session) {
		// get session
		ClaimApplicationBean claim = (ClaimApplicationBean) session.getAttribute("editPolicyClaimBean");

		/*
		 * if(claim==null) { //fetch the claim bean for json file using claimNo claim =
		 * policyClaimService.getClaimApplicationBeanByClaimNo(claimNo); }
		 */

		// find invoice no and add to model
		List<ClaimDetailsAnnexureBean> list1 = claim.getClaimDetailsAnnexureBean();
		ClaimDetailsAnnexureBean annexure1 = list1.stream().filter(a1 -> a1.getInvoiceNo().equals(invoiceNo))
				.findFirst().get();

		// model for prepopulating data into modal
		model.addAttribute("annexure1", annexure1);

		// set all model here------------------------
		List<ClaimInvoiceProcessingBean> invoiceList = claim.getClaimProcessingBean().getClaimInvoiceProcessingBean();

		// annexure1 list
		model.addAttribute("ann1list", list1);

		// for tab 1 prefilled details
		model.addAttribute("claimApplicationBean", claim);

		// Annexure Details tab
		model.addAttribute("ann1", new ClaimDetailsAnnexureBean());
		model.addAttribute("ann2", new ClaimExperienceWithBuyerAnnexureBean());

		// Assessment/Processing of Invoices Tab
		model.addAttribute("ListOfInvoicesRaisedButNotPaid", invoiceList);
		model.addAttribute("claimInvoiceProcessingBean", new ClaimInvoiceProcessingBean());
		model.addAttribute("claimPartPaymentScheduleBean", new ClaimPartPaymentScheduleMasterBean());
		model.addAttribute("documentChecklist", policyClaimService.getClaimDocumentCheckList());

		// Claim Decision Tab
		model.addAttribute("claimProcessingBean", claim.getClaimProcessingBean());

		model.addAttribute("id", 2);

		// pass id to pop modal for update
		model.addAttribute("updateAnn1", "yes");
		// set all model here------------------------

		return "claim/editClaimApplicationForm";
	}

	
	/**
	 * Update Invoice Details
	 * 
	 * @param ann1
	 */
	@PostMapping("editAnnexure1")
	public String editAnnexureClaimDetails(@Valid @ModelAttribute("annexure1") ClaimDetailsAnnexureBean ann1,
			BindingResult result, @RequestParam("dms") MultipartFile file, Model model, HttpSession session) {

		// fetch session object and update it
		ClaimApplicationBean claim = (ClaimApplicationBean) session.getAttribute("editPolicyClaimBean");

		/*
		 * if(claim==null) { //fetch the claim bean for json file using claimNo claim =
		 * policyClaimService.getClaimApplicationBeanByClaimNo(ann1.getClaimNo()); }
		 */

		if (result.hasErrors()) {

			// set all model here------------------------
			List<ClaimInvoiceProcessingBean> invoiceList = claim.getClaimProcessingBean()
					.getClaimInvoiceProcessingBean();

			List<ClaimDetailsAnnexureBean> list1 = claim.getClaimDetailsAnnexureBean();

			// annexure1 list
			model.addAttribute("ann1list", list1);

			// for tab 1 prefilled details
			model.addAttribute("claimApplicationBean", claim);

			// Annexure Details tab
			model.addAttribute("ann1", new ClaimDetailsAnnexureBean());
			model.addAttribute("ann2", new ClaimExperienceWithBuyerAnnexureBean());

			// Assessment/Processing of Invoices Tab
			model.addAttribute("ListOfInvoicesRaisedButNotPaid", invoiceList);
			model.addAttribute("claimInvoiceProcessingBean", new ClaimInvoiceProcessingBean());
			model.addAttribute("claimPartPaymentScheduleBean", new ClaimPartPaymentScheduleMasterBean());
			model.addAttribute("documentChecklist", policyClaimService.getClaimDocumentCheckList());

			// Claim Decision Tab
			model.addAttribute("claimProcessingBean", claim.getClaimProcessingBean());

			model.addAttribute("id", 2);

			// keep modal open if contains error
			model.addAttribute("updateAnn1", "error");
			// set all model here------------------------

			return "claim/editClaimApplicationForm";
		}

		// set dms id here if file is upload
		if (!file.getOriginalFilename().isEmpty()) {
			// set dms id here
			int dmsId = new Random().nextInt(90) + 10;
			ann1.setDmsId("" + dmsId);
		}

		// update list if already present
		int counter = 0;
		String invoice = ann1.getInvoiceNo();
		List<ClaimDetailsAnnexureBean> ann1List = claim.getClaimDetailsAnnexureBean();
		for (ClaimDetailsAnnexureBean a1 : ann1List) {
			if (a1.getInvoiceNo().equals(invoice)) {

				ann1.setDmsId(a1.getDmsId());
				if (ann1.getDmsId() == null) {
					ann1.setInvoiceUploadedStatus("Invoice Not Uploaded");
				} else {
					ann1.setInvoiceUploadedStatus("Invoice Uploaded");
				}
				ann1List.set(counter, ann1);
				break;
			}
			counter++;
		}

		// update list to session object
		claim.setClaimDetailsAnnexureBean(ann1List);

		// To update Gross Loss Value if update amount due value
		BigDecimal sumGrossLossValue = claim.getClaimDetailsAnnexureBean().stream()
				.map(ClaimDetailsAnnexureBean::getAmountDue).reduce(BigDecimal.ZERO, BigDecimal::add);

		// set sum value to Gross Loss Value in session variable
		claim.setGrossLossValue(sumGrossLossValue);

		// Update invoice Processing Bean value if invoice details gets updated
		int counter1 = 0;
		// Get List of Annexure1 Details
		List<ClaimInvoiceProcessingBean> invoiceList = claim.getClaimProcessingBean().getClaimInvoiceProcessingBean();

		for (ClaimInvoiceProcessingBean claimInvoiceProcessingBean : invoiceList) {

			if (claimInvoiceProcessingBean.getInvoiceNo().equals(invoice)) {

				ClaimInvoiceProcessingBean claimInvoiceProcessing = new ClaimInvoiceProcessingBean();
				claimInvoiceProcessing.setClaimNo(ann1.getClaimNo());
				claimInvoiceProcessing.setDmsId(ann1.getDmsId());
				claimInvoiceProcessing.setInvoiceNo(ann1.getInvoiceNo());
				claimInvoiceProcessing.setGrossInvoiceValueINR(ann1.getGrossInvoiceValueINR());
				claimInvoiceProcessing.setInvoiceDate(ann1.getInvoiceDate());
				claimInvoiceProcessing.setOriginalDueDate(ann1.getOriginalDueDate());
				claimInvoiceProcessing.setExtendedDueDate(ann1.getExtendedDueDate());
				claimInvoiceProcessing.setAmountDue(ann1.getAmountDue());
				claimInvoiceProcessing.setProcessInvoiceStatus("Pending");
				invoiceList.set(counter1, claimInvoiceProcessing);
				break;
			}
			counter1++;
		}

		// set updated list of invoiceProcessingBean to claim Processing Bean object
		if (claim.getClaimProcessingBean() == null) {
			ClaimProcessingBean claimProcessingBean = new ClaimProcessingBean();
			claimProcessingBean.setClaimInvoiceProcessingBean(invoiceList);
			claim.setClaimProcessingBean(claimProcessingBean);
		} else {
			ClaimProcessingBean claimProcessingBean = claim.getClaimProcessingBean();
			claimProcessingBean.setClaimInvoiceProcessingBean(invoiceList);
			claim.setClaimProcessingBean(claimProcessingBean);
		}

		session.setAttribute("editPolicyClaimBean", claim);

		// set all model here------------------------
		List<ClaimDetailsAnnexureBean> annexure1 = claim.getClaimDetailsAnnexureBean();

		// annexure1 list
		model.addAttribute("ann1list", annexure1);

		// for tab 1 prefilled details
		model.addAttribute("claimApplicationBean", claim);

		// Annexure Details tab
		model.addAttribute("ann1", new ClaimDetailsAnnexureBean());
		model.addAttribute("ann2", new ClaimExperienceWithBuyerAnnexureBean());

		// Assessment/Processing of Invoices Tab
		model.addAttribute("ListOfInvoicesRaisedButNotPaid", invoiceList);
		model.addAttribute("claimInvoiceProcessingBean", new ClaimInvoiceProcessingBean());
		model.addAttribute("claimPartPaymentScheduleBean", new ClaimPartPaymentScheduleMasterBean());
		model.addAttribute("documentChecklist", policyClaimService.getClaimDocumentCheckList());

		// Claim Decision Tab
		model.addAttribute("claimProcessingBean", claim.getClaimProcessingBean());

		model.addAttribute("id", 2);
		// set all model here------------------------

		return "claim/editClaimApplicationForm";
	}

	/**
	 * Save Invoice Details if added new invoice while editing claim details
	 * 
	 * @param ann1
	 */
	@PostMapping("savAnnexure1")
	public String savAnnexureClaimDetails(@Valid @ModelAttribute("ann1") ClaimDetailsAnnexureBean ann1,
			BindingResult result, @RequestParam("dms") MultipartFile file, Model model, HttpSession session) {

		// fetch session object and update it
		ClaimApplicationBean claim = (ClaimApplicationBean) session.getAttribute("editPolicyClaimBean");

		if (result.hasErrors()) {

			// set all model here------------------------
			List<ClaimInvoiceProcessingBean> invoiceList = claim.getClaimProcessingBean()
					.getClaimInvoiceProcessingBean();

			List<ClaimDetailsAnnexureBean> annexure1 = claim.getClaimDetailsAnnexureBean();

			// annexure1 list
			model.addAttribute("ann1list", annexure1);

			// for tab 1 prefilled details
			model.addAttribute("claimApplicationBean", claim);

			// Annexure Details tab
			// model.addAttribute("ann1", new ClaimDetailsAnnexureBean());
			model.addAttribute("ann1", ann1);
			model.addAttribute("ann2", new ClaimExperienceWithBuyerAnnexureBean());

			// Assessment/Processing of Invoices Tab
			model.addAttribute("ListOfInvoicesRaisedButNotPaid", invoiceList);
			model.addAttribute("claimInvoiceProcessingBean", new ClaimInvoiceProcessingBean());
			model.addAttribute("claimPartPaymentScheduleBean", new ClaimPartPaymentScheduleMasterBean());
			model.addAttribute("documentChecklist", policyClaimService.getClaimDocumentCheckList());

			// Claim Decision Tab
			model.addAttribute("claimProcessingBean", claim.getClaimProcessingBean());

			model.addAttribute("id", 2);
			// set all model here------------------------

			// keep modal open if contains error
			model.addAttribute("ann1Error", "yes");

			if (ann1.getInvoiceSanctionedByBank() != null) {
				if (ann1.getInvoiceSanctionedByBank().equals("yes")) {
					model.addAttribute("sanction", "yes");
					model.addAttribute("showreasonfield", "yes");
				} else {
					model.addAttribute("sanction", "no");
					model.addAttribute("showbankfield", "yes");
				}
			}

			return "claim/editClaimApplicationForm";
		}

		// store it in list if first or update list if already present
		List<ClaimDetailsAnnexureBean> ann1List = claim.getClaimDetailsAnnexureBean();

		// Set random dms id if file selected
		if (!file.isEmpty()) {
			int len = 4;
			StringBuilder strBuilder = new StringBuilder();
			for (int i = 0; i < len; i++) {
				strBuilder.append(num[(int) Math.floor(Math.random() * 5)]);
			}
			ann1.setDmsId(strBuilder.toString());
			ann1.setInvoiceUploadedStatus("Invoice Uploaded");
		} else {
			ann1.setInvoiceUploadedStatus("Invoice Not Uploaded");
		}

		// Iterator to check for same Invoice No
		Iterator<ClaimDetailsAnnexureBean> iterator = ann1List.iterator();
		ClaimDetailsAnnexureBean claimDetailsAnnexureBean = new ClaimDetailsAnnexureBean();
		while (iterator.hasNext()) {
			claimDetailsAnnexureBean = iterator.next();
			if (claimDetailsAnnexureBean.getInvoiceNo().equals(ann1.getInvoiceNo())) {
				model.addAttribute("ann1Error", "yes");
				model.addAttribute("invoiceNoExistError", true);

				// pass list in model for datatable
				model.addAttribute("ann1list", claim.getClaimDetailsAnnexureBean());

				// pass id in model
				model.addAttribute("id", 2);

				// and other tab models
				// tab 1 models
				model.addAttribute("claimApplicationBean", claim);

				// tab 2 annexure models
				model.addAttribute("ann1", new ClaimDetailsAnnexureBean());
				model.addAttribute("ann2", new ClaimExperienceWithBuyerAnnexureBean());
				model.addAttribute("ann3", new ClaimBankFilledDetailsAnnexureBean());

				// tab 3 models
				model.addAttribute("documentChecklist", policyClaimService.getClaimDocumentCheckList());

				// Assessment/Processing of Invoices Tab
				model.addAttribute("ListOfInvoicesRaisedButNotPaid",
						claim.getClaimProcessingBean().getClaimInvoiceProcessingBean());
				model.addAttribute("claimInvoiceProcessingBean", new ClaimInvoiceProcessingBean());
				model.addAttribute("claimPartPaymentScheduleBean", new ClaimPartPaymentScheduleMasterBean());

				// Claim Decision Tab
				model.addAttribute("claimProcessingBean", claim.getClaimProcessingBean());
				return "claim/editClaimApplicationForm";
			}
		}
		//set claim no to claimDetailAnnexureBean
		ann1.setClaimNo(claim.getClaimNo());
		ann1List.add(ann1);

		// bind list to session object
		claim.setClaimDetailsAnnexureBean(ann1List);

		List<ClaimDetailsAnnexureBean> claimDetailsAnnexureList = claim.getClaimDetailsAnnexureBean();

		int ListSize = claimDetailsAnnexureList.size();
		ListSize--;
		ClaimInvoiceProcessingBean claimInvoiceProcessing = new ClaimInvoiceProcessingBean();

		// set Annexure 1 data in Invoice Processing Bean
		claimInvoiceProcessing.setClaimNo(claimDetailsAnnexureList.get(ListSize).getClaimNo());
		claimInvoiceProcessing.setDmsId(claimDetailsAnnexureList.get(ListSize).getDmsId());
		claimInvoiceProcessing.setInvoiceNo(claimDetailsAnnexureList.get(ListSize).getInvoiceNo());
		claimInvoiceProcessing
				.setGrossInvoiceValueINR(claimDetailsAnnexureList.get(ListSize).getGrossInvoiceValueINR());
		claimInvoiceProcessing.setInvoiceDate(claimDetailsAnnexureList.get(ListSize).getInvoiceDate());
		claimInvoiceProcessing.setOriginalDueDate(claimDetailsAnnexureList.get(ListSize).getOriginalDueDate());
		claimInvoiceProcessing.setExtendedDueDate(claimDetailsAnnexureList.get(ListSize).getExtendedDueDate());
		claimInvoiceProcessing.setAmountDue(claimDetailsAnnexureList.get(ListSize).getAmountDue());
		claimInvoiceProcessing.setProcessInvoiceStatus("Pending");

		// Get List of Process Invoice
		List<ClaimInvoiceProcessingBean> invoiceList = claim.getClaimProcessingBean().getClaimInvoiceProcessingBean();

		// Add in InvoiceProcessingBean in list of Invoice Processing Bean
		invoiceList.add(claimInvoiceProcessing);

		// Set List of Invoice Processing Bean to claimProcessing Object
		if (claim.getClaimProcessingBean() == null) {
			ClaimProcessingBean claimProcessingBean = new ClaimProcessingBean();
			claimProcessingBean.setClaimInvoiceProcessingBean(invoiceList);
			claim.setClaimProcessingBean(claimProcessingBean);
		} else {
			ClaimProcessingBean claimProcessingBean = claim.getClaimProcessingBean();
			claimProcessingBean.setClaimInvoiceProcessingBean(invoiceList);
			claim.setClaimProcessingBean(claimProcessingBean);
		}

		// To sum the amount due of all invoice in Gross Loss Value
		BigDecimal sumGrossLossValue = claim.getClaimDetailsAnnexureBean().stream()
				.map(ClaimDetailsAnnexureBean::getAmountDue).reduce(BigDecimal.ZERO, BigDecimal::add);

		// set Sum value to Gross Loss Value to session variable
		claim.setGrossLossValue(sumGrossLossValue);

		session.setAttribute("editPolicyClaimBean", claim);

		// set all model here------------------------
		List<ClaimDetailsAnnexureBean> annexure1 = claim.getClaimDetailsAnnexureBean();

		// annexure1 list
		model.addAttribute("ann1list", annexure1);

		// for tab 1 prefilled details
		model.addAttribute("claimApplicationBean", claim);

		// Annexure Details tab
		model.addAttribute("ann1", new ClaimDetailsAnnexureBean());
		model.addAttribute("ann2", new ClaimExperienceWithBuyerAnnexureBean());

		// Assessment/Processing of Invoices Tab
		model.addAttribute("ListOfInvoicesRaisedButNotPaid", invoiceList);
		model.addAttribute("claimInvoiceProcessingBean", new ClaimInvoiceProcessingBean());
		model.addAttribute("claimPartPaymentScheduleBean", new ClaimPartPaymentScheduleMasterBean());
		model.addAttribute("documentChecklist", policyClaimService.getClaimDocumentCheckList());

		// Claim Decision Tab
		model.addAttribute("claimProcessingBean", claim.getClaimProcessingBean());

		model.addAttribute("id", 2);
		// set all model here------------------------

		// keep modal close if no error
		model.addAttribute("ann1Error", "no");

		return "claim/editClaimApplicationForm";
	}

	/**
	 * Update Scrutiny Details
	 * 
	 * @param claimBean
	 */
	@PostMapping("updateClaimScrutiny")
	public String updateClaimScrutinyDetails(
			@Validated(ClaimScrutinyInfo.class) @ModelAttribute("claimApplicationBean") ClaimApplicationBean claimBean,
			BindingResult result, Model model, HttpSession session) {

		// fetch session object and delete it
		ClaimApplicationBean claim = (ClaimApplicationBean) session.getAttribute("editPolicyClaimBean");

//		if (result.hasErrors()) {
//
//			// remove previously selected checkbox
//			claim.setClaimDocumentChecklistBean(null);
//
//			// set all model here------------------------
//			List<ClaimInvoiceProcessingBean> invoiceList = claim.getClaimProcessingBean()
//					.getClaimInvoiceProcessingBean();
//
//			List<ClaimDetailsAnnexureBean> annexure1 = claim.getClaimDetailsAnnexureBean();
//
//			// annexure1 list
//			model.addAttribute("ann1list", annexure1);
//
//			// for tab 1 prefilled details
//			claimBean.setInwardNo(claim.getInwardNo());
//			claimBean.setInwardItemNo(claim.getInwardItemNo());
//			claimBean.setPolicyNo(claim.getPolicyNo());
//			claimBean.setRodDate(claim.getRodDate());
//			claimBean.setRodId(claim.getRodId());
//			claimBean.setClaimApplicationDate(claim.getClaimApplicationDate());
//			claimBean.setGrossLossValue(claim.getGrossLossValue());
//			claimBean.setReasonForClaim(claim.getReasonForClaim());
//			claimBean.setRemarks(claim.getRemarks());
//			model.addAttribute("claimApplicationBean", claimBean);
//
//			// Annexure Details tab
//			model.addAttribute("ann1", new ClaimDetailsAnnexureBean());
//			model.addAttribute("ann2", new ClaimExperienceWithBuyerAnnexureBean());
//
//			// Assessment/Processing of Invoices Tab
//			model.addAttribute("ListOfInvoicesRaisedButNotPaid", invoiceList);
//			model.addAttribute("claimInvoiceProcessingBean", new ClaimInvoiceProcessingBean());
//			model.addAttribute("claimPartPaymentScheduleBean", new ClaimPartPaymentScheduleMasterBean());
//			model.addAttribute("documentChecklist", policyClaimService.getClaimDocumentCheckList());
//
//			// Claim Decision Tab
//			model.addAttribute("claimProcessingBean", claim.getClaimProcessingBean());
//
//			// pass id for current tab
//			model.addAttribute("id", 3);
//			// set all model here------------------------
//
//			return "claim/editClaimApplicationForm";
//		}
	
		
		// update the session object
		//claim.setClaimDocumentChecklistBean(claimBean.getClaimDocumentChecklistBean());
		
		//take set to avoid duplicate document id
		Set<String> mergedList = new HashSet<>();
		mergedList.addAll(documentCheck);
		//check to be added - read only checkbox for those are selected in add mode
		mergedList.addAll(claim.getClaimDocumentChecklistBean());
		
		claim.setClaimDocumentChecklistBean(new ArrayList<>(mergedList));
		
		session.setAttribute("editPolicyClaimBean", claim);

		// set all model here------------------------
		List<ClaimInvoiceProcessingBean> invoiceList = claim.getClaimProcessingBean().getClaimInvoiceProcessingBean();

		List<ClaimDetailsAnnexureBean> annexure1 = claim.getClaimDetailsAnnexureBean();

		// annexure1 list
		model.addAttribute("ann1list", annexure1);

		// for tab 1 prefilled details
		model.addAttribute("claimApplicationBean", claim);

		// Annexure Details tab
		model.addAttribute("ann1", new ClaimDetailsAnnexureBean());
		model.addAttribute("ann2", new ClaimExperienceWithBuyerAnnexureBean());

		// Assessment/Processing of Invoices Tab
		model.addAttribute("ListOfInvoicesRaisedButNotPaid", invoiceList);
		model.addAttribute("claimInvoiceProcessingBean", new ClaimInvoiceProcessingBean());
		model.addAttribute("claimPartPaymentScheduleBean", new ClaimPartPaymentScheduleMasterBean());
		model.addAttribute("documentChecklist", policyClaimService.getClaimDocumentCheckList());

		// Claim Decision Tab
		model.addAttribute("claimProcessingBean", claim.getClaimProcessingBean());

		// pass id for current tab
		model.addAttribute("id", 3);

		// Success updated Message
		model.addAttribute("updateScrutinyUploadDocumentsFlag", "success");

		// set all model here------------------------

		return "claim/editClaimApplicationForm";
	}
	
	/**
	 * Show Process Invoice Div For Invoice Processing in Edit Claim Details
	 * 
	 * @param invoiceNo
	 */
	@PostMapping("/viewProcessInvoice")
	public String showprocessInvoice(Model model, @RequestParam("invoiceNo") String invoiceNo,
			@ModelAttribute("claimApplicationBean") ClaimApplicationBean claimApplicationBean, HttpSession session) {

		ClaimApplicationBean claimBean = (ClaimApplicationBean) session.getAttribute("editPolicyClaimBean");
		// System.out.println(session.getAttribute("policyClaimBean"));

		// Get Invoice Data based on Invoice No from List of Invoices
		List<ClaimInvoiceProcessingBean> claimInvoiceProcessingList = claimBean.getClaimProcessingBean()
				.getClaimInvoiceProcessingBean();
		Iterator<ClaimInvoiceProcessingBean> iterator = claimInvoiceProcessingList.iterator();
		ClaimInvoiceProcessingBean claimInvoiceProcessingBean = new ClaimInvoiceProcessingBean();
		while (iterator.hasNext()) {
			claimInvoiceProcessingBean = iterator.next();
			if (claimInvoiceProcessingBean.getInvoiceNo().equals(invoiceNo)) {
				model.addAttribute("claimInvoiceProcessingBean", claimInvoiceProcessingBean);
			}
		}

		// update session value
		session.setAttribute("editPolicyClaimBean", claimBean);
		model.addAttribute("claimApplicationBean", claimBean);

		// Annexure Details tab
		model.addAttribute("ann1", new ClaimDetailsAnnexureBean());
		model.addAttribute("ann2", new ClaimExperienceWithBuyerAnnexureBean());

		// pass list of annexures in model
		model.addAttribute("ann1list", claimBean.getClaimDetailsAnnexureBean());

		// tab 3 models
		model.addAttribute("documentChecklist", policyClaimService.getClaimDocumentCheckList());

		// Assessment/Processing of Invoices Tab
		model.addAttribute("ListOfInvoicesRaisedButNotPaid",
				claimBean.getClaimProcessingBean().getClaimInvoiceProcessingBean());
		model.addAttribute("claimPartPaymentScheduleBean", new ClaimPartPaymentScheduleMasterBean());
		model.addAttribute("invoiceNo", invoiceNo);

		// Claim Decision Tab
		model.addAttribute("claimProcessingBean", claimBean.getClaimProcessingBean());

		model.addAttribute("id", 4);

		// show Process Invoice Form
		model.addAttribute("showProcessInvoiceForm", "yes");
		return "claim/editClaimApplicationForm";

	}

	/**
	 * Save Invoice Processing Details in Edit Claim Details
	 * 
	 * @param claimInvoiceProcessingBean
	 */
	@PostMapping("/updateProcessInvoiceDecision")
	public String updateInvoiceDecision(Model model,
			@Validated(ProcessInvoiceInfo.class) @ModelAttribute("claimInvoiceProcessingBean") ClaimInvoiceProcessingBean claimInvoiceProcessingBean,
			BindingResult result, HttpSession session) {

		// get session varibale value
		ClaimApplicationBean claimBean = (ClaimApplicationBean) session.getAttribute("editPolicyClaimBean");
		if (result.hasErrors()) {
			model.addAttribute("claimApplicationBean", claimBean);

			// Annexure Details tab
			model.addAttribute("ann1", new ClaimDetailsAnnexureBean());
			model.addAttribute("ann2", new ClaimExperienceWithBuyerAnnexureBean());

			// tab 3 models
			model.addAttribute("documentChecklist", policyClaimService.getClaimDocumentCheckList());

			// pass list of annexures in model
			model.addAttribute("ann1list", claimBean.getClaimDetailsAnnexureBean());

			// Assessment/Processing of Invoices Tab
			model.addAttribute("ListOfInvoicesRaisedButNotPaid",
					claimBean.getClaimProcessingBean().getClaimInvoiceProcessingBean());
			model.addAttribute("claimPartPaymentScheduleBean", new ClaimPartPaymentScheduleMasterBean());
			model.addAttribute("invoiceNo", claimInvoiceProcessingBean.getInvoiceNo());

			// Claim Decision Tab
			model.addAttribute("claimProcessingBean", claimBean.getClaimProcessingBean());

			model.addAttribute("id", 4);
			model.addAttribute("showProcessInvoiceForm", "yes");
			return "claim/editClaimApplicationForm";
		}
		
		if(claimInvoiceProcessingBean.getAmountApproved().compareTo(claimInvoiceProcessingBean.getAmountDue())==1) {
			model.addAttribute("claimApplicationBean", claimBean);

			// Annexure Details tab
			model.addAttribute("ann1", new ClaimDetailsAnnexureBean());
			model.addAttribute("ann2", new ClaimExperienceWithBuyerAnnexureBean());

			// pass list of annexures in model
			model.addAttribute("ann1list", claimBean.getClaimDetailsAnnexureBean());

			// tab 3 models
			model.addAttribute("claimChecklist", policyClaimService.getClaimDocumentCheckList());

			// Assessment/Processing of Invoices Tab
			model.addAttribute("ListOfInvoicesRaisedButNotPaid",
					claimBean.getClaimProcessingBean().getClaimInvoiceProcessingBean());
			model.addAttribute("claimPartPaymentScheduleBean", new ClaimPartPaymentScheduleMasterBean());

			// Claim Decision Tab
			model.addAttribute("claimProcessingBean", claimBean.getClaimProcessingBean());

			model.addAttribute("id", 4);
			
			model.addAttribute("invoiceNo", claimInvoiceProcessingBean.getInvoiceNo());
			
			// to keep open the process invoice div
			model.addAttribute("showProcessInvoiceForm", "yes");
			
			// Error Message
			model.addAttribute("AmountExceedsError", true);
			
			return "claim/editClaimApplicationForm";
		}

		// set ProcessInvoice status value
		claimInvoiceProcessingBean.setProcessInvoiceStatus("Processed");

		// set update invoice Processing bean with remaining values
		int counter = 0;
		for (ClaimInvoiceProcessingBean c : claimBean.getClaimProcessingBean().getClaimInvoiceProcessingBean()) {
			if (c.getInvoiceNo().equals(claimInvoiceProcessingBean.getInvoiceNo())) {
				claimBean.getClaimProcessingBean().getClaimInvoiceProcessingBean().set(counter,
						claimInvoiceProcessingBean);
			}
			counter++;
		}

		// update session varibale
		session.setAttribute("editPolicyClaimBean", claimBean);
		model.addAttribute("claimApplicationBean", claimBean);

		// Annexure Details tab
		model.addAttribute("ann1", new ClaimDetailsAnnexureBean());
		model.addAttribute("ann2", new ClaimExperienceWithBuyerAnnexureBean());

		// pass list of annexures in model
		model.addAttribute("ann1list", claimBean.getClaimDetailsAnnexureBean());

		// tab 3 models
		model.addAttribute("documentChecklist", policyClaimService.getClaimDocumentCheckList());

		// Assessment/Processing of Invoices Tab
		model.addAttribute("ListOfInvoicesRaisedButNotPaid",
				claimBean.getClaimProcessingBean().getClaimInvoiceProcessingBean());
		model.addAttribute("claimPartPaymentScheduleBean", new ClaimPartPaymentScheduleMasterBean());

		// Claim Decision Tab
		model.addAttribute("claimProcessingBean", claimBean.getClaimProcessingBean());

		model.addAttribute("id", 4);
		return "claim/editClaimApplicationForm";
	}
	

	/**
	 * Update Claim Decision Details
	 * 
	 * @param claimProcessingBean
	 */
	@PostMapping("updateClaimDecisionDetails")
	public String updateClaimDecisionDetails(
			@Validated(ClaimDecisionInfo.class) @ModelAttribute("claimProcessingBean") ClaimProcessingBean claimProcessingBean,
			BindingResult result, Model model, HttpSession session) {

		// fetch session object and delete it
		ClaimApplicationBean claim = (ClaimApplicationBean) session.getAttribute("editPolicyClaimBean");

		if (result.hasErrors()) {

			// set all model here------------------------
			List<ClaimInvoiceProcessingBean> invoiceList = claim.getClaimProcessingBean()
					.getClaimInvoiceProcessingBean();

			List<ClaimDetailsAnnexureBean> annexure1 = claim.getClaimDetailsAnnexureBean();

			// annexure1 list
			model.addAttribute("ann1list", annexure1);

			// for tab 1 prefilled details
			model.addAttribute("claimApplicationBean", claim);

			// Annexure Details tab
			model.addAttribute("ann1", new ClaimDetailsAnnexureBean());
			model.addAttribute("ann2", new ClaimExperienceWithBuyerAnnexureBean());

			// Assessment/Processing of Invoices Tab
			model.addAttribute("ListOfInvoicesRaisedButNotPaid", invoiceList);
			model.addAttribute("claimInvoiceProcessingBean", new ClaimInvoiceProcessingBean());
			model.addAttribute("claimPartPaymentScheduleBean", new ClaimPartPaymentScheduleMasterBean());
			model.addAttribute("documentChecklist", policyClaimService.getClaimDocumentCheckList());

			// Claim Decision Tab
			model.addAttribute("claimProcessingBean", claimProcessingBean);

			// pass id for current tab
			model.addAttribute("id", 5);
			// set all model here------------------------

			return "claim/editClaimApplicationForm";
		}

		// set all model here------------------------
		List<ClaimInvoiceProcessingBean> invoiceList = claim.getClaimProcessingBean().getClaimInvoiceProcessingBean();

		// update the claim processing bean here
		claimProcessingBean.setClaimInvoiceProcessingBean(invoiceList);
		claim.setClaimProcessingBean(claimProcessingBean);
		session.setAttribute("editPolicyClaimBean", claim);

		List<ClaimDetailsAnnexureBean> annexure1 = claim.getClaimDetailsAnnexureBean();
		
		/*
		 * if(claimProcessingBean.getClaimAmountApproved().compareTo(claim.
		 * getGrossLossValue())== 1) {
		 * 
		 * model.addAttribute("claimApplicationBean", claim);
		 * 
		 * // Annexure Details tab model.addAttribute("ann1", new
		 * ClaimDetailsAnnexureBean()); model.addAttribute("ann2", new
		 * ClaimExperienceWithBuyerAnnexureBean());
		 * 
		 * // pass list of annexures in model model.addAttribute("ann1list",
		 * claim.getClaimDetailsAnnexureBean());
		 * 
		 * // tab 3 models model.addAttribute("documentChecklist",
		 * policyClaimService.getClaimDocumentCheckList());
		 * 
		 * // Assessment/Processing of Invoices Tab
		 * model.addAttribute("ListOfInvoicesRaisedButNotPaid",
		 * claim.getClaimProcessingBean().getClaimInvoiceProcessingBean());
		 * model.addAttribute("claimPartPaymentScheduleBean", new
		 * ClaimPartPaymentScheduleMasterBean()); model.addAttribute("id", 5);
		 * 
		 * // Claim Decision Tab model.addAttribute("claimProcessingBean",
		 * claim.getClaimProcessingBean());
		 * 
		 * // Error Message model.addAttribute("ClaimAmountApprovedExceedsError", true);
		 * return "claim/editClaimApplicationForm"; }
		 */

		// annexure1 list
		model.addAttribute("ann1list", annexure1);

		// for tab 1 prefilled details
		model.addAttribute("claimApplicationBean", claim);

		// Annexure Details tab
		model.addAttribute("ann1", new ClaimDetailsAnnexureBean());
		model.addAttribute("ann2", new ClaimExperienceWithBuyerAnnexureBean());

		// Assessment/Processing of Invoices Tab
		model.addAttribute("ListOfInvoicesRaisedButNotPaid", invoiceList);
		model.addAttribute("claimInvoiceProcessingBean", new ClaimInvoiceProcessingBean());
		model.addAttribute("claimPartPaymentScheduleBean", new ClaimPartPaymentScheduleMasterBean());
		model.addAttribute("documentChecklist", policyClaimService.getClaimDocumentCheckList());

		// Claim Decision Tab
		model.addAttribute("claimProcessingBean", claim.getClaimProcessingBean());

		// pass id for current tab
		model.addAttribute("id", 5);

		// Success updated Message
		model.addAttribute("updateClaimDecisionDetailsFlag", "success");
		// set all model here------------------------

		// update the claim from details in mock json file
		int result1 = policyClaimService.updateClaimApplicationDetails(claim,claim.getClaimNo());
		
		if(result1==1) {
			session.removeAttribute("editPolicyClaimBean");
		}
		return "redirect:/claim/listClaimForm";

	}
	
	
	//ajax call file upload in (add mode)
	@PostMapping("/uploadClaimScrutinyDocs")
	public @ResponseBody void receiveUploadedFiles(@RequestParam("file") MultipartFile file,
									@RequestParam("checklistId") String checklistId, 
									Model model,
									HttpSession session) {

		//take claim bean from session and map id to claimDocumentchecklist bean 
		
		//fetch session object and update it
		ClaimApplicationBean claimBean = (ClaimApplicationBean) session.getAttribute("policyClaimBean");
		System.out.println("checklist id : "+checklistId);
		
		//store it in global list of documents
		if(documentCheck == null) documentCheck = new ArrayList<>();
		
		documentCheck.add(checklistId);
		
		//directory where files gets stored
		Path fileNameAndPath = Paths.get(uploadDirectory, file.getOriginalFilename());

		try {
			//actual upload
			Files.write(fileNameAndPath, file.getBytes());
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	

}
