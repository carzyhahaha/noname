package com.noname.vo;

import com.noname.entity.Article;
import com.noname.util.DateUtils;

public class ArticleVO extends Article {

    public String getCreateDateStr(){
        if(getCreateDate()!=null){
            return DateUtils.format(getCreateDate());
        }
        return null;
    }
}
