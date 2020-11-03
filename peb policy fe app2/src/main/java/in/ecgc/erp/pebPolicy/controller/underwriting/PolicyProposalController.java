
package in.ecgc.erp.pebPolicy.controller.underwriting;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import in.ecgc.erp.pebPolicy.exceptions.ProposalAssessmentInfo;
import in.ecgc.erp.pebPolicy.exceptions.ProposalBasicInfo;
import in.ecgc.erp.pebPolicy.exceptions.ProposalDecisionInfo;
import in.ecgc.erp.pebPolicy.exceptions.ProposalMiscellaneousInfo;
import in.ecgc.erp.pebPolicy.exceptions.ProposalScrutinyInfo;
import in.ecgc.erp.pebPolicy.model.underwriting.DummyPolicyDetailsBean;
import in.ecgc.erp.pebPolicy.model.underwriting.PebPolicyProposalBean;
import in.ecgc.erp.pebPolicy.model.underwriting.PebPolicyProposalDocumentCheckListBean;
import in.ecgc.erp.pebPolicy.service.underwriting.PolicyUnderwritingService;


@Controller

public class PolicyProposalController {

	List<DummyPolicyDetailsBean> list;

	@Autowired
	private PolicyUnderwritingService policyUnderwritingService;

	/**
	 * Show In Principle Approval Proposal Form
	 */
	@GetMapping("getPolicyProposalForm")
	public String getPolicyProposalForm(Model model,
			@ModelAttribute("pebPolicyProposalBean") PebPolicyProposalBean pebPolicyProposalBean) {
		// tab 1
		Map<Integer, String> productTypes = policyUnderwritingService.getProductType();
		Map<Integer, String> modeOfPayment = policyUnderwritingService.getModeOfPayment();
		model.addAttribute("productTypes", productTypes);
		model.addAttribute("modeOfPayment", modeOfPayment);
		model.addAttribute("id", 1);

		// tab 2
		List<PebPolicyProposalDocumentCheckListBean> checkList = policyUnderwritingService.getDocumentCheckList();
		// pebPolicyProposalBean.setDocumentCheckList(checkList);
		model.addAttribute("documentChecklist", checkList);

		// tab 4 start
		Map<String, String> countryMap = policyUnderwritingService.getCountryList();
		Map<String, String> currencyCode = policyUnderwritingService.currencyCode();
		Map<String, String> countryRating = policyUnderwritingService.countryRating();
		Map<Integer, String> transactionType = policyUnderwritingService.transactionType();
		Map<Integer, String> riskType = policyUnderwritingService.riskType();

		model.addAttribute("countryMap", countryMap);
		model.addAttribute("currencyCode", currencyCode);
		model.addAttribute("countryRating", countryRating);
		model.addAttribute("transactionType", transactionType);
		model.addAttribute("riskType", riskType);
		model.addAttribute("typeOfWork", policyUnderwritingService.typeOfWork());
		model.addAttribute("fundingArrangement", policyUnderwritingService.fundingArrangement());
		list = new ArrayList<>();
		model.addAttribute("allDummyPolicy", list);
		model.addAttribute("proposal", new DummyPolicyDetailsBean());
		// tab 4 end

		return "underwriting/policyTab1";
		//return "under/test";
	}

	/**
	 * Save Basic Proposal Details Data
	 * 
	 * @param pebPolicyProposalBean
	 */
	@PostMapping("/saveProposalDetails")
	public String saveBasicProposalDetails(Model model,
			@Validated(ProposalBasicInfo.class) @ModelAttribute("pebPolicyProposalBean") PebPolicyProposalBean pebPolicyProposalBean,
			BindingResult result, HttpSession session, HttpServletRequest request) {
		System.out.println("in save of controller proposal Details::");

		PebPolicyProposalBean bean1 = (PebPolicyProposalBean) session.getAttribute("proposalBean");
		// System.out.println("session propo Id::" + bean1.getProposalId());
		
		// tab 1
		Map<Integer, String> productTypes = policyUnderwritingService.getProductType();
		Map<Integer, String> modeOfPayment = policyUnderwritingService.getModeOfPayment();
		
		// tab 2
		List<PebPolicyProposalDocumentCheckListBean> checkList = policyUnderwritingService
				.getDocumentCheckList();
		
		// tab 4
		Map<String, String> countryMap = policyUnderwritingService.getCountryList();
		Map<String, String> currencyCode = policyUnderwritingService.currencyCode();
		Map<String, String> countryRating = policyUnderwritingService.countryRating();
		Map<Integer, String> transactionType = policyUnderwritingService.transactionType();
		Map<Integer, String> riskType = policyUnderwritingService.riskType();
		
		if (result.hasErrors()) {
			System.out.println(" In haserrors of 1st ");
			model.addAttribute("pebPolicyProposalBean", pebPolicyProposalBean);
			//model.addAttribute("checkId", bean1.getProposalId());
			
			// tab 1
			model.addAttribute("productTypes", productTypes);
			model.addAttribute("modeOfPayment", modeOfPayment);
			model.addAttribute("id", 1);
			
			// tab 2
			model.addAttribute("documentChecklist", checkList);

			// tab 4
			model.addAttribute("countryMap", countryMap);
			model.addAttribute("currencyCode", currencyCode);
			model.addAttribute("countryRating", countryRating);
			model.addAttribute("transactionType", transactionType);
			model.addAttribute("riskType", riskType);
			model.addAttribute("typeOfWork", policyUnderwritingService.typeOfWork());
			model.addAttribute("fundingArrangement", policyUnderwritingService.fundingArrangement());
			list = new ArrayList<>();
			model.addAttribute("allDummyPolicy", list);
			model.addAttribute("proposal", new DummyPolicyDetailsBean());
			

			return "underwriting/policyTab1";
		} else {
			System.out.println("In else");
			System.out.println("Check session::" + session.getAttribute("proposalBean"));
			PebPolicyProposalBean pBean = (PebPolicyProposalBean) session.getAttribute("proposalBean");
			if (pBean == null) {
				System.out.println("1");
				PebPolicyProposalBean proposalBean = policyUnderwritingService
						.addBasicProposalDetails(pebPolicyProposalBean);

				if (proposalBean.getProposalId() == null) {
					System.out.println("in iffff");
					// model.addAttribute("message","Please enter data of Basic Proposal tab first
					// !");
				} else {
					model.addAttribute("id", 1);
					model.addAttribute("pebPolicyProposalBean", pebPolicyProposalBean);

					// tab 1
					model.addAttribute("productTypes", productTypes);
					model.addAttribute("modeOfPayment", modeOfPayment);
					session.setAttribute("proposalBean", proposalBean);
					model.addAttribute("basicProposalDetailsList", new ArrayList<PebPolicyProposalBean>(
							policyUnderwritingService.getProposalDetailsStore().values()));
					System.out.println("2.basicProposalDetailsList" + new ArrayList<PebPolicyProposalBean>(
							policyUnderwritingService.getProposalDetailsStore().values()));
					model.addAttribute("BasicProposalDetailsFlag", "success");

					// tab 2
					model.addAttribute("documentChecklist", checkList);

					// tab 4 
					
					model.addAttribute("countryMap", countryMap);
					model.addAttribute("currencyCode", currencyCode);
					model.addAttribute("countryRating", countryRating);
					model.addAttribute("transactionType", transactionType);
					model.addAttribute("riskType", riskType);
					model.addAttribute("typeOfWork", policyUnderwritingService.typeOfWork());
					model.addAttribute("fundingArrangement", policyUnderwritingService.fundingArrangement());
					list = new ArrayList<>();
					model.addAttribute("allDummyPolicy", list);
					//model.addAttribute("allDummyPolicy", bean1.getDummyPolicyDetailsBean());
					model.addAttribute("proposal", new DummyPolicyDetailsBean());
					
				}
			} else {
				System.out.println("2");

				// session.setAttribute("proposalBean",pebPolicyProposalBean);
				System.out.println("Check session::" + session.getAttribute("proposalBean"));
				if (session.getAttribute("proposalBean") == null) {
					System.out.println("Null session");
					model.addAttribute("message", "Please enter data of Basic Proposal tab first !");
				} else {

					// tab 1
					model.addAttribute("productTypes", productTypes);
					model.addAttribute("modeOfPayment", modeOfPayment);
					System.out.println("pebPolicyProposalBean::" + pebPolicyProposalBean);
					System.out.println("3:::" + session.getAttribute("proposalBean"));

					PebPolicyProposalBean pBean1 = (PebPolicyProposalBean) session.getAttribute("proposalBean");
					System.out.println("pbean1 Id::" + pBean1.getProposalId());
					pBean1.setProductId(pebPolicyProposalBean.getProductId());
					pBean1.setInwardNumber(pebPolicyProposalBean.getInwardNumber());
					pBean1.setInwardItemNumber(pebPolicyProposalBean.getInwardItemNumber());
					pBean1.setProjectId(pebPolicyProposalBean.getProjectId());
					pBean1.setProposalDate(pebPolicyProposalBean.getProposalDate());
					pBean1.setContractValue(pebPolicyProposalBean.getContractValue());
					pBean1.setModeOfPayment(pebPolicyProposalBean.getModeOfPayment());
					pBean1.setInstrumentNumber(pebPolicyProposalBean.getInstrumentNumber());
					pBean1.setInstrumentDate(pebPolicyProposalBean.getInstrumentDate());
					pBean1.setInstrumentAmount(pebPolicyProposalBean.getInstrumentAmount());
					pBean1.setProcessingFeePaidDate(pebPolicyProposalBean.getProcessingFeePaidDate());
					pBean1.setProcessingFeePaidAmount(pebPolicyProposalBean.getProcessingFeePaidAmount());
					pBean1.setProposalStatus(pebPolicyProposalBean.getProposalStatus());
					pBean1.setReceiptNumber(pebPolicyProposalBean.getReceiptNumber());
					pBean1.setBankName(pebPolicyProposalBean.getBankName());
					// pBean1.setNeiaSharePercentage(pebPolicyProposalBean.getNeiaSharePercentage());
					pBean1.setTags(pebPolicyProposalBean.getTags());
					pBean1.setRemarks(pebPolicyProposalBean.getRemarks());
					model.addAttribute("pebPolicyProposalBean", pBean1);
					model.addAttribute("checkId", pBean1.getProposalId());
					session.setAttribute("proposalBean", pBean1);

					model.addAttribute("id", 1);
					// model.addAttribute("id", 2);
					// model.addAttribute("id", 3);

					policyUnderwritingService.updateBasicProposalDetails(pBean1);
					System.out.println("2.basicProposalDetailsList" + new ArrayList<PebPolicyProposalBean>(
							policyUnderwritingService.getProposalDetailsStore().values()));
					
					// tab 2
					model.addAttribute("documentChecklist", checkList);
					
					// tab 4
					model.addAttribute("countryMap", countryMap);
					model.addAttribute("currencyCode", currencyCode);
					model.addAttribute("countryRating", countryRating);
					model.addAttribute("transactionType", transactionType);
					model.addAttribute("riskType", riskType);
					model.addAttribute("typeOfWork", policyUnderwritingService.typeOfWork());
					model.addAttribute("fundingArrangement", policyUnderwritingService.fundingArrangement());
					/*
					 * list = new ArrayList<>(); List<DummyPolicyDetailsBean> list1 =
					 * pBean1.getDummyPolicyDetailsBean();
					 * System.out.println(bean1.getDummyPolicyDetailsBean()); list1.add(dummy);
					 */
					list = new ArrayList<>();
					model.addAttribute("allDummyPolicy", pBean1.getDummyPolicyDetailsBean());
					//model.addAttribute("allDummyPolicy", list);
					//model.addAttribute("allDummyPolicy", bean1.getDummyPolicyDetailsBean());
					model.addAttribute("proposal", new DummyPolicyDetailsBean());
				}
			}
			return "underwriting/policyTab1";
		}
	}

