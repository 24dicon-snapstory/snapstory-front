package com.example.snapstory.components.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.snapstory.components.icon.Close_eye
import com.example.snapstory.components.icon.Eye
import com.example.snapstory.components.icon.LeftButton
import com.example.snapstory.components.typography.Pretendard
import com.example.snapstory.data.GlobalViewModel
import com.example.snapstory.navigaiton.Screen
import com.example.snapstory.ui.theme.Green
import com.example.snapstory.ui.theme.Error
import com.example.snapstory.ui.theme.Login
import com.example.snapstory.ui.theme.TextField_Gray
import com.example.snapstory.ui.theme.TextField_White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(navController: NavController, globalViewModel: GlobalViewModel = viewModel()) {
    var email by remember { mutableStateOf("") }
    var nickname by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordCheck by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }
    var termsAgreed by remember { mutableStateOf(false) }
    var passwordVisible by remember { mutableStateOf(false) }
    var passwordCheckVisible by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Column(
            modifier = Modifier.weight(1f),
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
                    text = "회원가입",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // 닉네임 입력 필드
            Text(
                text = "닉네임",
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 4.dp),
                color = Color.Black
            )
            TextField(
                value = nickname,
                onValueChange = { nickname = it },
                placeholder = { Text("닉네임을 입력해주세요.", color = TextField_Gray, fontFamily = Pretendard, fontSize = 12.sp, fontWeight = FontWeight.Medium) },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                textStyle = TextStyle(fontSize = 12.sp),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = TextField_White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                shape = RoundedCornerShape(8.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            // 이메일 입력 필드
            Text(
                text = "이메일",
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 4.dp),
                color = Color.Black
            )
            TextField(
                value = email,
                onValueChange = { email = it },
                placeholder = { Text("이메일을 입력해주세요.", color = TextField_Gray, fontFamily = Pretendard, fontSize = 12.sp, fontWeight = FontWeight.Medium )},
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                textStyle = TextStyle(fontSize = 12.sp),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor =TextField_White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                shape = RoundedCornerShape(8.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            // 비밀번호 입력 필드
            Text(
                text = "비밀번호",
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 4.dp),
                color = Color.Black
            )
            TextField(
                value = password,
                onValueChange = { password = it },
                placeholder = { Text("비밀번호를 입력해주세요.",color = TextField_Gray, fontFamily = Pretendard, fontSize = 12.sp, fontWeight = FontWeight.Medium) },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        if (passwordVisible) {
                            Close_eye(modifier = Modifier.size(24.dp))
                        } else {
                            Eye(modifier = Modifier.size(24.dp))
                        }
                    }
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                textStyle = TextStyle(fontSize = 12.sp),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor =TextField_White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                shape = RoundedCornerShape(8.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            // 비밀번호 확인 입력 필드
            Text(
                text = "비밀번호 확인",
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 4.dp),
                color = Color.Black
            )
            TextField(
                value = passwordCheck,
                onValueChange = { passwordCheck = it },
                placeholder = { Text("비밀번호를 한 번 더 입력해주세요.", color = TextField_Gray, fontFamily = Pretendard, fontSize = 12.sp, fontWeight = FontWeight.Medium)},
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                visualTransformation = if (passwordCheckVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    IconButton(onClick = { passwordCheckVisible = !passwordCheckVisible }) {
                        if (passwordCheckVisible) {
                            Close_eye(modifier = Modifier.size(24.dp))
                        } else {
                            Eye(modifier = Modifier.size(24.dp))
                        }
                    }
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                textStyle = TextStyle(fontSize = 12.sp),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor =TextField_White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                shape = RoundedCornerShape(8.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // 로그인 안내
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("이미 계정이 있나요?", color = Color.Black, fontFamily = Pretendard, fontWeight = FontWeight.Medium)
                TextButton(onClick = { navController.navigate(Screen.Login.route) }) {
                    Text("로그인", color = Login)
                }
            }
        }

//        // 오류 메시지
//        if (errorMessage.isNotEmpty()) {
//            Text(
//                text = errorMessage,
//                color = Error,
//                fontSize = 12.sp,
//                fontFamily = Pretendard,
//                fontWeight = FontWeight.Medium,
//                modifier = Modifier.padding(vertical = 8.dp)
//            )
//        }

        // 회원가입 버튼
        Button(
            onClick = {  globalViewModel.updateEmail(email)
                globalViewModel.updateNickname(nickname)
                globalViewModel.updatePassword(password)
                navController.navigate("interests")},
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .align(Alignment.CenterHorizontally),
            colors = ButtonDefaults.buttonColors(containerColor = Green),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(
                text = "계정 만들기",
                color = Color.Black,
                fontFamily = Pretendard,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}

