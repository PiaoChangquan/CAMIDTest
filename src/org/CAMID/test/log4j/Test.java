package org.CAMID.test.log4j;

import org.apache.log4j.Logger;

public class Test {
	private static Logger logger = Logger.getLogger(Test.class);  

    /** 
     * @param args 
     */  
    public static void main(String[] args) {  
        // System.out.println("This is println message.");  

        // ��¼debug�������Ϣ  
        logger.debug("This is debug message.");  
        // ��¼info�������Ϣ  
        logger.info("This is info message.");  
        // ��¼error�������Ϣ  
        logger.error("This is error message.");  
    }  

}