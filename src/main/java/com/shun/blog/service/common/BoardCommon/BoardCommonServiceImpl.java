package com.shun.blog.service.common.BoardCommon;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Transactional(transactionManager = "txManager")
public class BoardCommonServiceImpl implements BoardCommonService {
    // content에서 img 태그의 src값만 추출
    @Override
    public List<String> extractImgSrc(String content) {

        List<String> result = new ArrayList<String>();
        ArrayList<String> files = new ArrayList<>();

        Pattern nonValidPattern = Pattern.compile("<img[^>]*src=[\"']?([^>\"']+)[\"']?[^>]*>");
        Matcher matcher = nonValidPattern.matcher(content);

        while (matcher.find()) {
            result.add(matcher.group(1));
        }

        for (int i = 0; i < result.size(); i++) {
            String imgUrl = result.get(i);
            imgUrl = imgUrl.replaceAll(":", "/");
            imgUrl = imgUrl.substring(19);

            files.add(imgUrl);
        }

        return files;
    }

    // html tag를 모두 제거함
    public String removeTags(String content) {
        String noTags = content.replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", "");
        return noTags;
    }


}
