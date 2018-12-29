package com.signature.esignature.service.Impl;

import com.signature.esignature.service.ResourceService;
import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class ResourceServiceImpl implements ResourceService {
    @Override
    public void saveKey(String filePath, Object key) throws IOException {
        filePath += "-key.txt";
        FileOutputStream fos = new FileOutputStream(filePath);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(key);
        oos.close();
        fos.close();
    }

    @Override
    public void saveSignature(String path, byte[] signature) throws IOException {
        path += "-sign.txt";
        FileOutputStream fos = new FileOutputStream(path);
        fos.write(signature);
        fos.close();
    }

    public Object deserialize(byte[] data) throws IOException, ClassNotFoundException {
        ByteArrayInputStream in = new ByteArrayInputStream(data);
        ObjectInputStream is = new ObjectInputStream(in);
        return is.readObject();
    }
}
