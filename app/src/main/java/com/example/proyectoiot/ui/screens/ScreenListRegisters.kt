package com.example.proyectoiot.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.example.proyectoiot.R
import com.example.proyectoiot.ui.pagging.PagingItemCard
import com.example.proyectoiot.ui.pagging.PagingViewData
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ScreenListRegisters(
    navController: NavController,
    viewModel: PagingViewData
){
    val FontKaushan = FontFamily(Font(R.font.kaushan_script_regular))
    val FontMontserrat = FontFamily(Font(R.font.montserrat_wght))

    Box(){
        Column( verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 30.dp),
        ) {
            Spacer(modifier = Modifier.height(height = 30.dp))

            Text(
                text = "Registros",
                fontSize = 32.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontFamily = FontKaushan,
            )

            val data = viewModel.sensorPagingFlow.collectAsLazyPagingItems()
            val state by viewModel.isLoading.collectAsState()
            val refreshState = rememberSwipeRefreshState(isRefreshing  = state)
            val context = LocalContext.current

            LaunchedEffect(key1 = data.loadState) {
                if (data.loadState.refresh is LoadState.Error) {
                    Toast.makeText(
                        context,
                        "Error: " + (data.loadState.refresh as LoadState.Error).error.message,
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 15.dp, bottom = 55.dp)
            ) {

                if (data.loadState.refresh is LoadState.Loading) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .size(50.dp),
                        color = Color.White,
                        strokeWidth = 8.dp,
                    )
                } else {

                    SwipeRefresh(
                        state = refreshState,
                        onRefresh = viewModel::pauseFlow,
                    ) {
                        LazyColumn(
                            verticalArrangement = Arrangement.spacedBy(6.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,

                        ) {
                            items(data) { pagingObject ->
                                if (pagingObject != null) {
                                    PagingItemCard(pagingObject)
                                }
                            }

                            item {
                                if (data.loadState.append is LoadState.Loading) {
                                    CircularProgressIndicator(
                                        modifier = Modifier.size(40.dp),
                                        color = Color.White,
                                        strokeWidth = 7.dp,
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

