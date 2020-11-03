package in.ecgc.erp.pebPolicy.controller.claim;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

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

import in.ecgc.erp.pebPolicy.exceptions.BasicClaimRepresentationInfo;
import in.ecgc.erp.pebPolicy.exceptions.ClaimDecisionInfo;
import in.ecgc.erp.pebPolicy.exceptions.ClaimScrutinyInfo;
import in.ecgc.erp.pebPolicy.exceptions.ProcessInvoiceInfo;
import in.ecgc.erp.pebPolicy.model.claim.ClaimApplicationBean;
import in.ecgc.erp.pebPolicy.model.claim.ClaimDetailsAnnexureBean;
import in.ecgc.erp.pebPolicy.model.claim.ClaimInvoiceProcessingBean;
import in.ecgc.erp.pebPolicy.model.claim.ClaimPartPaymentScheduleMasterBean;
import in.ecgc.erp.pebPolicy.model.claim.ClaimProcessingBean;
import in.ecgc.erp.pebPolicy.model.claim.ClaimRepresentationBean;
import in.ecgc.erp.pebPolicy.service.claim.PolicyClaimService;

@Controller
@RequestMapping("/claimRepresentation")
public class ClaimRepresentationController {
	
	public static String uploadDirectory = System.getProperty("user.dir") + "/uploads/cr";
	
	//list for maintaining uploaded document via ajax
	List<String> documentCheck = null;

	@Autowired
	private PolicyClaimService policyClaimService;

	List<ClaimInvoiceProcessingBean> invoiceList = null;
	
	List<ClaimInvoiceProcessingBean> editInvoiceList = null;

	@GetMapping("applyClaimRepresentation")
	public String applyForClaimRepresenation(
			@ModelAttribute("claimRepresentationBean") ClaimRepresentationBean claimRepresentationBean) {
		return "claimRepresentation/applyClaimRepresentation";
	}
	
	@PostMapping("getClaimRepresentationForm")
	public String getClaimRepresentationForm(@Valid @ModelAttribute("claimRepresentationBean") 
											ClaimRepresentationBean claimRepresentationBean ,
											BindingResult result, 
											Model model) {
		
		
		if(result.hasErrors()) {
			
			model.addAttribute("claimRepresentationBean", claimRepresentationBean);
			return "claimRepresentation/applyClaimRepresentation";
		}
	
		model.addAttribute("claimRepresentationBean", claimRepresentationBean);

		ClaimApplicationBean claimBean = policyClaimService
				.getClaimApplicationBeanById(claimRepresentationBean.getClaimNo(), claimRepresentationBean.getPolicyNo());

		if(claimBean.getClaimRepresentationBeanList()!=null) {
			Iterator<ClaimRepresentationBean> cRepresentationBean = claimBean.getClaimRepresentationBeanList().iterator();
			ClaimRepresentationBean bean = new ClaimRepresentationBean();
			while (cRepresentationBean.hasNext()) {
				bean = cRepresentationBean.next();
				if (bean.getCrId().longValue() == 0) {
					cRepresentationBean.remove();
				}
	
			}
		}

		// previous claim Representation List
		model.addAttribute("previousClaimRepresentationList", claimBean.getClaimRepresentationBeanList());

		// pass id for current tabs
		model.addAttribute("id", 1);

		// tab 3 models
		model.addAttribute("claimChecklist", policyClaimService.getClaimDocumentCheckList());

		// Assessment/Processing of Invoices Tab
		model.addAttribute("claimInvoiceProcessingBean", new ClaimInvoiceProcessingBean());
		model.addAttribute("claimPartPaymentScheduleBean", new ClaimPartPaymentScheduleMasterBean());

		// Claim Decision Tab
		model.addAttribute("claimProcessingBean", new ClaimProcessingBean());
		return "claimRepresentation/claimRepresentationForm";

	}

//	@PostMapping("getClaimRepresentationForm")
//	public String getClaimRepresentationForm(ClaimApplicationBean claimApplicationBean, Model model) {
//		
//		ClaimRepresentationBean crBean = new ClaimRepresentationBean();
//		crBean.setPolicyNo(claimApplicationBean.getPolicyNo());
//		crBean.setClaimNo(claimApplicationBean.getClaimNo());
//		model.addAttribute("claimRepresentationBean", crBean);
//
//		ClaimApplicationBean claimBean = policyClaimService
//				.getClaimApplicationBeanById(claimApplicationBean.getClaimNo(), claimApplicationBean.getPolicyNo());
//
//		//before iterating check claim representation list is present or not 
//		if(claimBean.getClaimRepresentationBeanList()!=null) {
//			Iterator<ClaimRepresentationBean> cRepresentationBean = claimBean.getClaimRepresentationBeanList().iterator();
//			ClaimRepresentationBean bean = new ClaimRepresentationBean();
//			while (cRepresentationBean.hasNext()) {
//				bean = cRepresentationBean.next();
//				if (bean.getCrId() == 0) {
//					claimBean.getClaimRepresentationBeanList().remove(0);
//				}
//	
//			}
//			
//			// previous claim Representation List
//			//model.addAttribute("previousClaimRepresentationList", claimBean.getClaimRepresentationBeanList());
//		}
//		
//		// previous claim Representation List
//		model.addAttribute("previousClaimRepresentationList", 
//				claimBean.getClaimRepresentationBeanList() != null ? claimBean.getClaimRepresentationBeanList() : null);
//
//		// model for pre-populating the claim Bean annexure list for selecting in cr
//		//model.addAttribute("ann1list", claimBean.getClaimDetailsAnnexureBean());
//		
//		// pass id for current tabs
//		model.addAttribute("id", 1);
//
//		// tab 3 models
//		model.addAttribute("claimChecklist", policyClaimService.getClaimDocumentCheckList());
//
//		// Assessment/Processing of Invoices Tab
//		model.addAttribute("claimInvoiceProcessingBean", new ClaimInvoiceProcessingBean());
//		model.addAttribute("claimPartPaymentScheduleBean", new ClaimPartPaymentScheduleMasterBean());
//
//		// Claim Decision Tab
//		model.addAttribute("claimProcessingBean", new ClaimProcessingBean());
//		return "claimRepresentation/claimRepresentationForm";
//
//	}

	@PostMapping("saveBasicClaimRepresentationDetails")
	public String saveBasicClaimRepresentationDetails(
			@Validated(BasicClaimRepresentationInfo.class) @ModelAttribute("claimRepresentationBean") 
			ClaimRepresentationBean claimRepresentationBean,
			BindingResult result, 
			Model model,
			HttpSession session) {
		
		ClaimApplicationBean claimBean = policyClaimService.getClaimApplicationBeanById(
				claimRepresentationBean.getClaimNo(), claimRepresentationBean.getPolicyNo());

		if(claimBean.getClaimRepresentationBeanList()!=null) {
			Iterator<ClaimRepresentationBean> cRepresentationBean = claimBean.getClaimRepresentationBeanList().iterator();
			ClaimRepresentationBean bean = new ClaimRepresentationBean();
			while (cRepresentationBean.hasNext()) {
				bean = cRepresentationBean.next();
				if (bean.getCrId() == 0) {
					claimBean.getClaimRepresentationBeanList().remove(0);
				}
	
			}
			
			
		}

		// previous claim Representation List
		model.addAttribute("previousClaimRepresentationList",
				claimBean.getClaimRepresentationBeanList()!=null ?  claimBean.getClaimRepresentationBeanList() : null);

		if (result.hasErrors()) {
			model.addAttribute("claimRepresentationBean", claimRepresentationBean);

			// pass id for current tabs
			model.addAttribute("id", 1);

			// tab 3 models
			model.addAttribute("claimChecklist", policyClaimService.getClaimDocumentCheckList());

			// Assessment/Processing of Invoices Tab
			model.addAttribute("claimInvoiceProcessingBean", new ClaimInvoiceProcessingBean());
			model.addAttribute("claimPartPaymentScheduleBean", new ClaimPartPaymentScheduleMasterBean());

			// Claim Decision Tab
			model.addAttribute("claimProcessingBean", new ClaimProcessingBean());

			return "claimRepresentation/claimRepresentationForm";
		} else {
			claimRepresentationBean.setClaimDetailsAnnexureBean(claimBean.getClaimDetailsAnnexureBean());
			
			// model.addAttribute("claimRepresentationBean", crBean);
			ClaimRepresentationBean claimRBean = (ClaimRepresentationBean) session
					.getAttribute("policyClaimRepresentationBean");

			if (claimRBean == null) {
				// int claimNo = new Random().nextInt(1001);
				// claimBean.setClaimNo((long) claimNo);
				
				//claim representation already exists and getting the max crId and adding new cr
				if(claimBean.getClaimRepresentationBeanList()!=null) {
					List<ClaimRepresentationBean> crList = claimBean.getClaimRepresentationBeanList();
					ClaimRepresentationBean user2 = crList.stream().max(Comparator.comparingLong(ClaimRepresentationBean::getCrId)).get();
					Long crId = user2.getCrId();
					claimRepresentationBean.setCrId(++crId);
				}else {
					//no previous cr Id exists so created the first claim representation
					claimRepresentationBean.setCrId(1l);
				}
				
				//set basic claim details 
				

				// and store it in session
				session.setAttribute("policyClaimRepresentationBean", claimRepresentationBean);

				// pass id for current tab
				model.addAttribute("id", 1);

				// tab 1 models
				model.addAttribute("claimRepresentationBean", claimRepresentationBean);

				// pass list of annexures in model
				model.addAttribute("ann1list", claimRepresentationBean.getClaimDetailsAnnexureBean());

				// tab 3 models
				model.addAttribute("claimChecklist", policyClaimService.getClaimDocumentCheckList());

				// Assessment/Processing of Invoices Tab
				model.addAttribute("claimInvoiceProcessingBean", new ClaimInvoiceProcessingBean());
				model.addAttribute("claimPartPaymentScheduleBean", new ClaimPartPaymentScheduleMasterBean());

				// Claim Decision Tab
				model.addAttribute("claimProcessingBean", new ClaimProcessingBean());

				// Success Saved Message
				model.addAttribute("BasicClaimRepresentationDetailsFlag", "success");
				
			} else {
				
				//updating the session object because cr id exists
				ClaimRepresentationBean sessionClaimRBean = (ClaimRepresentationBean) session
						.getAttribute("policyClaimRepresentationBean");
				// claimBean.setClaimNo(claim.getClaimNo());

				// pass id for current tab
				model.addAttribute("id", 1);

				// Set updated values in session variable
				sessionClaimRBean.setInwardNo(claimRepresentationBean.getInwardNo());
				sessionClaimRBean.setInwardItemNo(claimRepresentationBean.getInwardItemNo());
				sessionClaimRBean.setPolicyNo(claimRepresentationBean.getPolicyNo());
				sessionClaimRBean.setClaimRepresentationDate(claimRepresentationBean.getClaimRepresentationDate());
				
				sessionClaimRBean
						.setReasonForClaimRepresentation(claimRepresentationBean.getReasonForClaimRepresentation());
				
				sessionClaimRBean.setRemarks(claimRepresentationBean.getRemarks());

				// and store it in session
				session.setAttribute("policyClaimRepresentationBean", sessionClaimRBean);

				// tab 1 models
				model.addAttribute("claimRepresentationBean", sessionClaimRBean);

				// pass list of annexures in model
				model.addAttribute("ann1list", sessionClaimRBean.getClaimDetailsAnnexureBean());

				// tab 3 models
				model.addAttribute("claimChecklist", policyClaimService.getClaimDocumentCheckList());

				// Assessment/Processing of Invoices Tab
				// model.addAttribute("ListOfInvoicesRaisedButNotPaid",sessionClaimRBean.getClaimProcessingBean().getClaimInvoiceProcessingBean());
				model.addAttribute("claimInvoiceProcessingBean", new ClaimInvoiceProcessingBean());
				// model.addAttribute("claimPartPaymentScheduleBean", new
				// ClaimPartPaymentScheduleMasterBean());

				// Claim Decision Tab
				model.addAttribute("claimProcessingBean", sessionClaimRBean.getClaimProcessingBean()!=null?sessionClaimRBean.getClaimProcessingBean():new ClaimProcessingBean());

				// Success Saved Message
				model.addAttribute("BasicClaimRepresentationDetailsFlag", "success");
			}
		}
		return "claimRepresentation/claimRepresentationForm";

	}

