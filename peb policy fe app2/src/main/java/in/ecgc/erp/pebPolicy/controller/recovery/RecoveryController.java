package in.ecgc.erp.pebPolicy.controller.recovery;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import in.ecgc.erp.pebPolicy.model.recovery.PebPolicyBlockedFundBean;
import in.ecgc.erp.pebPolicy.model.recovery.PebPolicyRecoveryDetailsBean;
import in.ecgc.erp.pebPolicy.model.recovery.PebPolicyRecoveryMasterBean;
import in.ecgc.erp.pebPolicy.model.recovery.PebPolicyWriteOffBean;
import in.ecgc.erp.pebPolicy.service.recovery.AddRecoveryService;

@Controller
public class RecoveryController {
	public static String uploadDirectory = System.getProperty("user.dir") + "/uploads";
	List<PebPolicyRecoveryMasterBean> recoveryList;
	PebPolicyRecoveryMasterBean recoveryMasterBean1;
	PebPolicyBlockedFundBean recoveryBlockedFundBean;
	PebPolicyWriteOffBean recoveryWriteOffBean;
	List<PebPolicyRecoveryMasterBean> recoveryList1;
	List<PebPolicyRecoveryDetailsBean> recoveryDetailsList1=new ArrayList<PebPolicyRecoveryDetailsBean>();
	List<PebPolicyRecoveryDetailsBean> recoveryDetailsList2;
	PebPolicyRecoveryMasterBean pebPolicyRecoveryMasterBean=new PebPolicyRecoveryMasterBean();
	PebPolicyRecoveryMasterBean pebPolicyRecoveryMasterBean1=new PebPolicyRecoveryMasterBean();
	List<PebPolicyRecoveryMasterBean> pebPolicyRecoveryMasterBeanList=new ArrayList<PebPolicyRecoveryMasterBean>();

	PebPolicyRecoveryMasterBean recoveryMasterBean2;
	PebPolicyRecoveryMasterBean recoveryMasterBean3;
	PebPolicyRecoveryMasterBean recoveryMasterBean4;
	PebPolicyRecoveryMasterBean recoveryMasterBean5=new PebPolicyRecoveryMasterBean();
	PebPolicyRecoveryDetailsBean recoveryDetailsBean4;
	//PebPolicyRecoveryMasterBean recoveryMasterBean5;
	Long id=(long) 1;
	Long id1=(long) 0;
	Long id2=(long)0;

	
	public RecoveryController(List<PebPolicyRecoveryMasterBean> recoveryList
		) {
		super();
		this.recoveryList = recoveryList;
		
		
	}
	@Autowired
	private AddRecoveryService addRecoveryService;
	@GetMapping(value = "/getRecoverySystemEntryPage")
	public String getRecoverySystemEntryPage(Model model, HttpSession session) 
	{
		model.addAttribute("activepage",session.getAttribute("activepage"));
		return "recovery/recovery7";
	}
	@GetMapping(value = "/addRecoverySystemEntryPage")
	public String addRecoverySystemEntryPage(Model model, HttpSession session) {
		session.invalidate();
		model.addAttribute("activepage",session.getAttribute("activepage"));
		model.addAttribute("pebPolicyRecoveryDetailsBean1", new PebPolicyRecoveryDetailsBean());
		model.addAttribute("pebPolicyRecoveryDetailsBean", new PebPolicyRecoveryDetailsBean());
	
			model.addAttribute("pebPolicyBlockedFundBean",new PebPolicyBlockedFundBean());	
		
		
		
			model.addAttribute("pebPolicyWriteOffBean",new PebPolicyWriteOffBean());	
	
	
		
			model.addAttribute("pebPolicyRecoveryMasterBean",new PebPolicyRecoveryMasterBean());	
		
			
		
		if(recoveryDetailsList1==null)
		{
		 recoveryDetailsList1=new ArrayList<PebPolicyRecoveryDetailsBean>();
		}
		
		 
	//	model.addAttribute("pebPolicyRecoveryMasterBean",recoveryMasterBean5);
		return "recovery/recovery2";
	}
//	@PostMapping(value = "/addRecovery")
//	public String addRecovery( @ModelAttribute PebPolicyRecoveryMasterBean pebPolicyRecoveryMasterBean,Model model) {
//		model.addAttribute("pebPolicyRecoveryDetailsBean", new PebPolicyRecoveryDetailsBean());
//		id1++;
//		 
//		// pebPolicyRecoveryMasterBean.setRecoveryId(id);
//		 recoveryMasterBean5=pebPolicyRecoveryMasterBean;
//		
//	//	model.addAttribute("pebPolicyRecoveryMasterBean",recoveryMasterBean5);
//		return "recovery/addrecovery";
//	}
	
