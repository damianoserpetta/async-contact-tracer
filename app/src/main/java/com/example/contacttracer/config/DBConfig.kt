package com.example.contacttracer.config

class DBConfig {
    companion object {
        const val host: String = "http://192.168.1.150"//"http://192.168.1.112"
        const val port: Int = 3000
        const val dbName: String = ""
        const val devicesTableName: String = "users"
        const val contactsTableName: String = "contacts"
        const val devicesEndpoint: String = "/users"
        const val contactsEndpoint: String = "/contacts"


        fun getBaseUrl(): String {
            return ("$host:$port")
        }
    }
}