	/**
	 * show View Invoice Annexure 1 Modal with data
	 * 
	 * @param invoiceNo
	 */
	@GetMapping("viewAnnexure1/{InvoiceNo}")
	public String editAnnexureClaimDetails(@PathVariable("InvoiceNo") String invoiceNo, Model model,
			HttpSession session) {

		// get session
		ClaimRepresentationBean claimRBean = (ClaimRepresentationBean) session
				.getAttribute("policyClaimRepresentationBean");
		
		ClaimApplicationBean claimBean = policyClaimService.getClaimApplicationBeanById(claimRBean.getClaimNo(),
				claimRBean.getPolicyNo());

		if(claimBean.getClaimRepresentationBeanList()!=null) {
			Iterator<ClaimRepresentationBean> cRepresentationBean = claimBean.getClaimRepresentationBeanList().iterator();
			ClaimRepresentationBean bean = new ClaimRepresentationBean();
			while (cRepresentationBean.hasNext()) {
				bean = cRepresentationBean.next();
				if (bean.getCrId() == 0) {
					claimBean.getClaimRepresentationBeanList().remove(0);
				}
	
			}
			
			// previous claim Representation List
			model.addAttribute("previousClaimRepresentationList", claimBean.getClaimRepresentationBeanList());
		}

		// find invoice no and add to model
		List<ClaimDetailsAnnexureBean> list1 = claimRBean.getClaimDetailsAnnexureBean();
		ClaimDetailsAnnexureBean annexure1 = list1.stream().filter(a1 -> a1.getInvoiceNo().equals(invoiceNo))
				.findFirst().get();

		// model for prepopulating data into modal
		model.addAttribute("annexure1", annexure1);

		// pass id in model
		model.addAttribute("id", 2);

		// all datatable list of annexures
		model.addAttribute("ann1list", claimRBean.getClaimDetailsAnnexureBean());

		// tab 1 models
		model.addAttribute("claimRepresentationBean", claimRBean);

		// tab 3 models
		model.addAttribute("claimChecklist", policyClaimService.getClaimDocumentCheckList());

		// Assessment/Processing of Invoices Tab
		// model.addAttribute("ListOfInvoicesRaisedButNotPaid",claimBean.getClaimProcessingBean().getClaimInvoiceProcessingBean());
		model.addAttribute("claimInvoiceProcessingBean", new ClaimInvoiceProcessingBean());
		model.addAttribute("claimPartPaymentScheduleBean", new ClaimPartPaymentScheduleMasterBean());

		// Claim Decision Tab
		if (claimRBean.getClaimProcessingBean() != null) {
			model.addAttribute("claimProcessingBean", claimRBean.getClaimProcessingBean());
		} else {
			model.addAttribute("claimProcessingBean", new ClaimProcessingBean());
		}

		// pass id to pop modal for update
		model.addAttribute("viewAnn1", "yes");

		return "claimRepresentation/claimRepresentationForm";
	}

