package com.example.servicebook

class User{
    var username: String = ""
    var mail: String = ""
    var password: String = ""
    var userid: String = ""


    constructor(profilepic: String, username: String, mail: String, password: String, userid: String, lastmessage: String) {

        this.userid = userid
        this.username = username
        this.mail = mail
        this.password = password
    }

    constructor(username: String, mail: String, password: String) {
        this.username = username
        this.mail = mail
        this.password = password
    }

    constructor() // Empty constructor

    // Getter and setter methods are not needed in Kotlin, as properties are automatically generated.

}