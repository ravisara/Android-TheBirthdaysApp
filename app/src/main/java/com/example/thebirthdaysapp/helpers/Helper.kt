package com.example.thebirthdaysapp.helpers

import java.time.OffsetDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

fun convertISO8601DateTimeStringToHumanReadableUTCDateTimeString(dateTimeStringInISO8601: String): String {

    val offsetDateTime = OffsetDateTime.parse(dateTimeStringInISO8601)
    val dateTimeInUTC = offsetDateTime.withOffsetSameInstant(ZoneOffset.UTC)

    val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
    return dateTimeInUTC.format(formatter)

}

// As initials and the full name are always needed together, this approach is used.
data class InitialsAndNameToShow(var initialsToShow:String, var nameToShow: String)
fun getNameAndInitialsToShow(firstName:String, lastName: String): InitialsAndNameToShow {
    val initials = firstName.first().uppercase() + lastName.first().uppercase()
    val name = "$firstName $lastName"
    return InitialsAndNameToShow(initials, name)
}

fun getAgeTextToShow(age: Int): String {
    return "$age YEARS OLD"
}