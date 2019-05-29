package com.example.giflibrary;

public class GifInfoHandle {
 //调用native
    private native volatile ptr;
    private native long openFile(String path);
}
