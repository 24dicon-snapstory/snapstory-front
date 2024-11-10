package com.example.snapstory.data

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class GlobalViewModel : ViewModel() {

    var email = mutableStateOf("")
    var nickname = mutableStateOf("")
    var password = mutableStateOf("")

    val selectedInterests = mutableStateListOf<String>()


    // 이메일, 닉네임, 비밀번호 업데이트 함수
    fun updateEmail(newEmail: String) {
        email.value = newEmail
        Log.d("GlobalViewModel", "Email updated to: $newEmail")
    }

    fun updateNickname(newNickname: String) {
        nickname.value = newNickname
        Log.d("GlobalViewModel", "Nickname updated to: $newNickname")
    }

    fun updatePassword(newPassword: String) {
        password.value = newPassword
        Log.d("GlobalViewModel", "Password updated to: $newPassword")
    }


    fun addInterest(interest: String) {
        if (!selectedInterests.contains(interest)) {
            selectedInterests.add(interest)
        }
    }

    // 관심사 제거 함수
    fun removeInterest(interest: String) {
        selectedInterests.remove(interest)
    }
}