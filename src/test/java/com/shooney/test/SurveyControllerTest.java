//package com.shooney.test;
//
//import static org.hamcrest.CoreMatchers.instanceOf;
//import static org.hamcrest.CoreMatchers.is;
//import static org.junit.Assert.assertThat;
//
//import org.apache.log4j.Logger;
//import org.junit.After;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.web.WebAppConfiguration;
//
//import tunner.survey.model.Survey;
//import tunner.survey.service.SurveyService;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@WebAppConfiguration
//@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/config/action-servlet.xml", "classpath*:config/spring/context-*.xml" })
//public class SurveyControllerTest {
//	private static final Logger log = Logger.getLogger(SurveyControllerTest.class);
//
//	@Autowired
//	private SurveyService surveyService;
//
//	@Before
//	public void setup() {
//		// Create ServiceObject and save to DB
//	}
//
//	@Test
//	public void selectSurveyListNonCache() throws Exception {
//		log.info("param : selectSurveyListNonCache");
//		assertThat(surveyService, instanceOf(SurveyService.class));
//
//		Survey survey = new Survey();
//		survey.setType(1);
//		for (int i = 0; i < 100; i++) {
//			Assert.assertThat(surveyService.selectSurveyList(survey), is(surveyService.selectSurveyList(survey)));
//		}
//	}
//
//	@Test
//	public void selectSurveyListCache() throws Exception {
//		log.info("param : selectSurveyListCache");
//		assertThat(surveyService, instanceOf(SurveyService.class));
//
//		Survey survey = new Survey();
//		survey.setType(1);
//		for (int i = 0; i < 100; i++) {
//			Assert.assertThat(surveyService.selectSurveyList(survey), is(surveyService.selectSurveyList(survey)));
//		}
//	}
//	
//	@After
//	public void teardown() {
//		// Delete created ServiceObject from DB
//	}
//}
