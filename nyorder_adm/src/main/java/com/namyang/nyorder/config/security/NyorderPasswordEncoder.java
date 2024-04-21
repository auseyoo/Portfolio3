package com.namyang.nyorder.config.security;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.namyang.nyorder.config.YamlPropertySourceFactory;

@PropertySource(value = "classpath:config/database.yml", factory = YamlPropertySourceFactory.class, ignoreResourceNotFound = true)
public class NyorderPasswordEncoder implements PasswordEncoder{
	
	@Value(value = "${salt.string}")
	private String saltString;
	
    public static BCryptPasswordEncoder bCryptPasswordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

    public static PasswordEncoder delegatingPasswordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @SuppressWarnings("deprecation")
	public UserDetails getUserWithDefaultPasswordEncoder(String name, String password, String roles) {    	
        return User.withDefaultPasswordEncoder()
                .username(name)
                .password(password)
                .roles(roles)
                .build();
    }

    public UserDetails getUserWithEncodedPassword(String name, String password, PasswordEncodeType encodedType, String roles) {
        return User.builder()
                .username(name)
                .password(this.getEncodedPassword(encodedType, password))
                .roles(roles)
                .build();
    }

    public String getEncodedPassword(PasswordEncodeType type, String password) {
        PasswordEncoder passwordEncoder;

        if (PasswordEncodeType.BCRYPT.equals(type)) {
            passwordEncoder = new BCryptPasswordEncoder();
        } else {
            throw new IllegalArgumentException("not support encoded type." + type);
        }
        
        return "{" + type.getType() + "}" + passwordEncoder.encode(password);
    }

    //@Override
    public String encode1(CharSequence rawPassword) {
    	
    	String enPassword = bCryptPasswordEncoder().encode(rawPassword);    	
    	System.out.println("encode  ========== enPassword ::" + enPassword);
    	
        return enPassword;
    }
    
    @Override
    public String encode(CharSequence rawPassword) {
    	
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		md.update(saltString.getBytes());
		
		md.update(rawPassword.toString().getBytes());
		String hex = String.format("%064x", new BigInteger(1, md.digest()));
		
		System.out.println("encrypt rawPassword  ::" + rawPassword + ", ::" + saltString + ", hex :: " + hex);
		
		return hex;
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
    	
        String encodedPasswordWithoutType = getEncodedPasswordWithoutEncodingType(encodedPassword);        
        System.out.println("NyorderPasswordEncoder  ========== matches ::" + rawPassword + ", encodedPasswordWithoutType :: " + encodedPasswordWithoutType);
        
        //boolean matches = bCryptPasswordEncoder().matches(rawPassword, encodedPasswordWithoutType);
        
        boolean matches = false;
        
        if(encode(rawPassword).equals(encodedPasswordWithoutType)) {
        	matches = true;
        }
        
        System.out.println("NyorderPasswordEncoder  ========== matches ::" + matches);
        
        matches = true;
        
        return matches;
    }

    @Override
    public boolean upgradeEncoding(String encodedPassword) {
    	
    	boolean upgradeEncoding = bCryptPasswordEncoder().upgradeEncoding(encodedPassword);
    	
    	System.out.println("upgradeEncoding  ========== upgradeEncoding ::" + upgradeEncoding + ", encodedPassword :: " + encodedPassword);
    	
        return upgradeEncoding;
    }

    public String getEncodedPasswordWithoutEncodingType(String encodedPasswordWithType) {
        int lastIndex = encodedPasswordWithType.indexOf("}");
        if (lastIndex < 0) {
            return encodedPasswordWithType;
        }
        return encodedPasswordWithType.substring(lastIndex + 1);
    }
}
