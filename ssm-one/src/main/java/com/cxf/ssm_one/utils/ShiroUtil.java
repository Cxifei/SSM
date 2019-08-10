package com.cxf.ssm_one.utils;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * @author always_on_the_way
 * @date 2019-06-27
 */
public class ShiroUtil {

    public static String getSalt(){

        return new SecureRandomNumberGenerator().nextBytes().toHex();
    }

    public static String getPwd(String password,String salt){
        return new SimpleHash(Md5Hash.ALGORITHM_NAME,password,ByteSource.Util.bytes(salt),1024).toHex();
    }


}
