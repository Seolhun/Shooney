package com.shun.blog.service.common;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.shun.blog.model.common.Paging;
import com.shun.blog.model.menu.Menu;
import com.shun.blog.model.menu.MenuType;
import com.shun.blog.model.user.User;
import com.shun.blog.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
@Transactional(transactionManager = "txManager")
public class CommonServiceImpl implements CommonService {
    private static final Logger LOG = LoggerFactory.getLogger(CommonServiceImpl.class);

    private UserService userService;
    private JavaMailSender mailSender;

    @Autowired
    public CommonServiceImpl(UserService userService, JavaMailSender mailSender) {
        this.userService = userService;
        this.mailSender = mailSender;
    }

    private static final String FILE_PATH = "/Users/hunseol/Desktop/project/shooney/stack/";
//    private static final String FILE_PATH="/opt/tomcat/files/";

    /*
        (?=.*[0-9]) a digit must occur at least once
		(?=.*[a-z]) a lower case letter must occur at least once
		(?=.*[A-Z]) an upper case letter must occur at least once
		(?=.*[@#$%^&+=]) a special character must occur at least once
		(?=\\S+$) no whitespace allowed in the entire string
		.{8,} at least 8 characters
	*/


    @Override
    //Pattern에 틀리면 True || 맞으면 False
    public boolean validPattern(String parameter, String patternName) {
        boolean validation = false;
        switch (patternName) {
            case "password":
                String passwordPattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()_+=-~`]).{8,20}";
                validation = parameter.matches(passwordPattern);
            case "email":
                String emailPattern = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,3})$";
                validation = parameter.matches(emailPattern);
            case "id":
                String idPattern = "^[A-Za-z0-9].{1,20}";
                validation = parameter.matches(idPattern);
            case "name":
                String namePattern = ".[가-힣]{1,14}";
                validation = parameter.matches(namePattern);
            case "phone":
                String phonePattern = "\\d{10,11}";
                validation = parameter.matches(phonePattern);
            case "tel":
                String telPattern = "\\d{9,10}";
                validation = parameter.matches(telPattern);
        }
        LOG.info("return : {}", validation);
        return validation;
    }

    @Override
    public Long checkVDLong(String parameter, int default_value) {
        Long longValue;
        try {
            longValue = Long.parseLong(parameter);
        } catch (Exception e) {
            longValue = Integer.toUnsignedLong(default_value);
        }
        return longValue;
    }

    @Override
    public int checkVDInt(String parameter, int default_value) {
        int int_value;
        try {
            int_value = Integer.parseInt(parameter);
        } catch (Exception e) {
            int_value = default_value;
        }
        return int_value;
    }

    @Override
    public int checkVDInteger(Integer parameter, int default_value) {
        int int_value;
        try {
            int_value = parameter;
        } catch (Exception e) {
            int_value = default_value;
        }
        return int_value;
    }

    @Override
    public float checkVDFloat(String parameter, int default_value) {
        float int_value;
        try {
            int_value = Float.parseFloat(parameter);
        } catch (Exception e) {
            int_value = default_value;
        }
        return int_value;
    }


    @Override
    public String checkVDQuestion(String question) {
        String question_text;
        try {
            if (question.length() > 0) {
                question_text = question.replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", "")
                        .replaceAll("[^\uAC00-\uD7A3xfe0-9a-zA-Z\\s]", "%").trim();
            } else {
                question_text = null;
            }
        } catch (Exception e) {
            question_text = null;
        }
        return question_text;
    }

    @Override
    public Timestamp convertDateToday(String formatYouWnat) throws Exception {
        SimpleDateFormat format = new SimpleDateFormat(formatYouWnat);
        Date today = new Date();
        String fromDateStr = format.format(today);
        today = format.parse(fromDateStr);
        Timestamp fromStamp = new java.sql.Timestamp(today.getTime());
        LOG.info("return : convertDateToday {}", fromStamp);
        return fromStamp;
    }

    @Override
    public Timestamp convertDateFormat(String formatYouWnat, int day) throws Exception {
        SimpleDateFormat format = new SimpleDateFormat(formatYouWnat);
        Date today = new Date();
        Date toDate = new Date(today.getTime() + ((1000 * 60 * 60 * 24 * day)));
        String toDateStr = format.format(toDate);
        toDate = format.parse(toDateStr);
        Timestamp toStamp = new java.sql.Timestamp(toDate.getTime());
        LOG.info("return : convertDateFormat {}", toStamp);
        return toStamp;
    }

    @Override
    public ObjectMapper setJSONMapper() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper(); // create once, reuse. Thank
        // Mapping에 실패했을 때도 그냥 실행할 수 있게 하기.
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return mapper;
    }

    @Override
    public String getJSONData(Object rawData) throws JsonProcessingException {
        // 담을 JSON 매핑
        ObjectMapper mapper = new ObjectMapper();
        // Null일 때, Null값을 가져오지 마세요.
        mapper.setSerializationInclusion(Include.NON_NULL);
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        // 추출한 자료를 JSON으로 매핑하기
        return mapper.writeValueAsString(rawData);
    }

    @Override
    public String convertMapToVo(ObjectMapper mapper, Object object) throws Exception {
        //		Patient patient2 = mapper.readValue(objectJson, Patient.class);
        return mapper.writeValueAsString(object);
    }

    @Override
    public Map<?, ?> convertVoToMap(Object object) throws Exception {
        ObjectMapper mapper = setJSONMapper();
        return mapper.convertValue(object, Map.class);
    }

    @Override
    public String getUserIP(HttpServletRequest request) {
//		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String ip = request.getHeader("X-FORWARDED-FOR");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    @Override
    public String buildSHA256(String str) {
        try {
            MessageDigest sh = MessageDigest.getInstance("SHA-256");
            sh.update(str.getBytes());
            byte byteData[] = sh.digest();
            StringBuilder sb = new StringBuilder();
            for (byte aByteData : byteData) {
                sb.append(Integer.toString((aByteData & 0xff) + 0x100, 16).substring(1));
            }
            str = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            str = null;
        }
        return str;
    }

    @Override
    public boolean getLoginAuthValidation(Authentication auth, String authNameYouWant) {
        boolean valid = false;
        try {
            for (GrantedAuthority authority : auth.getAuthorities()) {
                LOG.info("return : getLoginAuthValidation = " + authority.getAuthority());
                String authName = authority.getAuthority();
                if (authName.equals(authNameYouWant)) {
                    valid = true;
                }
            }
        } catch (NullPointerException e) {
            LOG.error("Error NullPointException - getLoginAuthValidation = " + valid);
        }
        return valid;
    }

    @Override
    public void sendEmailLockingUser(String toEmail, String userName, String authentication, String httpPath, String password) throws IOException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss", Locale.KOREA);
        Date date = new Date();
        String currentTime = formatter.format(date);
        String from = "imedisyndev@gmail.com";
        String mailSubject = "안녕하세요. (주)Hi-Cord입니다. " + userName + "의 계정이 5회 로그인 실패로 잠금설정 되었습니다.";
        String mailContent =
                userName + "님 안녕하세요." + " (주)Hi-Cord입니다."
                        + "<br>회원님의 아이디에 누군가가 " + currentTime + "에 로그인 시도를 하여, 5회 이상 실패로 계정이 잠금처리 되었습니다."
                        + "<br>먼저, 비밀번호를 바꾸시길 요청드립니다. 밑의 비밀번호 변경 버튼을 눌러 1회용 Password를 입력하여 비밀번호 변경해주시기 바랍니다."
                        + "<h4>Password : " + password + "</h4>"
                        + "<br><a href=" + httpPath + "?key=" + authentication + "><button>비밀번호 변경</button></a>"
                        + "<br>추가로 로그인 시도된 정보를 제공해드리오니, 확인하시고 궁금하신 것이 있으시면 문의부탁드립니다.";
        mainSendMail(toEmail, from, mailSubject, mailContent);
    }

    private void mainSendMail(String toEmail, String from, String mailSubject, String mailContent) {
        new Thread(() -> {
            String emailRegex = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
            if (toEmail.matches(emailRegex)) {
                try {
                    MimeMessage message = mailSender.createMimeMessage();
                    // true로서 멀티파트 메세지라는 의미
                    MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
                    messageHelper.setFrom(from);
                    messageHelper.setTo(toEmail);
                    messageHelper.setSubject(mailSubject);
                    messageHelper.setText(
                            "<html>"
                                    + "<body>"
                                    + "<div style='text-align : left; font-color:black; font-size : 14px;'>"
                                    + mailContent
                                    + "</div>"
                                    + "</body>"
                                    + "</html>", true);
                    // 파일첨부하기 하지만, Url위치가 틀려서 파일을 찾을 수 없다고 에러가 발생...수정 요망
                    // FileSystemResource fileImage=new
                    // FileSystemResource("/resources/img/google.png");
                    // messageHelper.addAttachment("Google Png", fileImage);

                    // 로고 넣기
                    // ClassPathResource image=new
                    // ClassPathResource("/resources/img/google.png");
                    // messageHelper.addInline("Google_Logo", image);
                    mailSender.send(message);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public String mainSendMail(String toEmail, String from, String mailSubject, String subTitle, String mailContent) throws Exception {
        if (toEmail == null) {
            return "fail";
        }
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        Runnable runnable = () -> {
            String emailregex = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,3})$";
            String mailBody = "";
            mailBody += "<html>"
                    + "    <body>"
                    + "        <table style='width:60%; margin:0 auto; text-align:center; border:1px solid #000; margin-top:30px;'>"
                    + "            <tbody>"
                    + "                <tr>"
                    + "                    <td style='padding-top:30px;'>"
                    + "                        <img src='http://imedisyn.com/resources/assets/img/logo.png' style='height:70px; margin-top:30px; margin-bottom:10px; '></img>"
                    + "                        <hr style='width:70%; border-color:#000;'>"
                    + "                    </td>"
                    + "                </tr>"
                    + "                <tr>"
                    + "                    <td>"
                    + "                        <h2 style='font-weight:400;'>" + subTitle + "</h2>"
                    + "                    </td>"
                    + "                </tr>"
                    + "                <tr>"
                    + "                    <td>"
                    + "                        <p style='width:60%; margin:0 auto; background-color:#efefef; padding:30px;'>"
                    + "                            " + mailContent
                    + "                        </p>"
                    + "                    </td>"
                    + "                </tr>"
                    + "                <tr>"
                    + "                    <td>"
                    + "                        <hr style='width:70%; border-color:#000;'>"
                    + "                        <p style='font-size:8pt; color:#a2a2a2; margin-bottom:30px;'><br><br>"
                    + "                            본 메일은 발신 전용으로 회신되지 않습니다.<br>"
                    + "                            관련 문의사항은 hi-cord.com 고객센터를 이용하시기 바랍니다."
                    + "                        </p>"
                    + "                    </td>"
                    + "                </tr>"
                    + "                <tr>"
                    + "                    <td>"
                    + "                        <p style='background-color:#efefef; min-height:10px; color:#444; padding:20px; margin:0;'>"
                    + "                            <strong>Tel.</strong> 02-747-7422 &nbsp;&nbsp;&nbsp;<strong>Mail.</strong> imedisyn@imedisyn.com &nbsp;&nbsp;&nbsp;<strong>Homepage.</strong> www.imedisyn.com"
                    + "                        </p>"
                    + "                    </td>"
                    + "                </tr>"
                    + "            </tbody>"
                    + "        </table>"
                    + "    </body>"
                    + "</html>";

            if (toEmail.matches(emailregex)) {
                try {
                    MimeMessage message = mailSender.createMimeMessage();
                    // true로서 멀티파트 메세지라는 의미
                    MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
                    messageHelper.setFrom(from);
                    messageHelper.setTo(toEmail);
                    messageHelper.setSubject(mailSubject);
                    messageHelper.setText(mailBody, true);
                    // 파일첨부하기 하지만, Url위치가 틀려서 파일을 찾을 수 없다고 에러가 발생...수정 요망
                    // FileSystemResource fileImage=new
                    // FileSystemResource("/resources/img/google.png");
                    // messageHelper.addAttachment("Google Png", fileImage);

                    // 로고 넣기
                    // ClassPathResource image=new
                    // ClassPathResource("/resources/img/google.png");
                    // messageHelper.addInline("Google_Logo", image);
                    mailSender.send(message);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        executorService.execute(runnable);
        executorService.shutdown();
        return "success";
    }

    @Override
    public void validCheckAndSendError(MessageSource messageSource, BindingResult bindingResult, HttpServletRequest request, String inputValue, String objectName, String fieldName, String messagePropertyName) {
        try {
            FieldError error = new FieldError(objectName, fieldName, messageSource.getMessage(messagePropertyName, new String[]{inputValue}, request.getLocale()));
            bindingResult.addError(error);
        } catch (Exception e) {
            FieldError error = new FieldError(objectName, fieldName, messageSource.getMessage(messagePropertyName, new String[]{inputValue}, Locale.getDefault()));
            bindingResult.addError(error);
        }

    }

    // 파라미터 호출 및 유효성 검사
    @Override
    public Paging beforeGetPaging(HttpServletRequest request) {
        int currentPage = checkVDInt(request.getParameter("cPage"), 1);
        int searchType = checkVDInt(request.getParameter("sType"), 0);
        String searchText = checkVDQuestion(request.getParameter("sText"));
        int searchDate = checkVDInt(request.getParameter("sDate"), 0);
        int limit = checkVDInt(request.getParameter("limit"), 20);
        return new Paging(currentPage, searchType, searchText, searchDate, limit);
    }

    // 파라미터 호출 및 유효성 검사
    @Override
    public Paging beforePostPaging(Paging paging) {
        int currentPage = checkVDInteger(paging.getCurrentPage(), 1);
        int searchType = checkVDInteger(paging.getSearchType(), 0);
        String searchText = checkVDQuestion(paging.getSearchText());
        int searchDate = checkVDInteger(paging.getSearchDate(), 0);
        int limit = checkVDInteger(paging.getLimit(), 10);
        paging.setCurrentPage(currentPage);
        paging.setSearchType(searchType);
        paging.setSearchText(searchText);
        paging.setSearchDate(searchDate);
        paging.setLimit(limit);
        return paging;
    }

    @Override
    public void setAndValidationPaging(Paging paging) {
        int blockLimit = 10; // 페이지 당 보여줄 블록 번호 limit
        // [1],[2],[3],[4],[5],[6],[7],[8],[9],[10]
        int totalCount = paging.getTotalCount();
        int limit = paging.getLimit();
        int cPage = paging.getCurrentPage();
        int sType = paging.getSearchType();
        String sText = paging.getSearchText();
        int sDate = paging.getSearchDate();

        int totalPage = (int) Math.ceil(totalCount / limit);
        if (totalPage == 0) {
            totalPage = 1;
        }

        int maxCount = cPage * limit;
        int totalBlock = totalCount / limit + (totalCount % limit > 0 ? 1 : 0); // 전체
        int currentBlock = cPage / blockLimit + (cPage % blockLimit > 0 ? 1 : 0);// 현재
        int blockEndNum = currentBlock * blockLimit;
        int blockStartNum = blockEndNum - (blockLimit - 1);

        if (blockEndNum > totalBlock) {
            blockEndNum = totalBlock;
        }

        int previousPage = blockStartNum - blockLimit; // << *[이전]*
        int nextPage = blockStartNum + blockLimit; // *[다음]* >>

        if (previousPage < 1) {
            previousPage = 1;
        }

        if (nextPage > totalBlock) {
            nextPage = totalBlock / blockLimit * blockLimit + 1;
        }

        paging.setMaxCount(maxCount);
        paging.setTotalPage(totalPage);
        paging.setSearchType(sType);
        paging.setSearchDate(sDate);
        paging.setSearchText(sText);
        paging.setCurrentPage(cPage);
        paging.setBlockLimit(blockLimit);
        paging.setCurrentBlock(currentBlock);
        paging.setTotalBlock(totalBlock);
        paging.setBlockEndNum(blockEndNum);
        paging.setBlockStartNum(blockStartNum);
        paging.setNextPage(nextPage);
        paging.setPreviousPage(previousPage);
    }

    /**
     * 로그인 된 인원 DB정보 가쟈오기.
     * <p>
     * param String userEmail
     * return User user
     * throws Exception
     */
    @Override
    public User getAccessUserToModel() {
        String userEmail = getPrincipal();
        LOG.info("return : getAccessUserToModel : {}", userEmail);
        return userService.selectByEmail(userEmail);
    }

    @Override
    public String getPrincipal() {
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        try {
            if (principal instanceof UserDetails) {
                userName = ((UserDetails) principal).getUsername();
            }
            userName = principal.toString();
        } catch (NullPointerException e ){
            e.printStackTrace();
        }
        return userName;
    }

    /**
     * Menu 역할 확인하여 설정잡기.
     * <p>
     * param Menu menu
     * return Menu menu
     * throws Exception
     */
    @Override
    public Menu setMenuConfig(HttpServletRequest request) {
        Menu menu = new Menu(1);
        String uri = request.getRequestURI();
        //0="" || 1="shooney" || 2="admin or blog"
        String[] uriList = uri.split("/");
        try {
            if (uriList[2].equals("admin")) {
                menu.setMenuType(MenuType.ADMIN.getType());
            } else {
                menu.setMenuType(MenuType.NORMAL.getType());
            }
        } catch (Exception e) {
            menu.setMenuType(MenuType.NORMAL.getType());
        }
        return menu;
    }

    /**
     * Get from Img using Jsoup
     * <p>
     * param Img SRc
     * return
     * throws IOException
     */
    @Override
    public String getImgUsingJsoup(String imgSrc, String savedDirectoryName) throws IOException, StringIndexOutOfBoundsException {
        int indexName = imgSrc.lastIndexOf("/");
        if (indexName == imgSrc.length())
            imgSrc = imgSrc.substring(1, indexName);
        indexName = imgSrc.lastIndexOf("/");
        String savedName = imgSrc.substring(indexName, imgSrc.length());

        savedDirectoryName = savedDirectoryName.toLowerCase();
        savedName = savedName.toLowerCase();

        File directory = new File(FILE_PATH + savedDirectoryName);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        URL url = new URL(imgSrc);
        InputStream in = url.openStream();
        OutputStream out = new BufferedOutputStream(new FileOutputStream(FILE_PATH + savedDirectoryName + savedName));

        for (int b; (b = in.read()) != -1; ) {
            out.write(b);
        }
        out.close();
        in.close();
        return FILE_PATH + savedDirectoryName + savedName;
    }

    @Override
    public JsonObject getResponseAPI(String apiUrl) throws IOException {
        HttpURLConnection conn = null;
        JsonParser parser = new JsonParser();
        JsonObject jsonObject = new JsonObject();
        try {
            URL url = new URL(apiUrl);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            }

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader((conn.getInputStream())));

            //String output;
            String jsonStr = null;
            while ((jsonStr = bufferedReader.readLine()) != null) {
                jsonObject = parser.parse(jsonStr).getAsJsonObject();
                LOG.info("return API output : {}", jsonObject);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }
        return jsonObject;
    }

    @Override
    public Paging lastestSetPaging(int totalCount, int currentPage, int blockLimit) throws NullPointerException {
        Paging paging = new Paging();
        //Default Value = 30
        int limit = 30;

        //1. setting Total Page
        int totalPage = (int)(Math.ceil((double)totalCount / limit));
        if (totalCount <= limit) {
            totalPage = 1;
        }

        //2. setting CurerntPage
        if(currentPage >= totalPage){
            currentPage = totalPage;
        } else if(currentPage < 1){
            currentPage = 1;
        }

        //3. setting Total Block
        Integer totalBlock = 1;
        if (totalPage > blockLimit) {
            totalBlock = (int)(Math.ceil((double)totalPage / blockLimit));
        }

        //4. setting Paging Num
        int startNumInCurrent;
        //1, 11, 21, 31 ...
        startNumInCurrent = currentPage <= blockLimit ? (currentPage <= totalPage ? 1 : totalPage) : (int)(Math.floor((double)currentPage-1/blockLimit))*blockLimit+1;
        int lastNumInCurrent;
        //10, 20, 30, 40 ...
        lastNumInCurrent = totalPage <= blockLimit ? totalPage : (int)(Math.ceil((double)currentPage/blockLimit))*blockLimit >= totalPage? totalPage : (int)(Math.ceil((double)currentPage/blockLimit))*blockLimit;

        paging.setTotalCount(totalCount);
        paging.setCurrentPage(currentPage);
        paging.setTotalPage(totalPage);
        paging.setTotalBlock(totalBlock);
        paging.setBlockLimit(blockLimit);
        paging.setStartNumInCurrent(startNumInCurrent);
        paging.setLastNumInCurrent(lastNumInCurrent);
        return paging;
    }
}
