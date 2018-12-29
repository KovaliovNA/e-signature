package com.signature.esignature.service;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SignatureException;

public interface CustomSignature {
    void signMessage(byte[] message, final String path)
            throws NoSuchProviderException, NoSuchAlgorithmException, InvalidKeyException, IOException, SignatureException;

    boolean verifySignature(final byte[] messagePath, final byte[] keyPath, final byte[] signaturePath)
            throws InvalidKeyException, SignatureException, IOException, ClassNotFoundException;
}
