package com.subi.scard.utils

object Constants {
    //    const val BASE_URL = "http://192.168.1.29:8083/scard/"
    const val BASE_URL = "http://192.168.1.177/scard/"
    object USER {
        const val ID_USER = "idUser"
        const val FULLNAME = "FULLNAME"
        const val STATUS = "status"
    }

    const val HOTEN = "HOTEN"

    object TITLE {
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

    object ITEM_TYPE {
        const val SOCIAL = "SOCIAL"
        const val INFO = "INFO"
        const val BANK = "BANK"
        const val HEALTH = "HEALTH"
        const val FRIEND = "FRIEND"
        const val AVATAR = "AVATAR"
    }

    object INFO_TYPE {
        const val INFO = "INFO"
        const val CCCD = "CĂN CƯỚC CÔNG DÂN"
        const val PASSPORT = "PASSPORT"
    }

    object BANK_TYPE {
        const val SACOMBANK = "SACOMBANK"
        const val VIETCOMBANK = "VIETCOMBANK"
    }

    object HEALTH_TYPE {
        const val BHYT = "Bảo hiểm y tế"
        const val CSSK = "Chăm sóc sức khoẻ"
        const val COVI = "Phiếu tiêm chủng Vacxin"
        const val TEST = "Giấy đã test Covid"
    }

    object FRIEND_TYPE {
        const val FRIEND = "FRIEND"
        const val PHONG_GA = "Phong Gà"
    }


    object SOCIAL_TYPE {
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