package com.fourio.twynapp.model

class IdentityFeed(
    val identity_feed: List<Requests>
) {

    class Requests(val request_type: String,
                   val request_label: String,
                   val request_date: String,
                   val request_sender: String,
                   val request_sender_logo: String,
                   val request_description: String,){}

}