	@PostMapping("saveAnnexureDetails")
	public String saveAnnexureDetails(
			@ModelAttribute("claimRepresentationBean") ClaimRepresentationBean claimRepresentationBean,
			@RequestParam(value = "cers", required = false) int[] cers, Model model, HttpSession session) {

		// get session object
		ClaimRepresentationBean claimRBean = (ClaimRepresentationBean) session
				.getAttribute("policyClaimRepresentationBean");

		ClaimApplicationBean claimBean = policyClaimService.getClaimApplicationBeanById(claimRBean.getClaimNo(),
				claimRBean.getPolicyNo());

		Iterator<ClaimRepresentationBean> cRepresentationBean = claimBean.getClaimRepresentationBeanList().iterator();
		ClaimRepresentationBean bean = new ClaimRepresentationBean();
		while (cRepresentationBean.hasNext()) {
			bean = cRepresentationBean.next();
			if (bean.getCrId() == 0) {
				claimBean.getClaimRepresentationBeanList().remove(0);
			}

		}

		// previous claim Representation List
		model.addAttribute("previousClaimRepresentationList", claimBean.getClaimRepresentationBeanList());

		if (claimRepresentationBean.getClaimDetailsAnnexureBeanList().isEmpty()) { 
			System.out.println("Hello");
			// pass list in model for datatable
			model.addAttribute("ann1list", claimRBean.getClaimDetailsAnnexureBean());
			
			// pass id in model 
			model.addAttribute("id", 2);

			// and other tab models // tab 1 models
			model.addAttribute("claimRepresentationBean", claimRBean);

			// tab 3 models
			model.addAttribute("claimChecklist", policyClaimService.getClaimDocumentCheckList());

			// Assessment/Processing of Invoices Tab
			model.addAttribute("ListOfInvoicesRaisedButNotPaid", invoiceList);
			model.addAttribute("claimInvoiceProcessingBean", new ClaimInvoiceProcessingBean());
			model.addAttribute("claimPartPaymentScheduleBean", new ClaimPartPaymentScheduleMasterBean());

			// Claim Decision Tab
			model.addAttribute("claimProcessingBean", new ClaimProcessingBean());

			// Error Message
			model.addAttribute("NoInvoiceSelected", true);
			return "claimRepresentation/claimRepresentationForm";
		}

		if (invoiceList == null) {
			invoiceList = new ArrayList<ClaimInvoiceProcessingBean>();
		}

		// find invoice no and add to model

		Iterator<ClaimDetailsAnnexureBean> iterator = claimRBean.getClaimDetailsAnnexureBean().iterator();
		ClaimDetailsAnnexureBean ann1Bean = new ClaimDetailsAnnexureBean();
		while (iterator.hasNext()) {
			ann1Bean = iterator.next();
			
			for (int i = 0; i < claimRepresentationBean.getClaimDetailsAnnexureBeanList().size(); i++ ) {
				if (ann1Bean.getInvoiceNo().equals(claimRepresentationBean.getClaimDetailsAnnexureBeanList().get(i))) {
					ClaimInvoiceProcessingBean claimInvoiceProcessing = new ClaimInvoiceProcessingBean();
					claimInvoiceProcessing.setInvoiceNo(ann1Bean.getInvoiceNo());
					
					if(claimRBean.getClaimProcessingBean()!=null) {
						if(claimRBean.getClaimProcessingBean().getClaimInvoiceProcessingBean()!=null) {
							ClaimInvoiceProcessingBean p = claimRBean.getClaimProcessingBean().getClaimInvoiceProcessingBean()
					                .stream()
					                .filter(producer -> producer.getInvoiceNo().toString().equals(claimInvoiceProcessing.getInvoiceNo().toString()))
					                .findFirst()
					                .orElse(null);
							if(p!=null) {
								if(p.getProcessInvoiceStatus()=="Processed" || p.getProcessInvoiceStatus()=="Pending") {
									//do nothing
								}
							}else {
								// set Annexure 1 data in Invoice Processing Bean
								claimInvoiceProcessing.setClaimNo(ann1Bean.getClaimNo());
								claimInvoiceProcessing.setCrId(claimRBean.getCrId());
								claimInvoiceProcessing.setDmsId(ann1Bean.getDmsId());
								claimInvoiceProcessing.setGrossInvoiceValueINR(ann1Bean.getGrossInvoiceValueINR());
								claimInvoiceProcessing.setInvoiceDate(ann1Bean.getInvoiceDate());
								claimInvoiceProcessing.setOriginalDueDate(ann1Bean.getOriginalDueDate());
								claimInvoiceProcessing.setExtendedDueDate(ann1Bean.getExtendedDueDate());
								claimInvoiceProcessing.setAmountDue(ann1Bean.getAmountDue());
								claimInvoiceProcessing.setProcessInvoiceStatus("Pending");
			
								// Add in InvoiceProcessingBean in list of Invoice Processing Bean
								invoiceList.add(claimInvoiceProcessing);
							}
						}
					}else {
						// set Annexure 1 data in Invoice Processing Bean
						claimInvoiceProcessing.setClaimNo(ann1Bean.getClaimNo());
						claimInvoiceProcessing.setCrId(claimRBean.getCrId());
						claimInvoiceProcessing.setDmsId(ann1Bean.getDmsId());
						claimInvoiceProcessing.setInvoiceNo(ann1Bean.getInvoiceNo());
						claimInvoiceProcessing.setGrossInvoiceValueINR(ann1Bean.getGrossInvoiceValueINR());
						claimInvoiceProcessing.setInvoiceDate(ann1Bean.getInvoiceDate());
						claimInvoiceProcessing.setOriginalDueDate(ann1Bean.getOriginalDueDate());
						claimInvoiceProcessing.setExtendedDueDate(ann1Bean.getExtendedDueDate());
						claimInvoiceProcessing.setAmountDue(ann1Bean.getAmountDue());
						claimInvoiceProcessing.setProcessInvoiceStatus("Pending");
	
						// Add in InvoiceProcessingBean in list of Invoice Processing Bean
						invoiceList.add(claimInvoiceProcessing);
					}
					
				}
			}
		}
		
		// Set List of Invoice Processing Bean to claimProcessing Object
		if (claimRBean.getClaimProcessingBean() == null) {
			ClaimProcessingBean claimProcessingBean = new ClaimProcessingBean();
			claimProcessingBean.setClaimInvoiceProcessingBean(invoiceList);
			claimRBean.setClaimProcessingBean(claimProcessingBean);
		} else {
			ClaimProcessingBean claimProcessingBean = claimRBean.getClaimProcessingBean();
			claimProcessingBean.setClaimInvoiceProcessingBean(invoiceList);
			claimRBean.setClaimProcessingBean(claimProcessingBean);
		}
		
		if(claimRBean.getClaimProcessingBean()!=null) {
			if(claimRBean.getClaimProcessingBean().getClaimInvoiceProcessingBean()!=null) {
				if(claimRBean.getClaimProcessingBean().getClaimInvoiceProcessingBean().size()>claimRepresentationBean.getClaimDetailsAnnexureBeanList().size()) {
					Iterator<ClaimInvoiceProcessingBean> iterator1 = claimRBean.getClaimProcessingBean().getClaimInvoiceProcessingBean().iterator();
					ClaimInvoiceProcessingBean invoiceProcessingBean = new ClaimInvoiceProcessingBean();
					while (iterator1.hasNext()) {
						invoiceProcessingBean = iterator1.next();
						int count=0;
						for (int i = 0; i < claimRepresentationBean.getClaimDetailsAnnexureBeanList().size(); i++ ) {
							System.out.println("second list" + claimRepresentationBean.getClaimDetailsAnnexureBeanList().size());
							if(invoiceProcessingBean.getInvoiceNo().equals(claimRepresentationBean.getClaimDetailsAnnexureBeanList().get(i))) {
								count++;
							}
							
						}
						if (count==0) {
							iterator1.remove();
							
						}
						
				}
			}
		}
		}
		
		// To sum the amount due of all invoice in Gross Loss Value
		BigDecimal sumGrossLossValue =invoiceList.stream().map(ClaimInvoiceProcessingBean::getAmountDue)
		.reduce(BigDecimal.ZERO, BigDecimal::add);

		// set Sum value to Gross Loss Value to session variable
		claimRBean.setGrossLossValueINR(sumGrossLossValue);
		claimRBean.setClaimDetailsAnnexureBeanList(claimRepresentationBean.getClaimDetailsAnnexureBeanList());
		// update session vaiable
		session.setAttribute("policyClaimRepresentationBean", claimRBean);

		// pass list in model for datatable
		model.addAttribute("ann1list", claimRBean.getClaimDetailsAnnexureBean());

		// pass id in model
		model.addAttribute("id", 2);

		// and other tab models
		// tab 1 models
		model.addAttribute("claimRepresentationBean", claimRBean);

		// tab 3 models
		model.addAttribute("claimChecklist", policyClaimService.getClaimDocumentCheckList());

		// Assessment/Processing of Invoices Tab
		model.addAttribute("ListOfInvoicesRaisedButNotPaid", claimRBean.getClaimProcessingBean().getClaimInvoiceProcessingBean());
		model.addAttribute("claimInvoiceProcessingBean", new ClaimInvoiceProcessingBean());
		model.addAttribute("claimPartPaymentScheduleBean", new ClaimPartPaymentScheduleMasterBean());

		// Claim Decision Tab
		model.addAttribute("claimProcessingBean", claimRBean.getClaimProcessingBean());
		return "claimRepresentation/claimRepresentationForm";
	}

	/**
	 * Save Scrutiny Details
	 * 
	 * @param claim
	 */
	@PostMapping("saveClaimScrutiny")
	public String saveClaimScrutinyDetails(
			@Validated(ClaimScrutinyInfo.class) @ModelAttribute("claimRepresentationBean") ClaimRepresentationBean claimRepresentationBean,
			BindingResult result, Model model, HttpSession session) {

		// fetch session object
		ClaimRepresentationBean claimRBean = (ClaimRepresentationBean) session
				.getAttribute("policyClaimRepresentationBean");

		ClaimApplicationBean claimBean = policyClaimService.getClaimApplicationBeanById(claimRBean.getClaimNo(),
				claimRBean.getPolicyNo());

		if(claimBean.getClaimRepresentationBeanList()!=null) {
			Iterator<ClaimRepresentationBean> cRepresentationBean = claimBean.getClaimRepresentationBeanList().iterator();
			ClaimRepresentationBean bean = new ClaimRepresentationBean();
			while (cRepresentationBean.hasNext()) {
				bean = cRepresentationBean.next();
				if (bean.getCrId() == 0) {
					claimBean.getClaimRepresentationBeanList().remove(0);
				}
	
			}
			
			// previous claim Representation List
			model.addAttribute("previousClaimRepresentationList", claimBean.getClaimRepresentationBeanList());
		}

//		if (result.hasErrors()) {
//
//			// set remaining tab values in claim varibale
//			claimRepresentationBean.setClaimNo(claimRBean.getClaimNo());
//			claimRepresentationBean.setCrId(claimRBean.getCrId());
//			claimRepresentationBean.setInwardNo(claimRBean.getInwardNo());
//			claimRepresentationBean.setInwardItemNo(claimRBean.getInwardItemNo());
//			claimRepresentationBean.setPolicyNo(claimRBean.getPolicyNo());
//			claimRepresentationBean.setGrossLossValueINR(claimRBean.getGrossLossValueINR());
//			claimRepresentationBean.setClaimRepresentationDate(claimRBean.getClaimRepresentationDate());
//			claimRepresentationBean.setReasonForClaimRepresentation(claimRBean.getReasonForClaimRepresentation());
//			claimRepresentationBean.setRemarks(claimRBean.getRemarks());
//
//			// pass list of annexures in model
//			model.addAttribute("ann1list", claimRBean.getClaimDetailsAnnexureBean());
//
//			// pass id for current tab
//			model.addAttribute("id", 3);
//
//			// tab 1 models
//			model.addAttribute("claimRepresentationBean", claimRepresentationBean);
//
//			// tab 3 models
//			model.addAttribute("claimChecklist", policyClaimService.getClaimDocumentCheckList());
//
//			// Assessment/Processing of Invoices Tab
//			model.addAttribute("ListOfInvoicesRaisedButNotPaid",
//					claimRBean.getClaimProcessingBean().getClaimInvoiceProcessingBean());
//			model.addAttribute("claimInvoiceProcessingBean", new ClaimInvoiceProcessingBean());
//			model.addAttribute("claimPartPaymentScheduleBean", new ClaimPartPaymentScheduleMasterBean());
//
//			// Claim Decision Tab
//			model.addAttribute("claimProcessingBean", claimRBean.getClaimProcessingBean());
//
//			return "claimRepresentation/claimRepresentationForm";
//		}

		// update the session object
		//claimRBean.setClaimDocumentChecklistBean(claimRepresentationBean.getClaimDocumentChecklistBean());
		
		claimRBean.setClaimDocumentChecklistBean(documentCheck);
		session.setAttribute("policyClaimRepresentationBean", claimRBean);

		// pass list in model for datatable
		model.addAttribute("ann1list", claimRBean.getClaimDetailsAnnexureBean());

		// pass id in model
		model.addAttribute("id", 3);

		// tab 1 models
		model.addAttribute("claimRepresentationBean", claimRBean);
		// tab 3 models
		model.addAttribute("claimChecklist", policyClaimService.getClaimDocumentCheckList());

		ClaimProcessingBean cProcessingBean = claimRBean.getClaimProcessingBean();
		// Assessment/Processing of Invoices Tab
		model.addAttribute("ListOfInvoicesRaisedButNotPaid",
				cProcessingBean.getClaimInvoiceProcessingBean()!=null ? cProcessingBean.getClaimInvoiceProcessingBean() : null);
		model.addAttribute("claimInvoiceProcessingBean", new ClaimInvoiceProcessingBean());
		model.addAttribute("claimPartPaymentScheduleBean", new ClaimPartPaymentScheduleMasterBean());

		// Claim Decision Tab
		model.addAttribute("claimProcessingBean", cProcessingBean);

		// Success Saved Message
		model.addAttribute("ScrutinyUploadDocumentsFlag", "success");

		return "claimRepresentation/claimRepresentationForm";
	}

