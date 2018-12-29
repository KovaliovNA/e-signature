package com.signature.esignature.service;

import java.io.IOException;

public interface ResourceService {
    void saveKey(final String filePath, final Object key) throws IOException;
    void saveSignature(final String path, final byte[] signature) throws IOException;
    Object deserialize(byte[] data) throws IOException, ClassNotFoundException;
}
