package com.example.omr.Utils;

import com.example.omr.Modals.SharedPrefManager;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class UtilityFunctions {

    public static List<Character> ConvertStringtoList(String string, int questioncount) {
        StringTokenizer st = new StringTokenizer(string, ",");
        List<Character> yourStringList = new ArrayList<Character>(questioncount);
        for(int i =0 ;i< questioncount; i++) yourStringList.add('A');
        for (int i = 0; i < questioncount; i++) {
            String token = st.nextToken();
            int lastIndex = token.length() - 1;
            yourStringList.set(i, token.charAt(lastIndex));
        }

        return yourStringList;
    }

    public static String ConvertListtoString(List<Character> list, int questioncount) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < questioncount; i++) {
            stringBuilder.append(i+1).append(list.get(i)).append(",");
        }
        return stringBuilder.toString();

    }




}
