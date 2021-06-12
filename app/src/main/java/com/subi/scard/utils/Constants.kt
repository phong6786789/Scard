package com.subi.scard.utils

object Constants {
    const val BASE_URL = "http://192.168.1.27/scard/"
    object USER{
        const val ID_USER = "idUser"
        const val STATUS = "status"
    }

    object TITLE{
        const val HOME = "S-CARD"
        const val SETTINGS = "Cài đặt"
        const val QR = "Quét QR S-CARD"
        const val FRIENDS = "Bạn bè"
        const val MXH = "Mạng xã hội"
        const val BANK = "Ngân hàng"
        const val INFO = "Cá nhân"
        const val HEALTH = "Y tế"
        const val MORE = "Chức năng khác"
    }

    object ITEM_TYPE{
        const val SOCIAL = "SOCIAL"
        const val INFO = "INFO"
        const val ATM = "ATM"
        const val HEALTH = "HEALTH"
    }

    object SOCIAL_TYPE{
        const val FACEBOOK = "facebook"
        const val ZALO = "zalo"
        const val YOUTUBE = "youtube"
        const val INSTAGRAM = "instagram"
        const val SKYPE = "skype"
        const val EMAIL = "email"
        const val TWITTER = "twitter"
        const val TIKTOK = "tiktok"


    }
}