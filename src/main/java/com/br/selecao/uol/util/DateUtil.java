package com.br.selecao.uol.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    public static String formatDate(Date date, String formato) {
        SimpleDateFormat f = new SimpleDateFormat(formato);
        return f.format(date);
    }
}
