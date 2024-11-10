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
import com.example.snapstory.ui.theme.TextField_Gray
import com.example.snapstory.ui.theme.TextField_White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavController, globalViewModel: GlobalViewModel = viewModel()) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var emailError by remember { mutableStateOf(false) }
    var passwordError by remember { mutableStateOf(false) }
    var passwordVisible by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // 상단 뒤로가기 버튼과 제목 중앙 정렬
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
                text = "로그인",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // 이메일 입력 필드
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
            onValueChange = { email = it },
            placeholder = { Text("이메일을 입력해주세요.", color = TextField_Gray, fontFamily = Pretendard, fontSize = 12.sp, fontWeight = FontWeight.Medium) },
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
            shape = RoundedCornerShape(8.dp) // 모서리 둥글게 설정
        )
        if (emailError) {
            Text(
                text = "이메일 형식이 올바르지 않습니다.",
                color = Error,
                style = TextStyle(fontSize = 14.sp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 비밀번호 입력 필드
        Text(
            text = "비밀번호",
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

        Spacer(modifier = Modifier.height(8.dp))

        // 회원가입 및 비밀번호 찾기 링크 (중앙 정렬)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextButton(onClick = { navController.navigate(Screen.SignUp.route) }) {
                Text(
                    text = "회원가입",
                    color = Color.Black,
                    fontFamily = Pretendard,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )
            }
            Spacer(modifier = Modifier.width(70.dp))
            TextButton(onClick = { navController.navigate(Screen.PassWord.route)}) {
                Text(
                    text = "비밀번호 변경",
                    color = Color.Black,
                    fontFamily = Pretendard,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        // 오류 메시지 (예시로 간단히 추가)
        if (emailError || passwordError) {
            Text(
                text = "이메일 또는 비밀번호가 맞지 않습니다.",
                color = Error,
                fontSize = 14.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
        }

        // 로그인 버튼
        Button(
            onClick = {
                emailError = email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
                passwordError = password.length < 6
                if (!emailError && !passwordError) {
                    // TODO: 백엔드와 연결하여 이메일 및 비밀번호 확인
                    navController.navigate("home") // 홈 화면으로 이동
                }
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
