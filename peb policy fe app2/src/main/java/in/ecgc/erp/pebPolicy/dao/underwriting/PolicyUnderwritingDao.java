package in.ecgc.erp.pebPolicy.dao.underwriting;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import in.ecgc.erp.pebPolicy.model.underwriting.DummyPolicyDetailsBean;



@Repository
public class PolicyUnderwritingDao {
	
private static List<DummyPolicyDetailsBean> DB = null;
	
	@Autowired
	private ObjectMapper mapper;
	
	InputStream inputStream = null;
	
	@PostConstruct
	public void mockAssessment() {
		try {
			inputStream = new FileInputStream("C:/Users/eeee/Downloads/peb policy fe app/pebpolicyfeapplication11/src/main/resources/static/mockdata/mockAssessment.json");
			
			DB =  mapper.readValue(inputStream, new TypeReference<List<DummyPolicyDetailsBean>>() {});
		}catch(IOException ex) {
			System.out.println(ex.getMessage());
		}finally {
			try {
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	

	public int insertAssessment(DummyPolicyDetailsBean dummy) {
		DB.add(dummy);
		try {
			mapper.writeValue(new File("C:/Users/eeee/Downloads/peb policy fe app/pebpolicyfeapplication11/src/main/resources/static/mockdata/mockAssessment.json"), DB);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 1;
	}

	public List<DummyPolicyDetailsBean> selectAllDummyPolicy() {
		return DB;
	}


	public DummyPolicyDetailsBean getDummyPolicyByDummyPolicyNumber(Long dummyPolicyNumber) {
		return DB.stream().filter(d -> d.getDummyPolicyNumber() == dummyPolicyNumber).findFirst().get();
	}


	public void updateAssessment(DummyPolicyDetailsBean dummy, Long dummyPolicyNumber) {
		int counter = 0;
		for(DummyPolicyDetailsBean d : DB) {
			if(d.getDummyPolicyNumber() == dummyPolicyNumber) {
				DB.set(counter, dummy);
			}
			counter++;
		}
		try {
			//mapper.writeValue(new File("D:/STS_Projects/PebPolicyTest/src/main/resources/static/mockdata/mockAssessment.json"), DB);
			mapper.writeValue(new File("C:/Users/eeee/Downloads/peb policy fe app/pebpolicyfeapplication11/src/main/resources/static/mockdata/mockAssessment.json"), DB);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}



}
