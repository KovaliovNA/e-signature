package com.signature.esignature.service.Impl;

import com.signature.esignature.service.CustomSignature;
import com.signature.esignature.service.ResourceService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.*;

@Service
public class SignatureImpl implements CustomSignature {

    private ResourceService resourceService;

    @Setter
    private PublicKey publicKey;
    private PrivateKey privateKey;
    private Signature signature;

    public SignatureImpl() throws NoSuchProviderException, NoSuchAlgorithmException {
        signature = Signature.getInstance("SHA1withDSA", "SUN");
    }

    @Override
    public void signMessage(byte[] message, String signaturePath)
            throws InvalidKeyException, SignatureException, NoSuchProviderException,
            NoSuchAlgorithmException, IOException {

        generateKeys();

        signature.initSign(privateKey);
        signature.update(message);

        resourceService.saveKey(signaturePath, publicKey);
        resourceService.saveSignature(signaturePath, signature.sign());
    }

    @Override
    public boolean verifySignature(byte[] messagePath, byte[] keyPath, byte[] signaturePath)
            throws InvalidKeyException, SignatureException, IOException, ClassNotFoundException {

        signature.initVerify((PublicKey) resourceService.deserialize(keyPath));
        signature.update(messagePath);

        return signature.verify(signaturePath);
    }

    private void generateKeys() throws NoSuchProviderException, NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("DSA", "SUN");
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN");
        keyPairGenerator.initialize(512, random);
        KeyPair keyPair = keyPairGenerator.genKeyPair();
        privateKey = keyPair.getPrivate();
        publicKey = keyPair.getPublic();
    }

    @Autowired
    public void setResourceService(ResourceService resourceService) {
        this.resourceService = resourceService;
    }
}