	/**
	 * Save Scrutiny Verification Details
	 * 
	 * @param pebPolicyProposalBean
	 */
	@PostMapping("/saveScrutinyDetails")
	public String saveScrutinyDetails(Model model,
			@Validated(ProposalScrutinyInfo.class) @ModelAttribute("pebPolicyProposalBean") PebPolicyProposalBean pebPolicyProposalBean,
			BindingResult result, HttpSession session) {
		System.out.println("in save of controller Scrutiny Details::" + pebPolicyProposalBean);
		System.out.println("Check session::" + session.getAttribute("proposalBean"));
		// Extra 3 lines for flexi partial Save
		PebPolicyProposalBean bean1 = (PebPolicyProposalBean) session.getAttribute("proposalBean");
		// System.out.println("session propo Id::" + bean1.getProposalId());
		
		// tab 1
		Map<Integer, String> productTypes = policyUnderwritingService.getProductType();
		Map<Integer, String> modeOfPayment = policyUnderwritingService.getModeOfPayment();
		
		// tab 2
		List<PebPolicyProposalDocumentCheckListBean> checkList = policyUnderwritingService.getDocumentCheckList();
		
		// tab 4
		Map<String, String> countryMap = policyUnderwritingService.getCountryList();
		Map<String, String> currencyCode = policyUnderwritingService.currencyCode();
		Map<String, String> countryRating = policyUnderwritingService.countryRating();
		Map<Integer, String> transactionType = policyUnderwritingService.transactionType();
		Map<Integer, String> riskType = policyUnderwritingService.riskType();
		
		if (result.hasErrors()) {
			pebPolicyProposalBean.setProposalId(bean1.getProposalId());
			//pebPolicyProposalBean.setDocumentCheckList(bean1.getDocumentCheckList());
			//pebPolicyProposalBean.setIsExporterInSAL(bean1.getIsExporterInSAL());
			//pebPolicyProposalBean.setIsBuyerInBSAL(bean1.getIsBuyerInBSAL());
			pebPolicyProposalBean.setProductId(bean1.getProductId());
			pebPolicyProposalBean.setInwardNumber(bean1.getInwardNumber());
			pebPolicyProposalBean.setInwardItemNumber(bean1.getInwardItemNumber());
			pebPolicyProposalBean.setProjectId(bean1.getProjectId());
			pebPolicyProposalBean.setProposalDate(bean1.getProposalDate());
			pebPolicyProposalBean.setContractValue(bean1.getContractValue());
			pebPolicyProposalBean.setModeOfPayment(bean1.getModeOfPayment());
			pebPolicyProposalBean.setInstrumentNumber(bean1.getInstrumentNumber());
			pebPolicyProposalBean.setInstrumentDate(bean1.getInstrumentDate());
			pebPolicyProposalBean.setInstrumentAmount(bean1.getInstrumentAmount());
			pebPolicyProposalBean.setProcessingFeePaidDate(bean1.getProcessingFeePaidDate());
			pebPolicyProposalBean.setProcessingFeePaidAmount(bean1.getProcessingFeePaidAmount());
			pebPolicyProposalBean.setProposalStatus(bean1.getProposalStatus());
			pebPolicyProposalBean.setReceiptNumber(bean1.getReceiptNumber());
			pebPolicyProposalBean.setBankName(bean1.getBankName());
			// pebPolicyProposalBean.setNeiaSharePercentage(bean1.getNeiaSharePercentage());
			pebPolicyProposalBean.setRemarks(bean1.getRemarks());
			pebPolicyProposalBean.setTags(bean1.getTags());
			pebPolicyProposalBean.setDummyPolicyDetailsBean(bean1.getDummyPolicyDetailsBean());
			pebPolicyProposalBean.setTotalPremiumAmount(bean1.getTotalPremiumAmount());
			pebPolicyProposalBean.setDiscountScore(bean1.getDiscountScore());
			pebPolicyProposalBean.setDiscountPercentage(bean1.getDiscountPercentage());

			System.out.println(" In haserrors of 2nd ");
			// model.addAttribute("pebPolicyProposalBean",bean1);

			model.addAttribute("pebPolicyProposalBean", pebPolicyProposalBean); // used this b4 flexi partial Save
			model.addAttribute("checkId", bean1.getProposalId());
			session.setAttribute("proposalBean", pebPolicyProposalBean);
			
			// tab 1
			model.addAttribute("productTypes", productTypes);
			model.addAttribute("modeOfPayment", modeOfPayment);

			model.addAttribute("id", 2);

			// tab 2
			model.addAttribute("documentChecklist", checkList);
			
			// tab 4
			model.addAttribute("countryMap", countryMap);
			model.addAttribute("currencyCode", currencyCode);
			model.addAttribute("countryRating", countryRating);
			model.addAttribute("transactionType", transactionType);
			model.addAttribute("riskType", riskType);
			model.addAttribute("typeOfWork", policyUnderwritingService.typeOfWork());
			model.addAttribute("fundingArrangement", policyUnderwritingService.fundingArrangement());
			list = new ArrayList<>();
			model.addAttribute("allDummyPolicy", bean1.getDummyPolicyDetailsBean());
			model.addAttribute("proposal", new DummyPolicyDetailsBean());
			// tab 3 end

			return "underwriting/policyTab1";
		} else {
			System.out
					.println("in save of controller Scrutiny Details::" + pebPolicyProposalBean.getDocumentCheckList());
			System.out.println("Check session::" + session.getAttribute("proposalBean"));
			// PebPolicyProposalBean pBean = (PebPolicyProposalBean)
			// session.getAttribute("proposalBean");

			if (bean1 == null) {

				model.addAttribute("message", "Please enter data of Basic Proposal tab first !");
			} else {
				bean1.setIsExporterInSAL(pebPolicyProposalBean.getIsExporterInSAL());
				bean1.setIsBuyerInBSAL(pebPolicyProposalBean.getIsBuyerInBSAL());
				bean1.setDocumentCheckList(pebPolicyProposalBean.getDocumentCheckList());
				// model.addAttribute("pebPolicyProposalBean",pebPolicyProposalBean);
				model.addAttribute("pebPolicyProposalBean", bean1);

				session.setAttribute("proposalBean", bean1);
				// session.setAttribute("proposalBean", pBean);
				policyUnderwritingService.addScrutinyDetails(bean1);
				model.addAttribute("ScrutinyVerificationSuccessFlag", "success");
				
				model.addAttribute("id", 2);
				// tab 1
				model.addAttribute("productTypes", productTypes);
				model.addAttribute("modeOfPayment", modeOfPayment);

				// tab 2
				model.addAttribute("documentChecklist", checkList);
				
				
				// tab 4
				model.addAttribute("countryMap", countryMap);
				model.addAttribute("currencyCode", currencyCode);
				model.addAttribute("countryRating", countryRating);
				model.addAttribute("transactionType", transactionType);
				model.addAttribute("riskType", riskType);
				model.addAttribute("typeOfWork", policyUnderwritingService.typeOfWork());
				model.addAttribute("fundingArrangement", policyUnderwritingService.fundingArrangement());
				list = new ArrayList<>();
				System.out.println(bean1.getDummyPolicyDetailsBean());
				model.addAttribute("allDummyPolicy", bean1.getDummyPolicyDetailsBean());
				model.addAttribute("proposal", new DummyPolicyDetailsBean());
				// tab 3 end
			}
			return "underwriting/policyTab1";
		}
	}

	/**
	 * Save Exposure Norms Details
	 * 
	 * @param pebPolicyProposalBean
	 */
	@PostMapping("/saveExposurerNormsDetails")
	public String saveExposurerNormsDetails(
			@ModelAttribute("pebPolicyProposalBean") PebPolicyProposalBean pebPolicyProposalBean,Model model,HttpSession session) {
			PebPolicyProposalBean bean = (PebPolicyProposalBean) session.getAttribute("proposalBean");
		// tab 1
				Map<Integer, String> productTypes = policyUnderwritingService.getProductType();
				Map<Integer, String> modeOfPayment = policyUnderwritingService.getModeOfPayment();
				model.addAttribute("productTypes", productTypes);
				model.addAttribute("modeOfPayment", modeOfPayment);
				model.addAttribute("pebPolicyProposalBean", bean);
				model.addAttribute("id", 3);

				// tab 2
				List<PebPolicyProposalDocumentCheckListBean> checkList = policyUnderwritingService.getDocumentCheckList();
				// pebPolicyProposalBean.setDocumentCheckList(checkList);
				model.addAttribute("documentChecklist", checkList);

				// tab 4 start
				Map<String, String> countryMap = policyUnderwritingService.getCountryList();
				Map<String, String> currencyCode = policyUnderwritingService.currencyCode();
				Map<String, String> countryRating = policyUnderwritingService.countryRating();
				Map<Integer, String> transactionType = policyUnderwritingService.transactionType();
				Map<Integer, String> riskType = policyUnderwritingService.riskType();

				model.addAttribute("countryMap", countryMap);
				model.addAttribute("currencyCode", currencyCode);
				model.addAttribute("countryRating", countryRating);
				model.addAttribute("transactionType", transactionType);
				model.addAttribute("riskType", riskType);
				model.addAttribute("typeOfWork", policyUnderwritingService.typeOfWork());
				model.addAttribute("fundingArrangement", policyUnderwritingService.fundingArrangement());
				list = new ArrayList<>();
				model.addAttribute("allDummyPolicy", bean.getDummyPolicyDetailsBean());
				model.addAttribute("proposal", new DummyPolicyDetailsBean());
				return "underwriting/policyTab1";

	}

	/**
	 * Save Dummy Policy Details
	 * 
	 * @param dummy
	 */
	@PostMapping("/saveDummyPolicy")
	public String saveDummyPolicy(
			@Validated(ProposalAssessmentInfo.class) @ModelAttribute("proposal") DummyPolicyDetailsBean dummy,
			BindingResult result, Model model, HttpSession session) {

		System.out.println("in save of controller assess Details::" + dummy);
		System.out.println("Check session::" + session.getAttribute("proposalBean"));
		// Extra 3 lines for flexi partial Save
		PebPolicyProposalBean bean1 = (PebPolicyProposalBean) session.getAttribute("proposalBean");
		System.out.println("session propo Id::" + bean1.getProposalId());
		System.out.println("Bean1" + bean1);

		// tab 1
		Map<Integer, String> productTypes = policyUnderwritingService.getProductType();
		Map<Integer, String> modeOfPayment = policyUnderwritingService.getModeOfPayment();
		
		// tab 2
		List<PebPolicyProposalDocumentCheckListBean> checkList = policyUnderwritingService.getDocumentCheckList();
		
		// tab 4
		Map<String, String> countryMap = policyUnderwritingService.getCountryList();
		Map<String, String> currencyCode = policyUnderwritingService.currencyCode();
	//	Map<String, String> countryRating = policyUnderwritingService.countryRating();
		Map<Integer, String> transactionType = policyUnderwritingService.transactionType();
		Map<Integer, String> riskType = policyUnderwritingService.riskType();
		

		if (list == null) {
			System.out.println("if list empty");
			list = new ArrayList<>();
		}		if (result.hasErrors()) {
			System.out.println("3rd tab has erros::");
			System.out.println(result.getAllErrors().toString());

			// his
			model.addAttribute("pebPolicyProposalBean", session.getAttribute("proposalBean"));

			// tab 1
			model.addAttribute("productTypes", productTypes);
			model.addAttribute("modeOfPayment", modeOfPayment);
			
			// tab 2
			model.addAttribute("documentChecklist", checkList);
			
			// tab 4
			model.addAttribute("countryMap", countryMap);
			model.addAttribute("currencyCode", currencyCode);
			//model.addAttribute("countryRating", countryRating);
			model.addAttribute("transactionType", transactionType);
			model.addAttribute("riskType", riskType);
			model.addAttribute("typeOfWork", policyUnderwritingService.typeOfWork());
			model.addAttribute("fundingArrangement", policyUnderwritingService.fundingArrangement());
			model.addAttribute("allDummyPolicy", list);
			model.addAttribute("errors", "yes");
			model.addAttribute("id", 4);
			return "underwriting/policyTab1";
			
		}

		dummy.setDummyPolicyNumber((long) new Random().nextInt(1000));
		
		dummy.setProposalId(bean1.getProposalId());

		// save the data to list in mock data
		// policyUnderwritingService.insertAssessment(dummy);

		// save the data in arraylist
		if (bean1.getDummyPolicyDetailsBean() == null) {
			list.add(dummy);
			bean1.setDummyPolicyDetailsBean(list);
		} else {
			List<DummyPolicyDetailsBean> list1 = bean1.getDummyPolicyDetailsBean();
			System.out.println(bean1.getDummyPolicyDetailsBean());
			list1.add(dummy);
			bean1.setDummyPolicyDetailsBean(list1);
		}

		// get fresh list after save in mockdatabase
		// list = policyUnderwritingService.listOfDummyPolicy();

		
		session.setAttribute("proposalBean", bean1);
		
		// tab 1
		model.addAttribute("productTypes", productTypes);
		model.addAttribute("modeOfPayment", modeOfPayment);
		
		// tab 2
		model.addAttribute("documentChecklist", checkList);
		
		// tab 4
		model.addAttribute("countryMap", countryMap);
		model.addAttribute("currencyCode", currencyCode);
		//model.addAttribute("countryRating", countryRating);
		model.addAttribute("transactionType", transactionType);
		model.addAttribute("riskType", riskType);
		model.addAttribute("typeOfWork", policyUnderwritingService.typeOfWork());
		model.addAttribute("fundingArrangement", policyUnderwritingService.fundingArrangement());
		model.addAttribute("proposal", new DummyPolicyDetailsBean());
		System.out.println(bean1.getDummyPolicyDetailsBean());
		model.addAttribute("allDummyPolicy", bean1.getDummyPolicyDetailsBean());
		model.addAttribute("errors", "no");
		model.addAttribute("id", 4);
		model.addAttribute("pebPolicyProposalBean", session.getAttribute("proposalBean"));
		return "underwriting/policyTab1";
		
	}