	/**
	 * Show Process Invoice Div For Invoice Processing
	 * 
	 * @param invoiceNo
	 */
	@PostMapping("/processInvoice")
	public String processInvoice(Model model, @RequestParam("invoiceNo") String invoiceNo,
			@ModelAttribute("claimRepresentationBean") ClaimRepresentationBean claimRepresentationBean,
			HttpSession session) {

		ClaimRepresentationBean claimRBean = (ClaimRepresentationBean) session
				.getAttribute("policyClaimRepresentationBean");

		ClaimApplicationBean claimBean = policyClaimService.getClaimApplicationBeanById(claimRBean.getClaimNo(),
				claimRBean.getPolicyNo());

		if(claimBean.getClaimRepresentationBeanList()!=null) {
			Iterator<ClaimRepresentationBean> cRepresentationBean = claimBean.getClaimRepresentationBeanList().iterator();
			ClaimRepresentationBean bean = new ClaimRepresentationBean();
			while (cRepresentationBean.hasNext()) {
				bean = cRepresentationBean.next();
				if (bean.getCrId() == 0) {
					claimBean.getClaimRepresentationBeanList().remove(0);
				}
	
			}
			
			// previous claim Representation List
			model.addAttribute("previousClaimRepresentationList", claimBean.getClaimRepresentationBeanList());
		}

		// Get Invoice Data based on Invoice No from List of Invoices
		List<ClaimInvoiceProcessingBean> claimInvoiceProcessingList = claimRBean.getClaimProcessingBean()
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
		session.setAttribute("policyClaimRepresentationBean", claimRBean);
		model.addAttribute("claimRepresentationBean", claimRBean);

		// pass list of annexures in model
		model.addAttribute("ann1list", claimRBean.getClaimDetailsAnnexureBean());

		// tab 3 models
		model.addAttribute("claimChecklist", policyClaimService.getClaimDocumentCheckList());

		// Assessment/Processing of Invoices Tab
		model.addAttribute("ListOfInvoicesRaisedButNotPaid",
				claimRBean.getClaimProcessingBean().getClaimInvoiceProcessingBean());
		model.addAttribute("claimPartPaymentScheduleBean", new ClaimPartPaymentScheduleMasterBean());
		model.addAttribute("invoiceNo", invoiceNo);

		// Claim Decision Tab
		model.addAttribute("claimProcessingBean", claimRBean.getClaimProcessingBean());

		model.addAttribute("id", 4);

		// show Process Invoice Form
		model.addAttribute("showProcessInvoiceForm", "yes");
		return "claimRepresentation/claimRepresentationForm";

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
		ClaimRepresentationBean claimRBean = (ClaimRepresentationBean) session
				.getAttribute("policyClaimRepresentationBean");

		ClaimApplicationBean claimBean = policyClaimService.getClaimApplicationBeanById(claimRBean.getClaimNo(),
				claimRBean.getPolicyNo());
		
		if(claimBean.getClaimRepresentationBeanList()!=null) {
			Iterator<ClaimRepresentationBean> cRepresentationBean = claimBean.getClaimRepresentationBeanList().iterator();
			ClaimRepresentationBean bean = new ClaimRepresentationBean();
			while (cRepresentationBean.hasNext()) {
				bean = cRepresentationBean.next();
				if (bean.getCrId() == 0) {
					claimBean.getClaimRepresentationBeanList().remove(0);
				}
	
			}
			
			// previous claim Representation List
			model.addAttribute("previousClaimRepresentationList", claimBean.getClaimRepresentationBeanList());
		}

		if (result.hasErrors()) {
			model.addAttribute("claimRepresentationBean", claimRBean);

			// tab 3 models
			model.addAttribute("claimChecklist", policyClaimService.getClaimDocumentCheckList());

			// pass list of annexures in model
			model.addAttribute("ann1list", claimRBean.getClaimDetailsAnnexureBean());

			// Assessment/Processing of Invoices Tab
			model.addAttribute("ListOfInvoicesRaisedButNotPaid",
					claimRBean.getClaimProcessingBean().getClaimInvoiceProcessingBean());
			model.addAttribute("claimPartPaymentScheduleBean", new ClaimPartPaymentScheduleMasterBean());
			model.addAttribute("invoiceNo", claimInvoiceProcessingBean.getInvoiceNo());

			// Claim Decision Tab
			model.addAttribute("claimProcessingBean", claimRBean.getClaimProcessingBean());

			model.addAttribute("id", 4);
			model.addAttribute("showProcessInvoiceForm", "yes");
			return "claimRepresentation/claimRepresentationForm";
		}

		if (claimInvoiceProcessingBean.getAmountApproved().compareTo(claimInvoiceProcessingBean.getAmountDue()) == 1) {
			model.addAttribute("claimRepresentationBean", claimRBean);

			// pass list of annexures in model
			model.addAttribute("ann1list", claimRBean.getClaimDetailsAnnexureBean());

			// tab 3 models
			model.addAttribute("claimChecklist", policyClaimService.getClaimDocumentCheckList());

			// Assessment/Processing of Invoices Tab
			model.addAttribute("ListOfInvoicesRaisedButNotPaid",
					claimRBean.getClaimProcessingBean().getClaimInvoiceProcessingBean());
			model.addAttribute("claimPartPaymentScheduleBean", new ClaimPartPaymentScheduleMasterBean());

			// Claim Decision Tab
			model.addAttribute("claimProcessingBean", claimRBean.getClaimProcessingBean());

			model.addAttribute("id", 4);

			model.addAttribute("invoiceNo", claimInvoiceProcessingBean.getInvoiceNo());

			// to keep open the process invoice div
			model.addAttribute("showProcessInvoiceForm", "yes");

			// Error Message
			model.addAttribute("AmountExceedsError", true);

			return "claimRepresentation/claimRepresentationForm";
		}

		// set ProcessInvoice status value
		claimInvoiceProcessingBean.setProcessInvoiceStatus("Processed");

		// set update invoice Processing bean with remaining values
		int counter = 0;
		for (ClaimInvoiceProcessingBean c : claimRBean.getClaimProcessingBean().getClaimInvoiceProcessingBean()) {
			if (c.getInvoiceNo().equals(claimInvoiceProcessingBean.getInvoiceNo())) {
				claimInvoiceProcessingBean.setCrId(claimRBean.getCrId());
				
				claimRBean.getClaimProcessingBean().getClaimInvoiceProcessingBean().set(counter,
						claimInvoiceProcessingBean);
			}
			counter++;
		}

		// update session varibale
		session.setAttribute("policyClaimRepresentationBean", claimRBean);
		model.addAttribute("claimRepresentationBean", claimRBean);

		// pass list of annexures in model
		model.addAttribute("ann1list", claimRBean.getClaimDetailsAnnexureBean());

		// tab 3 models
		model.addAttribute("claimChecklist", policyClaimService.getClaimDocumentCheckList());

		// Assessment/Processing of Invoices Tab
		model.addAttribute("ListOfInvoicesRaisedButNotPaid",
				claimRBean.getClaimProcessingBean().getClaimInvoiceProcessingBean());
		model.addAttribute("claimPartPaymentScheduleBean", new ClaimPartPaymentScheduleMasterBean());

		// Claim Decision Tab
		model.addAttribute("claimProcessingBean", claimRBean.getClaimProcessingBean());

		model.addAttribute("id", 4);
		return "claimRepresentation/claimRepresentationForm";
	}

