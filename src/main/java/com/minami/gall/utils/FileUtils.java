package com.minami.gall.utils;

import java.util.UUID;

public class FileUtils {
    public static String getExt(String fileNm) {
        return fileNm.substring(fileNm.lastIndexOf("."));
    }
    public static String makeRandomFileNm(String fileNm) {
        return UUID.randomUUID() + getExt(fileNm);
    }
}
