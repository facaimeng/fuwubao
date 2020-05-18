package com.cbai.common.utils;  
  
  
import java.io.UnsupportedEncodingException;
import java.util.Random;  
  
  
/** 
 * 邀请码工具类 
 * @author cx 
 * 
 */  
@SuppressWarnings("all")  
public class InvitationCodeUtil {  
      
    private InvitationCodeUtil(){  
          
    }  
    /** 
     * 序列化编码字典 
     * 从左往右 在没有出现扰码前，都为正常序列化编码 
     */  
    private static final char[] SERIALCODEDICT = new char[]{'q', 'w', '8', 'a',  '2', 'd', 'x', '9','o', 'c', '7', '5', 'i', 'k', '3', 'm', 'j', 'u', 'f', 'r', '4', 'v', 'y', 'l', 't', 'n', '6', 'b', 'g', 'h'};  
  
  
    /** 
     * 扰码  
     * remark ： 出现这些字符后的剩余字符 则统统为 随机字符 
     */  
    private static final char[] SCRAMBLERDICT = new char[]{'s','z','$','e','p','0','1'};  
  
  
    /** 
     * 全部字典 
     */  
    private static final char[] AllDICT = new char[]{'q', 'w','8', 'a', '2', 'd','x', '9','o', 'c', '7', '5', 'i', 'k', '3', 'm', 'j', 'u', 'f', 'r', '4', 'v', 'y', 'l', 't', 'n', '6', 'b', 'g', 'h','s','z','e','p','0','1'};  
      
    /** 
     * ?进制   
     * remark ：SERIALCODEDICT.length 位进1 
     */  
    private static final int BINLEN = SERIALCODEDICT.length;  
  
  
    /**  
     * 邀请码最小长度  
     */  
    private static final int SERIALCODELENGTH = 6;  
  
  
    /** 
     * 根据ID生成  SERIALCODELENGTH-32个字节的随机序列化字符串 
     * @param id ID 
     * @return 随机码 
     */  
    public static String toSerialCode(long id) {  
          
        //存储得到的真实序列化id后的编码 最长32个字符  
        final char[] _codes = new char[32];  
          
        // 下标  
        int index = _codes.length ;  
         
        //id大于进制位数则进入循环  
        while(id > BINLEN) {  
              
            // id与位数的余数  10 /33 = 10  
            int residue = (int) (id % BINLEN);  
              
            // 保存序列化后的字符  
            _codes[--index]=SERIALCODEDICT[residue];  
              
            id /= BINLEN;  
        }  
          
        _codes[--index] = SERIALCODEDICT[(int)(id % BINLEN)];  
          
        String serialCode = new String(_codes, index, (32 - index));  
          
        // 不够长度的自动随机补全  
        if(serialCode.length() < SERIALCODELENGTH) {  
              
            Random random =new Random();  
            //扰码  
            serialCode += SCRAMBLERDICT[random.nextInt(SCRAMBLERDICT.length)];  
              
            for(int i=1; serialCode.length() < SERIALCODELENGTH ; i++) {  
                //扰码  
                if (random.nextInt(3)==1) {  
                    serialCode = SCRAMBLERDICT[random.nextInt(SCRAMBLERDICT.length)]+serialCode;  
                }else{  
                    serialCode += AllDICT[random.nextInt(AllDICT.length)];  
                }  
            }  
        }  
        return serialCode;  
    }  
  
  
   
    /** 
     * 将序列化好的邀请码进行解码操作 
     * @param code 
     * @return 
     */  
    public static long serialCodeToId(String serialCode) {  
          
        final char[] _codes = serialCode.toCharArray();  
          
        //排除左边的扰码 找到正常码开始的下标  
          
        long id = 0L;  
          
        //从左往右  扰码是否结束  
        boolean scramblerIsEnd = false;  
          
        for(int i=0; i < _codes.length; i++) {  
              
            //排除前面扰码部分  
            if (!scramblerIsEnd) {  
                //如果是扰码  直接跳过本次循环  
                if (isScrambler(_codes[i])) {  
                    continue;  
                }else{  
                    scramblerIsEnd = true;  
                }  
            }  
              
            //如果是扰码  直接停止循环  
            if(isScrambler(_codes[i])) {  
                break;  
            }  
  
  
            //找到对应编码下标  
            int index = 0;  
              
            for(int j=0; j < BINLEN; j++) {  
                  
                if(_codes[i] == SERIALCODEDICT[j]) {  
                    index = j;  
                    break;  
                }  
            }  
            if(i > 0) {  
                id = id * BINLEN + index;  
            } else {  
                id = index;  
            }  
        }  
        return id;  
    }  
  
  
    /** 
     * 是否是扰码 
     * @param scramblerCode 
     * @return 
     */  
    private static Boolean isScrambler(char scramblerCode){  
        for (char _code : SCRAMBLERDICT) {  
            if (scramblerCode == _code) {  
                return true;  
            }  
        }  
        return false;  
    }  
    public static void main(String[] args)  {
    	
    	InvitationCodeUtil s = new InvitationCodeUtil();
    	
    	System.out.println(s.toSerialCode(1));
    	
    	System.out.println(s.serialCodeToId("w$t67y"));
    	
    }
}  