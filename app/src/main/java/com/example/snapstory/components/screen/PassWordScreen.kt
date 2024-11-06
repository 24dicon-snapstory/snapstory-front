package com.example.snapstory.components.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.snapstory.components.icon.CheckChosenIcon
import com.example.snapstory.components.icon.CheckIcon
import com.example.snapstory.components.icon.Close_eye
import com.example.snapstory.components.icon.Eye
import com.example.snapstory.components.icon.LeftButton
import com.example.snapstory.components.typography.Pretendard
import com.example.snapstory.ui.theme.Error
import com.example.snapstory.ui.theme.Green
import com.example.snapstory.ui.theme.TextField_Gray
import com.example.snapstory.ui.theme.TextField_White
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PassWordScreen(navController: NavController) {
    var email by remember { mutableStateOf("") }
    var emailError by remember { mutableStateOf(false) }
    var emailExists by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    var password by remember { mutableStateOf("") }
    var passwordError by remember { mutableStateOf(false) }
    var passwordVisible by remember { mutableStateOf(false) }
    var passwordCheck by remember { mutableStateOf("") }
    var passwordMatchError by remember { mutableStateOf(false) }
    var passwordCheckVisible by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            IconButton(
                onClick = { navController.popBackStack() },
                modifier = Modifier.align(Alignment.CenterStart)
            ) {
                LeftButton(
                    modifier = Modifier
                        .width(20.dp)
                        .height(20.dp)
                )
            }
            Text(
                text = "비밀번호",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "이메일",
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 4.dp),
            color = Color.Black
        )

        TextField(
            value = email,
            onValueChange = { input ->
                email = input
                emailError = !isValidEmail(input)
                if (!emailError) {
                    // 비동기적으로 이메일 존재 확인
                    scope.launch {
                        emailExists = checkEmailExists(email)
                    }
                }
            },
            placeholder = {
                Text(
                    "이메일을 입력해주세요.",
                    color = TextField_Gray,
                    fontFamily = Pretendard,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium
                )
            },
            singleLine = true,
            isError = emailError,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            textStyle = TextStyle(
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp
            ),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = TextField_White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            shape = RoundedCornerShape(8.dp),
            trailingIcon = {
                if (!emailError) {
                    if (emailExists) {
                        CheckChosenIcon(Modifier.size(24.dp)) // 이메일이 존재할 경우 아이콘 변경
                    } else {
                        CheckIcon(Modifier.size(24.dp)) // 기본 아이콘
                    }
                }
            }
        )

//        if (emailError) {
//            Text(
//                text = "이메일 형식이 올바르지 않습니다.",
//                color = Error,
//                style = TextStyle(fontSize = 14.sp)
//            )
//        }
        Spacer(modifier = Modifier.height(16.dp))

        // 비밀번호 입력 필드
        Text(
            text = "비밀번호 변경",
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 4.dp),
            color = Color.Black
        )
        TextField(
            value = password,
            onValueChange = { password = it },
            placeholder = { Text("비밀번호를 입력해주세요.",  color = TextField_Gray, fontFamily = Pretendard, fontSize = 12.sp, fontWeight = FontWeight.Medium) },
            singleLine = true,
            isError = passwordError,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            textStyle = TextStyle(
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp
            ),
            trailingIcon = {
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    if (passwordVisible) {
                        Close_eye(modifier = Modifier.size(24.dp))
                    } else {
                        Eye(modifier = Modifier.size(24.dp))
                    }
                }
            },
            colors = TextFieldDefaults.textFieldColors(
                containerColor =TextField_White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            shape = RoundedCornerShape(8.dp) // 모서리 둥글게 설정
        )
        if (passwordError) {
            Text(
                text = "비밀번호는 6자 이상이어야 합니다.",
                color = Error,
                style = TextStyle(fontSize = 14.sp)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        // 비밀번호 입력 필드
        Text(
            text = "비밀번호 확인",
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 4.dp),
            color = Color.Black
        )
        TextField(
            value = passwordCheck,
            onValueChange = {
                passwordCheck = it
                var passwordMatchError = password != passwordCheck
            },
            placeholder = { Text("비밀번호를 한 번 더 입력해주세요.", color = TextField_Gray, fontFamily = Pretendard, fontSize = 12.sp, fontWeight = FontWeight.Medium) },
            singleLine = true,
            isError = passwordMatchError,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            visualTransformation = if (passwordCheckVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            textStyle = TextStyle(fontSize = 14.sp),
            trailingIcon = {
                IconButton(onClick = { passwordCheckVisible = !passwordCheckVisible }) {
                    if (passwordCheckVisible) {
                        Close_eye(modifier = Modifier.size(24.dp))
                    } else {
                        Eye(modifier = Modifier.size(24.dp))
                    }
                }
            },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = TextField_White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            shape = RoundedCornerShape(8.dp)
        )
        if (passwordMatchError) {
            Text(
                text = "비밀번호가 일치하지 않습니다.",
                color = Error,
                style = TextStyle(fontSize = 14.sp)
            )
        }

        Spacer(modifier = Modifier.weight(1f))


//        Button(
//            onClick = {
//                emailError = email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
//                passwordError = password.length < 6
//                if (!emailError && !passwordError) {
//                    // TODO: 백엔드와 연결하여 이메일 및 비밀번호 확인
//                    navController.navigate("home") // 홈 화면으로 이동
//                }
//            },
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(48.dp),
//            colors = ButtonDefaults.buttonColors(containerColor = Green),
//            shape = RoundedCornerShape(10.dp)
//        ) {
//            Text(
//                text = "로그인",
//                color = Color.Black,
//                fontFamily = Pretendard,
//                fontWeight = FontWeight.Medium,
//                fontSize = 16.sp
//            )
//        }


        Button(
            onClick = {
                emailError = email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
                passwordError = password.length < 6
//                if (!emailError && !passwordError) {
//                    // TODO: 백엔드와 연결하여 이메일 및 비밀번호 확인
//                    navController.navigate("home") // 홈 화면으로 이동
//                }

                navController.navigate("interests")
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Green),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(
                text = "로그인",
                color = Color.Black,
                fontFamily = Pretendard,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}

// 이메일 형식이 올바른지 확인하는 함수
fun isValidEmail(email: String): Boolean {
    return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
}

// 이메일 존재 여부를 확인하는 함수 (서버 통신 부분 TODO 추가)
suspend fun checkEmailExists(email: String): Boolean {
    delay(500) // 서버 확인 대기 시간 (예제)
    // TODO: 서버와 통신하여 이메일 존재 여부 확인 로직 추가
    return email == "existing@example.com" // 예제에서는 특정 이메일만 유효한 것으로 처리
}