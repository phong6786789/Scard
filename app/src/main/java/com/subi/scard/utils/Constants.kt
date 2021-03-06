package com.subi.scard.utils

object Constants {

//    const val BASE_URL = "http://192.168.1.29:8083/scard/"

    const val BASE_URL = "http://192.168.1.177/scard/"
//    const val BASE_URL = "http://192.168.1.12/scard/" //Tài

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
        const val THEME = "Tuỳ biến giao diện"
    }

    object THEME_TYPE {
        const val QR_CARD = "QR_CARD"
    }

    object THEME_COLOR {
        const val THEME_1 = "THEME_1"
        const val THEME_2 = "THEME_2"
        const val THEME_3 = "THEME_3"
        const val THEME_4 = "THEME_4"
        const val THEME_5 = "THEME_5"
        const val THEME_6 = "THEME_6"
        const val THEME_7 = "THEME_7"
        const val THEME_8 = "THEME_8"
        const val THEME_9 = "THEME_9"
        const val THEME_10 = "THEME_10"
        const val THEME_11 = "THEME_11"
        const val THEME_12 = "THEME_12"
        const val THEME_13 = "THEME_13"
        const val THEME_14 = "THEME_14"
        const val THEME_15 = "THEME_15"
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
        const val SACOMBANK = "Sacombank"
        const val AGRIBANK = "Agribank"
        const val VIETTINBANK = "ViettinBank"
        const val VIETCOMBANK = "Vietcombank"
        const val TECHCOMBANK = "Techcombank"
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

    object STATUS {
        const val SUCCESS = "success"
        const val EXIST = "exist"
        const val FAILED = "failed"

    }
}