	/**
	 * Save Claim Decision Details
	 * 
	 * @param claimProcessingBean
	 */
	@PostMapping("/saveClaimRepresentationDecisionDetails")
	public String saveClaimRepresentationDecisionDetails(Model model,
			@Validated(ClaimDecisionInfo.class) @ModelAttribute("claimProcessingBean") ClaimProcessingBean claimProcessingBean,
			BindingResult result, HttpSession session, HttpServletRequest request) {

		// get session varibale value
		ClaimRepresentationBean claimRBean = (ClaimRepresentationBean) session
				.getAttribute("policyClaimRepresentationBean");

		ClaimApplicationBean claimBean = policyClaimService.getClaimApplicationBeanById(claimRBean.getClaimNo(),
				claimRBean.getPolicyNo());

		if(claimBean.getClaimRepresentationBeanList()!=null) {
			Iterator<ClaimRepresentationBean> cRepresentationBean = claimBean.getClaimRepresentationBeanList().iterator();
			ClaimRepresentationBean bean = new ClaimRepresentationBean();
			while (cRepresentationBean.hasNext()) {
				bean = cRepresentationBean.next();
				if (bean.getCrId() == 0) {
					claimBean.getClaimRepresentationBeanList().remove(0);
				}
	
			}
			
			// previous claim Representation List
			model.addAttribute("previousClaimRepresentationList", claimBean.getClaimRepresentationBeanList());
		}

		if (result.hasErrors()) {

			model.addAttribute("claimRepresentationBean", claimRBean);

			// pass list of annexures in model
			model.addAttribute("ann1list", claimRBean.getClaimDetailsAnnexureBean());

			// tab 3 models
			model.addAttribute("claimChecklist", policyClaimService.getClaimDocumentCheckList());

			// Assessment/Processing of Invoices Tab
			model.addAttribute("ListOfInvoicesRaisedButNotPaid",
					claimRBean.getClaimProcessingBean().getClaimInvoiceProcessingBean());
			model.addAttribute("claimPartPaymentScheduleBean", new ClaimPartPaymentScheduleMasterBean());

			model.addAttribute("id", 5);
			return "claimRepresentation/claimRepresentationForm";
		}

		ClaimProcessingBean processingBean = new ClaimProcessingBean();
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
				.setClaimInvoiceProcessingBean(claimRBean.getClaimProcessingBean().getClaimInvoiceProcessingBean());

		claimRBean.setClaimProcessingBean(processingBean);

		session.setAttribute("policyClaimRepresentationBean", claimRBean);

		model.addAttribute("claimRepresentationBean", claimRBean);

		// pass list of annexures in model
		model.addAttribute("ann1list", claimRBean.getClaimDetailsAnnexureBean());

		// tab 3 models
		model.addAttribute("claimChecklist", policyClaimService.getClaimDocumentCheckList());

		// Assessment/Processing of Invoices Tab
		model.addAttribute("ListOfInvoicesRaisedButNotPaid",
				claimRBean.getClaimProcessingBean().getClaimInvoiceProcessingBean());
		model.addAttribute("claimPartPaymentScheduleBean", new ClaimPartPaymentScheduleMasterBean());
		model.addAttribute("id", 5);

		// Success Saved Message
		model.addAttribute("ClaimRepresentationDecisionDetailsFlag", "success");
		return "claimRepresentation/claimRepresentationForm";
	}

	/**
	 * Save Claim Form Details in JSON Mock File After Final Submit
	 * 
	 * @session policyClaimBean
	 */
	@GetMapping("submitClaimRepresentationForm")
	public String submitClaimRepresentationForm(Model model,
			@ModelAttribute("claimRepresentationBean") ClaimRepresentationBean claimRepresentationBean,
			HttpSession session, RedirectAttributes redirectAttributes) {
		// get session variable value
		ClaimRepresentationBean claimRBean = (ClaimRepresentationBean) session
				.getAttribute("policyClaimRepresentationBean");

		// save the claim from details in mock json file
		int result1 = policyClaimService.saveClaimRepresentationDetails(claimRBean);

		// show success message if saved in json file
		if (result1 == 1) {
			session.removeAttribute("policyClaimRepresentationBean");
			redirectAttributes.addFlashAttribute("finalSubmitMsg",
					"Claim Representation Form Details For Cr Id :" + claimRBean.getCrId() + " Saved Successfully");
		}
		 

		return "redirect:/claimRepresentation/applyClaimRepresentation";
	}

	/**
	 * Search Claim Representation Form Details
	 * 
	 */
	@GetMapping("searchClaimRepresentation")
	public String searchClaimRepresentationDetails(Model model,
			@ModelAttribute("claimRepresentationBean") ClaimRepresentationBean claimRepresentationBean) {
		return "claimRepresentation/searchClaimRepresentation";
	}
	
	/**
	 * Get List of Claim Representation for particular claimNo
	 * 
	 * @param claimApplicationBean
	 */
	@PostMapping("getClaimRepresentationList")
	public String getClaimRepresentationDetails(Model model,
			@Valid @ModelAttribute("claimRepresentationBean") ClaimRepresentationBean claimRepresentationBean,BindingResult result) {
		if(result.hasErrors()) {
			model.addAttribute("claimRepresentationBean", claimRepresentationBean);
			return "claimRepresentation/searchClaimRepresentation";
		}
		ClaimApplicationBean claimBean = policyClaimService
				.getClaimApplicationBeanById(claimRepresentationBean.getClaimNo(), claimRepresentationBean.getPolicyNo());

		if(claimBean.getClaimRepresentationBeanList()!=null) {
			Iterator<ClaimRepresentationBean> cRepresentationBean = claimBean.getClaimRepresentationBeanList().iterator();
			ClaimRepresentationBean bean = new ClaimRepresentationBean();
			while (cRepresentationBean.hasNext()) {
				bean = cRepresentationBean.next();
				if (bean.getCrId() == 0) {
					claimBean.getClaimRepresentationBeanList().remove(0);
				}
	
			}
		}
		
		// claim Representation List
		model.addAttribute("claimRepresentationList", claimBean.getClaimRepresentationBeanList()!=null ? claimBean.getClaimRepresentationBeanList() : new ArrayList<>());

		model.addAttribute("claimRepresentationBean", claimRepresentationBean);

		return "claimRepresentation/searchClaimRepresentation";
	}

	

	/**
	 * View Particular Claim Representation Form Details
	 * 
	 * @param claimNo
	 */
	@PostMapping("viewClaimRepresentationForm")
	public String viewClaimRepresentationDetails(@RequestParam Long claimNo, @RequestParam Long crId, Model model) {

		ClaimRepresentationBean claimRBean = policyClaimService.getClaimRepresentationBeanById(claimNo, crId);

		// tab 3 models
		model.addAttribute("claimChecklist", policyClaimService.getClaimDocumentCheckList());

		model.addAttribute("claimRepresentationBean", claimRBean);

		return "claimRepresentation/viewClaimRepresentation";
	}
	
	
	
	/**
	 * Edit Particular Claim Representation Form Details
	 * 
	 * @param claimNo
	 * @param crId
	 */
	@PostMapping("editClaimRepresentationForm")
	public String editClaimRepresentationDetails(@RequestParam Long claimNo, 
			@RequestParam Long crId, Model model,
			HttpSession session) {

		ClaimRepresentationBean claimRBean = policyClaimService.getClaimRepresentationBeanById(claimNo, crId);
		
		System.out.println("invoice processing bean :."+claimRBean.getClaimProcessingBean().getClaimInvoiceProcessingBean());

		// tab 3 models
		model.addAttribute("claimChecklist", policyClaimService.getClaimDocumentCheckList());

		model.addAttribute("claimRepresentationBean", claimRBean);
		
		model.addAttribute("claimProcessingBean", claimRBean.getClaimProcessingBean());
		
		model.addAttribute("ann1list", claimRBean.getClaimDetailsAnnexureBean());
		
		model.addAttribute("id", 1);
		
		//start editclaimrepresentation session here
		session.setAttribute("editPolicyClaimRepresentation", claimRBean);

		return "claimRepresentation/editClaimRepresentation";
	}

	/**
	 * View Particular Process Invoice Details
	 * 
	 * @param invoiceNo
	 * @param claimNo
	 * @param crId
	 */
	@GetMapping("viewProcessInvoice/{invoiceNo}/{claimNo}/{crId}")
	public @ResponseBody ClaimInvoiceProcessingBean findInvoicesRaisedButNotPaid(@PathVariable String invoiceNo,
			@PathVariable Long claimNo, @PathVariable Long crId) {
		
		System.out.println(invoiceNo +" "+claimNo+" "+crId);
		// get the Claim Details based on claim No
		ClaimRepresentationBean claimRBean = policyClaimService.getClaimRepresentationBeanById(claimNo, crId);

		ClaimProcessingBean claimProcessingBean = claimRBean.getClaimProcessingBean();

		List<ClaimInvoiceProcessingBean> invoices = claimProcessingBean.getClaimInvoiceProcessingBean();

		ClaimInvoiceProcessingBean invoice = invoices.stream().filter(i -> i.getInvoiceNo().equals(invoiceNo))
				.findFirst().get();

		return invoice;
	}
	