	@PostMapping(value = "/addRecoveryDetails")
	public String addRecoveryDetails(@Valid @ModelAttribute("pebPolicyRecoveryDetailsBean1") PebPolicyRecoveryDetailsBean pebPolicyRecoveryDetailsBean1,BindingResult bindingResult,Model model,HttpSession session) {
		model.addAttribute("activepage",session.getAttribute("activepage"));
		PebPolicyBlockedFundBean pebPolicyBlockedFundBean=(PebPolicyBlockedFundBean)session.getAttribute("pebPolicyBlockedFundBean");
		PebPolicyWriteOffBean pebPolicyWriteOffBean=(PebPolicyWriteOffBean)session.getAttribute("pebPolicyWriteOffBean");
		PebPolicyRecoveryMasterBean pebPolicyRecoveryMasterBean=(PebPolicyRecoveryMasterBean)session.getAttribute("pebPolicyRecoveryMasterBean");
		model.addAttribute("showtable","showtable");
		if(bindingResult.hasErrors()) {
		
			model.addAttribute("pebPolicyRecoveryDetailsBean", new PebPolicyRecoveryDetailsBean());
			if(pebPolicyBlockedFundBean==null)
			{
				model.addAttribute("pebPolicyBlockedFundBean",new PebPolicyBlockedFundBean());	
			}
			else
			{
				model.addAttribute("pebPolicyBlockedFundBean", pebPolicyBlockedFundBean);
			}
			if(pebPolicyWriteOffBean==null)
			{
				model.addAttribute("pebPolicyWriteOffBean",new PebPolicyWriteOffBean());	
			}
			else
			{
			model.addAttribute("pebPolicyWriteOffBean", pebPolicyWriteOffBean);
			}
			if(pebPolicyRecoveryMasterBean==null)
			{
				model.addAttribute("pebPolicyRecoveryMasterBean",new PebPolicyRecoveryMasterBean());	
			}
			else
			{
			model.addAttribute("pebPolicyRecoveryMasterBean", pebPolicyRecoveryMasterBean);
			}
			
			model.addAttribute("recoveryDetailsLists",recoveryDetailsList1);
		       return "recovery/recovery3";
		    }
		id1++;
		pebPolicyRecoveryDetailsBean1.setRecoveryInstance(id1);
		pebPolicyRecoveryDetailsBean1.setRecoveryId(id);
		
		model.addAttribute("pebPolicyRecoveryDetailsBean", new PebPolicyRecoveryDetailsBean());
		model.addAttribute("pebPolicyRecoveryDetailsBean1", new PebPolicyRecoveryDetailsBean());

		recoveryDetailsList1.add(pebPolicyRecoveryDetailsBean1);
		recoveryDetailsList2=recoveryDetailsList1;
		recoveryMasterBean5.setRecoveryDetailsList(recoveryDetailsList1);
	
		if(pebPolicyBlockedFundBean==null)
		{
			model.addAttribute("pebPolicyBlockedFundBean",new PebPolicyBlockedFundBean());	
		}
		else
		{
			model.addAttribute("pebPolicyBlockedFundBean", pebPolicyBlockedFundBean);
		}
		if(pebPolicyWriteOffBean==null)
		{
			model.addAttribute("pebPolicyWriteOffBean",new PebPolicyWriteOffBean());	
		}
		else
		{
		model.addAttribute("pebPolicyWriteOffBean", pebPolicyWriteOffBean);
		}
		if(pebPolicyRecoveryMasterBean==null)
		{
			model.addAttribute("pebPolicyRecoveryMasterBean",new PebPolicyRecoveryMasterBean());	
		}
		else
		{
		model.addAttribute("pebPolicyRecoveryMasterBean", pebPolicyRecoveryMasterBean);
		}
	
		model.addAttribute("recoveryDetailsLists",recoveryMasterBean5.getRecoveryDetailsList());
		
		return "recovery/recovery2";
		
	}
	@PostMapping(value = "/newRecoveryDetails1")
	public String newRecoveryDetailsAction1(@Valid @ModelAttribute PebPolicyRecoveryDetailsBean pebPolicyRecoveryDetailsBean,BindingResult bindingResult,Model model,HttpSession session) {
		//recoveryDetailsList.setRecoveryId(id);
		model.addAttribute("activepage",session.getAttribute("activepage"));
		PebPolicyBlockedFundBean pebPolicyBlockedFundBean=(PebPolicyBlockedFundBean)session.getAttribute("pebPolicyBlockedFundBean");
		PebPolicyWriteOffBean pebPolicyWriteOffBean=(PebPolicyWriteOffBean)session.getAttribute("pebPolicyWriteOffBean");
		PebPolicyRecoveryMasterBean pebPolicyRecoveryMasterBean=(PebPolicyRecoveryMasterBean)session.getAttribute("pebPolicyRecoveryMasterBean");
		model.addAttribute("showtable","showtable");
		if(bindingResult.hasErrors()) {
			
			model.addAttribute("pebPolicyRecoveryDetailsBean1", new PebPolicyRecoveryDetailsBean());
			model.addAttribute("pebPolicyRecoveryDetailsBean", pebPolicyRecoveryDetailsBean);
			model.addAttribute("recoveryDetailsLists",recoveryDetailsList1);
			if(pebPolicyBlockedFundBean==null)
			{
				model.addAttribute("pebPolicyBlockedFundBean",new PebPolicyBlockedFundBean());	
			}
			else
			{
				model.addAttribute("pebPolicyBlockedFundBean", pebPolicyBlockedFundBean);
			}
			if(pebPolicyWriteOffBean==null)
			{
				model.addAttribute("pebPolicyWriteOffBean",new PebPolicyWriteOffBean());	
			}
			else
			{
			model.addAttribute("pebPolicyWriteOffBean", pebPolicyWriteOffBean);
			}
			if(pebPolicyRecoveryMasterBean==null)
			{
				model.addAttribute("pebPolicyRecoveryMasterBean",new PebPolicyRecoveryMasterBean());	
			}
			else
			{
			model.addAttribute("pebPolicyRecoveryMasterBean", pebPolicyRecoveryMasterBean);
			}
		       return "recovery/recovery4";
		    }
	 	
		model.addAttribute("pebPolicyRecoveryDetailsBean1", new PebPolicyRecoveryDetailsBean());
		
		Iterator<PebPolicyRecoveryDetailsBean> Iterator = recoveryDetailsList1.iterator();
        while (Iterator.hasNext()) {
        	recoveryDetailsBean4= Iterator.next();
      
      // 	System.out.println(recoveryDetailsBean4);
        	if(recoveryDetailsBean4.getRecoveryInstance()==pebPolicyRecoveryDetailsBean.getRecoveryInstance())
        	{
        		break;
        	}
        }
        System.out.println(recoveryDetailsList1);
		int index=recoveryDetailsList1.indexOf(recoveryDetailsBean4);
		System.out.println(index);
		recoveryDetailsList1.set(index, pebPolicyRecoveryDetailsBean);
		recoveryMasterBean5.setRecoveryDetailsList(recoveryDetailsList1);
		if(pebPolicyBlockedFundBean==null)
		{
			model.addAttribute("pebPolicyBlockedFundBean",new PebPolicyBlockedFundBean());	
		}
		else
		{
			model.addAttribute("pebPolicyBlockedFundBean", pebPolicyBlockedFundBean);
		}
		if(pebPolicyWriteOffBean==null)
		{
			model.addAttribute("pebPolicyWriteOffBean",new PebPolicyWriteOffBean());	
		}
		else
		{
		model.addAttribute("pebPolicyWriteOffBean", pebPolicyWriteOffBean);
		}
		if(pebPolicyRecoveryMasterBean==null)
		{
			model.addAttribute("pebPolicyRecoveryMasterBean",new PebPolicyRecoveryMasterBean());	
		}
		else
		{
		model.addAttribute("pebPolicyRecoveryMasterBean", pebPolicyRecoveryMasterBean);
		}
	//	System.out.println(recoveryMasterBean5);
//		
//		recoveryList=addRecoveryService.getRecoveryList();
//		model.addAttribute("recoveryList",recoveryList);
		model.addAttribute("recoveryDetailsLists",recoveryMasterBean5.getRecoveryDetailsList());
		
		return "recovery/recovery2";
		
	}
	@PostMapping(value = "/addRecoveryDetailsList")
	public String addRecoveryDetailsAction1(@Valid @ModelAttribute PebPolicyRecoveryMasterBean pebPolicyRecoveryMasterBean1,BindingResult bindingResult,Model model,HttpSession session) {
	
		MultipartFile files=pebPolicyRecoveryMasterBean1.getRecoveryDocument();
		System.out.println("hii1"+pebPolicyRecoveryMasterBean1);
		PebPolicyBlockedFundBean pebPolicyBlockedFundBean=(PebPolicyBlockedFundBean)session.getAttribute("pebPolicyBlockedFundBean");
		PebPolicyWriteOffBean pebPolicyWriteOffBean=(PebPolicyWriteOffBean)session.getAttribute("pebPolicyWriteOffBean");
		PebPolicyRecoveryMasterBean pebPolicyRecoveryMasterBean=(PebPolicyRecoveryMasterBean)session.getAttribute("pebPolicyRecoveryMasterBean");
session.setAttribute("activepage", "RecoveryDetailsList");
		model.addAttribute("activepage",session.getAttribute("activepage"));
		
		System.out.println(recoveryDetailsList1);
		if (bindingResult.hasErrors()) {
			model.addAttribute("pebPolicyRecoveryDetailsBean1", new PebPolicyRecoveryDetailsBean());
			model.addAttribute("pebPolicyRecoveryDetailsBean", new PebPolicyRecoveryDetailsBean());

			model.addAttribute("recoveryDetailsLists",recoveryDetailsList1);
			if(pebPolicyBlockedFundBean==null)
			{
				model.addAttribute("pebPolicyBlockedFundBean",new PebPolicyBlockedFundBean());	
			}
			else
			{
				model.addAttribute("pebPolicyBlockedFundBean", pebPolicyBlockedFundBean);
			}
			if(pebPolicyWriteOffBean==null)
			{
				model.addAttribute("pebPolicyWriteOffBean",new PebPolicyWriteOffBean());	
			}
			else
			{
			model.addAttribute("pebPolicyWriteOffBean", pebPolicyWriteOffBean);
			}
			

		       return "recovery/recovery2";
		    }
	
		 // StringBuilder fileNames = new StringBuilder();\
		if(files.getOriginalFilename().equals("")){
			
		}
		else {
			System.out.println("helo"+pebPolicyRecoveryMasterBean1.getRecoveryDocument());
		StringBuilder fileNames = new StringBuilder();
		
			  Path fileNameAndPath = Paths.get(uploadDirectory, files.getOriginalFilename());
			 
			  fileNames.append(files.getOriginalFilename()+" ");
			  try {
				Files.write(fileNameAndPath, files.getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		  
		
		session.setAttribute("pebPolicyRecoveryMasterBean", pebPolicyRecoveryMasterBean1);
	
		 pebPolicyRecoveryMasterBean=pebPolicyRecoveryMasterBean1;
		
			session.setAttribute("PebPolicyRecoveryMasterBean", pebPolicyRecoveryMasterBean);

		 pebPolicyRecoveryMasterBean.setRecoveryDetailsList(recoveryDetailsList1);
		model.addAttribute("RecoveryMaster", "success");
		if(pebPolicyBlockedFundBean==null)
		{
			model.addAttribute("pebPolicyBlockedFundBean",new PebPolicyBlockedFundBean());	
		}
		else
		{
			model.addAttribute("pebPolicyBlockedFundBean", pebPolicyBlockedFundBean);
		}
		if(pebPolicyWriteOffBean==null)
		{
			model.addAttribute("pebPolicyWriteOffBean",new PebPolicyWriteOffBean());	
		}
		else
		{
		model.addAttribute("pebPolicyWriteOffBean", pebPolicyWriteOffBean);
		}
		
		if(pebPolicyRecoveryMasterBean==null)
		{
			model.addAttribute("pebPolicyRecoveryMasterBean",new PebPolicyRecoveryMasterBean());	
		}
		else
		{
		model.addAttribute("pebPolicyRecoveryMasterBean", pebPolicyRecoveryMasterBean);
		}
		model.addAttribute("pebPolicyRecoveryDetailsBean", new PebPolicyRecoveryDetailsBean());
		model.addAttribute("pebPolicyRecoveryDetailsBean1", new PebPolicyRecoveryDetailsBean());

		model.addAttribute("recoveryDetailsLists",recoveryMasterBean5.getRecoveryDetailsList());
		System.out.println(pebPolicyRecoveryMasterBean);
		return "recovery/recovery2";
		
	}
	@PostMapping(value = "/addBlockedFundDetails")
	public String addBlockedFundDetails(@Valid @ModelAttribute PebPolicyBlockedFundBean pebPolicyBlockedFundBean,BindingResult bindingResult,Model model,HttpSession session) {
		session.setAttribute("pebPolicyBlockedFundBean", pebPolicyBlockedFundBean);

		PebPolicyBlockedFundBean pebPolicyBlockedFundBean1=(PebPolicyBlockedFundBean)session.getAttribute("pebPolicyBlockedFundBean");
		PebPolicyWriteOffBean pebPolicyWriteOffBean=(PebPolicyWriteOffBean)session.getAttribute("pebPolicyWriteOffBean");
		PebPolicyRecoveryMasterBean pebPolicyRecoveryMasterBean=(PebPolicyRecoveryMasterBean)session.getAttribute("pebPolicyRecoveryMasterBean");
		session.setAttribute("activepage", "BlockedFundDetails");
		model.addAttribute("activepage",session.getAttribute("activepage"));
		//model.addAttribute("activepage","BlockedFundDetails");
		if (bindingResult.hasErrors()) {
			model.addAttribute("pebPolicyRecoveryDetailsBean1", new PebPolicyRecoveryDetailsBean());
			model.addAttribute("pebPolicyRecoveryDetailsBean", new PebPolicyRecoveryDetailsBean());

			model.addAttribute("recoveryDetailsLists",recoveryDetailsList1);
			if(pebPolicyBlockedFundBean1==null)
			{
				model.addAttribute("pebPolicyBlockedFundBean",new PebPolicyBlockedFundBean());	
			}
			else
			{
				model.addAttribute("pebPolicyBlockedFundBean", pebPolicyBlockedFundBean1);
			}
			if(pebPolicyWriteOffBean==null)
			{
				model.addAttribute("pebPolicyWriteOffBean",new PebPolicyWriteOffBean());	
			}
			else
			{
			model.addAttribute("pebPolicyWriteOffBean", pebPolicyWriteOffBean);
			}
			if(pebPolicyRecoveryMasterBean==null)
			{
				model.addAttribute("pebPolicyRecoveryMasterBean",new PebPolicyRecoveryMasterBean());	
			}
			else
			{
			model.addAttribute("pebPolicyRecoveryMasterBean", pebPolicyRecoveryMasterBean);
			}

		       return "recovery/recovery2";
		    }	
		if(pebPolicyBlockedFundBean1==null)
		{
			model.addAttribute("pebPolicyBlockedFundBean",new PebPolicyBlockedFundBean());	
		}
		else
		{
			model.addAttribute("pebPolicyBlockedFundBean", pebPolicyBlockedFundBean1);
		}
		if(pebPolicyWriteOffBean==null)
		{
			model.addAttribute("pebPolicyWriteOffBean",new PebPolicyWriteOffBean());	
		}
		else
		{
		model.addAttribute("pebPolicyWriteOffBean", pebPolicyWriteOffBean);
		}
		if(pebPolicyRecoveryMasterBean==null)
		{
			model.addAttribute("pebPolicyRecoveryMasterBean",new PebPolicyRecoveryMasterBean());	
		}
		else
		{
		model.addAttribute("pebPolicyRecoveryMasterBean", pebPolicyRecoveryMasterBean);
		}
		model.addAttribute("BlockedFundDetails","success");
		model.addAttribute("pebPolicyRecoveryDetailsBean", new PebPolicyRecoveryDetailsBean());
		model.addAttribute("pebPolicyRecoveryDetailsBean1", new PebPolicyRecoveryDetailsBean());
		model.addAttribute("recoveryDetailsLists",recoveryMasterBean5.getRecoveryDetailsList());

		System.out.println(pebPolicyBlockedFundBean);
		return "recovery/recovery2";
		
	}
	@PostMapping(value = "/addWriteOffDetails")
	public String addWriteOffDetails(@Valid @ModelAttribute PebPolicyWriteOffBean pebPolicyWriteOffBean,BindingResult bindingResult,Model model,HttpSession session) {

		session.setAttribute("activepage", "WriteOffDetails");
		model.addAttribute("activepage",session.getAttribute("activepage"));
		//model.addAttribute("activepage","WriteOffDetails");
		session.setAttribute("pebPolicyWriteOffBean", pebPolicyWriteOffBean);

		PebPolicyBlockedFundBean pebPolicyBlockedFundBean=(PebPolicyBlockedFundBean)session.getAttribute("pebPolicyBlockedFundBean");
		PebPolicyWriteOffBean pebPolicyWriteOffBean1=(PebPolicyWriteOffBean)session.getAttribute("pebPolicyWriteOffBean");
		PebPolicyRecoveryMasterBean pebPolicyRecoveryMasterBean=(PebPolicyRecoveryMasterBean)session.getAttribute("pebPolicyRecoveryMasterBean");
		if (bindingResult.hasErrors()) {
			model.addAttribute("pebPolicyRecoveryDetailsBean1", new PebPolicyRecoveryDetailsBean());
			model.addAttribute("pebPolicyRecoveryDetailsBean", new PebPolicyRecoveryDetailsBean());
			
			model.addAttribute("recoveryDetailsLists",recoveryDetailsList1);
			if(pebPolicyBlockedFundBean==null)
			{
				model.addAttribute("pebPolicyBlockedFundBean",new PebPolicyBlockedFundBean());	
			}
			else
			{
				model.addAttribute("pebPolicyBlockedFundBean", pebPolicyBlockedFundBean);
			}
			if(pebPolicyWriteOffBean1==null)
			{
				model.addAttribute("pebPolicyWriteOffBean",new PebPolicyWriteOffBean());	
			}
			else
			{
			model.addAttribute("pebPolicyWriteOffBean", pebPolicyWriteOffBean1);
			}
			if(pebPolicyRecoveryMasterBean==null)
			{
				model.addAttribute("pebPolicyRecoveryMasterBean",new PebPolicyRecoveryMasterBean());	
			}
			else
			{
			model.addAttribute("pebPolicyRecoveryMasterBean", pebPolicyRecoveryMasterBean);
			}

		       return "recovery/recovery2";
		    }	
		
		model.addAttribute("WriteOffDetails","success");
		if(pebPolicyBlockedFundBean==null)
		{
			model.addAttribute("pebPolicyBlockedFundBean",new PebPolicyBlockedFundBean());	
		}
		else
		{
			model.addAttribute("pebPolicyBlockedFundBean", pebPolicyBlockedFundBean);
		}
		if(pebPolicyWriteOffBean1==null)
		{
			model.addAttribute("pebPolicyWriteOffBean",new PebPolicyWriteOffBean());	
		}
		else
		{
		model.addAttribute("pebPolicyWriteOffBean", pebPolicyWriteOffBean1);
		}
		if(pebPolicyRecoveryMasterBean==null)
		{
			model.addAttribute("pebPolicyRecoveryMasterBean",new PebPolicyRecoveryMasterBean());	
		}
		else
		{
		model.addAttribute("pebPolicyRecoveryMasterBean", pebPolicyRecoveryMasterBean);
		}
		model.addAttribute("pebPolicyRecoveryDetailsBean", new PebPolicyRecoveryDetailsBean());

		model.addAttribute("pebPolicyRecoveryDetailsBean1", new PebPolicyRecoveryDetailsBean());
		model.addAttribute("recoveryDetailsLists",recoveryMasterBean5.getRecoveryDetailsList());

		System.out.println(pebPolicyWriteOffBean);
		
		return "recovery/recovery2";
		
	}
	@GetMapping(value = "/view/{id1}/{id}")
	@ResponseBody
	public PebPolicyRecoveryDetailsBean viewRecoverydetails(@PathVariable Integer id,Model model)
	{
		PebPolicyRecoveryDetailsBean pebPolicyRecoveryDetailsBean=recoveryDetailsList1.get(id-1);
	//	List<PebPolicyRecoveryDetailsBean> recoveryDetailsList=recoveryMasterBean1.getRecoveryDetailsList();
		//model.addAttribute("response",recoveryDetailsList1.get());
		Iterator<PebPolicyRecoveryDetailsBean> Iterator = recoveryDetailsList1.iterator();
        while (Iterator.hasNext()) {
        	recoveryDetailsBean4= Iterator.next();
       //	System.out.println(pebPolicyRecoveryDetailsBean);
       	//System.out.println(recoveryDetailsBean4);
        	if(recoveryDetailsBean4.getRecoveryInstance()==id)
        	{
        		
        		pebPolicyRecoveryDetailsBean=recoveryDetailsBean4;
        		break;
        	}
        }
        return pebPolicyRecoveryDetailsBean;

	}

	@GetMapping(value = "/edit/{id1}/{id}")
	@ResponseBody
	public PebPolicyRecoveryDetailsBean editRecoverydetails(@PathVariable Integer id,Model model)
	{
		model.addAttribute("showtable","showtable");


		PebPolicyRecoveryDetailsBean pebPolicyRecoveryDetailsBean=recoveryDetailsList1.get(id-1);
	//	List<PebPolicyRecoveryDetailsBean> recoveryDetailsList=recoveryMasterBean1.getRecoveryDetailsList();
		//model.addAttribute("response",recoveryDetailsList1.get());
		Iterator<PebPolicyRecoveryDetailsBean> Iterator = recoveryDetailsList1.iterator();
        while (Iterator.hasNext()) {
        	recoveryDetailsBean4= Iterator.next();
       //	System.out.println(pebPolicyRecoveryDetailsBean);
       	//System.out.println(recoveryDetailsBean4);
        	if(recoveryDetailsBean4.getRecoveryInstance()==id)
        	{
        		
        		pebPolicyRecoveryDetailsBean=recoveryDetailsBean4;
        		break;
        	}
        }
        
       //model.addAttribute("response",pebPolicyRecoveryDetailsBean);
       // System.out.println(pebPolicyRecoveryDetailsBean);
       // model.addAttribute("pebPolicyRecoveryDetailsBean",pebPolicyRecoveryDetailsBean);

		return pebPolicyRecoveryDetailsBean;
	}
	 @PostMapping(value = "/addfinalRecoveryDetailsList")
	 public String addfinalRecoveryDetailsList(@Valid @ModelAttribute PebPolicyWriteOffBean pebPolicyWriteOffBean,Model model,HttpSession session) {
		 id2++;
		
			pebPolicyRecoveryMasterBean1=(PebPolicyRecoveryMasterBean)session.getAttribute("pebPolicyRecoveryMasterBean");
			 pebPolicyRecoveryMasterBean1.setRecoveryId(id2);
			pebPolicyRecoveryMasterBean1.setPebPolicyWriteOffBean( (PebPolicyWriteOffBean)session.getAttribute("pebPolicyWriteOffBean"));
			pebPolicyRecoveryMasterBean1.setPebPolicyBlockedFundBean((PebPolicyBlockedFundBean)session.getAttribute("pebPolicyBlockedFundBean"));
			pebPolicyRecoveryMasterBeanList.add(pebPolicyRecoveryMasterBean1);
			model.addAttribute("recoveryList",pebPolicyRecoveryMasterBeanList);

		//	System.out.println("hii"+pebPolicyRecoveryMasterBean);
			return "recovery/recovery5";
	 }
	 @GetMapping(value = "/viewRecoverySystemEntryPage")
		public String viewRecoverySystemEntryPage(Model model,HttpSession session) 
		{
		
			PebPolicyBlockedFundBean pebPolicyBlockedFundBean=(PebPolicyBlockedFundBean)session.getAttribute("pebPolicyBlockedFundBean");
			PebPolicyWriteOffBean pebPolicyWriteOffBean=(PebPolicyWriteOffBean)session.getAttribute("pebPolicyWriteOffBean");
			PebPolicyRecoveryMasterBean pebPolicyRecoveryMasterBean=(PebPolicyRecoveryMasterBean)session.getAttribute("pebPolicyRecoveryMasterBean");
			if(pebPolicyBlockedFundBean==null)
			{
				model.addAttribute("pebPolicyBlockedFundBean",new PebPolicyBlockedFundBean());	
			}
			else
			{
				model.addAttribute("pebPolicyBlockedFundBean", pebPolicyBlockedFundBean);
			}
			if(pebPolicyWriteOffBean==null)
			{
				model.addAttribute("pebPolicyWriteOffBean",new PebPolicyWriteOffBean());	
			}
			else
			{
			model.addAttribute("pebPolicyWriteOffBean", pebPolicyWriteOffBean);
			}
			if(pebPolicyRecoveryMasterBean==null)
			{
				model.addAttribute("pebPolicyRecoveryMasterBean",new PebPolicyRecoveryMasterBean());	
			}
			else
			{
			model.addAttribute("pebPolicyRecoveryMasterBean", pebPolicyRecoveryMasterBean);
			}
		 System.out.println(pebPolicyRecoveryMasterBean1);
			return "recovery/recovery6";
		}
	 


	
}
