package com.seunghoshin.android.booktag_final;

import com.seunghoshin.android.booktag_final.domain.BookInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SeungHoShin on 2017. 7. 6..
 */

public class Data {
    //  공용으로 사용되는 데이터 저장소
    // 모든 Activity에서 접근할 수 있다 .
    public static List<BookInfo> list = new ArrayList<>();
}