	//ajax call file upload in (add mode)
	@PostMapping("/uploadClaimRepresentScrutinyDocs")
	public @ResponseBody void receiveUploadedFiles(@RequestParam("file") MultipartFile file,
									@RequestParam("checklistId") String checklistId, 
									Model model,
									HttpSession session) {

		
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
	
	//edit claim Representation form 
	
	//1.basic claim representation details
	
	@PostMapping("updateBasicClaimRepresentationDetails")
	public String updateBasicClaimRepresentationDetails(
			@Validated(BasicClaimRepresentationInfo.class) 
			@ModelAttribute("claimRepresentationBean") 
			ClaimRepresentationBean claimRepresentationBean,
			BindingResult result, Model model, HttpSession session) {
		
		//getting claim bean using claim no and policy no
//		ClaimApplicationBean claimBean = policyClaimService.getClaimApplicationBeanById(
//				claimRepresentationBean.getClaimNo(), claimRepresentationBean.getPolicyNo());

		System.out.println();
		if (result.hasErrors()) {
			model.addAttribute("claimRepresentationBean", claimRepresentationBean);

			// pass id for current tabs
			model.addAttribute("id", 1);

			// tab 3 models scrutiny
			model.addAttribute("claimChecklist", policyClaimService.getClaimDocumentCheckList());

			// Assessment/Processing of Invoices Tab
			model.addAttribute("claimInvoiceProcessingBean", new ClaimInvoiceProcessingBean());
			model.addAttribute("claimPartPaymentScheduleBean", new ClaimPartPaymentScheduleMasterBean());

			// Claim Decision Tab
			model.addAttribute("claimProcessingBean", new ClaimProcessingBean());

			return "claimRepresentation/editClaimRepresentation";
		} else {
			
			//fetch cr bean from session
			ClaimRepresentationBean sessionClaimRBean = (ClaimRepresentationBean) session.getAttribute("editPolicyClaimRepresentation");

			// pass id for current tab
			model.addAttribute("id", 1);

			// Set updated values in session variable
			sessionClaimRBean.setInwardNo(claimRepresentationBean.getInwardNo());
			sessionClaimRBean.setInwardItemNo(claimRepresentationBean.getInwardItemNo());
			sessionClaimRBean.setPolicyNo(claimRepresentationBean.getPolicyNo());
			sessionClaimRBean.setClaimRepresentationDate(claimRepresentationBean.getClaimRepresentationDate());
			
			sessionClaimRBean
					.setReasonForClaimRepresentation(claimRepresentationBean.getReasonForClaimRepresentation());
			
			sessionClaimRBean.setRemarks(claimRepresentationBean.getRemarks());

			// and store it in session
			session.setAttribute("editPolicyClaimRepresentation", sessionClaimRBean);

			// tab 1 models
			model.addAttribute("claimRepresentationBean", sessionClaimRBean);

			// pass list of annexures in model
			model.addAttribute("ann1list", sessionClaimRBean.getClaimDetailsAnnexureBean());

			// tab 3 models
			model.addAttribute("claimChecklist", policyClaimService.getClaimDocumentCheckList());

			// Assessment/Processing of Invoices Tab
			// model.addAttribute("ListOfInvoicesRaisedButNotPaid",sessionClaimRBean.getClaimProcessingBean().getClaimInvoiceProcessingBean());
			model.addAttribute("claimInvoiceProcessingBean", new ClaimInvoiceProcessingBean());
			// model.addAttribute("claimPartPaymentScheduleBean", new
			// ClaimPartPaymentScheduleMasterBean());

			// Claim Decision Tab
			model.addAttribute("claimProcessingBean", sessionClaimRBean.getClaimProcessingBean());

			// Success Saved Message
			model.addAttribute("BasicClaimRepresentationDetailsFlag", "success");
			
		}
		return "claimRepresentation/editClaimRepresentation";

	}
	
	//2.view annexure details in modal
	/**
	 * show View Invoice Annexure 1 Modal with data in edit mode 
	 * 
	 * @param invoiceNo
	 */
	@GetMapping("editMode/viewAnnexure1/{invoiceNo}")
	public String showAnnexureClaimRepresentationDetails(@PathVariable("invoiceNo") String invoiceNo,Model model,
			HttpSession session) {
		
		//System.out.println("invoiceno : "+invoiceNo);

		// get cr object from session
		ClaimRepresentationBean claimRBean = (ClaimRepresentationBean) session
				.getAttribute("editPolicyClaimRepresentation");

		//get claim bean using policy no and claim no
		ClaimApplicationBean claimBean = policyClaimService.getClaimApplicationBeanById(claimRBean.getClaimNo(),
				claimRBean.getPolicyNo());

		// previous claim Representation List
		//model.addAttribute("previousClaimRepresentationList", claimBean.getClaimRepresentationBeanList());

		// find invoice no and add to model
		List<ClaimDetailsAnnexureBean> list1 = claimRBean.getClaimDetailsAnnexureBean();
		ClaimDetailsAnnexureBean annexure1 = list1.stream().filter(a1 -> a1.getInvoiceNo().equals(invoiceNo))
				.findFirst().get();

		// model for prepopulating data into modal
		model.addAttribute("annexure1", annexure1);

		// pass id in model
		model.addAttribute("id", 2);

		// all datatable list of annexures
		model.addAttribute("ann1list", claimRBean.getClaimDetailsAnnexureBean());

		// tab 1 models
		model.addAttribute("claimRepresentationBean", claimRBean);

		// tab 3 models
		model.addAttribute("claimChecklist", policyClaimService.getClaimDocumentCheckList());

		// Assessment/Processing of Invoices Tab
		// model.addAttribute("ListOfInvoicesRaisedButNotPaid",claimBean.getClaimProcessingBean().getClaimInvoiceProcessingBean());
		model.addAttribute("claimInvoiceProcessingBean", new ClaimInvoiceProcessingBean());
		model.addAttribute("claimPartPaymentScheduleBean", new ClaimPartPaymentScheduleMasterBean());

		// Claim Decision Tab
		if (claimRBean.getClaimProcessingBean() != null) {
			model.addAttribute("claimProcessingBean", claimRBean.getClaimProcessingBean());
		} else {
			model.addAttribute("claimProcessingBean", new ClaimProcessingBean());
		}

		// pass id to pop modal for update
		model.addAttribute("viewAnn1", "yes");

		return "claimRepresentation/editClaimRepresentation";
	}
	
	
	//3 update scrutiny details
	
	/**
	 * Update Scrutiny Details
	 * 
	 * @param claimBean
	 */
	@PostMapping("updateClaimScrutiny")
	public String updateClaimScrutinyDetails(
			@Validated(ClaimScrutinyInfo.class) @ModelAttribute("claimRepresentationBean") ClaimRepresentationBean claimRepresentationBean,
			BindingResult result, Model model, HttpSession session) {

		// fetch session object
		ClaimRepresentationBean claimRBean = (ClaimRepresentationBean) session
				.getAttribute("editPolicyClaimRepresentation");

		ClaimApplicationBean claimBean = policyClaimService.getClaimApplicationBeanById(claimRBean.getClaimNo(),
				claimRBean.getPolicyNo());

		// previous claim Representation List
		//model.addAttribute("previousClaimRepresentationList", claimBean.getClaimRepresentationBeanList());


		// update the session object
		//claimRBean.setClaimDocumentChecklistBean(claimRepresentationBean.getClaimDocumentChecklistBean());
		
		//take set to avoid duplicate document id
		Set<String> mergedList = new HashSet<>();
		mergedList.addAll(documentCheck);
		//check to be added - read only checkbox for those are selected in add mode
		mergedList.addAll(claimRBean.getClaimDocumentChecklistBean());
		
		claimRBean.setClaimDocumentChecklistBean(new ArrayList<>(mergedList));
		
		//update the session 
		session.setAttribute("editPolicyClaimRepresentation", claimRBean);

		// pass list in model for datatable
		model.addAttribute("ann1list", claimRBean.getClaimDetailsAnnexureBean());

		// pass id in model
		model.addAttribute("id", 3);

		// tab 1 models
		model.addAttribute("claimRepresentationBean", claimRBean);
		// tab 3 models
		model.addAttribute("claimChecklist", policyClaimService.getClaimDocumentCheckList());

		// Assessment/Processing of Invoices Tab
		model.addAttribute("ListOfInvoicesRaisedButNotPaid",
				claimRBean.getClaimProcessingBean().getClaimInvoiceProcessingBean());
		model.addAttribute("claimInvoiceProcessingBean", new ClaimInvoiceProcessingBean());
		model.addAttribute("claimPartPaymentScheduleBean", new ClaimPartPaymentScheduleMasterBean());

		// Claim Decision Tab
		model.addAttribute("claimProcessingBean", claimRBean.getClaimProcessingBean());

		// Success Saved Message
		model.addAttribute("ScrutinyUploadDocumentsFlag", "success");

		return "claimRepresentation/editClaimRepresentation";
	}
	
	
	//4 assessment details
	
	
	//5 update claim decision details
	/**
	 * Update Claim Decision Details
	 * 
	 * @param claimProcessingBean
	 */
	@PostMapping("/updateClaimRepresentationDecisionDetails")
	public String updateClaimRepresentationDecisionDetails(Model model,
			@Validated(ClaimDecisionInfo.class) @ModelAttribute("claimProcessingBean") 
			ClaimProcessingBean claimProcessingBean,
			BindingResult result,
			HttpSession session, 
			HttpServletRequest request) {

		// get session object
		ClaimRepresentationBean claimRBean = (ClaimRepresentationBean) session
				.getAttribute("editPolicyClaimRepresentation");

		//ClaimApplicationBean claimBean = policyClaimService.getClaimApplicationBeanById(claimRBean.getClaimNo(),
				//claimRBean.getPolicyNo());

		// previous claim Representation List
		//model.addAttribute("previousClaimRepresentationList", claimBean.getClaimRepresentationBeanList());

		if (result.hasErrors()) {

			model.addAttribute("claimRepresentationBean", claimRBean);

			// pass list of annexures in model
			model.addAttribute("ann1list", claimRBean.getClaimDetailsAnnexureBean());

			// tab 3 models
			model.addAttribute("claimChecklist", policyClaimService.getClaimDocumentCheckList());

			// Assessment/Processing of Invoices Tab
			model.addAttribute("ListOfInvoicesRaisedButNotPaid",
					claimRBean.getClaimProcessingBean().getClaimInvoiceProcessingBean());
			model.addAttribute("claimPartPaymentScheduleBean", new ClaimPartPaymentScheduleMasterBean());

			model.addAttribute("id", 5);
			return "claimRepresentation/editClaimRepresentation";
		}

		ClaimProcessingBean processingBean = new ClaimProcessingBean();
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
				.setClaimInvoiceProcessingBean(claimRBean.getClaimProcessingBean().getClaimInvoiceProcessingBean());

		claimRBean.setClaimProcessingBean(processingBean);

		//update the session
		session.setAttribute("editPolicyClaimRepresentation", claimRBean);

		model.addAttribute("claimRepresentationBean", claimRBean);

		// pass list of annexures in model
		model.addAttribute("ann1list", claimRBean.getClaimDetailsAnnexureBean());

		// tab 3 models
		model.addAttribute("claimChecklist", policyClaimService.getClaimDocumentCheckList());

		// Assessment/Processing of Invoices Tab
		model.addAttribute("ListOfInvoicesRaisedButNotPaid",
				claimRBean.getClaimProcessingBean().getClaimInvoiceProcessingBean());
		model.addAttribute("claimPartPaymentScheduleBean", new ClaimPartPaymentScheduleMasterBean());
		model.addAttribute("id", 5);

		// Success Saved Message
		model.addAttribute("ClaimRepresentationDecisionDetailsFlag", "success");
		return "claimRepresentation/editClaimRepresentation";
	}
	
	//final submit after update Claim Representation form
	/**
	 * Update Claim Representation Form Details in JSON Mock File After Final Submit
	 * 
	 * @session policyClaimBean
	 */
	@GetMapping("submitUpdatedClaimRepresentationForm")
	public String submitUpdateClaimRepresentationForm(Model model,
			@ModelAttribute("claimRepresentationBean") ClaimRepresentationBean claimRepresentationBean,
			HttpSession session, 
			RedirectAttributes redirectAttributes) {
		
		// get session Object
		ClaimRepresentationBean claimRBean = (ClaimRepresentationBean) session
				.getAttribute("editPolicyClaimRepresentation");

		// update the claim representation and claim bean details in mock json file
		int result= policyClaimService.updateClaimRepresentationDetails(claimRBean, claimRBean.getCrId());

		// show success message if saved in json file
		if (result == 1) {
			session.removeAttribute("editPolicyClaimRepresentation");
			redirectAttributes.addFlashAttribute("finalSubmitMsg",
					"Claim Representation Form Details For Cr Id :" + claimRBean.getCrId() + " Updated Successfully");
		}

		return "redirect:/claimRepresentation/applyClaimRepresentation";
	}
	
	//select the annexure to view in processing tab (in edit mode)
	@PostMapping("updateAnnexureDetails")
	public String updateAnnexureDetails(
			@ModelAttribute("claimRepresentationBean") ClaimRepresentationBean claimRepresentationBean,
			@RequestParam(value = "cers", required = false) int[] cers,
			Model model,
			HttpSession session) {
		
		

		// get session object
		ClaimRepresentationBean claimRBean = (ClaimRepresentationBean) session.getAttribute("editPolicyClaimRepresentation");
		
		ClaimApplicationBean claimBean = policyClaimService.getClaimApplicationBeanById(claimRBean.getClaimNo(),
				claimRBean.getPolicyNo());

		if(claimBean.getClaimRepresentationBeanList()!=null) {
			Iterator<ClaimRepresentationBean> cRepresentationBean = claimBean.getClaimRepresentationBeanList().iterator();
			ClaimRepresentationBean bean = new ClaimRepresentationBean();
			while (cRepresentationBean.hasNext()) {
				bean = cRepresentationBean.next();
				if (bean.getCrId() == 0) {
					claimBean.getClaimRepresentationBeanList().remove(0);
				}
	
			}
		}

		// previous claim Representation List
		model.addAttribute("previousClaimRepresentationList", claimBean.getClaimRepresentationBeanList());

		if (claimRepresentationBean.getClaimDetailsAnnexureBeanList().isEmpty()) { 
			// pass list in model for datatable
			model.addAttribute("ann1list", claimRBean.getClaimDetailsAnnexureBean());
			
			// pass id in model 
			model.addAttribute("id", 2);

			// and other tab models // tab 1 models
			model.addAttribute("claimRepresentationBean", claimRBean);

			// tab 3 models
			model.addAttribute("claimChecklist", policyClaimService.getClaimDocumentCheckList());

			// Assessment/Processing of Invoices Tab
			model.addAttribute("ListOfInvoicesRaisedButNotPaid", editInvoiceList);
			model.addAttribute("claimInvoiceProcessingBean", new ClaimInvoiceProcessingBean());
			model.addAttribute("claimPartPaymentScheduleBean", new ClaimPartPaymentScheduleMasterBean());

			// Claim Decision Tab
			model.addAttribute("claimProcessingBean", new ClaimProcessingBean());

			// Error Message
			model.addAttribute("NoInvoiceSelected", true);
			return "claimRepresentation/editClaimRepresentation";
		}

		if (editInvoiceList == null) {
			editInvoiceList = new ArrayList<ClaimInvoiceProcessingBean>();
			
			editInvoiceList.addAll(claimRBean.getClaimProcessingBean().getClaimInvoiceProcessingBean());
		}

		// find invoice no and add to model
		Iterator<ClaimDetailsAnnexureBean> iterator = claimRBean.getClaimDetailsAnnexureBean().iterator();
		ClaimDetailsAnnexureBean ann1Bean = new ClaimDetailsAnnexureBean();
		while (iterator.hasNext()) {
			ann1Bean = iterator.next();
			
			for (int i = 0; i < claimRepresentationBean.getClaimDetailsAnnexureBeanList().size(); i++ ) {
				if (ann1Bean.getInvoiceNo().equals(claimRepresentationBean.getClaimDetailsAnnexureBeanList().get(i))) {
					ClaimInvoiceProcessingBean claimInvoiceProcessing = new ClaimInvoiceProcessingBean();
					claimInvoiceProcessing.setInvoiceNo(ann1Bean.getInvoiceNo());
					
					if(claimRBean.getClaimProcessingBean()!=null) {
						if(claimRBean.getClaimProcessingBean().getClaimInvoiceProcessingBean()!=null) {
							ClaimInvoiceProcessingBean p = claimRBean.getClaimProcessingBean().getClaimInvoiceProcessingBean()
					                .stream()
					                .filter(producer -> producer.getInvoiceNo().toString().equals(claimInvoiceProcessing.getInvoiceNo().toString()))
					                .findFirst()
					                .orElse(null);
							if(p!=null) {
								if(p.getProcessInvoiceStatus()=="Processed" || p.getProcessInvoiceStatus()=="Pending") {
									//do nothing
								}
							}else {
								// set Annexure 1 data in Invoice Processing Bean
								claimInvoiceProcessing.setClaimNo(ann1Bean.getClaimNo());
								claimInvoiceProcessing.setCrId(claimRBean.getCrId());
								claimInvoiceProcessing.setDmsId(ann1Bean.getDmsId());
								claimInvoiceProcessing.setGrossInvoiceValueINR(ann1Bean.getGrossInvoiceValueINR());
								claimInvoiceProcessing.setInvoiceDate(ann1Bean.getInvoiceDate());
								claimInvoiceProcessing.setOriginalDueDate(ann1Bean.getOriginalDueDate());
								claimInvoiceProcessing.setExtendedDueDate(ann1Bean.getExtendedDueDate());
								claimInvoiceProcessing.setAmountDue(ann1Bean.getAmountDue());
								claimInvoiceProcessing.setProcessInvoiceStatus("Pending");
			
								// Add in InvoiceProcessingBean in list of Invoice Processing Bean
								editInvoiceList.add(claimInvoiceProcessing);
							}
						}
					}else {
						// set Annexure 1 data in Invoice Processing Bean
						claimInvoiceProcessing.setClaimNo(ann1Bean.getClaimNo());
						claimInvoiceProcessing.setCrId(claimRBean.getCrId());
						claimInvoiceProcessing.setDmsId(ann1Bean.getDmsId());
						claimInvoiceProcessing.setInvoiceNo(ann1Bean.getInvoiceNo());
						claimInvoiceProcessing.setGrossInvoiceValueINR(ann1Bean.getGrossInvoiceValueINR());
						claimInvoiceProcessing.setInvoiceDate(ann1Bean.getInvoiceDate());
						claimInvoiceProcessing.setOriginalDueDate(ann1Bean.getOriginalDueDate());
						claimInvoiceProcessing.setExtendedDueDate(ann1Bean.getExtendedDueDate());
						claimInvoiceProcessing.setAmountDue(ann1Bean.getAmountDue());
						claimInvoiceProcessing.setProcessInvoiceStatus("Pending");
	
						// Add in InvoiceProcessingBean in list of Invoice Processing Bean
						editInvoiceList.add(claimInvoiceProcessing);
					}
					
				}
			}
		}
		
		// Set List of Invoice Processing Bean to claimProcessing Object
		if (claimRBean.getClaimProcessingBean() == null) {
			ClaimProcessingBean claimProcessingBean = new ClaimProcessingBean();
			claimProcessingBean.setClaimInvoiceProcessingBean(editInvoiceList);
			claimRBean.setClaimProcessingBean(claimProcessingBean);
		} else {
			ClaimProcessingBean claimProcessingBean = claimRBean.getClaimProcessingBean();
			claimProcessingBean.setClaimInvoiceProcessingBean(editInvoiceList);
			claimRBean.setClaimProcessingBean(claimProcessingBean);
		}
		
		//System.out.println("size of ip : "+claimRBean.getClaimProcessingBean().getClaimInvoiceProcessingBean().size());
		//System.out.println(claimRepresentationBean.getClaimDetailsAnnexureBeanList().size());
		//remove the invoice from annexures 
		if(claimRBean.getClaimProcessingBean()!=null) {
			if(claimRBean.getClaimProcessingBean().getClaimInvoiceProcessingBean()!=null) {
				if(claimRBean.getClaimProcessingBean().getClaimInvoiceProcessingBean().size()>claimRepresentationBean.getClaimDetailsAnnexureBeanList().size()) {
					Iterator<ClaimInvoiceProcessingBean> iterator1 = claimRBean.getClaimProcessingBean().getClaimInvoiceProcessingBean().iterator();
					ClaimInvoiceProcessingBean invoiceProcessingBean = new ClaimInvoiceProcessingBean();
					while (iterator1.hasNext()) {
						invoiceProcessingBean = iterator1.next();
						int count=0;
						
						for (int i = 0; i < claimRepresentationBean.getClaimDetailsAnnexureBeanList().size(); i++ ) {
							System.out.println("second list" + claimRepresentationBean.getClaimDetailsAnnexureBeanList().size());
							if(invoiceProcessingBean.getInvoiceNo().equals(claimRepresentationBean.getClaimDetailsAnnexureBeanList().get(i))) {
								count++;
							}
							
						}
						if (count==0) {
							iterator1.remove();
							
						}
						
				}
			}
		}
		}
		
		// To sum the amount due of all invoice in Gross Loss Value
		BigDecimal sumGrossLossValue =editInvoiceList.stream().map(ClaimInvoiceProcessingBean::getAmountDue)
		.reduce(BigDecimal.ZERO, BigDecimal::add);

		// set Sum value to Gross Loss Value to session variable
		claimRBean.setGrossLossValueINR(sumGrossLossValue);
		claimRBean.setClaimDetailsAnnexureBeanList(claimRepresentationBean.getClaimDetailsAnnexureBeanList());
		// update session vaiable
		session.setAttribute("editPolicyClaimRepresentation", claimRBean);

		// pass list in model for datatable
		model.addAttribute("ann1list", claimRBean.getClaimDetailsAnnexureBean());

		// pass id in model
		model.addAttribute("id", 2);

		// and other tab models
		// tab 1 models
		model.addAttribute("claimRepresentationBean", claimRBean);

		// tab 3 models
		model.addAttribute("claimChecklist", policyClaimService.getClaimDocumentCheckList());

		// Assessment/Processing of Invoices Tab
		model.addAttribute("ListOfInvoicesRaisedButNotPaid", claimRBean.getClaimProcessingBean().getClaimInvoiceProcessingBean());
		model.addAttribute("claimInvoiceProcessingBean", new ClaimInvoiceProcessingBean());
		model.addAttribute("claimPartPaymentScheduleBean", new ClaimPartPaymentScheduleMasterBean());

		// Claim Decision Tab
		model.addAttribute("claimProcessingBean", claimRBean.getClaimProcessingBean());
		return "claimRepresentation/editClaimRepresentation";
	}
	
	
	//process invoice in (edit mode)
	
	/**
	 * Show Process Invoice Div For Invoice Processing
	 * 
	 * @param invoiceNo
	 */
	@PostMapping("/editMode/processInvoice")
	public String processInvoiceEditMode(Model model, @RequestParam("invoiceNo") String invoiceNo,
			@ModelAttribute("claimRepresentationBean") ClaimRepresentationBean claimRepresentationBean,
			HttpSession session) {


		ClaimRepresentationBean claimRBean = (ClaimRepresentationBean) session
				.getAttribute("editPolicyClaimRepresentation");

		ClaimApplicationBean claimBean = policyClaimService.getClaimApplicationBeanById(claimRBean.getClaimNo(),
				claimRBean.getPolicyNo());

		if(claimBean.getClaimRepresentationBeanList()!=null) {
			Iterator<ClaimRepresentationBean> cRepresentationBean = claimBean.getClaimRepresentationBeanList().iterator();
			ClaimRepresentationBean bean = new ClaimRepresentationBean();
			while (cRepresentationBean.hasNext()) {
				bean = cRepresentationBean.next();
				if (bean.getCrId() == 0) {
					claimBean.getClaimRepresentationBeanList().remove(0);
				}
	
			}
			
		}

		// previous claim Representation List
		model.addAttribute("previousClaimRepresentationList", claimBean.getClaimRepresentationBeanList());
		
		// Get Invoice Data based on Invoice No from List of Invoices
		List<ClaimInvoiceProcessingBean> claimInvoiceProcessingList = claimRBean.getClaimProcessingBean()
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
		session.setAttribute("editPolicyClaimRepresentation", claimRBean);
		model.addAttribute("claimRepresentationBean", claimRBean);

		// pass list of annexures in model
		model.addAttribute("ann1list", claimRBean.getClaimDetailsAnnexureBean());

		// tab 3 models
		model.addAttribute("claimChecklist", policyClaimService.getClaimDocumentCheckList());

		// Assessment/Processing of Invoices Tab
		model.addAttribute("ListOfInvoicesRaisedButNotPaid",
				claimRBean.getClaimProcessingBean().getClaimInvoiceProcessingBean());
		model.addAttribute("claimPartPaymentScheduleBean", new ClaimPartPaymentScheduleMasterBean());
		model.addAttribute("invoiceNo", invoiceNo);

		// Claim Decision Tab
		model.addAttribute("claimProcessingBean", claimRBean.getClaimProcessingBean());

		model.addAttribute("id", 4);

		// show Process Invoice Form
		model.addAttribute("showProcessInvoiceForm", "yes");
		return "claimRepresentation/editClaimRepresentation";

	}
	
	//update the process invoice details
	/**
	 * Save Invoice Processing Details
	 * 
	 * @param claimInvoiceProcessingBean
	 */
	@PostMapping("/updateProcessInvoiceDecision")
	public String updateInvoiceDecision(Model model,
			@Validated(ProcessInvoiceInfo.class) @ModelAttribute("claimInvoiceProcessingBean")
			ClaimInvoiceProcessingBean claimInvoiceProcessingBean,
			BindingResult result, 
			HttpSession session) {


		// get session varibale value
		ClaimRepresentationBean claimRBean = (ClaimRepresentationBean) session
				.getAttribute("editPolicyClaimRepresentation");

		ClaimApplicationBean claimBean = policyClaimService.getClaimApplicationBeanById(claimRBean.getClaimNo(),
				claimRBean.getPolicyNo());
		
		if(claimBean.getClaimRepresentationBeanList()!=null) {
			Iterator<ClaimRepresentationBean> cRepresentationBean = claimBean.getClaimRepresentationBeanList().iterator();
			ClaimRepresentationBean bean = new ClaimRepresentationBean();
			while (cRepresentationBean.hasNext()) {
				bean = cRepresentationBean.next();
				if (bean.getCrId() == 0) {
					claimBean.getClaimRepresentationBeanList().remove(0);
				}
	
			}
			
			// previous claim Representation List
			model.addAttribute("previousClaimRepresentationList", claimBean.getClaimRepresentationBeanList());
		}

		if (result.hasErrors()) {
			model.addAttribute("claimRepresentationBean", claimRBean);

			// tab 3 models
			model.addAttribute("claimChecklist", policyClaimService.getClaimDocumentCheckList());

			// pass list of annexures in model
			model.addAttribute("ann1list", claimRBean.getClaimDetailsAnnexureBean());

			// Assessment/Processing of Invoices Tab
			model.addAttribute("ListOfInvoicesRaisedButNotPaid",
					claimRBean.getClaimProcessingBean().getClaimInvoiceProcessingBean());
			model.addAttribute("claimPartPaymentScheduleBean", new ClaimPartPaymentScheduleMasterBean());
			model.addAttribute("invoiceNo", claimInvoiceProcessingBean.getInvoiceNo());

			// Claim Decision Tab
			model.addAttribute("claimProcessingBean", claimRBean.getClaimProcessingBean());

			model.addAttribute("id", 4);
			model.addAttribute("showProcessInvoiceForm", "yes");
			return "claimRepresentation/editClaimRepresentation";
		}

		if (claimInvoiceProcessingBean.getAmountApproved().compareTo(claimInvoiceProcessingBean.getAmountDue()) == 1) {
			model.addAttribute("claimRepresentationBean", claimRBean);

			// pass list of annexures in model
			model.addAttribute("ann1list", claimRBean.getClaimDetailsAnnexureBean());

			// tab 3 models
			model.addAttribute("claimChecklist", policyClaimService.getClaimDocumentCheckList());

			// Assessment/Processing of Invoices Tab
			model.addAttribute("ListOfInvoicesRaisedButNotPaid",
					claimRBean.getClaimProcessingBean().getClaimInvoiceProcessingBean());
			model.addAttribute("claimPartPaymentScheduleBean", new ClaimPartPaymentScheduleMasterBean());

			// Claim Decision Tab
			model.addAttribute("claimProcessingBean", claimRBean.getClaimProcessingBean());

			model.addAttribute("id", 4);

			model.addAttribute("invoiceNo", claimInvoiceProcessingBean.getInvoiceNo());

			// to keep open the process invoice div
			model.addAttribute("showProcessInvoiceForm", "yes");

			// Error Message
			model.addAttribute("AmountExceedsError", true);

			return "claimRepresentation/editClaimRepresentation";
		}

		// set ProcessInvoice status value
		claimInvoiceProcessingBean.setProcessInvoiceStatus("Processed");

		// set update invoice Processing bean with remaining values
		int counter = 0;
		for (ClaimInvoiceProcessingBean c : claimRBean.getClaimProcessingBean().getClaimInvoiceProcessingBean()) {
			if (c.getInvoiceNo().equals(claimInvoiceProcessingBean.getInvoiceNo())) {
				claimInvoiceProcessingBean.setCrId(claimRBean.getCrId());
				
				claimRBean.getClaimProcessingBean().getClaimInvoiceProcessingBean().set(counter,
						claimInvoiceProcessingBean);
			}
			counter++;
		}

		// update session varibale
		session.setAttribute("editPolicyClaimRepresentation", claimRBean);
		model.addAttribute("claimRepresentationBean", claimRBean);

		// pass list of annexures in model
		model.addAttribute("ann1list", claimRBean.getClaimDetailsAnnexureBean());

		// tab 3 models
		model.addAttribute("claimChecklist", policyClaimService.getClaimDocumentCheckList());

		// Assessment/Processing of Invoices Tab
		model.addAttribute("ListOfInvoicesRaisedButNotPaid",
				claimRBean.getClaimProcessingBean().getClaimInvoiceProcessingBean());
		model.addAttribute("claimPartPaymentScheduleBean", new ClaimPartPaymentScheduleMasterBean());

		// Claim Decision Tab
		model.addAttribute("claimProcessingBean", claimRBean.getClaimProcessingBean());

		model.addAttribute("id", 4);
		return "claimRepresentation/editClaimRepresentation";
	}


}
