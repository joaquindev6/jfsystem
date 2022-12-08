package com.company.app.util;

public class TextFormatString {

    //Agrega mayuscula en la primera letra y minuscula en las demas, sirven para los nombres
    public static String formatTextData(String data) {
        String[] datas = data.split(" ");
        String dataFormat = "";
        for (String d: datas) {
            dataFormat = dataFormat + d.substring(0, 1).toUpperCase() + d.substring(1).toLowerCase() + " ";
        }
        return dataFormat.trim();
    }
}
