package id.co.flipbox.mvvmstarter.utils;

import android.util.Base64;

import java.io.UnsupportedEncodingException;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtUtils
{
    public static String getJwtToken(String payload, SignatureAlgorithm signatureAlgorithm, String secretKey)
    {
        try
        {
            return Jwts.builder()
                    .setPayload(payload)
                    .signWith(signatureAlgorithm, secretKey.getBytes("UTF-8"))
                    .compact();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String getBase64JwtToken(String payload, SignatureAlgorithm signatureAlgorithm, String secretKey)
    {
        try
        {
            return Jwts.builder()
                    .setPayload(payload)
                    .signWith(signatureAlgorithm, Base64.encodeToString(secretKey.getBytes("UTF-8"), Base64.DEFAULT))
                    .compact();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
    }
}