	/**
	 * Show Edit DummyPolicy in Add In Principle Proposal form
	 * 
	 * @param dummyPolicyNo
	 */
	@GetMapping("/editDummyPolicy/{dummyPolicyNo}/{proposalId}")
	public String editDummyPolicy(@PathVariable("dummyPolicyNo") Long dummyPolicyNumber,
			@PathVariable("proposalId") Long proposalId, Model model, HttpSession session) {
		// get data from session variable
		PebPolicyProposalBean pBean = (PebPolicyProposalBean) session.getAttribute("proposalBean");
		System.out.println("in edit dummy pBean::"+pBean);
		
		List<DummyPolicyDetailsBean> dummyPolicyBeanList = pBean.getDummyPolicyDetailsBean();
		Iterator<DummyPolicyDetailsBean> iterator = dummyPolicyBeanList.iterator();
		DummyPolicyDetailsBean detailsBean = new DummyPolicyDetailsBean();
		while (iterator.hasNext()) {
			detailsBean = iterator.next();
			if (detailsBean.getDummyPolicyNumber().equals(dummyPolicyNumber)) {
				model.addAttribute("proposal", detailsBean);

			}
		}
		session.setAttribute("proposalBean", pBean);
		model.addAttribute("pebPolicyProposalBean", pBean);
		model.addAttribute("myval", dummyPolicyNumber);
		model.addAttribute("id", 4);
		System.out.println("in edit dummy detailsBean::"+detailsBean);
		System.out.println("in edit dummy detailsBean::"+detailsBean.getPaymentCountryRating());
		// tab 1
		Map<Integer, String> productTypes = policyUnderwritingService.getProductType();
		Map<Integer, String> modeOfPayment = policyUnderwritingService.getModeOfPayment();
		model.addAttribute("productTypes", productTypes);
		model.addAttribute("modeOfPayment", modeOfPayment);

		// tab 2
		List<PebPolicyProposalDocumentCheckListBean> checkList = policyUnderwritingService.getDocumentCheckList();
		model.addAttribute("documentChecklist", checkList);

		// tab 4
		Map<String, String> countryMap = policyUnderwritingService.getCountryList();
		Map<String, String> currencyCode = policyUnderwritingService.currencyCode();
		//Map<String, String> countryRating = policyUnderwritingService.countryRating();
		Map<Integer, String> transactionType = policyUnderwritingService.transactionType();
		Map<Integer, String> riskType = policyUnderwritingService.riskType();
		model.addAttribute("countryMap", countryMap);
		model.addAttribute("currencyCode", currencyCode);
	//	model.addAttribute("countryRating", countryRating);
		model.addAttribute("transactionType", transactionType);
		model.addAttribute("riskType", riskType);
		model.addAttribute("typeOfWork", policyUnderwritingService.typeOfWork());
		model.addAttribute("allDummyPolicy", pBean.getDummyPolicyDetailsBean());
		model.addAttribute("fundingArrangement", policyUnderwritingService.fundingArrangement());
		return "underwriting/policyTab1";
	}

	/**
	 * Update Dummy policy Details
	 * 
	 * @param dummy
	 */
	@PostMapping("/updateDummyPolicy")
	public String updateDummyPolicy(
			@Validated(ProposalAssessmentInfo.class) @ModelAttribute("proposal") DummyPolicyDetailsBean dummy,
			BindingResult result, Model model, HttpSession session) {
		// Get Session value
		PebPolicyProposalBean pBean = (PebPolicyProposalBean) session.getAttribute("proposalBean");

		// tab 1
		Map<Integer, String> productTypes = policyUnderwritingService.getProductType();
		Map<Integer, String> modeOfPayment = policyUnderwritingService.getModeOfPayment();
		model.addAttribute("productTypes", productTypes);
		model.addAttribute("modeOfPayment", modeOfPayment);

		// tab 2
		List<PebPolicyProposalDocumentCheckListBean> checkList = policyUnderwritingService.getDocumentCheckList();
		model.addAttribute("documentChecklist", checkList);

		// tab 4
		Map<String, String> countryMap = policyUnderwritingService.getCountryList();
		Map<String, String> currencyCode = policyUnderwritingService.currencyCode();
	//	Map<String, String> countryRating = policyUnderwritingService.countryRating();
		Map<Integer, String> transactionType = policyUnderwritingService.transactionType();
		Map<Integer, String> riskType = policyUnderwritingService.riskType();

		if (result.hasErrors()) {
			model.addAttribute("myval", dummy.getDummyPolicyNumber());
			model.addAttribute("pebPolicyProposalBean", session.getAttribute("proposalBean"));
			
			// tab 1
			model.addAttribute("productTypes", productTypes);
			model.addAttribute("modeOfPayment", modeOfPayment);
			
			// tab 2
			model.addAttribute("documentChecklist", checkList);
			
			// tab 4
			model.addAttribute("countryMap", countryMap);
			model.addAttribute("currencyCode", currencyCode);
		//	model.addAttribute("countryRating", countryRating);
			model.addAttribute("transactionType", transactionType);
			model.addAttribute("riskType", riskType);
			model.addAttribute("typeOfWork", policyUnderwritingService.typeOfWork());
			model.addAttribute("fundingArrangement", policyUnderwritingService.fundingArrangement());
			model.addAttribute("allDummyPolicy", pBean.getDummyPolicyDetailsBean());
			model.addAttribute("UpdatePageErrors", "yes");
			model.addAttribute("id", 4);
			return "underwriting/policyTab1";
		}
		// update in list
		int counter = 0;
		for (DummyPolicyDetailsBean d : pBean.getDummyPolicyDetailsBean()) {
			if (d.getProposalId().longValue() == dummy.getProposalId().longValue()) {
				if (d.getDummyPolicyNumber().longValue() == dummy.getDummyPolicyNumber().longValue()) {
					pBean.getDummyPolicyDetailsBean().set(counter, dummy);
				}
			}
			counter++;
		}

		session.setAttribute("proposalBean", pBean);
		model.addAttribute("allDummyPolicy", pBean.getDummyPolicyDetailsBean());
		
		// tab 4
		model.addAttribute("countryMap", countryMap);
		model.addAttribute("currencyCode", currencyCode);
	//	model.addAttribute("countryRating", countryRating);
		model.addAttribute("transactionType", transactionType);
		model.addAttribute("riskType", riskType);
		model.addAttribute("typeOfWork", policyUnderwritingService.typeOfWork());
		model.addAttribute("fundingArrangement", policyUnderwritingService.fundingArrangement());
		model.addAttribute("proposal", new DummyPolicyDetailsBean());
		model.addAttribute("id", 4);

		model.addAttribute("pebPolicyProposalBean", session.getAttribute("proposalBean"));

		return "underwriting/policyTab1";
	}

	/**
	 * Save Miscellaneous Details
	 * 
	 * @param pebPolicyProposalBean
	 */
	@PostMapping("/saveMiscDetails")
	public String saveMiscelleneousDetails(Model model,
			@Validated(ProposalMiscellaneousInfo.class) @ModelAttribute("pebPolicyProposalBean") PebPolicyProposalBean pebPolicyProposalBean,
			BindingResult result, HttpSession session) {

		System.out.println("in save of controller Misc Details::" + pebPolicyProposalBean);
		System.out.println("Check session::" + session.getAttribute("proposalBean"));
		// Extra 3 lines for flexi partial Save
		PebPolicyProposalBean bean1 = (PebPolicyProposalBean) session.getAttribute("proposalBean");
		System.out.println("session propo Id::" + bean1.getProposalId());
		
		// tab 1
		Map<Integer, String> productTypes = policyUnderwritingService.getProductType();
		Map<Integer, String> modeOfPayment = policyUnderwritingService.getModeOfPayment();
		
		// tab 2
		List<PebPolicyProposalDocumentCheckListBean> checkList = policyUnderwritingService
				.getDocumentCheckList();
		
		// tab 4
		Map<String, String> countryMap = policyUnderwritingService.getCountryList();
		Map<String, String> currencyCode = policyUnderwritingService.currencyCode();
		Map<String, String> countryRating = policyUnderwritingService.countryRating();
		Map<Integer, String> transactionType = policyUnderwritingService.transactionType();
		Map<Integer, String> riskType = policyUnderwritingService.riskType();
		if (result.hasErrors()) {
			System.out.println(" In haserrors of 4th ");
			pebPolicyProposalBean.setProposalId(bean1.getProposalId());
			pebPolicyProposalBean.setDocumentCheckList(bean1.getDocumentCheckList());
			pebPolicyProposalBean.setIsExporterInSAL(bean1.getIsExporterInSAL());
			pebPolicyProposalBean.setIsBuyerInBSAL(bean1.getIsBuyerInBSAL());
			pebPolicyProposalBean.setProductId(bean1.getProductId());
			pebPolicyProposalBean.setInwardNumber(bean1.getInwardNumber());
			pebPolicyProposalBean.setInwardItemNumber(bean1.getInwardItemNumber());
			pebPolicyProposalBean.setProjectId(bean1.getProjectId());
			pebPolicyProposalBean.setProposalDate(bean1.getProposalDate());
			pebPolicyProposalBean.setContractValue(bean1.getContractValue());
			pebPolicyProposalBean.setModeOfPayment(bean1.getModeOfPayment());
			pebPolicyProposalBean.setInstrumentNumber(bean1.getInstrumentNumber());
			pebPolicyProposalBean.setInstrumentDate(bean1.getInstrumentDate());
			pebPolicyProposalBean.setInstrumentAmount(bean1.getInstrumentAmount());
			pebPolicyProposalBean.setProcessingFeePaidDate(bean1.getProcessingFeePaidDate());
			pebPolicyProposalBean.setProcessingFeePaidAmount(bean1.getProcessingFeePaidAmount());
			pebPolicyProposalBean.setProposalStatus(bean1.getProposalStatus());
			pebPolicyProposalBean.setReceiptNumber(bean1.getReceiptNumber());
			pebPolicyProposalBean.setBankName(bean1.getBankName());
			// pebPolicyProposalBean.setNeiaSharePercentage(bean1.getNeiaSharePercentage());
			pebPolicyProposalBean.setRemarks(bean1.getRemarks());
			pebPolicyProposalBean.setTags(bean1.getTags());
			pebPolicyProposalBean.setIsExporterInSAL(bean1.getIsExporterInSAL());
			pebPolicyProposalBean.setIsBuyerInBSAL(bean1.getIsBuyerInBSAL());
			pebPolicyProposalBean.setDocumentCheckList(bean1.getDocumentCheckList());
			model.addAttribute("pebPolicyProposalBean", pebPolicyProposalBean);
			model.addAttribute("checkId", bean1.getProposalId());
			
			// tab 1
			model.addAttribute("productTypes", productTypes);
			model.addAttribute("modeOfPayment", modeOfPayment);
			model.addAttribute("id", 5);

			// tab 2
			model.addAttribute("documentChecklist", checkList);

			// tab 3 
			model.addAttribute("countryMap", countryMap);
			model.addAttribute("currencyCode", currencyCode);
			model.addAttribute("countryRating", countryRating);
			model.addAttribute("transactionType", transactionType);
			model.addAttribute("riskType", riskType);
			model.addAttribute("typeOfWork", policyUnderwritingService.typeOfWork());
			model.addAttribute("fundingArrangement", policyUnderwritingService.fundingArrangement());
			list = new ArrayList<>();
			model.addAttribute("allDummyPolicy", bean1.getDummyPolicyDetailsBean());
			model.addAttribute("proposal", new DummyPolicyDetailsBean());
			
			return "underwriting/policyTab1";
		} else {
			PebPolicyProposalBean pBean = (PebPolicyProposalBean) session.getAttribute("proposalBean");
			// System.out.println("proposalBean****"+pBean.getProposalId());
			if (pBean == null) {
				model.addAttribute("message", "Please enter data of Basic Proposal tab first!");
			} else {
				pBean.setTotalPremiumAmount(pebPolicyProposalBean.getTotalPremiumAmount());
				pBean.setDiscountScore(pebPolicyProposalBean.getDiscountScore());
				pBean.setDiscountPercentage(pebPolicyProposalBean.getDiscountPercentage());

				model.addAttribute("pebPolicyProposalBean", pBean);
				// add session.setattribute
				session.setAttribute("proposalBean", pBean);
				policyUnderwritingService.addMiscelleneousDetails(pBean);
				model.addAttribute("MiscelleneousSuccessflag", "success");
				
				// tab 1
				model.addAttribute("productTypes", productTypes);
				model.addAttribute("modeOfPayment", modeOfPayment);
				
				// tab 2
				model.addAttribute("documentChecklist", checkList);

				// tab 4
				model.addAttribute("countryMap", countryMap);
				model.addAttribute("currencyCode", currencyCode);
				model.addAttribute("countryRating", countryRating);
				model.addAttribute("transactionType", transactionType);
				model.addAttribute("riskType", riskType);
				model.addAttribute("typeOfWork", policyUnderwritingService.typeOfWork());
				model.addAttribute("fundingArrangement", policyUnderwritingService.fundingArrangement());
				list = new ArrayList<>();
				model.addAttribute("allDummyPolicy", pBean.getDummyPolicyDetailsBean());
				model.addAttribute("proposal", new DummyPolicyDetailsBean());
				model.addAttribute("id", 5);

			}
			return "underwriting/policyTab1";
		}
	}

