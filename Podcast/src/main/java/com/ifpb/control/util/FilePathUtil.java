package com.ifpb.control.util;

public class FilePathUtil {

    private static String filePathAudio;

    private static String getFilePathImagem;

    public static String getPathAudio(){
        return  filePathAudio;
    }

    public static void setPathAudio(String pathAudio){
        filePathAudio = pathAudio;
    }

    public static String getPathImagem(){
        return  getFilePathImagem;
    }

    public static void setPathImagem(String pathImagem){
        getFilePathImagem = pathImagem;
    }

}
