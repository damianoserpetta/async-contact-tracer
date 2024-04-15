package com.example.contacttracer.domain

import java.security.MessageDigest

class HashTool {
    companion object{
        private const val algorithm : String = "SHA-256";
        fun toHash(_input : String):String {
            return MessageDigest.getInstance(algorithm)
                .digest(_input.toByteArray())
                .fold("", { str, it -> str + "%02x".format(it) })
        }
    }
}