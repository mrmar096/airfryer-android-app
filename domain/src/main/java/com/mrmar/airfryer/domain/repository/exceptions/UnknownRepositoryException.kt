package com.mrmar.airfryer.domain.repository.exceptions

private const val CODE = -1
private const val MESSAGE: String = "Unknown error in repository network call"

object UnknownRepositoryException : RepositoryException(CODE, MESSAGE)