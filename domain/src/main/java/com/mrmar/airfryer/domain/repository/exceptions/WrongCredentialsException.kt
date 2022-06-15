package com.mrmar.airfryer.domain.repository.exceptions

private const val MESSAGE: String = "Bad Credentials"
private const val CODE = 401

object WrongCredentialsException : RepositoryException(CODE, MESSAGE)