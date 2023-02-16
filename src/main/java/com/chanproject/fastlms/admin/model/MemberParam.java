package com.chanproject.fastlms.admin.model;

import lombok.Data;

@Data
public class MemberParam {

    long pageIndex;
    long pageSize;

    String searchType;
    String searchValue;


    /*
    limit 0, 10  --> pageIndex: 1
    limit 10, 10 --> pageIndex: 2
    limit 20, 10 --> pageIndex: 3
    limit 30, 10 --> pageIndex: 4
     */

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



}
