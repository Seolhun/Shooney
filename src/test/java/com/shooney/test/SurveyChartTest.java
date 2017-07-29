//package com.shooney.test;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import org.apache.log4j.Logger;
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.web.WebAppConfiguration;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@WebAppConfiguration
//@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/config/action-servlet.xml", "classpath*:config/spring/context-*.xml" })
//public class SurveyChartTest {
//	private static final Logger log = Logger.getLogger(SurveyChartTest.class);
//
//	@Autowired
//	private SurveyService surveyService;
//	@Autowired
//	private HospitalService hospitalService;
//
//
////	@Before
////	public void setup() {
////		// Create ServiceObject and save to DB
////	}
//
//	@Test
//	public void selectHospitalDetail() throws Exception {
//		log.info("param : selectSurveyListNonCache");
//		Map<String, Object> paramMap = new HashMap<>();
//		paramMap.put("sessionEmail", "hyunyoul1004@imedisyn.com");
//		paramMap.put("sessionUserName", "관리자");
//		paramMap.put("sessionHospitalIdx", "104-86-46675");
//		paramMap.put("sessionHospitalNm", "아이메디신");
//		paramMap.put("surveyBindingId", 34);
//
//		Survey survey = new Survey();
//		survey.setType(1);
//		for (int i = 0; i < 100; i++) {
//			Map<String, Object> hospitalDetail = hospitalService.hospitalDetail(paramMap); //병원 정보 조회
//			Assert.assertSame(hospitalDetail, hospitalDetail);
//		}
//	}
//
//
////	@After
////	public void teardown() {
////		// Delete created ServiceObject from DB
////	}
//}