	/**
	 * Save Decision Details
	 * 
	 * @param pebPolicyProposalBean
	 */
	@PostMapping("/saveDecisionDetails")
	public String saveDecisionDetails(Model model,
			@Validated(ProposalDecisionInfo.class) @ModelAttribute("pebPolicyProposalBean") PebPolicyProposalBean pebPolicyProposalBean,
			BindingResult result, HttpSession session) {

		System.out.println("in save of controller Decision Details::" + pebPolicyProposalBean);
		System.out.println("Check session::" + session.getAttribute("proposalBean"));
		// Extra 3 lines for flexi partial Save
		PebPolicyProposalBean bean1 = (PebPolicyProposalBean) session.getAttribute("proposalBean");
		System.out.println("session propo Id::" + bean1.getProposalId());
		
		
		// tab 1
		Map<Integer, String> productTypes = policyUnderwritingService.getProductType();
		Map<Integer, String> modeOfPayment = policyUnderwritingService.getModeOfPayment();
		
		// tab 2
		List<PebPolicyProposalDocumentCheckListBean> checkList = policyUnderwritingService.getDocumentCheckList();
		
		// tab 4
		Map<String, String> countryMap = policyUnderwritingService.getCountryList();
		Map<String, String> currencyCode = policyUnderwritingService.currencyCode();
		Map<String, String> countryRating = policyUnderwritingService.countryRating();
		Map<Integer, String> transactionType = policyUnderwritingService.transactionType();
		Map<Integer, String> riskType = policyUnderwritingService.riskType();
		
		if (result.hasErrors()) {
			System.out.println(" In haserrors of 5th ");
			pebPolicyProposalBean.setProposalId(bean1.getProposalId());
			pebPolicyProposalBean.setDocumentCheckList(bean1.getDocumentCheckList());
			pebPolicyProposalBean.setIsExporterInSAL(bean1.getIsExporterInSAL());
			pebPolicyProposalBean.setIsBuyerInBSAL(bean1.getIsBuyerInBSAL());
			pebPolicyProposalBean.setProductId(bean1.getProductId());
			pebPolicyProposalBean.setInwardNumber(bean1.getInwardNumber());
			pebPolicyProposalBean.setInwardItemNumber(bean1.getInwardItemNumber());
			pebPolicyProposalBean.setProjectId(bean1.getProjectId());
			pebPolicyProposalBean.setProposalDate(bean1.getProposalDate());
			pebPolicyProposalBean.setContractValue(bean1.getContractValue());
			pebPolicyProposalBean.setModeOfPayment(bean1.getModeOfPayment());
			pebPolicyProposalBean.setInstrumentNumber(bean1.getInstrumentNumber());
			pebPolicyProposalBean.setInstrumentDate(bean1.getInstrumentDate());
			pebPolicyProposalBean.setInstrumentAmount(bean1.getInstrumentAmount());
			pebPolicyProposalBean.setProcessingFeePaidDate(bean1.getProcessingFeePaidDate());
			pebPolicyProposalBean.setProcessingFeePaidAmount(bean1.getProcessingFeePaidAmount());
			pebPolicyProposalBean.setProposalStatus(bean1.getProposalStatus());
			pebPolicyProposalBean.setReceiptNumber(bean1.getReceiptNumber());
			pebPolicyProposalBean.setBankName(bean1.getBankName());
			// pebPolicyProposalBean.setNeiaSharePercentage(bean1.getNeiaSharePercentage());
			pebPolicyProposalBean.setRemarks(bean1.getRemarks());
			pebPolicyProposalBean.setTags(bean1.getTags());
			pebPolicyProposalBean.setIsExporterInSAL(bean1.getIsExporterInSAL());
			pebPolicyProposalBean.setIsBuyerInBSAL(bean1.getIsBuyerInBSAL());
			pebPolicyProposalBean.setDocumentCheckList(bean1.getDocumentCheckList());
			pebPolicyProposalBean.setTotalPremiumAmount(bean1.getTotalPremiumAmount());
			pebPolicyProposalBean.setDiscountScore(bean1.getDiscountScore());
			pebPolicyProposalBean.setDiscountPercentage(bean1.getDiscountPercentage());
			model.addAttribute("pebPolicyProposalBean", pebPolicyProposalBean);
			model.addAttribute("checkId", bean1.getProposalId());
			
			// tab 1
			model.addAttribute("productTypes", productTypes);
			model.addAttribute("modeOfPayment", modeOfPayment);
			model.addAttribute("id", 6);

			// tab 2
			model.addAttribute("documentChecklist", checkList);

			// tab 4 
			model.addAttribute("countryMap", countryMap);
			model.addAttribute("currencyCode", currencyCode);
			model.addAttribute("countryRating", countryRating);
			model.addAttribute("transactionType", transactionType);
			model.addAttribute("riskType", riskType);
			model.addAttribute("typeOfWork", policyUnderwritingService.typeOfWork());
			model.addAttribute("fundingArrangement", policyUnderwritingService.fundingArrangement());
			list = new ArrayList<>();
			model.addAttribute("allDummyPolicy", bean1.getDummyPolicyDetailsBean());
			model.addAttribute("proposal", new DummyPolicyDetailsBean());
			// tab 3 end

			return "underwriting/policyTab1";
		} else {
			PebPolicyProposalBean pBean = (PebPolicyProposalBean) session.getAttribute("proposalBean");
			// System.out.println("proposalBean****"+pBean.getProposalId());
			if (pBean == null) {
				model.addAttribute("message", "Please enter data of Basic Proposal tab first!");
			} else {

				pBean.setEcgcDecision(pebPolicyProposalBean.getEcgcDecision());
				pBean.setEcgcRemarks(pebPolicyProposalBean.getEcgcRemarks());
				pBean.setEcgcDecisionDate(pebPolicyProposalBean.getEcgcDecisionDate());
				pBean.setCodDecision(pebPolicyProposalBean.getCodDecision());
				pBean.setCodRemarks(pebPolicyProposalBean.getCodRemarks());
				pBean.setCodDecisionDate(pebPolicyProposalBean.getCodDecisionDate());
				pBean.setProcessingOfficerComments(pebPolicyProposalBean.getProcessingOfficerComments());
				pBean.setProcessingOfficerCommentedDate(pebPolicyProposalBean.getProcessingOfficerCommentedDate());
				pBean.setObserverComments(pebPolicyProposalBean.getObserverComments());
				pBean.setObserverCommentedDate(pebPolicyProposalBean.getObserverCommentedDate());
				pBean.setRecommenderComments(pebPolicyProposalBean.getRecommenderComments());
				pBean.setRecommenderCommentedDate(pebPolicyProposalBean.getRecommenderCommentedDate());
				pBean.setCodMeetingDate(pebPolicyProposalBean.getCodMeetingDate());
				model.addAttribute("pebPolicyProposalBean", pBean);
				// add session.setattribute
				session.setAttribute("proposalBean", pBean);
				policyUnderwritingService.addDecisionDetails(pBean);
				model.addAttribute("message", "Decision Details Saved Successfully!");
				
				// tab 1
				model.addAttribute("productTypes", productTypes);
				model.addAttribute("modeOfPayment", modeOfPayment);
				
				// tab 2
				model.addAttribute("documentChecklist", checkList);

				// tab 4
				model.addAttribute("countryMap", countryMap);
				model.addAttribute("currencyCode", currencyCode);
				model.addAttribute("countryRating", countryRating);
				model.addAttribute("transactionType", transactionType);
				model.addAttribute("riskType", riskType);
				model.addAttribute("typeOfWork", policyUnderwritingService.typeOfWork());
				model.addAttribute("fundingArrangement", policyUnderwritingService.fundingArrangement());
				list = new ArrayList<>();
				model.addAttribute("allDummyPolicy", pBean.getDummyPolicyDetailsBean());
				model.addAttribute("proposal", new DummyPolicyDetailsBean());
				Double contractValue = pBean.getContractValue();
				System.out.println("contractValue::"+contractValue);
				DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
				String firstNumberAsString = decimalFormat.format(contractValue);
				System.out.println(firstNumberAsString);
				
				model.addAttribute("contractValue",firstNumberAsString);
				model.addAttribute("basicProposalDetailsList", new ArrayList<PebPolicyProposalBean>(
						policyUnderwritingService.getProposalDetailsStore().values()));

			}
			return "underwriting/listProposal";
		}
	}

	/**
	 * Show Edit Proposal Page for particular Proposal id
	 * 
	 * @param proposalId
	 */
	@GetMapping("/editProposal/{proposalId}")
	public String editBasicProposalDetails(Model model, PebPolicyProposalBean pebPolicyProposalBean,
			@PathVariable("proposalId") Long id,HttpSession session) {

		System.out.println("Proposal Details: " + policyUnderwritingService.getProposalDetailsById(id));
		PebPolicyProposalBean pBean1 = (PebPolicyProposalBean) session.getAttribute("proposalBean");
		System.out.println("pBean1::"+pBean1);
		model.addAttribute("pebPolicyProposalBean", pBean1);
		/* changes by V on line 966,968 to resolve issue of proposal status DD empty on onload*/
		/*
		 * System.out.println("bean id: " + pebPolicyProposalBean.getProposalId());
		 * System.out.println("contract Value: "
		 * +pebPolicyProposalBean.getContractValue()); System.out.println("status: " +
		 * pebPolicyProposalBean.getProposalStatus());
		 */

		// tab 1
		Map<Integer, String> productTypes = policyUnderwritingService.getProductType();
		Map<Integer, String> modeOfPayment = policyUnderwritingService.getModeOfPayment();
		model.addAttribute("productTypes", productTypes);
		model.addAttribute("modeOfPayment", modeOfPayment);

		// tab 2 model
		List<PebPolicyProposalDocumentCheckListBean> checkList = policyUnderwritingService.getDocumentCheckList();
		model.addAttribute("documentChecklist", checkList);

		// tab 4 model
		Map<String, String> countryMap = policyUnderwritingService.getCountryList();
		Map<String, String> currencyCode = policyUnderwritingService.currencyCode();
		Map<String, String> countryRating = policyUnderwritingService.countryRating();
		Map<Integer, String> transactionType = policyUnderwritingService.transactionType();
		Map<Integer, String> riskType = policyUnderwritingService.riskType();

		model.addAttribute("allDummyPolicy",
				policyUnderwritingService.getProposalDetailsById(id).getDummyPolicyDetailsBean());
		model.addAttribute("countryMap", countryMap);
		model.addAttribute("currencyCode", currencyCode);
		model.addAttribute("countryRating", countryRating);
		model.addAttribute("transactionType", transactionType);
		model.addAttribute("riskType", riskType);
		model.addAttribute("typeOfWork", policyUnderwritingService.typeOfWork());
		model.addAttribute("proposal", new DummyPolicyDetailsBean());
		model.addAttribute("fundingArrangement", policyUnderwritingService.fundingArrangement());
		model.addAttribute("id", 1);

		return "underwriting/editProposal";
	}

