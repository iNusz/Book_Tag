package com.seunghoshin.android.booktag_final.domain;

/**
 * Created by SeungHoShin on 2017. 7. 6..
 */

/**
 * DB의 속성값 정의
 */
public class BookInfo {

    public String fileUriString; // 책 이미지
    public String title; // 책제목
    public String author; // 저자
    public String totalpage; // 전체페이지(마지막페이지..)
    public String currentpage; // 현재페이지
    public String date; // 시작일
    public String currentdate; // 시작일로부터 몇일지났는지

    public String content; // 마음가짐

    public String price; // 책가격
    public String buyplace; // 구매처

    public int booking; // % 책


    // 초기화
    public BookInfo() {

    }


    public BookInfo(String title, String author, String content) {

        this.title = title;
        this.author = author;
        this.content = content;
        this.totalpage = totalpage;
    }
}
