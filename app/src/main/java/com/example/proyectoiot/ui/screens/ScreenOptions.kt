package com.example.proyectoiot.ui.screens

import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.proyectoiot.MQTTmanager
import com.example.proyectoiot.R
import com.example.proyectoiot.ui.theme.GreenBlue
import com.example.proyectoiot.ui.theme.LightYellow
import com.example.proyectoiot.ui.theme.Pink
import java.util.UUID

@Composable
fun ScreenOptions(navController: NavController, mqttManager: MQTTmanager) {
    val FontKaushan = FontFamily(Font(R.font.kaushan_script_regular))
    val FontMontserrat = FontFamily(Font(R.font.montserrat_wght))
    var clicked by remember { mutableStateOf(false) }

    Box(){
        if(clicked){
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 150.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_light),
                    contentDescription = "Background Image",
                    modifier = Modifier
                        .size(3000.dp)
                        .graphicsLayer(alpha = 0.7f),
                    tint = Color.Unspecified
                )
            }
        }

        Column(
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 30.dp),

            ) {
            Spacer(modifier = Modifier.height(height = 30.dp))

            Text(
                text = "Opciones de luz",
                fontSize = 32.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontFamily = FontKaushan,
            )
        }

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 30.dp),
        ) {

            Column(
            ) {
                IconButton(
                    onClick = {
                        if(clicked == false) {
                            mqttManager.publish("{\"message\": \"ENCENDER\"}")
                        } else {
                            mqttManager.publish("{\"message\": \"APAGAR\"}")
                        }
                        clicked = !clicked

                    }) {

                    Box(){

                    }
                    val tint = if (!clicked) Color.Black else LightYellow
                    val alpha = if (!clicked) 0.7f else 0.9f

                    Icon(
                        painter = painterResource(id = R.drawable.ic_light_on_off),
                        contentDescription = "Bulb Image",
                        modifier = Modifier
                            .width(300.dp)
                            .graphicsLayer(alpha = alpha),
                        tint = tint
                    )
                }
                Spacer(modifier = Modifier.height(height = 40.dp))

                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .padding(horizontal = 30.dp)
                        .height(50.dp)
                        .fillMaxWidth()
                        .background(
                            color = Color.Black,
                            shape = RoundedCornerShape(16.dp)
                        )
                ) {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            text = if (clicked) "ENCENDIDO" else "APAGADO",
                            fontSize = 15.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                        )
                        Icon(
                            modifier = Modifier.padding(start = 10.dp),
                            painter = painterResource(id = if (clicked) R.drawable.ic_light_on else R.drawable.ic_light_off),
                            contentDescription = "Turn Icon",
                            tint = if (clicked) Color.Green else Color.Red,
                        )
                    }
                }
            }
        }
    }

}