	/**
	 * Update Basic Proposal Details
	 * 
	 * @param pebPolicyProposalBean
	 */
	@PostMapping("/updateBasicProposalDetails")
	public String updateBasicProposalDetails(Model model,
			@Validated(ProposalBasicInfo.class) @ModelAttribute("pebPolicyProposalBean") PebPolicyProposalBean pebPolicyProposalBean,
			BindingResult result, HttpSession session) {
		PebPolicyProposalBean bean1 = (PebPolicyProposalBean) session.getAttribute("proposalBean");
		System.out.println("session check::" + bean1);

		// tab 1
		Map<Integer, String> productTypes = policyUnderwritingService.getProductType();
		Map<Integer, String> modeOfPayment = policyUnderwritingService.getModeOfPayment();
		
		// tab 2
		List<PebPolicyProposalDocumentCheckListBean> checkList = policyUnderwritingService.getDocumentCheckList();
		
		// tab 4
		Map<String, String> countryMap = policyUnderwritingService.getCountryList();
		Map<String, String> currencyCode = policyUnderwritingService.currencyCode();
		Map<String, String> countryRating = policyUnderwritingService.countryRating();
		Map<Integer, String> transactionType = policyUnderwritingService.transactionType();
		Map<Integer, String> riskType = policyUnderwritingService.riskType();
		
		if (result.hasErrors()) {
			System.out.println("In else::" + pebPolicyProposalBean.getProposalId());
			System.out.println("Check session::" + session.getAttribute("proposalBean"));

			pebPolicyProposalBean.setProposalId(bean1.getProposalId());
			pebPolicyProposalBean.setDocumentCheckList(bean1.getDocumentCheckList());
			pebPolicyProposalBean.setIsExporterInSAL(bean1.getIsExporterInSAL());
			pebPolicyProposalBean.setIsBuyerInBSAL(bean1.getIsBuyerInBSAL());
			pebPolicyProposalBean.setProductId(bean1.getProductId());
			pebPolicyProposalBean.setInwardNumber(bean1.getInwardNumber());
			pebPolicyProposalBean.setInwardItemNumber(bean1.getInwardItemNumber());
			pebPolicyProposalBean.setProjectId(bean1.getProjectId());
			pebPolicyProposalBean.setProposalDate(bean1.getProposalDate());
			pebPolicyProposalBean.setContractValue(bean1.getContractValue());
			pebPolicyProposalBean.setModeOfPayment(bean1.getModeOfPayment());
			pebPolicyProposalBean.setInstrumentNumber(bean1.getInstrumentNumber());
			pebPolicyProposalBean.setInstrumentDate(bean1.getInstrumentDate());
			pebPolicyProposalBean.setInstrumentAmount(bean1.getInstrumentAmount());
			pebPolicyProposalBean.setProcessingFeePaidDate(bean1.getProcessingFeePaidDate());
			pebPolicyProposalBean.setProcessingFeePaidAmount(bean1.getProcessingFeePaidAmount());
			pebPolicyProposalBean.setProposalStatus(bean1.getProposalStatus());
			pebPolicyProposalBean.setReceiptNumber(bean1.getReceiptNumber());
			pebPolicyProposalBean.setBankName(bean1.getBankName());
			// pebPolicyProposalBean.setNeiaSharePercentage(bean1.getNeiaSharePercentage());
			pebPolicyProposalBean.setRemarks(bean1.getRemarks());
			pebPolicyProposalBean.setTags(bean1.getTags());
			pebPolicyProposalBean.setTotalPremiumAmount(bean1.getTotalPremiumAmount());
			pebPolicyProposalBean.setDiscountScore(bean1.getDiscountScore());
			pebPolicyProposalBean.setDiscountPercentage(bean1.getDiscountPercentage());
			pebPolicyProposalBean.setEcgcDecision(bean1.getEcgcDecision());
			pebPolicyProposalBean.setEcgcDecisionDate(bean1.getEcgcDecisionDate());
			pebPolicyProposalBean.setEcgcRemarks(bean1.getEcgcRemarks());
			pebPolicyProposalBean.setCodDecision(bean1.getCodDecision());
			pebPolicyProposalBean.setCodDecisionDate(bean1.getCodDecisionDate());
			pebPolicyProposalBean.setCodRemarks(bean1.getCodRemarks());
			pebPolicyProposalBean.setProcessingOfficerComments(bean1.getProcessingOfficerComments());
			pebPolicyProposalBean.setProcessingOfficerCommentedDate(bean1.getProcessingOfficerCommentedDate());
			pebPolicyProposalBean.setObserverComments(bean1.getObserverComments());
			pebPolicyProposalBean.setObserverCommentedDate(bean1.getObserverCommentedDate());
			pebPolicyProposalBean.setRecommenderComments(bean1.getRecommenderComments());
			pebPolicyProposalBean.setRecommenderCommentedDate(bean1.getRecommenderCommentedDate());
			pebPolicyProposalBean.setCodMeetingDate(bean1.getCodMeetingDate());
			model.addAttribute("pebPolicyProposalBean", pebPolicyProposalBean);
			
			// tab 1
			model.addAttribute("productTypes", productTypes);
			model.addAttribute("modeOfPayment", modeOfPayment);
			
			// tab 2
			model.addAttribute("documentChecklist", checkList);
			
			// tab 4
			model.addAttribute("countryMap", countryMap);
			model.addAttribute("currencyCode", currencyCode);
			model.addAttribute("countryRating", countryRating);
			model.addAttribute("transactionType", transactionType);
			model.addAttribute("riskType", riskType);
			model.addAttribute("typeOfWork", policyUnderwritingService.typeOfWork());
			model.addAttribute("fundingArrangement", policyUnderwritingService.fundingArrangement());
			list = new ArrayList<>();
			model.addAttribute("allDummyPolicy", bean1.getDummyPolicyDetailsBean());
			model.addAttribute("proposal", new DummyPolicyDetailsBean());
			return "underwriting/editProposal";

		} else {
			System.out.println("In else::" + pebPolicyProposalBean.getProposalId());
			System.out.println("Check session::" + session.getAttribute("proposalBean"));
			PebPolicyProposalBean pBean1 = (PebPolicyProposalBean) session.getAttribute("proposalBean");
			System.out.println("pbean1 Id::" + pBean1.getProposalId());
			pBean1.setProductId(pebPolicyProposalBean.getProductId());
			pBean1.setInwardNumber(pebPolicyProposalBean.getInwardNumber());
			pBean1.setInwardItemNumber(pebPolicyProposalBean.getInwardItemNumber());
			pBean1.setProjectId(pebPolicyProposalBean.getProjectId());
			pBean1.setProposalDate(pebPolicyProposalBean.getProposalDate());
			pBean1.setContractValue(pebPolicyProposalBean.getContractValue());
			pBean1.setModeOfPayment(pebPolicyProposalBean.getModeOfPayment());
			pBean1.setInstrumentNumber(pebPolicyProposalBean.getInstrumentNumber());
			pBean1.setInstrumentDate(pebPolicyProposalBean.getInstrumentDate());
			pBean1.setInstrumentAmount(pebPolicyProposalBean.getInstrumentAmount());
			pBean1.setProcessingFeePaidDate(pebPolicyProposalBean.getProcessingFeePaidDate());
			pBean1.setProcessingFeePaidAmount(pebPolicyProposalBean.getProcessingFeePaidAmount());
			pBean1.setProposalStatus(pebPolicyProposalBean.getProposalStatus());
			pBean1.setReceiptNumber(pebPolicyProposalBean.getReceiptNumber());
			pBean1.setBankName(pebPolicyProposalBean.getBankName());
			// pBean1.setNeiaSharePercentage(pebPolicyProposalBean.getNeiaSharePercentage());
			pBean1.setTags(pebPolicyProposalBean.getTags());
			pBean1.setRemarks(pebPolicyProposalBean.getRemarks());
			// pBean1.setDocumentCheckList(pebPolicyProposalBean.getDocumentCheckList());
			// pBean1.setIsExporterInSAL(pebPolicyProposalBean.getIsExporterInSAL());
			// pBean1.setIsBuyerInBSAL(pebPolicyProposalBean.getIsBuyerInBSAL());
			model.addAttribute("pebPolicyProposalBean", pBean1);
			model.addAttribute("checkId", pBean1.getProposalId());
			session.setAttribute("proposalBean", pBean1);

			model.addAttribute("id", 1);

			// tab 1
			model.addAttribute("productTypes", productTypes);
			model.addAttribute("modeOfPayment", modeOfPayment);
			
			// tab 2
			model.addAttribute("documentChecklist", checkList);
			
			// tab 4
			model.addAttribute("countryMap", countryMap);
			model.addAttribute("currencyCode", currencyCode);
			model.addAttribute("countryRating", countryRating);
			model.addAttribute("transactionType", transactionType);
			model.addAttribute("riskType", riskType);
			model.addAttribute("typeOfWork", policyUnderwritingService.typeOfWork());
			model.addAttribute("fundingArrangement", policyUnderwritingService.fundingArrangement());
			list = new ArrayList<>();
			model.addAttribute("allDummyPolicy", pBean1.getDummyPolicyDetailsBean());
			model.addAttribute("proposal", new DummyPolicyDetailsBean());
			policyUnderwritingService.updateBasicProposalDetails(pBean1);
			model.addAttribute("basicProposalDetailsList",
					new ArrayList<PebPolicyProposalBean>(policyUnderwritingService.getProposalDetailsStore().values()));
			System.out.println("basicProposalDetailsList" + new ArrayList<PebPolicyProposalBean>(
					policyUnderwritingService.getProposalDetailsStore().values()));
			model.addAttribute("UpdateBasicProposalDetailsFlag", "success");
			// return "listProposal";
			return "underwriting/editProposal";
		}
	}

	/**
	 * Update Scrutiny Verification Details
	 * 
	 * @param pebPolicyProposalBean
	 */
	@PostMapping("/updateScrutinyDetails")
	public String updateScrutinyDetails(Model model,
			@Validated(ProposalScrutinyInfo.class) @ModelAttribute("pebPolicyProposalBean") PebPolicyProposalBean pebPolicyProposalBean,
			BindingResult result, HttpSession session) {
		PebPolicyProposalBean bean1 = (PebPolicyProposalBean) session.getAttribute("proposalBean");

		System.out.println("session check::" + bean1);
		// tab 1
		Map<Integer, String> productTypes = policyUnderwritingService.getProductType();
		Map<Integer, String> modeOfPayment = policyUnderwritingService.getModeOfPayment();
		
		// tab 2
		List<PebPolicyProposalDocumentCheckListBean> checkList = policyUnderwritingService.getDocumentCheckList();

		// tab 4
		Map<String, String> countryMap = policyUnderwritingService.getCountryList();
		Map<String, String> currencyCode = policyUnderwritingService.currencyCode();
		//Map<String, String> countryRating = policyUnderwritingService.countryRating();
		Map<Integer, String> transactionType = policyUnderwritingService.transactionType();
		Map<Integer, String> riskType = policyUnderwritingService.riskType();

		if (result.hasErrors()) {
			System.out.println("In if of cont scru::" + pebPolicyProposalBean.getProposalId());
			System.out.println("Check session::" + session.getAttribute("proposalBean"));
			System.out.println("bean1.getProductId()::" + bean1.getProductId());
			pebPolicyProposalBean.setProposalId(bean1.getProposalId());
			pebPolicyProposalBean.setProductId(bean1.getProductId());
			pebPolicyProposalBean.setInwardNumber(bean1.getInwardNumber());
			pebPolicyProposalBean.setInwardItemNumber(bean1.getInwardItemNumber());
			pebPolicyProposalBean.setProjectId(bean1.getProjectId());
			pebPolicyProposalBean.setProposalDate(bean1.getProposalDate());
			pebPolicyProposalBean.setContractValue(bean1.getContractValue());
			pebPolicyProposalBean.setModeOfPayment(bean1.getModeOfPayment());
			pebPolicyProposalBean.setInstrumentNumber(bean1.getInstrumentNumber());
			pebPolicyProposalBean.setInstrumentDate(bean1.getInstrumentDate());
			pebPolicyProposalBean.setInstrumentAmount(bean1.getInstrumentAmount());
			pebPolicyProposalBean.setProcessingFeePaidDate(bean1.getProcessingFeePaidDate());
			pebPolicyProposalBean.setProcessingFeePaidAmount(bean1.getProcessingFeePaidAmount());
			pebPolicyProposalBean.setProposalStatus(bean1.getProposalStatus());
			pebPolicyProposalBean.setReceiptNumber(bean1.getReceiptNumber());
			pebPolicyProposalBean.setBankName(bean1.getBankName());
			pebPolicyProposalBean.setRemarks(bean1.getRemarks());
			pebPolicyProposalBean.setTags(bean1.getTags());
			pebPolicyProposalBean.setTotalPremiumAmount(bean1.getTotalPremiumAmount());
			pebPolicyProposalBean.setDiscountScore(bean1.getDiscountScore());
			pebPolicyProposalBean.setDiscountPercentage(bean1.getDiscountPercentage());
			pebPolicyProposalBean.setEcgcDecision(bean1.getEcgcDecision());
			pebPolicyProposalBean.setEcgcDecisionDate(bean1.getEcgcDecisionDate());
			pebPolicyProposalBean.setEcgcRemarks(bean1.getEcgcRemarks());
			pebPolicyProposalBean.setCodDecision(bean1.getCodDecision());
			pebPolicyProposalBean.setCodDecisionDate(bean1.getCodDecisionDate());
			pebPolicyProposalBean.setCodRemarks(bean1.getCodRemarks());
			pebPolicyProposalBean.setProcessingOfficerComments(bean1.getProcessingOfficerComments());
			pebPolicyProposalBean.setProcessingOfficerCommentedDate(bean1.getProcessingOfficerCommentedDate());
			pebPolicyProposalBean.setObserverComments(bean1.getObserverComments());
			pebPolicyProposalBean.setObserverCommentedDate(bean1.getObserverCommentedDate());
			pebPolicyProposalBean.setRecommenderComments(bean1.getRecommenderComments());
			pebPolicyProposalBean.setRecommenderCommentedDate(bean1.getRecommenderCommentedDate());
			pebPolicyProposalBean.setCodMeetingDate(bean1.getCodMeetingDate());
			model.addAttribute("pebPolicyProposalBean", pebPolicyProposalBean);
			
			
			// tab 1
			model.addAttribute("productTypes", productTypes);
			model.addAttribute("modeOfPayment", modeOfPayment);
			
			// tab 2
			model.addAttribute("documentChecklist", checkList);
			
			// tab 4
			model.addAttribute("countryMap", countryMap);
			model.addAttribute("currencyCode", currencyCode);
		//	model.addAttribute("countryRating", countryRating);
			model.addAttribute("transactionType", transactionType);
			model.addAttribute("riskType", riskType);
			model.addAttribute("typeOfWork", policyUnderwritingService.typeOfWork());
			model.addAttribute("fundingArrangement", policyUnderwritingService.fundingArrangement());
			list = new ArrayList<>();
			model.addAttribute("allDummyPolicy", bean1.getDummyPolicyDetailsBean());
			model.addAttribute("proposal", new DummyPolicyDetailsBean());
			model.addAttribute("id", 2);
			return "underwriting/editProposal";

		} else {
			System.out.println("In else of cont scru::" + pebPolicyProposalBean.getProposalId());
			System.out.println("Check session::" + session.getAttribute("proposalBean"));
			System.out.println("In else::" + pebPolicyProposalBean.getProposalId());
			System.out.println("Check session::" + session.getAttribute("proposalBean"));
			PebPolicyProposalBean pBean1 = (PebPolicyProposalBean) session.getAttribute("proposalBean");
			System.out.println("pbean1 Id::" + pBean1.getProposalId());
			/*
			 * pBean1.setProductId(pebPolicyProposalBean.getProductId());
			 * pBean1.setInstrumentNumber(pebPolicyProposalBean.getInwardNumber());
			 * pBean1.setInwardItemNumber(pebPolicyProposalBean.getInwardItemNumber());
			 * pBean1.setProjectId(pebPolicyProposalBean.getProjectId());
			 * pBean1.setProposalDate(pebPolicyProposalBean.getProposalDate());
			 * pBean1.setContractValue(pebPolicyProposalBean.getContractValue());
			 * pBean1.setModeOfPayment(pebPolicyProposalBean.getModeOfPayment());
			 * pBean1.setInstrumentNumber(pebPolicyProposalBean.getInstrumentNumber());
			 * pBean1.setInstrumentDate(pebPolicyProposalBean.getInstrumentDate());
			 * pBean1.setInstrumentAmount(pebPolicyProposalBean.getInstrumentAmount());
			 * pBean1.setProcessingFeePaidDate(pebPolicyProposalBean.
			 * getProcessingFeePaidDate());
			 * pBean1.setProcessingFeePaidAmount(pebPolicyProposalBean.
			 * getProcessingFeePaidAmount());
			 * pBean1.setProposalStatus(pebPolicyProposalBean.getProposalStatus());
			 * pBean1.setReceiptNumber(pebPolicyProposalBean.getReceiptNumber());
			 * pBean1.setBankName(pebPolicyProposalBean.getBankName());
			 * pBean1.setNeiaSharePercentage(pebPolicyProposalBean.getNeiaSharePercentage())
			 * ; pBean1.setTags(pebPolicyProposalBean.getTags());
			 * pBean1.setRemarks(pebPolicyProposalBean.getRemarks());
			 */
			pBean1.setDocumentCheckList(pebPolicyProposalBean.getDocumentCheckList());
			pBean1.setIsExporterInSAL(pebPolicyProposalBean.getIsExporterInSAL());
			pBean1.setIsBuyerInBSAL(pebPolicyProposalBean.getIsBuyerInBSAL());
			model.addAttribute("pebPolicyProposalBean", pBean1);
			model.addAttribute("checkId", pBean1.getProposalId());
			session.setAttribute("proposalBean", pBean1);

			model.addAttribute("id", 2);
			// tab 1
			model.addAttribute("productTypes", productTypes);
			model.addAttribute("modeOfPayment", modeOfPayment);
			
			// tab 2
			model.addAttribute("documentChecklist", checkList);
			
			// tab 4
			model.addAttribute("countryMap", countryMap);
			model.addAttribute("currencyCode", currencyCode);
		//	model.addAttribute("countryRating", countryRating);
			model.addAttribute("transactionType", transactionType);
			model.addAttribute("riskType", riskType);
			model.addAttribute("typeOfWork", policyUnderwritingService.typeOfWork());
			model.addAttribute("fundingArrangement", policyUnderwritingService.fundingArrangement());
			list = new ArrayList<>();
			model.addAttribute("allDummyPolicy", pBean1.getDummyPolicyDetailsBean());
			model.addAttribute("proposal", new DummyPolicyDetailsBean());
			policyUnderwritingService.updateScrutinyDetails(pBean1);
			model.addAttribute("basicProposalDetailsList",
					new ArrayList<PebPolicyProposalBean>(policyUnderwritingService.getProposalDetailsStore().values()));
			System.out.println("basicProposalDetailsList" + new ArrayList<PebPolicyProposalBean>(
					policyUnderwritingService.getProposalDetailsStore().values()));
			model.addAttribute("UpdateScrutinyVerficationFlag", "success");
			return "underwriting/editProposal";
		}

	}

