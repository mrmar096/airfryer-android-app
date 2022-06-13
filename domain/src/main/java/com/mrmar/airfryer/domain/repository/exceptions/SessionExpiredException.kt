package com.mrmar.airfryer.domain.repository.exceptions

private const val MESSAGE: String = "Session expired"
private const val CODE = 401

object SessionExpiredException : RepositoryException(CODE, MESSAGE)