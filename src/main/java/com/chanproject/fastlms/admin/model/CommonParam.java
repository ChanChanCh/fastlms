package com.chanproject.fastlms.admin.model;

import lombok.Data;

// MemberParam과 CourseParam 공통영역 정리해둠

@Data
public class CommonParam {

    long pageIndex;
    long pageSize;

    String searchType;
    String searchValue;

    public long getPageStart(){
        init();
        return(pageIndex - 1) * pageSize;
    }

    public long getPageEnd(){
        init();
        return pageSize;
    }

    public void init(){
        if(pageIndex < 1){
            pageIndex = 1;
        }

        if(pageSize < 10){
            pageSize = 10;
        }

    }

    public String getQueryString(){

        init();

        StringBuilder sb = new StringBuilder();

        if(searchType != null && searchType.length() > 0) {
            sb.append(String.format("searchType=%s", searchType));
        }

        if(searchType != null && searchType.length() > 0) {
           if(sb.length() > 0){
               sb.append("&");
           }
           sb.append(String.format("searchValue=%s", searchValue));
        }

        return sb.toString();

    }

}