	/**
	 * Update Exposure Norms Details
	 * 
	 * @param pebPolicyProposalBean
	 */
	@PostMapping("/updateExposurerNormsDetails")
	public String updateExposurerNormsDetails(
			@ModelAttribute("pebPolicyProposalBean") PebPolicyProposalBean pebPolicyProposalBean,Model model,HttpSession session) {
		PebPolicyProposalBean bean = (PebPolicyProposalBean) session.getAttribute("proposalBean");
		// tab 1
				Map<Integer, String> productTypes = policyUnderwritingService.getProductType();
				Map<Integer, String> modeOfPayment = policyUnderwritingService.getModeOfPayment();
				model.addAttribute("productTypes", productTypes);
				model.addAttribute("modeOfPayment", modeOfPayment);
				model.addAttribute("pebPolicyProposalBean", bean);
				model.addAttribute("id", 3);

				// tab 2
				List<PebPolicyProposalDocumentCheckListBean> checkList = policyUnderwritingService.getDocumentCheckList();
				// pebPolicyProposalBean.setDocumentCheckList(checkList);
				model.addAttribute("documentChecklist", checkList);

				// tab 4 start
				Map<String, String> countryMap = policyUnderwritingService.getCountryList();
				Map<String, String> currencyCode = policyUnderwritingService.currencyCode();
				Map<String, String> countryRating = policyUnderwritingService.countryRating();
				Map<Integer, String> transactionType = policyUnderwritingService.transactionType();
				Map<Integer, String> riskType = policyUnderwritingService.riskType();

				model.addAttribute("countryMap", countryMap);
				model.addAttribute("currencyCode", currencyCode);
				model.addAttribute("countryRating", countryRating);
				model.addAttribute("transactionType", transactionType);
				model.addAttribute("riskType", riskType);
				model.addAttribute("typeOfWork", policyUnderwritingService.typeOfWork());
				model.addAttribute("fundingArrangement", policyUnderwritingService.fundingArrangement());
				list = new ArrayList<>();
				model.addAttribute("allDummyPolicy", bean.getDummyPolicyDetailsBean());
				model.addAttribute("proposal", new DummyPolicyDetailsBean());
		return "underwriting/editProposal";

	}
	
	/**
	 * Save New Dummy Policy Details at the time of Edit Proposal
	 * 
	 * @param dummy
	 */
	@PostMapping("/saveNewDummyPolicy")
	public String saveNewDummyPolicy(
			@Validated(ProposalAssessmentInfo.class) @ModelAttribute("proposal") DummyPolicyDetailsBean dummy,
			BindingResult result, Model model, HttpSession session) {

		System.out.println("in save of controller assess Details::" + dummy);
		System.out.println("Check session::" + session.getAttribute("proposalBean"));
		// Extra 3 lines for flexi partial Save
		PebPolicyProposalBean bean1 = (PebPolicyProposalBean) session.getAttribute("proposalBean");
		System.out.println("session propo Id::" + bean1.getProposalId());
		System.out.println("Bean1" + bean1);
		// tab 1
		Map<Integer, String> productTypes = policyUnderwritingService.getProductType();
		Map<Integer, String> modeOfPayment = policyUnderwritingService.getModeOfPayment();
		
		// tab 2
		List<PebPolicyProposalDocumentCheckListBean> checkList = policyUnderwritingService.getDocumentCheckList();

		// tab 4
		Map<String, String> countryMap = policyUnderwritingService.getCountryList();
		Map<String, String> currencyCode = policyUnderwritingService.currencyCode();
		Map<String, String> countryRating = policyUnderwritingService.countryRating();
		Map<Integer, String> transactionType = policyUnderwritingService.transactionType();
		Map<Integer, String> riskType = policyUnderwritingService.riskType();

		if (list == null) {
			System.out.println("if list empty");
			list = new ArrayList<>();
		}
		if (result.hasErrors()) {
			System.out.println("3rd tab has erros::");

			// his
			model.addAttribute("pebPolicyProposalBean", session.getAttribute("proposalBean"));

			// tab 1
			model.addAttribute("productTypes", productTypes);
			model.addAttribute("modeOfPayment", modeOfPayment);
			
			// tab 2
			model.addAttribute("documentChecklist", checkList);
			
			// tab 4
			model.addAttribute("countryMap", countryMap);
			model.addAttribute("currencyCode", currencyCode);
			model.addAttribute("countryRating", countryRating);
			model.addAttribute("transactionType", transactionType);
			model.addAttribute("riskType", riskType);
			model.addAttribute("typeOfWork", policyUnderwritingService.typeOfWork());
			model.addAttribute("fundingArrangement", policyUnderwritingService.fundingArrangement());
			model.addAttribute("allDummyPolicy", list);
			model.addAttribute("errors", "yes");
			model.addAttribute("id", 4);
			return "underwriting/editProposal";
		}

		dummy.setDummyPolicyNumber((long) new Random().nextInt(1000));
		// PebPolicyProposalBean pBean = (PebPolicyProposalBean)
		// session.getAttribute("proposalBean");
		dummy.setProposalId(bean1.getProposalId());

		// save the data to list in mock data
		// policyUnderwritingService.insertAssessment(dummy);

		// save the data in arraylist

		System.out.println("List" + list);
		if (bean1.getDummyPolicyDetailsBean() == null) {
			list.add(dummy);
			bean1.setDummyPolicyDetailsBean(list);
		} else {
			List<DummyPolicyDetailsBean> list1 = bean1.getDummyPolicyDetailsBean();
			System.out.println(bean1.getDummyPolicyDetailsBean());
			list1.add(dummy);
			bean1.setDummyPolicyDetailsBean(list1);
		}

		// get fresh list after save in mockdatabase
		// list = policyUnderwritingService.listOfDummyPolicy();

		// System.out.println("In Assessment list"+bean1);
		session.setAttribute("proposalBean", bean1);
		
		// tab 1
		model.addAttribute("productTypes", productTypes);
		model.addAttribute("modeOfPayment", modeOfPayment);
		
		// tab 2
		model.addAttribute("documentChecklist", checkList);
		
		// tab 4
		model.addAttribute("countryMap", countryMap);
		model.addAttribute("currencyCode", currencyCode);
		model.addAttribute("countryRating", countryRating);
		model.addAttribute("transactionType", transactionType);
		model.addAttribute("riskType", riskType);
		model.addAttribute("typeOfWork", policyUnderwritingService.typeOfWork());
		model.addAttribute("fundingArrangement", policyUnderwritingService.fundingArrangement());
		model.addAttribute("proposal", new DummyPolicyDetailsBean());

		System.out.println(bean1.getDummyPolicyDetailsBean());
		model.addAttribute("allDummyPolicy", bean1.getDummyPolicyDetailsBean());
		model.addAttribute("errors", "no");
		model.addAttribute("id", 4);
		model.addAttribute("pebPolicyProposalBean", session.getAttribute("proposalBean"));
		System.out.println("basicProposalDetailsList" + new ArrayList<PebPolicyProposalBean>(
				policyUnderwritingService.getProposalDetailsStore().values()));
		return "underwriting/editProposal";
	}
	

	/**
	 * Show Edit DummyPolicy in Edit In Principle Proposal form
	 * 
	 * @param dummyPolicyNo
	 */
	@GetMapping("/editProposalDummyPolicy/{dummyPolicyNo}/{proposalId}")
	public String editProposalDummyPolicy(@PathVariable("dummyPolicyNo") Long dummyPolicyNumber,
			@PathVariable("proposalId") Long proposalId, Model model, HttpSession session) {
		// get data from session variable
		PebPolicyProposalBean pBean = (PebPolicyProposalBean) session.getAttribute("proposalBean");
		List<DummyPolicyDetailsBean> dummyPolicyBeanList = pBean.getDummyPolicyDetailsBean();
		Iterator<DummyPolicyDetailsBean> iterator = dummyPolicyBeanList.iterator();
		DummyPolicyDetailsBean detailsBean = new DummyPolicyDetailsBean();
		while (iterator.hasNext()) {
			detailsBean = iterator.next();
			if (detailsBean.getDummyPolicyNumber().equals(dummyPolicyNumber)) {
				model.addAttribute("proposal", detailsBean);

			}
		}
		session.setAttribute("proposalBean", pBean);
		model.addAttribute("pebPolicyProposalBean", pBean);
		model.addAttribute("myval", dummyPolicyNumber);
		model.addAttribute("id", 4);

		// tab 1
		Map<Integer, String> productTypes = policyUnderwritingService.getProductType();
		Map<Integer, String> modeOfPayment = policyUnderwritingService.getModeOfPayment();
		model.addAttribute("productTypes", productTypes);
		model.addAttribute("modeOfPayment", modeOfPayment);

		// tab 2
		List<PebPolicyProposalDocumentCheckListBean> checkList = policyUnderwritingService.getDocumentCheckList();
		model.addAttribute("documentChecklist", checkList);

		// tab 4
		Map<String, String> countryMap = policyUnderwritingService.getCountryList();
		Map<String, String> currencyCode = policyUnderwritingService.currencyCode();
		Map<String, String> countryRating = policyUnderwritingService.countryRating();
		Map<Integer, String> transactionType = policyUnderwritingService.transactionType();
		Map<Integer, String> riskType = policyUnderwritingService.riskType();
		model.addAttribute("countryMap", countryMap);
		model.addAttribute("currencyCode", currencyCode);
		model.addAttribute("countryRating", countryRating);
		model.addAttribute("transactionType", transactionType);
		model.addAttribute("riskType", riskType);
		model.addAttribute("typeOfWork", policyUnderwritingService.typeOfWork());
		model.addAttribute("allDummyPolicy", pBean.getDummyPolicyDetailsBean());
		model.addAttribute("fundingArrangement", policyUnderwritingService.fundingArrangement());
		return "underwriting/editProposal";
	}

