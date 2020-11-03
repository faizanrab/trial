package in.ecgc.erp.pebPolicy.controller.prebidquote;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpSession;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import in.ecgc.erp.pebPolicy.dao.prebidquote.FakeDao;
import in.ecgc.erp.pebPolicy.model.prebidquote.PEBPolicyPrebidQuotationBean;
import in.ecgc.erp.pebPolicy.model.prebidquote.PEBPolicyPrebidQuotationTOPBean;
import in.ecgc.erp.pebPolicy.service.prebidquote.PEBPolicyUnderWritingService;



@Controller
@RequestMapping("/pebpolicy/")
public class PrebidQuoteController {

	private static final Logger LOGGER=LoggerFactory.getLogger(PrebidQuoteController.class);

	@Autowired
	private PEBPolicyUnderWritingService pebService;
	
	@Autowired
	private FakeDao dao;
	
	
	@GetMapping("prebidpage")
	public String showPrebidPage(Model model, HttpSession session){
		session.removeAttribute("PREBIDQUOTE");
		
		Map<String,String> countryMap = pebService.getCountryList();
		//Map<String,String> countryRatingMap =pebService.getCountryList();
		model.addAttribute("countryMap", countryMap);
		//model.addAttribute("countryRatingMap", countryRatingMap);
		//session.setAttribute("countryRatingMap", countryRatingMap);
		model.addAttribute("checkId", 1);
		PEBPolicyPrebidQuotationBean prebidquote = new PEBPolicyPrebidQuotationBean();
		//PEBPolicyPrebidQuotationTOPBean termsOfPayment = new PEBPolicyPrebidQuotationTOPBean();
	
		List<PEBPolicyPrebidQuotationTOPBean> termsOfPayment = new ArrayList<PEBPolicyPrebidQuotationTOPBean>();
		//System.out.println();
		LOGGER.info("Proceed!");
		prebidquote.setTermsOfPayment(termsOfPayment);
		model.addAttribute("prebidquote", prebidquote);
		//model.addAttribute("termsOfPayment", termsOfPayment);
		//model.addAttribute("checkId", 12);
		//model.addAttribute("termsOfPayment", new PEBPolicyPrebidQuotationTOPBean());
		return "prebidquote/prebid";
		
	}
	
	@PostMapping("submitPrebid")
	public String submittedPrebidQuote(@Validated @ModelAttribute("prebidquote") PEBPolicyPrebidQuotationBean pebPolicyPrebidQuotationBean,
										BindingResult result,Model model,
										RedirectAttributes redirectAttributes) {
		Map<String,String> countryMap = pebService.getCountryList();
		if(result.hasErrors()){
			System.out.println("In hasErrors" +pebPolicyPrebidQuotationBean);
			System.out.println(result.getFieldError());
			model.addAttribute("countryMap", countryMap);
			return "prebidquote/prebid";
		}
		pebPolicyPrebidQuotationBean.setInwardNumber("in"+new Random().nextInt(9000));
		Long randomPrebidId = (long)new Random().nextInt(1000);
		pebPolicyPrebidQuotationBean.setPbId(randomPrebidId);
		//Boolean response = dao.insert(pebPolicyPrebidQuotationBean);
	
		System.out.println("In else pebPolicyPrebidQuotationBean::"+ pebPolicyPrebidQuotationBean);
		//System.out.println("TOP::"+pebPolicyPrebidQuotationBean.getTermsOfPayment());
		List<PEBPolicyPrebidQuotationTOPBean> top= pebPolicyPrebidQuotationBean.getTermsOfPayment();
		List<PEBPolicyPrebidQuotationTOPBean> newTermsOfPayment = new ArrayList<PEBPolicyPrebidQuotationTOPBean>();
		//System.out.println("Flag Value::"+top.get(0).getIsSupplyFlag());
		String flagValue = top.get(0).getIsSupplyFlag();
		System.out.println("flagValue::"+flagValue);
		//System.out.println("conndition...");
		
		if(flagValue.equals("1"))
		{
			
			 //System.out.println("TOP::"+pebPolicyPrebidQuotationBean.getTermsOfPayment());
			 //System.out.println("TOP11::"+top.get(0));
			 newTermsOfPayment.add(top.get(0));
			 pebPolicyPrebidQuotationBean.setTermsOfPayment(newTermsOfPayment);
			 System.out.println("supp final:"+pebPolicyPrebidQuotationBean);
		}
		if(flagValue.equals("2"))
		{
			 newTermsOfPayment.add(top.get(1));
			 pebPolicyPrebidQuotationBean.setTermsOfPayment(newTermsOfPayment);
			 System.out.println("supp final:"+pebPolicyPrebidQuotationBean);
		}
		if(flagValue.equals("3"))
		{
			 newTermsOfPayment.addAll(top);
			 pebPolicyPrebidQuotationBean.setTermsOfPayment(newTermsOfPayment);
			 System.out.println("supp final:"+pebPolicyPrebidQuotationBean);
		}
		Boolean response = dao.insert(pebPolicyPrebidQuotationBean);
		System.out.println("response::"+response);
		if(response)redirectAttributes.addFlashAttribute("msg",randomPrebidId);
		return "redirect:/pebpolicy/prebidpage";
	}
	
	
	//view list of prebid quotes 
	//changed by V convert double contract value to string in order to escape exponential scientific notation.
	
