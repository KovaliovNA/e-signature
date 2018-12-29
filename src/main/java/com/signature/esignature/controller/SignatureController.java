package com.signature.esignature.controller;

import com.signature.esignature.service.Impl.SignatureImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SignatureException;
import java.util.Objects;

@Controller
@Slf4j
public class SignatureController {

    @Autowired
    private SignatureImpl signatureImpl;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/verify")
    public String verify(@RequestParam("signature") MultipartFile signature,
                         @RequestParam("publicKey") MultipartFile publicKey,
                         @RequestParam("file") MultipartFile file,
                         Model model) throws SignatureException, InvalidKeyException {

        try {
            model.addAttribute("isOriginalFile",
                    signatureImpl.verifySignature(file.getBytes(), publicKey.getBytes(), signature.getBytes()));
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Failed verification: \t" + e);
        }
        return "index";
    }

    @PostMapping("/sign")
    public String sign(@RequestParam("file") MultipartFile file, Model model)
            throws NoSuchProviderException, SignatureException, NoSuchAlgorithmException, InvalidKeyException {

        if (file == null) {
            model.addAttribute("error", "Please select a file to upload");
            return "index";
        }

        try {
            String absolutePath = new File(Objects.requireNonNull(file.getOriginalFilename())).getAbsolutePath();
            String path = absolutePath.substring(0, StringUtils.lastIndexOf(absolutePath, "."));

            signatureImpl.signMessage(file.getBytes(), path);

            model.addAttribute("message", getSuccessSignMessage(file));

        } catch (IOException e) {
            System.out.println("Failed sigin of the file: \t" + e);
        }

        return "index";
    }

    private String getSuccessSignMessage(MultipartFile file){
        return new StringBuilder()
                .append("Your file: ")
                .append(file.getOriginalFilename())
                .append(" was successfully signing! ")
                .append("The key and the signature are located next to the application's war archive")
                .toString();
    }
}