	/**
	 * Update Dummy Policy Details From Edit Proposal form
	 * 
	 * @param dummyPolicyNo
	 */
	@PostMapping("/updateProposalDummyPolicy")
	public String updateProposalDummyPolicy(
			@Validated(ProposalAssessmentInfo.class) @ModelAttribute("proposal") DummyPolicyDetailsBean dummy,
			BindingResult result, Model model, HttpSession session) {
		// Get Session value
		PebPolicyProposalBean pBean = (PebPolicyProposalBean) session.getAttribute("proposalBean");

		// tab 1
		Map<Integer, String> productTypes = policyUnderwritingService.getProductType();
		Map<Integer, String> modeOfPayment = policyUnderwritingService.getModeOfPayment();
		model.addAttribute("productTypes", productTypes);
		model.addAttribute("modeOfPayment", modeOfPayment);

		// tab 2
		List<PebPolicyProposalDocumentCheckListBean> checkList = policyUnderwritingService.getDocumentCheckList();
		model.addAttribute("documentChecklist", checkList);

		// tab 4
		Map<String, String> countryMap = policyUnderwritingService.getCountryList();
		Map<String, String> currencyCode = policyUnderwritingService.currencyCode();
		Map<String, String> countryRating = policyUnderwritingService.countryRating();
		Map<Integer, String> transactionType = policyUnderwritingService.transactionType();
		Map<Integer, String> riskType = policyUnderwritingService.riskType();

		if (result.hasErrors()) {
			System.out.println("3rd Update tab has erros::");

			model.addAttribute("myval", dummy.getDummyPolicyNumber());
			model.addAttribute("pebPolicyProposalBean", session.getAttribute("proposalBean"));
			
			// tab 1
			model.addAttribute("productTypes", productTypes);
			model.addAttribute("modeOfPayment", modeOfPayment);
			
			// tab 2
			model.addAttribute("documentChecklist", checkList);
			
			// tab 4
			model.addAttribute("countryMap", countryMap);
			model.addAttribute("currencyCode", currencyCode);
			model.addAttribute("countryRating", countryRating);
			model.addAttribute("transactionType", transactionType);
			model.addAttribute("riskType", riskType);
			model.addAttribute("typeOfWork", policyUnderwritingService.typeOfWork());
			model.addAttribute("fundingArrangement", policyUnderwritingService.fundingArrangement());
			model.addAttribute("allDummyPolicy", pBean.getDummyPolicyDetailsBean());
			model.addAttribute("UpdatePageErrors", "yes");
			model.addAttribute("id", 4);
			return "underwriting/editProposal";
		}
		System.out.println(dummy.getProposalId());
		// update in list
		int counter = 0;
		for (DummyPolicyDetailsBean d : pBean.getDummyPolicyDetailsBean()) {
			if (d.getProposalId().longValue() == dummy.getProposalId().longValue()) {
				if (d.getDummyPolicyNumber().longValue() == dummy.getDummyPolicyNumber().longValue()) {
					pBean.getDummyPolicyDetailsBean().set(counter, dummy);
				}
			}
			counter++;
		}

		session.setAttribute("proposalBean", pBean);
		model.addAttribute("allDummyPolicy", pBean.getDummyPolicyDetailsBean());
		
		// tab 1
		model.addAttribute("productTypes", productTypes);
		model.addAttribute("modeOfPayment", modeOfPayment);
					
		// tab 2
		model.addAttribute("documentChecklist", checkList);
		
		// tab 4
		model.addAttribute("countryMap", countryMap);
		model.addAttribute("currencyCode", currencyCode);
		model.addAttribute("countryRating", countryRating);
		model.addAttribute("transactionType", transactionType);
		model.addAttribute("riskType", riskType);
		model.addAttribute("typeOfWork", policyUnderwritingService.typeOfWork());
		model.addAttribute("fundingArrangement", policyUnderwritingService.fundingArrangement());
		model.addAttribute("proposal", new DummyPolicyDetailsBean());
		model.addAttribute("id", 4);
		System.out.println("basicProposalDetailsList" + new ArrayList<PebPolicyProposalBean>(
				policyUnderwritingService.getProposalDetailsStore().values()));
		model.addAttribute("pebPolicyProposalBean", session.getAttribute("proposalBean"));

		return "underwriting/editProposal";
	}

	/**
	 * Update Miscellaneous Details
	 * 
	 * @param pebPolicyProposalBean
	 */
	@PostMapping("/updateMiscDetails")
	public String updateMiscDetails(Model model,
			@Validated(ProposalMiscellaneousInfo.class) @ModelAttribute("pebPolicyProposalBean") PebPolicyProposalBean pebPolicyProposalBean,
			BindingResult result, HttpSession session) {
		System.out.println("pebPolicyProposalBean::" + pebPolicyProposalBean);
		System.out.println("total premium ::" + pebPolicyProposalBean.getTotalPremiumAmount());
		PebPolicyProposalBean bean1 = (PebPolicyProposalBean) session.getAttribute("proposalBean");
		System.out.println("session check::" + bean1);
		
		// tab 1
		Map<Integer, String> productTypes = policyUnderwritingService.getProductType();
		Map<Integer, String> modeOfPayment = policyUnderwritingService.getModeOfPayment();
		
		// tab 2
		List<PebPolicyProposalDocumentCheckListBean> checkList = policyUnderwritingService.getDocumentCheckList();
		
		// tab 4
		Map<String, String> countryMap = policyUnderwritingService.getCountryList();
		Map<String, String> currencyCode = policyUnderwritingService.currencyCode();
		Map<String, String> countryRating = policyUnderwritingService.countryRating();
		Map<Integer, String> transactionType = policyUnderwritingService.transactionType();
		Map<Integer, String> riskType = policyUnderwritingService.riskType();


		if (result.hasErrors()) {
			System.out.println("In if of cont misc::" + pebPolicyProposalBean.getProposalId());
			// System.out.println("Check session::" + session.getAttribute("proposalBean"));
			System.out.println("bean1.getProductId()::" + bean1.getProductId());

			pebPolicyProposalBean.setProposalId(bean1.getProposalId());
			pebPolicyProposalBean.setProductId(bean1.getProductId());
			pebPolicyProposalBean.setInwardNumber(bean1.getInwardNumber());
			pebPolicyProposalBean.setInwardItemNumber(bean1.getInwardItemNumber());
			pebPolicyProposalBean.setProjectId(bean1.getProjectId());
			pebPolicyProposalBean.setProposalDate(bean1.getProposalDate());
			pebPolicyProposalBean.setContractValue(bean1.getContractValue());
			pebPolicyProposalBean.setModeOfPayment(bean1.getModeOfPayment());
			pebPolicyProposalBean.setInstrumentNumber(bean1.getInstrumentNumber());
			pebPolicyProposalBean.setInstrumentDate(bean1.getInstrumentDate());
			pebPolicyProposalBean.setInstrumentAmount(bean1.getInstrumentAmount());
			pebPolicyProposalBean.setProcessingFeePaidDate(bean1.getProcessingFeePaidDate());
			pebPolicyProposalBean.setProcessingFeePaidAmount(bean1.getProcessingFeePaidAmount());
			pebPolicyProposalBean.setProposalStatus(bean1.getProposalStatus());
			pebPolicyProposalBean.setReceiptNumber(bean1.getReceiptNumber());
			pebPolicyProposalBean.setBankName(bean1.getBankName());
			// pebPolicyProposalBean.setNeiaSharePercentage(bean1.getNeiaSharePercentage());
			pebPolicyProposalBean.setRemarks(bean1.getRemarks());
			pebPolicyProposalBean.setTags(bean1.getTags());
			pebPolicyProposalBean.setDocumentCheckList(bean1.getDocumentCheckList());
			pebPolicyProposalBean.setIsExporterInSAL(bean1.getIsExporterInSAL());
			pebPolicyProposalBean.setIsBuyerInBSAL(bean1.getIsBuyerInBSAL());
			model.addAttribute("pebPolicyProposalBean", pebPolicyProposalBean);

			// tab 1
			model.addAttribute("productTypes", productTypes);
			model.addAttribute("modeOfPayment", modeOfPayment);
			
			// tab 2
			model.addAttribute("documentChecklist", checkList);
		
			// tab 4
			model.addAttribute("countryMap", countryMap);
			model.addAttribute("currencyCode", currencyCode);
			model.addAttribute("countryRating", countryRating);
			model.addAttribute("transactionType", transactionType);
			model.addAttribute("riskType", riskType);
			model.addAttribute("typeOfWork", policyUnderwritingService.typeOfWork());
			model.addAttribute("fundingArrangement", policyUnderwritingService.fundingArrangement());
			list = new ArrayList<>();
			model.addAttribute("allDummyPolicy", bean1.getDummyPolicyDetailsBean());
			model.addAttribute("proposal", new DummyPolicyDetailsBean());
			model.addAttribute("id", 5);
			return "underwriting/editProposal";

		} else {
			System.out.println("In else of cont scru::" + pebPolicyProposalBean.getProposalId());
			System.out.println("Check session::" + session.getAttribute("proposalBean"));
			System.out.println("In else::" + pebPolicyProposalBean.getProposalId());
			System.out.println("Check session::" + session.getAttribute("proposalBean"));
			PebPolicyProposalBean pBean1 = (PebPolicyProposalBean) session.getAttribute("proposalBean");
			System.out.println("pbean1 Id::" + pBean1.getProposalId());
			pBean1.setTotalPremiumAmount(pebPolicyProposalBean.getTotalPremiumAmount());
			pBean1.setDiscountScore(pebPolicyProposalBean.getDiscountScore());
			pBean1.setDiscountPercentage(pebPolicyProposalBean.getDiscountPercentage());

			model.addAttribute("pebPolicyProposalBean", pBean1);
			model.addAttribute("checkId", pBean1.getProposalId());
			session.setAttribute("proposalBean", pBean1);

			model.addAttribute("id", 5);
			
			// tab 1
			model.addAttribute("productTypes", productTypes);
			model.addAttribute("modeOfPayment", modeOfPayment);
			
			// tab 2
			model.addAttribute("documentChecklist", checkList);
			
			// tab 4
			model.addAttribute("countryMap", countryMap);
			model.addAttribute("currencyCode", currencyCode);
			model.addAttribute("countryRating", countryRating);
			model.addAttribute("transactionType", transactionType);
			model.addAttribute("riskType", riskType);
			model.addAttribute("typeOfWork", policyUnderwritingService.typeOfWork());
			model.addAttribute("fundingArrangement", policyUnderwritingService.fundingArrangement());
			list = new ArrayList<>();
			model.addAttribute("allDummyPolicy", pBean1.getDummyPolicyDetailsBean());
			model.addAttribute("proposal", new DummyPolicyDetailsBean());
			policyUnderwritingService.updateMiscDetails(pBean1);
			model.addAttribute("basicProposalDetailsList",
					new ArrayList<PebPolicyProposalBean>(policyUnderwritingService.getProposalDetailsStore().values()));
			System.out.println("basicProposalDetailsList" + new ArrayList<PebPolicyProposalBean>(
					policyUnderwritingService.getProposalDetailsStore().values()));
			model.addAttribute("UpdateMiscDetailsFlag", "success");
			return "underwriting/editProposal";
		}

	}

