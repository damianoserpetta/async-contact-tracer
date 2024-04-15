package com.example.contacttracer.data.api

import com.example.contacttracer.config.DBConfig
import com.example.contacttracer.data.model.Contact
import com.example.contacttracer.data.model.Device
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface ContactTracerDBService {
    // TODO Rimettere apiKey
    @GET(DBConfig.devicesEndpoint)
    suspend fun getAllDevice(
        @Header("Authorization") accessToken: String
    ): Response<List<Device>>

    @GET(DBConfig.devicesEndpoint)
    suspend fun getDevice(
        @Header("Authorization") accessToken: String,
        @Query("id") id: String
    ): Response<Device>

    @POST(DBConfig.devicesEndpoint)
    suspend fun insertDevice(
        @Header("Authorization") accessToken: String,
        @Body device: Device
    ): Response<Device>

    @PATCH(DBConfig.devicesEndpoint)
    suspend fun patchDevice(
        @Header("Authorization") accessToken: String,
        @Query("id") id: String,
        @Body device: Device
    ): Response<Device>

    @DELETE(DBConfig.devicesEndpoint)
    suspend fun deleteDevice(
        @Header("Authorization") accessToken: String,
        @Query("id") id: String
    ): Response<Device>

    @GET(DBConfig.contactsEndpoint)
    suspend fun getAllContact(
        @Header("Authorization") accessToken: String,
        @Query("deviceId") deviceId: String
    ): Response<List<Contact>>

    @GET(DBConfig.contactsEndpoint)
    suspend fun getContact(
        @Header("Authorization") accessToken: String,
        @Query("id") id: String
    ): Response<Contact>

    @POST(DBConfig.contactsEndpoint)
    suspend fun insertContact(
        @Header("Authorization") accessToken: String,
        @Body contact: Contact
    ): Response<Contact>

    @PATCH(DBConfig.contactsEndpoint)
    suspend fun patchContact(
        @Header("Authorization") accessToken: String,
        @Query("id") id: String,
        @Body contact: Contact
    ): Response<Contact>

    @DELETE(DBConfig.contactsEndpoint)
    suspend fun deleteContact(
        @Header("Authorization") accessToken: String,
        @Query("id") id: String
    ): Response<Contact>
}