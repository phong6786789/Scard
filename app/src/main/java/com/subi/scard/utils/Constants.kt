package com.subi.scard.utils

object Constants {
    const val BASE_URL = "http://192.168.1.29:8083/scard/"
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
        const val SHOW = "Thông tin"
    }

    object ITEM_TYPE{
        const val SOCIAL = "SOCIAL"
        const val INFO = "INFO"
        const val BANK = "BANK"
        const val HEALTH = "HEALTH"
        const val FRIEND = "FRIEND"
    }

    object INFO_TYPE{
        const val INFO = "INFO"
    }

    object BANK_TYPE{
        const val SACOMBANK = "SACOMBANK"
    }

    object HEALTH_TYPE{
        const val GIA_DINH = "Gia Định"
    }

    object FRIEND_TYPE{
        const val FRIEND = "FRIEND"
        const val PHONG_GA = "Phong Gà"
    }


    object SOCIAL_TYPE{
        const val FACEBOOK = "Facebook"
        const val ZALO = "Zalo"
        const val YOUTUBE = "Youtube"
        const val INSTAGRAM = "Instagram"
        const val SKYPE = "Skype"
        const val EMAIL = "Email"
        const val TWITTER = "Twitter"
        const val TIKTOK = "Tiktok"

    }
}