	/**
	 * Update Decision Details
	 * 
	 * @param pebPolicyProposalBean
	 */
	@PostMapping("/updateDecisionDetails")
	public String updateDecisionDetails(Model model,
			@Validated(ProposalDecisionInfo.class) @ModelAttribute("pebPolicyProposalBean") PebPolicyProposalBean pebPolicyProposalBean,
			BindingResult result, HttpSession session) {
		System.out.println("pebPolicyProposalBean::" + pebPolicyProposalBean);
		
		PebPolicyProposalBean bean1 = (PebPolicyProposalBean) session.getAttribute("proposalBean");
		System.out.println("session check::" + bean1);
		
		// tab 1
		Map<Integer, String> productTypes = policyUnderwritingService.getProductType();
		Map<Integer, String> modeOfPayment = policyUnderwritingService.getModeOfPayment();
		
		// tab 2
		List<PebPolicyProposalDocumentCheckListBean> checkList = policyUnderwritingService.getDocumentCheckList();
		
		// tab 4
		Map<String, String> countryMap = policyUnderwritingService.getCountryList();
		Map<String, String> currencyCode = policyUnderwritingService.currencyCode();
		Map<String, String> countryRating = policyUnderwritingService.countryRating();
		Map<Integer, String> transactionType = policyUnderwritingService.transactionType();
		Map<Integer, String> riskType = policyUnderwritingService.riskType();

		if (result.hasErrors()) {
			System.out.println("In if of cont misc::" + pebPolicyProposalBean.getProposalId());
			// System.out.println("Check session::" + session.getAttribute("proposalBean"));
			System.out.println("bean1.getProductId()::" + bean1.getProductId());

			pebPolicyProposalBean.setProposalId(bean1.getProposalId());
			pebPolicyProposalBean.setProductId(bean1.getProductId());
			pebPolicyProposalBean.setInwardNumber(bean1.getInwardNumber());
			pebPolicyProposalBean.setInwardItemNumber(bean1.getInwardItemNumber());
			pebPolicyProposalBean.setProjectId(bean1.getProjectId());
			pebPolicyProposalBean.setProposalDate(bean1.getProposalDate());
			pebPolicyProposalBean.setContractValue(bean1.getContractValue());
			pebPolicyProposalBean.setModeOfPayment(bean1.getModeOfPayment());
			pebPolicyProposalBean.setInstrumentNumber(bean1.getInstrumentNumber());
			pebPolicyProposalBean.setInstrumentDate(bean1.getInstrumentDate());
			pebPolicyProposalBean.setInstrumentAmount(bean1.getInstrumentAmount());
			pebPolicyProposalBean.setProcessingFeePaidDate(bean1.getProcessingFeePaidDate());
			pebPolicyProposalBean.setProcessingFeePaidAmount(bean1.getProcessingFeePaidAmount());
			pebPolicyProposalBean.setProposalStatus(bean1.getProposalStatus());
			pebPolicyProposalBean.setReceiptNumber(bean1.getReceiptNumber());
			pebPolicyProposalBean.setBankName(bean1.getBankName());
			// pebPolicyProposalBean.setNeiaSharePercentage(bean1.getNeiaSharePercentage());
			pebPolicyProposalBean.setRemarks(bean1.getRemarks());
			pebPolicyProposalBean.setTags(bean1.getTags());
			pebPolicyProposalBean.setDocumentCheckList(bean1.getDocumentCheckList());
			pebPolicyProposalBean.setIsExporterInSAL(bean1.getIsExporterInSAL());
			pebPolicyProposalBean.setIsBuyerInBSAL(bean1.getIsBuyerInBSAL());
			pebPolicyProposalBean.setDocumentCheckList(bean1.getDocumentCheckList());
			pebPolicyProposalBean.setTotalPremiumAmount(bean1.getTotalPremiumAmount());
			pebPolicyProposalBean.setDiscountScore(bean1.getDiscountScore());
			pebPolicyProposalBean.setDiscountPercentage(bean1.getDiscountPercentage());

			model.addAttribute("pebPolicyProposalBean", pebPolicyProposalBean);

			// tab 1
			model.addAttribute("productTypes", productTypes);
			model.addAttribute("modeOfPayment", modeOfPayment);
			
			// tab 2
			model.addAttribute("documentChecklist", checkList);
			
			// tab 4
			model.addAttribute("countryMap", countryMap);
			model.addAttribute("currencyCode", currencyCode);
			model.addAttribute("countryRating", countryRating);
			model.addAttribute("transactionType", transactionType);
			model.addAttribute("riskType", riskType);
			model.addAttribute("typeOfWork", policyUnderwritingService.typeOfWork());
			model.addAttribute("fundingArrangement", policyUnderwritingService.fundingArrangement());
			list = new ArrayList<>();
			model.addAttribute("allDummyPolicy", bean1.getDummyPolicyDetailsBean());
			model.addAttribute("proposal", new DummyPolicyDetailsBean());
			model.addAttribute("id", 6);
			return "underwriting/editProposal";

		} else {
			System.out.println("In else of cont scru::" + pebPolicyProposalBean.getProposalId());
			System.out.println("Check session::" + session.getAttribute("proposalBean"));
			System.out.println("In else::" + pebPolicyProposalBean.getProposalId());
			System.out.println("Check session::" + session.getAttribute("proposalBean"));
			PebPolicyProposalBean pBean1 = (PebPolicyProposalBean) session.getAttribute("proposalBean");
			System.out.println("pbean1 Id::" + pBean1.getProposalId());
			pBean1.setEcgcDecision(pebPolicyProposalBean.getEcgcDecision());
			pBean1.setEcgcDecisionDate(pebPolicyProposalBean.getEcgcDecisionDate());
			pBean1.setEcgcRemarks(pebPolicyProposalBean.getEcgcRemarks());
			pBean1.setEcgcRecommendation(pebPolicyProposalBean.getEcgcRecommendation());
			pBean1.setCodDecision(pebPolicyProposalBean.getCodDecision());
			pBean1.setCodDecisionDate(pebPolicyProposalBean.getCodDecisionDate());
			pBean1.setCodRemarks(pebPolicyProposalBean.getCodRemarks());
			pBean1.setCodRecommendation(pebPolicyProposalBean.getCodRecommendation());
			pBean1.setProcessingOfficerComments(pebPolicyProposalBean.getProcessingOfficerComments());
			pBean1.setProcessingOfficerCommentedDate(pebPolicyProposalBean.getProcessingOfficerCommentedDate());
			pBean1.setObserverComments(pebPolicyProposalBean.getObserverComments());
			pBean1.setObserverCommentedDate(pebPolicyProposalBean.getObserverCommentedDate());
			pBean1.setRecommenderComments(pebPolicyProposalBean.getRecommenderComments());
			pBean1.setRecommenderCommentedDate(pebPolicyProposalBean.getRecommenderCommentedDate());
			pBean1.setCodMeetingDate(pebPolicyProposalBean.getCodMeetingDate());

			model.addAttribute("pebPolicyProposalBean", pBean1);
			model.addAttribute("checkId", pBean1.getProposalId());
			session.setAttribute("proposalBean", pBean1);

			model.addAttribute("id", 6);
			
			// tab 1
			model.addAttribute("productTypes", productTypes);
			model.addAttribute("modeOfPayment", modeOfPayment);
			
			// tab 2
			model.addAttribute("documentChecklist", checkList);
			
			// tab 4
			model.addAttribute("countryMap", countryMap);
			model.addAttribute("currencyCode", currencyCode);
			model.addAttribute("countryRating", countryRating);
			model.addAttribute("transactionType", transactionType);
			model.addAttribute("riskType", riskType);
			model.addAttribute("typeOfWork", policyUnderwritingService.typeOfWork());
			model.addAttribute("fundingArrangement", policyUnderwritingService.fundingArrangement());
			list = new ArrayList<>();
			model.addAttribute("allDummyPolicy", pBean1.getDummyPolicyDetailsBean());
			model.addAttribute("proposal", new DummyPolicyDetailsBean());
			policyUnderwritingService.updateDecisionDetails(pBean1);
			model.addAttribute("basicProposalDetailsList",
					new ArrayList<PebPolicyProposalBean>(policyUnderwritingService.getProposalDetailsStore().values()));
			System.out.println("basicProposalDetailsList" + new ArrayList<PebPolicyProposalBean>(
					policyUnderwritingService.getProposalDetailsStore().values()));
			return "underwriting/listProposal";
		}

	}

	/**
	 * Show List of Proposal Page
	 * 
	 */
	@GetMapping(value = "/listProposal")
	public String listProposal(Model model) {
		
		  Map<Integer, String> productTypes =policyUnderwritingService.getProductType();
		  model.addAttribute("productTypes", productTypes);
		  //model.addAttribute("basicProposalDetailsList",policyUnderwritingService.selectAllProposals());
		  model.addAttribute("basicProposalDetailsList", new ArrayList<PebPolicyProposalBean>(
					policyUnderwritingService.getProposalDetailsStore().values()));
		return "underwriting/listProposal";
	}

	
	
	

	 /**
	 * To View Proposal Details
	 * V
	 * @param proposalId
	 */
	
		  @GetMapping("/viewProposal/{proposalId}") 
		  public String viewBasicProposalDetails(Model model, PebPolicyProposalBean pebPolicyProposalBean,@PathVariable("proposalId") Long id) {
		  
		  System.out.println("In view" + id);
		  Map<Integer, String> productTypes = policyUnderwritingService.getProductType();
		  model.addAttribute("productTypes", productTypes);
		  
		  Map<Integer,String> transactionType = policyUnderwritingService.transactionType(); 
		  Map<Integer,String> riskType = policyUnderwritingService.riskType();
		  List<PebPolicyProposalDocumentCheckListBean> checkList = policyUnderwritingService.getDocumentCheckList();
		  model.addAttribute("transactionType", transactionType);
		  model.addAttribute("riskType", riskType);
		  model.addAttribute("documentChecklist", checkList);
		  model.addAttribute("typeOfWork", policyUnderwritingService.typeOfWork());
		  PebPolicyProposalBean bean=policyUnderwritingService.getProposalDetailsById(id);
		  System.out.println("product:"+bean.getProductId());
		  
		  Double contractValue = bean.getContractValue();
		  DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
	      String firstNumberAsString = decimalFormat.format(contractValue);
	      model.addAttribute("contractValue", firstNumberAsString);
		  model.addAttribute("pebPolicyProposalBean", bean);
		  List<DummyPolicyDetailsBean> dummyPolicyList = bean.getDummyPolicyDetailsBean();
		  System.out.println("allDummyPolicy::"+bean.getDummyPolicyDetailsBean());
		  model.addAttribute("allDummyPolicy", bean.getDummyPolicyDetailsBean());
		 // model.addAttribute("pebPolicyProposalBean",policyUnderwritingService.getProposalDetailsById(id)); 
		 // System.out.println("data:"+projectService.getProjectById(id));
		 // System.out.println("view::"+policyUnderwritingService.getProposalDetailsById(id)); 
		  //List<PebPolicyProposalDocumentCheckListBean> checkList =policyUnderwritingService.getDocumentCheckList();
		  model.addAttribute("documentChecklist", checkList); 
		  return "underwriting/viewProposal";
		  
		  
		  }
		 
		  /**
			 * To get details of specific dummy policy of on the basis of Id
			 * V
			 * @param dummyPolicyId
			 */
		  @RequestMapping(value = "/getDummyPolicy")
			@ResponseBody
			public DummyPolicyDetailsBean getDummyPolicyByDummyPolicyNumber(@RequestParam("dummyPolicyId") Long dummyPolicyNumber,Model model,HttpSession session) {
				System.out.println("In Ajax:");
				PebPolicyProposalBean pBean =(PebPolicyProposalBean) session.getAttribute("proposalBean");
				DummyPolicyDetailsBean allDummyPolicy = getDummyPolicy(dummyPolicyNumber,pBean);
				//model.addAttribute("response", dummyPolicyByDummyPolicyNumber);
				//return dummyPolicyByDummyPolicyNumber;
				System.out.println("allDummyPolicy::"+allDummyPolicy);
				return allDummyPolicy;
			}
		 
		
		  
		  /**
			 * To view assessment details of dummy policy of on the basis of Id
			 * V
			 * @param dummyPolicyId
			 */
			@GetMapping("viewDummyPolicy")
			public String viewDummyPolicy(@RequestParam("dummyPolicyId") Long dummyPolicyNumber,
										Model model,
										HttpSession session) {
				System.out.println("in viewDummy::"+dummyPolicyNumber );
				Map<String,String> countryMap = policyUnderwritingService.getCountryList();
				Map<String,String> currencyCode = policyUnderwritingService.currencyCode();
				Map<String,String> countryRating = policyUnderwritingService.countryRating();
				Map<Integer,String> transactionType = policyUnderwritingService.transactionType();
				Map<Integer,String> riskType = policyUnderwritingService.riskType();
				
				//get data from mock db
				//DummyPolicyDetailsBean dummyPolicyByDummyPolicyNumber = policyUnderwritingService.getDummyPolicyByDummyPolicyNumber(dummyPolicyNumber);
				
				//get data from arraylist 
				PebPolicyProposalBean pBean = (PebPolicyProposalBean) session.getAttribute("proposalBean");
				DummyPolicyDetailsBean dummyPolicyByDummyPolicyNumber = getDummyPolicy(dummyPolicyNumber,pBean);
				System.out.println(dummyPolicyByDummyPolicyNumber);
				model.addAttribute("dummyPolicyDetailsBean", dummyPolicyByDummyPolicyNumber);
				model.addAttribute("countryMap", countryMap);
				model.addAttribute("currencyCode", currencyCode);
				model.addAttribute("countryRating", countryRating);
				model.addAttribute("transactionType", transactionType);
				model.addAttribute("riskType", riskType);
				model.addAttribute("typeOfWork", policyUnderwritingService.typeOfWork());
				model.addAttribute("fundingArrangement", policyUnderwritingService.fundingArrangement());
				return "underwriting/viewassessment";
			}

			private DummyPolicyDetailsBean getDummyPolicy(Long dummyPolicyNumber,PebPolicyProposalBean pBean) {
				list= pBean.getDummyPolicyDetailsBean();
				System.out.println("list:"+list);
				return list.stream().filter(d -> d.getDummyPolicyNumber().longValue() == dummyPolicyNumber.longValue())
						.findAny().orElse(null);
			}

	/*
	 * @InitBinder("dummyPolicyDetailsBean") protected void initBinder(WebDataBinder
	 * binder) { binder.addValidators(new PersonValidator()); }
	 */
}
