package com.chris.bullseye

import org.jasypt.util.text.BasicTextEncryptor

/**
 * @author Chris
 * @date 2021-10-26 13:50
 */
class EncryptTest

fun main(args: Array<String>) {
    var textEncryptor = BasicTextEncryptor();
    textEncryptor.setPassword("EbfYkitspO235lKFNXI50JMXoaxZTKJ0");
    var url = textEncryptor.encrypt("");
    var name = textEncryptor.encrypt("Eixa2RiACvy6wcd4");
    var password = textEncryptor.encrypt("service@zhouhongxing.cn");
//解密内容
//        var url = textEncryptor.decrypt("");
//        var name = textEncryptor.decrypt("");
//        var password = textEncryptor.decrypt("EbfYkitspO235lKFNXI50JMXoaxZTKJ0");


    println(url + "----------------");
    println(name + "----------------");
    println(password + "----------------");
}