	@GetMapping("viewPrebids")
	public String viewListOfPrebids( Model model){
		System.out.println("to check::"+dao.getAll());
		List<PEBPolicyPrebidQuotationBean> list = dao.getAll();
		list.forEach(p -> {
			DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
			 String firstNumberAsString = decimalFormat.format(p.getContractValue());
			p.setContractValueString(firstNumberAsString);
		});
		model.addAttribute("listprebid", list);
		return "prebidquote/listprebid";
	}

	@GetMapping("list")
	public @ResponseBody List<PEBPolicyPrebidQuotationBean> getListOfPrebids(){
		System.out.println(dao.getAll());
		return dao.getAll();
	}
	
	//view individual prebid details
	@GetMapping("viewPrebids/{data}")
	public String getPrebidByPBId(@PathVariable("data") Long data, Model model){
		System.out.println("inside method of view");
		PEBPolicyPrebidQuotationBean bean = dao.getOne(data);
		System.out.println("bean"+ bean);
		System.out.println("contract value"+bean.getContractValue());
		   Double contractValue = bean.getContractValue();
		   DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
	       String firstNumberAsString = decimalFormat.format(contractValue);
	       System.out.println(firstNumberAsString);
	      // bean.setContractValue(firstNumberAsString);
	       model.addAttribute("contractValue",firstNumberAsString);
		model.addAttribute("prebid",bean);
		//model.addAttribute("top", bean.getTermsOfPayment());
		//model.addAttribute("checkId", 11);
		return "prebidquote/viewsubmittedprebid";
	}
	
	
//	@PostMapping("/submitPrebid")
//	public String submitPrebidQuote(Model model, @Valid @ModelAttribute("prebidquote") PEBPolicyPrebidQuotationBean pppqb, 
//			BindingResult result, HttpSession session){
//		session.removeAttribute("PREBIDQUOTE");
//		System.out.println(pppqb);
//		if(result.hasErrors()){
//			Map<String,String> countryMap = pebService.getCountryList();
//			model.addAttribute("countryMap", countryMap);
//			System.out.println("error occurred"+result);
//			return "pebpolicy/prebid";
//		}
//		
//		/**
//		 * Validations
//		 * 1) If moratorium period is set, it should not exceed the duration
//		 * 2) moratorium period, frequency and number of installments should all be set or should not be at all.
//		 * 3) To add Advance field in the prebid form. The Advance % (weightage) should not exceed 100%
//		 * 4) TOP like down, progress, PAC,FAC weightage should not exceed 100%
//		 * 5) Advance = 100 - sum(down, progress, PAC,FAC)
//		 * 6) For cross country scenarios either Funding agency or cross country should be set (not both)***
//		 */
//		boolean moratoriumFlag = PEBPolicyUtility.checkMoratoriumData(pppqb.getMoratoriumPeriod(), pppqb.getMoratoriumNoOfInstallments(), pppqb.getMoratoriumFreqOfInstallments());
//		if(!moratoriumFlag){
//			model.addAttribute("errorMessage", "Please provide valid moratorium details");
//			Map<String,String> countryMap = pebService.getCountryList();
//			model.addAttribute("countryMap", countryMap);				
//			return "pebpolicy/prebid";							
//		}
//		System.out.println(moratoriumFlag);
//		
//		/**
//		 * Validation for
//		 * every terms of payment must exists as a combination of duration and weightage. 
//		 * 
//		 */
//		if(pppqb != null && pppqb.getTermsOfPayment() != null){
//			System.out.println("validating terms of payment");
//			String errorMessage = PEBPolicyUtility.checkTOPDurationWeightageMapping(pppqb.getTermsOfPayment());
//			if(!"".equals(errorMessage)){
//				model.addAttribute("errorMessage", errorMessage);
//				Map<String,String> countryMap = pebService.getCountryList();
//				model.addAttribute("countryMap", countryMap);				
//				return "pebpolicy/prebid";				
//			}
//		}
//		
//		/*
//		 * Validation ends
//		 */
//		
//		System.out.println(pppqb);
//		/*
//		 * copy the contents of PEBPolicyPrebidQuotationBean (controller) to PEBPolicyPrebidQuotationBean (service, provided as a jar)
//		 * call the service method. To calculate the premium and insert the data in the table.
//		 */
//		//org.PEB_Policy_Service.model.PEBPolicyPrebidQuotationBean bean = new org.PEB_Policy_Service.model.PEBPolicyPrebidQuotationBean();
//		
//		//bean = populateBean(bean, pppqb);
//		System.out.println(pppqb);
//		long preBidId = pebService.preBidQuoteOperation(pppqb);
//		session.setAttribute("PREBIDQUOTE", preBidId);
//		System.out.println(preBidId);
//		return "redirect:viewSubmittedPrebidQuote";
//	}
//	
//	
//	/*private org.PEB_Policy_Service.model.PEBPolicyPrebidQuotationBean populateBean(org.PEB_Policy_Service.model.PEBPolicyPrebidQuotationBean bean1, PEBPolicyPrebidQuotationBean bean2){
//		
//		bean1.setExporterName(bean2.getExporterName());		
//		bean1.setProjectCountry(bean2.getProjectCountry());
//		bean1.setContractValue(bean2.getContractValue());
//		bean1.setPaymentCountry(bean2.getPaymentCountry());
//		bean1.setFundedBy(bean2.getFundedBy());
//		bean1.setBuyerName(bean2.getBuyerName());
//		bean1.setBuyerType(bean2.getBuyerType());
//		bean1.setDuration(bean2.getDuration());
//		bean1.setRiskType(bean2.getRiskType());
//		bean1.setTransactionType(bean2.getTransactionType());
//		bean1.setProjectDetails(bean2.getProjectDetails());
//		bean1.setIsPreshipmentCoverRequired(bean2.getIsPreshipmentCoverRequired());
//		bean1.setPreshipmentCoverTenure(bean2.getPreshipmentCoverTenure());
//		bean1.setMoratoriumPeriod(bean2.getMoratoriumPeriod());
//		bean1.setMoratoriumNoOfInstallments(bean2.getMoratoriumNoOfInstallments());
//		bean1.setMoratoriumFreqOfInstallments(bean2.getMoratoriumFreqOfInstallments());
//		org.PEB_Policy_Service.model.PEBPolicyPrebidQuotationTOPBean topbean = new org.PEB_Policy_Service.model.PEBPolicyPrebidQuotationTOPBean();
//		topbean.setDownDuration(bean2.getTermsOfPayment().getDownDuration());
//		topbean.setDownWeightage(bean2.getTermsOfPayment().getDownWeightage());
//		topbean.setProgressDuration(bean2.getTermsOfPayment().getProgressDuration());
//		topbean.setProgressWeightage(bean2.getTermsOfPayment().getProgressWeightage());
//
//		topbean.setPACDuration(bean2.getTermsOfPayment().getPACDuration());
//		topbean.setPACWeightage(bean2.getTermsOfPayment().getPACWeightage());
//		topbean.setFACDuration(bean2.getTermsOfPayment().getFACDuration());
//		topbean.setFACWeightage(bean2.getTermsOfPayment().getFACWeightage());
//				
//		bean1.setTermsOfPayment(topbean);
//		
//		return bean1;
//	}*/
//	
//	@GetMapping("/viewSubmittedPrebidQuote")
//	public String viewSubmittedPrebidQuote(HttpSession session, Model model){
//		long preBidId = (Long)session.getAttribute("PREBIDQUOTE");
//		PEBPolicyPrebidQuotationBean bean = pebService.getPrebidQuotationDetails(preBidId);
//		model.addAttribute("prebidquote", bean);
//		return "pebpolicy/viewsubmittedprebid";
//	}
		
}
