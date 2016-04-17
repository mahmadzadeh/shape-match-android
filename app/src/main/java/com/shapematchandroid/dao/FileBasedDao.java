package com.shapematchandroid.dao;

import com.shapematchandroid.io.FileIO;

import java.util.List;

public class FileBasedDao implements Dao {

    private FileIO fileIO;

    public FileBasedDao(FileIO fileIO) {
        this.fileIO = fileIO;
    }

    @Override
    public List<DataPoint> read() {
        return null;
    }

    @Override
    public void write(List<DataPoint> dataPoints) {
//
//        FileOutputStream stream = new FileOutputStream(file);
//        try {
//            stream.write("text-to-write".getBytes());
//        } finally {
//            stream.close();
//        }
    }
}
