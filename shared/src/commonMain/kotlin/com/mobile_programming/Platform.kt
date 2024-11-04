package com.mobile_programming

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform