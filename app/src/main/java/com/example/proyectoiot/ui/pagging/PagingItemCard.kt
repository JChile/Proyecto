package com.example.proyectoiot.ui.pagging

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyectoiot.R
import com.example.proyectoiot.ui.composables.ObjectData


val FontMontserrat = FontFamily(Font(R.font.montserrat_wght))


fun getLuminosity(lum: Int, third: Int): Int {
    return when {
        lum >= third * 2 -> 3
        lum >= third -> 2
        else -> 1
    }
}

@Composable
fun PagingItemCard(paggingobject: ObjectData) {
    val maxValue = 255
    val third = maxValue / 3
    val lum = paggingobject.DeviceData.luminosity

    Card(
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Black,
            contentColor = Color.White,
        ) ,
        modifier = Modifier.fillMaxWidth(),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .height(90.dp)
                .padding(horizontal = 20.dp),
        ){
            Column(

            ) {
                Row() {
                    Text(
                        text = "Fecha: ",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontMontserrat,
                    )
                    Text(
                        text = "${paggingobject.DeviceData.calendar}",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontMontserrat,
                    )
                }
                Spacer(modifier = Modifier.height(5.dp))

                Row(
                    modifier = Modifier.padding(start = 12.dp)
                ) {
                    Text(
                        text = "Hora: ",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = FontMontserrat,

                        color = Color.LightGray,

                        )
                    Text(
                        text = "${paggingobject.DeviceData.hour}",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = FontMontserrat,
                        color = Color.LightGray,
                    )
                }
                Spacer(modifier = Modifier.height(5.dp))
                Row(
                    modifier = Modifier.padding(start = 12.dp)
                ) {
                    Text(
                        text = "Luminosidad: ",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = FontMontserrat,
                        color = Color.LightGray,
                    )
                    Text(
                        text = "${paggingobject.DeviceData.luminosity} lum",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = FontMontserrat,
                        color = Color.LightGray,
                    )
                }

            }
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(end = 5.dp),
                contentAlignment = Alignment.CenterEnd
            ) {
                Icon(
                    painter = painterResource(id = if(lum >= third * 2) R.drawable.ic_high else if (lum >= third) R.drawable.ic_medium  else R.drawable.ic_low ),
                    modifier = Modifier.size(50.dp),
                    contentDescription = "Turn Icon",
                    tint = if(lum >= third * 2) Color.Red else if (lum >= third) Color.Yellow else Color.Green
                    )
            }

        }

    }

}
