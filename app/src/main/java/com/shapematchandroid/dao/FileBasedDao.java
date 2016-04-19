package com.shapematchandroid.dao;

import android.util.Log;

import com.shapematchandroid.io.FileIO;
import com.shapematchandroid.io.FileIOException;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import static com.shapematchandroid.JSONUtil.parse;

public class FileBasedDao implements Dao {

    private FileIO fileIO;

    public FileBasedDao(FileIO fileIO) {
        this.fileIO = fileIO;
    }

    @Override
    public DataDto read() {
        List<DataPoint> dataPoints = new ArrayList<>();

        try {

            dataPoints.addAll(parse(fileIO.read()));

        } catch (FileIOException e) {
            Log.e("PARSE_ERROR", e.getMessage());
        } catch (JSONException e) {
            Log.e("JSON_ERROR", e.getMessage());
        }

        return new DataDto(dataPoints);
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
